package module.user;

import dao.user.UserLoginDao;

public class PasswordModule {
	public String updatePassword(String userId, String newPassword) {
		UserLoginDao userLoginDao = new UserLoginDao();
		return userLoginDao.updatePassword(userId, newPassword);
	}
}
