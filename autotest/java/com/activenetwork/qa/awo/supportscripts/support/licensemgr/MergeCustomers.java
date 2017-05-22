package com.activenetwork.qa.awo.supportscripts.support.licensemgr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerMergeOptionWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomersSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrMergeCustomerDetails;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrTopMenuPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.TestDriverUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.SysInfo;
import com.activenetwork.qa.testapi.util.TestProperty;

public class MergeCustomers extends SupportCase {
	
	private static final int NOTRUN=0;
	private static final int INPROGRESS=1;
	private static final int SUCCESS=2;
	private static final int FAILED=3;
	
	private boolean loggedIn = false;// Don't login.
	private LoginInfo login = new LoginInfo();

	private LicenseManager lm = LicenseManager.getInstance();
	private String from_cust="",to_cust="";
	private int dispatch_id=0;
	private int count=0;
	private String hostname=SysInfo.getHostName().toLowerCase();
	
	public void execute() {
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
		
		try {
			gotoHomePage();
		} catch(Exception e) {
			lm.loginLicenseManager(login);
		}
		
		try {
			mergeCustomers();
			processPassed();
		} catch(Exception e) {
			processFailed(e);
		}
		reset();
	}
	
	private void reset() {
		from_cust="";
		to_cust="";
	}

	@Override
	public void wrapParameters(Object[] param) {
		TestProperty.putProperty("role.auto", "false");
		
		cursor = 0;
		startpoint = 0; // the start point in the data pool
		endpoint = 0; // the end point in the data pool
		
		dataSource = "support_merge_cust_ab";
		logFile="z:/AB_Merge_Cust/"+SysInfo.getHostName()+".log";
		
		dispatch_id=getTestRunnerID();
		logger.info("Got dispatch id#"+dispatch_id);
		
		
		login.url = "https://migracan.reserveamerica.com";
		login.userName="ao-automerge2";
		login.password="q@t3st3r";
//		login.url = "https://web01.qa.reserveamerica.com:6401";
//		login.userName="ra-jdu";
//		login.password="auto1234";

		login.role="imp-admin-role";
//		login.role="AB - Admin";
//		login.location="ALBERTA BEACH AUTO SERVICES LTD.";
		login.location="Alberta HF Agency";
		login.contract="AB Contract";
		
		
		if(param!=null && param.length>0) {//overwrite 	
			String temp=(String) TestDriverUtil.getParameter(param, "user");
			if(StringUtil.notEmpty(temp)) {
				login.userName=temp;
			}
			
			temp=(String) TestDriverUtil.getParameter(param, "pw");
			if(StringUtil.notEmpty(temp)) {
				login.password=temp;
			}
			
			temp=(String) TestDriverUtil.getParameter(param, "url");
			if(StringUtil.notEmpty(temp)) {
				login.url=temp;
			}
			
			temp=(String) TestDriverUtil.getParameter(param, "role");
			if(StringUtil.notEmpty(temp)) {
				login.role=temp;
			}
			
			temp=(String) TestDriverUtil.getParameter(param, "loc");
			if(StringUtil.notEmpty(temp)) {
				login.location=temp;
			}
			
			
			if(StringUtil.isEmpty(login.userName) || StringUtil.isEmpty(login.password) || StringUtil.isEmpty(login.url)) {
				throw new ItemNotFoundException("Missing user name, password and url");
			}
			
			temp=TestDriverUtil.getParameter(param, "table");
			if(StringUtil.notEmpty(temp)) {
				dataSource=temp;
			}
		}
		
		logMsg=new String[4];
		logMsg[0] = "Index";
		logMsg[1] = "To_MDWFP";
		logMsg[2] = "From_MDWFP";
		logMsg[3] = "Result";
	}
	
	public void getNextData() {
		from_cust=datasFromDB.get("from_cust_number");
		to_cust=datasFromDB.get("to_cust_number");
		
		db.resetDefaultDB();
		String query="update "+dataSource+" set status="+INPROGRESS+", start_time=now() where FROM_CUST_NUMBER='"+from_cust+"' and TO_CUST_NUMBER='"+to_cust+"'";
				
		db.executeUpdate(query);
		
		
		logMsg[0] = datasFromDB.get("id");
		logMsg[1] = to_cust;
		logMsg[2] = from_cust;
				
	}
	
	public void gotoHomePage() {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrTopMenuPage topMenuPg = LicMgrTopMenuPage.getInstance();

		logger.info("Go back to License Manager home page.");
		if (!homePg.exists()) {
			topMenuPg.clickHome();
			ajax.waitLoading();
			browser.waitExists(60, homePg);
		}
	}
	
