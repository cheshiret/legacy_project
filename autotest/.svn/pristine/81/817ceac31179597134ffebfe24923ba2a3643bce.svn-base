package com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PriInventoryItemInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrPrivilegeInventoryChangeHistoryPage extends LicMgrTopMenuPage{
	
	private static LicMgrPrivilegeInventoryChangeHistoryPage _instance = null;
	
	protected LicMgrPrivilegeInventoryChangeHistoryPage(){
		
	}
	
	public static LicMgrPrivilegeInventoryChangeHistoryPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrPrivilegeInventoryChangeHistoryPage();
		}
		return _instance;
	}
	
	public boolean exists() {

		return browser.checkHtmlObjectExists(".id", "auditlog_LIST");
	}
	
	public String getInventoryNum(){
		List<Property[]> proList = new ArrayList<Property[]>();
		
		Property[] divP = new Property[2];
		divP[0] = new Property(".class","Html.DIV");
		divP[1] = new Property(".text",new RegularExpression("^Inventory Number.*",false));
		
		Property[] spanP = new Property[1];
		spanP[0] = new Property(".class","Html.SPAN");
		
		proList.add(divP);
		proList.add(spanP);		
		IHtmlObject[] objs = browser.getHtmlObject(proList);
		if(objs.length<1){
			throw new ObjectNotFoundException("Did not found inventory number span object.");
		}
		
		String value = objs[0].getProperty(".text");
		Browser.unregister(objs);
		return value.replaceAll("Inventory Number", StringUtil.EMPTY);
	}
	
	public String getStatus(){
		List<Property[]> proList = new ArrayList<Property[]>();
		
		Property[] divP = new Property[2];
		divP[0] = new Property(".class","Html.DIV");
		divP[1] = new Property(".text",new RegularExpression("^Status.*",false));
		
		Property[] spanP = new Property[1];
		spanP[0] = new Property(".class","Html.SPAN");
		
		proList.add(divP);
		proList.add(spanP);		
		IHtmlObject[] objs = browser.getHtmlObject(proList);
		if(objs.length<1){
			throw new ObjectNotFoundException("Did not found status span object.");
		}
		
		String value = objs[0].getProperty(".text");
		Browser.unregister(objs);
		return value.replaceAll("Status", StringUtil.EMPTY);
	}
	
	public String getStore(){
		List<Property[]> proList = new ArrayList<Property[]>();
		
		Property[] divP = new Property[2];
		divP[0] = new Property(".class","Html.DIV");
		divP[1] = new Property(".text",new RegularExpression("^Store.*",false));
		
		Property[] spanP = new Property[1];
		spanP[0] = new Property(".class","Html.SPAN");
		
		proList.add(divP);
		proList.add(spanP);		
		IHtmlObject[] objs = browser.getHtmlObject(proList);
		if(objs.length<1){
			throw new ObjectNotFoundException("Did not found store span object.");
		}
		
		String value = objs[0].getProperty(".text");
		Browser.unregister(objs);
		return value.replaceAll("Store", StringUtil.EMPTY);
	}
	
	public String getOrder(){
		List<Property[]> proList = new ArrayList<Property[]>();
		
		Property[] divP = new Property[2];
		divP[0] = new Property(".class","Html.DIV");
		divP[1] = new Property(".text",new RegularExpression("^Order.*",false));
		
		Property[] spanP = new Property[1];
		spanP[0] = new Property(".class","Html.SPAN");
		
		proList.add(divP);
		proList.add(spanP);		
		IHtmlObject[] objs = browser.getHtmlObject(proList);
		if(objs.length<1){
			throw new ObjectNotFoundException("Did not found Order span object.");
		}
		
		String value = objs[0].getProperty(".text");
		Browser.unregister(objs);
		return value.replaceAll("Order", StringUtil.EMPTY);
	}
	
	public String getPrivilegeNumber(){
		List<Property[]> proList = new ArrayList<Property[]>();
		
		Property[] divP = new Property[2];
		divP[0] = new Property(".class","Html.DIV");
		divP[1] = new Property(".text",new RegularExpression("^Privilege Number.*",false));
		
		Property[] spanP = new Property[1];
		spanP[0] = new Property(".class","Html.SPAN");
		
		proList.add(divP);
		proList.add(spanP);		
		IHtmlObject[] objs = browser.getHtmlObject(proList);
		if(objs.length<1){
			throw new ObjectNotFoundException("Did not found Privilege Number span object.");
		}
		
		String value = objs[0].getProperty(".text");
		Browser.unregister(objs);
		return value.replaceAll("Privilege Number", StringUtil.EMPTY);
	}
	
	public void clickOk(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public List<ChangeHistory> getHistoriesInformation() {
		List<ChangeHistory> list = new ArrayList<ChangeHistory>();
		IHtmlObject[] objs = browser.getTableTestObject(".id", "auditlog_LIST");
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find any change history.");
		}
		IHtmlTable table = (IHtmlTable) objs[0];
		int rowCount = table.rowCount();
		for (int i = 1; i < rowCount; i++) {
			ChangeHistory history = new ChangeHistory();
			history.changeDate = table.getCellValue(i, 0);
			history.object = table.getCellValue(i, 1);
			history.action = table.getCellValue(i, 2);
			history.field = table.getCellValue(i, 3);
			history.oldValue = table.getCellValue(i, 4);
			history.newValue = table.getCellValue(i, 5);
			history.user = table.getCellValue(i, 6);
			history.location = table.getCellValue(i, 7);
			list.add(history);
		}
		Browser.unregister(objs);
		return list;
	}
	
	public boolean verifyInventoryInfo(PriInventoryItemInfo expPriInventoryItemInfo){
		boolean result = true;
		String value = this.getInventoryNum();
		if(!expPriInventoryItemInfo.inventoryNum.equals(value)){
			result &= false;
			MiscFunctions.compareResult("Inventory number is not correct.", expPriInventoryItemInfo.inventoryNum, value);
		}else {
			logger.info("Inventory number is correct.");
		}
		
		value = this.getStatus();
		if(!expPriInventoryItemInfo.status.equals(value)){
			result &= false;
			MiscFunctions.compareResult("Inventory item status is not correct.", expPriInventoryItemInfo.status, value);
		}else {
			logger.info("Inventory item status is correct.");
		}
		
		value = this.getStore();
		if(!expPriInventoryItemInfo.agentInfo.equals(value)){
			result &= false;
			MiscFunctions.compareResult("Inventory item agent info is not correct.", expPriInventoryItemInfo.agentInfo, value);
		}else {
			logger.info("Inventory item agetn info is correct.");
		}
		
		value = this.getOrder();
		if(!expPriInventoryItemInfo.order.equals(value)){
			result &= false;
			MiscFunctions.compareResult("Inventory item order info is not correct.", expPriInventoryItemInfo.order, value);
		}else {
			logger.info("Inventory item order info is correct.");
		}
		
		value = this.getPrivilegeNumber();
		if(!expPriInventoryItemInfo.privielgeNumber.equals(value)){
			result &= false;
			MiscFunctions.compareResult("Inventory item privilege number info is not correct.", expPriInventoryItemInfo.privielgeNumber, value);
		}else {
			logger.info("Inventory item privilege number info is correct.");
		}
		
		return result;
	}

}
