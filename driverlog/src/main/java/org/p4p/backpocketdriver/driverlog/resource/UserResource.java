package org.p4p.backpocketdriver.driverlog.resource;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.p4p.backpocketdriver.driverlog.model.UserDetails;
import org.p4p.backpocketdriver.driverlog.service.UserService;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
	private UserService userService = new UserService();
	
	/**
	 * Adding one user
	 * @param user
	 * @return
	 */
	@POST
	public UserDetails addUser(UserDetails user){
		userService.addUserDetails(user);
		return user;
	}
	
	/**
	 * Getting a user's details
	 * @param userName
	 * @return
	 */
	@GET
	@Path("/{userName}")
	public UserDetails getUser(@PathParam("userName") String userName){
		UserDetails user =  userService.getUserDetails(userName);
		return user;
		
	}
	
	/**
	 * Get all users 
	 * @return
	 */
	@GET
	public Collection<UserDetails> getAllUsers(){
		Collection<UserDetails> allUsers = userService.getAllUserDetails();
		return allUsers;
	}
	
	/**
	 * Deleting a user
	 * @param userName
	 * @return
	 */
	@DELETE
	@Path("/{userName}")
	public UserDetails deleteUser(@PathParam("userName") String userName){
		UserDetails user = userService.getUserDetails(userName);
		userService.removeUser(userName);
		return user;
	}
	

	

}
