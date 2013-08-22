package org.tilawa.internal.entities;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.tilawa.internal.constants.C;



@PersistenceCapable

@XmlRootElement (name = "User")

@XmlAccessorType(XmlAccessType.FIELD)
public final class User implements Serializable {
	
	private static final long serialVersionUID = 5623736504537148044L;
	
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	@PrimaryKey
	@XmlElement(name = "UserId", required = false)
	private String id;
	@Persistent
	@XmlElement(name = "UserType", required = false)
	private C.UserType userType;
	@Persistent
	@XmlElement(name = "Country", required = false)
	private String country;
	@Persistent
	@XmlElement(name = "Verified", required = false)
	private boolean verified;
	@Persistent
	@XmlElement(name = "Name", required = false)
	private String name;
	@XmlElement(name = "Email", required = false)
	private String email;
	@XmlElement(name = "Password", required = false)
	private String password;
	@Persistent
	@XmlElement(name = "Status", required = false)
	private C.SystemStatus systemStatus;
	@Persistent
	@XmlElement(name = "Created", required = false)
	
private Date creationDate;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public C.UserType getUserType() {
		return userType;
	}
	public void setUserType(C.UserType userType) {
		this.userType = userType;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public C.SystemStatus getSystemStatus() {
		return systemStatus;
	}
	public void setSystemStatus(C.SystemStatus systemStatus) {
		this.systemStatus = systemStatus;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
