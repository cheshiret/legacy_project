package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.closure;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Closure;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosureDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosuresPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosuresSitesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: 
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * Search entry [TC:045427]
 * Closure site found [TC:045431]
 * Search Execution_ All entry [TC:045434]  
 * @Task#: Auto-1895
 * @author Jane
 * @Date  Sep 25, 2013
 */
public class VerifyUIForSearchClosureSitePage extends InventoryManagerTestCase {
	private String facilityID;
	private List<String> showStatus;
	private List<String> loops;
	private String searchByLoop, searchByCode, expectedId;
	private List<String> expectedIdList;
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoFacilityDetailPageById(facilityID);
		im.gotoClosurePage();
		
		createClosure(closure);
		gotoClosureSitePageFromClosureDetailsPage();
		verifyUIForSearchClosureSitePage();
		searchSiteByLoops(searchByLoop, expectedIdList);
		searchSiteByAllEntry(searchByCode, searchByLoop, expectedId);
		
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
	    login.contract = "NC Contract";
	    login.location="Administrator/North Carolina State Parks";
	    schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "NC";
	    
	    facilityID = "552834";
	    
	    closure.productCategory = "Site";
	    closure.type="Problem";
	    closure.startDate=DateFunctions.getToday();
	    closure.endDate=DateFunctions.getDateAfterToday(1);
	    closure.status=OrmsConstants.INACTIVE_STATUS;
	    closure.comment="Regression Test";
	    
	    showStatus = new ArrayList<String>();
	    showStatus.add("Assigned");
	    showStatus.add("NOT Assigned");
	    showStatus.add("Assigned or Not");
	    
	    loops = new ArrayList<String>();
	    loops.add("");
	    loops.addAll(im.getLoopsByParkID(schema, facilityID));
	    
	    searchByLoop = "Medoc Mountain Group Campground";
	    expectedIdList = new ArrayList<String>();
	    expectedIdList.addAll(im.getPrdIDsByParkIDAndLocName(schema, facilityID, searchByLoop));
	    
	    expectedId = "1410";
	    searchByCode = im.getSiteNumber(expectedId, schema);
	}
	
	private void createClosure(Closure closure) {
		InvMgrClosureDetailPage invClosureDetailPg = InvMgrClosureDetailPage.getInstance();
		InvMgrClosuresPage invClosurePg = InvMgrClosuresPage.getInstance();

		logger.info("Add a new closure.");

		invClosurePg.clickAddNew();
		invClosureDetailPg.waitLoading();

		invClosureDetailPg.fillClosureDetails(closure);
		invClosureDetailPg.clickApply();
		invClosureDetailPg.waitLoading();
	}
	
	private void gotoClosureSitePageFromClosureDetailsPage() {
		InvMgrClosureDetailPage invClosureDetailPg = InvMgrClosureDetailPage.getInstance();
		InvMgrClosuresSitesPage invClosureSitesPg = InvMgrClosuresSitesPage.getInstance();
		
		invClosureDetailPg.clickClosureSite();
		ajax.waitLoading();
		invClosureSitesPg.waitLoading();
	}
	
	private void verifyUIForSearchClosureSitePage() {
		InvMgrClosuresSitesPage invClosureSitesPg = InvMgrClosuresSitesPage.getInstance();
		
		String siteCode = invClosureSitesPg.getProductCode();
		if(StringUtil.notEmpty(siteCode))
			throw new ErrorOnPageException("Site code default value should be empty.");
		logger.info("---Verify site code default value as EMPTY successfully.");
		
		List<String> showStatusUI = invClosureSitesPg.getShowStatusValues();
		if(!showStatusUI.equals(showStatus))
			throw new ErrorOnPageException("Show status values displayed uncorrectly on page.", showStatus, showStatusUI);
		logger.info("---Verify show status values on page successfully.");
		
		List<String> loopsUI = invClosureSitesPg.getLoopOrDockArea();
		if(!loopsUI.equals(loops))
			throw new ErrorOnPageException("Loops values displayed uncorrectly on page.", loops, loopsUI);
		logger.info("---Verify loops values on page successfully.");
		
		if(!invClosureSitesPg.isViewChangeRequestItemsBtnExisted())
			throw new ErrorOnPageException("View Change Request Items button should exist on page.");
		logger.info("---Verify View Change Request Items button on page successfully.");
	}

	private void searchSiteByLoops(String loops, List<String> expectedIdList) {
		InvMgrClosuresSitesPage invClosureSitesPg = InvMgrClosuresSitesPage.getInstance();
		
		logger.info("Search site by loop "+loops);
		invClosureSitesPg.searchProductByLoopOrDock(loops);
		List<String> results = invClosureSitesPg.getAllProductIDs();
		if(results==null || !results.equals(expectedIdList))
			throw new ErrorOnPageException("Search site by loops failed. Expected id list should be "+expectedIdList);
		logger.info("---Verify search site by loops successfully.");
	}
	
	private void searchSiteByAllEntry(String siteCode, String loops, String expectedSiteId) {
		InvMgrClosuresSitesPage invClosureSitesPg = InvMgrClosuresSitesPage.getInstance();
		
		logger.info("Search site by all entry site code="+siteCode+", loops="+loops);
		invClosureSitesPg.searchSiteOrSlipByAllEntry(siteCode, "Assigned or Not", loops);
		List<String> results = invClosureSitesPg.getAllProductIDs();
		if(results==null || results.size()!=1 || !results.get(0).equals(expectedSiteId))
			throw new ErrorOnPageException("Search site by all entry failed. Expected site id should be "+expectedSiteId);
		logger.info("---Verify search site by all entry successfully.");
	}
}
