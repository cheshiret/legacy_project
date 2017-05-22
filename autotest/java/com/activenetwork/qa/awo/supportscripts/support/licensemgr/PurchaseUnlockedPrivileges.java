package com.activenetwork.qa.awo.supportscripts.support.licensemgr;

import java.util.HashMap;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.TestDriverUtil;
import com.activenetwork.qa.testapi.util.SysInfo;
import com.activenetwork.qa.testapi.util.TestProperty;

public class PurchaseUnlockedPrivileges extends SupportCase{
	private boolean loggedIn = false;// Don't login.
	private LoginInfo login = new LoginInfo();
	private Customer cust=new Customer();
	private PrivilegeInfo privilege;//=new PrivilegeInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private String halid="",licenseCode="",licenseYear="",wmz="";
	private Map<String,String> resStatus=new HashMap<String,String>();
	private Payment pay=new Payment("Cash");
	private int count=0;
	@Override
	public void wrapParameters(Object[] param) {
		fromDB=false;
		TestProperty.putProperty("role.auto", "false");
		TestProperty.putProperty("control.printdoc", "false");
		
		startpoint = 3; // the start point in the data pool
		endpoint = 3; // the end point in the data pool
		
		if(param !=null && param.length>0) {
			startpoint=Integer.parseInt(TestDriverUtil.getParameter(param, "startpoint", startpoint+""));
			endpoint=Integer.parseInt(TestDriverUtil.getParameter(param, "endpoint", endpoint+""));
		}
		
		dataSource = casePath + "/PurchaseUnlockedPrivilege";
		logFile="X:/SK_support/SK_UnlockPriv_"+SysInfo.getHostName()+".log";
		
		
		login.url="https://uatcan.reserveamerica.com/";
		
		login.userName="ra-jdu";
		login.password="l0stP@sswd";
		login.role="SK - Compliance Field Offices";
		login.location="Ministry of Environment - Regina(Store Loc)";
		login.contract="SK Contract";
		
		resStatus.put("232", "Saskatchewan Resident");
		resStatus.put("249", "Saskatchewan Resident");
		resStatus.put("255", "Saskatchewan Resident");
		resStatus.put("265", "Saskatchewan Resident");
		resStatus.put("233", "Canadian Resident (Non-Saskatchewan)");
		
		cust.identifier.identifierType="HAL ID #";
		
		logMsg=new String[7];
		logMsg[0] = "Index";
		logMsg[1] = "HALID";
		logMsg[2] = "WMZ";
		logMsg[3] = "LICENCE CODE";
		logMsg[4] = "LICENCE YEAR";
		logMsg[5] = "ORDER#";
		logMsg[6] = "result";
		
	}

	@Override
	public void getNextData() {
		halid=this.dpIter.dpString("HALID");
		wmz=dpIter.dpString("WMZ");
		licenseCode=dpIter.dpString("LICENCE CODE");
		licenseYear=dpIter.dpString("LICENCE YEAR");
		
		cust.identifier.identifierNum=halid;
		cust.residencyStatus=resStatus.get(licenseCode);
		
		privilege=new PrivilegeInfo();
		privilege.purchasingName="<regex>"+licenseCode+"-Draw.+ "+wmz+" .*";
		privilege.licenseYear=licenseYear;
		privilege.useDefaultInventory=true;
		
		logMsg[1]=halid;
		logMsg[2]=wmz;
		logMsg[3]=licenseCode;
		logMsg[4]=licenseYear;
		logMsg[5]="N/A";
		logMsg[6]="Failed";

	}

	@Override
	public void execute() {
		logMsg[0]=cursor+"";
		count++;
		
		if((!loggedIn )){
			lm.loginLicenseManager(login);
			loggedIn = true;
		} else if(count%10==0) {
			try{
				lm.logOutLicenseManager();
			} catch(Exception e){}
			lm.loginLicenseManager(login);
			loggedIn = true;
		}
		
		lm.makePrivilegeToCartFromCustomerQuickSearch(cust, privilege);
		String orderNum=lm.processOrderCart(pay);
		
		logMsg[5]=orderNum;
		logMsg[6]="Successful";
		
		if(count!=0 && count % 20==0) {
			lm.tryToSignOut();
			loggedIn=false;
		}
		
	}
	
	@Override
	protected void onError() {
		loggedIn=false;
		lm.tryToSignOut();
		
	}

}
