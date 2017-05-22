package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeDisplayCategory;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author ssong
 * @date Mar 31, 2011
 */
public class LicMgrAddOrEditDisplayCategoryWidget extends DialogWidget{

	private static LicMgrAddOrEditDisplayCategoryWidget _instance =	null;
	
	private LicMgrAddOrEditDisplayCategoryWidget(){
	}
	
	public static LicMgrAddOrEditDisplayCategoryWidget getInstance(){
		if(_instance == null){
			_instance = new LicMgrAddOrEditDisplayCategoryWidget();
		}
		return _instance;
	}
	
	public boolean exists(){
		return super.exists()&&browser.checkHtmlObjectExists(".class","Html.INPUT.text",".id",new RegularExpression("DisplayCategoryView-\\d+.description",false));
	}
	
	public void setDescription(String description){
		browser.setTextField(".id", new RegularExpression("DisplayCategoryView-\\d+.description",false), description);
	}
	
	public String getDescription(){
		return browser.getTextFieldValue(".id", new RegularExpression("DisplayCategoryView-\\d+.description",false));
	}
	
	public void setDisplayOrder(String displayOrder){
		browser.setTextField(".id", new RegularExpression("DisplayCategoryView-\\d+.displayOrder",false), displayOrder);
	}
	
	public String getDisplayOrder(){
		return browser.getTextFieldValue(".id", new RegularExpression("DisplayCategoryView-\\d+.displayOrder",false));
	}
	
	public void selectHiddenInSaleCheckBox(String hidden){
		if(hidden.equalsIgnoreCase("Yes")){
			browser.selectCheckBox(".id",new RegularExpression("DisplayCategoryView-\\d+.hiddenSales",false));
		}else{
			browser.unSelectCheckBox(".id",new RegularExpression("DisplayCategoryView-\\d+.hiddenSales",false));
		}
	}
	
	public String getHiddenInSale(){
		boolean temp = browser.isCheckBoxSelected(".id",new RegularExpression("DisplayCategoryView-\\d+.hiddenSales",false));
		if(temp){
			return "Yes";
		}
		return "No";
	}
	
	public String getAddedUser(){
		String text = browser.getObjectText(".class","Html.SPAN",".id",new RegularExpression("DisplayCategoryView-\\d+.createUserName",false));
		return text.substring("Added User".length()).trim();
	}
	
	public String getUpdatedUser(){
		String text = browser.getObjectText(".class","Html.SPAN",".id",new RegularExpression("DisplayCategoryView-\\d+.lastUpdatedUserName",false));
		return text.substring("Last Updated User".length()).trim();
	}
	
	public String getAddedLocation(){
		String text = browser.getObjectText(".class","Html.SPAN",".id",new RegularExpression("DisplayCategoryView-\\d+.createLocationName",false)); 
		return text.substring("Added Location".length()).trim();
	}
	
	public String getUpdatedLocation(){
		String text = browser.getObjectText(".class","Html.SPAN",".id",new RegularExpression("DisplayCategoryView-\\d+.lastUpdatedLocationName",false)); 
		return text.substring("Last Updated Location".length()).trim();
	}
	
	public String getAddedDate(){
		String text = browser.getObjectText(".class","Html.SPAN",".id",new RegularExpression("DisplayCategoryView-\\d+.createDate",false));
		return text.substring("Added Date & Time".length(),text.indexOf(":")-2).trim();
	}
	
	public String getUpdatedDate(){
		String text = browser.getObjectText(".class","Html.SPAN",".id",new RegularExpression("DisplayCategoryView-\\d+.lastUpdatedDate",false));
		return text.substring("Last Updated Date & Time".length(),text.indexOf(":")-2).trim();
	}
	
	/**
	 * @return error message on the top of the widget
	 */
	public String getErrorMsg(){
		return browser.getObjectText(".class","Html.DIV",".className","message msgerror");
	}
	
	public void setDisplayCategoryInfo(String description,String displayOrd,String hiddenInSale){
		this.setDescription(description);
		this.setDisplayOrder(displayOrd);
		this.selectHiddenInSaleCheckBox(hiddenInSale);
	}
	
	/**
	 * Verify whether display category detail info is correct with expected or not
	 * @param expectedDisplayCategory
	 */
	public void verifyDisplayCategoryDetailsInfo(PrivilegeDisplayCategory expectedDisplayCategory) {
		boolean pass = true;
		String temp = this.getDescription();
		if(!temp.equalsIgnoreCase(expectedDisplayCategory.description)){
			pass &= false;
			logger.error("Display Category Description "+temp+" not correct.");
		}
		temp = this.getDisplayOrder();
		if(!temp.equalsIgnoreCase(expectedDisplayCategory.displayOrder)){
			pass &= false;
			logger.error("Display Category Display Order "+temp+" not correct.");
		}
		temp = this.getHiddenInSale();
		if(!temp.equalsIgnoreCase(expectedDisplayCategory.hiddenInSalesFlow)){
			pass &= false;
			logger.error("Display Category Hiden in Sale "+temp+" not correct.");
		}
		temp = this.getAddedUser().replace(" ", "");
		if(!temp.equalsIgnoreCase(expectedDisplayCategory.addUser)){
			pass &= false;
			logger.error("Added User "+temp+" not correct.");
		}
		temp = this.getAddedLocation();
		if(!temp.equalsIgnoreCase(expectedDisplayCategory.addLocation)){
			pass &= false;
			logger.error("Added Location "+temp+" not correct.");
		}
		temp = this.getAddedDate();
		if(!MiscFunctions.compareResult("Added Date", expectedDisplayCategory.addDate, temp)){
			pass &= false;
			logger.error("Added Date "+temp+" not correct.");
		}
		if(expectedDisplayCategory.lastUpdatedUser.length() > 0) {
			temp = this.getUpdatedUser().replace(" ", "");
			if(temp.length() > 0) {
				if(!temp.equalsIgnoreCase(expectedDisplayCategory.lastUpdatedUser)){
					pass &= false;
					logger.error("Last Updated User "+temp+" not correct.");
				}
			}
			temp = this.getUpdatedLocation();
			if(temp.length() > 0) {
				if(!temp.equalsIgnoreCase(expectedDisplayCategory.lastUpdatedLocation)){
					pass &= false;
					logger.error("Last Updated Location "+temp+" not correct.");
				}
			}
			temp = this.getUpdatedDate();
			if(temp.length() > 0) {
				if(!MiscFunctions.compareResult("Last Updated Date", expectedDisplayCategory.lastUpdatedDate, temp)){
					pass &= false;
					logger.error("Last Updated Date "+temp+" not correct.");
				}
			}
		}
		
		if(!pass) {
			throw new ErrorOnPageException("Display Category detail information is incorrect.");
		} else {
			logger.info("Verify Display Category detail informatio correctly.");
		}
	}
}
