package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.FacilityPrdAttr;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jan 9, 2014
 */
public class LicMgrAddFacilityProductWidget extends DialogWidget{
	static class SingletonHolder {
		protected static LicMgrAddFacilityProductWidget _instance = new LicMgrAddFacilityProductWidget();
	}

	protected LicMgrAddFacilityProductWidget() {
		super("Facility Name:.*");
	}

	public static LicMgrAddFacilityProductWidget getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] createModifyFacilityPrdGroupBar(){
		return Property.concatPropertyArray(table(), ".id", "CreateModifyFacilityProductGroupBar");
	}
	
	protected Property[] facilityPrdCode(){
		return Property.toPropertyArray(".id", new RegularExpression("FacilityProductView-\\d+.productCode", false));
	}
	
	protected Property[] facilityPrdName(){
		return Property.toPropertyArray(".id", new RegularExpression("FacilityProductView-\\d+.name", false));
	}
	
	protected Property[] facilityPrdStatus(){
		return Property.toPropertyArray(".id", new RegularExpression("FacilityProductView-\\d+.entityStatus", false));
	}
	
	protected Property[] facilityPrdDes(){
		return Property.toPropertyArray(".id", new RegularExpression("FacilityProductView-\\d+.facilityProductDesc", false));
	}
	
	protected Property[] facilityPrdType(){
		return Property.toPropertyArray(".id", new RegularExpression("FacilityProductView-\\d+.productGroup", false));
	}
	
	protected Property[] capacity(){
		return Property.toPropertyArray(".id", new RegularExpression("FacilityProductView-\\d+.maxCapacity", false));
	}
	
	protected Property[] adaCompliance(){
		return Property.toPropertyArray(".id", new RegularExpression("FacilityProductView-\\d+.handicappedAccessible", false));
	}
	
	protected Property[] facilityPrdGroupBar(){
		return Property.toPropertyArray(".id", "facilityProductGroupBar");
	}
	
	protected Property[] addNew(){
		return Property.concatPropertyArray(a(), ".text", "Add New");
	}
	
	protected Property[] ok(){
		return Property.concatPropertyArray(a(), ".text", "OK");
	}
	
	protected Property[] cancel(){
		return Property.concatPropertyArray(a(), ".text", "Cancel");
	}
	/** Page Object Property Definition END */
	
	public boolean exists(){
		boolean flag1=super.exists();
		boolean flag2=browser.checkHtmlObjectExists(createModifyFacilityPrdGroupBar());
		return flag1 && flag2;
	}
	
	public void setFacilityPrdCode(String prdCode){
		browser.setTextField(facilityPrdCode(), prdCode);
	}
	
	public String getFacilityPrdCode(){
		return browser.getTextFieldValue(facilityPrdCode());
	}
	
	public void setFacilityPrdName(String prdName){
		browser.setTextField(facilityPrdName(), prdName);
	}
	
	public String getFacilityPrdName(){
		return browser.getTextFieldValue(facilityPrdName());
	}
	
	public void selectFacilityPrdStatus(String status){
		browser.selectDropdownList(facilityPrdStatus(), status);
	}
	
	public String getFacilityPrdStatus(){
		return browser.getDropdownListValue(facilityPrdStatus(), 0); 
	}
	
	public void setFacilityPrdDesc(String prdDesc){
		browser.setTextArea(facilityPrdDes(), prdDesc);
	}
	
	public String getFacilityPrdDesc(){
		return browser.getTextAreaValue(facilityPrdDes());
	}
	
	public void selectFacilityPrdType(String prdType){
		browser.selectDropdownList(facilityPrdType(), prdType);
	}
	
	public String getFacilityPrdType(){
		return browser.getDropdownListValue(facilityPrdType(), 0);
	}
	
	public List<String> getFacilityPrdTypes(){
		return browser.getDropdownElements(facilityPrdType());
	}
	
	public void clickAddNew(){
		browser.clickGuiObject(Property.atList(facilityPrdGroupBar(), addNew()));
	}
	
	public void setCapacity(String capacity){
		browser.setTextField(capacity(), capacity);
	}
	
	public String getCapacity(){
		return browser.getTextFieldValue(capacity());
	}
	
	public void selectHandicappedAccessible(String handicappedAccessible){
		browser.selectDropdownList(adaCompliance(), handicappedAccessible);
	}
	
	public String getHandicappedAccessible(){
		return browser.getDropdownListValue(adaCompliance(), 0);
	}
	
	public void clickOK(){
		browser.clickGuiObject(Property.atList(createModifyFacilityPrdGroupBar(), ok()));
	}
	
	public void clickCancel(){
		browser.clickGuiObject(Property.atList(createModifyFacilityPrdGroupBar(), cancel()));
	}
	
	public boolean isAddNewExisted(){
		return browser.checkHtmlObjectExists(Property.atList(facilityPrdGroupBar(), addNew()));
	}
	
	public void verifyAddNewExisted(boolean existed){
		boolean fromUI = isAddNewExisted();
		if(existed!=fromUI){
			throw new ErrorOnPageException("Add new button should "+(existed?"":"not ")+"exist.");
		}
		logger.info("Successfully verify add new button "+(existed?"":"displays ")+"doesn't exist.");
	}
	
	public void setupFacilityPrdInfo(Data<FacilityPrdAttr> src){
		setFacilityPrdCode(src.stringValue(FacilityPrdAttr.prdCode));
		setFacilityPrdName(src.stringValue(FacilityPrdAttr.prdName));
		selectFacilityPrdStatus(src.stringValue(FacilityPrdAttr.prdStatus));
		setFacilityPrdDesc(src.stringValue(FacilityPrdAttr.prdDesc));
		selectFacilityPrdType(src.stringValue(FacilityPrdAttr.prdType));
		setCapacity(src.stringValue(FacilityPrdAttr.capacity));
		selectHandicappedAccessible(src.stringValue(FacilityPrdAttr.handicappedAccessible));
	}
}

