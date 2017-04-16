package org.koushik.javabrains.messenger.resources;

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

import org.koushik.javabrains.messenger.model.Profile;
import org.koushik.javabrains.messenger.service.ProfileService;

@Path("/profiles")
@Produces(MediaType.APPLICATION_JSON) //lets every method do this without having to repeat this line
@Consumes(MediaType.APPLICATION_JSON) //lets every method do this without having to repeat this line
public class ProfileResource {

	ProfileService profileService = new ProfileService();
	
	/**
	 * gets all the Profiles from the service
	 * @return
	 */
	@GET
	public List<Profile> getProfiles()
	{
		return profileService.getAllProfiles();
	}
	
	/**
	 * Gets a specific profile based on profile name
	 * @param profileName
	 * @return
	 */
	@GET
	@Path("/{profileName}")
	public Profile getProfile(@PathParam("profileName") String profileName)
	{
		return profileService.getProfile(profileName);
	}
	
	/**
	 * Returns the new profile once created
	 * @param profile
	 * @return
	 */
	@POST
	public Profile addProfile(Profile profile)
	{
		return profileService.addProfile(profile);
	}
	
	/**
	 * Returns updated profile
	 * @param profileName
	 * @param profile
	 * @return
	 */
	@PUT
	@Path("/{profileName}")
	public Profile updateProfile(@PathParam("profileName") String profileName, Profile profile)
	{
		profile.setProfileName(profileName);
		return profileService.updateProfile(profile);
	}
	
	/**
	 * Returns all profiles once the desired one is deleted. 
	 * If you look at the DELETE response after deleting profiles 1 or 2, you will see it removed
	 * but if you run a GET all profile, it will show up. Just a flaw with how we are mocking the data.
	 * @param profileName
	 * @return
	 */
	@DELETE
	@Path("/{profileName}")
	public List<Profile> deleteProfile(@PathParam("profileName") String profileName)
	{
		return profileService.removeProfile(profileName);		
	}
	
}
