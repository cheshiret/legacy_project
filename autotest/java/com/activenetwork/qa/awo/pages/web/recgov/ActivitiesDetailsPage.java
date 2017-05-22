package com.activenetwork.qa.awo.pages.web.recgov;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.KeyInput;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.Timer;


/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Dec 26, 2011
 */
public class ActivitiesDetailsPage extends RecgovCommonPage{

	private static ActivitiesDetailsPage _instance=null;

	public static ActivitiesDetailsPage getInstance() {
		if(null==_instance) {
			_instance=new ActivitiesDetailsPage();
		}

		return _instance;
	}

	public ActivitiesDetailsPage() {}

	/**
	 * Check whether this page exist according to the Input object(Enter a location or zip code:)
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.text", ".id", "locationCriteria");
	}

	public void clickActivities(String title){
		RegularExpression reg = new RegularExpression(".*marketing\\.do\\?goto=acm\\/Explore_And_More.*", false);
		browser.clickGuiObject(".href", reg, ".text", title);
	}

	public void clickAutoTouring(){
		this.clickActivities("AutoTouring");
	}
	public void clickBiking(){
		this.clickActivities("Biking");
	}
	public void clickBoating(){
		this.clickActivities("Boating");
	}
	public void clickCamping(){
		this.clickActivities("Camping");
	}
	public void clickClimbing(){
		this.clickActivities("Climbing");
	}
	public void clickHiking(){
		this.clickActivities("Hiking");
	}
	public void clickHistoricCultualSites(){
		this.clickActivities("Historic & Cultural Sites");
	}
	public void clickHorsebackRiding(){
		this.clickActivities("Horseback Riding");
	}
	public void clickHunting(){
		this.clickActivities("Hunting");
	}
	public void clickOffHighwayVehicle(){
		this.clickActivities("Off Highway Vehicle");
	}
	public void clickVisitorCenters(){
		this.clickActivities("Visitor Centers");
	}
	public void clickWildlifeViewing(){
		this.clickActivities("Wildlife Viewing");
	}
	public void clickWinterSports(){
		this.clickActivities("Winter Sports");
	}

	public void setLocationOrZipCode(String locationOrZipCode){
		browser.setTextField(".id", "locationCriteria", locationOrZipCode);
	}
	
	public void waitExploreActivitiesLoadingComplete(){
		Property[] p = Property.toPropertyArray(".id", "locationCriteria");
		browser.waitExists(OrmsConstants.SHORT_SLEEP_BEFORE_CHECK, p);
	}

	public void focusLocationOrZipCode(){
		IHtmlObject[] objs = browser.getTextField(".id", "locationCriteria");
		if(objs.length>0){
			objs[0].click();
		}else
			throw new ObjectNotFoundException("'Location or ZiP Code' object can't find.");
		Browser.unregister(objs);
	} 

	public void inputEndKey(){
		KeyInput.get(KeyInput.END);
	}

	public void inputCharacter(char charactor){
		if(charactor==' '){
			Browser.getInstance().inputKey(KeyInput.get(" "));
		}else{
			Browser.getInstance().inputKey(KeyInput.get("{"+charactor+"}"));
		}
	}

	public void setLocationOrZipCodeByChar(String locationOrZipCode){
		char c;
		logger.info("start set 'Location or Zip Code' feild by char..");
		for(int i=0;i<locationOrZipCode.length();i++){
			this.focusLocationOrZipCode();
			c=locationOrZipCode.charAt(i);
			this.inputEndKey();
			this.inputCharacter(c);
		}
	}

	public String getLocationOrZipCode(){
		return browser.getTextFieldValue(".id", "locationCriteria");
	}

	public String getLocationOrZipCodeDefaultValue(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", "locationCriteria");
		if(objs.length<1){
			throw new ObjectNotFoundException("LocationOrZipCode objects can't be found.");
		}
		String value = objs[0].getProperty("placeholder");
		Browser.unregister(objs);
		return value;
	}
	
	public void verifyLocationOrZipCode(String expectedLocationOrZipCode, boolean isDefaultValue){
		String actualLocationOrZipCode = StringUtil.EMPTY;
		if(isDefaultValue){
			actualLocationOrZipCode = getLocationOrZipCodeDefaultValue();
		}else actualLocationOrZipCode = this.getLocationOrZipCode();
		if(!actualLocationOrZipCode.equals(expectedLocationOrZipCode)){
			throw new ErrorOnPageException("The expected value should be:"+expectedLocationOrZipCode+", " +
					"but the actual one is:"+actualLocationOrZipCode);
		}
	}

	public void verifyLocationOrZipCode(String expectedLocationOrZipCode){
		verifyLocationOrZipCode(expectedLocationOrZipCode, false);
	}
	
	public void clickFindNearby(){ 
		browser.clickGuiObject(Property.concatPropertyArray(a(), ".href", new RegularExpression("javascript:RecSearchEngine\\.submit\\(\\)", false)));
	}

	public boolean checkSpanFieldExist(String text){
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", text);
	}

	public void verifySpanFieldExist(boolean expectedExisted, String text){
		boolean actualExisted = this.checkSpanFieldExist(text);
		if(actualExisted!=expectedExisted){
			throw new ErrorOnPageException("The Span (Text: "+text+") should "+(expectedExisted?"":"not ")+"be existed.");
		}
	}

	public void waitForSpan(String text){
		Property[] p = new Property[2];
		p[0] = new Property(".class", "Html.SPAN");
		p[1] = new Property(".text", text);
		browser.waitExists(p);
	}

	/**
	 * Get all the activities other than the selected one
	 * @return
	 */
	public String[] getActivities(){
		RegularExpression reg = new RegularExpression(".*marketing\\.do\\?goto=acm\\/Explore_And_More.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".href", reg);
		String[] activities = new String[objs.length];

		if(objs.length>0){
			for(int i=0; i<objs.length; i++){
				activities[i] = objs[i].text().trim();
			}
		}else throw new ObjectNotFoundException("Explore ways object can't be found.");

		Browser.unregister(objs);
		return activities;
	}
	
	public IHtmlObject[] getAutoCompleteBox(){
		Property[] p=new Property[]{new Property(".class","Html.DIV"),new Property(".className", "composite_autocomplete"),new Property(".id","locationCriteria_container")};
		return browser.getHtmlObject(p);
	}
	
	public boolean checkAutoCompleteBoxExist(){
		boolean flag=false;
		IHtmlObject[] autoCompleteBox = null;
		autoCompleteBox = this.getAutoCompleteBox();
		if(autoCompleteBox != null && autoCompleteBox.length>0 && !autoCompleteBox[0].style("display").trim().equals("none")){
			flag = true;
		}
		Browser.unregister(autoCompleteBox);
		return flag;
	}
	
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
	
	public void waitAutoCompleteBox(String endMark){
		waitAutoCompleteBox();
		
		endMark=endMark.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
		Property[] p=new Property[]{new Property(".class","Html.DIV"),new Property(".text",new RegularExpression(endMark+".*",false)),new Property(".className",new RegularExpression("(.*selectable.*)|message",false))};
		browser.waitExists(OrmsConstants.PAGE_LOADING_TRESHOLD,p);
	}
	
	public void triggerAutoCompleteBoxDisplay(String endMark){
		this.focusLocationOrZipCode();
		this.inputEndKey();
		this.waitAutoCompleteBox();//wait entire complete box exists
		this.waitAutoCompleteBox(endMark);//wait the specific 'endMark' exist in the complete box
	}
	
	public void selectAutoCompleteBoxValue(String value){
		logger.info("Start select the items from the auto complete dropdown list..");
		IHtmlObject[] objs = this.getAutoCompleteBox();
		logger.info("The items we are going to click is:" + value);
		browser.clickGuiObject(".class","Html.DIV",".text", new RegularExpression(value,false), true, 0, objs[0]);
		logger.info("Finish select items from auto complete dropdown list..");
		Browser.unregister(objs);
	}
}
