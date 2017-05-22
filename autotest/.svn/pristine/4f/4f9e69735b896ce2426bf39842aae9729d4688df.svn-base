/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.operationManager;

import com.activenetwork.qa.awo.datacollection.legacy.RefundIRequestnfo;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.KeyInput;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Oct 31, 2012
 */
public class OpMgrSearchRefundRequestPage extends OperationsManagerPage{

	private static OpMgrSearchRefundRequestPage _instance = null;

	protected OpMgrSearchRefundRequestPage() {

	}

	public static OpMgrSearchRefundRequestPage getInstance() {
		if (null == _instance) {
			_instance = new OpMgrSearchRefundRequestPage();
		}

		return _instance;
	}

	public boolean exists() {
		// using search result table as page mark
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("Refund Request ID", false));
	}
	
	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^(Go|Search)$", false));
	}
	
	public void clickRefundReqID(String id){
		browser.clickGuiObject(".class", "Html.A", ".text", id);
	}
	
	private IHtmlTable getTable(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("Refund Request ID( )*Order #", false));
		if(objs.length < 1){
			Browser.unregister(objs);
			throw new ItemNotFoundException("Can't get search result table.");
		} else {
			IHtmlTable table = (IHtmlTable)objs[0];
//			Browser.unregister(objs);
			return table;
		}
	}
	
	public String getFirstRefundReqID(){
		String id = StringUtil.EMPTY;
		IHtmlTable table = this.getTable();
		if(table.rowCount() > 2){
			id = table.getCellValue(1,1);
		}
		return id;
	}
	
	public void clickFirstRefundReqID(){
		clickRefundReqID(this.getFirstRefundReqID());
	}
	
	public String getFirstLocation(){
		String loc = StringUtil.EMPTY;
		IHtmlTable table = this.getTable();
		if(table.rowCount() > 2){
			loc = table.getCellValue(1,7);
		}
		return loc;
	}
	
	public String getFirstState(){
		String state = StringUtil.EMPTY;
		IHtmlTable table = this.getTable();
		if(table.rowCount() > 2){
			state = table.getCellValue(1,8);
		}
		return state;
	}
	
	public void setSearchBy(String searchBy){
		// if is blank, use default value.
		if(!StringUtil.isEmpty(searchBy)){
			browser.selectDropdownList("id", new RegularExpression("RefundRequestCriteria.searchType", false), searchBy);
		}
	}
	
	public void setSearchValue(String searchValue){
		browser.setTextField(".id", new RegularExpression("RefundRequestCriteria.searchValue", false), searchValue);
	}
	
	public void setFromDate(String fromDate){
		browser.setTextField(".id", new RegularExpression("RefundRequestCriteria.fromDate_ForDisplay", false), fromDate);
	}

	public void setToDate(String toDate){
		browser.setTextField(".id", new RegularExpression("RefundRequestCriteria.toDate_ForDisplay", false), toDate);
	}
	
	public void setSearchDate(String searchDate){
		browser.selectDropdownList(".id", new RegularExpression("RefundRequestCriteria.dateSearchType", false), searchDate);
	}
	
	public void searchRefundRequest(RefundIRequestnfo info){
		// TODO set up search criteria
		this.setSearchDate(info.searchDate);
		this.setFromDate(info.from);
		this.setToDate(info.to);
		browser.inputKey(KeyInput.get(KeyInput.TAB));
		this.clickGo();
		this.waitLoading();
	}
}
