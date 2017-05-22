package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description: (P) Verify basic work flow to add Privileges product type privilege, especially inventory type is not none
 * @SPEC: Add Privilege Product - Product Group is Privilege [TC:084678]
 * @Task#:AUTO-2050
 * 
 * @author SWang
 * @Date  Jan 25, 2014
 */
public class AddPrivWithPrivilegesPrdTypeInventoryType extends LicenseManagerTestCase {
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		
		//add privilege product
		lm.addPrivilegeProduct(privilege);
		
		//verify privilege brief info in list page
		lm.verifyPrivilegeListInfo(privilege);
		
		//verify privilege detail info in details page
		lm.verifyPrivilegeDetailInfo(privilege);
		
		//clean up
		lm.changePrivilegeProductStatus(privilege.code, OrmsConstants.INACTIVE_STATUS);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "SK Contract";
		login.location = "SK Admin - Auto/SASK";
		
		privilege.code = StringUtil.getRandomString(3, true);
		privilege.name = "AddPrivilege_Basic";
		privilege.legalName = "LegalNameForPriv-"+privilege.code;
		privilege.productGroup = "Privileges";
		privilege.status = OrmsConstants.ACTIVE_STATUS;
		privilege.isLandowner = true;
		privilege.validFromDateCalculation = "Prompt for Valid From Date";
		privilege.promptIndicator = "Per Licence";
		privilege.validToDateCalculation = "Valid To Date of Previous Licence plus Valid Days/Years";
		privilege.validDaysYears = "1";
		privilege.dateUnitOfValidToDate = "Years";
		privilege.renewalDays = "30";
		privilege.validToAge = "0";
		privilege.validDatePrinting = new String[]{"Print Valid From Date", "Print Valid To Date", "Print Valid From Time", "Print Valid To Time"};
		privilege.customerClasses = new String[]{OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS};
		privilege.authorizationQuantity = "Return as Individual Licences";
		privilege.invType = "ConvPack Type";
		privilege.inventoryQtyType = "Range";
		privilege.inventoryQtyFrom = "2";
		privilege.inventoryQtyTo= "3";
		privilege.allocationType = "None";
		privilege.allocationPriv = "None";
		privilege.displayCategory = "Angling";
		privilege.displaySubCategory = "Annual";
		privilege.reportCategory = "Non Resident Licences";
		privilege.priceNote = "Automation test";
	}
}
