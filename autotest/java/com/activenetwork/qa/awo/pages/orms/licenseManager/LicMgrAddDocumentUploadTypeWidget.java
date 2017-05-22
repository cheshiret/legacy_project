/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author QA
 * @Date  Feb 25, 2014
 */
public class LicMgrAddDocumentUploadTypeWidget extends DialogWidget{
	private static LicMgrAddDocumentUploadTypeWidget _instance = null;
	
	private LicMgrAddDocumentUploadTypeWidget () {}
	
	public static LicMgrAddDocumentUploadTypeWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrAddDocumentUploadTypeWidget();
		}
		return _instance;
	}
	
	protected Property[] typePrp(){
		return Property.toPropertyArray(".id", new RegularExpression("DocumentTypeView-\\d+\\.name",false));
	}
	
	public boolean exists(){
		boolean flag1=super.exists();
		boolean flag2=browser.checkHtmlObjectExists(this.typePrp());
		return flag1 && flag2;
	}
	
	public void setType(String type){
		browser.setTextField(this.typePrp(), type);
	}
	
}
