package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
import com.activenetwork.qa.testapi.interfaces.html.IToggle;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * ScriptName: LicMgrPrivilegeInventoryWidget
 * Description:
 * @date: Jan 14, 2011
 * @author qchen
 *
 */
public class LicMgrPrivilegeInventoryWidget extends DialogWidget {
	private static LicMgrPrivilegeInventoryWidget _instance = null;
	
	protected LicMgrPrivilegeInventoryWidget() {
		
	}
	
	public static LicMgrPrivilegeInventoryWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrPrivilegeInventoryWidget();
		}
		
		return _instance;
	}
	
	protected Property[] surveryQuestionTable(){
		return Property.concatPropertyArray(table(), ".id", "inventoryQuestionUITable");
	}
	
	protected Property[] privilegeInventorySpan(){
		return Property.concatPropertyArray(span(), ".text", "Privilege Inventory");
	}
	
	public boolean exists() {
		boolean flag1 = browser.checkHtmlObjectExists(privilegeInventorySpan());
		boolean flag2 = browser.checkHtmlObjectExists(surveryQuestionTable());
		return super.exists() && (flag1 || flag2);
	}
	
//	/**
//	 * Check the specify inventory number in Privilege Inventory Widget
//	 * @param inventoryNum
//	 * @return
//	 */
//	private String checkSpecifyInventoryNumber(String inventoryNum) {
//		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.LABEL", ".className", "trailing",".text",inventoryNum));
//		
//		if(objs.length == 0) {
//			throw new ItemNotFoundException("Can't find any Inventory Number "+inventoryNum);
//		}
//
//		String textValue = objs[0].text();
//
//		String checkBoxIdValue = objs[0].getProperty("for").trim();
//		browser.selectCheckBox(".id", checkBoxIdValue);
//		
//		Browser.unregister(objs);
//		return textValue;
//	}

	Property searchBtn1 = new Property(".class", "Html.A");
	Property searchBtn2 = new Property(".text", new RegularExpression("Search", false));
	Property[] searchBtn = new Property[]{searchBtn1, searchBtn2};
	
	/**
	 * if all of the inventory has been displayed on the widget, the search button will be disabled.
	 */
	public boolean isSearchButtonEnable(){
		return browser.checkHtmlObjectEnabled(searchBtn);
	}
	/**
	 * if all of the inventory has been displayed on the widget, the search button will be disabled.
	 */
	public void clickSearch(){
		browser.clickGuiObject(searchBtn);
	}
	
	/**
	 * will search by the value start with inventoryNum
	 * e.g. inventoryNum = 13, the result will list from 130, not 013.
	 * @param inventoryNum
	 */
	public void setStartInventoryNum(String inventoryNum){
		browser.setTextField(".id", new RegularExpression("InventoryListNonScrollingUI-\\d+.startInventoryNumber", false), inventoryNum);
	}
	
	public void selectInventoryByNumber(String inventoryNum){
		if(isSearchButtonEnable()){
			// search by inventory number
			this.setStartInventoryNum(inventoryNum);
			this.clickSearch();
			ajax.waitLoading();
			this.waitLoading();
		}// else all of the inventory is displayed on the widget, just select it.
		
		// select inventory by number
		this.selectInventory(inventoryNum);
		ajax.waitLoading();
	}
	
	private void selectInventory(String inventoryNum){
		RegularExpression reg = new RegularExpression(inventoryNum+"_radioButton",false);
		if(browser.checkHtmlObjectEnabled(".id", reg)){
			browser.selectRadioButton(".id", reg);
		} else {
			throw new ErrorOnPageException("Inventory "+inventoryNum+" is not available, please select another one!!");
		}
	}
	
	public String selectSingleFirstAvailableInventory(){
		IHtmlObject objs[] = browser.getHtmlObject(".class","Html.INPUT.radio",".id",new RegularExpression("\\d+_radioButton",false));
		int index=-1;
		for(int i=0;i<objs.length;i++) {
			if(objs[i].isEnabled()) {
				index=i;
				break;
			}
		}
		((IRadioButton)objs[index]).select();

		String id=objs[index].getAttributeValue(".id");
		String invNum =id.split("_")[0];
		Browser.unregister(objs);
		return invNum;
	}
	
	public String selectFirstAvailableInventory() {
		IHtmlObject objs[] = browser.getHtmlObject(".class","Html.INPUT.checkbox",".id",new RegularExpression("(InventoryPrompt-\\d+\\.selectedInventoryList_\\d+)|(\\d+_checkBox)",false));
		if(objs.length<1) {//James[20130710]: the inventory selection button can also be radio button
			objs=browser.getHtmlObject(Property.atList(this.widgetProperty,Property.toPropertyArray(".class","Html.INPUT.radio")));
		}
		String id=null;
		int index=-1;
		for(int i=0;i<objs.length;i++) {
			if(objs[i].isEnabled()) {
				index=i;
				break;
			}
		}
		id=objs[index].getAttributeValue(".id");
		if(id==null) {
			throw new ItemNotFoundException("Failed to find an inventory.");
		}
		String invNum= browser.getObjectText(".class","Html.LABEL","for",id);
		if(StringUtil.isEmpty(invNum)) {
			invNum = objs[index].getAttributeValue(".value");
		}
		
		((IToggle)objs[index]).select();
				
		Browser.unregister(objs);
		
		if(StringUtil.isEmpty(invNum)) {
			throw new ItemNotFoundException("Failed to get the inventory number");
		}
		
		return invNum;
	}
	
	public String getStartInvNumber(){
		return browser.getTextFieldValue(".id", new RegularExpression("InventoryListNonScrollingUI-\\d+.startInventoryNumber", false));
	}
	
	public String getSelectedInventoryNum(){
		return browser.getObjectText(Property.concatPropertyArray(td(), ".id", "selectedInventoryLabelTableCell")).split(":")[1].trim();
	}
	
	/**
	 * Select inventory number
	 * @param inventoryNum
	 * @return
	 */
	public String selectInventoryNumber(String inventoryNum) {
		String invNumFromUI;
		if(StringUtil.isEmpty(inventoryNum)) {
			invNumFromUI = selectFirstAvailableInventory();
			// 30402 Nicole select the first available inventory by default
//			invNumFromUI = getStartInvNumber();
			//Sara[03192014] "Start Inventory #" is different from the "selected Inventory Number"
			invNumFromUI = getSelectedInventoryNum();
		} else {
//			invNumFromUI = checkSpecifyInventoryNumber(inventoryNum);
//			ajax.waitLoading();
			invNumFromUI = inventoryNum;
			selectInventoryByNumber(inventoryNum);
		}
		return invNumFromUI;
	}
	
	public void selectReplatedInvNum(String replaceInventoryNum){
		selectInventoryNumber(replaceInventoryNum);
		ajax.waitLoading();
		clickOK();
		ajax.waitLoading();
	}
	
	public String deselectFirstAvailableInventory() {
		IHtmlObject objs[] = browser.getHtmlObject(".class","Html.INPUT.checkbox",".id",new RegularExpression("(InventoryPrompt-\\d+\\.selectedInventoryList_\\d+)|(\\d+_checkBox)",false));
		if(objs.length<1) {//James[20130710]: the inventory selection button can also be radio button
			objs=browser.getHtmlObject(Property.atList(this.widgetProperty,Property.toPropertyArray(".class","Html.INPUT.radio")));
		}
		String id=null;
		int index=-1;
		for(int i=0;i<objs.length;i++) {
			if(objs[i].isEnabled()) {
				index=i;
				break;
			}
		}
		id=objs[index].getAttributeValue(".id");
		if(id==null) {
			throw new ItemNotFoundException("Failed to find an inventory.");
		}
		String invNum= browser.getObjectText(".class","Html.LABEL","for",id);
		if(StringUtil.isEmpty(invNum)) {
			invNum = objs[index].getAttributeValue(".value");
		}
		
		((IToggle)objs[index]).deselect();
				
		Browser.unregister(objs);
		
		if(StringUtil.isEmpty(invNum)) {
			throw new ItemNotFoundException("Failed to get the inventory number");
		}
		
		return invNum;
	}
	
	/**
	 * This method was used to select nums of available inventory randomly
	 * @param num
	 */
	public void selectNumsOfAvailableInventory(int num) {
		IHtmlObject objs[] = browser.getHtmlObject(".class","Html.INPUT.checkbox",".id",new RegularExpression("(InventoryPrompt-\\d+\\.selectedInventoryList_\\d+)|(\\d+_checkBox)",false));
		if(objs.length<1) {//James[20130710]: the inventory selection button can also be radio button
			objs=browser.getHtmlObject(Property.atList(this.widgetProperty,Property.toPropertyArray(".class","Html.INPUT.radio")));
		}
		int count = 0;
		for(int i=0;i<objs.length;i++) {
			if(objs[i].isEnabled()) {
				((IToggle)objs[i]).select();
				ajax.waitLoading();
				count++;
				if(count==num)
					break;
			}
		}
		if(count!=num)
			throw new ErrorOnDataException("Failed to select "+num+" available inventory. There should be only "+count+" available inventory.");
		logger.info("Select "+num+" available inventory.");
	}
}
