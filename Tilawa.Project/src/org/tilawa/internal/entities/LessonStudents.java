package org.tilawa.internal.entities;

import java.util.Collection;

import javax.jdo.annotations.Persistent;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "LessonStudents")
@XmlAccessorType(XmlAccessType.FIELD)
public final class LessonStudents  {
	
	@Persistent
	@XmlElement(name = "LessonId", required = false)
	private String lessonId;
	@Persistent
	@XmlElement(name = "Students", required = false)
	private Collection<User> users;
	
	public String getLessonId() {
		return lessonId;
	}
	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}
	public Collection<User> getUsers() {
		return users;
	}
	public void setUsers(Collection<User> users) {
		this.users = users;
	}
	
	
}
