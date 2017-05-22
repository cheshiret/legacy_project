package com.activenetwork.qa.awo.pages.orms.licenseManager.store;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Agents;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;

/**
 *
 * @Description: This page is the sub page for adding a new store status reason
 * @Preconditions:
 * @SPEC:Add store status reason
 * @Task#:Auto-753
 *
 * @author jwang8
 * @Date  Jan 10, 2012
 */
public class LicMgrStoreStatusReasonMgtPage extends LicMgrCommonTopMenuPage{
     private static LicMgrStoreStatusReasonMgtPage instance = null;

     private LicMgrStoreStatusReasonMgtPage(){};

     public static LicMgrStoreStatusReasonMgtPage getInstance(){
    	 if(null == instance){
    		 instance = new LicMgrStoreStatusReasonMgtPage();
    	 }
    	 return instance;
     }

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "storeStatusReasonList");
	}
	/**
	 * Click the add button.
	 */
	public void clickAddButton(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add");
	}

	/**
	 * Get the store status reason row list info.
	 * @param expectedReason- the expected value of reason.
	 * @param expectedStaus - the expected value of status.
	 * @return List<String> - the list of specific  row info.
	 */
    public List<String> getStoreStatusReasonListRowInfo(String expectedStaus,String expectedReason){
    	List<String>  list = new ArrayList<String>();
    	IHtmlObject[] objs = browser.getTableTestObject(".id", "storeStatusReasonList");
    	if(objs.length < 1) {
			throw new ObjectNotFoundException("There is not a table which id = storeStatusReasonList");
		}
    	IHtmlTable table = (IHtmlTable)objs[0];
    	for(int i =0;i<table.rowCount(); i++){

    		if(expectedStaus.equals(table.getCellValue(i, 1)) && expectedReason.equals(table.getCellValue(i, 2))){
    			list = table.getRowValues(i);
    		}
    	}
    	Browser.unregister(objs);
    	return list;
    }

    public String getStoreStatusReasonId(String expectedStaus,String expectedReason){
        String id = "";
    	IHtmlObject[] objs = browser.getTableTestObject(".id", "storeStatusReasonList");
    	if(objs.length < 1) {
			throw new ObjectNotFoundException("There is not a table which id = storeStatusReasonList");
		}
    	IHtmlTable table = (IHtmlTable)objs[0];
    	for(int i =0;i<table.rowCount(); i++){
    		if(expectedStaus.equals(table.getCellValue(i, 1)) && expectedReason.equals(table.getCellValue(i, 2))){
    			id = table.getCellValue(i, 0);;
    		}
    	}
    	Browser.unregister(objs);
    	return id;
    }

    /**
	 * Compare the store status reason list info.
	 * @param expectedAgents - the expected agents info.
	 * @return boolean - compare the store status reason list info.
	 */
    public boolean compareStoreStatusReasonList(Agents expectedAgents){
    	boolean pass = true;
    	String temp = "";
    	List<String> array = this.getStoreStatusReasonListRowInfo(expectedAgents.status,expectedAgents.reason);
    	if(array.size()<=0){
    		pass &= false;
    		logger.error("Add agents status reason failed");
    	}
    	temp = array.get(0);
    	if(!temp.matches("\\d+")){
    		pass &=false;
    		logger.error("Agents id "+temp+" error");
    	}

    	temp = array.get(1);
    	if(!temp.equals(expectedAgents.status)){
    		pass &=false;
    		logger.error("Agents status "+temp+" error");
    	}
    	temp = array.get(2);
    	if(!temp.equals(expectedAgents.reason)){
    		pass &=false;
    		logger.error("Agents reason "+temp+" error");
    	}

    	temp = array.get(3);
    	if(!temp.equals(expectedAgents.systemStatusReason)){
    		pass &=false;
    		logger.error("Agents system status reason "+temp+" error");
    	}

    	temp = array.get(4);
    	if(!temp.replace(" ", "").equals(expectedAgents.creatUser.replace(" ", ""))){
    		pass &=false;
    		logger.error("Agents create user "+temp+" error");
    	}

    	temp = array.get(5);
    	if(!temp.equals(expectedAgents.creatLocation)){
    		pass &=false;
    		logger.error("Agents create location "+temp+" error");
    	}
    	return pass;
    }
}
