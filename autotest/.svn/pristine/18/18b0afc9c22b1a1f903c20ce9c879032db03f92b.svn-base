/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @ScriptName LicMgrVehiclesPage.java
 * @Date:Mar 21, 2011
 * @Description:
 * @author asun
 */
public class LicMgrVehiclesListPage extends LicMgrProductPage {
	
	private static LicMgrVehiclesListPage instance=null;
	private final RegularExpression vTypePattern=new RegularExpression("VehicleRTIUISearchCriteria-\\d+\\.vehicleTypes_\\d+",false);;
	private final RegularExpression vGroupPattern=new RegularExpression("VehicleRTIUISearchCriteria-\\d+\\.productGroups_\\d+",false);;
	private final RegularExpression vStatusPattern=new RegularExpression("VehicleRTIUISearchCriteria-\\d+\\.statuses_\\d+",false);;
	private LicMgrVehiclesListPage(){}
	
	public static LicMgrVehiclesListPage getInstance(){
		if(instance == null){
			instance = new LicMgrVehiclesListPage();
		}
		return instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE",".id","vehiclertilist");
	}
	
	/**Click 'Add Vehicle Product' button*/
	public void clickAddVehicleProduct(){
		browser.clickGuiObject(".class", "Html.A",".text","Add Vehicle Product");
	}
	
	/**click vehicle code*/
	public void clickVehicleCode(String code){
		browser.clickGuiObject(".class", "Html.A", ".text", code, true);
		ajax.waitLoading();
	}
	
	/**select active status*/
	public void selectActiveStatus(){
		browser.selectCheckBox(".id", vStatusPattern,0);
	}
	
	/**unSelect active status*/
	public void unSelectActiveStatus(){
		browser.unSelectCheckBox(".id", vStatusPattern,0);
	}
	
	/**is the status checkbox selected accordind to the index**/
	public boolean isStstusSelected(int index){
		boolean flag=false;
		IHtmlObject[] objs=browser.getCheckBox(".id", vStatusPattern);
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find vehicle status checkbox");
		}
		ICheckBox cb=(ICheckBox)objs[index];
		flag=cb.isSelected();
		Browser.unregister(objs);
		return flag;
	}
	
	/**is the status checkbox selected------Active**/
	public boolean isStatusSelected_Active(){
		return isStstusSelected(0);
	}
	
	/**is the status checkbox selected------InActive***/
	public boolean isStatusSelected_InActive(){
		return isStstusSelected(1);
	}
	
	/**select Inactive status*/
	public void selectInactiveStatus(){
		browser.selectCheckBox(".id", vStatusPattern,1);
	}
	
	/**select Inactive status*/
	public void unSelectInactiveStatus(){
		browser.unSelectCheckBox(".id", vStatusPattern,1);
	}
	
	/**select group type by index*/
	public void selectProGroup(int index){
		browser.selectCheckBox(".id", vGroupPattern,index);
	}
	
	/**unSelect group type by index*/
	public void unSelectProGroup(int index){
		browser.unSelectCheckBox(".id", vGroupPattern,index);
	}
	
	/**
	 * is group check box selected for specified index
	 * @param index
	 * @Return boolean
	 */
	public boolean isGroupSelected(int index){
		boolean flag=false;
		IHtmlObject[] objs=browser.getCheckBox(".id", vGroupPattern);
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find vehicle group checkbox");
		}
		ICheckBox cb=(ICheckBox)objs[index];
		flag=cb.isSelected();
		Browser.unregister(objs);
		return flag;
	}
	
	/**
	 * is group check box selected --------Registration
	 * @Return boolean
	 */
	public boolean isGroupSelected_Registration(){
		return isGroupSelected(0);
	}
	
	/**
	 * is group check box selected --------Title
	 * @Return boolean
	 */
	public boolean isGroupSelected_Title(){
		return isGroupSelected(1);
	}
	
	/**
	 * is group check box selected --------Inspection
	 * @Return boolean
	 */
	public boolean isGroupSelected_Inspection(){
		return isGroupSelected(2);
	}
	
	/**select group type:Registration*/
	public void selectProGroup_Registration(){
		this.selectProGroup(0);
	}
	
	/**unSelect group type:Registration*/
	public void unSelectProGroup_Registration(){
		this.unSelectProGroup(0);
	}
	
	/**select group type:Title */
	public void selectProGroup_Title(){
		this.selectProGroup(1);
	}
	
	/**unSelect group type:Title */
	public void unSelectProGroup_Title(){
		this.unSelectProGroup(1);
	}
	
	/**select group type: Inspection */
	public void selectProGroup_Inspection(){
		this.selectProGroup(2);
	}
	
	/**unSelect group type: Inspection */
	public void unSelectProGroup_Inspection(){
		this.unSelectProGroup(2);
	}
	
	/**select vehicle type by index*/
	public void selectVehicleType(int index){
		browser.selectCheckBox(".id", vTypePattern,index);
	}
	
	/**select vehicle type by index*/
	public void unSelectVehicleType(int index){
		browser.unSelectCheckBox(".id", vTypePattern,index);
	}
	
	/**
	 * get vehicle type check box status by index
	 * @param index
	 * @return
	 * @Return boolean
	 * @Throws
	 */
