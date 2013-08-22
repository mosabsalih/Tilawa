package org.tilawa.internal.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;

import org.tilawa.internal.appengine.GenericStorageManager;
import org.tilawa.internal.entities.LessonProgress;

public final class ProgressServices {

	private final Logger log = Logger.getLogger(ProgressServices.class.getSimpleName());

	private final GenericStorageManager progressManager = (GenericStorageManager) StorageFactory.get().getManager(LessonProgress.class);

	public synchronized LessonProgress createLesson(LessonProgress progress) throws Exception {

		if (progress.getCreationDate() == null)
			progress.setCreationDate(new Date());

		return (LessonProgress) this.progressManager.save(progress);

	}

	public LessonProgress getLessonProgressById(Object id) throws Exception {

		return (LessonProgress) this.progressManager.get(id);

	}
	

	public Collection<Object> getLessonProgressByLessonId(Object lessonId) throws Exception {

		Collection<Object> theLessons = new ArrayList<Object>();

		String query = "select from " + LessonProgress.class.getName() + " where lessonId == '" + lessonId + "'";
		
		theLessons = this.progressManager.get(query);
		 
		 if(theLessons.size() == 0) 
			 return new ArrayList<Object>();
		 else 
			 return theLessons;
		
	}


	public boolean hasLessonProgress(Object id) throws Exception {

		return this.progressManager.contain(id);

	}
		

	public Collection<Object> getAllLessonProgress() throws Exception {

		Collection<Object> theCollection = this.progressManager.getAll();

		return theCollection;

	}

}
