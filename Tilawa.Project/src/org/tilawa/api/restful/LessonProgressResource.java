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

import org.tilawa.internal.entities.LessonCollection;
import org.tilawa.internal.entities.LessonProgress;
import org.tilawa.internal.entities.LessonProgressCollection;
import org.tilawa.internal.services.LessonServices;
import org.tilawa.internal.services.ProgressServices;

/**
 * Restful API to manipulate a Quran Lesson Progress
 * 
 * @author m.sharif
 */

@Path("/v1/progress")
public class LessonProgressResource {

	private static final Logger log = Logger
			.getLogger(LessonProgressResource.class.getSimpleName());
	private final ProgressServices progressServices = new ProgressServices();
	private final LessonServices lessonServices = new LessonServices();

	/**
	 * Function to save a progress
	 * 
	 */
	@Path("/save/{lessonId}")
	@POST
	public synchronized Response saveProgress(
			@PathParam("lessonId") String lessonId,
			@FormParam("surat") String currentSurat,
			@FormParam("verse") String currentVerse, @Context UriInfo uriInfo) {

		// TODO [H] We should add validations to check if every field we need is
		// present
		Response theResponse = null;

		try {

			if (lessonServices.hasLesson(lessonId)) {

				LessonProgress tempProgress = new LessonProgress();
				tempProgress.setLessonId(lessonId);
				tempProgress.setCurrentSurat(currentSurat);
				tempProgress.setCurrentVerse(currentVerse);

				LessonProgress savedProgress = progressServices
						.createLesson(tempProgress);

				// Creating the location URI for a class
				URI location = new URI(uriInfo.getBaseUri().toString()
						+ "v1/progress/" + lessonId + "/"
						+ savedProgress.getId());

				theResponse = Response.created(location).build();

			} else {
				// Lesson not found
				theResponse = Response.status(Response.Status.NOT_FOUND)
						.build();
			}

		} catch (Throwable t) {

			theResponse = Response.status(Response.Status.BAD_REQUEST).build();
			log.severe("Error happend when creating a lesson .. ");
			log.log(Level.SEVERE, "Throwable", t);

		}

		return theResponse;
	}

	/**
	 * Function to get progress for a lesson
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Path("/{lessonId}")
	@GET
	@Produces({ "application/xml", "application/json" })
	public Response getProgressForLesson(@PathParam("lessonId") String lessonId) {

		Response theResponse = null;

		try {

			if (lessonServices.hasLesson(lessonId)) {

				Collection<Object> theCollection = progressServices.getLessonProgressByLessonId(lessonId);

				if (theCollection.size() > 0) {

					LessonProgressCollection theProgressCollection = new LessonProgressCollection();
					theProgressCollection.setLessonProgress((Collection)theCollection);
					theProgressCollection.setLessonId(lessonId);

					theResponse = Response.ok(theProgressCollection).build();

				} else {

					theResponse = Response.status(Response.Status.NOT_FOUND).build();
				}

			} else {
				// Lesson not found
				theResponse = Response.status(Response.Status.NOT_FOUND)
						.build();
			}

		} catch (Throwable t) {

			theResponse = Response.serverError().build();
			log.severe("Error happend when get all lesson progress information for lesson = "
					+ lessonId);
			log.log(Level.SEVERE, "Throwable", t);

		}

		return theResponse;
	}
	
	/**
	 * Function to get all lessons progress 
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Path("/all")
	@GET
	@Produces({"application/xml","application/json"})
	public Response getAllProgress() {
		
	Response theResponse = null;
		
		try {
			
			Collection<Object> theLessonCollection = progressServices.getAllLessonProgress();
				
			if(theLessonCollection.size() > 0) {
				
				LessonProgressCollection theCollection = new LessonProgressCollection();
				theCollection.setLessonProgress((Collection)theLessonCollection);
				
				theResponse = Response.ok(theCollection).build();
				
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
