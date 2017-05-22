package com.activenetwork.qa.awo.supportscripts.support.licensemgr;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductStoreAssignmentsPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author jwang8
 * @Date  Mar 19, 2012
 */
public class AssignPriToStore extends SupportCase{

	private boolean loggedIn = false;// Don't login.
	private LoginInfo login = new LoginInfo();
	private String contract = "";
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrPrivilegeProductStoreAssignmentsPage privilegeStoreAssignmentsPg = LicMgrPrivilegeProductStoreAssignmentsPage.getInstance();
	private String priCode = "";
	private String previousProdCode = "";
	private String[] locationClass = new String[]{};
	
	public void execute() {
		if (!contract.equalsIgnoreCase(login.contract) && loggedIn) {
			lm.logOutLicenseManager();
			loggedIn = false;
		}
		if((!loggedIn )|| (loggedIn && !privilegeStoreAssignmentsPg.exists())){
			lm.loginLicenseManager(login);
			loggedIn = true;
		}
		if(!previousProdCode.equalsIgnoreCase(priCode)) {
			lm.gotoPrivilegeSearchListPageFromTopMenu();
			lm.gotoPrivilegeDetailsPageFromProductListPage(priCode);
		}
		
		lm.assignPrivilegeToStoresThruLocationClass(locationClass);
		
		this.verifyResule();
		
		contract = login.contract;
		previousProdCode = priCode;
	}

	@Override
	public void wrapParameters(Object[] param) {
		cursor = 0;
		startpoint = 72; // the start point in the data pool
		endpoint = 72; // the end point in the data pool
		
		dataSource = casePath + "/" + caseName;
		
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		
		logMsg=new String[4];
		logMsg[0] = "Index";
		logMsg[1] = "PrivilegeCode";
		logMsg[2] = "LocationClass";
		logMsg[3] = "Result";
	}
	
	public void getNextData() {
		login.contract=dpIter.dpString("contract");
		login.location=dpIter.dpString("location");
		
		priCode = dpIter.dpString("priCode");
		locationClass = this.splitTextByComma(dpIter.dpString("locationClass"));
		
		logMsg[0] = String.valueOf(cursor);
		logMsg[1] = priCode;
		logMsg[2] = locationClass[0];
	}

	private String[] splitTextByComma(String text){
		String[] list = new String[]{};
		if(text.contains(",")){
			list =  text.split(",", 0);
		}else if(!text.equals("")){
			list = new String[]{text};
		}
		return list;
	}
	
	private void verifyResule(){
		if(!privilegeStoreAssignmentsPg.exists()){
			logger.error("Add privilege pricing failed:privilege code="+priCode+",document template="+locationClass+"");
			logMsg[3]="Failed";
		}else{
			logMsg[3]="Passed";
		}
	}
}
