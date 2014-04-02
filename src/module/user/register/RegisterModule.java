package module.user.register;

import java.util.List;

import persistent.user.login.UserLogin;
import dao.user.UserLoginDao;

public class RegisterModule {
		
		public Boolean register(String user_login_email, String user_password) {
			UserLoginDao userLoginDao = new UserLoginDao();
			UserLogin userLogin = new UserLogin(user_login_email, user_password);
			List queryResultList = userLoginDao.getUserLoginByUserLoginEmail(user_login_email);
			if ( queryResultList.size() == 0 ) {
				if(userLoginDao.addUserLogin(userLogin) == true) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
}

