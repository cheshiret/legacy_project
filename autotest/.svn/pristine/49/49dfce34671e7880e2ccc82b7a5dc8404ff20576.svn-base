package com.activenetwork.qa.awo.testcases.abstractcases.web.hf;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.CustomerPoint;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.FileImportInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.fileimports.LicMgrPointImportsPage;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.FileUtil;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Common test case for Points test in HFSK site
 * 
 * @author Lesley Wang
 * @Date  Sep 29, 2013
 */
public abstract class HFSKPointsTestCase extends HFSKWebTestCase {
	private FileImportInfo fileImport;
	protected CustomerPoint point = new CustomerPoint();
	protected String fileName;
	protected CustomerPoint[] points;
	
	protected HFSKPointsTestCase() {
		super();

		// Login Info for LM
		loginLM.location = "SK Admin - Auto/SASK";
	}
	
	protected void importPoints(String importFileName, CustomerPoint[] points) {
		this.importPoints(importFileName, points, true);
	}
	
	protected void importPoints(String importFileName, CustomerPoint[] points, boolean needToLoginInOut) {
		LicMgrPointImportsPage pointImportPg = LicMgrPointImportsPage.getInstance();

		// Generate Unlocked Privilege records file
		this.initialFileImportInfo(importFileName, points);
		FileUtil.deleteFile(fileImport.getFileImportPath());	
		FileUtil.generateAndWriteFile(fileImport.getFileImportPath(), fileImport.getFileContent());

		// Import the file in LM
		if (needToLoginInOut) {
			lm.loginLicenseManager(loginLM);
		}
		String importID = lm.importPointFile(fileImport.getFileImportPath());
		fileImport.setFileImportID(importID);
		pointImportPg.waitForImportCompleted(importID);
		pointImportPg.verifyPointImportResult(fileImport);
		if (needToLoginInOut) {
			lm.logOutLicenseManager();
		}

		// Delete File
		FileUtil.deleteFile(fileImport.getFileImportPath());					
	}

	protected void importPoints(String importFileName, CustomerPoint point) {
		this.importPoints(importFileName, new CustomerPoint[] {point});
	}

	protected void importPointsAfterLogin(String importFileName, CustomerPoint point) {
		this.importPoints(importFileName, new CustomerPoint[] {point}, false);
	}

	private void initialFileImportInfo(String fileName, CustomerPoint[] points) {
		// File Import Info
		fileImport = new FileImportInfo();
		String filePath = AwoUtil.CSV_ROOT + "/" + fileName + ".csv";
		logger.info(filePath);
		fileImport.setFileImportPath(filePath.replace("/", "\\"));
		fileImport.setFileImportStatus("Completed");
		fileImport.setFileContent(this.generateFileContent(points));	
		fileImport.setNumOfImported(Integer.toString(points.length));
	}

	private String generateFileContent(CustomerPoint[] points) {
		String header = "";
		if (hf.isFileHeaderRequired(schema, FILE_IMPORT_TYPE_POINT)) {
			List<String> headers = hf.getFileHeaderColNms(schema, FILE_IMPORT_TYPE_POINT);
			for (String h : headers) {
				header += h + ",";
			}
			header = header.substring(0, header.length()-1);
		}
		String totalRecords = (StringUtil.isEmpty(header) ? "" : header+"\r\n");
		for (int i = 0; i < points.length; i++) {
			CustomerPoint point = points[i];
			String date = point.dateOfChange;
			if (StringUtil.notEmpty(date)) {
				date = DateFunctions.formatDate(date, "yyyyMMdd");
			}
			totalRecords += point.licenseYear + "," + point.custNum + "," + 
					point.pointTypeCode + "," + point.pointBalance + "," + date + "," +
					point.trackingType + "," + point.comments + "\r\n";
		}
		return totalRecords;
	}
}
