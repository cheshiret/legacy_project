package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovCommonPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
import com.activenetwork.qa.testapi.util.Timer;
/**
 * 
 * @Description:
 * This page is used for Home page's unified search panel, and View as List page's unified search panel.
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jun 28, 2011
 */
public class UwpUnifiedSearchPanel extends RecgovCommonPage {
	private static UwpUnifiedSearchPanel _instance = null;

	protected UwpUnifiedSearchPanel() {
	}

	public static UwpUnifiedSearchPanel getInstance() {
		if (null == _instance)
			_instance = new UwpUnifiedSearchPanel();

		return _instance;
	}

	private int triggerDidplay = Integer.parseInt(TestProperty.getProperty("unified.search.trigger.delay"));

	protected Property[] interestSection(){
		return Property.concatPropertyArray(div(), ".id", "interest_section");

	}

	protected Property[] arrivalDate(){
		return Property.toPropertyArray(".id", "arrivalDate");
	}

	protected Property[] departureDate(){
		return Property.toPropertyArray(".id", "departureDate");
	}

	public boolean exists() {
		//		Property[] p = new Property[2];
		//		p[0] = new Property(".class", "Html.DIV");
		//		p[1] = new Property(".className", "unifSearchRecreation");  //Search for Places DIV
		//		p[2] = new Property(".text", new RegularExpression(".*Search for Places",false));			//Search for Places DIV text
		// Lesley[20130904]: Comment the last property due to different text in RA and REC unified Search. 
		return browser.checkHtmlObjectExists(interestSection());
	}

	public boolean isWhereFieldHighLighted(){
		return this.isTextFieldHighLighted(0, new Property[]{new Property(".id","locationCriteria")});
	}

	/**
	 * get the message like "Searching", "Retrieving" "Updating"
	 * @return
	 */
	public String getInterstitialMessage(){
		String msg = "";
		IHtmlObject[] objs  = null;
		Property[] p = new Property[]{new Property(".class","Html.DIV"),new Property(".id","contentProgressBar")};
		//		Property[] p1 = new Property[]{new Property(".class","Html.DIV"),new Property(".className","unifSearch")};
		IHtmlObject[] top = browser.getHtmlObject(".class","Html.DIV",".className","unifSearch");
		if(null != top){
			objs = browser.getHtmlObject(p, top[0]);
		}


		if(objs != null && objs.length>0) {
			msg = objs[0].text();
		} 
		Browser.unregister(objs);
		return msg;
	}

	/**
	 * get the message like "Searching", "Retrieving" "Updating" in the given timeout period.
	 * @param timeout
	 * @return
	 */
	public String getInterstitialMessage(int timeout) {
		String msg = "";
		Timer timer=new Timer();
		while(!(msg.length()>0) && timer.diffLong()<timeout*1000) {
			msg=getInterstitialMessage();
		}
		return msg;
	}
	/**
	 * Click on 'Clear Search' link 
	 */
	public void clickClearSearch() {
		IHtmlObject[] objs=browser.getHtmlObject(Property.concatPropertyArray(div(), ".className", "unifSearchRecreation"));
		if(objs.length<1){throw new ObjectNotFoundException("Can't found top DIV by ClassName:'unifSearchRecreation'");}

		//Sara[5/12/2014] In rec.gov website unified search panel, [x] is changed to DIV from SPAN
		//		browser.clickGuiObject(Property.toPropertyArray(".class", "Html.SPAN", ".text", "[x]"),true,0,objs[0]);
		browser.clickGuiObject(Property.concatPropertyArray(div(), ".text", "[x]", ".title", "Clear Search"), true, 0, objs[0]);
	}

	/**
	 * Clear search
	 */
	public void clearSearch(){
		this.clickClearSearch();
		this.waitClearSearchComplete();
		this.waitLoading();
	}

	/**
	 * wait for page loading completed
	 */
	public void waitClearSearchComplete(){
		boolean exists=true; //result filter exists or not 
		int time = SLEEP;
		Timer timer=new Timer();

		while(exists && timer.diff()<time) {
			if(this.isResultFiltersDisplaying()){
				continue;	
			}else{
				exists = false;
			}
		}

		if(exists) {
			throw new ItemNotFoundException("Clear search didn't finish in " + time + " seconds");
		}
	}

	/**
	 * Set the value in Where field
	 * @param whereValue
	 */
	public void setWhere(String whereValue){
		browser.setTextField(".id", "locationCriteria", whereValue);
	}

	public void setWhereByChar(String whereValue){

		char c;
		logger.info("start set where feild by char..");
		for(int i=0;i<whereValue.length();i++){
			c=whereValue.charAt(i);
			this.focusWhereTextField();
			this.inputEndKey();
			this.inputChar(c);
		}
	}

	public void setWhere(char charactor){
		browser.focusOn(".id", "locationCriteria");
		this.inputChar(charactor);
		this.inputEndKey();
	}

	public void setWhere(char charactor, int seconds){
		this.inputChar(charactor);
		this.inputEndKey();
		Browser.sleep(seconds);
	}

	/**
	 * Check Where text field exit
	 * @return
	 */
	public boolean checkWhereTextExist(){
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.text", ".id", "locationCriteria");
	}

	/** Get the value in Where field */
	public String getWhereText(){
		////		HtmlObject[] objs = browser.getHtmlObject(".class", "Html.INPUT.text", ".id", "locationCriteria");
		//		HtmlObject[] objs = browser.getTextField(".id", "locationCriteria");
		//		String whereText = objs[0].getProperty(".value");
		////		browser.gette
		//		Browser.unregister(objs);
		String text=browser.getTextFieldValue(".id", "locationCriteria");
		if(StringUtil.isEmpty(text)){
			IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.INPUT.text", ".id", "locationCriteria");
			if(objs!=null&&objs.length>0)
				text = objs[0].getProperty(".placeholder");
		}
		return text;

		//		return browser.getObjectText(".class", "Html.INPUT.text", ".id", "locationCriteria");
	}

	/**
	 * Where text value validate
	 * @param expectWhere
	 */
	public void verifyWhereTextValue(String expectWhere){
		String actualWhere = this.getWhereText().trim();

		if(!actualWhere.equals(expectWhere.trim())){
			throw new ErrorOnDataException("Where field value is wrong.", expectWhere, actualWhere);
		}
	}

	public void verifyWhereStartWith(String expectWhere){
		String actualWhere = this.getWhereText().trim();

		if(!actualWhere.startsWith(expectWhere.trim())){
			throw new ErrorOnDataException("Where field value is wrong.", expectWhere, actualWhere);
		}
	}

	/** Focus Where field */
	public void focusWhereTextField(){
		this.waitLoading();
		browser.focusOn(Property.toPropertyArray(".class","Html.INPUT.text",".id", "locationCriteria"),0);
	} 

