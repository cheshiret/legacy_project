package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch;

import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: Blocked by DEFECT-35748
 * 1. Open rec.gov website.
 * 2. Move the mouse cursor in 'Where' input text box, and check the instruction text. 
 * 3. Click 'Log-In' link and input user account/password to Log-In.
 * 4. Go back to Home page, and move the mouse cursor to 'Where' input text box, then check the instruction text. 
 * @Preconditions:
 * @SPEC:
 * 1. Rec.gov - 'Where' input - update instruction text (initial Home page) [TC:043315] 
 * 2. Rec.gov - 'Where' input - update instruction text (after clear search) [TC:043321] 
 * @Task#: AUTO-1179
 * 
 * @author SWang
 * @Date 8/14/2012
 */
public class WhereInputInstructionText extends RecgovTestCase {
	private  UwpUnifiedSearchPanel panel=UwpUnifiedSearchPanel.getInstance();
	private String hintMsg;

	public void execute() {
		//'Where' input - update instruction text (initial Home page) 
		web.invokeURL(url);
		panel.focusWhereTextField();
		this.verifyInstructionText(hintMsg);

		//'Where' input - update instruction text (user log-in)
		web.signIn(email, pw);
		web.gotoHomePage();
		panel.focusWhereTextField();
		this.verifyInstructionText(hintMsg);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		hintMsg = "Start typing... - US State name (or code) to search state-wide - National Park, Forest, or other landmark name to find it and search nearby - City, Zip, or Street to search the surrounding area We'll start suggesting options here as you type!";
	}

	/***
	 * Verify instruction text in Unified Search Form 'Where' field.
	 * @param expectedMessage
	 */
	public void verifyInstructionText(String expectedMessage) {
		UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();

		logger.info("Verify Where input instruction text.");
		searchPanel.waitHintMsgBoxExist(expectedMessage);
		String actualdMessage = searchPanel.getAutoCompleteBoxVaule().trim();
		if (!expectedMessage.equals(actualdMessage)){
			throw new ErrorOnPageException("Where input instruction text is wrong.", expectedMessage, actualdMessage);
		}
		logger.info("Successfully very 'Where input instruction text' - "+expectedMessage);

		searchPanel.removeFocus();
	}
}

