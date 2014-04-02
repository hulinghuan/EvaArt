package module.user.authen;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import persistent.user.login.UserLogin;
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
		UserLogin queryResult = null;
		List queryResultList = new ArrayList();
		queryResultList = userLoginDao.getUserLoginByUserLoginEmail(user_login_email);
		if(queryResultList.size() == 1) {
			queryResult = (UserLogin) queryResultList.get(0);
			if (user_password.equals(queryResult.getUser_password()) == true) {
				return String.valueOf(queryResult.getUser_id());
			} else {
				return "fail";
			}
		} else {
			return "fail";
		}
		
	}
}
