package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.onlinenemechange;

import com.activenetwork.qa.awo.pages.web.uwp.UwpAccountPanel;
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
 * @SPEC:RA, No restriction for on-line customer name changes on Sign-Up page [TC:043601]
 * @Task#:Auto-1074
 * 
 * @author asun
 * @Date  May 30, 2012
 */

public class NoRestrictOnLineNameChange_RA extends RecgovTestCase {
	String noteMessage;
	UwpCreateAccountPage accountPage=UwpCreateAccountPage.getInstance();
	UwpUpdateProfilePage profilePage=UwpUpdateProfilePage.getInstance();
	String nameInfo;
	
	@Override
	public void execute() {
		web.invokeURL(url);
		web.gotoSignUppage();
		this.verifyNoNoteMessage(noteMessage,accountPage);
		web.signIn(email, pw);
		web.gotoUpdateProfilePage();
		this.verifyNoNoteMessage(noteMessage, profilePage);
		this.verifyNameChangeable();
	}

	/**
	 * Verify Name Info is changeable
	 */
	private void verifyNameChangeable() {
		UwpAccountPanel accountPanelPg = UwpAccountPanel.getInstance();
		logger.info("Verify Name Info is changeable.....");
		String fNameOnPage=profilePage.getFName();
		String lNameOnPage=profilePage.getLName();
		String initialOnPage=profilePage.getInitial();
	    
	    //Update name Info
		String tempFName=fNameOnPage+"1";
		String tempLName=lNameOnPage+"1";
		String tempInitial=initialOnPage+"1";
		profilePage.setNameInfo("", tempFName, tempInitial, tempLName);
		profilePage.clickSaveChanges();
		accountPanelPg.waitLoading();
		
		//Goto profile to do verification
		web.gotoUpdateProfilePage();
		String updatedFNametemFName=profilePage.getFName();
		String updatedLNameOnPage=profilePage.getLName();
		String updatedInitialOnPage=profilePage.getInitial();
	    
		if(!updatedFNametemFName.equals(tempFName)){
			throw new ErrorOnPageException("FName should be editable","FName should be:"+tempFName,updatedFNametemFName);
		}
		
		if(!updatedLNameOnPage.equals(tempLName)){
			throw new ErrorOnPageException("LName should be editable","LName should be:"+tempLName,updatedLNameOnPage);
		}
		
		if(!updatedInitialOnPage.equals(tempInitial)){
			throw new ErrorOnPageException("Initial should be editable","LName should be:"+tempInitial,updatedInitialOnPage);
		}
		
		
		//Roll back the change
		profilePage.setNameInfo("", fNameOnPage, initialOnPage, lNameOnPage);
		profilePage.clickSaveChanges();
		accountPanelPg.waitLoading();
	}


	private void verifyNoNoteMessage(String noteMessage2, Page page) {
		logger.info("Verify No Note message from "+page.getName());
	    boolean noteOnPageExisting=false;
	    if(page==accountPage){
	    	noteOnPageExisting=accountPage.checkNameRestrictNoteExist();
	    }else if(page==profilePage){
	    	noteOnPageExisting=profilePage.checkNameRestrictNoteExist();
	    }else{
	    	throw new ErrorOnDataException("Unknown page:"+page.getName());
	    }
	    
	    if(noteOnPageExisting){
	    	throw new ErrorOnPageException("There should not be 'Restrict-Name-Changes' nessage in RA.");
	    }
		
	}

	@Override
	public void wrapParameters(Object[] param) {
        email=web.getNextEmail();
        url = TestProperty.getProperty(env + ".web.ra.url");
    	pw = TestProperty.getProperty("web.login.pw");	
	}

}
