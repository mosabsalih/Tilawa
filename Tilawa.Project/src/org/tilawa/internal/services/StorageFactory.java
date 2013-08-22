package org.tilawa.internal.services;

import org.tilawa.internal.appengine.GenericStorageManager;
import org.tilawa.internal.appengine.Storage;


public final class StorageFactory {

	private final static StorageFactory factory = new StorageFactory();
	
	public static StorageFactory get() {
		
		return factory;
		
	}
	
	public synchronized Storage<? extends Object> getManager(Class<?> theClass) {

			return new GenericStorageManager(theClass);
		
	}
			
	
}
