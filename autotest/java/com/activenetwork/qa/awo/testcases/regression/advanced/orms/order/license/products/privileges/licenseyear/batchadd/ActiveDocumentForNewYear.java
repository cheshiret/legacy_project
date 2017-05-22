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
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * verify when new license year being added equal to inactive license year[ly[0]], 
 * but privilege product still have active fulfillment document for the year being added, privilege product will NOT show
 * 
 * @Preconditions:
 * 1: there is one active privilege product[code AB1](with property in wrap parameter);
 * 1: privilege product have one Active license year for current year(with property in wrap parameter);
 * 2: privilege product have inActive license year for current year+8(with property in wrap parameter), as long as there is one Active license year for current year+8, the test case will automatically Inactive it to meet this prerequisite;
 * 3: privilege product have one Active fulfillment document for current year(with property in wrap parameter);
 * 4: privilege product have one Active fulfillment document for current year+8(with property in wrap parameter);
 * @SPEC: Add License Year for Privileges in Batch
 * @Task#:AUTO-725
 * 
 * @author bzhang
 * @Date  Feb 6, 2012
 */
public class ActiveDocumentForNewYear extends LicenseManagerTestCase {
	private LicenseYear[] ly = new LicenseYear[2];  
	private LicMgrDocumentInfo[] document = new LicMgrDocumentInfo[2];  
	private LicMgrPrivilegeLicenseYearBatchAddPage priLicYearBatchAddPg = LicMgrPrivilegeLicenseYearBatchAddPage.getInstance();
	private LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage.getInstance();
	
	public void execute() {
		lm.loginLicenseManager(login);
		
		//check prerequisite met the test case needs.
		this.checkPrerequisite();
		
		lm.gotoPrivilegeLicenseYearBatchAddPageFromTopMenu();
		//verify when new license year being added equal to inactive license year[ly[1]], but privilege product still have active fulfillment document for the year being added, privilege product will NOT show
		lm.searchPrivilegeLicenseYearOnBatchAddPg(ly[0]);
		priLicYearBatchAddPg.verifyLicYearRecordExist(ly[0], false);
		priLicYearBatchAddPg.verifyFulFillMentDocumentExist(document[0].docTepl, document[0].equipType, false);
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS"; 
		
		//privileges info
		privilege.status = "Active";
		privilege.code = "AB1";
		privilege.name = "Auto Batch License Status";
		privilege.displayCategory = "Freshwater Fishing";
		privilege.displaySubCategory = "Annual";
		privilege.customerClasses = new String[2];
		privilege.customerClasses[0] = "Individual";		
		privilege.customerClasses[1] = "Business";
		
		//license year info		
		this.resetLicneseYearInfo();
		
		//fulfillment document info for [current year]
		document[0] = new LicMgrDocumentInfo();
		document[0].docTepl = "AutoBatchLic03";  //change this to AutoBatchLic03
		document[0].equipType = "All";
		document[0].purchaseType = "Transfer";
		document[0].status = "Active";
		document[0].licYearFrom = ly[0].licYear;
		
		//fulfillment document info for [current year +8]
		document[1] = new LicMgrDocumentInfo();
		document[1].docTepl = "AutoBatchLic04"; //change this to AutoBatchLic04
		document[1].equipType = "All";
		document[1].purchaseType = "Original";
		document[1].status = "Active";
		document[1].licYearFrom = ly[1].licYear;
	}
	
	private void resetLicneseYearInfo(){
		ly[0] = new LicenseYear();
		ly[0].status = "Active";
		ly[0].locationClass = "03 - Lakes Offices";
		ly[0].licYear = String.valueOf(DateFunctions.getCurrentYear()); //make sure privilege product have active license year for this data;
		
		ly[0].productCode = privilege.code;
		ly[0].productName = privilege.name;		
		
		ly[0].numOfYearToAdd = "1";
		
		ly[1] = new LicenseYear();
		ly[1].status = "Inactive";
		ly[1].locationClass = "03 - Lakes Offices";
		ly[1].licYear = String.valueOf(DateFunctions.getCurrentYear() + 8); //make sure privilege product have Inactive license year for this data, the test case will deactivate license year if it has Active licen year for [current year +8]
		
		ly[1].productCode = privilege.code;
		ly[1].productName = privilege.name;
		
		ly[0].copyFromYear = ly[0].licYear;
		ly[0].newLicYear = ly[1].licYear;
		ly[0].numOfYearToAdd = "1";
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
		
		for(int i = 0 ; i < ly.length; i ++){
			logger.info("start checking the prerequisite for license year "+ i +". please wait..");
			
			if(i==0){ //make sure there is active license year for current year
				licenseYearPg.verifyLicenseYearExist(ly[i].status, ly[i].locationClass, ly[i].licYear);
			}else{
				//make sure there is no active license year for [current year + 8], if there is then Deactivate it.
				String id = licenseYearPg.getLicenseYearId(OrmsConstants.ACTIVE_STATUS, ly[1].locationClass, ly[1].licYear);
				if(id.length() >0){
					lm.deactivatePrivilegeLicenseYear(id);
					}else{
//						lm.searchPrivilegeLicenseYearByInfo(OrmsConstants.INACTIVE_STATUS, ly[i].licYear,ly[i].locationClass);
						//verify there is Inactive license year for [current year +8]
						licenseYearPg.verifyLicenseYearExist(ly[i].status, ly[i].locationClass, ly[i].licYear);	
					}
			}
		}
		logger.info("checking all license year prerequisite successful.");
		
		//check the prerequisite for print document
		for(int i = 0 ; i < document.length; i ++){
			logger.info("start checking the prerequisite for document  "+ i +". please wait..");
			lm.gotoPrivilegeDocumentPgFromPrivilegeDetailsPg(privilege.code);
			lm.searchPrintDocumentsInProductSubTab(document[i].status, document[i].docTepl, document[i].equipType, false);
			
			if(printDocPg.getPrintDocumentAssignmentID(document[i],false).length()< 1){
				throw new ErrorOnPageException("can't find the print document info based on following info, Status:" + document[i].status +", documentTemplate:" + document[i].docTepl +
						", equipment Type:" + document[i].equipType + "Purchase Type:" + document[i].purchaseType + "; license Year from:" + document[i].licYearFrom);
			}
		}
		logger.info("checking all document prerequisite successful.");
	}
}
