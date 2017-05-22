/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.voidorder;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.EducationDeferralRecords;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerEducationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrViewEducationDeferralRecordsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description:Verify deferal record status after undo void
 * @Preconditions: 
 * 1. Have privilege product: "VHR-HarvestPrivilege"
 * 2. Privilege has below two Business rules:
 * Customer may BYPASS education type requirement parameter number of times
 * 
 * Education/Certification enforcement
 * IF parameter EDUCATION type not on file, Customer must PROVIDE parameter EDUCATION type information in order to purchase
 * @SPEC:Process Undo Void Privilege Transaction
 * @Task#:AUTO-933
 * 
 * @author asun
 * @Date  2012-3-30
 */
public class VerifyDeferralRecord_UnDoVoid extends LicenseManagerTestCase {
    private LicMgrCustomerEducationPage custEducation = LicMgrCustomerEducationPage
	.getInstance();
	private String orderNum;
	private String voidReason = "14 - Other";
	private String undoVoidReason="17 - Other";
	private String note = "Auto test";
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//Remove All 
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoCustomerSubTabPage("Education");
		if(custEducation.getEducationNums()>0){
			lm.removeAllCustEdus();
		}
		
		//make a privilege order;
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
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
	
		
		//verify undovoid deferral record in customer harvest page;
		//order status should be pending;
		lm.gotoCustomerDetailFromTopMenu(cust);
		lm.gotoCustomerSubTabPage("Education");
		this.verifyDeferralRecordInCustEducationPage(orderNum);
		
		lm.logOutLicenseManager();
	}
	
	/**
	 * Verify Deferral record after undo void: privilege status: Active, deferral record:Active
	 */
	private void verifyDeferralRecordInCustEducationPage(String ordernum) {
		LicMgrCustomerEducationPage custEducation = LicMgrCustomerEducationPage
		.getInstance();
		LicMgrViewEducationDeferralRecordsPage deferralRecordWidget=LicMgrViewEducationDeferralRecordsPage.getInstance();
		
		custEducation.clickViewEducationDeferralRecords();
		ajax.waitLoading();
		deferralRecordWidget.waitLoading();
		
		List<EducationDeferralRecords> list=deferralRecordWidget.getEduDeferrafsadflRecordsByOrderNum(ordernum);
		
		logger.info("Verify Deferral record after undo void....");
		
		if(list==null || list.size()<1){
			throw new ErrorOnPageException("Can't get the differal record by order num:"+orderNum);
		}
		
		for(EducationDeferralRecords record:list){
			if(!record.deferralStatus.equals("Active")){
				throw new ErrorOnPageException("the deferral status of record which orderNum:"+record.orderNum+" is wrong ","Active",record.deferralStatus);
			}
			
			if(!record.privilegeStatus.equals("Active")){
				throw new ErrorOnPageException("the privilege status of record which orderNum:"+record.orderNum+" is wrong ","Active",record.privilegeStatus);
			}
		}
		
		deferralRecordWidget.clickOK();
		ajax.waitLoading();
		custEducation.waitLoading();
	}

	/**
//	 * verify harvest records for undo void privilege order in order history page
//	 */
//	private void verifyDeferralRecordsInOrderHistoryPage() {
//		LicMgrPrivilegeOrderHistoryPage privOrderHistoryPg = LicMgrPrivilegeOrderHistoryPage.getInstance();
//	    
//		List<OrderHistory> listForUndoVoid=privOrderHistoryPg.getHistoryInfoByTransaction("Undo Void Harvest Record");
//		
//		List<OrderHistory> listForVoid=privOrderHistoryPg.getHistoryInfoByTransaction("Undo Void");
//		
//		logger.info("verify harvest records for undo void privilege order in order history page");
//		
//		if(listForUndoVoid.size()!=1){
//			throw new ErrorOnPageException("there should be only one undo void transaction.");
//		}
//		
//		if(listForUndoVoid.get(0).InfoTransaction.equals(listForVoid.get(0).InfoTransaction)){
//			throw new ErrorOnPageException("info at time of transaction should same for void and undo void harvest record.");
//		}
//	}

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
		cust.identifier.identifierNum = "AUTO4321";
		cust.identifier.country="Canada";
		cust.residencyStatus="Non Resident";
	}

}
