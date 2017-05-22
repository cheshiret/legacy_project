package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrDocumentInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrEditPrintDocumentWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditLicenseYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearBatchAddPage;
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
 * @Description:This case is used to batch add license year and document for privilege.
 * @Preconditions: This case need an existing active privilege, privilege code = LY1, privilege name = BasicBatchAddLicenseYear;
 * so, this case need to run support script of add privilege
 * Blocked by DEFECT-33322 
 * @SPEC: Add License Year for Privileges in Batch.doc
 * @Task#: Auto-878

 * @author VZhang
 * @Date Feb 10, 2012
 */
public class BatchAddLicenseYear extends LicenseManagerTestCase{
	private LicenseYear oldlicenseYear = new LicenseYear();
	private LicenseYear newlicenseYear = new LicenseYear();
	private LicMgrDocumentInfo newDocument = new LicMgrDocumentInfo();
	private LicMgrPrivilegeLicenseYearBatchAddPage priLicYearBatchAddPg = LicMgrPrivilegeLicenseYearBatchAddPage.getInstance();
	private LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage.getInstance();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		this.prepareData();
		
		/*
		 * go to privilege batch add license year page; 
		 * verify license year and print document info populated data on the page.
		 */
		lm.gotoPrivilegeLicenseYearBatchAddPageFromTopMenu();
		oldlicenseYear.copyFromYear = oldlicenseYear.licYear;
		oldlicenseYear.newLicYear = this.getNewLicenseYear(schema);
		oldlicenseYear.numOfYearToAdd = "2";
		//search could batch added privilege license year info
		lm.searchPrivilegeLicenseYearOnBatchAddPg(oldlicenseYear);	
		
		/*
		 * Batch add license year and document info
		 */
		this.initialBatchAddInfo();
		this.batchAddLicenseYearandDocument(newlicenseYear, newDocument);
		
		/*
		 * go to privilege license year page, verify license year info
		 */
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeLicenseYearPage();
		this.verifyLicenseYearInfo(oldlicenseYear,newlicenseYear);
		lm.deactivateAllPrivilegeLicenseYear();
		
		/*
		 * go to privilege print document page, verify print document info
		 */
		lm.gotoPrivilegePrintDocumentPage();
		this.verifyPrintDocumentInfo(document, newDocument);		
		lm.deactivateAllPrivilegeDocumentAssignment();
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		privilege.code = "LY1";
		privilege.name = "BasicBatchAddLicenseYear";
		
		oldlicenseYear.locationClass = "All";
		oldlicenseYear.licYear = String.valueOf(DateFunctions.getCurrentYear() + 4);
		oldlicenseYear.status = "Active";
		oldlicenseYear.sellFromDate = DateFunctions.getDateAfterToday(-2, DataBaseFunctions.getContractTimeZone(schema));
		oldlicenseYear.sellFromTime = "12:00";
		oldlicenseYear.sellFromAmPm = "PM";
		oldlicenseYear.sellToDate =  DateFunctions.getDateAfterToday(30, DataBaseFunctions.getContractTimeZone(schema));
		oldlicenseYear.sellToTime = "11:59";
		oldlicenseYear.sellToAmPm = "AM";
		// must setup valid from/to date time!!!!!
		oldlicenseYear.validFromDate = oldlicenseYear.sellFromDate;
		oldlicenseYear.validFromTime = oldlicenseYear.sellFromTime;
		oldlicenseYear.validFromAmPm = oldlicenseYear.sellFromAmPm;
		oldlicenseYear.validToDate = oldlicenseYear.sellToDate;
		oldlicenseYear.validToTime = oldlicenseYear.sellToTime;
		oldlicenseYear.validToAmPm = oldlicenseYear.sellToAmPm;
		oldlicenseYear.productCode = privilege.code;
		oldlicenseYear.productName = privilege.name;
		
		document.prodType = "Privilege";
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
	
	private String getNewLicenseYear(String schema){
		String newLicenseYear = "";
		logger.info("Get new license year.");
		
		List<String> licenseYears = lm.getExistingLicenseYear(schema);
		if(licenseYears != null && licenseYears.size()>0){
			//add one year based on the info get from db
			newLicenseYear = String.valueOf(Integer.valueOf(licenseYears.get(0)) + 1);
		}
		
		return newLicenseYear;		
	}

