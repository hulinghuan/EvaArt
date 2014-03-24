package dao.user;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;

import persistent.user.login.UserLogin;
import persistent.user.profile.UserProfile;
import dao.HibernateUtility;

public class UserLoginDao extends HibernateUtility{
	
	/**
	 * user try to login to the system
	 * @param userLoginEmail the user's login email account 
	 * @param password the user's password
	 * @return "fail" if login failed. "user_id" if login success
	 */
	public String login(String userName, String password) {
		return null;
	}
	
	public String register(String userName, String password) {
		return null;
	}
	
	/**
	 * udapte the userPassword
	 * @param newPassword the newPassword
	 * @return return "error" if update fail. "success" if update successfully.
	 */
	
	public String updatePassword(String userId, String newPassword) {
		return null;
	}
	
	public void addUserLogin(UserLogin userLogin) {
		UserProfile userProfile = new UserProfile();
		UserProfileDao userProfileDao = new UserProfileDao();
		userProfileDao.addUserProfile(userProfile);
		userLogin.setUserProfile(userProfile);
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(userLogin);
		session.getTransaction().commit();
		session.close();
	}
	
	public void deleteUserLoginById(int userId) {
		session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			UserLogin userLogin = (UserLogin) session.get(UserLogin.class, userId);
			session.delete(userLogin);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if(session.getTransaction() != null) {
				session.getTransaction().rollback();
				e.printStackTrace();
			}
		} finally {
			session.close();
		}
	}
	
	public UserLogin getUserLoginById(int userId) {
		session = sessionFactory.openSession();
		UserLogin queryResult = null;
		try {
			session.beginTransaction();
			queryResult = (UserLogin) session.get(UserLogin.class, userId);
			//this println is for testing only
			System.out.println("userId = " + queryResult.getUser_id() + " Login Email =" + queryResult.getUser_login_email());
			return queryResult;
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
	
	public UserLogin getUserLoginByProfileId(int userProfileId) {
		session = sessionFactory.openSession();
		UserLogin queryResult = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery("FROM UserLogin ul where ul.userProfile.profileId =:pid").setParameter("pid", userProfileId);
			List queryResultList = query.list();
			queryResult = (UserLogin) queryResultList.get(0);
			//this println is for testing only
			System.out.println("userId = " + queryResult.getUser_id() + " Login Email =" + queryResult.getUser_login_email());
			return queryResult;
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
	
	public void deleteUserLoginByUserProfileId(int UserProfileId) {
		session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			//UserProfile userProfile = new UserProfile();
			//userProfile.setProfileId(13);
			//Query query = session.createQuery("").setParameter("uid", 10);
			//Query query = session.createQuery("FROM UserLogin U where U.user_id = 10");
			Query query = session.createQuery("FROM UserLogin where user_id = 10");
			List queryResultList = query.list();
			UserLogin userLogin = (UserLogin) queryResultList.get(0);
			session.delete(userLogin);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if(session.getTransaction() != null) {
				session.getTransaction().rollback();
				e.printStackTrace();
			}
		} finally {
			session.close();
		}
	}
	public void listUserLogin() {
		session = sessionFactory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         List queryResultList = session.createQuery("FROM UserLogin").list(); 
	         for (Iterator iterator = 
	                           queryResultList.iterator(); iterator.hasNext();){
	            UserLogin userLogin = (UserLogin) iterator.next(); 
	            System.out.print("First Name: " + userLogin.getUser_id()); 
	         }
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   
	}
	public static void main(String[] args) {
		UserLoginDao userLoginDao = new UserLoginDao();
		//userLoginDao.getUserLoginById(10);
		//userLoginDao.listUserLogin();
		/*UserLogin userLogin = new UserLogin();
		userLogin.setUser_login_email("bbb");
		userLogin.setUser_password("ccc");
		userLoginDao.addUserLogin(userLogin);*/
		userLoginDao.getUserLoginById(1);
		userLoginDao.getUserLoginByProfileId(3);
	}
}
