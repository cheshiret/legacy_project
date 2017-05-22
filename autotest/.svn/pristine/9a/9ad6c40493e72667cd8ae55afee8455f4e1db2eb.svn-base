/*
 * Created on Mar 2, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.inventoryManager;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.InventoryInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.KeyInput;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InvMgrNoteOrAlertDetailPage extends InventoryManagerPage{
    private static InvMgrNoteOrAlertDetailPage _instance = null;

	public static InvMgrNoteOrAlertDetailPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrNoteOrAlertDetailPage();
		}

		return _instance;
	}

	protected InvMgrNoteOrAlertDetailPage() {
	}

	public boolean exists() {
	  return browser.checkHtmlObjectExists(".class","Html.A",".text","Note/Alert Details");
	}
	
	protected Property[] typeDropList() {
		return Property.toPropertyArray(".id", new RegularExpression("MessageView(-\\d+)?\\.Type", false));
	}
	
	protected Property[] startDateTextField() {
		return Property.toPropertyArray(".id","MessageView.StartDate_ForDisplay");
	}
	
	protected Property[] endDateTextField(){
		return Property.toPropertyArray(".id","MessageView.EndDate_ForDisplay");
	}
	
	protected Property[] textContentArea(){
		return Property.toPropertyArray(".id","MessageView.Message");
	}
	
	protected Property[] activeCheckBox(){                           
		return Property.toPropertyArray(".id", new RegularExpression("MessageView(-\\d+)?.Active",false));
	}
	
	protected Property[] includeInConfirmationLetterCheckBox(){
		return Property.toPropertyArray(".id", new RegularExpression("MessageView(-\\d+)?.IncludeInConfLetter",false));
	}
	
	protected Property[] includeInPrintedPermit(){
		return Property.toPropertyArray(".id", new RegularExpression("MessageView(-\\d+)?.includeInPrintedPermit",false));
	}
	
	public void selectType(String type){//MessageView-1854381637.type | MessageView.Type
	   browser.selectDropdownList(typeDropList(), type);
	}
	
	public String getType(){
		return browser.getDropdownListValue(this.typeDropList(), 0);
	}
	
	public void setStartDate(String startDate){
	   browser.setTextField(startDateTextField(), startDate);
	}
	
	public String getStartDate(){
		return browser.getTextFieldValue(this.startDateTextField());
	}
	
	public void setEndDate(String endDate){
	  browser.setTextField(endDateTextField(),endDate);
	}
	
	public String getEndDate(){
		return browser.getTextFieldValue(this.endDateTextField());
	}
	
	public void setDescription(String description){
	  browser.setTextArea(textContentArea(),description);
	}
	
	public void moveFocusOutOfCalendar(){
		browser.clickGuiObject(textContentArea());
	}
	
	public void selectActive(){
	  browser.selectCheckBox(activeCheckBox());
	}
	
	public void unSelectActive(){
		  browser.unSelectCheckBox(activeCheckBox());
	}
	
	public String getStatus(){
		String status = "";
		if(browser.isCheckBoxSelected(this.activeCheckBox())){
			status = OrmsConstants.ACTIVE_STATUS;
		}else{
			status = OrmsConstants.INACTIVE_STATUS;
		}
		return status;
	}
	
	public void selectIncludeInPrintedPermit(){
		browser.selectCheckBox(includeInPrintedPermit());
	}
	
	public void unseelectIncludeInPrintedPermit(){
		browser.unSelectCheckBox(includeInPrintedPermit());
	}
	
	public boolean isIncludeInPrintedPermit(){
		boolean included = false;
		if(browser.isCheckBoxSelected(this.includeInPrintedPermit())){
			included = true;
		}
		return included;
	}
	
	public boolean isIncludeInConfirmationLetter(){
		boolean included = false;
		if(browser.isCheckBoxSelected(this.includeInConfirmationLetterCheckBox())){
			included = true;
		}
		return included;
	}
	
	public void selectIncludeInConfirmationLetter(){
		browser.selectCheckBox(includeInConfirmationLetterCheckBox());
	}
	
	public void unselectIncludeInConfirmationLetter(){
		browser.unSelectCheckBox(includeInConfirmationLetterCheckBox());
	}
	
	private String getApplicationCheckboxID(String app) {
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.LABEL", ".className", "trailing", ".text", app));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find application - " + app);
		}
		
		String forValue = objs[0].getProperty(".for");
		Browser.unregister(objs);
		
		return forValue;
	}
	
	public void selectApplications(String application){
//		Property[] p1 = Property.toPropertyArray(".className", "inputwithrubylabel", ".text", 
//				new RegularExpression("^"+application+"$", false));
//		HtmlObject[] top = browser.getHtmlObject(p1);
//		if(null==top || top.length<1){
//			throw new ObjectNotFoundException(application+" object can't be found.");
//		}
//		Property[] p2 = Property.toPropertyArray(".id", new RegularExpression("MessageView\\.Applications\\d+", false));
//		browser.selectCheckBox(p2, 0, top[0]);
//		
//		Browser.unregister(top);
		String checkboxId = getApplicationCheckboxID(application);
		browser.selectCheckBox(".id", checkboxId, true);
	}
	
	public void unSelectApplications(String application){
//		Property[] p1 = Property.toPropertyArray(".className", "inputwithrubylabel", ".text", 
//				new RegularExpression("^"+application+"$", false));
//		HtmlObject[] top = browser.getHtmlObject(p1);
//		if(null==top || top.length<1){
//			throw new ObjectNotFoundException(application+" object can't be found.");
//		}
//		Property[] p2 = Property.toPropertyArray(".id", new RegularExpression("MessageView\\.Applications\\d+", false));
//		browser.unSelectCheckBox(p2, 0, top[0]);
//		
//		Browser.unregister(top);
		String checkboxId = getApplicationCheckboxID(application);
		browser.unSelectCheckBox(".id", checkboxId);
	}
	
	public String[] getAllAppplicationsSelected(){
		List<String> applications = new ArrayList<String>();
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Call Center.*Field.*Web.*",false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Table object for applications doesn't find.");
		}
		IHtmlObject[] spans = browser.getHtmlObject(".class", "Html.SPAN", ".className", "inputwithrubylabel checkbox inlinedirect", objs[0]);
		for(int i=0; i<spans.length; i++){
			if(browser.isCheckBoxSelected(Property.toPropertyArray(".id", new RegularExpression("CheckboxExt-\\d+\\.checked", false)), spans[i])){
				String content = spans[i].text().trim();
				applications.add(content);
			}
		}
		String[] applicationArray = new String[applications.size()];
		for(int i=0; i<applications.size(); i++){
			applicationArray[i] = applications.get(i);
		}
		Browser.unregister(objs);
		Browser.unregister(spans);
		return applicationArray;
	}
	
	public List<String> getAllApplicationsUnSelected(){
		List<String> applications = new ArrayList<String>();
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Call Center.*Field.*Web.*",false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Table object for applications doesn't find.");
		}
		IHtmlObject[] spans = browser.getHtmlObject(".class", "Html.SPAN", ".className", "inputwithrubylabel checkbox inlinedirect", objs[0]);
		for(int i=0; i<spans.length; i++){
			if(!browser.isCheckBoxSelected(Property.toPropertyArray(".id", new RegularExpression("CheckboxExt-\\d+\\.checked", false)), spans[i])){
				String content = spans[i].text().trim();
				applications.add(content);
			}
		}
		Browser.unregister(objs);
		Browser.unregister(spans);
		return applications;
	}
	
	public void unselectActive(){
	  browser.unSelectCheckBox(".id","MessageView.Active");
	}
	
	public void clickOk(){
	  browser.clickGuiObject(".class","Html.A",".text","OK");
	}
	
	public void clickCancel(){
	  browser.clickGuiObject(".class","Html.A",".text","Cancel");
	}
	
	public void clickApply(){
	  browser.clickGuiObject(".class","Html.A",".text","Apply");
	}
	
	/** Click site */
	public void clickSites(){
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression("\"CampingUnitSites.do.*", false));
	}
	
	/** Click loops */
	public void clickLoops(){
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression("\"CampingUnitLoops.do.*", false));
	}
	
	/** Click Notes & Alerts tab */
	public void clickNoteAlertTab(){
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression("\"InvMgrNotesAlertsList.do.*", false));
	}
	
	public void clickEntranceTab(){
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression("\"InvMgrNotesAlertsEntrances.do.*", false));
	}
	/**
	 * Click the radio button for Applies to
	 * @param index
	 *             ---0:None
	 *             ---1:Facility
	 *             ---2:Assigned Loops/Sites
	 *             ---3:Assigned Tours
	 *             ---4:Assigned Entrances
	 */
	public void clickAppliedTo(int index){
		browser.clickGuiObject(".id","MessageView.AppliesTo",index);
	}
	
	public void setUpEntryInfo(InventoryInfo inv){
		if(null!=inv){
			if(!StringUtil.isEmpty(inv.alertType)){
				selectType(inv.alertType);
			}
			ajax.waitLoading();
			if(!StringUtil.isEmpty(inv.description)){
				setDescription(inv.description);
			}
			if(inv.status.equals("Active")){
				this.selectActive();
			}else{
				unSelectActive();
			}
			
			if(inv.includeInPrintedPermit){
				this.selectIncludeInPrintedPermit();
			}else{
				unseelectIncludeInPrintedPermit();
			}
			
			if(this.checkIncludeInConfirmationLetterAvailable()){
				if(inv.includeInConfirmationLetter){
					this.selectIncludeInConfirmationLetter();
				}else{
					this.unselectIncludeInConfirmationLetter();
				}
			}
			
			if(!StringUtil.isEmpty(inv.application)){
				if(inv.selectApplication){
					this.selectApplications(inv.application);
				}else{
					this.unSelectApplications(inv.application);
				}
			}
			
			if(inv.applications!=null && inv.applications.length>0){
				for(int i=0; i<inv.applications.length; i++){
					if(inv.selectApplications[i]){
						this.selectApplications(inv.applications[i]);
					}else{
						this.unSelectApplications(inv.applications[i]);
					}
				}
			}
			
			if(!StringUtil.isEmpty(inv.alertStartDate)){
				setStartDate(inv.alertStartDate);
				browser.inputKey(KeyInput.get(KeyInput.TAB));
			}
			if(!StringUtil.isEmpty(inv.alertEndDate)){
				setEndDate(inv.alertEndDate);
				browser.inputKey(KeyInput.get(KeyInput.TAB));
			}
			if(inv.appliesToIndex!=0){
				clickAppliedTo(inv.appliesToIndex);
			}
		}
	}
	
	public void addNewAlert(InventoryInfo inv){
		this.setUpEntryInfo(inv);
		this.clickApply();
	}
	
	public String getNoticeMessage(){
	   String message = "";
	   IHtmlObject[] objs;
	   objs=browser.getHtmlObject(".class","Html.DIV",".id","NOTSET");
	   if(objs.length>1){
		   for(int i=0; i<objs.length; i++){
			  message = message + objs[i].getProperty(".text").toString()+"#";
		   }
	   }else if(objs.length>0 && objs.length<=1){
		   message = objs[0].getProperty(".text").toString();
	   }else throw new ItemNotFoundException("Object doesn't find.");
	   
	   Browser.unregister(objs);
	   
	   return message;
	}
	
	public boolean isSuccessfullyMsgExists() {                                                  
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", new RegularExpression("venuemanager.notesalertsworker.notealert.(created|updated)", false));
	}
	
	public String getSuccessfullyMsg(){
		return browser.getObjectText(".class", "Html.DIV", ".className", "message msgsuccess");
	}
	
	public String getErrorMessage(){
		   String message = "";
		   IHtmlObject[] objs=browser.getHtmlObject(".class","Html.DIV",".id",new RegularExpression("venuemanager.notesalertsworker.notealert.*", false));

		   if(objs.length>1){
			   for(int i=0; i<objs.length; i++){
				  message = message + objs[i].getProperty(".text").toString()+"#";
			   }
		   }else if(objs.length>0 && objs.length<=1){
			   message = objs[0].getProperty(".text").toString();
		   }else throw new ItemNotFoundException("Object doesn't find.");
		   
		   Browser.unregister(objs);
		   
		   return message;
		}
	
	public String getDescription(){
	   IHtmlObject[] objs=browser.getHtmlObject(".id","MessageView.Message");
	   String des=objs[0].getProperty(".text").toString();
	   
	   Browser.unregister(objs);
	   
	   return des;
	}
	
	/**
	 * Get Not or Alert ID
	 * @return
	 */
	public String getNoteOrAlertID(){
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^Configure Note/Alert", false));
		IHtmlTable table = (IHtmlTable)objs[0];
		
		String toReturn = table.getCellValue(0, 1);
        Pattern p = Pattern.compile("\\d+(,\\d{3})*");
        Matcher m = p.matcher(toReturn);
        if(m.find()){
        	toReturn = m.group();
        }
        
		Browser.unregister(objs);
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
	
	/** Click the button 'View Change Request Items' */
	public void clickViewChangeRequestItems(){
		RegularExpression rex = new RegularExpression("\"SearchCRIListFromViewCRIButton\".*", false);
		browser.clickGuiObject(".class", "Html.A", ".href",rex);
	}
	
	/**
	 * Check the section 'Include in Confirmation Letter' check box available or not
	 * @return
	 */
	public boolean checkIncludeInConfirmationLetterAvailable(){
		boolean available = false;                         
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("MessageView(-\\d+)?.IncludeInConfLetter", false));
		if(objs.length>0){
			String disable = objs[0].getProperty(".disabled").toString();
			if(disable.equals("false")){
				available = true;
			}
		}else throw new ObjectNotFoundException("Object doesn't find.");
		
		return available;
	}
	
	public boolean isSlipLabelExist(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text","Slips");
	}
	
	public boolean isDockLabelExist(){
		return browser.checkHtmlObjectDisplayed(".class", "Html.A", ".text", "Docks");
	}
	
	public void clickSlipLable(){
		browser.clickGuiObject(".class", "Html.SPAN", ".text","Slips");
	}

	public void clickToursLable(){
		browser.clickGuiObject(".class", "Html.SPAN", ".text", "Tours / Combo Tours");
	}
	
	public void clickDocksLabel(){
		browser.clickGuiObject(".class", "Html.SPAN", ".text", "Docks");
	}
	
	public InventoryInfo getInventoryInfo(){
		InventoryInfo inv = new InventoryInfo();
		inv.alertType = this.getType();
		inv.alertStartDate = this.getStartDate();
		inv.alertEndDate = this.getEndDate();
		inv.description = this.getDescription();
		inv.status = this.getStatus();
		inv.includeInConfirmationLetter = this.isIncludeInConfirmationLetter();
		inv.includeInPrintedPermit = this.isIncludeInPrintedPermit();
		inv.applications = this.getAllAppplicationsSelected();
		
		return inv;
	}
	
	public boolean compareEntryInfo(InventoryInfo expInfo){
		InventoryInfo actInfo = this.getInventoryInfo();
		boolean result = true;
		result &= MiscFunctions.compareResult("Entry type",  expInfo.alertType, actInfo.alertType);
		result &= MiscFunctions.compareResult("Start date",  expInfo.alertStartDate, actInfo.alertStartDate);
		result &= MiscFunctions.compareResult("End date",  expInfo.alertEndDate, actInfo.alertEndDate);
		result &= MiscFunctions.compareResult("Note/Alert text",  expInfo.description, actInfo.description);
		result &= MiscFunctions.compareResult("Status",  expInfo.status, actInfo.status);
		result &= MiscFunctions.compareResult("Is include in confirmation letter",  expInfo.includeInConfirmationLetter, actInfo.includeInConfirmationLetter);
		result &= MiscFunctions.compareResult("Is include in printed permit",  expInfo.includeInPrintedPermit, actInfo.includeInPrintedPermit);
		List<String> expSelectedApplications = new ArrayList<String>();
		List<String> actSelectedApplications = new ArrayList<String>();
		for(int i=0; i< expInfo.applications.length; i++){
			if(expInfo.selectApplications[i]){
				expSelectedApplications.add(expInfo.applications[i]);
			}
		}
		for(int i=0; i< actInfo.applications.length; i++){
			actSelectedApplications.add(actInfo.applications[i]);
		}
		if(! (expSelectedApplications.containsAll(actSelectedApplications)&&actSelectedApplications.containsAll(expSelectedApplications))){
			logger.info("The selected applications is not correct, expect:" + expSelectedApplications.toString() + ", but actually is:" + actSelectedApplications.toString());
			result &= false;
		}
		return result;
	}
	
}