	/** Click Search button */
	public void clickSearch(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".id", "btnDiv");
		Property[] p2 = Property.toPropertyArray(".type","submit",".text", "Search");
		browser.clickGuiObject(Property.atList(p1, p2), true, 0);
	}

	/**
	 * Get all Interest In options
	 * @return
	 */
	public List<String> getAllInterestIns(){
		this.waitLoading();
		if(checkInterestInIsDropdownList()) {//drop down list
			List<String> interestInOptions =  browser.getDropdownElements(".id", "interest");
			for(int i=0; i<interestInOptions.size();i++){
				if(interestInOptions.get(i).matches(".*&.*&.*&.*&.*")){
					interestInOptions.remove(i);
				}
			}
			return interestInOptions;
		} else {//radio button
			IHtmlObject objs[] = browser.getRadioButton(".name", "interest");
			if(objs.length < 1) {
				throw new ItemNotFoundException("There is not an Interest In option named - 'interest'.");
			}

			IHtmlObject labelObjs[] = null;
			List<String> values = new ArrayList<String>();
			for(int i = 0; i < objs.length; i ++) {
				labelObjs = browser.getHtmlObject(".class", "Html.LABEL", "for", objs[i].getProperty(".id"));
				values.add(labelObjs[0].getProperty(".text"));
			}

			Browser.unregister(objs);
			Browser.unregister(labelObjs);

			return values;
		}
	}

	public String getSelectedInterestIn(){
		IHtmlObject objs[] =  browser.getDropdownList(".id", "interest");
		String text = "";
		if(objs.length > 0) {
			text = ((ISelect)objs[0]).getSelectedText();//getAjaxSelectedText(); //Sara[6/18/2014] getSelectedText();
			Browser.unregister(objs);
		} else {
			objs = browser.getRadioButton(".name", "interest");
			if(objs.length < 1) {
				throw new ItemNotFoundException("There is not an Interest In option named - 'interest'.");
			}
			String idValue = "";
			for(int i = 0; i < objs.length; i ++) {
				if(((IRadioButton)objs[i]).isSelected()) {
					idValue = objs[i].getProperty(".id");
					break;
				}
			}
			Browser.unregister(objs);

			if(idValue.equalsIgnoreCase("intbtn_all")) {
				text=UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
			} else if(idValue.equalsIgnoreCase("intbtn_camping")) {
				text="Camping & Lodging";
			} else if(idValue.equalsIgnoreCase("intbtn_dayuse")) {
				text="Day use & Picnic areas";
			} else if(idValue.equalsIgnoreCase("intbtn_permit")) {
				text="Permits & Wilderness";
			} else if(idValue.equalsIgnoreCase("intbtn_tour")) {
				text="Tours & Tickets";
			} else if(idValue.equalsIgnoreCase("intbtn_other")) {
				text=UwpUnifiedSearch.OTHERACTIVITIES_INSTERETED_IN;
			} else {
				throw new ItemNotFoundException("Unknown id: " + idValue);
			}
		}

		return text;
	}


	/**
	 * Get Electric Hookup drop down list elements
	 * @return
	 */
	public List<String> getElectricHookupDropDownListElements(){
		return browser.getDropdownElements(".id",getSpecificAttrDIVId()+"218");
	}

	//	/**
	//	 * Get default Interest In drop down list element
	//	 * @return
	//	 */
	//	public String getDefaultInterestInDropDownListElement(){
	//		return browser.getDropdownListValue(".id", "interest", 0);
	//	}

	/**
	 * Check if the 'Interest In' section is displayed as drop down list or radio button
	 */
	public boolean checkInterestInIsDropdownList() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", "interest");
	}

	public void selectInterestIn(int index){
		browser.clickGuiObject(".id", "interest", ".name", "interest", index);
	}

	/**
	 * Select the specific value from Interest In drop down list/radio button 
	 * @param dropDownListValue
	 */
	public boolean isInterestInMenuBar(){
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".id", "all_id"));
	}
	
	public void selectInterestIn(String value){
		if(!isInterestInMenuBar()){
			IHtmlObject[] objs=browser.getDropdownList(".id", "interest");

			if(objs.length>0) {
				((ISelect) objs[0]).select(value);
				Browser.unregister(objs);
			} 
		}else {
			String idValue=StringUtil.EMPTY;
			if(value.equalsIgnoreCase(UwpUnifiedSearch.DEFAULT_INSTERETED_IN) || value.equalsIgnoreCase(UwpUnifiedSearch.EVERYTHING_INSTERETED_IN)) {
				idValue="all";
			} else if(value.equalsIgnoreCase(UwpUnifiedSearch.CAMPINGANDLODGING_INSTERETED_IN)) {
				idValue="camping";
			} else if(value.equalsIgnoreCase(UwpUnifiedSearch.DAYUSEPICNICAREAS_INSTERETED_IN)) {
				idValue="dayuse";
			} else if(value.equalsIgnoreCase(UwpUnifiedSearch.PERMITSANDWILDERNESS_INSTERETED_IN)) {
				idValue="permit";
			} else if(value.equalsIgnoreCase(UwpUnifiedSearch.TOURSTICKETS_INSTERETED_IN)) {
				idValue="tour";
			} else if(value.equalsIgnoreCase(UwpUnifiedSearch.OTHERACTIVITIES_INSTERETED_IN)) {
				idValue="other";
			} else {
				throw new ItemNotFoundException("Unknown option: "+value);
			}

			idValue = "intbtn_" + idValue;
			if(browser.checkHtmlObjectExists(".id", idValue)){
				browser.selectDropdownList(".id", idValue, value);
			}else{
				idValue = idValue + "_id";	
				browser.clickGuiObject(".id", idValue);
			}
		}
	}

	//	/**
	//	 * Check if the 'Interest In' option is selected or not
	//	 * @param value
	//	 * @return
	//	 */
	//	public boolean isInterestInSelected(String value) {
	//		String option="intbtn_";
	//		if(value.equalsIgnoreCase("Everything")) {
	//			option += "all";
	//		} else if(value.equalsIgnoreCase("Camping & Lodging")) {
	//			option += "camping";
	//		} else if(value.equalsIgnoreCase("Day use & Picnic areas")) {
	//			option += "dayuse";
	//		} else if(value.equalsIgnoreCase("Permits & Wilderness")) {
	//			option += "permit";
	//		} else if(value.equalsIgnoreCase("Tours & Tickets")) {
	//			option += "tour";
	//		} else if(value.equalsIgnoreCase("Other Activities")) {
	//			option += "other";
	//		} else {
	//			throw new ItemNotFoundException("Unknown option: "+value);
	//		}
	//		HtmlObject objs[] = browser.getRadioButton(".id", option);
	//		boolean result = ((IRadioButton)objs[0]).isSelected();
	//		
	//		Browser.unregister(objs);
	//		return result;
	//	}

	public void setOccupants(String occupant){
		browser.setTextField(".id", getSpecificAttrDIVId()+"3012",occupant);
	}

	public void focusOccupant(){
		IHtmlObject[] objs = browser.getTextField(".id", getSpecificAttrDIVId()+"3012");
		if(objs.length>0){
			objs[0].click();
		}else
			throw new ObjectNotFoundException("'Occupants' object can't find.");
		Browser.unregister(objs);
	} 

	public void clearOccupants() {
		IHtmlObject[] objs = browser.getTextField(".id", getSpecificAttrDIVId()+"3012");
		if(objs.length>0){
			((IText)objs[0]).clear();
		}else
			throw new ObjectNotFoundException("'Occupants' object can't find.");
		Browser.unregister(objs);
	}

	public void setOccupantsByChar(String occupant){
		char c;
		logger.info("start set 'Occupants' feild by char..");
		for(int i=0;i<occupant.length();i++){
			this.focusOccupant();
			c=occupant.charAt(i);
			this.inputEndKey();
			this.inputChar(c);
		}
	}

	public String getOccupants(){
		return browser.getTextFieldValue(".id", getSpecificAttrDIVId()+"3012");
	}

	public boolean isOccupantsHighLighted(){
		return this.isTextFieldHighLighted(0, new Property[]{new Property(".id",getSpecificAttrDIVId()+"3012")});
	}

	public String getDIVIDForInterstedIn(){
		RegularExpression regx=new RegularExpression("interest_(camping|dayuse|other|all|permit|tour)",false);
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV",".id",regx);

		if(objs==null||objs.length<1){
			throw new ObjectNotFoundException("Can't find the top DIV");
		}
		String topDiv="";;
		for(IHtmlObject obj:objs){
			if(obj.style("display").trim().equalsIgnoreCase("BLOCK")){
				topDiv=obj.getProperty(".id");
				break;
			}
		}
		Browser.unregister(objs);
		return topDiv;
	}

	public void selectElectricHookup(String electricHookupValue){
		//		HtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", getDIVIDForInterstedIn());
		//		RegularExpression regx=new RegularExpression("(dayuse|camping)_(9001|2001)_218",false);
		//		browser.selectDropdownList(new Property[]{new Property(".id", regx)}, electricHookupValue,false,objs[0]);
		//	    Browser.unregister(objs);
		browser.selectDropdownList(".id", getSpecificAttrDIVId()+"218",electricHookupValue);

	}

	public String getElectricHookup(){
		return browser.getDropdownListValue(".id", getSpecificAttrDIVId()+"218",0);
	}

	/**
	 * Interest In drop down list value validate
	 * @param expectValue
	 */
	public void verifySelectedInterestInValue(String expectValue){
		String actualValue = this.getSelectedInterestIn();
		if(!actualValue.equals(expectValue)){
			throw new ErrorOnDataException("'Interest In' drop down list value is wrong! ",expectValue, actualValue);
		}
	}

	/**
	 * Check Interest In drop down list
	 * @return
	 */
	public boolean checkInterestInDropDownListExist(){
		return browser.checkHtmlObjectExists(".className", "interest");
	}

	/**
	 * Get Day Use Flexible drop down list elements
	 * @return
	 */
	public List<String> getFlexibleDropDownListElements(){
		String topId=this.getDIVIDForInterstedIn();
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV",".id",topId);
		List<String> list=browser.getDropdownElements(new Property[]{new Property(".id", new RegularExpression("dayUseFlex|campingDateFlex",false))},objs[0]);
		Browser.unregister(objs);
		return list;
	}

	/**
	 * Select the specific value from Day Use Flexible drop down list 
	 * @param dropDownListValue
	 */
	public void selectFlexibleDropDownList(String dropDownListValue){
		String topId=this.getDIVIDForInterstedIn();
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV",".id",topId);
		browser.selectDropdownList(new Property[]{new Property(".id", new RegularExpression("dayUseFlex|campingDateFlex",false))},dropDownListValue,false,objs[0]);
		Browser.unregister(objs);
	}

	/**
	 * Get default Day Use Flexible drop down list elements
	 * @return
	 */
	public String getFlexibleDropDownListValue(){
		String topId=this.getDIVIDForInterstedIn();
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV",".id",topId);
		String value= browser.getDropdownListValue(new Property[]{new Property(".id", new RegularExpression("dayUseFlex|campingDateFlex",false))},0,objs[0]);
		Browser.unregister(objs);
		return value;
	}

	/**
	 * Check Day Use Flexible drop down list
	 * @return
	 */
	public boolean checkDayUseFlexDropDownListExist(){
		boolean flag = false;
		IHtmlObject[] objs = browser.getHtmlObject(".id", "dayUseFlex");

		if(objs.length<=0){
			throw new ErrorOnDataException("Interest Day Use object can't be found.");
		}
		if(objs[0].style("display").equals("block")){
			flag = true;
		}else flag = false;

		Browser.unregister(objs);
		return flag;
	}

	/**
	 * Flexible drop down list value validate
	 * @param expectValue
	 */
	public void verifyFlexibleDropDownListValue(String expectValue){
		if(!this.getFlexibleDropDownListValue().equals(expectValue)){
			throw new ErrorOnDataException("The expect Flexible drop down list value should be: "+expectValue);
		}
	}

	/**
	 * Set day use Date
	 * @param date
	 */
		public void setArrivalDateForOlde(String date){
		String id=this.getDIVIDForInterstedIn();
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", id);
		browser.setTextField(".id", new RegularExpression(" ?(dayUse|camping)(Date|LengthOfStay)",false), date,objs[0]);
		this.removeFocus();
		Browser.unregister(objs);
	}

	public boolean isArrivalDateExist(){
		return browser.checkHtmlObjectExists(arrivalDate());
	}

	public boolean isDepartureDateExist(){
		return browser.checkHtmlObjectExists(departureDate());
	}

	public void setArrivalDate(String date){
		browser.setTextField(arrivalDate(), date);
	}

	public void setDepatureDate(String date){
		browser.setTextField(departureDate(), date);
	}

	public void setLengthOfStay(String lengthOfStay){
		String id=this.getDIVIDForInterstedIn();
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", id);
		browser.setTextField(".id", new RegularExpression("(dayUseL|l)engthOfStay",false), lengthOfStay,objs[0]);
		Browser.unregister(objs);
	}

	public void focusLengthOfStay(){
		String id=this.getDIVIDForInterstedIn();
		IHtmlObject[] objs1 = browser.getHtmlObject(".class", "Html.DIV", ".id", id);
		IHtmlObject[] objs2 = browser.getHtmlObject(".id", new RegularExpression("(dayUseL|l)engthOfStay",false),objs1[0]);
		if(objs2.length>0){
			objs2[0].click();
		}else
			throw new ObjectNotFoundException("'Length of stay' object can't find.");
		Browser.unregister(objs2, objs1);
	} 

	public void setLengthOfStayByChar(String lengthOfStay){
		char c;
		logger.info("start set 'Length of stay' feild by char..");
		for(int i=0;i<lengthOfStay.length();i++){
			this.focusLengthOfStay();
			c=lengthOfStay.charAt(i);
			this.inputEndKey();
			this.inputChar(c);
		}
	}

	public String getLengthOfStay(){
		String id=this.getDIVIDForInterstedIn();
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", id);
		String value=browser.getTextFieldValue(new Property[]{new Property(".id",  new RegularExpression("(dayUseL|l)engthOfStay",false))},objs[0]);
		Browser.unregister(objs);
		return value;
	}

	public boolean isLengthOfStayHighLighted(){
		String id=this.getDIVIDForInterstedIn();
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", id);
		return this.isTextFieldHighLighted(new Property[]{new Property(".id",  new RegularExpression("(dayUseL|l)engthOfStay",false))},objs[0]);
	}

	public void clickDayUseDate(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", getDIVIDForInterstedIn());
		browser.focusOn(new Property[]{new Property(".id", new RegularExpression("(dayUse|camping)Date",false))},0,objs[0]);
		this.inputEndKey();
		Browser.unregister(objs);
	}

	/** Arrival Date */
	public String getArrivalDate(){
		RegularExpression regx=new RegularExpression(".*Date$",false);
		return browser.getTextFieldValue(".id", regx);
	}

	/**
	 * Check day use date exist
	 * @return
	 */
	public boolean checkArrivalDateExist(){
		return browser.checkHtmlObjectExists(".id", "dayUseDate");
	}

	/**
	 * 
	 * @param expectValue
	 * @param arrivalDateIsNull: true:arrivalDate="". 1. Go to rec.gov home page and select the interest in with arrival date field
	 *                                                2. Based on 1, click "Clear Cache" x, then select the interest in with arrival date field
	 */
	public void verifyArrivalDateValue(String expectValue, boolean arrivalDateIsNull){
		String dateFromPage=this.getArrivalDate();

		//		if(expectValue.trim().length()>0 && !expectValue.trim().equalsIgnoreCase(UwpUnifiedSearch.ARRIVALDATE_MMDDYY)){
		//			dateFromPage=DateFunctions.formatDate(dateFromPage);
		//			expectValue=DateFunctions.formatDate(expectValue);
		//		}

		if (expectValue.trim().isEmpty()) {
			if (!arrivalDateIsNull) {
				expectValue = UwpUnifiedSearch.ARRIVALDATE_MMDDYY;
			} else {
				expectValue = expectValue.trim();
			}
		} else if (!expectValue.trim().equalsIgnoreCase(UwpUnifiedSearch.ARRIVALDATE_MMDDYY)) {
			dateFromPage=DateFunctions.formatDate(dateFromPage);
			expectValue=DateFunctions.formatDate(expectValue);
		}

		if(!dateFromPage.equals(expectValue)){
			throw new ErrorOnDataException("Expect Day Use Date should be: " + expectValue + ", but actual is: " + dateFromPage);
		}
	}

	public void verifyArrivalDateValue(String expectValue){
		this.verifyArrivalDateValue(expectValue, false);
	}

	public void verifyLengthOfStayValue(String expectValue){
		if(!this.getLengthOfStay().equals(expectValue)){
			throw new ErrorOnDataException("Expect Leng Of Stay should be: "+expectValue);
		}
	}

	public void verifyOccupantsValue(String expectValue){
		if(!this.getOccupants().equals(expectValue)){
			throw new ErrorOnDataException("Occupants should be: "+expectValue);
		}
	}

	public void verifyOccupantsExisting(){
		if(!browser.checkHtmlObjectExists(".id", getSpecificAttrDIVId()+"3012")){
			throw new ErrorOnPageException("can't find occupants test object.");
		}
	}

	public void verifyElectricHookup(String expectValue){
		if(!this.getElectricHookup().equals(expectValue)){
			throw new ErrorOnDataException("Electric Hookup should be: "+expectValue);
		}
	}

	public void selectMoreOptions(){
		//        RegularExpression regx= new RegularExpression("(dayuse|camping)_\\d+_moreOptions",false);
		//		HtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", getDIVIDForInterstedIn());
		browser.selectCheckBox(".id", getSpecificAttrDIVId()+"moreOptions");
		//	    Browser.unregister(objs);
	}

	public void unSelectMoreOptions(){
		//        RegularExpression regx= new RegularExpression("(dayuse|camping)_\\d+_moreOptions",false);
		//		HtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", getDIVIDForInterstedIn());
		//	    

		browser.unSelectCheckBox(".id", getSpecificAttrDIVId()+"moreOptions");
		//	    Browser.unregister(objs);
	}


	public boolean checkMoreOptionsSelected(){
		String idTop = this.getSpecificAttrDIVId();
		if(idTop.length() < 1) {
			return false;
		}
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", idTop);
		boolean flag = browser.isCheckBoxSelected(".id", idTop+"moreOptions", objs[0]);
		Browser.unregister(objs);
		return flag;
	}


	public void verifyMoreOptionsSelected(boolean flag){
		if(this.checkMoreOptionsSelected()!=flag){
			throw new ErrorOnDataException("More options check box should "+(flag?"":"not")+" select.");
		}
	}

	public void selectWaterFront(){
		//        RegularExpression regx= new RegularExpression("(dayuse|camping)_\\d+_3011",false);
		//		HtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", getDIVIDForInterstedIn());
		browser.selectCheckBox(".id", this.getSpecificAttrDIVId()+"3011");
		//	    Browser.unregister(objs);
	}

	public void unSelectWaterFront(){
		//        RegularExpression regx= new RegularExpression("(dayuse|camping)_9001_3011",false);
		//		HtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", getDIVIDForInterstedIn());
		browser.unSelectCheckBox(".id", this.getSpecificAttrDIVId()+"3011");
		//	    Browser.unregister(objs);
	}

	public boolean checkWaterFrontSelected(){
		//        RegularExpression regx= new RegularExpression("(dayuse|camping)_\\d+_3011",false);
		//		HtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", getDIVIDForInterstedIn());
		//	    boolean flag = browser.isCheckBoxSelected(".id", regx, objs[0]);
		//	    Browser.unregister(objs);
		return browser.isCheckBoxSelected(".id", this.getSpecificAttrDIVId()+"3011");
	}

	public void verifyWaterFrontSelected(boolean flag){
		if(this.checkWaterFrontSelected()!=flag){
			throw new ErrorOnDataException("Water Front check box should"+(flag?"":" not ")+" select.");
		}
	}

	/** select Accessibility needs check box */
	public void selectAccessibilityNeeds(){
		selectCommonFacilityAttr("Accessibility needs");
	}

	public void selectCommonFacilityAttr(String attr){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", getDIVIDForInterstedIn());	
		String index = "";
		if(attr.equals("Accessibility needs")){
			index = "3009";
		}else if(attr.equals("Pets allowed")){
			index = "3010";
		}
		RegularExpression regx=new RegularExpression("(dayuse|camping)_common_"+index,false);
		browser.selectCheckBox(".id", regx, 0, objs[0]);
		Browser.unregister(objs);
	}

	public boolean checkCommonFacilityAttrSelected(String attr){
		String index = "";
		if(attr.equals("Accessibility needs")){
			index = "3009";
		}else if(attr.equals("Pets allowed")){
			index = "3010";
		}
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", getDIVIDForInterstedIn());
		RegularExpression regx=new RegularExpression("(dayuse|camping)_common_"+index,false);
		boolean flag=browser.isCheckBoxSelected(".id", regx,objs[0]);
		Browser.unregister(objs);
		return flag;
	}

	/**
	 * Check Accessibility Needs check box exist
	 * @return
	 */
	public boolean checkAccessibilityNeedsExist(){
		return this.checkCommonFacilityAttrExisting("Accessibility needs");
	}

	public boolean checkLength_FTExisting(){
		//		Property[] p=new Property[]{new Property(".class", "Html.DIV"),
		//				new Property(".text", new RegularExpression("^Length (ft).*",false)),
		//				new Property(".className","site_attribute")};
		//		HtmlObject[] objs=browser.getHtmlObject(p);
		//		boolean inputExisting=browser.checkHtmlObjectExists(".class", "Html.INPUT.text", ".id", this.getSpecificAttrDIVId()+"3013", objs[0]);
		//		Browser.unregister(objs);
		//		return inputExisting;  
		String prefixID = this.getSpecificAttrDIVId();
		if(prefixID==null||prefixID.trim().length()<1){
			return false;
		}
		IHtmlObject[] parentTopObjs = browser.getHtmlObject(".class", "Html.DIV", ".id", prefixID);
		IHtmlObject parentTop = null;
		boolean inputExisting = false;

		for(int i = 0 ; i < parentTopObjs.length; i ++){
			if (parentTopObjs[i].style("display").trim().equalsIgnoreCase("block")){
				parentTop = parentTopObjs[i];
				break;
			}
		}

		IHtmlObject[] parentSecondObjs = browser.getHtmlObject(".class", "Html.DIV", ".id", prefixID+ "secattrs", parentTop);
		boolean flag = browser.checkHtmlObjectExists(".class", "Html.INPUT.text", ".id", prefixID+"3013",parentSecondObjs[0]);
		if(flag){
			if (parentSecondObjs[0].style("display").trim().equalsIgnoreCase("block")){
				inputExisting = true;
			}
		}else{
			if (parentTop != null){
				inputExisting=browser.checkHtmlObjectExists(".class", "Html.INPUT.text", ".id", prefixID+"3013",parentTop);
			}

		}

		Browser.unregister(parentTopObjs);
		return inputExisting;  
	}

	public boolean checkOccupantsExisting(){
		String prefixID = this.getSpecificAttrDIVId();
		if(prefixID==null ||prefixID.trim().length()<1){
			return false;
		}
		IHtmlObject[] parentTopObjs = browser.getHtmlObject(".class", "Html.DIV", ".id", prefixID);
		IHtmlObject parentTop = null;
		boolean inputExisting = false;

		for(int i = 0 ; i < parentTopObjs.length; i ++){
			if (parentTopObjs[i].style("display").trim().equalsIgnoreCase("block")){
				parentTop = parentTopObjs[i];
				break;
			}
		}

		IHtmlObject[] parentSecondObjs = browser.getHtmlObject(".class", "Html.DIV", ".id", prefixID+ "secattrs", parentTop);
		boolean flag = browser.checkHtmlObjectExists(".class", "Html.INPUT.text", ".id", prefixID+"3012",parentSecondObjs[0]);
		if(flag){
			if (parentSecondObjs[0].style("display").trim().equalsIgnoreCase("block")){
				inputExisting = true;
			}
		}else{
			if (parentTop != null){
				inputExisting=browser.checkHtmlObjectExists(".class", "Html.INPUT.text", ".id", prefixID+"3012",parentTop);
			}

		}

		Browser.unregister(parentTopObjs);
		return inputExisting;  
	}

	public void verifyLengthExisting(boolean isExisting){
		if(isExisting!=checkLength_FTExisting()){
			throw new ErrorOnPageException("Length (ft) should be "+(isExisting?"":" not "+"existing"));
		}
	}

	public void verifyElecticHookUpOptions(String[] options){
		List<String> list=getElectricHookupDropDownListElements();
		if(options.length!=list.size()){
			throw new ErrorOnPageException("There should be "+options.length+" options");
		}
		for(int i=0;i<options.length;i++){
			if(!options[i].equals(list.get(i))){
				throw new ErrorOnPageException("the option which index is "+i+" should be "+options[i]+" rather than "+list.get(i));
			}
		}
	}

	public boolean checkElecticHookUpExisting() {
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", getDIVIDForInterstedIn());
		RegularExpression regx=new RegularExpression("(dayuse|camping)_(9001|2001)_218",false);
		boolean isExisting= browser.checkHtmlObjectExists( ".id", regx,objs[0]);
		Browser.unregister(objs);
		return isExisting;
	}

	public void verifyElecticHookUpExisting(boolean isExisting){
		if(isExisting!=checkElecticHookUpExisting()){
			throw new ErrorOnPageException("There should "+(isExisting?"":" not ")+" be ElecticHookUp ddlist existing");
		}
	}

	public boolean checkCommonFacilityAttrExisting(String labelText){
		boolean flag=true;
		IHtmlObject[] labels=browser.getHtmlObject(".class", "Html.LABEL", ".text", labelText);
		if(labels.length<1){
			flag=false;
		}
		Browser.unregister(labels);
		return flag;
	}

	/** Un-select Accessibility needs check box */
	public void unSelectAccessibilityNeeds(){
		RegularExpression regx=  new RegularExpression("(dayuse|camping)_common_3009",false);
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", getDIVIDForInterstedIn());
		browser.unSelectCheckBox(".id", regx, 0, objs[0]);
		Browser.unregister(objs);
	}

	public boolean checkAccessibilityNeedsSelected(){
		return checkCommonFacilityAttrSelected("Accessibility needs");
	}


	public void verifyAccessibilityNeedsCheckBoxSelected(boolean flag){
		if(this.checkAccessibilityNeedsSelected()!=flag){
			throw new ErrorOnDataException("Accessibility needs check box should select.");
		}
	}

	/** select Pets allowed check box */
	public void selectPetsAllowed(){
		this.selectCommonFacilityAttr("Pets allowed");
	}

	/**
	 * Check Pets allowed check box exist
	 * @return
	 */
	public boolean checkPetsAllowedCheckBoxExist(){
		return this.checkCommonFacilityAttrExisting("Pets allowed");
	}

	/** Un-select Pets allowed check box */
	public void unSelectPetsAllowed(){
		RegularExpression regx=  new RegularExpression("(dayuse|camping)_common_3010",false);
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", getDIVIDForInterstedIn());
		browser.unSelectCheckBox(".id", regx, 0, objs[0]);
		Browser.unregister(objs);
	}

	/**
	 * Check Pets allowed check box Unselected
	 * @return  true: Unselected
	 *          false: selected
	 */
	public boolean checkPetsAllowedCheckBoxSelected(){
		return this.checkCommonFacilityAttrSelected("Pets allowed");
	}

	public void verifyPetsAllowedCheckBoxSelecte(boolean flag){
		if(this.checkPetsAllowedCheckBoxSelected()!=flag){
			throw new ErrorOnDataException("Pets allowed check box should"+(flag?"":" not ")+" select.");
		}

	}

	/**
	 * Get auto complete box object
	 * @return
	 */
	public IHtmlObject[] getAutoCompleteBox(){
		Property[] p=new Property[]{new Property(".class","Html.DIV"),new Property(".id","locationCriteria_container")};
		return browser.getHtmlObject(p);
	}

	/**
	 * Check whether auto complete Box Exist or not within define (this value will be defined in the property file).
	 * @return
	 */
	public boolean checkAutoCompleteBoxExist(){
		boolean flag=false;
		IHtmlObject[] autoCompleteBox = null;
		autoCompleteBox = this.getAutoCompleteBox();
		logger.info(autoCompleteBox[0].style("display"));
		if(autoCompleteBox != null && autoCompleteBox.length>0 && !autoCompleteBox[0].style("display").trim().equals("none")){
			flag = true;
		}
		Browser.unregister(autoCompleteBox);
		return flag;
	}

	public void verifyAutoCompleteBoxExist(boolean flag){
		if(flag){
			if(!this.checkAutoCompleteBoxExist()){
				throw new ErrorOnDataException("The auto-complete should be exised.");
			}
		}else if(this.checkAutoCompleteBoxExist()){
			throw new ErrorOnDataException("The auto-complete should not be exised.");
		}
	}

	private boolean isEndMarkInAutoCompletedBox(String endMark){
		Property[] p=Property.concatPropertyArray(div(), ".text",new RegularExpression(endMark+".*",false), ".className",new RegularExpression("(.*selectable.*)|message",false));
		return browser.checkHtmlObjectExists(p);
	}

	public long triggerAndWaitForAutoCompleteBox(String endMark){
		long maxWaitTime=OrmsConstants.FILE_DIALOG_LONG_SLEEP*20;//500*4=2s

		boolean autoCompletedBoxDisplays=false;
		boolean expectedEndMarkDisplays=false;
		Timer time = new Timer();

		do{
			if(!autoCompletedBoxDisplays || !expectedEndMarkDisplays){ 
				focusWhereTextField();
				inputEndKey();
			}
			autoCompletedBoxDisplays=this.checkAutoCompleteBoxExist();
			expectedEndMarkDisplays = isEndMarkInAutoCompletedBox(endMark);

		}while(time.diffLong() < maxWaitTime&&!autoCompletedBoxDisplays&&!expectedEndMarkDisplays);

		long timeDiff=time.diffLong();
		if(!autoCompletedBoxDisplays) {
			throw new ItemNotFoundException("Auto complete loading timed out in "+maxWaitTime+" ms");
		}
		if(!autoCompletedBoxDisplays){
			throw new ItemNotFoundException("Auto complete finish loading, but can't find end mark:"+endMark+" in "+maxWaitTime+" ms");
		}
		return timeDiff;
	}

	public long triggerAndWaitForAutoCompleteBox(){
		return triggerAndWaitForAutoCompleteBox(UwpUnifiedSearch.HEADING_FACILITIES);
	}
	/**
	 * Get auto complete Value
	 * @return
	 */
	public String getAutoCompleteBoxVaule(){
		String autoCompleteBoxValue = "";
		IHtmlObject[] autoCompleteBox = null;
		autoCompleteBox = this.getAutoCompleteBox();
		autoCompleteBoxValue=autoCompleteBox[0].getProperty(".text");
		return  autoCompleteBoxValue.trim();
	}

	/**
	 * Verify auto-complete heading exist or not
	 * @param matchedType
	 * @param existed
	 */
	public void verifyAutoCompletedHeading(String[] matchedType, boolean[] existed){
		if(matchedType.length!=existed.length){
			throw new ErrorOnDataException("The length of testString and existed doesn't equal.");
		}
		for(int i=0; i<matchedType.length; i++){
			if(existed[i]){
				if(!this.getAutoCompleteBoxVaule().contains(matchedType[i])){
					throw new ErrorOnDataException("The text String "+matchedType[i]+" should be existed");
				}
			}else{
				if(this.getAutoCompleteBoxVaule().contains(matchedType[i])){
					throw new ErrorOnDataException("The text String "+matchedType[i]+" should not be existed ");
				}
			}
		}
	}

	/**
	 * Wait for auto-complete box displays
	 */
	public long waitAutoCompleteBox(){
		long maxWaitTime=OrmsConstants.FILE_DIALOG_LONG_SLEEP*4;//500*4=2s

		boolean eixsting=false;
		Timer time = new Timer();

		do{
			eixsting=this.checkAutoCompleteBoxExist();

		}while(time.diffLong() < maxWaitTime&&!eixsting);//!flag && 
		long timeDiff=time.diffLong();
		if(!eixsting) {
			throw new ItemNotFoundException("Auto complete loading timed out in "+maxWaitTime+" ms");
		}

		return timeDiff;
	}

	/**
	 * Wait for auto-complete box displays
	 * @param endMark  the expect string than is displayed last
	 * @return
	 */
	public void waitAutoCompleteBox(String endMark){
		waitAutoCompleteBox();

		endMark=endMark.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
		Property[] p=new Property[]{new Property(".class","Html.DIV"),new Property(".text",new RegularExpression(endMark+".*",false)),new Property(".className",new RegularExpression("(.*selectable.*)|message",false))};
		browser.waitExists(OrmsConstants.PAGE_LOADING_TRESHOLD,p);
	}

	public void waitHintMsgBoxExist(String msg){
		//        boolean flag=false;
		//        browser.waitExists(new Property[]{new Property("",""),new Property("","")});
		this.waitAutoCompleteBox(msg);

	}

	public long waitLoadingIconDismiss(){
		boolean dismissed=false;
		Timer time = new Timer();
		long maxWaitTime=OrmsConstants.FILE_DIALOG_LONG_SLEEP*20;//500*20=10s

		do{
			dismissed=!this.loadingIconExist();
		}while(time.diffLong() < maxWaitTime&&!dismissed);//!flag && 

		if(!dismissed) {
			throw new ItemNotFoundException("Auto complete loading timed out.");
		}

		Browser.sleep(1);//syncronize 1 second

		return time.diffLong();
	}


	public boolean loadingIconExist(){
		boolean flag=false;
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.SPAN", ".id", "loadingLocations");
		if(objs==null||objs.length<1){
			flag=false;
		}else if(objs[0].style("display").trim().equalsIgnoreCase("block")){
			flag=true;
		}
		Browser.unregister(objs);
		return flag;
	}

	public void selectAutoCompleteBoxValue(String value){
		logger.info("Start select the items from the auto complete dropdown list..");
		IHtmlObject[] objs = this.getAutoCompleteBox();
		//		HtmlObject[] ob = browser.getHtmlObject(".class","Html.DIV",".text", value,objs[0]);
		value = value.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
		logger.info("The items we are going to click is:" + value);
		//		ob[0].click();
		browser.clickGuiObject(".class","Html.DIV",".text", new RegularExpression(value,false), true, 0, objs[0]);
		logger.info("Finish select items from auto complete dropdown list..");
		Browser.unregister(objs);
	}

	/** Verify auto complete box exist when click headings: State, Facility Names, Address and bland line*/
	public void verifyAutoCompleteBoxExistAfterClickHead(){
		if(checkAutoCompleteBoxExist()){
			IHtmlObject[] objs = this.getAutoCompleteBox();
			Property pro[] = new Property[2];
			pro[0] = new Property(".class","Html.DIV");
			pro[1] = new Property(".className", "unselectable");
			IHtmlObject[] ob = browser.getHtmlObject(pro,objs[0]);
			if(ob.length!=5){
				throw new ErrorOnDataException("Auto complete box only has five deading: Facility Names, bland line, Addresses and State.");
			}else{
				for(int i=0; i<ob.length; i++){
					ob[i].click();
					if(!this.checkAutoCompleteBoxExist()){
						throw new ErrorOnDataException("The auto complete box should exist when click the deadings: Facility Names, bland line, Addresses and State.");
					}
				}
			}

			Browser.unregister(objs, ob);
		}else throw new ObjectNotFoundException("Auto complete box doesn't trigger results with in "+triggerDidplay);
	}

	public void removeFocus(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", "unifSearchRecreation");
		//		Property[] p2 = Property.toPropertyArray(".class", "Html.SPAN",".text",new RegularExpression("Search for Places.*",false));
		//		HtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2)); //Lesley[20130904]: different text in RA and REC unified search form
		IHtmlObject[] objs = browser.getHtmlObject(p1);

		if(objs != null && objs.length>0){
			objs[0].click();
		}else throw new ObjectNotFoundException("Search Recreation object can't find.");
		Browser.unregister(objs);
	}

	public List<String> getAutoCompleteOptions() {
		Property[] p1=Property.toPropertyArray(".class","Html.DIV",".id","locationCriteria_container");
		IHtmlObject[] topContainers=browser.getHtmlObject(p1);
		if(topContainers==null || topContainers.length<1){
			throw new ObjectNotFoundException("Can't find top containers.");
		}
		Property[] p2=Property.toPropertyArray(".class","Html.DIV",".className",new RegularExpression("(un)?selectable(_over)?",false));
		IHtmlObject[] objs=browser.getHtmlObject(p2,topContainers[0]);

		List<String> options=new ArrayList<String>();
		for(IHtmlObject o:objs) {
			String text=o.text();
			if(text.length()>0){
				options.add(text);
			}
		}
		Browser.unregister(objs);

		return options;
	}


	/**
	 * Get Facility and GOOGLE options from auto-complete box
	 * @param returnType   --Facility: Only return facility name options
	 *                     --Google: Only return GOOGLE results options
	 *                     --Others: Return facility and GOOGLE options
	 * @return
	 */
	public List<String> getAutoCompleteOptions(String returnType){
		String[] autoCompleteOptions = null;
		int m = -1,n = -1,q = -1, s=-1;
		List<String> facilityReturn = new ArrayList<String>();
		List<String> googleReturn = new ArrayList<String>();
		List<String> statesReturn = new ArrayList<String>();
		List<String> allReturn = new ArrayList<String>();
		List<String> finalReturn = new ArrayList<String>();
		if(checkAutoCompleteBoxExist()){
			//			HtmlObject[] objs = this.getAutoCompleteBox();
			//			autoCompleteOptions = objs[0].getProperty("innerText").split("\\r\\n");
			autoCompleteOptions=getAutoCompleteOptions().toArray(new String[0]);
			for(int i=0; i<autoCompleteOptions.length; i++){
				String autoCompletedValue = autoCompleteOptions[i].trim();
				if(autoCompletedValue.equals(UwpUnifiedSearch.HEADING_WITHIN_STATE)){
					m=i;
				}
				if(autoCompletedValue.equals(UwpUnifiedSearch.HEADING_FACILITIES)){
					n=i;
				}
				if(autoCompletedValue.equals(UwpUnifiedSearch.HEADING_DISCOVER)){
					s=i;
				}
				if(autoCompletedValue.equals(UwpUnifiedSearch.HEADING_ADDRESSES)){
					q=i;
				}

			}
			//Only contains Facility Names
			if(m==-1 && n!=-1 && q==-1){
				for(int p=n+1; p<autoCompleteOptions.length; p++){
					facilityReturn.add(autoCompleteOptions[p]);
					allReturn.add(autoCompleteOptions[p]);
				}
				//Only contains GOOGLE Results
			}else if(m==-1 && n==-1 && q!=-1){
				for(int p=q+1; p<autoCompleteOptions.length; p++){
					facilityReturn.add(autoCompleteOptions[p]);
					allReturn.add(autoCompleteOptions[p]);
				}
				//Only contains States and Facility Names
			}else if(m!=-1 && n!=-1 && q==-1){
				for(int p=m+1; p<n; p++){
					statesReturn.add(autoCompleteOptions[p]);
					allReturn.add(autoCompleteOptions[p]);
				}
				for(int p=n+1; p<autoCompleteOptions.length; p++){
					facilityReturn.add(autoCompleteOptions[p]);
					allReturn.add(autoCompleteOptions[p]);
				}
				//Only contains States and GOOGLE
			}else if(m!=-1 && n==-1 && q!=-1){
				for(int p=m+1; p<q; p++){
					statesReturn.add(autoCompleteOptions[p]);
					allReturn.add(autoCompleteOptions[p]);
				}
				for(int p=q+1; p<autoCompleteOptions.length; p++){
					facilityReturn.add(autoCompleteOptions[p]);
					allReturn.add(autoCompleteOptions[p]);
				}
				//Only contains Facility Names and GOOGLE Results
			}else if(m==-1 && n!=-1 && q!=-1){
				for(int p=n+1; p<q; p++){
					facilityReturn.add(autoCompleteOptions[p]);
					allReturn.add(autoCompleteOptions[p]);
				}
				for(int p=q+1; p<autoCompleteOptions.length; p++){
					googleReturn.add(autoCompleteOptions[p]);
					allReturn.add(autoCompleteOptions[p]);
				}
				//Has States, Facility Names and GOOGLE Results
			}else{
				for(int p=m+1; p<n; p++){
					statesReturn.add(autoCompleteOptions[p]);
					allReturn.add(autoCompleteOptions[p]);
				}
				for(int p=n+1; p<q; p++){
					facilityReturn.add(autoCompleteOptions[p]);
					allReturn.add(autoCompleteOptions[p]);
				}
				for(int p=q+1; p<autoCompleteOptions.length; p++){
					googleReturn.add(autoCompleteOptions[p]);
					allReturn.add(autoCompleteOptions[p]);
				}
			}

			//			Browser.unregister(objs);
		}else throw new ObjectNotFoundException("Auto complete box doesn't trigger results with in "+triggerDidplay);

		//Return Status, Facility and GOOGLE options
		if(returnType.equals("Within State")){
			finalReturn = statesReturn;
		}else if(returnType.equalsIgnoreCase("Facility")){
			finalReturn = facilityReturn;
		}else if(returnType.equalsIgnoreCase("Google")){
			finalReturn = googleReturn;
		}else {
			finalReturn = allReturn;
		}

		return finalReturn;
	}

	public List<String> getStatesOptions(){
		return this.getAutoCompleteOptions("Within State");
	}
	public List<String> getFacilityOptions(){
		return this.getAutoCompleteOptions("Facility");
	}
	public List<String> getGoogleOptions(){
		return this.getAutoCompleteOptions("Google");
	}
	public List<String> getAllAutoCompletedOptions(){
		return this.getAutoCompleteOptions("States and Facility and Google");
	}

	/** Get number of facility names from auto complete box  */
	public int getNumOfFacilityNames(){
		return this.getAutoCompleteOptions("Facility").size();
	}

	/**  Get number of address names from auto complete box  */
	public int getNumOfAddressNames(){
		return this.getAutoCompleteOptions("Google").size();
	}

	/** Get number of facility and address names from auto complete box */
	public int getNumOfFacilityAndAddressNames(){
		return this.getNumOfFacilityNames() + this.getNumOfAddressNames();
	}

	/** Check Powered By GOOGLE icon exist  */
	public void verifyPoweredByGoogleIcon(){
		if(checkAutoCompleteBoxExist()){
			boolean flag = browser.checkHtmlObjectExists(".class","Html.IMG",".src", new RegularExpression(".*google_power.png$",false));
			if(!flag){
				throw new ObjectNotFoundException("Powered By Google Icon can't find.");
			}
		}else throw new ObjectNotFoundException("Auto complete box doesn't trigger results with in "+triggerDidplay);
	}

	/**
	 * NOTE: due to the internet speed issue, this check points may not be very stable.
	 * verify the progress bar will display during input the given String value, this function is highly affected by Internet speed issue.
	 * and it's very unstable. but the more string you input in the field the more easy to catch the progress bar.
	 * @param where
	 */
	public void verifyProgressBarDisplay(String where){

		String style = "";
		boolean flag = false;
		for(char whereChar: where.toCharArray()){
			this.focusWhereTextField();
			this.inputEndKey();
			this.inputChar(whereChar);

			//get when the process bar will return.
			IHtmlObject objs[] = browser.getHtmlObject(".class","Html.SPAN",".id", "loadingLocations");
			style = objs[0].style("display");
			if(style.contains("block")){
				Browser.unregister(objs);
				flag = true;
				break;
			}
			Browser.unregister(objs);
		}
		if(!flag){
			throw new ErrorOnPageException("The process bar did not display during the process of entering Where values:" + where);
		}else{
			logger.info("process bar display verification on the search panel succesful.");
		}
	}

	/**
	 * Verify alphabetical order sort
	 * @param data: entering data
	 */
	public void verifyAlphabeticalOrderSort(String[] data){
		for(int i=0; i<data.length; i++){
			//Enter sample date
			this.setWhere(data[i]);
			this.waitLoadingIconDismiss();
			if(!this.checkAutoCompleteBoxExist())
				this.triggerAutoCompleteBoxDisplay();
			//Get test data
			List<String> facilityOptionsList = this.getFacilityOptions();
			String[] facilityOptionsSorting  =  new String[facilityOptionsList.size()];
			for(int j=0; j<facilityOptionsList.size(); j++){
				facilityOptionsSorting[j] = facilityOptionsList.get(j).split(",")[0];
			}

			Arrays.sort(facilityOptionsSorting);
			//Verify sorting result
			for(int k=0; k<facilityOptionsList.size(); k++){
				if(!facilityOptionsSorting[k].equals(facilityOptionsList.get(k).split(",")[0])){
					throw new ErrorOnDataException("The sorting date --- "+facilityOptionsSorting[k]+" --- doesn't equal to --- "+
							facilityOptionsList.get(k).split(",")[0]);
				}
			}
		}
	}

	/**
	 * Drop down list values validation: length, default value and all values
	 * @param dropDownListValues
	 * @param expectDropDownListValues
	 * @param defaultDropDownListValue
	 * @param expectDefaultDropDownListValue
	 */
	public void verifyDropDownListValues(List<String> dropDownListValues, String[] expectDropDownListValues, 
			String defaultDropDownListValue, String expectDefaultDropDownListValue){
		//Default value validation
		if(!defaultDropDownListValue.equals(expectDefaultDropDownListValue)){
			throw new ErrorOnDataException("The default drop down list value --- "+defaultDropDownListValue+
					" doesn't equal to the expected --- " +expectDefaultDropDownListValue);
		}
		//Length validation
		if(dropDownListValues.size()!=expectDropDownListValues.length){
			throw new ErrorOnDataException("The lenght of drop down list --- "+dropDownListValues.size()+
					" doesn't equal to the expected --- " +expectDropDownListValues.length);
		}
		//Values validation
		for(int i=0; i<dropDownListValues.size(); i++){
			if(!dropDownListValues.get(i).equals(expectDropDownListValues[i])){
				throw new ErrorOnDataException("The drop down list value --- "+dropDownListValues.get(i)+
						" doesn't equal to the expected --- "+expectDropDownListValues[i]);
			}
		}
	}


	/**
	 * Set search criteria
	 * @param unifiedSearch
	 */
	public void setupUnifiedSearch(UwpUnifiedSearch unifiedSearch){
		//Where
		if(!unifiedSearch.selectAutoCompleteOption){
			this.setWhere(unifiedSearch.whereTextValue);
		}else{
			String selectedAutoCompletedOption = "";
			this.setWhere(unifiedSearch.whereTextValue);
			if(unifiedSearch.selectedAutoCompletedOption.trim().length()>0){
				this.triggerAutoCompleteBoxDisplay(unifiedSearch.selectedAutoCompletedOption);
				selectedAutoCompletedOption = unifiedSearch.selectedAutoCompletedOption;
			}else{
				this.triggerAutoCompleteBoxDisplay();
				List<String> autoCompletedOptions = this.getAutoCompleteOptions("");
				selectedAutoCompletedOption = autoCompletedOptions.get(unifiedSearch.selectedAutoCompletedOptionIndex);
			}

			this.selectAutoCompleteBoxValue(selectedAutoCompletedOption);
		}
		if(!isInterestInMenuBar()){
			this.removeFocus();

			//		Interest In radio button list
			if(unifiedSearch.interestInValue.length()>0 && !this.getSelectedInterestIn().trim().equals(unifiedSearch.interestInValue.trim())){
				if(unifiedSearch.interestInValue.equals(UwpUnifiedSearch.EVERYTHING_INSTERETED_IN) && !this.getAllInterestIns().contains(UwpUnifiedSearch.EVERYTHING_INSTERETED_IN)){
					this.selectInterestIn(0);
				}else 
					this.selectInterestIn(unifiedSearch.interestInValue);
				if(!isInterestInMenuBar()){
					this.removeFocus();
				}
			}

			if(!isInterestInMenuBar()){
				//Set up search for interstIn
				if( unifiedSearch.interestInValue.equals(UwpUnifiedSearch.DEFAULT_INSTERETED_IN) || unifiedSearch.interestInValue.equals(UwpUnifiedSearch.EVERYTHING_INSTERETED_IN) || unifiedSearch.interestInValue.equals("Tours & Tickets")
						|| unifiedSearch.interestInValue.equals("Permits & Wilderness")){ 
					logger.info("Search criteria setting work Done.");
				}else if(unifiedSearch.interestInValue.equals("Day use & Picnic areas")){
					//Occupants
					this.setOccupants(unifiedSearch.occupants);

				}else if(unifiedSearch.interestInValue.equals(UwpUnifiedSearch.OTHERACTIVITIES_INSTERETED_IN)){
					if(unifiedSearch.otherActivitiesName.length>0){
						for(int i=0; i<unifiedSearch.otherActivitiesName.length; i++){
							this.selectOtherActivitiesName(unifiedSearch.otherActivitiesName[i]);
						}
					}
				}else if(unifiedSearch.interestInValue.equals("Camping & Lodging")){
					if(unifiedSearch.lookFor.trim().length()>0){
						this.selectLookingFor(unifiedSearch.lookFor);
					}
				}else throw new ErrorOnDataException("No matched Interest In option.");

				if(unifiedSearch.interestInValue.equals("Day use & Picnic areas")||unifiedSearch.interestInValue.equals("Camping & Lodging")){
					//Electric hookup
					this.removeFocus();
					if(unifiedSearch.moreOptions){
						this.selectMoreOptions();
						if(unifiedSearch.electricHookupValue.length()>0){
							this.selectElectricHookup(unifiedSearch.electricHookupValue);
						}
						if(unifiedSearch.waterFront){
							this.selectWaterFront();
						}else{
							this.unSelectWaterFront();
						}

						if(unifiedSearch.waterHookup){
							this.selectWaterHookup();
						}else{
							this.unSelectWaterHookup();
						}
						if(unifiedSearch.sewerHookup){
							this.selectSewerHookup();
						}else{
							this.unSelectSewerHookup();
						}
						if(unifiedSearch.pullthroughDriveWay){
							this.selectPullthroughDriveway();
						}else{
							this.unSelectPullthroughDriveway();
						}
						this.setLength(unifiedSearch.length);

					}else{
						if(this.checkMoreOptionsSelected()) {
							this.unSelectMoreOptions();
						}
					}
					//Length(FT) and Occupanta
					if(this.checkLength_FTExisting()){
						this.setLength(unifiedSearch.length);
					}
					if(this.checkOccupantsExisting()){
						this.setOccupants(unifiedSearch.occupants);
					}
					//Date
					this.setArrivalDateForOlde(unifiedSearch.arrivalDate);

					//Flexible
					if(unifiedSearch.flexibleValue.length()>0){
						this.selectFlexibleDropDownList(unifiedSearch.flexibleValue);
					}
					//Length of Stay
					this.setLengthOfStay(unifiedSearch.lengthOfStay);

					//Accessibility Needs
					if(unifiedSearch.accessibilityNeeds){
						this.selectAccessibilityNeeds();
					}else{
						this.unSelectAccessibilityNeeds();
					}
					//Pets Allowed
					if(unifiedSearch.petsAllowed){
						this.selectPetsAllowed();
					}else{
						this.unSelectPetsAllowed();
					}
				}
			}
		}else{
			//Date
			if(isArrivalDateExist() && StringUtil.notEmpty(unifiedSearch.arrivalDate)){
				this.setArrivalDate(unifiedSearch.arrivalDate);
			}
			if(isDepartureDateExist() && StringUtil.notEmpty(unifiedSearch.depatureDate)){
				this.setDepatureDate(unifiedSearch.depatureDate);
			}
		}
	}

	/**
	 * Set length(ft)
	 * @param length
	 */
	public void setLength(String length) {
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".className", "unifSearch");
		if(objs.length<1){
			throw new ObjectNotFoundException();
		}

		browser.setTextField(".id", getSpecificAttrDIVId()+"3013", length,objs[0]);
		Browser.unregister(objs);
	}

	public void focusLengthFT(){
		IHtmlObject[] objs = browser.getTextField(".id", getSpecificAttrDIVId()+"3013");
		if(objs.length>0){
			objs[0].click();
		}else
			throw new ObjectNotFoundException("'Length(ft)' object can't find.");
		Browser.unregister(objs);
	} 

	public void clearLength() {
		IHtmlObject[] objs = browser.getTextField(".id", getSpecificAttrDIVId()+"3013");

		if(objs.length>0){
			((IText)objs[0]).clear();
		}else
			throw new ObjectNotFoundException("'Length(ft)' object can't find.");
		Browser.unregister(objs);
	}

	public void setLengthByChar(String lengthFT){
		char c;
		logger.info("start set 'Length(ft)' feild by char..");
		for(int i=0;i<lengthFT.length();i++){
			this.focusLengthFT();
			c=lengthFT.charAt(i);
			this.inputEndKey();
			this.inputChar(c);
		}
	}

	/**
	 * get attributes prefix 
	 * @return
	 */
	public String getSpecificAttrDIVId(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", this.getDIVIDForInterstedIn());

		String attrDivId="";
		Property[] p=new Property[]{
				new Property(".className","specattrs"),
				new Property(".class","Html.DIV"),
				new Property(".id",new RegularExpression("(camping|dayuse)_\\d+_",false))};
		IHtmlObject[] attrObjs=browser.getHtmlObject(p,objs[0]);
		if(attrObjs.length<1){
			throw new ObjectNotFoundException("Can't find 'look for' dropdown list");
		}
		for(IHtmlObject obj:attrObjs){
			if(obj.style("display").trim().equalsIgnoreCase("block")){
				attrDivId=obj.getProperty(".id");
				break;
			}
		}
		Browser.unregister(objs,attrObjs);
		return attrDivId;
	}

	public String getLength(){
		return browser.getTextFieldValue(".id", getSpecificAttrDIVId()+"3013");
	}

	public boolean isLengthHighLighted(){
		return this.isTextFieldHighLighted(0, new Property[]{new Property(".id",getSpecificAttrDIVId()+"3013")});
	}

	/**
	 * select Pull-through driveway in more options
	 */
	public void selectPullthroughDriveway() {
		browser.selectCheckBox(".id", getSpecificAttrDIVId()+"3008");
	}

	/**
	 * unselect Pull-through driveway in more options
	 */
	public void unSelectPullthroughDriveway() {
		browser.unSelectCheckBox(".id", getSpecificAttrDIVId()+"3008");
	}

	/**
	 * select Sewer in more options 
	 */
	public void selectSewerHookup() {
		browser.selectCheckBox(".id", getSpecificAttrDIVId()+"3007");
	}

	/**
	 * unselect Sewer in more options 
	 */
	public void unSelectSewerHookup() {
		browser.unSelectCheckBox(".id", getSpecificAttrDIVId()+"3007");
	}


	/**
	 * select water hookup in more options
	 */
	public void selectWaterHookup() {
		browser.selectCheckBox(".id", getSpecificAttrDIVId()+"3006");
	}

	/**
	 * unselect water hookup in more options
	 */
	public void unSelectWaterHookup() {
		browser.unSelectCheckBox(".id", getSpecificAttrDIVId()+"3006");
	}

	/**
	 * Check the specific interest in exist
	 * @param interestInName
	 * @return
	 */
	private boolean checkSpecificInterestInExist(String interestInName){
		boolean flag = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.DIV",".id", "interest_"+interestInName);

		if(objs.length < 1){
			throw new ErrorOnDataException("Interest Day Use object can't be found.");
		}
		if(objs[0].style("display").trim().equals("block")){
			flag = true;
		}

		Browser.unregister(objs);
		return flag;
	}

	/**
	 * Check specific Interest In DIV exist
	 * @param expected
	 * 0: Any Recreation
	 * 1: Camping & Lodging
	 * 2: Day use & Picnic areas
	 * 3: Permits & Wilderness
	 * 4: Tours & Tickets
	 * 5: Other Activities
	 * @return
	 */
	public boolean checkInterestInDIVExist(String expected){
		boolean givenInterestInExist = false;
		List<String> interestInOptions = this.getAllInterestIns();
		String[] interestInIds =null;
		if(checkInterestInIsDropdownList()) {
			if (MiscFunctions.isRAEnv() && MiscFunctions.isRAUnifiedSearchOpen()) {
				interestInIds = new String[]{"select", "all", "camping", "dayuse", "marina"}; //Lesley[20130918]: update for RA Unified Search
			} else {
				interestInIds = new String[]{"select", "all", "camping", "dayuse", "permit", "tour", "other"};//TODO when the drop down list is back, double check
			}
		} else {
			interestInIds = new String[]{"all", "camping", "dayuse", "permit", "tour", "other"};
		}

		expected = this.interestInOptionAdapter(interestInOptions, expected);

		if(interestInOptions.size()==interestInIds.length){
			for(int i=0; i<interestInOptions.size();i++){
				if(expected.equals(interestInOptions.get(i))){
					givenInterestInExist = this.checkSpecificInterestInExist(interestInIds[i]);
				}
			}
		}else{
			throw new ErrorOnDataException("The expected length of Interest in options doesn't equal.");
		}

		return givenInterestInExist;
	}

	/**
	 * Check InterestedIn sub DIV exist
	 * 
	 * @return
	 */
	public boolean checkInterestInDivExist(){
		String[] interestInIds = new String[]{"camping","dayuse","permit","tour","other"};

		for(int i=0; i<interestInIds.length;i++){
			if(this.checkSpecificInterestInExist(interestInIds[i])){
				return true;
			}
		}
		return false;
	}

	public void verifyInterestInExist(String option, boolean exist){
		if(exist != checkInterestInDIVExist(option)) {
			throw new ErrorOnDataException("The Interest In option '" + option + "' should " + (exist ? "":"NOT ") + "be existed.");
		}
	}

	/**
	 * Get interest in drop down list selected value
	 * @return
	 */
	public String getInterestInSelectedValue(){
		return browser.getDropdownListValue(".id", "interest");
	}

	/**
	 * Verify interest in drop down lust selected value
	 * @param value
	 */
	public void verifyInterestInSelectedValue(String value){
		String actualValue = this.getInterestInSelectedValue();
		if(!value.equals(actualValue)){
			throw new ErrorOnPageException("Interest In value is wrong.", value, actualValue);
		}
		logger.info("Successfully verify Interest in value - "+value);
	}

	/**
	 * Check if specific interest in has expanding form 
	 * @param interestInName: "camping","dayuse","permit","tour","other"
	 * @return
	 */
	public boolean checkInterestInExpandingFormExist(String interestInName){
		Property[] p = Property.toPropertyArray(".class", "Html.DIV", ".id", "interest_"+interestInName);
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV");
		return browser.checkHtmlObjectExists(Property.atList(p, p1));
	}

	/**
	 *  Verify if specific interest in has expanding form
	 * @param interestInName: "camping","dayuse","permit","tour","other"
	 * @param existed true:interest in has expanding form, false:doesn't have
	 */
	public void verifyInterestInExpandingFormExist(String interestInName, boolean existed){
		if(existed!=this.checkInterestInExpandingFormExist(interestInName)){
			throw new ErrorOnPageException("Interest in-"+interestInName+" should "+(existed?"":"not ")+"have expanding form.");
		}
		logger.info("Successfully verify Interest in-"+interestInName+(existed?" ":" doesn't ")+"have expanding form.");
	}

	/**
	 * verify the value in Length(ft) field match with the given parameter.
	 * @param expectValue
	 */

	public void verifyLengthFTInfo(String expectValue){
		String currentValue = this.getLength();
		if(currentValue.equalsIgnoreCase(expectValue)){
			logger.info("Vefification for LengthFT value successfully.");
		}else{
			logger.error("The expect  Length(ft) value is:" + expectValue);
			logger.error("The current Length(ft) value is:" + currentValue);
			throw new ErrorOnDataException("Verification for LengthFT value failed");
		}
	}

	public boolean checkWaterHookupSelected(){
		return browser.isCheckBoxSelected(".id", this.getSpecificAttrDIVId()+"3006");
	}
	/**
	 * verify the water hookup check box select status.
	 * @param flag
	 */
	public void verifyWaterHookupSelected(boolean flag){
		boolean currentValue = this.checkWaterHookupSelected();
		if(flag!=currentValue){
			throw new ErrorOnPageException("Water hookup check box status verification failed.", flag, currentValue);
		}
		logger.info("Verification for Water hookup checkbox status successfully.");
	}

	public boolean checkSewerHookupSelected(){
		return browser.isCheckBoxSelected(".id", this.getSpecificAttrDIVId()+"3007");
	}
	/**
	 * verify the Sewer hookup check box select status.
	 * @param flag
	 */
	public void verifySewerHookupSelected(boolean flag){
		boolean currentValue = this.checkSewerHookupSelected();
		if(flag!=currentValue){
			throw new ErrorOnPageException("SewerHookup check box status verification failed.", flag, currentValue);
		}
		logger.info("Verification for SewerHookup checkbox status successfully.");
	}

	public boolean checkPullthroughDrivewaySelected(){
		return browser.isCheckBoxSelected(".id", this.getSpecificAttrDIVId()+"3008");
	}
	/**
	 * verify the pull through drive way check box select status.
	 * @param flag
	 */
	public void verifyPullthroughDrivewaySelected(boolean flag){
		boolean currentValue = this.checkPullthroughDrivewaySelected();
		if(flag!=currentValue){
			throw new ErrorOnPageException("through drive way check box status verification failed.", flag, currentValue);
		}
		logger.info("Verification for through drive way checkbox status successfully.");
	}

	/**
	 * compare the search criteria in search widget with the given parameters.
	 * Check Unified Search 
	 * @param unifiedSearch
	 */
	public void verifySearchCriteria(UwpUnifiedSearch unifiedSearch){
		verifySearchCriteria(unifiedSearch, false);
	}
	public void verifySearchCriteria(UwpUnifiedSearch unifiedSearch, boolean whereStartWith){
		if(whereStartWith){
			verifyWhereStartWith(unifiedSearch.whereTextValue);
		}else this.verifyWhereTextValue(unifiedSearch.whereTextValue);
		this.verifySelectedInterestInValue(unifiedSearch.interestInValue);
		boolean hasSubItem=true;
		//        if(unifiedSearch.interestInValue.equalsIgnoreCase(UwpUnifiedSearch.DEFAULT_INSTERETED_IN)||unifiedSearch.interestInValue.equalsIgnoreCase(UwpUnifiedSearch.EVERYTHING_INSTERETED_IN)){
		////    	if(unifiedSearch.interestInValue.equalsIgnoreCase("Everything")) {
		//        	hasSubItem=false;
		//        }
		this.verifyInterestInExist(unifiedSearch.interestInValue, hasSubItem);

		//1. Any Recreation
		//		if(unifiedSearch.interestInValue.equals("Any Recreation") || unifiedSearch.interestInValue.equals("-- Please Select --")){
		if(unifiedSearch.interestInValue.equals(UwpUnifiedSearch.EVERYTHING_INSTERETED_IN) || 
				unifiedSearch.interestInValue.equalsIgnoreCase(UwpUnifiedSearch.DEFAULT_INSTERETED_IN) || 
				unifiedSearch.interestInValue.equalsIgnoreCase(UwpUnifiedSearch.PERMITSANDWILDERNESS_INSTERETED_IN) || 
				unifiedSearch.interestInValue.equalsIgnoreCase(UwpUnifiedSearch.TOURSTICKETS_INSTERETED_IN)) {
			logger.info("Successfully verify Where and Interest In Options.");
		}

		//2. Day use & Picnic areas
		else if(unifiedSearch.interestInValue.equals("Day use & Picnic areas")){
			this.verifyOccupantsValue(unifiedSearch.occupants);
			if(unifiedSearch.moreOptions){
				this.verifyMoreOptionsSelected(true);
				this.verifyElectricHookup(unifiedSearch.electricHookupValue);
				this.verifyWaterFrontSelected(unifiedSearch.waterFront);
			}else{
				this.verifyMoreOptionsSelected(false);
			}
			this.verifyArrivalDateValue(unifiedSearch.arrivalDate);
			this.verifyFlexibleDropDownListValue(unifiedSearch.flexibleValue);
			this.verifyLengthOfStayValue(unifiedSearch.lengthOfStay);
			this.verifyAccessibilityNeedsCheckBoxSelected(unifiedSearch.accessibilityNeeds);
			this.verifyPetsAllowedCheckBoxSelecte(unifiedSearch.petsAllowed);
		}

		//3. Other Activities
		else if(unifiedSearch.interestInValue.equals(UwpUnifiedSearch.OTHERACTIVITIES_INSTERETED_IN)){
			//Check selected Other activities name
			for(int i=0; i<unifiedSearch.otherActivitiesName.length; i++){
				if(!this.checkSelectOtherActivityName(unifiedSearch.otherActivitiesName[i])){
					throw new ErrorOnDataException("The Other Activities "+unifiedSearch.otherActivitiesName[i]+" should be selected.");
				}
			}
			//Check un-select Other activities name count
			int unSelectedOtherActivities = this.getOtheActivitiesName().length-unifiedSearch.otherActivitiesName.length;
			if(this.getUnselectOtherActivitiesCount()!=unSelectedOtherActivities){
				throw new ErrorOnDataException("The unselected other activities count should be "+unSelectedOtherActivities);
			}
		}

		//4. Camping & Lodging
		else if(unifiedSearch.interestInValue.equals("Camping & Lodging")){
			String lookFor = unifiedSearch.lookFor;
			this.verifyLookForValue(lookFor);

			if (lookFor.equalsIgnoreCase("Any type of site") || lookFor.equalsIgnoreCase("Any camping spot")){

				//TODO add method make sure more link, lenth field won't show
			}else if(lookFor.equals("RV sites")||lookFor.equals("Trailer sites")||lookFor.equals("Tent")||lookFor.equals("Tent-only sites")||lookFor.equals("Cabins")||lookFor.equals("Lookouts")||
					lookFor.equals("Group sites") || lookFor.equals("Day use & Picnic areas") || lookFor.equals("Horse sites")||lookFor.equals("Boat sites") || lookFor.equals("Yurts")){
				//verify the occupants value
				if(lookFor.equalsIgnoreCase("Tent") ||lookFor.equalsIgnoreCase("Tent-only sites") || 
						lookFor.equalsIgnoreCase("Cabins")||lookFor.equalsIgnoreCase("Group sites") ||lookFor.equalsIgnoreCase("Lookouts")||
						lookFor.equalsIgnoreCase("Day use & Picnic areas") || lookFor.equalsIgnoreCase("Yurts")){

					this.verifyOccupantsValue(unifiedSearch.occupants);
				}else{
					this.verifyLengthFTInfo(unifiedSearch.length);
				}

				//verify more options...
				this.verifyMoreOptionsSelected(unifiedSearch.moreOptions);

				if(unifiedSearch.moreOptions){

					if (lookFor.equalsIgnoreCase("RV sites")||lookFor.equalsIgnoreCase("Trailer sites")||lookFor.equalsIgnoreCase("Group sites")||lookFor.equalsIgnoreCase("Horse sites")){
						if (lookFor.equalsIgnoreCase("Group sites")){
							this.verifyLengthFTInfo(unifiedSearch.length);
						}
						if (!lookFor.equalsIgnoreCase("Horse sites")){
							this.verifyElectricHookup(unifiedSearch.electricHookupValue);
							this.verifySewerHookupSelected(unifiedSearch.sewerHookup);
						}

						this.verifyWaterHookupSelected(unifiedSearch.waterHookup);
						this.verifyPullthroughDrivewaySelected(unifiedSearch.pullthroughDriveWay);
						this.verifyWaterFrontSelected(unifiedSearch.waterFront);
					}else if (lookFor.equalsIgnoreCase("Tent")||lookFor.equalsIgnoreCase("Tent-only sites") || lookFor.equalsIgnoreCase("Cabins")||lookFor.equalsIgnoreCase("Yurts")){
						this.verifyWaterFrontSelected(unifiedSearch.waterFront);
					}else if(lookFor.equalsIgnoreCase("Lookouts")||lookFor.equalsIgnoreCase("Group sites")||lookFor.equalsIgnoreCase("Day use & Picnic areas")){
						this.verifyElectricHookup(unifiedSearch.electricHookupValue);
						this.verifyWaterFrontSelected(unifiedSearch.waterFront);
					}else if (lookFor.equalsIgnoreCase("Boat sites")){
						this.verifyOccupantsValue(unifiedSearch.occupants);
					}else{
						throw new ErrorOnDataException("There is no related data for more option has been verified based on the given parameter:" + unifiedSearch.lookFor);
					}
				}

			}
			//No matched looking for option for the bd.lookFor value
			else throw new ErrorOnDataException("There is no looking for items match the given parameter:" + unifiedSearch.lookFor );

			this.verifyArrivalDateValue(unifiedSearch.arrivalDate);
			this.verifyFlexibleDropDownListValue(unifiedSearch.flexibleValue);
			this.verifyLengthOfStayValue(unifiedSearch.lengthOfStay);
			if (MiscFunctions.isRECEnv()) { //Lesley[20131112]: update due to product change in RA.com. 
				this.verifyAccessibilityNeedsCheckBoxSelected(unifiedSearch.accessibilityNeeds);
				this.verifyPetsAllowedCheckBoxSelecte(unifiedSearch.petsAllowed);
			}
		}

		//No matched Interest In option
		else throw new ErrorOnDataException("No intrested in value match the given value:" + unifiedSearch.interestInValue);

	}

	/**
	 * Verify initialized UI for specific Interest In value
	 * @param interestIdType
	 *                       --Any Recreation
	 *                       --Camping & Lodging
	 *                       --Day Use & Picnic Areas
	 * 	                     --Permits & Wilderness
	 * 	                     --Tours & Tickets
	 * 	                     --Other Activities"
	 * @param arrivalDateIsNull: true:arrivalDate="". 1. Go to rec.gov home page and select the interest in with arrival date field
	 *                                                2. Based on 1, click "Clear Cache" x, then select the interest in with arrival date field
	 */
	public void verifyInterestInitializedUI(String interestIdType, boolean arrivalDateIsNull){
		this.removeFocus();
		if(interestIdType.equals(UwpUnifiedSearch.DEFAULT_INSTERETED_IN) || interestIdType.equals(UwpUnifiedSearch.EVERYTHING_INSTERETED_IN)){
			this.verifyWhereTextValue(UwpUnifiedSearch.DEFAULT_WHEREVALUE);//Production Change
			this.selectInterestIn(interestIdType);	
			this.verifyInterestInExist(interestIdType, true);
		}else if(interestIdType.equals("Day use & Picnic areas")){
			this.verifyOccupantsValue("");
			this.verifyMoreOptionsSelected(false);
			this.verifyArrivalDateValue("", arrivalDateIsNull);
			this.verifyFlexibleDropDownListValue(this.getFlexibleDropDownListElements().get(0));
			this.verifyLengthOfStayValue("");
			this.verifyAccessibilityNeedsCheckBoxSelected(false);
			this.verifyPetsAllowedCheckBoxSelecte(false);
		}else if(interestIdType.equals(UwpUnifiedSearch.OTHERACTIVITIES_INSTERETED_IN)){
			this.verifyUnselectedOtherActivitiesCount(this.getOtheActivitiesName().length);
		}else throw new ErrorOnDataException("Interest In Type can't be matched.");
	}

	public void verifyInterestInitializedUI(String interestIdType){
		this.verifyInterestInitializedUI(interestIdType, false);
	}

	public void selectFlexbile(int weeks) {
		String option="Not Flexible";
		if(weeks==2) {
			option="Flexible for next 2 weeks";
		} else if (weeks==4) {
			option="Flexible for next 4 weeks";
		} else if(weeks!=0) {
			throw new ItemNotFoundException("The number "+weeks+" is not valid, should be 0, 2, 4");
		}


		browser.selectDropdownList(".id","campingDateFlex",option,true);
	}

