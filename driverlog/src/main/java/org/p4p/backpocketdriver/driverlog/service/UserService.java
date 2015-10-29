package org.p4p.backpocketdriver.driverlog.service;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.p4p.backpocketdriver.driverlog.database.UserDatabase;
import org.p4p.backpocketdriver.driverlog.model.Journey;
import org.p4p.backpocketdriver.driverlog.model.UserDetails;



public class UserService {
	
	private UserDatabase userDB = new UserDatabase();
	
	/**
	 * Takes a userName (represents the id) and returns back the UserDetails
	 * @param userName (String)
	 * @return UserDetails
	 */
	public UserDetails getUserDetails(String userName){
		return userDB.getUserDetails(userName);
	}
	
	public Collection<UserDetails> getAllUserDetails(){
		return userDB.getAllUserDetails();
	}
	
	/**
	 * Adds a user to the database
	 * @param newUser
	 */
	public void addUserDetails(UserDetails newUser){
		userDB.addUserDetails(newUser);

	}
	
	/**
	 * Removes a user from the database
	 * @param userName
	 */
	public void removeUser(String userName){
		userDB.removeUser(userName);
				
	}
		
	/**
	 * Changes the password for a given user
	 * @param user
	 * @param newPassword
	 */
	public void changePassword(String userName, String newPassword){
		userDB.changePassword(userName, newPassword);
		
	}
		
	

	


	
	
	
	
	

	

}
