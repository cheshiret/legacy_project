package com.activenetwork.qa.awo.pages.web.recgov;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.web.uwp.UwpPermitingPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Swang
 * @Date  Mar 25, 2013
 */
public class RecgovEntranceListPage extends UwpPermitingPage {

	private static RecgovEntranceListPage _instance = null;

	public static RecgovEntranceListPage getInstance() {
		if (null == _instance)
			_instance = new RecgovEntranceListPage();

		return _instance;
	}

	public RecgovEntranceListPage() {}

	public static String DESTINATION_ZONE_SEARCH_RESULTS = "Destination Zone Search Results";
	public static String ENTRANCE_SEARCH_RESULTS = "Entrance Search Results";
	public static String PERMIT_ZONE_SEARCH_RESULTS = "Permit Zone Search Results";
	public static String RIVER_SEARCH_RESULTS = "River Search Results";
	public static String TRAIL_SEARCH_RESULTS = "Trail Search Results";

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "entranceSearch", ".text", 
				new RegularExpression("(River|Trail|Entrance|(Permit|Destination) Zone) List", false));
	}

	/**Check Next link is available*/
	public boolean checkNext(){
		Property[] p = new Property[3];
		p[0] = new Property(".id", "resultNext");
		p[1] = new Property(".text", new RegularExpression("^Next.*",false));
		p[2] = new Property(".className", "disabled");
		return !browser.checkHtmlObjectExists(p);
	}

	/**Click Next button*/
	public void clickNext(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Next");
	}

	/**
	 * Get all entrance names
	 * @return
	 */
	public List<String> getAllEntrances(){
		List<String> trail = new ArrayList<String>();
		int count = -1;
		do{
			if(this.checkNext() && count!=-1){
				this.clickNext();
				this.waitLoading();
			}
			IHtmlObject[] objs = browser.getTableTestObject(".id", "shoppingitems", ".text",
					new RegularExpression("^(River|Trail|Entrance|Permit Zone|Destination Zone) Search.*Online availability (River|Trail|Entrance|Permit Zone|Destination Zone).*", false));
			IHtmlTable table = (IHtmlTable)objs[0];
			for(int i=3; i<table.rowCount(); i++){
				trail.add(table.getCellValue(i, 1).replaceAll("Map", "").trim());
			}
			count++;
		}while(this.checkNext());

		return trail;
	}

	public boolean isSeeDetailsLinkExist() {
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.A", ".classname", "book now", ".text", "See Details"));
	}

	public void verifySeeDetailsLinkExist(boolean exp) {
		String msg = exp ? " " : " not ";
		if (exp != this.isSeeDetailsLinkExist()) {
			throw new ErrorOnPageException("See Details link should" + msg + "exist on the entrance list page!");
		}
		logger.info("See Details link does" + msg + "exist on the entrance list page!");
	}

	public boolean isFindNextAvailLinkExist() {
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.A", ".classname", "book next", ".text", "Find Next Avail. Date**"));
	}

	public void verifyFindNextAvailLinkExist(boolean exp) {
		String msg = exp ? " " : " not ";
		if (exp != this.isFindNextAvailLinkExist()) {
			throw new ErrorOnPageException("Find Next Avail link should" + msg + "exist on the entrance list page!");
		}
		logger.info("Find Next Avail does" + msg + "exist on the entrance list page!");
	}

	private IHtmlObject[] getSearchResultsTables() {
		IHtmlObject[] objs = browser.getTableTestObject(".id", "shoppingitems", ".text", 
				new RegularExpression("(River|Trail|Entrance|(Permit|Destination) Zone) Search Results.*Online availability.*", false));
		if (objs.length < 1) {
			throw new ErrorOnPageException("No search results table displayed on the page!");
		}
		return objs;
	}

	private String getFirstSearchResultColumnValue(int index) {
		IHtmlObject[] objs = this.getSearchResultsTables();
		IHtmlTable resultTable = (IHtmlTable)objs[0];
		String value = resultTable.getCellValue(3, index); // The first result row's index = 3
		Browser.unregister(objs);
		return value;
	}

	/** Get the online availability status for the first result on entrance list page */
	public String getFirstResultOnlineAvailabilityStatus() {
		return this.getFirstSearchResultColumnValue(0); // the first column is Online Availability
	}

	/** Verify the online availability status for the first result on entrance list page */
	public void verifyFirstResultOnlineAvailStatus(String exp) {
		String actual = this.getFirstResultOnlineAvailabilityStatus();
		if (!actual.startsWith(exp)) {
			throw new ErrorOnPageException("The online availability status for the first result is wrong!", exp, actual);
		}
		logger.info("Verify the online availability status for the first result correct as " + exp);
	}

	public void clickEntranctNameLink(String entrance) {
		browser.clickGuiObject(".class", "Html.A", ".text", entrance);
	}

	public void clickEntranceName(String parkID, String entranceID){
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression(".*parkId="+parkID+"&entranceId="+entranceID+".*", false));
	}
	
	/**
	 * Verify search result type, such as "Destination Zone Search Results"
	 * @param type
	 */
	public void verifySearchResultType(String type){
		IHtmlObject[] objs = this.getSearchResultsTables();
		String tableContent = objs[0].text().trim();
		if(!tableContent.startsWith(type)){
			throw new ErrorOnDataException("Search result type is wrong!", type, tableContent);
		}
		logger.info("Successfully verify search result type:"+type);
	}

	public void verifyEnterDateBtnExisting() {
		logger.info("Verify 'Enter Date' button existing....");
		if(!checkEnterDateBtnExisting()){
			throw new ErrorOnPageException("Can't find 'Enter Date' button (link).");
		}
	}

	private boolean checkEnterDateBtnExisting() {
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class","Html.A",".className", "book now", ".text", "Enter Date"));	
	}
	
	public boolean checkProductPhotoExist(String tourName, String description){
		Property[] p1 = Property.toPropertyArray(".class", "Html.TR", ".text", new RegularExpression(".*"+MiscFunctions.regxBracket(tourName)+".*", false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.IMG", ".title", description);
		Property[] p3 = Property.toPropertyArray(".class", "Html.IMG", ".alt", description);
		return browser.checkHtmlObjectExists(Property.atList(p1, p2)) && browser.checkHtmlObjectExists(Property.atList(p1, p3));
	}
}
