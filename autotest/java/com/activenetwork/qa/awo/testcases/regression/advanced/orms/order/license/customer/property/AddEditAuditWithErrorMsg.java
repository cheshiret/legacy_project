package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.property;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.cust.AuditAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.cust.PropertyAttr;
import com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property.LicMgrAuditDetailsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property.LicMgrPropertyListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description: (P) Verify initial UI during add audit, and error message during add and edit audit
 * @Preconditions: 
 * SetupTypeOfOwnership.sql
 * SetupAuditDocumentType.sql
 * @LinkSetUp:
 * d_assign_feature:id=5022 --MO Admin - Auto, %Property%
 * d_hf_add_cust_profile:id=2630 --QA-Advanced07, TEST-Advaced07
 * @SPEC:
 * Add Property Audit [TC:070266] 
 * Edit Property Audit [TC:070272] 
 * @Task#:AUTO-2042
 * 
 * @author SWang
 * @Date  Jan 21, 2014
 */
public class AddEditAuditWithErrorMsg extends LicenseManagerTestCase {
	private LicMgrPropertyListPage custPropertyListPg = LicMgrPropertyListPage.getInstance();
	private LicMgrAuditDetailsWidget auditDetailsPg = LicMgrAuditDetailsWidget.getInstance();
	private Data<PropertyAttr> cpa;
	private Data<AuditAttr> audit, auditInitial;
	private String msg1, msg2, msg3, testData;

	public void execute() {
		//Go to property list page
		lm.loginLicenseManager(login);
		lm.gotoPropertyPgFromTopHomePg(cust);

		//Add property is necessary
		int numOfProperty = custPropertyListPg.getNumOfProperty();
		if(numOfProperty<1){
			cpa.put(PropertyAttr.propertyID, lm.addCustProperty(cpa));
		}else cpa.put(PropertyAttr.propertyID, custPropertyListPg.getFirstPropertyID());
		auditInitial.put(AuditAttr.propertyID, cpa.stringValue(PropertyAttr.propertyID));

		//Go to add audit page to check
		lm.gotoAuditDetailsWidgetFromPropertyPg(cpa.stringValue(PropertyAttr.propertyID));

		//#1 Initial UI
		auditDetailsPg.verifyAuditInfo(auditInitial, false);
		//#2 Error message
		verifyErrorMsg(true);
		lm.gotoAuditListPgFromAuditDetailsWidget();

		//Add audit
		List<String> auditsFromDB = lm.getAuditsFromDB(schema, cpa.stringValue(PropertyAttr.propertyID));
		if(auditsFromDB.size()>0){
			audit.put(AuditAttr.auditID, auditsFromDB.get(0));
		}else{
			lm.addAudit(audit);
			audit.put(AuditAttr.auditID, lm.getAuditsFromDB(schema, cpa.stringValue(PropertyAttr.propertyID)).get(0));
		}

		//Check error messages during edit audit
		lm.gotoAuditDetailsWidgetFromAuditListPg(audit.stringValue(AuditAttr.auditID));
		verifyErrorMsg(false);
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
		cust.customerClass = "Individual";
		cust.fName = "QA-Advanced07";
		cust.lName = "TEST-Advaced07";
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
		auditInitial = new Data<AuditAttr>();
		auditInitial.put(AuditAttr.auditID, "NEW");
		auditInitial.put(AuditAttr.auditStatus, "Active");
		auditInitial.put(AuditAttr.cust, StringUtil.EMPTY);
		auditInitial.put(AuditAttr.documentType, StringUtil.EMPTY);
		auditInitial.put(AuditAttr.documentDate, StringUtil.EMPTY);
		auditInitial.put(AuditAttr.contactDate,StringUtil.EMPTY);
		auditInitial.put(AuditAttr.auditYear, String.valueOf(DateFunctions.getCurrentYear()));
		auditInitial.put(AuditAttr.auditComment, StringUtil.EMPTY);

		audit = new Data<AuditAttr>();
		audit.put(AuditAttr.cust, cust.lName+", "+cust.fName+"("+cust.licenseNum+")");
		audit.put(AuditAttr.documentType, "Audit_doc1");
		audit.put(AuditAttr.documentDate, DateFunctions.getToday());
		audit.put(AuditAttr.contactDate, DateFunctions.getToday());
		audit.put(AuditAttr.auditYear, auditInitial.stringValue(AuditAttr.auditYear));
		audit.put(AuditAttr.auditComment, "Audit comment for AddEditAuditWithErrorMsg");

		testData = "12345";
		msg1 = "Customer is required for the Audit Details. Please specify the Customer.";
		msg2 = "Document Type is required for the Audit Details. Please specify the Document Type.";
		msg3 = "Invalid date. The date format should be: YYYYMMDD";
	}

	private boolean checkErrorMsg(String attriName,String errorMsg, boolean withPopupDialog) {
		LicMgrAuditDetailsWidget auditDetailsPg = LicMgrAuditDetailsWidget.getInstance();
		ConfirmDialogPage confirmPage = ConfirmDialogPage.getInstance();
		String errorMesFromUI = StringUtil.EMPTY;

		if(withPopupDialog){
			confirmPage.setDismissible(false);
			confirmPage.setBeforePageLoading(false);
			confirmPage.waitLoading();
			errorMesFromUI = confirmPage.text();
			confirmPage.clickOK();
		}else{
			auditDetailsPg.clickOK();
			ajax.waitLoading();
			errorMesFromUI = auditDetailsPg.getErrorMsg();
		}

		return MiscFunctions.compareResult("ErrorMsg about " + attriName, errorMsg, errorMesFromUI);
	}

	private void verifyErrorMsg(boolean duringAdd) {
		LicMgrAuditDetailsWidget auditDetailsPg = LicMgrAuditDetailsWidget.getInstance();

		//Customer
		boolean result = true;

		if(duringAdd){
			result &= checkErrorMsg("Customer has not been specified", msg1, false);
		}

		//Document Type
		if(duringAdd){
			auditDetailsPg.selectCustomer(audit.stringValue(AuditAttr.cust));
		}else auditDetailsPg.selectDocumentType(0);
		result &= checkErrorMsg("Document type has not been specified", msg2, false);

		//Document date
		auditDetailsPg.selectDocumentType(audit.stringValue(AuditAttr.documentType));
		auditDetailsPg.setDocumentDate(testData);
		result &= checkErrorMsg("Document Date has invalid format", msg3, true);

		//Contact date
		auditDetailsPg.setDocumentDate(audit.stringValue(AuditAttr.contactDate));
		auditDetailsPg.setContactDate(testData);
		result &= checkErrorMsg("Contact Date has invalid format", msg3, true);

		if (!result) {
			throw new ErrorOnPageException("Not all error message are passed in Audit Details page. Please check the details from previous logs.");
		}
		logger.info("All error message are passed in Audit Details page.");
	}
}
