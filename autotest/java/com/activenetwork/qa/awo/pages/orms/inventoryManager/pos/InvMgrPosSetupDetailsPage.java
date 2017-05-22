package com.activenetwork.qa.awo.pages.orms.inventoryManager.pos;

import java.util.ArrayList;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo.BarCode;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo.PosSalesAttributes;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**  
 * @Description:  add pos product.
 * @Preconditions:  
 * @SPEC:  
 * @Task#: Auto-972
 * @author jwang8  
 * @Date  Mar 29, 2012
 * 
 * Note: This page was used for warehouse pos setup details page and master pos setup details page
 */
public class InvMgrPosSetupDetailsPage extends InvMgrCommonTopMenuPage{

	private static InvMgrPosSetupDetailsPage instance = null;
	
	private InvMgrPosSetupDetailsPage(){};
	
	public static InvMgrPosSetupDetailsPage getInstance(){
		if(null == instance){
			instance = new InvMgrPosSetupDetailsPage();
		}
		return instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("^Product Information.*",false));
	}
	/**
	 * Select order type.
	 * @param type- the type of pos.
	 */
   public void selectOrderType(String type){
	   browser.selectDropdownList(".id", new RegularExpression("POSSaleProductView-\\d+\\.prdSubcat",false), type);
   }
   /**
	 * set pos code.
	 * @param posCode- pos code.
	 */
   public void setPosProductCode(String posCode){
	   browser.setTextField(".id", new RegularExpression("POSSaleProductView-\\d+\\.code",false), posCode);
   }
   /**
	 * set pos name.
	 * @param posCode- pos name.
	 */
   public void setPosProdectName(String posName){
	   browser.setTextField(".id", new RegularExpression("POSSaleProductView-\\d+\\.name",false), posName);
   }
   /**
    * select product class.
    * @param productClass -  the product class.
    */
   public void selectProductClass(String productClass){
	   if(StringUtil.isEmpty(productClass)){
		   browser.selectDropdownList(".id", new RegularExpression("POSSaleProductView-\\d+\\.productClass",false), 0);
	   } else {
		   browser.selectDropdownList(".id", new RegularExpression("POSSaleProductView-\\d+\\.productClass",false), productClass);
	   }
   }
   
   /**
    * select sub product class.
    * @param subClass -  the sub product class.
    */
   public void selectProductSubClass(String subClass){
	   if(StringUtil.isEmpty(subClass)){
		   browser.selectDropdownList(".id", new RegularExpression("POSSaleProductView-\\d+\\.productSubClass",false), 0);
	   } else {
		   browser.selectDropdownList(".id", new RegularExpression("POSSaleProductView-\\d+\\.productSubClass",false), subClass);
	   }
   }
   
   /**
    * select product group.
    * @param group -  the product group.
    */
   public void selectProductGroup(String group){
	   browser.selectDropdownList(".id", new RegularExpression("POSSaleProductView-\\d+\\.productGroup",false), group);
   }

   /**
    * Added by Nicole
    * select Product Relation Type
    * @param relationType
    */
   public void selectRelationType(String relationType){
	   browser.selectDropdownList(".id", new RegularExpression("POSSaleProductView-\\d+\\.relationType",false), relationType);
   }

   /**
    * Added by Nicole
    * Get value of Product Relation Type
    * @param relationType
    */
   public String getRelationType(){
	   return browser.getDropdownListValue(".id", new RegularExpression("POSSaleProductView-\\d+\\.relationType",false));
  }

   /**
    * select inventory type.
    * @param inventoryType -  the s inventory type.
    */
   public void selectInventoryType(String inventoryType){
	   browser.selectDropdownList(".id", new RegularExpression("POSSaleProductView-\\d+\\.inventoryType",false), inventoryType);
   }
   /**
    * set pos product description.
    * @param description -  the pos product description.
    */
   public void setPosProductDescription(String description){
	   browser.setTextArea(".id", new RegularExpression("POSSaleProductView-\\d+\\.description",false), description);//for 3.03 INV add master pos, it should be text area
   }
  /**
   * click add barcode.
   */
   public void clickAddBarcode(){
	   browser.clickGuiObject(".class", "Html.A", ".text", "Add Barcode");
   }
   /**
    * select product available location.
    * @param location - the product available location.
    */
   public void selectAvaLocation(String location){
	   browser.selectDropdownList(".id", new RegularExpression("POSSaleProductView-\\d+\\.prdAvailableLocID",false), location);
   }
   /**
    * select acquire zip code in sale.
    * @param code - yes or no.
    */
   public void selectAcquireZipCode(String code){
	   browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[10417\\]:BOOLEAN_YESNO",false), code);
   }
   /**
    * select applicable customer.
    * @param customer - customer.
    */
   public void selectApplicationCustomer(String customer){
	   browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[10415\\]",false), customer);
   }
   
   /** Select In/Out State. Only two options: In-State and Out-State*/
   public void selectState(String state) {
	   browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[2052\\]",false), state);
   }
   /** Set Number of Occupants */
   public void setNumOfOccupants(String num) {
	   browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[2051\\]",false), num);
   }
   /** Select Vehicle indicator. Only two options: No and Yes*/
   public void selectVehicle(String vehicle) {
	   browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[2050\\]",false), vehicle);
   }
   
   public boolean isDefaultUnitPriceExists() {
	   return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[10411\\]",false));
   }
   
   /** Set Default unit price*/
   public void setDefaultUnitPrice(String price) {
	   browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[10411\\]",false), price);
   }
   
   public boolean isOverrideDefaultUnitPriceExists() {
	   return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[10412\\].*",false));
   }
   
   /** Select Override Default Unit Price. Only two options: No and Yes*/
   public void selectOverrideDefaultUnitPrice(String override) {
	   browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[10412\\].*",false), override);
   }
   
   public void selectAccount(String account) {
	   browser.selectDropdownList(".id", new RegularExpression("LocationAccountWrapper-\\d+\\.account", false), account);
   }
   
   public void selectAccount(int index) {
	   browser.selectDropdownList(".id", new RegularExpression("LocationAccountWrapper-\\d+\\.account", false), index);
   }
   
   public void selectDefaultAccountForAllAgent(){
	   IHtmlObject[] objs = browser.getDropdownList(".id", new RegularExpression("LocationAccountWrapper-\\d+\\.account",false));
		if (objs == null || objs.length < 1) {
			logger.warn("Can't find Account dropdown lists");
//			throw new ItemNotFoundException("Can't find Barcode text fields");
		} else {
			for (int i = 0; i < objs.length; i++) {
				((ISelect)objs[i]).select(1);
			}
		}
		Browser.unregister(objs);
   }
   
   
   /**
    * click add product attribute.
    */
   public void clickAddProductAttribute(){
	   browser.clickGuiObject(".class", "Html.A", ".text", "Add Product Attribute");
   }
   /**
    * click apply button.
    */
   public void clickApplyButton(){
	   browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
   }
   /**
    * click ok button.
    */
   public void clickOkButton(){
	   browser.clickGuiObject(".class", "Html.A", ".text", "OK");
   }
   /**
    * click Cancel button.
    */
   public void clickCancelButton(){
	   browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
   }
   
  /**
   * click remove barcode.
   */
  public void clickRemoveBarcode(int index){
	  browser.clickGuiObject(".class", "Html.A", ".text", "Remove Barcode",index);
  }
  /**
   * set bar code in pos set up details page.
   * @param barcode - the bar code value.
   * @param index -  the index of bar code input box.
   */
  
  public void setBarCode(String barcode, int index){
	  browser.setTextField(".id", new RegularExpression("StringBean-\\d+\\.value",false), barcode,index);
	  
  }
  /**
   * set pos sales attributes value.
   * @param attribute - the attribute value.
   * @param index - the attribute input box index.
   */
  public void selectAttribute(String attribute,int index){
	  browser.selectDropdownList(".id", new RegularExpression("SectionableProductAttribute-\\d+\\.prdAttribute.attribute",false), attribute,index);
  }
  /**
   * set display sequence.
   * @param squence - the sequence value.
   * @param index the sequence input box
   */
  public void setDisplaySquence(String squence,int index){
	  browser.setTextField(".id", new RegularExpression("SectionableProductAttribute-\\d+\\.prdAttribute.ordinal:ZERO_TO_NULL",false), squence,index);
  }
  /**
   * select mandatory value.
   * @param mondatory - the mandatory value.
   * @param index - input box of mandatory index.
   */
  public void selectMandatory(String mondatory,int index){
	  browser.selectDropdownList(".id", new RegularExpression("SectionableProductAttribute-\\d+\\.mandatory",false), mondatory,index);
  }
  /**
   * select active yes or on.
   * @param isActive - is active or on
   * @param index - the index.
   */
  public void selectActive(String isActive, int index){
	  browser.selectDropdownList(".id", new RegularExpression("SectionableProductAttribute-\\d+\\.active",false), isActive,index);
  }
  /**
   * click remove product attribute.
   * @param - the index of attribute.
   */
  public void clickRemoveProductAttribute(int index){
	  browser.clickGuiObject(".class", "Html.A", ".text", "Remove Product Attribute",index);
  }
  /**
   * set bar code list.
   * @param barCodeList - the list of bar code info.
   */
  public void setBarcode(ArrayList<BarCode> barCodeList){
	  for(int i = 0;i<barCodeList.size();i++){
//		  this.clickAddBarcode();
//		  ajax.waitLoading();
//		  this.setBarCode(barCodeList.get(i).barCode, i);
		  this.addPOSBarcode(barCodeList.get(i).barCode, i);		 
		  if(barCodeList.get(i).isRemove){
//			  this.clickRemoveBarcode(i);
//			  ajax.waitLoading();
			  this.removePOSBarcode(i);
		  }
	  }
  }
  /**
   * set the pos sales attributes info value.
   * @param attributes -  the attribute value.
   * @param index - the index of attribute.
   */
  public void setPosSalesAttribute(PosSalesAttributes attributes,int index){
	  this.selectAttribute(attributes.attributes, index);
	  this.setDisplaySquence(attributes.displaySequence, index);
	  this.selectMandatory(attributes.mandatory, index);
	  this.selectActive(attributes.active, index);
  }
  /**
   * set pos sales attributes info
   * @param attributesList - the list of attributes.
   */
  public void setPosSalesAttributes(ArrayList<PosSalesAttributes> attributesList){
	  for(int i = 0;i<attributesList.size();i++){
//		  this.clickAddProductAttribute();
//		  ajax.waitLoading();
//		  this.setPosSalesAttributes(attributesList.get(i),i);
		  this.addPOSSalesAttribute(attributesList.get(i),i);
		  if(attributesList.get(i).isRemove){
//			  this.clickRemoveProductAttribute(i);
//			  ajax.waitLoading();
			  this.removePOSSalesAttribute(i);
		  }
	  }
  }
  /**
   * check serialization type Exist.
   * @return 
   */
  public boolean checkSerializationTypeExist(){
	  return browser.checkHtmlObjectExists(".id",new RegularExpression("POSSaleProductView-\\d+\\.serializationConfig.serializationType",false));
  }
  /**
   * Select serialization type.
   * @param type - serialization type.
   */
  
  public void selectSerializationType(String type){
	  browser.selectDropdownList(".id",new RegularExpression("POSSaleProductView-\\d+\\.serializationConfig.serializationType",false), type);
  }
  /**
   * check Serialization Reference Id exist or not
   * @return
   */
  public boolean checkSerializationReferenceId(){
	  return browser.checkHtmlObjectExists(".id",new RegularExpression("POSSaleProductView-\\d+\\.serializationConfig.serializationTypeInstanceId",false));
  }
  /**
   * select serialization reference id.
   * @param referenceId - reference id.
   */
  public void selectSerializationReferenceId(String referenceId){
	  browser.selectDropdownList(".id",new RegularExpression("POSSaleProductView-\\d+\\.serializationConfig.serializationTypeInstanceId",false), referenceId);
  }
  
  protected Property[] serializationNumType(){
	  return Property.toPropertyArray(".class","Html.SELECT",".id",new RegularExpression("POSSaleProductView-\\d+\\.serializationConfig.serializationNumberType",false));
  }
  
  public boolean checkSerializationNumTypeExists(){
	  return browser.checkHtmlObjectExists(serializationNumType());
  }
  
  public void selectSerializationNumType(String option){
	  browser.selectDropdownList(serializationNumType(), option);
  }
  
  /**
   * check serialization format.
   * @return
   */
  public boolean checkSerializationFormat(){
	 return browser.checkHtmlObjectExists(".id",new RegularExpression("POSSaleProductView-\\d+\\.serializationConfig.format",false));
  }
  /**
   * set serialization format.
   * @param fomat
   */
  public void setSerializationFormat(String fomat){
	  browser.setTextField(".id",new RegularExpression("POSSaleProductView-\\d+\\.serializationConfig.format",false), fomat);
	  
  }
  /**
   * check serialization increment exist.
   * @return
   */
  public boolean checkSerializationIncrement(){
	  return browser.checkHtmlObjectExists(".id",new RegularExpression("POSSaleProductView-\\d+\\.serializationConfig.increment:ZERO_TO_NULL",false));
  }
  /**
   * set serialization increment.
   * @param increment
   */
  public void setSerializationIncrement(String increment){
	  browser.setTextField(".id",new RegularExpression("POSSaleProductView-\\d+\\.serializationConfig.increment:ZERO_TO_NULL",false), increment);
  }
  /**
   * check serialization expiry date rule exist or not.
   * @return
   */
  public boolean checkSerializationExpiryDateRule(){
	  return browser.checkHtmlObjectEnabled(".id",new RegularExpression("POSSaleProductView-\\d+\\.serializationConfig.expiryDateRule",false));
  }
  /**
   * set serialization expiry date rule.
   * @param rule
   */
  public void selectSerializationExpiryDateRule(String rule){
	  browser.selectDropdownList(".id",new RegularExpression("POSSaleProductView-\\d+\\.serializationConfig.expiryDateRule",false), rule);
  }
  
  public String getSelectedInventoryType(){
	   return browser.getDropdownListValue(".id", new RegularExpression("POSSaleProductView-\\d+\\.inventoryType",false));
  }

  public void selectExtraDecimalIndicator(String indicator){
	   browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[4901\\]:BOOLEAN_YESNO", false), indicator);
  }
  
  public void selectSpecificRevLoc(){
	   browser.selectRadioButton(".id", new RegularExpression("EditPOSProductUI-\\d+\\.revLocSelectionType", false), 1);	   
  }
  
  public boolean isExtraDecimalIndicatorExisted(){
	   return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[4901\\]:BOOLEAN_YESNO", false));
  }
  
  /**
   * This method only used for master pos product setup, after click apply button, system will generate product id
   * @return
   */
  	public String getProductId(){
  		return browser.getTextFieldValue(".id", new RegularExpression("POSSaleProductView-\\d+\\.id:ZERO_TO_NEW", false));
	}
   
  	/** Get Product Information */ 
	public String getOrderType() {
		return browser.getDropdownListValue(".id", new RegularExpression("POSSaleProductView-\\d+\\.prdSubcat",false));
	}
	
	public String getProductCode() {
		return browser.getTextFieldValue(".id", new RegularExpression("POSSaleProductView-\\d+\\.code",false));
	}
	
	public String getProductName() {
		return browser.getTextFieldValue(".id", new RegularExpression("POSSaleProductView-\\d+\\.name",false));
	}
	
	public String getProductStatus() {
		return browser.getDropdownListValue(".id", new RegularExpression("UserInput-\\d+\\.status",false));
	}
	
	public String getProductClass() {
		 return browser.getDropdownListValue(".id", new RegularExpression("POSSaleProductView-\\d+\\.productClass",false));
	}
	
	public String getProductSubClass() {
		 return browser.getDropdownListValue(".id", new RegularExpression("POSSaleProductView-\\d+\\.productSubClass",false));
	}
	public String getProductGroup() {
		return browser.getDropdownListValue(".id", new RegularExpression("POSSaleProductView-\\d+\\.productGroup",false));
	}
	
	public String getProductGroup(int index) {
		return browser.getDropdownListValue(".id", new RegularExpression("POSSaleProductView-\\d+\\.productGroup",false), index);
	}
	
	public String getInventoryType() {
		return browser.getDropdownListValue(".id", new RegularExpression("POSSaleProductView-\\d+\\.inventoryType",false));
	}
	
	public String getProductDescription() {
		return browser.getTextAreaValue(".id", new RegularExpression("POSSaleProductView-\\d+\\.description",false));
	}
	
	public String getBarCode(int index) {
		return browser.getTextFieldValue(".id", new RegularExpression("StringBean-\\d+\\.value",false), index);
	}
	
	public ArrayList<BarCode> getBarCodes() {
		ArrayList<BarCode> barcodes = new ArrayList<BarCode>();
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("StringBean-\\d+\\.value",false));
		if (objs == null || objs.length < 1) {
			logger.warn("Can't find Barcode text fields");
//			throw new ItemNotFoundException("Can't find Barcode text fields");
		} else {
			for (int i = 0; i < objs.length; i++) {
				BarCode barcode = new POSInfo().new BarCode();
				barcode.barCode = this.getBarCode(i);
				barcodes.add(barcode);
			}
		}
		Browser.unregister(objs);
		return barcodes;
	}
	
	public String getSerializationType() {
		return browser.getDropdownListValue(".id",new RegularExpression("POSSaleProductView-\\d+\\.serializationConfig.serializationType",false));
	}
	
	public String getSerializationRefID() {
		return browser.getDropdownListValue(".id",new RegularExpression("POSSaleProductView-\\d+\\.serializationConfig.serializationTypeInstanceId",false));
	}
	
	public String getSerializationFormat() {
		return browser.getTextFieldValue(".id",new RegularExpression("POSSaleProductView-\\d+\\.serializationConfig.format",false));
	}
	
	public String getSerializationIncrement() {
		return browser.getTextFieldValue(".id",new RegularExpression("POSSaleProductView-\\d+\\.serializationConfig.increment:ZERO_TO_NULL",false));
	}
	
	public String getSerializationExpiryDateRule() {
		return browser.getDropdownListValue(".id",new RegularExpression("POSSaleProductView-\\d+\\.serializationConfig.expiryDateRule",false));
	}
	
	/** Get Location info */
	public String getAvailableLocation() {
		return browser.getDropdownListValue(".id", new RegularExpression("POSSaleProductView-\\d+\\.prdAvailableLocID",false));
	}
	public boolean isSpecificLocationSelected() {
		IHtmlObject[] objs = browser.getRadioButton(".id", new RegularExpression("EditPOSProductUI-\\d+\\.revLocSelectionType", false));
		if (objs == null || objs.length < 1) {
			throw new ItemNotFoundException("Can't find the Specific Location radio button");
		} 
		boolean isSelected = ((IRadioButton)objs[1]).isSelected();
		Browser.unregister(objs);
		return isSelected;
	
	}
	public String getRevenueLocation() {
		return browser.getTextFieldValue(".id", new RegularExpression("POSSaleProductView-\\d+\\.revenueLocationName", false));
	}
	
	/** Get POS Attributes */
	public String getAcquireZipCode() {
		return browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[10417\\]:BOOLEAN_YESNO",false));
	}
	
	public boolean isAcquireZipCodeDropdownListExist() {
		return browser.checkHtmlObjectExists(".class", "Html.Select", ".id", 
				new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[10417\\]:BOOLEAN_YESNO",false));
	}
	
	public String getApplicationCustomer() {
		return browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[10415\\]",false));
	}
	
	public boolean isApplicationCustomerDropdownListExist() {
		return browser.checkHtmlObjectExists(".class", "Html.Select", ".id", 
				new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[10415\\]",false));
	}
	
	public String getState() {
		return browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[2052\\]",false));
	}
	
	public boolean isStateDropdownListExist() {
		return browser.checkHtmlObjectExists(".class", "Html.Select", ".id", 
				new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[2052\\]",false));
	}
	
	public String getNumOfOccupants() {
		return browser.getTextFieldValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[2051\\]",false));
	}
	
	public boolean isNumOfOccupantsTextFieldExist() {
		return browser.checkHtmlObjectExists(".class", "Html.Input.text", ".id", 
				new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[2051\\]",false));
	}
	
	public String getVehicle() {
		return browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[2050\\]",false));
	}
	
	public boolean isVehicleDropdownListExist() {
		return browser.checkHtmlObjectExists(".class", "Html.Select", ".id", 
				new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[2050\\]",false));
	}
	
	/** Get Account Schedule */
	public String getAccountSchedule() {
		return browser.getDropdownListValue(".id", new RegularExpression("LocationAccountWrapper-\\d+\\.account", false));
	}
	
	public boolean isAccountScheduleDropdownListExist() {
		return browser.checkHtmlObjectExists(".class", "Html.Select", ".id", 
				new RegularExpression("LocationAccountWrapper-\\d+\\.account", false));
	}
	
	public boolean isAccountScheduleDropdownListEnabled() {
		IHtmlObject[] objs = browser.getDropdownList(".class", "Html.Select", ".id", 
				new RegularExpression("LocationAccountWrapper-\\d+\\.account", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find Account Schedule Dropdown list on the page.");
		}
		boolean isEnabled = objs[0].isEnabled();
		Browser.unregister(objs);
		logger.info("The account schedule dropdown list is " + (isEnabled ? "enabled" : "disabled"));
		return isEnabled;
	}
	
	/** Get POS Sales Attributes */
	public String getPOSSalesAttributeName(int index) {
		return browser.getDropdownListValue(".id", new RegularExpression("SectionableProductAttribute-\\d+\\.prdAttribute.attribute",false), index);
	}
	public String getPOSSalesAttributeDisplaySeq(int index) {
		return browser.getTextFieldValue(".id", new RegularExpression("SectionableProductAttribute-\\d+\\.prdAttribute.ordinal:ZERO_TO_NULL",false), index);
	}
	public String getPOSSalesAttributeMandatoryValue(int index) {
		return browser.getDropdownListValue(".id", new RegularExpression("SectionableProductAttribute-\\d+\\.mandatory",false), index);
	}
	public String getPOSSalesAttributeActiveValue(int index) {
		return browser.getDropdownListValue(".id", new RegularExpression("SectionableProductAttribute-\\d+\\.active",false), index);
	}
	public PosSalesAttributes getPOSSalesAttributeValues(int index) {
		PosSalesAttributes attr = new POSInfo().new PosSalesAttributes();
		attr.attributes = this.getPOSSalesAttributeName(index);
		attr.displaySequence = this.getPOSSalesAttributeDisplaySeq(index);
		attr.mandatory = this.getPOSSalesAttributeMandatoryValue(index);
		attr.active = this.getPOSSalesAttributeActiveValue(index);
		return attr;
	}
	public ArrayList<PosSalesAttributes> getSalesAttributes() {
		ArrayList<PosSalesAttributes> attributes = new ArrayList<PosSalesAttributes>();
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.Table", ".id", "stgPOS Sales Attributes");
		if (objs == null || objs.length < 1) {
			throw new ItemNotFoundException("Can't find POS Sales Attributes table");
		} 
		int rows = ((IHtmlTable)objs[0]).rowCount();
		for (int i = 0; i < rows - 1; i++) {
			attributes.add(this.getPOSSalesAttributeValues(i));
		}	
		Browser.unregister(objs);
		return attributes;
	}
	
	/**
	 * Get all POS Info in the page
	 * @return
	 */
	public POSInfo getPOSInfo() {
		POSInfo pos = new POSInfo();
		
		logger.info("Get POS Info in the page...");
		// Get Product Information
		pos.productID = this.getProductId();
		pos.orderType = this.getOrderType();
		pos.productCode = this.getProductCode();
		pos.product = this.getProductName();
		pos.status = this.getProductStatus();
		pos.productClass = this.getProductClass();
		pos.productSubClass = this.getProductSubClass();
		pos.productGroup = this.getProductGroup();
		pos.inventoryType = this.getInventoryType();
		pos.productDescription = this.getProductDescription();
		
		pos.barcodeList = this.getBarCodes();
		if (pos.inventoryType.equalsIgnoreCase(OrmsConstants.SERIALIZED_INVENTORY_TYPE)) {
			pos.serilizType = this.getSerializationType();
			pos.serilizReferenceId = this.getSerializationRefID();
			pos.serilizFormat = this.getSerializationFormat();
			pos.serilizIncrement = this.getSerializationIncrement();
			pos.serilizRule = this.getSerializationExpiryDateRule();
		}
		// Get Location
		pos.availableLocation = this.getAvailableLocation();
		pos.specificLocation = this.isSpecificLocationSelected();
		String revLoc = this.getRevenueLocation();
		pos.revLocation.agency = revLoc;
		pos.revLocation.region = revLoc;
		pos.revLocation.facility = revLoc;
		
		// Get POS Attributes
		if (this.isAcquireZipCodeDropdownListExist()) {
			pos.acquierZipCodeInSale = this.getAcquireZipCode();
		}
		if (this.isApplicationCustomerDropdownListExist()) {
			pos.applicationCustomer = this.getApplicationCustomer();
		}
		if (this.isStateDropdownListExist()) {
			pos.state = this.getState();
		}
		if (this.isNumOfOccupantsTextFieldExist()) {
			pos.numOfOccupants = this.getNumOfOccupants();
		}
		if (this.isVehicleDropdownListExist()) {
			pos.vehicle = this.getVehicle();
		}
		// Get Account Schedules
		if (this.isAccountScheduleDropdownListExist()) {
			pos.account = this.getAccountSchedule();			
		}
		
		// Get POS Sales Attributes
		pos.attributesArray = this.getSalesAttributes();
		
		return pos;
	}
	
	public boolean compareBarCodes(ArrayList<BarCode> expBarCodes, ArrayList<BarCode> actBarCodes) {
		logger.info("Compare barcodes...");
		boolean result = true;
		int count = expBarCodes.size();
		for (int i = 0; i < expBarCodes.size(); i++) {
			if (!expBarCodes.get(i).isRemove) {
				result &= MiscFunctions.compareResult("Barcode #" + i, expBarCodes.get(i).barCode, actBarCodes.get(i).barCode);				
			} else {
				count--;
			}
		}
		result &= MiscFunctions.compareResult("The number of POS Barcodes", count, actBarCodes.size());
		return result;
	}
	
	public boolean comparePOSSalesAttribute(PosSalesAttributes expAttr, PosSalesAttributes actAttr) {
		logger.info("Compare POS Sales Attribute " + expAttr.attributes);
		boolean result = true;
		result &= MiscFunctions.compareResult("Attribute Name", expAttr.attributes, actAttr.attributes);	
		result &= MiscFunctions.compareResult("Display Sequence", expAttr.displaySequence, actAttr.displaySequence);
		result &= MiscFunctions.compareResult("Mandatory value", expAttr.mandatory, actAttr.mandatory);
		result &= MiscFunctions.compareResult("Active value", expAttr.active, actAttr.active);
		return result;
	}
	
	public boolean comparePOSSalesAttributes(ArrayList<PosSalesAttributes> expAttributes, ArrayList<PosSalesAttributes> actAttributes) {
		logger.info("Compare POS Sales Attributes...");
		boolean result = true;
		int count = expAttributes.size();
		for (int i = 0; i < expAttributes.size(); i++) {
			PosSalesAttributes expAttr = expAttributes.get(i);
			if (!expAttr.isRemove) {
				PosSalesAttributes actAttr = actAttributes.get(i);
				result &= this.comparePOSSalesAttribute(expAttr, actAttr);
			} else {
				count--;
			}
		}
		result &= MiscFunctions.compareResult("The number of POS Sales Attributes", count, actAttributes.size());
		return result;
	}
	
	public boolean comparePOSInfo(POSInfo pos) {
		POSInfo actPOS = this.getPOSInfo();
		logger.info("Compare POS Info in the page...");
		boolean result = true;
		// compare product information
		if(!StringUtil.isEmpty(pos.orderType)){
			result &= MiscFunctions.compareResult("Order Type", pos.orderType, actPOS.orderType);
		}
		if(!StringUtil.isEmpty(pos.productID)){
			result &= MiscFunctions.compareResult("POS Product ID", pos.productID, actPOS.productID);
		}
		if(!StringUtil.isEmpty(pos.productCode)){
			result &= MiscFunctions.compareResult("POS Product Code", pos.productCode, actPOS.productCode);
		}
		if(!StringUtil.isEmpty(pos.product)){
			result &= MiscFunctions.compareResult("POS Product Name", pos.product, actPOS.product);
		}
		if(!StringUtil.isEmpty(pos.status)){
			result &= MiscFunctions.compareResult("POS Product Status", pos.status, actPOS.status);
		}
		if(!StringUtil.isEmpty(pos.productClass)){
			result &= MiscFunctions.compareResult("POS Product Class", pos.productClass, actPOS.productClass);
		}
		if(!StringUtil.isEmpty(pos.productSubClass)){
			result &= MiscFunctions.compareResult("POS Product Sub Class", pos.productSubClass, actPOS.productSubClass);
		}

		if (StringUtil.isEmpty(pos.productGroup)) {
			pos.productGroup = this.getProductGroup(0);
		}
		if(!StringUtil.isEmpty(pos.productGroup)){
			result &= MiscFunctions.compareResult("POS Product Group", pos.productGroup, actPOS.productGroup);
		}
		if(!StringUtil.isEmpty(pos.inventoryType)){
			result &= MiscFunctions.compareResult("Inventory Type", pos.inventoryType, actPOS.inventoryType);
		}
		if(!StringUtil.isEmpty(pos.productDescription)){
			result &= MiscFunctions.compareResult("POS Product Description", pos.productDescription, actPOS.productDescription);
		}
		result &= this.compareBarCodes(pos.barcodeList, actPOS.barcodeList);
		if(!StringUtil.isEmpty(pos.serilizType)){
			result &= MiscFunctions.compareResult("Serialization Type", pos.serilizType, actPOS.serilizType);
		}
		if(!StringUtil.isEmpty(pos.serilizReferenceId)){
			result &= MiscFunctions.compareResult("Serialization Reference ID", pos.serilizReferenceId, actPOS.serilizReferenceId);
		}
		if(!StringUtil.isEmpty(pos.serilizFormat)){
			result &= MiscFunctions.compareResult("Serialization Format", pos.serilizFormat, actPOS.serilizFormat);
		}
		if(!StringUtil.isEmpty(pos.serilizIncrement)){
			result &= MiscFunctions.compareResult("Serialization Increment", pos.serilizIncrement, actPOS.serilizIncrement);
		}
		if(!StringUtil.isEmpty(pos.serilizRule)){
			result &= MiscFunctions.compareResult("Serialization Expiry Date Rule", pos.serilizRule, actPOS.serilizRule);
		}
		
		// compare location
		if(!StringUtil.isEmpty(pos.availableLocation)){
			result &= MiscFunctions.compareResult("Product Available Location", pos.availableLocation, actPOS.availableLocation);
		}
		result &= MiscFunctions.compareResult("Specific Location Selection", pos.specificLocation, actPOS.specificLocation);
		if (pos.revLocation.agency.equalsIgnoreCase(actPOS.revLocation.agency) || 
				pos.revLocation.region.equalsIgnoreCase(actPOS.revLocation.region) ||
				pos.revLocation.facility.equalsIgnoreCase(actPOS.revLocation.facility)) {
			result &= true;
			logger.info("The revnue location is correct! " + actPOS.revLocation.facility);
		} else {
			result &= false;
			logger.error("The revenue location is wrong! The actual revenue location " + actPOS.revLocation.facility +
						" should be same as " + pos.revLocation.agency + " or " + pos.revLocation.region + " or " + pos.revLocation.facility);
		}
		
		// compare POS Attributes
		if(!StringUtil.isEmpty(pos.acquierZipCodeInSale)){
			result &= MiscFunctions.compareResult("Acquire Zip Code in Sale", pos.acquierZipCodeInSale, actPOS.acquierZipCodeInSale);
		}
		if(!StringUtil.isEmpty(pos.applicationCustomer)){
			result &= MiscFunctions.compareResult("Application Customer", pos.applicationCustomer, actPOS.applicationCustomer);
		}
		if(!StringUtil.isEmpty(pos.state)){
			result &= MiscFunctions.compareResult("In/Out State", pos.state, actPOS.state);
		}
		if(!StringUtil.isEmpty(pos.numOfOccupants)){
			result &= MiscFunctions.compareResult("Number of Occupants", pos.numOfOccupants, actPOS.numOfOccupants);
		}
		if(!StringUtil.isEmpty(pos.vehicle)){
			result &= MiscFunctions.compareResult("Vehicle", pos.vehicle, actPOS.vehicle);
		}
		
		// compare POS Sales Attributes
		result &= this.comparePOSSalesAttributes(pos.attributesArray, actPOS.attributesArray);
		
		return result;
	}
  
	public void verifyPOSInfo(POSInfo pos) {
		boolean result = this.comparePOSInfo(pos);
		if (!result) {
			throw new ErrorOnPageException("The POS Info is NOT correct in the POS Setup Details page!");
		}
		logger.info("The POS Info is correct in the POS Setup Details page!");
	}
	
	/** Click Change Location button */
	public void clickChangeLocation() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Change Location");
	}
	public boolean isChangeLocationButtonExist() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Change Location");
	}
	
	/** Remove a POS Barcode */
	public void removePOSBarcode(int index) {
		this.clickRemoveBarcode(index);
		ajax.waitLoading();
		this.waitLoading();
	}
	
	/** Add a POS Barcode */
	public void addPOSBarcode(String barcode, int index) {
		this.clickAddBarcode();
		ajax.waitLoading();
		this.setBarCode(barcode, index);
	}
	
	/** Remove a POS Sales Attribute */
	public void removePOSSalesAttribute(int index) {
		this.clickRemoveProductAttribute(index);
		ajax.waitLoading();
		this.waitLoading();
	}
	
	/** Add a POS Sales Attribute */
	public void addPOSSalesAttribute(PosSalesAttributes attr, int index) {
		this.clickAddProductAttribute();
		ajax.waitLoading();
		this.setPosSalesAttribute(attr,index);
	}
	
	public String getMessage(){
		return browser.getObjectText(".className","message msgerror",".id","NOTSET");
	}
}
