package com.activenetwork.qa.awo.supportscripts.function.license;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleProductStoreAssignmentsPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AssignVehicleToStoreFunction extends FunctionCase{
	LoginInfo login=new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private String vehicleCode = "";
	private String locationClasses = "";
	LicMgrVehicleProductStoreAssignmentsPage vehicleStoreAssignmentPg = LicMgrVehicleProductStoreAssignmentsPage.getInstance();
	String assignLocAndNum = "";
	private boolean loggedin=false;
	private String contract = "";
	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private String location;
	@Override
	public void wrapParameters(Object[] param) {
		String env = TestProperty.getProperty("target_env");
		login = (LoginInfo)param[0];
		vehicleCode = (String)param[1];
		locationClasses = (String)param[2];
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
	}
	@Override
	public void execute() {
		if (!login.contract.equals(contract) && loggedin && isBrowserOpened) {
			lm.logOutLicenseManager();
			loggedin= false;
		}
		if(login.contract.equals(contract) && loggedin && isBrowserOpened){
			if(!login.location.equals(location)){
				lm.switchLocationInHomePage(login.location.replace("/", "-"));
			}
		}
		if (!loggedin || !isBrowserOpened) { 
			lm.loginLicenseManager(login);
			loggedin= true;
		}
		if (!homePg.exists()) {
			lm.gotoHomePage();
		}
		contract = login.contract;
		location = login.location;
		lm.gotoVehicleSearchListPageFromTopMenu();
		lm.gotoVehicleProductDetailsPageFromListPage(vehicleCode);
		lm.assignVehicleToStoresThruLocationClass(locationClasses.split(","));
		this.verifyResule();
		newAddValue = assignLocAndNum;
	
	}
	
	private void verifyResule(){
		boolean passed = true;
		String errorMsg = "";
		if(!vehicleStoreAssignmentPg.exists()){
			logger.error("[FAILED]Add vehicle failed: vehicle code= "+vehicleCode+",Location Classes= "+locationClasses);
		}else{
			String[] locaClassArr = locationClasses.split(",");
			for(String lc:locaClassArr){
				String lCode = lc.split("-")[0].trim();
				List<String> assignInfo = vehicleStoreAssignmentPg.getLocationClassStoreAssignmentInfo(lc);
				String locationTotalNum = assignInfo.get(3);
				String AssignedlocationNum = assignInfo.get(4);
				if( Integer.parseInt(locationTotalNum) != Integer.parseInt(AssignedlocationNum)){
					passed = false;
					errorMsg += "Assign vehicle to store failed for location class:" + lc + ";";
				}else{
					assignLocAndNum += lCode + ":" + AssignedlocationNum + ",";
				}
			}
			if(!passed){
				logger.error("[FAILED]" + errorMsg);
				throw new ErrorOnPageException("Assign vehicle to store failed, please see the log above!");
			}else{
				logger.info("[PASSED]Add vehicle passed: vehicle code= "+vehicleCode+",Location Classes= "+locationClasses);
			}
		}
	}
}
