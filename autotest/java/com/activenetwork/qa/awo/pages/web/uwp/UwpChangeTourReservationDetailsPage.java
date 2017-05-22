package com.activenetwork.qa.awo.pages.web.uwp;


import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @author Swang
 * This page display when click "Change Date/Transfer to Another Site" button in "Change Reservation Details"
 * Title: Change Reservation
 *
 */
public class UwpChangeTourReservationDetailsPage extends TourParticipantDetailsPage {

	private static UwpChangeTourReservationDetailsPage _instance = null;
	
	private static String pageTitle="Change Tour Reservation Details";

	public static UwpChangeTourReservationDetailsPage getInstance() {
		if (null == _instance)
			_instance = new UwpChangeTourReservationDetailsPage();

		return _instance;
	}

	public UwpChangeTourReservationDetailsPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.DIV",".text", new RegularExpression("Change Tour Reservation Details",false));
	}

	public void clickChangeTourReservationDetailsBtn(){
		browser.clickGuiObject(".id","continueshop",".text","Change Tour Reservation Details");
	}
	
	public boolean checkCancelChangeLinkExisting(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Cancel Change");
	}
	
	public void clickCancelChangeLink(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel Change",0);
	}

	public void clickCancelChangeLinkFromBottom() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel Change",1);
	}

	public void unSelectAgreementCheckBox(){
		browser.unSelectCheckBox(".id", "agreement");
	}

	/**
	 * 
	 */
	public void selectAgreementCheckBox() {
		browser.selectCheckBox(".id", "agreement");
	}

	/**
	 * 
	 */
	public boolean checkChangeTourResDetailsBtnExist() {
		return browser.checkHtmlObjectExists(".id", "continueshop", ".text", "Change Tour Reservation Details");
	}

	/**
	 * 
	 */
	public void verifyPageTitle() {
		String titleOnPage=this.getPageTitle();
		logger.info("Verify Page Title is '"+pageTitle+"'");
		if(!pageTitle.equals(titleOnPage)){
			throw new ErrorOnPageException("",pageTitle,titleOnPage);
		}
	}
	
	public String getPageTitle(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", "pagetitle");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Can't find Page Title DIV.");
		}
		String title=objs[0].text();
		Browser.unregister(objs);
		return title;
	}
	
	public IHtmlObject[] getTourDateObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".className", "tour_abstract_info");
		if(null==objs || objs.length<1){
			throw new ObjectNotFoundException("Tour date objs can't be found.");
		}
		return objs;
	}
	
	/**
	 * Get tour date information
	 * @return
	 */
	public List<String> getTourDates(){
		IHtmlObject[] objs = this.getTourDateObjs();
		List<String> tourDates = new ArrayList<String>();
		
		for(int i =0; i<objs.length; i++){
			tourDates.add(objs[i].text().trim());
		}
		
		Browser.unregister(objs);
		return tourDates;
	}
	
	/**
	 * Verify tour date information
	 * @param expectedTourDate
	 */
	public void verifyTourDates(List<String> expectedTourDates){
		List<String> actualTourDates = this.getTourDates();
		if(expectedTourDates.size()!=actualTourDates.size()){
			throw new ErrorOnPageException("The length of tour dates is wrong.", expectedTourDates.size(), actualTourDates.size());
		}
		
		for(int i=0; i<actualTourDates.size(); i++){
			if(!expectedTourDates.get(i).replaceAll("\\s*", "").equals(actualTourDates.get(i).replaceAll("\\s*", ""))){
				throw new ErrorOnPageException("Tour date is wrong!", expectedTourDates.get(i), actualTourDates.get(i));
			}
			logger.info("Successfully verify tour date:"+expectedTourDates.get(i));
		}
	}
	
}
