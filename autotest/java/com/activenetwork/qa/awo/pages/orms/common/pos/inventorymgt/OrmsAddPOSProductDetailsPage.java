package com.activenetwork.qa.awo.pages.orms.common.pos.inventorymgt;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo.BarCode;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo.PosSalesAttributes;
import com.activenetwork.qa.awo.pages.orms.common.pos.OrmsPOSCommonPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

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
public class OrmsAddPOSProductDetailsPage extends OrmsPOSCommonPage {
	
	private static OrmsAddPOSProductDetailsPage _instance = null;

	private OrmsAddPOSProductDetailsPage() {}

	public static OrmsAddPOSProductDetailsPage getInstance() {
		if (null == _instance) {
			_instance = new OrmsAddPOSProductDetailsPage();
		}
		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("POSSaleProductView-\\d+\\.description",false));
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
	   browser.selectDropdownList(".id", new RegularExpression("POSSaleProductView-\\d+\\.productClass",false), productClass);
  }
  /**
   * select sub product class.
   * @param subClass -  the sub product class.
   */
  public void selectProductSubClass(String subClass){
	   browser.selectDropdownList(".id", new RegularExpression("POSSaleProductView-\\d+\\.productSubClass",false), subClass);
  }
  /**
   * select sub product group.
   * @param group -  the sub product group.
   */
  public void selectProductGroup(String group){
	   browser.selectDropdownList(".id", new RegularExpression("POSSaleProductView-\\d+\\.productGroup",false), group);
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
	   browser.setTextArea(".id", new RegularExpression("POSSaleProductView-\\d+\\.description",false), description);
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
	   browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr[10417]:BOOLEAN_YESNO",false), code);
   }

   public boolean isAccountEnabled() {
	   IHtmlObject objs[] = browser.getDropdownList(".id", new RegularExpression("LocationAccountWrapper-\\d+\\.account", false));
	   if(objs.length < 1) {
		   throw new ItemNotFoundException("Cannot find Account dropdown list object.");
	   }
	   boolean enabled = objs[0].isEnabled();
	   Browser.unregister(objs);
	   
	   return enabled;
   }
   
   public void selectAccount(String account) {
	   browser.selectDropdownList(".id", new RegularExpression("LocationAccountWrapper-\\d+\\.account", false), account);
   }
   
   public void selectAccount(int index) {
	   browser.selectDropdownList(".id", new RegularExpression("LocationAccountWrapper-\\d+\\.account", false), index);
   }
   
   /**
    * select applicable customer.
    * @param customer - customer.
    */
   public void selectApplicationCustomer(String customer){
	   browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr[10415]",false), customer);
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
    * set pos product unit price.
    * @param unitPrice - the value of unit price.
    */
   public void setPosProductUnitPrice(String unitPrice){
	   browser.setTextField(".id", new RegularExpression("AssignmentBean-\\d+\\.unitPrice", false), unitPrice);
   }
   /**
    * set effective start date
    * @param startDate - start date.
    */
   public void setEffectiveStartDate(String startDate){
	   browser.setCalendarField(".id", new RegularExpression("AssignmentBean-\\d+\\.effectiveStartDate_ForDisplay", false), startDate);
   }
   /**
    * set effective end date.
    * @param endDate - the end date.
    */
   public void setEffectiveEndDate(String endDate){
	   browser.setCalendarField(".id", new RegularExpression("AssignmentBean-\\d+\\.effectiveEndDate_ForDisplay", false), endDate);
   }
   /**
    * select variable price indicator.
    * @param priceIndicator -  price indicator.
    */
   public void selectVariablePriceIndicator(String priceIndicator){
	   browser.selectDropdownList(".id", new RegularExpression("AssignmentBean-\\d+\\.variablyPriced", false), priceIndicator);
   }
   /**
    * select partial quantity indicator.
    * @param quantityIndicator - the quantity indicator.
    */
   public void selectPartialQuantityIndicator(String quantityIndicator){
	   browser.selectDropdownList(".id", new RegularExpression("AssignmentBean-\\d+\\.partialQuantities",false), quantityIndicator);
   }
   /**
    * set display order.
    * @param displayOrder - the display order.
    */
   public void setDisplayOrder(String displayOrder){
	   browser.setTextField(".id", new RegularExpression("AssignmentBean-\\d+\\.displayOrder",false), displayOrder);
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
 		  this.clickAddBarcode();
 		  ajax.waitLoading();
 		  this.setBarCode(barCodeList.get(i).barCode, i);
 		  if(barCodeList.get(i).isRemove){
 			  this.clickRemoveBarcode(i);
 			  ajax.waitLoading();
 		  }
 	  }
   }
   /**
    * set the pos sales attributes info value.
    * @param attributes -  the attribute value.
    * @param index - the index of attribute.
    */
   public void setPosSalesAttributes(PosSalesAttributes attributes,int index){
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
 		  this.clickAddProductAttribute();
 		  ajax.waitLoading();
 		  this.setPosSalesAttributes(attributesList.get(i),i);
 		  if(attributesList.get(i).isRemove){
 			  this.clickRemoveProductAttribute(i);
 			  ajax.waitLoading();
 		  }
 	  }
   }
   
   public void selectSpecificRevLoc(){
	   browser.selectRadioButton(".id", new RegularExpression("EditPOSProductUI-\\d+\\.revLocSelectionType", false), 1);	   
   }
   
   public void selectWhereSold(){
	   browser.selectRadioButton(".id", new RegularExpression("EditPOSProductUI-\\d+\\.revLocSelectionType", false), 0);	   
   }
   
   public String getSelectedInventoryType(){
	   return browser.getDropdownListValue(".id", new RegularExpression("POSSaleProductView-\\d+\\.inventoryType",false));
   }

   public void selectExtraDecimalIndicator(String indicator){
	   browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[4901\\]:BOOLEAN_YESNO", false), indicator);
   }
   
   public boolean isExtraDecimalIndicatorExisted(){
	   return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[4901\\]:BOOLEAN_YESNO", false));
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
}
