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
@XmlRootElement (name = "Verse")
@XmlAccessorType(XmlAccessType.FIELD)
public final class Verse implements Serializable {
	
	private static final long serialVersionUID = 4226566687142663592L;
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@PrimaryKey
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	@XmlElement(name = "VerseId", required = false)
	private String id;
	@Persistent
	@XmlElement(name = "Verse", required = false)
	private String verse;
	@Persistent
	@XmlElement(name = "SuratNo", required = false)
	private int suratNo;
	@Persistent
	@XmlElement(name = "VerseNo", required = false)
	private int verseNo;
	@Persistent
	@XmlElement(name = "IsLong", required = false)
	private boolean isLong;
	@Persistent
	@XmlElement(name = "ExtraId", required = false)
	private String extraId;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSuratNo() {
		return suratNo;
	}
	public void setSuratNo(int suratNo) {
		this.suratNo = suratNo;
	}
	public int getVerseNo() {
		return verseNo;
	}
	public void setVerseNo(int verseNo) {
		this.verseNo = verseNo;
	}
	public String getVerse() {
		return verse;
	}
	public void setVerse(String verse) {
		this.verse = verse;
	}
	public boolean isLong() {
		return isLong;
	}
	public void setLong(boolean isLong) {
		this.isLong = isLong;
	}
	public String getExtraId() {
		return extraId;
	}
	public void setExtraId(String extraId) {
		this.extraId = extraId;
	}

}
