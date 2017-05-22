package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.batcheditlicenseyear;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrBatchEditLicenseYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegePrintDocumentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description:
 * 1. Year to Edit should be sorted in descending order
 * 2. Verify error message for Print From: left blank, valid date & time.
 * 3. Verify error message for Print To: left blank, valid date & time.
 * 4. Verify error message when Print From Date is greater than Print To Date
 * 5. verify no fields are edited after click Cancel button
 * @Preconditions: 1.At least has one privilege fulfillment document record
 * 2.license year from and license to must be set up, so the text field of print from&to date can be display in the widget.
 * 3.print from&to date has been set up.
 * @SPEC: Edit License Year for Privileges in Batch
 * @Task#: AUTO-726
 * 
 * @author SWang
 * @Date  Aug 25, 2011
 */
public class ValidateDocumentFields extends LicenseManagerTestCase{
	private LicMgrBatchEditLicenseYearPage batchEditLicenseYearPg = LicMgrBatchEditLicenseYearPage.getInstance();
	private String[] invalidDates = new String[] { "VerifyDate", "1/0/2015"};
	private String str0, str1, str2, newerPrintFromDate, editedPrintFromDate;
	private String parkName="";

	public void execute() {
		//clean up
		lm.deleteAllProductDocFormDBForOneProduct(privilege.document.prodCode, privilege.document.prodType, parkName, schema);
				
		//login in 
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		
		//Add privilege and associated fulfillment document with login location
		this.checkPrivilegesAndAdd();
		this.checkAndAddFulfillmentDocuments();
		
		//Year to Edit should be sorted in descending order
		this.checkYearToEditSort();
		batchEditLicenseYearPg.gotoPrivilegeLicenseYearGridWithYearToEdit(privilege.document.licYearFrom);
		this.initializeErrorMsg();
		privilege.index = batchEditLicenseYearPg.selectPrivilegeProductCheckBox("", privilege.document.docTepl+" - "+privilege.document.equipType, true);
		editedPrintFromDate = batchEditLicenseYearPg.getPrintFrom(privilege.index);

		//Check error message for privilege fulfillment document record
		//Print From Date
		privilege.document.printFromDate = " ";
		this.checkErrorMsgForPrivilegeLicenseYearFields(str0);
		batchEditLicenseYearPg.verifyInvalidPrintFromDate(invalidDates, privilege.index);

		//Print To Time
		privilege.document.printFromDate=DateFunctions.getDateAfterToday(5);
		privilege.document.printToDate = " ";
		this.checkErrorMsgForPrivilegeLicenseYearFields(str1);
		batchEditLicenseYearPg.verifyInvalidPrintToDate(invalidDates, privilege.index);

		//Print From Date is greater than Print To Date
		privilege.document.printToDate=DateFunctions.getDateAfterToday(2);
		this.checkErrorMsgForPrivilegeLicenseYearFields(str2);

		//Verify Cancel Batch Edit Document
		this.verifyCancelEditDocument();

		//Log out
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		parkName=login.location.split("/")[1];
		schema=TestProperty.getProperty(env+".db.schema.prefix")+"MS";
		
		//Privilege information
		privilege.code = "d1D";
		privilege.name = "BatchEditLicenseYearTest7";
		privilege.status = "Active";
		privilege.customerClasses = new String[]{"Individual"};
		privilege.displayCategory = "bELYDisplayCategory2";
		privilege.displaySubCategory = "BELYDisplaySubCategory";
//		privilege.reportCategory = "rachel test";
		privilege.displayOrder = "0";
		
		//Document information
		privilege.document.prodCode = privilege.code;
		privilege.document.prodType="privilege";
		privilege.document.docTepl = "BatchEditLicenseYearDOC2";
		privilege.document.equipType = "All";
		privilege.document.printOrd = "1";
		privilege.document.licYearFrom = String.valueOf(DateFunctions.getCurrentYear()+3);	
		privilege.document.printFromDate=DateFunctions.getDateAfterToday(3);
		privilege.document.printToDate=DateFunctions.getDateAfterToday(10);
		privilege.document.fulfillMethod="Fulfilled by Mail";
		privilege.document.equipType="All";
		privilege.document.purchaseTypes=new String[]{"Transfer"};
//		privilege.document.species = "1 - Ducks";
//		privilege.document.huntSeason = "01 - Alex_Test_01";
		privilege.document.effectiveFromDate =  DateFunctions.getDateAfterToday(2);
		privilege.document.effectiveToDate = DateFunctions.getDateAfterToday(100);
		privilege.document.configVariables = new String[]{"auto test"};
	}

