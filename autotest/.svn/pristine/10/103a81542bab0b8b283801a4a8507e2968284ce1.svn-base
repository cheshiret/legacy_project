package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description: View privilege order history.
 * @Preconditions: The specific privilege order exist and have agents assignment, price,quality control and license year.
 * @SPEC:  View privilege order history.
 * @Task#: Auto-871
 * @author jwang8
 * @Date  Feb 21, 2012
 */
public class ViewPrivilegeOrderHistory extends LicenseManagerTestCase{

	private OrderInfo orderInfo = new OrderInfo();
	private OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();
	LicMgrPrivilegeOrderHistoryPage orderHistoryPg = LicMgrPrivilegeOrderHistoryPage.getInstance();
	LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private String voidReason = "";
	private String voidNote = "";
	private String undoVoidReasion = "";
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		orderInfo.orderPrice = "$" + ormsOrderCartPg.getTotalPriceAmount();
		orderInfo.orderPaid = "$" + ormsOrderCartPg.getPaymentAmount()+"0";
		orderInfo.orderNum = lm.processOrderCart(pay,false);
		orderInfo.privilegeNum = lm.getPrivilegeNumByOrdNum(schema, orderInfo.orderNum);
		lm.gotoPrivilegeOrderDetailPage(orderInfo.orderNum);
		this.voidPrivilege();

		lm.gotoPrivilegeOrderDetailPage(orderInfo.orderNum);

		this.undoVoidPrivilege();
		lm.gotoPrivilegeOrderDetailPage(orderInfo.orderNum);
		lm.gotoPrivilegeOrderHistoryPage();
		undoVoidReasion = undoVoidReasion.split("-")[1].trim();
		this.verifyPriveliegeOrderHistoryInfo(orderInfo,voidReason,undoVoidReasion);

		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = DataBaseFunctions.getSchemaName("MS",env);

		privilege.purchasingName = "VPH-HisPrivilegeOrder";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "01 - Operator Error";
		privilege.operateNote = "Automation-test";

		cust.lName = "Test-ViewPriDetail";
		cust.fName ="QA-ViewPriDetail";
		cust.identifier.identifierType = "MDWFP #";//"Green Card";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);;
		cust.identifier.country = "Canada";
		cust.customerClass = "Individual";
		cust.residencyStatus="Non Resident";

		voidReason = "14 - Other";
		voidNote = "AutoTest";
		undoVoidReasion ="50 - Void by mistake";

		orderInfo.creationUser = DataBaseFunctions.getLoginUserName(login.userName);
		orderInfo.transactionLocation = login.location.split("/")[1];
		//orderInfo.transactionList.add("Generate Document");
		orderInfo.transactionList.add("Original Purchase");
		orderInfo.transactionList.add("Make Payment");
		orderInfo.transactionList.add("Reverse");
		orderInfo.transactionList.add("Reverse Fee");
		orderInfo.transactionList.add("Reallocate Payment");
		orderInfo.transactionList.add("Undo Reverse");
		orderInfo.transactionList.add("Reverse Fee");
		orderInfo.transactionList.add("Make Payment");
	}
	
	/**
	 * Verify the order
	 */
	private void verifyPriveliegeOrderHistoryInfo(OrderInfo expectedOrder,String voidReason,String undoVoidReasion){

		boolean pass = orderHistoryPg.compareHistoryInfo(expectedOrder,voidReason,undoVoidReasion);
		if(!pass){
			throw new ErrorOnPageException("Privilege order history info display error");
		}else{
			logger.info("Privilege order history info display correct");
		}
	}
	/**
	 * Void the privilege
	 */
	private void voidPrivilege(){
		//lm.backPrivilegeOrderDetailPgFromHistoryPg();
		lm.reversePrivilegeOrdToCart(voidReason, voidNote);
		orderInfo.orderPastPaid = "$" + ormsOrderCartPg.getTotalPastPaid().toString() ;
		lm.processOrderCart(pay);
	}

	/**
	 * undoVoid the privilege
	 */
	private void undoVoidPrivilege(){
		//lm.backPrivilegeOrderDetailPgFromHistoryPg();
		lm.undoReversePrivilegeOrderToCart(undoVoidReasion, voidReason);
		lm.processOrderCart(pay);
	}

}
