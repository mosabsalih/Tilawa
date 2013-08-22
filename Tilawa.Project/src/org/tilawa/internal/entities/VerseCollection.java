package org.tilawa.internal.entities;

import java.util.Collection;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Verses")
@XmlAccessorType(XmlAccessType.FIELD)
public final class VerseCollection  {
	
	@XmlElement(name = "Verse", required = false)
	private Collection<Verse> verse;

	public Collection<Verse> getVerse() {
		return verse;
	}

	public void setVerses(Collection<Verse> verse) {
		this.verse = verse;
	}
	
}
