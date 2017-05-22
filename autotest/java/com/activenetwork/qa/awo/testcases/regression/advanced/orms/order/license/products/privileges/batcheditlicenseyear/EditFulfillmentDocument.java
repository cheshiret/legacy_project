package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.batcheditlicenseyear;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrDocumentInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrEditPrintDocumentWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrBatchEditLicenseYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegePrintDocumentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC: Edit License Year for Privileges in Batch
 * @Task#: AUTO-726
 * 
 * @author SWang
 * @Date  Sep 1, 2011
 */
public class EditFulfillmentDocument extends LicenseManagerTestCase {
	private LicMgrBatchEditLicenseYearPage batchEditLicenseYearPg = LicMgrBatchEditLicenseYearPage.getInstance();
	private LicMgrPrivilegePrintDocumentsPage printDocSubTab = LicMgrPrivilegePrintDocumentsPage.getInstance();
	private LicMgrDocumentInfo editeDocument = new LicMgrDocumentInfo();
	private LicMgrDocumentInfo newDocument= new LicMgrDocumentInfo();
	private String expectPrintFrom, expectPrintTo;
	private String parkName = "";

	public void execute() {
		//clean up document
		lm.deleteAllProductDocFormDBForOneProduct(document.prodCode, document.prodType, parkName, schema);
		//login in 
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeDocumentPgFromTopMenu(privilege.code);

		lm.addPrintDocumentForProduct(document);
		document.id = printDocSubTab.getDocumentID(document);
		lm.gotoBatchEditLicenseYearPg();
		batchEditLicenseYearPg.gotoPrivilegeLicenseYearGridWithYearToEdit(document.licYearFrom);

		//Successfully edit when Print From and Print To Date are not empty
		expectPrintFrom = DateFunctions.getDateAfterToday(367);
		expectPrintTo = DateFunctions.getDateAfterToday(368);
		privilege.index= batchEditLicenseYearPg.selectPrivilegeProductCheckBox("", document.docTepl+" - "+document.equipType, true);
		this.getEditedAndNewerDocumentInfo();
		this.verifyEditedAndNewDocumentInfo();
		this.checkEditedFieldInBatchEditLicenseYearList();

		//Successfully edit when Print From and Print To Date are empty
		expectPrintFrom = " ";
		expectPrintTo = " ";
		privilege.index= batchEditLicenseYearPg.selectPrivilegeProductCheckBox("", document.docTepl+" - "+document.equipType, true);
		this.getEditedAndNewerDocumentInfo();
		this.verifyEditedAndNewDocumentInfo();
 		this.checkEditedFieldInBatchEditLicenseYearList();
 		
		//Logout
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		parkName=login.location.split("/")[1];
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		//Privilege information
		privilege.code = "c1D";
		privilege.name = "BatchEditLicenseYearTest3";
		privilege.status = "Active";
		privilege.customerClasses = new String[]{"Individual"};
		privilege.displayCategory = "bELYDisplayCategory2";
		privilege.displaySubCategory = "BELYDisplaySubCategory";
//		privilege.reportCategory = "rachel test";
		privilege.displayOrder = "0";
		privilege.licYear.licYear = String.valueOf(DateFunctions.getCurrentYear()+3);

		//Document information
		document.prodCode = privilege.code;
		document.prodType="privilege";
		document.status = "Active";
		document.docTepl="Harvest DOC";
		document.equipType="All";
		document.purchaseType = "Transfer";
		document.licYearFrom=privilege.licYear.licYear;
		document.licYearTo=privilege.licYear.licYear;
		document.printFromDate = DateFunctions.getDateAfterToday(2);
		document.printToDate = DateFunctions.getDateAfterToday(6);
		document.effectiveFromDate = DateFunctions.getDateAfterToday(1);
		document.effectiveFromDate = DateFunctions.getDateAfterToday(10);
		document.printOrd = "1";
		document.fulfillMethod="Fulfilled by Mail";
		document.species = "01 - Ducks";
		document.huntSeason = "02 - LicYearBatchAdd";
		document.harvestDocument = "Yes";
		document.configVariables = new String []{"auto1"};
}

