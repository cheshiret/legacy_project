package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @ScriptName LicMgrCustomerChangeHistoryPage.java
 * @Date:Mar 24, 2011
 * @Description:
 * @author Swang
 */
public class LicMgrCustomerChangeHistoryPage extends LicMgrCommonTopMenuPage implements ILicMgrChangeHistoryPage {
	private static LicMgrCustomerChangeHistoryPage instance=null;

	private LicMgrCustomerChangeHistoryPage(){};
	

	public static LicMgrCustomerChangeHistoryPage getInstance(){
		if(instance==null){
			instance=new LicMgrCustomerChangeHistoryPage();
		}
		return instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id","hfcustomerhistory_LIST");
	}
	
	@Override
	public void clickFirst(){
		browser.clickGuiObject(".class", "Html.A", ".text", "First");
	}
	
	@Override
	public void clickPrevious(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Previous");
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
	
	@Override
	public void clickNext(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Next");
	}
	
	@Override
	public void clickLast(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Last");
	}
	
	public void clickReturnToCustomerDetail(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Return to Customer Detail");
	}
	
	/**
	 * Get change history info
	 * @return: List<ChangeHistoryInfo>
	 */
	public List<ChangeHistory> getChangeHistoryInfo(){
		return getChangeHistoryInfo(null);
	}
	
	/**
	 * Get table specific row value
	 * @param row
	 * @return
	 */
	public List<String> getTableRowCells(int row){
		List<String> tableRowCells = new ArrayList<String>();
		IHtmlObject[] objs = browser.getTableTestObject(".id","hfcustomerhistory_LIST");
		if(objs.length>0){
			IHtmlTable table = (IHtmlTable)objs[0];
			for(int col=0; col<table.columnCount(); col++){
				tableRowCells.add(table.getCellValue(row, col));
			}
		}else throw new ObjectNotFoundException("Table can't find.");

		Browser.unregister(objs);
		return tableRowCells;
	}
	
	private int getColumnNumber(String colNm) {
		IHtmlObject[] objs = browser.getTableTestObject(".id","hfcustomerhistory_LIST");
		int colNum = -1;
		IHtmlTable table = (IHtmlTable)objs[0];
		// get the column number for the given column
		for(int col=0; col<table.columnCount(); col++) {
			if (table.getCellValue(0, col).equalsIgnoreCase(colNm)) {
				colNum = col;
				break;
			}
		}
		
		if (colNum < 0) {
			throw new ErrorOnPageException("Please check! There is no column " + colNm);
		}
		Browser.unregister(objs);
		return colNum;
	}
	
	public List<String> getTableRowCells(String newValue) {
		List<String> tableRowCells = new ArrayList<String>();
		IHtmlObject[] objs = browser.getTableTestObject(".id","hfcustomerhistory_LIST");
		int row = -1;
		int colForNewV = this.getColumnNumber("New Value");
		if(objs.length>0){
			IHtmlTable table = (IHtmlTable)objs[0];
			
			for (int i = 1; i < table.rowCount(); i++) {
				if (table.getCellValue(i, colForNewV).equalsIgnoreCase(newValue)) {
					row = i;
					break;
				}
			}
			if (row < 0) {
				throw new ErrorOnPageException("Can't find the new value: " + newValue);
			}
			
			for(int col=0; col<table.columnCount(); col++){
				tableRowCells.add(table.getCellValue(row, col));
			}
		}else throw new ObjectNotFoundException("Table can't find.");

		Browser.unregister(objs);
		return tableRowCells;
	}
	
	public List<ChangeHistory> getChangeHistoryInfo(String instanceId){
		List<ChangeHistory> tableRowCells = new ArrayList<ChangeHistory>();
		IHtmlObject[] objs = null;
		IHtmlTable table = null;
		do {
			objs = browser.getTableTestObject(".id","hfcustomerhistory_LIST");
			if(objs.length < 1) {
				throw new ObjectNotFoundException("Table can't find.");
			}
			
			table = (IHtmlTable)objs[0];
			for(int row=1; row<table.rowCount(); row++){
				String object=table.getCellValue(row, 1);
				
				if(instanceId!=null&&instanceId.trim().length()>0&&!object.contains(instanceId)){
					continue;
				}
				ChangeHistory changeHistory = new ChangeHistory();
				changeHistory.changeDate=table.getCellValue(row, 0);
				changeHistory.object = object;
				changeHistory.action = table.getCellValue(row, 2);
				changeHistory.field = table.getCellValue(row, 3);
				changeHistory.oldValue = table.getCellValue(row, 4);
				changeHistory.newValue = table.getCellValue(row, 5);
				changeHistory.user = table.getCellValue(row, 6);
				changeHistory.location = table.getCellValue(row, 7);
				tableRowCells.add(changeHistory);
			}
		} while(this.gotoNext());
		
		Browser.unregister(objs);
		
		return tableRowCells;
	}
	
	/**
	 * Verify the customer change history info.
	 * @param oldCust - the before changing customer info.
	 * @param action - the action.
	 * @param location -  the location.
	 */ 
	public void verifyChangeHisSuccessful(Customer oldCust,String action,String location){
		this.verifyChangeHisSuccessful(oldCust,null, action, location);
	}
	
	/**
	 * Verify the customer change history info.
	 * @param oldCust - the before changing customer info.
	 * @param newCust - the after changing customer info.
	 * @param action - the action.
	 * @param location -  the location.
	 */ 
	public void verifyChangeHisSuccessful(Customer oldCust,Customer newCust,String action,String location){
		boolean pass = true;
		List<ChangeHistory>  hisList = this.getChangeHistoryInfo();
		for(int i = 0;i<hisList.size();i++){
			ChangeHistory changeHis = hisList.get(i);
			if(action.equals("Update")){
				if(changeHis.field.trim().equals("Suffix")){
					if(!changeHis.oldValue.trim().equals(oldCust.suffix.trim())|| !changeHis.newValue.trim().equals(newCust.suffix.trim())){
						pass &= false;
						logger.error("Suffix value "+newCust.suffix+" error");
					}
				}
				if(changeHis.field.trim().equals("Fax")){
					if(!changeHis.oldValue.trim().equals(oldCust.fax.trim())|| !changeHis.newValue.trim().equals(newCust.fax.trim())){
						pass &= false;
						logger.error("Fax value "+newCust.fax+" error");
					}
				 }
				
				if(changeHis.field.trim().contains("Ethnicity")){
					if(changeHis.oldValue.equals("1")) {
						changeHis.oldValue="White";
					}
					if(changeHis.newValue.equals("2")){
						changeHis.newValue="Black";
					}
					if(!changeHis.oldValue.trim().equals(oldCust.ethnicity.trim())|| !changeHis.newValue.trim().equals(newCust.ethnicity.trim())){
						pass &= false;
						logger.error("Ethnicity value "+newCust.ethnicity+" error");
					}
				}
				
				if(changeHis.field.trim().contains("Solicitation Indicator")){
					/*if(oldCust.solicitationIndcator.equals("Yes")){
						oldCust.solicitationIndcator="y";
					}else if(oldCust.solicitationIndcator.equals("No")){
						oldCust.solicitationIndcator="n";
					}
					
					if(newCust.solicitationIndcator.equals("Yes")){
						newCust.solicitationIndcator="y";
					}else if(newCust.solicitationIndcator.equals("No")){
						newCust.solicitationIndcator="n";
					}*/
					
					if(!changeHis.oldValue.trim().equals(oldCust.solicitationIndcator.trim())|| !changeHis.newValue.trim().equals(newCust.solicitationIndcator.trim())){
						pass &= false;
						logger.error("Solicitation Indcator value "+newCust.solicitationIndcator+" error");
					}
				}
				
				if(changeHis.field.trim().contains("Gender")){

					//oldCust.custGender = oldCust.custGender.substring(0, 1);
					//newCust.custGender = newCust.custGender.substring(0, 1);
					
					if(!changeHis.oldValue.trim().equals(oldCust.custGender.trim())|| !changeHis.newValue.trim().equals(newCust.custGender.trim())){
						pass &= false;
						logger.error("Customer gender value "+newCust.custGender+" error");
					}
				}
				if(changeHis.field.trim().equals("Status") && changeHis.newValue.contains("active")) {
					if(!changeHis.oldValue.trim().equals(oldCust.status.trim())|| !changeHis.newValue.trim().equals(newCust.status.trim())){
						pass &= false;
						logger.error("Customer status value "+newCust.status+" error");
					}
				}	
				if(changeHis.field.trim().equals("Date Of Birth")){
					int oldValue = DateFunctions.compareDates(DateFunctions.formatDate(changeHis.oldValue.trim(), "E MMM dd yyyy"), DateFunctions.formatDate(oldCust.dateOfBirth, "E MMM dd yyyy"));
					int newValue = DateFunctions.compareDates(DateFunctions.formatDate(changeHis.newValue.trim(), "E MMM dd yyyy"), DateFunctions.formatDate(newCust.dateOfBirth, "E MMM dd yyyy"));
					if(oldValue !=0 || newValue!=0){
						pass &= false;
						logger.error("Customer status value "+newCust.status+" error");
					}
				}
				
				if(changeHis.field.trim().equals("Email")){
					if(!changeHis.oldValue.trim().equals(oldCust.email.trim())|| !changeHis.newValue.trim().equals(newCust.email.trim())){
						pass &= false;
						logger.error("Email "+newCust.email+" error");
					}
				}
				
				if(changeHis.field.trim().equals("First Name")){
					if(!changeHis.oldValue.trim().equals(oldCust.fName.trim())|| !changeHis.newValue.trim().equals(newCust.fName.trim())){
						pass &= false;
						logger.error("First name "+newCust.fName+" error");
					}
				}
				
				if(changeHis.field.trim().equals("Last Name")){
					if(!changeHis.oldValue.trim().equals(oldCust.lName.trim())|| !changeHis.newValue.trim().equals(newCust.lName.trim())){
						pass &= false;
						logger.error("Last name "+newCust.lName+" error");
					}
				}
				
				if(changeHis.field.trim().equals("Middle Name")){
					if(!changeHis.oldValue.trim().equals(oldCust.mName.trim())|| !changeHis.newValue.trim().equals(newCust.mName.trim())){
						pass &= false;
						logger.error("Middle name "+newCust.mName+" error");
					}
				}
				if(changeHis.field.trim().equals("Home Phone")){
					if(!changeHis.oldValue.trim().equals(oldCust.hPhone.trim())|| !changeHis.newValue.trim().equals(newCust.hPhone.trim())){
						pass &= false;
						logger.error("Home phone "+newCust.hPhone+" error");
					}
				}
				
				if(changeHis.field.trim().equals("Business Phone")){
					if(!changeHis.oldValue.trim().equals(oldCust.bPhone.trim())|| !changeHis.newValue.trim().equals(newCust.bPhone.trim())){
						pass &= false;
						logger.error("Busniess phone "+newCust.bPhone+" error");
					}
				}
				
				if(changeHis.field.trim().equals("Mobile Phone")){
					if(!changeHis.oldValue.trim().equals(oldCust.mPhone.trim())|| !changeHis.newValue.trim().equals(newCust.mPhone.trim())){
						pass &= false;
						logger.error("Mobile phone "+newCust.mPhone+" error");
					}
				}	
				if(!changeHis.user.replaceAll(", ", ",").equals(newCust.creationUser)){
					pass &= false;
					logger.error("User "+newCust.creationUser+" error. Expected value is: " + newCust.creationUser + ", but actual value is: " + changeHis.user);
				}
			}else{
				if(!changeHis.user.replaceAll(", ", ",").equals(oldCust.creationUser)){
					pass &= false;
					logger.error("User "+oldCust.creationUser+" error. Expected value is: " + oldCust.creationUser + ", but actual value is: " + changeHis.user);
				}
			}
			if(!changeHis.location.equals(location)){
				pass &= false;
				logger.error("Location "+location+" error");
			}
			if(!pass){
				throw new ErrorOnPageException("Change history record error. Please refer log for details info.");
			}	
		}
	}
}
