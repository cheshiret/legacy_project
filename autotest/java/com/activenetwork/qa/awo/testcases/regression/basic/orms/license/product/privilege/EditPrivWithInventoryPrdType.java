package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description: (P) Verify basic work flow to edit Inventory product type privilege
 * @SPEC:Edit Privilege Product - Product Group is Inventory [TC:084803]
 * @Task#:AUTO-2050
 * 
 * @author SWang
 * @Date  Jan 25, 2014
 */
public class EditPrivWithInventoryPrdType extends LicenseManagerTestCase {
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
		lm.verifyPrivilegeDetailInfo(privilege, true);

		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "SK Contract";
		login.location = "SK Admin - Auto/SASK";

		privilege.code = StringUtil.getRandomString(3, true);
		privilege.name = "AddInventoryPrdTypePriv";
		privilege.legalName = "LegalNameForAddPriv-"+privilege.code;
		privilege.productGroup = "Inventory";
		privilege.status = OrmsConstants.ACTIVE_STATUS;
		privilege.customerClasses = new String[]{OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS};
		privilege.invType = "ConvPack Type";
		privilege.inventoryQty = "2";
		privilege.displayCategory = "Angling";
		privilege.displaySubCategory = "Annual";
		privilege.reportCategory = "Non Resident Licences";
		privilege.priceNote = "Price note for add";
	}

	public void prepareEditingParameters() {
		privilege.name = "EditnventoryPrdTypePriv";
		privilege.legalName = "LegalNameFoEditrPriv-"+privilege.code;
		privilege.status = OrmsConstants.INACTIVE_STATUS;
		privilege.customerClasses = new String[]{OUTFITTER_CUSTOMER_CLASS};
		privilege.invType = "Big Game Seal";
		privilege.inventoryQty = "3";
		privilege.displayCategory = "Hunting";
		privilege.displaySubCategory = "Big Game";
		privilege.reportCategory = "Big Game Draw";
		privilege.priceNote = "Price note for edit";
		privilege.displayOrder = "12";
	}
}
