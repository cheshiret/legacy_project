package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Vehicle;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.MotorInfo;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA
 * @Date  Dec 17, 2011
 */
public class LicMgrVehiclesSearchPage extends LicMgrCommonTopMenuPage {

	private static LicMgrVehiclesSearchPage _instance = null;

	public static LicMgrVehiclesSearchPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrVehiclesSearchPage();
		}

		return _instance;
	}

	protected LicMgrVehiclesSearchPage() {
	}

	private String prefix = "VehicleSearchCriteria-\\d+\\.";
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id",new RegularExpression(prefix+"serialNum",false));
	}
	
	public void setMINum(String miNum){
		browser.setTextField(".id", new RegularExpression("vehicleNumber",false), miNum);
	}
	
	public void setSerialNum(String serialNum){
		browser.setTextField(".id", new RegularExpression("VehicleSearchCriteria-\\d+\\.serialNum",false), serialNum);
		
	}
	
	public void setVehicleType(String type){
		if(!"".equals(type)){
			browser.selectDropdownList(".id", new RegularExpression(prefix+"vehicleTypeID",false), type, true);
		} else {// when type is blank, select the first option
			browser.selectDropdownList(".id", new RegularExpression(prefix+"vehicleTypeID",false), 0);
		}
	}
	
	public void setVehicleStatus(String status){
		if(!"".equals(status)){
			browser.selectDropdownList(".id", new RegularExpression(prefix+"statusID",false), status, true);
		} else {// when status is blank, select the first option
			browser.selectDropdownList(".id", new RegularExpression(prefix+"statusID",false), 0);
		}
	}
	
	public void setManufacturer(String manufacturer){
		browser.setTextField(".id", new RegularExpression("VehicleSearchCriteria-\\d+\\.manufacturer",false), manufacturer);
	}
	
	public void setModelYear(String modelYear){
		browser.setTextField(".id", new RegularExpression("VehicleSearchCriteria-\\d+\\.modelYear",false), modelYear);
	}
	
	public void setTitleNum(String titleNum){
		browser.setTextField(".id", new RegularExpression(prefix+"titleNum",false), titleNum);
	}
	
	public void setVehicleSearchType(String searchType){
		if(!"".equals(searchType)){
			browser.selectDropdownList(".id", new RegularExpression("AttributeComboBox-\\d+.selectedAttrDef",false), searchType, true);
		} else {// when searchType is blank, select the first option
			browser.selectDropdownList(".id", new RegularExpression("AttributeComboBox-\\d+.selectedAttrDef",false), 0);
		}
	}
	
	public void setVehicleSearchValue(String searchType, String searchValue){
		if(null != searchType){
			if("Boat Information - Inventory #".equals(searchType)){
				browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+.attr\\[5018\\]",false), searchValue);
			} else {
				if(!"".equals(searchValue)){
					browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+.attr.*",false), searchValue, true);
				} else {
					browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+.attr\\[d+\\]",false), 0);
				}
			}
		}
	}
	
	public void setOwnerOrCoowner(String owner){
		if(!"".equals(owner)){
			browser.selectDropdownList(".id", new RegularExpression(prefix+"ownerSearchType",false), owner, true);
		} else {
			browser.selectDropdownList(".id", new RegularExpression(prefix+"ownerSearchType",false), 0);
		}
		
	}
	
	public void setLastName(String lastName){
		browser.setTextField(".id", new RegularExpression(prefix+"lastName",false), lastName);
	}
	
	public void setFirstName(String firstName){
		browser.setTextField(".id", new RegularExpression(prefix+"firstName",false), firstName);
	}
	
	public void setBusinessName(String businessName){
		browser.setTextField(".id", new RegularExpression(prefix+"businessName",false), businessName);
	}
	
	// only exist when search by Owner or Previous Owner -- start
	public void setPhone(String businessName){
		browser.setTextField(".id", new RegularExpression(prefix+"phoneNumber",false), businessName);
	}
	
	public void setPhysicalAddr(String physicalAddress){
		browser.setTextField(".id", new RegularExpression(prefix+"physicalAddress",false), physicalAddress);
	}
	
	public void setSupplementalAddr(String supplementalAddress){
		browser.setTextField(".id", new RegularExpression(prefix+"supplementalAddress",false), supplementalAddress);
	}
	
	public void setCityTown(String city){
		browser.setTextField(".id", new RegularExpression(prefix+"city",false), city);
	}
	
	public void setCounty(String county){
		if(!"".equals(county)){
			browser.selectDropdownList(".id", new RegularExpression(prefix+"county", false), county, true);
		} else {
			browser.selectDropdownList(".id", new RegularExpression(prefix+"county",false), 0);
		}
	}// can be selected after state is selected.
	
	public void setState(String state){
		if(!"".equals(state)){
			browser.selectDropdownList(".id", new RegularExpression(prefix+"state", false), state, true);
		} else {
			browser.selectDropdownList(".id", new RegularExpression(prefix+"state",false), 0);
		}
	}// option will be changed with country changed.
	
	public void setZipCode(String zipCode){
		browser.setTextField(".id", new RegularExpression(prefix+"zipCode",false), zipCode);
	}

	public void setCountry(String country){
		if(!"".equals(country)){
			browser.setTextField(".id", new RegularExpression(prefix+"country",false), country);
		} else {
			browser.selectDropdownList(".id", new RegularExpression(prefix+"country",false), 0);
		}
	}
	// only exist when search by Owner or Previous Owner-- end
	
	public void checkExactMatch(){
		browser.selectCheckBox(".id", new RegularExpression(prefix+"exactMatch",false));
	}
	
	public void uncheckExactMatch(){
		browser.unSelectCheckBox(".id", new RegularExpression(prefix+"exactMatch",false));
	}
	
	
	public void checkIncludeAreaCode(){
		browser.selectCheckBox(".id", new RegularExpression(prefix+"includeAreaCode",false));
	}
	
	public void uncheckIncludeAreaCode(){
		browser.unSelectCheckBox(".id", new RegularExpression(prefix+"includeAreaCode",false));
	}
	
	public void setupSearchCriteria(Vehicle vehicle){
		// set state to empty
		this.setState(StringUtil.EMPTY);
		ajax.waitLoading();
		
		if(vehicle instanceof BoatInfo) {
			if(!StringUtil.isEmpty(((BoatInfo)vehicle).hullIdSerialNum)) {
				this.setSerialNum(((BoatInfo)vehicle).hullIdSerialNum);
			}
			if(!StringUtil.isEmpty(((BoatInfo)vehicle).manufacturerName)) {
				this.setManufacturer(((BoatInfo)vehicle).manufacturerName);
			}
			if(!StringUtil.isEmpty(((BoatInfo)vehicle).modelYear)) {
				this.setModelYear(((BoatInfo)vehicle).modelYear);
			}
		}
		
		if(vehicle instanceof MotorInfo) {
			if(!StringUtil.isEmpty(((MotorInfo)vehicle).getSerialNum())) {
				this.setSerialNum(((MotorInfo)vehicle).getSerialNum());
			}
			if(!StringUtil.isEmpty(((MotorInfo)vehicle).getManufacturerName())) {
				this.setManufacturer(((MotorInfo)vehicle).getManufacturerName());
			}
			if(!StringUtil.isEmpty(((MotorInfo)vehicle).getModelYear())) {
				this.setModelYear(((MotorInfo)vehicle).getModelYear());
			}
		}
		
		if(!StringUtil.isEmpty(vehicle.id)) {
			this.setMINum(vehicle.id);
		}
		if(vehicle.isExactMatch){
			this.checkExactMatch();
		} else {
			this.uncheckExactMatch();
		}
		if(!StringUtil.isEmpty(vehicle.type)) {
			this.setVehicleType(vehicle.type);
		}
		if(!StringUtil.isEmpty(vehicle.status)) {
			this.setVehicleStatus(vehicle.status);
		}
		if(!StringUtil.isEmpty(vehicle.title.titleNum)) {
			this.setTitleNum(vehicle.title.titleNum);
		}
		if(!StringUtil.isEmpty(vehicle.vehicleSearchType)) {
			this.setVehicleSearchType(vehicle.vehicleSearchType);
			ajax.waitLoading();
		}
		if(!StringUtil.isEmpty(vehicle.vehicleSearchType) && !StringUtil.isEmpty(vehicle.vehicleSearchValue)) {
			this.setVehicleSearchValue(vehicle.vehicleSearchType, vehicle.vehicleSearchValue);
		}
		if(!StringUtil.isEmpty(vehicle.searchyByOwnerOrCoowner)) {
			this.setOwnerOrCoowner(vehicle.searchyByOwnerOrCoowner);
			ajax.waitLoading();
		}
		if(!StringUtil.isEmpty(vehicle.lastName)) {
			this.setLastName(vehicle.lastName);
		}
		if(!StringUtil.isEmpty(vehicle.firstName)) {
			this.setFirstName(vehicle.firstName);
		}
		if(!StringUtil.isEmpty(vehicle.businessName)) {
			this.setBusinessName(vehicle.businessName);
		}
		if("Owner".equals(vehicle.searchyByOwnerOrCoowner) || "Previous Owner".equals(vehicle.searchyByOwnerOrCoowner)){
			if(!StringUtil.isEmpty(vehicle.phone)) {
				this.setPhone(vehicle.phone);
			}
			if(vehicle.isIncludeAreaCode){
				this.checkIncludeAreaCode();
			} else {
				this.uncheckIncludeAreaCode();
			}
//			if(!StringUtil.isEmpty(vehicle.address)) {
//				this.setPhysicalAddr(vehicle.address);
//			}
//			if(!StringUtil.isEmpty(vehicle.suppAddr)) {
//				this.setPhysicalAddr(vehicle.suppAddr);
//			}
//			if(!StringUtil.isEmpty(vehicle.city)) {
//				this.setCityTown(vehicle.city);
//			}
//			if(!StringUtil.isEmpty(vehicle.zip)) {
//				this.setZipCode(vehicle.zip);
//			}
//			if(!StringUtil.isEmpty(vehicle.country)) {
//				this.setCountry(vehicle.country);
//				ajax.waitLoading();
//			}
//			if(!StringUtil.isEmpty(vehicle.state)) {
//				this.setState(vehicle.state);
//				ajax.waitLoading();
//				if(!StringUtil.isEmpty(vehicle.county)) {
//					this.setCounty(vehicle.county);
//					ajax.waitLoading();
//				}
//			}
		}
	}
	
	public void clickSearch() {
		// udpate by lesley wang
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id" , new RegularExpression("vehicleSearchCriteria", false));
		if(null == objs || objs.length<1){
			throw new ItemNotFoundException("Can't find Search Vehicle section.");
		}
		browser.clickGuiObject(".class", "Html.A", ".text", "Search", false, 0, objs[0]);
		Browser.unregister(objs);
	}
	
	public void clickVehiclesID(String id){
		browser.clickGuiObject(".class", "Html.A", ".text",id);
	}
	public void clickVehiclesTab(){
		browser.clickGuiObject(".class", "Html.A", ".id",new RegularExpression("VehicleMgrTabs_\\d+",false));
	}
	
	public void searchVehicleOrderBySerialNum(String num){
		this.setState(StringUtil.EMPTY);
		this.setCountry(StringUtil.EMPTY);
		this.setSerialNum(num);
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void searchVehicleByVehicelId(String id) {
		this.setMINum(id);
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void clickVehiclesIDBySerialNum(String serialNum){
		String id = getIDBySerialNum(serialNum);
		browser.clickGuiObject(".class", "Html.A", ".text",id);
	}
	
	public void clickInspectionsTab(){
		this.clickOtherTab("Inspections");
	}
	
	public String getIDBySerialNum(String serialNum){
		IHtmlObject[] objs = browser.getTableTestObject(".id","vehicleList_LIST");
		IHtmlTable grid = (IHtmlTable)objs[0];
		String miNum = "";
		
		for(int i=1;i<grid.rowCount();i++){
			if(grid.getCellValue(i, 3).equalsIgnoreCase(serialNum)){
				miNum = grid.getCellValue(i, 0);
				break;
			}
		}
		
		Browser.unregister(objs);
		return miNum;
	}
	
	public void clickOtherTab(String tabName){
		//wait for the expected tab to completely display
		browser.waitExists(Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression(tabName, false)));
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression(tabName, false), true);
	}
	
	public void switchToRegistrationsTab() {
		clickOtherTab("Registrations");
		ajax.waitLoading();
	}
	
	public void switchToTitlesTab() {
		clickOtherTab("Titles");
		ajax.waitLoading();
	}
	
	public void switchToInspectionsTab() {
		clickOtherTab("Inspections");
		ajax.waitLoading();
	}
	
	public String getWarnMessage(){
		String message = "";
		IHtmlObject divObjs[] = browser.getHtmlObject(".class", "Html.Div", ".id", "NOTSET");
		if(divObjs.length > 0){
			message = divObjs[0].getProperty(".text").toString();
			//divObjs[0].text();
			Browser.unregister(divObjs);
		}	
		return message;
	}
	
	private IHtmlTable getResultTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("vehicleList", false));
		if(objs.length <= 0){
			throw new ItemNotFoundException("Can't find search result list.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
//		Browser.unregister(objs);
		return table;
	}
	
	public List<String> getColumnByName(String columnName){

		List<String> valueList = new ArrayList<String>();
		List<String> tempList = new ArrayList<String>();

		IHtmlTable table = this.getResultTable();
		int col = table.findColumn(0, columnName);
		tempList = table.getColumnValues(col);
		tempList.remove(0);
		valueList.addAll(tempList);

		PagingComponent turnPageComponent = new PagingComponent();
		while (turnPageComponent.nextExists()){
			turnPageComponent.clickNext();
			ajax.waitLoading();
			
			table = this.getResultTable();
			col = table.findColumn(0, columnName);
			tempList = new ArrayList<String>();
			tempList = table.getColumnValues(col);
			tempList.remove(0);
			valueList.addAll(tempList);
		}
		return valueList;
	}
}
