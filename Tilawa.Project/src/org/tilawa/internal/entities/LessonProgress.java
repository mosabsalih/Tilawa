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

@PersistenceCapable
@XmlRootElement (name = "LessonProgress")
@XmlAccessorType(XmlAccessType.FIELD)
public final class LessonProgress implements Serializable {
	
	private static final long serialVersionUID = 7168313214882577516L;
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@PrimaryKey
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	@XmlElement(name = "StatusId", required = false)
	private String id;
	@Persistent
	@XmlElement(name = "LessonId", required = false)
	private String lessonId;
	@Persistent
	@XmlElement(name = "CurrentSurat", required = false)
	private String currentSurat;
	@Persistent
	@XmlElement(name = "CurrentVerse", required = false)
	private String currentVerse;
	@Persistent
	@XmlElement(name = "Created", required = false)
	private Date creationDate;
	@Persistent
	@XmlElement(name = "LastUpdated", required = false)
	private Date lastUpdated;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLessonId() {
		return lessonId;
	}
	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}
	public String getCurrentSurat() {
		return currentSurat;
	}
	public void setCurrentSurat(String currentSurat) {
		this.currentSurat = currentSurat;
	}
	public String getCurrentVerse() {
		return currentVerse;
	}
	public void setCurrentVerse(String currentVerse) {
		this.currentVerse = currentVerse;
	}

	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

}
