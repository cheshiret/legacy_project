package com.activenetwork.qa.awo.pages.web.recgov;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.page.IPopupPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Swang
 * @Date  Apr 11, 2013
 */
public class PhotoManagerCrossOverToRecPage extends UwpPage implements IPopupPage {
	private String attributeName;
	private Object value;
	private static PhotoManagerCrossOverToRecPage _instance = null;

	public static PhotoManagerCrossOverToRecPage getInstance() {
		if (null == _instance)
			_instance = new PhotoManagerCrossOverToRecPage();

		return _instance;
	}

	protected PhotoManagerCrossOverToRecPage() {
		browser = null;
		attributeName = "url";
		value = new RegularExpression(".*(campgroundDetails|campsiteDetails|entranceDetails|wildernessAreaDetails).*",false);
	}

	public boolean exists() {
		browser=Browser.getInstance().getBrowser(attributeName, value);
		return browser!=null;
	}

	/**wait for page to load*/
	public void waitForFacilityDetailsPageLoad(){
		RegularExpression idpattern=new RegularExpression("(btnbookacampsite|btn_book_now(_[a-z]+)?_id|permitTypeId)",false);//Sara[20140710] permit park has itinerary permit type
		browser.searchObjectWaitExists(".id", idpattern,".text",new RegularExpression("( ?(Book|Apply) Now|Select Permit Type)",false));
	}
	
	/**wait for page to load*/
	public void waitForSiteDetailsPageLoad(){
		browser.searchObjectWaitExists(".class", "Html.FORM", ".id", "booksiteform");
	}
	
	public void waitForEntranceDetailsPageLoad(){
		browser.searchObjectWaitExists(".class", "Html.FORM", ".id", "searchPermitForm");
	}
	
	/**Close recgov browser.*/
	public void closeBrowser(){
		browser.close();
	}
	
	public String getFacilityName(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".id", "cgroundName");
		if(null==objs || objs.length<1){
			throw new ObjectNotFoundException("camp ground name objects can't be found.");
		}
		String value = objs[0].text().split(",")[0].trim();
		Browser.unregister(objs);
		return value;
	}
	
	public void verifyFacilityName(String expectedName){
		String actualName = this.getFacilityName();
		if(!expectedName.equals(actualName)){
				throw new ErrorOnDataException("Facility name is wrong!", expectedName, actualName);
				}
		logger.info("Successfully verify facility name:"+expectedName);
	}
	
	public String getSiteName(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "sitenamearea");
		if(null==objs || objs.length<1){
			throw new ObjectNotFoundException("site name area objects can't be found.");
		}
		String value = objs[0].text().split(":")[1].split(",")[0].trim();
		Browser.unregister(objs);
		return value;	
	}
	
	public void VerifySiteNum(String expectedName){
		String actualName = this.getSiteName();
		if(!expectedName.equals(actualName)){
			throw new ErrorOnDataException("Site name is wrong!", expectedName, actualName);
		}
		logger.info("Successfully verify site name:"+expectedName);
	}
	
	public String getEntranceName(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "productname");
		if(null==objs || objs.length<1){
			throw new ObjectNotFoundException("Entrance name objects can't be found.");
		}
		String value = objs[0].text().trim();
		Browser.unregister(objs);
		return value;	
	}
	
	public void VerifyEntranceName(String expectedName){
		String actualName = this.getEntranceName();
		if(!actualName.contains(actualName)){
			throw new ErrorOnDataException("Entrance name is wrong!", expectedName, actualName);
		}
		logger.info("Successfully verify Entrance name:"+expectedName);
	}
}
