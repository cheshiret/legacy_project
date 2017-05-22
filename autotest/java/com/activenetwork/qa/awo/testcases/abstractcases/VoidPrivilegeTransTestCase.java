/**
 * 
 */
package com.activenetwork.qa.awo.testcases.abstractcases;

import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrVoidUndoVoidPrivilegePage;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * @Defect:DEFECT-33826
 * @author asun
 * @Date  2012-3-15
 */
public abstract class VoidPrivilegeTransTestCase extends LicenseManagerTestCase {
    protected boolean isPrinted;
	protected String orderNum;
	protected String price;
	protected String orderType;
	private String voidReason="14 - Other";
	private String undoVoidReason="17 - Other";
	private String note="Auto test";
	private String orderItemStatus;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//make a privilege order;
 		lm.makePrivilegeToCartFromCustomerQuickSearch(cust, privilege);
		orderNum=lm.processOrderCart(pay,isPrinted);
		voidTransExecute();
		
		//Reprint order and verify order status
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		if(isPrinted)
		   lm.rePrintPrivilegeInPrivilegeOrderDetailsPg();
		this.verifyOrderItemStatus(privilege.purchasingName,"Active");		 
		
		//abandon void do verify order status;
		voidOrReversePrivilegeOrderToCart(voidReason,note);
		lm.verifyTransactionNameInOrdCart("Void "+orderType+" Privilege");
		lm.cancelCart();
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		this.verifyOrderItemStatus(privilege.purchasingName,"Active");
		
		//void order and do verify order status;
		voidOrReversePrivilegeOrderToCart(voidReason,note);
		lm.verifyTransactionNameInOrdCart("Void "+orderType+" Privilege");
//		lm.processOrderCart(pay,isPrinted);//TODO DEFECT-35437
		lm.processOrderCart(pay, false);
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		this.verifyOrderItemStatus(privilege.purchasingName,orderItemStatus);
		
		//abandon Undo Void
		unDoVoidOrReversePrivilegeOrderToCart(undoVoidReason, note);
		lm.verifyTransactionNameInOrdCart("Undo Void "+orderType+" Privilege");
		lm.cancelCart();
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		this.verifyOrderItemStatus(privilege.purchasingName,orderItemStatus);
		
		//Undo Void
		unDoVoidOrReversePrivilegeOrderToCart(undoVoidReason, note);
		lm.verifyTransactionNameInOrdCart("Undo Void "+orderType+" Privilege");
//		lm.processOrderCart(pay,isPrinted);//TODO DEFECT-35437
		lm.processOrderCart(pay, false);
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		this.verifyOrderItemStatus(privilege.purchasingName,"Active");
		
		lm.logOutLicenseManager();
	}
	
/**
 * if printed, void, or reverse
 * @param reason
 * @param note
 */
	private void voidOrReversePrivilegeOrderToCart(String reason,String note){
		if(isPrinted){
			lm.voidPrivilegeOrderToCart(reason, note);
		}else{
			lm.reversePrivilegeOrdToCart(reason, note);
		}
	}
	
	public void operatePrivilegeOrdToCart(String action, String reason,
			String note) {
		LicMgrPrivilegeOrderDetailsPage privOrderDetailPage = LicMgrPrivilegeOrderDetailsPage
				.getInstance();
		LicMgrVoidUndoVoidPrivilegePage voidUndoVoidPg = LicMgrVoidUndoVoidPrivilegePage
				.getInstance();
		LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget confirmWidget = LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget
				.getInstance();
		OrmsOrderCartPage orderCartPg = OrmsOrderCartPage.getInstance();

		logger.info(action + " Privilege Order To Cart.");

		if (!action.contains("Undo")) {
			privOrderDetailPage.voidOrReverseOrder();
		} else {
			privOrderDetailPage.undoVoidOrReverseOrder();
		}
		ajax.waitLoading();
		voidUndoVoidPg.waitLoading();
		voidUndoVoidPg.clickVoidOrUndoVoidSelectedTransaction();
		ajax.waitLoading();
		confirmWidget.waitLoading();
		confirmWidget.setupConfirmInfo(action, reason, note);

		orderCartPg.waitLoading();
	}
	/**
	 * if printed, undo void, or ondo reverse
	 * @param reason
	 * @param note
	 */
	private void unDoVoidOrReversePrivilegeOrderToCart(String reason,String note){
		if(isPrinted){
			lm.undoVoidPrivilegeOrdToCart(reason, note);
		}else{
			lm.undoReversePrivilegeOrderToCart(reason, note);
		}
	}

	public void voidTransExecute(){
		
	}
	/**
	 * Verify Order status for product in order details page
	 */
	private void verifyOrderItemStatus(String purchasingName,String status) {
		LicMgrPrivilegeOrderSearchPage priviOrderSearchPg = LicMgrPrivilegeOrderSearchPage.getInstance();
		
		String sttausOnPage=priviOrderSearchPg.getOrderItemStatus(purchasingName);
		logger.info("Verify Order status for product:"+purchasingName+" is "+status);
		if(!status.equals(sttausOnPage)){
			throw new ErrorOnPageException("Order status of product:"+purchasingName+" is wrong ",status,sttausOnPage);
		}
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = DataBaseFunctions.getSchemaName("MS",env);
		
		privilege.purchasingName = "COP-CalculateOrderPrice";
		privilege.licenseYear = String.valueOf(DateFunctions.getCurrentYear());		
		privilege.qty = "1";
		privilege.operateReason = "01 - Operator Error";
		privilege.operateNote = "Automation-test";
		
		cust.identifier.identifierType = "VISA";
		cust.identifier.identifierNum = "AUTO12342";
		cust.identifier.country="Canada";
		cust.residencyStatus="Non Resident";
		
		wrapLocalParameter(param);
		orderItemStatus=isPrinted?"Void":"Reversed";
	}
	
	public abstract void wrapLocalParameter(Object[] param);

}
