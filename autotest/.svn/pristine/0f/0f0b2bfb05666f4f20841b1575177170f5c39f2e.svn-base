package com.activenetwork.qa.awo.pages.orms.licenseManager.common;

import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
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
 * @Date  Oct 27, 2011
 */
public abstract class LicMgrOrderDetailsCommonPage extends LicMgrOrderCommonPage {

	/**
	 * Click Fees button to goto  order fee detail page
	 */
	public void clickFees() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Fees", true);
	}
	
	/**
	 * Click Receipts button go to  order receipts detail page
	 */
	public void clickReceipts() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Receipts", true);
	}
	

	/**
	 * Click History button to goto  order history page
	 */
	public void clickHistory() {
		browser.clickGuiObject(".class", "Html.A", ".text", "History", true);
	}
	
	/**
	 * Click History button to goto  order history page
	 */
	public void clickReprint() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Reprint", true);
	}
	
	/**
	 * Method to get the current  order's all attributes' value
	 * @param attributeName
	 * @param index
	 * @return
	 */
	protected String getOrderAttributeValueByName(String attributeName, int index) {
		IHtmlObject divObjs[] = null;
		IHtmlObject textFieldObjs[] = null;
		String attributeValue = "";
		Property property[] = new Property[3];
		
		if(attributeName.equalsIgnoreCase("Receipt #") || attributeName.equalsIgnoreCase("MDWFP #")) {
			//get the DIV object which includes a Link object and a Label object according to the corresponding attribute name
			property[0] = new Property(".class", "Html.SPAN");
			property[1] = new Property(".className", "inputwithrubylabel");
			property[2] = new Property(".text", new RegularExpression("^" + attributeName, false));
			divObjs = browser.getHtmlObject(property);

			if(divObjs.length > 0){
				attributeValue = divObjs[0].getProperty(".text").split(attributeName)[1].trim();
			}
		} else {
			//get the DIV object which includes a IText and a Label object according to the corresponding attribute name
			property[0] = new Property(".class", "Html.Span"); //Lesley[20131021]: change from DIV to Span in 3.05.00
			property[1] = new Property(".className", new RegularExpression("inputwithrubylabel( readonly)?", false));
			property[2] = new Property(".text", new RegularExpression("^" + attributeName, false));
			divObjs = browser.getHtmlObject(property);

			//get the TextField object according the matched label
			textFieldObjs = browser.getTextField(new Property[]{new Property(".className", "readonly")}, divObjs[index]);
			if(textFieldObjs.length > 0) {//this logic is for privilege order details page
				attributeValue = ((IText)textFieldObjs[0]).getText().trim();
			} else {//this logic is for consumable order details page
				//String temp = divObjs[0].getProperty(".text").trim();
				//attributeValue = temp.substring(temp.indexOf(attributeName));
				attributeValue = (divObjs[0].getProperty(".text") + " ").split(attributeName)[1].trim();
			}
		}
		
		if(divObjs.length == 0) {
			throw new ItemNotFoundException("Can't find attribute according to the attribute name - " + attributeName);
		}
		
		Browser.unregister(divObjs);
		Browser.unregister(textFieldObjs);
		return attributeValue;
	}
	
	/**
	 * Overloaded method to get the current  order's all attributes' value
	 * @param attributeName
	 * @return
	 */
	protected String getOrderAttributeValueByName(String attributeName) {
		return getOrderAttributeValueByName(attributeName, 0);
	}
	
	/**
	 * Get the order number
	 * @return
	 */
	public String getOrderNum() {
		return getOrderAttributeValueByName("Order #");
	}
	
	/**
	 * Get the order verification status
	 * @return
	 */
	public String getVerificationStatus() {
		return getOrderAttributeValueByName("Verification Status");
	}
	
	/**
	 * Get the order sales location
	 * @return
	 */
	public String getSalesLocation() {
		return getOrderAttributeValueByName("Sales Location");
	}
	
	/**
	 * Get the order balance
	 * @return
	 */
	public String getBalance() {
		return getOrderAttributeValueByName("Balance");
	}
	
	/**
	 * Get the order price
	 * @return
	 */
	public String getPrice() {
		return getOrderAttributeValueByName("Price").split("\\$")[1];
	}
	/**
	 * Get the order price info
	 * @return
	 */
	public String getPriceInfo(){
		return getOrderAttributeValueByName("Price");
	}
	
	/**
	 * Get the order paid
	 * @return
	 */
	public String getPaid() {
		return getOrderAttributeValueByName("Paid");
	}
	
	/**
	 * Get the order un-issued refund
	 * @return
	 */
	public String getUnissuedRefund() {
		return getOrderAttributeValueByName("Unissued Refund");
	}
	
	/**
	 * Get the order confirmation status
	 * @return
	 */
	public String getConfirmationStatus() {
		return getOrderAttributeValueByName("Confirmation Status");
	}
	
	/**
	 * Get the order invoice number
	 * @return
	 */
	public String getInvoiceNum() {
		return getOrderAttributeValueByName("Invoice #");
	}
	
	/**
	 * Get the order receipt number
	 * @return
	 */
	public String getReceiptNum() {
		return getOrderAttributeValueByName("Receipt #");
	}
	
	/**
	 * Select a specified  receipt by receipt number.
	 * @param receiptNum
	 */
	public void clickReceiptNum(String receiptNum) {
		if(receiptNum == null || receiptNum.length() == 0) {
			throw new ItemNotFoundException("Please set the  Receipt Number.");
		}
		
		browser.clickGuiObject(".class", "Html.A", ".text", receiptNum, true);
	}
	
	/**
	 * Get the order identifier number
	 * @return
	 */
	public String getIdentifierNum() {
		return getOrderAttributeValueByName("Identifier #");
	}
	
	/**
	 * Get the order identifier verification status
	 * @return
	 */
	public String getIdentifierVerificationStatus() {
		return getOrderAttributeValueByName("Verification Status", 1);
	}
	
	/**
	 * Get the order identifier additional proof of residency
	 * @return
	 */
	public String getIdentifierAdditionalProofOfResidency() {
		return getOrderAttributeValueByName("Additional Proof of Residency");
	}
	
	/**
	 * Get the order identifier used
	 * @return
	 */
	public String getIdentifierName() {
		return getOrderAttributeValueByName("Identifier Used");
	}
	
	/**
	 * Get the order residency status
	 * @return
	 */
	public String getResidencyStatus() {
		return getOrderAttributeValueByName("Residency Status");
	}
	
	/**
	 * Get the order residency qualifier
	 * @return
	 */
	public String getResidencyQualifier() {
		return getOrderAttributeValueByName("Residency Qualifier");
	}
	
	/**
	 * Get the order residency qualifier #
	 * @return
	 */
	public String getResidencyQualifierNum() {
		return getOrderAttributeValueByName("Residency Qualifier #");
	}
	
	/**
	 * Get the order residency override
	 * @return
	 */
	public String getResidencyOverride() {
		return getOrderAttributeValueByName("Residency Override");
	}
	
	/**
	 * Click OK button
	 */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK", true);
	}
	
	private String getPriCustomerRowText() {
		return browser.getObjectText(".class", "Html.tr", ".text", new RegularExpression("^(Privilege|Licence) Customer.*Customer Class.*", false));
	}
	public String getCustomerIDType() {
		String s = this.getPriCustomerRowText();
		return StringUtil.getSubstring(s, "Customer", "Customer Class").replaceAll("\\d+", StringUtil.EMPTY).trim(); // return MDWFP # for MS contract or HAL ID # for SK contract
	}
	
	public String getCustomerID() {
		String id = this.getPriCustomerRowText();
		return RegularExpression.getMatches(id, "\\d+")[0];
	}
	
	public void verifyIdentAndResidentInfo(String residStatus, String identTypeName, String identNum, boolean isOverride) {
		boolean result = true;
		String usedIdentType = StringUtil.isEmpty(identTypeName) ? this.getCustomerIDType() : identTypeName;
		result &= MiscFunctions.compareString("Used Identifier Name", usedIdentType, this.getIdentifierName());
		String usedIdentNum = StringUtil.isEmpty(identNum) ? this.getCustomerID() : identNum;
		result &= MiscFunctions.compareString("Identifier Number", usedIdentNum, this.getIdentifierNum());
		result &= MiscFunctions.compareString("Residency status", residStatus, this.getResidencyStatus());
		result &= MiscFunctions.compareString("Residency Qualifier", identTypeName, this.getResidencyQualifier());
		result &= MiscFunctions.compareString("Residency Qualifier Number", identNum, this.getResidencyQualifierNum());
		result &= MiscFunctions.compareString("Residency Override", (isOverride ? "Yes" : "No"), this.getResidencyOverride());
		if (!result) {
			throw new ErrorOnPageException("The identifier and residency info is wrong in order details page.");
		}
		logger.info("--Verify identifier and residency info correctly in order details page.");
	}
}
