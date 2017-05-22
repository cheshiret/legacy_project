package com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.cust.OwnerAttr;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.interfaces.ITable;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA
 * @Date  Jan 16, 2014
 */
public class LicMgrOwnerDetailsWidget extends DialogWidget{
	static class SingletonHolder {
		protected static LicMgrOwnerDetailsWidget _instance = new LicMgrOwnerDetailsWidget();
	}

	protected LicMgrOwnerDetailsWidget() {
	}

	public static LicMgrOwnerDetailsWidget getInstance() {
		return SingletonHolder._instance;
	}

	private static String LABEL_PROPERTYID = "Property ID";
	private static String LABEL_OWNERID = "ID";
	private static String LABEL_CUSTOMER = "Customer";
	private static String LABEL_APPLICATIONNAME = "Application Name";
	private static String LABEL_CREATIONDATE = "Creation Date";
	private static String LABEL_CREATIONUSER = "Creation User";

	/** Page Object Property Definition Begin */
	protected Property[] ownerDetailsTable(){
		return Property.concatPropertyArray(table(), ".id", new RegularExpression("FormBar_\\d+", false), ".text", new RegularExpression("^(Find Owner|Owner Details).*", false));
	}

	protected Property[] conservation(){
		return Property.toPropertyArray(".id", new RegularExpression("HFCustomerSearchCriteria-\\d+.searchByValue", false));
	}

	protected Property[] lastName(){
		return Property.toPropertyArray(".id", new RegularExpression("HFCustomerSearchCriteria-\\d+.lastName", false));
	}

	protected Property[] firstName(){
		return Property.toPropertyArray(".id", new RegularExpression("HFCustomerSearchCriteria-\\d+.firstName", false));
	}

	protected Property[] businessName(){
		return Property.toPropertyArray(".id", new RegularExpression("HFCustomerSearchCriteria-\\d+.businessName", false));
	}

	protected Property[] dob(){
		return Property.toPropertyArray(".id", new RegularExpression("HFCustomerSearchCriteria-\\d+.dateOfBirth_ForDisplay", false));
	}

	protected Property[] ownerID(){
		return Property.toPropertyArray(".id", new RegularExpression("LandPropertyOwnershipView-\\d+.id:ZERO_TO_NEW", false));
	}

	protected Property[] typeOfOwnership(){
		return Property.toPropertyArray(".id", new RegularExpression("LandPropertyOwnershipView-\\d+.ownershipType", false));
	}

	protected Property[] yearOwned(){
		return Property.toPropertyArray(".id", new RegularExpression("LandPropertyOwnershipView-\\d+.yearOwned", false));
	}

	protected Property[] corporation(){
		return Property.toPropertyArray(".id", new RegularExpression("LandPropertyOwnershipView-\\d+.corporation", false));
	}

	protected Property[] ownerComments(){
		return Property.toPropertyArray(".id", new RegularExpression("LandPropertyOwnershipView-\\d+.comments", false));
	}

	protected Property[] okAndCancelTable(){
		return Property.concatPropertyArray(table(), ".text", new RegularExpression("OK\\s+Cancel", false));
	}

	protected Property[] ownerStatus(){
		return Property.toPropertyArray(".id", new RegularExpression("LandPropertyOwnershipView-\\d+.status", false));
	}

	protected Property[] ok(){
		return Property.concatPropertyArray(a(), ".text", "OK");
	}

	protected Property[] cancel(){
		return Property.concatPropertyArray(a(), ".text", "Cancel");
	}

	protected Property[] search(){
		return Property.concatPropertyArray(a(), ".text", "Search");
	}

	protected Property[] ownerListTable(){
		return Property.concatPropertyArray(table(), ".id", "ownerList");
	}

	protected Property[] ownerRowTR(){
		return Property.concatPropertyArray(tr(), ".className", "oddRow");
	}

	protected Property[] ownerRadio(){
		return Property.toPropertyArray(".id", new RegularExpression("GenericGrid-\\d+.selectedItems", false));
	}

