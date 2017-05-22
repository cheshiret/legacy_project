package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityEntrance;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EntranceAttributesInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EntranceInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitTypeInformation;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Feb 14, 2012
 */
public class InvMgrAddNewEntrancePage extends InventoryManagerPage {

	private static InvMgrAddNewEntrancePage _instance = null;

	public static InvMgrAddNewEntrancePage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrAddNewEntrancePage();
		}

		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "EntranceView.code");
	}
	
	/**
	 * Click Add to add Permit Type Information
	 */
	public void clickAdd(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add", true, 0);
	}
	
	/**
	 * Click to add zone.
	 */
	public void clickZoneAdd(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.table", ".id", "zoneTable");
		if(objs.length <= 0){
			throw new ItemNotFoundException("Can't find zone table...");
		}
		browser.clickGuiObject(".class", "Html.A", ".text", "Add", true, 0, objs[0]);
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public void clickApply(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}

	public void clickRemove(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove");
	}
	
	// Entrance Information -- Start
	public void setEntranceCode(String entranceCode){
		browser.setTextField(".id", "EntranceView.code", entranceCode, true);
	}
	
	public void setEntranceName(String entranceName){
		browser.setTextField(".id", "EntranceView.name", entranceName, true);
	}
	
	public void setEntranceType(String entranceType){
		browser.selectDropdownList(".id", "EntranceView.entranceTypeID", entranceType);
	}

	public void setDescription(String description){
		browser.setTextArea(".id", "EntranceView.description", description, true);
	}

	public void setEntryType(String entryType){
		browser.selectDropdownList(".id", "EntranceView.entryTypeID", entryType);
	}
	
	public void selectUseType(String useType){
		browser.selectDropdownList(".id", "EntranceView.useTypeID", useType);
	}
	
	// Entrance Information -- End
	
	// Entrance Attributes -- Start
	public void setDistrict(String district){
		browser.selectDropdownList(".id", "EntranceView.districtID", district);		
	}
	
	public void setTowm(String town){
		browser.selectDropdownList(".id", "EntranceView.townID", town);
	}

	public void setAdaAccessible(String adaAccessible){
		browser.selectDropdownList(".id", "EntranceView.adaAccessible", adaAccessible);
	}
	
	public void setOpenTime(String openTime){
		browser.setTextField(".id", "time_0", openTime);
	}

	public void setOpenTimeAmPm(String openTimeAmPm){
		browser.selectDropdownList(".id", "ampm_0", openTimeAmPm);
	}

	public void setCloseTime(String closeTime){
		browser.setTextField(".id", "time_1", closeTime);
	}

	public void setCloseTimeAmPm(String closeTimeAmPm){
		browser.selectDropdownList(".id", "ampm_1", closeTimeAmPm);
	}

	public void setLongitudeDir(String longitudeDir){
		browser.setTextField(".id", "EntranceView.longitudeDir", longitudeDir);
	}

	public void setLongitudeDeg(String longitudeDeg){
		browser.setTextField(".id", "EntranceView.longitudeDeg", longitudeDeg);
	}

	public void setLongitudeMin(String longitudeMin){
		browser.setTextField(".id", "EntranceView.longitudeMin", longitudeMin);
	}

	public void setLongitudeSec(String longitudeSec){
		browser.setTextField(".id", "EntranceView.longitudeSec", longitudeSec);
	}

	public void setLatitudeDir(String latitudeDir){
		browser.setTextField(".id", "EntranceView.latitudeDir", latitudeDir);
	}

	public void setLatitudeDeg(String latitudeDeg){
		browser.setTextField(".id", "EntranceView.latitudeDeg", latitudeDeg);
	}

	public void setLatitudeMin(String latitudeMin){
		browser.setTextField(".id", "EntranceView.latitudeMin", latitudeMin);
	}

	public void setLatitudeSec(String latitudeSec){
		browser.setTextField(".id", "EntranceView.latitudeSec", latitudeSec);
	}
	
	public void setAccessibility(String accessibility){
		browser.setTextArea(".id", "EntranceView.accessibility", accessibility);
	}
	
	public void setImportantInfo(String importantInfo){
		browser.setTextArea(".id", "EntranceView.importantInfo", importantInfo);
	}
	
	public void setLongDescription(String longDescription){
		browser.setTextArea(".id", "EntranceView.longDescription", longDescription);
	}
	
	public void setDrivingDirections(String drivingDirections){
		browser.setTextArea(".id", "EntranceView.drivingDirections", drivingDirections);
	}
	
	public void setParkingInstructions(String parkingInstructions){
		browser.setTextArea(".id", "EntranceView.parkingInstructions", parkingInstructions);
	}
	
	public void setDefaultPermitIssueStation(String defaultPermitIssueStation){
		browser.selectDropdownList(".id", "EntranceView.defaultIssueStationID", defaultPermitIssueStation);
	}
	
	public void setZone(String zone){
		browser.selectDropdownList(".id", "EntranceView.zoneIds", zone, 1);
	}
	
	public String getZone(){
		if(!this.checkZoneDropdownList())  //added by pzhu
		{
			this.clickZoneAdd();
		}
		return browser.getDropdownListValue(".id", "EntranceView.zoneIds",1);		
	}

	public List<String> getAllZone(){
		List<String> list = browser.getDropdownElements(".id", "EntranceView.zoneIds");//update by pzhu

		if(null == list || list.size() <= 1){
			throw new ErrorOnPageException("Can't find the Zone drop down list.");
		}
		return list;
	}
	// Entrance Attributes -- End
	
	// Permit Type Information--Start
	public void setPermitType(String permitType){
		browser.selectDropdownList(".id", "ept_permit_type", permitType);
	}
	
	public boolean checkMaxStayLengthExists(){
		return browser.checkHtmlObjectDisplayed(".id", "ept_max_exit_date_length");
	}
	
	public boolean checkMaxGroupSizeExists(){
		return browser.checkHtmlObjectExists(".id", "ept_max_group_size");
	}
	
	public boolean checkMaxWatercraftExists(){
		return browser.checkHtmlObjectExists(".id", "ept_max_watercraft");
	}
	
	public boolean checkMaxGroupMemExists(){
		return browser.checkHtmlObjectExists(".id", "ept_max_groupmember");
	}
	
	public boolean checkMaxPetsExists(){
		return browser.checkHtmlObjectExists(".id", "ept_max_pets");
	}
	
	public boolean checkMaxStockExists(){
		return browser.checkHtmlObjectExists(".id", "ept_max_stock");
	}
	
	public boolean checkStockRatioExists(){
		return browser.checkHtmlObjectExists(".id", "ept_stock_ratio");
	}
	
	public String getMaxGroupSize(){
		return browser.getTextFieldValue(".id", "ept_max_group_size");
	}
	
	public void setMaxStayLength(String maxStayLength){
		browser.setTextField(".id", "ept_max_exit_date_length", maxStayLength);
	}
	
	public void setMaxGroupSize(String maxGroupSize){
		browser.setTextField(".id", "ept_max_group_size", maxGroupSize);
	}
	
	public void setMaxWatercraft(String maxWatercraft){
		browser.setTextField(".id", "ept_max_watercraft", maxWatercraft);
	}
	
	public void setMaxGroupMem(String maxGroupMem){
		browser.setTextField(".id", "ept_max_groupmember", maxGroupMem);
	}
	
	public void setMaxPets(String maxPets){
		browser.setTextField(".id", "ept_max_pets", maxPets);
	}
	
	public void setMaxStock(String maxStock){
		browser.setTextField(".id", "ept_max_stock", maxStock);
	}
	
	public void setStockRatio(String stockRatio){
		browser.setTextField(".id", "ept_stock_ratio", stockRatio);
	}
	
	public void setIssueStartDateType(String issueStartDateType){
		browser.selectDropdownList(".id", "ept_issue_start_date", issueStartDateType);
	}
	
	public void setIssueStartDateTime(String startDateTime){
		browser.setTextField(".id", "epttime_0", startDateTime);
	}

	public void setIssueStartDateAmPm(String startAmPm){
		browser.selectDropdownList(".id", "eptampm_0", startAmPm);
	}
	
	public void setIssueEndDateType(String issueEndDateType){
		browser.selectDropdownList(".id", "ept_issue_end_date", issueEndDateType);
	}
	
	public void setIssueEndDateTime(String endDateTime){
		browser.setTextField(".id", "epttime_1", endDateTime);
	}

	public void setIssueEndDateAmPm(String endAmPm){
		browser.selectDropdownList(".id", "eptampm_1", endAmPm);
	}
	
	public String getUpdateResWindow(){		
		return browser.getTextFieldValue(".id", new RegularExpression("^ept_updateRestrictionWindow*", false));
	}
	
	public void setUpdateResWindow(String updateResWindow){
		browser.setTextField(".id", "ept_updateRestrictionWindow", updateResWindow, true);
	}
	
	public void setStatus(String status){
		browser.selectDropdownList(".id", "ept_status", status);
	}
	
	public boolean checkZoneDropdownList(){
//		return browser.checkHtmlObjectExists(".id", new RegularExpression("EntranceView\\.(zoneID|zoneIds)", false));
		return browser.checkHtmlObjectDisplayed(".id", new RegularExpression("EntranceView\\.(zoneID|zoneIds)", false));
//		HtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("EntranceView\\.(zoneID|zoneIds)", false));
//		Browser.unregister(objs);
//		if(objs.length>0)
//		{
//			return true;
//		}else{
//			return false;
//		}
	}
	// Permit Type Information--End
	
	public void setUpEntranceInfo(EntranceInfo entranceInfo){
		this.setEntranceCode(entranceInfo.entranceCode);
		this.setEntranceName(entranceInfo.entranceName);
		this.setDescription(entranceInfo.description);
		this.setEntranceType(entranceInfo.entranceType);
		this.setEntryType(entranceInfo.entryType);
		this.selectUseType(entranceInfo.useType);
	}
	
	public void setUpEntranceAttriInfo(EntranceAttributesInfo entranceAttriInfo){
		if(!entranceAttriInfo.district.isEmpty()){
			this.setDistrict(entranceAttriInfo.district);
			this.waitLoading();
		}
		if(!entranceAttriInfo.town.isEmpty()){
			this.setTowm(entranceAttriInfo.town);
			this.waitLoading();
		}
		if(!entranceAttriInfo.adaAccessible.isEmpty()){
			this.setAdaAccessible(entranceAttriInfo.adaAccessible);
		}
		if(!entranceAttriInfo.openTime.isEmpty()){
			this.setOpenTime(entranceAttriInfo.openTime);
		}
		if(!entranceAttriInfo.openTimeAmPm.isEmpty()){
			this.setOpenTimeAmPm(entranceAttriInfo.openTimeAmPm);
		}
		if(!entranceAttriInfo.closeTime.isEmpty()){
			this.setCloseTime(entranceAttriInfo.closeTime);
		}
		if(!entranceAttriInfo.closeTimeAmPm.isEmpty()){
			this.setCloseTimeAmPm(entranceAttriInfo.closeTimeAmPm);
		}
		if(!entranceAttriInfo.longitudeDir.isEmpty()){
			this.setLongitudeDir(entranceAttriInfo.longitudeDir);
		}
		if(!entranceAttriInfo.longitudeDeg.isEmpty()){
			this.setLongitudeDeg(entranceAttriInfo.longitudeDeg);
		}
		if(!entranceAttriInfo.longitudeMin.isEmpty()){
			this.setLongitudeMin(entranceAttriInfo.longitudeMin);
		}
		if(!entranceAttriInfo.longitudeSec.isEmpty()){
			this.setLongitudeSec(entranceAttriInfo.longitudeSec);
		}
		if(!entranceAttriInfo.latitudeDir.isEmpty()){
			this.setLatitudeDir(entranceAttriInfo.latitudeDir);
		}
		if(!entranceAttriInfo.latitudeDeg.isEmpty()){
			this.setLatitudeDeg(entranceAttriInfo.latitudeDeg);
		}
		if(!entranceAttriInfo.latitudeMin.isEmpty()){
			this.setLatitudeMin(entranceAttriInfo.latitudeMin);
		}
		if(!entranceAttriInfo.latitudeSec.isEmpty()){
			this.setLatitudeSec(entranceAttriInfo.latitudeSec);
		}

		this.setAccessibility(entranceAttriInfo.accessibility);	
		this.setImportantInfo(entranceAttriInfo.importantInfo);
		this.setLongDescription(entranceAttriInfo.longDescription);
		this.setDrivingDirections(entranceAttriInfo.drivingDirections);
		this.setParkingInstructions(entranceAttriInfo.parkingInstructions);
		
		if(!entranceAttriInfo.defaultPermitIssueStation.isEmpty()){
			this.setDefaultPermitIssueStation(entranceAttriInfo.defaultPermitIssueStation);
		}
		
		if(entranceAttriInfo.zone!=null && !entranceAttriInfo.zone.isEmpty()) {
			if(!this.checkZoneDropdownList())  //added by pzhu
			{
				this.clickZoneAdd();
			}
			this.setZone(entranceAttriInfo.zone);
		}
	}

	public void setUpPermitTypeInfo(PermitTypeInformation permitTypeInfo, boolean isUpdateResWindowBlank){
		if(!permitTypeInfo.permitType.isEmpty()){
			this.setPermitType(permitTypeInfo.permitType);
		}
		if(this.checkMaxStayLengthExists()){
			this.setMaxStayLength(permitTypeInfo.maxStayLength);
		}
		if(this.checkMaxGroupSizeExists()){
			this.setMaxGroupSize(permitTypeInfo.maxGroupSize);
		}
		if(this.checkMaxWatercraftExists()){
			this.setMaxWatercraft(permitTypeInfo.maxWatercraft);
		}

		if(this.checkMaxGroupMemExists()){
			this.setMaxGroupMem(permitTypeInfo.maxGroupMem);
		}
		if(this.checkMaxPetsExists()){
			this.setMaxPets(permitTypeInfo.maxPets);
		}
		if(this.checkMaxStockExists()){
			this.setMaxStock(permitTypeInfo.maxStock);
		}
		if(this.checkStockRatioExists())
		{
			this.setStockRatio(permitTypeInfo.stockRatio);
		}
		if(!permitTypeInfo.issueStartDateType.isEmpty())
		{
			this.setIssueStartDateType(permitTypeInfo.issueStartDateType);
		}
		this.setIssueStartDateTime(permitTypeInfo.startDateTime);
		if(!permitTypeInfo.startAmPm.isEmpty()){
			this.setIssueStartDateAmPm(permitTypeInfo.startAmPm);
		}
		if(!permitTypeInfo.issueEndDateType.isEmpty()){
			this.setIssueEndDateType(permitTypeInfo.issueEndDateType);
		}
		this.setIssueEndDateTime(permitTypeInfo.endDateTime);
		if(!permitTypeInfo.endAmPm.isEmpty()){
			this.setIssueEndDateAmPm(permitTypeInfo.endAmPm);
		}
		
		if(!isUpdateResWindowBlank){
			if(!permitTypeInfo.updateResWindow.isEmpty()){
				this.setUpdateResWindow(permitTypeInfo.updateResWindow);
			}
		} else {
			this.setUpdateResWindow(permitTypeInfo.updateResWindow);
		}
		
		if(!permitTypeInfo.status.isEmpty()){
			this.setStatus(permitTypeInfo.status);
		}
	}
	
	public void setUpInfoForNewEntrance(EntranceInfo entranceInfo, EntranceAttributesInfo entranceAttriInfo, PermitTypeInformation permitTypeInfo, boolean isUpdateResWinBlank){
		logger.info("Set Up Information for adding new Entrance.");
		
		// set up entrance information.
		if(null != entranceInfo){
			this.setUpEntranceInfo(entranceInfo);
		}
		
		// set up entrance attributes.
		if(null != entranceAttriInfo){
			this.setUpEntranceAttriInfo(entranceAttriInfo);
		}
		
		// set up permit type information.
		if(null != permitTypeInfo){
			this.clickAdd();
			ajax.waitLoading();
			this.setUpPermitTypeInfo(permitTypeInfo, isUpdateResWinBlank);
		}
	}
	
	public String getErrMsg(){
		String errMsg = "";
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", new RegularExpression("com.reserveamerica.common.ValidationEx", false));
		if(objs != null && objs.length > 0){
			errMsg=objs[0].getProperty(".text");
		}
		Browser.unregister(objs);
		return errMsg;
	}
	
	public String getSuccessMsg(){
		String successMsg = "";
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", new RegularExpression("NOTSET", false));
		if(objs != null && objs.length > 0){
			successMsg=objs[0].getProperty(".text");
		}
		Browser.unregister(objs);
		return successMsg;
	}
	
}
