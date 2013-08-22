package org.tilawa.internal.appengine;

import java.util.Collection;

/**
 * @author m.sharif (mosabsalih@gmail.com)
 */
public interface Storage<T extends Object> {

	public boolean contain(Object id);
	
	public T get(Object id) ;
	
	public Collection<T> get(String query) ;
	
	public Collection<T> getAll();
	
	public T save(T t);
	
	public void delete(Object id);
	
	public T update(Object id, T updated);
	
}