	protected Property[] textField(){
		return Property.concatPropertyArray(input("text"));
	}

	protected Property[] spanField(){
		return Property.concatPropertyArray(span());
	}

	protected Property[] spanWhereLabelIs(String label){
		return Property.concatPropertyArray(span(), ".text", new RegularExpression("^"+label+".*", false));
	}

	/** Page Object Property Definition END */

	public boolean exists() {
		boolean flag1 = super.exists();
		boolean flag2 = browser.checkHtmlObjectExists(ownerDetailsTable());
		boolean flag3 = browser.checkHtmlObjectExists(ownerID());
		return flag1 && (flag2 || flag3);
	}

	public void setConservation(String conservation){
		browser.setTextField(conservation(), conservation);
	}

	public void setLastName(String lastName){
		browser.setTextField(lastName(), lastName);
	}

	public void setFirstName(String firstName){
		browser.setTextField(firstName(), firstName);
	}

	public void setBusinessName(String businessName){
		browser.setTextField(businessName(), businessName);
	}

	public void setDateOfBirth(String dateOfBirth){
		browser.setTextField(dob(), dateOfBirth);
	}

	public void setOwnerComments(String comments){
		browser.setTextArea(ownerComments(), comments);
	}

	public void selectTypeOfOwnership(String typeOfOwnership){
		browser.selectDropdownList(typeOfOwnership(), typeOfOwnership);
	}

	public String getTypeOfOwnership(){
		return browser.getDropdownListValue(typeOfOwnership(), 0);
	}

	public void setYearOwned(String yearOwned){
		browser.setTextField(yearOwned(), yearOwned);
	}

	public String getYearOwned(){
		return browser.getTextFieldValue(yearOwned());
	}

	public void setCorporation(String corporation){
		browser.setTextField(corporation(), corporation);
	}

	public String getCorporation(){
		return browser.getTextFieldValue(corporation());
	}

	public void setComments(String comments){
		browser.setTextArea(ownerComments(), comments);
	}

	public String getComments(){
		return browser.getTextAreaValue(ownerComments());
	}

	public void clickSearch(){
		browser.clickGuiObject(search());
	}

	public void selectOwnerStatus(String status){
		browser.selectDropdownList(ownerStatus(), status);
	}

	public String getOwnerStatus(){
		return browser.getDropdownListValue(ownerStatus(), 0);
	}

	public IHtmlObject[] spanObjsWhereLabelIs(String label){
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(ownerDetailsTable(), spanWhereLabelIs(label)));
		if(objs.length<1){
			throw new ObjectNotFoundException("Failed to find span with label:"+label);
		}
		return objs;
	}

	public String getDisabledTextFieldValue(String label){
		IHtmlObject[] objs = browser.getHtmlObject(textField(), spanObjsWhereLabelIs(label)[0]);
		if(objs.length<1){
			throw new ObjectNotFoundException("Failed to find Span objects which label is "+label);
		}
		if(objs[objs.length-1].getProperty(".isDisabled").equalsIgnoreCase("false")){
			throw new ErrorOnPageException("Text field should be disabled with label:"+label);
		}
		String value = objs[objs.length-1].getProperty(".value");
		logger.info(label+":"+value);
		Browser.unregister(objs);
		return value;
	}

	public String getOwnerID(){
		return getDisabledTextFieldValue(LABEL_OWNERID);
	}

	public String getCustomer(){
		return getDisabledTextFieldValue(LABEL_CUSTOMER);
	}

	public String getPropertyID(){
		return getDisabledTextFieldValue(LABEL_PROPERTYID);
	}

	public String getApplicationName(){
		return getDisabledTextFieldValue(LABEL_APPLICATIONNAME);
	}

	public String getCreationDate(){
		return getDisabledTextFieldValue(LABEL_CREATIONDATE);
	}

	public String getCreationUser(){
		return getDisabledTextFieldValue(LABEL_CREATIONUSER);
	}

	public void clickOK(){
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(okAndCancelTable(), ok()));
		if(objs.length<1){
			throw new ObjectNotFoundException("Failed to find OK button in Owner Details page.");
		}
		objs[objs.length-1].click();
		Browser.unregister(objs);
	}

	public void clickCancel(){
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(okAndCancelTable(), cancel()));
		if(objs.length<1){
			throw new ObjectNotFoundException("Failed to find Cancel button in Owner Details page.");
		}
		objs[objs.length-1].click();
		Browser.unregister(objs);
	}

	public void findOwner(Customer cust){
		if(StringUtil.notEmpty(cust.custId))
			setConservation(cust.custId);
		if(StringUtil.notEmpty(cust.lName))
			setLastName(cust.lName);
		if(StringUtil.notEmpty(cust.fName))
			setFirstName(cust.fName);
		if(StringUtil.notEmpty(cust.businessName))
			setBusinessName(cust.businessName);
		if(StringUtil.notEmpty(cust.dateOfBirth))
			setDateOfBirth(cust.dateOfBirth);

		clickSearch();
		ajax.waitLoading();
		waitLoading();
	}
