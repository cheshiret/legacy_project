package com.activenetwork.qa.awo.testcases.abstractcases.web.hf;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo.Dates;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.FileImportInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LocationInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.fileimports.LicMgrUnlockedPriImportsPage;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.FileUtil;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Common test case for Unlocked Privilege test in HFMO site
 * 
 * @author Lesley Wang
 * @Date  Aug 9, 2013
 */
public abstract class HFMOUnlockedPrivTestCase extends HFMOWebTestCase {
	private FileImportInfo fileImport;
	protected HuntInfo hunt;
	protected String fileName;
	
	protected HFMOUnlockedPrivTestCase() {
		super();
		
		// Login Info for LM
		loginLM.location = "MO Admin/MO Department of Conservation";
		pay = new Payment("master");
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
			totalRecords += privs[i].licenseYear + "," + custNum[i] + "," + hunts[i].getHuntCode() + "," + hunts[i].getNumOfTagQty() + "," + hunts[i].getPointTypeCode() + "\r\n";
		}
		return totalRecords;
	}
	
	/** Initialize hunt location info without sub location */
	protected LocationInfo initializeHuntLocWithoutSubLoc() {
		LocationInfo locWithoutSubLoc = new LocationInfo();
		locWithoutSubLoc.setCode("HL3");
		locWithoutSubLoc.setDescription("HuntLoc003");
		return locWithoutSubLoc;
	}
	
	/** Initialize hunt location info with sub location and location image */
	protected LocationInfo initializeHuntLocWithSubLoc() {
		LocationInfo locWithSubLoc = new LocationInfo();
		locWithSubLoc.setCode("HL1");
		locWithSubLoc.setDescription("HuntLoc001");
		locWithSubLoc.setImage("HuntLocImg_PrdDetailsTest.png");
		locWithSubLoc.setLocationImgFilePath((AwoUtil.getProjectPath() + casePath + "/" + locWithSubLoc.getImage()).replace("/", "\\"));
		
		LocationInfo.SubLocation subLocation = new LocationInfo.SubLocation("SubLoc Category1", "HL001 CatValue1");
		LocationInfo.SubLocation subLocation2 = new LocationInfo.SubLocation("SubLoc Category2", "HL001 CatValue2");
		List<LocationInfo.SubLocation> subLocations = new ArrayList<LocationInfo.SubLocation>();
		subLocations.add(subLocation);
		subLocations.add(subLocation2);
		locWithSubLoc.setSubLocations(subLocations);

		return locWithSubLoc;
	}

	/** Initialize hunt date period info without category and only one date period */
	protected DatePeriodInfo initializeHuntDPWithoutCategory(String ly) {
		// hunt date period - current license year, 1 date info with from and to date but without category
		DatePeriodInfo dateInfoWithoutCat = new DatePeriodInfo();
		dateInfoWithoutCat.setCode("DP1");
		dateInfoWithoutCat.setDescription("Date Period 001");	
		
		DatePeriodLicenseYearInfo ly1 = new DatePeriodLicenseYearInfo();
		ly1.setLicenseYear(ly);
		Dates date1 = ly1.new Dates("Jan 01", "Dec 31", "");
		ly1.setDates(date1);
		dateInfoWithoutCat.setLicenseYears(ly1);

		return dateInfoWithoutCat;
	}
	
	/** Initialize hunt date period info with category and 4 date periods */
	protected DatePeriodInfo initializeHuntDPWithCategory(String ly) {
		// hunt date period - current license year, 4 dates info: with from, to dates and category; with from and to dates; with from date and category; only with from date
		DatePeriodInfo dateInfoWithCat = new DatePeriodInfo();
		dateInfoWithCat.setCode("DP3");
		dateInfoWithCat.setDescription("Date Period 003");	
		DatePeriodLicenseYearInfo ly2 = new DatePeriodLicenseYearInfo();
		ly2.setLicenseYear(ly);
		Dates date2 = ly2.new Dates("Jan 01", "Dec 31", "DP CategoryName");
		Dates date3 = ly2.new Dates("Jan 01", "", "DP CategoryName"); 
		Dates date4 = ly2.new Dates("Jan 01", "Dec 31", ""); 
		Dates date5 = ly2.new Dates("Jan 01", "", ""); 
		ly2.setDates(date2, date3, date4, date5);
		dateInfoWithCat.setLicenseYears(ly2);

		return dateInfoWithCat;
	}
}
