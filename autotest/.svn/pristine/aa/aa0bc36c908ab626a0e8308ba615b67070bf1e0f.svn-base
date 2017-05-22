package com.activenetwork.qa.awo.pages.web.bw;

import java.util.Date;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.TimedOutException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.Timer;

/**
 * @author QA This is permit entrance details page.
 */
public class BwBookPermitPage extends UwpPage {

	private static BwBookPermitPage _instance = null;

	public static BwBookPermitPage getInstance() {
		if (null == _instance)
			_instance = new BwBookPermitPage();

		return _instance;
	}
	
	/** Page Object Property Definition Begin */
	Property[] exitAvailabilityDivProp = Property.toPropertyArray(".class", "Html.DIV", ".className", "compactoptions", ".text", new RegularExpression( ".*Exit Availability.*", false));
	Property[] entryAvailabilityDivProp = Property.toPropertyArray(".class", "Html.DIV", ".className", "compactoptions", ".text", new RegularExpression(".*Entry Availability.*", false));
	Property[] updateAvailabilityLinkProp = Property.toPropertyArray(".class", "Html.A", ".text", "Update Availability");
	Property[] findPermitsButtonProp = Property.toPropertyArray(".class", "Html.BUTTON", ".text", "Find Permits", ".id", new RegularExpression("findPermit(_\\d)?", false));
	RegularExpression statusARegx = new RegularExpression("^A ?\\d*$", false);
	RegularExpression  statusLRegx = new RegularExpression("^L ?\\d*$", false);

