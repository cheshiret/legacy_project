package com.activenetwork.qa.awo.pages.orms.licenseManager.fileimports;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.util.Property;

/**
 * @Description: Import File page. The page is shown after click Import File button on File Import List page
 * @author Lesley Wang
 * @Date  Aug 5, 2013
 */
public class LicMgrImportFilePage extends LicMgrCommonTopMenuPage {
private static LicMgrImportFilePage instance=null;
	
	protected LicMgrImportFilePage(){}
	
	public static LicMgrImportFilePage getInstance(){
		if(instance == null){
			instance=new LicMgrImportFilePage();
		}
		return instance;
	}
	/** Page Object Property Definition Begin */
	protected Property[] importFileNameTextField() {
		return Property.toPropertyArray(".id", "importFileName");
	}
	
	protected Property[] okBtn() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "OK");
	}
	
	protected Property[] cancelBtn() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Cancel");
	}
	/** Page Object Property Definition END */
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(this.importFileNameTextField());
	}
	
	public void setFileName(String fileNm) {
		browser.setTextField(this.importFileNameTextField(), fileNm, true, 0);
	}
	
	public void clickOK() {
		browser.clickGuiObject(this.okBtn());
	}
	
	public void clickCancel() {
		browser.clickGuiObject(this.cancelBtn());
	}
}
