package com.activenetwork.qa.awo.testcases.abstractcases.web.hf;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.FileImportInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.fileimports.LicMgrUnlockedPriImportsPage;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.FileUtil;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * @Description: Common test case for Unlocked Privilege test in HFSK site
 * 
 * @author Swang
 * @Date  Sep 10, 2013
 */
public abstract class HFSKUnlockedPrivTestCase extends HFSKWebTestCase {
	private FileImportInfo fileImport;
	protected HuntInfo hunt;
	protected String fileName;

	protected HFSKUnlockedPrivTestCase() {
		super();

		// Login Info for LM
		loginLM.location = "SK Admin - Auto/SASK";
	}

	protected void prepareUnlockedPriv(String importFileName, PrivilegeInfo[] privs, String[] custNum, HuntInfo[] hunts) {
		LicMgrUnlockedPriImportsPage ulPriImportPg = LicMgrUnlockedPriImportsPage.getInstance();

		//if unlocked privileges not imported
		if (!hf.isUnlockedPriExist(schema, cus.custNum, privilege, hunt, UL_PRI_AWARDED_ID)) {
			// Generate Unlocked Privilege records file
			this.initialFileImportInfo(importFileName, privs, custNum, hunts);
			FileUtil.deleteFile(fileImport.getFileImportPath());	
			FileUtil.generateAndWriteFile(fileImport.getFileImportPath(), fileImport.getFileContent());

			// Import the file in LM
			lm.loginLicenseManager(loginLM);
			String importID = lm.importUnlockedPriFile(fileImport.getFileImportPath());
			fileImport.setFileImportID(importID);
			ulPriImportPg.waitForImportCompleted(importID);
			ulPriImportPg.verifyUnlockedPriImportResult(fileImport);
			lm.logOutLicenseManager();

			// Delete File
			FileUtil.deleteFile(fileImport.getFileImportPath());		
		}
	}

	protected void prepareUnlockedPriv(String importFileName, PrivilegeInfo priv, String custNum, HuntInfo hunt) {
		this.prepareUnlockedPriv(importFileName, new PrivilegeInfo[] {priv}, new String[] {custNum}, new HuntInfo[] {hunt});
	}

	protected void prepareUnlockedPriv(String importFileName, PrivilegeInfo[] privs, String custNum, HuntInfo hunt) {
		String[] custNums = new String[privs.length];
		HuntInfo[] hunts = new HuntInfo[privs.length]; 
		for (int i = 0; i < privs.length; i++) {
			custNums[i] = custNum;
			hunts[i] = hunt;
		}
		this.prepareUnlockedPriv(importFileName, privs, custNums, hunts);
	}

	protected void prepareUnlockedPriv(String importFileName, PrivilegeInfo[] privs, String custNum, HuntInfo[] hunts) {
		String[] custNums = new String[privs.length];
		for (int i = 0; i < privs.length; i++) {
			custNums[i] = custNum;
		}
		this.prepareUnlockedPriv(importFileName, privs, custNums, hunts);
	}

	private void initialFileImportInfo(String fileName, PrivilegeInfo[] privs, String[] custNum, HuntInfo[] hunts) {
		// File Import Info
		fileImport = new FileImportInfo();
//		String filePath = AwoUtil.getProjectPath() + casePath + "/" + fileName + ".csv";
		String filePath = AwoUtil.CSV_ROOT + "/" + fileName + ".csv";
		logger.info(filePath);
		fileImport.setFileImportPath(filePath.replace("/", "\\"));
		fileImport.setFileImportStatus("Completed");
		fileImport.setFileContent(this.generateFileContent(privs, custNum, hunts));	
		fileImport.setNumOfImported(Integer.toString(privs.length));
	}

	private String generateFileContent(PrivilegeInfo[] privs, String[] custNum, HuntInfo[] hunts) {
		String header = "";
		if (hf.isFileHeaderRequired(schema, FILE_IMPORT_TYPE_ULPRI)) {
			List<String> headers = hf.getFileHeaderColNms(schema, FILE_IMPORT_TYPE_ULPRI);
			for (String h : headers) {
				header += h + ",";
			}
			header = header.substring(0, header.length()-1);
		}
		String totalRecords = (StringUtil.isEmpty(header) ? "" : header+"\r\n");
		if (privs.length != custNum.length || privs.length != hunts.length) {
			throw new ErrorOnPageException("privilege, customer num and hunts info numbers are wrong! priv: " + privs.length + "; cust num:" + custNum.length + "; hunt:" + hunts.length);
		}
		for (int i = 0; i < privs.length; i++) {
			totalRecords += privs[i].licenseYear + "," + custNum[i] + "," + hunts[i].getHuntCode() + "," + privs[i].code + "\r\n";
		}
		return totalRecords;
	}
}