//	public void setupMarinaSearchCrititeria(MarinaBookingData mbd){
//		if(StringUtil.notEmpty(mbd.interestInValue)){
//			selectInterestIn(UwpUnifiedSearch.EVERYTHING_INSTERETED_IN);
//		}
//
//		setWhere(mbd.park);
//	}

	/**
	 * Fill in Camping & Lodging search criteria.
	 * @param bd - booking data
	 */
	public void setupCampingSearchCriteria(BookingData bd) {
		String lookFor1 = "Any type of site";
		String lookFor2 = "Any camping spot";
		this.selectInterestIn("Camping & Lodging");

		//setup where value
		if (StringUtil.notEmpty(bd.park)) { // Lesley[20130916]: Update to Search by Park or State name 
			this.setWhere(bd.park);
		} else if (StringUtil.notEmpty(bd.state)) {
			this.setWhere(bd.state);
		}
		if(bd.selectAutoCompleteOption){
			this.waitLoadingIconDismiss();		
			if(!this.checkAutoCompleteBoxExist())	
				this.triggerAutoCompleteBoxDisplay();
			String value = this.getFacilityOptions().get(0);
			this.selectAutoCompleteBoxValue(value);
		}else if(!isInterestInMenuBar()){
			this.removeFocus();
		}

		if(!isInterestInMenuBar()){
			//Looking for drop down list
			String allLookForElements = getLookingForElements().toString(); //Sara[8/29/2013]
			if(StringUtil.notEmpty(bd.lookFor) && allLookForElements.contains(bd.lookFor)){
				this.selectLookingFor(bd.lookFor);
			}else if(allLookForElements.contains(lookFor1)){
				this.selectLookingFor(lookFor1);
			}else if(allLookForElements.contains(lookFor2)){
				this.selectLookingFor(lookFor2);
			}else throw new ErrorOnPageException("No matched lookfor option can be found.");
	
			String lookFor = bd.lookFor;
			if(lookFor.equalsIgnoreCase("RV sites") || lookFor.equalsIgnoreCase("Trailer sites") || lookFor.equalsIgnoreCase("Horse sites") || lookFor.equalsIgnoreCase("Boat sites")){
				if (bd.length.length() >0){
					this.setLength(bd.length);
				}else{
					this.setLength("");
				}
			}else if (lookFor.equalsIgnoreCase("Tent") || lookFor.equalsIgnoreCase("Tent-only sites")||lookFor.equalsIgnoreCase("Cabins") || lookFor.equalsIgnoreCase("Lookouts") ||
					lookFor.equalsIgnoreCase("Group sites") || lookFor.equalsIgnoreCase("Day use & Picnic areas") || lookFor.equalsIgnoreCase("Yurts")){
				if (bd.occupants.length() >0){
					this.setOccupants(bd.occupants);
				}else{
					this.setOccupants("");
				}
			}
			//test RV sites
			if (!lookFor.equalsIgnoreCase("Any type of site") && lookFor.length()>0 ){
				if(bd.moreOptions){
					this.selectMoreOptions();
					//setup length(ft)
					if(lookFor.equalsIgnoreCase("Group sites")){
						if (bd.length.length() >0){
							this.setLength(bd.length);
						}else{
							this.setLength("");
						}
					}
					//setup Occupants.
					if(lookFor.equalsIgnoreCase("Boat sites")){
						if (bd.occupants.length() >0){
							this.setOccupants(bd.occupants);
						}else{
							this.setOccupants("");
						}
					}
					if(bd.electricHookupValue.length()>0){
						this.selectElectricHookup(bd.electricHookupValue);
					}else{
						this.selectElectricHookup("Not Required");
					}
					if(bd.waterFront){
						this.selectWaterFront();
					}else{
						this.unSelectWaterFront();
					}
	
					if(bd.waterHookup){
						this.selectWaterHookup();
					}else{
						this.unSelectWaterHookup();
					}
					if(bd.sewerHookup){
						this.selectSewerHookup();
					}else{
						this.unSelectSewerHookup();
					}
					if(bd.pullthroughDriveWay){
						this.selectPullthroughDriveway();
					}else{
						this.unSelectPullthroughDriveway();
					}
	
				}else{
					this.unSelectMoreOptions();
				}
			}
			//Date
			this.setArrivalDateForOlde(bd.arrivalDate);

			//Flexible
			if(bd.isRange) {
				this.selectFlexbile(4);
			}else if(bd.flexibleValue.length()>0){
				this.selectFlexibleDropDownList(bd.flexibleValue);
			}else{
				this.selectFlexibleDropDownList("Not Flexible");
			}
	
			//Length of Stay
			this.setLengthOfStay(bd.lengthOfStay);
	
			//Accessibility Needs
			if(bd.accessibilityNeeds){
				this.selectAccessibilityNeeds();
			}else{
				this.unSelectAccessibilityNeeds();
			}
			//Pets Allowed
			if(bd.petsAllowed){
				this.selectPetsAllowed();
			}else{
				this.unSelectPetsAllowed();
			}
		}else {
			//Date
			if(isArrivalDateExist() && StringUtil.notEmpty(bd.arrivalDate)){
				this.setArrivalDate(bd.arrivalDate);
			}
			if(isDepartureDateExist() && StringUtil.notEmpty(bd.depatureDate)){
				this.setDepatureDate(bd.depatureDate);
			}
		}

	}

	/**
	 * Fill in Tour Ticket search criteria.
	 * @param bd - booking data
	 */
	public boolean isTourInterestedInExist(){
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".id", "tour_id"));
	}
	
	public void setupTourSearchCriteria(TicketInfo bd) {
		//Sara[08292013] RA website is no "Tours & Tickets" interest in option
		boolean hasTour = false;
		if(!isInterestInMenuBar()){
			hasTour = getAllInterestIns().toString().contains(UwpUnifiedSearch.TOURSTICKETS_INSTERETED_IN);
		}else hasTour = isTourInterestedInExist();

		if(hasTour){
			bd.interestInValue = UwpUnifiedSearch.TOURSTICKETS_INSTERETED_IN; 
		}else bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;

		this.selectInterestIn(bd.interestInValue);
		this.setWhere(bd.park);
		//automation issue, need to trigger auto complete box again sometimes
		if(bd.selectAutoCompleteOption){
			this.waitLoadingIconDismiss();
			if(!this.checkAutoCompleteBoxExist())	
				this.triggerAutoCompleteBoxDisplay();
			String value = this.getFacilityOptions().get(0);
			this.selectAutoCompleteBoxValue(value);
		}
		else if (!isInterestInMenuBar()){
			this.removeFocus();
		}
	}

	/**
	 * This method is used to trigger auto complete box display
	 * as sometimes the auto complete box will not display by automation issue
	 */
	public void triggerAutoCompleteBoxDisplay(){
		triggerAutoCompleteBoxDisplay(UwpUnifiedSearch.HEADING_FACILITIES);
	}

	/**
	 * This method is used to trigger auto complete box display
	 * as sometimes the auto complete box will not display by automation issue
	 */
	public void triggerAutoCompleteBoxDisplay(String endMark){
		this.focusWhereTextField();
		this.inputEndKey();
		this.waitAutoCompleteBox();//wait entire complete box exists
		this.waitAutoCompleteBox(endMark);//wait the specific 'endMark' exist in the complete box
	}

	public void setupPermitSearchCriteria(PermitInfo permit) {
		//		this.waitExists();
		//		if(permit.faclilty!=null) 
		this.setWhere(permit.facility);
		if(!isInterestInMenuBar())
			//		this.removeFocus();
			this.selectInterestIn(UwpUnifiedSearch.PERMITSANDWILDERNESS_INSTERETED_IN);
		//			this.waitLoadingIconDismiss();
		//			if(!this.checkAutoCompleteBoxExist()) {
		//				this.triggerAutoCompleteBoxDisplay();
		//			}
		//			
		//			String value = this.getFacilityOptions().get(permit.autoCompleteOptionIndex);
		//			logger.debug("Select option: "+value);
		//			this.selectAutoCompleteBoxValue(value);
		//		}	
		//			//test
		//			if(permit.selectAutoCompleteOption){
		//				this.waitLoadingIconDismiss();
		//				if(!this.checkAutoCompleteBoxExist())	
		//					this.triggerAutoCompleteBoxDisplay();
		//				String value = this.getFacilityOptions().get(0);
		//				this.selectAutoCompleteBoxValue(value);
		//			}else{
		//				this.removeFocus();
		//			}
		//			
		//			String value = this.getFacilityOptions().get(permit.autoCompleteOptionIndex);
		//			logger.debug("Select option: "+value);
		//			this.selectAutoCompleteBoxValue(value);
	}

	/**
	 * Get all Other activities(Interested in...) applied options
	 * @return
	 */
	public String[] getOtheActivitiesName(){
		String[] otherActivitiesApplied = null;

		IHtmlObject[] interestedOther = browser.getHtmlObject(".id", "interest_other");
		IHtmlObject[] otherApplied = browser.getHtmlObject(".class", "Html.DIV", ".className", "checkBox", interestedOther[0]);
		otherActivitiesApplied = new String[otherApplied.length];

		for(int i=0; i<otherApplied.length; i++){
			otherActivitiesApplied[i] = otherApplied[i].text();
		}

		Browser.unregister(interestedOther, otherApplied);
		return otherActivitiesApplied;
	}

	/**
	 * Get Other Activities ID
	 * @param activityName
	 * @return
	 */
	public String getOtherActivitiesID(String activityName){
		String activityID = "othact_";
		if(activityName.equalsIgnoreCase("Auto Touring")){
			activityID = activityID+"4";
		}else if(activityName.equalsIgnoreCase("Biking")){
			activityID = activityID+"5";
		}else if(activityName.equalsIgnoreCase("Boating")){
			activityID = activityID+"6";
		}else if(activityName.equalsIgnoreCase("Camping")){
			activityID = activityID+"9";
		}else if(activityName.equalsIgnoreCase("Climbing")){
			activityID = activityID+"7";
		}else if(activityName.equalsIgnoreCase("Hiking")){
			activityID = activityID+"14";
		}else if(activityName.equalsIgnoreCase("Historic & Cultural Site")){
			activityID = activityID+"8";
		}else if(activityName.equalsIgnoreCase("Horseback Riding")){
			activityID = activityID+"15";
		}else if(activityName.equalsIgnoreCase("Hunting")){
			activityID = activityID+"16";
		}else if(activityName.equalsIgnoreCase("Off Highway Vehicle")){
			activityID = activityID+"18";
		}else if(activityName.equalsIgnoreCase("Visitor Center")){
			activityID = activityID+"24";
		}else if(activityName.equalsIgnoreCase("Wildlife Viewing")){
			activityID = activityID+"26";
		}else if(activityName.equalsIgnoreCase("Winter Sports")){
			activityID = activityID+"22";
		}else throw new ErrorOnDataException("No matched activity name.");

		return activityID;
	}

	public String[] getOtherActivitiesIDs(){
		String[] otherActivitiesIDs = null;
		IHtmlObject[] otherActivityObjs = browser.getHtmlObject(".id", new RegularExpression("othact_\\d+", false));
		otherActivitiesIDs = new String[otherActivityObjs.length];

		for(int i=0; i<otherActivityObjs.length; i++){
			otherActivitiesIDs[i] = otherActivityObjs[i].getProperty(".id");
		}

		return otherActivitiesIDs;
	}

	/**
	 * Select Other activities name
	 * @param activityID
	 */
	public void selectOtherActivitiesName(String activityName){
		String activityID = this.getOtherActivitiesID(activityName);
		browser.selectCheckBox(".id", activityID);
	}

	/**
	 * Select the specific other activity is selected
	 * @param activityName
	 * @return
	 */
	public boolean checkSelectOtherActivityName(String activityName){
		String activityID = this.getOtherActivitiesID(activityName);

		return browser.isCheckBoxSelected(".id", activityID);
	}

	/**
	 * Get un-selected Other Activities name count
	 * @return
	 */
	public int getUnselectOtherActivitiesCount(){
		int unselectedCount = 0;
		String[] otherAcivitiesIDs = this.getOtherActivitiesIDs();

		for(int i=0; i<otherAcivitiesIDs.length; i++){
			if(!browser.isCheckBoxSelected(".id", otherAcivitiesIDs[i])){
				unselectedCount++;
			}
		}

		return unselectedCount;
	}

	/**
	 * Get Looking For Value
	 * @return
	 */
	public String getLookingForValue() {
		return browser.getDropdownListValue(".id", "lookingFor", 0);
	}

	/**
	 * get all looking for elements values
	 * @return
	 */
	public List<String> getLookingForElements(){
		return browser.getDropdownElements(".id", "lookingFor");
	}

	/**
	 * select looking for
	 */
	public void selectLookingFor(String lookingFor) {
		browser.selectDropdownList(".id", "lookingFor", lookingFor);
	}

	public boolean isWarterHookupSelected(){
		return browser.isCheckBoxSelected(".id", this.getSpecificAttrDIVId()+"3006");
	}

	public boolean isSewerHookupSelected(){
		return browser.isCheckBoxSelected(".id", this.getSpecificAttrDIVId()+"3007");
	}

	public boolean isPulthroughDriveway(){
		return browser.isCheckBoxSelected(".id", this.getSpecificAttrDIVId()+"3008");
	}

	/**
	 * Default values should be unselected.
	 */
	public void verifyDefaultValueForSubCheckboxOfMoreOptions(){
		RegularExpression regx=new RegularExpression(this.getSpecificAttrDIVId()+"[0-9]+",false);
		IHtmlObject[] objs=browser.getCheckBox(".id", regx);
		if(null == objs || objs.length<1){
			throw new ObjectNotFoundException("can't find check boxes for more options");
		}
		boolean failed=false;
		for(int i=0;i<objs.length;i++){
			ICheckBox chebox=(ICheckBox)objs[i];
			if(chebox.isSelected()){
				failed=true;
				break;
			}
		}
		if(failed){
			throw new ErrorOnPageException("Default values should be unselected.");
		}
	}

	public boolean isDefaultValueForSubCheckboxOfMoreOptionsExisting(){
		RegularExpression regx=new RegularExpression("camping_2001_[0-9]+",false);
		IHtmlObject[] objs=browser.getCheckBox(".id", regx);
		boolean flag=false;
		if(null != objs && objs.length>0){
			flag=true;
		}
		Browser.unregister(objs);
		return flag;
	}


	/**
	 * judge whether looking for and other option is existing.
	 * @return
	 */
	public boolean isSubInterestedInOptionsExistingForCampingLodging(){
		boolean isExisting=false;
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", "interest_camping");
		if(objs != null && objs.length>0&&objs[0].style("display").equalsIgnoreCase("block")){
			isExisting=true;
		}
		Browser.unregister(objs);
		return isExisting;
	}

	/**
	 * Check Unselected Other Activities Count
	 * @param expectValue
	 */
	public void verifyUnselectedOtherActivitiesCount(int expectValue){
		if(this.getUnselectOtherActivitiesCount()!=expectValue){
			throw new ErrorOnDataException("The un-selected other activities applied check box should be "+expectValue);
		}
	}

	/**
	 * Click  hyperlinked in Hint message
	 */
	public void clickHintMesHyperlinked(){
		IHtmlObject[] hintObjs = browser.getHtmlObject(".id", "locationCriteria_container");
		Property[] p = new Property[2];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text","try matching alternative names of places");
		browser.clickGuiObject(p, true, 0, hintObjs[0]);

		Browser.unregister(hintObjs);
	}

	/**
	 * @param lookFor
	 */
	public void verifyLookForValue(String lookFor) {
		if(!this.getLookingForValue().equals(lookFor)){
			throw new ErrorOnPageException("Current 'Look For' value should be '"+lookFor+"'");
		}
	}


	public boolean checkWaterHookupExisting() {
		return checkSubItemExistingForMoreOptions("Water hookup");
	}

	public boolean checkSewerExisting() {
		return checkSubItemExistingForMoreOptions("Sewer hookup");
	}

	public boolean checkPullThroughDrivewayExisting() {
		return checkSubItemExistingForMoreOptions("Pull-through driveway");
	}

	public boolean checkWaterFrontExisting() {
		return checkSubItemExistingForMoreOptions("Waterfront");
	}

	public boolean checkElectricHookupExisting(){
		return checkSubItemExistingForMoreOptions("Electric hookup");
	}

	public boolean checkSubItemExistingForMoreOptions(String label){
		boolean isExisting=true;
		int index=-1;
		IHtmlObject[] divs=browser.getHtmlObject(".class", "Html.DIV", ".id", this.getSpecificAttrDIVId()+"secattrs");

		for(int i=0;i<divs.length;i++){
			if(divs[i].style("display").equalsIgnoreCase("BLOCK")){
				index=i;
			}
		}
		if(index==-1){
			Browser.unregister(divs);
			return false;
		}
		//		String value=divs[index].getProperty(".text");
		//		
		//		if(value.indexOf(label)<0){
		//			isExisting=false;
		//		}
		Property[] p=new Property[]{new Property(".class", "Html.LABEL"),new Property(".text", new RegularExpression(" ?"+label+".*",false))};
		IHtmlObject[] objs=browser.getHtmlObject(p,divs[index]);

		if(objs.length<1){
			isExisting=false;
		}

		Browser.unregister(divs,objs);
		return isExisting;
	}

	public void verifySubItemExistingForMoreOptions(String label){
		if(!checkSubItemExistingForMoreOptions(label)){
			throw new ItemNotFoundException("Can't find sub-item by label:"+label);
		}
	}

	public void verifySearchCriteriaLable(String lableText){
		boolean existed = browser.checkHtmlObjectExists(".class", "Html.LABEL", ".text", lableText);
		if(!existed){
			throw new ErrorOnDataException("Day Use lable "+lableText+" doesn't existed.");
		}
	}

	public String interestInOptionAdapter(List<String> interestInOptions, String option) {
		String toReturn = option;
		if(interestInOptions.toString().contains(option)){
			toReturn = option;
		}else if(interestInOptions.toString().contains(UwpUnifiedSearch.EVERYTHING_INSTERETED_IN)){
			toReturn = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		}else if(interestInOptions.toString().contains("Any Recreation")){
			toReturn = "Any Recreation";
		}else throw new ErrorOnPageException("Can't find matched interest in option.");

		return toReturn;
	}

	public String getUnifSearchCriteriaText(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".className", "unifSearchCriteria");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("'unifSearchCriteria' object can't be found.");
		}

		String value = objs[0].text();
		Browser.unregister(objs);
		return value;
	}
}
