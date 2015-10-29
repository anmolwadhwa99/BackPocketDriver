package org.p4p.backpocketdriver.driverlog.database;



import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.p4p.backpocketdriver.driverlog.exception.DriverLogException;
import org.p4p.backpocketdriver.driverlog.model.UserDetails;

public class UserDatabase {

	private SessionFactory sessionFactory;
	private Session session;
	 

	public UserDatabase(){

	}

	public void openSessionFactory(){
		//configuration to enter the database
		Configuration c = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
				applySettings(c.getProperties());

		sessionFactory = c.buildSessionFactory(builder.build());


	}

	public void closeSessionFactory(){
		sessionFactory.close();

	}

	/**
	 * Retrieves details for a particular user 
	 * @param userName
	 * @return UserDetails object
	 */
	public UserDetails getUserDetails(String userName){
		openSessionFactory();
		session = sessionFactory.openSession();
		UserDetails user = (UserDetails) session.get(UserDetails.class, userName);
		
		if(user == null){
			closeSessionFactory();
			throw new DriverLogException("User with username " + userName + " could not be found");
		}
		
		session.close();
		closeSessionFactory();
		return user;
	}

	/**
	 * Adds a user to the database
	 * @param newUser
	 */
	public void addUserDetails(UserDetails newUser){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(newUser);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
	}

	/**
	 * Removes a user from the database
	 * @param userName
	 */
	public void removeUser(String userName){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		UserDetails user = (UserDetails) session.get(UserDetails.class, userName);
		
		if(user == null){
			closeSessionFactory();
			throw new DriverLogException("User with username " + userName + " could not be found therefore unable to delete user");
		}
		
		session.delete(user);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
	}

	/**
	 * Changes the password for a given user
	 * @param user
	 * @param newPassword
	 */
	public void changePassword(String userName, String newPassword){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		UserDetails userDetails = (UserDetails) session.get(UserDetails.class, userName);
		
		if(userDetails == null){
			closeSessionFactory();
			throw new DriverLogException("User with username " + userName + " could not found therefore unable to change password");
		}
		
		userDetails.setPassword(newPassword);
		session.update(userDetails);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
	}
	
	
	public Collection<UserDetails> getAllUserDetails(){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		String getAllQuery = "FROM UserDetails u";
		Query query = session.createQuery(getAllQuery);
		Collection<UserDetails> allUsers = query.list();
		
		if(allUsers.size() == 0){
			closeSessionFactory();
			throw new DriverLogException("No users in the database to display.");
		}
		
		session.close();
		closeSessionFactory();
		return allUsers;
	}


}
