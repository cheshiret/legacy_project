package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.property;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.cust.AuditAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.cust.PropertyAttr;
import com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property.LicMgrAuditDetailsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property.LicMgrAuditsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property.LicMgrPropertyListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description: (P) Basic work flow to add and edit audit and then check the results in audit list and audit details page
 * @Preconditions:
 * @LinkSetUp:
 * d_assign_feature:id=5022 --MO Admin - Auto, %Property%
 * d_hf_add_cust_profile:id=2590 --QA-Advanced03, TEST-Advaced03
 * @SPEC:
 * Add Property Audit [TC:070266] 
 * Edit Property Audit [TC:070272] 
 * View Property Audit List [TC:070265] 
 * @Task#:AUTO-2043
 * 
 * @author SWang
 * @Date  Jan 16, 2014
 */
public class AddAndEditAudit extends LicenseManagerTestCase {
	private LicMgrPropertyListPage custPropertyListPg = LicMgrPropertyListPage.getInstance();
	private LicMgrAuditDetailsWidget auditDetailsPg = LicMgrAuditDetailsWidget.getInstance();
	private LicMgrAuditsPage auditPg = LicMgrAuditsPage.getInstance();
	private Data<PropertyAttr> cpa;
	private Data<AuditAttr> audit, auditEdit;

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
		auditEdit.put(AuditAttr.propertyID, cpa.stringValue(PropertyAttr.propertyID));

		//Add audit
		lm.gotoAuditListPgFromPropertyPg(cpa.stringValue(PropertyAttr.propertyID));
		lm.addAudit(audit);
		audit.put(AuditAttr.auditID, lm.getAuditsFromDB(schema, cpa.stringValue(PropertyAttr.propertyID)).get(0));
		auditEdit.put(AuditAttr.auditID, audit.stringValue(AuditAttr.auditID));
		//#1 Check in audit list
		auditPg.verifyAuditExisted(audit, true);
		//#2 Check in audit details widget
		lm.gotoAuditDetailsWidgetFromAuditListPg(audit.stringValue(AuditAttr.auditID));
		auditDetailsPg.verifyAuditInfo(audit);
		lm.gotoAuditListPgFromAuditDetailsWidget();

		//Edit audit
		lm.editAudit(auditEdit);
		//#1 Check in audit list
		auditPg.verifyAuditExisted(audit, false);
		auditPg.verifyAuditExisted(auditEdit, true);
		//#2 Check in audit details widget
		lm.gotoAuditDetailsWidgetFromAuditListPg(auditEdit.stringValue(AuditAttr.auditID));
		auditDetailsPg.verifyAuditInfo(auditEdit);
		lm.gotoAuditListPgFromAuditDetailsWidget();

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
		cust.fName = "QA-Advanced03";
		cust.lName = "TEST-Advaced03";
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

		auditEdit = new Data<AuditAttr>();
		auditEdit.put(AuditAttr.auditStatus, "Inactive");
		auditEdit.put(AuditAttr.lName, cust.lName);
		auditEdit.put(AuditAttr.fName, cust.fName);
		auditEdit.put(AuditAttr.custID, cust.licenseNum);
		auditEdit.put(AuditAttr.cust, cust.lName+", "+cust.fName+"("+cust.licenseNum+")");
		auditEdit.put(AuditAttr.documentType, "Audit_doc2");
		auditEdit.put(AuditAttr.documentDate, DateFunctions.getDateAfterToday(1));
		auditEdit.put(AuditAttr.contactDate, DateFunctions.getDateAfterToday(2));
		auditEdit.put(AuditAttr.auditYear, String.valueOf(DateFunctions.getYearAfterCurrentYear(-1)));
		auditEdit.put(AuditAttr.auditComment, "Audit comment for edit audit");
		auditEdit.put(AuditAttr.creationApplication, audit.stringValue(AuditAttr.creationApplication));
		auditEdit.put(AuditAttr.creationDate, audit.stringValue(AuditAttr.creationDate));
		auditEdit.put(AuditAttr.creationUser, audit.stringValue(AuditAttr.creationUser));
	}
}
