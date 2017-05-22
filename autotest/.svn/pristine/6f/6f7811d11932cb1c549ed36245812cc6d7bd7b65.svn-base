package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.feevalidation;

import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

/**
 * 
 * @Description:
 * @Preconditions: this cases need a Transaction fee created on site 001
 * @SPEC:
 * @Task#:
 * 
 * @author swang
 * @Date  
 */
public class VerifyFeeValidationDetails extends InventoryManagerTestCase{
	String[] feeTypes = new String[]{"Use Fee", "Santee 17 ( Tier 1 discount)", "Accomodations Tax (tax)", "Attribute Fee", "Transaction Fee"};
	String discountName = "Santee 17";

	public void execute(){
		//Login inventory manager
		im.loginInventoryManager(login);
		String siteInfo = im.gotoSiteDetailforCalculation(inventory.facilityName, siteAttr.siteID);

		//Verify the information associated with fee validation details
		for(int i=0; i<feeTypes.length; i++){
			im.verifyFeeValidationDetails(inventory.facilityName, feeTypes[i], siteAttr.siteID, discountName, siteInfo);
			logger.info("...... "+feeTypes[i]+ " ...... verified cussesfully!");
		}

		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName="SANTEE";
		siteAttr.siteID = "1137";
		res.siteIDs=new String[]{"1137"};

		//Fee Penalty details page shall be future released
	}
}
