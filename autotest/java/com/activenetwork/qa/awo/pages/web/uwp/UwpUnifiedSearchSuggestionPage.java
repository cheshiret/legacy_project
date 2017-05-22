package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.web.recgov.RecgovCommonPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:AUTO-680
 *
 * @author SWang
 * @Date  Jul 21, 2011
 */
public class UwpUnifiedSearchSuggestionPage extends RecgovCommonPage {

	private static UwpUnifiedSearchSuggestionPage _instance = null;

	public static UwpUnifiedSearchSuggestionPage getInstance() {
		if (null == _instance)
			_instance = new UwpUnifiedSearchSuggestionPage();

		return _instance;
	}

	protected UwpUnifiedSearchSuggestionPage() {
	}

	public boolean exists() {
//		boolean flag1 = browser.checkHtmlObjectExists(".className", "msg error");
//		boolean flag2 =  browser.checkHtmlObjectExists(".className", "suggestions_content");
//
//		return flag1 | flag2;
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "facilities_suggestions_list")&&!browser.checkHtmlObjectExists(".id", "progressBar");
	}

	/**
	 * Click the specific facility suggestion
	 * @param facilityName
	 */
	public void clickFacilitySuggestion(String facilityName){
		facilityName=facilityName.replace("(", "\\(").replace(")", "\\)");
		browser.clickGuiObject(".class", "Html.A",".text", new RegularExpression("^" +facilityName+ ".*", false),true);
	}
	
	public void clickExtraSuggestion(String suggestionName){
		browser.clickGuiObject(".class", "Html.A",".text", suggestionName);
	}

	public void clickFacilitySuggestion(String parkId,String contrCode){
		//Updated by: Fanny Liu
		//Fixed the regularexpression to get correct object.
		RegularExpression regx=new RegularExpression("javascript:UnifSearchEngine.*\""+contrCode+":"+parkId+".*\", \""+parkId+"\".* \\)",false);
		browser.clickGuiObject(".class", "Html.A",".href", regx,true);
	}

	public void waitForFacilitySuggestion(String parkId,String contrCode){
		RegularExpression regx=new RegularExpression("javascript:UnifSearchEngine.*\""+contrCode+":"+parkId+".*\", \""+parkId+"\".* \\)",false);
		Property[] p = Property.toPropertyArray(".class", "Html.A",".href", regx);
		browser.waitExists(p);
	}

	/**
	 * Click first s sate suggestion item
	 * @return first state name in the format of "SOUTH DAKOTA (center of State)";
	 */
	public String clickFirstStateSuggestion(){
		IHtmlObject[] div = browser.getHtmlObject(".class", "Html.DIV", ".id", "states_suggestions_list");
		IHtmlObject[] subDIV = browser.getHtmlObject(".class", "Html.A", ".id", new RegularExpression("^suggestion_\\d+",false), div[0]);

		String stateName = subDIV[0].text();
		subDIV[0].click();

		Browser.unregister(div, subDIV);
		return stateName;
	}

	/**
	 * Click the specific state suggestion
	 * @param stateName
	 */
	public void clickStatesSuggestion(String stateName){
		browser.clickGuiObject(".class", "Html.A",".text", new RegularExpression("^" +stateName+ ".*", false),true);
	}

	/**
	 * Click first facility suggestion
	 * @return
	 */
	public String clickFirstFacilitySuggestion(){
		IHtmlObject[] div = browser.getHtmlObject(".class", "Html.DIV", ".id", "facilities_suggestions_list");
		IHtmlObject[] subDIV = browser.getHtmlObject(".class", "Html.A", ".id", new RegularExpression("^suggestion_\\d+",false), div[0]);

		String facilitName = subDIV[0].getProperty(".text").split(",")[0];
		subDIV[0].click();

		Browser.unregister(div, subDIV);
		return facilitName;
	}

	/**
	 * Click first Address suggestion
	 * @return
	 */
	public String clickFirstAddressSuggestion(){
		IHtmlObject[] div = browser.getHtmlObject(".class", "Html.DIV", ".id", "addresses_suggestions_list");
		IHtmlObject[] subDIV = browser.getHtmlObject(".class", "Html.A", ".id", new RegularExpression("^suggestion_\\d+",false), div[0]);

		String facilitName = subDIV[0].getProperty(".text");
		subDIV[0].click();

		Browser.unregister(div, subDIV);
		return facilitName;
	}

	/**
	 * Get Suggestion Content
	 * @return
	 */
	public String getSuggestionContent(){
		String suggestionContent = "";
		IHtmlObject[] suggestionContentObjs = browser.getHtmlObject(".className", "suggestions_content");

		if(suggestionContentObjs.length>0){
			suggestionContent = suggestionContentObjs[0].getProperty(".text");
		}else throw new ObjectNotFoundException("Suggestion Content Object can't be found.");

		Browser.unregister(suggestionContentObjs);
		return suggestionContent;
	}

	/**
	 * Get heading (Within State, Facilities; Addresses) suggestions list content
	 * @param matchedType: Within State, Facilities; Addresses
	 * @return
	 */
	private String getHeadingSuggestionContent(String matchedType){
		logger.info("Get "+matchedType+" suggestions list DIV content.");
		IHtmlObject[] headingSuggestionContentObjs = null;
		String headingSuggestionContent = "";

		if(matchedType.equals("Within State")){
			headingSuggestionContentObjs = browser.getHtmlObject(".id", "states_suggestions_list");
		}else if(matchedType.equals("Facilities")){
			headingSuggestionContentObjs = browser.getHtmlObject(".id", "facilities_suggestions_list");
		}else if(matchedType.equals("Addresses")){
			headingSuggestionContentObjs = browser.getHtmlObject(".id", "addresses_suggestions_list");
		}else{
			throw new ErrorOnDataException("No matched heading can be found.");
		}

		if(null!=headingSuggestionContentObjs && headingSuggestionContentObjs.length>0){
			headingSuggestionContent = headingSuggestionContentObjs[0].text();
		}else throw new ObjectNotFoundException(matchedType+" suggestions list Object can't be found.");

		Browser.unregister(headingSuggestionContentObjs);
		return headingSuggestionContent;
	}

	/**
	 * Get states suggestions list DIV content
	 * @return
	 */
	public String getStatesSuggestionsListContent(){
		return this.getHeadingSuggestionContent("Within State");
	}

	/**
	 * Get facilities suggestions list DIV content
	 * @return
	 */
	public String getFacilitiesSuggestionsListContent(){
		return this.getHeadingSuggestionContent("Facilities");
	}

	/**
	 * Get addresses suggestions list DIV content
	 * @return
	 */
	public String getAddressesSuggestionsListContent(){
		return this.getHeadingSuggestionContent("Addresses");
	}

	/**
	 * Get all States suggestions
	 * @return
	 */
	public String[] getStatesSuggestions(){
		String[] statesSuggestions = null;
		IHtmlObject[] div = browser.getHtmlObject(".class", "Html.DIV", ".id", "states_suggestions_list");
		IHtmlObject[] subDIV = browser.getHtmlObject(".class", "Html.DIV", ".className", "facility_suggestion", div[0]);

		statesSuggestions = new String[subDIV.length];
		for(int i=0; i<statesSuggestions.length; i++){
			statesSuggestions[i] = subDIV[i].getProperty(".text");
		}

		Browser.unregister(div, subDIV);
		return statesSuggestions;
	}

	/**
	 * Check matching input high lighted
		1. Wihin State: highlighted the matching input
		2. Facilities:
		2.1 highlighted the whole word including matching input in facility name;
		2.2 highlighted the matching input in alias
		3. Google: mostly highlighted the matching input (Google results not under our control)
	 * @param textValue
	 * @param exactMatch: --true: all matching input is highlighted
	 *                    --false: part matching input is highlighted
	 * @return
	 */
	public boolean checkMatchingInputHighLighted(String highlightString, String suggestion, boolean exactMatch){
		boolean isMatch = false;
		Property[] p1=Property.toPropertyArray(".className", "facility_suggestion", ".text", suggestion);
		if(exactMatch) {
			Property[] p2 = Property.toPropertyArray(".class", "Html.STRONG", ".text", new RegularExpression(highlightString, false));
			isMatch = browser.checkHtmlObjectExists(Property.atList(p1, p2));
		
		} else {
			Property[] p2 = Property.toPropertyArray(".class", "STRONG", ".text", new RegularExpression(".*" + highlightString + ".*", false));
			isMatch = browser.checkHtmlObjectExists(Property.atList(p1,p2));
		}
		
		return isMatch;
	}

	/**
	 * Verify matching input high lighted
	 * @param textValue
	 * @param exactMatch: --true: all matching input is highlighted
	 *                    --false: part matching input is highlighted
	 */
	public void verifyMatchingInputHighLighted(String highlightedString, String suggestion, boolean exactMatch){
		boolean isHighLighted = this.checkMatchingInputHighLighted(highlightedString, suggestion, exactMatch);
		if(!isHighLighted){
			throw new ErrorOnDataException("It should "+(exactMatch?"exact":"part")+" matching input:"+highlightedString+" highlighted for suggestion:"+suggestion);
		}else{
			logger.info("Successfully verify"+(exactMatch?"exact":"part")+" matching input:"+highlightedString+" highlighted about suggestion:"+suggestion);
		}
	}

	/**
	 * Get first state suggeston
	 * @return
	 */
	public String getFirstStateSuggestion(){
		String firstStateSuggestion = "";
		Property[] p1 = new Property[]{new Property(".class", "Html.DIV"), new Property(".id", "states_suggestions_list")};
		Property[] p2 = new Property[]{new Property(".class", "Html.DIV"), new Property(".className", "facility_suggestion")};
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));

		if(null!=objs && objs.length>0){
			firstStateSuggestion = objs[0].text();
		}else{
			throw new ObjectNotFoundException("State suggestion object can't be found.");
		}

		Browser.unregister(objs);
		return firstStateSuggestion;
	}

	/**
	 * Get all States, Facility and GOOGLE suggestions
	 * @return
	 */
	public String[] getSuggestions(){
		String[] suggestions = null;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".className", "facility_suggestion");

		suggestions = new String[objs.length];
		for(int i=0; i<suggestions.length; i++){
			suggestions[i] = objs[i].getProperty(".text");
		}

		Browser.unregister(objs);
		return suggestions;
	}

	/**
	 * check
	 * @param isAscending
	 * @return
	 */
	public boolean checkStatesSuggestionsOrder(boolean isAscending){
		boolean flag = true;
		String[] states = this.getStatesSuggestions();
		if(isAscending){
			for (int i = 0; i <states.length-1;i ++ ){
				if (states[i].compareToIgnoreCase(states[i+1])>0){
					flag = false;
					break;
				}
			}
		}else{
			for (int i = 0; i <states.length-1;i ++ ){
				if (states[i].compareToIgnoreCase(states[i+1])<0){
					flag = false;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * check whether states suggestion list exist or not.
	 * @return
	 */
	public boolean checkStatesSuggestionsListDisplay(){
		boolean flag = false;
		IHtmlObject[] div = browser.getHtmlObject(".class", "Html.DIV", ".id", "states_suggestions_list");

		if(div.length >0 && !(div[0].style("display").equals("none"))){
			logger.info("The display style is:" + div[0].style("display"));
			flag = true;
		}
		Browser.unregister(div);
		return flag;
	}

	/**
	 * Verify state suggestions list display
	 * existed  --true: state suggestion list displays
	 *          --false: state suggestion list doesn't display
	 */
	public void verifyStatesSuggestionsListDisplay(boolean existed){
		boolean actualResult = this.checkStatesSuggestionsListDisplay();
		if(existed!=actualResult){
			throw new ErrorOnPageException("States suggestions list should "+(existed?"displays.": "doesn't display."));
		}else{
			logger.info("Successfully verify States suggestions list "+(existed?"displays.": "doesn't display."));
		}
	}

	/**
	 * check whether facilities suggestion list exist or not.
	 * @return
	 */
	public boolean checkFacilitySuggestionsListDisplay(){
		boolean flag = false;
		IHtmlObject[] div = browser.getHtmlObject(".class", "Html.DIV", ".id", "facilities_suggestions_list");

		if(div.length >0 && !(div[0].style("display").equals("none"))){
			flag = true;
		}
		Browser.unregister(div);
		return flag;
	}

	/**
	 * Verify facilities suggestions list display
	 * existed  --true: facilities suggestion list displays
	 *          --false: facilities suggestion list doesn't display
	 */
	public void verifyFacilitiesSuggestionsListDisplay(boolean existed){
		boolean actualResult = this.checkFacilitySuggestionsListDisplay();
		if(existed!=actualResult){
			throw new ErrorOnPageException("Facilities suggestions list "+(existed?"displays.": "doesn't display."));
		}
	}

	/**
	 * All matches will be hyperlinked (not including the \ufffdmatched on\ufffd label and the aliases)
	 * @return
	 */
	public boolean checkFacilityListMatchedOnAliasInfoNotHyperlinked(){
		boolean flag = true;
		IHtmlObject[] div = browser.getHtmlObject(".class", "Html.DIV", ".id", "facilities_suggestions_list");
		if(div.length != 0){
			IHtmlObject[] mathcedOnAliasHyperlink = browser.getHtmlObject(".class", "Html.A", ".text", new RegularExpression(".* matched on .*", false),div[0]);
			if(mathcedOnAliasHyperlink.length >0){
				flag = false;
			}
			Browser.unregister(mathcedOnAliasHyperlink);
		}
		Browser.unregister(div);
		return flag;
	}

	/**
	 * check whether addresses suggestion list exist or not.
	 * @return
	 */
	public boolean checkAddressSuggestionsListDisplay(){
		boolean flag = false;
		IHtmlObject[] div = browser.getHtmlObject(".class", "Html.DIV", ".id", "addresses_suggestions_list");

		if(div.length >0  && !(div[0].style("display").equals("none"))){
			flag = true;
		}
		Browser.unregister(div);
		return flag;
	}

	/**
	 * Verify addressed suggestions list display
	 * existed  --true: addresses suggestion list displays
	 *          --false: addresses suggestion list doesn't display
	 */
	public void verifyAddressesSuggestionsListDisplay(boolean existed){
		boolean actualResult = this.checkAddressSuggestionsListDisplay();
		if(existed!=actualResult){
			throw new ErrorOnPageException("Address suggestions list "+(existed?"displays.": "doesn't display."));
		}
	}

	/**
	 * Check Powered By GOOGLE icon displayed or not.
	 */
	public boolean checkPoweredByGoogleIconDisplay(){
		return browser.checkHtmlObjectExists(".class","Html.IMG",".src", new RegularExpression(".*google_power.png$",false));
	}


	/**
	 * Get all facility suggestions
	 * @return
	 */
	public String[] getFacilitySuggestions(){
		String[] facilitySuggestions = null;
		IHtmlObject[] div = browser.getHtmlObject(".class", "Html.DIV", ".id", "facilities_suggestions_list");
		IHtmlObject[] subDIV = browser.getHtmlObject(".class", "Html.DIV", ".className", "facility_suggestion", div[0]);

		facilitySuggestions = new String[subDIV.length];
		for(int i=0; i<facilitySuggestions.length; i++){
			facilitySuggestions[i] = subDIV[i].getProperty(".text");
		}

		Browser.unregister(div, subDIV);
		return facilitySuggestions;
	}

	/**
	 * Get first facility suggestion
	 * @return
	 */
	public String getFirstFacilitySuggestion(){
		String firstFacilitySuggestion = "";
		Property[] p1 = new Property[]{new Property(".class", "Html.DIV"), new Property(".id", "facilities_suggestions_list")};
		Property[] p2 = new Property[]{new Property(".class", "Html.DIV"), new Property(".className", "facility_suggestion")};
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));

		if(null!=objs && objs.length>0){
			firstFacilitySuggestion = objs[0].text();
		}else{
			throw new ObjectNotFoundException("Facility suggestion object can't be found.");
		}

		Browser.unregister(objs);
		return firstFacilitySuggestion;
	}

	/**
	 * Get all facility suggestions
	 * @return
	 */
	public String[] getFacilitySuggestions(String parkName){
		String[] facilitySuggestions = null;
		IHtmlObject[] div = browser.getHtmlObject(".class", "Html.DIV", ".id", "facilities_suggestions_list");
		IHtmlObject[] suggestions = browser.getHtmlObject(".class", "Html.A", ".text", new RegularExpression("^"+parkName+".*",false), div[0]);

		facilitySuggestions = new String[suggestions.length];
		for(int i=0; i<suggestions.length; i++){
			facilitySuggestions[i] = suggestions[i].getProperty(".text");
		}

		Browser.unregister(div, suggestions);
		return facilitySuggestions;
	}

	/**
	 * Check Facility Suggestions values
	 * @param expectedFacilitySuggestions
	 */
	public void verifyFacilitySuggestions(String[] expectedFacilitySuggestions){
		String[] actualFacilitySuggestions = this.getFacilitySuggestions();
		if(actualFacilitySuggestions.length!=expectedFacilitySuggestions.length){
			throw new ErrorOnDataException("Do not ignore 'Length of Stay' for searching for Facility suggestions!");
		}else{
			for(int i=0; i<expectedFacilitySuggestions.length; i++){
				if(!actualFacilitySuggestions[i].equals(expectedFacilitySuggestions[i])){
					throw new ErrorOnDataException("The actual facility Suggestion --- "
							+actualFacilitySuggestions[i]+" is defferent from the expected one --- "
							+expectedFacilitySuggestions);
				}
			}
		}
	}

	/**
	 * Get all Address suggestions
	 * @return
	 */
	public String[] getAddressSuggestions(){
		String[] addressSuggestions = null;

		IHtmlObject[] div = browser.getHtmlObject(".class", "Html.DIV", ".id", "addresses_suggestions_list");
		IHtmlObject[] subDIV = browser.getHtmlObject(".class", "Html.DIV", ".className", "facility_suggestion", div[0]);

		addressSuggestions = new String[subDIV.length];
		for(int i=0; i<addressSuggestions.length; i++){
			addressSuggestions[i] = subDIV[i].getProperty(".text");
		}

		Browser.unregister(div, subDIV);
		return addressSuggestions;
	}

	/**
	 * Get first address suggestion
	 * @return
	 */
	public String getFirstAddressSuggestion(){
		String firstAddressSuggestion = "";
		Property[] p1 = new Property[]{new Property(".class", "Html.DIV"), new Property(".id", "addresses_suggestions_list")};
		Property[] p2 = new Property[]{new Property(".class", "Html.DIV"), new Property(".className", "facility_suggestion")};
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));

		if(null!=objs && objs.length>0){
			firstAddressSuggestion = objs[0].text();
		}else{
			throw new ObjectNotFoundException("Addresses suggestion object can't be found.");
		}

		Browser.unregister(objs);
		return firstAddressSuggestion;
	}

	/**
	 * Get Suggestion title info, such as "Where?"
	 * @return
	 */
	public String getSuggestionTitle(){
		String suggestionTitle = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "suggestions_title");

		if (objs.length < 1){
			throw new ErrorOnPageException("Can't find the suggestions title object");
		}
		suggestionTitle = objs[0].text();
		Browser.unregister(objs);
		return suggestionTitle;
	}

	/**
	 * Get Suggestion message info, such as "We're unable to tell where you wanted to search. Did you mean any of the followings?"
	 * @return
	 */
	public String getSuggestionMessage(){
		String suggestionMsg = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "suggestions_message");

		if (objs.length < 1){
			throw new ErrorOnPageException("Can't find the suggestions Message object");
		}
		suggestionMsg = objs[0].text();
		Browser.unregister(objs);
		return suggestionMsg;
	}

	public void verifySuggestionMessage(String expectMsg){
		String currentMsg = this.getSuggestionMessage();

		if(!currentMsg.equals(expectMsg)){
			logger.error("The current suggestion msg is:" + currentMsg);
			logger.error("The expect  suggestion msg is:" + expectMsg);
			throw new ErrorOnDataException("verify suggestion message failed.");
		}else{
			logger.info("verify suggestion message on unified suggestion page successfully");
		}
	}

	/**
	 * Check Address suggestions values
	 * @param expectedAddressSuggestions
	 */
	public void verifyAddressSuggestions(String[] expectedAddressSuggestions){
		String[] actualAddressSuggestions = this.getAddressSuggestions();
		if(actualAddressSuggestions.length!=expectedAddressSuggestions.length){
			throw new ErrorOnDataException("Do not ignore 'Length of Stay' for searching for Facility suggestions!");
		}else{
			for(int i=0; i<expectedAddressSuggestions.length; i++){
				if(!actualAddressSuggestions[i].equals(expectedAddressSuggestions[i])){
					throw new ErrorOnDataException("The actual address Suggestion --- "
							+actualAddressSuggestions[i]+" is defferent from the expected one --- "
							+expectedAddressSuggestions[i]);
				}
			}
		}
	}

	/**
	 * Get error message
	 * @return
	 */
	public String getErrorMes(){
		String errorMes = "";
		IHtmlObject[] objs = browser.getHtmlObject(".className", "msg error");

		if(objs.length>0){
			errorMes = objs[0].getProperty(".text").replaceAll("\n|\r", " ");
		}else throw new ObjectNotFoundException("Message error Object can't find.");

		Browser.unregister(objs);
		return errorMes;
	}

	/**
	 * Verify error message in suggestion page
	 * @param errorMes: expected error message
	 */
	public void verifyErrorMsg(String errorMes){
		String currentMsg = this.getErrorMes();
		if(!currentMsg.equals(errorMes)){
			throw new ErrorOnDataException("The error message is wrong.", errorMes, currentMsg);
		}else{
			logger.info("verify error message on unified suggestion page successfully");
		}
	}

	
	public IHtmlObject[] getErrorMesObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(".className", "msg error");
		if(objs==null ||objs.length<1){
			throw new ObjectNotFoundException("Error message objects can't be found.");
		}
		return objs;
	}
	public void verifyErrorMsgExist(boolean exist){
		boolean actualErrorMes = false;
		IHtmlObject[] objs = this.getErrorMesObjs();
		if(!objs[0].style("display").equals("none")){
			actualErrorMes = true;
		}

		if(exist!=actualErrorMes){
			throw new ErrorOnDataException("Error message should "+(exist?"":"not ")+"exist!");
		}else logger.info("Successfully verify error message "+(exist?"exists":"doesn't exist."));

		Browser.unregister(objs);
	}

	/**
	 * Get over limit message when 1. Facility Results beyond 'max-facilities-results';
	 * 'max-facilities-results' is configuration in UI-Option
	 * @return
	 */
	public String getFacilityOverLimitMes(){
		String friendlyReminderMes = "";
		Property[] p1 = new Property[]{new Property(".class", "Html.DIV"), new Property(".id", "facilities_suggestions_list")};
		Property[] p2 = new Property[]{new Property(".className", "overlimit")};
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));

		if(objs.length>0){
			friendlyReminderMes = objs[0].getProperty(".text");
		}else throw new ObjectNotFoundException("Facility over limit Object can't find.");

		Browser.unregister(objs);
		return friendlyReminderMes;
	}

	/**
	 * Verify facility over limit message
	 * @param expectedMes
	 */
	public void verifyFacilityOverLimitMes(String expectedMes){
		String actualMes = this.getFacilityOverLimitMes();
		if(!expectedMes.equals(actualMes)){
			throw new ErrorOnDataException("Facility over limit message is incorrect.", expectedMes, actualMes);
		}else{
			logger.info("Successfully verify facility over limit message:"+expectedMes);
		}
	}
	/**
	 * check whether the large number of matches found message displayed.
	 * Message: Large number of matches found. Facility name entered may be too general.
	 * @return
	 */
	public boolean checkFacilitiesOverLimitMesDisplay(){
		boolean flag = false;
		IHtmlObject[] objTop = browser.getHtmlObject(".class", "Html.DIV", ".id", "facilities_suggestions_list");
		if(null!=objTop && objTop.length>0){
			flag = browser.checkHtmlObjectExists(".className", "overlimit", objTop[0]);
		}else{
			throw new ObjectNotFoundException("Over limit object can't be found.");
		}

		Browser.unregister(objTop);
		return flag;
	}

	/**
	 * Verify whether the over limit message displays or not
	 * @param displaied    --true: Over limit message should display
	 *                     --false: Over limit message should not display
	 */
	public void verifyOverLimitMesDisplay(boolean displaied){

		boolean actualDisplaied = this.checkFacilitiesOverLimitMesDisplay();
		if(actualDisplaied!=displaied){
			throw new ErrorOnPageException("Over limit message should "+(displaied?"displays.":"doesn't display."));
		}else{
			logger.info("Successfully verify Over limit message "+(displaied?"displays.":"doesn't display."));
		}
	}

	/**
	 * Check Powered By GOOGLE icon exist
	 */
	public void verifyPoweredByGoogleIcon(){
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.IMG",".src", new RegularExpression(".*google_power.png$",false));
		if(null!=objs && objs.length<=0){
			throw new ObjectNotFoundException("Powered By Google Icon can't find.");
		}else{
			logger.info("Successfully verify powered By Google Icon displays.");
		}
	}

	public String getHeaderAndFooterAtRecGovPg(){
		return this.getTopNavigationInfo()+" "+this.getTabBarInfo()+" "+this.getFooterInfo();
	}

	public void verifyHeaderAndFooterInfoInSuggestionPg(String expectHeadingInfo){
		if(!expectHeadingInfo.equals(this.getHeaderAndFooterAtRecGovPg())){
			throw new ErrorOnDataException("The Header and Footer info is defferent in recGov page and Suggestion page.");
		}
	}
	/**
	 * Get suggestion header as:
	 * Where? We're unable to tell where you wanted to search. Did you mean any of the following?
	 * @return
	 */
	public String getSuggestionsHeader(){
		String suggestionHeader = "";

		IHtmlObject[] objs = browser.getHtmlObject(".id", "suggestions_header");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Suggestion header object can't be found.");
		}

		suggestionHeader = objs[0].text();

		Browser.unregister(objs);
		return suggestionHeader;
	}
}
