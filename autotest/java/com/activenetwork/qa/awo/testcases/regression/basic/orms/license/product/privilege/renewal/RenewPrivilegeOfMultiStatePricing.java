
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.renewal;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @Description:The two customer from different states should have the same total price. because pricing of privilege has value 'Yes' on attribute 'Applies to All State/Province'.
 * @Preconditions:1. Two customers: 
 * 						QA-Renew1,QA-Renew1 --> State is 'New York'. (D_HF_ADD_CUST_PROFILE: ID=1490)
 * 						QA-Renew2,QA-Renew2 --> State is 'Utah'. (D_HF_ADD_CUST_PROFILE: ID=1500)
 * 				  2. Privilege: rp-renewPrivilegeMulti, pricing of which has value 'no' on attribute 'Applies to All State/Province'.
 * 						this privilege has two pricing configurations: one is for 'UT', another is for 'NY'.
 * 				  3. Feature 'PrivilegeQuickRenew' is assigned to role 'HF HQ Role' in LicenseManager.
 * 
 * @SPEC:Different Sate of pricing on privilege, should be used on different customer which has different state.
 * @Task#:AUTO-1242 Renew Privilege PCR 2940: TC047822(303 feature)
 * 
 * @author pzhu
 * @Date  Oct 31, 2012
 */

public class RenewPrivilegeOfMultiStatePricing extends LicenseManagerTestCase{

    private Customer cust_NY = new Customer(); //customer, state of which is New York.
    private Customer cust_UT = new Customer(); //customer, state of which is Utah.
	
	private String[][] features ={
			{"PrivilegeQuickRenew",	"Privilege Quick Renew"},
			};
	private String totalPriceForNY;
	private String totalPriceForUT;
	private String stateID_NY;
	private String stateID_UT; 
	
	public void execute() {
		
		//check roles/features-->Privilege Quick Renew
		lm.checkRolesFeatures("HF HQ Role", this.features, LICENSE_MANAGER, schema);
		
		lm.loginLicenseManager(login);
		
		lm.invalidatePrivilegePerCustomer(this.cust_NY, privilege);
		lm.invalidatePrivilegePerCustomer(this.cust_UT, privilege);
		lm.gotoLicenseManageHomePage();
		
		String amount_NY = this.getRenewalTotalAmount(cust_NY,privilege);
		logger.info("Total price of renewal for Customer(New York) is -->"+amount_NY);
		String amount_UT = this.getRenewalTotalAmount(cust_UT,privilege);
		logger.info("Total price of renewal for Customer(Utah) is -->"+amount_UT);
		
		
		if(StringUtil.compareNumStrings(this.totalPriceForNY, amount_NY)!=0)
		{
			throw new ErrorOnPageException("Total price of renew privilege of customer from 'New York' is wrong... ", this.totalPriceForNY, amount_NY);
		}else{
			logger.info("Check total price of renew privilege of customer from 'New York' Passed!!!");
		}
		
		if(StringUtil.compareNumStrings(this.totalPriceForUT, amount_UT)!=0)
		{
			throw new ErrorOnPageException("Total price of renew privilege of customer from 'Utah' is wrong... ", this.totalPriceForUT, amount_UT);
		}else{
			logger.info("Check total price of renew privilege of customer from 'Utah' Passed!!!");
		}
				
		lm.logOutLicenseManager();
	}
	
	/**
	 * @param privilege 
	 * @param cust 
	 * 
	 */
	private String getRenewalTotalAmount(Customer cust, PrivilegeInfo privilege) {
		lm.makePrivilegeToCartFromCustomerQuickSearch(cust, privilege);
		String ordNum1 = lm.processOrderCart(pay,false);
		lm.quickRenewalPrivilegeToCart(cust.identifier.identifierNum,privilege);
		String ordNum2 = lm.processOrderCartToOrderSummaryPage(pay,false);
		String amount = this.getTotalPrice();
		lm.finishOrder();
		
		this.clearOrder(ordNum1);
		this.clearOrder(ordNum2);
		
		return amount;
	}

	private void clearOrder(String ordNum) {
		lm.gotoOrderPageFromOrdersTopMenu(ordNum);
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);		
	}

	/**
	 * 
	 */
	private String getTotalPrice() {
		OrmsOrderSummaryPage pmOrdSumPg = OrmsOrderSummaryPage.getInstance();
		return pmOrdSumPg.getTotalPrice(true);
		
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = DataBaseFunctions.getSchemaName("MS", env);
		
		cust_NY.fName = "QA-Renew1";
		cust_NY.lName = "QA-Renew1";
		cust_NY.customerClass = "Individual";
		cust_NY.dateOfBirth = "Jun 01 1980";
		cust_NY.identifier.identifierType = "MDWFP #";
		cust_NY.identifier.identifierNum = lm.getCustomerNumByCustName(cust_NY.lName, cust_NY.fName, schema);
		cust_NY.residencyStatus = "Non Resident";

		cust_UT.fName = "QA-Renew2";
		cust_UT.lName = "QA-Renew2";
		cust_UT.customerClass = "Individual";
		cust_UT.dateOfBirth = "Jun 01 1980";
		cust_UT.identifier.identifierType = "MDWFP #";
		cust_UT.identifier.identifierNum = lm.getCustomerNumByCustName(cust_UT.lName, cust_UT.fName, schema);
		cust_UT.residencyStatus = "Non Resident";
	
		privilege.purchasingName = "rpn-renewPrivilegeMulti";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.code = "rpn";
		//privilege.operateReason = "14 - Other";
		privilege.operateNote = "QA Automation";
		
		this.stateID_NY = "80251";
		this.totalPriceForNY = lm.getPriCustPrice(schema, privilege.code, ORIGINAL_PURCHASE_TYPE_ID, OrmsConstants.STATUS_INACTIVE,this.stateID_NY);//"20";//Total price comes from table D_HF_ADD_PRICING, ID=2332
		
		this.stateID_UT = "63";
		this.totalPriceForUT = lm.getPriCustPrice(schema, privilege.code, ORIGINAL_PURCHASE_TYPE_ID, OrmsConstants.STATUS_INACTIVE,this.stateID_UT);//"32";//Total price comes from table D_HF_ADD_PRICING, ID=2342
	}
}
