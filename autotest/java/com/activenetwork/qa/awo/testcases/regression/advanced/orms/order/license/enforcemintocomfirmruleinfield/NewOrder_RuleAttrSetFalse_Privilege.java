package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.enforcemintocomfirmruleinfield;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This case used to verify set 'Enforce Minimum to Confrim Rule in Field' to No, 
 * when payment less than minimum payment, process order cart will success;
 * work flow:
 *          1. make privilege order to order cart
 * check point:
 *            1. payment less than minimum payment, process order cart
 *               order cart will process successfully
 *               
 * Due to privilege order, full payment is required, so the minimum payment is equal to full payment
 * 
 * @Preconditions: make sure the following setup is existing:
 *                1. d_loc_class_config table,
 *                   make sure the used park's location class configuration record in this table, will have a id(location class configuration id)
 *                   such as location class configuration id = 106, for location class = State Parks Agent
 *                2. d_loc_class_conf_attr table
 *                   make sure the used park's location class have 'Enforce Minimum to Confrim Rule in Field' attribute value record
 *                   such as insert into d_loc_class_config_attr(id, loc_class_conf_id, attr_id, value )values( get_sequence( 'd_loc_class_config_attr' ), 106, 4131, 'Y' );
 *                3. d_loc_attr_value table
 *                   make sure this park's  'Enforce Minimum to Confrim Rule in Field' attribute value is set to No
 *                   such as:
 *                   insert into d_loc_attr_value(id, loc_id, attr_id, value)
 *                   select get_sequence('d_loc_attr_value'),d_loc.id,4131,'N'
 *                   from d_loc
 *                   where d_loc.name = 'AgentForIssRefInCash' and type_id = 7      
 * @SPEC: TC:041753
 * @Task#: Auto-1202
 * 
 * @author vzhang
 * @Date  Aug 24, 2012
 */

public class NewOrder_RuleAttrSetFalse_Privilege extends LicenseManagerTestCase{
	private OrmsOrderCartPage orderCartPg = OrmsOrderCartPage.getInstance();
	private OrmsOrderSummaryPage ordSumPg = OrmsOrderSummaryPage.getInstance();
	private String locationName,attrName;

	@Override
	public void execute() {
		this.checkSetup();
		lm.loginLicenseManager(login);

		// purchase a privilege
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		double fullAmount = orderCartPg.getAmountOwing();
		
		//payment amount less than minimum payment, order process success
		//(minimum payment is equal to full payment because H&F must full payment is required)
		pay.amount = String.valueOf(fullAmount-1);
		this.processOrderCart();
		String orderNum = ordSumPg.getAllOrdNums();
		lm.finishOrder();
		
		//clear up
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		pay = new Payment("Visa");
		lm.processOrderCart(pay);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		locationName = "AgentForIssRefInCash";
		login.location = "HF HQ Role/" + locationName;
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		attrName = OrmsConstants.ATTRIBUT_ENFORCEMINIMUMTOCONFIRMRULEINFIELD_NAME;
		
		privilege.code = "TDP";
		privilege.name = "TestPrivilege";
		privilege.purchasingName = privilege.code + "-"+privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "QA Auto Regresssion Test";

		cust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "19880628";
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "111198";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		cust.fName="TEST-Basic98";
		cust.lName="QA-Basic98";
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
		orderCartPg.clickProcessOrder();
		try{
			ordSumPg.waitLoading();
		}catch(PageNotFoundException e){
			throw new ErrorOnPageException("Expect order cart could be process success. Order summary page should wait existing");
		}
		
	}

}
