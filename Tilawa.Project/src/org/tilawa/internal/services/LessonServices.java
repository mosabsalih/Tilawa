package org.tilawa.internal.services;

import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;
import org.tilawa.internal.appengine.GenericStorageManager;
import org.tilawa.internal.constants.C;
import org.tilawa.internal.entities.QuranLesson;

public final class LessonServices {

	private final Logger log = Logger.getLogger(LessonServices.class.getSimpleName());

	private final GenericStorageManager lessonManager = (GenericStorageManager) StorageFactory.get().getManager(QuranLesson.class);

	public synchronized QuranLesson createLesson(QuranLesson quranlesson) throws Exception {

		if (quranlesson.getStatus() == null)
			quranlesson.setStatus(C.SystemStatus.ACTIVE);
		if (quranlesson.getCreationDate() == null)
			quranlesson.setCreationDate(new Date());

		return (QuranLesson) this.lessonManager.save(quranlesson);

	}

	public QuranLesson getLesson(Object id) throws Exception {

		return (QuranLesson) this.lessonManager.get(id);

	}

	public boolean hasLesson(Object id) throws Exception {

		return this.lessonManager.contain(id);

	}

	public Collection<Object> getAllLessons() throws Exception {

		Collection<Object> theCollection = this.lessonManager.getAll();

		return theCollection;

	}

}
