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
 * Search entry [TC:045459] 
 * Closure Slip list page [TC:045460] 
 * Closure slip found [TC:045463] 
 * Search Execution_ All entry [TC:045466]   
 * @Task#: Auto-1896
 * @author Jane
 * @Date  Sep 25, 2013
 */
public class VerifyUIForSearchClosureSlipPage extends InventoryManagerTestCase {
	private String facilityID;
	private List<String> showStatus;
	private List<String> docks;
	private String searchByDock, searchByCode, expectedId;
	private List<String> expectedIdList;
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoFacilityDetailPageById(facilityID);
		im.gotoClosurePage();
		
		createClosure(closure);
		gotoClosureSlipPageFromClosureDetailsPage();
		verifyUIForSearchClosureSlipPage();
		searchSlipByDocks(searchByDock, expectedIdList);
		searchSlipByAllEntry(searchByCode, searchByDock, expectedId);
		
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
	    login.contract = "NC Contract";
	    login.location="Administrator/North Carolina State Parks";
	    schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "NC";
	    
	    facilityID = "552834";
	    
	    closure.productCategory = "Slip";
	    closure.type="Problem";
	    closure.startDate=DateFunctions.getDateAfterToday(5);
	    closure.endDate=DateFunctions.getDateAfterToday(8);
	    closure.status=OrmsConstants.INACTIVE_STATUS;
	    closure.comment="Regression Test";
	    
	    showStatus = new ArrayList<String>();
	    showStatus.add("Assigned");
	    showStatus.add("NOT Assigned");
	    showStatus.add("Assigned or Not");
	    
	    docks = new ArrayList<String>();
	    docks.add("");
	    docks.addAll(im.getDocksByParkID(schema, facilityID));
	    
	    searchByDock = "DockForNoShowSlip";
	    expectedIdList = new ArrayList<String>();
	    expectedIdList.addAll(im.getPrdIDsByParkIDAndLocName(schema, facilityID, searchByDock));
	    
	    searchByCode = "SNS01";
	    expectedId = im.getSlipProductID("Product Code", searchByCode, facilityID, schema);
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
	
	private void gotoClosureSlipPageFromClosureDetailsPage() {
		InvMgrClosureDetailPage invClosureDetailPg = InvMgrClosureDetailPage.getInstance();
		InvMgrClosuresSitesPage invClosureSitesPg = InvMgrClosuresSitesPage.getInstance();
		
		invClosureDetailPg.clickClosureSlip();
		ajax.waitLoading();
		invClosureSitesPg.waitLoading();
	}
	
	private void verifyUIForSearchClosureSlipPage() {
		InvMgrClosuresSitesPage invClosureSitesPg = InvMgrClosuresSitesPage.getInstance();
		
		String slipCode = invClosureSitesPg.getProductCode();
		if(StringUtil.notEmpty(slipCode))
			throw new ErrorOnPageException("Slip code default value should be empty.");
		logger.info("---Verify slip code default value as EMPTY successfully.");
		
		List<String> showStatusUI = invClosureSitesPg.getShowStatusValues();
		if(!showStatusUI.equals(showStatus))
			throw new ErrorOnPageException("Show status values displayed uncorrectly on page.", showStatus, showStatusUI);
		logger.info("---Verify show status values on page successfully.");
		
		List<String> docksUI = invClosureSitesPg.getLoopOrDockArea();
		if(!docksUI.equals(docks))
			throw new ErrorOnPageException("Loops values displayed uncorrectly on page.", docks, docksUI);
		logger.info("---Verify loops values on page successfully.");
		
		if(invClosureSitesPg.isViewChangeRequestItemsBtnExisted())
			throw new ErrorOnPageException("View Change Request Items button should NOT exist on page.");
		logger.info("---Verify View Change Request Items button NOT on page successfully.");
	}
	
	private void searchSlipByDocks(String loops, List<String> expectedIdList) {
		InvMgrClosuresSitesPage invClosureSitesPg = InvMgrClosuresSitesPage.getInstance();
		
		logger.info("Search site by loop "+loops);
		invClosureSitesPg.searchProductByLoopOrDock(loops);
		List<String> results = invClosureSitesPg.getAllProductIDs();
		if(results==null || !results.equals(expectedIdList))
			throw new ErrorOnPageException("Search site by loops failed. Expected id list should be "+expectedIdList);
		logger.info("---Verify search site by loops successfully.");
	}
	
	private void searchSlipByAllEntry(String slipCode, String docks, String expectedSlipId) {
		InvMgrClosuresSitesPage invClosureSitesPg = InvMgrClosuresSitesPage.getInstance();
		
		logger.info("Search site by all entry slip code="+slipCode+", docks="+docks);
		invClosureSitesPg.searchSiteOrSlipByAllEntry(slipCode, "Assigned or Not", docks);
		List<String> results = invClosureSitesPg.getAllProductIDs();
		if(results==null || results.size()!=1 || !results.get(0).equals(expectedSlipId))
			throw new ErrorOnPageException("Search slip by all entry failed. Expected slip id should be "+expectedSlipId);
		logger.info("---Verify search slip by all entry successfully.");
	}
}
