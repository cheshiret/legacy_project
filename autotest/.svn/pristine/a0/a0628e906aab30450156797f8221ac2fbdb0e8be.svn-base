package com.activenetwork.qa.awo.pages.orms.common;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.NoteAndAlertInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * @author phoebeChen
 *
 * Orms note/alert detail page for Customer[FM]/Pos/<?Reservation>/GiftCard
 */
public class OrmsNoteAlertListCommonPage extends OrmsPage {
	static private OrmsNoteAlertListCommonPage _instance = null;
	
	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsNoteAlertListCommonPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsNoteAlertListCommonPage();
		}

		return _instance;
	}
   
	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsNoteAlertListCommonPage() {
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".className", "btnanchor", ".text", "Notes & Alerts");
	}
	/**
	 * Get list table	
	 * @return
	 */
	public IHtmlObject[] getAlertNoteTable() {
		return browser.getTableTestObject(".text", new RegularExpression("^Note/Alert ID Start Date End Date.*", false));
	}
	/**
	 * Select the check box in front of a note/alert record according to the id
	 * @param id
	 */
	public void selectNoteAlertById(String id){
		IHtmlObject[] objs = getAlertNoteTable();
		if(objs.length<1){
			throw new ErrorOnPageException("Could not find table with text 'Note/Alert ID Start Date End Date...'");
		}
		IHtmlTable grid = (IHtmlTable)objs[objs.length-1];
		int row = grid.findRow(1, id);
		if(row <1){
			throw new ItemNotFoundException("Did not get any row info with id = " + id);
		}
		
		int index = row-1;
		browser.selectCheckBox(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems",false), index, true);
		Browser.unregister(objs);
	}
	/**
	 * Click the link of a note/alert according to the id
	 * @param id
	 */
	public void clickNoteAlertID(String id){
		browser.clickGuiObject(".class", "Html.A", ".text", id, true);
	}
	/**
	 * Select all the check box in front of each note/alert 
	 */
	public void selectAllNoteAlert(){
		browser.selectCheckBox(".name", "all_slct");
	}
    /**
     * Click delete button
     */
	public void clickDelete(){
		browser.clickGuiObject(".class","Html.A",".text",new RegularExpression("Delete", false));
	}
	
    /**
     * Click activate button
     */
	public void clickActivate(){
		browser.clickGuiObject(".class","Html.A",".text",new RegularExpression("Activate", false));
	}
	/**
	 * Click deactive button
	 */
	public void clickDeactivate(){
		browser.clickGuiObject(".class","Html.A",".text",new RegularExpression("Deactivate", false));
	}
	/**
	 * Click cancel button
	 */
	public void clickCancel(){
		browser.clickGuiObject(".class","Html.A",".text",new RegularExpression("Cancel", false));
	}
	/**
	 * Click search button
	 */
	public void clickSearch(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", new RegularExpression("SearchBar_\\d+",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get any search bar div object.");
		}
		browser.clickGuiObject(Property.toPropertyArray(".class","Html.A",".text", new RegularExpression("Search", false)), true, 0, objs[objs.length-1]);
		Browser.unregister(objs);
	}
	/**
	 * Click add new buttone
	 */
	public void clickAddNew(){
		IHtmlObject[] frame = browser.getFrame("transaction");		
		if(frame.length > 0){
			browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Add New", false), true, 0, frame[0]);
		}else{
			browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Add New", false));
		}
		
		Browser.unregister(frame);
	}
    /**
     * Get the number of all note and alert that shown on the page
     * @return
     */
	public int getNumOfNoteAlert() {
		IHtmlObject[] objs = getAlertNoteTable();
		if(objs.length<1){
			throw new ErrorOnPageException("Could not find table with text 'Note/Alert ID Start Date End Date...'");
		}
		IHtmlTable grid = (IHtmlTable)objs[1];
//		Browser.unregister(objs);
		return grid.rowCount() - 1;
	}
    /**
     * Get all the note message after click apply or ok button
     * @return
     */
	public String getNoteMessage() {
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV", ".id", "NOTSET"));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find message object.");
		}

		String text = objs[0].text();
		Browser.unregister(objs);
		
		return text;
		
	}
	
	public String getNoteAlertIDByText(String text){
		IHtmlObject[] objs = this.getNoteAlertListTableObject();
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		
		int row = table.findRow(5, text);
		String id = table.getCellValue(row, 1);
		Browser.unregister(objs);
		return id;
	}
    /**
     *Click the first note/alert in the list page
     */
	public void clickFirstNoteAlertInList() {
		IHtmlObject[] frame = browser.getFrame("transaction");
		if(frame.length > 0){
			browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("\\d+",false), true, 0, frame[0]);
		}else{
			throw new ObjectNotFoundException("Frame 'transaction' does not found!");
		}		
		Browser.unregister(frame);
	}
	
	private IHtmlObject[] getNoteAlertListTableObject(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "messagesGrid_LIST");
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get any note alert list table object.");
		}
		return objs;
	}
	
	public NoteAndAlertInfo getNoteAlertInfoByID(String id){
		logger.info("Get note and alert info by id = " + id);
		IHtmlObject[] objs = this.getNoteAlertListTableObject();
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		
		NoteAndAlertInfo noteAndAlertInfo = new NoteAndAlertInfo();
		int row = table.findRow(1, id);
		if(row<1){
			throw new ItemNotFoundException("Did not get any note alert info by id = " + id);
		}
		noteAndAlertInfo.id = table.getCellValue(row, 1);
		noteAndAlertInfo.startDate = table.getCellValue(row, 2);
		noteAndAlertInfo.endDate = table.getCellValue(row, 3);
		noteAndAlertInfo.type = table.getCellValue(row, 4);
		noteAndAlertInfo.text = table.getCellValue(row, 5);
		noteAndAlertInfo.status = table.getCellValue(row, 6); 
		noteAndAlertInfo.createUser = table.getCellValue(row, 7); 
		Browser.unregister(objs);
		return noteAndAlertInfo;
	}
	
	public void verifyNoteAlertInfo(NoteAndAlertInfo noteAndAlertInfo){
		logger.info("Verify note and alert info.");
		NoteAndAlertInfo actNoteAlertInfo = this.getNoteAlertInfoByID(noteAndAlertInfo.id);
		boolean result = true;
		result &= MiscFunctions.compareResult("Start Date", noteAndAlertInfo.startDate, actNoteAlertInfo.startDate);
		result &= MiscFunctions.compareResult("End Date", noteAndAlertInfo.endDate, actNoteAlertInfo.endDate);
		result &= MiscFunctions.compareResult("Type", noteAndAlertInfo.type, actNoteAlertInfo.type);
		result &= MiscFunctions.compareResult("Text", noteAndAlertInfo.text, actNoteAlertInfo.text);
		result &= MiscFunctions.compareResult("Status", noteAndAlertInfo.status, actNoteAlertInfo.status);
		
		if(!result){
			throw new ErrorOnPageException("Note and Alert info not correct, please check log.");
		}else logger.info("Note and Alert info is correct.");
	}
	
	public void deactivateNoteAlertInfo(String id){
		this.updateNoteAlertInfoStatus(id, OrmsConstants.INACTIVE_STATUS);
	}
	
	public void activateNoteAlertInfo(String id){
		this.updateNoteAlertInfoStatus(id, OrmsConstants.ACTIVE_STATUS);
	}
	
	private void updateNoteAlertInfoStatus(String id, String status){
		this.selectNoteAlertById(id);
		if(status.equalsIgnoreCase(OrmsConstants.ACTIVE_STATUS)){
			this.clickActivate();
		}else if(status.equalsIgnoreCase(OrmsConstants.INACTIVE_STATUS)){
			this.clickDeactivate();
		}
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public boolean isNoteAlertInfoExistsByID(String id){
		logger.info("Is note and alert info exists by id = " + id);
		IHtmlObject[] objs = this.getNoteAlertListTableObject();
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		
		int row = table.findRow(1, id);
		boolean isExists = true;
		if(row<1){
			isExists = false;
		}else isExists = true;
		
		Browser.unregister(objs);
		return isExists;
	}
	
}

