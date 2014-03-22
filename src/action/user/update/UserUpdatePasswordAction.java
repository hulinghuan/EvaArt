package action.user.update;

import module.user.PasswordModule;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserUpdatePasswordAction extends ActionSupport{
	private String newPassword;
	private ActionContext actionContext = ActionContext.getContext();
	
	public String execute() {
		String userId;
		userId = (String) actionContext.getSession().get("userId");
		if( userId != null) {
			if(new PasswordModule().updatePassword(userId, this.newPassword) == "success") {
				return SUCCESS;
			}
		} else {
			return ERROR;
		}
		//program will not reach here
		return null;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPasswrod) {
		this.newPassword = newPasswrod;
	}
	public ActionContext getActionContext() {
		return actionContext;
	}
	public void setActionContext(ActionContext actionContext) {
		this.actionContext = actionContext;
	}
	

}
