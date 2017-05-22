package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description: (P) Verify basic work flow to edit Privileges product type privilege
 * @SPEC:Edit Privilege Product - Product Group is Privilege [TC:084801]
 * @Task#:AUTO-2050
 * 
 * @author SWang
 * @Date  Jan 25, 2014
 */
public class EditPrivWithPrivilegesPrdTypeInventoryType extends LicenseManagerTestCase {
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

		LicMgrPrivilegesListPage.getInstance().searchPrivilegeByAllStatus();
		//update the privilege display order
		lm.editDisplayOrderForPrivilege(privilege.code, privilege.displayOrder);

		//verify privilege list info
		lm.verifyPrivilegeListInfo(privilege);

		//verify privilege detail info
		lm.verifyPrivilegeDetailInfo(privilege);

		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "SK Contract";
		login.location = "SK Admin - Auto/SASK";
		
		privilege.code = StringUtil.getRandomString(3, true);
		privilege.name = "AddnventoryPrdTypePriv";
		privilege.legalName = "LegalNameForAddPriv-"+privilege.code;
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
		privilege.priceNote = "Price note for add";
	}

	public void prepareEditingParameters() {
		privilege.name = "EditnventoryPrdTypePriv";
		privilege.legalName = "LegalNameFoEditrPriv-"+privilege.code;
		privilege.status = OrmsConstants.INACTIVE_STATUS;
		privilege.isLandowner = false;
		privilege.validFromDateCalculation = "Prompt for Valid From Date and Time";
		privilege.promptIndicator = "Per Transaction";
		privilege.validToDateCalculation = "Valid From Date plus Valid Days/Years";
		privilege.validDaysYears = "365";
		privilege.dateUnitOfValidToDate = "Days";
		privilege.renewalDays = "0";
		privilege.validToAge = "0";
		privilege.validDatePrinting = new String[]{"Print Valid From Date", "Print Valid To Date"};
		privilege.customerClasses = new String[]{OUTFITTER_CUSTOMER_CLASS};
		privilege.authorizationQuantity = "Return as Single Licence with Quantity";
		privilege.invType = "Big Game Seal";
		privilege.inventoryQtyType = "Fixed";
		privilege.inventoryQty = "3";
		privilege.allocationType = "None";
		privilege.allocationPriv = "(241) RAL 1st Guided White-tail";
		privilege.displayCategory = "Hunting";
		privilege.displaySubCategory = "Big Game";
		privilege.reportCategory = "Big Game Draw";
		privilege.priceNote = "Price note for edit";
		privilege.displayOrder = "12";
	}
}
