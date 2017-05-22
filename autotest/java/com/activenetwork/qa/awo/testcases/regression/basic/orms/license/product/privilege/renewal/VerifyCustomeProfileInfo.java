package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.renewal;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmCustomerPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:Eidt the customer profile in renew privilege process.
 * @Preconditions:  1."ModifyCustomerProfile" feature need to assign.
 * 2.Privilege must have only one license year record.(Fiscal year)
 * @SPEC:
 * @Task#::Auto-1106 Spira Team case number is TC:036470
 * 
 * @author jwang8
 * @Date  2012-07-02
 */
public class VerifyCustomeProfileInfo extends LicenseManagerTestCase{
	
	private LicMgrConfirmCustomerPage confirmPg = LicMgrConfirmCustomerPage.getInstance();

	public void execute() {
		lm.loginLicenseManager(login);
		//invalidate privilege.
        lm.invalidatePrivilegePerCustomer(cust, privilege);
		
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		lm.processOrderCart(pay,false);
		
		lm.gotoQuickRenewPrivilegeCustomerProfilePage(cust.identifier.identifierNum);
		lm.editRenewPrivilegeCustomerProfileInfo(cust);
		lm.processOrderCart(pay,false);
		
		lm.gotoQuickRenewPrivilegeCustomerProfilePage(cust.identifier.identifierNum);
		this.verifyRenewCustomerDetailsInfo(cust);
		confirmPg.verifyOkButtonEnable();
		confirmPg.verifyCancelButtonEnable();
		
		lm.gotoHomePage();
		lm.invalidatePrivilegePerCustomer(cust, privilege);
		
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = DataBaseFunctions.getSchemaName("MS", env);
		
		cust.fName = "QA-Renewal";
		cust.lName = "TEST-Renewal";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Feb 01 1985";
		cust.identifier.identifierType = "MDWFP #";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);//"814618203";
		cust.residencyStatus = "Non Resident";
		
		cust.status = "Active";
		cust.suffix = "SR";
		cust.hPhone = "4088333333";
		cust.bPhone = "4088222222";
		cust.mPhone = "4088111111";	
		cust.fax = "02178945";
		cust.email = "Liu@sina.com";	
		cust.custGender = "Male";
		cust.ethnicity = "Black";
		cust.solicitationIndcator = "Yes";
		
		cust.physicalAddr.address ="H"+ DateFunctions.getCurrentTime();
		cust.physicalAddr.supplementalAddr = "B" + DateFunctions.getCurrentTime();
		cust.physicalAddr.city = "Virginia Beach";
		cust.physicalAddr.state="Virginia";
		cust.physicalAddr.county="Virginia Beach city";
		cust.physicalAddr.zip = "23456";
		cust.physicalAddr.country="United States";	
		cust.physicalAddr.isValidate = true;
		cust.mailAddrAsPhy=false;
		
		cust.mailingAddr.address ="J"+DateFunctions.getCurrentTime();
		cust.mailingAddr.supplementalAddr = "N" + DateFunctions.getCurrentTime();
		cust.mailingAddr.city = "Houghton";
		cust.mailingAddr.state="Iowa";
		cust.mailingAddr.county="Lee";
		cust.mailingAddr.zip = "52631";
		cust.mailingAddr.country="United States";	
		cust.mailingAddr.isValidate = true;
		
		
		privilege.purchasingName = "rew-quickRenewal";
		privilege.licenseYear = lm.getFiscalYear(schema);		
		privilege.qty = "1";
		privilege.validFromDate = DateFunctions.getToday();
		privilege.validToDate =  DateFunctions.getDateAfterToday(365);//The privilege vaild date was set 356 day.
		privilege.operateReason = "21 - Other";
		privilege.operateNote = "QA Automation";
		
	}
	/*
	 * verify renew customer details info
	 */
	public void verifyRenewCustomerDetailsInfo(Customer cust){
		boolean pass = confirmPg.compareCustomerInfo(cust);
		if(!pass){
			throw new ErrorOnPageException("Renew customer info error.");
		}
	}
	
	

}
