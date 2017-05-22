/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.awo.pages.web.common.UwpHeaderCommonBar;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Residency Status Validation Fail page
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Apr 18, 2013
 */
public class HFResidStatusValidationFailPage extends UwpHeaderCommonBar {
	private static HFResidStatusValidationFailPage _instance = null;

	public static HFResidStatusValidationFailPage getInstance() {
		if (null == _instance)
			_instance = new HFResidStatusValidationFailPage();

		return _instance;
	}

	protected HFResidStatusValidationFailPage() {
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".classname", "msg error", ".id", "subtitleError");
	}
	
	public void clickUpdateAccountLink() {
		browser.clickGuiObject(".class", "Html.A", ".text", "update your account");
	}
	
	public void clickContactCallCenterLink() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Contact our call center", false));
	}
	
	public void clickGoBack() {
		browser.clickGuiObject(".class", "Html.A", ".text", "go back and try again");
	}
	
	public void clickProceed() {
		browser.clickGuiObject(".class", "Html.Button", ".text", "Proceed");
	}
	
	public String getPageTitle() {
		return browser.getObjectText(".id", "pagetitle");
	}

	public String getSubtitleErrorMsg() {
		return browser.getObjectText(".id", "subtitleError");
	}
	
	protected Property[] importantInfo() {
		return Property.toPropertyArray(".class", "Html.p", ".className", "info");
	}
	
	public boolean isImportantInfoExist() {
		return browser.checkHtmlObjectExists(importantInfo());
	}
	
	public String getImportantInfoMsg() {
		return browser.getObjectText(importantInfo());
	}
	
	public String getSubInfoMsg() {
		return browser.getObjectText(".class", "Html.ul", ".className", "defaultlist");
	}
	
	public void verifyErrorMsgs(String expSubTitle, String expImpInfo) {
		boolean result = true;
		String actSubTitle = this.getSubtitleErrorMsg();
		String actImpInfo = this.getImportantInfoMsg();
		boolean isImpInfoExist = this.isImportantInfoExist();
		if (StringUtil.isEmpty(actSubTitle) || (isImpInfoExist && StringUtil.isEmpty(actImpInfo))) {
			logger.info("The actual messages should not be empty.");
			result = false;
		} else {
			result &= MiscFunctions.matchString("page sub title msg", actSubTitle, expSubTitle);
			if (isImpInfoExist) {
				result &= MiscFunctions.matchString("page important info msg", actImpInfo, expImpInfo);
			}
		}
		if (!result) {
			throw new ErrorOnPageException("Error messgaes on page are wrong. Check logger error.");
		}
		logger.info("---Verify error messages correctly!");
	}
	
	public void verifyPgSubTitle(String expSubTitle){
		String actSubTitle = this.getSubtitleErrorMsg();
		if(!actSubTitle.equals(expSubTitle)){
			throw new ErrorOnPageException("Page Sub title is wrong!", expSubTitle, actSubTitle);
		}else logger.info("Successfully verify page sub title.");
		
	}
}
