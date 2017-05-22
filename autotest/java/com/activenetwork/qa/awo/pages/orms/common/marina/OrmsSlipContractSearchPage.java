/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.marina;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipContractInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author QA
 * @Date  Jan 6, 2014
 */
public class OrmsSlipContractSearchPage extends OrmsPage {
	private static OrmsSlipContractSearchPage _instance = null;
	
	protected OrmsSlipContractSearchPage() {}
	
	public static OrmsSlipContractSearchPage getInstance(){
		if(null == _instance){
			_instance = new OrmsSlipContractSearchPage();
		}
		
		return _instance;
	}
	
	protected Property[] slipContractSearchConditionBarProp(){
		return Property.concatPropertyArray(div(), ".id","SlipContractSearchConditionBar");
	}
	
	protected Property[] contractIDTextFieldProp(){
		return Property.concatPropertyArray(input("text"), ".id",new RegularExpression("SlipContractSearchCriteria-\\d+\\.contractID",false));
	}
	
	protected Property[] searchButtonProp(){
		return Property.concatPropertyArray(a(), ".text","Search");
	}
	
	protected Property[] searchForDropDownListProp(){
		return Property.concatPropertyArray(select(), ".id",new RegularExpression("SlipContractSearchCriteria-\\d+\\.searchFor",false));
	}
	
	protected Property[] facilityTextFieldProp(){
		return Property.concatPropertyArray(input("text"), ".id",new RegularExpression("SlipContractSearchCriteria-\\d+\\.facility",false));
	}
	
	protected Property[] phoneTextFieldProp(){
		return Property.concatPropertyArray(input("text"), ".id",new RegularExpression("SlipContractSearchCriteria-\\d+\\.phone",false));
	}
	
	protected Property[] includeAreaCodeCheckBoxProp(){
		return Property.concatPropertyArray(input("checkbox"), ".id",new RegularExpression("SlipContractSearchCriteria-\\d+\\.includeAreaCode",false));
	}
	
	protected Property[] lastNameTextFieldProp(){
		return Property.concatPropertyArray(input("text"), ".id",new RegularExpression("SlipContractSearchCriteria-\\d+\\.lastName",false));
	}
	
	protected Property[] firstNameTextFieldProp(){
		return Property.concatPropertyArray(input("text"), ".id",new RegularExpression("SlipContractSearchCriteria-\\d+\\.firstName",false));
	}
	
	protected Property[] emailAddressTextFieldProp(){
		return Property.concatPropertyArray(input("text"), ".id",new RegularExpression("SlipContractSearchCriteria-\\d+\\.emailAddress",false));
	}
	
	protected Property[] contarctIDTextFieldProp(){
		return Property.concatPropertyArray(input("text"), ".id",new RegularExpression("SlipContractSearchCriteria-\\d+\\.contractID",false));
	}
	
	protected Property[] contarctTypeDropDownListProp(){
		return Property.concatPropertyArray(select(), ".id",new RegularExpression("SlipContractSearchCriteria-\\d+\\.contractType",false));
	}
	
	protected Property[] contarctStatusDropDownListProp(){
		return Property.concatPropertyArray(select(), ".id",new RegularExpression("SlipContractSearchCriteria-\\d+\\.contractStatus",false));
	}
	
	protected Property[] contractListTableProp(){
		return Property.concatPropertyArray(table(), ".id", new RegularExpression("grid_\\d+_LIST",false));
	}
	
	protected Property[] errorMessageProp(){
		return Property.concatPropertyArray(div(), ".id","NOTSET");
	}
	
	protected Property[] contractIDLinkProp(String contractID){
		return Property.concatPropertyArray(a(), ".text",contractID);
	}
	