//	public boolean isVehicleTypeSelected(int index){
//		boolean flag=false;
//		HtmlObject[] objs=browser.getCheckBox(".id", vTypePattern);
//		if(objs.length<1){
//			throw new ObjectNotFoundException("Can't find vehicle type checkbox");
//		}
//		ICheckBox cb=(ICheckBox)objs[index];
//		flag=cb.isSelected();
//		Browser.unregister(objs);
//		return flag;
//	}
	/**
	 * get vehicle type status-----------Boat
	 * @Return boolean
	 * @Throws
	 */
	public boolean isVehicleTypSelected_Boat(){
		return isVehicleTypeSelecte("Boat");
	}
	
	/**
	 * get vehicle type status-----------Motor
	 * @Return boolean
	 * @Throws
	 */
	public boolean isVehicleTypSelected_Motor(){
		return isVehicleTypeSelecte("Motor");
	}
	
	/**
	 * get vehicle type status-----------Dealer
	 * @Return boolean
	 * @Throws
	 */
	public boolean isVehicleTypSelected_Dealer(){
		return isVehicleTypeSelecte("Dealer");
	}

	private IHtmlObject[] getVehicleTypeObject(String type){
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.Label", ".text", type);
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find Vehicle Type object.");
		}
		String forValue = objs[0].getAttributeValue("for");
		
		return browser.getCheckBox(".id",forValue);
	}
	
	private boolean isVehicleTypeSelecte(String type){
		IHtmlObject objs[] = this.getVehicleTypeObject(type);
		ICheckBox cb=(ICheckBox)objs[0];
		boolean flag=cb.isSelected();
		Browser.unregister(objs);
		return flag;
	}
	
	private void selectOrUnselectVehicleType(String type, boolean isSelect){
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.Label", ".text", type);
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find Vehicle Type object.");
		}
		String id = objs[0].getAttributeValue("for");
		if(isSelect){
			browser.selectCheckBox(".id", id);
		} else {
			browser.unSelectCheckBox(".id", id);
		}
		Browser.unregister(objs);
	}
	
	private void selectVehicleType(String type){
		this.selectOrUnselectVehicleType(type, true);
	}
	
	private void unselectVehicleType(String type){
		this.selectOrUnselectVehicleType(type, false);
	}
	
	public void selectVehicleTyp_Boat(){
		this.selectVehicleType("Boat");
	}
	
	public void unSelectVehicleTyp_Boat(){
		this.unselectVehicleType("Boat");
	}
	
	public void selectVehicleTyp_Motor(){
		this.selectVehicleType("Motor");
	}	
	
	public void unSelectVehicleTyp_Motor(){
		this.unselectVehicleType("Motor");
	}
	
	public void selectVehicleTyp_Dealer(){
		this.selectVehicleType("Dealer");
	}
	
	public void unSelectVehicleTyp_Dealer(){
		this.unselectVehicleType("Dealer");
	}
	
	
