//package com.activenetwork.qa.awo.pages.orms.licenseManager.receipt;
//
//import java.util.List;
//
//import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderSearchCommonPage;
//import com.activenetwork.qa.testapi.ErrorOnPageException;
//import com.activenetwork.qa.testapi.interfaces.browser.Browser;
//import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
//import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
///**
// * @Description:This page is under the orders of license manager
// * @Preconditions:
// * @SPEC:
// * @Task#:
// * 
// * @author Phoebe
// * @Date April 2, 2013
// */
//public class LicMgrReceiptSearchPage extends LicMgrOrderSearchCommonPage {
//	private static LicMgrReceiptSearchPage _instance = null;
//
//	protected LicMgrReceiptSearchPage() {
//
//	}
//
//	public static LicMgrReceiptSearchPage getInstance() {
//		if (null == _instance) {
//			_instance = new LicMgrReceiptSearchPage();
//		}
//
//		return _instance;
//	}
//	
//	@Override
//	public boolean exists() {
//		return (browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id",
//				"t_tSEARCHBAR_TAG"));
//	}
//	
//	public List<String> getElementsForReceiptContains(){
//		return browser.getDropdownElements(".id", "receiptContains");
//	}
//	
//	public void setReceiptNum(String receiptNum){
//		browser.setTextField(".id","receiptNumber", receiptNum);
//	}
//	
//	public void clickGo(){
//		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
//	}
//	
//	public void searchByReceiptNum(String receiptNum){
//		this.setReceiptNum(receiptNum);
//		this.clickGo();
//		ajax.waitLoading();
//		this.waitLoading();
//	}
//	
//	public IHtmlObject[] getSearchResultTable(){
//		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".className", "searchResult");
//		if(objs.length < 1){
//			throw new ErrorOnPageException("The result table does not exist!");
//		}
//		return objs;
//	}
//	
//	
//	public List<String> getSearchResultInfo(){
//		IHtmlObject[] objs = this.getSearchResultTable();
//		IHtmlTable table = (IHtmlTable) objs[0];
//		List<String> result = table.getRowValues(1);
//		Browser.unregister(objs);
//		return result;
//	}
//	
//	@Override
//	public void setupOrderSearchCriteria(Object object) {
//		
//	}
//}
