package com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.pospurchaseorder;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPurchaseOrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPurchaseOrderItemInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Jun 26, 2012
 */
public class InvMgrNewPurchaseOrderPage extends InvMgrCommonTopMenuPage {

    public static InvMgrNewPurchaseOrderPage instance = null;
    
    private InvMgrNewPurchaseOrderPage(){};
    
    public static InvMgrNewPurchaseOrderPage getInstance(){
    	if(null == instance){
    		instance = new InvMgrNewPurchaseOrderPage();
    	}
		return instance;
    }
    
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("po-det",false));
	}
	
	private String prefix = "POView-\\d+.";
	public void setSupplierName(String supplierName){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"supplierLocation", false), supplierName, true);
	}
	
	public void setPurchaseDate(String purchaseDate){
		browser.setTextField(".id", new RegularExpression(prefix+"purchaseDate_ForDisplay", false), purchaseDate, true);
	}
	
	public void setRequiredShippingDate(String shippingDate){
		browser.setTextField(".id", new RegularExpression(prefix+"reqShippingDate_ForDisplay", false), shippingDate, true);
	}
	
	public void setStartShippingDate(String shippingDate){
		if(StringUtil.notEmpty(shippingDate)){
			browser.setTextField(".id", new RegularExpression(prefix+"startShippingDate_ForDisplay", false), shippingDate, true);	
		}
	}

	public void setPaymentTerms(String paymentTerms){
		browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+.attr\\[10431\\]", false), paymentTerms, true);
	}

	public void setPaymentMethod(String paymentMethod){
		browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+.attr\\[10432\\]", false), paymentMethod, true);
	}

	public void setShippingMethod(String shippingMethod){
		browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+.attr\\[10433\\]", false), shippingMethod, true);
	}
	
	public void setInternalNotes(String internalNotes){
		browser.setTextArea(".id", new RegularExpression(prefix+"internalNotes", false), internalNotes, true);
	}
	
	public void setProductName(String productName,int index){
//		browser.setTextField(".id", new RegularExpression("POItemView-\\d+.autoCompleteProductName", false), productName, true,index);
		browser.setTextField(".id", new RegularExpression("POItemView-\\d+.autoCompleteProductName", false), productName, index,  IText.Event.FOCUS);
		Property[] p = Property.toPropertyArray(".id", new RegularExpression("POItemView-\\d+.autoCompleteProductName_autocomplete_value", false));
		browser.waitExists(p);
		Browser.sleep(OrmsConstants.PAGELOADING_SYNC_TIME);
		//browser.clickGuiObject(".class", "Html.LI", ".text", new RegularExpression(productName, false), true);
		browser.clickGuiObject(".class", "Html.LI", ".text", new RegularExpression(productName, false));
		ajax.waitLoading();
	}
	
	public void setSupplierProductCode(String supplierProductCode,int index){
		browser.setTextField(".id", new RegularExpression("POItemView-\\d+.autoCompleteProductCode", false), supplierProductCode, true,index);
	}
	
	public void setQtyToOrder(String qty,int index){
		browser.setTextField(".id", new RegularExpression("POItemView-\\d+.quantityRequested", false), qty, true,index);
	}
	
	public void setDiscount(String discount){
		if(!StringUtil.isEmpty(discount)){
			browser.setTextField(".id",new RegularExpression("POView-\\d+.discountRateStr",false), discount);
		}
	}
	
	public void refreshPage(){
		browser.clickGuiObject(".class","Html.DIV",".className","footer");
		ajax.waitLoading();
	}
	public void setupPOSPurchaseOrderInfo(POSPurchaseOrderInfo orderInfo){
		this.setSupplierName(orderInfo.supplierName);
		ajax.waitLoading();
		this.setPurchaseDate(orderInfo.purchaseDate);
		this.setRequiredShippingDate(orderInfo.shippingDate);
		this.setStartShippingDate(orderInfo.startShippingDate);//Jane[2014-5-20]:Added by Jane
		this.setPaymentTerms(orderInfo.paymentTerms);
		this.setPaymentMethod(orderInfo.paymentMethod);
		this.setShippingMethod(orderInfo.shippingMethod);
		POSPurchaseOrderItemInfo orderItemInfo;
		for(int i=0; i<orderInfo.orderItemInfo.size(); i++){
			orderItemInfo = orderInfo.orderItemInfo.get(i);
			this.setProductName(orderItemInfo.productName,i);
			this.setQtyToOrder(orderItemInfo.ordered,i);
			this.refreshPage();
			if(isSizeListExist() && StringUtil.notEmpty(orderItemInfo.size))
				selectSize(orderItemInfo.size, i);
			if(isColorTextExist() && StringUtil.notEmpty(orderItemInfo.color))
				setColor(orderItemInfo.color, i);
			if(i != (orderInfo.orderItemInfo.size()-1)) {
				this.clickAdd();	
				ajax.waitLoading();
			}
		}
		
		if(!StringUtil.isEmpty(orderInfo.discount)){
			this.setDiscount(orderInfo.discount);
			this.refreshPage();
		}
	}

	public void clickAdd(){
		browser.clickGuiObject(".class","Html.A",".text",new RegularExpression("Add",false), true);
	}

	public void clickRemove(int index){
		browser.clickGuiObject(".class","Html.A",".text","Remove", true, index);
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class","Html.A",".text","OK");
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class","Html.A",".text","Cancel");
	}
	
	public void clickApply(){
		browser.clickGuiObject(".class","Html.A",".text","Apply");
	}
	
	public boolean isSizeListExist() {
		return browser.checkHtmlObjectDisplayed(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[2874\\]", false));
	}
	
	public void selectSize(String size, int i) {
		browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[2874\\]", false), size, i);
	}
	
	public boolean isColorTextExist() {
		return browser.checkHtmlObjectDisplayed(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[2873\\]", false));
	}
	
	public void setColor(String color, int i) {
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[2873\\]", false), color, i);
	}
	
	public String getErrorMessage() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "NOTSET");
		if(objs.length<1)
			throw new ItemNotFoundException("Could not find error message on page.");
		String err = objs[0].text();
		Browser.unregister(objs);
		return err;
	}
}