	private void initialBatchAddInfo(){
		newlicenseYear.licYear = oldlicenseYear.newLicYear;
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
		newlicenseYear.validToTime = "11:58";
		newlicenseYear.validToAmPm = "PM";
		newlicenseYear.productCode = oldlicenseYear.productCode;
		newlicenseYear.productName = oldlicenseYear.productName;
		newlicenseYear.createUser = DataBaseFunctions.getLoginUserName(login.userName);
		newlicenseYear.createLocation = login.location.split("/")[1];
		newlicenseYear.createTime = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		
		newDocument.status = "Active";
		newDocument.prodType = document.prodType;
		newDocument.printOrd = document.printOrd;
		newDocument.docTepl = document.docTepl;
		newDocument.licYearFrom = newlicenseYear.licYear;
		newDocument.licYearTo = newDocument.licYearFrom;
		newDocument.printFromDate = DateFunctions.getDateAfterGivenDay(document.printFromDate, 30);
		newDocument.printToDate = DateFunctions.getDateAfterGivenDay(document.printToDate, 30);
		newDocument.effectiveFromDate = document.effectiveFromDate;
		newDocument.effectiveToDate = document.effectiveToDate;
		newDocument.fulfillMethod = document.fulfillMethod;
		newDocument.prodCode = newlicenseYear.productCode;
		newDocument.equipType = document.equipType;
		newDocument.purchaseType = document.purchaseType;
		newDocument.harvestDocument = document.harvestDocument;
		newDocument.createUser = DataBaseFunctions.getLoginUserName(login.userName);
		newDocument.createLoc= login.location.split("/")[1];
		newDocument.createDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
	}
	
	private void batchAddLicenseYearandDocument(LicenseYear batchLicenseYear,LicMgrDocumentInfo batchDocument){
		logger.info("Batch Add Prvilege License Year and Document info.");
		
		priLicYearBatchAddPg.editLicenseYearInfo(batchLicenseYear);
		priLicYearBatchAddPg.editDocumentInfo(batchDocument);
		priLicYearBatchAddPg.selectBatchAddedLicensYearCheckBox(batchLicenseYear);
		priLicYearBatchAddPg.selectBatchAddedDocumentCheckBox(batchDocument.prodCode, batchDocument.docTepl, batchDocument.equipType);
		priLicYearBatchAddPg.clickOKButton();
		ajax.waitLoading();
		privilegeListPage.waitLoading();
	}
	
	private void verifyLicenseYearInfo(LicenseYear oldLicenseYear,LicenseYear newLicenseYear){
		LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage.getInstance();
		LicMgrPrivilegeEditLicenseYearWidget editWidget = LicMgrPrivilegeEditLicenseYearWidget.getInstance();
		boolean result = true;
		
		logger.info("Verify License Year info from license year list page.");
		result &= licenseYearPg.verifyLicenseYearExist(oldLicenseYear);
		if(!result){
			throw new ErrorOnPageException("Expect old license year is existing, but actually is not existing.");
		}else {
			logger.info("Old license year is existing.");
		}
		
		//go to license year detail page, verify license year info
		newLicenseYear.id = licenseYearPg.getLicenseYearId(newLicenseYear.status, newLicenseYear.locationClass, newLicenseYear.licYear);
		licenseYearPg.clickLicenseYear(newLicenseYear.id);
		ajax.waitLoading();
		editWidget.waitLoading();
		result &= editWidget.compareLicenseYearRecord(newLicenseYear);
		editWidget.clickCancel();
		ajax.waitLoading();
		licenseYearPg.waitLoading();
		
		if(!result){
			throw new ErrorOnPageException("License year info is not correct. please check error log.");
		}else {
			logger.info("License year info is correct in licnese year detail page.");
		}		
	}
	
	private void verifyPrintDocumentInfo(LicMgrDocumentInfo oldDocument,LicMgrDocumentInfo newDocument){
		LicMgrPrivilegePrintDocumentsPage printDocSubTab = LicMgrPrivilegePrintDocumentsPage.getInstance();	
		LicMgrEditPrintDocumentWidget editWidget = LicMgrEditPrintDocumentWidget.getInstance();
		boolean result = true;
		logger.info("Verify print document info from print document list page.");
		
		result = printDocSubTab.verifyDocumentExist(oldDocument);
		if(!result){
			throw new ErrorOnPageException("Expect old print document info is existing, but actually is not existing.");
		}else {
			logger.info("Old document is existing.");
		}
		
		//go to document detail page verify document info 
		newDocument.id = printDocSubTab.getDocumentID(newDocument);
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
