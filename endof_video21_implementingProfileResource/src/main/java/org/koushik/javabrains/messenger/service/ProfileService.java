package org.koushik.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.koushik.javabrains.messenger.database.DatabaseClass;
import org.koushik.javabrains.messenger.model.Profile;

public class ProfileService {
	
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService()
	{
		profiles.put("First Profile", new Profile(1, "First Profile", "Barack", "Obama"));
		profiles.put("Second Profile", new Profile(2, "Second Profile", "George", "Bush"));
	}
	
	/**
	 * Returns all profiles
	 * @return
	 */
	public List<Profile> getAllProfiles()
	{
		return new ArrayList<Profile>(profiles.values());
	}
	
	/**
	 * Returns a specific profile based on the name
	 * @param profileName
	 * @return
	 */
	public Profile getProfile(String profileName)
	{
		if (profiles.containsKey(profileName))
		{
			return profiles.get(profileName);
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Adds a new Profile to the DB profiles. Sets the date as well
	 * @param profile
	 * @return
	 */
	public Profile addProfile(Profile profile)
	{
		profile.setId(profiles.size() + 1);
		if (profile.getCreated() == null)
		{
			profile.setCreated(new Date());
		}
		profiles.put(profile.getProfileName(), profile);
		return profiles.get(profile.getProfileName());
	}
	
	/**
	 * Updates the profile like the way MessageServices updates Messages.
	 * One change is that i added in the id check in case the user does not include it in the json payload
	 * @param profile
	 * @return
	 */
	public Profile updateProfile(Profile profile)
	{
		if (profiles.containsKey(profile.getProfileName()))
		{
			if (profile.getCreated() == null)
			{
				Profile originalProfile = profiles.get(profile.getProfileName());
				Date originalProfileDate = originalProfile.getCreated();
				profile.setCreated(originalProfileDate);
			}
			if (profile.getId() == 0)
			{
				Profile originalProfile = profiles.get(profile.getProfileName());
				Long originalId = originalProfile.getId();
				profile.setId(originalId);
			}
			profiles.put(profile.getProfileName(), profile);
			return profiles.get(profile.getProfileName());
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Removes a profile
	 * @param profileName
	 * @return
	 */
	public List<Profile> removeProfile(String profileName)
	{
		profiles.remove(profileName);
		return new ArrayList<Profile>(profiles.values());
	}

}
