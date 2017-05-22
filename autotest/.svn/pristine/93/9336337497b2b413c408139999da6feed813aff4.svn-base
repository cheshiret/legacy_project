package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.textdisplay;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeTextDisplay;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeTextDisplayPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: This test case used to verify the initial filter and text display sorting correct
 * @Preconditions: need to prepare a privilege at first
 * @SPEC:<View Privilege Text Display>
 * @Task#:Auto-615
 * 
 * @author ssong
 * @Date  Jun 17, 2011
 */
public class ViewPrivilegeTextDisplay extends LicenseManagerTestCase{

	private PrivilegeTextDisplay textDisplayInfo1 = new PrivilegeTextDisplay();
	private PrivilegeTextDisplay textDisplayInfo2 = new PrivilegeTextDisplay();
	private PrivilegeTextDisplay textDisplayInfo3 = new PrivilegeTextDisplay();
	private PrivilegeTextDisplay textDisplayInfo4 = new PrivilegeTextDisplay();
	private LicMgrPrivilegeTextDisplayPage textDisplayPg = LicMgrPrivilegeTextDisplayPage
	.getInstance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		lm.gotoProductTextDisplayPgFromTopMenu(privilege.code);
		//clean up environment
		textDisplayPg.showAllActiveDisplays();
		lm.deactivatePrivilegeTextDisplayByText(textDisplayInfo1.text, textDisplayInfo2.text, textDisplayInfo3.text, textDisplayInfo4.text);
		
		// add  four text display and they are prepared to sorted by display type first and then by effective from date
		textDisplayInfo1.id = lm.addPrivilegeTextDisplay(textDisplayInfo1);
		textDisplayInfo2.id = lm.addPrivilegeTextDisplay(textDisplayInfo2);
		textDisplayInfo3.id = lm.addPrivilegeTextDisplay(textDisplayInfo3);
		textDisplayInfo4.id = lm.addPrivilegeTextDisplay(textDisplayInfo4);
		this.setDefaultFilter();
		this.verifyTextDisplayByDefaultFilter(textDisplayInfo1.id, true);
		this.verifyTextDisplayByDefaultFilter(textDisplayInfo2.id, true);
		this.verifyTextDisplayByDefaultFilter(textDisplayInfo3.id, true);
		this.verifyTextDisplayByDefaultFilter(textDisplayInfo4.id, false);  //should not display by default
		
		//show all text displays and get the fourth text id
		textDisplayPg.showAllTextDisplays();
		textDisplayInfo4.id = textDisplayPg.getTextDisplayIdByText(textDisplayInfo4.text);
		
		List<String> ids = new ArrayList<String>();
		ids.add(textDisplayInfo1.id);
		ids.add(textDisplayInfo3.id);
		ids.add(textDisplayInfo2.id);
		ids.add(textDisplayInfo4.id);
		if(!textDisplayPg.verifyTextDisplaySortedCorrect(ids)){
			throw new ErrorOnPageException("Given Text display Ids are Not Sorted Ascending by default.");
		}
		
		lm.logOutLicenseManager();
	}

	/**
	 * set Default Filter by clicking "Text Display" tab;
	 */
	private void setDefaultFilter() {
		logger.info(" set Default Filter by clicking \"Text Display\" tab");
		textDisplayPg.clickTextDisplayTab();
		ajax.waitLoading();	
		textDisplayPg.waitLoading();
	}

	private void verifyTextDisplayByDefaultFilter(String textId, boolean display){
		logger.info("Verify Text Display Correct By Default Filter For "+textId);
		String msg = display ? "" : "NOT";
		if(textDisplayPg.checkTextDisplayRecordExists("id", textId) != display) {
			throw new ErrorOnPageException("Text '"+textId+"' should " + msg + "displayed by default!");
		}
//		if(display){
//			if(!textDisplayPg.checkTextDisplayRecordExists("id", textId)){
//				throw new ErrorOnPageException("Text '"+textId+"' should displayed by default!");
//			}
//		}else{
//			if(textId.length()>0){
//				throw new ErrorOnPageException("Text '"+textId+"' should not displayed by default!");
//			}
//		}
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		privilege.code = "QQQ";

		textDisplayInfo1.displayType = "Alert warning text displayed after product selected";
		textDisplayInfo1.text = "View privilege text display-1";
		textDisplayInfo1.effectiveFromDate = DateFunctions.getToday();
		textDisplayInfo1.effectiveToDate = "";
		textDisplayInfo2.displayType = "Text displayed in pop-up when product clicked";
		textDisplayInfo2.text = "View privilege text display-2";
		textDisplayInfo2.effectiveFromDate = DateFunctions.getDateAfterToday(1);
		textDisplayInfo2.effectiveToDate = DateFunctions.getDateAfterToday(2);
		textDisplayInfo3.displayType = "Text displayed in pop-up when product clicked";
		
		textDisplayInfo3.text = "View privilege text display-3";
		textDisplayInfo3.effectiveFromDate = DateFunctions.getDateAfterToday(-2);
		textDisplayInfo3.effectiveToDate = DateFunctions.getToday();
		textDisplayInfo4.displayType = "Text displayed under product prior to selection";
		
		textDisplayInfo4.text = "View privilege text display-4";
		textDisplayInfo4.effectiveFromDate = DateFunctions.getDateAfterToday(-3);
		textDisplayInfo4.effectiveToDate = DateFunctions.getDateAfterToday(-1);
	}
}
