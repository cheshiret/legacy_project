/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.certification;

import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Certification;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.SystemManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerCertificationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerChangeHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.systemManager.SysMgrServiceDaemonsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * @Preconditions:
 * 1.When compare date time, we use EDT as the .
 * @SPEC:Expire Customer Certification.doc
 * @Task#:AUTO-719
 * 
 * @author asun
 * @Date Aug 31, 2011
 */
public class ExpireCustCertifications extends LicenseManagerTestCase {

	private SystemManager sm = SystemManager.getInstance();
	private LoginInfo sysLogin = new LoginInfo();
	private String runAt;
	private String runAtTime;
	private String daemonName;
	private String licType;
	private String licNum;
	private String custClass;
	private Certification certification = new Certification();
	private String today;
	private TimeZone timeZone_EDT;
	private TimeZone ms_TimeZone;
	private String scheduleTime_Today;
	private String systemUser;

	@Override
	public void execute() {
		//Get the daemon run time from System manager
		sm.loginSystemManager(sysLogin);
		sm.gotoServiceDaemonsPage();
		runAt = this.getDaemonRunAtTime(daemonName);
		sm.logoutSystemManager();
		
		runAtTime=runAt.replaceAll("[A-Za-z]+", "").trim();
		scheduleTime_Today = DateFunctions.formatDate(today+" "+runAtTime , "MM/dd/yyyy h:mm","MM/dd/yyyy hh:mm");
		
		//Verify Expired Certification
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		lm.gotoCuscomerDetailsByLincenseType(licType, licNum, custClass);
		this.verifyExpireCertification(certification, schema);
		lm.removeAllCustomerCertifications(true);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		sysLogin.url = login.url + "/SystemMgrLogin.do";
		sysLogin.userName = TestProperty.getProperty("orms.sm.user");
		sysLogin.password = TestProperty.getProperty("orms.sm.pw");
		sysLogin.contract = "MS Contract";
		sysLogin.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		schema = DataBaseFunctions.getSchemaName("MS", env);
		systemUser="Administrator, System";
		daemonName = "com.reserveamerica.licensing.customer.daemon.ExpireCusomerCertificationDaemon";
		licType = "NON-US DL Number";
		licNum = "AUTO12345";
		custClass = "Individual";
        
		//Certification Information
		certification.status = "Active";
		certification.type = "Trapper Certification";
		certification.num = "AUTOExpireCetification";
		// Hard code for past date is better for this case
		certification.effectiveFrom = "7/1/2010";
		certification.effectiveTo = "9/1/2011";

		// get EDT time zone because daemon schedule is run by EDT time
		timeZone_EDT = DataBaseFunctions.getContractTimeZone(null);
		today = DateFunctions.getToday("MM/dd/yyyy", timeZone_EDT);
		//get contract time zone
		ms_TimeZone=DataBaseFunctions.getContractTimeZone(schema);
	}

	public void verifyExpireCertification(Certification c, String schema) {
		LicMgrCustomerCertificationPage custCertificationPg = LicMgrCustomerCertificationPage
				.getInstance();
		LicMgrCustomerDetailsPage detailsPage=LicMgrCustomerDetailsPage.getInstance();
		
		detailsPage.clickCertificationsTab();
		ajax.waitLoading();
		custCertificationPg.waitLoading();
		
		// Date
		c.status = "Expired";
		if (custCertificationPg.checkCertificationRecordExists(c)) {
			c.creationDateTime = custCertificationPg.getCreationDateTime(c);
			c.creationDateTime = DateFunctions.formatDate(c.creationDateTime, "MMM dd,yyyy hh:mm a z", "MM/dd/yyyy hh:mm");
			c.id = custCertificationPg.getCertificationID(c);
			this.verifyAuditLog(c);
		} else {
			// if there is no Record which effective to date is past date,add
			// one.
			c.status = "Active";
			if (!custCertificationPg.checkCertificationRecordExists(c)) {
				lm.removeAllCustomerCertifications(true);
				lm.addCustomerCertification(c);
				throw new TestCaseFailedException("Please wait for daemon "
						+ runAt + " EAST TIME");
			} else {
				c.creationDateTime = custCertificationPg.getCreationDateTime(c);
				c.creationDateTime = DateFunctions.formatDate(c.creationDateTime, "MMM dd,yyyy hh:mm a z", "MM/dd/yyyy hh:mm");
				if(DateFunctions.compareDates(c.creationDateTime, scheduleTime_Today)<0){
					throw new ActionFailedException("Verify Expired customer certification failed.");
				}else{
					throw new TestCaseFailedException("Please wait for daemon "
							+ runAt + " EAST TIME");
				}
			}
		}
	}

	/**
	 * Verify Change History.
	 * @param c   target certification
	 */
	public void verifyAuditLog(Certification c) {
		LicMgrCustomerCertificationPage custCertificationPg = LicMgrCustomerCertificationPage
				.getInstance();
		LicMgrCustomerChangeHistoryPage historyPage = LicMgrCustomerChangeHistoryPage
				.getInstance();

		custCertificationPg.clickChangeHistory();
		historyPage.waitLoading();
		List<ChangeHistory> list = historyPage.getChangeHistoryInfo(c.id);
		ChangeHistory expiredHistory=null;
		for(ChangeHistory history:list){
			if(history.field.equals("Status")&&history.newValue.equals("Expired")){
				expiredHistory=history;
				break;
			}
		}
		
		if(expiredHistory==null){
			throw new ErrorOnPageException("there is not expired history for certification which id="+c.id);
		}
		// compare date time
		String changeDate=DateFunctions.formatDate(expiredHistory.changeDate, "MM/dd/yyyy hh:mm");
		String changeDate_EDT=DateFunctions.changeDateStampTimeZone(changeDate,"MM/dd/yyyy hh:mm", ms_TimeZone, "MM/dd/yyyy hh:mm", timeZone_EDT);
		if (DateFunctions.compareDates(changeDate_EDT, c.creationDateTime)<0) {
            throw new ErrorOnPageException("History change date is wrong");
		}
		if(!expiredHistory.object.equals("Customer Certification-"+c.type+"("+c.id+")")){
			throw new ErrorOnPageException("Object instance is wrong!");
		}
		if(!expiredHistory.action.equals("Update")){
			throw new ErrorOnPageException("Action should be 'Update'");
		}
		if(!expiredHistory.location.equals(login.location.split("/")[1])){
			throw new ErrorOnPageException("Location should be "+login.location.split("/")[1]);
		}
		
		if(!expiredHistory.user.equals(systemUser)){
			throw new ErrorOnPageException("User should be "+systemUser);
		}
		
		historyPage.clickReturnToCustomerDetail();
		custCertificationPg.waitLoading();
	}

	/**
	 * get daemon run at time
	 * 
	 * @param name
	 * @return
	 */
	public String getDaemonRunAtTime(String name) {
		SysMgrServiceDaemonsPage daemonPage = SysMgrServiceDaemonsPage
				.getInstance();
		return daemonPage.getDaemonRunAt(name);
	}

}
