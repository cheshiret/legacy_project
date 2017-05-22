package com.activenetwork.qa.awo.pages.web;

import com.activenetwork.qa.testapi.interfaces.dialog.IAuthenticationDialog;
import com.activenetwork.qa.testapi.page.AuthenticationDialogPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class AdiminDoDialogPage extends AuthenticationDialogPage {
	
	private static AdiminDoDialogPage _instance=null;
	
	public static AdiminDoDialogPage getInstance() {
		if(null==_instance) {
			_instance=new AdiminDoDialogPage();
		}
		
		return _instance;
	}
	
	protected AdiminDoDialogPage() {
		titlePattern=new RegularExpression("Connect to .+",false);
		this.dismissable=false;
		
	}

	@Override
	public String getDialogMessage() {
		return ((IAuthenticationDialog)dialog).getDialogMessage();
	}

	@Override
	public void clickLeaveThisPage() {
		// TODO Auto-generated method stub
		
	}	
	
}
