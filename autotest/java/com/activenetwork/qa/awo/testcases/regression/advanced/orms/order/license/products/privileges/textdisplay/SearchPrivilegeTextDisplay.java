package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.textdisplay;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeTextDisplay;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeTextDisplayPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: This test case used to verify remove initial filter and search text display by different criteria
 * @Preconditions: need to prepare a privilege at first
 * @SPEC:<View Privilege Text Display>
 * @Task#:Auto-615
 * 
 * @author ssong
 * @Date  Jun 20, 2011
 */

public class SearchPrivilegeTextDisplay extends LicenseManagerTestCase{

	private PrivilegeTextDisplay textDisplayInfo = new PrivilegeTextDisplay();
	private LicMgrPrivilegeTextDisplayPage textDisplayPg = LicMgrPrivilegeTextDisplayPage
	.getInstance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoProductTextDisplayPgFromTopMenu(privilege.code);
		//clean up environment as precondition
		lm.deactivatePrivilegeTextDisplayByText(textDisplayInfo.text);
		
		// add  a new privilege text display
		textDisplayInfo.id = lm.addPrivilegeTextDisplay(textDisplayInfo);
		
		//remove initial filter to show all
		textDisplayPg.showAllTextDisplays();
		checkGivenTextDisplayed();

		//search text display by both status and display type
		textDisplayPg.searchTextDisplay(textDisplayInfo.status,textDisplayInfo.displayType);
		checkGivenTextDisplayed();
		textDisplayPg.verifySearchResultMatchCriteria("Status", textDisplayInfo.status);
		textDisplayPg.verifySearchResultMatchCriteria("Display Type", textDisplayInfo.displayType);
		
		//search by display type
		textDisplayInfo.status = "";
		textDisplayPg.searchTextDisplay(textDisplayInfo.status,textDisplayInfo.displayType);
		checkGivenTextDisplayed();
		textDisplayPg.verifySearchResultMatchCriteria("Display Type", textDisplayInfo.displayType);
		
		//search by active status
		textDisplayInfo.displayType = "";
		textDisplayInfo.status = "Active";
		textDisplayPg.searchTextDisplay(textDisplayInfo.status,textDisplayInfo.displayType);
		checkGivenTextDisplayed();
		textDisplayPg.verifySearchResultMatchCriteria("Status", textDisplayInfo.status);
		
		//search by inactive status
		lm.deactivatePrivilegeTextDisplay(textDisplayInfo.id);
		textDisplayInfo.status = "Inactive";
		textDisplayPg.searchTextDisplay(textDisplayInfo.status,textDisplayInfo.displayType);
		checkGivenTextDisplayed();
		textDisplayPg.verifySearchResultMatchCriteria("Status", textDisplayInfo.status);
		
		lm.logOutLicenseManager();
	}

	private void checkGivenTextDisplayed(){
		if(!textDisplayPg.checkTextDisplayRecordExists("id", textDisplayInfo.id)){
			throw new ErrorOnPageException("Given Text Id "+textDisplayInfo.id+" should displayed.");
		}
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		privilege.code = "QQQ";

		textDisplayInfo.displayType = "Alert warning text displayed after product selected";
		textDisplayInfo.text = "Search privilege text display";
		textDisplayInfo.effectiveFromDate = DateFunctions.getDateAfterToday(10);
		textDisplayInfo.effectiveToDate = DateFunctions.getDateAfterToday(15);
	}

}
