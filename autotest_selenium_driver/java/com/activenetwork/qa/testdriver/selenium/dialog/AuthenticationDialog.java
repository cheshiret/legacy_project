package com.activenetwork.qa.testdriver.selenium.dialog;

import com.activenetwork.qa.testapi.interfaces.dialog.IAuthenticationDialog;

public class AuthenticationDialog extends ConfirmDialog implements IAuthenticationDialog {

	public AuthenticationDialog(String attributes) {
		super(attributes);
	}
	
	public AuthenticationDialog() {
		this("[TITLE:Internet Explore; CLASS:#32770]");
	}
	
	
	@Override
	public void setUserName(String name) {
		this.setTextField(10508, name);
	}
	@Override
	public void setPassword(String password) {
		this.setTextField(10513, password);
	}

}
