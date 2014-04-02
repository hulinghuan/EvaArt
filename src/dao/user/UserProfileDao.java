package dao.user;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import persistent.user.login.UserLogin;
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
		UserProfileDao userProfileDao = new UserProfileDao();
		//try to delete rows contain the foreign key of user_login table
		
		//userProfileDao.deleteUserProfileById(13);
		userProfileDao.getUserProfileById(13);
		
		
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
	public UserProfile getUserProfileById(int UserProfileId) {
		session = sessionFactory.openSession();
		try {
		session.beginTransaction();
		Query query = session.createQuery("from UserProfile where profileId = "
				+ UserProfileId);
		
		List queryResultList = query.list();
		if(queryResultList.size() == 1) {
			UserProfile queryResult = (UserProfile) queryResultList.get(0);
			System.out.println(queryResult.getProfileFirstName());
            
			return queryResult;
		} else {
			
			return new UserProfile();
		} 
		} catch (HibernateException e) {
			if(session.getTransaction() != null) {
				session.getTransaction().rollback();
				e.printStackTrace();
			}
		} finally {
			session.close();
		}
		return null;
	}
	
	public UserProfile getUserProfileByUserId(String userId) {
		int userIdInt = Integer.valueOf(userId).intValue();
		UserProfile queryResult = new UserProfile();
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			queryResult = (UserProfile) session.get(UserProfile.class, userIdInt);
			return queryResult;
		} catch(HibernateException e) {
			if(session.getTransaction() != null) {
				session.getTransaction().rollback();
				e.printStackTrace();
			}
			return queryResult;
		} finally {
			session.close();
		}
	}
	
	public void deleteUserProfileById(int userProfileId) {
		UserLoginDao userLoginDao = new UserLoginDao();
		userLoginDao.deleteUserLoginByUserProfileId(userProfileId);
		
		session = sessionFactory.openSession();
		try {
			
			session.beginTransaction();
			UserProfile userProfile = (UserProfile) session.get(UserProfile.class, userProfileId);
			session.delete(userProfile);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
				e.printStackTrace();
			} 
		} finally {
			session.close();
		}
		
	}
} 
