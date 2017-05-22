package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.closure;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Closure;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosureDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosuresPage;
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
 * Closure schedule found [TC:045414] 
 * Search Execution_ All entry [TC:045417]
 * @Task#: Auto-1894
 * @author Jane
 * @Date  Sep 22, 2013
 */
public class SearchClosure extends InventoryManagerTestCase {

	private String facilityID;
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoFacilityDetailPageById(facilityID);
		im.gotoClosurePage();
		
		String id = this.createClosure(closure);
		searchClosureByAllEntryExceptID(closure, id);
		
		closure.closureID = id;
		searchClosureByID(id);
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
	    closure.startDate=DateFunctions.getDateAfterToday(10);
	    closure.endDate=DateFunctions.getDateAfterToday(70);
	    closure.status=OrmsConstants.ACTIVE_STATUS;
	    closure.occurencePattern = "Monthly";
	    closure.dayOfMonth = "1";
	    closure.comment="Regression Test";
	    closure.recurring = StringUtil.EMPTY;
	    closure.affectedOrdInstr = StringUtil.EMPTY;
	    closure.createdAppID = "Inventory Manager";
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
	
	private void searchClosureByAllEntryExceptID(Closure closure, String id) {
		InvMgrClosuresPage invClosurePg = InvMgrClosuresPage.getInstance();
		
		List<String> idsUI = invClosurePg.searchByClosureInfo(closure);
		if(idsUI == null || !idsUI.contains(id))
			throw new ErrorOnPageException("Clousre id "+id+" should in search result list.");
		logger.info("---Verify search closure by all entry except id successfully.");
	}
	
	private void searchClosureByID(String id) {
		InvMgrClosuresPage invClosurePg = InvMgrClosuresPage.getInstance();
		
		//change search criteria
		invClosurePg.selectProductCategory("Site");
		invClosurePg.selectClosureType("General");
		invClosurePg.selectApplication("Field Manager");
		List<String> idsUI = invClosurePg.searchByClosureID(id);
		if(idsUI == null || !idsUI.contains(id))
			throw new ErrorOnPageException("Clousre id "+id+" should in search result list.");
		logger.info("---Verify search closure by id successfully.");
	}
}
