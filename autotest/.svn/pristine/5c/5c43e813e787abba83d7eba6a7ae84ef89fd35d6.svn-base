package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovCommonPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * This page will be used for campsite common page's search panel.
 * 
 * The common page include("campgroundDetails.do", "campgroundMap.do", "campsiteSearch.do", and "campsiteCalendar.do")
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author bzhang
 * @Date  Nov 1, 2011
 */
public class UwpUnifiedFindProductCommonPanel extends RecgovCommonPage{
	private static UwpUnifiedFindProductCommonPanel _instance = null;

	protected UwpUnifiedFindProductCommonPanel() {
	}

	public static UwpUnifiedFindProductCommonPanel getInstance() {
		if (null == _instance)
			_instance = new UwpUnifiedFindProductCommonPanel();

		return _instance;
	}

	public boolean exists() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.DIV");
		p[1] = new Property(".className", "unifSearchRecreation");  //Find Sites DIV 
//		p[2] = new Property(".text", "[x]Find Sites");			//Find Sites DIV text
		p[2] = new Property(".text", new com.activenetwork.qa.testapi.util.RegularExpression("\\[x\\]( )?Find (Sites|Campsites)", false)); //Lesley[0130916]Update due to RA Unified Search

		return browser.checkHtmlObjectExists(p);
	}

	/** Click Search button */
	public void clickSearch(){
		browser.clickGuiObject(".class","Html.BUTTON",".text", "Search");
	}

	/**
	 * make camp site search
	 * @param bd
	 */
	public void makeSearch(BookingData bd){
		this.setupSearchCriteria(bd);
		this.clickSearch();
		this.waitLoading();
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

	/**
	 * @param lookFor
	 */
	public void verifyLookForValue(String lookFor) {
		if(!this.getLookingForValue().equals(lookFor)){
			throw new ErrorOnPageException("Current 'Look For' value should be '"+lookFor+"'");
		}
	}

	private String getDIVIDForInterstedIn(){
		RegularExpression regx=new RegularExpression("interest_(camping|dayuse|other|all|permit|tour)",false);
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV",".id",regx);

		if(objs==null||objs.length<1){
			throw new ObjectNotFoundException("Can't find the top DIV");
		}
		String topDiv="";;
		for(IHtmlObject obj:objs){
			if(obj.style("display").trim().equalsIgnoreCase("BLOCK")){
				topDiv=obj.getProperty(".id");
			}
		}
		Browser.unregister(objs);
		return topDiv;
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
				new Property(".id",new RegularExpression("(camping|dayuse|marina)_\\d+_",false))};//Sara[10/15/2013]:for marina in RA.COM
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

	public void setOccupants(String occupant){
		browser.setTextField(".id", getSpecificAttrDIVId()+"3012",occupant);
	}
	
	public boolean isOccupantsHighLighted(){
		return this.isTextFieldHighLighted(".id", getSpecificAttrDIVId()+"3012");
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

	public void focusOccupants(){
		browser.focusOn(".id",getSpecificAttrDIVId()+"3012");
	} 

	public void setOccupantsByChar(String occupant){
		char c;
		logger.info("start set 'Occupants' feild by char..");
		for(int i=0;i<occupant.length();i++){
			this.focusOccupants();
			c=occupant.charAt(i);
			this.inputEndKey();
			this.inputChar(c);
		}
	}

	public String getOccupants(){
		return browser.getTextFieldValue(".id", getSpecificAttrDIVId()+"3012");
	}

	/**
	 * Default values should be unselected.
	 */
	public void verifyDefaultValueForSubCheckboxOfMoreOptions(){
		RegularExpression regx=new RegularExpression("camping_2001_[0-9]+",false);
		IHtmlObject[] objs=browser.getCheckBox(".id", regx);
		if(objs.length<1){
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

	/**
	 * 
	 * click the "Find Sites" title DIV in order to remove mouse point out of a certain object.
	 */
	public void removeFocus(){
		browser.clickGuiObject(".class","Html.DIV", ".className", "unifSearchRecreation");
	}

	/**
	 * 
	 * @param invaildDates
	 * @return  true: the data is invalidated
	 *          false: the data is validated
	 */
	public boolean isInvaildDateParsedProperlyByDateComponent(String[] invaildDates){
		boolean isInvalidate = true; 
		
		for(int i=0;i<invaildDates.length;i++){
			this.setArrivalDate(invaildDates[i]);
			this.removeFocus();
			String valForChangableDate=this.getArrivalDate();
			
			if(valForChangableDate.trim().length()>0){
				if(valForChangableDate.equalsIgnoreCase(UwpUnifiedSearch.ARRIVALDATE_MMDDYY)){
					isInvalidate = false;
				}else{
					if(DateFunctions.isValidDate(valForChangableDate)){
						isInvalidate = false;
					}
				}
			}else{
				isInvalidate = false;
			}
		}
		
		return isInvalidate;
	}


	/**
	 * verify on the new find sites panel, there is no Date Range field any more.
	 */
	public void verifyDateRangeDisplay(){
		if (browser.checkHtmlObjectExists(".id", "rangeyes")){
			throw new ErrorOnPageException("The new unified Find Sites panel should not have Date Range feild");
		}
	}

	/**
	 * verify on the new find sites panel, there should be a clear search link.
	 */
	public void verifyClearSearchLinkDisplay(){
		Property[] p1 = new Property[]{new Property(".class", "Html.DIV"), new Property(".className", "unifSearch") };
		Property[] p2 = new Property[]{new Property(".class", "Html.DIV"), new Property(".text", "[x]") };
		
		IHtmlObject[] clearSearchObj = browser.getHtmlObject(Property.atList(p1,p2));
		if (clearSearchObj.length >0 ){
			logger.info("Verify Clear Search link display on the top of the Find Sites panel successfully.");
		}else{
			throw new ErrorOnPageException("There should be a Clear Search link on the top of the Find Sites panel.");
		}
		Browser.unregister( clearSearchObj);
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
	 * get the current loop drop down list selected value.
	 * @return
	 */
	public String getLoopValue(){
		String loopValue = "";
		loopValue = browser.getDropdownListValue(".id", "loop", 0);

		return loopValue;
	}

	/**
	 * verify the selected loop value match with the given parameter.
	 * @param expectValue
	 */
	public void verifyLoopValue(String expectValue){
		String currentValue = this.getLoopValue();
		if(expectValue.equalsIgnoreCase(currentValue)){
			logger.info("Verification for loop value successfully.");
		}else{
			logger.error("The expect  loop value is:" + expectValue);
			logger.error("The current loop value is:" + currentValue);
			throw new ErrorOnDataException("Verification for loop value failed");
		}
	}

	/**
	 * get the site# input box value.
	 * @return
	 */
	public String getSiteNumInfo(){
		return browser.getTextFieldValue(".id", "siteCode");
	}

	/**
	 * set the site# info based on given parameter.
	 * @param siteNum
	 */
	public void setSiteNumInfo(String siteNum){
		browser.setTextField(".id", "siteCode", siteNum);
	}

	public void focusSiteNum(){
		IHtmlObject[] objs = browser.getTextField(".id", "siteCode");
		if(objs.length>0){
			objs[0].click();
		}else
			throw new ObjectNotFoundException("'Site #' object can't find.");
		Browser.unregister(objs);
	} 

	public void setSiteNumTByChar(String siteNum){
		char c;
		logger.info("start set 'Site #' feild by char..");
		for(int i=0;i<siteNum.length();i++){
			this.focusSiteNum();
			c=siteNum.charAt(i);
			this.inputEndKey();
			this.inputChar(c);
		}
	}

	/**
	 * 
	 * verify the site number input box value match with the given parameter.
	 * @param expectValue
	 */
	public void verifySiteNumInfo(String expectValue){
		String currentValue = this.getSiteNumInfo();
		if(currentValue.equalsIgnoreCase(expectValue)){
			logger.info("Vefification for Site#  value successfully.");
		}else{
			logger.error("The expect  Site# value is:" + expectValue);
			logger.error("The current Site# value is:" + currentValue);
			throw new ErrorOnDataException("Verification for Site#  value failed");
		}
	}

	/**
	 * get the Length (ft) value.
	 * @return
	 */
	public String getLengthFT(){
		return browser.getTextFieldValue(".id", getSpecificAttrDIVId()+"3013");
	}

	/**
	 * get the Length (ft) value.
	 * @return
	 */
	public void setLengthFT(String lengthFt){
		browser.setTextField(".id", getSpecificAttrDIVId()+"3013", lengthFt);
	}
	
	public boolean isLengthFTHighLighted(){
		return this.isTextFieldHighLighted(".id", getSpecificAttrDIVId()+"3013");
	}
	
	public boolean checkLength_FTExisting(){
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

	public void focusLengthFT(){
		browser.focusOn(".id",getSpecificAttrDIVId()+"3013");
	} 

	public void setLengthFTByChar(String lengthFT){
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
	 * verify the value in Length(ft) field match with the given parameter.
	 * @param expectValue
	 */
	public void verifyLengthFTInfo(String expectValue){
		String currentValue = this.getLengthFT();
		if(currentValue.equalsIgnoreCase(expectValue)){
			logger.info("Vefification for LengthFT value successfully.");
		}else{
			logger.error("The expect  Length(ft) value is:" + expectValue);
			logger.error("The current Length(ft) value is:" + currentValue);
			throw new ErrorOnDataException("Verification for LengthFT value failed");
		}
	}

	public void selectMoreOptions(){
		browser.selectCheckBox(".id", getSpecificAttrDIVId()+"moreOptions");
	}

	public void unSelectMoreOptions(){
		browser.unSelectCheckBox(".id", getSpecificAttrDIVId()+"moreOptions");
	}


	public boolean checkMoreOptionsSelected(){
		String childId = this.getSpecificAttrDIVId();
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", childId);
		boolean flag = browser.isCheckBoxSelected(".id", childId+"moreOptions", objs[0]);
		Browser.unregister(objs);
		return flag;
	}


	public void verifyMoreOptionsSelected(boolean flag){
		if(this.checkMoreOptionsSelected()!=flag){
			throw new ErrorOnDataException("More options should be:" + flag);
		}
	}

	/**
	 * get Electric hookup selected value
	 * @return
	 */
	public String getElectricHookup(){
		return browser.getDropdownListValue(".id", getSpecificAttrDIVId()+"218",0);
	}


	public void verifyElectricHookup(String expectValue){
		String currentValue = this.getElectricHookup();
		if(this.getElectricHookup().equals(expectValue)){
			logger.info("Verification for the Electric Hookup value successfully.");
		}else{
			logger.error("The expect  Electric hookup value is:" + expectValue);
			logger.error("The current Electric hookup value is:" + currentValue);
			throw new ErrorOnDataException("Electric Hookup should be: "+expectValue);
		}
	}

	//Water hookup checkbox
	public void selectWaterHookup(){
		browser.selectCheckBox(".id", this.getSpecificAttrDIVId()+"3006");
	}

	public void unSelectWaterHookup(){
		browser.unSelectCheckBox(".id", this.getSpecificAttrDIVId()+"3006");
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
		if(currentValue == flag){
			logger.info("Verification for Water hookup checkbox status successfully.");
		}else{
			logger.error("The expect  Water hookup check box selected status is:" + flag);
			logger.error("The current Water hookup check box selected status is:" + currentValue);
			throw new ErrorOnDataException("Water hookup check box status verification failed.");
		}
	}

	//Sewer hookup

	/**
	 * select Sewer in more options 
	 */
	private void selectSewerHookup() {
		browser.selectCheckBox(".id", getSpecificAttrDIVId()+"3007");
	}

	public void unSelectSewerHookup(){
		browser.unSelectCheckBox(".id", this.getSpecificAttrDIVId()+"3007");
	}

	public boolean checkSewerHookupSelected(){
		return browser.isCheckBoxSelected(".id", this.getSpecificAttrDIVId()+"3007");
	}

	/**
	 * verify the pull through drive way check box select status.
	 * @param flag
	 */
	public void verifySewerHookupSelected(boolean flag){
		boolean currentValue = this.checkWaterHookupSelected();
		if(currentValue == flag){
			logger.info("Verification for SewerHookup checkbox status successfully.");
		}else{
			logger.error("The expect  SewerHookup check box selected status is:" + flag);
			logger.error("The current SewerHookup way check box selected status is:" + currentValue);
			throw new ErrorOnDataException("SewerHookup check box status verification failed.");
		}
	}

	//Pull-through driveway
	/**
	 * select Pull-through driveway in more options
	 */
	private void selectPullthroughDriveway() {
		browser.selectCheckBox(".id", getSpecificAttrDIVId()+"3008");
	}

	public void unSelectPullthroughDriveway(){
		browser.unSelectCheckBox(".id", this.getSpecificAttrDIVId()+"3008");
	}

	public boolean checkPullthroughDrivewaySelected(){
		return browser.isCheckBoxSelected(".id", this.getSpecificAttrDIVId()+"3008");
	}

	/**
	 * verify the pull through drive way check box select status.
	 * @param flag
	 */
	public void verifyPullthroughDrivewaySelected(boolean flag){
		boolean currentValue = this.checkWaterHookupSelected();
		if(currentValue == flag){
			logger.info("Verification for through drive way checkbox status successfully.");
		}else{
			logger.error("The expect  through drive way check box selected status is:" + flag);
			logger.error("The current through drive way check box selected status is:" + currentValue);
			throw new ErrorOnDataException("through drive way check box status verification failed.");
		}
	}

	//Waterfront checkbox
	public void selectWaterFront(){
		browser.selectCheckBox(".id", this.getSpecificAttrDIVId()+"3011");
	}

	public void unSelectWaterFront(){
		browser.unSelectCheckBox(".id", this.getSpecificAttrDIVId()+"3011");
	}

	public boolean checkWaterFrontSelected(){
		return browser.isCheckBoxSelected(".id", this.getSpecificAttrDIVId()+"3011");
	}

	/**
	 * verify the water front check box select status.
	 * @param flag
	 */
	public void verifyWaterFrontSelected(boolean flag){
		boolean currentValue = this.checkWaterFrontSelected();
		if(currentValue == flag){
			logger.info("Verification for Waterfront checkbox status successfully.");
		}else{
			logger.error("The expect  Waterfont check box selected status is:" + flag);
			logger.error("The current Waterfont check box selected status is:" + currentValue);
			throw new ErrorOnDataException("Water Front check box status verification failed.");
		}
	}

	public void verifyOccupantsValue(String expectValue){
		if(!this.getOccupants().equals(expectValue)){
			throw new ErrorOnDataException("Occupants should be: "+expectValue);
		}
	}


	/** Arrival Date */
	public String getArrivalDate(){
		RegularExpression regx=new RegularExpression(".*Date$",false);
		return browser.getTextFieldValue(".id", regx);
	}

	public void verifyArrivalDateValue(String expectValue){
		String dateFromPage=this.getArrivalDate();
		
		if(expectValue.trim().length()>0 && !expectValue.equalsIgnoreCase(UwpUnifiedSearch.ARRIVALDATE_MMDDYY)){
			dateFromPage=DateFunctions.formatDate(dateFromPage);
			expectValue=DateFunctions.formatDate(expectValue);
		}
		
		if (expectValue.trim().isEmpty()) {
			expectValue = UwpUnifiedSearch.ARRIVALDATE_MMDDYY;
		}
		
		if(!dateFromPage.equals(expectValue)){
			throw new ErrorOnDataException("Expect Day Use Date should be: "+expectValue);
		}
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

	public String getLengthOfStay(){
		String id=this.getDIVIDForInterstedIn();
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", id);
		String value=browser.getTextFieldValue(new Property[]{new Property(".id",  new RegularExpression("(dayUseL|l)engthOfStay",false))},objs[0]);
		Browser.unregister(objs);
		return value;
	}

	public void verifyLengthOfStayValue(String expectValue){
		if(!this.getLengthOfStay().equals(expectValue)){
			throw new ErrorOnDataException("Expect Length Of Stay should be: "+expectValue);
		}
	}


	public boolean checkAccessibilityNeedsSelected(){
		return checkCommonFacilityAttrSelected("Accessibility needs");
	}

	public void verifyAccessibilityNeedsCheckBoxSelected(boolean flag){
		if(this.checkAccessibilityNeedsSelected()!=flag){
			throw new ErrorOnDataException("Accessibility needs check box should select.");
		}
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
	 * select the loop value based on given parameter, if no match do nothing.
	 * @param loopValue
	 */
	public void selectLoopDropDownList(String loopValue){
		browser.selectDropdownList(".id", "loop",loopValue);
	}

	/**
	 * Set Arrival  Date
	 * @param date
	 */
	public void setArrivalDate(String date){
		browser.setTextField(".id", new RegularExpression("(dayUse|camping)Date",false), date);
	}


	public void setLengthOfStay(String lengthOfStay){
		String id=this.getDIVIDForInterstedIn();
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", id);
		browser.setTextField(".id", new RegularExpression("(dayUseL|l)engthOfStay",false), lengthOfStay,objs[0]);
		Browser.unregister(objs);
	}
	
	public boolean isLengthOfStayHighLighted(){
		String id=this.getDIVIDForInterstedIn();
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", id);
		return this.isTextFieldHighLighted(new Property[]{new Property(".id", new RegularExpression("(dayUseL|l)engthOfStay",false))}, objs[0]);
	}

	public void focusLengthOfStay(){
		IHtmlObject[] objs=browser.getHtmlObject(".id", new RegularExpression("(dayUseL|l)engthOfStay",false));
		if(objs.length>0){
			objs[0].click();
		}else
			throw new ObjectNotFoundException("'Length of stay' object can't find.");
		Browser.unregister(objs);
	} 

	public void setLengthOfStayByChar(String lengthOfStay){
		char c;
		logger.info("start set 'Site #' feild by char..");
		for(int i=0;i<lengthOfStay.length();i++){
			this.focusLengthOfStay();
			c=lengthOfStay.charAt(i);
			this.inputEndKey();
			this.inputChar(c);
		}
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

	/** select Accessibility needs check box */
	public void selectAccessibilityNeeds(){
		selectCommonFacilityAttr("Accessibility needs");
	}

	/** Un-select Accessibility needs check box */
	public void unSelectAccessibilityNeeds(){
		RegularExpression regx=  new RegularExpression("(dayuse|camping)_common_3009",false);
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", getDIVIDForInterstedIn());
		browser.unSelectCheckBox(".id", regx, 0, objs[0]);
		Browser.unregister(objs);
	}

	/** select Pets allowed check box */
	public void selectPetsAllowed(){
		this.selectCommonFacilityAttr("Pets allowed");
	}

	/** Un-select Pets allowed check box */
	public void unSelectPetsAllowed(){
		RegularExpression regx=  new RegularExpression("(dayuse|camping)_common_3010",false);
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", getDIVIDForInterstedIn());
		browser.unSelectCheckBox(".id", regx, 0, objs[0]);
		Browser.unregister(objs);
	}

	/**
	 * Set update criteria
	 * @param unifiedSearch
	 */
	public void setupSearchCriteria(BookingData bd){
		//Loop
		this.selectLoopDropDownList(bd.loop);

		//Site#
		this.setSiteNumInfo(bd.siteNo);

		//set looking for
		if(bd.lookFor.trim().length()>0){
			this.selectLookingFor(bd.lookFor);
			if(this.checkLength_FTExisting()){
				this.setLengthFT(bd.length);
			}
			if(this.checkOccupantsExisting()){
				this.setOccupants(bd.occupants);
			}
		}

		if (!bd.lookFor.equalsIgnoreCase("Any type of site") && bd.lookFor.length()>0 ){
			if(bd.moreOptions){
				this.selectMoreOptions();

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
				
				this.setLengthFT(bd.length);
				this.setOccupants(bd.occupants);
				
			}else{
				this.unSelectMoreOptions();
			}
		}

		//Flexible
		if(bd.flexibleValue.length()>0){
			this.selectFlexibleDropDownList(bd.flexibleValue);
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
		
		//Date
		this.setArrivalDate(bd.arrivalDate);

	}

	/**
	 * compare the search criteria in search widget with the given parameters.
	 * Check Unified Search 
	 * @param unifiedSearch
	 */
	public void verifySearchCriteria(BookingData bd){
		logger.info("Start verify data populdated in the Find Sites panel...");
		//1: verify the loop dropdown list value match with given parameter
		this.verifyLoopValue(bd.loop);

		//2: verify site# value match with given parameter
		this.verifySiteNumInfo(bd.siteNo);

		//3: verify the looking for drop down list slect value match with given parameter
		this.verifyLookForValue(bd.lookFor);

		//4: verify the specific looking for related field values
		//4.1 "any type of sites" 
		String lookFor = bd.lookFor;

		if (lookFor.equalsIgnoreCase("Any type of site") || lookFor.equalsIgnoreCase("Any camping spot")){ //Lesley[20130918]: Update for RA Unified Search panel, look for default value is Any camping spot

			//TODO add method make sure more link, lenth field won't show
		}else if(lookFor.equals("RV sites")||lookFor.equals("Trailer sites")||lookFor.equals("Tent")||lookFor.equals("Tent-only sites")||lookFor.equals("Cabins")||lookFor.equals("Lookouts")||
				lookFor.equals("Group sites") || lookFor.equals("Day use & Picnic areas") || lookFor.equals("Horse sites")||lookFor.equals("Boat sites") || lookFor.equals("Yurts")){
			//verify the occupants value
			if(lookFor.equalsIgnoreCase("Tent") ||lookFor.equalsIgnoreCase("Tent-only sites") || 
					lookFor.equalsIgnoreCase("Cabins")||lookFor.equalsIgnoreCase("Group sites") ||lookFor.equalsIgnoreCase("Lookouts")||
					lookFor.equalsIgnoreCase("Day use & Picnic areas") || lookFor.equalsIgnoreCase("Yurts")){

				this.verifyOccupantsValue(bd.occupants);
			}else{
				this.verifyLengthFTInfo(bd.length);
			}

			//verify more options...
			this.verifyMoreOptionsSelected(bd.moreOptions);

			if(bd.moreOptions){

				if (lookFor.equalsIgnoreCase("RV sites")||lookFor.equalsIgnoreCase("Trailer sites")||lookFor.equalsIgnoreCase("Group sites")||lookFor.equalsIgnoreCase("Horse sites")){
					if (lookFor.equalsIgnoreCase("Group sites")){
						this.verifyLengthFTInfo(bd.length);
					}
					if (!lookFor.equalsIgnoreCase("Horse sites")){
						this.verifyElectricHookup(bd.electricHookupValue);
						this.verifySewerHookupSelected(bd.sewerHookup);
					}

					this.verifyWaterHookupSelected(bd.waterHookup);
					this.verifyPullthroughDrivewaySelected(bd.pullthroughDriveWay);
					this.verifyWaterFrontSelected(bd.waterFront);
				}else if (lookFor.equalsIgnoreCase("Tent")||lookFor.equalsIgnoreCase("Tent-only sites") || lookFor.equalsIgnoreCase("Cabins")||lookFor.equalsIgnoreCase("Yurts")){
					this.verifyWaterFrontSelected(bd.waterFront);
				}else if(lookFor.equalsIgnoreCase("Lookouts")||lookFor.equalsIgnoreCase("Group sites")||lookFor.equalsIgnoreCase("Day use & Picnic areas")){
					this.verifyElectricHookup(bd.electricHookupValue);
					this.verifyWaterFrontSelected(bd.waterFront);
				}else if (lookFor.equalsIgnoreCase("Boat sites")){
					this.verifyOccupantsValue(bd.occupants);
				}else{
					throw new ErrorOnDataException("There is no related data for more option has been verified based on the given parameter:" + bd.lookFor);
				}
			}

		}
		//No matched looking for option for the bd.lookFor value
		else throw new ErrorOnDataException("There is no looking for items match the given parameter:" + bd.lookFor );

		this.verifyArrivalDateValue(bd.arrivalDate);
		this.verifyFlexibleDropDownListValue(bd.flexibleValue);
		this.verifyLengthOfStayValue(bd.lengthOfStay);
		if (MiscFunctions.isRECEnv() || MiscFunctions.isRAEnv() && bd.moreOptions) { //Lesley[20131112]: update due to product change in RA.com. 
			this.verifyAccessibilityNeedsCheckBoxSelected(bd.accessibilityNeeds);
			this.verifyPetsAllowedCheckBoxSelecte(bd.petsAllowed);
		}

		logger.info("Finish verify data populated in the Find Sites panel...");
	}

	/**
	 * verify search form elements.
	 * this function will check flexible field display on the search panel, clear search link display on the panel, each items in the looking for elements, and data populated in the search form.
	 * @param bd
	 * @param expectLookingForElements
	 */
	public void verifySearchFormElements(BookingData expectBd, String[] expectLookingForElements){
		//verify the flexible parameters will replace the Date range function.
		this.verifyDateRangeDisplay();
		//verify there is a Clear Search link in the facility details search form.
		this.verifyClearSearchLinkDisplay();
		//verify the looking for drop down list each items.
		List<String> currentLookingFor = this.getLookingForElements();
		if (currentLookingFor.size() != expectLookingForElements.length){
			logger.error("The expect  total looking for items is:" + expectLookingForElements.length);
			logger.error("The current total looking for items is:" + currentLookingFor.size());
			throw new ErrorOnDataException("Verification for the total number of looking for items failed.");
		}else{
			for(int i = 0 ; i < currentLookingFor.size(); i ++){
				if(!currentLookingFor.get(i).equals(expectLookingForElements[i])){
					logger.error("The expect  #" + i + " value is:" + expectLookingForElements[i]);
					logger.error("The current #" + i + " value is:" + currentLookingFor.get(i));
					throw new ErrorOnDataException("Verification for the specific looking for value failed");
				}
			}
			logger.info("Verification for the items in Looking For dropdown list successfully");
		}
		//verify the values pre-populated in the Find Sites drop down list.
		this.verifySearchCriteria(expectBd);		
	}

	/**
	 * 
	 */
	public void clickClearSearch() {
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".className", "unifSearchRecreation");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Can't found top DIV by ClassName:'unifSearchRecreation'");
		}
//		browser.clickGuiObject(Property.toPropertyArray(".class", "Html.SPAN", ".text", "[x]"), true, 0, objs[0]);
		
		//Sara[5/12/2014] In rec.gov website unified search panel, [x] is changed to DIV from SPAN
//		browser.clickGuiObject(Property.toPropertyArray(".class", "Html.SPAN", ".text", "[x]"),true,0,objs[0]);
		browser.clickGuiObject(Property.toPropertyArray(".class", "Html.DIV", ".text", "[x]"),true,0,objs[0]);
	}
}
