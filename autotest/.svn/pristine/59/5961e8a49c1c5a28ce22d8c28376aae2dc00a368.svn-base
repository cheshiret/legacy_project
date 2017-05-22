package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.closure;

import java.util.ArrayList;
import java.util.List;

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
 * Search entry [TC:045410]
 * @Task#: Auto-1894
 * @author Jane
 * @Date  Sep 22, 2013
 */
public class VerifyUIForSearchClosurePage extends InventoryManagerTestCase {
	private String facilityID;
	private List<String> dateTypes;
	private List<String> closureTypes;
	private List<String> prdCategories;
	private List<String> recurring;
	private List<String> affRes;
	private List<String> instructions;
	private List<String> application;
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoFacilityDetailPageById(facilityID);
		im.gotoClosurePage();
		//TODO handled with start date and end date
		verifyUIForSearchClosurePage();
		
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
	    login.contract = "NC Contract";
	    login.location="Administrator/North Carolina State Parks";
	    schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "NC";
	    
	    facilityID = "552834";

	    dateTypes = new ArrayList<String>();
	    dateTypes.add("Start Date");
	    dateTypes.add("End Date");
	    dateTypes.add("Notification Date");
	    dateTypes.add("Closure Includes");
	    
	    closureTypes = new ArrayList<String>();
	    closureTypes.add("");
	    closureTypes.add("Maintenance");
	    closureTypes.add("Emergency");
	    closureTypes.add("Problem");
	    closureTypes.add("Seasonal");
	    closureTypes.add("Special Event");
	    closureTypes.add("General");
	    closureTypes.add("Cabin Cleaning");
	    closureTypes.add("Block Advance Reservation");
	    
	    prdCategories = new ArrayList<String>();
	    prdCategories.add("Site");
	    prdCategories.add("Slip");
	    
	    recurring = new ArrayList<String>();
	    recurring.add("");
	    recurring.add("Yes");
	    recurring.add("No");
	    
	    affRes = new ArrayList<String>();
	    affRes.add("");
	    affRes.add("Yes");
	    affRes.add("No");
	    
	    instructions = new ArrayList<String>();
	    instructions.add("");
	    instructions.add("Not Specified");
	    instructions.add("Honor Reservations");
	    instructions.add("Cancel Reservations");
	    instructions.add("Cancel Reservations With Full Refund");
	    instructions.add("Transfer Reservations");
	    
	    application =  new ArrayList<String>();
	    application.add("");
	    application.add("Inventory Manager");
	    application.add("Field Manager");
	}
	
	private void verifyUIForSearchClosurePage() {
		InvMgrClosuresPage closurePg = InvMgrClosuresPage.getInstance();
		
		List<String> dateTypesUI = closurePg.getDateTypeValues();
		List<String> closureTypesUI = closurePg.getClosureTypeValues();
		List<String> prdCategoriesUI = closurePg.getPrdCategoryValues();
		List<String> recurringUI = closurePg.getRecurringValues();
		List<String> affResUI = closurePg.getAffResValues();
		List<String> instructionsUI = closurePg.getInstructionValues();
		List<String> applicationUI = closurePg.getApplicationValues();
		
		if(!dateTypesUI.equals(dateTypes))
			throw new ErrorOnPageException("Date types values displayed uncorrectly on page.", dateTypes, dateTypesUI);
		logger.info("---Verify date types values on page successfully.");
	
		if(!closureTypesUI.equals(closureTypes))
			throw new ErrorOnPageException("Closure types values displayed uncorrectly on page.", closureTypes, closureTypesUI);
		logger.info("---Verify closure types values on page successfully.");
		
		if(!prdCategoriesUI.equals(prdCategories))
			throw new ErrorOnPageException("Product category values displayed uncorrectly on page.", prdCategories, prdCategoriesUI);
		logger.info("---Verify product category values on page successfully.");
		
		if(!recurringUI.equals(recurring))
			throw new ErrorOnPageException("Recurring values displayed uncorrectly on page.", recurring, recurringUI);
		logger.info("---Verify recurring values on page successfully.");
		
		if(!affResUI.equals(affRes))
			throw new ErrorOnPageException("Affected Reservations values displayed uncorrectly on page.", affRes, affResUI);
		logger.info("---Verify Affected Reservations values on page successfully.");
		
		if(!instructionsUI.equals(instructions))
			throw new ErrorOnPageException("Instructions values displayed uncorrectly on page.", instructions, instructionsUI);
		logger.info("---Verify instructions values on page successfully.");
		
		if(!applicationUI.equals(application))
			throw new ErrorOnPageException("Application values displayed uncorrectly on page.", application, applicationUI);
		logger.info("---Verify application values on page successfully.");
	}

}
