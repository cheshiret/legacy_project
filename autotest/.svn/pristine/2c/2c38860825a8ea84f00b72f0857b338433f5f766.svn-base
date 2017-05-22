package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * TODO: enter class description
 */
public class UwpUpdateDetailsPage extends UwpPage {
	
	private static UwpUpdateDetailsPage _instance = null;

	public static UwpUpdateDetailsPage getInstance() {
		if (null == _instance)
			_instance = new UwpUpdateDetailsPage();

		return _instance;
	}

	public UwpUpdateDetailsPage() {
	}

	protected Property[] continueButton(){
		return Property.toPropertyArray(".class","Html.BUTTON",".id", "continueshop");
	}
	
	protected Property[] equip(){
		return Property.toPropertyArray(".id", "equip");
	}
	
	protected Property[] vehicleLength(){
		return Property.toPropertyArray(".id", "vehicleLength");
	}
	
	protected Property[] agreement(){
		return Property.toPropertyArray(".id", "agreement");
	}
	
	protected Property[] numOfOccupant(){
		return Property.toPropertyArray(".id", "numoccupants");
	}
	
	protected Property[] numOfVehicles(){
		return Property.toPropertyArray(".id", "numvehicles");
	}
	
	protected Property[] discount(String discName){
		return Property.concatPropertyArray(div(), ".className", "discountSection", ".text", new RegularExpression("^"+discName+".*", false));
	}
	
	protected Property[] custPasses(){
		return Property.toPropertyArray(".id", new RegularExpression("cpass\\d+", false));
	}
	
	protected Property[] custTypes(){
		return Property.toPropertyArray(".id", new RegularExpression("ctype\\d+", false));
	}
	
	protected Property[] custTypeNum(){
		return Property.toPropertyArray(".id", new RegularExpression("ctypenum_\\d+", false));
	}
	
	protected Property[] cancelChangeLink(){
		return Property.concatPropertyArray(a(), ".text", "Cancel Change");
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(continueButton());
	}
	
	public void selectEquipType(String equipType){
		browser.selectDropdownList(equip(), equipType);
	}
	
	public void setVehicleLength(String equipLength){
		browser.setTextField(vehicleLength(), equipLength);
	}
	
	public void clickCancelChangeLink(){
		browser.clickGuiObject(cancelChangeLink());
	}
	
	public void setNumOfOccupant(String numOfOccupant){
		browser.setTextField(numOfOccupant(), numOfOccupant);
	}
	
	public void setNumOfVehicles(String numOfVehicles){
		browser.setTextField(numOfVehicles(), numOfVehicles);
	}
	
	public boolean isCustPassDiscountExisted(String discName){
		return browser.checkHtmlObjectExists(Property.atList(discount(discName), custPasses()));
	}
	
