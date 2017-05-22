package com.activenetwork.qa.awo.pages.orms.licenseManager.vendor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBankAccountInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsConfirmDialogWidget;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrVendorBankAccountsSubPage extends LicMgrVendorDetailsPage {
	public static LicMgrVendorBankAccountsSubPage _instance = null;

	protected LicMgrVendorBankAccountsSubPage() {}

	public static LicMgrVendorBankAccountsSubPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrVendorBankAccountsSubPage();
		}

		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Add Bank Account");
	}

	/**
	 * Click 'Add Bank Account' button
	 */
	public void clickAddBankAccount() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Bank Account");
	}

	/**
	 * Click 'Change Store Bank Account Assignment'
	 */
	public void clickChangeStoreBankAccountAssignments() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Change (Store|Agent) Bank Account Assignments", false));
	}

	/**
	 * Click 'View Store Bank Account Assignments'
	 */
	public void clickViewStoreAccountAssignments() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("View (Store|Agent) Bank Account Assignments", false));
	}

	/**
	 * Get the check box id by using the corresponding label name
	 * @param labelName
	 * @return
	 */
	private String getCheckBoxIDByLabelName(String labelName) {
		IHtmlObject objs[] = null;
		Property property[] = new Property[3];
		property[0] = new Property(".class", "Html.LABEL");
		property[1] = new Property(".className", "trailing");
		property[2] = new Property(".text", new RegularExpression("^" + labelName + "$", false));

		if(null != labelName && labelName.length() > 0){
			objs = browser.getHtmlObject(property);
		} else {
			throw new ActionFailedException("Please give the correct label name of check box.");
		}

		String checkBoxID = "";
		if(objs.length == 1) {
			checkBoxID = objs[0].getProperty("htmlFor").trim();
		} else {
			throw new ActionFailedException("Find 0 or multiple LABEL named - " + labelName + ".");
		}

		Browser.unregister(objs);
		return checkBoxID;
	}

	/**
	 * Check the 'Show Current Records Only' check box
	 */
	public void checkShowCurrentRecordsOnlyCheckBox() {
		browser.selectCheckBox(".id", getCheckBoxIDByLabelName("Show Current Records only"));
		ajax.waitLoading();
	}

	/**
	 * Un-chek the 'Show Current Records Only' check box
	 */
	public void unCheckShowCurrentRecordsOnlyCheckBox() {
		browser.unSelectCheckBox(".id", getCheckBoxIDByLabelName("Show Current Records only"));
		ajax.waitLoading();
	}

	/**
	 * Check Bank Account Status - Active
	 */
	public void checkBankAccountStatusActive() {
		browser.selectCheckBox(".id", getCheckBoxIDByLabelName("Active"));
	}

	/**
	 * Un-check Bank Account Status - Active
	 */
	public void unCheckBankAccountStatusActive() {
		browser.unSelectCheckBox(".id", getCheckBoxIDByLabelName("Active"));
	}

	/**
	 * Check Bank Account Status - Inactive
	 */
	public void checkBankAccountStatusInactive() {
		browser.selectCheckBox(".id", getCheckBoxIDByLabelName("Inactive"));
	}

	/**
	 * Un-check Bank Account Status - Inactive
	 */
	public void unCheckBankAccountStatusInactive() {
		browser.unSelectCheckBox(".id", getCheckBoxIDByLabelName("Inactive"));
	}

	/**
	 * Check Prenote Status - Pending
	 */
	public void checkPrenoteStatusPending() {
		browser.selectCheckBox(".id", getCheckBoxIDByLabelName("Pending"));
	}

	/**
	 * Un-check Prenote Status - Pending
	 */
	public void unCheckPrenoteStatusPending() {
		browser.unSelectCheckBox(".id", getCheckBoxIDByLabelName("Pending"));
	}

	/**
	 * Check Prenote Status - Sent
	 */
	public void checkPrenoteStatusSent() {
		browser.selectCheckBox(".id", getCheckBoxIDByLabelName("Sent"));
	}

	/**
	 * Un-check Prenote Status - Sent
	 */
	public void unCheckPrenoteStatusSent() {
		browser.unSelectCheckBox(".id", getCheckBoxIDByLabelName("Sent"));
	}

	/**
	 * Check Prenote Status - Bypassed
	 */
	public void checkPrenoteStatusBypassed() {
		browser.selectCheckBox(".id", getCheckBoxIDByLabelName("Bypassed"));
	}

	/**
	 * Un-check Prenote Status - Bypassed
	 */
	public void unCheckPrenoteStatusBypassed() {
		browser.unSelectCheckBox(".id", getCheckBoxIDByLabelName("Bypassed"));
	}

	/**
	 * Check Prenote Status - Successful
	 */
	public void checkPrenoteStatusSuccessful() {
		browser.selectCheckBox(".id", getCheckBoxIDByLabelName("Successful"));
	}

	/**
	 * Un-check Prenote Status - Successful
	 */
	public void unCheckPrenoteStatusSuccessful() {
		browser.unSelectCheckBox(".id", getCheckBoxIDByLabelName("Successful"));
	}

	/**
	 * Check Prenote Status - Failed
	 */
	public void checkPrenoteStatusFailed() {
		browser.selectCheckBox(".id", getCheckBoxIDByLabelName("Failed"));
	}

	/**
	 * Un-check Prenote Status - Failed
	 */
	public void unCheckPrenoteStatusFailed() {
		browser.unSelectCheckBox(".id", getCheckBoxIDByLabelName("Failed"));
	}

	/**
	 * Check Account Type - Checking
	 */
	public void checkAccountTypeChecking() {
		browser.selectCheckBox(".id", getCheckBoxIDByLabelName("Checking"));
	}

	/**
	 * Un-check Account Type - Checking
	 */
	public void unCheckAccountTypeChecking() {
		browser.unSelectCheckBox(".id", getCheckBoxIDByLabelName("Checking"));
	}

	/**
	 * Check Account Type - Saving
	 */
	public void checkAccountTypeSaving() {
		browser.selectCheckBox(".id", getCheckBoxIDByLabelName("Saving(s)?"));
	}

	/**
	 * Un-check Account Type - Saving
	 */
	public void unCheckAccountTypeSaving() {
		browser.unSelectCheckBox(".id", getCheckBoxIDByLabelName("Saving(s)?"));
	}

	/**
	 * Click 'Deactivate' button
	 */
	public void clickDeactivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate");
		ajax.waitLoading();
	}

	/**
	 * Click 'Bypass Pre-Note' button
	 */
	public void clickBypassPreNote() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Bypass Pre-Note");
		ajax.waitLoading();
	}
	
	public void bypassPreNote(String id){
		OrmsConfirmDialogWidget confirm = OrmsConfirmDialogWidget.getInstance();
		this.selectBankAccount(id);
		this.clickBypassPreNote();
		confirm.waitLoading();
		
		confirm.clickOK();
		ajax.waitLoading();
		this.waitLoading();
	}

	public void clickGo() {
		browser.clickGuiObject(".class","Html.A",".text","Go");
		ajax.waitLoading();
	}

	public void isShowCurrentReadOnly(boolean isCurrentReadOnly){
		if (!isCurrentReadOnly){
			this.unCheckShowCurrentRecordsOnlyCheckBox();
		}else{
			this.checkShowCurrentRecordsOnlyCheckBox();
		}
		this.clickGo();
	}

	public String[] getBankAccountListColumnNames(){
		IHtmlObject objs[] = browser.getTableTestObject(".id", new RegularExpression(bankAccountTableID, false));

		if(objs.length < 1) {
			throw new ItemNotFoundException("There is not a table which id =  "+bankAccountTableID);
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		String columNames[] = new String[table.columnCount()-1];

		for (int i = 1 ; i < table.columnCount(); i ++){
			columNames[i-1] = table.getCellValue(0, i).trim();
		}

		Browser.unregister(objs);
		Browser.unregister(table);
		return columNames;
	}

	/**
	 * Get all vendor bank accounts displayed on the page
	 * @return
	 */
	public List<VendorBankAccountInfo> getAllBankAccounts() {

		IHtmlObject objs[] = browser.getTableTestObject(".id", new RegularExpression(bankAccountTableID, false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("There is not a table which id =  "+bankAccountTableID);
		}
		IHtmlTable table = (IHtmlTable)objs[0];

		List<VendorBankAccountInfo> accounts = new ArrayList<VendorBankAccountInfo>();

		for(int i = 1 ; i < table.rowCount(); i ++) {
			if(table.getCellValue(0, 0).contains("Deactivate")) {
				continue;
			}
			VendorBankAccountInfo account = new VendorBankAccountInfo();
			account.accountID = table.getCellValue(i, 1);
			account.accountPrenoteStatus = table.getCellValue(i, 2);
			account.accountStatus = table.getCellValue(i, 3);
			account.accountType = table.getCellValue(i, 4);
			account.routingNum = table.getCellValue(i, 5);
			account.accountNum = table.getCellValue(i, 6);
			account.numOfAssignedStore = Integer.parseInt(table.getCellValue(i, 7).trim());
			account.setBankName(table.getCellValue(i, 8));
			account.setBankBranchName(table.getCellValue(i, 9));
			account.creationDateTime = table.getCellValue(i, 10);
			account.creationUser = table.getCellValue(i, 11);

			accounts.add(account);
		}

		Browser.unregister(objs);
		Browser.unregister(table);
		return accounts;
	}
	public void selectShowCurrentRecordsOnly(boolean select){
		if(select)
		{
			browser.selectCheckBox(".id", new RegularExpression("CheckboxExt-\\d+\\.checked",false));
		}else{
			browser.unSelectCheckBox(".id", new RegularExpression("CheckboxExt-\\d+\\.checked",false));	
		}
	}

	/**
	 * Get the vendor bank account detail info by account id
	 * @param accountID
	 * @return
	 */
	public VendorBankAccountInfo getBankAccount(String accountID) {
		this.selectShowCurrentRecordsOnly(false);
		ajax.waitLoading();
		this.waitLoading();
		
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
		
		List<VendorBankAccountInfo> accounts = getAllBankAccounts();
		VendorBankAccountInfo account = new VendorBankAccountInfo();

		for(int i = 0 ; i < accounts.size(); i ++) {
			if(Integer.parseInt(accounts.get(i).accountID) == Integer.parseInt(accountID)) {
				account = accounts.get(i);
				break;
			}
		}

		return account;
	}

	/**
	 * Get the first active bank account details info which has assignments
	 * @return
	 */
	public VendorBankAccountInfo getFirstBankAccountWithAssignedStore() {
		this.isShowCurrentReadOnly(false);
		List<VendorBankAccountInfo> accounts = getAllBankAccounts();
		VendorBankAccountInfo account = new VendorBankAccountInfo();

		for(int i = 0 ; i < accounts.size(); i ++) {
			if (accounts.get(i).accountStatus.equalsIgnoreCase("Active") && accounts.get(i).numOfAssignedStore >0){
				account = accounts.get(i);
				break;
			}
		}

		return account;
	}

	/**
	 * Get the newest active account id. Because the lasted added account always shows at the 1st row OR if someone click the 'ID' column name to
	 * descending order, at this time get the last row.
	 * @return
	 */
	public String getNewestAccountID() {
		IHtmlObject objs[] = browser.getTableTestObject(".id", new RegularExpression(bankAccountTableID, false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("There is not a table which id = "+bankAccountTableID);
		}
		IHtmlTable table = (IHtmlTable)objs[0];

		String firstRowCreatTime = table.getCellValue(1, 8).trim();
		String lastRowCreatTime = table.getCellValue(table.rowCount() - 1, 8).trim();

		Date firstRowCreatTimeDate = DateFunctions.parseDateString(firstRowCreatTime, "MM/dd/yyyy hh:mm:ss");
		Date lastRowCreatTimeDate =DateFunctions.parseDateString(lastRowCreatTime, "MM/dd/yyyy hh:mm:ss");
		int needeRowNum = DateFunctions.compareDates(firstRowCreatTimeDate, lastRowCreatTimeDate) == 1 ? 1 : table.rowCount() - 1;

		String accountID = table.getCellValue(needeRowNum, 1).trim();
		Browser.unregister(objs);

		return accountID;
	}
	
	private String bankAccountTableID = "vendoreBAnkAccountProductGrid|BankAccountViewGrid";

	/**
	 * Get the bank account id identified by account detail information
	 * @param account
	 * @return
	 */
	public String getBankAccountID(VendorBankAccountInfo account) {
		IHtmlObject objs[] = browser.getTableTestObject(".id", new RegularExpression(bankAccountTableID, false));
		if(objs.length < 1) {
			throw new ObjectNotFoundException("There is not a table which id = "+bankAccountTableID);
		}
		IHtmlTable table = (IHtmlTable)objs[0];

		String prenoteStatus, status, accountType, routingNum, accountNum;
		int counter = -1;
		for(int i = 1; i < table.rowCount(); i ++) {
			prenoteStatus = table.getCellValue(i, 2);
			status = table.getCellValue(i, 3);
			accountType = table.getCellValue(i, 4);
			routingNum = table.getCellValue(i, 5);
			accountNum = table.getCellValue(i, 6);

			if(prenoteStatus.equals(account.accountPrenoteStatus)
					&& status.equals(account.accountStatus)
					&& accountType.equals(account.accountType)
					&& routingNum.equals(account.routingNum)
					&& (accountNum.length() == account.accountNum.length())
					&& accountNum.substring(0, 4).equals(account.accountNum.substring(0, 4))) {
				counter = i;
				break;
			}
		}

		if(counter == -1) {
			throw new ObjectNotFoundException("Can't find the account id.");
		}

		String id = table.getCellValue(counter, 1);
		Browser.unregister(objs);

		return id;
	}

	/**
	 * Check whether a specific bank account exists or not
	 * @param account
	 * @return
	 */
	public boolean checkBankAccountRecordExists(VendorBankAccountInfo account) {
		IHtmlObject objs[] = browser.getTableTestObject(".id", new RegularExpression(bankAccountTableID, false));
		if(objs.length < 1) {
			throw new ObjectNotFoundException("There is not a table which id = "+bankAccountTableID);
		}
		IHtmlTable table = (IHtmlTable)objs[0];

		String prenoteStatus, status, accountType, routingNum, accountNum;
		for(int i = 1; i < table.rowCount(); i ++) {
			prenoteStatus = table.getCellValue(i, 2);
			status = table.getCellValue(i, 3);
			accountType = table.getCellValue(i, 4);
			routingNum = table.getCellValue(i, 5);
			accountNum = table.getCellValue(i, 6);

			if(prenoteStatus.equals(account.accountPrenoteStatus)
					&& status.equals(account.accountStatus)
					&& accountType.equals(account.accountType)
					&& routingNum.equals(account.routingNum)
					&& (accountNum.length() == account.accountNum.length())
					&& accountNum.substring(0, 4).equals(account.accountNum.substring(0, 4))) {
				return true;
			}
		}

		Browser.unregister(objs);
		return false;
	}

	/**
	 * Get the total bank accounts count
	 * @return
	 */
	public int getTotalBankAccountsCount() {
		IHtmlObject objs[] = browser.getTableTestObject(".id", new RegularExpression(bankAccountTableID, false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("There is not a table which id =  "+bankAccountTableID);
		}

		int totalCount = ((IHtmlTable)objs[0]).rowCount() - 1;

		Browser.unregister(objs);

		return totalCount;
	}

	/**
	 * Select the radio button of bank account identified by account id
	 * @param accountID
	 */
	public void selectBankAccount(String accountID) {
		IHtmlObject objs[] = browser.getTableTestObject(".id", new RegularExpression(bankAccountTableID, false));
		if(objs.length == 0) {
			throw new ItemNotFoundException("There is not a table which id = "+bankAccountTableID);
		}
		IHtmlTable table = (IHtmlTable)objs[0];

		IHtmlObject radioBtnObjs[] = browser.getHtmlObject(".class", "Html.INPUT.radio", ".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems", false));
		int rowNum = table.findRow(1, accountID);

		radioBtnObjs[rowNum - 1].click();
		Browser.unregister(objs);
		Browser.unregister(radioBtnObjs);
		Browser.unregister(table);
	}

	/**
	 * Deactivate bank account
	 * @param accountID
	 */
	public void deactivateBankAccount(String accountID) {
		selectBankAccount(accountID);
		clickDeactivate();
		ajax.waitLoading();
	}

	public boolean compareBanckAccountListInfo(VendorBankAccountInfo expectedBankInfo){
		boolean pass = true;
		VendorBankAccountInfo actualBankAccount = this.getBankAccount(expectedBankInfo.accountID);
		if(null == actualBankAccount){
			pass &= false;
			logger.error("Can't get the specific banck account info");
		}
		if(!actualBankAccount.accountID.equals(expectedBankInfo.accountID)){
			pass &= false;
			logger.error("Expected bank account id should be "+expectedBankInfo.accountID+" but actual bank account id is"+actualBankAccount.accountID+"");
		}
		if(!actualBankAccount.accountPrenoteStatus.equals(expectedBankInfo.accountPrenoteStatus)){
			pass &= false;
			logger.error("Expected bank account prenote status should be "+expectedBankInfo.accountPrenoteStatus+" but actual bank account id is "+actualBankAccount.accountPrenoteStatus+"");
		}
		if(!actualBankAccount.accountStatus.equals(expectedBankInfo.accountStatus)){
			pass &= false;
			logger.error("Expected bank account status should be "+expectedBankInfo.accountStatus+" but actual bank account status is "+actualBankAccount.accountStatus+"");
		}
		if(!actualBankAccount.accountType.equals(expectedBankInfo.accountType)){
			pass &= false;
			logger.error("Expected bank account type should be "+expectedBankInfo.accountType+" but actual bank acount type is "+actualBankAccount.accountType+"");
		}
		if(!actualBankAccount.routingNum.equals(expectedBankInfo.routingNum)){
			pass &= false;
			logger.error("Expected bank rounting # should be"+expectedBankInfo.routingNum+" but actual bank rounting # is "+actualBankAccount.routingNum+"");
		}
		if(!actualBankAccount.accountNum.contains(expectedBankInfo.accountNum.substring(0, 4))){
			pass &= false;
			logger.error("Expected bank account # should be "+expectedBankInfo.accountNum+" but actual bank account # is "+actualBankAccount.accountNum+"");
		}
		if(!String.valueOf(actualBankAccount.numOfAssignedStore).equals(String.valueOf(expectedBankInfo.numOfAssignedStore))){
			pass &= false;
			logger.error("Expected bank agent assignments # should be"+expectedBankInfo.numOfAssignedStore+" but actual bank agent assingment # is "+actualBankAccount.numOfAssignedStore+"");
		}
		// replace " " with "", in order to match Test-Auto, QA-Auto or Test-Auto,QA-Auto
		if(!actualBankAccount.creationUser.replaceAll(" ", StringUtil.EMPTY).equals(expectedBankInfo.creationUser.replaceAll(" ", StringUtil.EMPTY))){
			pass &= false;
			logger.error("Expected creation user should be "+expectedBankInfo.creationUser+" but actual creation user is "+actualBankAccount.creationUser+"");
		}
		if(null != expectedBankInfo.getBankName()){
			pass &= MiscFunctions.compareResult("Bank Name", expectedBankInfo.getBankName(), actualBankAccount.getBankName());
		}
		if(null != expectedBankInfo.getBankBranchName()){
			pass &= MiscFunctions.compareResult("Bank Branch Name", expectedBankInfo.getBankBranchName(), actualBankAccount.getBankBranchName());
		}
		
		return pass;
	}
	
	private IHtmlTable getBankAccountListTable(){
		IHtmlObject objs[] = browser.getTableTestObject(".id", new RegularExpression(bankAccountTableID, false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("There is not a table which id = "+bankAccountTableID);
		}
		IHtmlTable table = (IHtmlTable)objs[0];
//		Browser.unregister(objs);
		return table;
		
	}
	
	private List<String> getColumnValueByName(String columnName){
		IHtmlTable table = getBankAccountListTable();
		
		int col = table.findColumn(0, columnName);
		if(col < 0){
			throw new ItemNotFoundException("Can't find column by given name "+columnName);
		}
		
		List<String> colList = table.getColumnValues(col);
		colList.remove(0);// remove title
		
		return colList;
	}
	
	private void verifyColumnValue(String columnName, String... expectValue){
		boolean result = true;
		List<String> colList = this.getColumnValueByName(columnName);
		List<String> actualList = new ArrayList<String>();
		
		// remove duplicate value
		for(String actual:colList){
			if(!actualList.contains(actual)){
				actualList.add(actual);
			}
		}
		
		// list from page contains more value than expect.
		if(expectValue.length < actualList.size()){
			throw new ErrorOnPageException("Column "+columnName+" is not correct!");
		} else {
			List<String> expectList = Arrays.asList(expectValue);
			for(int i=0; i<actualList.size(); i++){
				if(!expectList.contains(actualList.get(i))){
					result = false;
					break;
				}
			}
		}
		
		if(!result){
			throw new ErrorOnPageException("Column "+columnName+" should only contain "+expectValue);
		}
	}
	
	public void verifyPrenoteStatusColumnValue(String... expectValue){
		this.verifyColumnValue("Prenote Status", expectValue);
	}
	

	public void verifyStatusColumnValue(String... expectValue){
		this.verifyColumnValue("Status", expectValue);
	}

	public void verifyAccountTypeColumnValue(String... expectValue){
		this.verifyColumnValue("Account Type", expectValue);
	}
}
