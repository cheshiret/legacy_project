/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.consumables.order;

import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: verify purchase work flow 
 *               verify purchase info in order cart page and order summary page
 * @Preconditions: need POS product could purchase
 *                 need active customer 
 * @LinkSetUp:d_hf_add_cust_profile 770 <TEST-Advanced1,QA-Advanced1>
 *            d_hf_addconsu_prd 70 <POSForVoid>
 * @SPEC: 	Purchase Subscription [TC:024150] 
 *          Order Cart view [TC:024152] 
 *          Order Summary view [TC:024151]
 * @Task#: AUTO-1456
 * @author szhou
 * @Date  Mar 25, 2013
 */
public class PurchaseSubscription extends LicenseManagerTestCase{

	@Override
	public void execute() {
       lm.loginLicenseManager(login);
		
		//purchase a consumable
       lm.makeConsumableOrderToCartFromCustomerTopMenu(cust, consumable);
		// view in order cart page
       this.verifyCustomerInfo();
	   this.verifyTransTypeAndOrderType();
	   lm.verifyAddedCorrectConsumableAndQty(consumable.name, consumable.licenseYear, "1", 1);
		
		String orderNum = lm.processOrderCartToOrderSummaryPage(pay,Boolean.valueOf(TestProperty.getProperty("control.printdoc", "true")));
		// view in order summary page
		this.verifyItemInfoInOrderSummaryPg();
		// verify order status
		lm.verifyResStatusInDB(orderNum, PROC_STATUS_PREARRIVAL, ORD_STATUS_ACTIVE, -1, schema);
		
		lm.logOutLicenseManager();
	}
	
	private void verifyTransTypeAndOrderType(){
		OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();
		String transaction=ormsOrderCartPg.getFirstTransactionName();
		String ordertype=ormsOrderCartPg.getFirstOrderType().split("\\(")[0];
		if(!TRANNAME_PURCHASE_CONSUMABLE.equals(transaction)||!ORDERTYPE_POS_SALE.equals(ordertype)){
			throw new ErrorOnDataException("transaction name or order type is not correct.",TRANNAME_PURCHASE_CONSUMABLE+"+"+ORDERTYPE_POS_SALE,transaction+"+"+ordertype);
		}
	}
	
	private void verifyCustomerInfo(){
		OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();
		String expValue;
		boolean result = true;
		
		//verify customer info
		expValue =cust.lName + "," + cust.fName;
		result &= MiscFunctions.compareResult("Customer name", expValue, ormsOrderCartPg.getCustName());
		
		if(!result){
			throw new ErrorOnDataException("customer info is not correct,please cheke log...");
		}
	}
	
	private void verifyItemInfoInOrderSummaryPg(){
		OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage.getInstance();
		LicenseMgrHomePage lmHomePg = LicenseMgrHomePage.getInstance();
		
		logger.info("Verify order items info in order summary page.");
		String expValue;
		boolean result = true;
		
		//verify transaction name
		expValue = TRANNAME_PURCHASE_CONSUMABLE+", Make Payment";
		result &= lmOrdSumPg.verifyTransactionInfo(expValue);
		
		//verify order item info
		expValue = "(" + consumable.licenseYear + ") " + consumable.name;
		result &= lmOrdSumPg.verifyProductItemInfo(expValue);
		
		//verify customer info
		expValue =cust.lName + "," + cust.fName;
		result &= MiscFunctions.compareResult("Customer name", expValue, lmOrdSumPg.getCustName());
		if(StringUtil.notEmpty(cust.email)){
			result &= MiscFunctions.compareResult("Customer mail", cust.email, lmOrdSumPg.getCustMail());
		}
		
		if(!result){
			throw new ErrorOnDataException("UI displayed info is not correct at order summary page, please check error log.");
		}
		
		lmOrdSumPg.clickFinishButton();
		lmHomePg.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		consumable.name = "WL2 - POSForVoid";
		consumable.licenseYear = lm.getFiscalYear(schema);
		
		cust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "19850224";
		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "111111";
		cust.residencyStatus = "Non Resident";
		cust.lName = "TEST-Advanced1";
		cust.fName = "QA-Advanced1";
	}

}
