package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.transfer.businessrule;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Don't change privilege info!!!!!!
 * Rule Name:IF parameter privilege is on file, the valid dates of this privilege CANNOT overlap with the valid dates of the parameter privilege
 * The valid dates of privileges should be NOT overlapped, verify transfer successfully.
 * @Preconditions:1.existing 2 customers
 *                2.privilege can be purchased and transferred
 *                (valid dates of privileges should be NOT overlapped.)
 *                3.Transfer from privilege must have business rule: IF parameter privilege is on file, the valid dates of this privilege CANNOT overlap with the valid dates of the parameter privilege
 *                (d_hf_add_pri_business_rule, ID:200)
 *                4.Privilege must have license year which is fiscal year
 * @SPEC:Check Business Rules for Privilege Transfer.doc
 * @Task#:Auto-872
 * 
 * @author nding1
 * @Date  Mar 12, 2012
 */
public class ValidDatesCannotOverlapWithParamPrivilege2 extends LicenseManagerTestCase {
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private Customer toCust = new Customer();
	private PrivilegeInfo parameterPriv = new PrivilegeInfo();
	private String locationSales;
	
	@Override
	public void execute(){
		lm.checkPrivilegesExist(schema, privilege.code);
		lm.loginLicenseManager(login);
		
		// 1. Precondition check: should not overlap, will NOT violate rule.
		checkValidDateOverlap(false);
		
		// 2.clean up active privilege orders.
		lm.switchLocationInHomePage(locationSales);
		lm.invalidatePrivilegePerCustomer(cust, privilege, parameterPriv);
		lm.invalidatePrivilegePerCustomer(toCust, privilege, parameterPriv);

		// 3.make a parameter privilege order for transfer to customer
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(toCust, parameterPriv);
		lm.processOrderCart(pay);
		
		// 4.make a privilege order for transferred from customer
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum1 = lm.processOrderCart(pay).split(" ")[0];

		// 8.transfer again
		lm.gotoPrivilegeOrderDetailPage(orderNum1);
		String allPrivNum = privOrderDetailsPage.getAllPrivilegesNum();
		lm.gotoPrivilegeDetailFromOrderPg(allPrivNum);
		lm.transferPrivToOrderCart(toCust, privilege);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}
	
	@Override
	public void wrapParameters(Object[] param){
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "Aug 12 1986";
		cust.identifier.identifierType = "MDWFP #";
		cust.fName = "QA-TransferRule19";
		cust.lName = "TEST-TransferRule19";
		cust.residencyStatus = "Non Resident";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);

		toCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		toCust.dateOfBirth = "Aug 12 1986";
		toCust.identifier.identifierType = "MDWFP #";
		toCust.fName = "QA-TransferRule119";
		toCust.lName = "TEST-TransferRule119";
		toCust.residencyStatus = "Non Resident";
		toCust.identifier.identifierNum = lm.getCustomerNumByCustName(toCust.lName, toCust.fName, schema);

		privilege.code = "928";
		privilege.name = "NotOverlap1";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "21 - Other";
		privilege.operateNote = "";
		privilege.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};

		parameterPriv.code = "927";
		parameterPriv.name = "NotOverlap2";
		parameterPriv.purchasingName = parameterPriv.code+"-"+parameterPriv.name;
		parameterPriv.licenseYear = lm.getFiscalYear(schema);
		parameterPriv.qty = "1";
		parameterPriv.operateReason = "21 - Other";
		parameterPriv.operateNote = "";
		parameterPriv.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};

		locationSales = "HF HQ Role - Auto-WAL-MART";
	}

	/**
	 * Get privilge valid from and to date.
	 * @param privilegeCode
	 */
	private void getValidDate(PrivilegeInfo pri){
		LicMgrPrivilegeLicenseYearPage licYearPg = LicMgrPrivilegeLicenseYearPage.getInstance();
		
		lm.gotoProductSearchListPageFromTopMenu("Privilege");
		lm.gotoPrivilegeDetailsPageFromProductListPage(pri.code);
		lm.gotoPrivilegeSubTabPage(pri.code, "License Year");

		LicenseYear licYearInfo = licYearPg.getLicenseYearInfo("All", pri.licenseYear);
		pri.licYear.validFromDate = licYearInfo.validFromDate;
		pri.licYear.validToDate = licYearInfo.validToDate;
	}
	
	/**
	 * Check the valid date is overlap or not.
	 * @param expect
	 */
	private void checkValidDateOverlap(boolean expect){
		// get privilege valid from and to date.
		this.getValidDate(privilege);
		this.getValidDate(parameterPriv);
		
		// if the valid date is overlap
		boolean isOverlap;
		
		if((DateFunctions.compareDates(privilege.licYear.validFromDate, parameterPriv.licYear.validFromDate) >= 0 &&
				DateFunctions.compareDates(privilege.licYear.validFromDate, parameterPriv.licYear.validToDate) < 0) ||
				(DateFunctions.compareDates(parameterPriv.licYear.validFromDate, privilege.licYear.validFromDate) >= 0 &&
					DateFunctions.compareDates(parameterPriv.licYear.validFromDate, privilege.licYear.validToDate) < 0)){
			isOverlap = true;
		} else {
			isOverlap = false;
		}
		
		if(isOverlap != expect){
			throw new ErrorOnPageException("The valid date of privilege ("+privilege.code+" and "+parameterPriv.code+") should "+(expect?"NOT":"")+" be overlap. Please update the valid date and click Bypass to make it work.");
		}
	}
}
