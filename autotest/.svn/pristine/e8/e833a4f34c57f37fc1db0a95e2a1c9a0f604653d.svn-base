package com.activenetwork.qa.awo.pages.orms.licenseManager.vendor;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBankAccountInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * ScriptName: LicMgrVendorBankAccountStoreAssignmentsDetailsWidget
 * Description:
 * @date: Apr 6, 2011-3:59:29 AM
 * @author QA-qchen
 *
 */
public class LicMgrVendorBankAccountStoreAssignmentsDetailsWidget extends DialogWidget {
	
	public static LicMgrVendorBankAccountStoreAssignmentsDetailsWidget _instance = null;
	
	protected LicMgrVendorBankAccountStoreAssignmentsDetailsWidget() {}
	
	public static LicMgrVendorBankAccountStoreAssignmentsDetailsWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrVendorBankAccountStoreAssignmentsDetailsWidget();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "change_bank_acct_store_assignmnents_detail_ui");
	}
	
	/**
	 * Check 'Show Current Records Only' check box
	 */
	public void checkShowCurrentRecordsOnly() {
		browser.selectCheckBox(".id", new RegularExpression("VendorBankStoreAssignmentSearchCriteria-\\d+\\.currentOnly", false));
	}
	
	/**
	 * Un-check 'Show Current Records Only' check box
	 */
	public void unCheckShowCurrentRecordsOnly() {
		browser.unSelectCheckBox(".id", new RegularExpression("VendorBankStoreAssignmentSearchCriteria-\\d+\\.currentOnly", false));
	}
	
	public void checkAssignmentStatusActive() {
		browser.selectCheckBox(".id", new RegularExpression("VendorBankStoreAssignmentSearchCriteria-\\d+\\.assignmentStatusList_\\d+", false), 0);
	}
	
	public void unCheckAssignmentStatusActive() {
		browser.selectCheckBox(".id", new RegularExpression("VendorBankStoreAssignmentSearchCriteria-\\d+\\.assignmentStatusList_\\d+", false), 0);
	}
	
	public void checkAssignmentStatusInactive() {
		browser.selectCheckBox(".id", new RegularExpression("VendorBankStoreAssignmentSearchCriteria-\\d+\\.assignmentStatusList_\\d+", false), 1);
	}
	
	public void unCheckAssignmentStatusInactive() {
		browser.unSelectCheckBox(".id", new RegularExpression("VendorBankStoreAssignmentSearchCriteria-\\d+\\.assignmentStatusList_\\d+", false), 1);
	}
	
	/**
	 * Select the store in the filter area to search bank account assignments
	 * @param store
	 */
	public void selectFilterStore(String store) {
		if(store.equalsIgnoreCase("null"))
			browser.selectDropdownList(".id", new RegularExpression("VendorBankStoreAssignmentSearchCriteria-\\d+\\.storeView",false), 0);
		else
			browser.selectDropdownList(".id", new RegularExpression("VendorBankStoreAssignmentSearchCriteria-\\d+\\.storeView", false), store);
	}
	
	/**
	 * Select the account in the filter area to search bank account assignments
	 * @param account
	 */
	public void selectFilterAccount(String account) {
		if(null ==account || account.length()<1)
			browser.selectDropdownList(".id", new RegularExpression("VendorBankStoreAssignmentSearchCriteria-\\d+\\.bankAccountView",false), 0);
		else{
			IHtmlObject objs[] = browser.getDropdownList(".id", new RegularExpression("VendorBankStoreAssignmentSearchCriteria-\\d+\\.bankAccountView", false));
			if(objs.length == 0) {
				throw new ItemNotFoundException("Can't find any Bank Account drop down list objects.");
			}
			((ISelect)objs[0]).select(new RegularExpression(("^" + account), false));
			
			Browser.unregister(objs);
		}
	}
	
	/**
	 * Click Go button to filter vendor bank account assignments
	 */
	public void clickGo() {
		IHtmlObject[] objs=this.getWidget();
		browser.clickGuiObject(new Property[]{new Property(".class","Html.A"),new Property(".text","Go")}, true, 0, objs[0]);
		Browser.unregister(objs);
		/*HtmlObject[] objs = browser.getHtmlObject(".class","Html.A",".text","Go");
		browser.clickGuiObject(".class","Html.A",".text","Go");*/
	}
	
	/**
	 * Click OK button
	 */
	public void clickOK() {
		IHtmlObject[] objs=this.getWidget();
		browser.clickGuiObject(new Property[]{new Property(".class","Html.A"),new Property(".text","Ok")}, true, 0, objs[0]);
		Browser.unregister(objs);
	}
	
	/**Bank Account Details info**/
	
	/**
	 * Get the bank account details info by each attibute's name
	 * @param attributeName
	 * @return
	 */
	private String getAttributeValueByName(String attributeName) {
		IHtmlObject objs[] = null;
		Property property[] = new Property[3];
		property[0] = new Property(".class", "Html.DIV");
		property[1] = new Property(".className", "inputwithrubylabel");
		property[2] = new Property(".text", new RegularExpression("^" + attributeName, false));
		
		if(null != attributeName && attributeName.length() > 0){
			objs = browser.getHtmlObject(property);
		} else {
			throw new ActionFailedException("Please give the correct attribute name.");
		}

		String attributeValue = "";
		if(attributeName.equalsIgnoreCase("Status")){
			attributeValue = objs[1].getProperty(".text").split(attributeName)[1].trim();
		} else {
			if(objs.length == 1) {
				attributeValue = objs[0].getProperty(".text").split(attributeName)[1].trim();
			} else {
				throw new ActionFailedException("Find 0 or multiple DIV named - " + attributeName + ".");
			}
		}
		
		Browser.unregister(objs);
		return attributeValue;
	}
	
	/**
	 * Get bank account id
	 * @return
	 */
	public String getBankAccountID() {
		return getAttributeValueByName("ID");
	}
	
	/**
	 * Get bank account status
	 * @return
	 */
	public String getBankAccountStatus() {
		return getAttributeValueByName("Status");
	}
	
	/**
	 * Get bank account prenote status
	 * @return
	 */
	public String getPrenoteStatus() {
		return getAttributeValueByName("Prenote Status");
	}
	
	/**
	 * Get bank account type
	 * @return
	 */
	public String getAccountType() {
		return getAttributeValueByName("Account Type");
	}
	
	/**
	 * Get bank account routing number
	 * @return
	 */
	public String getRoutingNum() {
		return getAttributeValueByName("Routing #");
	}
	
	/**
	 * Get bank account number
	 * @return
	 */
	public String getAccountNum() {
		return getAttributeValueByName("Account #");
	}
	
	/**
	 * Get bank account been created date time
	 * @return
	 */
	public String getCreationDateTime() {
		return getAttributeValueByName("Creation Date/Time");
	}
	
	/**
	 * Get bank acccount been created user name
	 * @return
	 */
	public String getCreationUser() {
		return getAttributeValueByName("Creation User");
	}
	
	/**
	 * Get bank account lasted been modified date time
	 * @return
	 */
	public String getLastModifiedDateTime() {
		return getAttributeValueByName("Last Modified Date/Time");
	}
	
	/**
	 * Get the user who lasted modified account
	 * @return
	 */
	public String getLastModifiedUser() {
		return getAttributeValueByName("Last Modified User");
	}
	
	/**Store/Bank Account Assignments details info**/
	
	/**
	 * Get all Vendor Bank Account - Store Assignment records identified by store name
	 * @param storeName
	 * @return
	 */
	public List<List<String>> getVendorBankAccountStoreAssignmentByStoreName(String storeName) {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "storeAssignmentGrid");
		if(objs.length < 1) {
			throw new ErrorOnDataException("Can't find Bank Account - Store Assignments table object.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		int col = table.findColumn(0, "Agent Name & address");
		List<List<String>> assignments = new ArrayList<List<String>>();
		for(int i = 0; i < table.rowCount(); i ++) {
			if(table.getCellValue(i, col).contains(storeName)) {
				assignments.add(table.getRowValues(i));
			}
		}
		
		if(assignments.size() == 0) {
			throw new ItemNotFoundException("Can't find any vendor bank account assignment identified by store - " + storeName);
		}
		
		return assignments;
	}
	
	/**
	 * Get a specific vendor bank account store assignment record identified by store name and account id
	 * @param storeName
	 * @param accountID
	 * @return
	 */
	public List<String> getVendorBankAccountStoreAssignmentByStoreNameAndAccountID(String storeName, String accountID) {
		List<List<String>> assignments = this.getVendorBankAccountStoreAssignmentByStoreName(storeName);
		
		for(int i = 0; i < assignments.size(); i ++) {
			if(assignments.get(i).get(5).contains(accountID)) {
				return assignments.get(i);
			}
		}
		
		return null;
	}
	/**
	 * setupactive assignment bu store name.
	 * @param storeName
	 */
	public void setupActiveAssignmentByStoreName(String storeName){
		this.setupFilter(false, true, false, storeName, null);
	}
	
	public void setupFilter(boolean currentOnly, boolean activeOnly, boolean inActiveOnly, String store, String account){
	
		if(currentOnly){
			checkShowCurrentRecordsOnly();
			ajax.waitLoading();
		}else if(!currentOnly){
			unCheckShowCurrentRecordsOnly();
			ajax.waitLoading();
			
			if(activeOnly){
				checkAssignmentStatusActive();
			}else{
				unCheckAssignmentStatusActive();
			}
			
			if(inActiveOnly){
				checkAssignmentStatusInactive();
			}else{
				unCheckAssignmentStatusInactive();
			}
			
			selectFilterStore(store);
			selectFilterAccount(account);
		}
		
		clickGo();
		ajax.waitLoading();
	}
	
	public VendorBankAccountInfo getVendorBankAccountInfoByID(String id){
		IHtmlObject objs[] = browser.getTableTestObject(".id", "storeAssignmentGrid");
		if(objs.length < 1) {
			throw new ErrorOnDataException("Can't find Bank Account - Store Assignments table object.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		VendorBankAccountInfo bankAccount = new VendorBankAccountInfo();
		for(int i = 1; i < table.rowCount(); i ++) {
			if(id.equalsIgnoreCase(table.getCellValue(i, 0))){
				bankAccount.accountID = table.getCellValue(i, 0);
				bankAccount.assignStatus = table.getCellValue(i, 1);
				bankAccount.assignAgentName = table.getCellValue(i, 2);
				String[] temp = table.getCellValue(i, 4).split(" ");
				//ID:133650169 Type:Checking Routing #:026009593 Account #:c651******
				bankAccount.accountType = temp[1].replaceAll("Type:", "");
				bankAccount.routingNum = temp[3].replaceAll("#:", "");
				bankAccount.accountNum = temp[5].replaceAll("#:", "");
				bankAccount.effectiveDate = table.getCellValue(i, 5);
				break;
			}
		}
		
		return bankAccount;
	}
	
	public boolean verfiyVendorBankAccountAssignment(VendorBankAccountInfo bankAccount, VendorBankAccountInfo comapredInfo){
		boolean flag = true;
		
		if(!comapredInfo.assignStatus.equalsIgnoreCase(bankAccount.assignStatus)){
			logger.error("Bank account assigned status display error! The expect value is:" + bankAccount.assignStatus);
			flag &= false;
		}
		
		if(!comapredInfo.assignAgentName.startsWith(bankAccount.assignAgentName)){
			logger.error("Bank account assigned agent name display error! The expect value is:" + bankAccount.assignAgentName);
			flag &= false;
		}
		
		if(!comapredInfo.accountType.equalsIgnoreCase(bankAccount.accountType)){
			logger.error("Bank account type display error! The expect value is:" + bankAccount.accountType);
			flag &= false;
		}
		
		if(!comapredInfo.routingNum.equalsIgnoreCase(bankAccount.routingNum)){
			logger.error("Bank account routing num display error! The expect value is:" + bankAccount.routingNum);
			flag &= false;
		}
		
		if(!DateFunctions.formatDate(comapredInfo.effectiveDate,"M/d/yyyy").equalsIgnoreCase(bankAccount.effectiveDate)){
			logger.error("Bank account effective date display error! The expect value is:" + bankAccount.effectiveDate);
			flag &= false;
		}
		
		return flag;
	}
	
	public List<VendorBankAccountInfo> getAssignmentsInfo(){
		IHtmlObject objs[] = browser.getTableTestObject(".id", "storeAssignmentGrid");
		if(objs.length < 1) {
			throw new ErrorOnDataException("Can't find Bank Account - Store Assignments table object.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		List<VendorBankAccountInfo> comparedBankAccount = new ArrayList<VendorBankAccountInfo>();
		for(int i = 1; i < table.rowCount(); i ++) {
			VendorBankAccountInfo bankAccount = new VendorBankAccountInfo();
			bankAccount.accountID = table.getCellValue(i, 1);
			bankAccount.assignStatus = table.getCellValue(i, 2);
			bankAccount.assignAgentName = table.getCellValue(i, 2);
			String[] temp = table.getCellValue(i, 5).split(" ");
			//ID:133650169 Type:Checking Routing #:026009593 Account #:c651******
			bankAccount.accountType = temp[1].replaceAll("Type:", "");
			bankAccount.routingNum = temp[3].replaceAll("#:", "");
			bankAccount.accountNum = temp[5].replaceAll("#:", "");
			bankAccount.effectiveDate = table.getCellValue(i, 6);
			//Fri Feb 17 2012 Test-Auto, QA-Auto 
			bankAccount.creationDateTime = RegularExpression.getMatches(table.getCellValue(i, 7), "[a-zA-Z]{3} [a-zA-Z]{3} [0-9]{1,2} [0-9]{4}")[0];
			bankAccount.creationUser = RegularExpression.getMatches(table.getCellValue(i, 7), "[a-zA-Z]+-[a-zA-Z]+,\\s[a-zA-Z]+-[a-zA-Z]+")[0];
			comparedBankAccount.add(bankAccount);
		}
		
		return comparedBankAccount;
	}
	
	public String getAssignIdByAgentName(String agent) {
		this.setupFilter(false, true, false, agent, "");
		IHtmlObject[] objs = browser.getTableTestObject(".id","storeAssignmentGrid");
		IHtmlTable table = (IHtmlTable) objs[0];
		String assignId = "";
		int rowNum = table.findRow(3, new RegularExpression(agent,false));
		if(rowNum > 0){
			assignId = table.getCellValue(rowNum, 1);
		}
		Browser.unregister(objs);
		return assignId;
	}
}
