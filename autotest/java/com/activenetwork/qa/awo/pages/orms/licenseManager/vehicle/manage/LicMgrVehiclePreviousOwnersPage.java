package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OwnerInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
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
public class LicMgrVehiclePreviousOwnersPage extends LicMgrVehicleDetailPage{
	
	private static LicMgrVehiclePreviousOwnersPage _instance = null;
	
	protected LicMgrVehiclePreviousOwnersPage(){
		
	}
	
	public static LicMgrVehiclePreviousOwnersPage getInstance(){
		if(_instance == null){
			_instance = new LicMgrVehiclePreviousOwnersPage();
		}
		return _instance;
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(".class","Html.TABLE",".text",new RegularExpression("^MDWFP # Customer Status First Name.*",false));
	}
	
	public List<OwnerInfo> getPreviousOwners(){
		IHtmlObject[] objs = browser.getTableTestObject(".text",new RegularExpression("^MDWFP # Customer Status First Name.*",false));
		IHtmlTable grid = (IHtmlTable)objs[1];

		OwnerInfo owner;
		List<OwnerInfo> owners = new ArrayList<OwnerInfo>();
		
		for(int i=1;i<grid.rowCount();i++){
			owner = new OwnerInfo();
			owner.mdwfpNum = grid.getCellValue(i, 0);
			owner.customerStatus = grid.getCellValue(i, 1);
			owner.firstName = grid.getCellValue(i, 2);
			owner.midName = grid.getCellValue(i, 3);
			owner.lastName = grid.getCellValue(i, 4);
			owner.dateOfBirth = grid.getCellValue(i, 5);
			owner.businessName = grid.getCellValue(i, 6);
			owner.ownerFromDate = grid.getCellValue(i, 7);
			owner.ownerToDate = grid.getCellValue(i, 8);
			owners.add(owner);
		}
		Browser.unregister(objs);
		return owners;
	}
	/**
	 * get previous owner row info.
	 * @param custNum - the customer number.
	 * @return
	 */
	public OwnerInfo getPreOwnerRowInfoByCustNum(String custNum){
		IHtmlObject[] objs = browser.getTableTestObject(".text",new RegularExpression("^MDWFP # Customer Status First Name.*",false));
		IHtmlTable grid = (IHtmlTable)objs[1];
		int row = grid.findRow(0, custNum);
		OwnerInfo owner = new OwnerInfo();
		if(row > 0){
			List<String> list = grid.getRowValues(row);
			owner.mdwfpNum = list.get(0);
			owner.customerStatus =list.get(1);
			owner.firstName = list.get(2);
			owner.midName = list.get(3);
			owner.lastName = list.get(4);
			owner.dateOfBirth = list.get(5);
			owner.businessName = list.get(6);
			owner.ownerFromDate =list.get(7);
			owner.ownerToDate = list.get(8);
		}else{
			throw new ErrorOnDataException("the "+custNum+" can't have the owner info");
		}
		
		Browser.unregister(objs);
		return owner;
	}
	/**
	 * compare previous owners info.
	 * @param preCust
	 * @return
	 */
	
	public boolean comparePreOwnersInfo(Customer preCust){
		boolean isEqual = true;
		OwnerInfo ownerInfo= this.getPreOwnerRowInfoByCustNum(preCust.custNum);
		if(null !=ownerInfo ){
			isEqual &= MiscFunctions.compareResult("privious owner number",preCust.custNum,  ownerInfo.mdwfpNum);
			isEqual &= MiscFunctions.compareResult("privious owner cusomer status",preCust.status,ownerInfo.customerStatus);
			isEqual &= MiscFunctions.compareResult("owner cusomer first name ",preCust.fName,ownerInfo.firstName);
			isEqual &= MiscFunctions.compareResult("privious owner cusomer last name",preCust.lName,ownerInfo.lastName);
			isEqual &= MiscFunctions.compareResult("privious owner cusomer date Of Birth",DateFunctions.formatDate(preCust.dateOfBirth, "MMM dd, yyyy"),DateFunctions.formatDate(ownerInfo.dateOfBirth, "MMM dd, yyyy"));
			isEqual &= MiscFunctions.compareResult("privious owner cusomer business name",preCust.businessName,ownerInfo.businessName);
			isEqual &= MiscFunctions.compareResult("privious owner from date",DateFunctions.formatDate(preCust.ownerFromDate, "MMM dd, yyyy"),DateFunctions.formatDate(ownerInfo.ownerFromDate, "MMM dd, yyyy"));
			isEqual &= MiscFunctions.compareResult("privious owner to date",DateFunctions.formatDate(preCust.ownerToDate, "MMM dd, yyyy"),DateFunctions.formatDate(ownerInfo.ownerToDate, "MMM dd, yyyy"));
		}
		return isEqual;
	}
}
