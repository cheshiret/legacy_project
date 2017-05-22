package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.enforcemintocomfirmruleinfield;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This case used to verify set 'Enforce Minimum to Confrim Rule in Field' to no, 
 * when payment less than minimum payment, process order cart will success
 * work flow:
 *          1. make privilege order to order cart
 *          2. transfer this order
 *          3. add a consumable from order cart to order cart
 *          3. verify check point
 * check point:
 *            1. payment less than minimum payment, process order cart
 *               process order cart will success
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

public class MultiPrdInOneCart_RuleAttrSetFalse extends LicenseManagerTestCase{
	private OrmsOrderCartPage orderCartPg = OrmsOrderCartPage.getInstance();
	private OrmsOrderSummaryPage ordSumPg = OrmsOrderSummaryPage.getInstance();
	private String locationName,attrName;
	private Customer toCust = new Customer();

	@Override
	public void execute() {
		this.checkSetup();
		lm.loginLicenseManager(login);
		
		// purchase a privilege
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay);
		
		//transfer privilege order
		lm.gotoOrderPageFromOrdersTopMenu(orderNum);
		String privilegeNum = LicMgrPrivilegeOrderDetailsPage.getInstance().getAllPrivilegesNum();
		lm.gotoPrivilegeDetailFromOrderPg(privilegeNum);
		lm.transferPrivToOrderCart(toCust, privilege);
		//add a consumable product from order cart
		lm.addConsumableFromCartToCart(consumable);
		double fullAmount = orderCartPg.getAmountOwing();
		
		//payment amount less than minimum payment, order process success
		//(minimum payment is equal to full payment because H&F must full payment is required)
		pay.amount = String.valueOf(fullAmount-1);
		this.processOrderCart();
		lm.finishOrder();
		
		lm.logOutLicenseManager();	
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		locationName = "AgentForIssRefInCash";
		login.location = "HF HQ Role/" + locationName;
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		privilege.code = "TDP";
		privilege.name = "TestPrivilege";
		privilege.purchasingName = privilege.code + "-"+privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "21 - Other";
		privilege.operateNote = "";
		
		cust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "19880618";
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "111199";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		cust.fName="TEST-Basic99";
		cust.lName="QA-Basic99";
		
		toCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		toCust.fName = "QA-Advanced2";
		toCust.lName = "TEST-Advanced2";
		toCust.dateOfBirth = "19850224";
		toCust.identifier.identifierType = "Tax ID";
		toCust.identifier.identifierNum = "333333";
		toCust.identifier.country = "Canada";
		toCust.residencyStatus = "Non Resident";
		
		// Initialize consumable info
		consumable.name = "WL2 - POSForVoid";
		consumable.quantity = "1";	
		
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
		orderCartPg.clickProcessOrder();
		try{
			ordSumPg.waitLoading();
		}catch(PageNotFoundException e){
			throw new ErrorOnPageException("Expect order cart could be process success. Order summary page should wait existing");
		}
	}
}
