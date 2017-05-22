package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description: This case is used to verify adding a privilege product successfully or not
 * @Preconditions:
 * @SPEC: <<Add Privilege Product.doc>> <<View Privilege Product List.doc>>
 * @Task#: AUTO-842
 * 
 * @author qchen
 * @Date  Dec 29, 2011
 */
public class AddPrivilege extends LicenseManagerTestCase {
	
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
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		privilege.code = StringUtil.getRandomString(3, true);
		privilege.name = "AddPrivilege_Basic";
		privilege.productGroup = "Privileges";
		privilege.status = OrmsConstants.ACTIVE_STATUS;
		privilege.validFromDateCalculation = "Prompt for Valid From Date";
		privilege.promptIndicator = "Per Privilege";
		privilege.validToDateCalculation = "Valid To Date of Previous License plus Valid Days/Years";
		privilege.validDaysYears = "1";
		privilege.dateUnitOfValidToDate = "Years";
		privilege.renewalDays = "30";
		privilege.validToAge = "0";
		privilege.validDatePrinting = new String[]{"Print Valid From Date", "Print Valid To Date", "Print Valid From Time", "Print Valid To Time"};
		privilege.customerClasses = new String[]{"Individual"};
		privilege.authorizationQuantity = "Return as Single Privilege with Quantity";
		privilege.invType = "Winter Paddlefish Tags";
		privilege.displayCategory = "Saltwater Fishing";
		privilege.displaySubCategory = "Applications";
		privilege.reportCategory = "Non-Resident Licenses";
	}
}
