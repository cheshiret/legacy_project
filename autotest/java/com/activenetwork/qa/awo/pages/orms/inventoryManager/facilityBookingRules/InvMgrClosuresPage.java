/*
 * $Id: InvMgrClosuresPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Closure;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * TODO: enter class description
 * 
 * @author CGuo
 */
public class InvMgrClosuresPage extends InvMgrCommonTopMenuPage {

	/**
	 * Script Name   : InvMgrClosuresPage
	 * Generated     : Jun 14, 2005 4:58:59 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2005/06/14
	 */

	private static InvMgrClosuresPage _instance = null;

	public static InvMgrClosuresPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrClosuresPage();
		}

		return _instance;
	}

	protected InvMgrClosuresPage() {
	}

	/** Determine if the FieldManager Order Summary page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.text", ".id", "closure_id");
	}

	/**
	 * Input Closure ID
	 * @param id
	 */
	public void setClosureID(String id) {
		browser.setTextField(".id", "closure_id", id);
	}

	/**
	 * Select Date Type
	 * @param type
	 */
	public void selectDateType(String type) {
		browser.selectDropdownList(".id", "rangeType", type);
	}

	/**
	 * Input Start Date
	 * @param date
	 */
	public void setStartDate(String date) {
		browser.setTextField(".id", "rangeStart_ForDisplay", date);
	}
	
	public String getSCStartDate(){
		return browser.getTextFieldValue(".id", "rangeStart_ForDisplay");
	}
	
	public String getSCPrdCategoryValues() {
		return browser.getDropdownListValue(".id", "product_category");
	}

	/**
	 * Input End Date
	 * @param date
	 */
	public void setEndDate(String date) {
		browser.setTextField(".id", "rangeEnd_ForDisplay", date);
	}

	/**
	 * Select Closure Type
	 * @param type
	 */
	public void selectClosureType(String type) {
		browser.selectDropdownList(".id", "closureType", type);
	}
	
	public String getSCClosureType(){
		return browser.getDropdownListValue(".id", "closureType");
	}

	/**
	 * Select Recurring item
	 * @param item
	 */
	public void selectRecurring(String item) {
		browser.selectDropdownList(".id", "recurring", item);
	}

	/**
	 * Select Affected Reservations
	 * @param item
	 */
	public void selectAffectedReservations(String item) {
		if(item.equalsIgnoreCase("All")){
			browser.selectDropdownList(".id", "affected_res", 0);
		}else{
			browser.selectDropdownList(".id", "affected_res", item);
		}
	}

	
	/**
	 * Select Instructions item
	 * @param inst
	 */
	public void selectInstruction(String inst) {
		if(inst.equalsIgnoreCase("All")){
			browser.selectDropdownList(".id", "res_instruction", 0);
		}else{
			browser.selectDropdownList(".id", "res_instruction", inst);
		}
	}

	/**
	 * Select Application
	 * @param app
	 */
	public void selectApplication(String app) {
		if(app.equalsIgnoreCase("All")){
			browser.selectDropdownList(".id", "create_application", 0);
		}else{
			browser.selectDropdownList(".id", "create_application", app);
		}
	}

	/**Click Activate*/
	public void clickActivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Activate");
	}

	/**Click Deactivate*/
	public void clickDeactivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate");
	}

	/**Click Add New*/
	public void clickAddNew() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New");
	}

	/**Click GO button*/
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}

	/**
	 * Search closure by date and closure information
	 * @param date
	 * @param closure
	 */
	public void searchClosure(String date, Closure closure) {
		this.setClosureID("");
		
		this.selectDateType(date);
		this.setStartDate(closure.startDate);
		this.setEndDate(closure.endDate);
		this.selectClosureType(closure.type);

		this.clickGo();
		this.waitLoading();
	}
	
	/**
	 * Search closure by closureID
	 * @param closuresID
	 */
	public void searchClosure(String closuresID) {
		this.setClosureID(closuresID);
		this.clickGo();
		this.waitLoading();
	}
	
	public void searchClosureByType(String type){
		this.selectClosureType(type);
		this.clickGo();
		this.waitLoading();
	}

	/**
	 * Activate Closure by index of activate
	 * @param index
	 * @return
	 * @throws PageNotFoundException
	 */
	public String activateClosure(int index) throws PageNotFoundException {
		String toReturn = this.selectClosureByIndex(index);
		this.clickActivate();
		return toReturn;
	}

	/**
	 * Deactivate closure by index of activate
	 * @param index
	 * @return
	 */
	public String deactivateClosure(int index){
		String toReturn = this.selectClosureByIndex(index);
		this.clickDeactivate();
		return toReturn;
	}

	/**
	 * Select Closure by index of closure
	 * @param index
	 * @return
	 * @throws PageNotFoundException
	 */
	public String selectClosureByIndex(int index) throws PageNotFoundException {
		IHtmlObject[] objs = browser.getCheckBox(".id", new RegularExpression("row_"+index+"_checkbox", false));
		String toReturn = objs[0].getProperty(".value").toString();
		
		if ((Boolean.parseBoolean(objs[0].getProperty(".checked"))) == false){
			ICheckBox checkBox = (ICheckBox)objs[0];
		    checkBox.click();
		}
		Browser.unregister(objs);

		return toReturn;
	}

	/**
	 * Select Closures By Closure IDs
	 * @param id
	 */
	public void selectClosuresCheckBoxByIDs(String[] ids) {
	  	if(ids==null || ids.length<1) //do nothing
	  	  	return;
	  	
	  	for(int i=0;i<ids.length;i++) {   
//	  		this.searchByClosureID(ids[i]);  //For the ids not in the same page, search method is not suitable for select more than one ids, please do not use this!
	  		this.selectClosureCheckBoxByID(ids[i]);
	  	}
	  	
	}
	
	/**
	 * Select Closure check box via ID
	 * @param closureID
	 */
	public void selectClosureCheckBoxByID(String closureID){
		browser.selectCheckBox(".value", closureID);
	}

	/**
	 * Click Closure ID
	 * @param id
	 */
	public void clickClosureID(String id) {
		browser.clickGuiObject(".class", "Html.A", ".text", id, true);
	}

	/**
	 * Active one or more Closures by closure ID
	 * @param closureIDs
	 */
	public void activeClosures(String... closureIDs){
		this.selectClosuresCheckBoxByIDs(closureIDs);
		this.clickActivate();
	}

	public void deactivateClosureByID(String id){
		this.searchByClosureID(id);
		this.deactivateClosures(id);
		this.waitLoading();
	}

	/**
	 * Deactivate Closures by closure IDs
	 * @param closureIDs
	 */
	public void deactivateClosures(String... closureIDs){
		this.selectClosuresCheckBoxByIDs(closureIDs);
		this.clickDeactivate();
	}

	/**
	 * Get closure ID by index of closure
	 * @param index
	 * @return
	 * @throws PageNotFoundException
	 */
	public String getClosureId(int index) throws PageNotFoundException {
		IHtmlObject[] objs = browser.getCheckBox(".id", new RegularExpression("row_"+index+"_checkbox", false));
		String toReturn = objs[0].getProperty(".value").toString();
		Browser.unregister(objs);

		return toReturn;
	}
	
	/**
	 * Get closure ID in InvMgrClosuresPage
	 * @return
	 * @throws PageNotFoundException
	 */
	public String getClosureId() throws PageNotFoundException {
		String closureId = "";
		String toReturn = "";
		StringBuffer toReturnBuffer = new StringBuffer();
		IHtmlObject[] objs = browser.getCheckBox(".id", new RegularExpression("row_\\d+_checkbox", false));
		for(int i=0; i<objs.length; i++){
		    closureId = objs[i].getProperty(".value").toString();
		    toReturnBuffer.append(toReturn+" "+closureId);
		    //toReturn  = toReturnBuffer.toString();
		}

		Browser.unregister(objs);
		toReturn  = toReturnBuffer.toString().trim();

		return toReturn;
	}
	
	public IHtmlObject[] getClosureListTable(){
//		HtmlObject[] objs=browser.getTableTestObject(".text", new RegularExpression("Closure ID Active Closure Type Start Date End Date.*", false));
		IHtmlObject[] objs=browser.getTableTestObject(".text", new RegularExpression("Closure ID Active Closure Type.*", false));
		return objs;
	}
	
	private List<String> getColumnValueByName(String name){
		IHtmlObject[] objs = getClosureListTable();
		if(objs.length < 0){
			throw new ItemNotFoundException("Can't find closure table!");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		int col = table.findColumn(0, name);
		if(col < 0){
			throw new ItemNotFoundException("Can't find column by given name "+name);
		}
		
		List<String> colList = table.getColumnValues(col);
		return colList;
	}
	
	public List<String> getClosureIDsByTypePrdComments(String comment, String type, String productCat){
		this.selectClosureType(type);
		this.selectProductCategory(productCat);
		this.clickGo();

		List<String> commentList = this.getColumnValueByName("Comments");
		List<String> idList = this.getColumnValueByName("Closure ID");
		List<String> statusList = this.getColumnValueByName("Active");
		
		List<String> returnIDList = new ArrayList<String>();
		
		for(int i=0; i<idList.size(); i++){
			if(comment.equalsIgnoreCase(commentList.get(i)) && "Yes".equalsIgnoreCase(statusList.get(i))){
				returnIDList.add(idList.get(i));
			}
		}
		
		return returnIDList;
	}

	public List<String> getClosureIDsByComments(String comment, String type){
		return this.getClosureIDsByTypePrdComments(comment, type, "Slip");
	}
	
	public String getClosureInfo() {
//	   HtmlObject[] objs=browser.getTableTestObject(".text", new RegularExpression("Closure ID Active Closure Type Start Date End Date Notification Date Recurring Affected Reservations Application Sites Comments.*", false));
	   IHtmlObject[] objs=browser.getTableTestObject(".text", new RegularExpression("Closure ID Active Closure Type.*", false));
	   String closure=objs[0].getProperty(".text").toString();
	   
	   Browser.unregister(objs);
	   return closure;
	}
	
	/**
	 * Get closure number.
	 */
	public int getClosureNum() {
		int closureNum = 0;
		IHtmlObject[] table = this.getClosureListTable();
		IHtmlTable tableGrid=(IHtmlTable) table[0];
		closureNum = tableGrid.rowCount()-1;

		Browser.unregister(table);
		return closureNum;
	}
	
	/**
	 * Get closure type.
	 */
	public String getClosureType() {
		String closureType = "";
	  	IHtmlObject[] table = this.getClosureListTable();
	  	if(table.length>0){
		  	IHtmlTable tableGrid=(IHtmlTable) table[0];
		  	closureType = tableGrid.getCellValue(1, 3);
	  	}else throw new ObjectNotFoundException("Object can't find");
	  	
	  	Browser.unregister(table);
	  	return closureType;
	}
	/**
	 * click next button
	 *
	 */
	public void clickNext() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Next", true);
		this.waitLoading();
	}
	
	/**
	 * check if next button exits.
	 * @return
	 */
	public boolean checkNextButtonExit() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Next");
	}
	
	/**
	 * click last button
	 *
	 */
	public void clickLast() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Last", true);
		this.waitLoading();
	}
	
	/**
	 * check if last button exits.
	 * @return
	 */
	public boolean checkLastButtonExit() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Last");
	}
	
	/**
	 * check if next button is disable
	 * @return
	 */
    public boolean checkNextButtonDisable() {
		return browser.checkHtmlObjectExists(".id", "Next", ".disabled", "true");
    }
	
    /* Select all closures check box */
	public void selectAllClosuresCheckBox(){
		  browser.clickGuiObject(".class", "Html.INPUT.checkbox", ".name", "all_slct");
	}
	
	/**Get the default value of drop down list*/
	public String getDefaultValueForDropDownList(String property) {
		return browser.getDropdownListValue(".id", property, 0);
	}
	
	/** Click season tab*/
  	public void clickSeasonsTab() {
  	  	browser.clickGuiObject(".class", "Html.A", ".text", "Seasons");
  	}
  	
	/** Click Closure tab*/
  	public void clickClosuresTab() {
  	  	browser.clickGuiObject(".class", "Html.A", ".text", "Closures");
  	}
	
	/**Get all elements of specific drop down list*/
	public String getDropDownElements(String id) {
		List<String> groups = browser.getDropdownElements(".id", id);
		StringBuffer criterias = new StringBuffer();
		for (int i = 0; i < groups.size(); i++) {
			criterias.append(groups.get(i) + "#");
		}
		return criterias.toString();
	}
	
    /**
	 * Get warning message
	 * @return
	 */
	public String getWarningMessage(){
		String warningMessage = "";
		
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if(objs.length>0){
			warningMessage =  objs[0].getProperty(".text").toString();
		}else throw new ObjectNotFoundException("Object can't find.");
		
		Browser.unregister(objs);
		return warningMessage;
	}
	
	/**
	 * Click #Sites via specific closure ID
	 * @param closureID
	 */
	public void clickNumSites(String closureID){
		RegularExpression rex = new RegularExpression("\"viewClosuresSites\".+\"" + closureID + "\"", false);
		browser.clickGuiObject(".class","Html.A",".href",rex);
	}
	
	/** Click the button 'View Change Request Items'*/
	public void clickViewChangeRequestItems(){
		RegularExpression rex = new RegularExpression("\"SearchCRIListFromViewCRIButton\".*", false);
		browser.clickGuiObject(".class", "Html.A", ".href",rex);
	}
	
	public boolean isClosureActive(String closureID) {
//		IHtmlObject objs[] = browser.getTableTestObject(".className", "listView");
		IHtmlObject objs[] = browser.getTableTestObject(".className", "searchResult");//For case DeactiveSlipClosure
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Closure table object.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowIndex = table.findRow(1, closureID);
		String status = table.getCellValue(rowIndex, 2);
		
		Browser.unregister(objs);
		return status.equals(OrmsConstants.YES_STATUS);
	}
	
	public void selectClosureProduct(String product) {
		browser.selectDropdownList(".id", "product_category", product);
	}

	public void searchClosureByProduct(String product){
		this.selectClosureProduct(product);
		this.clickGo();
		this.waitLoading();
	}

	public String getFirstClosureIDByProduct(String product){
		this.searchClosureByProduct(product);
		String closureID = this.getClosureId(0);// get first closure ID
		return closureID;
	}
	
	public List<String> getDateTypeValues() {
		return browser.getDropdownElements(".id", "rangeType");
	}
	
	public List<String> getClosureTypeValues() {
		return browser.getDropdownElements(".id", "closureType");
	}
	
	public List<String> getPrdCategoryValues() {
		return browser.getDropdownElements(".id", "product_category");
	}
	
	public List<String> getRecurringValues() {
		return browser.getDropdownElements(".id", "recurring");
	}
	
	public List<String> getAffResValues() {
		return browser.getDropdownElements(".id", "affected_res");
	}
	
	public List<String> getInstructionValues() {
		return browser.getDropdownElements(".id", "res_instruction");
	}
	
	public List<String> getApplicationValues() {
		return browser.getDropdownElements(".id", "create_application");
	}
	
	public void selectProductCategory(String category) {
		browser.selectDropdownList(".id", "product_category", category);
	}
	
	public List<String> searchByClosureInfo(Closure closure) {
		List<String> ids = new ArrayList<String>();
		
		logger.info("Search cloure by all entry excpet id");
		this.setClosureID(StringUtil.EMPTY);
		if(StringUtil.notEmpty(closure.startDate)) {
			this.selectDateType("Start Date");
			this.setStartDate(closure.startDate);
			this.setEndDate(StringUtil.EMPTY);
		} else if(StringUtil.notEmpty(closure.endDate)) {
			this.selectDateType("End Date");
			this.setEndDate(closure.endDate);
			this.setStartDate(StringUtil.EMPTY);
		}
		
		if(StringUtil.notEmpty(closure.type))
			this.selectClosureType(closure.type);
		if(StringUtil.notEmpty(closure.productCategory))
			this.selectProductCategory(closure.productCategory);
		if(StringUtil.notEmpty(closure.recurring))
			this.selectRecurring("Yes");
		else
			this.selectRecurring("No");
		if(StringUtil.notEmpty(closure.affectedOrdInstr))
			this.selectAffectedReservations("Yes");
		else
			this.selectAffectedReservations("No");
		if(StringUtil.notEmpty(closure.createdAppID))
			this.selectApplication(closure.createdAppID);
		
		this.clickGo();
		this.waitLoading();
		ids=this.getAllClosureIDs();
		return ids;
	}
	
	public List<String> searchByClosureID(String id) {
		if(StringUtil.isEmpty(id))
			throw new ErrorOnDataException("Please specify closure id.");
			
		logger.info("Search closure by id "+id);
		List<String> ids = new ArrayList<String>();
		this.setClosureID(id);
		this.clickGo();
		this.waitLoading();
		ids=this.getAllClosureIDs();
		return ids;
	}
	
	public List<String> getAllClosureIDs() {
		List<String> ids = new ArrayList<String>();
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.Table", ".text", new RegularExpression("^Closure ID.*", false));
		if(objs.length<1)
			throw new ErrorOnPageException("Could not get closure list table on page.");
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		if(table.rowCount()<2)
			return null;
		int col = table.findColumn(0, "Closure ID");
		for(int i=1;i<table.rowCount();i++)
			ids.add(table.getCellValue(i, col));
		Browser.unregister(objs);
		return ids;
	}

}