	protected Property[] getPermitGridContainerDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", new RegularExpression("permitGridContainer(_1)?", false));
	}
	
	protected Property[] getSecPermitGridContainerDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "permitGridContainer_2");
	}
	
	protected Property[] getPermitGridTDProp(String status) {
		return Property.toPropertyArray(".class", "Html.TD", ".className", new RegularExpression("status " + status  +".*", false));
	}
	/** Page Object Property Definition END */
	
	public boolean exists() {
		//No Exit entrance info
		boolean entry = browser.checkHtmlObjectExists(".id", "calendar");
		//Have Entry and Exit entry info
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".id", "contentArea");
		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV", ".id", "facilitynamearea", ".text", new RegularExpression("^Entry & Exit Selection.*", false));
		boolean entryAndExit = browser.checkHtmlObjectExists(Property.atList(p1, p2));
		
		return entry || entryAndExit;
	}

	public void clickEntryUpdateAvailability() {
		browser.clickGuiObject(Property.atList(entryAvailabilityDivProp, updateAvailabilityLinkProp), false, 0);
	}
	
	public void clickExitUpdateAvailability() {
		browser.clickGuiObject(Property.atList(exitAvailabilityDivProp, updateAvailabilityLinkProp), false, 0);
	}
	
	public void waitForBookNowButtonExisting(){
		RegularExpression idPattern = new RegularExpression(
				"permitBookButton|permitAvailabilitySearchButton", false);
		browser.searchObjectWaitExists(".class", "Html.BUTTON", ".id", idPattern);
		
	}

	/**
	 * Click on Book Now button to book permit.
	 */
	public void clickBookNow() {
		RegularExpression idPattern = new RegularExpression(
				"permitBookButton|permitAvailabilitySearchButton|findPermit_\\d+", false);
		browser.clickGuiObject(".class", "Html.BUTTON", ".id", idPattern, true);
	}

	/**
	 * Fill in entry date.
	 * @param date - entry date
	 */
	public void setEntryDate(String date) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",
				".text", new RegularExpression("^Entry Date.*", false));
		if (objs == null || objs.length < 1) {
			throw new ObjectNotFoundException("can't find entry date DIV.");
		}

		browser.setTextField(".className", new RegularExpression("date(_\\d+|)", false), date, objs[objs.length - 1]);//"date"
		Browser.unregister(objs);
	}

	/**
	 * Retrieve the selected permit inventory type "Permit" or "Lottery"
	 * 
	 * @return
	 */
	public String getSelectedInventoryType() {
		RegularExpression pattern = new RegularExpression("status . slct",
				false);
		String text = browser.getObjectText(".class", "Html.TD", ".className",
				pattern);

		if (text.contains("A"))
			return "Permit";
		else if (text.contains("L"))
			return "Lottery";
		else if (text.contains("X"))
			return "None";
		else
			return "Unknown";
	}

	public boolean isLotterySelected() {
		return getSelectedInventoryType().equalsIgnoreCase("Lottery");
	}

	public boolean isPermitSelected() {
		return getSelectedInventoryType().equalsIgnoreCase("Permit");
	}

	public boolean inventoryNotAvailable() {
		return getSelectedInventoryType().equalsIgnoreCase("None");
	}

	/**
	 * Verify inventory availability.
	 * 
	 * @return
	 */
	public boolean inventroyAvailable() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"A");
		boolean found = objs.length > 0;
		Browser.unregister(objs);
		return found;
	}

	/**
	 * Select a permit inventory by given index.
	 * @param index - index of inventory, start from 1
	 */
	public void selectInventory(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text", statusARegx, index - 1);
	}

	/**
	 * Select on lottery inventory by given index.
	 * @param index  - index of lottery inv, start from 1
	 */
	public void selectLotteryInventory(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text", statusLRegx, true, index - 1);
	}

	/**
	 * Retrieve the entry date.
	 * @return - entry date
	 */
	public String getEntryDate() {
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".text",
				new RegularExpression("^Entry Date.*", false));

		IHtmlObject[] objs = browser.getHtmlObject(p1);
		if (objs == null || objs.length < 1) {// the first date text feild is
												// hidden
			throw new ObjectNotFoundException("Can't find 'Entry Date'");
		}
		String date = browser.getTextFieldValue(Property.toPropertyArray(".id",
				new RegularExpression("date(_\\d)?", false)),
				objs[objs.length - 1]);
		Browser.unregister(objs);
		return date;
	}

	/**
	 * Retrieve the error or warning message displayed on site (or Entrance)
	 * details page
	 * 
	 * @return
	 */
	public String getErrorMsg() {
		IHtmlObject[] contentArea = browser.getHtmlObject(".class", "Html.DIV",
				".className", new RegularExpression("(topofpage )?msg (topofpage )?error", false));
		if (contentArea == null || contentArea.length < 1) {
			throw new ObjectNotFoundException("Can't find error message DIV.");
		}
		String text = (String) contentArea[0].getProperty(".text");
		Browser.unregister(contentArea);
		return text;
	}

	/** Click on Find Other Entrances link in entrance details page. */
	public void clickFindOtherEntrances() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Find Other Entrances");
	}

	/** Click on Find Permit link in entrance details page. */
	public void clickFindPermit() {
		browser.clickGuiObject(".type", "submit", ".text", "Find Permit");
	}

	/**
	 * Get the entrance name of the permit.
	 * 
	 * @return
	 */
	public String getEntranceName() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".id",
				"productname");
		String entranName = "";
		if (objs.length > 0) {
			entranName = objs[0].getProperty(".text").trim();
		}

		Browser.unregister(objs);
		return entranName;
	}

	/**
	 * 
	 * @param date
	 *            mm/dd/yyyy
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String getEntryLocInventoryByDate(String date) {
		String inv = null;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id",
				new RegularExpression("^permitGridContainer(_1)?$", false));
		if (objs == null || objs.length < 1) {
			throw new ObjectNotFoundException(
					"Cann't find the 'Entry Location Availability Div'");
		}
		System.out.println(objs[0].id());
		Date d = new Date(date);
		IHtmlObject[] calendars = browser.getTableTestObject(Property
				.toPropertyArray(".id", "calendar"), objs[0]);
		IHtmlTable table = (IHtmlTable) calendars[0];
		int col = table.findColumn(1, new RegularExpression(d.getDate()
				+ " ?[A-Z]", false));
		inv = table.getCellValue(2, col);
		inv = inv.replaceAll("[A-Z]", "").trim();
		Browser.unregister(calendars, objs);
		return inv;
	}

	/**
	 * 
	 * @param date
	 *            mm/dd/yyyy
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String getExitLocInventoryByDate(String date) {
		String inv;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id",
				"permitGridContainer_2");
		if (objs == null || objs.length < 1) {
			throw new ObjectNotFoundException(
					"Cann't find the 'Exit Location Availability Div'");
		}
		Date d = new Date(date);
		IHtmlObject[] calendars = browser.getTableTestObject(Property
				.toPropertyArray(".id", "calendar"), objs[0]);
		IHtmlTable table = (IHtmlTable) calendars[0];
		int col = table.findColumn(1, new RegularExpression(d.getDate()
				+ "[A-Z]", false));
		inv = table.getCellValue(2, col).trim();
		inv = inv.replaceAll("[A-Z]", "");
		Browser.unregister(calendars, objs);
		return inv;
	}

	/**
	 * Fill in entry date.
	 * 
	 * @param date
	 *            - entry date
	 */
	public void setExitDate(String date) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",
				".text", new RegularExpression("^Exit Date.*", false));
		if (objs == null || objs.length < 1) {
			throw new ObjectNotFoundException("can't find Exit date DIV.");
		}

		browser.setTextField(".className", new RegularExpression("date(_\\d+|)", false), date, objs[objs.length - 1]);//"date"
		Browser.unregister(objs);
	}

	public boolean checkExitDate() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV",
				".text", new RegularExpression("^Exit Date.*", false));
	}

	public void selectExitPoint(String exitPoint) {
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(
				".class", "Html.DIV", ".id", "entranceDiv", ".text",
				new RegularExpression("^Exit Trail.*", false)));
		if (objs == null || objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find entry trail drop-down list DIV.");
		}
		browser.selectDropdownList(Property.toPropertyArray(".id",
				new RegularExpression("entrance_\\d", false)), exitPoint,
				false, objs[0]);

		Browser.unregister(objs);
	}

	public void selectEntryPoint(String entryPoint) {
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(
				".class", "Html.DIV", ".id", "entranceDiv", ".text",
				new RegularExpression("^Entry Trail.*", false)));
		if (objs == null || objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find entry trail drop-down list DIV.");
		}
		browser.selectDropdownList(Property.toPropertyArray(".id",
				new RegularExpression("entrance_\\d", false)), entryPoint,
				false, objs[0]);

		Browser.unregister(objs);
	}

	public String getExitPoint() {
		return browser.getDropdownListValue(".id", "entrance_2");
	}

	public void clickUpdateExitPointAvailability() {
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".text",
				new RegularExpression("^Exit Availability.*", false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.A", ".text",
				"Update Availability");
		browser.clickGuiObject(Property.atList(p1, p2), false, 0);
	}

	public void waitUpdateAvailability() {
		boolean exists = true;
		Timer timer = new Timer();
		logger.info("wait for Updateing availability....");
		while (exists && timer.diffLong() < LONG_SLEEP) {
			exists = checkUpdatePop_upExists();
		}
		if (exists) {
			throw new TimedOutException(
					"Progression bar doesn't disappear within " + timer.diff()
							+ "s.");
		}
	}

	public boolean checkUpdatePop_upExists() {
		boolean isExist = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id",
				new RegularExpression("matrixprogresspopup_\\d", false));
		if (objs == null || objs.length < 1) {
			isExist = false;
		}
		for (IHtmlObject o : objs) {
			if ("display".equalsIgnoreCase(o.style("display"))) {
				isExist = true;
				break;
			}
		}
		return isExist;
	}
	
	public boolean isEntryAndExitSelectionModel() {
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".id", "contentArea");
		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV", ".id", "facilitynamearea", ".text", new RegularExpression("^Entry & Exit Selection.*", false));
		return browser.checkHtmlObjectExists(Property.atList(p1, p2));
	}

	/**
	 * Check exit point list exist
	 * 
	 * @return
	 * @author Lesley Wang
	 * @date Jun 29, 2012
	 */
	public boolean checkExitPointListExist() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id",
				"entrance_2");
	}

	/**
	 * Verify Entry Inventory Num
	 * 
	 * @param entryDate
	 * @param expNum
	 * @author Lesley Wang
	 * @date Jun 29, 2012
	 */
	public void verifyEntryInventoryNum(String entryDate, String expNum) {
		String actNum = this.getEntryLocInventoryByDate(entryDate);

		if (!actNum.equals(expNum)) {
			throw new ErrorOnPageException("Entry Inventory is wrong.", expNum,
					actNum);
		}
		logger.info("The Entry inventory is correct!");
	}

	/**
	 * @return 'Exit' or 'Entry'
	 */
	public String getFirstSectionTitleType() {
		IHtmlObject[] objs = browser.getHtmlObject(Property
				.toPropertyArray(".class", "Html.DIV", ".id", "selEEDiv1",
						".text", new RegularExpression(
								"Select your.*Location and.*Date", false)));
		if (objs == null || objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find first section Div object ");
		}
		String title = objs[0].text();
		Browser.unregister(objs);
		if (title.contains("Exit")) {
			title = "Exit";
		} else if (title.contains("Entry")) {
			title = "Entry";
		} else {
			throw new ErrorOnPageException("Unknown location type:" + title);
		}

		return title;
	}

	/**
	 * 
	 * @return 'Exit' or 'Entry'
	 */
	public String getSecondSectionTitleType() {
		IHtmlObject[] objs = browser.getHtmlObject(Property
				.toPropertyArray(".class", "Html.DIV", ".id", "selEEDiv2",
						".text", new RegularExpression(
								"Select your.*Location and.*Date", false)));
		if (objs == null || objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find first section Div object ");
		}
		String title = objs[0].text();
		Browser.unregister(objs);

		if (title.contains("Exit")) {
			title = "Exit";
		} else if (title.contains("Entry")) {
			title = "Entry";
		} else {
			throw new ErrorOnPageException("Unknown location type");
		}
		return title;
	}

	/**
	 * @param exitDate
	 */
	public void verifyExitDate(String exitDate) {
		logger.info("Verify Exit date is '" + exitDate + "'");
		String dateOnPage = this.getExitDate();
		if (DateFunctions.compareDates(dateOnPage, exitDate) != 0) {
			throw new ErrorOnPageException("Exit Date is wrong.", exitDate,
					dateOnPage);
		}
	}

	/**
	 * 
	 */
	public String getExitDate() {
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".text",
				new RegularExpression("^Exit Date.*", false));

		IHtmlObject[] objs = browser.getHtmlObject(p1);
		if (objs == null || objs.length < 1) {
			throw new ObjectNotFoundException("Can't find 'Exit Date'");
		}
		String date = browser.getTextFieldValue(Property.toPropertyArray(".id",
				new RegularExpression("date_\\d", false)), objs[0]);
		Browser.unregister(objs);
		return date;
	}

	/**
	 * @param entryTrail
	 * @param entryDate
	 */
	public void findEntryAvailability(String entryTrail, String entryDate) {
		this.selectEntryPoint(entryTrail);
		this.waitForProgressBarDisapear();
		this.setEntryDate(entryDate);
		this.waitForProgressBarDisapear();
		if (this.isFindPermitBtnExist_Entry()) {
			this.clickFindPermit_Entry();
			this.waitForProgressBarDisapear();
		}else{
			this.clickEntryUpdateAvailability();
			this.waitForProgressBarDisapear();
		}
	}

	public void clickFindPermit_Exit() {
		browser.clickGuiObject(Property.atList(exitAvailabilityDivProp, findPermitsButtonProp), false, 0);
	}
	
	public void clickFindPermit_Entry() {
		browser.clickGuiObject(Property.atList(entryAvailabilityDivProp, findPermitsButtonProp), false, 0);
	}
	
	public boolean isFindPermitBtnExist_Exit() {
		return browser.checkHtmlObjectExists(Property.atList(exitAvailabilityDivProp, findPermitsButtonProp));
	}

	public boolean isFindPermitBtnExist_Entry() {
		IHtmlObject[] topDivs = browser.getHtmlObject(entryAvailabilityDivProp);
		if (topDivs == null || topDivs.length < 1) {
			throw new ObjectNotFoundException("Can't find top div object.");
		}
		Property[] p2 = Property.toPropertyArray(".id", "findPermit_2");
		boolean flag = browser.checkHtmlObjectExists(p2, topDivs[0]);
		return flag;
	}

	/**
	 * @param Exit
	 * @param entryDate
	 */
	public void findExitAvailability(String exitTrail, String exitDate) {
		this.selectExitPoint(exitTrail);
		this.waitForProgressBarDisapear();
		this.setExitDate(exitDate);
		this.waitForProgressBarDisapear();
		if (this.isFindPermitBtnExist_Exit()) {
			this.clickFindPermit_Exit();
			this.waitForProgressBarDisapear();
		}else{
			this.clickExitUpdateAvailability();
			this.waitForProgressBarDisapear();
		}
	}

	/**
	 * @param entryDate
	 */
	public void verifyEntryDate(String entryDate) {
		logger.info("Verify Exit date is '" + entryDate + "'");
		String dateOnPage = this.getEntryDate();
		if (DateFunctions.compareDates(dateOnPage, entryDate) != 0) {
			throw new ErrorOnPageException("Entry Date is wrong.", entryDate,
					dateOnPage);
		}
	}

	/**
	 * verify second section type
	 */
	public void verifySecondSectionType(String expectedType) {
		BwBookPermitPage bwBookPage = BwBookPermitPage.getInstance();

		logger.info("verify second section type is '" + expectedType + "'");
		if (!expectedType.equals(bwBookPage.getSecondSectionTitleType())) {
			throw new ErrorOnPageException(
					"the 2nd section for location should be '" + expectedType
							+ "'");
		}
	}

	/**
	 * verify first section type
	 */
	public void verifyFirstSectionType(String expectedType) {
		BwBookPermitPage bwBookPage = BwBookPermitPage.getInstance();

		logger.info("verify first section type is '" + expectedType + "'");
		if (!expectedType.equals(bwBookPage.getFirstSectionTitleType())) {
			throw new ErrorOnPageException(
					"the 1st section for location should be '" + expectedType
							+ "'");
		}
	}

	/**
	 * @param msg
	 */
	public void verifyErrorMessage(String msg) {
		logger.info("Verify error message exists:'" + msg + "'");
		String msgOnPage = this.getErrorMsg();
		if (!msg.equals(msgOnPage)) {
			throw new ErrorOnPageException("Error message is wrong.", msg,
					msgOnPage);
		}
	}

	/**
	 * Is entrance alert message existing
	 * @param entranceCodeAndName: "entranceCode - entranceName"
	 * @return
	 */
	public boolean isExitTrailAlertMesExisting(String entranceCodeAndName){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", "content first", ".text", new RegularExpression("Exit Location: ?"+entranceCodeAndName.replace("(", "\\(").replace(")", "\\)")+".*", false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV", ".className", "alertBulletin", ".text",new RegularExpression("^Notice:.*", false));
		return browser.checkHtmlObjectExists(Property.atList(p1, p2));
	}
	public boolean isEntryTrailAlertMesExisting(String entranceCodeAndName){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", "content first", ".text", new RegularExpression("Entry Location: ?"+entranceCodeAndName.replace("(", "\\(").replace(")", "\\)")+".*", false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV", ".className", "alertBulletin", ".text",new RegularExpression("^Notice:.*", false));
		return browser.checkHtmlObjectExists(Property.atList(p1, p2));
	}
	
	/**
	 * Verify entrance no alter message
	 * @param entranceCodeAndName
	 */
	public void verifyExitEntranceNoAlertMes(String entranceCodeAndName){
		if(this.isExitTrailAlertMesExisting(entranceCodeAndName)){
			throw new ErrorOnDataException("Exit Entrance:"+entranceCodeAndName+" should not have alter message.");
		}else{
			logger.info("Successfully verify exit entrance:"+entranceCodeAndName+" does't have alter message.");
		}
	}
	public void verifyEntryEntranceNoAlertMes(String entranceCodeAndName){
		if(this.isEntryTrailAlertMesExisting(entranceCodeAndName)){
			throw new ErrorOnDataException("Entry Entrance:"+entranceCodeAndName+" should not have alter message.");
		}else{
			logger.info("Successfully verify entry entrance:"+entranceCodeAndName+" does't have alter message.");
		}
	}
	
	/**
	 * Get entrance alert message objects
	 * @param entranceCodeAndName
	 * @return
	 */
	public IHtmlObject[] getExitTrailAlertMesObjs(String entranceCodeAndName){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", "content first", ".text", new RegularExpression("Exit Location: ?"+entranceCodeAndName.replace("(", "\\(").replace(")", "\\)")+".*", false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV", ".className", "alertBulletin", ".text",new RegularExpression("^Notice:.*", false));
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		if (objs == null || objs.length < 1) {
			throw new ObjectNotFoundException("can't find Exit trail alert DIV object.");
		}
		return objs;
	}
	public IHtmlObject[] getEntryTrailAlertMesObjs(String entranceCodeAndName){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", "content first", ".text", new RegularExpression("Entry Location: ?"+entranceCodeAndName.replace("(", "\\(").replace(")", "\\)")+".*", false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV", ".className", "alertBulletin", ".text",new RegularExpression("^Notice:.*", false));
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		if (objs == null || objs.length < 1) {
			throw new ObjectNotFoundException("can't find entry trail alert DIV object.");
		}
		return objs;
	}
	/**
	 * Get entrance alter message 
	 * @param entranceNameAndCode: "entranceCode - entranceName"
	 * @return
	 */
	public String getExitTrailAlertMsg(String entranceCodeAndName) {
        IHtmlObject[] objs = this.getExitTrailAlertMesObjs(entranceCodeAndName);
		String alert = objs[0].text().replaceAll("Notice:", "").trim();
		Browser.unregister(objs);
		return alert;
	}
	public String getEntryTrailAlertMsg(String entranceCodeAndName) {
        IHtmlObject[] objs = this.getEntryTrailAlertMesObjs(entranceCodeAndName);
		String alert = objs[0].text().replaceAll("Notice:", "").trim();
		Browser.unregister(objs);
		return alert;
	}
	
	/**
	 * Verify entrance alert message
	 * @param entranceCodeAndName: "entranceCode - entranceName"
	 * @param alertDesc: entrance alert description
	 */
	public void verifyExitTrailAlertMes(String entranceCodeAndName, String alertDesc) {
		logger.info("Verify exit entry trail alert info....");
		String msgFromPage=this.getExitTrailAlertMsg(entranceCodeAndName);
		if(!msgFromPage.contains(alertDesc)){
			throw new ErrorOnPageException("Exit trail alert message is wrong.",alertDesc,msgFromPage);
		}
	}
	public void verifyEntryTrailAlertMes(String entranceCodeAndName, String alertDesc) {
		logger.info("Verify entry trail alert info....");
		String msgFromPage=this.getEntryTrailAlertMsg(entranceCodeAndName);
		if(!msgFromPage.contains(alertDesc)){
			throw new ErrorOnPageException("Entry trail alert message is wrong.",alertDesc,msgFromPage);
		}
	}
	
	public void selectPermitType(String permitType) {
		browser.selectDropdownList(".id", "permitTypeId", permitType);
	}
	
	public void setGroupSize(String size) {
		browser.setTextField(".id", "pGroupSize", size);
	}
	
	public void setLengthOfStay(String length) {
		browser.setTextField(".id", "pLengthOfStay", length);
	}
	
	public void clickPermitAvailabilityTable() {
		browser.clickGuiObject(".class", "Html.Table", ".id", "entranceAvailability");
	}
	
	public void setPermitAvailabilityInfo(PermitInfo permit) {
		logger.info("Set permit info for permit availability...");
		this.selectPermitType(permit.permitType);
		this.clickPermitAvailabilityTable();
		this.waitLoading();
		this.setEntryDate(permit.entryDate);
		this.clickPermitAvailabilityTable();
		this.waitLoading();
		this.setGroupSize(permit.groupSize);
		this.clickPermitAvailabilityTable();
		this.waitLoading();
		this.setLengthOfStay(permit.numOfDays);
		this.clickPermitAvailabilityTable();
		this.waitLoading();
	}
	
	public boolean isLengthOfStayExist() {
		return !browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.DIV", ".className", "hiddenoptions hide", ".id", "lengthOfStayDivId"));
	}
	
	public void verifyLengthOfStayExist(boolean exp) {
		String msg = exp ? " " : " NOT ";
		if (exp != this.isLengthOfStayExist()) {
			throw new ErrorOnPageException("The Length of Stay text field should" + msg + "exist on Entranct Details page.");
		}
		logger.info("The Length of Stay text field does" + msg + "exist on Entranct Details page.");
	}
	
	/** Check the selected grids status on Permit Availability table */
	public boolean checkSelectedGridsStatus(String... status) {
		Property[] propertiesParent, propertiesChild;
		boolean result = true;
		for (String s : status) {
			propertiesParent = Property.toPropertyArray(".class", "Html.TD", ".className", "status " + s.toLowerCase() + " slct");
			if (s.equalsIgnoreCase("A")) {
				propertiesChild = Property.toPropertyArray(".class", "Html.A", ".text", statusARegx);
			} else if (s.equalsIgnoreCase("L")) {
				propertiesChild = Property.toPropertyArray(".class", "Html.A", ".text", statusLRegx);
			} else {
				propertiesChild = Property.toPropertyArray(".class", "Html.Div", ".classname", "permitStatus", ".text", s.toUpperCase());
			}
			result &= browser.checkHtmlObjectExists(Property.atList(propertiesParent, propertiesChild));
			if (!result) {
				logger.error("the grid for status=" + s + " NOT exist on the page!");
			}
		}
		return result;
	}
	
	/** Verify the selected grids status on Permit Availability table */
	public void verifySelectedGridsStatus(String... status) {
		if (!this.checkSelectedGridsStatus(status)) {
			throw new ErrorOnPageException("The selected grids status are wrong! Check logger error above!");
		}
		logger.info("The selected grids status correctly on Entrance Details page!");
	}
	
	/** Check if the selected grids status equal to A on Permit Availability table */
	public boolean checkSelectedAvailGridsExist(String entryDate, String lengthOfStay) {
		entryDate = DateFunctions.formatDate(entryDate, "EEE MMM dd yyyy");
		int days = Integer.valueOf(lengthOfStay);
		boolean result = true;
		for (int i = 0; i < days; i++) {
			result &= browser.checkHtmlObjectExists(Property.atList(
					Property.toPropertyArray(".class", "Html.TD", ".className", "status a slct"), 
					Property.toPropertyArray(".class", "Html.A", ".text", statusARegx, 
							".onclick", new RegularExpression(".*" + entryDate + "\", " + String.valueOf(i) + ".*", false))));
		}
		return result;
	}
	/** Verify the selected grids status equal to A on Permit Availability table */
	public void verifySelectedAvailGridsExist(String entryDate, String lengthOfStay, boolean exp) {
		String msg = exp ? " " : " NOT ";
		if (exp != this.checkSelectedAvailGridsExist(entryDate, lengthOfStay)) {
			throw new ErrorOnPageException("The selected availibility grids should" + msg + "exist on Entrance Details page!");
		}
		logger.info("The selected availibility grids does" + msg + "exist on Entrance Details page!");
	}
	
	public boolean checkProductPhotoExist(String description){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".id", "samplpics");
		Property[] p2 = Property.toPropertyArray(".class", "Html.IMG", ".title", description);
		Property[] p3 = Property.toPropertyArray(".class", "Html.IMG", ".alt", description);
		return browser.checkHtmlObjectExists(Property.atList(p1, p2)) && browser.checkHtmlObjectExists(Property.atList(p1, p3));
	}
	
	/**
	 * Get permit inventory calendar grid TD
	 * @return
	 */
	private IHtmlObject[] getPermitAvailGridTDs(Property[] p){
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p, this.getPermitGridTDProp("a")));
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Permit inventory grid objects can't be found.");
		}
		
		return objs;
	}
	
	/**
	 * Get the string when mouse overs the first permit inventory grid
	 * @return
	 */
	private String getMouseOverPermitInventoryNumInfo(Property[] p){
		IHtmlObject[] objs = this.getPermitAvailGridTDs(p);
		String mouseOverString = objs[0].getProperty(".onmouseover");
		
		Browser.unregister(objs);
		return mouseOverString;
	}
	
	private void verifyMouseOverPermitInventoryNumInfo(String exp, Property[] p) {
		String actual = this.getMouseOverPermitInventoryNumInfo(p);
		if (!actual.matches(exp)) {
			throw new ErrorOnPageException("Permit Inventory Num Info on calendar grid is wrong!", actual, exp);
		}
		logger.info("Permit Inventory Num Info on calendar grid is correct!");
	}
	
	public void verifyMouseOverPermitInventoryNumInfo(String exp) {
		this.verifyMouseOverPermitInventoryNumInfo(exp, this.getPermitGridContainerDivProp());
	}
	
	public void verifyMouseOverPermitInventoryNumInfoOnSecCal(String exp) {
		this.verifyMouseOverPermitInventoryNumInfo(exp, this.getSecPermitGridContainerDivProp());
	}
	/**
	 * Check if availability grid quota number tool tip is shown. 
	 * @return
	 */
	private boolean isAvailGridQuotaNumTooltipExist(Property[] p) {
		String info = this.getMouseOverPermitInventoryNumInfo(p);
		return (StringUtil.notEmpty(info) && info.contains("Available Quota"));
	}
	
	/**
	 * Verify if availability grid quota number tool tip is shown or not
	 * @param exp
	 */
	private void verifyAvailGridQuotaNumTooltipExist(boolean exp, Property[] p) {
		boolean actual = this.isAvailGridQuotaNumTooltipExist(p);
		String msg = exp ? "" : "NOT";
		if (exp != actual) {
			throw new ErrorOnPageException("Availability Grid Quota Number tooltip should " + msg + " exist on Book Permit page!");
		}
		logger.info("---Succesffully verify Availability Grid Quota Number tooltip " + msg + " exist on Book Permit page!");
	}
	
	public void verifyAvailGridQuotaNumTooltipExist(boolean exp) {
		this.verifyAvailGridQuotaNumTooltipExist(exp, this.getPermitGridContainerDivProp());
	}
	
	public void verifyAvailGridQuotaNumTooltipOnSecCalExist(boolean exp) {
		this.verifyAvailGridQuotaNumTooltipExist(exp, this.getSecPermitGridContainerDivProp());
	}
}
