package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeReportCategory;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrAddOrEditReportCategoryWidget extends DialogWidget{
	private static LicMgrAddOrEditReportCategoryWidget _instance =	null;
	
	private LicMgrAddOrEditReportCategoryWidget(){
	}
	
	public static LicMgrAddOrEditReportCategoryWidget getInstance(){
		if(_instance == null){
			_instance = new LicMgrAddOrEditReportCategoryWidget();
		}
		return _instance;
	}
	
	public boolean exist(){
		return super.exists()&&browser.checkHtmlObjectExists(".class","Html.INPUT.text", ".id", new RegularExpression("DisplayCategoryView-\\d+.description",false));
	}
	
	public void setDescription(String description){
		browser.setTextField(".id", new RegularExpression("DisplayCategoryView-\\d+.description",false),description);
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
	
	public void setGroupNum(String groupNum){
		browser.setTextField(".id", new RegularExpression("DisplayCategoryView-\\d+.groupNum",false),groupNum);
	}
	
	public String getGroupNum(){
		return browser.getTextFieldValue(".id", new RegularExpression("DisplayCategoryView-\\d+.groupNum",false));
	}
	
	public String getAddedUser(){
		String text = browser.getObjectText(".class","Html.SPAN",".id",new RegularExpression("DisplayCategoryView-\\d+.createUserName",false));
		return text.substring("Added User".length()).trim();
	}
	
	public String getAddedLocation(){
		String text = browser.getObjectText(".class","Html.SPAN",".id",new RegularExpression("DisplayCategoryView-\\d+.createLocationName",false)); 
		return text.substring("Added Location".length()).trim();
	}
	
	
	public String getAddedDate(){
		String text = browser.getObjectText(".class","Html.SPAN",".id",new RegularExpression("DisplayCategoryView-\\d+.createDate",false));
		return text.substring("Added Date & Time".length(),text.indexOf(":")-2).trim();
	}
	
	
	public String getUpdateUserName(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.SPAN", ".id",new RegularExpression( "DisplayCategoryView-\\d+.lastUpdatedUserName",false));
		String userName=objs[0].getProperty(".text").split("Last Updated User")[1].trim();
		Browser.unregister(objs);
		return userName;
	}
	
	public String getUpdateLocation(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.SPAN", ".id", new RegularExpression( "DisplayCategoryView-\\d+.lastUpdatedLocationName",false));
		String location=objs[0].getProperty(".text").split("Last Updated Location")[1].trim();
		Browser.unregister(objs);
		return location;
	}
	
	public String getUpdateDate(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.SPAN", ".id", new RegularExpression("DisplayCategoryView-\\d+.lastUpdatedDate",false));
		String date=objs[0].getProperty(".text").split("Last Updated Date & Time")[1].trim();
		Browser.unregister(objs);
		return date;
	}
	
	
	public String getErrorMsg(){
		return browser.getObjectText(".class","Html.DIV",".className","message msgerror");
	}
	
	public void setReportCategoryInfo(String description,String displayOrd,String groupNum){
		this.setDescription(description);
		this.setDisplayOrder(displayOrd);
		this.setGroupNum(groupNum);
	}
	
	public boolean compareReportDetailInfo(PrivilegeReportCategory info) {
		boolean result = true;
		String temp = this.getDescription();
		if (!info.description.equals(temp)){
			logger.error("The expected description is " + info.description + ", but actual is " + temp);
			result &= false;
		}
		temp = this.getDisplayOrder();
		if (!info.displayOrder.equals(temp)) {
			logger.error("The expected display order is " + info.displayOrder + ", but actual is " + temp);
			result &= false;
		}
		temp = this.getGroupNum();
		if (!info.groupNum.equals(temp)) {
			logger.error("The expected group number is " + info.groupNum + ", but actual is " + temp);
			result &= false;
		}
		temp = this.getAddedUser();
		if(info.addUser!="" && !info.addUser.replace(", ", ",").equals(temp.replace(", ", ","))) {
			logger.error("The expected add user name is " + info.addUser + ", but actual is " + temp);
			result &= false;
		}
		temp = this.getAddedLocation();
		if(info.addLocation!="" && !info.addLocation.equals(temp)) {
			logger.error("The expected add location is " + info.addLocation + ", but actual is " + temp);
			result &= false;
		}
		temp = DateFunctions.formatDate(this.getAddedDate(),"EEE MMM dd yyyy");
		
		if(!temp.contains(info.addDate)) {
			logger.error("The expected add date is " + info.addDate + ", but actual is " + temp);
			result &= false;
		}
		if(info.updateUser.length() > 0) {
			temp = this.getUpdateUserName();
			if(temp.length() > 0) {
				if (!info.updateUser.replace(", ", ",").equals(temp.replace(", ", ","))) {
					logger.error("The expected update user name is " + info.updateUser + ", but actual is " + temp);
					result &= false;
				}
			}
			temp = this.getUpdateLocation();
			if(temp.length() > 0) {
				if (!info.updateLocation.equals(temp)) {
					logger.error("The expected update location is " + info.updateLocation + ", but actual is " + temp);
					result &= false;
				}
			}
			
			temp = DateFunctions.formatDate(this.getUpdateDate(),"EEE MMM dd yyyy");
			
			logger.debug("the temp update date is : " + temp);

			if(temp.length() > 0) {
				if (!temp.contains(info.updateDate)) {
					logger.error("The expected update date is " + info.updateDate + ", but actual is " + temp);
					result &= false;
				}
			}
		}
		
		return result;
	}
}
