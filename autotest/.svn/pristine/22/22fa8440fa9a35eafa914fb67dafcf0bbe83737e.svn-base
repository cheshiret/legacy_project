/*
 * Created on Nov 3, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common.financial;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.FinSession;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OrmsFinSessionSearchPage extends OrmsFinancialsCommonPage {

  	/**
	 * A handle to the unique Singleton instance.
	 */
	private static OrmsFinSessionSearchPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	private OrmsFinSessionSearchPage() {

	}

	/**
	 * @return The unique instance of this class.
	 */
	public static OrmsFinSessionSearchPage getInstance() {
		if (null == _instance)
			_instance = new OrmsFinSessionSearchPage();

		return _instance;
	}

	/** 
	 * Determine if the page object exists 
	 */
	public boolean exists() {
//		return browser.checkHtmlObjectExists(".text", "Go", ".href", new RegularExpression("javascript:invokeAction(.*\"SearchFinSession.do\",.*\"GetFinSessions\",.*\"FinSessionWorker\",.*\"\".*)", false));
		return browser.checkHtmlObjectExists(".text", "Financial Sessions", ".href",
				new RegularExpression("javascript:invokeAction(.*\"SearchFinSession.do\",.*\"GetFinSessions\",.*\"FinSessionWorker\",.*\"OPEN\".*)", false));
	}
	
	public void clickFinSession(String sessionId){
		browser.clickGuiObject(".class","Html.A",".text",sessionId);
	}

	/**
	 * Select search Type
	 * @param searchType
	 */
	public void selectSearchType(String searchType) {
		browser.selectDropdownList(".id", "finsesssearchtype", searchType);
	}

	/**
	 * Select Status Value
	 * @param status
	 */
	public void selectStatus(String status) {
		browser.selectDropdownList(".id", "finsesssstatus", status);
	}

	/**
	 * Select search Date Type
	 * @param dateType
	 */
	public void selectSearchDateType(String dateType) {
		browser.selectDropdownList(".id", "searchdatetype", dateType);
	}

	/**
	 * Select Adjusted Type from dropDownList
	 * @param adjusted
	 */
	public void selectAdjusted(String adjusted) {
		browser.selectDropdownList(".id", "searchadjmtty", adjusted);
	}

	/**
	 * Input Search Type Value
	 * @param value
	 */
	public void setSearchTypeValue(String value) {
		browser.setTextField(".id", "finsesssearchtypevalue", value);
	}

	/**
	 * Input start Date
	 * @param startDate
	 */
	public void setStartDate(String startDate) {
		browser.setTextField(".id", "searchstartdate_ForDisplay", startDate);
	}

	/**
	 * Input End Date
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
		browser.setTextField(".id", "searchenddate_ForDisplay", endDate);
	}

	/**
	 * Click Go Button
	 *
	 */
	public void clickGo() {
		IHtmlObject[] objs = getTransactionFrame();
		if(objs.length>0){
			browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^Search$", false),false,0,objs[0]);
		}else{
			browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^Search$", false));
		}
	}

	/**
	 * Search Financial Session with given criteria
	 * @param f Financial Session Object
	 */
	public void searchFinSession(FinSession f) {
		if (null != f.status && !f.status.equals("")) {
			selectStatus(f.status);
		}
		if (null != f.searchType && !f.searchType.equals("")
				&& null != f.searchValue && !"".equals(f.searchValue)) {
			selectSearchType(f.searchType);
			setSearchTypeValue(f.searchValue);
		}
		if (null != f.dateRange && !"".equals(f.dateRange)) {
			selectSearchDateType(f.dateRange);
			if (null != f.startDate && !"".equals(f.startDate)) {
				setStartDate(f.startDate);
			}
			if (null != f.endDate && !"".equals(f.endDate)) {
				setEndDate(f.endDate);
			}
		}
		if (null != f.adjusted && !"".equals(f.adjusted)) {
			selectAdjusted(f.adjusted);
		}

		clickGo();
		waitLoading();
	}

	public void searchFinSessionById(String id){
		this.searchFinSession("Fin Session ID", id);
	}
	
	public void searchFinSessionByStatus(String status) {
		this.selectStatus(status);
		this.clickGo();
		this.waitLoading();
	}
	
	public void searchFinSessionByLocation(String location) {
		this.searchFinSession("Location", location);
	}
	
	public void searchFinSession(String searchBy, String searchByValue) {
		this.selectSearchType(searchBy);
		this.setSearchTypeValue(searchByValue);
		this.clickGo();
		this.waitLoading();
	}
	
	public void searchOpenFinSession() {
		searchFinSessionByStatus("Open");
	}
	
	/**
	 * Search And Verify FinSession
	 * @param fs
	 */
	public String searchAndVerifyFinSession(FinSession fs) {
		searchFinSession(fs);
		waitLoading();
		String errorIds = "";
		if (fs.searchType != null && fs.searchType.equalsIgnoreCase("Location")) {
			verifyFinSession(fs.searchType, fs.searchValue);
		} else if (fs.searchType != null
				&& fs.searchType.equalsIgnoreCase("Open User (Last Name)")) {
			errorIds = verifyFinSession("Open User/Station", fs.openUser);
		}
		if (fs.status != null && !fs.status.equals("")) {
			verifyFinSession("Status", fs.status);
		}
		if (fs.adjusted != null && !fs.adjusted.equals("")) {
			if (fs.adjusted.equalsIgnoreCase("Not Adjusted")) {
				verifyFinSession("Adjusted", "No");
			} else {
				verifyFinSession("Adjusted", "Yes");
			}
		}
		return errorIds;
	}

	/**
	 * Verify Fin Session list are all match search condition
	 * @param colNum
	 * @param value
	 */
	public String verifyFinSession(String colName, String value) {
		RegularExpression rex = new RegularExpression(
				"Fin Sess ID Deposit ID Status.*", false);
		int colNum = getColNum(colName);
		IHtmlObject[] objs = null;
		StringBuffer errorIds = new StringBuffer();
//		do {
			objs = browser.getHtmlObject(".class", "Html.TABLE", ".text",
					rex);
			IHtmlTable tableGrid=(IHtmlTable)objs[0];
			for (int i = 1; i < tableGrid.rowCount(); i++) {
				if (null != tableGrid.getCellValue(i, colNum)) {
					if (!tableGrid.getCellValue(i, colNum).toString().trim().equals(
							value)) {
						if(colName.equals("Open User/Station")){
							errorIds.append(tableGrid.getCellValue(i, 1)).append(",");
							continue;
						}
						Browser.unregister(objs);
						logger.error("Search Fin Session by " + value
								+ " Fail! ");
						throw new ItemNotFoundException(
						"Search Fin Session Fail!");
					}
				}
			}
//		} while (gotoNext());

		Browser.unregister(objs);
		return errorIds.toString();
	}

	/**
	 * Check Next button exists or not,if exists,click it
	 * @return Next button exists or not
	 */
	public boolean gotoNext() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",
				".text", "Next");

		boolean toReturn = false;
		if (objs.length > 0) {
			toReturn = true;
			((ILink)objs[0]).click();
		}
		Browser.unregister(objs);

		this.waitLoading();

		return toReturn;
	}

	/**
	 * Get column Num for specific Column Name
	 * @param colName
	 * @return column Number
	 */
	public int getColNum(String colName) {
		RegularExpression rex = new RegularExpression(
				"Fin Sess ID Deposit ID Status.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", rex);
		if (null != objs && objs.length > 0) {
			IHtmlTable tableGrid=(IHtmlTable)objs[0];
			int colCounts = tableGrid.columnCount();
			for (int i = 0; i < colCounts; i++) {
				if (tableGrid.getCellValue(0, i).toString()
						.equalsIgnoreCase(colName)) {
					Browser.unregister(objs);
					return i;
				}
			}
		}
		Browser.unregister(objs);
		return -1;
	}
	
	public void clickPaymentTab() {
		browser.clickGuiObject(".class","Html.A",".text","Payments");
	}
	
	/**
	 * Get All Open Fin Session ID
	 * @return
	 */
	public List<String> getAllOpenFinSessID() {
		List<String> finSessInfo = new ArrayList<String>();
//		RegularExpression rex = new RegularExpression("Fin Sess ID Deposit ID Status.*", false);
		IHtmlObject[] objs;
		do {
//			objs = browser.getTableTestObject(".text", rex);
			objs = browser.getTableTestObject(".className", "searchResult");//Quentin[20131029] UI changes
			IHtmlTable tableGrid=(IHtmlTable)objs[0];
			for (int i = 1; i < tableGrid.rowCount(); i++) {
				finSessInfo.add(tableGrid.getCellValue(i, 1));
			}
		} while (gotoNext());
		Browser.unregister(objs);
		return finSessInfo;
	}
	
	public void selectAllCheckBox(){
		browser.selectCheckBox(".name", "all_slct", true);
	}
	
	public void unSelectAllCheckBox(){
		browser.unSelectCheckBox(".class","Html.INPUT.checkbox",".value","all");
	}
	
	/**
	 * Click CloseWithOutAdjustment Button
	 *
	 */
	public void clickCloseWithoutAdjus() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Close Without Adjustment");
	}
	
