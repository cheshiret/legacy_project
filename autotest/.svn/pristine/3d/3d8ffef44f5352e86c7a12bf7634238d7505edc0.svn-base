package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.licences;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Suspension;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.keywords.orms.SystemManager;
import com.activenetwork.qa.awo.pages.web.hf.HFCurrentLicensesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFExpiredLicensesListPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFOrderHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P)
 * Verify the expired licence display only in expired licence list page, not current licence list page
 * 
 * @Preconditions:
    d_web_hf_signupaccount,id=300
	d_hf_add_privilege_prd, id=1910, 1920
	d_hf_add_pricing, id=2532, 2542, 2552, 2562, 2572, 2582
	d_hf_assi_pri_tQA-AUTO-ADMo_store, id=1160, 1170
	d_hf_add_qty_control, id=1140, 1150
	d_hf_add_prvi_license_year, id=1630, 1640
	d_hf_add_print_doc, id=290, 300

 * @SPEC:
 * Verify the status of the license/permit on Current tab [TC:046090] 
 * Verify the status of the license/permit on Expired tab [TC:044618] 
 * Order History - License Order [TC:050344]
 * License Details section - items [TC:046891]
 * Order Details - contain Expired/Revoked order items [TC:050517]
 * @Task#: Auto-1641, Auto-1723, Auto-1718
 * 
 * @author SWang, Lesley Wang
 * @Date  May 22, 2013, Jun 4, 2013
 */
public class ExpiredStatusValidation extends HFSKWebTestCase {
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private Suspension suspension = new Suspension();
	//	private String[] searchStatus;
	private	List<String> licensesInCurrentLis, licensesInExpiredLis;
	private HFCurrentLicensesListPage currentLicensePg = HFCurrentLicensesListPage.getInstance();
	private HFExpiredLicensesListPage expiredLicensesListPg = HFExpiredLicensesListPage.getInstance();
	private HFOrderHistoryPage ordHistPg = HFOrderHistoryPage.getInstance();
	private HFOrderDetailsPage ordDetailsPg = HFOrderDetailsPage.getInstance();
	private HFLicenseDetailsPage licDetailsPg = HFLicenseDetailsPage.getInstance();
	protected SystemManager sm = SystemManager.getInstance();
	protected LoginInfo loginSm = new LoginInfo();
	protected String daemonName, daemonRunTime, estimateExpireDateTime;
	protected TimeZone timeZone_SK;
	private boolean isValidDatesHide;
	
	public void execute() {
		//Get the previous privilege number which is stored in qa_automation table
		String values[] = lm.readQADB(this.caseName).split(",");

		//Validation when there is a previous privilege exists in system		
		if(values.length > 0 && lm.checkOrderExists(schema, values[0]) && lm.checkPrivilegeNumberExists(schema, values[1])) {

			//Check orders information in account overview and licenses list page in HFSK
			hf.signIn(url, cus.email, cus.password);
			hf.gotoCurrentLicencesListPgFromMyAcctPanel();
			licensesInCurrentLis = currentLicensePg.getAllLicenses();
			currentLicensePg.verifyLicenseDisplays(licensesInCurrentLis, privilege.name, false);

			hf.gotoExpiredLicensesListPgFromCurrentLicencesListPg();
			licensesInExpiredLis = expiredLicensesListPg.getAllLicenses();
			expiredLicensesListPg.verifyLicenseDisplays(licensesInExpiredLis, privilege.name, true);

			this.initializePriInfo(values); // 0 - order number; 1 - privilege number; 2 - order date; 3 - valid dates
			// Check license details in License Details page
			hf.gotoLicDetailsPgFromExpiredLicListPg(privilege.privilegeId);
			privilege.status = "Expired";
			licDetailsPg.verifyLicDetails(privilege, DataBaseFunctions.getTimeZoneString(schema));
			
			// Check order in order history page
			hf.gotoOrderHistoryPgFromMyAcctPanel();
			privilege.status = "";
			ordHistPg.verifyLicenseOrderDetails(isValidDatesHide, values[0], values[2], privilege);
			
			// Check order in license order details page
			hf.gotoOrderDetailsPgFromOrdHistPg(privilege.privilegeId, values[0]);
			ordDetailsPg.verifyLicenseOrderDetails(isValidDatesHide, values[0], values[2], privilege);
			hf.signOut();
		} else {
			//Get demo run time
			sm.loginSystemManager(loginSm);
			daemonRunTime = sm.getDaemonRunningTime(daemonName);
			sm.logoutSystemManager();

			//create a new privilege instance and insert the order number and privilege number into db
			//Precondition
			//* Add identifier in HFSK
			hf.signIn(url, cus.email, cus.password);
			hf.deleteCustIden(schema, OrmsConstants.IDEN_RCMP_ID, cus.email);
			hf.addIdentifier(cus.identifier);
			hf.signOut();

			//* Invalid all licenses in LM
			lm.loginLicenseManager(login);
			//* Remove added suspension in LM
			lm.gotoCustomerDetailFromTopMenu(cus);
			lm.gotoSuspensionsFromCustomerDetailsPg();
			lm.removeCustAllSuspensions();

			//1. make 1 privilege instance into cart
			lm.makePrivilegeToCartFromCustomerTopMenu(cus, privilege);
			String orderNum = lm.processOrderCart(pay);
			String privilegeNum = lm.getPrivilegeNumByOrdNum(schema, orderNum);
			String ordDate = DateFunctions.getToday("E MMM dd yyyy", timeZone_SK);

			//2. update valid from and valid to values as current date time
			String currentTime = DateFunctions.getToday("yyyy/MM/dd h:m aa", timeZone_SK);
			lm.updatePrivilegeInstanceValidFromAndTo(schema, privilegeNum, currentTime, currentTime);

			//3. insert order number, privilege number, order date, invalid from and to dates into db for next running
			lm.writeQADB(this.caseName, orderNum + "," + privilegeNum + "," + ordDate + "," + currentTime);

			//4. calculate estimate expired date time
			lm.logOutLicenseManager();
			
			estimateExpireDateTime = this.calculateEstimateExpiredDateTime(currentTime);
			throw new TestCaseFailedException("A new privilege(#=" + privilegeNum + ") has been purchased for testing expiry. Please run this case after " + estimateExpireDateTime + ".");
		}
	}

