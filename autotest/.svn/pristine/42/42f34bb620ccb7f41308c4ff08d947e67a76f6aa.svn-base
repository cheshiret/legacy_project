package com.activenetwork.qa.awo.pages.orms.licenseManager.vendor;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * ScriptName: LicMgrChangeVendorBankAccountStoreAssignmentsWidget
 * Description:
 * @date: Mar 30, 2011-4:09:17 AM
 * @author QA-qchen
 *
 */
public class LicMgrChangeVendorBankAccountStoreAssignmentsWidget extends DialogWidget {
	
	public static LicMgrChangeVendorBankAccountStoreAssignmentsWidget _instance = null;
	
	protected LicMgrChangeVendorBankAccountStoreAssignmentsWidget() {
		super("Vendor Bank Account Agent Assignments");
	}
	
	public static LicMgrChangeVendorBankAccountStoreAssignmentsWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrChangeVendorBankAccountStoreAssignmentsWidget();
		}
		
		return _instance;
	}
	
	
	/**
	 * Get the row number in the storeAssignmentGrid table identified by specified store id
	 * @param storeID
	 * @return
	 */
	public int getRowNumByStoreID(String storeID) {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "storeAssignmentGrid");
		
		if(objs.length == 0) {
			throw new ItemNotFoundException("Can't find the Store Assignment table.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowNum = table.findRow(0, storeID);
		Browser.unregister(objs);
		return rowNum;
	}
	
	/**
	 * Get the row number in the storeAssignmentGrid table identified by specified store name
	 * @param storeName
	 * @return
	 */
	private int getRowNumByStoreName(String storeName) {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "storeAssignmentGrid");
		
		if(objs.length == 0) {
			throw new ItemNotFoundException("Can't find the Store Assignment table.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowNum = table.findRow(1, new RegularExpression(("^" + storeName), false));
		
		Browser.unregister(objs);
		return rowNum;
	}
	
	/**
	 * If store name is NOT null, select the corresponding Bank Account object identified by store name;
	 * if store name is NULL, select account of the 1st Bank Account drop down list object to set ALL stores to this account by default.
	 * @param storeID - a specified store id or NULL
	 * @param account - the bank account regular expression which store will be assigned to
	 */
	public void selectBankAccountByStoreName(String storeName, String account) {
		int objectIndex = -1;
		if(null != storeName && storeName.length() > 0) {
			objectIndex = getRowNumByStoreName(storeName) - 1;
		} else {
			objectIndex = 0;
		}
		selectBankAccountByRowNum(objectIndex, account);
	}
	
	/**
	 * Select a specified account option in Bank Account drop down list identified by row number
	 * @param objectIndex
	 * @param account
	 */
	private void selectBankAccountByRowNum(int objectIndex, String account) {
		IHtmlObject objs[] = browser.getDropdownList(".id", new RegularExpression("VendorBankAcctStoreAssignmentView-\\d+\\.vendorBankAccountView", false));
		if(objs.length == 0) {
			throw new ItemNotFoundException("Can't find any Bank Account drop down list objects.");
		}
		((ISelect)objs[objectIndex]).select(new RegularExpression(("^" + account), false));
		ajax.waitLoading();
		
		Browser.unregister(objs);
	}
	
	/**
	 * Set ALL stores to a specified account
	 * @param account
	 */
	public void setAllStoresToABankAccount(String account) {
		selectBankAccountByStoreName("", account);
	}
	
	/**
	 * If store name is NOT null, select a specified store's bank account identified by store name;
	 * if store name is NULL, select account of the 1st Bank Account drop down list object to set ALL stores to this account by default.
	 * @param storeName
	 * @param effectDate
	 */
	public void setEffectiveDateByStoreName(String storeName, String effectDate) {
		int objectIndex = -1;
		
		if(null != storeName && storeName.length() > 0) {
			objectIndex = getRowNumByStoreName(storeName) - 1;
		} else {
			objectIndex = 0;
		}
		
		setEffectiveDateByRowNum(objectIndex, effectDate);
	}
	
	/**
	 * Set an effective date into Effective date text field identified by row number
	 * @param objectIndex
	 * @param effectiveDate
	 */
	private void setEffectiveDateByRowNum(int objectIndex, String effectiveDate) {
		browser.setTextField(".id", new RegularExpression("VendorBankAcctStoreAssignmentView-\\d+\\.effectiveDate_ForDisplay", false), effectiveDate, objectIndex);
	}
	
	/**
	 * Set ALL stores to a specified effective date
	 * @param effectDate
	 */
	public void setAllStoresToASameEffectiveDate(String effectDate) {
		setEffectiveDateByStoreName("", effectDate);
	}
	
	/**
	 * Assign a store to a specified Bank Account and set the effective date identified by store name.
	 * If store name is NULL, this method will default set ALL store to a specified Bank Account and set effective date.
	 * @param storeName
	 * @param account
	 * @param effectiveDate
	 */
	public void assignBankAccountToStore(String storeName, String account, String effectiveDate) {
		int objectIndex = -1;
		
		if(null != storeName && storeName.length() > 0) {
			objectIndex = getRowNumByStoreName(storeName) - 1;
		} else {
			objectIndex = 0;
		}
		
		selectBankAccountByRowNum(objectIndex, account);
		setEffectiveDateByRowNum(objectIndex, effectiveDate);
	}
	
	/**
	 * Get the selected bank account identified by store name
	 * @param storeName
	 * @return
	 */
	public String getBankAccountByStoreName(String storeName) {
		int rowNum = getRowNumByStoreName(storeName);
		String selectedValue = browser.getDropdownListValue(".id", new RegularExpression("VendorBankAcctStoreAssignmentView-\\d+\\.vendorBankAccountView", false), rowNum - 1);
		
		return selectedValue;
	}
	
	/**
	 * Get the set effective date value
	 * @param storeName
	 * @return
	 */
	public String getEffectiveDateByStoreName(String storeName) {
		int rowNum = getRowNumByStoreName(storeName);
		String effectiveDate = browser.getTextFieldValue(".id", new RegularExpression("VendorBankAcctStoreAssignmentView-\\d+\\.effectiveDate_ForDisplay", false), rowNum - 1);
		
		return effectiveDate;
	}
	
	/**
	 * Get the error message
	 * @return
	 */
	public String getErrorMessage() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".className", "message msgerror");
		String msg = objs[0].getProperty(".text").trim();
		return msg;
	}
	
	/**
	 * Verify the effective date component will auto change the invalid dates to valid ones
	 * @param storeName
	 * @param account
	 * @param invalidDates
	 * @return
	 */
	public boolean verifyEffectiveDate(String storeName, String account, String[] invalidDates) {
		int rowNum = getRowNumByStoreName(storeName);
		selectBankAccountByRowNum(rowNum - 1, account);
		return verifyAutomaticDateCorrection((IText)browser.getTextField(".id", new RegularExpression("VendorBankAcctStoreAssignmentView-\\d+\\.effectiveDate_ForDisplay", false))[rowNum - 1], invalidDates);
	}
	
	/**
	 * Get the error message displayed at the widget top
	 * @return
	 */
	public String getErrorMsg() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".id", "NOTSET");
		String errorMsg = "";
		if(objs.length > 0) {
			errorMsg = objs[0].getProperty(".text").trim();
		}
		
		return errorMsg;
	}
	
}
