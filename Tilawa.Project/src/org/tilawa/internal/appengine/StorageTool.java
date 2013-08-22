package org.tilawa.internal.appengine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;


/**
 * A utility class to manage the storage of entities in the GAE
 * @author m.sharif (mosabsalih@gmail.com)
 *
 */
public abstract class StorageTool <T extends Object> implements Storage<T> {

	protected static final Logger log = Logger.getLogger(StorageTool.class.getSimpleName());

	protected Class<?> classType;

	protected StorageTool(Class<?> type) {
		
		classType = type; 
		
	} 

	/**
	 * Function to save an entity to the data store
	 */
	public synchronized T save(T t) {

		T theEntity = null;
		
		PersistenceManager manager = null;

		Transaction tx = null;

		try { 

			manager  =	PersistenceFactory.get().getPersistenceManager();

			tx = manager.currentTransaction();

			tx.begin();

			theEntity = manager.makePersistent(t);

			tx.commit();

		} catch(Throwable throwable) {
			
			log.log(Level.SEVERE,"Throwable",throwable);
			
		} finally {

			if (tx.isActive())
			{

				log.severe("Rolling back the transaction, Error in saving entity to the GAE datastore !!!");
				
				// Error occurred so roll back the transaction
				tx.rollback();
			}

			// The changes will be saved by the PersistenceManager since its know about it
			manager.close();

		}

		return theEntity;
	}


	/**
	 * To be implemented by each specific storage manager on his own 
	 * @param id The entity identifier 
	 * @param updated The transit entity
	 * @return T Updated entity
	 */
	public abstract T update(Object id, T updated);


	@SuppressWarnings("unchecked")
	public synchronized void delete(Object id) {

		PersistenceManager manager = null;

		Transaction tx = null;

		try { 

			manager  =	PersistenceFactory.get().getPersistenceManager();

			tx = manager.currentTransaction();

			tx.begin();

 			T theEntity = (T)manager.getObjectById(classType, id);
			// Deleting the object
			manager.deletePersistent(theEntity);

			tx.commit();

		} catch(Throwable throwable) {
			
			log.log(Level.SEVERE,"Throwable",throwable);
			
		} finally {

			if (tx.isActive())
			{
				log.severe("Rolling back the transaction, could not delete the entity !!!"); 
				// Error occurred so roll back the transaction
				tx.rollback();
			}

			// The changes will be saved by the PersistenceManager since its know about it
			manager.close();

		}		
	}

	@SuppressWarnings("unchecked")
	public boolean contain(Object id) {

		boolean  isFound = false;

		PersistenceManager manager = null;

		try {

			manager  =	PersistenceFactory.get().getPersistenceManager();

			T theEntity = (T) manager.getObjectById(classType, id);

			if(theEntity != null) isFound = true; else isFound = false;

		} catch(Throwable throwable) {
			
			 log.log(Level.SEVERE,"Throwable",throwable);
			isFound = false;
			
		} finally {

			manager.close();

		}

		return isFound;

	}


	@SuppressWarnings("unchecked")
	public T get(Object id) {

		T theEntity, detached  = null;

		PersistenceManager manager = null;

		try {

			manager  =	PersistenceFactory.get().getPersistenceManager();

			theEntity = (T) manager.getObjectById(classType, id);

			detached = manager.detachCopy(theEntity);

		} catch(Throwable throwable) {
			
			 log.log(Level.SEVERE,"Throwable",throwable);
			
		} finally {

			manager.close();

		}

		return detached;
	}

	@SuppressWarnings("unchecked")
	public Collection<T> get(String query) {

		List<T> entites, detachedEntites = new ArrayList<T>();

		PersistenceManager manager = null;

		try {

			manager  =	PersistenceFactory.get().getPersistenceManager();

			entites =  (List<T>) manager.newQuery(query).execute();

			if(entites.size() > 0) {

				detachedEntites = (List<T>) manager.detachCopyAll(entites);

			}

		} catch(Throwable throwable) {
			
			 log.log(Level.SEVERE,"Throwable",throwable);
			
		} finally {

			manager.close();

		}

		return detachedEntites;


	}

	@SuppressWarnings("unchecked")
	public Collection<T> getAll() {

		List<T> entites = new ArrayList<T>();
		
		Extent<T> extent = null;
		
		PersistenceManager manager = null;

		try {

			manager  =	PersistenceFactory.get().getPersistenceManager();

			extent = (Extent<T>) manager.getExtent(classType);
			
			
			for(T t : extent) {
				
				entites.add(t);
				
			}

		} catch(Throwable throwable) {
			
			log.log(Level.SEVERE,"Throwable",throwable);
			
		} finally {

			extent.closeAll();
			manager.close();

		}

		return entites;

	}

}
