package org.tilawa.internal.appengine;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.cache.Cache;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;

/**
 * A Wrapper class for accessing cache services provided by the GAE
 * @author m.sharif (mosabsalih@gmail.com)
 *
 */
public final class MemoryStoreTool<T,V> {

	private static final Logger log = Logger.getLogger(MemoryStoreTool.class.getSimpleName());
	private CacheFactory cacheFactory;
	private Cache  	     cache;

	/**
	 * Constructor to create a cache at memory given only a map with the default setting's 
	 * [Add if not exists, replace if exists]
	 * @param map
	 */
	@SuppressWarnings("unchecked")
	public MemoryStoreTool(Map map) {

		try {

			cacheFactory = CacheManager.getInstance().getCacheFactory();
			// Map is used to pass the needed cache parameters, if default is needed an empty map is passed 
			cache = cacheFactory.createCache(map);

		} catch (Throwable e) {
			
			log.severe("Error happend when creating a memeory cache .. ");
			log.log(Level.SEVERE,"Throwable",e);
		}

	}
	
	/**
	 * Constructor to create a cache at memory given a map and default expiration timeout for entries and retention policy
	 * @param map
	 */
	@SuppressWarnings("unchecked")
	public MemoryStoreTool(Map map, long timeoutInSeconds, boolean addOnly) {

		try {

			// Setting the default expiration timeout
			map.put(GCacheFactory.EXPIRATION_DELTA, timeoutInSeconds);
			
			if(addOnly)
				// Only new will be added and old ones will not be replaced or touched
				map.put(MemcacheService.SetPolicy.ADD_ONLY_IF_NOT_PRESENT, true);
			else
				// Default mode, where new is added and old a replaced
				map.put(MemcacheService.SetPolicy.SET_ALWAYS, true);
			
			cacheFactory = CacheManager.getInstance().getCacheFactory();
			cache = cacheFactory.createCache(map);

		} catch (Throwable e) {
			
			log.severe("Error happend when creating a memeory cache .. ");
			log.log(Level.SEVERE,"Throwable",e);
		}

	}

	
	/**
	 * Function to check for a creating key in-memory 
	 * @param key
	 * @return Boolean
	 */
	public boolean contains(T key) {
		
		if(cache.containsKey(key)) return true; else return false;
		
	}
	
	/**
	 * Function to put a key and value in the memory
	 * @param key
	 * @param value
	 */
	@SuppressWarnings("unchecked")
	public void put(T key, V value) {
		
		cache.put(key, value);

	}
	
	/**
	 * Function to get a value for a cretin key
	 * @param key
	 */
	@SuppressWarnings("unchecked")
	public V get(T key) {
		
		return (V) cache.get(key);
	}

}