	protected Property[] contractIDHeaderLinkProp(){
		return Property.concatPropertyArray(a(), ".text","Contract ID");
	}
	
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(this.slipContractSearchConditionBarProp());
	}
	
	public void setContractID(String contractID){
		browser.setTextField(this.contractIDTextFieldProp(), contractID);
	}
	
	public void clickSearch(){
		IHtmlObject[] topObjs = browser.getHtmlObject(this.slipContractSearchConditionBarProp());
		browser.clickGuiObject(this.searchButtonProp(), true, 0, topObjs[topObjs.length-1]);
		Browser.unregister(topObjs);
	}
	
	public void searchSlipContractByContractID(String contractID){
		logger.info("Search slip contract by contract id = " + contractID);
		
		this.setContractID(contractID);
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void clickContractID(String contractID){
		browser.clickGuiObject(this.contractIDLinkProp(contractID));
	}
	
	public void clickContractIDHeaderLink(){
		browser.clickGuiObject(this.contractIDHeaderLinkProp());
	}
	
	public String getSearchForValue(){
		return browser.getDropdownListValue(this.searchForDropDownListProp(), 0);
	}
	
	public String getFacilityValue(){
		return browser.getTextFieldValue(this.facilityTextFieldProp());
	}
	
	public String getPhone(){
		return browser.getTextFieldValue(this.phoneTextFieldProp());
	}
	
	public boolean isIncludeAreaCodeSelected(){
		return browser.isCheckBoxSelected(this.includeAreaCodeCheckBoxProp());
	}
	
	public void selectSearchFor(String searchForValue){
		browser.selectDropdownList(this.searchForDropDownListProp(), searchForValue);
	}
	
	public void setPhone(String phone){
		browser.setTextField(this.phoneTextFieldProp(), phone);
	}
	
	public void selectIncludeAreaCode(){
		browser.selectCheckBox(this.includeAreaCodeCheckBoxProp());
	}
	
	public void unselectIncludeAreaCode(){
		browser.unSelectCheckBox(this.includeAreaCodeCheckBoxProp());
	}
	
	public void setLastName(String lastName){
		browser.setTextField(this.lastNameTextFieldProp(), lastName);
	}
	
	public void setFirstname(String firstName){
		browser.setTextField(this.firstNameTextFieldProp(), firstName);
	}
	
	public void setFacility(String facility){
		browser.setTextField(this.facilityTextFieldProp(), facility);
	}
	
	public void setEmailAddress(String emailAddress){
		browser.setTextField(this.emailAddressTextFieldProp(), emailAddress);
	}
	
	public void selectContractType(String contractType){
		browser.selectDropdownList(this.contarctTypeDropDownListProp(), contractType);
	}
	
	public void selectContractStatus(String contractStatus){
		browser.selectDropdownList(this.contarctStatusDropDownListProp(), contractStatus);
	}
	
	public boolean facilityTextFieldIsEnable(){
		return browser.checkHtmlObjectEnabled(this.facilityTextFieldProp());
	}
	
	public void setupSlipContractCriteria(SlipContractInfo contractInfo){
		if(!StringUtil.isEmpty(contractInfo.getSearchFor())){
			this.selectSearchFor(contractInfo.getSearchFor());
		}
		
		if(!StringUtil.isEmpty(contractInfo.getPhone())){
			this.setPhone(contractInfo.getPhone());
		}
		
		if(contractInfo.getIsIncludeAreaCode()){
			this.selectIncludeAreaCode();
		}else this.unselectIncludeAreaCode();
		
		if(!StringUtil.isEmpty(contractInfo.getLastName())){
			this.setLastName(contractInfo.getLastName());
		}
		
		if(!StringUtil.isEmpty(contractInfo.getFirstName())){
			this.setFirstname(contractInfo.getFirstName());
		}
		
		if(!StringUtil.isEmpty(contractInfo.getCustomerEmail())){
			this.setEmailAddress(contractInfo.getCustomerEmail());
		}
		
		if(!StringUtil.isEmpty(contractInfo.getId())){
			this.setContractID(contractInfo.getId());
		}
		
		if(!StringUtil.isEmpty(contractInfo.getLocation()) && this.facilityTextFieldIsEnable()){
			this.setFacility(contractInfo.getLocation());
		}
		
		if(!StringUtil.isEmpty(contractInfo.getType())){
			this.selectContractType(contractInfo.getType());
		}
		
		if(!StringUtil.isEmpty(contractInfo.getStatus())){
			this.selectContractStatus(contractInfo.getStatus());
		}
	}
	
	public void searchSlipContract(SlipContractInfo contractInfo){
		this.setupSlipContractCriteria(contractInfo);
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	private IHtmlObject[] getContractListTableObject(){
		IHtmlObject[] objs = browser.getHtmlObject(this.contractListTableProp());
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found contract list table object.");
		}
		
		return objs;
	}
	
	private List<String> getColumnListValue(String columnName){
		IHtmlObject[] objs = this.getContractListTableObject();
		
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		int col = table.findColumn(0, columnName);
		List<String> listValues = table.getColumnValues(col);
		listValues.remove(0);
		Browser.unregister(objs);
		return listValues;
	}
	
	public List<String> getContractIDColumnListValue(){
		return this.getColumnListValue("Contract ID");
	}
	
	public List<String> getContractTypeColumnListValue(){
		return this.getColumnListValue("Contract Type");
	}
	
	public List<String> getPhoneColumnListValue(){
		return this.getColumnListValue("Phone");
	}
	
	public List<String> getStatusColumnListValue(){
		return this.getColumnListValue("Active");
	}
	
	public List<String> getCustomerListValue(){
		return this.getColumnListValue("Customer");
	}	
	
	
	public void verifyColumnListInfoForSingleSearch(String columnName, String expectedValue,boolean isSearchByLastName){
		List<String> actListValue = this.getColumnListValue(columnName);
		boolean result = true;
		for(int i=0;i<actListValue.size();i++){
			if(columnName.equalsIgnoreCase("Customer")){
				String actValue;
				if(isSearchByLastName){
					actValue = actListValue.get(i).split(",")[0];
					result &= MiscFunctions.compareResult("Column List Value for single search which column is " + columnName, expectedValue, actValue);
				}else{
					actValue = actListValue.get(i).split(",")[1];
				}
			}else if(columnName.equalsIgnoreCase("Phone")){
				result &=actListValue.get(i).contains(expectedValue);
			}else{
				result &= MiscFunctions.compareResult("Column List Value for single search which column is " + columnName, expectedValue, actListValue.get(i));
			}
			
		}
		
		if(!result){
			throw new ErrorOnDataException("The column list value not correct.");
		}
		
	}
	
	public String getErrorMessage(){
		return browser.getObjectText(this.errorMessageProp());
	}
	
	public void verifyErrorMessage(String expMessage){
		String actMsg = this.getErrorMessage();
		boolean result = MiscFunctions.compareResult("Error Message", expMessage, actMsg);
		if(!result){
			throw new ErrorOnPageException("Error Message Not Correct.");
		}
	}
	
}
