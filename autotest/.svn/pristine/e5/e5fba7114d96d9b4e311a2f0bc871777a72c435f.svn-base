package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrDocumentInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrEditPrintDocumentWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrBatchEditLicenseYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditLicenseYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegePrintDocumentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case is used to verify batch edit license year and document info.
 * @Preconditions:This case need an existing active privilege, privilege code = LY2, privilege name = BasicBatchEditLicenseYear;
 * so, this case need to run support script of add privilege
 * @SPEC:Edit License Year for Privileges in Batch.doc
 * @Task#:Auto-878

 * @author VZhang
 * @Date Feb 15, 2012
 */
public class BatchEditLicenseYear extends LicenseManagerTestCase{
	private LicenseYear oldlicenseYear = new LicenseYear();
	private LicenseYear newlicenseYear = new LicenseYear();
	private LicMgrDocumentInfo newDocument = new LicMgrDocumentInfo();
	private LicMgrBatchEditLicenseYearPage batchEditLicenseYearPg = LicMgrBatchEditLicenseYearPage.getInstance();
	private LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage.getInstance();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//prepare data
		this.prepareData();
		
		/*
		 * go to batch edit license year page, batch edit license year and document
		 */
		lm.gotoBatchEditLicenseYearPg();
		batchEditLicenseYearPg.gotoPrivilegeLicenseYearGridWithYearToEdit(oldlicenseYear.licYear);
		this.initialBatchEditInfo();
		this.editLicenseYearandDocument(newlicenseYear, newDocument);
		
		/**
		 * go to privilege license year page, verify license year info
		 */
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeLicenseYearPage();
		this.verifyLicenseYearInfo(oldlicenseYear, newlicenseYear);
		//clear up
		lm.deactivateAllPrivilegeLicenseYear();
		
		/**
		 * go to privilege document page, verify document info
		 */
		lm.gotoPrivilegePrintDocumentPage();
		this.verifyDocumentInfo(document, newDocument);
		//clear up
		lm.deactivateAllPrivilegeDocumentAssignment();
		
		lm.logOutLicenseManager();		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		privilege.code = "LY2";
		privilege.name = "BasicBatchEditLicenseYear";
		
		oldlicenseYear.locationClass = "All";
		oldlicenseYear.licYear = String.valueOf(DateFunctions.getCurrentYear()+5);
		oldlicenseYear.status = "Active";
		oldlicenseYear.sellFromDate = DateFunctions.getDateAfterToday(-2, DataBaseFunctions.getContractTimeZone(schema));
		oldlicenseYear.sellFromTime = "12:00";
		oldlicenseYear.sellFromAmPm = "PM";
		oldlicenseYear.sellToDate =  DateFunctions.getDateAfterToday(30, DataBaseFunctions.getContractTimeZone(schema));
		oldlicenseYear.sellToTime = "11:59";
		oldlicenseYear.sellToAmPm = "AM";
		oldlicenseYear.validFromAmPm = "AM";
		oldlicenseYear.validToAmPm = "AM";
		oldlicenseYear.productCode = privilege.code;
		oldlicenseYear.productName = privilege.name;
			
