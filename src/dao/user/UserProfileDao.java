package dao.user;

import java.util.List;

import org.hibernate.Query;

import persistent.user.profile.UserProfile;
import dao.HibernateUtility;

public class UserProfileDao extends HibernateUtility{
	
	public String updateUserProfile(UserProfile newUserProfile) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		//try to update directly test 1.0 - test result: success!
		session.update(newUserProfile);
		session.getTransaction().commit();
		session.close();
		
		return null;
	}
	
	//main function for testing only
	public static void main(String[] args) {
		
	}
	
	public void addUserProfile(UserProfile userProfile) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(userProfile);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * search the specific UserProfile by using UserProfileId
	 * @param id the UserProfileId
	 * @return if UserProfile is found, return the UserProfile object that contains 
	 * the specific UserProfile, if is not found, return empty UserProfil Object
	 *
	 */
	public UserProfile getUserProfileById(int id) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from UserProfile where profileId = "
				+ id);
		session.close();
		List queryResultList = query.list();
		if(queryResultList.size() == 1) {
			UserProfile queryResult = (UserProfile) queryResultList.get(0);
			return queryResult;
		} else {
			return new UserProfile();
		}
	}
	
	
}
