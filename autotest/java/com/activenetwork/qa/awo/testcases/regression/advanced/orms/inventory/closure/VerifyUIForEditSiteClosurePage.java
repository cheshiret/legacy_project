package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.closure;

import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Closure;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosureDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosuresPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosuresSitesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * Edit Site Closure Schedule page [TC:045399]
 * Check Product Category_Site Closure Schedule [TC:048065]
 * Check editable entries_Site Closure Schedule [TC:048066]
 * @Task#: Auto-1893
 * @author Jane
 * @Date  Sep 18, 2013
 */
public class VerifyUIForEditSiteClosurePage extends InventoryManagerTestCase {

	private String facilityID;
	private TimeZone tz;
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoFacilityDetailPageById(facilityID);
		im.gotoClosurePage();
		
		closure.closureID = this.createClosure(closure);
		im.gotoClosureDetaiPg(closure.closureID, false);
		verifyEditClosurePageUI(false);
		assignSitesToClosure(closure.siteIds);
		gotoClosureDetailsPgFromClosureSitePg();
		verifyEditClosurePageUI(true);
		verifyChangeRequestModeLink();		
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
	    login.contract = "NC Contract";
	    login.location="Administrator/North Carolina State Parks";
	    schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "NC";
	    tz = DataBaseFunctions.getContractTimeZone(schema);
	    
	    facilityID = "552834";
	    
	    closure.productCategory = "Site";
	    closure.type="Maintenance";
	    closure.startDate=DateFunctions.getDateAfterToday(5, tz);
	    closure.endDate=DateFunctions.getDateAfterToday(90, tz);
	    closure.occurencePattern = "Monthly";
	    closure.dayOfMonth = "2";
	    closure.status=OrmsConstants.INACTIVE_STATUS;
	    closure.comment="Regression Test";
	    
	    closure.siteIds = new String[]{"1403"};//Shelter
	}
	
	private String createClosure(Closure closure) {
		InvMgrClosureDetailPage invClosureDetailPg = InvMgrClosureDetailPage.getInstance();
		InvMgrClosuresPage invClosurePg = InvMgrClosuresPage.getInstance();
		String closureID = "";

		logger.info("Add a new closure.");

		invClosurePg.clickAddNew();
		invClosureDetailPg.waitLoading();

		invClosureDetailPg.fillClosureDetails(closure);
		invClosureDetailPg.clickApply();
		invClosureDetailPg.waitLoading();
		
		closureID = invClosureDetailPg.getClosureID();
		invClosureDetailPg.clickOK();
		invClosurePg.waitLoading();
		
		return closureID;
	}
	
	private void assignSitesToClosure(String[] siteIds) {
		InvMgrClosureDetailPage invClosureDetailPg = InvMgrClosureDetailPage.getInstance();
		InvMgrClosuresSitesPage invClosureSitesPg = InvMgrClosuresSitesPage.getInstance();
		
		invClosureDetailPg.clickClosureSite();
		invClosureSitesPg.waitLoading();
		invClosureSitesPg.assignSites(siteIds);
	}
	
	private void gotoClosureDetailsPgFromClosureSitePg() {
		InvMgrClosureDetailPage invClosureDetailPg = InvMgrClosureDetailPage.getInstance();
		InvMgrClosuresSitesPage invClosureSitesPg = InvMgrClosuresSitesPage.getInstance();
		
		invClosureSitesPg.clickClosuresDetailsTab();
		invClosureDetailPg.waitLoading();
	}
	
	private void verifyEditClosurePageUI(boolean assignedSlips) {
		InvMgrClosureDetailPage closureDetailsPg = InvMgrClosureDetailPage.getInstance();
		
		if(closureDetailsPg.isClosureTypeDropdownListExisted())
			throw new ErrorOnPageException("Closure type should NOT be editable.");
		if(!assignedSlips && !closureDetailsPg.isProdCategoryDropdownListEditable()) {
			throw new ErrorOnPageException("Since no slip assigned, product category should BE editable.");
		} else if(assignedSlips && closureDetailsPg.isProdCategoryDropdownListEditable()) {
			throw new ErrorOnPageException("Since slips were assigned, product category should NOT be editable.");
		}
		if(!closureDetailsPg.isStartDateTextFieldEditable())
			throw new ErrorOnPageException("Start Date should BE editable.");
		if(!closureDetailsPg.isEndDateTextFieldEditable())
			throw new ErrorOnPageException("End Date should BE editable.");
		if(!closureDetailsPg.isActiveCheckboxEditable())
			throw new ErrorOnPageException("Active check box should BE editable.");
		if(closureDetailsPg.isAffResEditable())
			throw new ErrorOnPageException("Affected Reservations should NOT be editable.");
		if(closureDetailsPg.isApplicationEditable())
			throw new ErrorOnPageException("Application should NOT be editable.");
		if(closureDetailsPg.isOccPatternsDropdownListEditable())
			throw new ErrorOnPageException("Occurrence Pattern should NOT be editable.");
		if(!closureDetailsPg.isDayOfMonthEditable())
			throw new ErrorOnPageException("Day of Month should BE editable.");
		if(!closureDetailsPg.isCommentsTextAreaEditable())
			throw new ErrorOnPageException("Comments text area should BE editable.");
		if(!closureDetailsPg.isNotificationDateEditable())
			throw new ErrorOnPageException("Notification Date should BE editable.");
		if(closureDetailsPg.isCreateDateEditable())
			throw new ErrorOnPageException("Create Date should NOT be editable.");
		logger.info("---Verify edit closure details page UI successfully.");
	}
	
	private void verifyChangeRequestModeLink() {
		InvMgrClosureDetailPage closureDetailsPg = InvMgrClosureDetailPage.getInstance();
		
		boolean existed = closureDetailsPg.isChangeRequestModeLinkExisted();
		
		if(!existed)
			throw new ErrorOnPageException("Change Request Mode link should exist on closure detail page.");
		logger.info("---Verify change request mode link on page successfully.");
	}
}

