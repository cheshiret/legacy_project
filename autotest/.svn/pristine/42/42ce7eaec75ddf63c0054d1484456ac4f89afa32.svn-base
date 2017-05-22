/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.voidorder;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.Harvest;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerHarvestPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description:Verify havest record for undo void privilege order.
 * @Preconditions:
 * 1. the priveilege product must have print document - Print Document Template must be 'Harvest DOC';
 * 2. privilege order must print document;
 * @SPEC:Process Undo Void Privilege Transaction
 * @Task#:AUTO-933
 * 
 * @author asun
 * @Date  2012-3-30
 */
public class VerifyHarvestRecord_UnDoVoid extends LicenseManagerTestCase {

	private String orderNum;
	private String voidReason = "14 - Other";
	private String undoVoidReason="17 - Other";
	private String note = "Auto test";
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//make a privilege order;
		lm.makePrivilegeToCartFromCustomerQuickSearch(cust, privilege);
		orderNum=lm.processOrderCart(pay,true).split(" ")[0];
		
		//Void privilege order;
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.rePrintPrivilegeInPrivilegeOrderDetailsPg();
		lm.voidPrivilegeOrderToCart(voidReason,note);
		lm.processOrderCart(pay);
		
		//Undo Void
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.undoVoidPrivilegeOrdToCart(undoVoidReason, note);
		lm.processOrderCart(pay);
		
		//verify undo void harvest record records in order history page;
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.gotoPrivilegeOrderHistoryPage();
		this.verifyHarvestRecordsInOrderHistoryPage();
		
		//verify undovoid harvest record in customer harvest page;
		//order status should be pending;
		lm.gotoCustomerDetailFromTopMenu(cust);
		lm.gotoCustomerHarvestSubTab();
		this.verifyHarvestRecordInCustHarvestPage(orderNum);
		
		lm.logOutLicenseManager();
	}
	
	/**
	 * verify undovoid harvest record in customer harvest page;
	 * order status should be pending;
	 */
	private void verifyHarvestRecordInCustHarvestPage(String ordernum) {
		LicMgrCustomerHarvestPage custHarvestPage = LicMgrCustomerHarvestPage
		.getInstance();
		Harvest  harvest=custHarvestPage.getHarvestListInfoByOrderNum(ordernum);
		
		logger.info("Verify Harvest record should be Pending after undo void....");
		if(!harvest.status.equals("Pending")){
			throw new ErrorOnPageException("Havest status is wrong","Pending",harvest.status);
		}
	}

	/**
	 * verify harvest records for undo void privilege order in order history page
	 */
	private void verifyHarvestRecordsInOrderHistoryPage() {
		LicMgrPrivilegeOrderHistoryPage privOrderHistoryPg = LicMgrPrivilegeOrderHistoryPage.getInstance();
	    
		List<OrderHistory> listForUndoVoid=privOrderHistoryPg.getHistoryInfoByTransaction("Undo Void Harvest Record");
	
		List<OrderHistory> listForVoid=privOrderHistoryPg.getHistoryInfoByTransaction("Void Harvest Record");
		
		logger.info("verify harvest records for undo void privilege order in order history page");
		
		if(listForUndoVoid.size()!=1){
			throw new ErrorOnPageException("there should be only one undo void transaction.");
		}
		
		if(!listForUndoVoid.get(0).infoTransaction .equals(listForVoid.get(0).infoTransaction )){
			throw new ErrorOnPageException("info at time of transaction should same for void and undo void harvest record.");
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = DataBaseFunctions.getSchemaName("MS",env);
		
		privilege.purchasingName = "VHR-HarvestPrivilege";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "01 - Operator Error";
		privilege.operateNote = "Automation-test";
		
		cust.identifier.identifierType = "VISA";
		cust.identifier.identifierNum = "AUTO12344";
		cust.identifier.country="Canada";
		cust.residencyStatus="Non Resident";
	}
}
