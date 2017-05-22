package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.licenseyear.batchadd;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrDocumentInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearBatchAddPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegePrintDocumentsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @prerequisite:
 * 1: there is a privilege product code(AT4), name (Auto Batch Add Detault Va) exist.
 * 2: there is one active license year setup for this product(current year+3) no active license year for current year+8 .
 * 3: there is one active  fulfillment document setup for current year+3;
 * has nothing to do with too many license year can be added.
 * @Description
 * 1: verify pre-populated value in SellFrom/To and ValidFrom/To fields for new added license year.
 * 2: verify pre-populated value in PrintFrom and PrintTo fields for new added fulfillment document.
 * 3: verify cancel operation won't add license year info.
 * @Preconditions:
 * @SPEC: Add License Year for Privileges in Batch
 * @Task#:AUTO-725
 *
 * @author bzhang
 * @Date  Aug 23, 2011
 */
public class VerifySchResultPopulateValue extends LicenseManagerTestCase{
	private LicenseYear ly = new LicenseYear();
	private LicMgrPrivilegeLicenseYearBatchAddPage priLicYearBatchAddPg = LicMgrPrivilegeLicenseYearBatchAddPage.getInstance();
	private LicMgrPrivilegesListPage priListPg = LicMgrPrivilegesListPage.getInstance();
	boolean pass = true;

	public void execute() {

		lm.loginLicenseManager(login);
		lm.editPrivilegeLicenseYearInfoFromTopMenu(ly);
		lm.editPrintDocumentFroProductInfo(document);

		//1. license year and fulfillment document specified Valid From Date& Time, Valid To Date& Time.
		lm.gotoPrivilegeLicenseYearBatchAddPageFromTopMenu();
		lm.searchPrivilegeLicenseYearOnBatchAddPg(ly);
		
		this.compareLiceseYear(ly);	//this method will change Ly start to date value, need to reset ly data collection.
		this.compareDocumentInfo(document);
		priLicYearBatchAddPg.clickCancelButton();
		priListPg.waitLoading();

		//2.  license year Valid From Date& Time, Valid To Date& Time are null/blank.
		this.resetLicneseYearInfo();
		ly.validFromDate = "";
		ly.validFromTime = "";
		ly.validFromAmPm = "AM";

		ly.validToDate = "";
		ly.validToTime = "";
		ly.validToAmPm = "AM";

		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.editPrivilegeLicenseYear(ly);
		//3. cancel on batch create page, license year info won't be added to license year page.
		this.verifyLicenseYearExistOnLicYearPg(ly.locationClass, ly.newLicYear, false);

		//4. cancel on batch create page, print document won't be added to license year page.
		document.licYearFrom = ly.newLicYear;
		lm.gotoPrivilegePrintDocumentPage();
		this.verifyPrintDocumentExistDocumentPage(document,false);

		lm.gotoPrivilegeLicenseYearBatchAddPageFromTopMenu();
		//5. verify pre-populated value for License year when Valid From Date& Time, Valid To Date& Time are null/blank..
		lm.searchPrivilegeLicenseYearOnBatchAddPg(ly);
		this.compareLiceseYear(ly);
		lm.logOutLicenseManager();
		if(!pass){
			throw new ErrorOnPageException("the warning message verification failured, please refer to log file for more details info");
		}

	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		//new added privileges info
		privilege.status = "Active";
		privilege.code = "AT4";
		privilege.name = "Auto Batch Add Detault Va";
		privilege.displayCategory = "Freshwater Fishing";
		privilege.displaySubCategory = "Annual";
		privilege.customerClasses = new String[2];
		privilege.customerClasses[0] = "Individual";
		privilege.customerClasses[1] = "Business";

		this.resetLicneseYearInfo();
		this.resetDocumentInfo();
	}

	private void resetLicneseYearInfo(){
		ly.status = "Active";
		ly.locationClass = "03 - Lakes Offices";
		ly.licYear = Integer.toString(DateFunctions.getYearAfterCurrentYear(3));//(Integer.valueOf(lm.getFiscalYear(schema))+3)+"";

		ly.productCode = privilege.code;
		ly.productName = privilege.name;

		ly.sellFromDate = DateFunctions.getDateAfterToday(1, "EEE MMM dd yyyy");
		ly.sellFromTime = "12:00";
		ly.sellFromAmPm = "AM";

		ly.sellToDate = DateFunctions.getDateAfterToday(2, "EEE MMM dd yyyy");
		ly.sellToTime = "12:00";
		ly.sellToAmPm = "PM";

		ly.validFromDate = ly.sellFromDate;
		ly.validFromTime = ly.sellFromTime;
		ly.validFromAmPm = ly.sellFromAmPm;

		ly.validToDate = ly.sellToDate;
		ly.validToTime = ly.sellToTime;
		ly.validToAmPm = ly.sellToAmPm;

		ly.copyFromYear = ly.licYear;
		ly.newLicYear = Integer.toString(DateFunctions.getYearAfterCurrentYear(11));//(Integer.valueOf(lm.getFiscalYear(schema))+11)+"";
		ly.numOfYearToAdd = "1";
	}

