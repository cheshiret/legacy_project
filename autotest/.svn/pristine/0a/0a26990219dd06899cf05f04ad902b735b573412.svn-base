package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.renewal;

import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerPrivilegePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:Quieck renewal privilege which have inventory type and inventory number.
 * @Preconditions:"PrivilegeQuickRenew" feature need to assign.
 * Privilege inventory in table d_hf_priv_invtype_licyear, ID:30
 * 
 * @SPEC:
 * @Task#::AUTO-1066 Spira Team case number is TC:037524
 * 
 * @author jwang8
 * @Date  2012-06-01
 */
public class QuickRenewalPrivilegeWithInventory extends LicenseManagerTestCase{

	private String renewalValidToDay = "";
	private String renewalValidFromDay = "";
	private LicMgrCustomerPrivilegePage custPrivilegPg = LicMgrCustomerPrivilegePage
	.getInstance();
	public void execute() {
		lm.loginLicenseManager(login);
		
		// clean up
		privilege.operateReason = "21 - Other";
		lm.invalidatePrivilegePerCustomer(cust, privilege);
		lm.gotoHomePage();
		
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum1 = lm.processOrderCart(pay,false);
		logger.info("orderNum1 is " +orderNum1);
		if(orderNum1.contains(" ")){
			orderNum1 = orderNum1.split(" ")[0];
		}
		
		privilege.inventoryNum = "02";
		lm.quickRenewalPrivilegeToCart(cust.identifier.identifierNum,privilege);
		String orderNum2 = lm.processOrderCart(pay,false);
		logger.info("orderNum2 is " +orderNum2);
		if(orderNum2.contains(" ")){
			orderNum2 = orderNum2.split(" ")[0];
		}
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoPrivilegeTabPageFromCustDetailPg();
		boolean pass = custPrivilegPg.comparePrivilegeValidDate(privilege.purchasingName,renewalValidFromDay, renewalValidToDay,1);
		if(!pass){
			throw new ErrorOnPageException("renewal privilege valid data info error.");
		}
		
		lm.gotoHomePage();
		lm.gotoOrderPageFromOrdersTopMenu(orderNum1);
		privilege.operateReason = "14 - Other";
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);
		
		lm.gotoOrderPageFromOrdersTopMenu(orderNum2);
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("MS", env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		cust.fName = "QA-Renewal2";
		cust.lName = "TEST-Renewal2";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Feb 03 1985";
		cust.identifier.identifierType = "MDWFP #";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName , cust.fName, schema);//"814618203";
		cust.residencyStatus = "Non Resident";
		
		privilege.invType = "renewalPrivelegeTest";
		privilege.purchasingName = "rpi-renewalPriInv";
		privilege.licenseYear = lm.getFiscalYear(schema);	
		privilege.qty = "1";
		privilege.inventoryNum = "01";
		privilege.operateNote = "QA Automation";
		
		privilege.validFromDate = DateFunctions.getToday();
		privilege.validToDate =  DateFunctions.getDateAfterToday(365);//The privilege vaild date was set 356 day.
		renewalValidFromDay = privilege.validToDate;
		renewalValidToDay = DateFunctions.getDateAfterGivenDay(renewalValidFromDay, 365);
	}

}
