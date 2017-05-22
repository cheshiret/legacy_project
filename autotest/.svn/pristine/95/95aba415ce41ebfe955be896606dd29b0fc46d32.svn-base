package com.activenetwork.qa.awo.supportscripts.support.licensemgr;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * This support script only support for adding "Active"  privilege product license year info.
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author bzhang
 * @Date  Feb 7, 2012
 */
public class AddPrivilegeLicenseYear extends SupportCase{
	private boolean loggedIn = false;// Don't login.
	private LoginInfo login = new LoginInfo();
	private TimeZone timeZone = null;
	private String schema = "";
	private String contract = "";
	private String productCode = "";
	private PrivilegeInfo privilege = new PrivilegeInfo();
	private LicenseYear licenseYear = new LicenseYear();
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage.getInstance();
	
	@Override
	public void execute() {
		//If login contract is different, logout
		if (loggedIn && !contract.equalsIgnoreCase(login.contract) ) {
			lm.logOutLicenseManager();
			loggedIn = false;
		}
		//If privilege product code is different, go back to privileges search list page, re-enter the corresponding License year page
		if ((!loggedIn) || (loggedIn && !licenseYearPg.exists())) {
			lm.loginLicenseManager(login);
			loggedIn=true;
		}
		if(!productCode.equalsIgnoreCase(privilege.code)) {
			lm.gotoPrivilegeSearchListPageFromTopMenu();
			lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
			lm.gotoPrivilegeLicenseYearPage();
		}
		
		lm.addLicenseYear(licenseYear);
		
		this.verifyResult();
		
		// in order to run next data in data pool
		contract = login.contract;
		productCode = privilege.code;
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		startpoint = 80; // the start point in the data pool
		endpoint = 80; // the end point in the data pool

		dataSource = casePath + "/" + caseName;
		
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		logMsg=new String[5];
		logMsg[0] = "Index";
		logMsg[1] = "PrivilegeCode";
		logMsg[2] = "LicenseYear";
		logMsg[3] = "LocationClass";
		logMsg[4] = "Result";
	}
	
	public void verifyResult() {
		if(!licenseYearPg.exists()) {
			 logger.error("create privilege product license year failed:code="+privilege.code+",name="+privilege.name+ ", license year=" 
					 + licenseYear.licYear + ",license year location class=" + licenseYear.locationClass + ",license year status =" + licenseYear.status);
			 logMsg[4] = "Failed";
	    } else {
	    	 logMsg[4] = "Passed";
	    }
	}

	@Override
	public void getNextData() {
		login.contract=dpIter.dpString("contract");
		login.location=dpIter.dpString("location");
		
		privilege.code=dpIter.dpString("privilegeCode");
		privilege.name=dpIter.dpString("privilegeName");
		
		licenseYear.status = "Active";  //need to add logical handle the inactive status when there is business need
		licenseYear.locationClass = dpIter.dpString("locationClass");
		licenseYear.licYear = dpIter.dpString("licenseYear");
		if(licenseYear.licYear.trim().length() < 1) {
			licenseYear.licYear = String.valueOf(DateFunctions.getCurrentYear());
		}
		
		licenseYear.sellFromDate = dpIter.dpString("sellFromDate");
		if(licenseYear.sellFromDate.trim().length() < 1) {
			licenseYear.sellFromDate = DateFunctions.getDateAfterToday(-2);
		}
		licenseYear.sellFromTime = dpIter.dpString("sellFromHHMM");
		if(licenseYear.sellFromTime.trim().length() < 1) {
			licenseYear.sellFromTime = "12:00";
		}
		licenseYear.sellFromAmPm = dpIter.dpString("sellFromAMPM");
		
		licenseYear.sellToDate = dpIter.dpString("sellToDate");
		if(licenseYear.sellToDate.trim().length() < 1) {
			licenseYear.sellToDate = DateFunctions.getDateAfterToday(365, timeZone);
		}
		licenseYear.sellToTime = dpIter.dpString("sellToHHMM");
		if(licenseYear.sellToTime.trim().length() < 1) {
			licenseYear.sellToTime = "11:59";
		}
		licenseYear.sellToAmPm = dpIter.dpString("sellToAMPM");
		
		licenseYear.validFromDate = dpIter.dpString("validFromDate");
		licenseYear.validFromTime = dpIter.dpString("validFromHHMM");
		licenseYear.validFromAmPm = dpIter.dpString("validFromAMPM");
		
		licenseYear.validToDate = dpIter.dpString("validToDate");
		licenseYear.validToTime = dpIter.dpString("validToHHMM");
		licenseYear.validToAmPm = dpIter.dpString("validToAMPM");
		
//		this.formatLicenseYearInfo();
		
		logMsg[0] = String.valueOf(cursor);
		logMsg[1] = privilege.code;
		logMsg[2] = licenseYear.licYear;
		logMsg[3] = licenseYear.locationClass;
	}
	
//	private void formatLicenseYearInfo(){
//		licenseYear.sellFromDate = licenseYear.sellFromDate.length() == 0?DateFunctions.getDateAfterToday(-1):licenseYear.sellFromDate;
//		licenseYear.sellFromTime = licenseYear.sellFromTime.length() == 0?"11:59":licenseYear.sellFromTime;
//		licenseYear.sellFromAmPm = licenseYear.sellFromAmPm.length() == 0?"AM":licenseYear.sellFromAmPm;
//		
//		licenseYear.sellToDate = licenseYear.sellToDate.length() == 0?DateFunctions.getDateAfterToday(356):licenseYear.sellToDate;
//		licenseYear.sellToTime = licenseYear.sellToTime.length() == 0?"11:59":licenseYear.sellToTime;
//		licenseYear.sellToAmPm = licenseYear.sellToAmPm.length() == 0?"PM":licenseYear.sellToAmPm;
//		
//		licenseYear.validFromAmPm = licenseYear.validFromAmPm.length() == 0?"AM":licenseYear.validFromAmPm;
//		
//		licenseYear.validToAmPm = licenseYear.validToAmPm.length() == 0?"PM":licenseYear.validToAmPm;
//	}
}
