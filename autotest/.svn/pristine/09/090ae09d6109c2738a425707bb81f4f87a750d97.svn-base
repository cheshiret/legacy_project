/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.callManager;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Jane
 * @Date  Sep 17, 2012
 */
public class CallMgrCustAddIdentifiersPage extends CallMgrConfirmCustomerPage {
	private final String identifierConutryReg = "(CustomerIdentifierView-\\d+\\.identifier\\.country)|(IdentifierView-\\d+\\.country)";
	
	private static CallMgrCustAddIdentifiersPage _instance = null;
	
	private CallMgrCustAddIdentifiersPage() {}
	
	public static CallMgrCustAddIdentifiersPage getInstance() {
		if(null == _instance) {
			_instance = new CallMgrCustAddIdentifiersPage();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		return checkLastTrailValueIs("Confirm Customer - Add Identifiers");
	}
	
	/**
	 * This method was used to return identifier type dropdown list enabled or not
	 * @return
	 */
	public boolean identifierTypeDropDownListEnabled() {
		IHtmlObject[] objs = browser.getDropdownList(identifierType());
		if(objs.length<1)
			throw new ItemNotFoundException("Could not find Identifier type drop down list on page.");
		boolean enabled = objs[0].isEnabled();
		Browser.unregister(objs);
		return enabled;
	}
	
	/**
	 * This method was used to select identifier type based on index
	 * @param type
	 * @param index
	 */
	public void selectIdentifierType(String type, int index) {
		browser.selectDropdownList(identifierType(), type, index);
	}

	/**
	 * This method was used to select the first identifier type
	 * @param type
	 */
	public void selectIdentifierType(String type) {
		this.selectIdentifierType(type, 0);
	}
	
	/**
	 * This method was used to set identifier number based on index
	 * @param number
	 * @param index
	 */
	public void setIdentifierNumber(String number, int index) {
		IHtmlObject[] top = browser.getHtmlObject(".class", "Html.Table", ".id", "identifier_table");
		if(top.length<1)
			throw new ItemNotFoundException("Could not found identifier table on page.");
		browser.setTextField(identifierNum(), number, true, index, top[0]);
		Browser.unregister(top);
	}

	/**
	 * This method was used to set the first identifier number
	 * @param number
	 */
	public void setIdentifierNumber(String number) {
		this.setIdentifierNumber(number, 0);
	}
	
	/**
	 * This method was used to select identifier country based on index
	 * @param country
	 * @param index
	 */
	public void selectIdentifierCountry(String country, int index) {
		RegularExpression regx = new RegularExpression(
				identifierConutryReg, false);
		browser.selectDropdownList(".id", regx, country);
	}

	/**
	 * This method was used to select the first identifier country
	 * @param country
	 */
	public void selectIdentifierCountry(String country) {
		this.selectIdentifierCountry(country, 0);
	}
	
	public boolean checkIdentifierCountryExist() {
		RegularExpression regx = new RegularExpression(
				identifierConutryReg, false);
		return browser.checkHtmlObjectExists(".id", regx);
	}
	
	public void setupNewIdentifier(String type, String num, String country){
		selectIdentifierType(type);
		ajax.waitLoading();
		this.waitLoading();
		setIdentifierNumber(num);
		if(checkIdentifierCountryExist()){
			selectIdentifierCountry(country);
			ajax.waitLoading();
			this.waitLoading();
		}
	}
	
	private IHtmlTable getExistingIdentifiersTable(){
		IHtmlObject[] tables = browser.getTableTestObject(".id", "Existing_Identifier_Panel");
		if(tables.length<1)
			throw new ItemNotFoundException("Could not find Existing Identifiers table on page.");
		IHtmlTable table = (IHtmlTable)tables[0];
//		Browser.unregister(tables);
		return table;
	}
	
	private Property[] identifierType(){
		return Property.toPropertyArray(".id", new RegularExpression("CustomerIdentifierView-\\d+\\.identifier\\.identifierType(\\.name)?", false));
	}
	
	private Property[] identifierNum(){
		return Property.toPropertyArray(".id", new RegularExpression("CustomerIdentifierView-\\d+\\.identifier\\.identifierNumber", false));
	}
	
	public void verifyExistingIdentifiersReadOnly(){
		IHtmlTable table = this.getExistingIdentifiersTable();
		boolean enabled = false;
			
		IHtmlObject[] identifierTypes = browser.getHtmlObject(identifierType(), table);
		if(identifierTypes.length<1)
			throw new ItemNotFoundException("Could not find any identifier type in Existing Identifiers table.");
		
		for(int i=0;i<identifierTypes.length;i++)
			enabled |= identifierTypes[i].isEnabled();
		Browser.unregister(identifierTypes);
		
		IHtmlObject[] identifierNums = browser.getHtmlObject(identifierNum(), table);
		if(identifierNums.length<1)
			throw new ItemNotFoundException("Could not find any identifier numbers in Existing Identifiers table.");
		for(int i=0;i<identifierNums.length;i++)
			enabled |= identifierNums[i].isEnabled();
		Browser.unregister(identifierNums);
		
		Browser.unregister(table);
		if(enabled)
			throw new ErrorOnPageException("Existing Identifiers should be read-only.");
		logger.info("-----Verify Existing Identifiers are read-only successfully.");
		
	}	
	
	public List<CustomerIdentifier> getExistingIdetifiers(){
		List<CustomerIdentifier> custIdenList = new ArrayList<CustomerIdentifier>();
		
		IHtmlTable table = this.getExistingIdentifiersTable();
		IHtmlObject[] idenTRs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.TR", ".text", new RegularExpression("^Identifier Type.*", false)), table);
		for(int i=0;i<idenTRs.length;i++){
			CustomerIdentifier custIden = new CustomerIdentifier();
			IHtmlObject[] types = browser.getHtmlObject(identifierType(), idenTRs[i]);
			if(types.length<1)
				throw new ItemNotFoundException("Could not find any identifier type in Existing Identifiers table.");
			custIden.identifierType = types[0].getProperty(".value");
			Browser.unregister(types);
			
			IHtmlObject[] nums = browser.getHtmlObject(identifierNum(), idenTRs[i]);
			if(nums.length<1)
				throw new ItemNotFoundException("Could not find any identifier number in Existing Identifiers table.");
			custIden.identifierNum = nums[0].getProperty(".value");
			Browser.unregister(nums);
			
			custIdenList.add(custIden);
		}
		Browser.unregister(idenTRs);
		Browser.unregister(table);
		return custIdenList;
	}
}
