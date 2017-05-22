/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeDisplayCategory;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @author szhou
 * @Date  Aug 18, 2011
 */
public class LicMgrAddOrEditDisplaySubCategoryWidget extends DialogWidget{
    private static LicMgrAddOrEditDisplaySubCategoryWidget _instance =	null;
	
	private LicMgrAddOrEditDisplaySubCategoryWidget(){
	}
	
	public static LicMgrAddOrEditDisplaySubCategoryWidget getInstance(){
		if(_instance == null){
			_instance = new LicMgrAddOrEditDisplaySubCategoryWidget();
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
	
	public String getAddedUserName(){
		IHtmlObject[] objs=browser.getHtmlObject(".id",new RegularExpression( "DisplayCategoryView-\\d+.createUserName",false));
		if(objs.length < 0){
			throw new ItemNotFoundException("Can't find Added User Name object!");
		}
		String userName=objs[0].getProperty(".text").split("Added User")[1].trim();
		Browser.unregister(objs);
		return userName;
	}
	
	public String getAddedLocation(){
		IHtmlObject[] objs=browser.getHtmlObject(".id", new RegularExpression( "DisplayCategoryView-\\d+.createLocationName",false));
		if(objs.length < 0){
			throw new ItemNotFoundException("Can't find Added Location object!");
		}
		String location =objs[0].getProperty(".text").split("Added Location")[1].trim();
		Browser.unregister(objs);
		return location;
	}
	
	public String getAddedDate(){
		IHtmlObject[] objs=browser.getHtmlObject(".id", new RegularExpression("DisplayCategoryView-\\d+.createDate",false));
		if(objs.length < 0){
			throw new ItemNotFoundException("Can't find Added Date & Time object!");
		}
		String date=objs[0].getProperty(".text").split("Added Date & Time")[1].trim();
		Browser.unregister(objs);
		return date;
	}
	
	public String getErrorMsg(){
		return browser.getObjectText(".class","Html.DIV",".className","message msgerror");
	}
	
	public void setDisplaySubCategoryInfo(String description,String displayOrd){
		this.setDescription(description);
		this.setDisplayOrder(displayOrd);
	}
	public String getUpdatedUser(){
		String text = browser.getObjectText(".id",new RegularExpression("DisplayCategoryView-\\d+.lastUpdatedUserName",false));
		return text.substring("Last Updated User".length()).trim();
	}
	
	public String getUpdatedLocation(){
		String text = browser.getObjectText(".id",new RegularExpression("DisplayCategoryView-\\d+.lastUpdatedLocationName",false)); 
		return text.substring("Last Updated Location".length()).trim();
	}
	
	public String getUpdatedDate(){
		String text = browser.getObjectText(".id",new RegularExpression("DisplayCategoryView-\\d+.lastUpdatedDate",false));
		return text.substring("Last Updated Date & Time".length(),text.indexOf(":")-2).trim();
	}
	
	public boolean compareDisplaySubCategoryDetailInfo(PrivilegeDisplayCategory info) {
		boolean result = true;
		String temp = this.getDescription();
		if (!info.description.equals(temp)){
			logger.error("The expected description is " +  info.description + ", but actual is " + temp);
			result &= false;
		}
		temp = this.getDisplayOrder();
		if (!info.displayOrder.equals(temp)) {
			logger.error("The expected display order is " + info.displayOrder + ", but actual is " + temp);
			result &= false;
		}
		temp = this.getAddedUserName().replace(" ", "");
		if (!info.addUser.equals(temp)) {
			logger.error("The expected add user name is " + info.addUser + ", but actual is " + temp);
			result &= false;
		}
		temp = this.getAddedLocation();
		if (!info.addLocation.equals(temp)) {
			logger.error("The expected add location is " + info.addLocation + ", but actual is " + temp);
			result &= false;
		}
		temp = DateFunctions.formatDate(this.getAddedDate(),"EEE MMM dd yyyy");
		if (!temp.contains(info.addDate)) {
			logger.error("The expected add date is " + info.addDate + ", but actual is " + temp);
			result &= false;
		}
		if(info.lastUpdatedUser.length() > 0) {
			temp = this.getUpdatedUser().replace(" ", "");
			if(temp.length() > 0) {
				if(!temp.equalsIgnoreCase(info.lastUpdatedUser)){
					logger.error("The expected Last Updated User is " + info.lastUpdatedUser + ", but actual is " + temp);
					result &= false;
				}
			}
			temp = this.getUpdatedLocation();
			if(temp.length() > 0) {
				if(!temp.equalsIgnoreCase(info.lastUpdatedLocation)){
					logger.error("The expected Last Updated Location is " + info.lastUpdatedLocation +", but actual is " + temp);
					result &= false;
				}
			}
			temp = this.getUpdatedDate();
			if(temp.length() > 0) {
				if (!temp.contains(info.addDate)) {
					logger.error("The expected Last Updated Date is " + info.lastUpdatedDate +", but actual is " + temp);
					result &= false;
				}
			}
		}
		
		return result;
	}
}
