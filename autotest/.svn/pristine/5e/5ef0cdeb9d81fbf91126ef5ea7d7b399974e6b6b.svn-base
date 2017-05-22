package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.batcheditlicenseyear;

import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrBatchEditLicenseYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegePrintDocumentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:
 * The System shall consider a Privilege Product as a candidate for the Fulfillment Document editing in batch if the following conditions are true:
 * 1. The Privilege Product has a Status of "Active"; and
 * 2. The Privilege Product has Revenue Location in the same Chart of Accounts as the known logged-in Location; and
 * 3. At least one Privilege Fulfillment Document record associated with the Privilege Product exists with a Status of "Active" 
 * and with License Year equal to the specified License Year to be edited.
 * @Preconditions:
 * @SPEC: Edit License Year for Privileges in Batch
 * @Task#: AUTO-726
 * 
 * @author SWang
 * @Date  Aug 19, 2011
 */

public class CandidateFulfillmentDocument extends LicenseManagerTestCase{
	private LicMgrBatchEditLicenseYearPage batchEditLicenseYearPg = LicMgrBatchEditLicenseYearPage.getInstance();
	private String parkName;

	public void execute() {
		//clean up
		lm.deleteAllProductDocFormDBForOneProduct(document.prodCode, document.prodType, parkName, schema);
				
		//login in 
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeSearchListPageFromTopMenu();

		//Add privilege and associated fulfillment document with login location
		this.checkPrivilegesAndAdd();
		lm.activateOrInactivatePrivilege(privilege.code, "Activate");
		this.checkAndAddFulfillmentDocuments();

		//Check the active privilege with active fulfillment document displays in Batch edit license year list
		this.checkProductFulfillmentDocumentExist(true);

		//Check fulfillment document doesn't display with active privilege and associated inactive fulfillment document
		lm.gotoPrivilegeListPgFromGivePage(batchEditLicenseYearPg);
		lm.activateOrInactivateTheDocument(document, parkName, schema, false);
		this.checkProductFulfillmentDocumentExist(false);

		//Check fulfillment document doesn't display with inactive privilege and associated inactive fulfillment document
		lm.gotoPrivilegeListPgFromGivePage(batchEditLicenseYearPg);
		lm.activateOrInactivatePrivilege(privilege.code, "Inactivate");
		this.checkProductFulfillmentDocumentExist(false);

		//Check fulfillment document doesn't display with inactive privilege and associated active fulfillment document
		lm.gotoPrivilegeListPgFromGivePage(batchEditLicenseYearPg);
		lm.activateOrInactivateTheDocument(document, parkName, schema, true);
		this.checkProductFulfillmentDocumentExist(false);

		//Activate privilege
		lm.gotoPrivilegeListPgFromGivePage(batchEditLicenseYearPg);
		lm.activateOrInactivatePrivilege(privilege.code, "activate");

		//Log out 
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		parkName=login.location.split("/")[1];
		schema=TestProperty.getProperty(env+".db.schema.prefix")+"MS";

		//Privilege information
		privilege.code = "e1F";
		privilege.name = "BatchEditLicenseYearTest5";
		privilege.status = "Active";
		privilege.customerClasses = new String[]{"Individual"};
		privilege.displayCategory = "bELYDisplayCategory1";
		privilege.displaySubCategory = "BELYDisplaySubCategory";
//		privilege.reportCategory = "rachel test";
		privilege.displayOrder = "0";

		//Document information
		document.status = "Active";
		document.prodType="privilege";
		document.prodCode = privilege.code;
		document.printOrd = "1";
		document.docTepl="Harvest DOC";
		document.licYearFrom=String.valueOf(DateFunctions.getCurrentYear());
		document.effectiveFromDate= DateFunctions.getDateAfterToday(3);
		document.fulfillMethod="Fulfilled by Mail";
		document.equipType="All";
		document.purchaseTypes=new String[]{"Transfer"};
		document.species = "01 - Ducks";
		document.huntSeason = "02 - LicYearBatchAdd";
		document.configVariables = new String[]{"auto"};
	}

	private void checkPrivilegesAndAdd(){
		LicMgrPrivilegesListPage privilegeListPg = LicMgrPrivilegesListPage.getInstance();
		if(!privilegeListPg.verifyPrivilegeProductExist(privilege)){
			lm.addPrivilegeProduct(privilege);
		}
	}

	private void checkAndAddFulfillmentDocuments(){
		LicMgrPrivilegePrintDocumentsPage printDocSubTab = LicMgrPrivilegePrintDocumentsPage
		.getInstance();
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeDocumentPgFromPrivilegeDetailsPg(privilege.code);
		if(!printDocSubTab.verifyDocumentExist(document)){
			document.id = lm.addPrintDocumentForProduct(document);
		}
		lm.gotoPrivilegeListPgFromGivePage(printDocSubTab);
	}

	private void checkProductFulfillmentDocumentExist(boolean existed){
		lm.gotoBatchEditLicenseYearPg();
		batchEditLicenseYearPg.gotoPrivilegeLicenseYearGridWithYearToEdit(document.licYearFrom);
		batchEditLicenseYearPg.checkPrivilegeProductRecordExist(document.licYearFrom, privilege.displayCategory,
				document.docTepl+" - "+document.equipType, existed);
	}
}