	private void editDocumentPrintFromDate(){
		document.printFromDate = batchEditLicenseYearPg.getPrintFrom(privilege.index);
		document.printToDate = batchEditLicenseYearPg.getPrintTo(privilege.index);

		//Get Print From Date and Print To Date
		if(expectPrintFrom.trim().length()!=0 || expectPrintTo.trim().length()!=0){
			expectPrintFrom = DateFunctions.getDateAfterGivenDay(document.printFromDate, 1);
			expectPrintTo = DateFunctions.getDateAfterGivenDay(document.printToDate, 1);
		}
		//Edit Print From Date
		batchEditLicenseYearPg.setPrintFrom(expectPrintFrom, privilege.index);
		//Edit Print To Date
		batchEditLicenseYearPg.setPrintTo(expectPrintTo, privilege.index);
	}

	private void getEditedAndNewerDocumentInfo(){
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage
		.getInstance();
		LicMgrPrivilegePrintDocumentsPage printDocumentPg = LicMgrPrivilegePrintDocumentsPage.getInstance();

		this.editDocumentPrintFromDate();

		batchEditLicenseYearPg.clickOK();
		ajax.waitLoading();
		privilegeListPage.waitLoading();

		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeDocumentPgFromPrivilegeDetailsPg(privilege.code);

		//Edited document info
		lm.gotoEditPrintDocumentWidget(document.id);
		editeDocument = this.getDocumentInfo();

		//New document info
		document.id = printDocumentPg.getActiveIDViaGiveDocumentID(document);
		lm.gotoEditPrintDocumentWidget(document.id);
		newDocument = this.getDocumentInfo();
	}

	public LicMgrDocumentInfo getDocumentInfo(){
		LicMgrPrivilegePrintDocumentsPage printDocumentPg = LicMgrPrivilegePrintDocumentsPage.getInstance();
		LicMgrEditPrintDocumentWidget editWidget = LicMgrEditPrintDocumentWidget
		.getInstance();
		LicMgrDocumentInfo templeteDocument = new LicMgrDocumentInfo();
		templeteDocument.status = editWidget.getStatus();
		templeteDocument.printOrd = editWidget.getPrintOrder();
		templeteDocument.docTepl = editWidget.getDocTempl();
		templeteDocument.licYearFrom = editWidget.getLicenseYearFrom();
		templeteDocument.licYearTo = editWidget.getLicenseYearTo();
		templeteDocument.effectiveFromDate = editWidget.getEffectiveFromDate();
		templeteDocument.effectiveToDate = editWidget.getEffectiveToDate();
		templeteDocument.printFromDate = editWidget.getPrintFromDate();
		templeteDocument.printToDate = editWidget.getPrintToDate();
		templeteDocument.fulfillMethod = editWidget.getFulfillmentMethod();
		templeteDocument.equipType = editWidget.getEquipmentTyp();
		templeteDocument.purchaseType = editWidget.getPurcahseTyp();
		templeteDocument.species = editWidget.getSpecies();
		templeteDocument.huntSeason = editWidget.getHuntingSeason();
		templeteDocument.createUser = editWidget.getCreateUser();
		templeteDocument.createLoc = editWidget.getCreateLocation();
		templeteDocument.createDate = editWidget.getCreateDateTime();
		templeteDocument.lastUpdateUser = editWidget.getLastUpdateUser();
		templeteDocument.lastUpdateLoc = editWidget.getLastUpdatedLocation();
		templeteDocument.lastUpdateDate = editWidget.getLastUpdateDateTime();
		
		editWidget.clickCancel();
		printDocumentPg.waitLoading();
		return templeteDocument;
	}

	private void checkEditedFieldInBatchEditLicenseYearList(){
		LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage.getInstance();
		lm.gotoPrivilegeListPgFromGivePage(licenseYearPg);
		lm.gotoBatchEditLicenseYearPg();
		batchEditLicenseYearPg.gotoPrivilegeLicenseYearGridWithYearToEdit(privilege.licYear.licYear);
		
		System.out.println(batchEditLicenseYearPg.getPrintFrom(privilege.index).trim());
		System.out.println(batchEditLicenseYearPg.getPrintTo(privilege.index).trim());
		if(!batchEditLicenseYearPg.getPrintFrom(privilege.index).trim().equals(expectPrintFrom.trim()) 
				|| !batchEditLicenseYearPg.getPrintTo(privilege.index).trim().equals(expectPrintTo.trim())){
			throw new ErrorOnDataException("The Print From should be "+expectPrintFrom+" and Print To should be "+expectPrintTo);
		}
	}

