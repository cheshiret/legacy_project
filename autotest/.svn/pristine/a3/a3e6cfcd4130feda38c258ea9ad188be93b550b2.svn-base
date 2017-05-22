package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.property;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.cust.AuditAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.cust.PropertyAttr;
import com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property.LicMgrAuditsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property.LicMgrPropertyListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description: (P) Verify Audit number Audit list page
 * @Preconditions:
 * @LinkSetUp:
 * d_assign_feature:id=5022 --MO Admin - Auto, %Property%
 * d_hf_add_cust_profile:id=2910 --QA-Advanced13, TEST-Advaced13
 * @SPEC:
 * View Property Audit List [TC:070265] 
 * @Task#:AUTO-2043
 * 
 * @author SWang
 * @Date  Jan 16, 2014
 */
public class VerifyAuditsNum extends LicenseManagerTestCase {
	private LicMgrPropertyListPage custPropertyListPg = LicMgrPropertyListPage.getInstance();
	private LicMgrAuditsPage auditPg = LicMgrAuditsPage.getInstance();
	private Data<PropertyAttr> cpa;
	private Data<AuditAttr> audit;

	public void execute() {
		//Go to property list page
		lm.loginLicenseManager(login);
		lm.gotoPropertyPgFromTopHomePg(cust);

		//Add property if necessary
		int numOfProperty = custPropertyListPg.getNumOfProperty();
		if(numOfProperty<1){
			cpa.put(PropertyAttr.propertyID, lm.addCustProperty(cpa));
		}else cpa.put(PropertyAttr.propertyID, custPropertyListPg.getFirstPropertyID());
		audit.put(AuditAttr.propertyID, cpa.stringValue(PropertyAttr.propertyID));

		//Add audit
		lm.gotoAuditListPgFromPropertyPg(cpa.stringValue(PropertyAttr.propertyID));
		int auditNumBeforeAdd = auditPg.getAuditsNum();
		lm.addAudit(audit);
		int auditNumAfterAdd = auditPg.getAuditsNum();

		//Compare audit number
		if(auditNumAfterAdd!=auditNumBeforeAdd+1){
			throw new ErrorOnPageException("Audits number is wrong!", auditNumAfterAdd, auditNumBeforeAdd+1);
		}
		//Logout
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Login in License Manager
		schema = DataBaseFunctions.getSchemaName("MO", env);
		login.contract = "MO Contract";
		login.location = "MO Admin - Auto/MO Department of Conservation";

		// customer parameters
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.fName = "QA-Advanced13";
		cust.lName = "TEST-Advaced13";
		cust.dateOfBirth = "Jan 13 1989";
		cust.licenseType = "Conservation #";
		cust.licenseNum = lm.getCustomerNum(cust, schema);

		//Customer Property parameters
		cpa = new Data<PropertyAttr>();
		cpa.put(PropertyAttr.propertyCounty, "Barry");
		cpa.put(PropertyAttr.propertyAcres, "123");
		cpa.put(PropertyAttr.typeOfOwnership, "Private TFO1");
		cpa.put(PropertyAttr.yearOwned, String.valueOf(DateFunctions.getYearAfterCurrentYear(-1)));

		//Audits parameters
		audit = new Data<AuditAttr>();
		audit.put(AuditAttr.auditStatus, "Active");
		audit.put(AuditAttr.lName, cust.lName);
		audit.put(AuditAttr.fName, cust.fName);
		audit.put(AuditAttr.custID, cust.licenseNum);
		audit.put(AuditAttr.cust, cust.lName+", "+cust.fName+"("+cust.licenseNum+")");
		audit.put(AuditAttr.documentType, "Audit_doc2");
		audit.put(AuditAttr.documentDate, StringUtil.EMPTY);
		audit.put(AuditAttr.contactDate, StringUtil.EMPTY);
		audit.put(AuditAttr.auditYear, String.valueOf(DateFunctions.getCurrentYear()));
		audit.put(AuditAttr.auditComment, StringUtil.EMPTY);
		audit.put(AuditAttr.creationApplication, "LicenseManager");
		audit.put(AuditAttr.creationDate, DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)));
		audit.put(AuditAttr.creationUser, DataBaseFunctions.getLoginUserName(login.userName));
	}
}
