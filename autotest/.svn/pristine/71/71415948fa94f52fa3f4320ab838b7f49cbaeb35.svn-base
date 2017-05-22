package com.activenetwork.qa.awo.pages.web.maintenanceapps;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.Timer;

/**
 * 
 * @Description: It is for photo tool facility list page
 * The page is shown after selecting contract
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Feb 20, 2013
 */
public class PhotoToolFacilityListPage extends PhotoToolSelectFacilityPage{
	private static PhotoToolFacilityListPage _instance = null;

	public static PhotoToolFacilityListPage getInstance() {
		if (null == _instance)
			_instance = new PhotoToolFacilityListPage();

		return _instance;
	}

	protected PhotoToolFacilityListPage() {
	}

	private static int ROWS_OF_PER_PAGE = 25;

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".className", "formField") && browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "cgroundrst");
	}

	public boolean isStateDDLExisting(){
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", "selectedState");
	}

	public boolean isStateTextExisting(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", "inputlike", ".text", new RegularExpression("^State.*", false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.SPAN");
		return browser.checkHtmlObjectDisplayed(Property.atList(p1, p2));
	}

	public IHtmlObject[] getStateTextObjs(){//getTextField
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", "inputlike", ".text", new RegularExpression("^State.*", false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.SPAN");
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		if(objs==null || objs.length<2){
			throw new ObjectNotFoundException("Selected state objecte can't be found.");
		}
		return objs;
	}

	public String getStateText(){
		IHtmlObject [] objs = this.getStateTextObjs();
		String value = objs[objs.length-1].text().trim();

		Browser.unregister(objs);
		return value;
	}

	public void verifyStateText(String expectedState){
		String actualState = this.getStateText();
		if(!expectedState.equals(actualState)){
			throw new ErrorOnDataException("State text is wrong!", expectedState, actualState);
		}
		logger.info("Successfully verify state text:"+expectedState);
	}

	public void verifyNoStateDDLExisting(){
		if(this.isStateDDLExisting()){
			throw new ErrorOnDataException("State drop down list should not be existing.");
		}
		logger.info("Successfully verify state drop down list doesn't exist.");
	}

	public void verifyStateDDLExisting(){
		if(!this.isStateDDLExisting()){
			throw new ErrorOnDataException("State drop down list should be existing.");
		}
		logger.info("Successfully verify state drop down list exists.");
	}

	public void verifyNoStateTextExisting(){
		if(this.isStateTextExisting()){
			throw new ErrorOnDataException("State text should not be existing.");
		}
		logger.info("Successfully verify state text doesn't exist.");
	}

	public void selectState(String state){
		browser.selectDropdownList(".id", "selectedState", state);
	}

	public String getState(){
		return browser.getDropdownListValue(".id", "selectedState");
	}

	public void verifyState(String expectedState){
		String actualState = this.getState();
		if(!expectedState.equals(actualState)){
			throw new ErrorOnDataException("State is wrong!", expectedState, actualState);
		}
		logger.info("Successfully verify state:"+expectedState);
	}

	public List<String> getStates(){
		return browser.getDropdownElements(Property.toPropertyArray(".id", "selectedState"));
	}

	public List<String> getStatesExcludedTheFirst(){
		List<String> list = this.getStates();
		return list.subList(1, list.size());
	}

	public boolean isLetterSelectorExisting(){
		return browser.checkHtmlObjectExists("class", "Html.DIV", ".className", "letters");
	}

	public void verifyNoLetterSelectorExisting(){
		if(this.isLetterSelectorExisting()){
			throw new ErrorOnDataException("Letter selector should not be existing.");
		}
		logger.info("Successfully verify letter selector dosn't exist.");
	}

	public IHtmlObject[] getLetterSelectorsObjs(){
		Property[] p = Property.toPropertyArray(".class", "Html.DIV", ".className", "letters");
		IHtmlObject[] objs = browser.getHtmlObject(p);

		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Letter selectors objects can't be found.");
		}
		return objs;
	}

	public IHtmlObject[] getLetterSelectorObjs(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", "letters");
		Property[] p2 = Property.toPropertyArray(".class", "Html.A");
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));

		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Letter selector objects can't be found.");
		}
		return objs;
	}

	public void selectLetterSelector(String selector){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", "letters");
		Property[] p2 = Property.toPropertyArray(".class", "Html.A", ".text", selector);
		browser.clickGuiObject(Property.atList(p1, p2), false, 0);
	}

	public boolean isLetterSelected(String letter){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", "letters");
		Property[] p2 = Property.toPropertyArray(".class", "Html.A", ".text", letter);
		return browser.checkHtmlObjectExists(Property.atList(p1, p2));
	}

	public void verifyLetterSelected(String letter){
		if(isLetterSelected(letter)){
			throw new ErrorOnDataException("Letter:"+letter+" should be selected.");
		}
		logger.info("Successfully verify letter:"+letter+" is selected.");
	}

	public void selectFirstLetterSelector(){
		IHtmlObject[] objs = this.getLetterSelectorObjs();
		objs[0].click();
		Browser.unregister(objs);
	}

	public String getLetterSelectorValue(){
		IHtmlObject[] objs= this.getLetterSelectorsObjs();
		String value = objs[0].text();

		Browser.unregister(objs);
		return value;
	}

	public void verifyLetterSelectorValue(String expectedValue){
		expectedValue = expectedValue.replaceAll("\\s+", "");
		String actualValue = this.getLetterSelectorValue().replaceAll("\\s+", "");
		if(!expectedValue.equals(actualValue)){
			throw new ErrorOnDataException("Letter selector value is wrong!", expectedValue, actualValue);
		}
		logger.info("Successfully verify letter selector value:"+expectedValue);
	}

	public boolean checkFacilityExisting(String parkID){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".href", new com.activenetwork.qa.testapi.util.RegularExpression("/photoToolUpload\\.do\\?selectedParkId="+parkID, false));
	}

	public void selectFacility(String parkID){
		browser.clickGuiObject(".class", "Html.A", ".href", new com.activenetwork.qa.testapi.util.RegularExpression("/photoToolUpload\\.do\\?selectedParkId="+parkID, false));
	}

	public void selectFacility(int index){
		browser.clickGuiObject(".class", "Html.A", ".href", new com.activenetwork.qa.testapi.util.RegularExpression("/photoToolUpload\\.do\\?selectedParkId=\\d+", false), index);
	}

	public void selectFirstFacility(){
		this.selectFacility(0);
	}

	public IHtmlObject[] getFacilityListTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".className", "items");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Facilities list objects can't be found.");
		}
		return objs;
	}

	public List<String> getFacilitiesInFirstPg(){
		IHtmlObject[] objs = this.getFacilityListTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> facilities = new ArrayList<String>();

		for(int i=3; i<table.rowCount(); i++){
			facilities.add(table.getCellValue(i, 0));
		}

		Browser.unregister(objs);
		return facilities;
	}

	public List<String> getFacilities(){
		IHtmlObject[] objs = null; 
		IHtmlTable table = null;
		List<String> facilities = new ArrayList<String>();
		boolean isNextLinkDisable = true;

		do{
			//Check if the next link is able
			isNextLinkDisable = this.isNextLinkDisabled();

			//Get facilities in facility list
			objs = this.getFacilityListTable();
			table = (IHtmlTable)objs[0];
			for(int i=3; i<table.rowCount(); i++){
				facilities.add(table.getCellValue(i, 0));
			}

			Browser.unregister(objs);

			//If next link is able, click it to next page
			if(!isNextLinkDisable){
				this.navigatePage(true);
			}

		}while (!isNextLinkDisable);

		return facilities;
	}

	public void verifyFacilitiesInFirstPg(List<String> expectedFacilitiesInFirstPg){
		List<String> actualFacilitiesInFirstPg = this.getFacilitiesInFirstPg();
		if(!expectedFacilitiesInFirstPg.toString().equals(actualFacilitiesInFirstPg.toString())){
			throw new ErrorOnDataException("Facilities are wrong in first page.", expectedFacilitiesInFirstPg.toString(), actualFacilitiesInFirstPg.toString());
		}
		logger.info("Successfully verify facilities are correct in first page.");
	}

	public void verifyFacilities(List<String> expectedFacilities){
		List<String> actualFacilities= this.getFacilities();
		if(!expectedFacilities.toString().equals(actualFacilities.toString())){
			throw new ErrorOnDataException("Facilities are wrong.", expectedFacilities.toString(), actualFacilities.toString());
		}
		logger.info("Successfully verify facilities are correct.");
	}

	public List<String> getStatesInFirstPg(){
		List<String> states = new ArrayList<String>();
		IHtmlObject[] objs = this.getFacilityListTable();
		IHtmlTable table = (IHtmlTable)objs[0];

		for(int i=3; i<table.rowCount(); i++){
			states.add(table.getCellValue(i, 2));
		}
		Browser.unregister(objs);
		return states;
	}

	public void verifyStateInFacilityList(String expectedState){
		List<String> states = getStatesInFirstPg();
		for(int i=0; i<states.size(); i++){
			if(!states.get(i).equals(expectedState)){
				throw new ErrorOnDataException(states.get(i)+ " doesn't equal to "+expectedState);
			}
			logger.info("Successfully verify "+states.get(i)+ " equals to "+expectedState);
		}
	}

	public IHtmlObject[] getFacilityListTableObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "cgroundrst");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Facility list table objects can't be found.");
		}
		return objs;
	}

	public String getFilterResultContent(){
		IHtmlObject[] objs = this.getFacilityListTableObjs();
		String value = objs[0].text();

		Browser.unregister(objs);
		return value;
	}

	public void verifyFilterResultContent(String expectedValue){
		String actualValue = this.getFilterResultContent();
		if(!expectedValue.equals(actualValue)){
			throw new ErrorOnPageException("Filter result content is wrong!", expectedValue, actualValue);
		}
		logger.info("Successfully verify filter result content.");
	}

	public void synFacilities(String currentValue){
		String previousValue = this.getFilterResultContent();
		long maxWaitTime=OrmsConstants.FILE_DIALOG_LONG_SLEEP*4;

		boolean isChanged=false;
		Timer time = new Timer();

		do{
			isChanged = currentValue.equals(previousValue); //false, changed

		}while(time.diffLong() < maxWaitTime && isChanged);
		if(isChanged) {
			throw new ItemNotFoundException("Syn facility timed out in "+maxWaitTime+" ms");
		}
	}

	public String filterFacility(String contract, String state, String letterselector){
		String facilityListContent = this.getFilterResultContent();

		if(!StringUtil.isEmpty(contract)){
			this.selectContract(contract);
			this.synFacilities(facilityListContent);
			facilityListContent = this.getFilterResultContent();
		}
		if(!StringUtil.isEmpty(state)){
			this.selectState(state);
			this.synFacilities(facilityListContent);
			facilityListContent = this.getFilterResultContent();
		}
		if(!StringUtil.isEmpty(letterselector)){
			this.selectLetterSelector(letterselector);	
			this.synFacilities(facilityListContent);
			facilityListContent = this.getFilterResultContent();
		}

		return facilityListContent;
	}

	public boolean isPreviousLinkDisabled(){
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.A", ".className", "disabled", ".id", "resultPrevious"));
	}

	public boolean isPreviousLinkExist(){
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.A", ".id", "resultPrevious"));
	}

	public void verifyPreviousLinDisabled(){
		if(!this.isPreviousLinkDisabled()){
			throw new ErrorOnDataException("Previous link should be disabled.");
		}
		logger.info("Successfully verify previous link is disabled.");
	}

	public boolean isNextLinkDisabled(){
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.A", ".className", "disabled", ".id", "resultNext"));
	}

	public boolean isNextLinkExist(){
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.A", ".id", "resultNext"));
	}

	public void verifyNextLinkDisabled(){
		if(!this.isNextLinkDisabled()){
			throw new ErrorOnDataException("Next link should be disabled.");
		}
		logger.info("Successfully verify next link is disabled.");
	}

	public void clickPreviousLink(){
		browser.clickGuiObject(Property.toPropertyArray(".class", "Html.A", ".id", "resultPrevious", ".href", new com.activenetwork.qa.testapi.util.RegularExpression("/photoTool\\.do\\?startIdx=\\d+", false)));
	}

	public void clickNextLink(){
		browser.clickGuiObject(Property.toPropertyArray(".class", "Html.A", ".id", "resultNext", ".href", new com.activenetwork.qa.testapi.util.RegularExpression("/photoTool\\.do\\?startIdx=\\d+", false)));
	}

	/**
	 * Navigate time according to link "Previous" and "Next" link
	 * @param isNext --true: click "Next" link
	 *                       --false: click "Previous" link 
	 */
	public void navigatePage(boolean isNext){
		String currentValue = this.getFilterResultContent();

		if(isNext){
			if(isNextLinkExist() &&!isNextLinkDisabled()){
				this.clickNextLink();
				this.synFacilities(currentValue);
			}
		}else{
			if(isPreviousLinkExist() &&!isPreviousLinkDisabled()){
				this.clickPreviousLink();
				this.synFacilities(currentValue);
			}
		}
	}

	public IHtmlObject[] getTotalResultObjs(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.Span", ".className", "pageresults");
		Property[] p2 = Property.toPropertyArray(".class", "Html.Span", ".id", "resulttotal");
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Total result objects can't be found.");
		}
		return objs;
	}

	public int getTotalResultNum(){
		IHtmlObject[] objs = this.getTotalResultObjs();
		int result = Integer.parseInt(objs[0].text());

		Browser.unregister(objs);
		return result;
	}

	/**
	 * Get search result label via specific facility type filter and page number
	 * @param totalNum
	 * @param pageNum: 1--first page
	 *                 2--second page
	 *                 ... 
	 * @return
	 */
	public String generatePageResults(int totalNum, int pageNum){
		String pageResults = "";

		if(totalNum>(pageNum-1)*ROWS_OF_PER_PAGE){
			if(totalNum>=pageNum*ROWS_OF_PER_PAGE){
				pageResults = "Showing "+Integer.valueOf((pageNum-1)*ROWS_OF_PER_PAGE+1)+"-"+pageNum*ROWS_OF_PER_PAGE+" of "+totalNum;
			}else{
				pageResults = "Showing "+Integer.valueOf((pageNum-1)*ROWS_OF_PER_PAGE+1)+"-"+totalNum+" of "+totalNum;
			}
		}else{
			throw new ErrorOnDataException("Total number should be the bigerest.", String.valueOf(totalNum), String.valueOf((pageNum-1)*ROWS_OF_PER_PAGE));
		}

		return pageResults;
	}

	/**
	 *  Generate search result label according gave total number in first page
	 * Showing 1-25 of 2897
	 * Showing 1-4 of 4
	 * @param totalNum
	 * @return
	 */
	public String generatePageResultsInFirstPage(int totalNum){
		return generatePageResults(totalNum, 1);
	}

	/**
	 * Result like: Showing 1-25 of 2897
	 * @return
	 */
	public String getPageResults(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".className", "pageresults");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Page results objects can't be found.");
		}

		String value = objs[0].text().trim();
		Browser.unregister(objs);
		return value;
	}

	public void verifyPageResults(String expectedResult){
		String actualResult = this.getPageResults();
		if(!expectedResult.equals(actualResult)){
			throw new ErrorOnDataException("Page results is wrong!", expectedResult, actualResult);
		}
		logger.info("Successfully verify page results:"+expectedResult);
	}

	public void verifyTotalResultNumWithinOnePg(){
		int totalResult = this.getTotalResultNum();
		if(totalResult>ROWS_OF_PER_PAGE){
			throw new ErrorOnDataException("Total result number doesn't within one page.");
		}
		logger.info("Successfully verify total result number is within one page.");
	}

	public void verifyTotalResultNumMoreThanOnePg(){
		int totalResult = this.getTotalResultNum();
		if(totalResult<=ROWS_OF_PER_PAGE){
			throw new ErrorOnDataException("Total result number doesn't more than one page.");
		}
		logger.info("Successfully verify total result number is more than one page.");
	}

}
