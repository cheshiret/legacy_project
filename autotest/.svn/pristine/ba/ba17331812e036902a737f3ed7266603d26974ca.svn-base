/*
 * $Id: FldMgrPOSDetailPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.common.pos;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.CustPass;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ReceiptInfo;
import com.activenetwork.qa.awo.datacollection.legacy.web.MaPassInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * TODO: enter class description
 * 
 * @author jdu
 */
public class OrmsPOSDetailsPage extends OrmsPage {

	/**
	 * Script Name   : FldMgrPOSDetailPage
	 * Generated     : Feb 8, 2008 11:27:51 AM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2008/02/08
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsPOSDetailsPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsPOSDetailsPage() {
		browser = Browser.getInstance();
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsPOSDetailsPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsPOSDetailsPage();
		}

		return _instance;
	}
	
	private static String LABLE_PLACARDREQUIRED = "Placard Required";
	private static String LABLE_GIFTTAGREQUIRED = "Gift Tag Required";
	private static String LABLE_VEHICLEPLATE = "Vehicle Plate";
	private static String LABLE_SECONDVEHICLEPLATE = "2nd Vehicle Plate";
	
	protected Property[] invoiceNumberDivProp(){
		return Property.concatPropertyArray(div(), ".text", new RegularExpression("^Invoice #.*",false));
	}
	
	protected Property[] posSaleDivProp(){
		return Property.concatPropertyArray(div(), ".text", new RegularExpression("^POS Sale #.*",false));
	}
	
	protected Property[] addCustPassButtonProp(){
		return Property.concatPropertyArray(a(), ".text","Add Customer Pass");
	}
	
	protected Property[] custPassDivProp(){
		return Property.concatPropertyArray(div(), ".id","custDytable");
	}
	
	protected Property[] removeCustPassButtonProp(){
		return Property.concatPropertyArray(a(), ".text","Remove Customer Pass");
	}
	
	protected Property[] custPassDropdownListProp(){
		return Property.addToPropertyArray(select(), ".id", new RegularExpression("OrderProofView-\\d+\\.proofTypeID",false));
	}
	
	protected Property[] custPassNumTextProp(){
		return Property.addToPropertyArray(input("text"), ".id", new RegularExpression("OrderProofView-\\d+\\.proofNumber",false));
	}
	
	protected Property[] holderNameTextProp(){
		return Property.addToPropertyArray(input("text"), ".id", new RegularExpression("OrderProofView-\\d+\\.passHolderName",false));
	}
	
	protected Property[] eligibilityNoteTextProp(){
		return Property.addToPropertyArray(input("text"), ".id", new RegularExpression("OrderProofView-\\d+\\.proofNotes",false));
	}
	
	protected Property[] expiryDateTextProp(){
		return Property.addToPropertyArray(input("text"), ".id", new RegularExpression("OrderProofView-\\d+\\.expiryDate_ForDisplay",false));
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression(" POS Detail$", false)) ||
				browser.checkHtmlObjectExists(".class", "Html.TD", ".text",
						new RegularExpression("POS Details", false));
	}

	/**Click OK button*/
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK", true);
	}
	
	public boolean isOKButtonEnabled(){
		return browser.checkHtmlObjectDisplayed(".class", "Html.A", ".text", "OK");
	}

	/**Click Cancel button*/
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel", true);
	}

	/**Click Reset*/
	public void clickReset() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Reset", true);
	}

	/***
	 * Input reason
	 * @param note
	 */
	public void setNote(String note) {
		browser.setTextArea(".id", "reason_for_changes", note);
	}

	/**Click Move POS*/
	public void clickMovePOS() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Move POS( Sale)?",false), true);
	}

	/**click Add to cart */
	public void clickAddToCart() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add to Cart",true);
	}

	/**Click Fees*/
	public void clickFees() {
		IHtmlObject[] objs = getTransactionFrame();
		browser.clickGuiObject(".class", "Html.A", ".text", "Fees",false,0,objs[0]);
		Browser.unregister(objs);
	}

	/**Click void Sale*/
	public void clickVoidSale() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Void Sale", true);
	}

	/**Click AddItemTosale*/
	public void clickAddItemToSale() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Item to Sale",true);
	}

	public void clickApplyDiscount()
	{
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply Discount",true);
	}
	
	/**Click Add Note and Alerts button*/
	public void clickAddNoteAlerts() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Note/Alert");
	}
	
	/**
	 * Select Reason Code
	 * @param reason
	 */
	public void selectReasonCode(String reason) {
		if (reason == null || reason.length() < 1)
			browser.selectDropdownList(".id", "pos_reason_code", 1);
		else
			browser.selectDropdownList(".id", "pos_reason_code", reason);
	}
	
	public String getPosCreatedBy(){
		 RegularExpression reg=new RegularExpression("^POS Actions Add to Cart.*",false);
		 IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".text",reg);
		 String posInfo=objs[0].getProperty(".text").toString();
		 Browser.unregister(objs);
		 
		 String createdBy = posInfo.substring(posInfo.indexOf("Created By")+"Created By".length()+1, posInfo.indexOf("Order Status")).trim();
		 return createdBy;
	}
	
	public String getPosStatus(){
	    RegularExpression reg=new RegularExpression("^POS Actions Add to Cart.*",false);
	    IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".text",reg);
	    String posInfo=objs[0].getProperty(".text").toString();
	    Browser.unregister(objs);
	    String status=posInfo.substring(posInfo.indexOf("Order Status")+"Order Status".length(),posInfo.indexOf("Price")).trim();
	    
	    return status;
	}
	
	public String getPosItemName(int index){
	    IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".text",new RegularExpression("^ ?Barcode Item Name",false));
	    IHtmlTable table = (IHtmlTable) objs[0];
	    String posItem = table.getCellValue(index, 2);
	    
	    Browser.unregister(objs);
	    return posItem;
	}
	
	public String getPosTotalPrice(){
		int toReturn = 0;
	    IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".text",new RegularExpression("^ ?Barcode Item Name",false));
	    IHtmlTable table = (IHtmlTable) objs[0];
	    
		toReturn = table.findRow(1, "Total Price");
		Browser.unregister(objs);
	    return table.getCellValue(toReturn,6).split(" ")[1];
	}
	/**
	 * Get the pos sale number
	 * @return
	 *             --pos number
	 */
	public String getPosNum(){
		RegularExpression reg=new RegularExpression("^POS Actions Add to Cart.*",false);
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".text",reg);
	    String posInfo=objs[0].getProperty(".text").toString();
	    Browser.unregister(objs);
	    
	    String posNum = posInfo.substring(posInfo.indexOf("POS Sale #")+"POS Sale #".length(), posInfo.indexOf("Sale Date")).trim();
	    return posNum;
	}
	
	
	
	public boolean verifyPosStatus(String posStatus){
	    boolean correctStatus=false;
	    if(getPosStatus().equalsIgnoreCase(posStatus)){
	        correctStatus=true;
	    }
	    
	    return correctStatus;
	}
	
	public void setReturnQuantity(String quantity){
	    browser.setTextField(".id","return_quantity_row_0",quantity);
	}
	
	public void setReturnQuantityByPosName(String quantity, String posName){
		IHtmlObject[] objs=browser.getHtmlObject(".id",new RegularExpression("^(pos_row_)\\d",false));
		System.out.println("length:"+objs.length);
		System.out.println("length:"+objs[0].toString());
	    
		for(int i=0;i<objs.length;i++){
			System.out.println("text:"+objs[i].getProperty(".text").toString().trim());
	    	if(objs[i].getProperty(".text").toString().trim().equalsIgnoreCase(posName)){
	    		break;
	    	}
	    }
		
	    browser.setTextField(".id","return_quantity_row_"+"i",quantity);
	    
	    Browser.unregister(objs);

	}
	
	/**
	 * Get the current quantity
	 * @param item -- the item purchased
	 * @return -- quantity
	 */
	public String getCurrentQuantity(String item){
	    String quantity="";
	    IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".text",new RegularExpression("\\W*Barcode Item Name",false));
	    IHtmlObject[] trs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression(".*" + item + ".*", false), objs[0]);
	    IHtmlObject[] tds = browser.getHtmlObject(".class", "Html.TD", trs[0]);
	    quantity=tds[3].text().trim();
	    
	    Browser.unregister(objs);

	    return quantity;
	}
	
	private String getObjIDByLabel(String labelReg) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "HTML.Label", ".text", new RegularExpression(labelReg, false));
		if (objs.length < 1) {
			throw new ObjectNotFoundException("Can't find the div for " + labelReg);
		}
		String forValue = objs[0].getProperty("for");
		
		Browser.unregister(objs);
		return forValue;
	}
	
	public String getPlacardRequired() {
		return browser.getTextFieldValue(".id", new RegularExpression(getObjIDByLabel(LABLE_PLACARDREQUIRED), false));
	}
	
	public String getGiftTagRequired(){
		return browser.getTextFieldValue(".id", new RegularExpression(getObjIDByLabel(LABLE_GIFTTAGREQUIRED), false));
	}
	
	public String getVehiclePlate(){
		return browser.getTextFieldValue(".id", new RegularExpression(getObjIDByLabel(LABLE_VEHICLEPLATE), false));
	}
	
	public String getSecondVehiclePlate(){
		return browser.getTextFieldValue(".id", new RegularExpression(getObjIDByLabel(LABLE_SECONDVEHICLEPLATE), false));
	}
	
	/**
	 * Verify addition information for order's first record
	 * @param mapass
	 */
	public List<String> verifyAdditionalInfo(MaPassInfo mapass){
		boolean result = true;
		List<String> attrValues = new ArrayList<String>();

		if(mapass.isSelectPlacardCheckBox()){
			attrValues.add(mapass.isSelectPlacardCheckBox()?"YES":"");
			result = MiscFunctions.compareResult(LABLE_PLACARDREQUIRED, attrValues.get(attrValues.size()-1), getPlacardRequired());
		}

		if(mapass.isSelectGiftMesCard()){
			attrValues.add(mapass.isSelectGiftMesCard()?"YES":"");
			result &= MiscFunctions.compareResult(LABLE_GIFTTAGREQUIRED, attrValues.get(attrValues.size()-1), getGiftTagRequired());
		}

		attrValues.add(mapass.getVehicleLicenseNum().trim());
		result &= MiscFunctions.compareResult(LABLE_VEHICLEPLATE, attrValues.get(attrValues.size()-1), getVehiclePlate());

		if(mapass.isSelectBuyASecondCarStickerCheckBox()){
			attrValues.add(mapass.getSecondVehicleLicenseNum().trim());
			result &= MiscFunctions.compareResult(LABLE_SECONDVEHICLEPLATE, attrValues.get(attrValues.size()-1), getSecondVehiclePlate());	
		}

		if(!result){
			throw new ErrorOnPageException("Not all additional infomation are correct. Please check details from previous logs.");
		}
		
		return attrValues;
	}
	
	/**
	 * This method used to get pos charged reservation number
	 * @return ResNum
	 */
	public String getChargeToResNum() {
		RegularExpression reg=new RegularExpression("^POS Actions Add to Cart.*",false);
	    IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".text",reg);
	    String posInfo=objs[0].getProperty(".text").toString();
	    Browser.unregister(objs);
	    String resNum=posInfo.substring(posInfo.indexOf("Reservation #")+"Reservation #".length(),posInfo.indexOf("Site#(Name)")).trim();
	    
	    return resNum;
	}
	
	/**
	 * This method used to get alert or note info by match given string
	 * @param reg-determine the content we want to get,such as Notes or Alerts
	 * @return-a string which contain alert or note info
	 */
	public String getAlertOrNoteInfo(String reg) {
		RegularExpression rex = new RegularExpression("^POS Actions Add to Cart.*",false);
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".text",rex);
		String posInfo=objs[0].getProperty(".text").toString();
