package dao.user;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

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
		session = sessionFactory.openSession();
		session.beginTransaction();
		try{
			Query query = session.createQuery("FROM UserLogin where user_login_email = '"
					+ userName + "' and user_password = '"
					+ password + "'");			
			List userLogins = query.list();
			session.close();
			if(userLogins.size() != 1) {
				return "fail";
			} else {
				UserLogin userLogin = (UserLogin) userLogins.get(0);
				if(password.equals(userLogin.getUser_password()) == true) {
					return String.valueOf(userLogin.getUser_id());
				} else {
					return "fail";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String register(String userName, String password) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		try{
			//check the database for same login email exception
			//potential bug: the user_login_email can be null even already set user_login_email as not null attributes in MySQL
			Query query = session.createQuery("FROM UserLogin where user_login_email = '"
					+ userName + "'");
			List userLogins = query.list();
			
			if(userLogins.size() != 0) {
				session.close();
				return "error";
			} else {
				UserLogin userLogin = new UserLogin(userName, password);
				session.save(userLogin);
				session.getTransaction().commit();
				session.close();
				return "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * udapte the userPassword
	 * @param newPassword the newPassword
	 * @return return "error" if update fail. "success" if update successfully.
	 */
	public String updatePassword(String userId, String newPassword) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			Query query = session.createQuery("FROM UserLogin where user_id = '"
					+ userId + "'");
			List userLogins = query.list();
			if(userLogins.size() == 0) {
				session.close();
				return "error";
			} else {
				UserLogin userLogin = (UserLogin) userLogins.get(0);
				userLogin.setUser_password(newPassword);
				session.update(userLogin);
				session.getTransaction().commit();
				session.close();
				return "success";
				//session.persist(userLogin);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		//program will not reach here
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
	public static void main(String[] args) {
		System.out.println(new UserLoginDao().login("hulinghuan@gmail.com", "123456"));
		//System.out.println(new UserLoginDao().register("linghuan@live.cn", "1111111"));
	}
}
