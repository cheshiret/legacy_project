package com.activenetwork.qa.awo.testcases.regression.basic.web.bw.lottery;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.keywords.web.BWCooperator;
import com.activenetwork.qa.awo.pages.web.uwp.UwpPermitLotteryApplicationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: (P)
 *  Make permit lottery to 'Lottery Application' page to check default selection 'Online'
 *
 * @Preconditions: 
 * 1. Have Permit type 'Auto Overnight (Commercial)' has 'Applicable'"Permit Delivery Method
 * 2. Have entrance, code: PLDM, name:PermitLotteryDeliveryMethod
 * 3. Has permit lottery with
 * Name: PermitLotteryDeliveryMethod
 * Participating Location: Inyo National Forest - Wilderness Permits
 * Participating Product Groups: Entry Point
 * Participating Products: PermitLotteryDeliveryMethod
 * Inventory Start and End Date: 12/15/currentYear~12/31/currentYear

 * @SPEC: Lottery Application - Permit Delivery Method -Commercial [TC:035995] 
 * @Task#: AUTO-1175 
 * 
 * @author SWang
 * @Date  Aug 27, 2012
 */
public class DeliveryMethod_ApplicationPg extends WebTestCase {
	private PermitInfo bd = new PermitInfo();
	private BWCooperator bw = BWCooperator.getInstance();

	public void execute() {
		//Precondition check
		bw.preparePermitWithDeliveryMethod(schema, bd.parkId, bd.permitType, true);

		//Check default Delivery Method
		web.invokeURL(url);
		bw.signInBW(email, pw);
		bw.submitLotteryToLotteryApplication(bd, bd.isUnifiedSearch);
		this.verifyDeliveryMethodInLottery();
		bw.signOutBW();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.bw.url");
		email = TestProperty.getProperty("inyonationalforest.coop.email");
		pw = TestProperty.getProperty("inyonationalforest.coop.pwd");

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		bd.parkId = "72203";
		bd.contractCode = "NRSO";
		bd.facility = DataBaseFunctions.getFacilityName(bd.parkId, schema);//"Inyo National Forest - Wilderness Permits";

		bd.entrance = "PLDM PermitLotteryDeliveryMethod";
		bd.permitType = "Auto Overnight (Commercial)";
		bd.isRange = false;
		bd.entryDate = web.queryPermitLotteryInventoryStartDate(schema, "InyoForPermitLotteryDeliverMethod");
		bd.deliveryMethod = "Online";
	}

	/**
	 * Verify default Delivery Method value
	 */
	public void verifyDeliveryMethodInLottery(){
		UwpPermitLotteryApplicationPage lotteryApplicablePg = UwpPermitLotteryApplicationPage.getInstance();
		String defaultValue_Actual = lotteryApplicablePg.getPreferredChoiceDeliveryMethodValue();

		if(!bd.deliveryMethod.equals(defaultValue_Actual)){
			throw new ErrorOnPageException("Delivery Method default value is wrong!", bd.deliveryMethod, defaultValue_Actual);
		}
		logger.info("Successfully verify default Delivery Method value - "+defaultValue_Actual);
	}
}

