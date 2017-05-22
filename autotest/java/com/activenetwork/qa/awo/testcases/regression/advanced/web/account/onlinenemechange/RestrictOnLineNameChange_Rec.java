package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.onlinenemechange;

import com.activenetwork.qa.awo.pages.web.uwp.UwpCreateAccountPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUpdateProfilePage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.page.Page;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:
 * @Preconditions:
 * UI-option 'restrict-name-change'=false
 * @SPEC:Rec.gov, Restrict on-line customer name changes on Sign-Up page [TC:037261]
 * @Task#:Auto-1074
 * 
 * @author asun
 * @Date  May 30, 2012
 */

public class RestrictOnLineNameChange_Rec extends RecgovTestCase {
	String noteMessage;
	UwpCreateAccountPage accountPage=UwpCreateAccountPage.getInstance();
	UwpUpdateProfilePage profilePage=UwpUpdateProfilePage.getInstance();
	String nameInfo;
	
	@Override
	public void execute() {
		web.invokeURL(url);
		web.gotoSignUppage();
		this.verifyNoteMessage(noteMessage,accountPage);
		web.signIn(email, pw);
		web.gotoUpdateProfilePage();
		this.verifyNoteMessage(noteMessage, profilePage);
		this.verifyNameInfo();
	}

	
	private void verifyNameInfo() {
		logger.info("Verify Name Info.....");
		String nameOnPage=profilePage.getNameInfo();
		if(!nameInfo.equals(nameOnPage)){
			throw new ErrorOnPageException("",nameInfo,nameOnPage);
		}
	}


	private void verifyNoteMessage(String noteMessage2, Page page) {
		logger.info("Verify  Note message from "+page.getName());
	    String noteOnPage=null;
	    if(page==accountPage){
	    	noteOnPage=accountPage.getNameRestrictNote();
	    }else if(page==profilePage){
	    	noteOnPage=profilePage.getNameRestrictNote();
	    }else{
	    	throw new ErrorOnDataException("Unknown page:"+page.getName());
	    }
	    
	    if(!this.noteMessage.equals(noteOnPage)){
	    	throw new ErrorOnPageException("Message for name Restrict is wrong.",noteMessage,noteOnPage);
	    }
		
	}
	@Override
	public void wrapParameters(Object[] param) {
        email=web.getNextEmail();
        url = TestProperty.getProperty(env + ".web.recgov.url");
    	pw = TestProperty.getProperty("web.login.pw");	
    	noteMessage="Note: Names cannot be changed online once your Account has been created. Name changes must be done through our Call Center.";
    	nameInfo = TestProperty.getProperty("app.cust.fname")+" "+TestProperty.getProperty("app.cust.lname");
	}

}
