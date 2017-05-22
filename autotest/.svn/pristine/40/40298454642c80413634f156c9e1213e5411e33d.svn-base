package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.List;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * @author SWang
 * @Date 8/21/2012
 *
 */
public class UwpPermitLotteryApplicationPage extends UwpPage {
	private static UwpPermitLotteryApplicationPage _instance = null;

	private UwpPermitLotteryApplicationPage() {}

	public static UwpPermitLotteryApplicationPage getInstance() {
		if(null == _instance) {
			_instance = new UwpPermitLotteryApplicationPage();
		}

		return _instance;
	}

	Property[] trailHeadProp = Property.toPropertyArray(".id", new RegularExpression("trailheadid\\d+", false));
	Property[] deliveryMethodProp = Property.toPropertyArray(".id", new RegularExpression("permitdeliverymethodid", false));
	Property[] entranceProp = Property.toPropertyArray(".id", new RegularExpression("entranceid\\d+", false));
	Property[] permitTypeProp = Property.toPropertyArray(".id", new RegularExpression("permittypeid\\d+", false));


	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "lotteryprefs");
	}

	public boolean isLotteryPreferredChoiced(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.TABLE", ".text", new RegularExpression("^Preferred Choice.*", false));
		return browser.checkHtmlObjectExists(p1);
	}
	
	public IHtmlObject[] getPreferredChoiceTable(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.TABLE", ".text", new RegularExpression("^Preferred Choice.*", false));
		IHtmlObject[] objs = browser.getHtmlObject(p1);
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("'Preferred Choice' table can't be found.");
		}
		return objs;
	}
	public IHtmlObject[] getLotterPreferredChoiceDDLObjs(String namePropValue){
		IHtmlObject[] table = this.getPreferredChoiceTable();
		IHtmlObject[] objs = browser.getDropdownList(Property.toPropertyArray(".name", namePropValue), table[0]);
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Lottery Preferred choice drop down list objects can't be found.");
		}
		Browser.unregister(table);
		return objs;
	}
	public IHtmlObject[] getLotterPreferredChoiceTextObjs(String namePropValue){
		IHtmlObject[] table = this.getPreferredChoiceTable();
		IHtmlObject[] objs = browser.getTextField(Property.toPropertyArray(".name", namePropValue), table[0]);
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Lottery Preferred choice text objects can't be found.");
		}
		Browser.unregister(table);
		return objs;
	}
	public void setLotterPreferredChoiceElement(String namePropValue, String elementvalue){
		IHtmlObject[] objs = this.getLotterPreferredChoiceTextObjs(namePropValue);
		((IText) objs[0]).setText(elementvalue);
		Browser.unregister(objs);
	}
	public void selectLotterPreferredChoiceElement(String namePropValue, String elementvalue){
		IHtmlObject[] objs = this.getLotterPreferredChoiceDDLObjs(namePropValue);
		((ISelect) objs[0]).select(elementvalue);
		Browser.unregister(objs);
	}
	public String getLotterPreferredChoiceDDLElement(String namePropValue){
		IHtmlObject[] objs = this.getLotterPreferredChoiceDDLObjs(namePropValue);
		String elementValue = ((ISelect) objs[0]).getSelectedText();
		Browser.unregister(objs);
		return elementValue;
	}
	public String getLotterPreferredChoiceTextElement(String namePropValue){
		IHtmlObject[] objs = this.getLotterPreferredChoiceTextObjs(namePropValue);
		String elementValue = ((IText) objs[0]).getText();
		Browser.unregister(objs);
		return elementValue;
	}
	
	public void selectLotteryPermitType(String permitType){
		this.selectLotterPreferredChoiceElement("permitType", permitType);
		this.refreshPage();
	}
	public void selectLotteryEntrance(String entrance){
		this.selectLotterPreferredChoiceElement("entrance", entrance);
	}
	public void setLotteryEntryDate(String entryDate){
		this.setLotterPreferredChoiceElement("entryDate", entryDate);
	}
	public void selectLotteryTrailHead(String trailHead){
		this.selectLotterPreferredChoiceElement("trailHeadID", trailHead);
	}
	public void setLotteryGroupSize(String groupSize){
		this.setLotterPreferredChoiceElement("groupSizePref", groupSize);
	}
	public void selectLotteryExitPoint(String exitPoint){
		this.selectLotterPreferredChoiceElement("exitPoint", exitPoint);
	}
	public void setLotteryExitDate(String exitDate){
		this.setLotterPreferredChoiceElement("exitDate", exitDate);
	}
	public String getLotteryExitDate(){
		return this.getLotterPreferredChoiceTextElement("exitDate");
	}
	public List<String> getLotteryExitPoints(){
		IHtmlObject[] objs = this.getLotterPreferredChoiceDDLObjs("exitPoint");
		List<String> elementValues = ((ISelect) objs[0]).getAllOptions();
		Browser.unregister(objs);
		return elementValues;
	}
	public void verifyLotteryExitPoints(List<String> exitPoints){
		List<String> exitPointFromUI = this.getLotteryExitPoints();
		MiscFunctions.compareStringList("Exit points", exitPoints, exitPointFromUI);
	}
	
	public boolean checkLotteryExitDateReadOnly(){
		IHtmlObject[] objs = this.getLotterPreferredChoiceTextObjs("exitDate");
		IText text = (IText)objs[0];
		boolean readOnly = text.readOnly();
		Browser.unregister(objs);
		return readOnly;
	}
	
	public void verifyLotteryExitDateReadOnly(){
		if(!this.checkLotteryExitDateReadOnly()){
			throw new ErrorOnDataException("Failed to verify 'Preferred Choice' exit date is read only.");
		}
		logger.info("Successfully verify 'Preferred Choice' exit date is read only.");
	}
	
	public boolean isLotteryAlternativeChoiced(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.TABLE", ".text", new RegularExpression("^Alternative Choice.*", false));
		return browser.checkHtmlObjectExists(p1);
	}
	
	public IHtmlObject[] getAlternativeChoiceTable(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.TABLE", ".text", new RegularExpression("^Alternative Choice.*", false));
		IHtmlObject[] objs = browser.getHtmlObject(p1);
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("'Alternative Choice' table can't be found.");
		}
		return objs;
	}
	public IHtmlObject[] getLotterAlternativeDDLChoiceObjs(String namePropValue){
		IHtmlObject[] table = this.getAlternativeChoiceTable();
		IHtmlObject[] objs = browser.getDropdownList(Property.toPropertyArray(".name", namePropValue), table[0]);
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Lottery Alternative choice drop down list objects can't be found.");
		}
		Browser.unregister(table);
		return objs;
	}
	public IHtmlObject[] getLotterAlternativeChoiceTextObjs(String namePropValue){
		IHtmlObject[] table = this.getAlternativeChoiceTable();
		IHtmlObject[] objs = browser.getTextField(Property.toPropertyArray(".name", namePropValue), table[0]);
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Lottery Alternative choice text objects can't be found.");
		}
		return objs;
	}
	public void setLotterAlternativeChoiceElement(String namePropValue, String elementvalue){
		IHtmlObject[] objs = this.getLotterAlternativeChoiceTextObjs(namePropValue);
		((IText) objs[0]).setText(elementvalue);
		Browser.unregister(objs);
	}
	public void selectLotterAlternativeChoiceElement(String namePropValue, String elementvalue){
		IHtmlObject[] objs = this.getLotterAlternativeDDLChoiceObjs(namePropValue);
		((ISelect) objs[0]).select(elementvalue);
		Browser.unregister(objs);
	}
	public String getLotterAlternativeChoiceDDLElement(String namePropValue){
		IHtmlObject[] objs = this.getLotterAlternativeDDLChoiceObjs(namePropValue);
		String elementValue = ((ISelect) objs[0]).getSelectedText();
		Browser.unregister(objs);
		return elementValue;
	}
	public String getLotterAlternativeChoiceTextElement(String namePropValue){
		IHtmlObject[] objs = this.getLotterAlternativeChoiceTextObjs(namePropValue);
		String elementValue = ((IText) objs[0]).getText();
		Browser.unregister(objs);
		return elementValue;
	}

	public void selectLotteryAlternativePermitType(String permitType){
		this.selectLotterAlternativeChoiceElement("permitType", permitType);
		this.refreshPage();
	}
	
	public void waitForLotteryAlternativeElementSync(String id, String value){
		Property[] p1 = Property.toPropertyArray(".class", "Html.TABLE", ".text", new RegularExpression("^Alternative Choice.*", false));
		Property[] p2 = Property.toPropertyArray(".id", id, ".text", new RegularExpression(".*"+value+".*", false));
		browser.searchObjectWaitExists(Property.atList(p1, p2), timeout);
	}
	public void waitForLotteryAlternativeEntranceSync(String entrance){
		this.waitForLotteryAlternativeElementSync("entranceid1", entrance);
	}
	
	public void waitForLotteryAlternativeExitPointSync(String exitPoint){
		this.waitForLotteryAlternativeElementSync("exitpointid1", exitPoint);
	}
	
	public void waitForLotteryAlternativeEntryDateSync(String entryDate){
		this.waitForLotteryAlternativeElementSync("entrydateid1", entryDate);
	}
	
	public void selectLotteryAlternativePermitTypeToSyncEntrance(String permitType, String entrance){
		this.selectLotteryAlternativePermitType(permitType);
		this.waitForLotteryAlternativeEntranceSync(entrance);
	}
	
	public void selectLotteryAlternativeEntrance(String entrance){
		this.selectLotterAlternativeChoiceElement("entrance", entrance);
	}
	
	public void setLotteryAlternativeEntryDate(String entryDate){
		this.setLotterAlternativeChoiceElement("entryDate", entryDate);
	}
	
	public void setLotteryAlternativeTrailHead(String trailHead){
		this.selectLotterAlternativeChoiceElement("trailHeadID", trailHead);
	}
	
	public void setLotteryAlternativeGroupSize(String groupSize){
		this.setLotterAlternativeChoiceElement("groupSizePref", groupSize);
	}
	
	public void selectLotteryAlternativeExitPoint(String exitPoint){
		this.selectLotterAlternativeChoiceElement("exitPoint", exitPoint);
	}
	
	public void setLotteryAlternativeExitDate(String exitDate){
		this.setLotterAlternativeChoiceElement("exitDate", exitDate);
	}
	public String getLotteryAlternativeExitDate(){
		return this.getLotterAlternativeChoiceTextElement("exitDate");
	}
	
	public List<String> getLotteryAlternativeExitPoints(){
		IHtmlObject[] objs = this.getLotterAlternativeDDLChoiceObjs("exitPoint");
		List<String> elementValues = ((ISelect) objs[0]).getAllOptions();
		Browser.unregister(objs);
		return elementValues;
	}
	public void verifyLotteryAlternativeExitPoints(List<String> exitPoints){
		List<String> exitPointFromUI = this.getLotteryAlternativeExitPoints();
		MiscFunctions.compareStringList("Lottery alternative Exit points", exitPoints, exitPointFromUI);
	}
	
	public boolean checkLotteryAlternativeExitDateReadOnly(){
		IHtmlObject[] objs = this.getLotterAlternativeChoiceTextObjs("exitDate");
		IText text = (IText)objs[0];
		boolean readOnly = text.readOnly();
		Browser.unregister(objs);
		return readOnly;
	}
	
	public void verifyLotteryAlternativeExitDateReadOnly(){
		if(!this.checkLotteryAlternativeExitDateReadOnly()){
			throw new ErrorOnDataException("Failed to verify 'Alternative Choice 1' exit date is read only.");
		}
		logger.info("Successfully verify 'Alternative Choice 1' exit date is read only.");
	}
	
	public IHtmlObject[] getPreferredChoiceTR(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Preferred Choice.*", false));
		if(null==objs || objs.length<1){
			throw new ObjectNotFoundException("Preferred Choice TR can't be found.");
		}
		return objs;
	}
	
	public IHtmlObject[] getAlternativeChoiceTR(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Alternative Choice.*", false));
		if(null==objs || objs.length<1){
			throw new ObjectNotFoundException("Alternative Choice TR can't be found.");
		}

		return objs;
	}

	/**
	 * Select Preferred Choice Trail Head
	 * @param item
	 */
	public void selectPreferredChoiceTrailHead(String item){
		IHtmlObject[] objs = this.getPreferredChoiceTR();
		browser.selectDropdownList(trailHeadProp, item, true, objs[0]);
		Browser.unregister(objs);
	}

	/**
	 * Select Alternative Choice Trail Head
	 * @param item
	 */
	public void selectAlternativeChoiceTrailHead(String item){
		IHtmlObject[] objs = this.getAlternativeChoiceTR();
		browser.selectDropdownList(trailHeadProp, item, true, objs[0]);
		Browser.unregister(objs);
	}

	/**
	 * Get Preferred Choice Trail Head value
	 * @return
	 */
	public String getPreferredChoiceTrailHeadValue(){
		IHtmlObject[] objs = this.getPreferredChoiceTR();
		String value = browser.getDropdownListValue(trailHeadProp, 0, objs[0]);
		Browser.unregister(objs);
		return value;
	}

	/**
	 * Get Alternative Choice Trail Head value
	 * @return
	 */
	public String getAlternativeChoiceTrailHeadValue(){
		IHtmlObject[] objs = this.getAlternativeChoiceTR();
		String value = browser.getDropdownListValue(trailHeadProp, 0, objs[0]);
		Browser.unregister(objs);
		return value;
	}

	/**
	 * Get Preferred Choice Trail Head Options
	 * @return
	 */
	public List<String> getPreferredChoiceTrailHeadOptions(){
		IHtmlObject[] objs = this.getPreferredChoiceTR();
		List<String> values = browser.getDropdownElements(trailHeadProp, objs[0]);
		Browser.unregister(objs);
		return values;
	}

	/**
	 * Get Alternative Choice Trail Head Options
	 * @return
	 */
	public List<String> getAlternativeChoiceTrailHeadOptions(){
		IHtmlObject[] objs = this.getAlternativeChoiceTR();
		List<String> values = browser.getDropdownElements(trailHeadProp, objs[0]);
		Browser.unregister(objs);
		return values;
	}

	/**
	 * Get Delivery Method value
	 * @return
	 */
	public String getPreferredChoiceDeliveryMethodValue(){
		return browser.getDropdownListValue(deliveryMethodProp, 0);
	}

	/**
	 * Select Alternative Choice Permit Type(
	 * @param item
	 */
	public void selectAlternativeChoicePermitType(String item){
		IHtmlObject[] objs = this.getAlternativeChoiceTR();
		browser.selectDropdownList(permitTypeProp, item, true, objs[0]);
		//After select permit type, related entrance will display after several min, click empty will not work to the refresh
		Browser.sleep(5);
		Browser.unregister(objs);
	}

	/**
	 * Select Preferred Choice Permit Type
	 * @param item
	 */
	public void selectPreferredChoicePermitType(String item){
		IHtmlObject[] objs = this.getPreferredChoiceTR();
		browser.selectDropdownList(permitTypeProp, item, true, objs[0]);
		//After select permit type, related entrance will display after several min, click empty will not work to the refresh
		Browser.sleep(5);
		Browser.unregister(objs);
	}

	/**
	 * Select Alternative Choice entrance
	 * @param item
	 */
	public void selectAlternativeChoiceEntrance(String item){
		IHtmlObject[] objs = this.getAlternativeChoiceTR();
		browser.selectDropdownList(entranceProp, item, true, objs[0]);
		Browser.unregister(objs);
	}

	/**
	 * Select Preferred Choice entrance
	 * @param item
	 */
	public void selectPreferredChoiceEntrance(String item){
		IHtmlObject[] objs = this.getPreferredChoiceTR();
		browser.selectDropdownList(entranceProp, item, true, objs[0]);
		Browser.unregister(objs);
	}

	/**
	 * Get Preferred Choice Permit Type Options
	 * @return
	 */
	public List<String> getPreferredChoicePermitTypeOptions(){
		IHtmlObject[] objs = this.getPreferredChoiceTR();
		List<String> values = browser.getDropdownElements(permitTypeProp, objs[0]);
		Browser.unregister(objs);
		return values;
	}

	/**
	 * Get Alternative Choice Permit Type Options
	 * @return
	 */
	public List<String> getAlternativeChoicePermitTypeOptions(){
		IHtmlObject[] objs = this.getAlternativeChoiceTR();
		List<String> values = browser.getDropdownElements(permitTypeProp, objs[0]);
		Browser.unregister(objs);
		return values;
	}


	/**
	 * Get Preferred Choice Entrance Options
	 * @return
	 */
	public List<String> getPreferredChoiceEntrancesOptions(){
		IHtmlObject[] objs = this.getPreferredChoiceTR();
		List<String> values = browser.getDropdownElements(entranceProp, objs[0]);
		Browser.unregister(objs);
		return values;
	}

	/**
	 * Get Alternative Choice Entrance Options
	 * @return
	 */
	public List<String> getAlternativeChoiceEntrancesOptions(){
		IHtmlObject[] objs = this.getAlternativeChoiceTR();
		List<String> values = browser.getDropdownElements(entranceProp, objs[0]);
		Browser.unregister(objs);
		return values;
	}
	
	/**
	 * Verify Alternative Choice trail head value
	 * @param expectedValue
	 */
	public void verifyAlternativeChoiceTrailHeadValue(String expectedValue){
		logger.info("Start to verify Alternative Choice Trail Head.");
		String actualTrailHead = this.getAlternativeChoiceTrailHeadValue();

		if(!expectedValue.equals(actualTrailHead)){
			throw new ErrorOnPageException("Alternative Choice Trail Head is wrong!", expectedValue, actualTrailHead);
		}
		logger.info("Successfuly verify Alternative Choice Trail Head: "+actualTrailHead);
	} 
	
	/**
	 * Verify Preferred Choice trail head value
	 * @param expectedValue
	 */
	public void verifyPreferredChoiceTrailHeadValue(String expectedValue){
		logger.info("Start to verify Preferred Choice Trail Head.");
		String actualTrailHead = this.getPreferredChoiceTrailHeadValue();

		if(!expectedValue.equals(actualTrailHead)){
			throw new ErrorOnPageException("Preferred Choice Trail Head is wrong!", expectedValue, actualTrailHead);
		}
		logger.info("Successfuly verify Preferred Choice Trail Head: "+actualTrailHead);
	} 

	/**
	 * Verify Preferred Choice Trail Head display
	 * @param TrailHeads: all the active or inactive Trail Heads
	 * @param isActive: true:all the active options display, false:the inactive don't display
	 */
	public void verifyPreferredChoiceTrailHeadDDListOptions(boolean isActive, List<String> trailHeads){
		logger.info("Start to verify all Preferred Choice "+(isActive?"active":"inactive")+" Trail Heads "+(isActive?"":"don't ")+"display.");

		List<String> preferredChoiceTrailHeadOptions = this.getPreferredChoiceTrailHeadOptions();
		//Remove the default trail options"- Please Select -"
		preferredChoiceTrailHeadOptions.remove("- Please Select -");

		if(isActive && trailHeads.size()!=preferredChoiceTrailHeadOptions.size()){
			throw new ErrorOnPageException("The size of Preferred Choice Trail Heads is wrong.", trailHeads.size(), preferredChoiceTrailHeadOptions.size());
		}

		//Verify all active options display, the inactive don't display
		for(int i=0; i<preferredChoiceTrailHeadOptions.size(); i++){
			if(isActive!=trailHeads.toString().contains(preferredChoiceTrailHeadOptions.get(i))){
				throw new ErrorOnPageException("Not all preferred choice "+(isActive?"active":"inactive")+" Trail Heads "+(isActive?"":"don't ")+"display.");
			}
		}
		
		logger.info("Successfully verify all preferred choice "+(isActive?"active":"inactive")+" Trail Heads "+(isActive?"":"don't ")+"display.");
	}

	/**
	 * Verify Alternative Choice Trail Head display
	 * @param TrailHeads: all the active or inactive Trail Heads
	 * @param isActive: true:all the active options display, false:the inactive don't display
	 */
	public void verifyAlternativeChoiceTrailHeadDDListOptions(boolean isActive, List<String> trailHeads){
		logger.info("Start to verify all Alternative Choice "+(isActive?"active":"inactive")+" Trail Heads "+(isActive?"":"don't ")+"display.");

		List<String> alternativeChoiceTrailHeadOptions= this.getAlternativeChoiceTrailHeadOptions();
		//Remove the default trail options"- Please Select -"
		alternativeChoiceTrailHeadOptions.remove("- Please Select -");

		if(isActive && trailHeads.size()!=alternativeChoiceTrailHeadOptions.size()){
			throw new ErrorOnPageException("The size of Alternative Choice Trail Heads is wrong.", trailHeads.size(), alternativeChoiceTrailHeadOptions.size());
		}

		//Verify all active options display, the inactive don't display
		for(int i=0; i<alternativeChoiceTrailHeadOptions.size(); i++){
			if(isActive!=trailHeads.toString().contains(alternativeChoiceTrailHeadOptions.get(i))){
				throw new ErrorOnPageException("Not all alternative choice "+(isActive?"active":"inactive")+" Trail Heads "+(isActive?"":"don't ")+"display.");
			}
		}
		
		logger.info("Successfully verify all alternative choice "+(isActive?"active":"inactive")+" Trail Heads "+(isActive?"":"don't ")+"display.");
	}

	/**
	 * Verify Preferred Choice Entrance display
	 * @param TrailHeads: all the active or inactive Trail Heads
	 * @param isActive: true:all the active options display, false:the inactive don't display
	 */
	public void verifyPreferredChoiceEntranceDDListOptions(boolean isActive, List<String> entrances){
		logger.info("Start to verify all Preferred Choice "+(isActive?"active":"inactive")+" Entrance "+(isActive?"":"don't ")+"display.");

		List<String> preferredChoiceEntrance = this.getPreferredChoiceEntrancesOptions();
		//Remove the default trail options"- Please Select -"
		preferredChoiceEntrance.remove("- Please Select -");

		if(isActive && entrances.size()!=preferredChoiceEntrance.size()){
			throw new ErrorOnPageException("The size of Preferred Choice Entrance is wrong.", entrances.size(), preferredChoiceEntrance.size());
		}
		
		//Verify all active options display, the inactive don't display
		for(int i=0; i<preferredChoiceEntrance.size(); i++){
			if(isActive!=entrances.toString().contains(preferredChoiceEntrance.get(i))){
				throw new ErrorOnPageException("Not all preferred choice "+(isActive?"active":"inactive")+" Entrance "+(isActive?"":"don't ")+"display.");
			}
		}

		logger.info("Successfully verify all preferred choice"+(isActive?"active":"inactive")+" Trail Heads "+(isActive?"":"don't ")+"display.");
	}

	/**
	 * Verify Alternative Choice Entrance display
	 * @param TrailHeads: all the active or inactive Trail Heads
	 * @param isActive: true:all the active options display, false:the inactive don't display
	 */
	public void verifyAlternativeChoiceEntranceDisplay(boolean isActive, List<String> entrances){
		logger.info("Start to verify all Alternative Choice "+(isActive?"active":"inactive")+" Entrance "+(isActive?"":"don't")+" display.");

		List<String> alternativeChoiceEntrances= this.getAlternativeChoiceEntrancesOptions();
		//Remove the default trail options"- Please Select -"
		alternativeChoiceEntrances.remove("- Please Select -");

		if(isActive && entrances.size()!=alternativeChoiceEntrances.size()){
			//Verify all active options display
			throw new ErrorOnPageException("The size of Alternative Choice Entrance is wrong!", entrances.size(), alternativeChoiceEntrances.size());
		}

		//Verify all active options display, the inactive don't display
		for(int i=0; i<alternativeChoiceEntrances.size(); i++){
			if(isActive!=entrances.toString().contains(alternativeChoiceEntrances.get(i))){
				throw new ErrorOnPageException("Not all alternative choice "+(isActive?"active":"inactive")+" Entrance "+(isActive?"":"don't ")+"display.");
			}
		}

		logger.info("Successfully verify all alternative choice "+(isActive?"active":"inactive")+" Entrances "+(isActive?"":"don't ")+"display.");
	}

	public void waitforTripItineraryDDL(){
		browser.searchObjectWaitExists(Property.toPropertyArray(".id", "tripItineraryInfoRow"), LONG_SLEEP);
	}
	
	/**
	 * Get the number of "Trip Itinerary"
	 * @return
	 */
	public int getTripItineraryRows(){
		waitforTripItineraryDDL();
		IHtmlObject[] objs = browser.getHtmlObject(".id", "tripItineraryInfoRow");
        int count = objs.length;
		Browser.unregister(objs);
		return count;
	}

	/**
	 * Verify Trip Itinerary rows
	 * @param expectedRows
	 */
	public void verifyTripItineraryRows(int expectedRows){
		int actualRows = this.getTripItineraryRows();
		if(expectedRows!=actualRows){
			throw new ErrorOnPageException("Trip Itinerary rows is wrong!", String.valueOf(expectedRows), String.valueOf(actualRows));
		}
		logger.debug("Successfully verify Trip Itinerary rows:"+actualRows);
	}

	public void setExitDate(String date) {
		browser.setTextField(".id", new RegularExpression("exitdateid|exitdate(estimated)id0", false), date);
	}

	public void refreshPage(){
//		browser.clickGuiObject(".class", "Html.SPAN", ".text", new RegularExpression("^Exit Date(\\*)?$", false));
		browser.clickGuiObject(Property.toPropertyArray(".class", "Html.DIV", ".className", "contenthdr", ".text", new RegularExpression("^Group Leader Information.*", false)), true, 0);
	}

	/**
	 * Wait for the number of trip itinerary row display
	 * @param row: 1,2,3...
	 */
	public void waitForTripItinerarySync(int row){
//		Property[] p = Property.toPropertyArray(".id", new RegularExpression("tripitinerarypoint"+String.valueOf(row-1),false));
		Property[] p = Property.toPropertyArray(".id", new RegularExpression("tripitinerarypoint"+String.valueOf(row-1)+"id",false));
		browser.waitExists(p);
	} 

	/**
	 * Set Exit date in permit order details page
	 * @param exitDate: exit date
	 * @param tripItineraryRow: trip itinerary row
	 */
	public void setExitDateToSyncTripItinerary(String exitDate, int tripItineraryRow){
		logger.info("Setup exit date to sync trip itinerary.");
		this.setExitDate(exitDate);
		this.refreshPage();
		this.waitForTripItinerarySync(tripItineraryRow);
	}
	
	public void setPreferredExitDateToSyncTripItinerary(String exitDate, int tripItineraryRow){
		logger.info("Setup 'Preferred Choice' exit date to sync trip itinerary.");
		this.setLotteryExitDate(exitDate);
		this.refreshPage();
		this.waitForTripItinerarySync(tripItineraryRow);
	}
	
	/**
	 * Get trip itinerary
	 * @param row: 1, 2, 3...
	 * @return
	 */
	public List<String> getTripItinerarys(int row){
//		return browser.getDropdownElements(".id", new RegularExpression("tripitinerarypoint"+String.valueOf(row-1),false));
		return browser.getDropdownElements(".id", new RegularExpression("tripitinerarypoint"+String.valueOf(row-1)+"id",false));
	}
	
	/**
	 * Verify Trip Itinerary display
	 * @param TrailHeads: all the active or inactive Trail Heads
	 * @param isActive: true: all the active options display, false:the inactive don't display
	 * @param row:1:the first row of trip itinerary drop down list, 2:the second row of trip itinerary drop down list...
	 */
	public void verifyTripItinerarysDisplay(boolean isActive, List<String> tripItinerarys, int row){
		logger.info("Start to verify all "+(isActive?"active":"inactive")+" Trip Itinerary "+(isActive?"":"don't")+" display.");

		List<String> actualvalues = this.getTripItinerarys(row);
		//Remove the default trail options"- Please Select -"
		actualvalues.remove("- Please Select -");

		if(isActive && tripItinerarys.size()!=actualvalues.size()){
			throw new ErrorOnPageException("The size of Trip Itinerary is wrong!", tripItinerarys.size(), actualvalues.size());
		}

		//Verify all inactive options don't display
		for(int i=0; i<actualvalues.size(); i++){
			if(isActive!=tripItinerarys.toString().replaceAll("\\s+", " ").contains(actualvalues.get(i))){
				throw new ErrorOnPageException("Not all "+(isActive?"active":"inactive")+" Trip Itinerary "+(isActive?"":"don't ")+"display.");
			}
		}

		logger.info("Successfully verify all "+(isActive?"active":"inactive")+" Trip Itinerary "+(isActive?"":"don't ")+"display.");
	}

	/**
	 * Verify specific trip itinerary option displays
	 * @param tripItinerary
	 * @param display
	 * @param row
	 */
	public void verifySpecificTripItineraryOptionDisplays(String tripItinerary, boolean display, int row){
		logger.info("Verify the Trip Itinerary (Name:"+tripItinerary+") "+(display?"":"doesn't")+" display in 'Lottery Applicable' page");
		List<String> allTripItinerarys = this.getTripItinerarys(row);
		boolean result = allTripItinerarys.toString().contains(tripItinerary);

		if(display!=result){
			throw new ErrorOnPageException(allTripItinerarys.toString()+" should "+(display?"":"not")+"contain the trip itinerary:"+tripItinerary);
		}
		logger.info("Successfully verify the trip itinerary (Name:"+tripItinerary+") "+(display?"":"doesn't")+" display.");
	}
	
	/**
	 * Verify Alternative Choice specific Trail Head display
	 * @param trailHead
	 * @param display: true:trail head display, false:doesn't display
	 * @param isPreferredChoice true: in Preferred Choice section, false: in Alternative section
	 */
	public void verifyAlternativeChoiceSpecificTrailHeadOptionDisplay(String trailHead, boolean display){
		logger.info("Verify the Trail Head (Name:"+trailHead+") "+(display?"":"doesn't")+" display in 'Lottery Applicable' page");
		List<String> allTrailHead = this.getAlternativeChoiceTrailHeadOptions();
		
		boolean result = allTrailHead.toString().contains(trailHead);

		if(display!=result){
			throw new ErrorOnPageException(allTrailHead.toString()+" should "+(display?"":"not ")+"contain the Trail Head:"+trailHead+" in Preferred Choice section.");
		}
		logger.info("Successfully verify the Trail Head (Name:"+trailHead+") "+(display?"":"don't ")+"display in Preferred Choice section.");
	}
	
	/**
	 * Verify Preferred Choice specific Trail Head display
	 * @param trailHead
	 * @param display: true:trail head display, false:doesn't display
	 * @param isPreferredChoice true: in Preferred Choice section, false: in Alternative section
	 */
	public void verifyPreferredChoiceSpecificTrailHeadOptionDisplay(String trailHead, boolean display){
		logger.info("Verify the Trail Head (Name:"+trailHead+") "+(display?"":"doesn't ")+"display in 'Lottery Applicable' page");
		List<String> allTrailHead = this.getPreferredChoiceTrailHeadOptions();
	
		boolean result = allTrailHead.toString().contains(trailHead);

		if(display!=result){
			throw new ErrorOnPageException(allTrailHead.toString()+" should "+(display?"":"not ")+"contain the Trail Head:"+trailHead+" in Preferred Choice section.");
		}
		logger.info("Successfully verify the Trail Head (Name:"+trailHead+") "+(display?"":"don't")+" display in Alternative Choice section.");
	}
	
	/**
	 * Get facility name in "Applying for" section
	 * @return
	 */
	public String getFacility(){
		return browser.getDropdownListValue(".id", "facilityid");
	}
	
	/**
	 * Verify facility name
	 * @param facilityName: Expected facility name
	 */
	public void verifyFacilityName(String facilityName){
		String nameFromUI = this.getFacility();
		if (!nameFromUI.equals(facilityName)) {
			throw new ErrorOnDataException("The facility name is incorrect.", facilityName, nameFromUI);
		}
		logger.info("Successfully verify facility name:"+facilityName);
	}
	
	/**
	 * Check if the the "Facility" drop down list of "Applying for" section is disabled 
	 * @return
	 */
	public boolean checkFacilityDisabled(){
		boolean disabled = false;
		IHtmlObject[] objs = browser.getHtmlObject(".id", "facilityid");
		if(objs.length<0){
			throw new ObjectNotFoundException("'Appling for' object can't be found. ");
		}else {
			if(objs[0].getProperty(".disabled").equals("true")){
				disabled = true;
			}	
		}
		
		Browser.unregister(objs);
		return disabled;
	}
	
	public void verifyFacilityDisabled(){
		boolean result = this.checkFacilityDisabled();
		if(!result){
			throw new ErrorOnDataException("Failed to verify the facility name in 'Applying for' section should be disabled.");
		}
		logger.info("Successfully verify the facility name in 'Applying for' section is disabled.");
	}
	
	public String getLotteryPreferencesContent(){
		return browser.getObjectText(".class", "Html.DIV", ".id", "lotteryprefs");
	}
}
