package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrActivityMGTCommonPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
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
 * @Date  Mar 14, 2014
 */
public class LicMgrInstructorListPage extends LicMgrActivityMGTCommonPage {
	static class SingletonHolder {
		protected static LicMgrInstructorListPage _instance = new LicMgrInstructorListPage();
	}

	protected LicMgrInstructorListPage() {
	}

	public static LicMgrInstructorListPage getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] instructorSearchResultList(){
		return Property.concatPropertyArray(table(), ".id", "instructor.searchresult_LIST");
	}

	protected Property[] instructorNumber(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorSearchCriteria-\\d+\\.instructorNumber", false));
	}

	protected Property[] status(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorSearchCriteria-\\d+\\.status", false));
	}

	protected Property[] instructorType(){                            
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorSearchCriteria-\\d+\\.instructorType", false));
	}

	protected Property[] lastName(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorSearchCriteria-\\d+\\.lastName", false));
	}

	protected Property[] firstName(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorSearchCriteria-\\d+\\.firstName", false));
	}

	protected Property[] phoneNumber(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorSearchCriteria-\\d+\\.phoneNumber", false));
	}

	protected Property[] includeAreaCode(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorSearchCriteria-\\d+\\.includeAreaCode", false));
	}

	protected Property[] physicalAddress(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorSearchCriteria-\\d+\\.physicalAddress", false));
	}

	protected Property[] supplementalAddress(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorSearchCriteria-\\d+\\.supplementalAddress", false));
	}

	protected Property[] city(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorSearchCriteria-\\d+\\.city", false));
	}

	protected Property[] state(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorSearchCriteria-\\d+\\.state", false));
	}

	protected Property[] county(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorSearchCriteria-\\d+\\.county", false));
	}

	protected Property[] zipCode(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorSearchCriteria-\\d+\\.zipCode", false));
	}

	protected Property[] country(){
		return Property.toPropertyArray(".id", new RegularExpression("HuntersEdInstructorSearchCriteria-\\d+\\.country", false));
	}

	protected Property[] search(){
		return Property.concatPropertyArray(a(), ".id", "Anchor", ".text", "Search");
	}

	protected Property[] addInstructor(){
		return Property.concatPropertyArray(a(), ".id", "Anchor", ".text", "Add Instructor");
	}

	protected Property[] oddRowTR(){
		return Property.concatPropertyArray(tr(), ".className", "oddRow");
	}

	protected Property[] errorMsg(String msg){
		return Property.concatPropertyArray(div(), ".id", "NOTSET", ".text", new RegularExpression(msg, false));
	}

	protected Property[] instructorID(String instructorNum){
		return Property.concatPropertyArray(a(), ".text", instructorNum);
	}

	protected Property[] instructorID(){
		return Property.concatPropertyArray(a(), ".text", new RegularExpression("\\d+", false));
	}

	/** Page Object Property Definition END */

	public boolean exists() {
		return browser.checkHtmlObjectExists(instructorSearchResultList());
	}

	public void setInstructorNumber(String instructorNumber){
		browser.setTextField(instructorNumber(), instructorNumber);
	}

	public String getInstructorNumber(){
		return browser.getTextFieldValue(instructorNumber());
	}

	public void selectInstructorStatus(String status){
		browser.selectDropdownList(status(), status);
	}

	public String getInstructorStatus(){
		return browser.getDropdownListValue(status(), 0);
	}

	public List<String> getInstructorStatuses(){
		return browser.getDropdownElements(status());
	}

	public void selectInstructorType(String instructorType){
		browser.selectDropdownList(instructorType(), instructorType);
	}

	public String getInstructorType(){
		return browser.getDropdownListValue(instructorType(), 0);
	}

	public List<String> getInstructorTypes(){
		return browser.getDropdownElements(instructorType());
	}
	
	public void setLastName(String lastName){
		browser.setTextField(lastName(), lastName);
	}

	public String getLastName(){
		return browser.getTextFieldValue(lastName());
	}

	public void setFirstName(String firstName){
		browser.setTextField(firstName(), firstName);
	}

	public String getFirstName(){
		return browser.getTextFieldValue(firstName());
	}

	public void setPhoneNum(String phoneNumber){
		browser.setTextField(phoneNumber(), phoneNumber);
	}

	public String getPhoneNum(){
		return browser.getTextFieldValue(phoneNumber());
	}

	public void selectIncludeAreaCode(){
		browser.selectCheckBox(includeAreaCode());
	}

	public boolean isIncludeAreaCodeSelected(){
		return browser.isCheckBoxSelected(includeAreaCode());
	}

	public void unSelectIncludeAreaCode(){
		browser.unSelectCheckBox(includeAreaCode());
	}

	public void setPhysicalAddress(String physicalAddress){
		browser.setTextField(physicalAddress(), physicalAddress);
	}

	public String getPhysicalAddress(){
		return browser.getTextFieldValue(physicalAddress());
	}

	public void setSupplementalAddress(String supplementalAddress){
		browser.setTextField(supplementalAddress(), supplementalAddress);
	}

	public String getSupplementalAddress(){
		return browser.getTextFieldValue(supplementalAddress());
	}

	public void setCity(String city){
		browser.setTextField(city(), city);
	}

	public String getCity(){
		return browser.getTextFieldValue(city());
	}

	public void selectCounty(String county){
		browser.selectDropdownList(county(), 0);
	}

	public String getCounty(){
		return browser.getDropdownListValue(county(), 0);
	}

	public List<String> getCounties(){
		return browser.getDropdownElements(county());
	}
	
	public void selectState(String state){
		browser.selectDropdownList(state(), state);
	}

	public String getState(){
		return browser.getDropdownListValue(state(), 0);
	}

	public List<String> getStates(){
		return browser.getDropdownElements(state());
	}
	
	public void selectState(int index){
		browser.selectDropdownList(state(), index);
	}

	public void selectCountry(String country){
		browser.selectDropdownList(country(), country);
	}

	public String getCountry(){
		return browser.getDropdownListValue(country(), 0);
	}

	public List<String> getCountries(){
		return browser.getDropdownElements(country());
	}
	
	public void selectCountry(int index){
		browser.selectDropdownList(country(), index);
	}

	public void setZip(String zipCode){
		browser.setTextField(zipCode(), zipCode);
	}

	public String getZip(){
		return browser.getTextFieldValue(zipCode());
	}

	public void clickSearch(){
		browser.clickGuiObject(search());
	}

	public void clickInstructorID(String instructorID){
		browser.clickGuiObject(instructorID(instructorID));
	}

	public void clickInstructorID(){
		browser.clickGuiObject(instructorID());
	}

	public void clickAddInstructor(){
		browser.clickGuiObject(addInstructor());
	}

	public void searchInstructor(Customer cust){
		if(StringUtil.notEmpty(cust.instructorNum))
			setInstructorNumber(cust.instructorNum);
		if(StringUtil.notEmpty(cust.status))
			selectInstructorStatus(cust.status);
		if(StringUtil.notEmpty(cust.instructorType))
			selectInstructorType(cust.instructorType);
		if(StringUtil.notEmpty(cust.lName))
			setLastName(cust.lName);
		if(StringUtil.notEmpty(cust.fName))
			setFirstName(cust.fName);
		if (null != cust.hPhone && cust.hPhone.length() > 0) {
			setPhoneNum(cust.hPhone);
		} else if (null != cust.bPhone && cust.bPhone.length() > 0) {
			setPhoneNum(cust.bPhone);
		} else {
			setPhoneNum(cust.mPhone);
		}
		if (cust.includeAreaCode) {
			selectIncludeAreaCode();
		} else {
			unSelectIncludeAreaCode();
		}
		if (StringUtil.notEmpty(cust.physicalAddr.address))
			this.setPhysicalAddress(cust.physicalAddr.address);
		if (StringUtil.notEmpty(cust.physicalAddr.supplementalAddr)) 
			this.setSupplementalAddress(cust.physicalAddr.supplementalAddr);
		if (StringUtil.notEmpty(cust.physicalAddr.city)) {
			this.setCity(cust.physicalAddr.city);
		}
		selectCountry(0);
		ajax.waitLoading();
		if (StringUtil.notEmpty(cust.physicalAddr.country)) {
			this.selectCountry(cust.physicalAddr.country);
			ajax.waitLoading();
		}
		selectState(0);
		ajax.waitLoading();
		if (StringUtil.notEmpty(cust.physicalAddr.state)) {
			this.selectState(cust.physicalAddr.state);
			ajax.waitLoading();
		}
		if (StringUtil.notEmpty(cust.physicalAddr.county)) {
			this.selectCounty(cust.physicalAddr.county);
			ajax.waitLoading();
		}
		if (StringUtil.notEmpty(cust.physicalAddr.zip)) {
			this.setZip(cust.physicalAddr.zip);
		}

		clickSearch();
		ajax.waitLoading();
		waitLoading();
	}

	public void verifyInstructorInitialSearchUI(){
		boolean result = MiscFunctions.compareResult("Instructor #", StringUtil.EMPTY, getInstructorNumber());
		result &= MiscFunctions.compareResult("Instructor Status selected option", OrmsConstants.ACTIVE_STATUS, getInstructorStatus());
		result &= MiscFunctions.compareResult("Instructor Type", StringUtil.EMPTY, getInstructorType());
		result &= MiscFunctions.compareResult("Last Name", StringUtil.EMPTY, getLastName());
		result &= MiscFunctions.compareResult("First Name", StringUtil.EMPTY, getFirstName());
		result &= MiscFunctions.compareResult("Phone", StringUtil.EMPTY, getPhoneNum());
		result &= MiscFunctions.compareResult("Area Code", true, isIncludeAreaCodeSelected());
		result &= MiscFunctions.compareResult("Physical Address", StringUtil.EMPTY, getPhysicalAddress());
		result &= MiscFunctions.compareResult("Physical supplemental Address", StringUtil.EMPTY, getSupplementalAddress());
		result &= MiscFunctions.compareResult("Physical State", StringUtil.EMPTY, getState());
		result &= MiscFunctions.compareResult("Physical County", StringUtil.EMPTY, getCounty());
		result &= MiscFunctions.compareResult("Physical zip", StringUtil.EMPTY, getZip());
		result &= MiscFunctions.compareResult("Physical country", StringUtil.EMPTY, getCountry());
		if(!result){
			throw new ErrorOnPageException("Not all instructor initial search UI are correct. Please check the details from previous logs.");
		}else logger.info("Successfully verify instructor initial search UI.");
	}

	public IHtmlObject[] getInstructorResultObjs(){
		return browser.getHtmlObject(Property.atList(instructorSearchResultList(), oddRowTR()));
	}

	public boolean isMsgExisted(String msg) {
		return browser.checkHtmlObjectExists(errorMsg(msg));
	}

	public void verifyMsgExisted(String msg, boolean isExist) {
		if (isMsgExisted(msg) != isExist) {
			throw new ErrorOnPageException("The message: " + msg + " should " + (isExist ? " " : " not ") + "exist!");
		}
		logger.info("The message: " + msg + " does " + (isExist ? " " : " not ") + "exist!");
	}

	public int getNumOfResult(){
		IHtmlObject[] objs = getInstructorResultObjs();
		int numOfResult = objs.length;
		Browser.unregister(objs);
		return numOfResult;
	}

	public List<Customer> getInstructorData() {
		List<Customer> custList = new ArrayList<Customer>();
		IHtmlObject[] objs = browser.getHtmlObject(instructorSearchResultList());
		if(objs.length>0){
			IHtmlTable table = (IHtmlTable) objs[0];
			for (int i = 1; i < table.rowCount(); i++) {
				Customer cust = new Customer();
				cust.instructorNum = table.getCellValue(i, 0).trim();
				cust.status = table.getCellValue(i, 1).trim();
				cust.lName = table.getCellValue(i, 2).trim();
				cust.fName = table.getCellValue(i, 3).trim();
				cust.mName = table.getCellValue(i, 4).trim();
				cust.mPhone = table.getCellValue(i, 5).trim();
				cust.physicalAddr.supplementalAddr = table.getCellValue(i, 6).trim();
				cust.physicalAddr.city = table.getCellValue(i, 7).trim();
				cust.physicalAddr.zip = table.getCellValue(i, 8).trim();
				cust.physicalAddr.country = table.getCellValue(i, 9).trim();
				custList.add(cust);
			}
			Browser.unregister(table);
			Browser.unregister(objs);
			return custList;
		}else throw new ObjectNotFoundException("Can't find Instructor result grid list objects.");
	}

	public void verifyInstructorInListPg(List<Customer> instruction) {
		List<Customer> instruction_UI = getInstructorData();
		boolean result = MiscFunctions.compareResult("Size of list", instruction.size(), instruction_UI.size());
		if(!result){
			throw new ErrorOnPageException("The size of list is different.");
		}else{
			for(int i=0; i<instruction.size(); i++){
				result &= MiscFunctions.compareResult(i+"-Instructor Number", instruction.get(i).instructorNum, instruction_UI.get(i).instructorNum);
				result &= MiscFunctions.compareResult(i+"-Instructor status", instruction.get(i).status, instruction_UI.get(i).status);
				result &= MiscFunctions.compareResult(i+"-Last Name", instruction.get(i).lName, instruction_UI.get(i).lName);
				result &= MiscFunctions.compareResult(i+"-First Name", instruction.get(i).fName, instruction_UI.get(i).fName);
				result &= MiscFunctions.compareResult(i+"-Middle Name", instruction.get(i).mName, instruction_UI.get(i).mName);
				result &= MiscFunctions.compareResult(i+"-Phone", instruction.get(i).mPhone, instruction_UI.get(i).mPhone);
				result &= MiscFunctions.compareResult(i+"-Supp Address", instruction.get(i).physicalAddr.supplementalAddr, instruction_UI.get(i).physicalAddr.supplementalAddr);
				result &= MiscFunctions.compareResult(i+"-City", instruction.get(i).physicalAddr.city, instruction_UI.get(i).physicalAddr.city);
				result &= MiscFunctions.compareResult(i+"-Zip", instruction.get(i).physicalAddr.zip, instruction_UI.get(i).physicalAddr.zip);
				result &= MiscFunctions.compareResult(i+"-Country", instruction.get(i).physicalAddr.country, instruction_UI.get(i).physicalAddr.country);
			}
			if(!result){
				throw new ErrorOnPageException("Not all check points are passed in Instructor list page. Please check the details from previous logs.");
			}
			logger.info("All check points are passed in Instructor list page.");
		}
	}

	public boolean hasInstructor(Customer cust){
		searchInstructor(cust);
		if(getNumOfResult()>0){
			return true;
		}else return false;
	}
}
