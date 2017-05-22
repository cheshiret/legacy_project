package com.activenetwork.qa.awo.pages.orms.common.giftcard;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.GiftCardInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class OrmsGiftCardSaleSearchPage extends OrmsPage {
	
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsGiftCardSaleSearchPage _instance = null;
	
	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsGiftCardSaleSearchPage() {
	}
	
	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsGiftCardSaleSearchPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsGiftCardSaleSearchPage();
		}

		return _instance;
	}
	
	/** Determine if the associated web object exists */
	public boolean exists() {
		return (browser.checkHtmlObjectExists(".class", "Html.A", ".text",new RegularExpression("Gift Card Search/List", false)))
		&&(browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "giftCardOrderView_LIST"));			
	}
	
	/** 
	 * Input gift card number
	 * @param giftCardNum
	 */
	public void setGiftCardNumber(String giftCardNum){
		browser.setTextField(".id","GiftCardSearchCriteria.giftCardNumber", giftCardNum);
	}
	
		
	
	
	/**
	 * select Date Type
	 * @param status
	 */
	public void selectDateType(String type) {
		browser.selectDropdownList(".id", new RegularExpression("GiftCardSearchCriteria.giftCardDateType", false), type);
	}
	
	/**
	 * select Date Type
	 * @param id: index
	 */
	public void selectDateType(int id) {
		browser.selectDropdownList(".id", new RegularExpression("GiftCardSearchCriteria.giftCardDateType", false), id);
	}
	
	public void setOperatorLName(String lName){
		browser.setTextField(".id","GiftCardSearchCriteria.operatorLastName", lName);
	}
	
	public void setOperatorFName(String fName){
		browser.setTextField(".id","GiftCardSearchCriteria.operatorFirstName", fName);
	}

	/**
	 * Input gift card order ID
	 * @param giftCardSaleID
	 */
	public void setGiftCardSaleID(String giftCardSaleID){
		browser.setTextField(".id","GiftCardSearchCriteria.orderNum", giftCardSaleID);
	}
	
	/**
	 * Select gift card Status
	 * @param status
	 */
	public void selectGiftCardStatus(String status) {
		browser.selectDropdownList(".id", "GiftCardSearchCriteria.giftCardStatus",status);
	}
	/**
	 * select gift card status
	 * @param id: index
	 */
	public void selectGiftCardStatus(int id) {
		browser.selectDropdownList(".id", new RegularExpression("GiftCardSearchCriteria.giftCardStatus", false), id);
	}
	
	/**
	 * Input gift card program ID
	 * @param giftCardProgramID
	 */
	public void setGiftCardProgramID(String giftCardProgramID){
		browser.setTextField(".id","GiftCardSearchCriteria.giftCardPgmId", giftCardProgramID);
	}
	
	/**
	 * Input gift card program ID
	 * @param giftCardProgramID
	 */
	public void setGiftCardProgramName(String giftCardProgramName){
		browser.setTextField(".id","GiftCardSearchCriteria.giftCardPgmName", giftCardProgramName);
	}
	
	public String getErrorMsg()
	{
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",".id","NOTSET");
		String msg = "";
		if(objs.length>0)
		{
			msg = objs[0].text();
		}
		Browser.unregister(objs);
		return msg;
	}
	
	public String getStatusMsg()
	{
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",".id","statusMessages");
		String msg = "";
		if(objs.length>0)
		{
			msg = objs[0].text();
		}
		Browser.unregister(objs);
		return msg;
	}
	
	
	/**
	 * Select check box of available balance only
	 */
	public void selectAvailBalanceOnly(){
		browser.selectCheckBox(".id", "GiftCardSearchCriteria.availBalanceOnly");
	}
	
	/**
	 * Input invoice number
	 * @param num
	 */
	public void setInvoiceNum(String num) {
		browser.setTextField(".id","GiftCardSearchCriteria.invoiceNumber", num);
	}
	
	/**
	 * Input receipt number
	 * @param num
	 */
	public void setReceiptNum(String num){
		browser.setTextField(".id","GiftCardSearchCriteria.receiptNumber", num);
	}
	
	/**
	 * Input from date
	 * @param date
	 */
	public void setFromDate(String date){
		browser.setTextField(".id","GiftCardSearchCriteria.createFromDate_ForDisplay", date);
	}
	
	/**
	 * Input end date
	 * @param date
	 */
	public void setEndDate(String date){
		browser.setTextField(".id","GiftCardSearchCriteria.createEndDate_ForDisplay", date);
	}
	
	/**
	 * Input create location
	 * @param location
	 */
	public void setCreateLocation(String location){
		browser.setTextField(".id","GiftCardSearchCriteria.createLocation", location);
	}
	
	/**
	 * Input order number
	 * @param num
	 */
	public void setOrdNum(String num) {
		browser.setTextField(".id", "GiftCardSearchCriteria.orderNum", num);
	}
	
	/**
	 * Search gift card sales by order number
	 * @param num
	 */
	public void searchByOrdNum(String num){
		this.setOrdNum(num);
		this.clickGo();
		this.waitLoading();
	}
	
	/**
	 * Click gift card order number to goto detail page
	 * @param ordNum
	 */
	public void selectGiftCardOrd(String ordNum){
		browser.clickGuiObject(".class", "Html.A", ".text", ordNum, true);
	}
	
	/**
	 * Input customer phone
	 * @param phone
	 */
	public void setCustPhone(String phone){
		browser.setTextField(".id","GiftCardSearchCriteria.customerPhone", phone);	
	}
	
	/**
	 * Select check box of include area code
	 */
	public void selectIncludeAreaCode(){
		browser.selectCheckBox(".id", "GiftCardSearchCriteria.includeAreaCode");
	}
	
	/**
	 * Input customer last name
	 * @param laseName
	 */
	public void setCustLaseName(String lastName){
		browser.setTextField(".id","GiftCardSearchCriteria.customerLastName", lastName);	
	}
	
	/**
	 * Input customer first name
	 * @param laseName
	 */
	public void setCustFirstName(String firstName){
		browser.setTextField(".id","GiftCardSearchCriteria.customerFirstName", firstName);	
	}
	
	/**
	 * Input customer email address
	 * @param emailAdd
	 */
	public void setCustEmailAdd(String emailAdd){
		browser.setTextField(".id","GiftCardSearchCriteria.customerEmail", emailAdd);	
	}
	
	/**Click Go button*/
	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^Search$",false));
	}
	
	/**
	 * 
	 * @param posNum
	 * @throws PageNotFoundException
	 */
	public void selectGiftCardOrder(String giftCardSaleID) throws PageNotFoundException {
		browser.clickGuiObject(".class", "Html.A", ".text", giftCardSaleID, true);
	}
	
	/**
	 * Click first button
	 */
	public void clickFirst(){
		browser.clickGuiObject(".class","Html.A",".text","First");
	}
	
	/**
	 * Click previous button
	 */
	public void clickPrevious(){
		browser.clickGuiObject(".class","Html.A",".text","Previous");
	}
	
	/**
	 * Click next button
	 */
	public void clickNext(){
		browser.clickGuiObject(".class","Html.A",".text","Next");
	}
	
	/**
	 * Click last button
	 */
	public void clickLast(){
		browser.clickGuiObject(".class","Html.A",".text","Last");
	}

	public void refresh(){
		browser.clickGuiObject(".class", "Html.INPUT.text", ".id", "GiftCardSearchCriteria.giftCardNumber");
	}
	
 
	/**
	 * set all search criteria to search gift card sales records
	 * @param ordNum
	
	 */
	public void searchByCriteria(GiftCardInfo card) {
		
		if(!StringUtil.isEmpty(card.giftCardNum))
		{
			this.setGiftCardNumber(card.giftCardNum);
		}
		if(!StringUtil.isEmpty(card.status))
		{
			this.selectGiftCardStatus(card.status);
		}
		if(!StringUtil.isEmpty(card.giftCardProgrameID))
		{
			this.setGiftCardProgramID(card.giftCardProgrameID);
		}
		if(!StringUtil.isEmpty(card.giftCardProgrameName))
		{
			this.setGiftCardProgramName(card.giftCardProgrameName);
		}
		if(!StringUtil.isEmpty(card.giftCardSaleID))
		{
			this.setGiftCardSaleID(card.giftCardSaleID);
		}
		if(!StringUtil.isEmpty(card.invoiceNum))
		{
			this.setInvoiceNum(card.invoiceNum);
		}
		if(!StringUtil.isEmpty(card.receiptNum))
		{
			this.setReceiptNum(card.receiptNum);
		}
		if(!StringUtil.isEmpty(card.dateType))
		{
			this.selectDateType(card.dateType);
		}
				
		if(!StringUtil.isEmpty(card.fromDate))
		{
			this.setFromDate(card.fromDate);
			this.refresh();
		}
		if(!StringUtil.isEmpty(card.endDate))
		{
			this.setEndDate(card.endDate);
			this.refresh();
		}
		
		if(!StringUtil.isEmpty(card.location))
		{
			this.setCreateLocation(card.location);
		}
		if(!StringUtil.isEmpty(card.operatorLName))
		{
			this.setOperatorLName(card.operatorLName);
		}
		if(!StringUtil.isEmpty(card.operatorFName))
		{
			this.setOperatorFName(card.operatorFName);
		}
		if(!StringUtil.isEmpty(card.custPhone))
		{
			this.setCustPhone(card.custPhone);
		}
		if(!StringUtil.isEmpty(card.lName))
		{
			this.setCustLaseName(card.lName);
		}
		if(!StringUtil.isEmpty(card.fName))
		{
			this.setCustFirstName(card.fName);
		}
		if(!StringUtil.isEmpty(card.email))
		{
			this.setCustEmailAdd(card.email);
		}
		
		this.clickGo();
		this.waitLoading();
		
	}

	public void clearSearchCriteria() {

		this.setGiftCardNumber(StringUtil.EMPTY);
		this.selectGiftCardStatus(0);
		this.setGiftCardProgramID(StringUtil.EMPTY);
		this.setGiftCardProgramName(StringUtil.EMPTY);
		this.setGiftCardSaleID(StringUtil.EMPTY);
		this.setInvoiceNum(StringUtil.EMPTY);
		this.setReceiptNum(StringUtil.EMPTY);
		this.selectDateType(0);
		this.setFromDate(StringUtil.EMPTY);
		this.refresh();
		this.setEndDate(StringUtil.EMPTY);
		this.refresh();
		this.setCreateLocation(StringUtil.EMPTY);
		this.setOperatorLName(StringUtil.EMPTY);
		this.setOperatorFName(StringUtil.EMPTY);
		this.setCustPhone(StringUtil.EMPTY);
		this.setCustLaseName(StringUtil.EMPTY);
		this.setCustFirstName(StringUtil.EMPTY);
		this.setCustEmailAdd(StringUtil.EMPTY);

	}

	/**
	 * get total all records on the page.
	 * @param
	 * @return List of records.
	 */
	public List<GiftCardInfo> getRecordsOnPage() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<GiftCardInfo> records = new ArrayList<GiftCardInfo>();
		int rows;
		int columns;
		GiftCardInfo gift;
		
		do{
		objs = browser.getTableTestObject(".id", "giftCardOrderView_LIST");
		
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't find Gift card sales table object.");
				}
		
		table = (IHtmlTable)objs[0];
		rows = table.rowCount();
		columns = table.columnCount();
		logger.info("Find record on page OrmsGiftCardSaleSearchPage, "+rows+" rows, "+columns+" columns.");
		
		for(int i = 1; i < rows; i ++) {
			gift = new GiftCardInfo();
			gift.giftCardSaleID = table.getCellValue(i, table.findColumn(0, "Gift Card Sale #"));
			gift.invoiceNum = table.getCellValue(i, table.findColumn(0, "Invoice #"));
			gift.giftCardNum = table.getCellValue(i, table.findColumn(0, "Gift Card #"));
			gift.status = table.getCellValue(i, table.findColumn(0, "Status"));
			gift.availableBalance = table.getCellValue(i, table.findColumn(0, "Available Balance"));
			gift.custLNameAndFName = table.getCellValue(i, table.findColumn(0, "Customer"));
			gift.createDate = table.getCellValue(i, table.findColumn(0, "Create Date"));
			gift.location = table.getCellValue(i, table.findColumn(0, "Create Location"));
			gift.giftCardProgrameID = table.getCellValue(i, table.findColumn(0, "Gift Card Program ID"));
			gift.giftCardProgrameName = table.getCellValue(i, table.findColumn(0, "Gift Card Program Name"));
			records.add(gift);			
		}
				
		}while(gotoNext());
		
		Browser.unregister(objs);
		return records;
	}
	
	
	/**
	 * If "Next" button is available, click it
	 *
	 */
	public boolean gotoNext() {
		IHtmlObject[] pageingBar = browser.getHtmlObject(".class", "Html.TABLE", ".className", new RegularExpression("pagingBar",false));
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", "Next" );
		boolean toReturn = false;

		if (objs.length > 0) {
			toReturn = true;
			objs[0].click();
		}

		Browser.unregister(objs);
		Browser.unregister(pageingBar);
		this.waitLoading();

		return toReturn;
	}

	
}
