package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.transfer.businessrule;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.EducationDeferralRecords;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerEducationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrViewEducationDeferralRecordsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Rule Name:Customer may BYPASS education type requirement parameter number of times
 * @Preconditions:1.existing 2 customers
 *                2.privilege can be purchased and transferred
 *                (shall not include Questions)
 *                (shall include business rule)
 *                3.Transfer to customer doesn't have education type
 *                  Transfer from customer must have education type
 *                4.need corresponding business rule:
 *                IF parameter EDUCATION Type not on file, Customer must PROVIDE parameter EDUCATION type information in order to purchase
 *                5. Privilege business rule:
 *                d_hf_add_pri_business_rule: ID = 330, 340
 * @SPEC:Check Business Rules for Privilege Transfer.doc
 * @Task#:Auto-872
 * 
 * @author nding1
 * @Date  Mar 14, 2012
 */
public class BypassEducationTypeParamTimes extends LicenseManagerTestCase {
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private Customer toCust = new Customer();
	private Education education = new Education();
	private LicMgrCustomerEducationPage eduPage = LicMgrCustomerEducationPage.getInstance();
	
	@Override
	public void execute(){
		lm.checkPrivilegesExist(schema, privilege.code);
		lm.loginLicenseManager(login);

		// 3.remove education for transfer to customer
		this.manageEducation(toCust, "Remove", toCust.education);
		
		// add new education for transfer from customer
		this.manageEducation(cust, "Remove", cust.education);
		this.manageEducation(cust, "Add", cust.education);
		
		// 4.clean up orders for customers
		login.location = "HF HQ Role - Auto-WAL-MART";
		lm.switchLocationInHomePage(login.location);
		lm.invalidatePrivilegePerCustomer(cust, privilege );
		lm.invalidatePrivilegePerCustomer(toCust, privilege);
		
		// 5.make a privilege order
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay).split(" ")[0];
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		String allPrivNum = privOrderDetailsPage.getAllPrivilegesNum();

		// 6.transfer, should succeed, and system will automatically add one dererral record for transfer to customer
		lm.gotoPrivilegeDetailFromOrderPg(allPrivNum);
		lm.transferPrivToOrderCart(toCust, privilege);
		String transferredOrderNum1 = lm.processOrderCart(pay).split(" ")[0];
		lm.gotoPrivilegeOrderDetailPage(transferredOrderNum1);
		String privNum1 = privOrderDetailsPage.getAllPrivilegesNum();

		// 7.verify whether the order was added to Deferral records
		this.viewAndVerifyDeferralRecord(transferredOrderNum1, privNum1);
		
		// 8.make a privilege order and transfer again
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum2= lm.processOrderCart(pay).split(" ")[0];
		lm.gotoPrivilegeOrderDetailPage(orderNum2);
		String allPrivNum2 = privOrderDetailsPage.getAllPrivilegesNum();
		
		// 9.transfer, the corresponding rule will work
		lm.gotoPrivilegeDetailFromOrderPg(allPrivNum2);
		lm.transferPrivToOrderCart(toCust, privilege);
		lm.processOrderCart(pay);
		
