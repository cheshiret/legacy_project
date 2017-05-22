/*
 * Created on Feb 1, 2010
 */
package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author QA
 * You can access this page from user account panel. using this class both for notification list
 * and details page.
 */
public class UwpAvailabilityNotificationPage extends UwpPage {
  	private static UwpAvailabilityNotificationPage _instance = null;

	public static UwpAvailabilityNotificationPage getInstance() {
		if (null == _instance)
			_instance = new UwpAvailabilityNotificationPage();

		return _instance;
	}

	public UwpAvailabilityNotificationPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "availabilityNotifications",".className","accountside in");
	}
	
	/**
	 * Retrieve the notification message on page.
	 * @return
	 */
	public String getMessage() {
	  	IHtmlObject[] objs=browser.getHtmlObject(".id","msg1");
	  	String text=objs[0].getProperty(".text").toString();
	  	Browser.unregister(objs);
	  	return text;
	}
	
	/** Click on Back to Previous page link. */
	public void clickBackToPrevious() {
	  	browser.clickGuiObject(".id","msgprev");
	}
	
	/** Click on Stop email to remove notification.*/
	public void removeNotification() {
	  	browser.clickGuiObject(".id","canex");
	}
	
	/** wait for availability detail page to load*/
	public void availabilityDetailPageWaitExists(){
		browser.searchObjectWaitExists(".class","Html.A",".id","canex");
	}
	
	/** Click on button to go back to availability notification list page from details page.*/
	public void goBackToAVNListPg() {
	  	browser.clickGuiObject(".class","Html.BUTTON",".text","Back to " +
	  							"Availability Notifications");
	}
	
	/**
	 * Go to camp details page by given name.
	 * @param park
	 */
	public void gotoCampgroundDetailPg(String park) {
	  	RegularExpression reg=new RegularExpression(park+"\\, .*", false);
	  	IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.A", ".text", reg);
	  	
	  	((ILink) objs[0]).click();
	  	Browser.unregister(objs);
	}
	
	/**
	 * Go to camp details page by given index, start from 1.
	 * @param index - index of park
	 */
	public void gotoCampgroundDetailPg(int index) {
	  	RegularExpression reg=new RegularExpression(".*campgroundDetails\\.do.*", false);
	  	IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.A", ".href", reg);
	  	
	  	((ILink) objs[index - 1]).click();
	  	Browser.unregister(objs);
	}
	
	/**
	 * Go to availablility notification details by given index, start from 1.
	 * @param index - index of notification
	 */
	public void gotoAVNDetails(int index) {
	  	IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.A", ".text", "see details");
	  	
	  	((ILink) objs[index - 1]).click();
	  	Browser.unregister(objs);
	}
	
	/**
	 * Get the notification index by given park name and date.
	 * @param park - park name
	 * @param date - arrival date
	 * @return - record index, start from 1
	 */
	public int getNotificationIndex(String park, String date) {
	  	date=DateFunctions.formatDate(date,"EEE, MMM dd, yyyy");
	  	park=park.toUpperCase();
	  	int toReturn=-1;
	  	IHtmlObject[] table=browser.getTableTestObject(".id", "notifications");
	  	
	  	for(int i = 0; i < ((IHtmlTable) table[0]).rowCount(); i++) {
	  	  	if(((IHtmlTable) table[0]).getCellValue(i, 1).indexOf(park) != -1) {
	  	  	  	if(((IHtmlTable) table[0]).getCellValue(i, 3).indexOf(date) != -1){
	  	  	  	  	toReturn = i;
	  	  	  	}
	  	  	}
	  	}
	  	if(toReturn==-1)
	  	  	throw new ItemNotFoundException("Record not found by given park: "
	  	  	    										+park+" on date: "+date);
	  	
	  	Browser.unregister(table);
	  	return toReturn;
	}
}
