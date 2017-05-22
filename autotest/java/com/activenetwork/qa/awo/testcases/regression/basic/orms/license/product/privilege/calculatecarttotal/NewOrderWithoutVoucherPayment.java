/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.calculatecarttotal;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description: This script will verify calculate multiple order items and multiple order cart totals calculated correct without voucher payment in the order cart
 * 	1.	purchase a new privilege into cart then add more privilege
 *  2.	click 'purchase privilege' button from order cart,add another privilege order into cart
 *  3.  Verify calculate cart totals refer chapter '8.4	Cart Totals Calculation 1'
 *  
 * @Preconditions: make sure two customer info and one privilege exist before H&F go live
 * @SPEC:<Calculate Cart Totals>
 * @Task#:Auto-928
 * 
 * @author ssong
 * @Date  Mar 9, 2012
 */
public class NewOrderWithoutVoucherPayment extends LicenseManagerTestCase{

	private Customer newCust = new Customer();
	private double onePrivPrice = 0;
	
	@Override
	public void execute() {
		lm.checkPrivilegesExist(schema, privilege.code);
		lm.loginLicenseManager(login);
		//purchase a privilege into order cart and calculate the cart totals
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		this.calculateCartTotals(1);
		//add more privilege into cart and calculate the cart totals
		lm.addMorePrivilegeFromCartToCart(privilege);
		this.calculateCartTotals(2);
		//add another order into cart and calculate the cart totals
		lm.purchasePrivilegeFromCartToCart(newCust, privilege);
		this.calculateCartTotals(3);
		lm.cancelCart();
		lm.logOutLicenseManager();		
	}

	private void calculateCartTotals(int qty){
		OrmsOrderCartPage cartPg = OrmsOrderCartPage.getInstance();
		
		if(qty==1){
			onePrivPrice = cartPg.getAmountOwing();
		}

		logger.info("Check Order Cart Total amount equals "+qty*onePrivPrice);
		
		double sumOrderTotal = cartPg.getOrderTotalSum();
		double totalPrice = cartPg.getTotalPrice().doubleValue();
		double amountOwing = cartPg.getAmountOwing();
		double minimumPay = cartPg.getMinimumPaymentDue();
		//verify sum[order total]=privilege price*qty
		if(Double.compare(sumOrderTotal, onePrivPrice*qty)!=0){
			throw new ErrorOnPageException("Sum[Order Total] calculate not correct",String.valueOf(sumOrderTotal),String.valueOf(onePrivPrice*qty));
		}
		//verify sum[order total]=total price
		if(Double.compare(sumOrderTotal, totalPrice)!=0){
			throw new ErrorOnPageException("Total Price not equals sum[order total]",String.valueOf(sumOrderTotal),String.valueOf(totalPrice));
		}
		//verify sum[order total]=total amount owing
		if(Double.compare(sumOrderTotal, amountOwing)!=0){
			throw new ErrorOnPageException("Amount Owing not equals sum[order total]",String.valueOf(sumOrderTotal),String.valueOf(amountOwing));
		}
		//verify sum[order total]=minimum payment
		if(Double.compare(sumOrderTotal, minimumPay)!=0){
			throw new ErrorOnPageException("Minimum Payment Due not equals sum[order total]",String.valueOf(sumOrderTotal),String.valueOf(minimumPay));
		}
		//verify total past paid is 0
		if(cartPg.getTotalPastPaid().doubleValue()>0){
			throw new ErrorOnPageException("Total Past Paid should be '0'");
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
		privilege.operateReason = "";
		privilege.operateNote = "";
		
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Oct 6 1986";
		cust.identifier.identifierType = "Social Security Number";
		cust.identifier.identifierNum = "963258741";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		
		newCust.identifier.identifierType = "Green Card";
		newCust.identifier.identifierNum = "987654321";
		newCust.identifier.country="Canada";
		newCust.customerClass = "Individual";
		newCust.dateOfBirth = "19870803";
		newCust.residencyStatus="Non Resident";
		
	}
}
