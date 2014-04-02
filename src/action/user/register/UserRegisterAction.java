package action.user.register;

import module.user.authen.LoginModule;
import module.user.register.RegisterModule;

import com.opensymphony.xwork2.ActionSupport;

public class UserRegisterAction extends ActionSupport{
	private String loginEmail;
	private String password;
	
	public String execute() throws Exception {
		if (new RegisterModule().register(loginEmail, password) == true) {
			return SUCCESS;
		} else {
			return ERROR;
		}
		//need add register fail or success if statement in future.
		
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
