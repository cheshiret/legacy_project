package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.license;

import java.io.File;

import com.activenetwork.qa.awo.datacollection.legacy.ReportData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.InspectionInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrInspectionDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to check the support of uppercase of Boat Inspection Form.
 * @Preconditions: Need an officer and the officer has been assigned to a badge
 * Officer information(have not write the setup script)QA-Officer TEST-Officer 12/25/1999 10499 Xi'an 1369874512
 * Badge information: central  1111
 * @Task#:Auto-1159
 * @author Phoebe Chen
 * @Date  Aug 14, 2012
 */
public class BoatInspectionForm extends LicenseManagerTestCase {
	private InspectionInfo inspection = new InspectionInfo();
	private String reportFile = "";
	private String officer = "";
	private ReportData rd = new ReportData();
	private static final boolean UPPER_CASE = true;
	private LicMgrInspectionDetailsPage inspectionDetailPage = LicMgrInspectionDetailsPage.getInstance();
	@Override
	public void execute() {
		// Set upperCase		
		lm.updateUpperCaseConfigInDB(schema,UPPER_CASE);
		// Login in and goto inspection search page
		lm.loginLicenseManager(login);
		lm.gotoVehicleInspectionSearchPage();
		
	
		lm.gotoVehicleInspectionDetailPage(inspection.inspectionId);
		String status = inspectionDetailPage.getInspStatus();
		// Check the status of the inspection, if it is "completed", the
		// inspection is not suitable for test it'll throw an error
		this.checkStatus(status);
		
		// Print the report
		lm.printInspectionForm(rd.fileName);
		boolean result = lm.verifyPdfReport(templatesPath, rd.fileName);
		this.verifyReportCorrect(result);
		
        //Logout license manager
        lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
        
		//Set up schema for setting upperCase
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		logger.info("Use schema: "+ schema);

		inspection.inspectionId = "198075168";
		inspection.reason = "Boat Description";
        
		rd.reportName = "BoatInspectionForm";

		reportFile = logger.getLogPath() + "ComparedFile";
		reportFile = reportFile.replace("/", "\\");
		officer = "TEST-Officer, QA-Officer : 1111";
		
		this.setReportFileName(reportFile);
		//Run in local
        //templatesPath = "C://ReportTemplates";
	}

    public void setReportFileName(String reportPath){
		File file = new File(reportPath);
		if (!file.exists()) {
			boolean success = file.mkdir();
			if (!success) {
				throw new ItemNotFoundException(
				"Failed To Make Directory.");
			}
		}
		String fileName = file.getAbsolutePath()+ "\\";
		fileName +=  rd.reportName.replaceAll(" ", "");	
		fileName += ".pdf";
		rd.fileName = fileName;
    }
	/**
	 * Check the status for inspection before print the form
	 *  "In Progress" or "Closed" : it is ready to print inspection form 
	 *  "Pending": need to assign an officer before it can be printed 
	 *  "Completed": The inspection is not suitable for the case , need to change to another inspection(inspectionId)
	 * @param status
	 */
	private void checkStatus(String status) {
		if (status.equals("Completed")) {
			throw new ErrorOnPageException(
					"The status of inspection is completed, it can not print form any more, "
							+ "please choose another insepction!");
		}
		if (status.equals("Pending")) {
			lm.assignOfficerToInspction(officer, inspection.reason);
			lm.gotoVehicleInspectionDetailPage(inspection.inspectionId);
		}
	}
		
	/**
	 * This method used to verify report results is correct or not
	 * @param result
	 */
	public void verifyReportCorrect(boolean result) {
		logger.info("Report content comparsion");
		if (result) {
			logger.info("Report Content Correct.");
		} else {
			throw new ErrorOnPageException("Report Content Not Correct.");
		}
	}
}
