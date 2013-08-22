package org.tilawa.internal.appengine;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;

/**
 * 
 * @author m.sharif	(mosabsalih@gmail.com)
 *
 */

public final class HttpTool {

	private static final Logger log = Logger.getLogger(HttpTool.class.getSimpleName());

	public static HTTPResponse doGet(String url, Map<String, Object> headers, Map<String, Object> parameters, boolean isUrlEncoded) {

		HTTPResponse httpResponse = null;

		try {

			URL theUrl = new URL(url + formatQueryParams(parameters, isUrlEncoded));

			HTTPRequest httpRequest = new HTTPRequest(theUrl, HTTPMethod.GET);

			httpRequest = addHeaders(httpRequest, headers);

			URLFetchService  fetchService = URLFetchServiceFactory.getURLFetchService();

			httpResponse = fetchService.fetch(httpRequest);


		} catch (MalformedURLException e) {

			log.severe("Error happend when doing an HTTP get .. " );
			log.log(Level.SEVERE, "MalformedURLException", e);

		} catch (IOException e) {

			log.severe("Error happend when doing an HTTP get .. " );
			log.log(Level.SEVERE, "IOException", e);

		} catch(Throwable t) {

			log.severe("Error happend when doing an HTTP get .. " );
			log.log(Level.SEVERE, "Throwable", t);

		}

		return httpResponse;

	}


	public static HTTPResponse doPost(String url, Map<String, Object> headers, byte[] payload, Map<String, Object> parameters, boolean isUrlEncoded) {

		HTTPResponse httpResponse = null;

		try {

			URL theUrl = new URL(url + formatQueryParams(parameters, isUrlEncoded));

			HTTPRequest httpRequest = new HTTPRequest(theUrl, HTTPMethod.POST);

			httpRequest = addHeaders(httpRequest, headers);

			httpRequest.setPayload(payload);

			URLFetchService  fetchService = URLFetchServiceFactory.getURLFetchService();

			httpResponse = fetchService.fetch(httpRequest);


		} catch (MalformedURLException e) {

			log.severe("Error happend when doing an HTTP post .. " );
			log.log(Level.SEVERE, "MalformedURLException", e);

		} catch (IOException e) {

			log.severe("Error happend when doing an HTTP post .. " );
			log.log(Level.SEVERE, "IOException", e);

		} catch(Throwable t) {

			log.severe("Error happend when doing an HTTP post .. " );
			log.log(Level.SEVERE, "Throwable", t);

		}

		return httpResponse;

	}

	public static HTTPResponse doPut(String url, Map<String, Object> headers, byte[] payload, Map<String, Object> parameters, boolean isUrlEncoded) {

		HTTPResponse httpResponse = null;

		try {

			URL theUrl = new URL(url + formatQueryParams(parameters, isUrlEncoded));

			HTTPRequest httpRequest = new HTTPRequest(theUrl, HTTPMethod.PUT);

			httpRequest = addHeaders(httpRequest, headers);

			httpRequest.setPayload(payload);

			URLFetchService  fetchService = URLFetchServiceFactory.getURLFetchService();

			httpResponse = fetchService.fetch(httpRequest);


		} catch (MalformedURLException e) {

			log.severe("Error happend when doing an HTTP put .. " );
			log.log(Level.SEVERE, "MalformedURLException", e);

		} catch (IOException e) {

			log.severe("Error happend when doing an HTTP put .. " );
			log.log(Level.SEVERE, "IOException", e);

		} catch(Throwable t) {

			log.severe("Error happend when doing an HTTP put .. " );
			log.log(Level.SEVERE, "Throwable", t);

		}

		return httpResponse;

	}

	public static HTTPResponse doDelete(String url, Map<String, Object> headers, Map<String, Object> parameters, boolean isUrlEncoded) {

		HTTPResponse httpResponse = null;

		try {

			URL theUrl = new URL(url + formatQueryParams(parameters, isUrlEncoded));

			HTTPRequest httpRequest = new HTTPRequest(theUrl, HTTPMethod.DELETE);

			httpRequest = addHeaders(httpRequest, headers);

			URLFetchService  fetchService = URLFetchServiceFactory.getURLFetchService();

			httpResponse = fetchService.fetch(httpRequest);


		} catch (MalformedURLException e) {

			log.severe("Error happend when doing an HTTP delete .. " );
			log.log(Level.SEVERE, "MalformedURLException", e);

		} catch (IOException e) {

			log.severe("Error happend when doing an HTTP delete .. " );
			log.log(Level.SEVERE, "IOException", e);

		} catch(Throwable t) {

			log.severe("Error happend when doing an HTTP delete .. " );
			log.log(Level.SEVERE, "Throwable", t);

		}

		return httpResponse;

	}

/*	public static HTTPResponse doHead(String url, HashMap<String, String> headers, HashMap<String, Object> parameters, boolean isUrlEncoded) {

		HTTPResponse httpResponse = null;

		try {

			URL theUrl = new URL(url + formatQueryParams(parameters, isUrlEncoded));

			HTTPRequest httpRequest = new HTTPRequest(theUrl, HTTPMethod.HEAD);

			URLFetchService  fetchService = URLFetchServiceFactory.getURLFetchService();

			httpResponse = fetchService.fetch(httpRequest);


		} catch (MalformedURLException e) {

			log.severe("Error happend when doing an HTTP head .. " );
			log.log(Level.SEVERE, "MalformedURLException", e);

		} catch (IOException e) {

			log.severe("Error happend when doing an HTTP head .. " );
			log.log(Level.SEVERE, "IOException", e);

		} catch(Throwable t) {

			log.severe("Error happend when doing an HTTP head .. " );
			log.log(Level.SEVERE, "Throwable", t);

		}

		return httpResponse;

	}
*/

	@SuppressWarnings("unchecked")
	private static HTTPRequest addHeaders(HTTPRequest theRequest, Map<String, Object> headers) {

		if(headers != null ) {

			Iterator it = headers.entrySet().iterator();

			while (it.hasNext()) {

				Map.Entry pairs = (Map.Entry)it.next();

				String name = (String)pairs.getKey();
				String value = (String)pairs.getValue();

				if(name != null || value != null) 
					theRequest.addHeader(new HTTPHeader(name, value));
			}
		}

		return theRequest;
	}
	
	@SuppressWarnings("unchecked")
	private static String formatQueryParams(Map<String, Object> parameters, boolean isUrlEncoded) {

		String params = "?";
		int paramCount = 1;
		
		if(parameters != null) {

			Iterator it = parameters.entrySet().iterator();

			while (it.hasNext()) {

				Map.Entry pairs = (Map.Entry)it.next();
				// TODO To support adding URL encoding here
				String name = (String)pairs.getKey();
				Object value = (Object)pairs.getValue();

				params = params + name + "=" + value;
				
				if(paramCount < parameters.size()) { params = params + "&"; paramCount +=1; }
				
			}
		}

		return params;
	}
	
}