//	/**select vehicle type:Boat*/
//	public void selectVehicleTyp_Boat(){
//		this.selectVehicleType(0);
//	}
//	
//	/**unSelect vehicle type:Boat*/
//	public void unSelectVehicleTyp_Boat(){
//		this.unSelectVehicleType(0);
//	}
//	
//	/**Select vehicle type:Motor*/
//	public void selectVehicleTyp_Motor(){
//		this.selectVehicleType(1);
//	}
//	
//	/**unSelect vehicle type:Motor*/
//	public void unSelectVehicleTyp_Motor(){
//		this.unSelectVehicleType(1);
//	}
//	
//	/**select vehicle type:Dealer*/
//	public void selectVehicleTyp_Dealer(){
//		this.selectVehicleType(2);
//	}
//	
//	/**unSelect vehicle type:Dealer*/
//	public void unSelectVehicleTyp_Dealer(){
//		this.unSelectVehicleType(2);
//	}
	
	
	/**click 'Go'*/
	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A",".text","Go");
		ajax.waitLoading();
	}
	
	/**check if the Vehicle which code is specified is existing */
	public boolean isThisVehicleExist(String code){
		return browser.checkHtmlObjectExists(".class", "Html.A",".text",code);
	}
	
	/**get the 1st VehicleInfo*/
	public VehicleRTI getTheFirstVehicle(){
		VehicleRTI vehicle=new VehicleRTI();
		
		IHtmlObject[] objs=browser.getTableTestObject(".id", "vehiclertilist");
		
		if(objs.length<1){
			throw new ObjectNotFoundException("VehicleListTable is not found.");
		}
		
		IHtmlTable table=(IHtmlTable)objs[0];
		if(table.rowCount()<2){
			throw new ErrorOnDataException("there is no vehicle product.");
		}
		vehicle.setPrdCode(table.getCellValue(2, 0).trim());
		vehicle.setPrdName(table.getCellValue(2, 1).trim());
		vehicle.setStatus(table.getCellValue(2, 2).trim());
		vehicle.setVehicleType(table.getCellValue(2, 3).trim());
		
		return vehicle;
	}
	
	public boolean checkProductRecordExist(String prodCode) {
		IHtmlObject[] objs=browser.getTableTestObject(".id", "vehiclertilist");
		IHtmlTable table=(IHtmlTable)objs[0];
		int codeRow=table.findRow(0, prodCode);
		
		Browser.unregister(objs);
		
		return codeRow > 0;
	}
	
	/**
	 * get group from page by vehicle code
	 * @param code
	 * @Return String
	 */
	public String getGroupFromPage(String code){
		IHtmlObject[] objs=browser.getTableTestObject(".id", "vehiclertilist");
		String group="";
		if(objs.length<1){
			throw new ObjectNotFoundException("vehicle product list is not found.");
		}
		IHtmlTable table=(IHtmlTable)objs[0];
		int codeRow=table.findRow(0, code);
		
		while(codeRow>0){
			codeRow--;
			group=table.getCellValue(codeRow, 0);
			if(group.trim().equals("Registration")
					||group.equalsIgnoreCase("Title")
					||group.equalsIgnoreCase("Inspection")){
				break;
			}
		}
		
		Browser.unregister(objs);
		return group;
	}
	/**
	 * get vehicle row by code
	 * @param code
	 * @Return int
	 */
	public int getVehicleProductRow(String code){
		int row=0;
		IHtmlObject[] objs=browser.getTableTestObject(".id", "vehiclertilist");
		if(objs.length<1){
			throw new ObjectNotFoundException("vehicle product list is not found.");
		}
		IHtmlTable table=(IHtmlTable)objs[0];
	    row=table.findRow(0, code);
	    Browser.unregister(objs);
		return row;
	}
	
	/**
	 * Get column by column name
	 * @param columnName
	 * @return int
	 */
	public int getColumnByName(String columnName){
		int col = 0;
		IHtmlObject[] objs=browser.getTableTestObject(".id", "vehiclertilist");
		if(objs.length<1){
			throw new ObjectNotFoundException("vehicle product list is not found.");
		}
		IHtmlTable table=(IHtmlTable)objs[0];
		col = table.findColumn(0, columnName);
		Browser.unregister(objs);
		return col;
	}
	
	/**
	 * Get vehicle info by vehicle code
	 * @param code
	 * @return vehicle info
	 */
	public VehicleRTI getVehicleInfoByVehicleCode(String code){
		VehicleRTI vehicle = new VehicleRTI();
		
		IHtmlObject[] objs=browser.getTableTestObject(".id", "vehiclertilist");
		
		if(objs.length<1){
			throw new ObjectNotFoundException("VehicleListTable is not found.");
		}
		IHtmlTable table=(IHtmlTable)objs[0];
		
		int row = this.getVehicleProductRow(code);
		vehicle.setPrdCode(table.getCellValue(row, this.getColumnByName("Code")));
		vehicle.setPrdName(table.getCellValue(row, this.getColumnByName("Name")));
		vehicle.setStatus(table.getCellValue(row, this.getColumnByName("Status")));
		vehicle.setVehicleType(table.getCellValue(row, this.getColumnByName("Vehicle Type")));
		
		Browser.unregister(objs);		
		
		return vehicle;		
	}
	
	/**
	 * get all vehicle info from current search result
	 * @Return List<VehicleProduct>
	 */
	public List<VehicleRTI> getVehicles(){
        List<VehicleRTI> vehicles=new ArrayList<VehicleRTI>();
		
		IHtmlObject[] objs=browser.getTableTestObject(".id", "vehiclertilist");
		
		if(objs.length<1){
			return vehicles;
		}
		IHtmlTable table=(IHtmlTable)objs[0];
		int rowCount=table.rowCount();
		
		if(rowCount<2){
			throw new ErrorOnDataException("there is no vehicle product.");
		}
		
		for(int i=0;i<rowCount;i++){
			if(table.getCellValue(i, 0).trim().length()>3)continue;
			VehicleRTI vehicle = new VehicleRTI();
			int codeColumn=table.findColumn(0, "Code");
			int nameColumn=table.findColumn(0, "Name");
			int statusColumn=table.findColumn(0, "Status");
			int typeColumn=table.findColumn(0, "Vehicle Type");
			vehicle.setPrdCode(table.getCellValue(i, codeColumn).trim());
			vehicle.setPrdName(table.getCellValue(i, nameColumn).trim());
			vehicle.setStatus(table.getCellValue(i, statusColumn).trim());
			vehicle.setVehicleType(table.getCellValue(i, typeColumn).trim());
			vehicles.add(vehicle);
		}
		Browser.unregister(objs);
		return vehicles;
	}
	
    /**
     * get all vehicle info from current search result according status 
     * @param status
     * @Return List<VehicleProduct>
     */
	public List<VehicleRTI> getVehicles(String status){
		 List<VehicleRTI> vehicles=this.getVehicles();
		 if(!status.equals("Active")&&!status.equals("Inactive")){
			 throw new ErrorOnDataException("Unknow vehicle type:"+status);
		 }
		 for(int i=0;i<vehicles.size();i++){
			 if(!vehicles.get(i).getStatus().equals(status)){
				 vehicles.remove(i);
				 i--;
			 }
			 
		 }
		 return vehicles;
	}
	/**
	 * set vehicle status
	 * @param statuses
	 * @Return void
	 * @Throws
	 */
	public void selectVehicleStatus(Map<String,Boolean> statuses){
		for (Map.Entry<String, Boolean> status : statuses.entrySet()) {
			if (status.getKey().equalsIgnoreCase("Active")) {
				if (status.getValue())
					this.selectActiveStatus();
				else
					this.unSelectActiveStatus();
			} else if (status.getKey().equalsIgnoreCase("InActive")) {
				if (status.getValue())
					this.selectInactiveStatus();
				else
					this.unSelectInactiveStatus();
			} else {
				throw new ErrorOnDataException("Unknown status");
			}
		}
	}
	
	/**
	 * set vehicle group
	 * @param groups
	 * @Return void
	 * @Throws
	 */
	public void selectVehicleGroup(Map<String, Boolean> groups){
		for (Map.Entry<String, Boolean> group : groups.entrySet()) {
			if (group.getKey().equalsIgnoreCase("registration")) {
				if (group.getValue())
					this.selectProGroup_Registration();
				else
					this.unSelectProGroup_Registration();

			} else if (group.getKey().equalsIgnoreCase("Title"))
				if (group.getValue())
					this.selectProGroup_Title();
				else
					this.unSelectProGroup_Title();
			else if (group.getKey().equalsIgnoreCase("Inspection")) {
				if (group.getValue())
					this.selectProGroup_Inspection();
				else
					this.unSelectProGroup_Inspection();

			} else
				throw new ErrorOnDataException("UNknown group");
		}
	}
	
	/**
	 * check vehicle info whether display in product group list
	 * @param code
	 * @param productGroup
	 * @return
	 */
	public boolean checkVehicleDisplayProductGroupIsCorrect(String code, String productGroup){
		boolean correct = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text",productGroup);
		
		if(objs.length<1){
			throw new ObjectNotFoundException(productGroup + " object did not found.");
		}
		
		String value = objs[0].getProperty(".id");	
		//get product code object in product group list
		IHtmlObject[] trObjs = browser.getHtmlObject(".class","Html.TR", ".className", 
				new RegularExpression(".*" + value + ".*",false));
		
		if(trObjs.length<1){
			throw new ObjectNotFoundException("Vehicle code " + code + " row object not found." );
		}
		
		Property[] p = new Property[2];
		p[0] = new Property(".class","Html.TD");
		p[1] = new Property(".text",code);
		for(IHtmlObject trObj : trObjs) {		
			if(correct = browser.checkHtmlObjectExists(p,trObj)) {
				break;
			}
		}
		
		Browser.unregister(trObjs);
		Browser.unregister(objs);
		
		return correct;
	}
	
	public List<String> getColumnValues(String clomunName){	
		int col = 0;
		List<String> columnValues = new ArrayList<String>();
		
		IHtmlObject[] objs=browser.getTableTestObject(".id", "vehiclertilist");
		if(objs.length<1){
			throw new ObjectNotFoundException("vehicle product list is not found.");
		}
		IHtmlTable table=(IHtmlTable)objs[0];
		col = table.findColumn(0, clomunName);		
		columnValues = table.getColumnValues(col);
		columnValues.remove(0);
		
		Browser.unregister(objs);
		return columnValues;		
	}
	
	public List<String> getProductGropListValues(){
		List<String> productGropListValues = new ArrayList<String>();
		Property[] p = new Property[2];
		p[0] = new Property(".class","Html.TR");
		p[1] = new Property(".className", "label_title labelgroup   initialized parent");
		
		IHtmlObject[] objs = browser.getHtmlObject(p);
		if(objs.length<1){
			throw new ObjectNotFoundException("vehicle product group list is not found.");
		}
		
		for(int i=0; i<objs.length; i++){
			productGropListValues.add(objs[i].text());
		} 
		
		Browser.unregister(objs);
		return productGropListValues;		
	}
	
	public List<String> getVehicleListName(){
		List<String> vehicleListName =  new ArrayList<String>();
		
		IHtmlObject[] objs = browser.getHtmlObject(".id", "vehiclertilist");
		if(objs.length<1){
			throw new ObjectNotFoundException("vehicle list table not found.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];		
		vehicleListName = table.getRowValues(0);
		
		Browser.unregister(objs);
		return vehicleListName;
	}
	
	/**
	 * set vehicle type
	 * @Return void 
	 * @Throws
	 */
	public void selectVehicleType(Map<String, Boolean> types){
		for (Map.Entry<String, Boolean> type : types.entrySet()) {
			if (type.getKey().equalsIgnoreCase("Boat")) {
				if (type.getValue())
					this.selectVehicleTyp_Boat();
				else
					this.unSelectVehicleTyp_Boat();
			} else if (type.getKey().equalsIgnoreCase("Motor")) {
				if (type.getValue())
					this.selectVehicleTyp_Motor();
				else
					this.unSelectVehicleTyp_Motor();
			} else if (type.getKey().equalsIgnoreCase("Dealer")) {
				if (type.getValue())
					this.selectVehicleTyp_Dealer();
				else
					this.unSelectVehicleTyp_Dealer();
			} else {
				throw new ErrorOnDataException("Unknown Type");
			}
		}
	}
}
