package org.tilawa.internal.appengine;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * Class to get the PersistenceManagerFactory, noting that its expensive to create
 * @author m.sharif (mosabsalih@gmail.com)
 *
 */
public final class PersistenceFactory {

	private static PersistenceManagerFactory persistenceManagerFactory =  JDOHelper.getPersistenceManagerFactory("transactions-optional");
		
	public static PersistenceManagerFactory get() {
		
		return persistenceManagerFactory;
		
	}

	
}
