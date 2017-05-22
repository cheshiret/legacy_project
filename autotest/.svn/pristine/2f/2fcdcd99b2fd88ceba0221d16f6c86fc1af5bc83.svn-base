package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.voidorder;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.EducationDeferralRecords;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrViewEducationDeferralRecordsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: (P)
 * 1. Make privilege order use the privilege which has Harvest document
 * 2. Go to "View Education Deferral Records" page to check 
 *    "Privilege Status": Active
 *    "Deferral Status": Active
 * 3. Reverse privilege
 * 4. Go to "View Education Deferral Records" page to check 
 *    "Privilege Status": Reversed
 *    "Deferral Status": Inactive
 *    
 * @Preconditions: 
 * 1. Have Harvest template: Harvest DOC
 * 2. Have privilege product: "VHR-HarvestPrivilege"
 * 3. Privilege has below two Business rules:
 * Customer may BYPASS education type requirement parameter number of times
 * IF parameter EDUCATION type not on file, Customer must PROVIDE parameter EDUCATION type information in order to purchase
 * 
 * @SPEC: Process Void Privilege Transaction 
 * @Task#: AUTO-968
 * 
 * @author: SWangsmlDKMNl 
 * @Date: 2012-3-30
 * @Note: Verify and close DEFECT-34122
 */
public class ReversePrivilegeHarvestDeferralRecord extends LicenseManagerTestCase {
	private String orderNum;

	public void execute() {
		lm.loginLicenseManager(login);

		//Make privilege order using the privilege which has Harvest document
		lm.makePrivilegeToCartFromCustomerQuickSearch(cust, privilege);
		orderNum = lm.processOrderCart(pay, false).split(" ")[0];

		//Go to "View Education Deferral Records" page to check "Privilege Status" and "Deferral Status"
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoViewEducationDeferralRecordsFromCustomerDetailsPg();
		this.verifyPrivilegeAndDeferralStatus(OrmsConstants.ACTIVE_STATUS, OrmsConstants.ACTIVE_STATUS);
		lm.gotoEducationPageFromViewEducationDeferralRecordsPage();

		//Reverse privilege
		lm.gotoHomePage();
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);

		//Go to "View Education Deferral Records" page to check "Privilege Status" and "Deferral Status"
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoViewEducationDeferralRecordsFromCustomerDetailsPg();
		this.verifyPrivilegeAndDeferralStatus("Reversed", OrmsConstants.INACTIVE_STATUS);
		lm.gotoEducationPageFromViewEducationDeferralRecordsPage();

		//delete that deferral record from DB
		this.deleteDeferralReocrd(orderNum);
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		//privilege product info
		privilege.purchasingName = "VHR-HarvestPrivilege";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "Auto test";

		cust.identifier.identifierType = "VISA";
		cust.identifier.identifierNum = "AUTO4321";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
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

		if(eduDeferralRecords.size()!=1){
			throw new ErrorOnPageException("It should has only one 'Education Deferral Records' for orderNum:"+orderNum);
		}else{
			if(!eduDeferralRecords.get(0).privilegeStatus.equals(expectedPrivilegeStaus)){
				throw new ErrorOnPageException("Privilege status is wrong.", expectedPrivilegeStaus, eduDeferralRecords.get(0).privilegeStatus);
			}
			logger.info("Successfully verify Privilege status"+expectedPrivilegeStaus);

			if(!eduDeferralRecords.get(0).deferralStatus.equals(expectedDeferralStatus)){
				throw new ErrorOnPageException("Deferral status is wrong.", expectedDeferralStatus, eduDeferralRecords.get(0).deferralStatus);
			}
			logger.info("Successfully verify Deferral status"+expectedDeferralStatus);
		}
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
}
