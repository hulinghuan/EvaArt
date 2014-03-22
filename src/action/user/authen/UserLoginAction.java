package action.user.authen;

import module.user.authen.LoginModule;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserLoginAction extends ActionSupport{
	private String loginEmail;
	private String password;
	private ActionContext actionContext = ActionContext.getContext();
	public String execute() throws Exception {
		String user_id = "";
		if( (user_id = new LoginModule().login(loginEmail, password)) != "fail" ) {
			actionContext.getSession().put("userId", user_id);
			return SUCCESS;
		} else {
			return ERROR;
		}
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