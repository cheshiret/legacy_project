package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.enforcemintocomfirmruleinfield;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This case used to verify set 'Enforce Minimum to Confrim Rule in Field' to yes, 
 * when payment less than minimum payment, process order cart will pop up message;
 * when payment equal to minimum payment, process order cart will success
 * work flow:
 *          1. make pos order to order cart, from quick search, will use public customer
 * check point:
 *            1. payment less than minimum payment, process order cart
 *               verify error message
 *            2. payment equal to minimum payment, process order cart
 *               order cart will process successfully
 * Due to H&F order, full payment is required, so the minimum payment is equal to full payment
 * 
 * @Preconditions: make sure the following setup is existing:
 *                1. d_loc_class_config table,
 *                   make sure the used park's location class configuration record in this table, will have a id(location class configuration id)
 *                   such as location class configuration id = 106, for location class = State Parks Agent
 *                2. d_loc_class_conf_attr table
 *                   make sure the used park's location class have 'Enforce Minimum to Confrim Rule in Field' attribute value record
 *                   such as insert into d_loc_class_config_attr(id, loc_class_conf_id, attr_id, value )values( get_sequence( 'd_loc_class_config_attr' ), 106, 4131, 'Y' );
 *                3. d_loc_attr_value table
 *                   make sure this park's  'Enforce Minimum to Confrim Rule in Field' attribute value is set to yes
 *                   such as:
 *                   insert into d_loc_attr_value(id, loc_id, attr_id, value)
 *                   select get_sequence('d_loc_attr_value'),d_loc.id,4131,'Y'
 *                   from d_loc
 *                   where d_loc.name = 'WAL-MART' and type_id = 7
 * @SPEC: TC:042092
 * @Task#: Auto-1202
 * 
 * @author vzhang
 * @Date  Aug 24, 2012
 */

public class PublicCust_RuleAttrSetTrue_Consumable extends LicenseManagerTestCase{
	private OrmsOrderCartPage orderCartPg = OrmsOrderCartPage.getInstance();
	private OrmsOrderSummaryPage ordSumPg = OrmsOrderSummaryPage.getInstance();
	private String locationName,attrName,expMsg,reason,note;

	@Override
	public void execute() {
		this.checkSetup();
		lm.loginLicenseManager(login);
		
		lm.makeConsumableOrderToCartFromQuickSearch(consumable);
		double fullAmount = orderCartPg.getAmountOwing();
		
		//payment amount less than minimum payment, verify error message
		//(minimum payment is equal to full payment because H&F must full payment is required)
		pay.amount = String.valueOf(fullAmount-1);
		String actMsg = this.processOrderCart(true);
		System.out.println(actMsg);
		System.out.println(expMsg);
		this.verifyErrorMessage(expMsg, actMsg);
		
		//payment amount equal to minimum payment, order could process success
		pay.amount = String.valueOf(fullAmount);
		this.processOrderCart(false);
		String orderNum = ordSumPg.getAllOrdNums();
		lm.finishOrder();
		
		//clear up
		lm.gotoConsumableOrderDetailsPage(orderNum);
		lm.voidConsumableOrderToCart(reason, note);
		lm.processOrderCart(pay);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		locationName = "WAL-MART";
		login.location = "HF HQ Role/" + locationName;
		
		// Initialize consumable info
		consumable.name = "WL2 - POSForVoid";
		consumable.quantity = "1";
		reason = "14 - Other";
		note = "Auto Test";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		attrName = OrmsConstants.ATTRIBUT_ENFORCEMINIMUMTOCONFIRMRULEINFIELD_NAME;
		expMsg = "The Total Payment entered is less than the Minimum Payment Due. Please enter additional Payment.";
	}
	
	private void checkSetup(){
		boolean isExisting = lm.checkLocationAttributeValueWetherExisting(locationName,attrName,true,schema);
		if(!isExisting) {
			isExisting = lm.checkLocationAttributeValueWetherExisting(locationName, attrName, false, schema);
			if(isExisting) {
				lm.updateLocationAttributeValue(schema, attrName, locationName, true);
			}
			lm.insertLocationAttributeValue(schema, attrName, locationName, true);
		}
	}
	
	private String processOrderCart(boolean isPopupMessage){	
		String actMessage = "";
		logger.info("Process order cart.");
		
		orderCartPg.setupPayment(pay);
		orderCartPg.clickProcessOrder();
		if(isPopupMessage){
			orderCartPg.waitLoading();
			actMessage = orderCartPg.getErrorMessage();
		}else{
			try{
				ordSumPg.waitLoading();
			}catch(PageNotFoundException e){
				throw new ErrorOnPageException("Expect order cart could be process success. Order summary page should wait existing");
			}			
		}
		
		return actMessage;
	}
	
	private void verifyErrorMessage(String expMessage, String actMessage){
		logger.info("Verify error message.");
		boolean result = MiscFunctions.compareResult("Error Message when payment less than min payment", expMessage, actMessage);
		
		if(!result){
			throw new ErrorOnPageException("The Error Message when payment less than min payment is not correct.");
		}else{
			logger.info("The Error Message when payment less than min payment is correct.");
		}
	}

}
