package dao.user;

import java.util.ArrayList;
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
	
	public String register(String userName, String password) {
		return null;
	}
	
	/**
	 * udapte the userPassword of userId using given new password
	 * @param newPassword the newPassword
	 * @return Return fail if update fail. Return true if update successfully.
	 */
	
	public Boolean updatePassword(String userId, String newPassword) {
		UserLogin queryResult = new UserLogin();
		int userIdInt = Integer.valueOf(userId).intValue();
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			queryResult = (UserLogin) session.get(UserLogin.class, userIdInt);
			queryResult.setUser_password(newPassword);
			session.saveOrUpdate(queryResult);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException e ) {
			if(session.getTransaction() != null) {
				session.getTransaction().rollback();
				e.printStackTrace();
			}
			return false;
		} finally {
			session.close();
		}
		
	}
	
	/**
	 * add a Row to user_login Table(at the same time add a row to user_profile table)
	 * @param userLogin The userLogin object which contain the register information
	 * @return Return true if add transaction success. Return false if add transaction failed.
	 */
	public Boolean addUserLogin(UserLogin userLogin) {
		Boolean returnResult = false;
		UserProfile userProfile = new UserProfile();
		UserProfileDao userProfileDao = new UserProfileDao();
		userProfileDao.addUserProfile(userProfile);
		userLogin.setUserProfile(userProfile);
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(userLogin);
			session.getTransaction().commit();
			return true;
		} catch(HibernateException e) {
			if(session.getTransaction() != null) {
				session.getTransaction().rollback();
				e.printStackTrace();
			}
			return false;
		} finally {
			session.close();
		}
		
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
			//System.out.println("userId = " + queryResult.getUser_id() + " Login Email =" + queryResult.getUser_login_email());
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
			//System.out.println("userId = " + queryResult.getUser_id() + " Login Email =" + queryResult.getUser_login_email());
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
	
	/**
	 * seach and return the queryList contain the query results in user_login table by userLoginEmail
	 * @param userName
	 * @return return the queryResultList
	 */
	public ArrayList getUserLoginByUserLoginEmail(String userLoginEmail) {
		session = sessionFactory.openSession();
		ArrayList queryResultList = new ArrayList();
		try {
			session.beginTransaction();
			Query query = session.createQuery("FROM UserLogin ul where ul.user_login_email =:ule").setParameter("ule", userLoginEmail);
			queryResultList = (ArrayList) query.list();
			return (ArrayList) queryResultList;
		} catch (HibernateException e) {
			if(session.getTransaction() != null) {
				session.getTransaction().rollback();
				e.printStackTrace();
				return (ArrayList) queryResultList;
			}
		} finally {
			session.close();
		}
		return queryResultList;
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
		System.out.println("return value is " + new UserLoginDao().test());
	}	
	
	//This test function is for testing only
	public int test() {
		try {
			//System.out.println("1");
			session = sessionFactory.openSession();
			session.close();
			session.getTransaction().commit();
			return 0;
		} catch(HibernateException e) {
			e.printStackTrace();
			System.out.println("2");
			return 1;
		} finally {
			System.out.println("3");
			
		}
		
	}
}
