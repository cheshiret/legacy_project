package com.activenetwork.qa.testapi.page;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.dialog.IAuthenticationDialog;
import com.activenetwork.qa.testapi.util.RegularExpression;

public abstract class AuthenticationDialogPage extends ConfirmDialogPage implements IAuthenticationDialog{
	protected RegularExpression titlePattern;
	
	public boolean exists() {
		dialog=Browser.getInstance().getAuthenticationDialog(titlePattern);
		
		return dialog!=null;
	}

	@Override
	public void setPassword(String password) {
		((IAuthenticationDialog)dialog).setPassword(password);
	}

	@Override
	public void setUserName(String name) {
		((IAuthenticationDialog)dialog).setUserName(name);	
	}
	
	public void login(String name,String password) {
		this.setUserName(name);
		this.setPassword(password);
		this.clickOK();
	}

}
