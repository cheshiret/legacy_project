package com.activenetwork.qa.testapi.page;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.dialog.IAlertDialog;

/**
 * The general Dialog is the Confirm/Alert dialog page which has a OK button to confirm
 * @author QA
 *
 */
public class AlertDialogPage extends DialogPage{
	private static AlertDialogPage _instance=null;
	
	public static AlertDialogPage getInstance() {
		if(null==_instance) {
			_instance=new AlertDialogPage();
		}
		_instance.resetDefault();
		return _instance;
	}
	
	protected AlertDialogPage() {
		super();
	}

	@Override
	public boolean exists() {
		dialog=Browser.getInstance().getAlertDialog();
		return dialog!=null;
	}


	public void clickOK() {
		((IAlertDialog)dialog).clickOK();
	}
	
	public void clickLeaveThisPage() {
		((IAlertDialog)dialog).clickOK();
	}
	
	@Override
	public void dismiss() {
		clickOK();	
	}	
}
