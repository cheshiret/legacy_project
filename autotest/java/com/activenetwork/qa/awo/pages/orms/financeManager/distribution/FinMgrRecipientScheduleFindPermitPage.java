package com.activenetwork.qa.awo.pages.orms.financeManager.distribution;


import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class FinMgrRecipientScheduleFindPermitPage extends FinanceManagerPage{
	
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrRecipientScheduleFindPermitPage _instance = null;
	
	protected FinMgrRecipientScheduleFindPermitPage(){
		
	}
	
	static public FinMgrRecipientScheduleFindPermitPage getInstance(){
		if (null == _instance){
			_instance = new FinMgrRecipientScheduleFindPermitPage();
		}
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression("^\\s*Permit\\s*$", false));	
	}
	
	public void selectSearchBy(String searchType){
		browser.selectDropdownList(".id", "perm_search_by", searchType);
	}
	
	public void setSearchValue(String searchValue){
		browser.setTextField(".id", "perm_search_by_value", searchValue);
	}
	
	public void selectShowStatus(String status){
		browser.selectDropdownList(".id", "perm_show_active", status);
	}
	
	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}
	
	public void clickSelect(int index){
		browser.clickGuiObject(".class", "Html.A", ".text", "Select", index);
	}
	
	public void clickSelectByRevLocationAndRecipient(String revenueLocation, String recipient){
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("Recipient Permit ID.*",false));
		
		//The table we are finding is the second one but not the first one.
		IHtmlTable recipientPermitTable = (IHtmlTable)objs[1];
		
		int row = -1;
		for(int i=1; i<recipientPermitTable.rowCount(); i++){
			String revLocationFromUI = recipientPermitTable.getCellValue(i, 3);
			String recipientFromUI = recipientPermitTable.getCellValue(i, 5);
			if(revLocationFromUI.equals(revenueLocation) && recipientFromUI.equals(recipient)){
				row = i;
				break;
			}
			
		}
		if(row < 1 ){
			throw new ErrorOnPageException("Have not this permit schedule, please check date about revenue location and recipient.");
		}else {			
			this.clickSelect(row-1);
		}
		
		Browser.unregister(objs);		
	}
	
	/**
	 * search permit by recipient name
	 * @param recipient
	 */
	public void searchByRecipientName(String recipient){
		this.selectSearchBy("Recipient");
		if(!recipient.equals("")){
			this.setSearchValue(recipient);
		}
		this.selectShowStatus("Active");
		this.clickGo();
		this.waitLoading();
		ajax.waitLoading();
	}
	
	public void searchByRecipientID(String id){
		this.selectSearchBy("Recipient Permit ID");
		this.setSearchValue(id);
		this.selectShowStatus("Active");
		this.clickGo();
		this.waitLoading();
	}
	
	public void selectRecipientID(String id) {
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("Recipient Permit ID.*",false));
		
		IHtmlTable recipientPermitTable = (IHtmlTable)objs[objs.length-1];
		
		int row = -1;
		for(int i=1; i<recipientPermitTable.rowCount(); i++){
			String idUI = recipientPermitTable.getCellValue(i, 1);
			if(idUI.equals(id)){
				row = i;
				break;
			}
			
		}
		if(row < 1 ){
			throw new ErrorOnPageException("Could not find Permit by id "+id);
		}else {			
			this.clickSelect(row-1);//header row
		}
		
		Browser.unregister(objs);	
	}
	
}