	public void mergeCustomers() {
		LicMgrCustomerDetailsPage detailsPg = LicMgrCustomerDetailsPage.getInstance();
		LicMgrMergeCustomerDetails mergeDetail = LicMgrMergeCustomerDetails.getInstance();
		LicMgrCustomerMergeOptionWidget mergeOption=LicMgrCustomerMergeOptionWidget.getInstance();
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrCustomersSearchPage custSchPg = LicMgrCustomersSearchPage.getInstance();
		LicMgrConfirmDialogWidget dialog=LicMgrConfirmDialogWidget.getInstance();

		logger.info("Goto Customer Search/List page from customers quick search area.");

		homePg.selectIdentifierType("WIN #");
		homePg.setIdentifierNum(to_cust);
		homePg.clickSearchInCustomers();
		ajax.waitLoading();
		Object page=browser.waitExists(custSchPg,detailsPg);
		
		if(page==custSchPg) {
			String msg=custSchPg.getWarnMes();
			handledFailed(msg);
		}
		
		String status=detailsPg.getStatus();
		
		if(!status.equalsIgnoreCase("Active")) {
			handledFailed("Customer's status '"+status+"' is not active.");
		}
		
		if(!detailsPg.isMergeEnabled()) {
			handledFailed("Merge button is not enabled in Customer Details page.");
		}
		logger.info("Start Merge");
		detailsPg.clickMerge();
		ajax.waitLoading();
		mergeOption.waitLoading();
		mergeOption.selectMergeWithMDWFP();
		ajax.waitLoading();
		mergeOption.setMDWFP(from_cust);
		mergeOption.clickOK();
		ajax.waitLoading();

		page=browser.waitExists(mergeDetail,mergeOption);
		if(page == mergeOption) {			
			logMsg[3]="Failed";
			String msg=mergeOption.getErrorMsg();
			mergeOption.clickCancel();
			handledFailed(msg);
		}
		mergeDetail.selectCustomerToKeep(0);
		ajax.waitLoading();
		
		//verifications
//		String fn1=mergeDetail.getFirstName(0);
//		String ln1=mergeDetail.getLastName(0);
//		String dob1=mergeDetail.getDateOfBirth(0);
		
//		if(!(fn1.equalsIgnoreCase(fname) && ln1.equalsIgnoreCase(lname) && DateFunctions.compareDates(dob1, dob)==0)) {
//			handledFailed("To customer '"+fn1+" "+ln1+" "+dob1+"' is not expected '"+custInfo+"'");
//		} else {
//			String fn2=mergeDetail.getFirstName(1);
//			String ln2=mergeDetail.getLastName(1);
//			String dob2=mergeDetail.getDateOfBirth(1);
//			if(!(fn2.equalsIgnoreCase(fname) && ln2.equalsIgnoreCase(lname) && DateFunctions.compareDates(dob2, dob)==0)) {
//				handledFailed("From customer '"+fn2+" "+ln2+" "+dob2+"' is not expected '"+custInfo+"'");
//			}
//			
//		}
		int count =4;
		boolean mergeExists=false;
		while(!(mergeExists=mergeDetail.isMergeButtonExists()) && count>0) {
			mergeDetail.clickNext();
			ajax.waitLoading();
			boolean errorDialogExists=browser.tentativeWaitExists(1, dialog);
			String error=null;
			if(errorDialogExists) {
				error=dialog.getMessage();
				dialog.clickOK();
				ajax.waitLoading();
			} else {
				error=mergeDetail.getErrorMsg();
			}
			if(error!=null && error.trim().length()>0) {
				handledFailed("Get page error: '"+error+"'");
			}
			
			if(count==4) { //merge customer profiles
				List<String> merged=mergeDetail.mergeNameDOB();
				merged.addAll(mergeDetail.mergePhoneEmail());
				merged.addAll(mergeDetail.mergeHunterEligibility());
				merged.addAll(mergeDetail.mergePhysicalAddress());
				merged.addAll(mergeDetail.mergeMailingAddress());
				recordMergedProfile(merged);
			}
			
			count--;
			
			
		}
		
		if(!mergeExists) {
			handledFailed("Merge button doesn't display somehow.");
		}
		
		//merge identifiers
		if(mergeDetail.isIdentifiersTableExist()) {
//			List<CustomerIdentifier[]> list=mergeDetail.getCustomerIdentifiers();
//			CustomerIdentifier[] to_identifiers=list.get(0);
//			CustomerIdentifier[] from_identifiers=list.get(1);
//			List<CustomerIdentifier> mergedList=mergeDetail.mergeIdentifiers(to_identifiers, from_identifiers);
			List<CustomerIdentifier> mergedList=mergeDetail.mergeAllCustomerIdentifiers(1);
			recordMergedIdentifiers(mergedList);
		}
		
		//merge points
		if(mergeDetail.isPointsTableExist()) {
			Map<String,String> mergedPoints=mergeDetail.MergeAllePointsBalance(1);
			recordMergedPoints(mergedPoints);
		}		
		
		mergeDetail.clickMerge();
		ajax.waitLoading();
		detailsPg.waitLoading();	
	}
		
