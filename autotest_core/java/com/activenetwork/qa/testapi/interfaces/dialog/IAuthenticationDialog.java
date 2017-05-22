package com.activenetwork.qa.testapi.interfaces.dialog;


public interface IAuthenticationDialog extends IConfirmDialog {
	void setUserName(String name);
	void setPassword(String password);
}
