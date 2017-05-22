package com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.cust.PropertyAttr;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
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
 * @Date  Jan 14, 2014
 */
public class LicMgrPropertyListPage extends LicMgrCustomerDetailsPage{
	static class SingletonHolder {
		protected static LicMgrPropertyListPage _instance = new LicMgrPropertyListPage();
	}

	protected LicMgrPropertyListPage() {}

	public static LicMgrPropertyListPage getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	public Property[] custPropertyAssignmentTabTable(){
		return Property.concatPropertyArray(table(), ".id", "CustomerPropertyAssignmentTab_table");
	}

	public Property[] custPropertyAssignmentGridTable(){
		return Property.concatPropertyArray(table(), ".id", new RegularExpression("grid_\\d+", false), ".className", "table table-striped gridView");
	}

	public Property[] propertyID(String propertyID){
		return Property.concatPropertyArray(a(), ".text", propertyID);
	}

	public Property[] propertyID(){
		return Property.concatPropertyArray(a(), ".text", new RegularExpression("\\d+", false));
	}
	
	public Property[] addProperty(){
		return Property.concatPropertyArray(a(), ".text", "Add Property");
	}

	public Property[] go(){
		return Property.concatPropertyArray(a(), ".text", "Go");
	}
	/** Page Object Property Definition End */

	public boolean exists() {
		return browser.checkHtmlObjectExists(custPropertyAssignmentTabTable());
	}

	public void clickAddProperty(){
		browser.clickGuiObject(Property.atList(custPropertyAssignmentTabTable(), addProperty()));
	}

	public void clickPropertyID(String propertyID){
		browser.clickGuiObject(propertyID(propertyID));
	}
   
	public String getFirstPropertyID(){
		return browser.getObjectText(Property.atList(custPropertyAssignmentTabTable(), propertyID()));
	}
	
	public void clickGo(){
		browser.clickGuiObject(Property.atList(custPropertyAssignmentTabTable(), go()));
	}

	public boolean isPropertyExisted(Data<PropertyAttr> cpa){
		List<Data<PropertyAttr>> cpas = getCustPropertyData();
		boolean existed = false;
		for(int i=0; i<cpas.size(); i++){
			if(cpa.stringValue(PropertyAttr.ownershipStatus).equals(cpas.get(i).stringValue(PropertyAttr.ownershipStatus)) &&
					cpa.stringValue(PropertyAttr.propertyCounty).equals(cpas.get(i).stringValue(PropertyAttr.propertyCounty)) &&
					cpa.stringValue(PropertyAttr.propertyAcres).equals(cpas.get(i).stringValue(PropertyAttr.propertyAcres)) &&
					cpa.stringValue(PropertyAttr.typeOfOwnership).equals(cpas.get(i).stringValue(PropertyAttr.typeOfOwnership)) &&
					cpa.stringValue(PropertyAttr.yearOwned).equals(cpas.get(i).stringValue(PropertyAttr.yearOwned))){
				existed = true;
				break;
			}
		}

		return existed;
	}

	public void verifyPropertyExisted(Data<PropertyAttr> cpa, boolean existed){
		String propertyinfo = "Ownership status="+cpa.stringValue(PropertyAttr.ownershipStatus)+", " +
				"County="+cpa.stringValue(PropertyAttr.propertyCounty)+", " +
				"Acres="+cpa.stringValue(PropertyAttr.typeOfOwnership)+", " +
				"Year owned="+cpa.stringValue(PropertyAttr.yearOwned);
		if(isPropertyExisted(cpa)!=existed){
			throw new ErrorOnPageException("Property should "+(existed?"":"not ")+"existed which "+propertyinfo);
		}
		logger.info("Property "+(existed?"exists":"doesn't exist ")+"which "+propertyinfo);
	}

	public int getNumOfProperty(){
		IHtmlObject[] objs = getPropertyAssignmentGrid();
		IHtmlTable table = (IHtmlTable) objs[0];
		int num = table.rowCount()-1;
		Browser.unregister(table);
		Browser.unregister(objs);
		return num;
	}

	public IHtmlObject[] getPropertyAssignmentGrid(){
		IHtmlObject[] objs = browser.getHtmlObject(custPropertyAssignmentGridTable());
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find customer property assignment grid table.");
		}
		return objs;
	}

	public List<Data<PropertyAttr>> getCustPropertyData() {
		List<Data<PropertyAttr>> list = new ArrayList<Data<PropertyAttr>>();
		IHtmlObject[] objs = getPropertyAssignmentGrid();
		IHtmlTable table = (IHtmlTable) objs[0];
		for (int i = 1; i < table.rowCount(); i++) {
			Data<PropertyAttr> cpa = new Data<PropertyAttr>();
			cpa.put(PropertyAttr.propertyID, table.getCellValue(i, 0).trim());
			cpa.put(PropertyAttr.ownershipStatus, table.getCellValue(i, 1).trim());
			cpa.put(PropertyAttr.propertyCounty, table.getCellValue(i, 2).trim());
			cpa.put(PropertyAttr.propertyAcres, table.getCellValue(i, 3).trim());
			cpa.put(PropertyAttr.typeOfOwnership, table.getCellValue(i, 4).trim());
			cpa.put(PropertyAttr.yearOwned, table.getCellValue(i, 5).trim());
			list.add(cpa);
		}
		Browser.unregister(table);
		Browser.unregister(objs);
		return list;
	}

	public void verifyCustPropertyInfo(List<Data<PropertyAttr>> propertyData) {
		List<Data<PropertyAttr>> cpas = this.getCustPropertyData();
		boolean result = MiscFunctions.compareResult("Size of list", propertyData.size(), cpas.size());
		if(!result){
			throw new ErrorOnPageException("The size of list is different.");
		}else{
			for(int i=0; i<propertyData.size(); i++){
				result &= MiscFunctions.compareResult(i+"-Property ID", propertyData.get(i).stringValue(PropertyAttr.propertyID), cpas.get(i).stringValue(PropertyAttr.propertyID));
				result &= MiscFunctions.compareResult(i+"-Ownership Status", propertyData.get(i).stringValue(PropertyAttr.ownershipStatus), cpas.get(i).stringValue(PropertyAttr.ownershipStatus));
				result &= MiscFunctions.compareResult(i+"-Property County", propertyData.get(i).stringValue(PropertyAttr.propertyCounty), cpas.get(i).stringValue(PropertyAttr.propertyCounty));
				result &= MiscFunctions.compareResult(i+"-Property Acres", propertyData.get(i).stringValue(PropertyAttr.propertyAcres), cpas.get(i).stringValue(PropertyAttr.propertyAcres));
				result &= MiscFunctions.compareResult(i+"-Type of Ownership", propertyData.get(i).stringValue(PropertyAttr.typeOfOwnership), cpas.get(i).stringValue(PropertyAttr.typeOfOwnership));
				result &= MiscFunctions.compareResult(i+"-year Owned", propertyData.get(i).stringValue(PropertyAttr.yearOwned), cpas.get(i).stringValue(PropertyAttr.yearOwned));
			}
			if(!result){
				throw new ErrorOnPageException("Not all check points are passed in Customer Property list page. Please check the details from previous logs.");
			}
			logger.info("All check points are passed in Customer Property list page.");
		}
	}
}
