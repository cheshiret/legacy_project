package com.activenetwork.qa.awo.supportscripts.function.license;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBondInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorBondsSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorChangeStoreBondAssignmentWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorViewStoreBondAssignmentWidget;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class BondStoreAssignmentFunction extends FunctionCase{
	LoginInfo login=new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private VendorBondInfo bondInfo = new VendorBondInfo();
	private String agentName;
	private String vendorNum;
	LicMgrVendorBondsSubPage vendorBondPg = LicMgrVendorBondsSubPage.getInstance();
	private String assignId;
	private boolean loggedin=false;
	private String contract = "";
	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private String location;
	

	@Override
	public void wrapParameters(Object[] param) {
		String env = TestProperty.getProperty("target_env");
		login = (LoginInfo)param[0];
		vendorNum = (String)param[1];
		agentName = (String)param[2];
		bondInfo = (VendorBondInfo)param[3];
		
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
		lm.gotoVendorDetailsPgFromTopMenu(vendorNum);
		lm.gotoBondSubTabFromVendorDetailPg();
		bondInfo.id = this.getVendorBondIdByBondNum(bondInfo.bondNum,vendorNum);
		if(StringUtil.isEmpty(bondInfo.id)){
			throw new ErrorOnPageException("The bond that store will be assigned to is not exist,bond account="+bondInfo.bondNum);
		}
		lm.changeAgentBondAssignmentByName(agentName, bondInfo.bondNum, bondInfo.issuer);
		this.verifyResule();
		newAddValue = assignId;
	}
	
	public void verifyResule(){
		boolean passed = true;
		LicMgrVendorChangeStoreBondAssignmentWidget changPg = LicMgrVendorChangeStoreBondAssignmentWidget
				.getInstance();
		LicMgrVendorViewStoreBondAssignmentWidget viewPg = LicMgrVendorViewStoreBondAssignmentWidget.getInstance();
		if(changPg.exists()){
			passed = false;
			logger.error("[FAILED]Assign bond to store failed:Vendor number="+vendorNum+",bond account="+bondInfo.bondNum+"error");
		}else{
			lm.gotoViewAgentBondsAssigmentsPg(bondInfo.id);
			assignId = viewPg.getAssignIdByAgentName(agentName);
			if(StringUtil.notEmpty(assignId)){
				logger.info("[PASSED]Assign bond to store successful:Vendor number="+vendorNum+",bond account="+bondInfo.bondNum);
			}else{
				passed = false;
				logger.error("[FAILED]Assign bond to store failed:Vendor number="+vendorNum+",bond account="+bondInfo.bondNum+" error");
			}
			viewPg.clickOK();
			ajax.waitLoading();
		}
		if(!passed){
			throw new ErrorOnPageException("Assign bond to store failed, please see the log above!");
		}
	}
	
	private String getVendorBondIdByBondNum(String bondNum,String vendorNum){
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();;
		String schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		String vendorID = lm.getVendorID(schema, vendorNum, "");
		db.resetSchema(schema);
		String sql = "select ID from F_VENDOR_BOND where bond_num =  '"+bondNum+"' and vendor_id="+vendorID;
		logger.info("Execute SQL:"+sql);
		List<String> results = db.executeQuery(sql, "ID");
		if (results.isEmpty()) {
			return StringUtil.EMPTY;
		} else {
			return results.get(0);
		}
	}
	
}
