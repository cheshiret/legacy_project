package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description:
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Mar 13, 2014
 */
public class LicMgrAddInstructorWidget extends DialogWidget{
	static class SingletonHolder {
		protected static LicMgrAddInstructorWidget _instance = new LicMgrAddInstructorWidget();
	}

	protected LicMgrAddInstructorWidget() {
		super("Add Instructor");
	}

	public static LicMgrAddInstructorWidget getInstance() {
		return SingletonHolder._instance;
	}

	protected static String LABEL_CREATENEW = "Create New";
	protected static String LABEL_CREATEFROMMDWFP = "Create from MDWFP #";
			
	/** Page Object Property Definition Begin */
	protected Property[] createRadio(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityInstructorAddInstructorDialog-\\d+\\.createType", false));
	}
	
	protected Property[] createLabel(String label){
		return Property.concatPropertyArray(label(), ".text", label, ".for", new RegularExpression("ActivityInstructorAddInstructorDialog-\\d+\\.createType", false));
	}
	
	protected Property[] customerNum(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityInstructorAddInstructorDialog-\\d+\\.customerNum", false));
	}
	
	protected Property[] ok(){
		return Property.concatPropertyArray(a(), ".text", "OK");
	}
	
	protected Property[] cancel(){
		return Property.concatPropertyArray(a(), ".text", "Cancel");
	}
	/** Page Object Property Definition END */
	
	public String getCreateRadioForValue(String label){
		IHtmlObject[] objs = browser.getHtmlObject(createLabel(label));
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find "+label+" radio");
		}
		String value = objs[0].getProperty(".for");
		Browser.unregister(objs);
		return value;
	}
	
	public void selectCreateNewRadio(){
		browser.selectRadioButton(".id", getCreateRadioForValue(LABEL_CREATENEW));
	}
	
	public void selectCreateFromCustNumRadio(){
		browser.selectRadioButton(".id", getCreateRadioForValue(LABEL_CREATEFROMMDWFP));
	}
	
	public void setCustomerNum(String custNum){
		browser.setTextField(customerNum(), custNum);
	}
	public void clickOK(){
		browser.clickGuiObject(ok());
	}
	
	public void clickCancel(){
		browser.clickGuiObject(cancel());
	}
	
	public void setupAddInstructorInfo(String custNum){
		if(StringUtil.notEmpty(custNum)){
			selectCreateFromCustNumRadio();
			ajax.waitLoading();
			setCustomerNum(custNum);
		}else {
			selectCreateNewRadio();
			ajax.waitLoading();
		}
	}
}

