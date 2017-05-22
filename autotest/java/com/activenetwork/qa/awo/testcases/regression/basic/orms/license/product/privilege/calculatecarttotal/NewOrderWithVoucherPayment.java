/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.calculatecarttotal;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Voucher;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description: This script will verify calculate multiple order items and multiple order cart totals calculated correct with voucher payment in the order cart
 * 	1.	purchase a new privilege into cart then add more privilege
 *  2.	click 'purchase privilege' button from order cart,add another privilege order into cart
 *  3.  Verify calculate cart totals refer chapter '8.10	Cart Totals Calculation 7'
 *  
 * @Preconditions: make sure two customer info and one privilege exist before H&F go live
 * @SPEC:<Calculate Cart Totals>
 * @Task#:Auto-928
 * 
 * @author ssong
 * @Date  Mar 9, 2012
 */
public class NewOrderWithVoucherPayment extends LicenseManagerTestCase{
	private Customer newCust = new Customer();
	private Voucher voucher = new Voucher();
	
	@Override
	public void execute() {
		lm.checkPrivilegesExist(schema, privilege.code);
		lm.loginLicenseManager(login);
		//purchase a privilege order and void the order to get refund by voucher
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String ordNum = lm.processOrderCart(pay);
		lm.gotoPrivilegeOrderDetailPage(ordNum);
		lm.voidPrivilegeOrderToCart(privilege.operateReason, privilege.operateNote);
		pay.issueToVoucher = true;
		lm.processOrderToOrderSummary(pay);
		retrieveVoucherAndFinishOrder();
		
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		//add more privilege into cart
		lm.addMorePrivilegeFromCartToCart(privilege);
		//add another order into cart
		lm.purchasePrivilegeFromCartToCart(newCust, privilege);
		this.payByVocherAndVerifyCalculateAmount();
		
		lm.cancelCart();
		lm.logOutLicenseManager();		
	}

	private void retrieveVoucherAndFinishOrder(){
		OrmsOrderSummaryPage ordSumPg = OrmsOrderSummaryPage.getInstance();
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		
		voucher = ordSumPg.getVoucherInfo();
		ordSumPg.clickFinishOrder();
		homePg.waitLoading();
	}
	
	private void payByVocherAndVerifyCalculateAmount(){
		OrmsOrderCartPage cartPg = OrmsOrderCartPage.getInstance();
		pay.voucherID = voucher.voucherId+" : "+voucher.amount;

		logger.info("Pay by Voucher and calculate Cart Totals.");
		
		double sumOrderTotal = cartPg.getOrderTotalSum();
		double totalPrice = cartPg.getTotalPrice().doubleValue();
		
		//pay by voucher
		pay.voucherID = voucher.voucherId+" : "+voucher.amount;
		cartPg.selectVoucherAsPayment(pay.voucherID);
		//verify sum[order total]= total price
		if(Double.compare(sumOrderTotal, totalPrice)!=0){
			throw new ErrorOnPageException("Total Price not equals sum[order total]",String.valueOf(sumOrderTotal),String.valueOf(totalPrice));
		}
		//verify total past paid is 0
		if(cartPg.getTotalPastPaid().doubleValue()>0){
			throw new ErrorOnPageException("Total Past Paid should be '0'");
		}
		//verify total voucher payment amount = voucher payment amount
		double totalVoucherAmount = cartPg.getTotalVoucherAmount();
		if(Double.compare(Double.parseDouble(voucher.amount.replaceAll("\\$", "")), totalVoucherAmount)!=0){
			throw new ErrorOnPageException("Total Voucher Amount not correct",voucher.amount,String.valueOf(totalVoucherAmount));
		}
		//verify total amount owing and minimum pay equals [total price] less [total voucher payment]
		double amountOwing = cartPg.getAmountOwing();
		double minimumPay = cartPg.getMinimumPaymentDue();
		if(Double.compare(totalPrice-totalVoucherAmount, amountOwing)!=0){
			throw new ErrorOnPageException("Total Amount Owing calculate not correct",String.valueOf(totalPrice-totalVoucherAmount),String.valueOf(amountOwing));
		}
		if(Double.compare(totalPrice-totalVoucherAmount, minimumPay)!=0){
			throw new ErrorOnPageException("Total Minimum Payment Due calculate not correct",String.valueOf(totalPrice-totalVoucherAmount),String.valueOf(minimumPay));
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		schema = DataBaseFunctions.getSchemaName("MS",env);
		
		privilege.code = "COP";
		privilege.name = "CalculateOrderPrice";
		privilege.purchasingName = "COP-CalculateOrderPrice";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "Auto test";
		
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Oct 06 1987";
		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "215678451";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		
		newCust.identifier.identifierType = "Green Card";
		newCust.identifier.identifierNum = "987654321";
		newCust.identifier.country="Canada";
		newCust.customerClass = "Individual";
		newCust.dateOfBirth = "19870803";
		newCust.residencyStatus="Non Resident";
		
		pay.payType ="Cash";
	}
}
