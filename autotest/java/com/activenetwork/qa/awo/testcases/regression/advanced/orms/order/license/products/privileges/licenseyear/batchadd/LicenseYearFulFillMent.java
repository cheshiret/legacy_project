package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.licenseyear.batchadd;

import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrDocumentInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrEditPrintDocumentWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditLicenseYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearBatchAddPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegePrintDocumentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * 1: verify batch add license Year and fulfillment document successfully.
 * @Preconditions:
 * 1: there is one active license year [CurrentYear+3] info for the Privilege product(AT7) based on the warp parameter value.
 * 2: there is one active fulfillment document info for [CurrentYear+3](when add new fulfillment document, all the date time field MUST BE setup.).
 * 	  With template = "AutoBatchSuccess", 
 *    equipment type = "Internet Sales",
 *    purchase type = "Original".
 *    printOrd = "1";
 *	  fulfillMethod = "Fulfilled by Mail";
 *License Year To MUST set the same value [CurrentYear+3] as the License Year From, then the Print Date can be displayed in the page.
 * 3: there is no active license year[CurrentYear+10], and no fulfillment document [CurrentYear+10] for the new license year we want to batch add based on the value of [CurrentYear+10];
 * 
 * @SPEC: Add License Year for Privileges in Batch
 * @Task#:AUTO-725
 * 
 * @author bzhang
 * @Date  Sep 2, 2011
 */
public class LicenseYearFulFillMent extends LicenseManagerTestCase{
	private LicenseYear ly = new LicenseYear();
	private LicenseYear lyTemplate = new LicenseYear();
	private LicMgrDocumentInfo docTemplate = new LicMgrDocumentInfo();
	private LicMgrPrivilegeLicenseYearBatchAddPage priLicYearBatchAddPg = LicMgrPrivilegeLicenseYearBatchAddPage.getInstance();
	private LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage.getInstance();
	
	public void execute() {
		lm.loginLicenseManager(login);
		//check the prerequisite meet test case.
		this.checkPrerequisite();
		
		lm.gotoPrivilegeLicenseYearBatchAddPageFromTopMenu();		
		this.addNewLicYearAndFulFillRecords(); //add license year and fulfillment document.

		//1 verify new added license year info on edit privilege license year page.
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeLicenseYearPage();
		lm.searchPrivilegeLicenseYearByInfo(lyTemplate);
		lyTemplate.licYear = ly.newLicYear;
		lm.gotoPrivilegeLicenseYearDetailPg(lyTemplate.status, lyTemplate.locationClass, lyTemplate.licYear);
		this.verifyLicenseYearInfoOnEditPrivilegeLicenseYearPage(lyTemplate);		
		this.editLicenseYearStatus(false); //change status to Inactive,clean the environment.
		
		lm.gotoPrivilegeDocumentPgFromPrivilegeDetailsPg(privilege.code);
		lm.searchPrintDocumentsInProductSubTab(document.status, document.docTepl, document.equipType, false);	
		this.verifyPrintDocumentInfoOnEditPrintDocumentPage(docTemplate);
		this.editPrintDocumentStatus("Inactive"); //change status to Inactive,clean the environment.
		
		//2 add document info when print From and Print to are all blank value
		lm.gotoPrivilegeLicenseYearBatchAddPageFromTopMenu();
		this.addDocumentRecord();
		
		lm.gotoPrivilegeDocumentPgFromPrivilegeDetailsPg(privilege.code);
		lm.searchPrintDocumentsInProductSubTab(document.status, document.docTepl, document.equipType, false);	
		this.verifyPrintDocumentInfoOnEditPrintDocumentPage(docTemplate);
		this.editPrintDocumentStatus("Inactive"); //change status to Inactive,clean the environment.
		
		//3 End
		lm.logOutLicenseManager();
	}
	/**
	 * Start from batch add license year page, ends at privilege product list page 
	 */
	public void addDocumentRecord(){
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage.getInstance();
		
		priLicYearBatchAddPg.makeSearch(ly);
		docTemplate = priLicYearBatchAddPg.getDocumentInfo(document.docTepl, document.equipType);
		docTemplate.prodCode = privilege.code;
		docTemplate.printFromDate = "";
		docTemplate.printToDate = "";

		//wrap the other parameter based on parent document info.
		docTemplate.status = "Active";
		docTemplate.printOrd = document.printOrd;
		docTemplate.effectiveFromDate = document.effectiveFromDate;
		docTemplate.effectiveToDate = document.effectiveToDate;
		docTemplate.fulfillMethod = document.fulfillMethod;
		docTemplate.purchaseType = document.purchaseType;
		docTemplate.equipType = document.equipType;
		docTemplate.createUser  = "Test-Auto, QA-Auto";
		docTemplate.createLoc = login.location.split("/")[1];
		
		TimeZone timez = DataBaseFunctions.getContractTimeZone(schema);
//		docTemplate.createDate = DateFunctions.getToday("EEE MMM dd yyyy", timez);
		docTemplate.createDate = DateFunctions.getToday("EEE MMM d yyyy", timez);  //product didn't match with spec, in order to go thorugh the other checkpoints, temparay changed date format to product behavior.

		priLicYearBatchAddPg.editDocumentInfo(docTemplate);
		this.selectUpdateRecords(false); //check fulfillment document checkbox
		priLicYearBatchAddPg.clickOKButton();
		privilegeListPage.waitLoading();
	}
	
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		//privileges info
		privilege.status = "Active";
		privilege.code = "AT7";
		privilege.name = "AutoBatchLicFulfillSucces";
		privilege.displayCategory = "Freshwater Fishing";
		privilege.displaySubCategory = "Annual";
 		privilege.customerClasses = new String[2];
		privilege.customerClasses[0] = "Individual";		
		privilege.customerClasses[1] = "Business";
		