//		Browser.unregister(objs);

		return posInfo.split("Add Customer Pass")[1].split(reg)[1];
	}
	
	public boolean checkAddToCartEnable(){
		return browser.checkHtmlObjectExists(".text","Add to Cart",".disabled", "false");
	}
	
	public boolean checkVoidSaleEnable(){
		return browser.checkHtmlObjectExists(".text", "Void Sale", ".disabled","false");
	}
	
	public boolean checkReturnEnable(){
		return browser.checkHtmlObjectExists(".id", "return_quantity_row_0", ".disabled","false");
	}
	
	/**click NotesAndAlert */
	public void clickNotesAndAlert() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Notes & Alerts",true);
	}
	
	/**
	 * Retrieve the table objects for each section in order summary page by given expression.
	 * @param regStr
	 * @return
	 */
	public IHtmlObject[] getSubSectionTRS(Object regStr) {
		
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR",".text", regStr);
		if(objs.length==0) {
			throw new ItemNotFoundException("No table match the given expression.");
		}
		return objs;
	}
	
	/**
	 * This method get the receipt information on the POS sale detail page
	 * @return
	 */
	public ReceiptInfo getRecipientInfo(){
		//retrieve the data for Receipt Details section
		ReceiptInfo receipt = new ReceiptInfo();
		RegularExpression reg = new RegularExpression("^Receipt Details.*", false);
		IHtmlObject[] receiptObjs = this.getSubSectionTRS(reg);
		String receiptText = receiptObjs[0].getProperty(".text").toString();
		receipt.id = receiptText.split("Receipt #")[1].split("Receipt Date & Time")[0].trim();//get Receipt number
		receipt.receiptDateAndTime = receiptText.split("Receipt Date & Time")[1].split("Sales Location")[0].trim();//get Receipt Date & Time
		receipt.salesLocation = receiptText.split("Sales Location")[1].split("Created By")[0].trim();//get Receipt Sales Location
		receipt.createdBy = receiptText.split("Created By")[1].replace("Reprint Receipt","").trim().replace(" ", "");//get Receipt Sales Location
	
		Browser.unregister(receiptObjs);
		return receipt;
	}
	
	/**
	 * This method get the customer information on the POS sale detail page
	 * @return-Customer
	 */
	public Customer getCustomerInfo() {
		Customer cust = new Customer();
		RegularExpression reg = new RegularExpression("^Customer.*", false);
		IHtmlObject[] objs = this.getSubSectionTRS(reg); 
		String text = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		cust.mName = text.substring(text.indexOf("Name") + "Name".length(),
				text.indexOf("Phone")).replace(" ", "").trim();
		cust.hPhone = text.substring(text.indexOf("Phone") + "Phone".length(),
				text.indexOf("Email")).trim();
		cust.email = text.substring(text.indexOf("Email") + "Email".length(),
				text.indexOf("Organization Name")).trim();
		//"Organization Name" is null if there is no Organization Name information
		cust.organization = text.substring(text.indexOf("Organization Name")	+ "Organization Name".length(),
				text.length()).replace(" ", "");
		return cust;
	}
	
	/**
	 * This method get the detail information on the POS sale detail page
	 * @return-POS
	 */
	public POSInfo getPOSDetailInfo(){
		POSInfo pos = new POSInfo();
		RegularExpression reg = new RegularExpression("^POS Details.*", false);
		IHtmlObject[] objs = this.getSubSectionTRS(reg); 
		String text = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		pos.ordID = text.split("POS Sale #")[1].split("Sale Date")[0].trim();
		pos.saleInfo.saleDate = text.split("Sale Date")[1].split("Sale Location")[0].trim();
		pos.saleInfo.saleLocation = text.split("Sale Location")[1].split("Created By")[0].trim();
		pos.saleInfo.createBy = text.split("Created By")[1].split("Order Status")[0].trim();
		pos.saleInfo.orderStatus= text.split("Order Status")[1].split("Price")[0].trim();
		pos.price = text.split("Price")[1].split("Paid")[0].trim();
		pos.saleInfo.paid = text.split("Paid")[1].split("Unissued Refund")[0].trim();
		pos.saleInfo.unIssuedRefund = text.split("Unissued Refund")[1].split("Balance")[0].trim();
		pos.saleInfo.balance = text.split("Balance")[1].split("Collection Status")[0].trim();
		pos.saleInfo.collectionStatus= text.split("Collection Status")[1].trim();
		return pos;
	}
	
	public boolean isReprintReceiptButtonExist() {
		return browser.checkHtmlObjectExists(".class", "Html.A", 
				".text", "Reprint Receipt");
	}
	
	public void verifyOrderInfo(POSInfo pos, Customer cust, ReceiptInfo receipt) {
		boolean passed = true;
		//pos detail information check
		POSInfo posPage = this.getPOSDetailInfo();
		passed &= MiscFunctions.compareResult("POS Sale#:",pos.ordID, posPage.ordID);
		passed &= MiscFunctions.compareResult("POS Sale Date:",pos.saleInfo.saleDate, posPage.saleInfo.saleDate);
		passed &= MiscFunctions.compareResult("POS Sale Location:",pos.saleInfo.saleLocation, posPage.saleInfo.saleLocation);
		passed &= MiscFunctions.compareResult("POS Sale CreateBy:",pos.saleInfo.createBy.replace(" ", ""), posPage.saleInfo.createBy.replace(" ", ""));
		passed &= MiscFunctions.compareResult("POS Sale Status:",pos.saleInfo.orderStatus, posPage.saleInfo.orderStatus);
		passed &= MiscFunctions.compareResult("POS Sale Price:",pos.price, posPage.price);
		passed &= MiscFunctions.compareResult("POS Sale Paid:",pos.saleInfo.paid, posPage.saleInfo.paid);
		passed &= MiscFunctions.compareResult("POS Sale unIssuedRefund:",Double.parseDouble(pos.saleInfo.unIssuedRefund), 
				Double.parseDouble(posPage.saleInfo.unIssuedRefund.replace("$", "").trim()));
		passed &= MiscFunctions.compareResult("POS Sale Balance:",pos.saleInfo.balance, posPage.saleInfo.balance);
		passed &= MiscFunctions.compareResult("POS Sale CollectionStatus:",pos.saleInfo.collectionStatus, posPage.saleInfo.collectionStatus);
		//Customer information
		Customer custPage = this.getCustomerInfo();
		passed &= MiscFunctions.compareResult("Customer Name:",cust.mName, custPage.mName);
		passed &= MiscFunctions.compareResult("Customer phone:",cust.hPhone, custPage.hPhone);
		passed &= MiscFunctions.compareResult("Customer Email:",cust.email, custPage.email);
		passed &= MiscFunctions.compareResult("Customer OrgnizationName:",cust.organization, custPage.organization);
		//Receipt information
		ReceiptInfo receiptPage = this.getRecipientInfo();
		passed &= MiscFunctions.compareResult("Receipt Detail #:",receipt.id, receiptPage.id);
		int dif = DateFunctions.compareDateTime(receipt.receiptDateAndTime.replaceAll("AKST|AKDT", "").trim(), receiptPage.receiptDateAndTime.replaceAll("AKST|AKDT", "").trim());
		if(dif!=0){
			logger.error("Recipt Date&time not correct, expect:"+receipt.receiptDateAndTime+",actual:"+receiptPage.receiptDateAndTime);
		}
		passed &= (dif==0);
		passed &= MiscFunctions.compareResult("Receipt Detail SalesLocation:",receipt.salesLocation, receiptPage.salesLocation);
		passed &= MiscFunctions.compareResult("Receipt Detail CreatedBy:",receipt.createdBy, receiptPage.createdBy);
		if(!passed){
			throw new ErrorOnPageException("PosSaleDetailInfo/CustomerInfo/ReceiptInfo for the pos order detail may not correct, please check the log above!");
		}
	}
	
	public String getPOSSaleNum(){
		String text = browser.getObjectText(this.posSaleDivProp());
		String invoiceNum = text.replaceAll("POS Sale #", "").trim();
		return invoiceNum;
	}
	
	public void clickAddCustPassButton(){
		IHtmlObject[] objs = browser.getHtmlObject(this.custPassDivProp());
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get any customer pass div top object.");
		}
		browser.clickGuiObject(this.addCustPassButtonProp(), true, 0, objs[objs.length-1]);
		Browser.unregister(objs);
	}
	
	public void selectCustomerPass(String custPass, int index){
		browser.selectDropdownList(this.custPassDropdownListProp(), custPass, index);
	}
	
	public void setPassNumber(String passNum,int index){
		browser.setTextField(this.custPassNumTextProp(), passNum,index);
	}
	
	public void setHolderName(String holderName,int index){
		browser.setTextField(this.holderNameTextProp(), holderName,index);
	}
	
	public void setEligibilityNotes(String note,int index){
		browser.setTextField(this.eligibilityNoteTextProp(), note,index);
	}
	
	public void setExpiryDate(String expiryDate,int index){
		browser.setTextField(this.expiryDateTextProp(), expiryDate,index);
	}
	
	public void setupCustomerPassInfo(CustPass custPass,int index){
		if(StringUtil.notEmpty(custPass.passType)){
			this.selectCustomerPass(custPass.passType,index);
			ajax.waitLoading();
		}
		if(null != custPass.passNum){
			this.setPassNumber(custPass.passNum,index);
		}
		if(null != custPass.holderName){
			this.setHolderName(custPass.holderName,index);
		}
		if(null != custPass.notes){
			this.setEligibilityNotes(custPass.notes,index);
		}
		if(null != custPass.expiryDate){
			this.setExpiryDate(custPass.expiryDate,index);
		}		
	}
	
	public void addCustomerPassInfo(List<CustPass> custPasses){
		for(int i=0; i<custPasses.size();i++){
			this.addCustomerPassInfo(custPasses.get(i),i);
		}		
	}
	
	public void addCustomerPassInfo(CustPass custPass, int index){
		this.clickAddCustPassButton();
		ajax.waitLoading();
		this.waitLoading();
		this.setupCustomerPassInfo(custPass,index);
	}
	
	public List<String> getCustomerPassDropdownListElements(){
		return browser.getDropdownElements(this.custPassDropdownListProp());
	}
	
	public void clickRemoveCustPass(int index){
		browser.clickGuiObject(this.removeCustPassButtonProp(), index);
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public int getRemoveCustPassButtonObjLength(){
		IHtmlObject[] objs = browser.getHtmlObject(this.removeCustPassButtonProp());
		int length = objs.length;
		Browser.unregister(objs);
		return length;
	}
	
	public String getCustomerPassNumber(int index){
		IHtmlObject[] objs = browser.getTextField(this.custPassNumTextProp());
		String value = "";
		if(objs.length>index){
			value = objs[index].getProperty(".value");
		}else throw new ItemNotFoundException("Did not get any customer pass Number text object.");
		
		Browser.unregister(objs);
		return value;
	}
	
	public List<String> getCustomerPassNumbers(){
		IHtmlObject[] objs = browser.getHtmlObject(this.removeCustPassButtonProp());
		List<String> custPassNums = new ArrayList<String>();
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get any customer pass info.");
		}else {
			for (int i=0; i<objs.length;i++){
				String custPassNum = this.getCustomerPassNumber(i);
				custPassNums.add(custPassNum);
			}
		}
		
		Browser.unregister(objs);
		return custPassNums;
	}
	
	public boolean checkAddCustPassNumIsEnable(){
		return browser.checkHtmlObjectExists(this.addCustPassButtonProp());
	}
	
	public boolean checkRemoveCustPassNumIsEnable(){
		return browser.checkHtmlObjectExists(this.removeCustPassButtonProp());
	}
	
	public void verifyCustomerPassInfo(List<String> customerPassNum){
		logger.info("Verify customer pass info by customer pass number.");
		List<String> customerPassNum_UI = this.getCustomerPassNumbers();
		boolean result = MiscFunctions.compareListString("Customer pass number info", customerPassNum, customerPassNum_UI);
		if(!result){
			throw new ErrorOnPageException("Customer pass number info not correct, please check.");
		}else logger.info("Customer pass number info is correct.");
	}
	
	public String getErrorMessage(){
		return browser.getObjectText(Property.addToPropertyArray(div(), ".id", "Messages"));
	}

	private Property[] reprintPermit() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Reprint Permit");
	}
	
	public boolean isReprintPermitEnabled() {
		return browser.checkHtmlObjectEnabled(reprintPermit());
	}
	
	public void clickReprintPermit() {
		browser.clickGuiObject(reprintPermit());
	}
}
