package com.activenetwork.qa.awo.pages.orms.licenseManager.vendor;

import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBondInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author jwang
 * @Date  Nov 15, 2011
 */
public class LicMgrEditVendorBondsWidget extends DialogWidget {

	public static LicMgrEditVendorBondsWidget _instance = null;
	private String prefix = "VendorBondView-";
	
	protected LicMgrEditVendorBondsWidget() {}
	
	public static LicMgrEditVendorBondsWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrEditVendorBondsWidget();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		boolean flag1 = super.exists();
		boolean flag2 = browser.checkHtmlObjectExists(".class", "Html.DIV",
				".text", "Edit Vendor Bondclose");
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
	
	public String getCreationDateTime(){
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Creation Date/Time*",false));
		IHtmlTable table = (IHtmlTable) objs[0];
		
		String result = table.getCellValue(0, 0).replaceAll("Creation Date/Time", "").trim();
		if(result.length()<1){
			result = "";
		}
		
		Browser.unregister(objs);
		return result;
	}
	
	public String getCreationUser(){
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Creation Date/Time*",false));
		IHtmlTable table = (IHtmlTable) objs[0];
		
		String result = table.getCellValue(0, 1).replaceAll("Creation User", "").trim();
		if(result.length()<1){
			result = "";
		}
		
		Browser.unregister(objs);
		return result;
	}
	
	public String getLastModifiedDateTime(){
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Creation Date/Time*",false));
		IHtmlTable table = (IHtmlTable) objs[0];

		String result = table.getCellValue(1, 0).replaceAll("Last Modified Date/Time", "").trim();
		if(result.length()<1){
			result = "";
		}
		
		Browser.unregister(objs);
		return result;
	}
	
	public String getLastModifiedUser(){
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Creation Date/Time*",false));
		IHtmlTable table = (IHtmlTable) objs[0];
		
		String result = table.getCellValue(1, 1).replaceAll("Last Modified User", "").trim();
		if(result.length()<1){
			result = "";
		}
		
		Browser.unregister(objs);
		return result;
	}
	
	public String getStatus(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "addedit_vendor_bond");
		IHtmlTable table = (IHtmlTable) objs[0];
		
		String status = table.getCellValue(0, 1).replaceAll("Status", "").trim();
		
		if(status.length()<1){
			status = "";
		}
		
