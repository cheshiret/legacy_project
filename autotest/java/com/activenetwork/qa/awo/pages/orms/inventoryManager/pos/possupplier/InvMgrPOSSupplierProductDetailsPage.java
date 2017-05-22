package com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier;
//package testAPI.pages.orms.inventoryManager.pos.possupplier;
//
///**  
// * @Description:  pos supplier product details page.
// * @Preconditions:  
// * @SPEC:  
// * @Task#: Auto-972
// * @author jwang8  
// * @Date  Mar 30, 2012   
// */
//public class InvMgrPOSSupplierProductDetailsPage extends InvMgrCommonTopMenuPage{
//
//	public static InvMgrPOSSupplierProductDetailsPage instance = null;
//	
//	private InvMgrPOSSupplierProductDetailsPage(){};
//	
//	public static InvMgrPOSSupplierProductDetailsPage getInstance(){ 
//		if(null == instance){
//			instance = new InvMgrPOSSupplierProductDetailsPage();
//		}
//		return instance;
//	}
//	public boolean exists() {
//		return browser.checkHtmlObjectExists(".id", new RegularExpression("SupplierProductAssignmentLightView-\\d+\\.supplierUnitCost", false));
//	}
//	/**
//	 * set supplier product code.
//	 * @param productCode - product code.
//	 */
//	public void setSupplierProductCode(String productCode){
//		browser.setTextField(".id", new RegularExpression("SupplierProductAssignmentLightView-\\d+\\.supplierProductCode", false), productCode);
//	}
//	/**
//	 * set supplier unit cost.
//	 * @param unitCost - unit cose.
//	 */
//	public void setSupplierUnitCost(String unitCost){
//		browser.setTextField(".id", new RegularExpression("SupplierProductAssignmentLightView-\\d+\\.supplierUnitCost", false), unitCost);
//	}
//	/**
//	 * get supplier product code.
//	 * @return product code.
//	 */
//	public String getSupplierProductCode(){
//		return browser.getTextFieldValue(".id", new RegularExpression("SupplierProductAssignmentLightView-\\d+\\.supplierProductCode", false));
//	}
//	/**
//	 * get supplier unit cost.
//	 * @return supplier unit cost.
//	 */
//	public String getSupplierUnitCost(){
//		return browser.getTextFieldValue(".id", new RegularExpression("SupplierProductAssignmentLightView-\\d+\\.supplierUnitCost", false));
//	}
//	
//	/**
//	 * click ok button.
//	 */
//	public void clickOkButton(){
//		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
//	}
//	
//	/**
//	 * Compare pos assignment edit info.
//	 * @param productCode -  the product code.
//	 * @param unitCost - the unit cost.
//	 */
//	public boolean comparePosAssignmentEditInfo(String productCode,String unitCost){
//      boolean pass = true;
//      String actualResult = this.getSupplierProductCode();
//      if(!productCode.equals(actualResult)){
//    	  pass &= false;
//    	  logger.error("Expected product code is "+productCode+" but actual result is "+actualResult+"");
//      }
//      actualResult =  this.getSupplierUnitCost();
//      if(!unitCost.equals(actualResult)){
//    	  pass &= false;
//    	  logger.error("Expected unit cost is "+unitCost+" but actual result is "+actualResult+"");
//      }
//      return pass;
//    }
//}
