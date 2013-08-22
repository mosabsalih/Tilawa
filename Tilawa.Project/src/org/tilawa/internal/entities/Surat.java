package org.tilawa.internal.entities;

import java.io.Serializable;
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
@XmlRootElement (name = "Surat")
@XmlAccessorType(XmlAccessType.FIELD)
public final class Surat implements Serializable {
	
	private static final long serialVersionUID = 7168313214882577516L;
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@PrimaryKey
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	@XmlElement(name = "SuratId", required = false)
	private String id;
	@Persistent
	@XmlElement(name = "SuratName", required = false)
	private String name;
	@Persistent
	@XmlElement(name = "Order", required = false)
	private int order;
	@Persistent
	@XmlElement(name = "MaxVerse", required = false)
	private String maxVerse;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMaxVerse() {
		return maxVerse;
	}
	public void setMaxVerse(String maxVerse) {
		this.maxVerse = maxVerse;
	}


}
