package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.Property;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jul 11, 2013
 */
public class HFEducationInfomationAddedPage extends  HFHeaderBar {

	private static HFEducationInfomationAddedPage _instance = null;

	public static HFEducationInfomationAddedPage getInstance() {
		if (null == _instance)
			_instance = new HFEducationInfomationAddedPage();

		return _instance;
	}

	protected HFEducationInfomationAddedPage() {
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "eduForm") && browser.checkHtmlObjectExists(".id", "continue_button");
	}
	
	public void clickContinue() {
		browser.clickGuiObject(".id", "continue_button");
	}
	
	public String getEduNum(){
		return browser.getObjectText(Property.atList(Property.toPropertyArray(".id", "edunum"), Property.toPropertyArray(".className", "attrs")));
	}

	public String getCountry(){
		return browser.getObjectText(Property.atList(Property.toPropertyArray(".id", "countryGrp"), Property.toPropertyArray(".className", "attrs")));
	}
	
	public String getState(){
		return browser.getObjectText(Property.atList(Property.toPropertyArray(".id", "stateGrp"), Property.toPropertyArray(".className", "attrs")));
	}
	
	public void verifyEducationInfo(Education edu){
		boolean passed = MiscFunctions.compareResult("Eudcation number", edu.educationNum, getEduNum());
		passed &= MiscFunctions.compareResult("Country", edu.country, getCountry());
		passed &= MiscFunctions.compareResult("State", edu.state, getState());
		if(!passed){
			throw new ErrorOnPageException("Failed to verify all education check points.");
		}
		logger.info("Successfully verify all education check points.");
	}
	
	public boolean isMsgExist(String msg) {
		return browser.checkHtmlObjectExists(".classname", "ver_ok_heading");
	}
	
	public void verifyMsgExist(String msg, boolean isExist) {
		String info = isExist ? " " : " not ";
		if (this.isMsgExist(msg) != isExist) {
			throw new ErrorOnPageException("The message: " + msg + " should " + info + "exist!");
		}
		logger.info("The message: " + msg + " does " + info + "exist!");
	}

}

