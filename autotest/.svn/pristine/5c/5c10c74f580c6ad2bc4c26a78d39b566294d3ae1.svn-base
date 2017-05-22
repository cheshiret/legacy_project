/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.posAssignment;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Jane Wang
 * @Date  Jun 19, 2012
 */
public class FinMgrPosProductSetupDetailsPage extends FinanceManagerPage {

	static private FinMgrPosProductSetupDetailsPage _instance = null;
	
	static public FinMgrPosProductSetupDetailsPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrPosProductSetupDetailsPage();
		}

		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", //".id",
				new RegularExpression("^POS Product.*", false));
	}

	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public void setUnitPrice(String unitPrice){
		browser.setTextField(".id", "POSProductSetupView.unitPriceStr", unitPrice);
	}
	
	public void selectPartialQuantityAllowed(String quantityAllowed){
		browser.selectDropdownList(".id", "POSProductSetupView.partialQuantityAllowed", quantityAllowed);
	}
	
	public void selectVariablePriceAllowed(String variablePriceAllowed){
		browser.selectDropdownList(".id", "POSProductSetupView.variablePrice", variablePriceAllowed);
	}
	
	public void setEffectiveSalesStartDate(String startDate){
		browser.setTextField(".id", "POSProductSetupView.effectiveStartDate_ForDisplay", startDate);
		browser.clickGuiObject(".class", "Html.TD", ".text", "Effective Sales Start Date");
	}
	
	public void setEffetiveSalesEndDate(String endDate){
		browser.setTextField(".id", "POSProductSetupView.effectiveEndDate_ForDisplay", endDate);
		browser.clickGuiObject(".class", "Html.TD", ".text", "Effective Sales End Date");
	}
	
	/**
	 *  Get POS Info in the details page
	 */
	public String getProductID() {
		return browser.getTextFieldValue(".id", "POSProductSetupView.productID");
	}
	
	public String getProductName() {
		return browser.getTextFieldValue(".id", "POSProductSetupView.name");
	}
	
	public String getProductDesc() {
		return browser.getTextFieldValue(".id", "POSProductSetupView.description");
	}
	
	public String getProductGroup() {
		return browser.getTextFieldValue(".id", "POSProductSetupView.productGroup");
	}
	
	public String getProductUnitPrice() {
		return browser.getTextFieldValue(".id", "POSProductSetupView.unitPriceStr");
	}
	
	public String getVarPriceAllowedValue() {
		return browser.getDropdownListValue(".id", "POSProductSetupView.variablePrice");
	}
	
	public String getPartQuantityAllowedValue() {
		return browser.getDropdownListValue(".id", "POSProductSetupView.partialQuantityAllowed");
	}
	
	public String getEffectiveStartDate() {
		return browser.getTextFieldValue(".id", "POSProductSetupView.effectiveStartDate_ForDisplay");
	}
	
	public String getEffectiveEndDate() {
		return browser.getTextFieldValue(".id", "POSProductSetupView.effectiveEndDate_ForDisplay");
	}
	
	/**
	 * Get All POS Info in the details page
	 */
	public POSInfo getPOSDetailsInfo() {
		POSInfo pos = new POSInfo();
		
		pos.productID = this.getProductID();
		pos.product = this.getProductName();
		pos.productDescription = this.getProductDesc();
		pos.productGroup = this.getProductGroup();
		pos.unitPrice = this.getProductUnitPrice();
		pos.variablePriceAllowed = this.getVarPriceAllowedValue();
		pos.partialQuantityAllowed = this.getPartQuantityAllowedValue();
		pos.effectiveSalesStartDate = this.getEffectiveStartDate();
		pos.effectiveSalesEndDate = this.getEffectiveEndDate();
		
		return pos;
	}
	
	public boolean comparePOSInfo(POSInfo pos) {
		POSInfo actPOS = this.getPOSDetailsInfo();
		boolean result = true;
		if (!StringUtil.isEmpty(pos.productID)) {
			result &= MiscFunctions.compareResult("Product ID", pos.productID, actPOS.productID);
		}
		if (!StringUtil.isEmpty(pos.product)) {
			result &= MiscFunctions.compareResult("Product Name", pos.product, actPOS.product);
		}
		if (!StringUtil.isEmpty(pos.productDescription)) {
			result &= MiscFunctions.compareResult("Product Description", pos.productDescription, actPOS.productDescription);
		}
		if (!StringUtil.isEmpty(pos.productGroup)) {
			result &= MiscFunctions.compareResult("Product Group", pos.productGroup, actPOS.productGroup);
		}
		if (!StringUtil.isEmpty(pos.unitPrice)) {
			result &= MiscFunctions.compareResult("Unit Price", pos.unitPrice, actPOS.unitPrice);
		}
		if (!StringUtil.isEmpty(pos.variablePriceAllowed)) {
			result &= MiscFunctions.compareResult("Variable Price Allowed", pos.variablePriceAllowed, 
					actPOS.variablePriceAllowed);
		}
		if (!StringUtil.isEmpty(pos.partialQuantityAllowed)) {
			result &= MiscFunctions.compareResult("Partial Quantity Allowed", pos.partialQuantityAllowed, 
					actPOS.partialQuantityAllowed);
		}
		if (!StringUtil.isEmpty(pos.effectiveSalesStartDate)) {
			result &= compareDateWithFormat("Effective Start Date", pos.effectiveSalesStartDate, 
					actPOS.effectiveSalesStartDate);
		}
		if (!StringUtil.isEmpty(pos.effectiveSalesEndDate)) {
			result &= compareDateWithFormat("Effective End Date", pos.effectiveSalesEndDate, 
					actPOS.effectiveSalesEndDate);
		}
		return result;
	}
	
	public void verifyPOSInfo(POSInfo pos) {
		boolean result = this.comparePOSInfo(pos);
		if (!result) {
			throw new ErrorOnPageException("POS Info is NOT correct! Please check logger error!");
		}
		logger.info("The POS Info is correct!");
	}
	
	public void setPOSInfo(POSInfo pos) {
		logger.info("Setup POS info in details page...");
		this.selectVariablePriceAllowed(pos.variablePriceAllowed);
		this.selectPartialQuantityAllowed(pos.partialQuantityAllowed);
		this.setEffectiveSalesStartDate(pos.effectiveSalesStartDate);
		this.setEffetiveSalesEndDate(pos.effectiveSalesEndDate);
		if(!this.unitPriceIsReadOnly()){
			this.setUnitPrice(pos.unitPrice);
		}
	}
	
	private boolean compareDateWithFormat(String meg, String expDate, String actDate) {
		expDate = DateFunctions.formatDate(expDate, "EEE MMM d yyyy");
		boolean result = expDate.equalsIgnoreCase(actDate);
		if (!result) {
			logger.error(meg + " is wrong! The expected is " + expDate + ", but the actual is " + actDate);
		} else {
			logger.info(meg + " is correct!");
		}
		return result;
	}
	
	public boolean unitPriceIsReadOnly() {
		Boolean readOnly;
		IHtmlObject[]  objs = browser.getTextField(".id", "POSProductSetupView.unitPriceStr");
		if(objs.length<1){
			throw new ObjectNotFoundException("The object with id: ('POSProductSetupView.unitPriceStr') can't be found.");
		}
		readOnly = objs[0].getProperty(".className").trim().equals("readonly") ? true : false;
		Browser.unregister(objs);
		return readOnly;
	}
	
	public boolean isReadOnly(String label)
	{
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.LABEL", ".text", label);
		IHtmlObject div = (IHtmlObject)objs[0].getParent();
		IHtmlObject[] inputs = browser.getHtmlObject(".class", "Html.INPUT.text",div);
		
		boolean readOnly =inputs[0].getProperty(".className").trim().equals("readonly") ? true : false;
		Browser.unregister(objs);
		Browser.unregister(inputs);
		return readOnly;
		
	}
}