	protected void handledFailed(String msg) {	
		throw new ItemNotFoundException(msg);
	}
	
	private void processFailed(Exception e) {
		String msg=e.getMessage();
		logger.info("Merge failed due to "+msg);
		logMsg[3]="Failed";
		db.resetDefaultDB();
		String notes=msg==null?"null":msg.replaceAll("'", "''").replaceAll("\"", "\\\"");
		String query="update "+dataSource+" set status="+FAILED+", notes='"+notes+"', last_update=now() where FROM_CUST_NUMBER='"+from_cust+"' and TO_CUST_NUMBER='"+to_cust+"'";
		db.executeUpdate(query);
		throw new ItemNotFoundException(msg);
	}
	
	private void recordMergedPoints(Map<String, String> points) {
		if(!points.isEmpty()) {
			db.resetDefaultDB();
			
			String ps=points.toString();
			ps=ps.replaceAll("'", "''").replaceAll("\"", "\\\"");
			String query="update "+dataSource+" set points_merged='"+ps+"', last_update=now() where FROM_CUST_NUMBER='"+from_cust+"' and TO_CUST_NUMBER='"+to_cust+"'";
			db.executeUpdate(query);
		}
	}
	
	private void recordMergedProfile(List<String> merged) {
		if(!merged.isEmpty()) {
			db.resetDefaultDB();
			
			String ps=merged.toString();
			ps=ps.replaceAll("'", "''").replaceAll("\"", "\\\"");
			String query="update "+dataSource+" set profile_merged='"+ps+"', last_update=now() where FROM_CUST_NUMBER='"+from_cust+"' and TO_CUST_NUMBER='"+to_cust+"'";
			db.executeUpdate(query);
		}
	}
	
	private void recordMergedIdentifiers(List<CustomerIdentifier> mergedList) {		
		if(mergedList!=null && mergedList.size()>0) {
			db.resetDefaultDB();
			List<String> list=new ArrayList<String>();
			
			for(CustomerIdentifier idf:mergedList) {
				list.add(idf.identifierType);
			}
			
			String identifiers=list.toString();
			identifiers=identifiers.replaceAll("'", "''").replaceAll("\"", "\\\"");
			
			String query="update "+dataSource+" set identifier_merged='"+identifiers+"', last_update=now() where FROM_CUST_NUMBER='"+from_cust+"' and TO_CUST_NUMBER='"+to_cust+"'";
			db.executeUpdate(query);
		}
		
	}
	
	private void recordSwitchFromAndToCustomer(String msg) {
		db.resetDefaultDB();
		
		String query="update "+dataSource+" set notes='"+msg+"', last_update=now() where FROM_CUST_NUMBER='"+from_cust+"' and TO_CUST_NUMBER='"+to_cust+"'";
		db.executeUpdate(query);
	}
	
	
	private void processPassed() {
		logger.info("Merge is successful");
		logMsg[3]="Successful";
		db.resetDefaultDB();
		String query="update "+dataSource+" set status="+SUCCESS+",last_update=now() where FROM_CUST_NUMBER='"+from_cust+"' and TO_CUST_NUMBER='"+to_cust+"'";
				
		db.executeUpdate(query);
	}
	
	protected List<HashMap<String,String>> readDataFromDB(){
		db.resetDefaultDB();
		String query="select id,FROM_CUST_NUMBER,TO_CUST_NUMBER from "+dataSource+" where status="+NOTRUN+" and dispatch_id="+dispatch_id+" order by id";
				
		return db.executeQuery(query);
		
	}
	
	protected int getTestRunnerID() {
		
		if(hostname.matches("wd\\d+")) {
			return 100;
		}
		db.resetDefaultDB();
		String query="select id from testrunner where hostname='"+hostname.toLowerCase()+"'";
		String id=db.executeQuery(query, "id", 0);
		return Integer.parseInt(id);
	}

}