//	/**
//	 * Search Fin Session by fin session Id
//	 * @param finID Financial Session Id
//	 */
//	public void searchFinSessionByID(String finID) {
//		selectSearchType("Fin Session ID");
//		setSearchTypeValue(finID);
//		clickGo();
//	}
	
	/**
	 * Select Specific finSession from CheckBox
	 * @param finID FinSession ID
	 */
	public void clickOneCheckBox(String finID) {
		searchFinSessionById(finID);
		waitLoading();
		browser.selectCheckBox(".id", "closesessions", ".name", "closesessions");
	}
	
//	/**
//	 * Click FinancialSession Tab
//	 *
//	 */
//	public void clickFinancalSession() {
//		browser.clickGuiObject(".class", "Html.A", ".text", "Financial Sessions");
//	}
	
	/**
	 * Get a Fin Session Info by location and userName
	 * @param location
	 * @param userName
	 * @return
	 */
	public List<String> getFinSessInfoForSpecificUser(String location, String userName) {
		List<String> finSessInfo = new ArrayList<String>();
		RegularExpression rex = new RegularExpression("Fin Sess ID Deposit ID Status.*", false);
		IHtmlObject[] objs;
		this.searchFinSessionByLocation(location);
		do {
			objs = browser.getTableTestObject(".text", rex);

			IHtmlTable tableGrid=(IHtmlTable)objs[0];
			int locCol = tableGrid.findColumn(0, "Location");
			int userStationCol = tableGrid.findColumn(0, "Open User/Station");
			
			for (int i = 0; i < tableGrid.rowCount(); i++) {
				String loc = tableGrid.getCellValue(i, locCol);
				String user = tableGrid.getCellValue(i, userStationCol);
				if (null != loc && null != user) {
					if (loc.equalsIgnoreCase(location) && user.equalsIgnoreCase(userName)) {
						finSessInfo = tableGrid.getRowValues(i);
						break;
					}
				}
			}
			if (null != finSessInfo && finSessInfo.size() > 0)
				break;
		} while (gotoNext());

		Browser.unregister(objs);
		return finSessInfo;
	}
	
	/**
	 * Verify the fin session information is correct after you close it.
	 * @param finSessInfo
	 */
	public void verifyFinSess(List<String> finSessInfo, boolean doAdjust) {
		clickFinancalSessionsTab();
		waitLoading();
		String finID = finSessInfo.get(1).toString();
		logger.info("Start to verify fin session " + finID);
		searchFinSessionById(finID);
		waitLoading();
		List<String> closedFinSessInfo = getFinSessInfoByID(finID,true);
		if (null != closedFinSessInfo) {
			if (!closedFinSessInfo.get(2).toString().equals("Closed")) {
				logger.error("Closed Fin Session " + finID + " Fail!");
				throw new ItemNotFoundException("Closed Fin Session " + finID
						+ " Fail!");
			}
			if (doAdjust) {
				if (!closedFinSessInfo.get(3).toString().equals("Yes")) {
					logger.error("Closed Fin Session " + finID + " Fail!");
					throw new ItemNotFoundException("Closed Fin Session "
							+ finID + " Fail!");
				}
				String expectAmt = closedFinSessInfo.get(11);
				if(expectAmt.contains("(")){
					expectAmt = expectAmt.replaceAll("\\(", "").replaceAll("\\)", "");
					expectAmt = "-"+expectAmt;
				}
				int adjustUI = Integer.parseInt(expectAmt.replaceFirst("\\$", "").replaceFirst(
								"\\.00", ""));
				int actualAdjust = Integer.parseInt(closedFinSessInfo.get(12)
						.toString().replaceFirst("\\$", "").replaceFirst(
								"\\.00", ""))
						- Integer.parseInt(closedFinSessInfo.get(10).toString()
								.replaceFirst("\\$", "").replaceFirst("\\.00",
										""));
				if (adjustUI != actualAdjust) {
					logger.error("Closed Fin Session " + finID + " Fail!");
					throw new ItemNotFoundException("Closed Fin Session "
							+ finID + " Fail!");
				}
			} else {
				for (int i = 5; i < closedFinSessInfo.size(); i++) {
					if (!finSessInfo.get(i+1).toString().equals(
							closedFinSessInfo.get(i).toString())) {
						if (i != getColNum("Close Date")) {
							logger.error("Closed Fin Session " + finID
									+ " Fail!");
							throw new ItemNotFoundException(
									"Closed Fin Session " + finID + " Fail!");
						}
					}
				}
			}
		} else {
			logger.error("Closed Fin Session " + finID + " Fail!");
			throw new ItemNotFoundException("Closed Fin Session " + finID
					+ " Fail!");
		}

		logger.info("Close FinSession " + finID + " Successful!");
	}
	
	/**
	 * Get a fin session info by fin session ID.
	 * @param finSessID
	 * @return Vector Fin Session Info
	 */
	public List<String> getFinSessInfoByID(String finSessID, boolean isClosed) {
		List<String> finSessInfo = new ArrayList<String>();
		RegularExpression rex = new RegularExpression("Fin Sess ID Deposit ID Status.*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", rex);

		IHtmlTable tableGrid=(IHtmlTable)objs[0];
		int index = 1;
		if(isClosed){
		  index=0;
		}
		do {
			for (int i = 0; i < tableGrid.rowCount(); i++) {
				if (null != tableGrid.getCellValue(i, index)) {
					if (tableGrid.getCellValue(i, index).toString().equalsIgnoreCase(
							finSessID)) {
						for (int j = index; j < tableGrid.columnCount(); j++) {
							finSessInfo.add(tableGrid.getCellValue(i, j).toString());
						}
						break;
					}
				}
			}
			if (null != finSessInfo && finSessInfo.size() > 0)
				break;
		} while (gotoNext());

		Browser.unregister(objs);
		return finSessInfo;
	}
	
	/**
	 * Turn a Vector object to FinSession
	 * @param finSess
	 * @return FinSession
	 */
	public FinSession turnVectorToFinSession(List<String> finSess) {
		FinSession fs = new FinSession();
		if (null != finSess && finSess.size() > 0) {
			fs.finSessID = finSess.get(getColNum("Fin Sess ID") - 1).toString();
			fs.status = finSess.get(getColNum("Status") - 1).toString();
			fs.adjusted = finSess.get(getColNum("Adjusted") - 1).toString();
			fs.openDate = finSess.get(getColNum("Open Date") - 1).toString();
			fs.location = finSess.get(getColNum("Location") - 1).toString();
			fs.openUser = finSess.get(getColNum("Open User/Station") - 1)
					.toString();
			fs.transactions = Integer.parseInt(finSess.get(getColNum("# Transactions") - 1).toString());
			fs.transactionTotal = Double.parseDouble(finSess.get(getColNum("Transaction Total") - 1).toString().replace("$", StringUtil.EMPTY));
			fs.adjustment = Double.parseDouble(finSess.get(getColNum("Adjustment") - 1).toString().replace("$", StringUtil.EMPTY));
			fs.netTotal = Double.parseDouble(finSess.get(getColNum("Net Total") - 1).toString().replace("$", StringUtil.EMPTY));
			fs.totalIncludeNonDepositables = Double.parseDouble(finSess.get(getColNum("Total Incl. Non-Depositables") - 1).toString().replaceAll("(\\$)|,|\\(|\\)", StringUtil.EMPTY));
		}

		return fs;
	}
	
	public String getOpenFinSessionID(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".href", new RegularExpression("javascript:invokeAction\\( \"FinSessionSumm\\.do\".*", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find Open fin session.");
		}
		String finSess = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		return finSess;
	}
	
	public boolean isOpenFinSessionExists(String location) {
		IHtmlObject objs[] = getFinSessionListTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> locations = table.getColumnValues(table.findColumn(0, LOCATION_COL));
		boolean isExists = locations.contains(location);
		Browser.unregister(objs);
		
		return isExists;
	}
	
	public String getFinSessionIDByLocation(String location) {
		if(!isOpenFinSessionExists(location)) {
			this.searchFinSessionByLocation(location);
		}
		IHtmlObject objs[] = getFinSessionListTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowIndex = table.findRow(table.findColumn(0, LOCATION_COL), location);
		String id = table.getCellValue(rowIndex, table.findColumn(0, FIN_SESS_ID_COL));
		
		Browser.unregister(objs);
		
		return id;
	}
	
	private IHtmlObject[] getFinSessionListTable() {
		IHtmlObject objs[] = browser.getTableTestObject(".text", new RegularExpression("Fin Sess ID Deposit ID Status.*", false));
		
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find fin session list table object.");
		}
		
		return objs;
	}
	
	public boolean isFinSessionExists(String id) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", id);
	}
	
	private final static String FIN_SESS_ID_COL = "Fin Sess ID";
	private final static String DEPOSIT_ID_COL = "Deposit ID";
	private final static String STATUS_COL = "Status";
	private final static String ADJUSTED_COL = "Adjusted";
	private final static String OPEN_DATE_COL = "Open Date";
	private final static String CLOSE_DATE_COL = "Close Date";
	private final static String DEPOSIT_DATE_COL = "Deposit Date";
	private final static String LOCATION_COL = "Location";
	private final static String OPEN_USER_STATION_COL = "Open User/Station";
	private final static String NUM_OF_TRANSACTIONS_COL = "# Transactions";
	private final static String TRANSACTION_TOTAL_COL = "Transaction Total";
	private final static String ADJUSTMENT_COL = "Adjustment";
	private final static String NET_TOTAL_COL = "Net Total";
	private final static String TOTAL_INCLUDE_NON_DEPOSITABLES_COL = "Total Incl. Non-Depositables";
	private final static String OPENING_FLOAT_COL = "Opening Float";
	private final static String CLOSING_FLOAT_COL = "Closing Float";
	private final static String FLOAT_ADJUSTMENT_COL = "Float Adjustment";
	
	private boolean isOpeningFloatColumnDisplayed() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", OPENING_FLOAT_COL);
	}
	
	private boolean isClosingFloatColumnDisplayed() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", CLOSING_FLOAT_COL);
	}
	
	private boolean isFloatAdjustmentColumnDisplayed() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", FLOAT_ADJUSTMENT_COL);
	}
	
	public FinSession getFinSessionInfo(String id) {
		if(!isFinSessionExists(id)) {
			searchFinSessionById(id);
		}
		
		IHtmlObject objs[] = getFinSessionListTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int idColIndex = table.findColumn(0, FIN_SESS_ID_COL);
		List<String> values = table.getRowValues(table.findRow(idColIndex, id));
		FinSession fs = new FinSession();
		fs.finSessID = values.get(idColIndex);
		fs.depositID = values.get(table.findColumn(0, DEPOSIT_ID_COL));
		fs.status = values.get(table.findColumn(0, STATUS_COL));
		fs.adjusted = values.get(table.findColumn(0, ADJUSTED_COL));
		fs.openDate = values.get(table.findColumn(0, OPEN_DATE_COL));
		fs.closeDate = values.get(table.findColumn(0, CLOSE_DATE_COL));
		fs.depositDate = values.get(table.findColumn(0, DEPOSIT_DATE_COL));
		fs.location = values.get(table.findColumn(0, LOCATION_COL));
		//TODO judge if open user or open station
		fs.openUser = values.get(table.findColumn(0, OPEN_USER_STATION_COL));
		fs.transactions = Integer.parseInt(values.get(table.findColumn(0, NUM_OF_TRANSACTIONS_COL)));
		fs.transactionTotal = Double.NaN;
		String tempStr = values.get(table.findColumn(0, TRANSACTION_TOTAL_COL));
		if(!StringUtil.isEmpty(tempStr)) {
			fs.transactionTotal = Double.parseDouble(tempStr.replace("$", StringUtil.EMPTY).replaceAll(",", StringUtil.EMPTY));
		}
		
		fs.adjustment = Double.NaN;
		tempStr = values.get(table.findColumn(0, ADJUSTMENT_COL));
		if(!StringUtil.isEmpty(tempStr)) {
			fs.adjustment = Double.parseDouble(tempStr.replace("$", StringUtil.EMPTY).replaceAll(",", StringUtil.EMPTY));
		}
		
		fs.netTotal = Double.NaN;
		tempStr = values.get(table.findColumn(0, NET_TOTAL_COL));
		if(!StringUtil.isEmpty(tempStr)) {
			fs.netTotal = Double.parseDouble(tempStr.replace("$", StringUtil.EMPTY).replaceAll(",", StringUtil.EMPTY));
		}
		
		fs.totalIncludeNonDepositables = Double.NaN;
		tempStr = values.get(table.findColumn(0, TOTAL_INCLUDE_NON_DEPOSITABLES_COL));
		if(!StringUtil.isEmpty(tempStr)) {
			fs.totalIncludeNonDepositables = Double.parseDouble(tempStr.replace("$", StringUtil.EMPTY).replaceAll(",", StringUtil.EMPTY));
		}
		
		if(isOpeningFloatColumnDisplayed()) {
			fs.openingFloat = values.get(table.findColumn(0, OPENING_FLOAT_COL)).replace("$", StringUtil.EMPTY).replaceAll(",", StringUtil.EMPTY);
		}
		if(isClosingFloatColumnDisplayed()) {
			fs.closingFloat = StringUtil.isEmpty(values.get(table.findColumn(0, CLOSING_FLOAT_COL))) ? 0 : Double.parseDouble(values.get(table.findColumn(0, CLOSING_FLOAT_COL)));
		}
		if(isFloatAdjustmentColumnDisplayed()) {
			fs.floatAdjustment = StringUtil.isEmpty(values.get(table.findColumn(0, FLOAT_ADJUSTMENT_COL))) ? 0 : Double.parseDouble(values.get(table.findColumn(0, FLOAT_ADJUSTMENT_COL)));
		}
		
		Browser.unregister(objs);
		return fs;
	}
	
	public boolean compareFinSessionInfo(FinSession expected) {
		FinSession actual = getFinSessionInfo(expected.finSessID);
		
		boolean result = true;
		result &= MiscFunctions.compareResult("Fin Session ID", expected.finSessID, actual.finSessID);
		result &= MiscFunctions.compareResult("Deposit ID", expected.depositID, actual.depositID);
		result &= MiscFunctions.compareResult("Status", expected.status, actual.status);
		result &= MiscFunctions.compareResult("Adjusted", expected.adjusted, actual.adjusted);
		result &= MiscFunctions.compareResult("Open Date", expected.openDate, actual.openDate);
		result &= MiscFunctions.compareResult("Close Date", expected.closeDate, actual.closeDate);
		result &= MiscFunctions.compareResult("Deposit Date", expected.depositDate, actual.depositDate);
		result &= MiscFunctions.compareResult("Location", expected.location, actual.location);
		result &= MiscFunctions.compareResult("Open User/Station", expected.openUser, actual.openUser);//TODO handle both 2 situations
		result &= MiscFunctions.compareResult("# Transactions", expected.transactions, actual.transactions);
		result &= MiscFunctions.compareResult("Transaction Total", expected.transactionTotal, actual.transactionTotal);
		result &= MiscFunctions.compareResult("Adjustment", expected.adjustment, actual.adjustment);
		result &= MiscFunctions.compareResult("Net Total", expected.netTotal, actual.netTotal);
		result &= MiscFunctions.compareResult("Total Incl. Non-Depositables", expected.totalIncludeNonDepositables, actual.totalIncludeNonDepositables);
		if(!StringUtil.isEmpty(expected.openingFloat)) {
			result &= MiscFunctions.compareResult("Opening Float", Double.parseDouble(expected.openingFloat), Double.parseDouble(actual.openingFloat));
			result &= MiscFunctions.compareResult("Closing Float", expected.closingFloat, actual.closingFloat);
			result &= MiscFunctions.compareResult("Float Adjustment", expected.floatAdjustment, actual.floatAdjustment);
		}
		
		return result;
	}
	
	public void verifyFinSessionInfo(FinSession expected) {
		if(!compareFinSessionInfo(expected)) {
			throw new ErrorOnPageException("Fin Session list info is incorrect, please refer to log for details.");
		} else logger.info("Fin Session list info is totally correct.");
	}
	
	public String getFinSessionOpeningFloat(String id) {
		return getFinSessionInfo(id).openingFloat;
	}
	
	public boolean isCloseWithoutAdjustmentDisplayed() {
		return browser.checkHtmlObjectDisplayed(".class", "Html.A", ".text", "Close Without Adjustment");
	}
	
	public boolean isTransactionTotalValueDisplayed(String id) {
		if(!StringUtil.isEmpty(String.valueOf(getFinSessionInfo(id).transactionTotal)) && !Double.isNaN(getFinSessionInfo(id).transactionTotal)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isAdjustmentValueDisplayed(String id) {
		if(!StringUtil.isEmpty(String.valueOf(getFinSessionInfo(id).adjustment)) && !Double.isNaN(getFinSessionInfo(id).adjustment)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isNetTotalValueDisplayed(String id) {
		if(!StringUtil.isEmpty(String.valueOf(getFinSessionInfo(id).netTotal)) && !Double.isNaN(getFinSessionInfo(id).netTotal)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isTotalIncludeNonDepositablesValueDisplayed(String id) {
		if(!StringUtil.isEmpty(String.valueOf(getFinSessionInfo(id).totalIncludeNonDepositables)) && !Double.isNaN(getFinSessionInfo(id).totalIncludeNonDepositables)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isOpeningFloatValuDisplayed(String id) {
		return !StringUtil.isEmpty(getFinSessionInfo(id).openingFloat);
	}
	
	public boolean isOpenFinSessionExists() {
		this.searchOpenFinSession();
		return browser.checkHtmlObjectExists(".class", "Html.A", ".href", new RegularExpression("javascript:invokeAction\\( \"FinSessionSumm\\.do\".*", false));
	}
	
	public void verifyOpeningFloatAmount(String expected) {
		String finSessionID = this.getOpenFinSessionID();
		String actualOpeningFloat = this.getFinSessionOpeningFloat(finSessionID);
		
		if(!MiscFunctions.compareResult("Opening Float Amount", Double.parseDouble(expected), Double.parseDouble(actualOpeningFloat))) {
			throw new ErrorOnPageException("Opening Float Amount is not correct.");
		}
	}
	
	public boolean isFinSessionCheckboxExists(String id) {
		return browser.checkHtmlObjectExists(".id", "closesessions", ".value", id);
	}
	
	public void selectFinSessionCheckbox(String id) {
		browser.selectCheckBox(".id", "closesessions", ".value", id, true);
	}
}
