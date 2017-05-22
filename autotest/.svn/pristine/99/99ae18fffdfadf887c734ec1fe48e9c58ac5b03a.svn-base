/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.consumables.order;

import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: verify void POS order work flow 
 *               verify process order info
 * @Preconditions: need POS product could purchase
 *                 need active customer 
 * @LinkSetUp: d_hf_add_cust_profile 770 <TEST-Advanced1,QA-Advanced1>
 *             d_hf_addconsu_prd 70 <POSForVoid>
 * @SPEC: 	Void Subscription [TC:024153]  
 *          Process void subscription [TC:024154]
 * @Task#: AUTO-1456
 * @author szhou
 * @Date  Mar 26, 2013
 */
public class VoidSubscription extends LicenseManagerTestCase {
	private String voidReason, voidNote;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// purchase a consumable
		lm.makeConsumableOrderToCartFromCustomerTopMenu(cust, consumable);
		String ordNum = lm.processOrderCart(pay);

		// void order
		lm.gotoConsumableOrderDetailsPage(ordNum);
		lm.voidConsumableOrderToCart(voidReason, voidNote);
		// view in order cart page
		this.verifyCustomerInfo();
		this.verifyTransTypeAndOrderType();
		lm.verifyAddedCorrectConsumableAndQty(consumable.name,
				consumable.licenseYear, "1", 1);
		
		lm.processOrderCart(pay);
		// verify order status
		lm.verifyResStatusInDB(ordNum, PROC_STATUS_PREARRIVAL, ORD_STATUS_VOIDED, -1, schema);
		
		lm.logOutLicenseManager();

	}
	
	private void verifyTransTypeAndOrderType(){
		OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();
		String transaction=ormsOrderCartPg.getFirstTransactionName();
		String ordertype=ormsOrderCartPg.getFirstOrderType().split("\\(")[0];
		if(!TRANNAME_VOID_CONSUMABLE.equals(transaction)||!ORDERTYPE_POS_SALE.equals(ordertype)){
			throw new ErrorOnDataException("transaction name or order type is not correct.",TRANNAME_VOID_CONSUMABLE+"+"+ORDERTYPE_POS_SALE,transaction+"+"+ordertype);
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
		
		voidReason = "12 - Payment Not Received After Printing";
		voidNote = "Automation Sanity Test";

	}

}
