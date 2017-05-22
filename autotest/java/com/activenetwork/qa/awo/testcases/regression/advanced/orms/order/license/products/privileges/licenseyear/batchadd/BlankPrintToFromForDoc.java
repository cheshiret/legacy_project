package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.licenseyear.batchadd;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrDocumentInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearBatchAddPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegePrintDocumentsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @prerequisite:
 * 1: there is a privilege product code(AT3), name (AutoBatchAddBlank) exist.
 * 2: there is one active license year setup for this product[current year] no active license year for [current year + 10].
 * 3: there is one active  fulfillment document setup for [current year] but the corresponding Print From and Print To was left as blank;
 * @Description
 * 1: verify fulfillment document won't show up if the Print From and Print To is Blank.
 * @Preconditions:
 * @SPEC: Add License Year for Privileges in Batch
 * @Task#:AUTO-725
 *
 * @author bzhang
 * @Date  April 18, 2012
 */
public class BlankPrintToFromForDoc extends LicenseManagerTestCase{
	private LicenseYear ly = new LicenseYear();
	private LicMgrPrivilegeLicenseYearBatchAddPage priLicYearBatchAddPg = LicMgrPrivilegeLicenseYearBatchAddPage.getInstance();
	private LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage.getInstance();
	boolean pass = true;

	public void execute() {
		lm.loginLicenseManager(login);
		this.checkPrerequisite(); 

		//1. verify when fulfillment document with blank print from and print to date, it will not be listed on batch add page.
		lm.gotoPrivilegeLicenseYearBatchAddPageFromTopMenu();
		lm.searchPrivilegeLicenseYearOnBatchAddPg(ly);
		priLicYearBatchAddPg.verifyFulFillMentDocumentExist(document.docTepl, document.equipType, false);  //blank print to, print from, the document won't show on batch add page.
		lm.logOutLicenseManager();
		if(!pass){
			throw new ErrorOnPageException("the warning message verification failured, please refer to log file for more details info");
		}
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		// privileges info
		privilege.status = "Active";
		privilege.code = "AT3";
		privilege.name = "AutoBatchAddBlank";
		privilege.displayCategory = "Freshwater Fishing";
		privilege.displaySubCategory = "Annual";
		privilege.customerClasses = new String[2];
		privilege.customerClasses[0] = "Individual";
		privilege.customerClasses[1] = "Business";
		
		// license year info
		ly.status = "Active";
		ly.locationClass = "03 - Lakes Offices";
		ly.licYear = DateFunctions.getCurrentYear() +"";

		ly.productCode = privilege.code;
		ly.productName = privilege.name;


		ly.copyFromYear = ly.licYear;
		ly.newLicYear = (DateFunctions.getCurrentYear() +10) +"";
		ly.numOfYearToAdd = "10";
		
		//fulfillment document info
		document.prodCode = privilege.code;
		document.numOfYearsToAdd = "1";
	    document.prodType="privilege";
		document.status = "Active";
		document.printOrd = "1";
		document.docTepl = "AutoBatchAddBlank";
		document.licYearFrom = ly.licYear ;
		document.licYearTo = ly.licYear ;
		document.effectiveFromDate = DateFunctions.getDateAfterToday(1, "EEE MMM dd yyyy");
		document.effectiveToDate = DateFunctions.getDateAfterToday(2, "EEE MMM dd yyyy");
		document.printFromDate = "";
		document.printToDate = "";
		document.fulfillMethod = "Fulfilled by Mail";
		document.equipType = "All";
		document.purchaseType = "Transfer";
		document.species = "Ducks";
		document.huntSeason = "LicYearBatchAdd";
	}

	/**
	 * check whether the prerequisite has been setup correctly or not. it will automatically Inactive the [current year +8] license year to meet the prerequisite.
	 */
	private void checkPrerequisite(){
		LicMgrPrivilegePrintDocumentsPage printDocPg = LicMgrPrivilegePrintDocumentsPage.getInstance();
		
		//check the prerequisite for license year
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeLicenseYearPage();
		
		LicenseYear tempLY = new LicenseYear();
		lm.searchPrivilegeLicenseYearByInfo(tempLY); //use an empty license year data collection to make complete search in order to display all Active &Inactive License Year.
		
		logger.info("start checking the prerequisite for license year . please wait..");
			
		//make sure there is active license year for current year
		licenseYearPg.verifyLicenseYearExist(ly.status, ly.locationClass, ly.licYear);
		
		//make sure there is no active license year for [current year + 10], if there is then Deactivate it.
		String id = licenseYearPg.getLicenseYearId(ly.status, ly.locationClass, ly.newLicYear);
		if(id.length() >0){
			lm.deactivatePrivilegeLicenseYear(id);
			}
		
		logger.info("checking all license year prerequisite successful.");
		
		//check the prerequisite for print document
		logger.info("start checking the prerequisite for document. please wait..");
		lm.gotoPrivilegePrintDocumentPage();
		LicMgrDocumentInfo tempDoc = new LicMgrDocumentInfo(); //use an empty document template date collection to make complete search in order to display all document info.
		lm.searchPrintDocumentsInProductSubTab(tempDoc.status, tempDoc.docTepl, tempDoc.equipType, false);
		
		//check the first fulfillment document info exist.
		String tempFirstDocID = printDocPg.getPrintDocumentAssignmentID(document,false);
		if(tempFirstDocID.length()< 1){
			throw new ErrorOnPageException("can't find the print document info based on following info, Status:" + document.status +", documentTemplate:" + document.docTepl +
					", equipment Type:" + document.equipType + "Purchase Type:" + document.purchaseType + "; license Year from:" + document.licYearFrom);
		}else{
			//update the Print From and Print to to Blank	
			document.id = tempFirstDocID;
			lm.editPrintDocumentForProduct(document);
		}
	
		//check there is no Active fulfillment document info for [current year +8],if there is one then Inactive it.
		document.licYearFrom = ly.newLicYear; 
		String tempDocID = printDocPg.getPrintDocumentAssignmentID(document, false);
		if(tempDocID.length()>0){
			logger.info("goto Edit Print Document page by document ID:" + document.id);
			lm.activateOrInactivatePrivilegePrintDocument(tempDocID, OrmsConstants.INACTIVE_STATUS);
		}
		document.licYearFrom = ly.licYear;
			
		logger.info("checking all document prerequisite successful.");
	}
}