		document.prodCode = privilege.code;
		document.printOrd = "3";
		document.docTepl = "License Document";
		document.status = "Active";
		document.licYearFrom = oldlicenseYear.licYear;
		document.licYearTo = document.licYearFrom;
		document.effectiveFromDate = DateFunctions.getDateAfterToday(-4, DataBaseFunctions.getContractTimeZone(schema));
		document.printFromDate = DateFunctions.getDateAfterToday(-4, DataBaseFunctions.getContractTimeZone(schema));
		document.printToDate =  DateFunctions.getDateAfterToday(30, DataBaseFunctions.getContractTimeZone(schema));
		document.fulfillMethod = "Fulfilled by Mail";
		document.equipType = "All";
		document.purchaseType = "Transfer";
		document.harvestDocument = "No";
		
	}
	
	private void prepareData(){
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		if(!privilegeListPage.checkProductRecordExist(privilege.code)){
			throw new ErrorOnPageException("Did not found privilege which privilege code = " + privilege.code 
					+ ", please prepare privivlge.");
		}
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeLicenseYearPage();	
		//prepare license year; 
		lm.deactivateAllPrivilegeLicenseYear();
		oldlicenseYear.id = lm.addLicenseYear(oldlicenseYear).id;
		
		lm.gotoPrivilegePrintDocumentPage();
		//prepare print document
		lm.deactivateAllPrivilegeDocumentAssignment();		
		document.id = lm.addPrintDocumentForProduct(document);
	}
	
	private void initialBatchEditInfo(){
		newlicenseYear.licYear = oldlicenseYear.licYear;
		newlicenseYear.locationClass = oldlicenseYear.locationClass;
		newlicenseYear.status = "Active";
		newlicenseYear.sellFromDate = DateFunctions.getDateAfterGivenDay(oldlicenseYear.sellFromDate, 30);
		newlicenseYear.sellFromTime = "11:30";
		newlicenseYear.sellFromAmPm = "AM";
		newlicenseYear.sellToDate = DateFunctions.getDateAfterGivenDay(oldlicenseYear.sellToDate, 30);
		newlicenseYear.sellToTime = "11:50";
		newlicenseYear.sellToAmPm = "PM";
		newlicenseYear.validFromDate = newlicenseYear.sellFromDate;
		newlicenseYear.validFromTime = "12:00";
		newlicenseYear.validFromAmPm = "PM";
		newlicenseYear.validToDate = newlicenseYear.sellToDate;
		newlicenseYear.validToTime = "11:59";
		newlicenseYear.validToAmPm = "PM";
		newlicenseYear.productCode = oldlicenseYear.productCode;
		newlicenseYear.productName = oldlicenseYear.productName;
		newlicenseYear.createUser = DataBaseFunctions.getLoginUserName(login.userName);
		newlicenseYear.createLocation = login.location.split("/")[1];
		newlicenseYear.createTime = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		
		newDocument.status = "Active";
		newDocument.printOrd = document.printOrd;
		newDocument.docTepl = document.docTepl;
		newDocument.licYearFrom = newlicenseYear.licYear;
		newDocument.licYearTo = newDocument.licYearFrom;
		newDocument.effectiveFromDate = document.effectiveFromDate;
		newDocument.effectiveToDate = document.effectiveToDate;
		newDocument.printFromDate = DateFunctions.getDateAfterGivenDay(document.printFromDate, 30);
		newDocument.printToDate = DateFunctions.getDateAfterGivenDay(document.printToDate, 30);
		newDocument.fulfillMethod = document.fulfillMethod;
		newDocument.prodCode = newlicenseYear.productCode;
		newDocument.equipType = document.equipType;
		newDocument.purchaseType = document.purchaseType;
		newDocument.harvestDocument = document.harvestDocument;
		newDocument.createUser = DataBaseFunctions.getLoginUserName(login.userName);
		newDocument.createLoc= login.location.split("/")[1];
		newDocument.createDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
	}
	
	private void editLicenseYearandDocument(LicenseYear batchLicenseYear,LicMgrDocumentInfo batchDocument){
		logger.info("Batch Edit Prvilege License Year and Document info.");
		
		batchEditLicenseYearPg.setLicenseYearInfo(batchLicenseYear);
		batchEditLicenseYearPg.setDocumentInfo(batchDocument);
		batchEditLicenseYearPg.selectBatchEditedLicensYearCheckBox(batchLicenseYear);
		batchEditLicenseYearPg.selectBatchEditedDocumentCheckBox(batchDocument.prodCode, batchDocument.docTepl, batchDocument.equipType);
		batchEditLicenseYearPg.clickOK();
		ajax.waitLoading();
		privilegeListPage.waitLoading();		
	}
	
	private void verifyLicenseYearInfo(LicenseYear oldLicenseYear,LicenseYear newLicenseYear){
		LicMgrPrivilegeLicenseYearPage licenseYearPage = LicMgrPrivilegeLicenseYearPage.getInstance();
		LicMgrPrivilegeEditLicenseYearWidget editWidget = LicMgrPrivilegeEditLicenseYearWidget.getInstance();
		boolean result = true;
		logger.info("Verify License year info.");
		
		newLicenseYear.id = licenseYearPage.getLicenseYearId(newLicenseYear.status, newLicenseYear.locationClass, newLicenseYear.licYear);

		if(newLicenseYear.id.equals(oldLicenseYear.id)){
			result &= false;
			logger.error("Should create new license year info after edit license year " + newLicenseYear.id);
		}else {
			logger.info("Create new license year info after edit license year " + newLicenseYear.id);
		}
		
	
		//go to license year detail page, verify license year info
		licenseYearPage.clickLicenseYear(newLicenseYear.id);
		ajax.waitLoading();
		editWidget.waitLoading();
		result &= editWidget.compareLicenseYearRecord(newLicenseYear);
		editWidget.clickCancel();
		ajax.waitLoading();
		licenseYearPage.waitLoading();
		
		if(!result){
			throw new ErrorOnPageException("License year info is not correct. please check error log.");
		}else {
			logger.info("License year info is correct in licnese year detail page.");
		}		
	}
	
	private void verifyDocumentInfo(LicMgrDocumentInfo oldDocument,LicMgrDocumentInfo newDocument){
		LicMgrPrivilegePrintDocumentsPage printDocSubTab = LicMgrPrivilegePrintDocumentsPage.getInstance();	
		LicMgrEditPrintDocumentWidget editWidget = LicMgrEditPrintDocumentWidget.getInstance();
		boolean result = true;
		logger.info("Verify document info.");
		
		newDocument.id = printDocSubTab.getDocumentID(newDocument);
		if(newDocument.id.equals(oldDocument.id)){
			throw new ErrorOnPageException("Should create new doucment info after edit doucment " + newDocument.id);
		}else {
			logger.info("Create new document info " + newDocument.id);
		}
		
		//go to document detail page verify document info 
		printDocSubTab.clickPrintDocumentID(newDocument.id );
		ajax.waitLoading();
		editWidget.waitLoading();
		result &= editWidget.commpareDocumentInfo(newDocument);
		editWidget.clickCancel();
		ajax.waitLoading();
		printDocSubTab.waitLoading();
		if(!result){
			throw new ErrorOnPageException("Document info is not correct in document detail page. Please check error log.");
		}else {
			logger.info("Document info is correct in document detial page.");
		}	
	}

}
