package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author jchen
 */
public class UwpKOACampsiteDetailsPage extends UwpPage {

	private String reSelectEqp = ".*(s|S)elect.*";
	private String reNoEqp = ".*(n|N)(o|O)(n|N)(e|E).*";
	private static UwpKOACampsiteDetailsPage _instance = null;

	public UwpKOACampsiteDetailsPage() {
	}

	public static UwpKOACampsiteDetailsPage getInstance() {
		if (null == _instance)
			_instance = new UwpKOACampsiteDetailsPage();

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "btnbookdates");
	}
	
	/**
	 * Click on Find Other Campsites link to go to site list page.
	 */
	public void gotoFindOtherCampsites() {
		browser.clickGuiObject(".text", "Find other campsites");
	}
	
	/**
	 * Fill in the booking data.
	 * @param bd - booking data
	 */
	public void fillBookingData(BookingData bd) {
		setOccupants(bd.adultNum, bd.childrenNum, bd.freeNum);
		if(StringUtil.notEmpty(bd.equip)) {
			setEquipment(bd.equip, bd.equipLength);
		} else {
			setEquipment();
		}
	}
	
	/**
	 * Book any available date from arrival date used on site search page. 
	 * @param period - the maximum loop for date after retrieved arrival date
	 * @return - the real arrival date
	 */
	public String bookAnyAvailableDates(int period) {
		String startDate = this.getArrivalDate();
		String newDate = startDate;
		int count = 0;

		this.clickCheckAvailablility();

		browser.waitExists(this, UwpKOAOrderDetailsPage.getInstance());
		while (!UwpKOAOrderDetailsPage.getInstance().exists()) {
			count++;
			if (count > period)
				throw new ItemNotFoundException("Cannot find inventory in "
						+ period + " days period starting from " + startDate);
			newDate = DateFunctions.getDateAfterGivenDay(newDate, 1);
			this.setArriveDate(newDate);
			this.clickCheckAvailablility();
			browser.waitExists(this, UwpKOAOrderDetailsPage.getInstance());
		}

		return newDate;
	}
	
	/**
	 * Fill in occupants fields for adult, children and free.
	 * @param adult
	 * @param children
	 * @param free
	 */
	public void setOccupants(String adult, String children, String free) {
		adult = (adult !=null && adult.length()>0 ) ? adult : "2";
		browser.setTextField(".id", "adults", adult);

		children = (children !=null && children.length()>0) ? children : "0";
		browser.setTextField(".id", "children", children);

		free = (free !=null && free.length()>0) ? free : "0";
		browser.setTextField(".id", "free", free);
	}
	
	/**
	 * Select whether want to bring pet in travel.
	 */
	public void selectTravelPet() {
		browser.selectCheckBox(".id", "travellingWithPets");
	}
	
	/**
	 * Select whether need to slide out.
	 */
	public void selectSlideout() {
		browser.selectCheckBox(".id", "slidouts");
	}
	
	/**
	 * Get the exactly equip type by given string
	 * @param type - part or total equip type
	 * @return - actual equipment type
	 */
	/*private String getEqpType(String type) {
		HtmlObject[] objs = browser.getDropdownList(".id", "equip");
		ISelect equipType = (ISelect)objs[0];
		String toReturn = null;
		
		List<String> nameListElements=equipType.getAllOptions();
		int listElemCount = nameListElements.size();

		for (int i = 0; i < listElemCount; i++) {
			String nameListElement = nameListElements.get(i);
			if (type == null) {
				if (!nameListElement.matches(reSelectEqp)&& !nameListElement.matches(reNoEqp))
					toReturn = nameListElement;
			} else {
				if (nameListElement.matches(type))
					toReturn = nameListElement;
			}
		}
		Browser.unregister(objs);
		return toReturn;
	}*/
	
	/**
	 * Fill in the equipment if available, will choose None or Tent if they are available
	 * @return -  the equipment type has been chose
	 */
	public String setEquipment() {
		IHtmlObject[] objs = browser.getDropdownList(".id", "equip");
		ISelect equipType = (ISelect) objs[0];
		String selectedText = null;

		if (equipType.isEnabled()) {
			selectedText=equipType.getSelectedText();
			List<String> options=equipType.getAllOptions();
			String text=equipType.text();
			
			if (selectedText.matches(reSelectEqp)) {
				if (text.matches(reNoEqp)) {
					equipType.select(new RegularExpression("none",false));
				} else if (options.contains("Tent") || options.contains("TENT")) {
					equipType.select(new RegularExpression("^tent$",false));
				} else {
					equipType.select(1);
				}
			} else if(selectedText.matches(reNoEqp)) {
				//do nothing
			} 
			
			selectedText=equipType.getSelectedText();
			
			if (!selectedText.matches("Tent|TENT|None|NONE")){
				setEquipLength("20");
			}
	
			Browser.unregister(objs);
		}
		return selectedText;
	}
	
	/**
	 * Retrieve the select equipment type and length.
	 * @return equipment type:length
	 */
	public String getEquipDetails() {
		IHtmlObject[] objs = browser.getDropdownList(".id", "equip");
		String equip = (objs.length > 0) ? ((ISelect) objs[0]).getSelectedText(): "None";
		Browser.unregister(objs);

		objs = browser.getTextField(".name", "equipmentLength");
		String length = (objs.length > 0) ? ((IText) objs[0]).getText() : "0";
		Browser.unregister(objs);

		return equip + ":" + length;
	}
	
	/**
	 * Retrieve the total of all occupants
	 * @return - number of occupants
	 */
	public String getOccupantNum() {
		IHtmlObject[] objs = browser.getTextField(".id", "adults");
		String adults = (objs.length > 0) ? ((IText) objs[0])
				.getText() : "0";
		Browser.unregister(objs);

		objs = browser.getTextField(".id", "children");
		String children = (objs.length > 0) ? ((IText) objs[0])
				.getText() : "0";
		Browser.unregister(objs);

		objs = browser.getTextField(".id", "free");
		String free = (objs.length > 0) ? ((IText) objs[0])
				.getText() : "0";
		Browser.unregister(objs);

		int num = Integer.parseInt(adults) + Integer.parseInt(children)
				+ Integer.parseInt(free);

		return num + "";
	}
	
	/**
	 * Retrieve the arrival date from page.
	 * @return - arrival date
	 */
	public String getArrivalDate() {
		return browser.getTextFieldValue(".id", "arrivaldate");
	}
	
	/**
	 * Fill the equipment length. The user need to make sure the length field is available
	 * @param length - equipment length
	 */
	public void setEquipLength(String length) {
		if(length!=null && length.length()>0) {
			if(length.matches("\\d+"))
				browser.setTextField("id", "length", length);
			else
				throw new ItemNotFoundException("the length \""+length+"\" is not digits");
		}
	}
	
	/**
	 * Select equipment type and set the equipment length.
	 * @param etype - equipment type
	 * @param length - equipment length
	 */
	public void setEquipment(String etype, String length) {
		browser.selectDropdownList(".id", "equip", etype);
		this.setEquipLength(length);

	}
	
	/**
	 * Fill in the arrical date.
	 * @param date - arrival date
	 */
	public void setArriveDate(String date) {
		browser.setTextField(".id", "arrivaldate", date);
	}
	
	/**
	 * Fill in the length of stay.
	 * @param duration -  length of stay
	 */
	public void setLengthOfStay(String duration) {
		browser.setTextField(".id", "lenstay", duration);
	}
	
	/**
	 * Wait for page to load after clicking check availability button.
	 */
	public void clickCheckAvailablility() {
		browser.clickGuiObject(".id", "btnbookdates");
	}
	
	public String checkAvailability(String arriveDate, String duration) {
		String aDay=arriveDate;
		setArriveDate(aDay);
		setLengthOfStay(duration);
		clickCheckAvailablility();
		waitForProgressBarDisapear();
		int loop=60; //check availability in 60 days
		boolean notAvailable=facilityNotAvailable();
		while(loop>0 && notAvailable) {
			aDay=DateFunctions.getDateAfterGivenDay(aDay, 1);
			setArriveDate(aDay);
			clickCheckAvailablility();
			waitForProgressBarDisapear();
			notAvailable=facilityNotAvailable();
			loop--;
		}
		
		if(notAvailable) {
			throw new ItemNotFoundException("Could not find inventory in 60 days start from "+arriveDate);
		}
		
		return aDay;
		
	}
	
	public String checkAvailability() {
		String startDate=this.getArrivalDate();
		String aDay=startDate;
		clickCheckAvailablility();
		waitForProgressBarDisapear();
		int loop=30; //check availability in 7 days. If there is no inventory, the test case will just fail
		boolean notAvailable=facilityNotAvailable();
		while(loop>0 && notAvailable) {
			aDay=DateFunctions.getDateAfterGivenDay(aDay, 1);
			setArriveDate(aDay);
			clickCheckAvailablility();
			waitForProgressBarDisapear();
			notAvailable=facilityNotAvailable();
			loop--;
		}
		
		if(notAvailable) {
			throw new ItemNotFoundException("There is no KOA inventory in 7 days starting from "+startDate);
		}
		
		return aDay;
		
	}
	
	public boolean facilityNotAvailable() {
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class","Html.DIV",".className","topofpage msg error",".text","The facility that you have chosen is not available. Please try another facility."));
	}
	
	/**
	 * Verify whether there is any error message displays on page.
	 * @return
	 */
	public boolean anyError() {
		boolean toReturn = false;
		RegularExpression msgError = new RegularExpression(
				"topofpage msg error", false);
		IHtmlObject[] foundTOs = browser
				.getHtmlObject(".className", msgError);
		if (foundTOs != null && foundTOs.length >= 1) {
			String str = (String) foundTOs[0].getProperty(".text");
			logger.error("Error in the KOA campsite details page: " + str);
			toReturn = true;
		}

		Browser.unregister(foundTOs);
		return toReturn;

	}
	
	/**
	 * Retrieve the order details text. 
	 * @return - entire field's text
	 */
	public String getShopCartText() {
		// updated by Nicole, Sep 22, 2013.
		// Can't find a class which name is 'campsiteFields'.
		IHtmlObject[] objs = browser.getHtmlObject(".className", "campsiteFields");
		if(objs == null || objs.length < 1){
			objs = browser.getHtmlObject(".id", "koaCampsiteDetailsForm");
		}

		if(objs == null || objs.length < 1){
			throw new ItemNotFoundException("Can't fine shop cart text!!");
		}
		
		String content = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		return content;
	}
	
	public boolean isItemsInCartsLink(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",	new RegularExpression("Items In Cart: \\d+", false));
	}
	
	/**
	 * Go to shopping cart page.
	 */
	public void gotoShopingCart() {
		// updated by Nicole, Sep 22, 2013.
		// 'Items In Cart: 1' isn't a link anymore. It will go to order cart page by clicking Click 'Check Out Now'.
		if(isItemsInCartsLink()){
			browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Items In Cart: \\d+", false));
		} else {
			browser.clickGuiObject(".class", "Html.A", ".text",	new RegularExpression("Check Out Now", false));
		}
	}
}
