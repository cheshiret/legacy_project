package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityPermitType;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.TestProperty;


/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  Mar 14, 2012
 */
public class InvMgrPermitTypeListPage extends InventoryManagerPage {

	private static InvMgrPermitTypeListPage _instance = null;
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();

	public static InvMgrPermitTypeListPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrPermitTypeListPage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "PermitTypeSearchCriteria.active");
	}
	
	public void clickAddNew() {
		browser.clickGuiObject(".class","Html.A",".text", "Add New", true);
	}
	
	public void clickGo(){
		browser.clickGuiObject(".class","Html.A",".text", "Search", true);
	}
	
	public void selectStatus(String status){
		browser.selectDropdownList(".id", "PermitTypeSearchCriteria.active", status, true);
	}
	
	//not debugged.
	public List<List<String>> getPermitTypeList(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", "permitTypeList");
		if(objs == null || objs.length < 0) {
			throw new ErrorOnPageException("Can't find the table.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		List<List<String>> entranceList = new ArrayList<List<String>>();
		if(table.rowCount() > 2){
			// 1st line is title.
			for(int row=1; row<table.rowCount();row++){
				entranceList.add(table.getRowValues(row));
			}
			
		}
		return entranceList;
	}
	
	public boolean checkPermitTypeStatus(String permitTypeID, String status, String schema)
	{

		String env = TestProperty.getProperty("target_env");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + schema;
		db.resetSchema(schema);
		logger.info("Changed schema to -->>"+schema);
		String[] colNames = { "id", "status" };

		String query = "select id,decode(active_ind,1,'yes',0,'no') as status from P_PERMIT_TYPE where ID = '"
		+permitTypeID
		+"'";
		logger.debug("Execute query: " + query);
		List<String[]> rs = db.executeQuery(query, colNames);
		String printTmp = null;
		for(String[] tmp : rs)
		{
			printTmp="";
			for (String s: tmp)
			{
				printTmp+="["+s+"]";
			}
			logger.info(printTmp);
			
			
			if(tmp[0].equalsIgnoreCase(permitTypeID))
			{
				if(tmp[1].equalsIgnoreCase(status))
				{
					return true;
				}else
				{
					return false;
				}
			}
			
		}
		
		return false;
	}
	
	public void clickPermitTypeIDLink(String permitTypeID){
		browser.clickGuiObject(".class", "Html.A", ".text", permitTypeID);
	}
	
	public String activatePermitType(String permitTypeID)
	{
		browser.checkHtmlObjectExists(".class", "Html.INPUT.checkbox", ".value",permitTypeID);
		browser.selectCheckBox(".class", "Html.INPUT.checkbox", ".value",permitTypeID, false);
		browser.clickGuiObject(".class", "Html.A", ".text","Activate");
		browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Activate");
		String result  =getSuccessMsg(); 
		
		if(result.equals(""))
		{
			return "Activate result is empty";
		}else{
			return "Activate result is "+result;
		}
		
	}
	
	public String deactivatePermitType(String permitTypeID)
	{
		browser.checkHtmlObjectExists(".class", "Html.INPUT.checkbox", ".value",permitTypeID);
		browser.selectCheckBox(".class", "Html.INPUT.checkbox", ".value",permitTypeID, false);
		browser.clickGuiObject(".class", "Html.A", ".text","Deactivate");
		browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Deactivate");
		String result  =getSuccessMsg(); 
		
		if(result.equals(""))
		{
			return "Deactivate result is empty";
		}else{
			return "Deactivate result is "+result;
		}
		
	}
	
	public String getSuccessMsg(){
		String successMsg = "";
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", new RegularExpression("NOTSET", false));
		if(objs != null && objs.length > 0){
			successMsg=objs[0].text();
			logger.info("Activate/Deactivate permit types id-->> "+successMsg);
		}
		Browser.unregister(objs);
		return successMsg;
	}
	
	public void searchPermitType(String status){
		this.selectStatus(status);
		this.clickGo();
		this.waitLoading();
	}

	public void clickQuotaTypesTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Quota Types");	
	}
	public void clickEntrancesTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Entrances");	
	}
}
