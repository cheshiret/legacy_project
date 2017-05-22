/*
 * Created on Mar 2, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.inventoryManager;

import com.activenetwork.qa.awo.datacollection.legacy.orms.InventoryInfo;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InvMgrNoteAndAlertListPage extends InventoryManagerPage {

   private static InvMgrNoteAndAlertListPage _instance = null;

	public static InvMgrNoteAndAlertListPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrNoteAndAlertListPage();
		}

		return _instance;
	}

	protected InvMgrNoteAndAlertListPage() {
	}

	public boolean exists() {
		boolean exist_1 =  browser.checkHtmlObjectExists(".class","Html.A",".text","Note/Alert Details");
		boolean exist_2 = browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Notes & Alerts");
		boolean exist_3 = browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", "Delete");
	    return !exist_1 & exist_2 & exist_3;
	}
	
	public void selectLookingFor(String item) {
	   browser.selectDropdownList(".id", "searchLookingFor", item);
	}
	
	public void selectType(String type) {
	  browser.selectDropdownList(".id", "searchMessageType", type);
	}
	
	public void setStartDate(String startDate) {
	  browser.setTextField(".id", "searchStartDate_ForDisplay", startDate);
	}
	
	public void setEndDate(String endDate) {
	  browser.setTextField(".id", "searchEndDate_ForDisplay", endDate);
	}
	
	public void setAlertContent(String content) {
	  browser.setTextField(".id", "searchMessageText", content);
	}
	
	public void selectStatus(String status) {
	  browser.selectDropdownList(".id", "searchStatus", status);
	}
	
	public void setCreatBy(String user) {
	  browser.setTextField(".id", "searchCreatedBy", user);
	}
	
	public void clickSearch() {
	  browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}
	
	public void clickViewRequestItem() {
	  browser.clickGuiObject(".class", "Html.A", ".text", "View Change Request Items");
	}
	
	public void clickAddNew() {
	  browser.clickGuiObject(".class", "Html.A", ".text", "Add New", true);
	}
	
	public void clickAlertID(String alertID) {
	  browser.clickGuiObject(".class", "Html.A", ".text", alertID);
	}
	
	public void selectFirstAlert() {
	  browser.selectCheckBox(".id", "row_0_checkbox");
	}
	
	public void selectAllAlerts() {
		  browser.selectCheckBox(".name", "all_slct");
		}

	public void clickActivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Activate");
	}
	
	public void clickDeactivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate");
	}
	
	public void clickDelete(){
		browser.clickGuiObject(".class","Html.A",".text","Delete");
		System.out.println("ok?");
	}
	
	public IHtmlObject[]getNoteOrAlertTable(){
		return  browser.getTableTestObject(".text", new RegularExpression("Note/Alert ID\\s*Start Date\\s*End Date\\s*Assigned to.*", false));
	} 
	
	public boolean checkNoteOrAlertExist(){
		   IHtmlObject[] obj=this.getNoteOrAlertTable();
		   if(obj.length<1){
			   throw new ObjectNotFoundException("Notes/Alerts table can't be found.");
		   }
		   
		   IHtmlTable grid = (IHtmlTable)obj[0];
		   boolean exist = false;
		   if(grid.rowCount()>=2){//>2
			   exist = true;
		   }
		   Browser.unregister(obj);
		   return exist;
	}
	
	public String getAlertIDWithLoops() {
		String alertId = "";
		IHtmlObject[] obj=this.getNoteOrAlertTable();
		IHtmlTable grid = (IHtmlTable)obj[0];
		
		boolean found=false;
		boolean haveClickedNext = false;
		PagingComponent turnPageComponent = new PagingComponent();
		
		do{
			for(int i=1;i<grid.rowCount();i++){
				String temp = grid.getCellValue(i, 7);
				if(temp!=null&&!temp.trim().equals("")&&Integer.parseInt(temp)>0){
					alertId = grid.getCellValue(i, 1).trim();
					found=true;
					break;
				}
			}
			if(found)
				break;
			turnPageComponent.clickNext();
			haveClickedNext = true;
			this.waitLoading();
			obj=this.getNoteOrAlertTable();
			grid = (IHtmlTable)obj[0];
		}while(haveClickedNext || turnPageComponent.nextExists());
			
		Browser.unregister(obj);
		if(alertId.equals("")){
			throw new ItemNotFoundException("No Proper Alert With Loops Exists.");
		}

		return alertId;
	}
	
	/**
	 * Add by Sara
	 * @return
	 */
	public String getFirstAlertIDInFirstPg() {
		IHtmlObject[] obj=this.getNoteOrAlertTable();
		IHtmlTable grid = (IHtmlTable)obj[0];
		String alertId = grid.getCellValue(1, 1).trim();
		Browser.unregister(obj);
		Browser.unregister(grid);
		return alertId;
	}
	
	public String getFirstAlertIDWithLoops(){
		IHtmlObject[] obj=this.getNoteOrAlertTable();
		IHtmlTable grid = (IHtmlTable)obj[0];
		String alertId = "";
		
		PagingComponent turnPageComponent = new PagingComponent();
		boolean found=false;
		while(!found) {
			for(int i=1; i<grid.rowCount();i++){
				String assignToValue = grid.getCellValue(i, 4);
				String type = grid.getCellValue(i, 5);
				if(assignToValue.equalsIgnoreCase("Loops/Sites") && type.equalsIgnoreCase("Alert")){				
					alertId = grid.getCellValue(i, 1);
					found=true;
					break;
				}
			}
			
			if(found)
				break;
			
			if(turnPageComponent.nextExists()) {
				turnPageComponent.clickNext();
				this.waitLoading();
				obj=this.getNoteOrAlertTable();
				grid = (IHtmlTable)obj[0];
			}
		}
		Browser.unregister(obj);
		
		if(!found)
			throw new ActionFailedException("Could not get any note alert id with loop.");
		
		return alertId;
	}
	
	public String getLoopsInfoByAlertID(String alertID){
		IHtmlObject[] obj=this.getNoteOrAlertTable();
		IHtmlTable grid = (IHtmlTable)obj[0];
		
		int row = grid.findRow(1, alertID);
		String loopValue = grid.getCellValue(row, 7);
		Browser.unregister(obj);
		return loopValue;
	}
	
	public String getFirstAlertIDForEntrance() {
		   IHtmlObject[] obj=null;
		   PagingComponent turnPageComponent = new PagingComponent();
		   int col,row=-1;
		   obj=this.getNoteOrAlertTable();
		   IHtmlTable table=(IHtmlTable)obj[0];
		   while(row<0){
			   col=table.findColumn(0, "Assigned to");
			   row=table.findRow(col, "Entrances");
			   
			   if(turnPageComponent.nextExists()) {
				   turnPageComponent.clickNext();
				   this.waitLoading();
				   obj=this.getNoteOrAlertTable();
				   table=(IHtmlTable)obj[0];
			   }
		   }
		   if(row<0)
			   throw new ActionFailedException("Could not find any note alert id with entrance.");
		   
		   String alertId = ((IHtmlTable)obj[0]).getCellValue(row, 1).trim();
		   Browser.unregister(obj);
		   return alertId;
	}
	
	/**
	 * Select Note or Alert check box via ID
	 * @param NoteOrAlertID
	 */
	public void selectNoteOrAlertCheckBoxByID(String NoteOrAlertID){
		browser.selectCheckBox(".value", NoteOrAlertID);
	}
	
	public void unselectNoteOrAlertCheckBoxByID(String NoteOrAlertID){
		browser.unSelectCheckBox(".value", NoteOrAlertID);
	}
	
	/**
	 * Select Notes or Alerts checkbox By IDs
	 * @param id
	 */
	public void selectNoteOrAlertCheckBoxByIDs(String[] ids){
	  	if(ids==null || ids.length<1) //do nothing
	  	  	return;
	  	
	  	for(int i=0;i<ids.length;i++) {
	  		this.selectNoteOrAlertCheckBoxByID(ids[i]);
	  	}
	}
	
	/**
	 * Active one or more note or alert by note or alert IDs
	 * @param closureIDs
	 */
	public void activeNotesOrAlerts(String... noteOrAlertIDs){
		this.selectNoteOrAlertCheckBoxByIDs(noteOrAlertIDs);
		this.clickActivate();
	}
	
	/**
	 * Deactive one or more note or alert by note or alert IDs
	 * @param closureIDs
	 */
	public void deactiveNotesOrAlerts(String... noteOrAlertIDs){
		this.selectNoteOrAlertCheckBoxByIDs(noteOrAlertIDs);
		this.clickDeactivate();
	}
	
	/**
	 * Get warning message
	 * @return
	 */
	public String getWarningMessage(){
		String warningMessage = "";

		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if(objs.length>0){
			warningMessage = objs[0].getProperty(".text").toString();
		}else throw new ObjectNotFoundException("Object can't find.");

		Browser.unregister(objs);
		return warningMessage;
	}
	
	public String getErrorMsg(){
		return browser.getObjectText(".class", "Html.DIV", ".className", "message msgerror");
	}
	
	public String getSuccessfulMsg(){
		return browser.getObjectText(".class", "Html.DIV", ".className", "message msgsuccess");
	}
	
	public void searchForSlipNoteAlerts(){
		this.selectLookingFor("Slip Notes/Alerts");
		ajax.waitLoading();
		this.waitLoading();
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	/**
	 * Search notes or alerts
	 * @param invent
	 */
	public void searchNotesOrAlerts(InventoryInfo invent){
	
		if(!StringUtil.isEmpty(invent.lookFor)){
			this.selectLookingFor(invent.lookFor);
		}
		
		if(!StringUtil.isEmpty(invent.entrance)){
			this.selectEntrance(invent.entrance);
		}
		
		if(null!=invent.alertType && invent.alertType.length()>0){
			this.selectType(invent.alertType);
		}
		
        if(null!=invent.alertStartDate && invent.alertStartDate.length()>0){
        	this.setStartDate(invent.alertStartDate);
        }
        if(null!=invent.alertEndDate && invent.alertEndDate.length()>0){
        	this.setEndDate(invent.alertEndDate);
        }
        if(null!=invent.description && invent.description.length()>0){
        	this.setAlertContent(invent.description);
        }
        if(null!=invent.status && invent.status.length()>0){
        	if(invent.status.equals("Deactive")){
        		invent.status = "Inactive";
        	}
        	this.selectStatus(invent.status);
        }

        this.clickSearch();
        this.waitLoading();
	}
	
	/**
	 * @param entrance
	 */
	private void selectEntrance(String entrance) {
		browser.selectDropdownList(".id", "searchEntrance", entrance);
	}

	/** Click the button 'View Change Request Items'*/
	public void clickViewChangeRequestItems(){
		RegularExpression rex = new RegularExpression("\"SearchCRIListFromViewCRIButton\".*", false);
		browser.clickGuiObject(".class", "Html.A", ".href",rex);
	}
	
	/**
	 * Click #Sites via specific note or alert ID
	 * @param noteOrAlertID
	 */
	public void clickNumSites(String noteOrAlertID){
		RegularExpression rex = new RegularExpression(".*\"ViewNotesAlertsSites\".+\"" + noteOrAlertID + "\"", false);
		browser.clickGuiObject(".class","Html.A",".href",rex);
	}
	
	/**
	 * Click #Loops via specific note or alert ID
	 * @param noteOrAlertID
	 */
	public void clickNumLoops(String noteOrAlertID){
		RegularExpression rex = new RegularExpression("\"ViewNotesAlertsLoops\".+\"" + noteOrAlertID + "\"", false);
		browser.clickGuiObject(".class","Html.A",".href",rex);
	}
	
	/**
	 * Click #Loops via specific note or alert ID
	 * @param noteOrAlertID
	 */
	public void clickNumEntrances(String noteOrAlertID){
		RegularExpression rex = new RegularExpression("\"ViewNotesAlertsEntrances\".+\"" + noteOrAlertID + "\"", false);
		browser.clickGuiObject(".class","Html.A",".href",rex);
	}
	
	/**
	 * Click note or alert id
	 * @param noteOrAlertID
	 */
	public void clickNoteOrAlertID(String noteOrAlertID){
		RegularExpression rex = new RegularExpression("\"ViewNotesAlertsDetails\".+\"" + noteOrAlertID + "\"", false);
		browser.clickGuiObject(".class","Html.A",".href",rex);
	}
	
	/**
	 * If "Last" button is avaliable, click last button in note or alert list page.
	 *
	 */
	public boolean gotoLast() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",
				".text", "Last" );
		boolean toReturn = false;

		if (objs.length > 0) {
			toReturn = true;
			objs[0].click();
		}

		Browser.unregister(objs);
		this.waitLoading();

		return toReturn;
	}
	
	/**
	 * Check button 'View Change Request Items' exist or not
	 * @return
	 */
	public boolean checkViewChangeRequestItems(){
		RegularExpression rex = new RegularExpression("\"SearchCRIListFromViewCRIButton\".*", false);
		return browser.checkHtmlObjectExists(".class", "Html.A", ".href", rex);
	}
	
	public boolean isNoteOrAlertIDExisted(String noteOrAlertID){
		RegularExpression rex = new RegularExpression("\"ViewNotesAlertsDetails\".+\"" + noteOrAlertID + "\"", false);
		return browser.checkHtmlObjectExists(".class","Html.A",".href",rex);
	}
	
	public void searchNotesOrAlertsByStatus(String status){
		this.selectStatus(status);
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
}
