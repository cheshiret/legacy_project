/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.operationManager;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ReceiptInfo;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Nov 1, 2012
 */
public class OpMgrSearchReceiptlsPage extends OperationsManagerPage{

	private static OpMgrSearchReceiptlsPage _instance = null;

	protected OpMgrSearchReceiptlsPage() {

	}

	public static OpMgrSearchReceiptlsPage getInstance() {
		if (null == _instance) {
			_instance = new OpMgrSearchReceiptlsPage();
		}

		return _instance;
	}

	public boolean exists() {
		// using search criteria as page mark
		return browser.checkHtmlObjectExists(".id", new RegularExpression("t_tSEARCHBAR_TAG", false));
	}
	
	public void clickSearch(){
		browser.clickGuiObject(".class", "Html.A", ".text",  new RegularExpression("^Search$", false));
	}

	public void setOrderNum(String orderNum){
		browser.setTextField(".id", "reservationNumber", orderNum);
	}
	
	public void setFromDate(String fromDate){
		browser.setTextField(".id", "receiptStartDate_ForDisplay", fromDate);
	}

	public void setToDate(String toDate){
		browser.setTextField(".id", "receiptEndDate_ForDisplay", toDate);
	}

	public void searchReceipt(ReceiptInfo info){
		// TODO set other search criteria
		if(!StringUtil.isEmpty(info.searchOrderNum)){
			this.setOrderNum(info.searchOrderNum);
		}
		if(!StringUtil.isEmpty(info.toDate)){
			this.setToDate(info.toDate);
		}
		if(!StringUtil.isEmpty(info.fromDate)){
			this.setFromDate(info.fromDate);
		}

		this.clickSearch();
		this.waitLoading();
	}
	
	private IHtmlTable getTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".class", "Html.TABLE", ".text", new RegularExpression("^( )*Receipt #( )*Orders( )*Receipt Date & Time", false));
		if(objs.length < 1){
			Browser.unregister(objs);
			throw new ItemNotFoundException("Can't find search result table");
		} else {
			IHtmlTable table = (IHtmlTable)objs[0];
//			Browser.unregister(objs);
			return table;
		}
	}
	
	public String getFirstReceiptNum(){
		String id = "";
		IHtmlTable table = this.getTable();
		if(table.rowCount() > 1){
			id = table.getCellValue(1, 0);
		}
		return id;
	}
	
	public void clickReceiptNum(String id){
		browser.clickGuiObject(".class", "Html.A", ".text", id);
	}
	
	
	
	
	
}
