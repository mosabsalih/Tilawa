package org.tilawa.internal.entities;

import java.util.Collection;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "LessonProgress")
@XmlAccessorType(XmlAccessType.FIELD)
public final class LessonProgressCollection  {

	@XmlElement(name = "LessonId", required = false)
	private String lessonId;
	@XmlElement(name = "Progress", required = false)
	private Collection<LessonProgress> lessonProgress;
	
	public String getLessonId() {
		return lessonId;
	}
	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}
	public Collection<LessonProgress> getLessonProgress() {
		return lessonProgress;
	}
	public void setLessonProgress(Collection<LessonProgress> lessonProgress) {
		this.lessonProgress = lessonProgress;
	}


}