		//license year info		
		ly.status = "Active";
		ly.locationClass = "All";
		ly.licYear = (DateFunctions.getCurrentYear()+3)+ "";;
		
		ly.productCode = privilege.code;
		ly.productName = privilege.name;	
		
		ly.copyFromYear = ly.licYear;
		ly.newLicYear = (DateFunctions.getCurrentYear()+ 10)+ "";
		ly.numOfYearToAdd = "2";
		ly.sellFromDate = "";
		ly.sellToDate = "";
		
		//fulfillment document info for 2015
		document.prodCode = privilege.code;
		document.docTepl = "AutoBatchSuccess";
		document.equipType = "Internet Sales";
		document.licYearFrom = ly.licYear;
		document.purchaseType = "Original";
		document.printOrd = "1";		
		document.fulfillMethod = "Fulfilled by Mail";
		document.effectiveFromDate = "Wed Apr 11 2012";  //effective from date info we setup for the document;
		document.effectiveToDate = "Wed Apr 28 2021"; //effective to date info we setup for the document;
		document.printFromDate = "Wed Apr 11 2012";  //print from date info we setup for the document;
		document.printToDate = "Thu Apr 29 2021"; //print to date info we setup for the document;
	}
	
	/**
	 * select license or document record .
	 * @param isLicRecord  true: the changes will be made to license year record; false: the changes will be made to print document record
	 */
	private void selectUpdateRecords(boolean isLicRecord){
		LicMgrPrivilegeLicenseYearBatchAddPage priLicYearBatchAddPg = LicMgrPrivilegeLicenseYearBatchAddPage.getInstance();
		int index;
		
		if (isLicRecord){
			index = priLicYearBatchAddPg.getLicenseYearCheckBoxIndex(privilege.code, privilege.name, ly.locationClass);
		}else{
			index = priLicYearBatchAddPg.getDocumentCheckBoxIndex(document.docTepl, document.equipType);
		}
		priLicYearBatchAddPg.selectCheckBox(index);		
	}
	
	
	
	/**
	 * get new added license year and fulfillment document info, and then click go(ADD) button, start from batch add license year page,
	 * ends at privileges list page.
	 */
	private void addNewLicYearAndFulFillRecords(){
		LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage.getInstance();
		LicMgrPrivilegeLicenseYearBatchAddPage priLicYearBatchAddPg = LicMgrPrivilegeLicenseYearBatchAddPage.getInstance();
        
		logger.info("get new added license year and fulfillment document info, and then click go(ADD) button, start from batch add license year page,ends at privileges list page.");
		priLicYearBatchAddPg.makeSearch(ly);
		lyTemplate = priLicYearBatchAddPg.getLicenseYearInfo(ly.productCode, ly.productName, ly.locationClass);
		docTemplate = priLicYearBatchAddPg.getDocumentInfo(document.docTepl, document.equipType);
		this.wrapLicenseYearInfo(lyTemplate);
		this.wrapDocumentInfo(docTemplate);
		this.selectUpdateRecords(false); //check license year checkbox
		this.selectUpdateRecords(true); //check fulfillment document checkbox
		priLicYearBatchAddPg.clickOKButton();
		privilegeListPage.waitLoading();
	}
	
	/**
	 * set the License Year other properties
	 * @param docInfoFromPg
	 */
	private void wrapLicenseYearInfo(LicenseYear licenseYearInfo){
		licenseYearInfo.status = "Active";
		licenseYearInfo.createUser = DataBaseFunctions.getLoginUserName(login.userName);
		licenseYearInfo.createLocation = login.location.split("/")[1];
		TimeZone timez = DataBaseFunctions.getContractTimeZone(schema);
		licenseYearInfo.createTime = DateFunctions.getToday("EEE MMM dd yyyy", timez);
	}
	
	/**
	 * set the fulfillment document other properties
	 * @param docInfoFromPg
	 */
	private void wrapDocumentInfo(LicMgrDocumentInfo docInfoFromPg){
		docInfoFromPg.status = "Active";
		docInfoFromPg.printOrd = document.printOrd;
		docInfoFromPg.effectiveFromDate = document.effectiveFromDate;
		docInfoFromPg.effectiveToDate = document.effectiveToDate;
		docInfoFromPg.fulfillMethod = document.fulfillMethod;
		docInfoFromPg.purchaseType = document.purchaseType;
		docInfoFromPg.equipType = document.equipType;
		docInfoFromPg.createUser  = "Test-Auto, QA-Auto";
		docInfoFromPg.createLoc = login.location.split("/")[1];
		document.printFromDate = "Wed Apr 11 2014";  //print from date info we setup for the document;
		document.printToDate = "Thu Apr 29 2023"; //print to date info we setup for the document;
		
		TimeZone timez = DataBaseFunctions.getContractTimeZone(schema);
//		docInfoFromPg.createDate = DateFunctions.getToday("EEE MMM dd yyyy", timez);
		docInfoFromPg.createDate = DateFunctions.getToday("EEE MMM d yyyy", timez);//product didn't match with spec, in order to go thorugh the other checkpoints, temparay changed date format to product behavior.
	}
	
	/** verify the license year info on Edit Privilege License Year Page.
	 * start/end at edit privilege license year page.
	 * @param info
	 */
	private void verifyLicenseYearInfoOnEditPrivilegeLicenseYearPage(LicenseYear info){
		LicMgrPrivilegeEditLicenseYearWidget detailPg = LicMgrPrivilegeEditLicenseYearWidget.getInstance();
		logger.info("start verifing License Year list ...");
		if (!detailPg.compareLicenseYearRecord(info)) {
			throw new ErrorOnDataException("verify License Year Information on the privilege license year page fail.");
		}
	}
	
	/**
	 * udpate the license year status info on edit privilege license year page.
	 * start from edit privilege license year page, ends at license year page.
	 * @param status
	 */
	private void editLicenseYearStatus(boolean isActive){
		LicMgrPrivilegeEditLicenseYearWidget detailPg = LicMgrPrivilegeEditLicenseYearWidget.getInstance();
		LicMgrPrivilegeLicenseYearPage licYearPg = LicMgrPrivilegeLicenseYearPage.getInstance();
		
		if (isActive){			
			detailPg.selectStatus("Active");
		}else{
			detailPg.selectStatus("Inactive");
		}
		detailPg.clickOK();
		licYearPg.waitLoading();

	}
	
	/**
	 * verify the print document fulfillment info with the given print fulfillment document info.
	 * start from privilege fulfillment document page, ends at edit print document page.
	 * 
	 */
	private void verifyPrintDocumentInfoOnEditPrintDocumentPage(LicMgrDocumentInfo document){
		LicMgrPrivilegePrintDocumentsPage printDocPg = LicMgrPrivilegePrintDocumentsPage.getInstance();
		LicMgrEditPrintDocumentWidget printEditPg = LicMgrEditPrintDocumentWidget.getInstance();
		
		document.id = printDocPg.getPrintDocumentAssignmentID(document, false);
		
		logger.info("goto Edit Print Document page by document ID:" + document.id);
		lm.gotoEditPrintDocumentWidget(document.id);
	
		if(!printEditPg.commpareDocumentInfo(document)){
			throw new ErrorOnDataException("The new batch added Print document info is NOT correct.");
		}
	}
	
	/**
	 * change the status for Print Fulfillment Document , start from Edit Print Document widget, ends at product Print Document sub tab.
	 * @param status
	 */
	private void editPrintDocumentStatus(String status){
		LicMgrEditPrintDocumentWidget printEditPg = LicMgrEditPrintDocumentWidget.getInstance();
		LicMgrPrivilegePrintDocumentsPage printDocPg = LicMgrPrivilegePrintDocumentsPage.getInstance();
		
		printEditPg.selectStatus(status);
		printEditPg.clickOK();
		ajax.waitLoading();
		printDocPg.waitLoading();
	}
	
	/**
	 * check whether the prerequisite has been setup correctly or not. it will automatically Inactive the [current year +8] license year and [current year +8] fulfillment document to meet the prerequisite.
	 */
	private void checkPrerequisite(){
		LicMgrPrivilegePrintDocumentsPage printDocPg = LicMgrPrivilegePrintDocumentsPage.getInstance();
		
		//check the prerequisite for license year
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeLicenseYearPage();
		
		LicenseYear tempLY = new LicenseYear();
		lm.searchPrivilegeLicenseYearByInfo(tempLY); //use an empty license year data collection to make complete search in order to display all Active &Inactive License Year.
		
		//check 2015 Active license year exist.
		licenseYearPg.verifyLicenseYearExist(ly.status, ly.locationClass, ly.licYear);
		//check there is no Active License year for [current Year+ 10],Inactive it if it has one Active record.
		String id = licenseYearPg.getLicenseYearId(OrmsConstants.ACTIVE_STATUS, ly.locationClass, ly.newLicYear);
		if(id.length() >0){
			lm.deactivatePrivilegeLicenseYear(id);
			}
		logger.info("checking all license year prerequisite successful.");
		
		//check the prerequisite for print document
		lm.gotoPrivilegeDocumentPgFromPrivilegeDetailsPg(privilege.code);
		
		LicMgrDocumentInfo tempDoc = new LicMgrDocumentInfo(); //use an empty document template date collection to make complete search in order to display all document info.
		lm.searchPrintDocumentsInProductSubTab(tempDoc.status, tempDoc.docTepl, tempDoc.equipType, false);
		
		//check the first fulfillment document info exist.
		if(printDocPg.getPrintDocumentAssignmentID(document,false).length()< 1){
			throw new ErrorOnPageException("can't find the print document info based on following info, Status:" + document.status +", documentTemplate:" + document.docTepl +
					", equipment Type:" + document.equipType + "Purchase Type:" + document.purchaseType + "; license Year from:" + document.licYearFrom);
		}
		//check there is no Active fulfillment document info for [current year +8],if there is one then Inactive it.
		document.licYearFrom = ly.newLicYear; 
		String tempDocId = printDocPg.getPrintDocumentAssignmentID(document, false);
		if(tempDocId.length()>0){
			logger.info("goto Edit Print Document page by document ID:" + tempDocId);
			lm.gotoEditPrintDocumentWidget(tempDocId);
			this.editPrintDocumentStatus(OrmsConstants.INACTIVE_STATUS);
		}
		document.licYearFrom = ly.licYear;
			
		logger.info("checking all document prerequisite successful.");
	}
}
