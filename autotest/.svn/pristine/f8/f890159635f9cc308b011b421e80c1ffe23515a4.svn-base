package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author jchen
 */
public class UwpTourParkDetailsPage extends UwpPage {

	private static UwpTourParkDetailsPage _instance = null;

	public static UwpTourParkDetailsPage getInstance() {
		if (null == _instance)
			_instance = new UwpTourParkDetailsPage();

		return _instance;
	}

	public UwpTourParkDetailsPage() {
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "tourParkDetail", ".text", "Tour Park Details") && 
		browser.checkHtmlObjectExists(".id", "contentcol", ".text", new RegularExpression("^Overview.*", false));
	}
	
	/**
	 * Click on book tour button.
	 */
	public void bookNow() {
		browser.clickGuiObject(".class", "Html.BUTTON", ".id", "btnbookacampsite");
	}
	
	/**
	 * Get tour park name and state info
	 * @return
	 */
	public String getTourParkNameAndRelatedStateCode(){
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
	 * Verify tour park name and state info
	 * @param tourParkName
	 * @param stateCode
	 */
	public void verifyTourParkNameAndRelatedStateCode(String tourParkName, String stateCode){
		String actualValue = this.getTourParkNameAndRelatedStateCode();
		String expectedRegx = tourParkName +", ?"+ stateCode;
		if(actualValue.matches(expectedRegx)){
			logger.info("Successfully verify tour name and state:"+actualValue);
		}else{
			throw new ErrorOnDataException("verify for tour name and state failed because "+actualValue+" doesn't match "+expectedRegx);
		}
	}
	
	public boolean checkFacilityPhotoExist(String description){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".id", "samplpics");
		Property[] p2 = Property.toPropertyArray(".class", "Html.IMG", ".title", description);
		Property[] p3 = Property.toPropertyArray(".class", "Html.IMG", ".alt", description);
		return browser.checkHtmlObjectExists(Property.atList(p1, p2)) && browser.checkHtmlObjectExists(Property.atList(p1, p3));
	}
	
	public void clickToutListTab(){
		browser.clickGuiObject(Property.toPropertyArray(".class", "Html.A", ".id", "tourList", ".text", "Tour List"));
	}
	
}
