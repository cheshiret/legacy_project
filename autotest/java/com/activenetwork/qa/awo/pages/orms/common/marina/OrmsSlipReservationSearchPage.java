package com.activenetwork.qa.awo.pages.orms.common.marina;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipReservationInfo;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.pages.orms.common.OrmsReservationSearchPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Oct 23, 2012
 */
public class OrmsSlipReservationSearchPage extends OrmsReservationSearchPage {

	private static OrmsSlipReservationSearchPage _instance = null;
	
	private OrmsSlipReservationSearchPage() {}
	
	public static OrmsSlipReservationSearchPage getInstance() {
		if(_instance == null) {
			_instance = new OrmsSlipReservationSearchPage();
		}
		
		return _instance;
	}
	
	private String prefix = "MarinaOrderSearchCriteria-\\d+\\.";
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "MarinaOrderSearchBar");
	}
	
	public void setAreaDock(String dock) {
		browser.setTextField(".id", new RegularExpression(prefix+"areaDockName", false), dock);
	}
	
	public void setSlipNumName(String numOrName) {
		browser.setTextField(".id", new RegularExpression(prefix+"slipCd", false), numOrName);
	}
	
	public void setArrivalDate(String arrival) {
		browser.setCalendarField(".id", new RegularExpression(prefix+"arrivalDate_ForDisplay", false), arrival);
	}
	
	public void selectIncludeLaterArrivals() {
		browser.selectCheckBox(".id", new RegularExpression(prefix+"includeLaterArrivals", false));
	}
	
	public void unselectIncludeLaterArrivals() {
		browser.unSelectCheckBox(".id", new RegularExpression(prefix+"includeLaterArrivals", false));
	}
	
	public void setDepartureDate(String departure) {
		browser.setCalendarField(".id", new RegularExpression(prefix+"departureDate_ForDisplay", false), departure);
	}
	
	public void selectIncludeEarlierDepartures() {
		browser.selectCheckBox(".id", new RegularExpression(prefix+"includeEarlyDepartures", false));
	}
	
	public void unselectIncludeEarlierDepartures() {
		browser.unSelectCheckBox(".id", new RegularExpression(prefix+"includeEarlyDepartures", false));
	}
	
	public void setCaptainLastName(String lName) {
		browser.setTextField(".id", new RegularExpression(prefix+"captainLastName", false), lName);
	}
	
	public void setCaptainFirstName(String fName) {
		browser.setTextField(".id", new RegularExpression(prefix+"captainFirstName", false), fName);
	}
	
	public void setBoatName(String boatName) {
		browser.setTextField(".id", new RegularExpression(prefix+"boatName", false), boatName);
	}
	
	public void setBoatRegistrationNum(String registrationNum) {
		browser.setTextField(".id", new RegularExpression(prefix+"boatRegistrationNumber", false), registrationNum);
	}
	
	public void selectRafting(boolean yesOrNo) {
		browser.selectDropdownList(".id", new RegularExpression(prefix+"rafting", false), yesOrNo ? OrmsConstants.YES_STATUS : OrmsConstants.NO_STATUS);
	}
	
	public String getLabelText(RegularExpression regularText){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", regularText);
		if(objs.length<1){
			throw new ErrorOnPageException("Top SPAN object not exist");
		}
		IHtmlObject[] labelObjs = browser.getHtmlObject(".class", "Html.LABEL", objs[0]);
		if(labelObjs.length<1){
			throw new ErrorOnPageException("No label object exist");
		}
		
		String text = labelObjs[0].getProperty(".text");
		Browser.unregister(objs);
		Browser.unregister(labelObjs);
		return text;
	}
	
	public String getLabelText(String textValue){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^" + textValue, false));
		if(objs.length<1){
			throw new ErrorOnPageException("No TOP SPAN object exist");
		}
		IHtmlObject[] labelObjs = browser.getHtmlObject(".class", "Html.LABEL", objs[0]);
		if(labelObjs.length<1){
			throw new ErrorOnPageException("No label object exist");
		}
		
		String text = labelObjs[0].getProperty(".text");
		Browser.unregister(objs);
		Browser.unregister(labelObjs);
		return text;
	}
	
	public String getSearchForLableText(){
		
		/*HtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^Search For.*",false));
		if(objs.length<1){
			throw new ErrorOnPageException("No DIV object exist");
		}
		HtmlObject[] labelObjs = browser.getHtmlObject(".class", "Html.LABEL", objs[0]);
		if(labelObjs.length<1){
			throw new ErrorOnPageException("No label object exist");
		}
		
		String text = labelObjs[0].getProperty(".text");
		Browser.unregister(objs);
		Browser.unregister(labelObjs);
		return text;*/
		return this.getLabelText(new RegularExpression("^Search For.*",false));
	}
	/**
	 * get search For Default Value.
	 * @return
	 */
	public String getSearchForDefaultValue(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefix+"searchFor",false));
	}
	
	public String getPhoneLableValue(){
		/*HtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".text", "Phone");
		if(objs.length<1){
			throw new ErrorOnPageException("No DIV object exist");
		}
		HtmlObject[] labelObjs = browser.getHtmlObject(".class", "Html.LABEL", objs[0]);
		if(labelObjs.length<1){
			throw new ErrorOnPageException("No label object exist");
		}
		
		String text = labelObjs[0].getProperty(".text");
		Browser.unregister(objs);
		Browser.unregister(labelObjs);
		return text;*/
		return this.getLabelText("Phone");
	}
	
	public String getPhoneDefaultValue(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"phone",false));
	}
	
	public String getIncludeAreaCodeLabel(){
		return this.getLabelText("Include Area Code");
	}
	
	public boolean isIncludeAreaCodeCheckBoxSelected(){
		return browser.isCheckBoxSelected(".class", "Html.INPUT.checkbox", ".id", new RegularExpression(prefix+"includeAreaCode",false));
	}
	
	public String getLastNameLabelValue(){
		return this.getLabelText("Last Name");
	}
	
	public String getFirstNameLabelValue(){
		return this.getLabelText("First Name");
	}
	
	public String getEmailAddressLabelValue(){
		return this.getLabelText("Email Address");
	}
	
	public String getLastName(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"lastName",false));
	}
	
	public String getFirstName(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"firstName",false));
	}
	public String getEmailAddress(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"email",false));
	}
	
	public String getMarina(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"facilityName",false));
	}
	
	public boolean isMarianReadOnly(){
		 IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(prefix+"facilityName",false));
		 if(objs.length<1){
			 throw new ErrorOnPageException("No html object exist");
		 }
		 String text = objs[0].getProperty(".isDisabled");
		 return Boolean.valueOf(text);
	}
	
	public String getDockLabelValue(){
		return this.getLabelText("Dock or Area");
	}
	
	public String getDock(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"areaDockName",false));
	}
	
	public String getSlipNumLableValue(){
		return this.getLabelText("Slip # \\(Name\\)");
	}
	
	public String getSlipNum(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"slipCd",false));
	}
	
	public String getArrivalDateLabelValue(){
		return this.getLabelText("Arrival Date");
	}
	
	public String getArrivalDate(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"arrivalDate_ForDisplay",false));
	}
	public boolean isIncludeLaterArrivalsSelected(){
		return browser.isCheckBoxSelected(".id", new RegularExpression(prefix+"includeLaterArrivals",false));
	}
	
	public String getDptDateLabel(){
		return this.getLabelText("Departure Date");
	}
	
	public String getDptDate(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"departureDate_ForDisplay",false));
	}
	
	public boolean isIncludeEarlierDptSelected(){
		return browser.isCheckBoxSelected(".id", new RegularExpression(prefix+"includeEarlyDepartures",false));
	}
	public String getInvoiceNumLabel(){
		return this.getLabelText("Invoice #");
	}
	
	public String getInvoiceNum(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"invoiceNumber",false));
	}
	
	public String getReceiptNumLabel(){
		return this.getLabelText("Receipt #");
	}
	
	public String getReceiptNum(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"receiptNumber",false));
	}
	
	public String getClosureIdLabel(){
		return this.getLabelText("Closure ID");
	}
	
	public String getClosureId(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"closureID",false));
	}
	
	public String getPermitNumLabel(){
		return this.getLabelText("Permit Number");
	}
	
	public String getPermitNum(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"permitNumber",false));
	}
	
	public String getOrderStatus(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefix+"orderStatus",false));
	}
	
	public boolean isOrderStatusDropDownEnable(){
		return browser.checkHtmlObjectEnabled(".id", new RegularExpression(prefix+"orderStatus",false));
	}
	
	public List<String> getOrderStatusList(){
		List<String> list = browser.getDropdownElements(".id", new RegularExpression(prefix+"orderStatus",false));
		if(null == list || list.size()<1){
			throw new ErrorOnPageException("Order status list not exist");
		}
		return list;
	}
	
	public List<String> getReservationTypeList(){
		List<String> list = browser.getDropdownElements(".id", new RegularExpression(prefix+"slipReservationType",false));
		if(null == list || list.size()<1){
			throw new ErrorOnPageException("Reservation type list not exist");
		}
		return list;
	}
	
	public String getReservationStatus(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefix+"resStatus",false));
	}
	
	public boolean isReservationStatusDropDownEnable(){
		return browser.checkHtmlObjectEnabled(".id", new RegularExpression(prefix+"resStatus",false));
	}
	
	public List<String> getReservationStatusList(){
		List<String> list = browser.getDropdownElements(".id", new RegularExpression(prefix+"resStatus",false));
		if(null == list || list.size()<1){
			throw new ErrorOnPageException("Reservation status list not exist");
		}
		return list;
	}
	
	public String getCaptainLastNameLabelValue(){
		return this.getLabelText("Captain's Last Name");
	}
	
	public String getCaptainLastName(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"captainLastName",false));
	}
	
	public String getCatainFirstNameLabelValue(){
		return this.getLabelText("Captain's First Name");
	}
	
	public String getCaptainFirstName(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"captainFirstName",false));
	}
	
	public String getBoatNameLabelValue(){
		return this.getLabelText("Boat Name");
	}
	
	public String getBoatName(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"boatName",false));
	}
	
	public String getBoatRegistrationLabelValue(){
		return this.getLabelText("Boat Registration #");
	}
	
	public String getBoatRegistrationNum(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"boatRegistrationNumber",false));
	}
	
	public String getOperationLastNameLabelValue(){
		return this.getLabelText("Operator Last Name");
	}
	
	public String getOperationLastName(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"operatorLastName",false));
	}
	
	public String getOperationFirstNameLabelValue(){
		return this.getLabelText("Operator First Name");
	}
	
	public String getOperationFirstName(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"operatorFirstName",false));
	}
	
	public String getCustTypeLableValue(){
		return this.getLabelText(new RegularExpression("^Customer Type.*",false));
	}
	
	public List<String> getCustTypeList(){
		return browser.getDropdownElements(".id", new RegularExpression(prefix+"customerType",false));
	}
	
	public String getCustPassLabelValue(){
		return this.getLabelText("Customer Pass");
	}
	
	public String getCustPassNumLabelValue(){
		return this.getLabelText("Customer Pass Number");
	}
	
	  
	public String getCustPassNum(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"customerPassNumber",false));
	}
	
	public String getNameOfDisCountAppliedLabelValue(){
		return this.getLabelText("Name of Discount Applied");
	}
	
	public String getNameOfDisCountApplied(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix+"nameOfDiscountApplied",false));
	}
	
	public void setMarina(String marina){
		if(!this.isMarianReadOnly()){
			browser.setTextField(".id", new RegularExpression(prefix+"facilityName",false), marina);
		}
	}
	
	private IHtmlTable getTable() {
		RegularExpression reg = new RegularExpression("grid_\\d+_LIST", false);
		IHtmlObject[] reservetable = browser.getTableTestObject(".id", reg);
		if(reservetable.length < 0){
			throw new ItemNotFoundException("Can't find search result table!");
		}
		
		IHtmlTable reserveTableGrid = (IHtmlTable) reservetable[0];
//		Browser.unregister(reservetable);
		return reserveTableGrid;
	}

	public List<String> getColumnListValue(String columnName){
		IHtmlTable table = this.getTable();
		int col = table.findColumn(0, columnName);
		if(col < 0){
			throw new ItemNotFoundException("Can't find column with name:"+columnName);
		}
		
		List<String> colList = table.getColumnValues(col);
		colList.remove(0);// remove title.
		return colList;
	}
	
	public List<String> getMarinaColValue(){
		return this.getColumnListValue("Marina");
	}
	
	public void setPermitNumber(String permitNum){
		browser.setTextField(".id", new RegularExpression(prefix+"permitNumber", false), permitNum);
	}
	
	public void setCustomerType(String type){
		if(StringUtil.isEmpty(type)){
			browser.selectDropdownList(".id", new RegularExpression(prefix+"customerType", false), 0, true);
		} else {
			browser.selectDropdownList(".id", new RegularExpression(prefix+"customerType", false), type, true);
		}
	}
	
	public void setCustomerPass(String pass){
		if(StringUtil.isEmpty(pass)){
			browser.selectDropdownList(".id", new RegularExpression(prefix+"customerPassType", false), 0, true);
		} else {
			browser.selectDropdownList(".id", new RegularExpression(prefix+"customerPassType", false), pass, true);
		}
	}
	
	public void setCustomerPassNum(String passNum){
		browser.setTextField(".id", new RegularExpression(prefix+"customerPassNumber", false), passNum);
	}

	public void searchSlipReservation(SlipReservationInfo res){
		browser.sync();
		if(res.customer.hPhone!=null)
		  this.setPhone(res.customer.hPhone);
		
		if (res.areacode == true) {
			this.deSelectIncludeAreaCode();
		} else {
			this.selectIncludeAreaCode();
		}

		if(res.customer.lName!=null)
		  this.setLastName(res.customer.lName);
		
		if(res.customer.fName!=null)
		  this.setFirstName(res.customer.fName);
		
		if(res.customer.email!=null)
		  this.setEmail(res.customer.email);
		
		if(res.getSlip().getParentDockArea()!=null)
		  this.setParkName(res.site.parkName);

		if(res.getSlip().getCode() != null)
			this.setSlipNum(res.getSlip().getCode());
		else if(res.getSlip().getName() != null)
			this.setSlipNum(res.getSlip().getName());

		if(res.getSlip().getArrivalDate() != null)
			this.setArrivalDate(res.getSlip().getArrivalDate());
		
		if (res.includelaterarrivedate == true)
			this.selectIncludeLaterArrivals();
		else 
			this.deSelectIncludeLaterArrivals();
		
		if(res.getSlip().getDepartureDate() != null){
			this.setDepartureDate(res.getSlip().getDepartureDate());
		}

		if (res.includeEarlierDepartures == true)
			this.selectIncludeEarlyDepatures();
		else 
			this.deSelectIncludeEarlyDepartures();
		
		if(res.reservNum!=null)
		  this.setReservNum(res.reservNum);
		
		if(res.invoiceNum != null)
			this.setInvoiceNumber(res.invoiceNum);

		if(res.receiptnum!=null && res.receiptnum.length()>0)
		  this.setReceiptNumber(res.receiptnum);
		
		// if order status is blank, will select the first one automatically.
		if (res.orderStatus != null) {
			this.selectOrderStatus(res.orderStatus);
		}
		
		// if reservation type is blank, will select the first one automatically.
		if(null != res.getResType()){
			this.selectResType(res.getResType());
		}
		
		// if reservation status is blank, will select the first one automatically.
		if (null!=res.reservStatus) {
			this.selectReservationStatus(res.reservStatus);
		}

		if(res.closureID!=null)
		  this.setClosureID(res.closureID);
		
		if(res.customer.type != null)
			this.setCustomerType(res.customer.type);

		if(res.customer.pass != null)
			this.setCustomerPass(res.customer.pass);
		
		if(res.customer.passNumber != null)
			this.setCustomerPassNum(res.customer.passNumber);
		
		clickGO();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void setSlipNum(String slip){
		browser.setTextField(".id", new RegularExpression(prefix+"slipCd", false), slip);
	}
	
	private IHtmlObject[] getResistTableObject(){
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Res\\. #.*", false));
		if(objs.length <= 0){
			throw new ItemNotFoundException("Could not find marina order search result list table.t");
		}
		return objs;
	}
	
	public List<String> getAllResNumOnPage() {
		return getAllResNums(true);
	}
	
	public List<String> getAllResNums() {
		return getAllResNums(false);
	}
	
	public List<String> getAllResNums(boolean onPage) {
		List<String> resUI = new ArrayList<String>();
		List<String> temp = new ArrayList<String>();
		
		IHtmlObject[] objs = this.getResistTableObject();
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		temp = table.getColumnValues(table.findColumn(0, "Res. #"));
		temp.remove(0);
		resUI.addAll(temp);
		
		if(!onPage) {
			PagingComponent paging = new PagingComponent();
			while (paging.nextExists(true)){
				paging.clickNext();
				ajax.waitLoading();
				objs = this.getResistTableObject();
				table = (IHtmlTable)objs[objs.length-1];
				temp.clear();
				temp = table.getColumnValues(table.findColumn(0, "Res. #"));
				temp.remove(0);
				resUI.addAll(temp);
			}
		}
		
		Browser.unregister(objs);
		return resUI;
	}
	
	public void verifySlipResNumsExistedInSearchResult(List<String> resUI, String... expectResNums) {
		logger.info("Verify Slip reservation number "+Arrays.toString(expectResNums)+" existed in search result list.");
		if(resUI.size()<1)
			throw new ErrorOnPageException("Slip reservation number "+Arrays.toString(expectResNums)+" should existed in search result list.");
		
		boolean found = true;
		for(String resNum:expectResNums) {
			found &= resUI.contains(resNum);
			if(!resUI.contains(resNum))
				logger.error("Slip reservation number "+resNum+" should existed in search result list.");
		}
		
		if(!found)
			throw new ErrorOnPageException("Failed to verify search result. Please check log.");
		
		logger.info("---Verify slip reservation number "+Arrays.toString(expectResNums)+" existed in search result list successfully.");
	}
	
	public void verifySlipResNumsNotExistedInSearchResult(List<String> resUI, String... expectResNums) {
		logger.info("Verify Slip reservation number "+Arrays.toString(expectResNums)+" NOT existed in search result list.");
		if(resUI.size()<1) {
			logger.info("---No reservations existed in search result list.");
			return;
		}
		
		boolean found = false;
		for(String resNum:expectResNums) {
			found |= resUI.contains(resNum);
			if(resUI.contains(resNum))
				logger.error("Slip reservation number "+resNum+" should NOT existed in search result list.");
		}
		
		if(found)
			throw new ErrorOnPageException("Failed to verify search result. Please check log.");
		
		logger.info("---Verify slip reservation number "+Arrays.toString(expectResNums)+" NOT existed in search result list successfully.");
	}
	
	public String getErrorMessage() {
		String err = null;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".className", "message msgerror");
		if(objs.length<1)
			return err;
		err=objs[0].text();
		Browser.unregister(objs);
		return err;
	}
	
	public String getNoteMessage(){
		String notes = null;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".className", "message msgsuccess");
		if(objs.length<1)
			return notes;
		notes=objs[0].text();
		Browser.unregister(objs);
		return notes;
	}
	
	public List<String> getNoteMessgaes(){
		List<String> messageList = new ArrayList<String>();
		
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".className", "message msgsuccess");
		if(objs.length<1) {
			logger.info("can't get any note message!");
			return messageList;
		}
		for(int i=0; i<objs.length; i++){
			messageList.add(objs[i].text());
		}
		Browser.unregister(objs);
		return messageList;
	}
	
	public void verifyErrorMsgForSlipResSearch(String expectedMsg) {
		logger.info("Verify error message for slip reservation search.");
		String errUI=this.getErrorMessage();
		if(StringUtil.isEmpty(errUI))
			throw new ErrorOnPageException("Error Message "+expectedMsg+" should exist on page.");
		if(!errUI.contains(expectedMsg))
			throw new ErrorOnPageException("Error Message was not correct on page.", expectedMsg, errUI);
		
		logger.info("---Verify error message successfully on page.");
	}
	
	public List<String> getAllBalanceOnPage() {
		List<String> resUI = new ArrayList<String>();
		List<String> temp = new ArrayList<String>();
		
		IHtmlObject[] objs = 	this.getResistTableObject();
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		temp = table.getColumnValues(table.findColumn(0, "Balance"));
		temp.remove(0);
		resUI.addAll(temp);
		
		return resUI;
	}
	
	/**
	 * Check the corresponding check box of a specified reservation identified by a reservation number
	 * @param resID
	 */
	public void checkReservation(String resID) {
		IHtmlObject objs[] = null;
		IHtmlTable resTable = null;
		int index = -1;
		boolean found=false;
		do {
			objs = browser.getHtmlObject(".class", "Html.TABLE", ".id", new RegularExpression("grid_\\d+_LIST",false));
//			objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^Res. # Invoice #.*",false));
			resTable = (IHtmlTable) objs[0];
			for(int i = 1; i < resTable.rowCount(); i ++) {
				if(resTable.getCellValue(i, 1).trim().equals(resID)) {
					index = i;
					found = true;
					break;
				}
			}
			if(!found && this.hasNext()) {
				this.clickNext();
				this.waitLoading();
			} else
				break;
		} while(!found);
		
		if(!found) {
			throw new ErrorOnPageException("Can't find a reservation which number is " + resID);
		}
		IHtmlObject checkboxes[] = browser.getCheckBox(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems", false));
		((ICheckBox)checkboxes[index - 1]).select();
		
		Browser.unregister(objs);
		Browser.unregister(resTable);
		Browser.unregister(checkboxes);
	}
	
	public String getResStatus(String resNum){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",".id", new RegularExpression("grid_\\d+_LIST", false));
//		HtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",".text", new RegularExpression("^Res. # Invoice #.*",false));
		int rowNum = ((IHtmlTable) objs[0]).findRow(0, resNum);
		String status = ((IHtmlTable) objs[0]).getCellValue(rowNum, 2);
		Browser.unregister(objs);
		return status;
	}
	
	/**
	 *The method execute the work flow that verify the reservation's status in
	 * reservation search/list page
	 * @param resNum
	 * @param expectedStatus
	 */
	public void verifyResStatus(String resNum, String expectedStatus) {
		logger
		.info("Verify the reservation's status in reservation search page.");
		this.searchReservation(resNum);
		ajax.waitLoading();
		this.searchWaitExists();
		String actualStatus = this.getResStatus(resNum);
		if (!expectedStatus.equals(actualStatus)) {
			throw new ItemNotFoundException("The reservation status is not "
					+ expectedStatus);
		}
	}
	
	private void verifyBatchActionResult(String action){
		boolean result = true;
		List<String> messageList = this.getNoteMessgaes();
		if(null == messageList || messageList.size() < 0){
			logger.error("Didn't do DOCK!!!");
			result = false;
		} else {
			for(String message:messageList){
				if(!message.contains(action+" process successfully completed")){
					result = false;
				}
			}
		}
		
		if(!result){
			throw new ErrorOnPageException("Batch check in isn't successful!!!");
		}
	}
	
	public void verifyBatchCheckInResult(){
		this.verifyBatchActionResult("Check-in");
	}
	
	public void verifyBatchCheckOutResult(){
		this.verifyBatchActionResult("Check-out");
	}

	public void verifyBatchDockResult(){
		this.verifyBatchActionResult("Docking");
	}
}
