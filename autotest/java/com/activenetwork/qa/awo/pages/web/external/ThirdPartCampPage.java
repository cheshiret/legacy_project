package com.activenetwork.qa.awo.pages.web.external;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class ThirdPartCampPage extends UwpPage {
	private static ThirdPartCampPage _instance = null;

	public static ThirdPartCampPage getInstance() {
		if (null == _instance)
			_instance = new ThirdPartCampPage();

		return _instance;
	}

	protected ThirdPartCampPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "propertyImageWrapper");
	}
	
	public String getParkName(){
		return browser.getObjectText(".class", "Html.DIV", ".id", "propertyImageWrapper");
	}
	
	
	
	/** verify the park name match with the park name displayed on RA website.
	 *  there is a possible chance that the park displayed on RA website may different a little bit with the name displayed
	 *  on the third part website.
	 *  for example, the park displayed as "" on RA, but displayed as "" on third part website.
	 *  */
	public void verifyParkName(String parkName){
		String currentParkName = this.getParkName();
		if(currentParkName.equalsIgnoreCase(parkName)){
			logger.info("verify park display on third part website match with RA website successfully.");
		}else{
			logger.error("expect  Park name is:" + parkName);
			logger.error("current Park name is:" + currentParkName);
			throw new ErrorOnPageException("Park name displayed on third part did not match with the name displayed on RA website.");
	  	}
	}
}
