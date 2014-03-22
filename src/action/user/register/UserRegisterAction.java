package action.user.register;

import module.user.authen.LoginModule;
import module.user.register.RegisterModule;

import com.opensymphony.xwork2.ActionSupport;

public class UserRegisterAction extends ActionSupport{
	private String loginEmail;
	private String password;
	
	public String execute() throws Exception {
		new RegisterModule().register(loginEmail, password);
		//need add register fail or success if statement in future.
		return SUCCESS;
	}

	public String getLoginEmail() {
		return loginEmail;
	}

	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
