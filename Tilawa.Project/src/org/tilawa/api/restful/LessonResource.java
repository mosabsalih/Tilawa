package org.tilawa.api.restful;

import java.net.URI;
import java.util.Collection;
import java.util.Date;
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
import org.tilawa.internal.entities.LessonCollection;
import org.tilawa.internal.entities.QuranLesson;
import org.tilawa.internal.services.LessonServices;




/**
 * Restful API to manipulate a Quran Lesson
 * @author m.sharif
 */

@Path("/v1/lesson")
public class LessonResource {

	private static final Logger log = Logger.getLogger(LessonResource.class.getSimpleName());
	private final LessonServices lessonServices = new LessonServices(); 
	
	/**
	 * Function to save a lesson
	 * 
	 */
	@Path("/save")
	@POST
	public synchronized Response saveLesson(@FormParam("name") String name,
										   @FormParam("start") String startDate,
										   @FormParam("end") String endDate,
										   @FormParam("leap") int leap,
										   @Context UriInfo uriInfo) {	
		
		// TODO [H] We should add validations to check if every field we need is present
		Response theResponse = null;

		try { 
			
			QuranLesson tempLesson = new QuranLesson();
			
			tempLesson.setLessonName(name);
			tempLesson.setStartDate(startDate);
			tempLesson.setEndDate(endDate);
			tempLesson.setLeapCounter(leap);
			
			QuranLesson savedLesson = lessonServices.createLesson(tempLesson);


			// Creating the location URI for a class
			URI location  = new URI(uriInfo.getBaseUri().toString() + "v1/lesson/" +  savedLesson.getId());

			theResponse = Response.created(location).build();


		} catch(Throwable t) {

			theResponse = Response.status(Response.Status.BAD_REQUEST).build();
			log.severe("Error happend when creating a lesson .. ");
			log.log(Level.SEVERE,"Throwable",t);

		}

		return theResponse;
	}
	
	/**
	 * Function to get a lesson information 
	 * 
	 */
	@Path("/{lessonId}")
	@GET
	@Produces({"application/xml","application/json"})
	
	public Response getLesson(@PathParam("lessonId") String id) {
		
	Response theResponse = null;
		
		try {
							
			QuranLesson theClass = lessonServices.getLesson(id);
				
			if(theClass != null) {
				
				theResponse = Response.ok(theClass).build();
				
			} else {
				
				theResponse = Response.status(Response.Status.NOT_FOUND).build();
			}
			
		} catch(Throwable t) {
			
			theResponse = Response.serverError().build();
			log.severe("Error happend when get a lesson information.. ");
			log.log(Level.SEVERE,"Throwable",t);	
		}
		
		return theResponse;
	}
	
	/**
	 * Function to get all lessons 
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Path("/all")
	@GET
	@Produces({"application/xml","application/json"})
	public Response getAllLessons() {
		
	Response theResponse = null;
		
		try {
			
			Collection<Object> theCollection = lessonServices.getAllLessons();
				
			if(theCollection.size() > 0) {
				
				LessonCollection theUserCollection = new LessonCollection();
				theUserCollection.setQuranLessons((Collection)theCollection);
				
				theResponse = Response.ok(theUserCollection).build();
				
			} else {
				
				theResponse = Response.status(Response.Status.NOT_FOUND).build();
			}
				
			
		} catch(Throwable t) {
			
			theResponse = Response.serverError().build();
			log.severe("Error happend when get all lesson information.. ");
			log.log(Level.SEVERE,"Throwable",t);
			
		}
		
		return theResponse;
	}
	
		
}
