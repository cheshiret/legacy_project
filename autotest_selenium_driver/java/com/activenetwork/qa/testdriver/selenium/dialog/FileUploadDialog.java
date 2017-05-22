package com.activenetwork.qa.testdriver.selenium.dialog;

import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.interfaces.dialog.IFileUploadDialog;
import com.sun.jna.WString;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Aug 1, 2012
 */
public class FileUploadDialog extends Dialog implements IFileUploadDialog {
	
	public FileUploadDialog(String attribute) {
		super(attribute);
	}
	
	public FileUploadDialog() {
		this("[TITLE:Choose File; CLASS:#32770]");
	}
	
	@Override
	public void clickOpen() {
		this.clickButton("&Open");
	}

	@Override
	public void clickCancel() {
		this.clickButton("Cancel");
	}

	@Override
	public void clickClose() {
		
	}

	@Override
	public void setFileName(String fullPathAndFileName) {
		String fileNameFieldProp;
		fileNameFieldProp="[CLASS:Edit]";
		
		dialog.AU3_WinActivate(new WString(attributes), new WString(""));
		dialog.AU3_ControlSetText(new WString(attributes), new WString(""), new WString(fileNameFieldProp),new WString(fullPathAndFileName));
	}

	@Override
	public void selectFilesOfType(String type) {
		
	}

	@Override
	public void chooseAndOpenFile(String fullPathAndFileName) {
		try {    		
			this.setFileName(fullPathAndFileName);
			this.clickOpen();
		} catch(Exception e) {
			throw new ActionFailedException(e);
		}
	}
}
