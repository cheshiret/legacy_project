package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description: (P) Verify basic work flow to add Inventory product type privilege
 * @SPEC: Add Privilege Product - Product Group is Inventory [TC:084747]
 * @Task#:AUTO-2050
 * 
 * @author SWang
 * @Date  Jan 25, 2014
 */
public class AddPrivWithInventoryPrdType extends LicenseManagerTestCase {

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeSearchListPageFromTopMenu();

			//add privilege product
			lm.addPrivilegeProduct(privilege);
			
			try{
			//verify privilege brief info in list page
			lm.verifyPrivilegeListInfo(privilege);

			//verify privilege detail info in details page
			lm.verifyPrivilegeDetailInfo(privilege, true);
		}finally{
			
			//clean up
			lm.gotoPrivilegeSearchListPageFromTopMenu();
			lm.changePrivilegeProductStatus(privilege.code, OrmsConstants.INACTIVE_STATUS);
			lm.logOutLicenseManager();
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "SK Contract";
		login.location = "SK Admin - Auto/SASK";

		privilege.code = StringUtil.getRandomString(3, true);
		privilege.name = "AddInventoryPrdTypePriv";
		privilege.legalName = "LegalNameForPriv-"+privilege.code;
		privilege.productGroup = "Inventory";
		privilege.status = "Active";
		privilege.customerClasses = new String[]{OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS};
		privilege.invType = "ConvPack Type";
		privilege.inventoryQty = "2";
		privilege.displayCategory = "Angling";
		privilege.displaySubCategory = "Annual";
		privilege.reportCategory = "Non Resident Licences";
		privilege.priceNote = "Automation test";
	}
}
