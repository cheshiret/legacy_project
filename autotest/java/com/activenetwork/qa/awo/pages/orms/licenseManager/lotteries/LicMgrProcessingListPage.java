package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeLotteryScheduleInfo;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Sep 4, 2013
 */
public class LicMgrProcessingListPage extends LicMgrLotteriesCommonPage {
	private static LicMgrProcessingListPage _instance = null;
	
	private LicMgrProcessingListPage() {}
	
	public static LicMgrProcessingListPage getInstance() {
		if(_instance == null) _instance = new LicMgrProcessingListPage();
		
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(addLotterySchedule());
	}
	
	private Property[] addLotterySchedule() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Add Lottery Schedule");
	}
	
	private Property[] status() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryScheduleSearchCriteria-\\d+\\.status", false));
	}
	
	private Property[] lottery() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryScheduleSearchCriteria-\\d+\\.lotteryId", false));
	}
	
	private Property[] algorithm() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryScheduleSearchCriteria-\\d+\\.algorithm", false));
	}
	
	private Property[] processingStatus() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryScheduleSearchCriteria-\\d+\\.executionStatus", false));
	}
	
	private Property[] licenseYear() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryScheduleSearchCriteria-\\d+\\.licenseYear", false));
	}
	
	private Property[] go() {
		return Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("^(Go|Search)$", false));
	}
	
	private Property[] lotteryScheduleListTableObject() {
		return Property.toPropertyArray(".id", new RegularExpression("grid_\\d+", false));
	}
	
	private Property[] id(String id) {
		return Property.toPropertyArray(".class", "Html.A", ".text", id);
	}
	
	public void clickAddLotterySchedule() {
		browser.clickGuiObject(addLotterySchedule());
	}
	
	public void selectStatus(String status) {
		browser.selectDropdownList(status(), status);
	}
	
	public void selectLottery(String product) {
		browser.selectDropdownList(lottery(), product);
	}
	
	public void selectAlgorithm(String algorithm) {
		browser.selectDropdownList(algorithm(), algorithm);
	}
	
	public void selectProcessingStatus(String status) {
		browser.selectDropdownList(processingStatus(), status);
	}
	
	public void selectLicenseYear(String licYear) {
		if(StringUtil.isEmpty(licYear)){
			browser.selectDropdownList(licenseYear(), 0);
		}else{
			browser.selectDropdownList(licenseYear(), licYear);
		}
	}
	
	public void clickGo() {
		browser.clickGuiObject(go());
	}
	
	private IHtmlObject[] getLotteryScheduleListTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(lotteryScheduleListTableObject());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Lottery Schedule list table object.");
		
		return objs;
	}
	
	private static final String LOTTERY_SCH_ID_COL = "Lottery Sch ID";
	private static final String STATUS_COL = "Status";
	private static final String LOTTERY_COL = "Lottry";
	private static final String ALGORITHM_COL = "Algorithm";
	private static final String SCHEDULE_COL = "Schedule";
	private static final String LICENSE_YEAR_COL = "License Year";
	private static final String PROCESSING_STATUS_COL = "Processing Status";
	
	public void searchLotteryScheduleByStatus(String status) {
		this.selectStatus(status);
		this.selectLicenseYear(StringUtil.EMPTY);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void searchActiveLotteryScheduleByLotteryPrdAndLicenseYear(String prd, String licenseYear){
		this.selectStatus("Active");
		this.selectLottery(prd);
		this.selectLicenseYear(licenseYear);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void searchLotterySchedule(String status, String licenseYear) {
		this.selectStatus(status);
		this.selectLicenseYear(licenseYear);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void searchLotterySchedule(PrivilegeLotteryScheduleInfo schedule) {
		if(!StringUtil.isEmpty(schedule.getStatus())) this.selectStatus(schedule.getStatus());
		if(!StringUtil.isEmpty(schedule.getLottery())) this.selectLottery(schedule.getLottery());
		if(!StringUtil.isEmpty(schedule.getAlgorithm())) this.selectAlgorithm(schedule.getAlgorithm());
		if(!StringUtil.isEmpty(schedule.getProcessingStatus())) this.selectProcessingStatus(schedule.getProcessingStatus());
		if(!StringUtil.isEmpty(schedule.getLicenseYear())) this.selectLicenseYear(schedule.getLicenseYear());
		
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public boolean isLotteryScheduleExists(String description) {
		IHtmlObject objs[] = this.getLotteryScheduleListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int descColIndex = table.findColumn(0, SCHEDULE_COL);
		int rowIndex = table.findRow(descColIndex, description);
		Browser.unregister(objs);
		
		return rowIndex > 0;
	}
	
	public String getFirstLotteryScheduleIDInList(){
		IHtmlObject objs[] = this.getLotteryScheduleListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		int idColIndex = table.findColumn(0, LOTTERY_SCH_ID_COL);
		int rowCount = table.rowCount();
		String id = "";
		if(rowCount >1){
			id = table.getCellValue(1, idColIndex);
		}
		Browser.unregister(objs);
		return id;
	}
	
	public String getLotteryScheduleID(String description) {
		IHtmlObject objs[] = this.getLotteryScheduleListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int descColIndex = table.findColumn(0, SCHEDULE_COL);
		int rowIndex = table.findRow(descColIndex, description);
		int idColIndex = table.findColumn(0, LOTTERY_SCH_ID_COL);
		String id = table.getCellValue(rowIndex, idColIndex);
		
		Browser.unregister(objs);
		return id;
	}
	
	public List<String> getLotteryScheduleInfo(){
		IHtmlObject objs[] = this.getLotteryScheduleListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> scheduleInfo = table.getRowValues(1);
		Browser.unregister(objs);
		return scheduleInfo;
	}
	
	public void clickLotteryScheduleID(String id) {
		browser.clickGuiObject(id(id));
	}
	
	public void clickLotterySchedule(String desc) {
		String id = this.getLotteryScheduleID(desc);
		this.clickLotteryScheduleID(id);
	}
	
	public boolean isLotteryScheduleIdLinkExist(String id){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", id);
	}
	
	public boolean isAddScheduleButtonExist(){
		return browser.checkHtmlObjectExists(addLotterySchedule());
	}
}
