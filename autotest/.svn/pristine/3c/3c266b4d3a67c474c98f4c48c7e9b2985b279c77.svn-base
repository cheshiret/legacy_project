package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * ScriptName: LicMgrVoidUndoVoidPrivilegePage
 * Description:
 * @date: Feb 12, 2011
 * @author qchen
 *
 */
public class LicMgrVoidUndoVoidPrivilegePage extends LicMgrCommonTopMenuPage {
	private static LicMgrVoidUndoVoidPrivilegePage _instance = null;
	
	protected LicMgrVoidUndoVoidPrivilegePage() {
		
	}
	
	public static LicMgrVoidUndoVoidPrivilegePage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrVoidUndoVoidPrivilegePage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "voidundovoidprivilege");
	}
	
	/**
	 * Set privilege order search criteria - order number
	 * @param orderNum
	 */
	public void setOrderNum(String orderNum) {
		browser.setTextField(".id", new RegularExpression("VoidPrivilegeSearchCriteria-\\d+\\.orderNumber", false), orderNum, true);
	}
	
	/**
	 * Set privilege order search criteria - store id
	 * @param storeID
	 */
	public void setStoreID(String storeID) {
		browser.setTextField("", new RegularExpression("VoidPrivilegeSearchCriteria-\\d+\\.storeId", false), storeID);
	}

	/**
	 * Set privilege order search criteria - register id
	 * @param registerID
	 */
	public void setRegisterID(String registerID) {
		browser.setTextField("", new RegularExpression("VoidPrivilegeSearchCriteria-\\d+\\.registerId", false), registerID);
	}
	
	/**
	 * Set privilege order search criteria - vender number
	 * @param venderNum
	 */
	public void setVenderNum(String venderNum) {
		browser.setTextField("", new RegularExpression("VoidPrivilegeSearchCriteria-\\d+\\.vendorId", false), venderNum);
	}
	
	/**
	 * Click Go button to search privilege order
	 */
	public void clickGO() {
		browser.clickGuiObject(".class", "Html.A", ".text",  new RegularExpression("Go", false));
	}
	
	/**
	 * Overload method to select privilege order without order number to void
	 * @return
	 */
	public boolean selectPrivilegeOrder() {
		return this.selectPrivilegeOrder("");
	}
	
	/**
	 * Select the privilege order identifying by order number
	 * @param priOrdNum
	 * @return
	 */
	public boolean selectPrivilegeOrder(String priOrdNum) {
		IHtmlObject tableObjs[] = browser.getTableTestObject(".id", "voidundovoidprivilege");
		IHtmlObject selectObjs[] = browser.getHtmlObject(".class", "Html.INPUT.radio");
		
		boolean isOrderExists = true;
		IHtmlTable table = null;
		if(tableObjs.length > 0) {
			table = (IHtmlTable)tableObjs[0];
		} else {
			throw new ItemNotFoundException("Cannot find the table object.");
		}
		
		if(selectObjs.length == 0) {
			isOrderExists = false;
		}
			
		if(null != priOrdNum && priOrdNum.length() > 0) {
			String orderNumList[] = new String[selectObjs.length];
			//get the all order number and insert into the array orderNumList
			int tempIndex = 0;
			for(int i = 0; i < table.rowCount(); i ++) {
				if(table.getCellValue(i, 0).split(":")[0].trim().equals("Order #")) {
					orderNumList[tempIndex] = table.getCellValue(i, 0).trim().split(":")[1].trim();
					tempIndex ++;
				}
			}
			
			//traverse the orderNumList array with the known order, if get the index break
			int counter = -1;
			for(int j = 0; j < orderNumList.length; j ++) {
				if(orderNumList[j].equalsIgnoreCase(priOrdNum.trim())) {
					counter = j;
					break;
				}
			}
			
			//select the specified(index is counter) radio button
			if(counter != -1) {
				((IRadioButton)selectObjs[counter]).select();
				ajax.waitLoading();
			} else {
				throw new ItemNotFoundException("Cannot find the Privilege Order(#=" + priOrdNum + ").");
			}
			
			Browser.unregister(tableObjs);
			Browser.unregister(selectObjs);
		}
		
		return isOrderExists;
	}
	
	/**
	 * Overload method to select privilege without privilege number to void
	 * @return
	 */
	public boolean selectPrivilege() {
		return this.selectPrivilege("");
	}
	
	public void selectPriByName(String privilegeName){
		if(StringUtil.notEmpty(privilegeName)) {
			IHtmlObject tableObjs[] = browser.getTableTestObject(".id", "voidundovoidprivilege");
			IHtmlTable table = null;
			if(tableObjs.length > 0) {
				table = (IHtmlTable)tableObjs[0];
			} else {
				throw new ItemNotFoundException("Cannot find the table object.");
			}
	
			IHtmlObject radioObjs[] = browser.getHtmlObject(".class", "Html.INPUT.radio", table);
			String privilegeNameList[] = new String[radioObjs.length];
			
			//get the all privilege name
			int tempIndex = 0;
			int rowNumOfKnownPrivNum = -1;
			for(int i = 0; i < table.rowCount(); i ++) {
				if(!table.getCellValue(i, 0).split(":")[0].trim().equals("Order #") && !table.getCellValue(i, 1).trim().equals("Product")) {
					privilegeNameList[tempIndex] = table.getCellValue(i, 1).trim();
					tempIndex ++;
				}
				if(table.getCellValue(i, 1).trim().equalsIgnoreCase(privilegeName)) {
					rowNumOfKnownPrivNum = i;
				}
			}
			
			//traverse the privilegeNumList array with the known privilege number, if get the index break
			int counter = -1;
			for(int j = 0; j < privilegeNameList.length; j ++) {
				if(privilegeNameList[j].equalsIgnoreCase(privilegeName.trim())) {
					counter = j;
					break;
				}
			}
			
			//select the specified(index is counter) radio button
			String privNameFromUI = table.getCellValue(rowNumOfKnownPrivNum, 1).trim();
			if((counter != -1) && privNameFromUI.equals(privilegeName)) {
				((IRadioButton)radioObjs[counter]).select();
				ajax.waitLoading();
			} else {
				throw new ItemNotFoundException("Cannot find the Privilege(name =" + privilegeName + ").");
			}
			Browser.unregister(tableObjs);
			Browser.unregister(radioObjs);
		}
	}
	
	/**
	 * Select the privilege identifying by privilege number
	 * @param privilegeNum
	 * @return
	 */
	public boolean selectPrivilege(String privilegeNum) {
		IHtmlObject tableObjs[] = browser.getTableTestObject(".id", "voidundovoidprivilege");
		Property[] p1=Property.toPropertyArray(".id", "voidundovoidprivilege");
		Property[] p2=Property.toPropertyArray(".class", "Html.INPUT.radio");
		IHtmlObject radioObjs[] = browser.getHtmlObject(Property.atList(p1,p2));
		
		boolean isPrivilegeExists = true;
		IHtmlTable table = null;
		if(tableObjs.length > 0) {
			table = (IHtmlTable)tableObjs[0];
		} else {
			throw new ItemNotFoundException("Cannot find the table object.");
		}
		
		if(radioObjs.length == 0) {
			isPrivilegeExists = false;
		}
		
		if(null != privilegeNum && privilegeNum.length() > 0) {
			String privilegeNumList[] = new String[radioObjs.length];
			//get the all privilege numbers
			int tempIndex = 0;
			int rowNumOfKnownPrivNum = -1;
			for(int i = 0; i < table.rowCount(); i ++) {
				if(!table.getCellValue(i, 0).split(":")[0].trim().equals("Order #") && !table.getCellValue(i, 0).trim().equals("Privilege Number")) {
					privilegeNumList[tempIndex] = table.getCellValue(i, 0).trim();
					tempIndex ++;
				}
				if(table.getCellValue(i, 0).trim().equalsIgnoreCase(privilegeNum)) {
					rowNumOfKnownPrivNum = i;
				}
			}
			
			//traverse the privilegeNumList array with the known privilege number, if get the index break
			int counter = -1;
			for(int j = 0; j < privilegeNumList.length; j ++) {
				if(privilegeNumList[j].equalsIgnoreCase(privilegeNum.trim())) {
					counter = j;
					break;
				}
			}
			
			//select the specified(index is counter) radio button
			String privNumFromUI = table.getCellValue(rowNumOfKnownPrivNum, 0).trim();
			if((counter != -1) && privNumFromUI.equals(privilegeNum)) {
				((IRadioButton)radioObjs[counter]).select();
				ajax.waitLoading();
			} else {
				throw new ItemNotFoundException("Cannot find the Privilege(#=" + privilegeNum + ").");
			}
			
			Browser.unregister(tableObjs);
			Browser.unregister(radioObjs);
		}
		return isPrivilegeExists;
	}
	
	/**
	 * Click the 'Void Selected Transaction' link to void privilege order
	 */
	public void clickVoidOrUndoVoidSelectedTransaction() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Void Selected Transaction|Undo Void of Selected Transaction", false));
		ajax.waitLoading();
	}
	
	public int getNumOfOrderItemRadio(){
		IHtmlObject[] objs = browser.getRadioButton(Property.toPropertyArray(".id", new RegularExpression("\\d+", false)));
		if(objs.length<1){
			throw new ObjectNotFoundException("Failed to get order items radio");
		}
		int value = objs.length;
		Browser.unregister(objs);
		return value;
	}
	
	public void selectkOrderItemRadio(int index){
		browser.selectRadioButton(".id", new RegularExpression("\\d+", false), index);
	}
	/**
	 * The method used to get displayed privilege row num
	 * @return
	 */
	public int getDisplayedPrivilegeRowNum(){
		IHtmlObject[] objs = browser.getTableTestObject(".id","voidundovoidprivilege");
		IHtmlTable grid = (IHtmlTable)objs[0];
		int qty = grid.rowCount()-1;
		Browser.unregister(objs);
		return qty;
	}
	
	public int getPrivilegeRow(String privilegeName){
		IHtmlObject[] objs = browser.getTableTestObject(".id","voidundovoidprivilege");
		if(objs.length<1){
			throw new ObjectNotFoundException("Failed to find voidundovoidprivilege table");
		}
		int index = -1;
		IHtmlTable grid = (IHtmlTable)objs[0];
		List<String> names = grid.getColumnValues(1);
		for(int i=2; i<names.size(); i++){
			if(names.get(i).equals(privilegeName)){
				index = i-2;
				break;
			}
		}
		Browser.unregister(objs);
		return index;
	}
}
