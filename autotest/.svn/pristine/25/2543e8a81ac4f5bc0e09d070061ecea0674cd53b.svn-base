package com.activenetwork.qa.awo.pages.orms.common.pos.inventorymgt;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo.BarCode;
import com.activenetwork.qa.awo.pages.orms.common.pos.OrmsPOSCommonPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.DateFunctions;
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
 * @Date  Oct 8, 2012
 */
public class OrmsPOSProductSetupDetailsPage extends OrmsPOSCommonPage {
	
	private static OrmsPOSProductSetupDetailsPage _instance = null;

	private OrmsPOSProductSetupDetailsPage(){}

	public static OrmsPOSProductSetupDetailsPage getInstance() {
		if (null == _instance) {
			_instance = new OrmsPOSProductSetupDetailsPage();
		}
		return _instance;
	}
	
	public boolean exists() {
//		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("^Unit Price.*", false));
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "pos-prd-det-tab");
	}
	
	/**
	 * set unit price.
	 * @param unitPrice - value of unit price.
	 */
	public void setUnitPrice(String unitPrice){
//		browser.setTextField(".id", "POSProductSetupView.unitPrice", unitPrice);
		browser.setTextField(".id", "POSProductSetupView.unitPriceStr", unitPrice);
	}
	
	/**
	 * select variable price allowed.
	 * @param variablePriceAllowed -  variable price Allowed.
	 */
	public void selectVariablePriceAllowed(String variablePriceAllowed){
		browser.selectDropdownList(".id", "POSProductSetupView.variablePrice", variablePriceAllowed);
	}
	
	/**
	 * select partial quantity allowed.
	 * @param quantityAllowed
	 */
	public void selectPartialQuantityAllowed(String quantityAllowed){
		browser.selectDropdownList(".id", "POSProductSetupView.partialQuantityAllowed", quantityAllowed);
	}
	
	/**
	 * set display order.
	 * @param displayOrder
	 */
	public void setDisPlayOrder(String displayOrder){
		browser.setTextField(".id", "POSProductSetupView.displayOrder", displayOrder);
	}
	
	/**
	 * set effective sales start Date.
	 * @param startDate
	 */
	public void setEffectiveSalesStartDate(String startDate){
		browser.setTextField(".id", "POSProductSetupView.effectiveStartDate_ForDisplay", startDate);
	}
	
	/**
	 * set effective sales end date.
	 * @param endDate
	 */
	public void setEffetiveSalesEndDate(String endDate){
		browser.setTextField(".id", "POSProductSetupView.effectiveEndDate_ForDisplay", endDate);
	}
	
	/**
	 * get pos product id.
	 * @return
	 */
	public String getProductId(){
		return browser.getTextFieldValue(".id", "POSProductSetupView.productID");
	}
	
	public String getUnitPrice(){
		return browser.getTextFieldValue(".id", "POSProductSetupView.unitPriceStr");
	}
	
	public void setPosProductInfo(POSInfo posInfo){
		this.setUnitPrice(posInfo.unitPrice);
		this.selectVariablePriceAllowed(posInfo.variablePriceAllowed);
		this.selectPartialQuantityAllowed(posInfo.partialQuantityAllowed);
		this.setDisPlayOrder(posInfo.displayOrder);
		this.setEffectiveSalesStartDate(posInfo.effectiveSalesStartDate);
		this.setEffetiveSalesEndDate(posInfo.effectiveSalesEndDate);
	}
	
	 /**
	   * set bar Code
	   * @param barCode - the var code value.
	   */
	  public void setBarCode(String barCode,int index){
		  browser.setTextField(".id", "barcode", barCode,index);
	  }
	  
	  /**
	   * click Add button.
	   */
	  public void clickAddButton(){
		  browser.clickGuiObject(".class", ".Html.A", ".text", "Add");
	  }
	  
	  /*
	   * click ok button.
	   */
	  public void clickOK(){
		  browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	  }
	  
	  /*
	   * click cancel button.
	   */
	  public void clickCancel(){
		  browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	  }
	  
	  /*
	   * click remove button.
	   */
	  public void clickRemoveButton(int index){
		  browser.clickGuiObject(".class", "Html.A", ".text", "Remove",index);
	  }
	  
	  /**
	   * set bar code info for pos
	   * @param barcodeList -  the list of barcode.
	   */
	  public void setBarCode(ArrayList<BarCode> barcodeList){
		  for(int i=0;i<barcodeList.size();i++){
			  this.clickAddButton();
			  this.setBarCode(barcodeList.get(i).barCode,i);
			  if(barcodeList.get(i).isRemove){
				  this.clickRemoveButton(i);
			  }
		  }
	  }
	  
	/**
	 * Set up pos details info
	 * 
	 * @param pos
	 */
	public void setupPOSDetails(POSInfo pos) {
		if (!StringUtil.isEmpty(pos.unitPrice))
			setUnitPrice(pos.unitPrice);
		if (!StringUtil.isEmpty(pos.variablePriceAllowed))
			selectVariablePriceAllowed(pos.variablePriceAllowed);
		if (!StringUtil.isEmpty(pos.partialQuantityAllowed))
			selectPartialQuantityAllowed(pos.partialQuantityAllowed);
		if (!StringUtil.isEmpty(pos.displayOrder))
			setDisPlayOrder(pos.displayOrder);
		if (!StringUtil.isEmpty(pos.effectiveSalesStartDate))
			setEffectiveSalesStartDate(pos.effectiveSalesStartDate);
		if (pos.effectiveSalesEndDate != null)
			setEffetiveSalesEndDate(pos.effectiveSalesEndDate);
	}

	/**
	 *  Get POS Info in the details page
	 */
	public String getProductName() {
		return browser.getTextFieldValue(".id", "POSProductSetupView.name");
	}
	
	public String getProductDesc() {
		return browser.getTextFieldValue(".id", "POSProductSetupView.description");
	}
	
	public String getProductGroup() {
		return browser.getTextFieldValue(".id", "POSProductSetupView.productGroup");
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
	
	public String getBarcode(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^Barcode.*",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get any barcode div obj");
		}
		
		IHtmlObject[] textObj = browser.getHtmlObject(".class", "Html.INPUT.text", objs[objs.length-1]);
		if(textObj.length<1){
			throw new ItemNotFoundException("Did not get any text object.");
		}
		
		String text = textObj[textObj.length-1].getProperty(".value");
		Browser.unregister(objs);
		Browser.unregister(textObj);
		return text;
		
	}
	
	/**
	 * Get All POS Info in the details page
	 */
	public POSInfo getPOSDetailsInfo() {
		POSInfo pos = new POSInfo();
		
		pos.productID = this.getProductId();
		pos.product = this.getProductName();
		pos.productDescription = this.getProductDesc();
		pos.productGroup = this.getProductGroup();
		if (this.isUnitPriceTextFieldExist() && this.isUnitPriceTextFieldEnabled()) {
			pos.unitPrice = this.getUnitPrice();
		}
		pos.variablePriceAllowed = this.getVarPriceAllowedValue();
		pos.partialQuantityAllowed = this.getPartQuantityAllowedValue();
		if (this.isEffStartDateTextFieldExist()) {
			pos.effectiveSalesStartDate = this.getEffectiveStartDate();
		}
		if (this.isEffEndDateTextFieldExist()) {
			pos.effectiveSalesEndDate = this.getEffectiveEndDate();
		}
		
		return pos;
	}
	
	public boolean comparePOSInfo(POSInfo pos) {
		POSInfo actPOS = this.getPOSDetailsInfo();
		boolean result = true;
		logger.info("Compare two POS product setup Info...");
		result &= MiscFunctions.compareResult("Product ID", pos.productID, actPOS.productID);
		result &= MiscFunctions.compareResult("Product Name", pos.product, actPOS.product);
		result &= MiscFunctions.compareResult("Product Description", pos.productDescription, actPOS.productDescription);
		result &= MiscFunctions.compareResult("Product Group", pos.productGroup, actPOS.productGroup);
		result &= MiscFunctions.compareResult("Unit Price", pos.unitPrice, actPOS.unitPrice);
		result &= MiscFunctions.compareResult("Variable Price Allowed", pos.variablePriceAllowed, 
					actPOS.variablePriceAllowed);
		result &= MiscFunctions.compareResult("Partial Quantity Allowed", pos.partialQuantityAllowed, 
					actPOS.partialQuantityAllowed);
		result &= this.verifyDateTextFeildFormatValue(pos.effectiveSalesStartDate, 
					actPOS.effectiveSalesStartDate, "Effective Sales Start Date");
		result &= this.verifyDateTextFeildFormatValue(pos.effectiveSalesEndDate, 
					actPOS.effectiveSalesEndDate, "Effective Sales End Date");
		return result;
	}
	/**
	 * Verify POS Info
	 * @param pos
	 */
	public void verifyPOSInfo(POSInfo pos) {
		boolean result = this.comparePOSInfo(pos);
		if (!result) {
			throw new ErrorOnPageException("POS Info is NOT correct! Please check logger error!");
		}
		logger.info("The POS Info is correct!");
	}
	
	public boolean isUnitPriceTextFieldExist() {
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.text", 
				".id", "POSProductSetupView.unitPriceStr");
	}
	
	public void verifyUnitPriceTextFieldExist(boolean exp) {
		boolean act = this.isUnitPriceTextFieldExist();
		this.compareResults("The display of the Unit Price Testfield", exp, act);
	}
	/**
	 * Check if Unit Price text field enabled
	 */
	public boolean isUnitPriceTextFieldEnabled() {
		IHtmlObject[] objs = browser.getTextField(".id", "POSProductSetupView.unitPriceStr");
		boolean isEnabled = false;
		if (objs != null && objs.length > 0) {
			isEnabled = objs[0].isEnabled();
		} else {
			throw new ErrorOnPageException("The unit price text field doesn't exist!");
		}
		Browser.unregister(objs);
		return isEnabled;
	}

	/**
	 * Check effective sales start date text field exist
	 * @return
	 */
	public boolean isEffStartDateTextFieldExist() {
		return browser.checkHtmlObjectExists(".id", "POSProductSetupView.effectiveStartDate_ForDisplay");
	}
	
	/**
	 * Verify effective sales start date text field exist
	 * @param exp
	 */
	public void verifyEffStartDateTextFieldExist(boolean exp) {
		boolean act = this.isEffStartDateTextFieldExist();
		this.compareResults("Effective Sales Start Date existence", exp, act);
	}
	
	/**
	 * Check effective sales end date text field exist
	 * @return
	 */
	public boolean isEffEndDateTextFieldExist() {
		return browser.checkHtmlObjectExists(".id", "POSProductSetupView.effectiveEndDate_ForDisplay");
	}
	
	/**
	 * Verify effective sales end date text field exist
	 * @param exp
	 */
	public void verifyEffEndDateTextFieldExist(boolean exp) {
		boolean act = this.isEffEndDateTextFieldExist();
		this.compareResults("Effective Sales End Date existence", exp, act);
	}
	
	/**
	 * Verify the date value with the fixed format. 
	 * @param expDate
	 * @param actDate
	 * @param meg
	 * @return
	 */
	private boolean verifyDateTextFeildFormatValue(String expDate, String actDate, String meg) {
		expDate = DateFunctions.formatDate(expDate, "EEE MMM d yyyy");
		boolean result = expDate.equalsIgnoreCase(actDate);
		if (!result) {
			logger.error(meg + " is wrong! The expect date is " + expDate + ", but the actual is " + actDate);
		} else {
			logger.info(meg + " is correct!");
		}
		return result;
	}
	
	/**
	 * Compare the expect and actual results.
	 * @param meg
	 * @param exp
	 * @param act
	 */
	private void compareResults(String meg, Object exp, Object act) {
		if (!MiscFunctions.compareResult(meg, exp, act)) {
			throw new ErrorOnPageException("---ERROR! Please check logger error!");
		}
	}
}
