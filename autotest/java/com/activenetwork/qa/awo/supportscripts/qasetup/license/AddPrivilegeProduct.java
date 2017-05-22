/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import java.util.Arrays;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddPrivilegeProductFunction;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author szhou
 * @Date Feb 1, 2012
 */
public class AddPrivilegeProduct extends SetupCase {
	private PrivilegeInfo privilege = new PrivilegeInfo();
	private Object[] args = new Object[4];
	private AddPrivilegeProductFunction func = new AddPrivilegeProductFunction();

	@Override
	public void executeSetup() {
		func.execute(args);
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_privilege_prd";
		ids = "3380";
	}

	@Override
	public void readDataFromDatabase() {
		args[0] = datasFromDB.get("contract");
		args[1] = datasFromDB.get("location");

		privilege.code = datasFromDB.get("code");
		privilege.name = datasFromDB.get("name");
		privilege.productGroup = datasFromDB.get("productGroup");
		privilege.validFromDateCalculation = datasFromDB
				.get("validFromDateCalculation");
		privilege.promptIndicator = datasFromDB.get("promptIndicator");
		privilege.validToDateCalculation = datasFromDB
				.get("validToDateCalculation");
		privilege.validDaysYears = datasFromDB.get("validDaysYears");
		privilege.dateUnitOfValidToDate = datasFromDB
				.get("dateUnitOfValidToDate");
		privilege.renewalDays = datasFromDB.get("renewalDays");
		privilege.validToAge = datasFromDB.get("validToAge");
		if (!"".equals(datasFromDB.get("validDatePrinting"))) {
			privilege.validDatePrinting = datasFromDB.get("validDatePrinting")
					.split("\\|");
		}
		if (!"".equals(datasFromDB.get("customerClass"))) {
			privilege.customerClasses = datasFromDB.get("customerClass").split(
					"\\|");
		}
		privilege.authorizationQuantity = datasFromDB
				.get("authorizationQuantity");
		privilege.invType = datasFromDB.get("inventoryType");
		privilege.inventoryQty = datasFromDB.get("inventoryQty");
		privilege.displayCategory = datasFromDB.get("displayCategory");
		privilege.displaySubCategory = datasFromDB.get("displaySubCategory");
		privilege.reportCategory = datasFromDB.get("reportCategory");
		privilege.huntsRequired = datasFromDB.get("avaViaAppOnly");
		privilege.allocationPriv = datasFromDB.get("allocationPriv");
		if(StringUtil.notEmpty(datasFromDB.get("allowquicksale")))
			privilege.allowQuickSale = Boolean.parseBoolean(datasFromDB.get("allowquicksale"));
		if(StringUtil.notEmpty(datasFromDB.get("CONSECUTIVEDAYSIND")))
			privilege.consecutiveDaysInd = datasFromDB.get("CONSECUTIVEDAYSIND");
		if(StringUtil.notEmpty(datasFromDB.get("DEFAULTTOBLANK")))
			privilege.defaultToBlank = datasFromDB.get("DEFAULTTOBLANK");
		if(StringUtil.notEmpty(datasFromDB.get("ALLOWGENERALPUBLIC")))
			privilege.allowGeneralPublic = Boolean.parseBoolean(datasFromDB.get("ALLOWGENERALPUBLIC"));
		privilege.inventoryQtyType = datasFromDB.get("QTYTYPE");
		privilege.inventoryQtyFrom = datasFromDB.get("QTYFROM");
		privilege.inventoryQtyTo = datasFromDB.get("QTYTO");
		privilege.purchaseAuth = datasFromDB.get("PURCHASEAUTH");
		privilege.purchaseAuthType = datasFromDB.get("PURCHASEAUTHTYPE");
		privilege.authQtyPerLY = datasFromDB.get("AUTHQTYPERLY");
		if(StringUtil.notEmpty(datasFromDB.get("CHECKBUSIRULEINSALEFLOW")))
			privilege.checkBusiRuleInSaleFlow = Boolean.parseBoolean(datasFromDB.get("CHECKBUSIRULEINSALEFLOW"));
		privilege.hunterHostLicenceType = datasFromDB.get("HUNTERHOSTLICENCETYPE");
		privilege.requiredHunterHostPrivilege = datasFromDB.get("REQUIRESHUNTERHOSTLICENCE");
		privilege.hunterHostAppendLicence = datasFromDB.get("HUNTERHOSTAPPENDLICENCE");
		privilege.maxHuntersAllowed = datasFromDB.get("MAXHUNTERSALLOWED");
		privilege.allocationType = datasFromDB.get("AllocationType");
		privilege.extensionType = datasFromDB.get("ExtensionType");
		args[2] = privilege;
		args[3] = datasFromDB.get("DELETEEXISTING");

		privilege.isPartnerLicence = datasFromDB.get("PARTNERLICENCE").equalsIgnoreCase("Yes") ? true : false;
		String associatedPartnerLicences = datasFromDB.get("ASSOCIATEDPARTNERLICENCES");
		if(!StringUtil.isEmpty(associatedPartnerLicences)) {
			String licences[] = associatedPartnerLicences.split(",");
			privilege.associatedPartnerLicences = Arrays.asList(licences);
		}
	}
}
