package module.user.authen;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import dao.user.UserLoginDao;

public class LoginModule {
	/**
	 * user try to login to the system
	 * @param userLoginEmail the user's login email account 
	 * @param password the user's password
	 * @return "fail" if login failed. "user_id" if login success
	 */
	public String login(String user_login_email, String user_password) {
		UserLoginDao userLoginDao = new UserLoginDao();
		return userLoginDao.login(user_login_email, user_password);
	}
}
