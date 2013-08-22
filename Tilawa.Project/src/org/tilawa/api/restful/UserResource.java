package org.tilawa.api.restful;

import java.net.URI;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.tilawa.internal.constants.C;
import org.tilawa.internal.entities.User;
import org.tilawa.internal.entities.UserCollection;
import org.tilawa.internal.services.UserServices;


/**
 * Restful API to manipulate a User
 * @author m.sharif
 */

@Path("/v1/user")
public class UserResource {

	private static final Logger log = Logger.getLogger(UserResource.class.getSimpleName());
	private final UserServices userServices = new UserServices();
	
	/**
	 * Function to save a users
	 * 
	 * curl -v -d "country=Saudi&name=Mussab&email=mosabsalih@hotmail.com&password=1234&type=STUDENT" http://tilawaapp.appspot.com/v1/user/save
	 */
	@Path("/save")
	@POST
	public synchronized Response saveUser(@FormParam("country") String country,
										 @FormParam("name") String name,
										 @FormParam("email") String email,
										 @FormParam("password") String password,
										 @FormParam("type") C.UserType type,
										 @Context UriInfo uriInfo) {	
		
		// TODO [H] We should add validations to check if every field we need is present
		Response theResponse = null;

		try { 
			
			User tempUser = new User();
			tempUser.setName(name);
			tempUser.setEmail(email);
			tempUser.setCountry(country);
			tempUser.setPassword(password);
			tempUser.setUserType(type);
			
			User savedCustomer = userServices.createUser(tempUser);
			// Creating the location URI for a user
			URI location  = new URI(uriInfo.getBaseUri().toString() + "v1/user/" +  savedCustomer.getId());

			theResponse = Response.created(location).build();


		} catch(Throwable t) {

			theResponse = Response.status(Response.Status.BAD_REQUEST).build();
			log.severe("Error happend when creating a user .. ");
			log.log(Level.SEVERE,"Throwable",t);

		}

		return theResponse;
	}
	
	/**
	 * Function to get a user information 
	 * 
	 * curl -v http://tilawaapp.appspot.com/v1/user/agtzfnRpbGF3YWFwcHIRCxIEVXNlchiAgICAgICACgw
	 */
	@Path("/{userId}")
	@GET
	@Produces({"application/xml","application/json"})
	
	public Response getUser(@PathParam("userId") String id) {
		
	Response theResponse = null;
		
		try {
							
			User theUser = userServices.getUser(id);
				
			if(theUser != null) {
				
				theResponse = Response.ok(theUser).build();
				
			} else {
				
				theResponse = Response.status(Response.Status.NOT_FOUND).build();
			}
			
		} catch(Throwable t) {
			
			theResponse = Response.serverError().build();
			log.severe("Error happend when get a user information.. ");
			log.log(Level.SEVERE,"Throwable",t);	
		}
		
		return theResponse;
	}
	
	/**
	 * Function to get all users 
	 * 
	 * curl -v http://tilawaapp.appspot.com/v1/user/all
	 */
	@SuppressWarnings("unchecked")
	@Path("/all")
	@GET
	@Produces({"application/xml","application/json"})
	public Response getAllUsers() {
		
	Response theResponse = null;
		
		try {
			
			Collection<Object> theCollection = userServices.getAllUsers();
				
			if(theCollection.size() > 0) {
				
				UserCollection theUserCollection = new UserCollection();
				theUserCollection.setUsers((Collection)theCollection);
				
				theResponse = Response.ok(theUserCollection).build();
				
			} else {
				
				theResponse = Response.status(Response.Status.NOT_FOUND).build();
			}
				
			
		} catch(Throwable t) {
			
			theResponse = Response.serverError().build();
			log.severe("Error happend when get all users information.. ");
			log.log(Level.SEVERE,"Throwable",t);
			
		}
		
		return theResponse;
	}
	
		
}