//
//	public void findOwnerFilter(Data<PropertyAttr> cp){
//		if(StringUtil.notEmpty(cp.stringValue(PropertyAttr.typeOfOwnership)))
//			selectTypeOfOwnership(cp.stringValue(PropertyAttr.typeOfOwnership));
//		if(StringUtil.notEmpty(cp.stringValue(PropertyAttr.yearOwned)))
//			setYearOwned(cp.stringValue(PropertyAttr.yearOwned));
//		if(StringUtil.notEmpty(cp.stringValue(PropertyAttr.corporation)))
//			setCorporation(cp.stringValue(PropertyAttr.corporation));
//		if(StringUtil.notEmpty(cp.stringValue(PropertyAttr.ownershipComments)))
//			setComments(cp.stringValue(PropertyAttr.ownershipComments));
//	}

	public void setOwnerDetails(Data<OwnerAttr> owner){
		if(StringUtil.notEmpty(owner.stringValue(OwnerAttr.ownershipStatus)))
			selectOwnerStatus(owner.stringValue(OwnerAttr.ownershipStatus));
		if(StringUtil.notEmpty(owner.stringValue(OwnerAttr.yearOwned)))
			selectTypeOfOwnership(owner.stringValue(OwnerAttr.typeOfOwnership));
		if(StringUtil.notEmpty(owner.stringValue(OwnerAttr.yearOwned)))
			setYearOwned(owner.stringValue(OwnerAttr.yearOwned));
		if(StringUtil.notEmpty(owner.stringValue(OwnerAttr.corporation)))
			setCorporation(owner.stringValue(OwnerAttr.corporation));
		if(StringUtil.notEmpty(owner.stringValue(OwnerAttr.ownerComments)))
			setComments(owner.stringValue(OwnerAttr.ownerComments));
	}
	
	public IHtmlObject[] getOwnerListTable(){
		IHtmlObject[] objs = browser.getTableTestObject(ownerListTable());
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find owner objects");
		}
		return objs;
	}

	public Data<OwnerAttr> getOwnerData(){
		Data<OwnerAttr> owner = new Data<OwnerAttr>();
		owner.put(OwnerAttr.ownerID, getOwnerID());
		owner.put(OwnerAttr.ownershipStatus, getOwnerStatus());
		owner.put(OwnerAttr.cust, getCustomer());
		owner.put(OwnerAttr.propertyID, getPropertyID());
		owner.put(OwnerAttr.typeOfOwnership, getTypeOfOwnership());
		owner.put(OwnerAttr.yearOwned, getYearOwned());
		owner.put(OwnerAttr.corporation, getCorporation());
		owner.put(OwnerAttr.ownerComments, getComments());
		owner.put(OwnerAttr.applicationName, getApplicationName());
		owner.put(OwnerAttr.creationDate, getCreationDate());
		owner.put(OwnerAttr.creationUser, getCreationUser());
		return owner;
	}
	
	public void verifyOwnerInfo(Data<OwnerAttr> audit){
		Data<OwnerAttr> ownerFromUI= getOwnerData();

		boolean result = MiscFunctions.compareResult("Owner ID", audit.stringValue(OwnerAttr.ownerID), ownerFromUI.stringValue(OwnerAttr.ownerID));
		result &= MiscFunctions.compareResult("Onwer Status", audit.stringValue(OwnerAttr.ownershipStatus), ownerFromUI.stringValue(OwnerAttr.ownershipStatus));
		result &= MiscFunctions.compareResult("Customer", audit.stringValue(OwnerAttr.cust), ownerFromUI.stringValue(OwnerAttr.cust));
		result &= MiscFunctions.compareResult("Property ID", audit.stringValue(OwnerAttr.propertyID), ownerFromUI.stringValue(OwnerAttr.propertyID));
		result &= MiscFunctions.compareResult("Type of ownership", audit.stringValue(OwnerAttr.typeOfOwnership), ownerFromUI.stringValue(OwnerAttr.typeOfOwnership));
		result &= MiscFunctions.compareResult("Year ownerd", audit.stringValue(OwnerAttr.yearOwned), ownerFromUI.stringValue(OwnerAttr.yearOwned));
		result &= MiscFunctions.compareResult("Corporation", audit.stringValue(OwnerAttr.corporation), ownerFromUI.stringValue(OwnerAttr.corporation));
		result &= MiscFunctions.compareResult("Owner Comment", audit.stringValue(OwnerAttr.ownerComments), ownerFromUI.stringValue(OwnerAttr.ownerComments));
		result &= MiscFunctions.compareResult("Application Name", audit.stringValue(OwnerAttr.applicationName), ownerFromUI.stringValue(OwnerAttr.applicationName));
		result &= MiscFunctions.compareResult("Creation Date", 0, DateFunctions.compareDates(audit.stringValue(OwnerAttr.creationDate),ownerFromUI.stringValue(OwnerAttr.creationDate)));
		result &= MiscFunctions.compareResult("Creation User", audit.stringValue(OwnerAttr.creationUser), ownerFromUI.stringValue(OwnerAttr.creationUser));

		if(!result){
			throw new ErrorOnPageException("Not all check points are passed in Owner details page.");
		}
		logger.info("Successfully verify all check points in Owner details page");
	}

	public List<Customer> getOwnerListData(){
		List<Customer> owners = new ArrayList<Customer>();
		IHtmlObject[] objs = getOwnerListTable();
		ITable table = (ITable) objs[0];
		for(int i=1; i<table.rowCount(); i++){
			Customer cust = new Customer();
			cust.custId = table.getCellValue(i, 1);
			String name = table.getCellValue(i, 2);
			cust.lName = name.split(",")[0].trim();
			cust.fName = name.split(",")[1].trim();
			cust.dateOfBirth = table.getCellValue(i, 3);
			cust.address = table.getCellValue(i, 4);
			owners.add(cust);
		}
		Browser.unregister(objs);
		return owners;
	}

	public int getOwnerIndex(Customer cust){
		List<Customer> custs = getOwnerListData();
		System.out.println(12);
		int ownerIndex = -1; 
		for(int i=0; i<custs.size(); i++){
			if((StringUtil.notEmpty(cust.custId)?cust.custId.equals(custs.get(i).custId):true) && 
					(StringUtil.notEmpty(cust.lName)?cust.lName.equals(custs.get(i).lName):true) &&
					(StringUtil.notEmpty(cust.fName)?cust.fName.equals(custs.get(i).fName):true) &&
					(StringUtil.notEmpty(cust.dateOfBirth)?DateFunctions.compareDates(cust.dateOfBirth, custs.get(i).dateOfBirth)==0:true) &&
					(StringUtil.notEmpty(cust.address)?cust.address.equals(custs.get(i).address):true)){
				ownerIndex = i;
				break;
			}
		}
		return ownerIndex;
	}

	public void selectOwner(Customer cust){
		int ownerIndex = getOwnerIndex(cust);
		browser.clickGuiObject(ownerRadio(), ownerIndex);
	}
}
