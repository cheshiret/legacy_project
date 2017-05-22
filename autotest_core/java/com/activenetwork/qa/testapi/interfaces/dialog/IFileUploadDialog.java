package com.activenetwork.qa.testapi.interfaces.dialog;


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
public interface IFileUploadDialog extends IDialog {
	public void clickOpen();
	
	public void clickCancel();
	
	public void clickClose();
	
	public void setFileName(String fullPathAndFileName);
	
	public void selectFilesOfType(String type);
	
	public void chooseAndOpenFile(String fullPathAndFileName);
}
