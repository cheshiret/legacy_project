package com.activenetwork.qa.awo.pages.orms.licenseManager.vendor;

import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBondInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author jwang
 * @Date  Nov 14, 2011
 */
public class LicMgrAddVendorBondsWidget extends DialogWidget {

	public static LicMgrAddVendorBondsWidget _instance = null;
	private String prefix = "VendorBondView-";
	
	protected LicMgrAddVendorBondsWidget() {}
	
	public static LicMgrAddVendorBondsWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrAddVendorBondsWidget();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		boolean flag1 = super.exists();
		boolean flag2 = browser.checkHtmlObjectExists(".class", "Html.DIV",
				".text", "Add Vendor Bondclose");
		return flag1 && flag2;
	}
	
	public void selectBondIssuer(String issuer){
		browser.selectDropdownList(".id", 
				new RegularExpression(prefix+"\\d+\\.issuer.id", false), 
				issuer);
		
	}
	
	public void selectBondType(String type){
		browser.selectDropdownList(".id", 
				new RegularExpression(prefix+"\\d+\\.type", false), 
				type);
		
	}

	public void setBondNum(String bondNum) {
		browser.setTextField(".id", 
				new RegularExpression(prefix+"\\d+\\.bondNumber", false), 
				bondNum);
	}
	
	public void setBondAmount(String amount) {
		browser.setTextField(".id", 
				new RegularExpression(prefix+"\\d+\\.amount:ZERO_TO_NULL", false), 
				amount);
	}
	
	public void setEffectiveFrom(String fromDate) {
		browser.setTextField(".id", 
				new RegularExpression(prefix+"\\d+\\.effectiveFrom_ForDisplay", false), 
				fromDate);
	}
	
	public void setEffectiveTo(String toDate) {
		browser.setTextField(".id", 
				new RegularExpression(prefix+"\\d+\\.effectiveTo_ForDisplay", false), 
				toDate);
	}
	
	/**
	 * This method is just for catching warning message when setting up bind info
	 * @param bondInfo
	 */
	public void setBondInfo(VendorBondInfo bondInfo){
		selectBondIssuer(bondInfo.issuer);
		selectBondType(bondInfo.type);
		setBondNum(bondInfo.bondNum);
		setBondAmount(bondInfo.bondAmount);
		setEffectiveFrom(bondInfo.effectiveFrom);
		setEffectiveTo(bondInfo.effectiveTo);
	}
	
	public void clickOK(){
		clickButtonByText("OK");
	}
	
	public void clickCancel(){
		clickButtonByText("Cancel");
	}
	
	/**
	 * Get the error message displayed at the widget top
	 * @return
	 */
	public String getErrorMsg() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".id", "NOTSET");
		String errorMsg = "";
		if(objs.length > 0) {
			errorMsg = objs[0].getProperty(".text").trim();
		}
		
		return errorMsg;
	}
	
	
	/**
	    * Verify effective from date component works correctly
	    * @param invalidDates
	    * @return
	    */
	   public boolean verifyEffectiveFromDateFieldValid(String invalidDates[]) {
		   return verifyAutomaticDateCorrection((IText)browser.getTextField(".id", new RegularExpression(prefix+"\\d+\\.effectiveFrom_ForDisplay", false))[0], invalidDates);
	   }
	   
	   /**
	    * Verify effective to date component works correctly
	    * @param invalidDates
	    * @return
	    */
	   public boolean verifyEffectiveToDateFieldValid(String invalidDates[]) {
		   return this.verifyAutomaticDateCorrection((IText)browser.getTextField(".id", new RegularExpression(prefix+"\\d+\\.effectiveTo_ForDisplay", false))[0], invalidDates);
	   }
}
