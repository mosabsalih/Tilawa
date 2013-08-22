package org.tilawa.internal.appengine;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskHandle;
import com.google.appengine.api.taskqueue.TaskOptions;

/**
 * 
 *@author m.sharif (mosabsalih@gmail.com)
 *
 */
public class QueueTool {
	
	private final Logger log = Logger.getLogger(QueueTool.class.getSimpleName());
	private final Queue queue;
	private WorkingMode mode  = WorkingMode.PUSH;
	
	public QueueTool(String queueName, boolean isPull) {
			
		if(isPull) this.mode = WorkingMode.PULL;
		
		if(queueName == null || queueName.trim().equals("") || queueName.trim().equalsIgnoreCase("default")  ) {
			// Using default queue
			queue = QueueFactory.getDefaultQueue();
		
		} else {
		
			queue = QueueFactory.getQueue(queueName);
			
		}
		
	}
	
	
	public synchronized TaskHandle addTask(String callbackUrl, String taskPayload) {
		
		TaskHandle handle = null;
		
		try {
			
			if(mode.equals(WorkingMode.PUSH))
				handle = queue.add(TaskOptions.Builder
											  .withDefaults()
								 		  	  .payload(taskPayload)
								 		  	  .url(callbackUrl));
			else
				handle = queue.add(TaskOptions.Builder.withMethod(TaskOptions.Method.PULL)
						  	.payload(taskPayload));
				
			
		} catch(Throwable t) {
			
			log.log(Level.SEVERE, "Error happend when adding a task ...",t);
		}
		
		return handle;
		
	}
	
	
	public synchronized TaskHandle addTaskWithDelay(String callbackUrl, String taskPayload, long delay) {
		
		TaskHandle handle = null;
		
		try {
			
			if(mode.equals(WorkingMode.PUSH))
				handle = queue.add(TaskOptions.Builder
									 		  .withDefaults()
									 		  .payload(taskPayload)
									 		  .url(callbackUrl)
									 		  .countdownMillis(delay));
			else 
				handle = queue.add(TaskOptions.Builder
											  .withMethod(TaskOptions.Method.PULL)
											  .payload(taskPayload)
											  .countdownMillis(delay));
			
		} catch(Throwable t) {
			
			log.log(Level.SEVERE, "Error happend when adding a task with delay ...",t);
		}
		
		return handle;
		
	}
	

	public void deleteAllTasks() {

		try {
			
			this.queue.purge();

		} catch(Throwable t) {

			log.log(Level.SEVERE, "Error happend when trying to purge tasks ...",t);
		}

	}
	
	
	public WorkingMode getWorkingMode() {

		return this.mode;
		
	}
	
	private enum WorkingMode {
		
		PULL, PUSH
		
	}
		
}
