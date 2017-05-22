package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrCreateNewVehiclePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehiclesListPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddVehicleProductFunction extends FunctionCase{
	LoginInfo login=new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private VehicleRTI vehicle = new VehicleRTI();
	private boolean loggedin=false;
	private String contract = "";
	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private String location, schema;
	
	@Override
	public void wrapParameters(Object[] param) {
		String env = TestProperty.getProperty("target_env");
		login = (LoginInfo)param[0];
		vehicle = (VehicleRTI)param[1];
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + login.contract.split("Contract")[0].trim();
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
		lm.gotoProductSearchListPageFromTopMenu("Vehicles");
		if(!lm.verifyProductExistInSys(schema, vehicle.getPrdCode(), vehicle.getPrdName())) {
			String msg = lm.addVehicleProduct(vehicle);
			verifyResultSuccess(msg);
		} else logger.info("The Vehicle(CD#=" + vehicle.getPrdCode() + ") already exists in System. No need to add.");
		newAddValue = vehicle.getPrdCode();
	
	}
	
	public void verifyResultSuccess(String msg) {
		boolean passed =true;
		LicMgrVehiclesListPage listPage=LicMgrVehiclesListPage.getInstance();
		LicMgrCreateNewVehiclePage vehicleInfoPage=LicMgrCreateNewVehiclePage.getInstance();
		if(!listPage.exists()) {
			 logger.error("[FAILED]Create vehicle product failed:code="+vehicle.getPrdCode()+",name="+vehicle.getPrdName() + "; error msg:" + vehicleInfoPage.getWarningMessage());
			 passed = false;
			 vehicleInfoPage.clickCancel();
			 listPage.waitLoading();
	    } else {
	    	 if (msg.matches("[0-9]+")) {
	    		 vehicle.setPrdId(msg);
	    		 logger.info("[PASSED]Create vehicle product failed:code="+vehicle.getPrdCode()+",name="+vehicle.getPrdName());
	    	 }else{
		    		logger.error("[FAILED]Create vehicle product failed:code="+vehicle.getPrdCode()+",name="+vehicle.getPrdName());
		    	 }
	    }
		if(!passed){
			throw new ErrorOnPageException("Create vehicle product failed, please see the log above!");
		}
	}
}
