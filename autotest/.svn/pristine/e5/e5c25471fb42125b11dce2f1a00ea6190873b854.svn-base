package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @author Swang
 * This page display when click "Change Date/Transfer to Another Site" button in "Change Reservation Details"
 * Title: Change Reservation
 *
 */
public class UwpChangeReservationPage extends UwpPage {

	private static UwpChangeReservationPage _instance = null;

	public static UwpChangeReservationPage getInstance() {
		if (null == _instance)
			_instance = new UwpChangeReservationPage();

		return _instance;
	}

	public UwpChangeReservationPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.DIV",".id", "siteTransferComponent");
	}

	/**
	 * Set "Enter Site Number to Search" text field value
	 * @param siteNum
	 */
	public void setSiteNum(String siteNum){
		browser.setTextField(".id", "siteSearchInput", siteNum);
	}

	/**
	 * Select "Pick one from the Similar Available Sites" drop down option
	 * @param siteNum
	 */
	public void selectAvailableSite(String availableSite){
		//james[20130916]: remove extra spaces
		availableSite=availableSite.replaceAll(" +", " ");
		browser.selectDropdownList(".id", "AlternativeSites", availableSite,true);
	}

	/**
	 * Click "Search Site" button
	 */
	public void clickSearchSite(){
		browser.clickGuiObject(".id", "siteTransferSearchBtn", ".text", "Search Site");
	}

	/**
	 * Set "Arrival date" text field
	 * @param arrivalDate
	 */
	public void setArrivalDate(String arrivalDate){
		browser.setTextField(".id", "arrivaldate", arrivalDate);
	}

	/**
	 * Set "Length of stay" text field
	 * @param lengthOfStay
	 */
	public void setLengthOfStay(String lengthOfStay){
		browser.setTextField(".id", "lengthOfStay", lengthOfStay);
	}

	/**
	 * Click "Book these Dates" button
	 */
	public void clickBookTheseDays(){
		Property[] properties=new Property[ ]{
				new Property(".id", "btnbookdates"),
				new Property(".type","submit")
		};
		browser.clickGuiObject(properties);
	}

	public void searchSiteViaSiteNum(String siteNum){
		this.setSiteNum(siteNum);
		this.clickSearchSite();
		waitForTransferToSiteInfoDisplay(siteNum);
		logger.info("Successfully display transfer site(Site Num:"+siteNum+") info");
	}
	
	public void searchSiteViaAvailableSite(String siteName, String siteNum, String availableSite){
		this.selectAvailableSite(availableSite);
		this.waitLoading();
		waitForTransferToSiteInfoDisplay(siteNum);
		logger.info("Successfully display transfer site(Site Num:"+siteNum+") info");
	}

	public void waitForTransferToSiteInfoDisplay(String siteNo){
		RegularExpression regx = new RegularExpression("^Site, ?Loop: ?"+siteNo+",.*", false);
		Property[] p1=Property.toPropertyArray(".class","Html.DIV",".id","sitenamearea",".text", regx);
		
		//Sara[8/29/2013]: In RA website, no TR object is under previous parent objects
//		Property[] p2=Property.toPropertyArray(".class", "Html.TR", ".text", regx);
//		browser.searchObjectWaitExists(Property.atList(p1,p2));
		
		browser.searchObjectWaitExists(p1, SLEEP);
		
	}

	public void freshData(){
		browser.clickGuiObject(".class", "Html.IMG", ".src", new RegularExpression(".*AvailKey_sitespecific\\.gif$", false));
	}
	
	/**
	 * 
	 * @param bd
	 * @param searchBySiteNum
	 * @param changeDate
	 */
	public void changeDateOrThansferToAnotherSite(BookingData bd, boolean searchBySiteNum, boolean isChangingDateOnly){
		this.waitLoading();
		if(searchBySiteNum){
			this.searchSiteViaSiteNum(bd.siteNo);
		}else if(!isChangingDateOnly){
			String availableSite = bd.siteName+", "+bd.loop+" ("+bd.siteType+")";
			this.searchSiteViaAvailableSite(bd.siteName, bd.siteNo, availableSite);
		}
		
        if(!StringUtil.isEmpty(bd.lengthOfStay)){
    		this.setLengthOfStay(bd.lengthOfStay);
        }
		if(!StringUtil.isEmpty(bd.arrivalDate)){
			this.setArrivalDate(bd.arrivalDate);
		}
		
		this.freshData();
	}

	/**
	 * Fill in equipment type and equipment length.
	 * @param equipType - equipment type
	 * @param equipLength - equipment length
	 * @return
	 */
	public void setOrderDetails(String equipType, String equipLength) {
		// 1. check if Primary Equipment needs to be select or not.		
		if (null == equipType || equipType.equals(""))
			equipType = checkPrimaryEquipment();

		if (equipType != null)
			browser.selectDropdownList(".id", "equip", equipType);

		if ( null ==equipLength || equipLength.length()<1)
			equipLength = checkEqpLength();

		if (equipLength != null)
			browser.setTextField(".id", "vehicleLength", equipLength);

		this.selectCheckBoxAgreementAccepted();
		browser.clickGuiObject(".id", "continueshop");
	}

	/**
	 * Select accept agreement check box.
	 */
	public void selectCheckBoxAgreementAccepted() {
		browser.selectCheckBox(".id", "agreement");
	}

	public void clickConfirmPermitRes(){
		browser.clickGuiObject(".id", "continueshop");
	}

	/**
	 * Check the primary equipment.
	 * @return - select equipment name
	 */
	//TODO Need to be modify. 
	private String checkPrimaryEquipment() {
		//		ISelect list_equip = (ISelect)getObject("list_equip");
		//		String toReturn = null;
		//		if (list_equip.exists() && list_equip.enabled()) { //if there is only one choose, choose the first different one
		//			String current = list_equip.getSelectedText();
		//			ITestDataList nameList;
		//			ITestDataElementList nameListElements;
		//			ITestDataElement nameListElement;
		//
		//			nameList = (ITestDataList) list_equip.getAttributeValue("list");
		//			nameListElements = nameList.getElements();
		//
		//			int listElemCount = nameList.getElementCount();
		//
		//			for (int i = 0; i < listElemCount; i++) {
		//				nameListElement = nameListElements.getElement(i);
		//				if (!current.equalsIgnoreCase(nameListElement.getElement().toString()))
		//					return nameListElement.getElement().toString();
		//			}
		//			toReturn = current;
		//		}
		//		return toReturn;
		return null;
	}

	/**
	 * Click on equipment length field if it exists and available.
	 * @return - 2 if available
	 */
	private String checkEqpLength() {
		IHtmlObject text_rvLength = null;
		try {
			Browser.sleep(1);
			IHtmlObject[] foundTOs = browser.getHtmlObject(".id", "rvLength");
			if (foundTOs.length == 1)
				text_rvLength = (IHtmlObject) foundTOs[0];
			else {
				text_rvLength = null;
				return null;
			}
			if (text_rvLength != null && text_rvLength.isEnabled())
				text_rvLength.click();
			Browser.unregister(foundTOs);
			return "2";
		} catch (Exception e) {
			Browser.unregister(text_rvLength);
			return null;
		}
	}

	/**
	 * Click "Change Date/Transfer to Another Site" button
	 */
	public void clickChangeDateOrTransferToAnotherSite(){
		browser.clickGuiObject(".className", "book now", ".text", "Change Date/Transfer to Another Site");
	}
}
