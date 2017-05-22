package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeItemSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 *
 * @Description: This case is used to search privilege.
 * @Preconditions:
 * @SPEC: Search privilege
 * @Task#: AUTO-871
 *
 * @author Jwang8
 * @Date  Feb 28, 2012
 * @defect DEFECT-33487 and DEFECT-33489.
 */
public class SearchPrivilege extends LicenseManagerTestCase{
    private PrivilegeInfo privilege = new PrivilegeInfo();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//go to customer manager search privilege page.
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		String orderNum = lm.processOrderCart(pay,false);
		if(orderNum.contains(" ")){
			orderNum = orderNum.split(" ")[0];
		}
		//Get privilege number with order number.
		privilege.searchValue = lm.getPrivilegeNumByOrdNum(schema, orderNum);
		//Go to privileges search page.
		lm.gotoPrivilegeTabPage();
		//Search and verify privilege.
		this.verifySearchPrivilegeResult();
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = DataBaseFunctions.getSchemaName("MS",env);

		cust.lName = "Test-ViewPriDetail";
		cust.fName ="QA-ViewPriDetail";
		cust.identifier.identifierType = "MDWFP #";//"Green Card";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);//"123456789";
		cust.identifier.country = "Canada";
		cust.customerClass = "Individual";
		cust.residencyStatus="Non Resident";

		privilege.purchasingName = "VPH-HisPrivilegeOrder";
		privilege.searchBy = "Privilege #";
		privilege.searchDateType = "Valid Dates";
		privilege.validFromDate = DateFunctions.getToday();
		privilege.validToDate ="";
		privilege.status ="Active";
		privilege.code= "VPH";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.searchZip = "85001";
		privilege.searchCounty = "Maricopa";
		privilege.searchState = "Arizona";
		privilege.searchCountry="United States";
		privilege.searchReStatus = "Non Resident";
		
	}

	/**
	 * Search privilege with different criteria and verify privilege result.
	 */
	public void verifySearchPrivilegeResult(){
		LicMgrPrivilegeItemSearchPage priSearchPg = LicMgrPrivilegeItemSearchPage.getInstance();
		priSearchPg.searchPrivilege(privilege);
		priSearchPg.verifyPrivilegeSearchResult(privilege);

		priSearchPg.clearUpSearchCriteria();
		PrivilegeInfo searchPri = new PrivilegeInfo();
		searchPri.searchBy = "Privilege #";
		searchPri.validFromDate = "";
		searchPri.searchValue = privilege.searchValue;
		priSearchPg.searchPrivilege(searchPri);
		priSearchPg.verifyPrivilegeSearchResult(searchPri);

		priSearchPg.clearUpSearchCriteria();
		searchPri.searchBy ="";
		searchPri.searchValue = "";
		searchPri.validFromDate = "";
		searchPri.status = "Active";
		priSearchPg.searchPrivilege(searchPri);
		priSearchPg.verifyPrivilegeSearchResult(searchPri);

		priSearchPg.clearUpSearchCriteria();
		searchPri.status = "";
		searchPri.validFromDate = "";
		searchPri.code = "VPH";
		priSearchPg.searchPrivilege(searchPri);
		priSearchPg.verifyPrivilegeSearchResult(searchPri);

	}
}
