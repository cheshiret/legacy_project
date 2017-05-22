package com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.pospurchaseorder;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPurchaseOrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPurchaseOrderItemInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.util.KeyInput;
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
public class InvMgrPurchaseOrderDetailsPage extends InvMgrCommonTopMenuPage {

    public static InvMgrPurchaseOrderDetailsPage instance = null;
    
    private InvMgrPurchaseOrderDetailsPage(){};
    
    public static InvMgrPurchaseOrderDetailsPage getInstance(){
    	if(null == instance){
    		instance = new InvMgrPurchaseOrderDetailsPage();
    	}
		return instance;
    }
    
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.Table", ".id", new RegularExpression("gridItems",false));
	}
	
	public String getStatus(){
		String status = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("POView-\\d+.status:CB_TO_NAME", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find status.");
		}
		status = objs[0].getProperty(".value").toString();
		return status;
	}
	
	public String getPONum(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("POView-\\d+.id:ZERO_TO_NEW", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find PO#.");
		}
		String poNum = objs[0].getProperty(".value").toString();
		return poNum;
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
	public void clickPrint(){
		browser.clickGuiObject(".class","Html.A",".text","Print Purchase Order");
	}

	public String getSupplierNameInTop(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("POView-\\d+.supplierName", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find supplier name.");
		}
		String supplierName = objs[0].getProperty(".value").toString();
		return supplierName;
	}
	
	public String getSupplierNameInDetail(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("POView-\\d+.supplierLocation", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find supplier name.");
		}
		String supplierName = objs[0].getProperty(".value").toString();
		return supplierName;
	}

	public String getItemOrdered(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("POView-\\d+.itemsOrdered.*", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find number of Item Ordered.");
		}
		String itemOrdered = objs[0].getProperty(".value").toString();
		return itemOrdered;
	}
	
	public String getPurchaseDate(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("POView-\\d+.purchaseDate_ForDisplay", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find purchase date.");
		}
		String purchaseDate = objs[0].getProperty(".value").toString();
		return purchaseDate;
	}
	
	public String getReqShippingDate(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("POView-\\d+.reqShippingDate_ForDisplay", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find required shipping date.");
		}
		String shippingDate = objs[0].getProperty(".value").toString();
		return shippingDate;
	}
	
	public String getPaymentTerms(){
//		HtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("AttributeValuesWrapper-\\d+.attr\\[10431\\]", false));
		IHtmlObject[] objs = browser.getDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+.attr\\[10431\\]", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find Payment Terms.");
		}
//		String paymentTerms = objs[0].getProperty(".value").toString();  
		String paymentTerms = ((ISelect)objs[0]).getSelectedText();  
		return paymentTerms;
	}
	
	public String getPaymentMethod(){
//		HtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("AttributeValuesWrapper-\\d+.attr\\[10432\\]", false));
		IHtmlObject[] objs = browser.getDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+.attr\\[10432\\]", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find Payment Method.");
		}
//		String paymentMethod = objs[0].getProperty(".value").toString();
		String paymentMethod = ((ISelect)objs[0]).getSelectedText();  
		return paymentMethod;
	}
	
	public String getShippingMethod(){
//		HtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("AttributeValuesWrapper-\\d+.attr\\[10433\\]", false));
		IHtmlObject[] objs = browser.getDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+.attr\\[10433\\]", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find Shipping Method.");
		}
