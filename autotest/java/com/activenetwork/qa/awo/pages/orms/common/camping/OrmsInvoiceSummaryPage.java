package com.activenetwork.qa.awo.pages.orms.common.camping;


import com.activenetwork.qa.awo.datacollection.legacy.orms.InvoiceInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class OrmsInvoiceSummaryPage extends OrmsPage {
	static private OrmsInvoiceSummaryPage _instance = null;

	protected OrmsInvoiceSummaryPage() {
	}

	static public OrmsInvoiceSummaryPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsInvoiceSummaryPage();
		}

		return _instance;
	}
	
	protected Property[] backButtonProp(){
		return Property.concatPropertyArray(a(), ".text","Back");
	}
	
	@SuppressWarnings("unused")
	private Property[] invoiceNum() {
		return Property.toPropertyArray(".id", "BillingInvoiceView.invoiceNumber");
	}
	
	private Property[] invoiceCreatedDateTime() {
		return Property.toPropertyArray(".id", "BillingInvoiceView.invoiceDate:LOCAL_TIME");
	}
	
	private Property[] createdBy() {
		return Property.toPropertyArray(".id", "BillingInvoiceView.createdBy");
	}
	
	private Property[] customerName() {
		return Property.toPropertyArray(".class", "Html.A", ".href", new RegularExpression("CustomerDetails\\.do", false));
	}
	
	private Property[] customerPhone() {
		return Property.toPropertyArray(".id", "BillingInvoiceView.customerPhone");
	}
	
	private Property[] customerEmail() {
		return Property.toPropertyArray(".id", "BillingInvoiceView.customerEmail");
	}
	
	private Property[] customerOrganizationName() {
		return Property.toPropertyArray(".id", "BillingInvoiceView.customerOrganization");
	}
	
	private Property[] orderNum(String num) {
		return Property.toPropertyArray(".class", "Html.A", ".text", num);
	}
	
