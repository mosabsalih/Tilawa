package org.tilawa.internal.appengine;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreInputStream;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

/**
 * 
 * @author m.sharif	(mosabsalih@gmail.com)
 *
 */

public final class BlobTool {

	private static final Logger log = Logger.getLogger(BlobTool.class.getSimpleName());
	private static BlobstoreService blobService = BlobstoreServiceFactory.getBlobstoreService();

	public static String getUploadURL(String callback) {
		
		return blobService.createUploadUrl(callback);
		
	}
	
	public static Map<String, BlobKey> getUploadedBlobs(HttpServletRequest req) {
		
		return blobService.getUploadedBlobs(req);
	}
	
	public void downloadBlob(String blobKey, HttpServletResponse resp) throws IOException, Exception {
		
		blobService.serve(new BlobKey(blobKey), resp);
	}
	
	public static InputStream getInputStream(String blobKey){
		try {
			
			return new BlobstoreInputStream(new BlobKey(blobKey));
			
		} catch (IOException e) {
			
			log.log(Level.SEVERE,"IOException",e);
			
			return null;

		} catch (Exception e) {
			
			log.log(Level.SEVERE,"Exception",e);
			
			return null;
		}

	}
}
