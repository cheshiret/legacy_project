package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrInvalidateReactivatePrivilegeOrderConfirmationWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCustomerMgrTabPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author ssong
 * @date Feb 11, 2011
 */
public class LicMgrPrivilegeItemDetailPage extends LicMgrCustomerMgrTabPage{

	private static LicMgrPrivilegeItemDetailPage _instance = null;
	
	protected LicMgrPrivilegeItemDetailPage() {};
	
	public static LicMgrPrivilegeItemDetailPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrPrivilegeItemDetailPage();
		}
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "pidetail");
	}
	
	public void clickInvalidate(){
		browser.clickGuiObject(".class","Html.A",".text","Invalidate",true);
		ajax.waitLoading();
	}
	
	public void clickTransfer(){
		browser.clickGuiObject(".class","Html.A",".text","Transfer",true);
	}
	
	public void clickReactivate(){
		browser.clickGuiObject(".class","Html.A",".text","Reactivate",true);
		ajax.waitLoading();
	}
	
	public void clickOrder(String ordNum){
		browser.clickGuiObject(".class","Html.A",".text",ordNum);
	}
	/**
	 * Get the privilege item status - Active/Invalid
	 * @return
	 */
	public String getPrivilegeStatus() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id", new RegularExpression("PrivilegeInstanceDetailData-\\d+\\.statusName", false));
		
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find privilege status in this page.");
		}
		String status = objs[0].getProperty(".text").split("Status")[1].trim();
		
		Browser.unregister(objs);
		return status;
	}
	
	/**
	 * Get the error message after operating some un-allowed actions
	 * @return
	 */
	public String getErrorMsg() {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "_page_messages");
		String errorMsg = "";
		if(objs.length > 0) {
			errorMsg = ((IHtmlTable)objs[0]).getProperty(".text").trim();
		}
		
		return errorMsg;
	}
	
	/**
	 * Click 'Invalidate' button to invalidate the privilege in privilege detail page
	 * @param reason
	 * @param note
	 */
	public void invalidatePrivilege(String reason, String note) {
		this.operatePrivilegeItem("Invalidate", reason, note);
	}
	
	/**
	 * Click 'Reactivate' button to reactivate the privilege in privilege item detail page
	 * @param reason
	 * @param note
	 */
	public void reactivatePrivilege(String reason, String note) {
		this.operatePrivilegeItem("Reactivate", reason, note);
	}
	
	/**
	 * Click 'Transfer' button to transfer the privilege in privilege item detail page
	 * @param reason
	 * @param note
	 */
	public void transferPrivilege(String reason, String note) {
		this.operatePrivilegeItem("Transfer", reason, note);
	}
	
	public void clickMDWFPNumber() {
		RegularExpression pattern=new RegularExpression("PrivilegeInstanceDetailData-\\d+\\.customerNumber",false);
		browser.clickGuiObject(".class","Html.A",".name",pattern);
	}
	
	/**
	 * Operation privilege item, including Invalidate/Reactivate/Transfer, etc.
	 * @param operation
	 * @param reason
	 * @param note
	 */
	private void operatePrivilegeItem(String operation, String reason, String note) {
		LicMgrInvalidateReactivatePrivilegeOrderConfirmationWidget widget = LicMgrInvalidateReactivatePrivilegeOrderConfirmationWidget.getInstance();
		
		logger.info(operation + " privilege.");
		if(operation.equalsIgnoreCase("Invalidate")) {
			this.clickInvalidate();
		} else if(operation.equalsIgnoreCase("Reactivate")) {
			this.clickReactivate();
		} else if(operation.equalsIgnoreCase("Transfer")) {
			this.clickTransfer();
		} else {
			throw new ActionFailedException("The operation is wrong.");
		}
		ajax.waitLoading();
		widget.waitLoading();
		widget.setInfo(reason, note);
		
		this.waitLoading();
	}
	
	/**
	 * Get privilege attribute value
	 * @param attributeName-- the attribute name
	 * @return the input text value.
	 */
	private String getPrivilegeAttributeValueByName(String attributeName){
		String attributeValue = "";
		Property property[] = new Property[3];
		IHtmlObject divObjs[] = null;
		
		property[0] = new Property(".class", "Html.DIV");
		property[1] = new Property(".className", "inputwithrubylabel");
		property[2] = new Property(".text", new RegularExpression("^" + attributeName, false));
		divObjs = browser.getHtmlObject(property);

		if(divObjs.length > 0){
			if(divObjs[0].getProperty(".text").length()>attributeName.length()){
				attributeValue = divObjs[0].getProperty(".text").split(attributeName)[1].trim();
			}else{
				attributeValue = "";
			}
			
		}
		return attributeValue;
	}
	
	private String prefix = "PrivilegeInstanceDetailData-\\d+\\.";
	
	/**
	 * get privilege number
	 * @return String - privilege number
	 */
	public String getPrivilegeNumber(){
		return browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression(prefix+"privilegeNumber", false)).replaceAll("Privilege Number", StringUtil.EMPTY);
	}
	
	/**
	 * get privilege status
	 * @return String - privilege status.
	 */
	public String getStatus(){
		return browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression(prefix+"statusName", false)).replaceAll("Status", StringUtil.EMPTY);
	}
	
	/**
	 * get privilege product
	 * @return String - privilege product.
	 */
	public String getProduct(){
		return browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression(prefix+"privilegeProductDisplayName", false)).replaceAll("Product", StringUtil.EMPTY);
	}
	
	/**
	 * get privilege license year.
	 * @return String - privilege product.
	 */
	public String getLicenseYear(){
		return browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression(prefix+"licenseYear", false)).replaceAll("License Year", StringUtil.EMPTY);
	}
	
	/**
	 * get privilege valid from date.
	 * @return String - privilege valid from date.
	 */
	public String getValidFromDate(){
		return browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression(prefix+"validFrom:DATE_TIME2", false)).replaceAll("Valid From Date/Time", StringUtil.EMPTY);
	}
	
	/**
	 * get privilege valid to date.
	 * @return String - privilege valid to date.
	 */
	public String getValidToDate(){
		return browser.getObjectText(".class", "Html.SPAN", ".id", 
			new RegularExpression(prefix+"validTo:DATE_TIME2", false)).replaceAll("Valid To Date/Time", StringUtil.EMPTY);
	}
	
	/**
	 * get privilege creation price.
	 * @return String - privilege creation price.
	 */
	public String getCreationPrice(){
		return browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression(prefix+"priceWhenCreated:CURRENCY", false)).replaceAll("Creation Price", StringUtil.EMPTY);
	}
	
	/**
	 * get privilege number of duplicates.
	 * @return String - privilege number of duplicates.
	 */
	public String getNumOfDuplicates(){
		return browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression(prefix+"numberOfDuplicateTransactions", false)).replaceAll("# of Duplicates", StringUtil.EMPTY);
	}
	
	/**
	 * get privilege number of reprints.
	 * @return String - privilege number of reprints.
	 */
	public String getNumOfReprints(){
		return browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression(prefix+"numberOfReprintTransactions", false)).replaceAll("# of Reprints", StringUtil.EMPTY);
	}
	
	/**
	 * get privilege inventory number.
	 * @return String - privilege inventory number.
	 */
	public String getInventoryNum(){
		return this.getPrivilegeAttributeValueByName("Inventory Number");
	}
	
	/**
	 * get privilege MDWFP number.
	 * @return String - privilege MDWFP number.
	 */
	public String getMDWFPNum(){
		return browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression(prefix+"customerNumber", false)).replaceAll("MDWFP #", StringUtil.EMPTY);
	}
	
	/**
	 * get privilege customer class.
	 * @return String - privilege MDWFP number.
	 */
	public String getCustomerClass(){
		return browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression(prefix+"customerClass", false)).replaceAll("Customer Class", StringUtil.EMPTY);
	}
	
	/**
	 * get privilege customer name.
	 * @return String - privilege customer name.
	 */
	public String getCustomerName(){
		return browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression(prefix+"customerNameLastFirst", false)).replaceAll("Customer Name", StringUtil.EMPTY);
	}
	
	/**
	 * get privilege business name.
	 * @return String - privilege business name.
	 */
	public String getBusniessName(){
		return browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression(prefix+"customerBusinessName", false)).replaceAll("Business Name", StringUtil.EMPTY);
	}
	
	/**
	 * get privilege phone number.
	 * @return String - privilege phone number.
	 */
	public String getPhoneNum(){
		return browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression(prefix+"customerPhone", false)).replaceAll("Phone Number", StringUtil.EMPTY);
	}
	
	/**
	 * get privilege email address.
	 * @return String - privilege email address.
	 */
	public String getEmailAddress(){
		return browser.getObjectText(".class", "Html.SPAN", ".id", 
				new RegularExpression(prefix+"customerEmail", false)).replaceAll("Email Address", StringUtil.EMPTY);
	}
	
	/**
	 * Compare privilege detail info and privilege customer info.
	 * @return boolean - whether the info correct or not.
	 */
	public boolean comparePrivilegeDetailsInfo(PrivilegeInfo expectedPriInfo, Customer priCustomer){
		boolean pass = true;
		String temp = this.getPrivilegeNumber();
		if(!expectedPriInfo.privilegeId.equals(temp)){
			pass &= false;
			logger.error("Expected privilege number is "+expectedPriInfo.privilegeId+" but actual value is "+temp+"");
		}
		temp = this.getStatus();
		if(!expectedPriInfo.status.equals(temp)){
			pass &= false;
			logger.error("Expected privilege status is "+expectedPriInfo.status+" but actual value is "+temp+"");
		}
		temp = this.getProduct().replace(" ", "");
		if(!expectedPriInfo.purchasingName.equals(temp)){
			pass &= false;
			logger.error("Expected privilege product is "+expectedPriInfo.purchasingName+" but actual value is "+temp+"");
		}
		temp = this.getLicenseYear();
		if(!expectedPriInfo.licenseYear.equals(temp)){
			pass &= false;
			logger.error("Expected privilege license year is "+expectedPriInfo.licenseYear+" but actual value is "+temp+"");
		}
		temp = this.getCreationPrice();
		if(!expectedPriInfo.creationPrice.equals(temp)){
			pass &= false;
			logger.error("Expected privilege creation price is "+expectedPriInfo.creationPrice+" but actual value is "+temp+"");
		}
		temp = this.getNumOfDuplicates();
		if(!expectedPriInfo.numOfDuplicates.equals(temp)){
			pass &= false;
			logger.error("Expected privilege number of deplicates is "+expectedPriInfo.numOfDuplicates+" but actual value is "+temp+"");
		}
		temp = this.getMDWFPNum();
		if(!priCustomer.identifier.identifierNum.equals(temp)){
			pass &= false;
			logger.error("Expected privilege customer identirier number is "+priCustomer.identifier.identifierNum+" but actual value is "+temp+"");
		}
		temp = this.getCustomerClass();
		if(!priCustomer.customerClass.equals(temp)){
			pass &= false;
			logger.error("Expected privilege customer class is "+priCustomer.customerClass+" but actual value is "+temp+"");
		}
		temp = this.getCustomerName();
		String name = ""+priCustomer.lName+", "+priCustomer.fName+"";
		if(!name.equals(temp)){
			pass &= false;
			logger.error("Expected privilege customer name is "+name+" but actual value is "+temp+"");
		}
		temp = this.getPhoneNum();
		if(!priCustomer.phoneContact.equals(temp)){
			pass &= false;
			logger.error("Expected privilege phone contact is "+priCustomer.phoneContact+" but actual value is "+temp+"");
		}
		temp = this.getEmailAddress();
		if(!priCustomer.email.equals(temp)){
			pass &= false;
			logger.error("Expected privilege email is "+priCustomer.email+" but actual value is "+temp+"");
		}
		return pass;
	}
	
	/**
	 * Get privilege history info identified by transaction name
	 * @param transactionName - Original Purchase, Expire Privilege, etc.
	 * @return
	 */
	public List<String> getPrivilegeHistory(String transactionName) {
		IHtmlObject objs[] = browser.getTableTestObject(".text", new RegularExpression("^Date & Time", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find privilege history table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		int colIndex = table.findColumn(0, "Transaction");
		int rowIndex = table.findRow(colIndex, transactionName);
		List<String> values = table.getRowValues(rowIndex);
		
		Browser.unregister(objs);
		return values;
	}
	
	public void verifyPrivilegeStatus(String expectStatus){
		String actualStatus = this.getStatus();
		if(!MiscFunctions.compareResult("Status", expectStatus, actualStatus)){
			throw new ErrorOnPageException("Status isn't correct!!");
		}
	}
	
}