		// 10.delete that deferral record from DB
		this.deleteDeferralReocrd(transferredOrderNum1);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param){
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "May 18 1977";// Don't change!
		cust.identifier.identifierType = "MDWFP #";
		cust.fName = "QA-TransferRule17";
		cust.lName = "TEST-TransferRule17";
		cust.residencyStatus = "Non Resident";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);

		toCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		toCust.dateOfBirth = "Jan 31 1988";
		toCust.identifier.identifierType = "MDWFP #";
		toCust.fName = "QA-TransferRule117";
		toCust.lName = "TEST-TransferRule117";
		toCust.identifier.identifierNum = lm.getCustomerNumByCustName(toCust.lName, toCust.fName, schema);
		toCust.residencyStatus = "Non Resident";

		privilege.code = "968";
		privilege.purchasingName = "968-BypassEduType";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "21 - Other";
		privilege.operateNote = "";
		privilege.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};
		
		education.status = OrmsConstants.ACTIVE_STATUS;
		education.educationType = "HuntEducation";
		education.educationNum = "EDU654320";
		education.state = "Mississippi";
		education.country = "United States";
		cust.education = education;
		toCust.education = education;
		cust.education.educationNum = "397290360";// Don't change! TODO
	}
	
	/**
	 * Manage Education for customer.
	 * @param customer
	 * @param operation
	 * @param edu
	 */
	private void manageEducation(Customer customer, String operation, Education edu){
		logger.info("Manage Education for customer.");
		lm.gotoHomePage();
		lm.gotoCustomerDetailFromTopMenu(customer);
		lm.gotoCustomerSubTabPage("Education");
		if(("Remove".equals(operation) && eduPage.checkEducationExists(edu.educationType, edu.educationNum)) || "Add".equals(operation)){
			lm.manageEducationsForCustomer(operation, null, edu);
		}
	}
	
	private void viewAndVerifyDeferralRecord(String orderNum, String privilegeNum){
		logger.info("Go to customer education page, view Defferal record and verify detail of that record.");

		LicMgrViewEducationDeferralRecordsPage viewEducationDeferralRecordsPg = LicMgrViewEducationDeferralRecordsPage.getInstance();
		LicMgrCustomerEducationPage educationPg = LicMgrCustomerEducationPage.getInstance();
		EducationDeferralRecords deferralReocrdUI = new EducationDeferralRecords();
		
		// go to deferral record page
		lm.gotoCustomerDetailFromTopMenu(toCust);
		lm.gotoViewEducationDeferralRecordsFromCustomerDetailsPg();
		
		// get deferral record from UI
		List<EducationDeferralRecords> deferralReocrdList = viewEducationDeferralRecordsPg.getEduDeferrafsadflRecordsByOrderNum(orderNum);
		if(null == deferralReocrdList || deferralReocrdList.size() < 0){
			throw new ErrorOnPageException("There isn't any deferral record!! The system should add one deferral for this customer!!");
		} else {
			deferralReocrdUI = deferralReocrdList.get(0);
		}
		
		// get expect deferral record
		EducationDeferralRecords deferralReocrdExpect = this.setExpectDeferralRecord(orderNum, privilegeNum);
		
		// verify deferral record
		boolean result = true;
		// verify Education Type
		if(!deferralReocrdExpect.eduType.equals(deferralReocrdUI.eduType)){
			result = false;
			logger.error("--The Education Type is not correct. Expect one is:"+deferralReocrdExpect.eduType+", but actual one is:"+deferralReocrdExpect.eduType);
		}
		
		// verify Order Number
		if(!deferralReocrdExpect.orderNum.equals(deferralReocrdUI.orderNum)){
			result = false;
			logger.error("--The Order Number is not correct. Expect one is:"+deferralReocrdExpect.orderNum+", but actual one is:"+deferralReocrdExpect.orderNum);
		}
		
		// verify Privilege Number
		if(!deferralReocrdExpect.privilegeNum.equals(deferralReocrdUI.privilegeNum)){
			result = false;
			logger.error("--The Privilege Number is not correct. Expect one is:"+deferralReocrdExpect.privilegeNum+", but actual one is:"+deferralReocrdExpect.privilegeNum);
		}
		
		// verify Privilege Status
		if(!deferralReocrdExpect.privilegeStatus.equals(deferralReocrdUI.privilegeStatus)){
			result = false;
			logger.error("--The Privilege Status is not correct. Expect one is:"+deferralReocrdExpect.privilegeStatus+", but actual one is:"+deferralReocrdExpect.privilegeStatus);
		}
		
		// verify Status
		if(!deferralReocrdExpect.deferralStatus.equals(deferralReocrdUI.deferralStatus)){
			result = false;
			logger.error("--The Status is not correct. Expect one is:"+deferralReocrdExpect.deferralStatus+", but actual one is:"+deferralReocrdExpect.deferralStatus);
		}
		
		if(!result){
			throw new ErrorOnPageException("Not all of the test point is passed. Please check log above!");
		} else {
			logger.info("Deferral record is correct!");
		}
		
		viewEducationDeferralRecordsPg.clickOK();
		ajax.waitLoading();
		educationPg.waitLoading();
	}
	
	/**
	 * Set each field for expect deferral record.
	 * @param orderNum
	 * @param privilegeNum
	 */
	private EducationDeferralRecords setExpectDeferralRecord(String orderNum, String privilegeNum){
		logger.info("Set each field for expect deferral record.");
		EducationDeferralRecords deferralRecord = new EducationDeferralRecords();
		deferralRecord.eduType = education.educationType;
		deferralRecord.orderNum = orderNum;
		deferralRecord.privilegeNum = privilegeNum;
		deferralRecord.privilegeStatus = OrmsConstants.ACTIVE_STATUS;
		deferralRecord.deferralStatus = OrmsConstants.ACTIVE_STATUS;
		return deferralRecord;
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
