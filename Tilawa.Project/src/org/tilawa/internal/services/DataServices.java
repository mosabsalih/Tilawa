package org.tilawa.internal.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.Logger;

import org.tilawa.internal.appengine.GenericStorageManager;
import org.tilawa.internal.entities.Verse;

public final class DataServices {

	private final Logger log = Logger.getLogger(LessonServices.class
			.getSimpleName());

	private final GenericStorageManager verseManager = (GenericStorageManager) StorageFactory
			.get().getManager(Verse.class);

	private static final String FILE_PATH = "quran-simple-small.txt";

	public void loadVerseData() throws Exception {

		BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));

		String line;

		while ((line = br.readLine()) != null) {

			String[] lineData = line.split(";");

			// Saving the data to the DataStore
			Verse tempVerse = new Verse();

			tempVerse.setVerseNo(Integer.parseInt(lineData[1]));
			tempVerse.setSuratNo(Integer.parseInt(lineData[0]));
			tempVerse.setVerse(lineData[2]);

			//createVerse(tempVerse);
			System.out.println(tempVerse.toString());
			tempVerse = null;

		}

	}

	private synchronized Verse createVerse(Verse theVerse) throws Exception {

		return (Verse) this.verseManager.save(theVerse);

	}
	
	public static void main(String[] args) {
		
		DataServices services = new DataServices();
		
		try {
			services.loadVerseData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

}
