package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.renewal;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerPrivilegePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:Verify auto select the privilege which need to renew and no need confirm.
 * @Preconditions:"PrivilegeQuickRenew" feature need to assign.
 * @SPEC:
 * @Task#:AUTO-1066 Spira Team case number is TC:037527
 * 
 * @author jwang8
 * @Date  2012-06-05
 */
public class QuickRenewalPrivilegeAutoFilter extends LicenseManagerTestCase{

	private PrivilegeInfo [] privilegeList = null;
	private String renewalValidToDay = "";
	private String renewalValidFromDay = "";
	private LicMgrCustomerPrivilegePage custPrivilegPg = LicMgrCustomerPrivilegePage
	.getInstance();
	
	public void execute() {
		lm.loginLicenseManager(login);
		//invalidate privilege.
        lm.invalidatePrivilegePerCustomer(cust, privilegeList);
		
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilegeList);
		lm.processOrderCart(pay,false);
		
		lm.quickRenewalPrivilegeToCart(cust.identifier.identifierNum,privilegeList);
		lm.processOrderCart(pay,false);
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoPrivilegeTabPageFromCustDetailPg();
		boolean pass = custPrivilegPg.comparePrivilegeValidDate(privilegeList[0].purchasingName,renewalValidFromDay, renewalValidToDay,1);
		if(!pass){
			throw new ErrorOnPageException("Purchase privilege "+privilegeList[0].purchasingName+"valid data info error.");
		}
		pass = verifyAutoFilterRenewalPrivilege(privilegeList[1].purchasingName);
		if(!pass){
			throw new ErrorOnPageException("renewal privilege "+privilegeList[1].purchasingName+"valid data info error");
		}
		lm.gotoHomePage();
		lm.invalidatePrivilegePerCustomer(cust, privilegeList);
		
		lm.logOutLicenseManager();
	}

	
	public void wrapParameters(Object[] param) {
		
		schema = DataBaseFunctions.getSchemaName("MS", env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		cust.fName = "QA-Renewal5";
		cust.lName = "TEST-Renewal5";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Feb 05 1985";
		cust.identifier.identifierType = "MDWFP #";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName , cust.fName, schema);
		cust.residencyStatus = "Non Resident";
		
		String fiscalYear = lm.getFiscalYear(schema);
		
		privilegeList = new PrivilegeInfo[2];
		privilegeList[0] = new PrivilegeInfo();
		privilegeList[0].purchasingName = "rpc-renewalPriConfirm";
		privilegeList[0].licenseYear = fiscalYear;	
		privilegeList[0].qty = "1";
		privilegeList[0].validFromDate = DateFunctions.getToday();
		privilegeList[0].validToDate = DateFunctions.getDateAfterToday(365);
		privilegeList[0].operateReason = "21 - Other";
		privilegeList[0].operateNote = "QA Automation";
		
		privilegeList[1] = new PrivilegeInfo();
		privilegeList[1].purchasingName = "prf-renewPriFiter";
		privilegeList[1].licenseYear = fiscalYear;	
		privilegeList[1].qty = "1";
		privilegeList[1].validFromDate = DateFunctions.getToday();
		privilegeList[1].validToDate = DateFunctions.getDateAfterToday(365);
		privilegeList[1].operateReason = "21 - Other";
		privilegeList[1].operateNote = "QA Automation";
		
		renewalValidFromDay = privilegeList[0].validToDate;
		renewalValidToDay = DateFunctions.getDateAfterGivenDay(renewalValidFromDay, 365);
		
	}
	/**
	 * verify the auto filtered privilege should not renewal.
	 */
	private boolean verifyAutoFilterRenewalPrivilege(String privilegeName){
		boolean isMoreRecord = true;
		List<List<String>> listArray = custPrivilegPg.getTheSamePrivlegeRowInfo(privilegeName);
		if(listArray.size()>1){
			isMoreRecord = false;
		}
		return isMoreRecord;
	}

}
