package org.tilawa.api.restful;

import java.net.URI;
import java.net.URLDecoder;
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

import org.tilawa.internal.entities.Verse;
import org.tilawa.internal.entities.VerseCollection;
import org.tilawa.internal.entities.VerseExtra;
import org.tilawa.internal.services.VerseServices;

import com.google.appengine.api.datastore.Text;




/**
 * Restful API to manipulate verse data 
 * @author m.sharif
 */

@Path("/v1/verse")
public class VerseResource {

	private static final Logger log = Logger.getLogger(VerseResource.class.getSimpleName());
	private final VerseServices verseServices = new VerseServices(); 
	
	/**
	 * Function to create verse
	 * 
	 */
	@Path("/create")
	@POST
	public synchronized Response createVerse(@FormParam("verse") String verse,
											@FormParam("verseno") int verseNo,
											@FormParam("suratno") int suratNo,
											@Context UriInfo uriInfo) {	
		
		// TODO [H] We should add validations to check if every field we need is present
		Response theResponse = null;

		try { 
			

			Verse tempVerse = new Verse();
			
			String theVerse = URLDecoder.decode(verse, "UTF-8");
			
			if(theVerse.length() > 500) {
				
				VerseExtra extra = new VerseExtra();
				
				extra.setVerse(new Text(theVerse));
				
				VerseExtra tempExtra = verseServices.createVerseExtra(extra);
				
				tempVerse.setLong(true);
				tempVerse.setExtraId(tempExtra.getId());
				tempVerse.setSuratNo(suratNo);
				tempVerse.setVerseNo(verseNo);
				
				
			} else {

				tempVerse.setVerse(theVerse);
				tempVerse.setSuratNo(suratNo);
				tempVerse.setVerseNo(verseNo);
				tempVerse.setLong(false);
				
			}
			
			Verse savedVerse = verseServices.createVerse(tempVerse);
			
			
			// Creating the location URI for a class
			URI location  = new URI(uriInfo.getBaseUri().toString() + "v1/verse/" +  savedVerse.getId());

			theResponse = Response.created(location).build();


		} catch(Throwable t) {

			theResponse = Response.status(Response.Status.BAD_REQUEST).build();
			log.severe("Error happend when saving verse data.. [" + suratNo +"],["+ verseNo + "]" + "[" + verse +"]");
			log.log(Level.SEVERE,"Throwable",t);

		}

		return theResponse;
	}
	
	
	/**
	 * Function to get verses for Surat
	 * 
	 */
	@Path("/{suratNo}")
	@GET
	@Produces({ "application/xml", "application/json" })
	public Response getAllVersesForSurat(@PathParam("suratNo") int suratNo) {

		Response theResponse = null;

		try {
			
			Collection<Object> theVerseCollection = verseServices.getAllVersesForSurat(suratNo);
			
			if(theVerseCollection.size() > 0) {
				
				VerseCollection theCollection = new VerseCollection();
				theCollection.setVerses((Collection)theVerseCollection);
				
				theResponse = Response.ok(theCollection).build();
				
			} else {
				
				theResponse = Response.status(Response.Status.NOT_FOUND).build();
			}
				
			

		} catch (Throwable t) {

			theResponse = Response.serverError().build();
			log.severe("Error happend when getting verses information");
			log.log(Level.SEVERE, "Throwable", t);

		}

		return theResponse;
	}

	// TODO This function need to be checked as the output is wrong 
	/**
	 * Function to get verse patch from a Surat
	 * 
	 */
	@Path("/{suratNo}/{verseNo}/{verseCount}")
	@GET
	@Produces({ "application/xml", "application/json" })
	public Response getXVersesForSurat(@PathParam("suratNo")   int suratNo,
									  @PathParam("verseNo")    int verseNo,
									  @PathParam("verseCount") int verseCount) {

		Response theResponse = null;

		try {
			
			Collection<Object> theVerseCollection = verseServices.getXVersesForSurat(suratNo, verseNo, verseCount);
			
			if(theVerseCollection.size() > 0) {
				
				VerseCollection theCollection = new VerseCollection();
				theCollection.setVerses((Collection)theVerseCollection);
				
				theResponse = Response.ok(theCollection).build();
				
			} else {
				
				theResponse = Response.status(Response.Status.NOT_FOUND).build();
			}
			

		} catch (Throwable t) {

			theResponse = Response.serverError().build();
			log.severe("Error happend when getting single verse information");
			log.log(Level.SEVERE, "Throwable", t);

		}

		return theResponse;
	}
	
	/**
	 * Function to get single verse from Surat
	 * 
	 */
	@Path("/{suratNo}/{verseNo}")
	@GET
	@Produces({ "application/xml", "application/json" })
	public Response getSingleVersesFormSurat(@PathParam("suratNo")   int suratNo,
									  	    @PathParam("verseNo")    int verseNo) {

		Response theResponse = null;

		try {
			
			Verse theVerse = verseServices.getSingleVerseFromSurat(suratNo, verseNo);
			
			if(theVerse == null) {
				
				theResponse = Response.status(Response.Status.NOT_FOUND).build();
				
			} else {
				
				theResponse = Response.ok(theVerse).build();
				
			}
			

		} catch (Throwable t) {

			theResponse = Response.serverError().build();
			log.severe("Error happend when getting single verse information");
			log.log(Level.SEVERE, "Throwable", t);

		}

		return theResponse;
	}


	/**
	 * Function to get all verses 
	 * 
	 */
/*	@Path("/all")
	@GET
	@Produces({"application/xml","application/json"})
	public Response getAllVerses() {
		
	Response theResponse = null;
		
		try {
			
			Collection<Object> theVerseCollection = verseServices.getAllVerses();
				
			if(theVerseCollection.size() > 0) {
				
				VerseCollection theCollection = new VerseCollection();
				theCollection.setVerses((Collection)theVerseCollection);
				
				theResponse = Response.ok(theCollection).build();
				
			} else {
				
				theResponse = Response.status(Response.Status.NOT_FOUND).build();
			}
				
			
		} catch(Throwable t) {
			
			theResponse = Response.serverError().build();
			log.severe("Error happend when get all verses information.. ");
			log.log(Level.SEVERE,"Throwable",t);
			
		}
		
		return theResponse;
	}*/

	
		
}
