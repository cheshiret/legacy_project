package com.activenetwork.qa.awo.pages.web.recgov;

import com.activenetwork.qa.awo.pages.web.uwp.UwpPermitingPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Sep 26, 2012
 */
public class RecgovPermitAreaDetailsPage extends UwpPermitingPage {

	private static RecgovPermitAreaDetailsPage _instance = null;

	public static RecgovPermitAreaDetailsPage getInstance() {
		if (null == _instance)
			_instance = new RecgovPermitAreaDetailsPage();

		return _instance;
	}

	protected RecgovPermitAreaDetailsPage() {}

	/** Elements Properties Define Begin */
	protected Property[] getFacilityOverviewDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "content first");
	}
	
	/** Elements Properties Define End */
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "wildernessAreaFacilityDetails", ".text", "Permit Area Details") && 
		browser.checkHtmlObjectExists(".id", "contentcol", ".text", new RegularExpression("^Overview.*", false));
	}
	
	/**
	 * Get permit park name and state info 
	 * e.g: CABLES ON HALF DOME, CA
	 * @return
	 */
	public String getPermitParkNameAndRelatedStateCode(){
		Property[] p = Property.toPropertyArray(".id", "campname", ".className", "facility_view_header");
		Property[] p1 = Property.toPropertyArray(".class", "Html.SPAN");
		IHtmlObject[] objs=browser.getHtmlObject(Property.atList(p, p1));
		if(null==objs || objs.length<1){
			throw new ObjectNotFoundException("Can't find any Span object under 'campname' object.");
		}
		
	  	String campName = objs[0].text().trim();
	  	Browser.unregister(objs);
	  	return campName;
	}
	
	/**
	 * Get permit park name and state
	 * @param permitParkName
	 * @param stateCode
	 */
	public void verifyPermitParkNameAndRelatedStateCode(String permitParkName, String stateCode){
		String actualValue = this.getPermitParkNameAndRelatedStateCode();
		String expectedVlue = permitParkName +", ?"+ stateCode;
		if(actualValue.matches(MiscFunctions.regxBracket(expectedVlue))){
			logger.info("Successfully verify permit name and state.");
		}else{
			throw new ErrorOnDataException("verify for permit name and state failed because "+actualValue+" doesn't match "+expectedVlue);
		}
	}
	
	public boolean checkFacilityPhotoExist(String description){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".id", "headingpic"); //"samplpics");
		Property[] p2 = Property.toPropertyArray(".class", "Html.IMG", ".title", description);
		Property[] p3 = Property.toPropertyArray(".class", "Html.IMG", ".alt", description);
		return browser.checkHtmlObjectExists(Property.atList(p1, p2)) && browser.checkHtmlObjectExists(Property.atList(p1, p3));
	}
	
	public String getFacilityOverview() {
		return browser.getObjectText(getFacilityOverviewDivProp());
	}
	
	public void verifyFacilityOverview(String exp) {
		String actual = this.getFacilityOverview();
		if (!exp.startsWith("Overview")) {
			exp = "Overview " + exp;
		}
		if (!actual.replaceAll("\\s", StringUtil.EMPTY).equals(exp.replaceAll("\\s", StringUtil.EMPTY))) {
			throw new ErrorOnPageException("facility overview info is wrong!", exp, actual);
		}
		logger.info("---Successfully verify facility overview info!");
	}
}
