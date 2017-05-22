/*
 * $Id: InvMgrFacilityBookingRulesPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.SeasonData;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * TODO: enter class description
 * 
 * @author CGuo
 */
public class InvMgrFacilityBookingRulesPage extends InventoryManagerPage {

	/**
	 * Script Name   : InvMgrFacilityBookingRulesPage
	 * Generated     : Jun 14, 2005 4:50:22 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2005/06/14
	 */
	private final String noMatchesFound="No Matches Found";
	
	private static InvMgrFacilityBookingRulesPage _instance = null;

	public static InvMgrFacilityBookingRulesPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrFacilityBookingRulesPage();
		}

		return _instance;
	}

	protected InvMgrFacilityBookingRulesPage() {
	}

	/** Determine if the Inventory Manager BookingRules page exists
	 * 	Booking rules page is same as the Seasons page
	 * */
	public boolean exists() {
		RegularExpression rex = new RegularExpression("^( )*Season ID ?Active ?Season Type.*", false);
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", rex);
	}
	
	private IHtmlObject[] getSeasonTable(){
		RegularExpression rex = new RegularExpression("^( )*Season ID ?Active ?Season Type.*", false);
		IHtmlObject[] objs = browser.getTableTestObject( ".text", rex);
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get season table object.");
		}
		return objs;
	}

	/**Click Closures*/
	public void clickClosures() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Closures");
	}

	/**Click Add New button*/
	public void clickAddNew() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New", true);
	}

	/**
	 * Select "SearchActive"
	 * @param item
	 */
	public void selectSearchActive(String item) {
		browser.selectDropdownList(".id", "search_active", item);
	}

	/**
	 * Select "Season type"
	 * @param type
	 */
	public void selectSeasonType(String type) {
		browser.selectDropdownList(".id", "search_season_type", type);
	}

	/**
	 * Select "Loop Area"
	 * @param loop
	 */
	public void selectLoopArea(String loop) {
		if(loop.equalsIgnoreCase("All"))
			browser.selectDropdownList(".id", "search_area", 0);
		else
			browser.selectDropdownList(".id", "search_area", loop);
	}

	/**
	 * Select "Site Type"
	 * @param type
	 */
	public void selectSiteType(String type) {
		if(type.equalsIgnoreCase("All"))
			this.selectSiteType(0);
		else
			browser.selectDropdownList(".id", "search_site_type", type);
	}
	
	public void selectSiteType(int index){
		browser.selectDropdownList(".id", "search_site_type", index);
	}
	
	public void selectDateType(String type){
		browser.selectDropdownList(".id", "date_type", type);
	}

	/**
	 * Input "End Date"
	 * @param date
	 */
	public void setEndDate(String date) {
		browser.setTextField(".id", "search_end_date_ForDisplay", date);
	}

	/**
	 * Input "Start Date"
	 * @param date
	 */
	public void setStartDate(String date) {
		browser.setTextField(".id", "search_start_date_ForDisplay", date);
	}

	/**click Search*/
	public void clickSearch() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^Search$", false));
	}

	/**Click Delete button*/
	public void clickDelete() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Delete");
	}

	/**Click Acitvate button*/
	public void clickActivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Activate");
	}

	/**Click Deactivate Button*/
	public void clickDeactivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate");
	}
	
	public void selectSeasonType(int index){
		browser.selectDropdownList(".id", "search_season_type", index);
	}
	
	public void selectSearchActive(int index){
		browser.selectDropdownList(".id", "search_active",index);
	}
	
	/**
	 * Search season by season data
	 * @param sd
	 */
	public void searchSeason(SeasonData sd) {
		if(StringUtil.isEmpty(sd.m_searchStatus) && !StringUtil.isEmpty(sd.m_Status)) {
			sd.m_searchStatus = sd.m_Status;
		}
	  	if(!StringUtil.isEmpty(sd.m_searchStatus)){
	  		this.selectSearchActive(sd.m_searchStatus);
	  	}else{
	  		this.selectSearchActive(0);
	  	}
	  	
	  	if(!StringUtil.isEmpty(sd.m_PrdCategory))
	  		this.selectPrdCategory(sd.m_PrdCategory);
	  	
	  	if(!StringUtil.isEmpty(sd.m_SeasonType)){
	  		this.selectSeasonType(sd.m_SeasonType);
	  	}else{
	  		this.selectSeasonType(0);
	  	}
	  	  	
	  	if(!StringUtil.isEmpty(sd.m_Loop))
	  	  	this.selectLoopArea(sd.m_Loop);
	  
	  	if(!StringUtil.isEmpty(sd.m_SiteType)){
	  	  	this.selectSiteType(sd.m_SiteType);
	  	}else{
	  		this.selectSiteType(0);
	  	}
	  	
	  	if(!StringUtil.isEmpty(sd.m_DateType)){
	  		this.selectDateType(sd.m_DateType);
	  		this.setStartDate(sd.m_FromDate);
			this.setEndDate(sd.m_ToDate);
	  	}
	  	
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void searchSeason(String seasonType, String loop, String siteType, String prdCategory, String startFrom, String startTo) {
		browser.selectDropdownList(".id", "search_active", 0);//IMPORTANT: should consider Active/Inactive status
		if(!StringUtil.isEmpty(prdCategory))
	  		this.selectPrdCategory(prdCategory);
	  	
	  	if(!StringUtil.isEmpty(seasonType))
	  	  	this.selectSeasonType(seasonType);
	  	
	  	if(!StringUtil.isEmpty(loop))
	  	  	this.selectLoopArea(loop);
	  
	  	if(!StringUtil.isEmpty(siteType))
	  	  	this.selectSiteType(siteType);
		
	  	if(!StringUtil.isEmpty(startFrom))
	  		this.setStartDate(startFrom);
	  	
	  	if(!StringUtil.isEmpty(startTo))
	  		this.setEndDate(startTo);
	  	
	  	this.clickSearch();
	  	this.waitLoading();
	}
	
	/** Select the check box of the first season */
	public void selectFirstSeasonCheckBox() {
	  browser.selectCheckBox(".id", "row_0_checkbox");
	}

	/**
	 * Select Season check box via ID
	 * @param id
	 */
	public void selectSeasonCheckBoxByID(String id) {
		browser.selectCheckBox(".value", id);
	}
	
	public void selectAllSeasonCheckBoxs() {
		browser.selectCheckBox(".name", "all_slct");
	}
	
	/**
	 * Select seasons check box via ID
	 * @param ids: ID list
	 */
	public void selectSeasonsCheckBoxByIDs(String[] ids) {
	  	if(ids==null || ids.length<1) //do nothing
	  	  	return;
	  
	  	for(int i=0;i<ids.length;i++) {
	  		selectSeasonCheckBoxByID(ids[i]);
	  	}
	}
	
	/**
	 * Click Season ID
	 * @param id
	 */
	public void clickSeasonID(String id) {
		browser.clickGuiObject(".class", "Html.A", ".text", id, true);
	}

	/**
	 * Click #Sites via specific season ID
	 * @param seasonID
	 */
	public void clickNumSites(String seasonID){
		RegularExpression rex = new RegularExpression("\"SeasonSites.do\".+\"" + seasonID + "\"", false);
		browser.clickGuiObject(".class", "Html.A", ".href", rex, true);
	}
	
	public String getSiteOrSlipNumInSeason(String seasonID){
		RegularExpression rex = new RegularExpression("\"viewSeasonSites\".+\"" + seasonID + "\"", false);
		return browser.getObjectText(".class","Html.A",".href",rex);
	}
	
	/**
	 * Delete special season by season ID
	 * @param sID
	 */
	public void deleteSeason(String sID) {
		this.selectSeasonCheckBoxByID(sID);
		this.clickDelete();
	}
	
	/**
	 * Deactivate one or more seasons by season ID
	 * @param id
	 */
	public void deactivateSeason(String... seasonIDs) {
		this.selectSeasonsCheckBoxByIDs(seasonIDs);
		this.clickDeactivate();
		ajax.waitLoading();
	}
	
	/**
	 * Activate one or more seasons by ID
	 * @param seasonIDs
	 */
	public void activateSeason(String... seasonIDs) {
		this.selectSeasonsCheckBoxByIDs(seasonIDs);
		this.clickActivate();
		Browser.sleep(OrmsConstants.SHORT_SLEEP_BEFORE_CHECK);
//		this.waitExists(); 
	}
	
	/**
	 * Retrieve the status message after performing actions.
	 * @return - status message
	 */
	public String getStatusMessage() {
	  	String toReturn = "";
	  	IHtmlObject[] objs = browser.getHtmlObject(".id", "statusMessages");
	  	if(objs.length > 0) {
	  	  	String msg=objs[0].getProperty(".text").toString();
	  	  	toReturn = msg;
	  	}
	  	Browser.unregister(objs);
	  	return toReturn;	  	
	}
	
	/**
	 * Click on Next Results page when it available.
	 * @return true - available; false - not available
	 */
	public boolean nextResult() {
	  	IHtmlObject[] objs = browser.getHtmlObject(".id", "resultNext");
		
		boolean toReturn = false;
		if (objs.length > 0&& !(objs[0].getProperty(".className")) .equals("disabled")) {
			ILink link = (ILink)objs[0];
			link.click();
			toReturn = true;
			this.waitLoading();
		}
		
		Browser.unregister(objs);
		return toReturn;
	}
	
	/**
	 * Is next result link available.
	 * @return
	 */
	public boolean isNext() {
	  	IHtmlObject[] objs = browser.getHtmlObject(".id", "resultNext");
	  	
	  	Browser.unregister(objs);
	  	return objs.length>0;
	}
	
	/**
	 * go to last season search result page.
	 */
	public void gotoLastResultPg() {
	  	IHtmlObject[] objs = browser.getHtmlObject(".id", "resultNext");
	  	
	  	while(this.isNext()){
	  		
	  		ILink link = (ILink)objs[0];
			link.click();
	  	  	this.waitLoading();
	  	}
	  	
	  	Browser.unregister(objs);
	}
	
	/**
	 * Retrieve the last season record's end date in page.
	 * @return - end date
	 */
	public String getLastSeasonEndDate() {
	  	IHtmlObject[] table = this.getSeasonTable();
	  	IHtmlTable tableGrid=(IHtmlTable) table[0];
	  	
	  	String colName="";
	  	int colNum=0;
	  	for(int i=0; i< tableGrid.columnCount(); i++) {
	  	  	colName = tableGrid.getCellValue(0,i).toString();
	  	  	if(colName.equalsIgnoreCase("End Date"))
	  	  	  	colNum=i;// get the column number
	  	}
	  	
	  	int rowNum=tableGrid.rowCount();
	  	String endDate="";
	  	endDate=tableGrid.getCellValue(rowNum-1, colNum).toString();
	  	
	  	Browser.unregister(table);
	  	return endDate;
	}
	
	/**
	 * Get the end date of the first season in the list.
	 * @return
	 */
	public String getFirstSeasonEndDate() {
		IHtmlObject[] table = this.getSeasonTable();
	  	IHtmlTable tableGrid=(IHtmlTable) table[0];
	  	
	  	int colNum = tableGrid.findColumn(0, "End Date");
	  	String endDate = tableGrid.getCellValue(1, colNum);
	  	logger.info("First season end date: " + endDate);
	  	Browser.unregister(table);
	  	return endDate;
	}
	/**
	 * Get season number.
	 */
	public int getSeasonNum() {
		int seasonNum = 0;
		IHtmlObject[] table = this.getSeasonTable();
		if (table.length > 0) {
			IHtmlTable tableGrid=(IHtmlTable) table[0];
			seasonNum = tableGrid.rowCount()-1;
		}
		Browser.unregister(table);
		return seasonNum;
	}
	
	/**
	 * Get season id.
	 */
	public String getSeasonID() {
		String seasonID = "";
	  	IHtmlObject[] table = this.getSeasonTable();
	  	if(table.length>0){
		  	IHtmlTable tableGrid=(IHtmlTable) table[0];
		  	seasonID = tableGrid.getCellValue(1, 1);
	  	} else {
	  		logger.error("Can't get season id.");
	  	}
	  	
	  	Browser.unregister(table);
	  	return seasonID;
	}
	
	/**Check if closure tab exist
	 * @return
	 */
	public boolean isClosureTabExist() {
	  return browser.checkHtmlObjectExists(".className", "tabanchor", ".text", "Closures");
	}
	
	public String getSeasonInfo() {
	   IHtmlObject[] objs=this.getSeasonTable();
	   String season=objs[0].getProperty(".text").toString();

	   Browser.unregister(objs);
	   return season;
	}
	
	/**
	 * Parse the seasons table and return the whole seasons' detail
	 * @return - the list of seasons 
	 */
	public List<SeasonData> parseSeasonDetailsTable() {
		List<SeasonData> seasons = new ArrayList<SeasonData>();
		IHtmlObject seasonTab[] = this.getSeasonTable();
		
		int rowNum = ((IHtmlTable)seasonTab[0]).rowCount();
		int colNum = ((IHtmlTable)seasonTab[0]).columnCount();

		for(int row = 0; row < rowNum; row++) {
			SeasonData season = new SeasonData();
			for(int col = 0; col < colNum; col++) {
				String titleName[] = new String[colNum];
				
				titleName[col] = ((IHtmlTable)seasonTab[0]).getCellValue(0, col).trim();

				if(row >= 1){
					if(titleName[col].length() > 0){
						if(titleName[col].equals("Season ID")) {
							season.m_SeasonID = ((IHtmlTable)seasonTab[0]).getCellValue(row, col).toString().trim();
						}
						if(titleName[col].equals("Active")) {
							season.m_Status = ((IHtmlTable)seasonTab[0]).getCellValue(row, col).toString().trim();
						}
						if(titleName[col].equals("Season Type")) {
							season.m_SeasonType = ((IHtmlTable)seasonTab[0]).getCellValue(row, col).toString().trim();
						}
						if(titleName[col].matches("Loop/(Dock/)?Area")) {
							season.m_Loop = ((IHtmlTable)seasonTab[0]).getCellValue(row, col).toString().trim();
						}
						if(titleName[col].matches("Site(/Slip)? Type")) {
							season.m_SiteType = ((IHtmlTable)seasonTab[0]).getCellValue(row, col).toString().trim();
						}
						if(titleName[col].equals("Start Date")) {
							season.m_StartDate = ((IHtmlTable)seasonTab[0]).getCellValue(row, col).toString().trim();
						}
						if(titleName[col].equals("End Date")) {
							season.m_EndDate = ((IHtmlTable)seasonTab[0]).getCellValue(row, col).toString().trim();
						} 
						if(titleName[col].equals("Prod Category")) {//add this for Slip season
							season.m_PrdCategory = ((IHtmlTable)seasonTab[0]).getCellValue(row, col).toString().trim();
						}
						if(titleName[col].equals("Display Name")) {//add this for Slip season
							season.m_DisplayName = ((IHtmlTable)seasonTab[0]).getCellValue(row, col).toString().trim();
						}
					}
				}
			}
			if(season.m_SeasonID != null && !season.m_SeasonID.equals("")) {
				seasons.add(season);
			}
		}
		return seasons;
	}
	
	public void gotoSeasonDetails(String seasonID) {
	  browser.clickGuiObject(".class", "Html.A", ".text", seasonID, true);
	}
	
	/**
	 * Get warning message in season list page
	 * @return
	 */
	public String getWarningMessage(){
		String warningMessage = "";
		
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if(objs.length>0){
			warningMessage =  objs[0].getProperty(".text").toString();
		}
		
		Browser.unregister(objs);
		return warningMessage;
	}
		
	/** Click the button 'View Change Request Items'*/
	public void clickViewChangeRequestItems(){
		RegularExpression rex = new RegularExpression("\"SearchCRIListFromViewCRIButton\".*", false);
		browser.clickGuiObject(".class", "Html.A", ".href",rex);
	}
	
	/**
	 * Find how many seasons in season page
	 * @return
	 */
    public int getNumOfSeason(){
    	int seasonNum = 0;
    	IHtmlObject[] objs=this.getSeasonTable();
    	if(null==objs || objs.length<1){
    		throw new ObjectNotFoundException("No season table is found");
    	}
    	IHtmlTable table=(IHtmlTable)objs[0];
    	seasonNum = table.rowCount()-2;
    	
    	Browser.unregister(objs);
    	return seasonNum;
    }
    
    public void selectPrdCategory(String prdCategory) {
    	browser.selectDropdownList(".id", "product_category", prdCategory);
    }
    
    /**
     * This method was used to return is there any season found according to warning message
     * @return
     */
    public boolean isSeasonFound() {
    	boolean found=true;
    	IHtmlObject[] objs = browser.getHtmlObject(".id", "statusMessages");
		if(objs.length>0){
			String msg =  objs[0].getProperty(".text").toString();
			if(msg.matches(noMatchesFound))
				found=false;
		}
		Browser.unregister(objs);
    	return found;
    }
    
    public List<String> getSeasonIDsByStartAndEndDate(String seasonType, String startDate, String endDate) {
    	List<String> ids=new ArrayList<String>();
    	List<SeasonData> seasons=this.parseSeasonDetailsTable();
    	for(SeasonData s:seasons) {
    		if(s.m_SeasonType.equals(seasonType)
    		&& DateFunctions.compareDates(s.m_StartDate, startDate)==0
    		&& DateFunctions.compareDates(s.m_EndDate, endDate)==0) {
    			logger.info("Found season id:"+s.m_SeasonID+" with start date:"+s.m_StartDate+" and end date:"+s.m_EndDate);
    			ids.add(s.m_SeasonID);
    		}	
    	}
    	return ids;
    }
    
    /**
     * This method was used to check season ID existed on page or not. 
     * Some times we need to click Next page to get season
     * @param id
     * @return
     */
    public boolean isSeasonIDExisted(String id) {
    	return browser.checkHtmlObjectExists(".class", "Html.A", ".text", id);
    }
    
    public boolean isErrorMsgExists() {
    	return browser.checkHtmlObjectExists(".id", new RegularExpression("paymentrefund.search.notfound", false)) || browser.checkHtmlObjectExists(".class", "Html.DIV", ".className", "message msgerror");
    }
    
    public String getErrorMessage(){
    	String msg = browser.getObjectText(".id", new RegularExpression("paymentrefund.search.notfound", false));
    	if(StringUtil.isEmpty(msg)) {
    		//Deletion of season schedule 43486061 is not allowed. The following slip reservations M2-1193 have booked inventory against corresponding slips.
    		msg = browser.getObjectText(".class", "Html.DIV", ".className", "message msgerror");
    	}
    	
    	return msg;
    }
    
    /**
     * Check expect season exist or not.
     * @param season
     * @return
     */
    public boolean checkSeasonExist(SeasonData season){
    	boolean isExist = true;
    	this.searchSeason(season);
    	String msg = this.getErrorMessage();
    	if(StringUtil.isEmpty(msg)){// exist
    		return isSeasonIDExisted(season.m_SeasonID);
    	} else {
    		isExist = false;
    		logger.error("Expect season does not exist!");
    	}
    	return isExist;
    }
    
    public String getFirstSeasonAssignedNums() {
		IHtmlObject[] table = this.getSeasonTable();
	  	IHtmlTable tableGrid=(IHtmlTable) table[0];
	  	
	  	int colNum = tableGrid.findColumn(0, "# Sites/Slips");
	  	String nums = tableGrid.getCellValue(1, colNum);
	  	logger.info("First season Sites/Slips number: " + nums);
	  	Browser.unregister(table);
	  	return nums;
	}
    
    private List<String> getColumnListValues(String columnName){
    	IHtmlObject[] objs = this.getSeasonTable();
    	IHtmlTable table = (IHtmlTable)objs[0];
    	
    	int col = table.findColumn(0, columnName);
    	List<String> colValues = table.getColumnValues(col);
    	colValues.remove(0);
    	Browser.unregister(objs);
    	return colValues;
    }
    
    public List<String> getLoopListValues(){
    	return this.getColumnListValues("Loop/Dock/Area");
    }
    
    public List<String> getSeasonTypeListValues(){
    	return this.getColumnListValues("Season Type");
    }
    
    public List<String> getActiveListValues(){
    	return this.getColumnListValues("Active");
    }
    
    public List<String> getProdCateListValues(){
    	return this.getColumnListValues("Prod Category");
    }
    
    public List<String> getSlipTypeListValues(){
    	return this.getColumnListValues("Site/Slip Type");
    }
    
    public List<String> getStartDateListValues(){
    	return this.getColumnListValues("Start Date");
    }
    
    public List<String> getEndDateListValues(){
    	return this.getColumnListValues("End Date");
    }
        
	/**
	 * verify whether or not the special season is active status.
	 * @param season - season ID
	 * @return true - active; false - inactive
	 */
	public boolean isSeasonActive(String seasonID) {
	  	boolean toReturn=false;
	  	String status  = "";
	  	
	  	IHtmlObject[] objs = browser.getHtmlObject(".class","Html.TABLE",".text",
	    	new RegularExpression("^Season ID Active.*",false));
	    IHtmlTable tableGrid = (IHtmlTable)objs[0];
	    if(tableGrid.rowCount() > 0 ) {
	    	int rowNum = tableGrid.findRow(1, seasonID);
	    	status = tableGrid.getCellValue(rowNum, 2);
	    }
	  	Browser.unregister(objs);
	  	
	  	if(status.equalsIgnoreCase("Yes")){
	  	  	toReturn=true;
	  	}
	  	return toReturn;
	}
	
	private static final String SEASON_ID_COL = "Season ID";
	private static final String ACTIVE_COL = "Active";
	private static final String SEASON_TYPE_COL = "Season Type";
	private static final String PROD_CATEGORY_COL = "Prod Category";
	private static final String DISPLAY_NAME_COL = "Display Name";
	private static final String LOOP_DOCK_AREA_COL = "Loop/Dock/Area";
	private static final String SITE_SLIP_TYPE_COL = "Site/Slip Type";
	private static final String START_DATE_COL = "Start Date";
	private static final String END_DATE_COL = "End Date";
	private static final String NUM_OF_SITES_SLIPS_COL = "# Sites/Slips";
	
	public SeasonData getSeasonInfo(String seasonID) {
		IHtmlObject objs[] = getSeasonTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int idColumnIndex = table.findColumn(0, "Season ID");
		int rowIndex = table.findRow(idColumnIndex, seasonID);
		List<String> values = table.getRowValues(rowIndex);
		
		SeasonData season = new SeasonData();
		season.m_SeasonID = seasonID;
		season.m_Status = values.get(table.findColumn(0, ACTIVE_COL));
		season.m_SeasonType = values.get(table.findColumn(0, SEASON_TYPE_COL));
		season.m_PrdCategory = values.get(table.findColumn(0, PROD_CATEGORY_COL));
		season.m_DisplayName = values.get(table.findColumn(0, DISPLAY_NAME_COL));
		season.m_Loop = values.get(table.findColumn(0, LOOP_DOCK_AREA_COL));
		season.m_SiteType = values.get(table.findColumn(0, SITE_SLIP_TYPE_COL));
		season.m_StartDate = values.get(table.findColumn(0, START_DATE_COL));
		season.m_EndDate = values.get(table.findColumn(0, END_DATE_COL));
		//season site/slip assign number
		
		Browser.unregister(objs);
		return season;
	}
	
	public boolean compareSeasonInfo(SeasonData expected) {
		this.searchSeason(expected);
		SeasonData actual = this.getSeasonInfo(expected.m_SeasonID);
		boolean result = true;
		result &= MiscFunctions.compareResult(SEASON_ID_COL, expected.m_SeasonID, actual.m_SeasonID);
		result &= MiscFunctions.compareResult(ACTIVE_COL, expected.m_Status.equalsIgnoreCase(OrmsConstants.INACTIVE_STATUS) ? OrmsConstants.NO_STATUS : OrmsConstants.YES_STATUS, actual.m_Status);
		result &= MiscFunctions.compareResult(SEASON_TYPE_COL, expected.m_SeasonType, actual.m_SeasonType);
		result &= MiscFunctions.compareResult(PROD_CATEGORY_COL, expected.m_PrdCategory, actual.m_PrdCategory);
		if(actual.m_PrdCategory.equalsIgnoreCase("Slip")) {
			result &= MiscFunctions.compareResult(DISPLAY_NAME_COL, expected.m_DisplayName, actual.m_DisplayName);
		}
		result &= MiscFunctions.compareResult(LOOP_DOCK_AREA_COL, expected.m_Loop, actual.m_Loop);
		result &= MiscFunctions.compareResult(SITE_SLIP_TYPE_COL, expected.m_SiteType, actual.m_SiteType);
		result &= MiscFunctions.compareResult(START_DATE_COL, expected.m_StartDate, actual.m_StartDate);
		result &= MiscFunctions.compareResult(END_DATE_COL, expected.m_EndDate, actual.m_EndDate);
		
		return result;
	}
}
