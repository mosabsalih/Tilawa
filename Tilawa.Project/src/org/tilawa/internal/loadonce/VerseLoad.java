package org.tilawa.internal.loadonce;

import java.io.BufferedReader;
import java.io.FileReader;
import org.tilawa.internal.entities.Verse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class VerseLoad {


	private static final String FILE_PATH = "quran-simple.final.txt";

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

			saveVerse(tempVerse);
			
		}
	}
	
	
	public void saveVerse(Verse verse) {
	
		try {
			 
			Client client = Client.create();
	 
			WebResource webResource = client.resource("http://tilawaapp.appspot.com/v1/verse/create");
	 
			String line = "verse="+ verse.getVerse() +"&suratno="+ verse.getSuratNo() +"&verseno=" + verse.getVerseNo();
			
			ClientResponse response = webResource.post(ClientResponse.class,line);
			
			System.out.println(response.getStatus());
	 
		  } catch (Exception e) {
	 
			e.printStackTrace();
	 
		  }
	 

	}
	
	public static void main(String[] args) {
		
		VerseLoad load = new VerseLoad();
		try {
			load.loadVerseData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
