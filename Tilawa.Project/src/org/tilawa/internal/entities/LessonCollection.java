package org.tilawa.internal.entities;

import java.util.Collection;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Lessons")
@XmlAccessorType(XmlAccessType.FIELD)
public final class LessonCollection  {
	
	@XmlElement(name = "Lesson", required = false)
	private Collection<QuranLesson> quranLessons;

	public Collection<QuranLesson> getQuranLessons() {
		return quranLessons;
	}

	public void setQuranLessons(Collection<QuranLesson> quranLessons) {
		this.quranLessons = quranLessons;
	}


}
