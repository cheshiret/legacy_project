package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.closure;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosureDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosuresPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * Create Closure Page [TC:044823]
 * Check Occurrence Patterns [TC:048057]
 * Check Product Category [TC:048058]
 * @Task#: Auto-1893
 * @author Jane
 * @Date  Sep 16, 2013
 */
public class VerifyUIForNewSlipClosurePage extends InventoryManagerTestCase {

	private String facilityID;
	private List<String> closureTypes = new ArrayList<String>();
	private List<String> occPatterns = new ArrayList<String>();
	private List<String> prdCategories = new ArrayList<String>();
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoFacilityDetailPageById(facilityID);
		im.gotoClosurePage();
		gotoClosureDetailsPgByAddNew();
		verifyNewClosurePageUI();
		verifyOccPatterns();
		verifyChangeRequestModeLink();
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
	    login.contract = "NC Contract";
	    login.location="Administrator/North Carolina State Parks";
	    schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "NC";
	    
	    facilityID = "552834";
	    
	    closureTypes.add("Maintenance");
	    closureTypes.add("Emergency");
	    closureTypes.add("Problem");
	    closureTypes.add("Seasonal");
	    closureTypes.add("Special Event");
	    closureTypes.add("General");
	    closureTypes.add("Cabin Cleaning");
	    closureTypes.add("Block Advance Reservation");
	    
	    occPatterns.add("All");
	    occPatterns.add("Monthly");
	    occPatterns.add("BiWeekly");
	    occPatterns.add("Weekly");
	    
	    prdCategories.add("Site");
	    prdCategories.add("Slip");
	}
	
	private void gotoClosureDetailsPgByAddNew() {
		InvMgrClosuresPage invClosurePg = InvMgrClosuresPage.getInstance();
		InvMgrClosureDetailPage closureDetailsPg = InvMgrClosureDetailPage.getInstance();
		
		invClosurePg.clickAddNew();
		closureDetailsPg.waitLoading();
	}
	
	private void verifyNewClosurePageUI() {
		InvMgrClosureDetailPage closureDetailsPg = InvMgrClosureDetailPage.getInstance();
		
		List<String> closureTypesUI = closureDetailsPg.getClosureTypeValues();
		if(!closureTypesUI.equals(closureTypes))
			throw new ErrorOnPageException("Closure types values displayed uncorrectly on page.", closureTypes, closureTypesUI);
		logger.info("---Verify closure types values on page successfully.");
		
		List<String> occPatternsUI = closureDetailsPg.getOccPatternsValues();
		if(!occPatternsUI.equals(occPatterns))
			throw new ErrorOnPageException("Occurrence Pattern values displayed uncorrectly on page.", occPatterns, occPatternsUI);
		logger.info("---Verify Occurrence Pattern values on page successfully.");
		
		List<String> prdCategoriesUI = closureDetailsPg.getPrdCategoryValues();
		prdCategoriesUI.remove(0);
		if(!prdCategoriesUI.equals(prdCategories))
			throw new ErrorOnPageException("Product Category values displayed uncorrectly on page.", prdCategories, prdCategoriesUI);
		logger.info("---Verify Product Category values on page successfully.");
		
		if(closureDetailsPg.isActive())
			throw new ErrorOnPageException("Active checkbox should be un-selected as default.");
		logger.info("---Verify active checkbox was un-selected as default.");
	}
	
	private void verifyOccPatterns() {
		InvMgrClosureDetailPage closureDetailsPg = InvMgrClosureDetailPage.getInstance();
		
		logger.info("Verify Occurrence Pattern as default.");
		if(closureDetailsPg.isDayOfMonthExisted())
			throw new ErrorOnPageException("Day of Month text box should NOT display on page.");
		if(closureDetailsPg.isSundayCheckboxExisted())
			throw new ErrorOnPageException("Sunday checkbox should NOT display on page.");
		
		logger.info("Verify Occurrence Pattern for "+occPatterns.get(1));
		closureDetailsPg.selectOccurencePattern(occPatterns.get(1));
		closureDetailsPg.waitLoading();
		if(!closureDetailsPg.isDayOfMonthExisted())
			throw new ErrorOnPageException("Day of Month text box should display on page.");
		logger.info("---Verify day of month text box on page successfully.");
		
		logger.info("Verify Occurrence Pattern for "+occPatterns.get(2));
		closureDetailsPg.selectOccurencePattern(occPatterns.get(2));
		closureDetailsPg.waitLoading();
		if(!closureDetailsPg.isSundayCheckboxExisted())
			throw new ErrorOnPageException("Sunday checkbox should display on page.");
		logger.info("---Verify Sunday checkbox on page successfully.");
		
		logger.info("Verify Occurrence Pattern for "+occPatterns.get(3));
		closureDetailsPg.selectOccurencePattern(occPatterns.get(3));
		closureDetailsPg.waitLoading();
		if(!closureDetailsPg.isSundayCheckboxExisted())
			throw new ErrorOnPageException("Sunday checkbox should display on page.");
		logger.info("---Verify Sunday checkbox on page successfully.");
	}
	
	private void verifyChangeRequestModeLink() {
		InvMgrClosureDetailPage closureDetailsPg = InvMgrClosureDetailPage.getInstance();
		
		boolean existed = closureDetailsPg.isChangeRequestModeLinkExisted();
		
		if(!existed)
			throw new ErrorOnPageException("Change Request Mode link should exist on closure detail page.");
		logger.info("---Verify change request mode link on page successfully.");
	}
}
