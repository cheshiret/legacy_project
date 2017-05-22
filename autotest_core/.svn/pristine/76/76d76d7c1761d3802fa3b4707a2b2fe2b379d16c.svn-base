package com.activenetwork.qa.testapi.interfaces.browser;

import com.activenetwork.qa.testapi.interfaces.dialog.IAlertDialog;
import com.activenetwork.qa.testapi.interfaces.dialog.IAuthenticationDialog;
import com.activenetwork.qa.testapi.interfaces.dialog.IConfirmDialog;
import com.activenetwork.qa.testapi.interfaces.dialog.IFileDownloadDialog;
import com.activenetwork.qa.testapi.interfaces.dialog.IFileUploadDialog;
import com.activenetwork.qa.testapi.interfaces.dialog.IPrintDialog;
import com.activenetwork.qa.testapi.page.DialogPage;

public interface IBrowser extends ISimpleBrowser {
	public void open(String url);
	
	public void open();
	
	public void load(String url);
	
	public void maximize();
	
	public void minimize();
	
	public void back();
	
	public void forward();
	
	public void refresh();
	
	public IBrowser getBrowser(String attributeName, Object value);
	
	public IBrowser getBrowser(String attributeName, Object value, int timeout);
	
	public ISimpleBrowser getHTMLDialog();
	
	public ISimpleBrowser getHTMLDialog(String attributeName, Object value);
	
	public IAlertDialog getAlertDialog();
	
	public IConfirmDialog getConfirmDialog();
	
	public IFileUploadDialog getFileUploadDialog();
	
	public IFileDownloadDialog getFiledownloadDialog();
	
	public IPrintDialog getPrintDialog();
	
	public IAuthenticationDialog getAuthenticationDialog(Object title);

	public int dismissDialogs(int timeout, DialogPage dialog, int limits);

}
