package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.voidorder;

import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.EducationDeferralRecords;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrViewEducationDeferralRecordsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: (P)
 * 1. Make privilege order
 * 2. Go to "View Education Deferral Records" page to check 
 *    "Privilege Status": Active
 *    "Deferral Status": Active
 * 3. Void privilege
 * 4. Go to "View Education Deferral Records" page to check 
 *    "Privilege Status": Voided
 *    "Deferral Status": Inactive
 *    
 * @Preconditions: 
 * 1. Have privilege product: "COP-CalculateOrderPrice"
 * 2. Privilege has below two Business rules:
 * Customer may BYPASS education type requirement parameter number of times
 * IF parameter EDUCATION type not on file, Customer must PROVIDE parameter EDUCATION type information in order to purchase
 * 
 * @SPEC: Process Void Privilege Transaction 
 * @Task#: AUTO-968
 * 
 * @author: SWang
 * @Date: 2012-3-30
 * @Note: Verify and close DEFECT-34090
 */
public class VoidPrivilegeDeferralRecord extends LicenseManagerTestCase {
	private String orderNum, salesLocation;
	private PrivilegeBusinessRule bypassRule = new PrivilegeBusinessRule();
	private PrivilegeBusinessRule educationRule = new PrivilegeBusinessRule();
	
	public void execute() {
		lm.loginLicenseManager(login);
		
		//1. add privilege business rule
		lm.gotoProductSearchListPageFromTopMenu("Privilege");
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeSubTabPage(privilege.code, "Business Rule");
		lm.safeAddBusinessRuleForProduct(bypassRule);
		lm.addBusinessRuleForProduct(educationRule);
		
		lm.switchLocationInHomePage(salesLocation);
		//Make a privilege order
		lm.makePrivilegeToCartFromCustomerQuickSearch(cust, privilege);
		orderNum = lm.processOrderCart(pay).split(" ")[0];

		//Reprint Privilege
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.rePrintPrivilegeInPrivilegeOrderDetailsPg();
		lm.gotoHomePage();
		
		//Go to "View Education Deferral Records" page to check "Privilege Status" and "Deferral Status"
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoViewEducationDeferralRecordsFromCustomerDetailsPg();
		this.verifyPrivilegeAndDeferralStatus("Active", "Active");
		lm.gotoEducationPageFromViewEducationDeferralRecordsPage();

		//Void privilege
		lm.gotoHomePage();
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.voidPrivilegeOrderToCart(privilege.operateReason,privilege.operateNote);
		lm.processOrderCart(pay);

		//Go to "View Education Deferral Records" page to check "Privilege Status" and "Deferral Status"
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoViewEducationDeferralRecordsFromCustomerDetailsPg();
		this.verifyPrivilegeAndDeferralStatus("Voided", "Inactive");
		lm.gotoEducationPageFromViewEducationDeferralRecordsPage();

		// clean up, delete deferral record from DB
		this.deleteDeferralReocrd(orderNum);
		
		lm.logOutLicenseManager();
	}

	private void deleteDeferralReocrd(String orderNum){
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		String sql = "DELETE FROM C_CUST_HFP_DEFERED_EDU_RECORD where order_num = '"+orderNum+"'";
		int num = db.executeUpdate(sql);
		if(num<1){
			throw new ErrorOnPageException("There should be at least one deferral record!!");
		}
	}
	
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		salesLocation = "HF HQ Role - Auto-WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		//privilege product info
		privilege.code = "COP";
		privilege.purchasingName = "COP-CalculateOrderPrice";
		privilege.licenseYear = lm.getFiscalYear(schema);		
		privilege.qty = "1";
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "Auto test";
		privilege.cust = new Customer();
		privilege.cust.education.educationNum = "EDU654321";
		
		cust.identifier.identifierType = "VISA";
		cust.identifier.identifierNum = "AUTO4321";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		cust.dateOfBirth = "Jan 01 1980";
		cust.education.educationType = "HuntEducation";
		cust.education.educationNum = "EDU654321";
		cust.education.state = "Mississippi";
		cust.education.country = "United States";
		
		bypassRule.status = OrmsConstants.ACTIVE_STATUS;
		bypassRule.ruleCategory = "Education/Certification Enforcement";
		bypassRule.name = "Customer may BYPASS education type requirement parameter number of times";
		bypassRule.parameters = new RuleParameters[1];
		bypassRule.parameters[0] = bypassRule.new RuleParameters();
		bypassRule.parameters[0].educationType = "HuntEducation";
		bypassRule.parameters[0].passNum = "10000000";//important: The allowed BYPASS num decide the Deferral records count
		bypassRule.parameters[0].effectiveDate = DateFunctions.getToday(timeZone);
		
		educationRule.status = OrmsConstants.ACTIVE_STATUS;
		educationRule.ruleCategory = "Education/Certification Enforcement";
		educationRule.name = "IF parameter EDUCATION Type not on file, Customer must PROVIDE parameter EDUCATION type information in order to purchase";
		educationRule.parameters = new RuleParameters[1];
		educationRule.parameters[0] = educationRule.new RuleParameters();
		educationRule.parameters[0].educationType = "HuntEducation";
		educationRule.parameters[0].workAction = "Prompt for Education with Message 4002";
		educationRule.parameters[0].effectiveDate = DateFunctions.getToday(timeZone);
	}

	/**
	 * Verify privilege status and deferral status
	 * @param expectedPrivilegeStaus
	 * @param expectedDeferralStatus
	 */
	public void verifyPrivilegeAndDeferralStatus(String expectedPrivilegeStaus, String expectedDeferralStatus){
		LicMgrViewEducationDeferralRecordsPage viewEducationDeferralRecordsPg
		= LicMgrViewEducationDeferralRecordsPage.getInstance();

		logger.info("Start to verify privilege and deferral status.");
		List<EducationDeferralRecords> eduDeferralRecords =  viewEducationDeferralRecordsPg.getEduDeferrafsadflRecordsByOrderNum(orderNum);

		if(eduDeferralRecords.size() == 0) {
			throw new ErrorOnDataException("It must have 'Education Deferral Records' for Order: " + orderNum);
		}else{
			EducationDeferralRecords expectedRecord = null;
			for(EducationDeferralRecords record : eduDeferralRecords) {
				if(record.orderNum.equals(orderNum)) {
					expectedRecord = new EducationDeferralRecords();
					expectedRecord = record;
					break;
				}
			}
			if(expectedRecord == null) {
				throw new ErrorOnPageException("Can't find Education Deferral Record for Order: " + orderNum);
			}
			if(!expectedRecord.privilegeStatus.equals(expectedPrivilegeStaus)){
				throw new ErrorOnPageException("Privilege status is wrong.", expectedPrivilegeStaus, eduDeferralRecords.get(0).privilegeStatus);
			}
			logger.info("Successfully verify Privilege status"+expectedPrivilegeStaus);

			if(!expectedRecord.deferralStatus.equals(expectedDeferralStatus)){
				throw new ErrorOnPageException("Deferral status is wrong.", expectedDeferralStatus, eduDeferralRecords.get(0).deferralStatus);
			}
			logger.info("Successfully verify Deferral status"+expectedDeferralStatus);
		}
	}
}
