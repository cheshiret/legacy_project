package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OwnerInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @author ssong
 * @date Dec 6, 2011
 */
public class LicMgrVehicleViewPreviousCoOwnersWidget extends DialogWidget{
	private static LicMgrVehicleViewPreviousCoOwnersWidget _instance = null;
	
	protected LicMgrVehicleViewPreviousCoOwnersWidget(){
		super("View Previous Co-Owners");
	}
	
	public static LicMgrVehicleViewPreviousCoOwnersWidget getInstance(){
		if(_instance == null){
			_instance = new LicMgrVehicleViewPreviousCoOwnersWidget();
		}
		return _instance;
	}
	
	public List<OwnerInfo> getVehiclePreviousCoOwners(){
		IHtmlObject[] objs = browser.getTableTestObject(".text",new RegularExpression("^Co-Owner # First Name Middle Name.*",false));
		IHtmlTable grid = (IHtmlTable)objs[1];
		OwnerInfo owner;
		List<OwnerInfo> owners = new ArrayList<OwnerInfo>();
		
		for(int i=1;i<grid.rowCount();i++){
			owner = new OwnerInfo();
			owner.id = grid.getCellValue(i, 0);
			owner.firstName = grid.getCellValue(i, 1);
			owner.midName = grid.getCellValue(i, 2);
			owner.lastName = grid.getCellValue(i, 3);
			owner.dateOfBirth = grid.getCellValue(i, 4);
			owner.businessName = grid.getCellValue(i, 5);
			owner.identifierNum = grid.getCellValue(i, 6);
			owner.coOwnerFrom = grid.getCellValue(i, 7);
			owner.coOwnerTo = grid.getCellValue(i, 8);
			owners.add(owner);
		}
		Browser.unregister(objs);
		return owners;
	}
	
	public OwnerInfo getPreviousCoOwnerInfoById(String id){
		IHtmlObject[] objs = browser.getTableTestObject(".text",new RegularExpression("^Co-Owner # First Name Middle Name.*",false));
		IHtmlTable table = (IHtmlTable)objs[1];
		OwnerInfo owner = new OwnerInfo();
		
		for(int i=1;i<table.rowCount();i++){
			if(table.getCellValue(i, 0).equals(id)){
				owner.id = table.getCellValue(i, 0);
				owner.firstName = table.getCellValue(i, 1);
				owner.midName = table.getCellValue(i, 2);
				owner.lastName = table.getCellValue(i, 3);
				owner.dateOfBirth = table.getCellValue(i, 4);
				owner.businessName = table.getCellValue(i, 5);
				owner.identifierNum = table.getCellValue(i, 6);
				owner.coOwnerFrom = table.getCellValue(i, 7);
				owner.coOwnerTo = table.getCellValue(i, 8);
				break;
			}
		}
		Browser.unregister(objs);
		return owner;
	}
	
	public boolean compareCoOwnerDetailInfo(OwnerInfo ownerInfo, OwnerInfo ownerInfoUI){
		boolean flag = true;
		
		if (!ownerInfo.id.equals(ownerInfoUI.id)) {
			logger.error("Given coOwner ID is "+ownerInfo.id+" ,actual Id is "+ownerInfoUI.id);
			flag &= false;
		}
		if (!ownerInfo.firstName.equals(ownerInfoUI.firstName)) {
			logger.error("Given coOwner firstName is "+ownerInfo.firstName+" ,actual firstName is "+ownerInfoUI.firstName);
			flag &= false;
		}
		if (!ownerInfo.midName.equals(ownerInfoUI.midName)) {
			logger.error("Given coOwner midName is "+ownerInfo.midName+" ,actual midName is "+ownerInfoUI.midName);
			flag &= false;
		}
		if (!ownerInfo.lastName.equals(ownerInfoUI.lastName)) {
			logger.error("Given coOwner lastName is "+ownerInfo.lastName+" ,actual lastName is "+ownerInfoUI.lastName);
			flag &= false;
		}
		if (!ownerInfo.suffix.equals(ownerInfoUI.suffix)) {
			logger.error("Given coOwner suffix is "+ownerInfo.suffix+" ,actual suffix is "+ownerInfoUI.suffix);
			flag &= false;
		}
		if ("" != ownerInfo.dateOfBirth
			&& "" != ownerInfoUI.dateOfBirth
			&& (DateFunctions.compareDates(ownerInfo.dateOfBirth, ownerInfoUI.dateOfBirth)!=0)) {
			logger.error("Given coOwner dateOfBirth is "+ownerInfo.dateOfBirth+" ,actual dateOfBirth is "+ownerInfoUI.dateOfBirth);
			flag &= false;
		}
		if (!ownerInfo.businessName.equals(ownerInfoUI.businessName)) {
			logger.error("Given coOwner businessName is "+ownerInfo.businessName+" ,actual businessName is "+ownerInfoUI.businessName);
			flag &= false;
		}
		if("" != ownerInfo.identifierNum){
			if((ownerInfo.identifierType.equalsIgnoreCase("US Drivers License")
			   || ownerInfo.identifierType.equalsIgnoreCase("MS Drivers License"))
			   && !ownerInfoUI.identifierNum.equalsIgnoreCase(ownerInfo.identifierType+" "+ownerInfo.identifierNum)){
				logger.error("Given coOwner identifierNum is "+ownerInfo.identifierType+" "+ownerInfo.identifierNum+" ,actual businessName is "+ownerInfoUI.identifierNum);
				flag &= false;
			}else if(ownerInfo.identifierType.equalsIgnoreCase("Social Security Number")
				&& !ownerInfoUI.identifierNum.equalsIgnoreCase(ownerInfo.identifierType+" *********")){
				logger.error("Given coOwner identifierNum is "+ownerInfo.identifierType+" *********"+" ,actual businessName is "+ownerInfoUI.identifierNum);
				 flag &= false;
			}else{	
				throw new ErrorOnDataException("Unhandle identification type:"+ownerInfo.identifierType);
			}
		}
				
		return flag;
	}
	
	public void verifyCoOwnerNumDisplay(){
		IHtmlObject[] objs = browser.getTableTestObject(".text",new RegularExpression("^Co-Owner # First Name Middle Name.*",false));
		if(objs.length<1){
			throw new ErrorOnPageException("Could not find table with text 'Co-Owner # First Name Middle Name...'");
		}
		logger.info("Co-Owner # has displayed in Previous Co-Owner table");
		Browser.unregister(objs);
	}
	/**
	 * Verify coOwner exist in previous view.
	 * @param id
	 */
	public boolean checkCoOwnerExistInPreviousView(String id){
		IHtmlObject[] objs = browser.getTableTestObject(".text",new RegularExpression("^Co-Owner # First Name Middle Name.*",false));
		IHtmlTable grid = (IHtmlTable)objs[1];
		boolean isExist  = grid.getColumnValues(0).contains(id);
		Browser.unregister(objs);
		return isExist;
	}
	/**
	 * click OK.
	 */
	public void clickOK(){
		browser.clickGuiObject(".class","Html.A", ".text", "OK",1);
	}
}