//		String shippingMethod = objs[0].getProperty(".value").toString();
		String shippingMethod = ((ISelect)objs[0]).getSelectedText();
		return shippingMethod;
	}
	
	public String getProductName(int index){
		return browser.getTextFieldValue(".id", new RegularExpression("POItemView-\\d+.autoCompleteProductName", false), index);
	}
	
	public String getSupplierProductCode(int index){                   
		return browser.getTextFieldValue(".id", new RegularExpression("POItemView-\\d+.autoCompleteProductCode", false), index);
	}
	
	public String getSupplierUnitCost(int index){
		return browser.getTextFieldValue(".id", new RegularExpression("POItemView-\\d+.unitCost", false), index);
	}

	public String getQtyoOrder(int index){
		return browser.getTextFieldValue(".id", new RegularExpression("POItemView-\\d+.quantityRequested", false), index);
	}

	public String getDiscount(){
		return browser.getTextFieldValue(".id", new RegularExpression("POView-\\d+.discountRateStr", false));
	}
	
	public ArrayList<POSPurchaseOrderItemInfo> getOrderItemInfo(){
		ArrayList<POSPurchaseOrderItemInfo> itemInfoList = new ArrayList<POSPurchaseOrderItemInfo>();
		POSPurchaseOrderItemInfo orderItemInfo;

		int itemNum = Integer.valueOf(this.getItemOrdered().substring(0, 1));
		for(int i=0; i< itemNum; i++){
			orderItemInfo = new POSPurchaseOrderItemInfo();
			orderItemInfo.productName = this.getProductName(i);
			orderItemInfo.supplierProductCode = this.getSupplierProductCode(i);
			orderItemInfo.costPerUnit = this.getSupplierUnitCost(i);
			orderItemInfo.ordered = this.getQtyoOrder(i);
			BigDecimal unitCost = new BigDecimal(orderItemInfo.costPerUnit).setScale(2);
			BigDecimal qutility = new BigDecimal(orderItemInfo.ordered).setScale(2);
			orderItemInfo.subTotalForItem = unitCost.multiply(qutility).setScale(2).toString();
			itemInfoList.add(orderItemInfo);
		}
		return itemInfoList;
	}
	
	private String prefix = "POView-\\d+.";
	public void setPurchaseDate(String purchaseDate){
		browser.setTextField(".id", new RegularExpression(prefix+"purchaseDate_ForDisplay", false), purchaseDate, true);
	}
	
	public void setRequiedShippingDate(String shippingDate){
		browser.setTextField(".id", new RegularExpression(prefix+"reqShippingDate_ForDisplay", false), shippingDate, true);
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
	
	public void setProductName(String productName){
		browser.setTextField(".id", new RegularExpression("POItemView-\\d+.autoCompleteProductName", false), productName, true);
		Property[] p = Property.toPropertyArray(".id", new RegularExpression("POItemView-\\d+.autoCompleteProductName_autocomplete_value", false));
		browser.waitExists(p);
		browser.clickGuiObject(".class", "Html.DIV", ".text", new RegularExpression(productName, false), true);
		ajax.waitLoading();
	}
	
	public void setSupplierProductCode(String supplierProductCode){
		browser.setTextField(".id", new RegularExpression("POItemView-\\d+.autoCompleteProductCode", false), supplierProductCode, true);
	}
	
	public void setQtyToOrder(String qty){
		browser.setTextField(".id", new RegularExpression("POItemView-\\d+.quantityRequested", false), qty, true);
	}
	
	public void setDiscount(String discount){
		browser.setTextField(".id", new RegularExpression(prefix+"discountRateStr", false), discount, true);
	}
	
	/**
	 * 
	 * @param orderInfo
	 * @param AddOrRemove
	 * @param index using for remove order item.when want to add order item, no need to set this value.
	 */
	public void editPOSPurchaseOrderInfo(POSPurchaseOrderInfo orderInfo, String AddOrRemove, int index){
		if(null != orderInfo.purchaseDate && !orderInfo.purchaseDate.equals(this.getPurchaseDate())){
			this.setPurchaseDate(orderInfo.purchaseDate);
		}
		
		if(null != orderInfo.shippingDate && !orderInfo.shippingDate.equals(this.getReqShippingDate())){
			this.setRequiedShippingDate(orderInfo.shippingDate);
		}
		
		if(null != orderInfo.paymentTerms && !orderInfo.paymentTerms.equals(this.getPaymentTerms())){
			this.setPaymentTerms(orderInfo.paymentTerms);
		}
		
		if(null != orderInfo.paymentMethod && !orderInfo.paymentMethod.equals(this.getPaymentMethod())){
			this.setPaymentMethod(orderInfo.paymentMethod);
		}
		
		if(null != orderInfo.shippingMethod && !orderInfo.shippingMethod.equals(this.getShippingMethod())){
			this.setShippingMethod(orderInfo.shippingMethod);
		}
		
		if("Add".equals(AddOrRemove)){
			this.addOrderItem(orderInfo.orderItemInfo);
		} else if("Remove".equals(AddOrRemove)){
			this.clickRemove(index);
			ajax.waitLoading();
		}

		if(null != orderInfo.discount && !"".equals(orderInfo.discount)){
			this.setDiscount(orderInfo.discount);
			browser.inputKey(KeyInput.get(KeyInput.TAB));
			ajax.waitLoading();
		}
	}
	
	public void addOrderItem(ArrayList<POSPurchaseOrderItemInfo> orderItemInfoList){
		if(orderItemInfoList.size() > 0){
			POSPurchaseOrderItemInfo orderItemInfo;
			for(int i=0; i<orderItemInfoList.size(); i++){
				if(i != (orderItemInfoList.size()-1)) {
					this.clickAdd();
					ajax.waitLoading();
				}
				orderItemInfo = orderItemInfoList.get(i);
				this.setProductName(orderItemInfo.productName);
				this.setQtyToOrder(orderItemInfo.ordered);
				browser.inputKey(KeyInput.get(KeyInput.TAB));
				ajax.waitLoading();
			}
		}
	}
	
	public void clickAdd(){
		browser.clickGuiObject(".class","Html.A",".text",new RegularExpression("Add",false), true);
	}

	public void clickRemove(int index){
		browser.clickGuiObject(".class","Html.A",".text",new RegularExpression("Remove",false), true, index - 1);
	}
	
	public String getSubtotal(){
		ArrayList<POSPurchaseOrderItemInfo> itemInfoList = this.getOrderItemInfo();
		BigDecimal subTotal = new BigDecimal("0.00").setScale(2);
		for(int i=0; i< itemInfoList.size(); i++){
			subTotal = subTotal.add(new BigDecimal(itemInfoList.get(i).subTotalForItem));
		}
		return subTotal.setScale(2).toString();
	}
	
	public String getTotal(){
		String text = browser.getObjectText(".class","Html.DIV",".id","gridItems");
		return text.substring(text.lastIndexOf("$")+1);
	}
	
	public POSPurchaseOrderInfo getOrderInfo(){
		POSPurchaseOrderInfo purchaseOrderInfo = new POSPurchaseOrderInfo();
		purchaseOrderInfo.poNum = this.getPONum();
		purchaseOrderInfo.supplierName = this.getSupplierNameInTop();
		purchaseOrderInfo.itemOrdered = this.getItemOrdered();
		purchaseOrderInfo.status = this.getStatus();
		purchaseOrderInfo.purchaseDate = this.getPurchaseDate();
		purchaseOrderInfo.shippingDate = this.getReqShippingDate();
		purchaseOrderInfo.paymentTerms = this.getPaymentTerms();
		purchaseOrderInfo.paymentMethod = this.getPaymentMethod();
		purchaseOrderInfo.shippingMethod = this.getShippingMethod();
		purchaseOrderInfo.orderItemInfo = this.getOrderItemInfo();
		purchaseOrderInfo.subTotal = this.getSubtotal();
		purchaseOrderInfo.total = this.getTotal();
		purchaseOrderInfo.discount = this.getDiscount();
		return purchaseOrderInfo;
	}
	
	public String getStartShippingDate(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("POView-\\d+.startShippingDate_ForDisplay", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find start shipping date.");
		}
		String shippingDate = objs[0].getProperty(".value").toString();
		return shippingDate;
	}
	
	public String getBuyer(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("POView-\\d+.creationInfo.userName", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find buyer.");
		}
		String purchaseDate = objs[0].getProperty(".value").toString();
		return purchaseDate;
	}
	
	public String getSize(int index){
		return browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[2874\\]", false), index);
	}
	
	public String getColor(int index){
		return browser.getTextFieldValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[2873\\]", false), index);
	}
	
	public String getOrderingAddr(){
		return browser.getTextAreaValue(".id", new RegularExpression("POView-\\d+\\.shippingAddress", false));
	}
	
	public String getShippingAddr(){
		return browser.getTextAreaValue(".id", new RegularExpression("POView-\\d+\\.orderingAddress", false));
	}
	
	public boolean isSizeListExist() {
		return browser.checkHtmlObjectDisplayed(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[2874\\]", false));
	}
	
	public void selectSize(String size, int i) {
		if(StringUtil.isEmpty(size))
			browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[2874\\]", false), 0);
		else
			browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[2874\\]", false), size, i);
	}
	
	public boolean isColorTextExist() {
		return browser.checkHtmlObjectDisplayed(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[2873\\]", false));
	}
	
	public void setColor(String color, int i) {
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[2873\\]", false), color, i);
	}
	
	public void setSartShippingDate(String shippingDate){
		browser.setTextField(".id", new RegularExpression(prefix+"startShippingDate_ForDisplay", false), shippingDate, true);
	}
}