	public String getDiscountCheckBoxID(String discName){
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(discount(discName), custPasses()));
		if(objs.length<1){
			objs = browser.getHtmlObject(Property.atList(discount(discName), custTypes()));
			if(objs.length<1){
				throw new ObjectNotFoundException("Discount:"+discName+" check box can't be found.");
			}
		}
		String id= objs[0].getProperty(".id");
		Browser.unregister(objs);
		return id;
	}
	
	public void selectDiscount(String discName){
		if(isCustPassDiscountExisted(discName)){
			browser.selectCheckBox(Property.atList(discount(discName), custPasses()));
		}else browser.selectCheckBox(Property.atList(discount(discName), custTypes()));
		
	}
	
	public void unSelectDiscount(String discName){
		browser.unSelectCheckBox(".id", getDiscountCheckBoxID(discName));
	}
	
	public boolean isCustTypeNumExisted(String discName){
		return browser.checkHtmlObjectExists(Property.atList(discount(discName), custTypeNum()));
	}
	
	public void setCustTypeNum(String discName, String cardID){
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(discount(discName), custTypeNum()));
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find card ID under discount:"+discName);
		}
		browser.setTextField(".id", objs[0].getProperty("id"), cardID);
	}
	
	public void selectCheckBoxAgreementAccepted() {
		browser.selectCheckBox(agreement());
	}
	
	public void clickConfirmPermitRes(){
		browser.clickGuiObject(continueButton());
	}
	
	/**
	 * Fill in equipment type and equipment length.
	 * @param equipType - equipment type
	 * @param equipLength - equipment length
	 * @return
	 */
	public void setEquipment(String equipType, String equipLength, String numOfOccupant, String numOfVehicles) {
		// 1. check if Primary Equipment needs to be select or not.		
		if (StringUtil.notEmpty(equipType)){
			equipType = checkPrimaryEquipment();
			selectEquipType(equipType);
		}

		if (StringUtil.notEmpty(equipLength)){
//			equipLength = checkEqpLength(); //Sara[12/2/2013] equipLength will initialize by our case
			setVehicleLength(equipLength);
		}
		
		if(StringUtil.notEmpty(numOfOccupant))
			setNumOfOccupant(numOfOccupant);
		
		if(StringUtil.notEmpty(numOfVehicles))
			setNumOfVehicles(numOfVehicles);
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
		browser.clickGuiObject(".className", "book now", ".text", 
				new RegularExpression("Change Date(s)?/Transfer to Another Site", false));
	}
	
	public boolean changeDateOrTransferToAnotherSiteExists() {
		return browser.checkHtmlObjectExists(".className", "book now", ".text", 
				new RegularExpression("Change Date(s)?/Transfer to Another Site", false));
	}
	
	public String getReservationInfo(String resType){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^"+resType+".*", false));
		if(objs.length<1){
			throw new ObjectNotFoundException(resType+" DIV can't be found.");
		}
		
		return objs[0].text();
	}
	
	public void verifyReservationInfo(BookingData bd, String resType){
		logger.info("Start to verify '"+resType+"' information.");
		String reservationInfo = this.getReservationInfo(resType);
		
		String siteNo = reservationInfo.split("Site, Loop:")[1].split("Type:")[0].split(",")[0].trim();
		if(!siteNo.equalsIgnoreCase(bd.siteNo)){
			throw new ErrorOnDataException("Site number is wrong.", bd.siteNo, siteNo);
		}
		
		String siteLoop = reservationInfo.split("Site, Loop:")[1].split("Type:")[0].split(",")[1].trim();
		if(!siteLoop.equalsIgnoreCase(bd.loop)){
			throw new ErrorOnDataException("Site loop is wrong.", bd.loop, siteLoop);
		}
		
		String siteType = reservationInfo.split("Type:")[1].split("Arriving:")[0].trim();
		if(!siteType.equalsIgnoreCase(bd.siteType)){
			throw new ErrorOnDataException("site type is wrong.", bd.siteType, siteType);
		}
		
		String arriving = reservationInfo.split("Arriving:")[1].split("Length of Stay:")[0].trim();
		if(DateFunctions.compareDates(bd.arrivalDate, arriving)!=0){
			throw new ErrorOnDataException("Arriving date is wrong.", bd.arrivalDate, arriving);
		}
		
		String lengthOfStayUI = reservationInfo.split("Length of Stay:")[1].trim();
		String lengthOfStay = "";
		if(bd.typeOfUse.equalsIgnoreCase("OverNight")){
			lengthOfStay = bd.lengthOfStay+" (nights)";
		}else{
			lengthOfStay = bd.lengthOfStay+" (days)";
		}
		if(!lengthOfStayUI.equalsIgnoreCase(lengthOfStay)){
			throw new ErrorOnDataException("Length of stay is wrong.", lengthOfStay, lengthOfStayUI);
		}
	}
	
	/**
	 * Verify Original Reservation and New Reservation summary information
	 * @param BookingData originalBd
	 * @param BookingData vewBd
	 * Site, Loop 
	 * Type
	 * Arriving
	 * Length of Stay
	 * @return
	 */
	public void verifyOriginalAndNewResSummary(BookingData originalBd, BookingData vewBd){
		this.verifyReservationInfo(originalBd, "Original Reservation");
		this.verifyReservationInfo(vewBd, "New Reservation");
	}
}
