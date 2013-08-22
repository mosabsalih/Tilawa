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

import com.google.appengine.api.datastore.Text;


@PersistenceCapable
@XmlRootElement (name = "VerseExtra")
@XmlAccessorType(XmlAccessType.FIELD)
public final class VerseExtra implements Serializable {
	
	private static final long serialVersionUID = 4226566687142663592L;
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@PrimaryKey
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	@XmlElement(name = "ExtraId", required = false)
	private String id;
	@Persistent
	@XmlElement(name = "Verse", required = false)
	private Text verse;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Text getVerse() {
		return verse;
	}
	public void setVerse(Text verse) {
		this.verse = verse;
	}

	
}
