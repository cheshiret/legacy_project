package com.activenetwork.qa.awo.pages.orms.inventoryManager.pos;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo.BarCode;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**  
 * @Description:  set pos barcode page.. (warehouse product setup details edit page)
 * @Preconditions:  
 * @SPEC:  
 * @Task#: Auto-972
 * @author jwang8  
 * @Date  Mar 29, 2012   
 */


public class InvMgrPosSetBarcodePage extends InvMgrCommonTopMenuPage{
	public static InvMgrPosSetBarcodePage instance = null;

	private InvMgrPosSetBarcodePage() {
	};

	public static InvMgrPosSetBarcodePage getInstance() {
		if (null == instance) {
			instance = new InvMgrPosSetBarcodePage();
		}
		return instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "barcode_table");
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
		  browser.clickGuiObject(".class", "Html.A", ".text", "Add");
	  }
	  /*
	   * click ok button.
	   */
	  public void clickOkButton(){
		  browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	  }
	  /*
	   * click Cancel button.
	   */
	  public void clickCancelButton() {
		  browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	  }
	  /*
	   * click remove button.
	   */
	  public void clickRemoveButton(int index){
		  browser.clickGuiObject(".class", "Html.A", ".text", "Remove",index);
	  }
	  /**
	   * get pos product id.
	   * @return the product id.
	   */
	  public String getPosProductId(){
		  return browser.getTextFieldValue(".id", "POSProductSetupView.productID");
	  }
	  /**
	   * set bar code info for pos
	   * @param barcodeList -  the list of barcode.
	   */
	  public void setBarCode(ArrayList<BarCode> barcodeList){
		  for(int i=0;i<barcodeList.size();i++){
			  this.clickAddButton();
			  this.setBarCode(barcodeList.get(i).barCode,i+1);
			  if(barcodeList.get(i).isRemove){
				  this.clickRemoveButton(i+1);
			  }
		  }
	  }
	  
	  /** Get Product Information */ 
	  public String getPosProductName() {
		  	return browser.getTextFieldValue(".id", "POSProductSetupView.name");
	  }
	  
	  public String getPosProductDescription() {
			return browser.getTextFieldValue(".id", "POSProductSetupView.description");
	  }
	  
	  public String getPosProductGroup() {
			return browser.getTextFieldValue(".id", "POSProductSetupView.productGroup");
	  }
	  
	  public String getPosProductClass() {
			return browser.getTextFieldValue(".id", "POSProductSetupView.productClass");
	  }
	
	  public String getPosProductSubClass() {
			return browser.getTextFieldValue(".id", "POSProductSetupView.productSubClass");
	  }
	  
	  public String getPosProductInventoryType() {
			return browser.getTextFieldValue(".id", "POSProductSetupView.inventoryTypeName");
	  }
	  
	  public String getPosProductQtyOnHand() {
		  IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".text", 
					  new RegularExpression("^Qty On Hand.*", false));
		  String text = browser.getTextFieldValue(Property.toPropertyArray(".classname", "readonly"), objs[0]);
		  Browser.unregister(objs);
		  return text;
	  }
	 
	  private IHtmlObject[] getPosProductBarcodeDivs() {
		  IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.Div", ".text", 
				  new RegularExpression("^Barcode",false));
		  if (objs == null || objs.length < 1) {
			  throw new ItemNotFoundException("Barcode DIV object can NOT be found!");
		  }
		  return objs;
	  }
	  
	  private IHtmlObject[] getPosProductBarcodeTextFields() {
		  IHtmlObject[] objs = this.getPosProductBarcodeDivs();
		  IHtmlObject[] textFields = browser.getHtmlObject(".class", "Html.Input.Text", objs[0]);
		  if (textFields == null || textFields.length < 1) {
			  throw new ItemNotFoundException("Barcode text field object can NOT be found!");
		  }
		  Browser.unregister(objs);
		  return textFields;
	  }
	  
	  public String getPosProductBarcode() {
		  IHtmlObject[] objs = this.getPosProductBarcodeDivs();
		  String text = browser.getTextFieldValue(Property.toPropertyArray(".classname", "readonly"), objs[0]);
		  Browser.unregister(objs);
		  return text;
	  }
	  
	  public POSInfo getPOSInfo() {
		  POSInfo pos = new POSInfo();
		  pos.productID = this.getPosProductId();
		  pos.product = this.getPosProductName();
		  pos.productDescription = this.getPosProductDescription();
		  pos.productGroup = this.getPosProductGroup();
		  pos.inventoryType = this.getPosProductInventoryType();
		  if (isPosProductQtyOnHandTextFieldExist()) {
			  pos.qtyOnHand = this.getPosProductQtyOnHand();
		  }
		  pos.barcode = this.getPosProductBarcode();
		  
		  return pos;
	  }

	  public boolean compareBarcodes(List<BarCode> barcodes, String barcodeFromUI) {
		  boolean result = true;
		  for (BarCode barcode : barcodes) {
			  if (!barcode.isRemove) {
				  if (barcodeFromUI.contains(barcode.barCode)) {
					  result &= true;
					  logger.info("Barcode " + barcode.barCode + " is shown in page correctly!");
				  } else {
					  result &= false;
					  logger.error("Barcodes "+ barcode.barCode + " is NOT shown in page!");
				  }
			  }	  
		  }
		  return result;
	  }
	  
	  public boolean comparePOSInfo(POSInfo pos) {
		  	POSInfo actPOS = this.getPOSInfo();
		  	logger.info("Compare POS Info in the page...");
		  	boolean result = true;
		  	// compare product information
		  	result &= MiscFunctions.compareResult("POS Product ID", pos.productID, actPOS.productID);
			result &= MiscFunctions.compareResult("POS Product Name", pos.product, actPOS.product);
			result &= MiscFunctions.compareResult("POS Product Description", pos.productDescription, actPOS.productDescription);
			result &= MiscFunctions.compareResult("POS Product Group", pos.productGroup, actPOS.productGroup);
			result &= MiscFunctions.compareResult("Inventory Type", pos.inventoryType, actPOS.inventoryType);
			result &= MiscFunctions.compareResult("Qty on Hand", pos.qtyOnHand, actPOS.qtyOnHand);
			result &= this.compareBarcodes(pos.barcodeList, actPOS.barcode);
			return result;
	  }
	  
	  public void verifyPOSInfo(POSInfo pos) {
		  	boolean result = this.comparePOSInfo(pos);
			if (!result) {
				throw new ErrorOnPageException("The POS Info is NOT correct in the Warehouse POS Setup Details page!");
			}
			logger.info("The POS Info is correct in the Warehouse POS Setup Details page!");
	  }
	  
	  public boolean isPosProductQtyOnHandTextFieldExist() {
		  return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", 
					  new RegularExpression("^Qty On Hand.*", false));
	  }
	  
	  private boolean isTextFieldEnabled(String idValue, String meg) {
		  IHtmlObject[] objs = browser.getTextField(".id", idValue);
		  if (objs == null || objs.length < 1) {
			  throw new ItemNotFoundException("Text Field with id=" + idValue + " can NOT be found!");
		  }
		  boolean isEnabled = objs[0].isEnabled();
		  Browser.unregister(objs);
		  logger.info("text field is " + (isEnabled ? " enabled" : "disabled"));
		  return isEnabled;
	  }
	  
	  public boolean isProductIDTextFieldEnabled() {
		  return this.isTextFieldEnabled("POSProductSetupView.productID", "Product ID");
	  }
	  
	  public boolean isProductNameTextFieldEnabled() {
		  return this.isTextFieldEnabled("POSProductSetupView.name", "Product Name");
	  }
	  
	  public boolean isProductDescriptionTextFieldEnabled() {
		  return this.isTextFieldEnabled("POSProductSetupView.description", "Product Description");
	  }
	  
	  public boolean isProductGroupTextFieldEnabled() {
		  return this.isTextFieldEnabled("POSProductSetupView.productGroup", "Product Group");
	  }
	  
	  public boolean isProductClassTextFieldEnabled() {
		  return this.isTextFieldEnabled("POSProductSetupView.productClass", "Product Class");
	  }
	  
	  public boolean isProductSubClassTextFieldEnabled() {
		  return this.isTextFieldEnabled("POSProductSetupView.productSubClass", "Product Sub Class");
	  }
	  
	  public boolean isProductInventoryTypeTextFieldEnabled() {
		  return this.isTextFieldEnabled("POSProductSetupView.inventoryTypeName", "Inventory Type");
	  }
	  
	  public boolean isProductQtyOnHandTextFieldEnabled() {
		  return this.isTextFieldEnabled("POSProductSetupView.qtyOnHand", "Qty On Hand");
	  }
	  
	  public boolean isProductBarcodeTextFieldEnabled() {
		  IHtmlObject[] objs = this.getPosProductBarcodeTextFields();
		  boolean isEnabled = objs[0].isEnabled();
		  Browser.unregister(objs);
		  logger.info("text field is " + (isEnabled ? " enabled" : "disabled"));
		  return isEnabled;
	  }
 }