	private void verifyEditedAndNewDocumentInfo(){
		//Status
		if(!newDocument.status.equals("Active")){
			throw new ErrorOnDataException("The status of newer should be "+newDocument.status);
		}
		if(!editeDocument.status.equals("Inactive")){
			throw new ErrorOnDataException("The status of edited should be "+editeDocument.status);
		}
		//Print Order
		if(!newDocument.printOrd.equals(editeDocument.printOrd)){
			throw new ErrorOnDataException("Print Order of newer and edited should be equal as "+editeDocument.printOrd);
		}
		//Document Template
		if(!newDocument.docTepl.equals(editeDocument.docTepl)){
			throw new ErrorOnDataException("Document Template of newer and edited should be equal as "+editeDocument.docTepl);
		}
		//License Year From
		if(!newDocument.licYearFrom.equals(editeDocument.licYearFrom)){
			throw new ErrorOnDataException("License Year From of newer and edited should be equal as "+editeDocument.licYearFrom);
		}
		//License Year To
		if(!newDocument.licYearTo.equals(editeDocument.licYearTo)){
			throw new ErrorOnDataException("License Year To of newer and edited should be equal as "+editeDocument.licYearTo);
		}
		//Effective From Date
		if(!newDocument.effectiveFromDate.equals(editeDocument.effectiveFromDate)){
			throw new ErrorOnDataException("Effective From Date of newer should be equal as "+editeDocument.effectiveFromDate);
		}
		//Effective To Date
		if(!newDocument.effectiveToDate.equals(editeDocument.effectiveToDate)){
			throw new ErrorOnDataException("Effective To Date of newer and edited should be equal as "+editeDocument.effectiveToDate);
		}
		//Print Form
		if(!editeDocument.printFromDate.equals(document.printFromDate)){
			throw new ErrorOnDataException("Print From Date of newer should be equal as "+document.printFromDate);
		}
		if(!newDocument.printFromDate.trim().equals(expectPrintFrom.trim())){
			throw new ErrorOnDataException("Print From Date of newer should be equal as "+expectPrintFrom);
		}
		//Print To
		if(!editeDocument.printToDate.equals(document.printToDate)){
			throw new ErrorOnDataException("Print To Date of newer should be equal as "+document.printToDate);
		}
		if(!newDocument.printToDate.trim().equals(expectPrintTo.trim())){
			throw new ErrorOnDataException("Print To Date of newer should be equal as "+expectPrintTo);
		}
		//Fulfillment Method
		if(!newDocument.fulfillMethod.equals(editeDocument.fulfillMethod)){
			throw new ErrorOnDataException("Fulfillment Methodof newer and edited should be equal as "+editeDocument.fulfillMethod);
		}
		//Equipment Type
		if(!newDocument.equipType.equals(editeDocument.equipType)){
			throw new ErrorOnDataException("Equipment Type of newer and edited should be equal as "+editeDocument.equipType);
		}
		//Purchase Type
		if(!newDocument.purchaseType.equals(editeDocument.purchaseType)){
			throw new ErrorOnDataException("Purchase Type of newer and edited should be equal as "+editeDocument.purchaseType);
		}
		//Species
		if(!newDocument.species.equals(editeDocument.species)){
			throw new ErrorOnDataException("Species of newer and edited should be equal as "+editeDocument.species);
		}
		//Season
		if(!newDocument.huntSeason.equals(editeDocument.huntSeason)){
			throw new ErrorOnDataException("Season of newer and edited should be equal as "+editeDocument.huntSeason);
		}
		//Create User
		if(!newDocument.createUser.equals(editeDocument.lastUpdateUser)){
			throw new ErrorOnDataException("Create User of newer should be equal to the Last Update User of edited as "+editeDocument.lastUpdateUser);
		}
		//Create Location
		if(!newDocument.createLoc.equals(editeDocument.lastUpdateLoc)){
			throw new ErrorOnDataException("Create Location of newer should be equal to the Last Update Location of edited as "+editeDocument.lastUpdateLoc);
		}
		//Create Time
		if(!newDocument.createDate.equals(editeDocument.lastUpdateDate)){
			throw new ErrorOnDataException("Create Time of newer should be equal to the Last Update Time of edited as "+editeDocument.lastUpdateDate);
		}
		//Last Update User
		if(!newDocument.lastUpdateUser.equals("")){
			throw new ErrorOnDataException("Last Update User of newer should be null.");
		}
		//Last Update Location
		if(!newDocument.lastUpdateLoc.equals("")){
			throw new ErrorOnDataException("Last Update Location of newer should be null.");
		}
		//Last Update Time
		if(!newDocument.lastUpdateDate.equals("")){
			throw new ErrorOnDataException("Last Update Time of newer should be null.");
		}
	}
}
