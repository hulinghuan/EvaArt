package module.user.register;

import persistent.user.login.UserLogin;
import dao.user.UserLoginDao;

public class RegisterModule {
		public String login(String user_login_email, String user_password) {
			UserLoginDao userLoginDao = new UserLoginDao();
			return userLoginDao.login(user_login_email, user_password);
		}
		
		public void register(String user_login_email, String user_password) {
			UserLogin userLogin = new UserLogin(user_login_email, user_password);
			UserLoginDao userLoginDao = new UserLoginDao();
			userLoginDao.addUserLogin(userLogin);
		}
}

