package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: add a facility.
 * @author Phoebe
 * @Date  April 21, 2014
 */
public class AddFacilityFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private FacilityData facilityInfo = new FacilityData();
	private boolean loggedIn = false;
	private String location = "";
	private String contract = "";
	@Override
	public void execute() {
		if (!login.contract.equals(contract) && loggedIn && isBrowserOpened) {
			lm.logOutLicenseManager();
			loggedIn= false;
		}
		if(login.contract.equals(contract) && loggedIn && isBrowserOpened){
			if(!login.location.equals(location)){
				lm.switchLocationInHomePage(login.location.replace("/", "-"));
			}
		}
		if (!loggedIn || !isBrowserOpened) { 
			lm.loginLicenseManager(login);
			loggedIn= true;
		}
		
		contract = login.contract;
		location = login.location;

		//Add facility
		lm.gotoFacilityListPgFromHomePg();
		facilityInfo.facilityID = lm.addFacility(facilityInfo);
		newAddValue = facilityInfo.facilityID;	
		if(!facilityInfo.facilityID.matches("\\d+")){
			throw new ErrorOnPageException("Failed to add new facility:" + facilityInfo.facilityName);
		}else{
			newAddValue = facilityInfo.facilityID;
		}
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		facilityInfo = (FacilityData)param[1];
		
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
	}
}
