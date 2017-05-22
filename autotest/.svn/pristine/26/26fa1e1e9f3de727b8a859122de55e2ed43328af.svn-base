package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description: This case is used to verify editing an existing privilege
 * product(including Display Order) successfully or not
 * @Preconditions:
 * @SPEC: <<Edit Privilege Product.doc>> <<View Privilege Product List.doc>>
 * @Task#: AUTO-842
 * 
 * @author qchen
 * @Date  Dec 29, 2011
 */
public class EditPrivilege extends LicenseManagerTestCase {
	private PrivilegeInfo privilege = new PrivilegeInfo();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		
		//add a privilege product as precondition
		lm.addPrivilegeProduct(privilege);
		
		//edit the new added privilege
		this.prepareEditingParameters();
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.editPrivilege(privilege);
		
		//update the privilege display order
		lm.editDisplayOrderForPrivilege(privilege.code, privilege.displayOrder);
		
		//verify privilege list info
		lm.verifyPrivilegeListInfo(privilege);
		
		//verify privilege detail info
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
		privilege.name = "Add_ForEdit_Basic";
		privilege.productGroup = "Privileges";
		privilege.status = "Active";
		privilege.validFromDateCalculation = "Prompt for Valid From Date";
		privilege.promptIndicator = "Per Privilege";
		privilege.validToDateCalculation = "Valid To Date of Previous License plus Valid Days/Years";
		privilege.validDaysYears = "1";
		privilege.dateUnitOfValidToDate = "Years";
		privilege.renewalDays = "30";
		privilege.validToAge = "0";
		privilege.validDatePrinting = new String[]{"Print Valid From Date", "Print Valid To Date", "Print Valid From Time", "Print Valid To Time"};
		privilege.customerClasses = new String[]{"Individual"};
		privilege.authorizationQuantity = "Return as Single Privilege With Quantity";
		privilege.invType = "Winter Paddlefish Tags";
		privilege.displayCategory = "Saltwater Fishing";
		privilege.displaySubCategory = "Applications";
		privilege.reportCategory = "Non-Resident Licenses";
	}
	
	public void prepareEditingParameters() {
		privilege.name = "EditPrivilege_Basic";
		privilege.status = "Active";
		privilege.validFromDateCalculation = "Prompt for Valid From Date and Time";
		privilege.promptIndicator = "Per Transaction";
		privilege.validToDateCalculation = "Valid From Date plus Valid Days/Years";
		privilege.validDaysYears = "365";
		privilege.dateUnitOfValidToDate = "Days";
		privilege.renewalDays = "0";
		privilege.validToAge = "0";
		privilege.validDatePrinting = new String[]{"Print Valid From Date", "Print Valid To Date"};
		privilege.customerClasses = new String[]{"Business"};
		privilege.authorizationQuantity = "Return as Individual Privileges";
		privilege.invType = "Summer Paddlefish";
		privilege.displayCategory = "Freshwater Fishing";
		privilege.displaySubCategory = "Licenses";
		privilege.reportCategory = "Sales for DMR";
		
		privilege.displayOrder = "14";
	}
}
