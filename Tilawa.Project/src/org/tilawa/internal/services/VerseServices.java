package org.tilawa.internal.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;
import org.tilawa.internal.appengine.GenericStorageManager;
import org.tilawa.internal.entities.Verse;
import org.tilawa.internal.entities.VerseExtra;

public final class VerseServices {

	private final Logger log = Logger.getLogger(VerseServices.class
			.getSimpleName());

	private final GenericStorageManager verseManager = (GenericStorageManager) StorageFactory
			.get().getManager(Verse.class);
	private final GenericStorageManager extraManager = (GenericStorageManager) StorageFactory
			.get().getManager(VerseExtra.class);

	public synchronized Verse createVerse(Verse verse) throws Exception {

		return (Verse) this.verseManager.save(verse);

	}

	public Verse getVerse(Object id) throws Exception {

		return (Verse) this.verseManager.get(id);

	}

	// TODO Fix the get methods to support the new VerseExtra retrieval
	// TODO For long Surat try to find a pagination way to get the verses
	public Collection<Object> getAllVersesForSurat(Object suratNo)
			throws Exception {

		Collection<Object> theVerses = new ArrayList<Object>();

		String query = "select from " + Verse.class.getName()
				+ " where suratNo == " + suratNo;

		theVerses = this.verseManager.get(query);

		if (theVerses.size() == 0)
			return new ArrayList<Object>();
		else
			return this.getFinalVerseList(theVerses);

	}

	public Collection<Object> getXVersesForSurat(Object suratNo, int verseNo,
			int verseCount) throws Exception {

		Collection<Object> theVerses = new ArrayList<Object>();
		Collection<Object> nextVerses = new ArrayList<Object>();

		// TODO We should do a check against the total verses in a Surat
		int versesEnd = verseNo + verseCount;

		int nextVerse = verseNo + 1;

		for (int i = verseNo + 1; i <= versesEnd; i++) {

			String query = "select from " + Verse.class.getName()
					+ " where suratNo == " + suratNo + " && verseNo == "
					+ nextVerse;

			theVerses = this.verseManager.get(query);

			nextVerses.add((Verse) theVerses.iterator().next());

			nextVerse += 1;

		}

		return this.getFinalVerseList(nextVerses);

	}

	public Verse getSingleVerseFromSurat(Object suratNo, Object verseNo)
			throws Exception {

		Verse thisVerse = null;
		
		Collection<Object> theVerses = new ArrayList<Object>();

		String query = "select from " + Verse.class.getName()
				+ " where suratNo == " + suratNo + " && verseNo == " + verseNo;

		theVerses = this.verseManager.get(query);

		if (theVerses.size() == 0)
			return thisVerse;
		else {
			
			thisVerse = (Verse) theVerses.iterator().next();
			
			if(thisVerse.isLong()) {
				
				VerseExtra extra = this.getVerseExtra(thisVerse.getExtraId());

				thisVerse.setVerse(extra.getVerse().getValue());

			}
						
			return thisVerse;
			
		}

	}

/*	public Collection<Object> getAllVerses() throws Exception {

		Collection<Object> theCollection = this.verseManager.getAll();

		return theCollection;

	} */

	public synchronized VerseExtra createVerseExtra(VerseExtra extra)
			throws Exception {

		return (VerseExtra) this.extraManager.save(extra);

	}

	public VerseExtra getVerseExtra(Object id) throws Exception {

		return (VerseExtra) this.extraManager.get(id);

	}

	private Collection<Object> getFinalVerseList(Collection<Object> theVerses) throws Exception {

		Collection<Object> theFinalList = new ArrayList<Object>();

		for (Object v : theVerses) {

			Verse thisVerse = (Verse) v;

			// If this text is long, we get the extra value for the verse
			if (thisVerse.isLong()) {

				VerseExtra extra = this.getVerseExtra(thisVerse.getExtraId());

				thisVerse.setVerse(extra.getVerse().getValue());
			}

			theFinalList.add(thisVerse);

		}
		
		return theFinalList;
	}

}
