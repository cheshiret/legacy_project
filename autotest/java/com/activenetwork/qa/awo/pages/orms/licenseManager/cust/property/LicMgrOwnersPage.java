package com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.cust.OwnerAttr;
//import com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.marina.slip.batch.RegularCheckIn;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.datacollection.Data;
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
 * @author SWang
 * @Date  Jan 20, 2014
 */
public class LicMgrOwnersPage extends LicMgrPropertyDetailsPage{
	static class SingletonHolder {
		protected static LicMgrOwnersPage _instance = new LicMgrOwnersPage();
	}

	protected LicMgrOwnersPage() {}

	public static LicMgrOwnersPage getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] ownersGridTable(){
		return Property.concatPropertyArray(table(), ".id", "OwnersGrid");
	}

	protected Property[] addOwner(){
		return Property.concatPropertyArray(a(), ".text", "Add Owner");
	}

	protected Property[] go(){
		return Property.concatPropertyArray(a(), ".text", "Go");
	}
	
	protected Property[] ownerID(String ownerID){
		return Property.concatPropertyArray(a(), ".text", ownerID);
	}
	
	protected Property[] ownerStatus(){
		return Property.toPropertyArray(".id", new RegularExpression("LandPropertySearchCriteria-\\d+\\.status", false));
	}
	/** Page Object Property Definition End */

	public boolean exists() {
		return browser.checkHtmlObjectExists(ownersGridTable());
	}

	public void clickAddOwner(){
		browser.clickGuiObject(addOwner());
	}

	public void clickGo(){
		browser.clickGuiObject(go());
	}
	
	public void clickOwnerID(String ownerID){
		browser.clickGuiObject(ownerID(ownerID));
	}

	public void selectOwnerStatus(String ownerStatus){
		browser.selectDropdownList(ownerStatus(), ownerStatus);
	}
	public List<Data<OwnerAttr>> getOwnerData() {
		List<Data<OwnerAttr>> list = new ArrayList<Data<OwnerAttr>>();
		IHtmlObject[] objs = browser.getHtmlObject(ownersGridTable());
		if(objs.length>0){
			IHtmlTable table = (IHtmlTable) objs[0];
			for (int i = 1; i < table.rowCount(); i++) {
				Data<OwnerAttr> oa = new Data<OwnerAttr>();
				oa.put(OwnerAttr.ownerID, table.getCellValue(i, 0).trim());
				oa.put(OwnerAttr.ownershipStatus, table.getCellValue(i, 1).trim());
				oa.put(OwnerAttr.cust, table.getCellValue(i, 2));
				oa.put(OwnerAttr.lastName, oa.stringValue(OwnerAttr.cust).split(",")[0].trim());
				oa.put(OwnerAttr.custID, oa.stringValue(OwnerAttr.cust).split(" ")[1].trim());
				oa.put(OwnerAttr.firstName, oa.stringValue(OwnerAttr.cust).split(",")[1].split(oa.stringValue(OwnerAttr.custID))[0].trim());
				oa.put(OwnerAttr.typeOfOwnership, table.getCellValue(i, 3).trim());
				oa.put(OwnerAttr.yearOwned, table.getCellValue(i, 4).trim());
				list.add(oa);
			}
			Browser.unregister(table);
			Browser.unregister(objs);
			return list;
		}else throw new ObjectNotFoundException("Can't find owner grid table.");
	}
	
	public boolean isOwnerExisted(Data<OwnerAttr> owner){
		List<Data<OwnerAttr>> ownerList = getOwnerData();
		boolean existed=false;
		for(int i=0; i<ownerList.size(); i++){
			existed = true;
			existed &= owner.stringValue(OwnerAttr.ownerID).equals(ownerList.get(i).stringValue(OwnerAttr.ownerID));
			existed &= owner.stringValue(OwnerAttr.ownershipStatus).equals(ownerList.get(i).stringValue(OwnerAttr.ownershipStatus));
			existed &= owner.stringValue(OwnerAttr.lastName).equals(ownerList.get(i).stringValue(OwnerAttr.lastName));
			existed &= owner.stringValue(OwnerAttr.firstName).equals(ownerList.get(i).stringValue(OwnerAttr.firstName));
			existed &= owner.stringValue(OwnerAttr.custID).equals(ownerList.get(i).stringValue(OwnerAttr.custID));
			existed &= owner.stringValue(OwnerAttr.typeOfOwnership).equals(ownerList.get(i).stringValue(OwnerAttr.typeOfOwnership));
			existed &= owner.stringValue(OwnerAttr.yearOwned).equals(ownerList.get(i).stringValue(OwnerAttr.yearOwned));
			if(existed)
				break;
		}
		return existed;
	}

	public void verifyOwnerExisted(Data<OwnerAttr> owner, boolean existed){
		boolean resultFromUI = isOwnerExisted(owner);
		if(resultFromUI!=existed){
			throw new ErrorOnPageException("Owner which id="+owner.stringValue(OwnerAttr.ownerID)+" should "+(existed?"":"not ")+"exist.");
		}
		logger.info("Successfully verify owner which id="+owner.stringValue(OwnerAttr.ownerID)+(existed?" exists":" doesn't exist "));
	}
	
	public void verifyOwnerInfo(List<Data<OwnerAttr>> ownerData) {
		List<Data<OwnerAttr>> oas = this.getOwnerData();
		boolean result = MiscFunctions.compareResult("Size of list", ownerData.size(), oas.size());
		if(!result){
			throw new ErrorOnPageException("The size of list is different.");
		}else{
			for(int i=0; i<ownerData.size(); i++){
				result &= MiscFunctions.compareResult(i+"-Owner ID", ownerData.get(i).stringValue(OwnerAttr.ownerID), oas.get(i).stringValue(OwnerAttr.ownerID));
				result &= MiscFunctions.compareResult(i+"-Ownership Status", ownerData.get(i).stringValue(OwnerAttr.ownershipStatus), oas.get(i).stringValue(OwnerAttr.ownershipStatus));
				result &= MiscFunctions.compareResult(i+"-Customer Last Name", ownerData.get(i).stringValue(OwnerAttr.lastName), oas.get(i).stringValue(OwnerAttr.lastName));
				result &= MiscFunctions.compareResult(i+"-Customer First Name", ownerData.get(i).stringValue(OwnerAttr.firstName), oas.get(i).stringValue(OwnerAttr.firstName));
				result &= MiscFunctions.compareResult(i+"-Customer ID", ownerData.get(i).stringValue(OwnerAttr.custID), oas.get(i).stringValue(OwnerAttr.custID));
				result &= MiscFunctions.compareResult(i+"-Type of Ownership", ownerData.get(i).stringValue(OwnerAttr.typeOfOwnership), oas.get(i).stringValue(OwnerAttr.typeOfOwnership));
				result &= MiscFunctions.compareResult(i+"-year Owned", ownerData.get(i).stringValue(OwnerAttr.yearOwned), oas.get(i).stringValue(OwnerAttr.yearOwned));
			}
			if(!result){
				throw new ErrorOnPageException("Not all check points are passed in Owner list page. Please check the details from previous logs.");
			}
			logger.info("All check points are passed in Owner list page.");
		}
	}
	
	public void filterOwner(String ownerStatus){
		selectOwnerStatus(ownerStatus);
		clickGo();
		ajax.waitLoading();
	}
}