	public void wrapParameters(Object[] param) {
		//System manager parameters
		loginSm.url = AwoUtil.getOrmsURL(env) + "/SystemMgrLogin.do";
		loginSm.userName = TestProperty.getProperty("orms.adm.user");
		loginSm.password = TestProperty.getProperty("orms.adm.pw");
		loginSm.contract = "SK Contract";
		loginSm.location = "Administrator - Auto/Saskatchewan Ministry of Environment";
		daemonName = "com.reserveamerica.licensing.order.impl.daemon.ExpirePrivilegeDaemon";
		schema = DataBaseFunctions.getSchemaName("SK", env);
		timeZone_SK = DataBaseFunctions.getContractTimeZone(schema);

		//License manager parameters
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		login.contract = "SK Contract";
		login.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";

		//Customer parameters
		cus.email = "hfsk_00011@webhftest.com";
		cus.dateOfBirth = "2000-01-13";
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.residencyStatus = RESID_STATUS_SK;
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "1R9Y4x4130";
		cus.identifier.state = "Saskatchewan";
		cus.residencyStatus = "Saskatchewan Resident - RCMP #";

		//Privileges parameters
		privilege.licenseYear = hf.getFiscalYear(schema);
		privilege.code = "SKA";
		privilege.name = "HFSK License001";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.operateReason = "01 - Operator Error";
		privilege.operateNote = "Automation-test";
		privilege.creationPrice = hf.getPriCustPrice(schema, privilege.code, 
				OrmsConstants.ORIGINAL_PURCHASE_TYPE_ID, "1");
		privilege.displaySubCategory = "Annual";
		
		//Suspension parameters
		suspension.status = OrmsConstants.ACTIVE_STATUS;
		suspension.type = "Angling Violation";
		suspension.beginDate = DateFunctions.getDateAfterToday(-1, "yyyyMMdd");
		suspension.endDate = DateFunctions.getDateAfterToday(1, "yyyyMMdd");
		suspension.datePosted = suspension.beginDate;
		suspension.comment = "Addiing customer suspension to make privilege as revoked";

		//Search licence parameters
		//		searchStatus = new String[]{"Active", "Expired", "Revoked"};
		pay.payType = Payment.PAY_DEF_CASH;
		
		isValidDatesHide = this.isValidDatesHide(url);
	}

	private String calculateEstimateExpiredDateTime(String validTo) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateFunctions.parseDateString(validTo));
		calendar.add(Calendar.DATE, 1);
		String expireDate = DateFunctions.formatDate(calendar.getTime(), "EEE MMM dd yyyy");
		String expireDateTimeInSystem = expireDate + " " +  daemonRunTime + " AM";
		//logger.info("----Expected expire Date Time in ORMS System is: " + expireDateTimeInSystem);
		TimeZone systemTimeZone = DataBaseFunctions.getContractTimeZone("");
		estimateExpireDateTime = DateFunctions.changeDateStampTimeZone(expireDateTimeInSystem, "EEE MMM dd yyyy h:mm a", systemTimeZone, "EEE MMM dd yyyy hh:mm a z", timeZone_SK);

		logger.info("----Expected expire Date Time in MS is: " + estimateExpireDateTime);

		return estimateExpireDateTime;
	}

	private void initializePriInfo(String[] values) {
		privilege.privilegeId = values[1];
		privilege.validFromDate = DateFunctions.formatDate(values[3], "E MMM dd yyyy hh:mm a");
		privilege.validToDate = privilege.validFromDate;
	}
}
