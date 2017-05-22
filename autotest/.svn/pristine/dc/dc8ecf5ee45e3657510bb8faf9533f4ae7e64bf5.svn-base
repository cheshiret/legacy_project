package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.enforcemintocomfirmruleinfield;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This case used to verify set 'Enforce Minimum to Confrim Rule in Field' to no, 
 * when payment less than minimum payment, order cart will process successfully;
 * work flow:
 *          1. make vehicle order to order cart
 *          2. pay use mixed payment type, cash and visa
 *          3. verify check points
 * check point:
 *            1. payment less than minimum payment, process order cart
 *               order cart will process successfully
 *               
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
 *                   make sure this park's  'Enforce Minimum to Confrim Rule in Field' attribute value is set to no
 *                   such as: (No record exists)
 *							insert into d_loc_attr_value(id, loc_id, attr_id, value)
 *							values( get_sequence('d_loc_attr_value'), (select id from d_loc where d_loc.name = 'AgentForIssRefInCash' and type_id = 7), 4131, 'N');
 *						OR (record exist but not 'N')
 *							update d_loc_attr_value set VALUE = 'N' where attr_id = 4131 and loc_id = (select id from d_loc where name = 'AgentForIssRefInCash' and type_id = 7);
 *						
 * @SPEC: TC:041753
 * @Task#: Auto-1202
 * 
 * @author vzhang
 * @Date  Aug 24, 2012
 */

public class MixedPayment_RuleAttrSetFalse extends LicenseManagerTestCase{
	private OrmsOrderCartPage orderCartPg = OrmsOrderCartPage.getInstance();
	private OrmsOrderSummaryPage ordSumPg = OrmsOrderSummaryPage.getInstance();
	private String locationName,attrName;
	private BoatInfo vehicle = new BoatInfo();

	@Override
	public void execute() {
		this.checkSetup();
		
		lm.loginLicenseManager(login);
		//registration a vehicle product
		lm.registerVehicleToOrderCart(cust, vehicle);
		double fullAmount = orderCartPg.getAmountOwing();
		
		//payment amount less than minimum payment, order process success
		//(minimum payment is equal to full payment because H&F must full payment is required)
		pay.amount = String.valueOf((fullAmount-1)/2);
		pay.additionalAmount = pay.amount;
		this.processOrderCart();
		String orderNum = ordSumPg.getAllOrdNums();
		lm.finishOrder();
		
		//clear up
		lm.gotoVehicleOrderDetailPage(orderNum);
		lm.reverseVehicleOrderToOrderCartFromVehicleDetailPg(vehicle.operationReason, vehicle.operationNote);
		pay = new Payment("Visa");
		lm.processOrderCart(pay);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		locationName = "AgentForIssRefInCash";
		login.location = "HF HQ Role/" + locationName;
		
		cust.fName = "QA-VehicleOrdCart";
		cust.lName = "Test-VehicleOrdCart";
		cust.dateOfBirth = "Jan 01 1980";
		cust.licenseType = "MDWFP #";
		cust.mailingAddr.address = "aac Street";
		cust.mailingAddr.supplementalAddr = "Auto test";
		cust.mailingAddr.zip = "12020";
		cust.mailingAddr.city = "Ballston Spa";
		cust.mailingAddr.state = "New York";
		
		vehicle.type = "Boat";
		vehicle.hullIdSerialNum = "MinPayFalseMixedPay"+DataBaseFunctions.getEmailSequence();
		vehicle.manufacturerName = "YAMA";
		vehicle.modelYear = String.valueOf(DateFunctions.getCurrentYear());
		vehicle.feet = "15";
		vehicle.inches = "10";
		vehicle.hullMaterial = "STEEL";
		vehicle.boatUse = "PLEASURE";
		vehicle.propulsion = "SAIL";
		vehicle.fuelType = "Gasoline";
		vehicle.typeOfBoat = "Open";
		vehicle.registration.product = "tta - advTAN";
		vehicle.operationReason = "14 - Other";
		vehicle.operationNote = "QA Automation";
		
		pay.payType = "Cash*";
		Payment additionPay = new Payment("Visa");
		pay.additionalPayType = "Visa";
		pay.additionalCreditCardNumber = additionPay.creditCardNumber;
		pay.additionalExpiryMon = additionPay.expiryMon;
		pay.additionalExpiryYear = additionPay.expiryYear;
		pay.additionalCardHolder = additionPay.cardHolder;
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		attrName = OrmsConstants.ATTRIBUT_ENFORCEMINIMUMTOCONFIRMRULEINFIELD_NAME;
	}
	
	private void checkSetup(){
		boolean isExisting = lm.checkLocationAttributeValueWetherExisting(locationName,attrName,false,schema);
		if(!isExisting) {
			isExisting = lm.checkLocationAttributeValueWetherExisting(locationName, attrName, true, schema);
			if(isExisting) {
				lm.updateLocationAttributeValue(schema, attrName, locationName, false);
			}
			lm.insertLocationAttributeValue(schema, attrName, locationName, false);
		}
	}
	
	private void processOrderCart(){	
		logger.info("Process order cart.");
		
		orderCartPg.setupPayment(pay);
		if(orderCartPg.checkClickAdditionalPaymentExisting()){
			orderCartPg.clickAdditionalPayment();
		}		
		orderCartPg.setupAdditionalPayment(pay);
		orderCartPg.clickProcessOrder();
		try{
			ordSumPg.waitLoading();
		}catch(PageNotFoundException e){
			throw new ErrorOnPageException("Expect order cart could be process success. Order summary page should wait existing");
		}
		
	}

}
