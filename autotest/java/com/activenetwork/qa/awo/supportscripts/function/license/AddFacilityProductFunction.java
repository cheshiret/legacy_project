package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.FacilityPrdAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.SearchFacilityAttr;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrFacilityListPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: add a product for facility.
 * @author Phoebe
 * @Date  April 21, 2014
 */
public class AddFacilityProductFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private Data<FacilityPrdAttr> fpd = new Data<FacilityPrdAttr>();
	private Data<SearchFacilityAttr> searchFacility = new Data<SearchFacilityAttr>();
	private LicMgrFacilityListPage facilityListPg = LicMgrFacilityListPage.getInstance();
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
		
		//Add facility product
		lm.gotoFacilityListPgFromHomePg();
		if(!facilityListPg.hasFacility(searchFacility)){
			throw new ErrorOnPageException("The facility with name:" + searchFacility.getValue(SearchFacilityAttr.facilityName) + " does not exist,please set up" +
					"facility first!");
		}

		//Add facility product
		lm.gotoFacilityPrdPgFromFacilityListPg(searchFacility);
		String errMsg = lm.addFacilityPrd(fpd);
		if(StringUtil.notEmpty(errMsg)){
			newAddValue = errMsg;	
			throw new ErrorOnPageException("Failed to add product for facility due to:" + errMsg);
		}else{
			newAddValue = (String) fpd.getValue(FacilityPrdAttr.prdCode);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		fpd = (Data<FacilityPrdAttr>)param[1];
		searchFacility = (Data<SearchFacilityAttr>)param[2];
		
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
	}

}