	private void checkYearToEditSort(){
		LicMgrPrivilegePrintDocumentsPage printDocSubTab = LicMgrPrivilegePrintDocumentsPage.getInstance();
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage.getInstance();
		lm.gotoPrivilegeListPgFromGivePage(printDocSubTab);
		privilegeListPage.clickBatchEditLicenseYearButton();
		batchEditLicenseYearPg.waitLoading();
		List<String> YearsToEdit = batchEditLicenseYearPg.getYearsToEdit();
		for(int i=0; i+1<YearsToEdit.size(); i++){
			if(YearsToEdit.get(i).compareTo(YearsToEdit.get(i+1))<=0){
				throw new ErrorOnDataException("Year To Edit should be sorted in descending order.");
			}
		}
	}

	private void checkErrorMsgForPrivilegeLicenseYearFields(String expectErrorMsg){
		LicMgrPrivilegesListPage privilegeListPg = LicMgrPrivilegesListPage.getInstance();
		batchEditLicenseYearPg.setBatchEditLicenseYearFields(privilege);
		batchEditLicenseYearPg.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(batchEditLicenseYearPg, privilegeListPg);
		if(page == batchEditLicenseYearPg){
			batchEditLicenseYearPg.verifyErrorMsg(expectErrorMsg);
		} else {
			throw new ErrorOnPageException("It should display error messge("+expectErrorMsg+") in batch edit license year page.");
		}
	}

	private void verifyCancelEditDocument(){
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage.getInstance();
		batchEditLicenseYearPg.clickGo();
		ajax.waitLoading();
		editedPrintFromDate = batchEditLicenseYearPg.getPrintFrom(privilege.index);
		newerPrintFromDate = DateFunctions.getDateAfterToday(2);
		batchEditLicenseYearPg.setPrintFrom(newerPrintFromDate, privilege.index);
		
		//Cancel editing Document
		batchEditLicenseYearPg.clickCancel();
		ajax.waitLoading();
		privilegeListPage.waitLoading();
		lm.gotoBatchEditLicenseYearPg();
		batchEditLicenseYearPg.gotoPrivilegeLicenseYearGridWithYearToEdit(String.valueOf(DateFunctions.getCurrentYear()+3));

		//Verify No field be edited
		String actualPrintFromDate = batchEditLicenseYearPg.getPrintFrom(privilege.index);
		if(!actualPrintFromDate.equals(editedPrintFromDate) || actualPrintFromDate.equals(newerPrintFromDate)){
			throw new ErrorOnDataException("Privilege document Print From date should not be changed after canceling.");
		}
	}
	
	private void checkPrivilegesAndAdd(){
		LicMgrPrivilegesListPage privilegeListPg = LicMgrPrivilegesListPage.getInstance();
		if(!privilegeListPg.verifyPrivilegeProductExist(privilege)){
			lm.addPrivilegeProduct(privilege);
		}
		String status = privilegeListPg.getPrivilegeListInfoByColumnName(privilege.code, "Privilege Product Status");
		if(status.equalsIgnoreCase("Inactive")){
			logger.info("Privilege "+privilege.code+" was Inactive now.");
			lm.activateOrInactivatePrivilege(privilege.code, "Activate");
		}
	}
	
	private void checkAndAddFulfillmentDocuments(){
		LicMgrPrivilegePrintDocumentsPage printDocSubTab = LicMgrPrivilegePrintDocumentsPage
		.getInstance();
		lm.gotoPrivilegeDocumentPgFromTopMenu(privilege.code);
		lm.gotoPrivilegeDocumentPgFromPrivilegeDetailsPg(privilege.code);
		if(!printDocSubTab.verifyDocumentExist(privilege.document)){
			document.id = lm.addPrintDocumentForProduct(privilege.document);
		}else{
			throw new ErrorOnPageException("There should no active documents for Privilege:"+privilege.code);
		}
	}

	private void initializeErrorMsg(){
		str0 = privilege.code+" - "+privilege.name+": The Print From Date is required. Please enter the Print From Date using the format Ddd Mmm dd yyyy.";
		str1 = privilege.code+" - "+privilege.name+": The Print To Date is required. Please enter the Print To Date using the format Ddd Mmm dd yyyy.";
		str2 = privilege.code+" - "+privilege.name+": The Print From Date must not be later than the Print To Date. Please change your entries.";
	}
}
