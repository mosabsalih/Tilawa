package org.tilawa.internal.appengine;




public class GenericStorageManager extends StorageTool<Object> {

	public GenericStorageManager(Class<?> type) {
		
		super(type);
		
	}

	public Object update(Object id, Object updated) {
		
		throw new UnsupportedOperationException();

	}

}
