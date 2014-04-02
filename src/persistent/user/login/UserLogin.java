package persistent.user.login;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import persistent.user.address.UserBillingAddress;
import persistent.user.address.UserShippingAddress;
import persistent.user.profile.UserProfile;


public class UserLogin {
	private int user_id;
	private String user_login_email;
	private String user_password;
	private UserProfile userProfile;
	
	public UserLogin(){
		
	}
	
	/**
	 * initial a new UserLogin persistent instance with following initial value
	 * @param user_login_email
	 * @param user_password
	 * @param userProfile
	 */
	public UserLogin(String user_login_email, String user_password) {
		this.user_login_email = user_login_email;
		this.user_password = user_password;
		this.userProfile = userProfile;
	}

	//main function for testing only
	public static void main(String[] args) {
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_login_email() {
		return user_login_email;
	}
	public void setUser_login_email(String user_login_email) {
		this.user_login_email = user_login_email;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public UserProfile getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

}
