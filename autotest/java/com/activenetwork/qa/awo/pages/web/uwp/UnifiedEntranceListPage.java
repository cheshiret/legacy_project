package com.activenetwork.qa.awo.pages.web.uwp;
//package testAPI.pages.web.uwp;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import runtime.Browser;
//import runtime.HtmlObject;
//import runtime.ITable;
//
///**
// * @author SWang
// * @Date  Oct 31, 2011
// */
//public class UnifiedEntranceListPage extends UwpCampingPage {
//
//	private static UnifiedEntranceListPage _instance = null;
//
//	public static UnifiedEntranceListPage getInstance() {
//		if (null == _instance)
//			_instance = new UnifiedEntranceListPage();
//
//		return _instance;
//	}
//
//	public UnifiedEntranceListPage() {}
//
//	public boolean exists() {
//		return browser.checkHtmlObjectExists(".id", "entranceSearch", ".text", new RegularExpression("Entrance List|Destination Zone List", false));
//	}
//
//	/**Check Next link is available*/
//	public boolean checkNext(){
//		Property[] p = new Property[3];
//		p[0] = new Property(".id", "resultNext");
//		p[1] = new Property(".text", new RegularExpression("^Next.*",false));
//		p[2] = new Property(".className", "disabled");
//		return !browser.checkHtmlObjectExists(p);
//	}
//
//	/**Click Next button*/
//	public void clickNext(){
//		browser.clickGuiObject(".class", "Html.A", ".text", "Next");
//	}
//
//	/**
//	 * Get all entrance names
//	 * @return
//	 */
//	public List<String> getAllEntrance(){
//		List<String> entrance = new ArrayList<String>();
//		int count = -1;
//		do{
//			if(this.checkNext() && count!=-1){
//				this.clickNext();
//				this.waitExists();
//			}
//			HtmlObject[] objs = browser.getTableTestObject(".id", "shoppingitems", ".text", 
//					new RegularExpression("Entrance Search Results.*Online availability Entrance.*", false));
//			ITable table = (ITable)objs[0];
//			for(int i=3; i<table.rowCount(); i++){
//				entrance.add(table.getCellValue(i, 1).replaceAll("Map", "").trim());
//			}
//			count++;
//		}while(this.checkNext());
//
//		return entrance;
//	}
//	
//	public boolean isSeeDetailsLinkExist() {
//		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.A", ".classname", "book now", ".text", "See Details"));
//	}
//	
//	public void verifySeeDetailsLinkExist(boolean exp) {
//		String msg = exp ? " " : " not ";
//		if (exp != this.isSeeDetailsLinkExist()) {
//			throw new ErrorOnPageException("See Details link should" + msg + "exist on the entrance list page!");
//		}
//		logger.info("See Details link does" + msg + "exist on the entrance list page!");
//	}
//	
//	public boolean isFindNextAvailLinkExist() {
//		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.A", ".classname", "book next", ".text", "Find Next Avail. Date**"));
//	}
//	
//	public void verifyFindNextAvailLinkExist(boolean exp) {
//		String msg = exp ? " " : " not ";
//		if (exp != this.isFindNextAvailLinkExist()) {
//			throw new ErrorOnPageException("Find Next Avail link should" + msg + "exist on the entrance list page!");
//		}
//		logger.info("Find Next Avail does" + msg + "exist on the entrance list page!");
//	}
//	
//	private HtmlObject[] getSearchResultsTables() {
//		HtmlObject[] objs = browser.getTableTestObject(".id", "shoppingitems", ".text", 
//				new RegularExpression("(Entrance|Destination Zone) Search Results.*Online availability.*", false));
//		if (objs.length < 1) {
//			throw new ErrorOnPageException("No search results table displayed on the page!");
//		}
//		return objs;
//	}
//	
//	private String getFirstSearchResultColumnValue(int index) {
//		HtmlObject[] objs = this.getSearchResultsTables();
//		ITable resultTable = (ITable)objs[0];
//		String value = resultTable.getCellValue(3, index); // The first result row's index = 3
//		Browser.unregister(objs);
//		return value;
//	}
//	
//	/** Get the online availability status for the first result on entrance list page */
//	public String getFirstResultOnlineAvailabilityStatus() {
//		return this.getFirstSearchResultColumnValue(0); // the first column is Online Availability
//	}
//	
//	/** Verify the online availability status for the first result on entrance list page */
//	public void verifyFirstResultOnlineAvailStatus(String exp) {
//		String actual = this.getFirstResultOnlineAvailabilityStatus();
//		if (!actual.startsWith(exp)) {
//			throw new ErrorOnPageException("The online availability status for the first result is wrong!", exp, actual);
//		}
//		logger.info("Verify the online availability status for the first result correct as " + exp);
//	}
//	
//	public void clickEntranctNameLink(String entrance) {
//		browser.clickGuiObject(".class", "Html.A", ".text", entrance);
//	}
//}
