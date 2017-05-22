/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.support.licensemgr;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrCreateNewPriviledgePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;


/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author szhou
 * @Date  Feb 1, 2012
 */
public class AddPrivilegeProduct extends SupportCase{
	private boolean loggedIn = false;// Don't login.
	private LoginInfo login = new LoginInfo();
	private String contract = "";
	private PrivilegeInfo privilege = new PrivilegeInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrPrivilegesListPage privilegeListPage = LicMgrPrivilegesListPage.getInstance();
	
	@Override
	public void execute() {
		//log into license Manger
		if (!contract.equalsIgnoreCase(login.contract) && loggedIn) {
			lm.logOutLicenseManager();
			loggedIn = false;
		}
		if((!loggedIn )|| (loggedIn && !privilegeListPage.exists())){
			lm.loginLicenseManager(login);
			loggedIn=true;
			lm.gotoPrivilegeSearchListPageFromTopMenu();
		}
		
		// add privilege product basic information
		lm.addPrivilegeProduct(privilege);
		this.verifyResult();
		
		// in order to run next data in data pool
		contract=login.contract;
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		startpoint = 128; // the start point in the data pool
		endpoint = 128; // the end point in the data pool

		dataSource = casePath + "/" + caseName;
		
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		
		logMsg=new String[4];
		logMsg[0] = "Index";
		logMsg[1] = "PrivilegeCode";
		logMsg[2] = "PrivilegeName";
		logMsg[3] = "Result";
	}
	
	public void verifyResult(){
		LicMgrCreateNewPriviledgePage createNewPrivilegeProductPage = LicMgrCreateNewPriviledgePage.getInstance();	    
		if(createNewPrivilegeProductPage.exists()){
			 logger.error("create privilege product failed:code="+privilege.code+",name="+privilege.name+createNewPrivilegeProductPage.getWarningMessage());
			 logMsg[3] = "Failed";
	    }else{
	    	 logMsg[3] = "Passed";
	    }
		
		createNewPrivilegeProductPage.clickCancel();
		privilegeListPage.waitLoading();
	}

	@Override
	public void getNextData() {
		login.contract=dpIter.dpString("contract");
		login.location=dpIter.dpString("location");
		
		privilege.code=dpIter.dpString("code");
		privilege.name=dpIter.dpString("name");
		privilege.legalName = datasFromDB.get("legalName");
		privilege.productGroup=dpIter.dpString("productGroup");
		privilege.validFromDateCalculation = dpIter.dpString("validFromDateCalculation");
		privilege.promptIndicator = dpIter.dpString("promptIndicator");
		privilege.validToDateCalculation = dpIter.dpString("validToDateCalculation");
		privilege.validDaysYears = dpIter.dpString("validDaysYears");
		privilege.dateUnitOfValidToDate = dpIter.dpString("dateUnitOfValidToDate");
		privilege.renewalDays = dpIter.dpString("renewalDays");
		privilege.validToAge = dpIter.dpString("validToAge");
		if(!"".equals(dpIter.dpString("validDatePrinting"))){
			privilege.validDatePrinting = dpIter.dpString("validDatePrinting").split("\\|");
		}
		if(!"".equals(dpIter.dpString("customerClass"))){
			privilege.customerClasses = dpIter.dpString("customerClass").split("\\|");
		}
		privilege.authorizationQuantity = dpIter.dpString("authorizationQuantity");
		privilege.invType =dpIter.dpString("inventoryType");
		privilege.displayCategory = dpIter.dpString("displayCategory");
		privilege.displaySubCategory = dpIter.dpString("displaySubCategory");
		privilege.reportCategory = dpIter.dpString("reportCategory");
		
		logMsg[0] = String.valueOf(cursor);
		logMsg[1] = privilege.code;
		logMsg[2] = privilege.name;
	}
}
