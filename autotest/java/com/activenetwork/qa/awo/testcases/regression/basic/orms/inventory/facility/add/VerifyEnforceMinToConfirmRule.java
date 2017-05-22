package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.facility.add;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrAddFacilityDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrAddFacilitySelectLocationPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This test case is to verify the "Enforce Minimum to Confirm Rule in Field" in facility detail page when add facility.
 * It should contains Yes and No option, and should only contains these two options.
 * @Preconditions: Need assign feature "CreateFacility".
 * (D_ASSIGN_FEATURE ID:1640)
 * @SPEC: TC:042088
 * @Task#: AUTO-1201
 * 
 * @author nding1
 * @Date  Aug 16, 2012
 */
public class VerifyEnforceMinToConfirmRule extends InventoryManagerTestCase {
	private FacilityData facility = new FacilityData();
	private InvMgrAddFacilityDetailsPage addFacilityDetailPg = InvMgrAddFacilityDetailsPage.getInstance();
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		this.gotoAddNewFacilityDetailPage(facility);
		
		// default value is No
		String defaultValue = addFacilityDetailPg.getSelectionOfEnforceMinToConfirmRule();
		if(!OrmsConstants.NO_STATUS.equals(defaultValue)){
			throw new ErrorOnPageException("---Default value is not correct.Expect value is:No, but actual value is:"+defaultValue);
		}
		
		// get elements of Enforce Minimum to Confirm Rule in Field drop down list
		List<String> elementsList = addFacilityDetailPg.getElementsOfEnforceMinToConfirmRule();
		if(null == elementsList || elementsList.size() != 2){
			throw new ErrorOnPageException("---Enforce Minimum to Confirm Rule in Field drop down list should contains Yes and No option, and ONLY these two options.");
		} else {
			if(elementsList.contains(OrmsConstants.YES_STATUS) && elementsList.contains(OrmsConstants.NO_STATUS)){
				logger.info("Enforce Minimum to Confirm Rule in Field drop down list contains Yes and No option.");
			} else {
				throw new ErrorOnPageException("---Enforce Minimum to Confirm Rule in Field drop down list should contains Yes and No option...");
			}
		}
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		facility.agency = "STATE PARKS";
		facility.region = "DISTRICT 1";
	}
	
	/**
	 * "Go to add new facility detail page
	 * @param fd
	 */
	private void gotoAddNewFacilityDetailPage(FacilityData fd){
		InvMgrHomePage invHmPg = InvMgrHomePage.getInstance();
		InvMgrAddFacilitySelectLocationPage selectLocationPg = InvMgrAddFacilitySelectLocationPage.getInstance();
		
		logger.info("Go to add new facility detail page...");
		invHmPg.clickFacilitiesAddNew();
		selectLocationPg.waitLoading();
		selectLocationPg.setupLocationData(fd);
		selectLocationPg.clickOK();
		addFacilityDetailPg.waitLoading();
	}
}
