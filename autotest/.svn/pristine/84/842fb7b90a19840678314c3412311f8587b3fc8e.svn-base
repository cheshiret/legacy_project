package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;

public class UwpCampingLotteryDetailsPage1  extends UwpPage {
	
	private static UwpCampingLotteryDetailsPage1 _instance = null;

	public static UwpCampingLotteryDetailsPage1 getInstance() {
		if (null == _instance)
			_instance = new UwpCampingLotteryDetailsPage1();

		return _instance;
	}

	public UwpCampingLotteryDetailsPage1() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "continueshop");
	}
	/**
	 * Click on Continue to go to lottery application details page 2.
	 */
	public void clickContinue() {
		browser.clickGuiObject(".id", "continueshop");
	}
	
	/**Go to facility details page.*/
	public void clickSeeDetails() {
		browser.clickGuiObject(".class","Html.A",".text","See Details");
	}

	/**Fill in Arrival Date.*/
	public void setArrivalDate(String date) {
		browser.setTextField(".id","arrivaldateid0",date);
	}
	
	/**
	 * Retrieve arrival date.
	 * @return - retrieved arrival date
	 */
	public String getArrivalDate() {
		IHtmlObject[] objs = browser.getHtmlObject(".id","arrivaldateid0");
		String date = objs[0].getProperty(".value");
		Browser.unregister(objs);
		return date;
	}
	
	/**Fill in Length of stay.*/
	public void setLengthOfStay(String length) {
		browser.setTextField(".id","lengthofstayid0",length);
	}
	
	/**
	 * Retrieve the length of stay.
	 * @return - length of stay
	 */
	public String getLengthOfStay() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "lengthofstayid0");
		String length = objs[0].getProperty(".value");

		Browser.unregister(objs);
		return length;
	}
	
	/**Select facility.*/
	public void selectApplyFor(String facility) {
		browser.selectDropdownList(".id", "facilityid", facility);
	}
	
	/**Select facility loop.*/
	public void selectLoop(String loop) {
		browser.selectDropdownList(".id", "loopid0", loop);
	}
	
	/**
	 * Set up lottery order details for page one.
	 * @param bd - booking data
	 * @param isUpdate - whether or not update the details in page one, will set as default if not update
	 */
	public void setPageOneDetails(BookingData bd, boolean isUpdate) {
		if(isUpdate) {
			if(bd.park !=null && bd.park.length()>0)
				this.selectApplyFor(bd.park);
			if(bd.arrivalDate !=null && bd.arrivalDate.length()>0)
				this.setArrivalDate(bd.arrivalDate);
			if(bd.lengthOfStay !=null && bd.lengthOfStay.length()>0)
				this.setLengthOfStay(bd.lengthOfStay);
			if(bd.area !=null && bd.area.length()>0)
				this.selectLoop(bd.area);
		}
	}
	
	/**
	 * Do nothing when setting up lottery order details for page one.
	 * @param bd - booking data
	 */
	public void setPageOneDetails(BookingData bd) {
		setPageOneDetails(bd, false);
	}
}
