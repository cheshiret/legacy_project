package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductViewChangeHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description: This case is used to verify the privilege change history info
 * @Preconditions:
 * @SPEC: <<View Privilege Product Info Change History.doc>>
 * @Task#: AUTO-842
 * 
 * @author qchen
 * @Date  Dec 30, 2011
 */
public class ViewPrivilegeChangeHistory extends LicenseManagerTestCase {
	private PrivilegeInfo privilege  = new PrivilegeInfo();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		
		//add a new privilege product
		lm.addPrivilegeProduct(privilege);
		
		//edit privilege product
		this.prepareEditingParameters();
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.editPrivilege(privilege);
		
		//update the privilege display order
		lm.editDisplayOrderForPrivilege(privilege.code, privilege.displayOrder);
		
		//update privilege status
		lm.changePrivilegeProductStatus(privilege.code, OrmsConstants.INACTIVE_STATUS);

		//verify privilege info change history
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code, false);
//		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeChangeHistoryDetailPage();
		this.verifyChangeHistoryInfo(privilege);
		
		//clean up
		lm.deletePrivilegeInDB(schema, privilege.code);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = DataBaseFunctions.getSchemaName("MS",env);
		
		privilege.code = StringUtil.getRandomString(3, true);
		privilege.name = "Add_ForViewHistory_Basic";
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
		privilege.name = "Edit_ForViewHistory_Basic";
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
		
		privilege.displayOrder = "13";
		privilege.updateDate = DateFunctions.getToday();
		privilege.createUser = DataBaseFunctions.getLoginUserName(login.userName);
		privilege.createLocation = login.location.split("/")[1].trim();
	}
	
	/**
	 * Verify privilege change history info
	 * @param expectedPriv
	 */
	public void verifyChangeHistoryInfo(PrivilegeInfo expectedPriv) {
		LicMgrProductViewChangeHistoryPage changeHistoryPage = LicMgrProductViewChangeHistoryPage.getInstance();
		
		boolean result = changeHistoryPage.comparePrivilegeChangeHistoryInfo(expectedPriv);
		if(!result) {
			throw new ErrorOnPageException("Privilege change history info are incorrect.");
		} else logger.info("Privilege Change History info are correct.");
	}
}