		Browser.unregister(objs);
		return status;
	}
	
	public String getBondIssuer(){
		return browser.getDropdownListValue(".id", 
				new RegularExpression(prefix+"\\d+\\.issuer.id", false));
		
	}
	
	public String getBondType(){
		return browser.getDropdownListValue(".id", 
				new RegularExpression(prefix+"\\d+\\.type", false));
		
	}

	public String getBondNum() {
		return browser.getTextFieldValue(".id", 
				new RegularExpression(prefix+"\\d+\\.bondNumber", false));
	}
	
	public String getBondAmount() {
		return browser.getTextFieldValue(".id", 
				new RegularExpression(prefix+"\\d+\\.amount:ZERO_TO_NULL", false));
	}
	
	public String getEffectiveFrom() {
		return browser.getTextFieldValue(".id", 
				new RegularExpression(prefix+"\\d+\\.effectiveFrom_ForDisplay", false));
	}
	
	public String getEffectiveTo() {
		return browser.getTextFieldValue(".id", 
				new RegularExpression(prefix+"\\d+\\.effectiveTo_ForDisplay", false));
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
		   return verifyAutomaticDateCorrection((IText)browser.getTextField(".id", new RegularExpression(prefix+"\\d+\\.effectiveTo_ForDisplay", false))[0], invalidDates);
	   }
	   
	   public VendorBondInfo getBondInfo(){
		   VendorBondInfo bondInfo = new VendorBondInfo();
		   bondInfo.status = getStatus();
		   bondInfo.issuer = getBondIssuer();
		   bondInfo.type = getBondType();
		   bondInfo.bondNum = getBondNum();
		   bondInfo.bondAmount = getBondAmount();
		   bondInfo.effectiveFrom = getEffectiveFrom();
		   bondInfo.effectiveTo = getEffectiveTo();
		   bondInfo.creationDateTime = getCreationDateTime();
		   bondInfo.creationUser = getCreationUser();
		   bondInfo.lastModifiedDateTime = getLastModifiedDateTime();
		   bondInfo.lastModifiedUser = getLastModifiedUser();		   
		   
		   return bondInfo;
	   }
	   
	   public boolean comapareBondInfo(VendorBondInfo bondInfo, VendorBondInfo comparedInfo){
			boolean flag = true;
			
			if(!comparedInfo.status.equals(bondInfo.status)) {
				logger.error("Bond status display error! The expect value is:" + bondInfo.status);
				flag &= false;
			}
			
			if(!comparedInfo.issuer.startsWith(bondInfo.issuer)) {
				logger.error("Bond issuer display error! The expect value is:" + bondInfo.issuer);
				flag &= false;
			}
			
			if(!comparedInfo.type.equals(bondInfo.type)) {
				logger.error("Bond type display error! The expect value is:" + bondInfo.type);
				flag &= false;
			}
			
			if(!comparedInfo.bondNum.equals(bondInfo.bondNum)) {
				logger.error("Bond num display error! The expect value is:" + bondInfo.bondNum);
				flag &= false;
			}
			
			if(!comparedInfo.bondAmount.replaceAll("\\$", "").equals(bondInfo.bondAmount)) {
				logger.error("Bond amount display error! The expect value is:" + bondInfo.bondAmount);
				flag &= false;
			}
			
			if(bondInfo.assignedAgentsNum.length()>1 &&
	          !comparedInfo.assignedAgentsNum.equals(bondInfo.assignedAgentsNum)) {
				logger.error("Bond assignedAgentsNum display error! The expect value is:" + bondInfo.assignedAgentsNum);
				flag &= false;
			}
			
			if(!DateFunctions.formatDate(comparedInfo.effectiveFrom,"M/d/yyyy").equals(bondInfo.effectiveFrom)) {
				logger.error("Bond effectiveFrom date display error! The expect value is:" + bondInfo.effectiveFrom);
				flag &= false;
			}
			
			if(bondInfo.effectiveTo.length()>1 &&
			   !DateFunctions.formatDate(comparedInfo.effectiveTo,"M/d/yyyy").equals(bondInfo.effectiveTo)) {
				logger.error("Bond effectiveTo date display error! The expect value is:" + bondInfo.effectiveTo);
				flag &= false;
			}
			
			// only verify date
			if(bondInfo.creationDateTime.length()>1 &&
			   !comparedInfo.creationDateTime.substring(0, 15).equalsIgnoreCase(bondInfo.creationDateTime.substring(0, 15))){
				logger.error("Bond creationDateTime date display error! The expect value is:" + bondInfo.creationDateTime);
				flag &= false;
			}
			
			if(bondInfo.creationUser.length()>1 &&
			  !comparedInfo.creationUser.equalsIgnoreCase(bondInfo.creationUser)){
				logger.error("Bond creationUser date display error! The expect value is:" + bondInfo.creationUser);
				flag &= false;
			}
			
			// only verify date
			if(bondInfo.lastModifiedDateTime.length()>1 &&
			   !comparedInfo.lastModifiedDateTime.substring(0, 15).equalsIgnoreCase(bondInfo.lastModifiedDateTime.substring(0, 15))){
				logger.error("Bond lastModifiedDateTime date display error! The expect value is:" + bondInfo.lastModifiedDateTime);
				flag &= false;
			}
			
			if(bondInfo.lastModifiedUser.length()>1 &&
			   !comparedInfo.lastModifiedUser.equalsIgnoreCase(bondInfo.lastModifiedUser)){
				logger.error("Bond lastModifiedUser date display error! The expect value is:" + bondInfo.lastModifiedUser);
				flag &= false;
			}
			
			return flag;
		}

}
