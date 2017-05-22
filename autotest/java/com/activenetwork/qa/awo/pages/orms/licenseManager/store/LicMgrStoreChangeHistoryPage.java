package com.activenetwork.qa.awo.pages.orms.licenseManager.store;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Jan 9, 2012
 */
public class LicMgrStoreChangeHistoryPage extends LicMgrTopMenuPage implements ILicMgrChangeHistoryPage {
	
	private static LicMgrStoreChangeHistoryPage _instance = null;
	
	protected LicMgrStoreChangeHistoryPage() {}
	
	public static LicMgrStoreChangeHistoryPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrStoreChangeHistoryPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "storehistory_LIST");
	}
	
	@Override
	public void clickFirst() {
		browser.clickGuiObject(".class", "Html.A", ".text", "First");
	}

	@Override
	public void clickLast() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Last");
	}

	@Override
	public void clickNext() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Next");
	}

	@Override
	public void clickPrevious() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Previous");
	}

	@Override
	public List<ChangeHistory> getChangeHistoryInfo() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<ChangeHistory> histories = new ArrayList<ChangeHistory>();
		do {
			objs = browser.getTableTestObject(".id", "storehistory_LIST");
			if(objs.length < 1) {
				throw new ObjectNotFoundException("Can't find store change history table object which id='storehistory_LIST'.");
			}
			table = (IHtmlTable)objs[0];
			for(int i = 1; i < table.rowCount(); i ++) {
				ChangeHistory history = new ChangeHistory();
				history.changeDate = table.getCellValue(i, 0).trim();
				history.object = table.getCellValue(i, 1).trim();
				history.action = table.getCellValue(i, 2).trim();
				history.field = table.getCellValue(i, 3).trim();
				history.oldValue = table.getCellValue(i, 4).trim();
				history.newValue = table.getCellValue(i, 5).trim();
				history.user = table.getCellValue(i, 6).trim();
				history.location = table.getCellValue(i, 7).trim();
				
				histories.add(history);
			}
		} while(gotoNext());
		
		return histories;
	}

	public List<ChangeHistory> getChangeHistoryInfo(String objectIdentifier) {
		List<ChangeHistory> allHistories = this.getChangeHistoryInfo();
		
		for(int i = 0; i < allHistories.size(); i ++) {
			if(objectIdentifier != null && !allHistories.get(i).object.equals(objectIdentifier)) {
				allHistories.remove(i);
			}
		}
		return allHistories;
	}
	
	@Override
	public boolean gotoNext() {
		boolean exists = browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Next");
		if(exists) {
			this.clickNext();
			waitLoading();
		}
		
		return exists;
	}
	
	public void clickReturnToAgentsDetails(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Return to Agent Details");
	}
	
	/**
	 * Compare store financial config change history info
	 * @param histories
	 * @return
	 */
	public boolean compareFinancialConfigChangeHistoryInfo(List<ChangeHistory> histories) {
		List<ChangeHistory> actualHistories = this.getChangeHistoryInfo("Agent Financial Information");
		
		boolean result = true;
		actualHistories = actualHistories.subList(0, histories.size()-1);//get actual change histories record with the same length with expected
		ChangeHistory history = new ChangeHistory();
		ChangeHistory actualHistory = new ChangeHistory();
		for(int i = 0; i < histories.size(); i ++) {
			for(int j = 0; j < actualHistories.size(); j ++) {
				if(histories.get(i).field.equals(actualHistories.get(j).field)) {
					history = histories.get(i);
					actualHistory = actualHistories.get(j);
					if(!history.equals(actualHistory)) {
						logger.error("Expected Change History - Field: " + history.field + ", Action: " + history.action + ", New Value: " + history.newValue
								+ " but actual record - Field: " + actualHistory.field + ", Action: " + actualHistory.action + ", New Value: " + actualHistory.newValue);
						result &= false;
					} else {
						logger.info("Actaul Change histroy record is correct with expected.");
					}
				}
			}
		}
		
		return result;
	}
	
	public boolean compareStoreChangeHistoryInfo(String action,StoreInfo expectedOldStore,StoreInfo expectedNewStore,List<String> fieldList){
		boolean pass = true;
		List<ChangeHistory> list = this.getChangeHistoryInfo();
		ChangeHistory history  = new ChangeHistory();
		
			for(int i =0;i<fieldList.size();i++){
				if(null != expectedNewStore){
					if(fieldList.get(i).equals("Physical Address-Address")){
						history = this.getHistory(action, fieldList.get(i),list);
						if(null != history){
							pass &= MiscFunctions.compareResult("Old Physical Address-Address", expectedOldStore.physicalAddress.address, history.oldValue);
							pass &= MiscFunctions.compareResult("new Physical Address-Address", expectedNewStore.physicalAddress.address, history.newValue);
						}
					}else if(fieldList.get(i).equals("Physical Address-ZIP/Postal")){
						history = this.getHistory(action, fieldList.get(i),list);
						if(null != history){
							pass &= MiscFunctions.compareResult("Old Physical Address-ZIP/Postal", expectedOldStore.physicalAddress.zip, history.oldValue);
							pass &= MiscFunctions.compareResult("new Physical Address-ZIP/Postal", expectedNewStore.physicalAddress.zip, history.newValue);
						}
					}else if(fieldList.get(i).equals("Physical Address-City/Town")){
						history = this.getHistory(action, fieldList.get(i),list);
						if(null != history){
							pass &= MiscFunctions.compareResult("Old Physical Address-City/Town", expectedOldStore.physicalAddress.city, history.oldValue);
							pass &= MiscFunctions.compareResult("new Physical Address-City/Town", expectedNewStore.physicalAddress.city, history.newValue);
						}
					}else if(fieldList.get(i).equals("Mailing Address-Address")){
						history = this.getHistory(action, fieldList.get(i),list);
						if(null != history){
							pass &= MiscFunctions.compareResult("Old Mailing Address-Address", expectedOldStore.mailingAddress.address, history.oldValue);
							pass &= MiscFunctions.compareResult("new Mailing Address-Address", expectedNewStore.mailingAddress.address, history.newValue);
						}
						
					}else if(fieldList.get(i).equals("Mailing Address-ZIP/Postal")){
						history = this.getHistory(action, fieldList.get(i),list);
						if(null != history){
							pass &= MiscFunctions.compareResult("Old Mailing Address-ZIP/Postal", expectedOldStore.mailingAddress.zip, history.oldValue);
							pass &= MiscFunctions.compareResult("new Mailing Address-ZIP/Postal", expectedNewStore.mailingAddress.zip, history.newValue);
						}
						
					}else if(fieldList.get(i).equals("Mailing Address-City/Town")){
						history = this.getHistory(action, fieldList.get(i),list);
						if(null != history){
							pass &= MiscFunctions.compareResult("Old Mailing Address-City/Town", expectedOldStore.mailingAddress.city, history.oldValue);
							pass &= MiscFunctions.compareResult("new Mailing Address-City/Town", expectedNewStore.mailingAddress.city, history.newValue);
						}
					}else if(fieldList.get(i).equals("Agent Name")){
						history = this.getHistory(action, fieldList.get(i),list);
						if(null != history){
							pass &= MiscFunctions.compareResult("Old Mailing Address-City/Town", expectedOldStore.storeName, history.oldValue);
							pass &= MiscFunctions.compareResult("new Mailing Address-City/Town", expectedNewStore.storeName, history.newValue);
						}
					}else if(fieldList.get(i).equals("Operation Mgr-Suffix")){
						history = this.getHistory(action, fieldList.get(i),list);
						if(null != history){
							pass &= MiscFunctions.compareResult("Old Operation Mgr-Suffix", expectedOldStore.contactArray.get(0).suffix, history.oldValue);
							pass &= MiscFunctions.compareResult("new Operation Mgr-Suffix", expectedNewStore.contactArray.get(0).suffix, history.newValue);
						}
					}else if(fieldList.get(i).equals("Operation Mgr-Fax")){
						history = this.getHistory(action, fieldList.get(i),list);
						if(null != history){
							pass &= MiscFunctions.compareResult("Old Operation Mgr-Fax", expectedOldStore.contactArray.get(0).fax, history.oldValue);
							pass &= MiscFunctions.compareResult("new Operation Mgr-Fax", expectedNewStore.contactArray.get(0).fax, history.newValue);
						}
					}else if(fieldList.get(i).equals("Operation Mgr-Mobile Phone")){
						history = this.getHistory(action, fieldList.get(i),list);
						if(null != history){
							pass &= MiscFunctions.compareResult("Old Operation Mgr-Mobile Phone", expectedOldStore.contactArray.get(0).mobilePhone, history.oldValue);
							pass &= MiscFunctions.compareResult("new Operation Mgr-Mobile Phone", expectedNewStore.contactArray.get(0).mobilePhone, history.newValue);
						}
					}else if(fieldList.get(i).equals("Operation Mgr-Business Phone")){
						history = this.getHistory(action, fieldList.get(i),list);
						if(null != history){
							pass &= MiscFunctions.compareResult("Old Operation Mgr-Business Phone", expectedOldStore.contactArray.get(0).businessPhone, history.oldValue);
							pass &= MiscFunctions.compareResult("new Operation Mgr-Business Phone", expectedNewStore.contactArray.get(0).businessPhone, history.newValue);
						}
					}else if(fieldList.get(i).equals("Operation Mgr-Home Phone")){
						history = this.getHistory(action, fieldList.get(i),list);
						if(null != history){
							pass &= MiscFunctions.compareResult("Old Operation Mgr-Home Phone", expectedOldStore.contactArray.get(0).homePhone, history.oldValue);
							pass &= MiscFunctions.compareResult("new Operation Mgr-Home Phone", expectedNewStore.contactArray.get(0).homePhone, history.newValue);
						}
					}else if(fieldList.get(i).equals("Operation Mgr-Middle Name")){
						history = this.getHistory(action, fieldList.get(i),list);
						if(null != history){
							pass &= MiscFunctions.compareResult("Old Operation Mgr-Middle Name", expectedOldStore.contactArray.get(0).midName, history.oldValue);
							pass &= MiscFunctions.compareResult("new Operation Mgr-Middle Name", expectedNewStore.contactArray.get(0).midName, history.newValue);
						}
					}else if(fieldList.get(i).equals("Operation Mgr-Last Name")){
						history = this.getHistory(action, fieldList.get(i),list);
						if(null != history){
							pass &= MiscFunctions.compareResult("Old Operation Mgr-Last Name", expectedOldStore.contactArray.get(0).lastName, history.oldValue);
							pass &= MiscFunctions.compareResult("new Operation Mgr-Last Name", expectedNewStore.contactArray.get(0).lastName, history.newValue);
						}
					}else if(fieldList.get(i).equals("Operation Mgr-First Name")){
						history = this.getHistory(action, fieldList.get(i),list);
						if(null != history){
							pass &= MiscFunctions.compareResult("Old Operation Mgr-First Name", expectedOldStore.contactArray.get(0).firstName, history.oldValue);
							pass &= MiscFunctions.compareResult("new Operation Mgr-First Name", expectedNewStore.contactArray.get(0).firstName, history.newValue);
						}
					}
				}
				
			}
			return pass;
		}
		
	/**
	 * get history old value.
	 */
	public ChangeHistory getHistory(String action,String fieldValue,List<ChangeHistory> historyList){
		ChangeHistory history= null ;
		//List<ChangeHistory> historyInfo = this.getChangeHistoryInfo();
		for(int index = 0;index<historyList.size();index++){
			if(historyList.get(index).action.equals(action) && historyList.get(index).field.equals(fieldValue)){
				history =  historyList.get(index);
			}
		}
		return history;
	}
	
}
