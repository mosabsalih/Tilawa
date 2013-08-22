package org.tilawa.internal.services;

import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;

import org.tilawa.internal.appengine.GenericStorageManager;
import org.tilawa.internal.constants.C;
import org.tilawa.internal.entities.User;

public final class UserServices {

	private final Logger log = Logger.getLogger(UserServices.class.getSimpleName());

	private final GenericStorageManager userManager = (GenericStorageManager) StorageFactory.get().getManager(User.class);

	public synchronized User createUser(User user) throws Exception {

		if (user.getSystemStatus() == null)
			user.setSystemStatus(C.SystemStatus.ACTIVE);
		if (user.getCreationDate() == null)
			user.setCreationDate(new Date());
		if (user.getUserType() == null)
			user.setUserType(C.UserType.STUDENT);


		return (User) this.userManager.save(user);

	}

	public User getUser(Object id) throws Exception {

		return (User) this.userManager.get(id);

	}

	public boolean hasCustomer(Object id) throws Exception {

		return this.userManager.contain(id);

	}

	public Collection<Object> getAllUsers() throws Exception {

		Collection<Object> theCollection = this.userManager.getAll();

		return theCollection;

	}

}
