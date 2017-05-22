package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.voidorder;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrVendorFinConfigInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeReturnDocumentPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorFinConfigSubPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:work flow:  
 * 1. make privilege order, with print document
 * 2. void this privilege order   
 * 3. wait financialConfig.voidReturnChargeDays + gracePeriodForReturningPriDoc days,
 *  then check privilege instance status should be 'Expired Charged'
 * 
 * @Preconditions:
 * 1. An existing privilege(pricing, agent assignment, license year, quantity control) named 'CalculateOrderPrice'.
 * 2. select * from X_PROP WHERE NAME Like ('%PrivilegeVoidReversalTransactionCoverage%'), make sure value is '3'       
 * 3. Vendor should has no "Auto Return Voided Documents" at finicial configuation
 *    and void Return Charge Days should be '1'  
 *    and gracePeriodForReturningPriDoc value should be '1' in x_prop table 
 * @SPEC: EXPIRED CHARGED privilege transactions [TC:021287]
 *        View Unreturned Voided Privilege Documents - Expired Charged [TC:004687]
 * @Task#:AUTO-1314
 * 
 * @author vzhang
 * @Date  Nov 6, 2012
 */

public class ExpiredChargedPrivilegeDoc extends LicenseManagerTestCase{
	private String vendorNum, storeName,gracePeriodForReturningPriDoc;
	private LicMgrVendorFinConfigInfo financialConfig = new LicMgrVendorFinConfigInfo();
	private LicMgrPrivilegeOrderDetailsPage orderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		this.checkGracePeriodForReturningPrivilegeDocumentInfo(schema, gracePeriodForReturningPriDoc);

		//precondition#1: go to Financial Config page in Vendor Details page, to update 'Void Return Charge Days' as 1(it's the minimum),
		//and set 'Auto-Return' Voided Documents as NO
		lm.gotoVendorDetailsPgFromTopMenu(vendorNum);
		lm.gotoVendorFinConfigSubPage();
		this.editVendorFinancialConfig(prepareVendorFinancialConfigInfo());

		//make privilege order with print document
		lm.gotoHomePage();
		lm.switchLocationInHomePage("HF HQ Role - Auto-" + storeName);
		String orderNum = lm.readQADB(this.caseName);
		if(orderNum.length() > 0 && lm.checkOrderExists(schema, orderNum)) {
			String privilegeNum = lm.getPrivilegeNumByOrdNum(schema, orderNum);
			//go to order detail page
			lm.gotoPrivilegeOrderDetailPage(orderNum);
			String orderStatus = orderDetailsPage.getPrivilegeOrderItemStatus(privilegeNum);
			if(!orderStatus.equals(OrmsConstants.EXPIRED_CHARGED_STATUS)) {//Void first, then wait Charged, then wait expired charged
				//if the order purchase at 2012/11/02, privilege instance status is 'Expired Charged' at 2012/11/06
				String creationDate = orderDetailsPage.getCreationDate().split(" ")[0];
				String expectedExpiredChargedDate = DateFunctions.getDateAfterGivenDay(creationDate, Integer.valueOf(gracePeriodForReturningPriDoc) + Integer.valueOf(financialConfig.voidReturnChargeDays) +1);
				String today = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
				if(DateFunctions.compareDates(expectedExpiredChargedDate, today) < 0) {
					throw new ActionFailedException("The privilege status should be " + OrmsConstants.EXPIRED_CHARGED_STATUS + " today, but now it is " + orderStatus);
				}
				throw new TestCaseFailedException("The privilege is '" + orderStatus + "' now to wait Expired Charged. Please re-run this case after " + expectedExpiredChargedDate + " CST");
			}
			
			lm.gotoReturnDocumentPageFromHome();
			this.verifyExpiredChargePrivilegeOrderDocumentInfoShouldNotExisting(orderNum);
		}
		
		//precondition#2: make a privilege order with printing document
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		orderNum = lm.processOrderCart(pay, true).split(" ")[0];
		
		//precondition#3:void the privilege order without returning document
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.voidPrivilegeOrderToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);
		lm.writeQADB(this.caseName, orderNum);
		lm.logOutLicenseManager();		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		storeName = "WAL-MART";
		vendorNum = "Auto555";
		
		privilege.code = "COP";
		privilege.name = "CalculateOrderPrice";
		privilege.purchasingName = "COP-CalculateOrderPrice";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.fName = "QA-Basic14";
		cust.lName = "TEST-Basic14";
		cust.dateOfBirth = "19880608";
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "AutoBasic000014";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		
		gracePeriodForReturningPriDoc = "1";
	}
	
	private void checkGracePeriodForReturningPrivilegeDocumentInfo(String schema,String value){
		logger.info("Check Grace Period For Returning Privilege Document Info.");
		db.resetSchema(schema);
		
		String sql = "select value from x_prop where name = 'GracePeriodForReturningPrivilegeDocuments'";
		List<String> results = db.executeQuery(sql, "value");
		if(null != results && results.size() > 0){
			if(!results.get(0).equals(value)){
				sql = "update x_prop set value = '" + value + "' where name = 'GracePeriodForReturningPrivilegeDocuments'";
				db.executeUpdate(sql);
			}else logger.info("Finished update x_prop table, make sure value is " + value + "for 'GracePeriodForReturningPrivilegeDocuments'");
		}else{
			sql = "insert into x_prop(id, name,namespace,type,value) values(get_sequence(x_prop),'GracePeriodForReturningPrivilegeDocuments','Contract','Number','" + value+ "')";
			db.executeUpdate(sql);
			logger.info("Finished insert into data to x_prop table, make sure value is " + value + "for 'GracePeriodForReturningPrivilegeDocuments'");
		}
		
	}
	
	private LicMgrVendorFinConfigInfo prepareVendorFinancialConfigInfo() {
		financialConfig.voidReturnChargeDays = "1";
		financialConfig.autoReturnVoidedDoc = "No";
		
		return financialConfig;
	}
	
	/**
	 * Edit vendor financial config info
	 */
	private void editVendorFinancialConfig(LicMgrVendorFinConfigInfo vendorFinConfigInfo) {
		LicMgrVendorFinConfigSubPage financialConfigPage = LicMgrVendorFinConfigSubPage.getInstance();
		
		logger.info("Edit vendor fianncial config - 'Void Return Charge Days' as " + vendorFinConfigInfo.voidReturnChargeDays + " and 'Auto-Return Voided Documents' as " + vendorFinConfigInfo.autoReturnVoidedDoc);
		financialConfigPage.editFinancialConfigInfo(vendorFinConfigInfo);
		financialConfigPage.clickSave();
		ajax.waitLoading();
		financialConfigPage.waitLoading();
	}
	
	private void verifyExpiredChargePrivilegeOrderDocumentInfoShouldNotExisting(String expriedOrdNum){
		LicMgrPrivilegeReturnDocumentPage privilegeReturnDocumentPg = LicMgrPrivilegeReturnDocumentPage.getInstance();
		
		logger.info("Verify Expired Charged privilege order document info in this list with search by order number = " + expriedOrdNum);
		privilegeReturnDocumentPg.searchReturnDocumentByOrdNum(expriedOrdNum);
		boolean noSearchResult = privilegeReturnDocumentPg.checkNoSearchResult();
		if(!noSearchResult){
			throw new ErrorOnPageException("Expired Charged privilege order document info should not display in this list with search by order number = " + expriedOrdNum);
		}else logger.info("Expired Charged privilege order document info not display in this list." );
	}

}
