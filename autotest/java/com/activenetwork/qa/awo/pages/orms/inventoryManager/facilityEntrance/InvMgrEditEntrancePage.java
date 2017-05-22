package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityEntrance;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EntranceAttributesInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EntranceInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitTypeInformation;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Feb 21, 2012
 */
public class InvMgrEditEntrancePage extends InventoryManagerPage {

	private static InvMgrEditEntrancePage _instance = null;

	public static InvMgrEditEntrancePage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrEditEntrancePage();
		}

		return _instance;
	}

	public static boolean IS_ADD_BTN_EXISTING = true;
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "EntranceView.code") && browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Delete Entrance");
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

	public void clickAdd(){
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression("javascript:ajaxCall2\\(.*addEntrancePermitType.*", false));
	}

	public void clickRemove(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove");
	}

	// Entrance Information -- Start
	public void setEntranceCode(String entranceCode){
		browser.setTextField(".id", "EntranceView.code", entranceCode, true);
	}
	
	public String getEntranceCode(){
		return browser.getTextFieldValue(".id", "EntranceView.code");
	}
	
	public void setEntranceName(String entranceName){
		browser.setTextField(".id", "EntranceView.name", entranceName, true);
	}
	
	public String getEntranceName(){
		return browser.getTextFieldValue(".id", "EntranceView.name");
	}
	
	public void setEntranceType(String entranceType){
		browser.selectDropdownList(".id", "EntranceView.entranceTypeID", entranceType);
	}
	
	public String getEntranceType(){
		return browser.getDropdownListValue(".id", "EntranceView.entranceTypeID");
	}
	
	public void setDescription(String description){
		browser.setTextArea(".id", "EntranceView.description", description, true);
	}
	
	public String getDescription(){
	  	IHtmlObject[] objs = browser.getTextArea(".id", "EntranceView.description");
	  	String desc = objs[0].getProperty(".value").toString();
	  	Browser.unregister(objs);
	  	return desc;
	}

	public void setEntryType(String entryType){
		browser.selectDropdownList(".id", "EntranceView.entryTypeID", entryType);
	}
	
	public String getEntryType(){
		return browser.getDropdownListValue(".id", "EntranceView.entryTypeID");
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
		browser.setTextField(".id", "ampm_0", openTimeAmPm);
	}

	public void setCloseTime(String closeTime){
		browser.setTextField(".id", "time_1", closeTime);
	}

	public void setCloseTimeAmPm(String closeTimeAmPm){
		browser.setTextField(".id", "ampm_1", closeTimeAmPm);
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
		return browser.getDropdownListValue(".id", "EntranceView.zoneIds");
	}

	public List<String> getAllZone(){
		List<String> list = browser.getDropdownElements(".id", "EntranceView.zoneID");
		if(null == list || list.size() <= 1){
			throw new ErrorOnPageException("Can't find the Zone drop down list.");
		}
		return list;
	}
	// Entrance Attributes -- End
	
	// Permit Type Information--Start
	public boolean checkPerminTypeExist(){
		return browser.checkHtmlObjectExists(".id", "ept_permit_type");
	}
	
	public boolean checkMaxStayLengthExists(){
		return browser.checkHtmlObjectExists(".id", "ept_max_exit_date_length");
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
	
	private boolean checkMultiSelectionExists() {
		return browser.checkHtmlObjectExists(".class", "Html.LABEL", ".text", "Applicable Exits");
	}
	
	public void setPermitType(String permitType, int rowIndex){
		browser.selectDropdownList(".id", "ept_permit_type", permitType, rowIndex);
		Browser.sleep(1);
		waitLoading();
	}
	
	public void setPermitType(String permitType){
		browser.selectDropdownList(".id", "ept_permit_type", permitType);
	}
	
	public String getPermitType(){
		return browser.getDropdownListValue(".id", "ept_permit_type");
	}
	
	public String getMaxGroupSize(){
		return browser.getTextFieldValue(".id", "ept_max_group_size");
	}
	
	public void setMaxGroupSize(String maxGroupSize, int rowIndex){
		if (maxGroupSize != null) {
			browser.setTextField(".id", "ept_max_group_size", maxGroupSize, rowIndex);
		}
	}
	
	public void setMaxGroupSize(String maxGroupSize){
		browser.setTextField(".id", "ept_max_group_size", maxGroupSize);
	}
	
	public void setMaxStayLength(String maxStayLength, int rowIndex){
		if (maxStayLength != null) {
			browser.setTextField(".id", "ept_max_exit_date_length", maxStayLength, rowIndex);
		}
	}
	
	public void setMaxStayLength(String maxStayLength){
		browser.setTextField(".id", "ept_max_exit_date_length", maxStayLength);
	}
	
	public void setMaxWatercraft(String maxWatercraft, int rowIndex){
		if (maxWatercraft != null) {
			browser.setTextField(".id", "ept_max_watercraft", maxWatercraft, rowIndex);
		}
	}

	public void setMaxWatercraft(String maxWatercraft){
		browser.setTextField(".id", "ept_max_watercraft", maxWatercraft);
	}
	
	public String getMaxWatercraft(){
		return browser.getTextFieldValue(".id", "ept_max_watercraft");
	}
	
	public void setMaxGroupMem(String maxGroupMem, int rowIndex){
		if (maxGroupMem != null) {
			browser.setTextField(".id", "ept_max_groupmember", maxGroupMem, rowIndex);
		}
	}
	
	public void setMaxGroupMem(String maxGroupMem){
		browser.setTextField(".id", "ept_max_groupmember", maxGroupMem);
	}
	
	public void setMaxPets(String maxPets, int rowIndex){
		if (maxPets != null) {
			browser.setTextField(".id", "ept_max_pets", maxPets, rowIndex);
		}
	}
	
	public void setMaxPets(String maxPets){
		browser.setTextField(".id", "ept_max_pets", maxPets);
	}
	
	public void setMaxStock(String maxStock, int rowIndex){
		if (maxStock != null) {
			browser.setTextField(".id", "ept_max_stock", maxStock, rowIndex);
		}
	}
	
	public void setMaxStock(String maxStock){
		browser.setTextField(".id", "ept_max_stock", maxStock);
	}
	
	public void setStockRatio(String stockRatio, int rowIndex){
		if (stockRatio != null) {
			browser.setTextField(".id", "ept_stock_ratio", stockRatio, rowIndex);
		}
	}
	
	public void setStockRatio(String stockRatio){
		browser.setTextField(".id", "ept_stock_ratio", stockRatio);
	}
	
	public void setIssueStartDateType(String issueStartDateType, int rowIndex){
		if (issueStartDateType != null) {
			browser.selectDropdownList(".id", "ept_issue_start_date", issueStartDateType, rowIndex);
		}
	}
	
	public void setIssueStartDateType(String issueStartDateType){
		browser.selectDropdownList(".id", "ept_issue_start_date", issueStartDateType);
	}
	
	public String getIssueStartDateType(){
		return browser.getDropdownListValue(".id", "ept_issue_start_date");
	}
	
	public void setIssueStartDateTime(String startDateTime, int rowIndex){
		if (startDateTime != null) {
			browser.setTextField(".id", "epttime_0", startDateTime, rowIndex);
		}
	}
	
	public void setIssueStartDateTime(String startDateTime){
		browser.setTextField(".id", "epttime_0", startDateTime);
	}
	
	public String getIssueStartDateTime(){
		return browser.getTextFieldValue(".id", "epttime_0");
	}
	
	public void setIssueStartDateAmPm(String startAmPm, int rowIndex){
		if (startAmPm != null) {
			browser.selectDropdownList(".id", "eptampm_0", startAmPm, rowIndex);
		}
	}

	public void setIssueStartDateAmPm(String startAmPm){
		browser.selectDropdownList(".id", "eptampm_0", startAmPm);
	}
	
	public String getIssueStartDateAmPm(){
		return browser.getDropdownListValue(".id", "eptampm_0");
	}
	
	public void setIssueEndDateType(String issueEndDateType, int rowIndex){
		if (issueEndDateType != null) {
			browser.selectDropdownList(".id", "ept_issue_end_date", issueEndDateType, rowIndex);
		}
	}
	
	public void setIssueEndDateType(String issueEndDateType){
		browser.selectDropdownList(".id", "ept_issue_end_date", issueEndDateType);
	}
	
	public String getIssueEndDateType(){
		return browser.getDropdownListValue(".id", "ept_issue_end_date");
	}
	
	public void setIssueEndDateTime(String endDateTime, int rowIndex){
		if (endDateTime != null) {
			browser.setTextField(".id", "epttime_1", endDateTime, rowIndex);
		}
	}
	
	public void setIssueEndDateTime(String endDateTime){
		browser.setTextField(".id", "epttime_1", endDateTime);
	}
	
	public String getIssyeEndDateTime(){
		return browser.getTextFieldValue(".id", "epttime_1");
	}
	
	public void setIssueEndDateAmPm(String endAmPm, int rowIndex){
		if (endAmPm != null) {
			browser.selectDropdownList(".id", "eptampm_1", endAmPm, rowIndex);
		}
	}
	
	public void setIssueEndDateAmPm(String endAmPm){
		browser.selectDropdownList(".id", "eptampm_1", endAmPm);
	}
	
	public String getIssyeEndDateAmPm(){
		return browser.getDropdownListValue(".id", "eptampm_1");
	}
	
	public String getUpdateResWindow(){
		return browser.getTextFieldValue(".id", new RegularExpression("^ept_updateRestrictionWindow*", false));
	}
	
	public void setUpdateResWindow(String updateResWindow, int rowIndex){
		if (updateResWindow != null) {
			browser.setTextField(".id", "ept_updateRestrictionWindow", updateResWindow, true, rowIndex);
		}
	}
	
	public void setUpdateResWindow(String updateResWindow){
		browser.setTextField(".id", "ept_updateRestrictionWindow", updateResWindow, true);
	}
	
	public void setStatus(String status, int rowIndex){
		if (status != null) {
			browser.selectDropdownList(".id", "ept_status", status, rowIndex);
		}
	}
	
	public void setStatus(String status){
		browser.selectDropdownList(".id", "ept_status", status);
	}
	
	public String getStatues(){
		return browser.getDropdownListValue(".id", "ept_status");
	}
	// Permit Type Information--End

	public void setUpEntranceInfo(EntranceInfo entranceInfo){
		this.setEntranceCode(entranceInfo.entranceCode);
		this.setEntranceName(entranceInfo.entranceName);
		this.setDescription(entranceInfo.description);
		this.setEntranceType(entranceInfo.entranceType);
		this.setEntryType(entranceInfo.entryType);
	}
	
	public EntranceInfo getEntranceInfo(){
		EntranceInfo entranceInfo = new EntranceInfo();
		entranceInfo.entranceCode = this.getEntranceCode();
		entranceInfo.entranceName = this.getEntranceName();
		entranceInfo.description = this.getDescription();
		entranceInfo.entryType = this.getEntryType();
		entranceInfo.entranceType = this.getEntranceType();
		return entranceInfo;
	}
	
	public void setUpEntranceAttriInfo(EntranceAttributesInfo entranceAttriInfo){
		this.setDistrict(entranceAttriInfo.district);
		this.setTowm(entranceAttriInfo.town);
		this.setAdaAccessible(entranceAttriInfo.adaAccessible);
		this.setOpenTime(entranceAttriInfo.openTime);
		this.setOpenTimeAmPm(entranceAttriInfo.openTimeAmPm);
		this.setCloseTime(entranceAttriInfo.closeTime);
		this.setCloseTimeAmPm(entranceAttriInfo.closeTimeAmPm);
		this.setLongitudeDir(entranceAttriInfo.longitudeDir);
		this.setLongitudeDeg(entranceAttriInfo.longitudeDeg);
		this.setLongitudeMin(entranceAttriInfo.longitudeMin);
		this.setLongitudeSec(entranceAttriInfo.longitudeSec);
		this.setLatitudeDir(entranceAttriInfo.latitudeDir);
		this.setLatitudeDeg(entranceAttriInfo.latitudeDeg);
		this.setLatitudeMin(entranceAttriInfo.latitudeMin);
		this.setLatitudeSec(entranceAttriInfo.latitudeSec);
		this.setAccessibility(entranceAttriInfo.accessibility);
		this.setImportantInfo(entranceAttriInfo.importantInfo);
		this.setLongDescription(entranceAttriInfo.longDescription);
		this.setDrivingDirections(entranceAttriInfo.drivingDirections);
		this.setParkingInstructions(entranceAttriInfo.parkingInstructions);
		this.setDefaultPermitIssueStation(entranceAttriInfo.defaultPermitIssueStation);
		this.setZone(entranceAttriInfo.zone);
	}
	
	public PermitTypeInformation getPermitTypeInfo(){
		PermitTypeInformation permitTypeInfo = new PermitTypeInformation();
		permitTypeInfo.permitType = this.getPermitType();
		permitTypeInfo.maxGroupSize = this.getMaxGroupSize();
		permitTypeInfo.maxWatercraft = this.getMaxWatercraft();
		permitTypeInfo.issueStartDateType = this.getIssueStartDateType();
		permitTypeInfo.issueEndDateType = this.getIssueEndDateType();
		permitTypeInfo.startDateTime = this.getIssueStartDateTime();
		permitTypeInfo.endDateTime = this.getIssyeEndDateTime();
		permitTypeInfo.startAmPm = this.getIssueStartDateAmPm();
		permitTypeInfo.endAmPm = this.getIssyeEndDateAmPm();
		permitTypeInfo.updateResWindow = this.getUpdateResWindow();
		permitTypeInfo.status = this.getStatues();
		return permitTypeInfo;
	}
	
	public void setupPermitTypeInfo(PermitTypeInformation permitTypeInfo, int rowIndex) {
		if(permitTypeInfo.permitType != null){
			this.setPermitType(permitTypeInfo.permitType,rowIndex);
		}
		if(this.checkMaxStayLengthExists()){
			this.setMaxStayLength(permitTypeInfo.maxStayLength, rowIndex);
		}
		if(this.checkMaxGroupSizeExists()){
			this.setMaxGroupSize(permitTypeInfo.maxGroupSize, rowIndex);
		}
		if(this.checkMaxWatercraftExists()){
			this.setMaxWatercraft(permitTypeInfo.maxWatercraft, rowIndex);
		}

		if(this.checkMaxGroupMemExists()){
			this.setMaxGroupMem(permitTypeInfo.maxGroupMem, rowIndex);
		}
		if(this.checkMaxPetsExists()){
			this.setMaxPets(permitTypeInfo.maxPets, rowIndex);
		}
		if(this.checkMaxStockExists()){
			this.setMaxStock(permitTypeInfo.maxStock, rowIndex);
		}
		if(this.checkStockRatioExists())
		{
			this.setStockRatio(permitTypeInfo.stockRatio, rowIndex);
		}
		
		if(this.checkMultiSelectionExists()){
			this.setExitsByMultiSelection(permitTypeInfo.multiSelectEntrances, rowIndex);
		}
		
		if(permitTypeInfo.issueStartDateType != null)
		{
			this.setIssueStartDateType(permitTypeInfo.issueStartDateType, rowIndex);
		}
		this.setIssueStartDateTime(permitTypeInfo.startDateTime, rowIndex);
		if(permitTypeInfo.startAmPm != null){
			this.setIssueStartDateAmPm(permitTypeInfo.startAmPm, rowIndex);
		}
		if(permitTypeInfo.issueEndDateType != null){
			this.setIssueEndDateType(permitTypeInfo.issueEndDateType, rowIndex);
		}
		this.setIssueEndDateTime(permitTypeInfo.endDateTime, rowIndex);
		if(permitTypeInfo.endAmPm != null){
			this.setIssueEndDateAmPm(permitTypeInfo.endAmPm, rowIndex);
		}
		if(permitTypeInfo.updateResWindow != null){
			this.setUpdateResWindow(permitTypeInfo.updateResWindow, rowIndex);
		}
		if(permitTypeInfo.status != null){
			this.setStatus(permitTypeInfo.status, rowIndex);
		}
	}
	
	public void setUpPermitTypeInfo(PermitTypeInformation permitTypeInfo){
		setupPermitTypeInfo(permitTypeInfo, 0);
	}
	
	public void setUpInfoForEdit(EntranceInfo entranceInfo, EntranceAttributesInfo entranceAttriInfo, PermitTypeInformation permitTypeInfo, boolean addNewPermitInfo){
		logger.info("Set Up Information for editing new Entrance.");
		
		// set up entrance information.
		if(null != entranceInfo){
			this.setUpEntranceInfo(entranceInfo);
		}
		
		// set up entrance attributes.
		if(null != entranceAttriInfo){
			this.setUpEntranceAttriInfo(entranceAttriInfo);
		}
		
		// set up permit type information.
		if(addNewPermitInfo){
			this.clickAdd();
			ajax.waitLoading();
		}
		if(null != permitTypeInfo){
			this.setUpPermitTypeInfo(permitTypeInfo);
		}
	}
	
	public String getErrMsg(){
		String errMsg = "";
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", "com.reserveamerica.common.ValidationEx");
		if(objs != null && objs.length > 0){
			errMsg=objs[0].getProperty(".text");
		}
		Browser.unregister(objs);
		return errMsg;
	}
	
	public String getSuccessMsg(){
		String successMsg = "";
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", "NOTSET");
		if(objs != null && objs.length > 0){
			successMsg=objs[0].getProperty(".text");
		}
		Browser.unregister(objs);
		return successMsg;
	}
	
	public void clickDelete(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Delete Entrance");
	}
	
	public void deleteEntrance(){
		this.clickDelete();
		browser.waitExists();
		ConfirmDialogPage cfirmDiaPg = ConfirmDialogPage.getInstance();
		if(cfirmDiaPg.exists()){
			cfirmDiaPg.clickOK();
		}
	}
	
	private static class MultiSelectComponent {
		private ISelect mainList;
		@SuppressWarnings("unused")
		private ISelect selectedList;
		private IHtmlObject addBtn;
		@SuppressWarnings("unused")
		private IHtmlObject removeBtn;
		private IHtmlObject top;

		private MultiSelectComponent(IHtmlObject top) {
			this.top = top;
			IS_ADD_BTN_EXISTING = isAddBtnExisting();
			if(IS_ADD_BTN_EXISTING){
				getAddBtn();
				getRemoveBtn();
				getMainList();
				getSelectedList();
			}else com.activenetwork.qa.testapi.util.AutomationLogger.getInstance().info("Used permit type doesn't have 'Exit Point' attribute.");
		}

		private void getRemoveBtn() {
			removeBtn = Browser.getInstance().getHtmlObject(".class", "Html.A",
					".text", "<<Remove", top)[0];
		}

		private boolean isAddBtnExisting(){
			return Browser.getInstance().checkHtmlObjectExists(".class", "Html.A",".text", "Add>>", top);
		}
		
		private void getAddBtn() {
			addBtn = Browser.getInstance().getHtmlObject(".class", "Html.A",
					".text", "Add>>", top)[0];
		}

		private void getMainList() {
			mainList = (ISelect) Browser.getInstance().getHtmlObject(
					".class", "Html.SELECT", ".id",
					new RegularExpression("\\w+_MainList_\\d+", false), top)[0];
		}

		private void getSelectedList() {
			selectedList = (ISelect) Browser.getInstance().getHtmlObject(
					".class", "Html.SELECT", ".id",
					new RegularExpression("\\w+_SelectedList", false), top)[0];
		}

		public void addOptions(String[] options) {
			if (options != null && options.length > 0) {
				mainList.select(options);
				addBtn.click();
			}
		}
	}
	
	private String[] getMultiSelectedOptions(String val) {
		return val.split(",");
	}
	
	private void setExitsByMultiSelection(String val, int rowIndex){
		if (val != null && !val.trim().isEmpty()) {
			IHtmlObject[] permitTypeTables = Browser.getInstance().getHtmlObject(".class", "Html.table", ".id", "entrance_permit_types");
			IHtmlObject[] multiSelectTables = browser.getHtmlObject(".class","Html.table",".text",new RegularExpression("^Applicable Exits.*", false),permitTypeTables[0]);
			
			IHtmlObject permitTypeTable = null;
			if(multiSelectTables.length > 0 && multiSelectTables[rowIndex*2] != null){
				permitTypeTable = multiSelectTables[rowIndex*2];
			}
			
			String[] options = getMultiSelectedOptions(val);
			MultiSelectComponent component = new MultiSelectComponent(
					permitTypeTable);
			if(IS_ADD_BTN_EXISTING){
				component.addOptions(options);
			}
		}
	}
}
