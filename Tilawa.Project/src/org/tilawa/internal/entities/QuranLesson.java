package org.tilawa.internal.entities;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.tilawa.internal.constants.C;

@PersistenceCapable
@XmlRootElement (name = "Lesson")
@XmlAccessorType(XmlAccessType.FIELD)
public final class QuranLesson implements Serializable {
	
	private static final long serialVersionUID = 260263750476224320L;
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@PrimaryKey
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	@XmlElement(name = "LessonId", required = false)
	private String id;
	@Persistent
	@XmlElement(name = "LessonName", required = false)
	private String lessonName;
	@Persistent
	@XmlElement(name = "StartDate", required = false)
	private String startDate;
	@Persistent
	@XmlElement(name = "EndDate", required = false)
	private String endDate;
	@Persistent
	@XmlElement(name = "LeapCounter", required = false)
	private int leapCounter;
	@Persistent
	@XmlElement(name = "Status", required = false)
	private C.SystemStatus status;
	@Persistent
	@XmlElement(name = "Created", required = false)
	private Date creationDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public C.SystemStatus getStatus() {
		return status;
	}
	public void setStatus(C.SystemStatus status) {
		this.status = status;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public int getLeapCounter() {
		return leapCounter;
	}
	public void setLeapCounter(int leapCounter) {
		this.leapCounter = leapCounter;
	}
	
}
