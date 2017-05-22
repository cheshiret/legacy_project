package com.activenetwork.qa.testapi.interfaces.dialog;


public interface IFileDownloadDialog extends IDialog {
	public void clickOpen();
	
    public void clickCancel();
    
    public void clickClose();
    
    public void save(String fullPathAndFileName);
    
    public void closeThisDialogBoxWhenDownloadCompletes(boolean close);
    
    public void waitUntilDownloadComplete();
}