//	public String getInvoiceNum() {
//		return browser.getObjectText(invoiceNum());
//	}
	
	public String getInvoiceCreatedDateTime() {
		return browser.getTextFieldValue(invoiceCreatedDateTime());
	}
	
	public String getCreatedBy() {
		return browser.getTextFieldValue(createdBy());
	}
	
	public String getCustomerName() {
		return browser.getObjectText(customerName());
	}
	
	public String getCustomerPhone() {
		return browser.getTextFieldValue(customerPhone());
	}
	
	public String getCustomerEmail() {
		return browser.getTextFieldValue(customerEmail());
	}
	
	public String getCustomerOrganizationName() {
		return browser.getTextFieldValue(customerOrganizationName());
	}
	
	/** Determine if the page object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression("Invoice Details.*",false));
	}
	
	public void clickCustomer(){
		browser.clickGuiObject(".class","Html.A",".href", new RegularExpression("javascript:invokeActionTarget\\( \"CallMgrCustomerDetails\\.do\".*",false));
	}
	
	/**Get the Invoice detail cell values*/
	public String getInvoiceDetailCellValue(int row, int col){
		IHtmlObject [] objs = browser.getHtmlObject(".class", "Html.TABLE",".text", new RegularExpression("^Invoice Detail.*", true));
		IHtmlTable table = (IHtmlTable) objs[0];
		
		String toReturn = table.getCellValue(row, col).toString();
		Browser.unregister(objs);
		return toReturn;
	}
	
	public boolean checkInvoiceNum(){
		return browser.checkHtmlObjectExists(".id", "BillingInvoiceView.invoiceNumber");
	}
	
	/**Get the invoice number*/
	public String getInvoiceNum(){
		String invoiceNum = "";
		if(this.checkInvoiceNum()){
			IHtmlObject [] objs = browser.getHtmlObject(".id", "BillingInvoiceView.invoiceNumber");
			if(objs.length>0){
				invoiceNum = objs[0].getProperty(".value");
			}else throw new ObjectNotFoundException("InvoiceNum object can't find.");
		}else{
			invoiceNum = this.getInvoiceDetailCellValue(0, 1).split("#")[1].trim().split("Invoice")[0].trim().toString();
		}
		return invoiceNum;
	}
	
	/**Get the order list cell value*/
	public String getOrderListCellValue(int row, int col){
		IHtmlObject [] objs = browser.getHtmlObject(".class", "Html.TABLE",".text", new RegularExpression("^Order Order.*", true));
		IHtmlTable table = (IHtmlTable) objs[0];
		
		String toReturn = table.getCellValue(row, col).toString();
		Browser.unregister(objs);
		return toReturn;
	}
	
	/**
	 * Click the "Make Payment" button
	 */
	public void clickMakePayment(){
		browser.clickGuiObject(".class", "Html.A",".text","Make Payment", true);
	}
			
	/**
	 * select the first check box which id is "order"
	 */
	public void selectFirstCheckBox(){
		IHtmlObject [] objs = browser.getHtmlObject(".class", "Html.TABLE",".text", new RegularExpression("^Order Order.*", true));
		IHtmlTable table = (IHtmlTable) objs[0];
		
		browser.selectCheckBox(".id",  new RegularExpression("order",false),0,true,table);
		Browser.unregister(objs);
	}
	
	public void clickBack(){
		browser.clickGuiObject(this.backButtonProp());
	}
	
	public boolean isOrderNumExists(String num) {
		return browser.checkHtmlObjectExists(orderNum(num));
	}
	
	public void verifyOrderNumDisplayedAsLink(String num) {
		if(!isOrderNumExists(num)) throw new ErrorOnPageException("Order num shall be displayed as link.");
	}
	
	public void clickOrderNum(String num) {
		browser.clickGuiObject(orderNum(num));
	}
	
	public InvoiceInfo getInvoiceInfo() {
		InvoiceInfo invoice = new InvoiceInfo();
		
		invoice.invoiceNum = getInvoiceNum();
		invoice.createdDateTime = getInvoiceCreatedDateTime();
		invoice.createdDateTime = RegularExpression.getMatches(invoice.createdDateTime, "[a-zA-Z]{3} [a-zA-Z]{3} [0-9]{1,2} [0-9]{4}")[0];
		invoice.createdBy = getCreatedBy();
		String fullName = getCustomerName();
		String names[] = fullName.split(",");
		invoice.lastName = names[0].trim();
		invoice.firstName = names[1].trim();
		invoice.phone = getCustomerPhone();
		invoice.email = getCustomerEmail();
		invoice.organizationName = getCustomerOrganizationName();
		
		return invoice;
	}
	
	public boolean compareInvoiceInfo(InvoiceInfo expected) {
		InvoiceInfo actual = getInvoiceInfo();
		boolean result = true;
		
		result &= MiscFunctions.compareResult("Invoice Num", expected.invoiceNum, actual.invoiceNum);
		result &= MiscFunctions.compareResult("Invoice Created Date & Time", expected.createdDateTime, actual.createdDateTime);
		result &= MiscFunctions.compareResult("Created By", expected.createdBy.replace(", ", ","), actual.createdBy.replace(", ", ","));
		result &= MiscFunctions.compareResult("Customer Last Name", expected.lastName.toUpperCase(), actual.lastName.toUpperCase());
		result &= MiscFunctions.compareResult("Customer First Name", expected.firstName.toUpperCase(), actual.firstName.toUpperCase());
		result &= MiscFunctions.compareResult("Customer Phone", expected.phone, actual.phone);
		result &= MiscFunctions.compareResult("Customer Email", expected.email, actual.email);
		result &= MiscFunctions.compareResult("Customer Organization Name", expected.organizationName, actual.organizationName);
		
		return result;
	}
	
	public void verifyInvoiceInfo(InvoiceInfo expected) {
		if(!compareInvoiceInfo(expected))
			throw new ErrorOnPageException("Invoice info is NOT correct.");
	}
}