	private void resetDocumentInfo(){
		//fulfillment document info
		document.prodCode = privilege.code;
		document.numOfYearsToAdd = "1";
	    document.prodType="privilege";
		document.status = "Active";
		document.printOrd = "1";
		document.docTepl = "AutoBatchAddFulFill";
		document.licYearFrom = Integer.toString(DateFunctions.getYearAfterCurrentYear(3));//(Integer.valueOf(lm.getFiscalYear(schema))+3)+"";
		document.licYearTo = document.licYearFrom;//(Integer.valueOf(lm.getFiscalYear(schema))+3)+"";
		document.effectiveFromDate = DateFunctions.getDateAfterToday(1, "EEE MMM dd yyyy");
		document.effectiveToDate = DateFunctions.getDateAfterToday(2, "EEE MMM dd yyyy");
		document.printFromDate = document.effectiveFromDate;
		document.printToDate = document.effectiveToDate;
		document.fulfillMethod = "Fulfilled by Mail";
		document.equipType = "All";
		document.purchaseType = "Transfer";
	}

	/**
	 * verify the pre-populated value based on given ly.newYear and ly.NumofYearsToAdd
	 * Start/End at license year batch add page. this method will change the valid sell from and valid to value.
	 * @param ly
	 */
	private void compareLiceseYear(LicenseYear ly){

		//wrap parameter
		int month = Integer.parseInt(ly.numOfYearToAdd) * 12;
		ly.sellFromDate = DateFunctions.getDateAfterGivenMonth(ly.sellFromDate, month, "EEE MMM dd yyyy");
		ly.sellToDate = DateFunctions.getDateAfterGivenMonth(ly.sellToDate, month, "EEE MMM dd yyyy");

		if (ly.validFromDate.trim().length() >0){
			ly.validFromDate = DateFunctions.getDateAfterGivenMonth(ly.validFromDate, month, "EEE MMM dd yyyy");
		}

		if (ly.validToDate.trim().length() >0){
			ly.validToDate = DateFunctions.getDateAfterGivenMonth(ly.validToDate, month, "EEE MMM dd yyyy");
		}

		if (!priLicYearBatchAddPg.compareLicenseYearRecords(ly)){
			logger.info("prepopulated value for license year is incorrect");
			pass = false;
			}
	}

	/**
	 *
	 * verify the licnese year info exist or not on license year page, start/ends at license year page.
	 * @param locClass
	 * @param year
	 * @param expect: true - the license year info exist; false - the license year info didn't exist
	 */
	private void verifyLicenseYearExistOnLicYearPg(String locClass, String year, boolean expect){
		LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage.getInstance();
		String yearId = licenseYearPg.getLicenseYearId(locClass, year);

		if(expect){
			if (yearId.equals("")){
				logger.error("The Expect license year:" + year + " for location class:" + locClass + " did NOT display on lisence year page.");
				pass = false;
			}
		}else{
			if (!yearId.equals("")){
				logger.error("The license year:" + year + " for location class:" + locClass + " should NOT display on lisence year page.");
				pass = false;
			}
		}
	}

	/**
	 * verify the print document fulfillment info exist on fulfillment document page.
	 * start and ends at privilege fulfillment document page.
	 *
	 */
	private void verifyPrintDocumentExistDocumentPage(LicMgrDocumentInfo document, boolean expect){
		LicMgrPrivilegePrintDocumentsPage printDocPg = LicMgrPrivilegePrintDocumentsPage.getInstance();

		document.id = printDocPg.getPrintDocumentAssignmentID(document, false);

		if(expect){
			if (!(document.id.trim().length()>0)){
				throw new ErrorOnPageException("The print fulfillment document should exist on the page..");
			}
		}else{
			if (document.id.trim().length()>0){
				throw new ErrorOnPageException("The Print fulfillment document should not exist: " + document.id);
			}
		}
	}

	/**
	 * verify whether the given fulfillment document records consistent with the expect document value.
	 * this method will change the print from  and print to value.
	 * @param doc
	 */
	private void compareDocumentInfo(LicMgrDocumentInfo doc){

		int month = Integer.parseInt(doc.numOfYearsToAdd) *12;
		if(doc.printFromDate.trim().length() >0){
			doc.printFromDate = DateFunctions.getDateAfterGivenMonth(doc.printFromDate, month, "EEE MMM dd yyyy");
		}
		if (doc.printToDate.trim().length() >0){
			doc.printToDate = DateFunctions.getDateAfterGivenMonth(doc.printToDate, month, "EEE MMM dd yyyy");
		}

		if (!priLicYearBatchAddPg.compareDocumentRecords(doc)){
			logger.error("prepopuldated data verify for fulfillment document failed");
			pass = false;
		}
	}
}
