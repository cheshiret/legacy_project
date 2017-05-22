package com.activenetwork.qa.awo.pages.orms.inventoryManager.pos;
//package testAPI.pages.orms.inventoryManager.pos;
//
//import org.apache.xerces.impl.xpath.regex.RegularExpression;
//
//
//import runtime.HtmlObject;
///**  
// * @Description:  pos product search page.
// * @Preconditions:  
// * @SPEC:  
// * @Task#: Auto-972
// * @author jwang8  
// * @Date  Mar 29, 2012   
// */
//public class InvMgrPosSearchPage extends InvMgrCommonTopMenuPage{
//    private static InvMgrPosSearchPage instance = null;
//    
//    private InvMgrPosSearchPage(){}
//    
//    public static InvMgrPosSearchPage getInstance(){
//    	if(null == instance){
//    		instance = new InvMgrPosSearchPage();
//    	}
//    	return instance;
//    }
//	
//	public boolean exists() {
//		return browser.checkHtmlObjectExists(".id", "POSProductSetupView_LIST");
//	}
//	
//	/**
//	 * click add pos product.
//	 */
//	public void clickAddPosProduct(){
//		browser.clickGuiObject(".class", "Html.A", ".text", "Add POS Product");
//	}
//	
//	public void clickPOSPrintBarcodes() {
//		browser.clickGuiObject(".class", "Html.A", ".text", "POS Print Barcodes");
//	}
//	
//	public void selectAssignmentStatus(String status) {
//		browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.assignmentStatusID", status);
//	}
//	
//	public void setProductBarcode(String barcode) {
//		browser.setTextField(".id", "POSProductSetupSearchCriteria.productUPC", barcode);
//	}
//	
//	public void setProductName(String name) {
//		browser.setTextField(".id", "POSProductSetupSearchCriteria.productName", name);
//	}
//	
//	public void setProductGroup(String group) {
//		browser.setTextField(".id", "POSProductSetupSearchCriteria.productGroup", group);
//	}
//	
//	public void selectInventoryType(String type) {
//		browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.inventoryTypeID", type);
//	}
//	
//	public void setQuantityOnHand(String qty) {
//		browser.setTextField(".id", "POSProductSetupSearchCriteria.qtyOnHandForInput", qty);
//	}
//	
//	public void selectDateType(String type) {
//		browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.dateSearchType", type);
//	}
//	
//	public void setFromDate(String from) {
//		browser.setTextField(".id", "POSProductSetupSearchCriteria.startDate_ForDisplay", from);
//	}
//	
//	public void setToDate(String to) {
//		browser.setTextField(".id", "POSProductSetupSearchCriteria.endDate_ForDisplay", to);
//	}
//	
//	public void selectShowProductPackageOnly() {
//		browser.selectCheckBox(".id", "POSProductSetupSearchCriteria.prdPckg");
//	}
//	
//	public void unselectShowProductPackageOnly() {
//		browser.unSelectCheckBox(".id", "POSProductSetupSearchCriteria.prdPckg");
//	}
//	
//	public void clickGo() {
//		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
//	}
//	
//	public void clickAssignSelectedPOSProducts() {
//		browser.clickGuiObject(".class", "Html.A", ".text", "Assign Selected POS Products");
//	}
//	
//	public void clickUnassignSelectedPOSProducts() {
//		browser.clickGuiObject(".class", "Html.A", ".text", "Unassign Selected POS Products");
//	}
//	
//	private HtmlObject[] getPOSProductTableObject() {
//		HtmlObject objs[] = browser.getTableTestObject(".id", "POSProductSetupView_LIST");
//		if(objs.length < 1) {
//			throw new ItemNotFoundException("Can't find POS Product table object.");
//		}
//		
//		return objs;
//	}
//	
//	
//	
//}
