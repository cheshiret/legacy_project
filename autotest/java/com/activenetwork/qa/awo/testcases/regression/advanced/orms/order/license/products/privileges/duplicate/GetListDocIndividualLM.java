package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.duplicate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrReplacePrivSaleAddItemPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Get Privilege List for duplicate.
 * @Preconditions:1.Privilege can be purchased and duplicated.
 *                  (Available via Application Only is NO, otherwise can't be purchased.)
 *               2.Privilege PP1 have two pricing: 1 original and 1 replacement.
 *                    Pricing of replacement is applied to All.
 *                  Privilege 999 have two pricing: 1 original and 1 replacement.
 *                    Pricing of replacement is applied to specified "State/Province" which is the same as Customer's.(MS)
 *                  Privilege 999 have three pricing: 1 original and 2 replacement.
 *                    One Pricing of replacement is applied to All.
 *                    The other is applied to specified "State/Province" which is the same as Customer's.(MS)(Should use this pricing when duplicate.)
 *                3.Privilege PP1 and 998 has the same print document, and different from 999's.
 *                4.Customer class is Individual.
 *                  --10 means Privilege, 19 means LM, 1 means Individual.
 *                  Insert into C_CUST_CLASS_CFG values (get_sequence('C_CUST_CLASS_CFG'),19,10,Null,1);
 *                5.PrivilegeReplacementTransactionCoverage is set to 2
 *                  (update X_PROP set value=2 where name='PrivilegeReplacementTransactionCoverage';)
 *                  2 means Replacement of an individual Privilege Instance in an Order also replaces other Privilege Instances in the Order that were printed in the SAME Document(s).
 *                  The price of the Replacement shall take into account the price for replacing that particular Privilege Instance ONLY.
 * @SPEC: Step 1 of TC:042694
 * @Task#:Auto-1243
 * 
 * @author nding1
 * @Date  Sep 14, 2012
 */
public class GetListDocIndividualLM extends LicenseManagerTestCase {
	private PrivilegeInfo pri1 = new PrivilegeInfo();
	private PrivilegeInfo pri2 = new PrivilegeInfo();
	
	public void execute() {
		// setup precondition No.4
		lm.updatePriReplacementTransactionCoverage(schema, "2");
		
		lm.loginLicenseManager(login);
		
		// clean up
		lm.invalidatePrivilegePerCustomer(cust, privilege, pri1, pri2);
		
		// make a privilege order
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege, pri1, pri2);
		String orderNum = lm.processOrderCart(pay).split(" ")[0];

		// duplicate
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoAddItemPgFromCustDetailPg(cust.residencyStatus);
		lm.gotoReplacePrivAddItemPgFromOrgiAddPg();

		// verify product and pricing
		this.verifyProductAndPricing(orderNum);

		// clean up
		lm.gotoHomePage();
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role - Auto/CLARK CREEK NATURAL AREA";
		login.station = "Station 1 AM";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		String fiscalYear = lm.getFiscalYear(schema);
		
		// replacement of pricing is applied to all
		privilege.code = "PP1";
		privilege.name = "PriParticular1";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = fiscalYear;
		privilege.qty = "1";
		privilege.document.docTepl = "License Document";
		
		// replacement of pricing is applied to specified state.
		pri1.code = "999";
		pri1.name = "TransferPrivilege";
		pri1.purchasingName = pri1.code+"-"+pri1.name;
		pri1.licenseYear = fiscalYear;
		pri1.qty = "1";
		pri1.document.docTepl = "FuifillmentDoc";

		// one of replacement of pricing is applied to all and the other is applied to specified state.
		pri2.code = "998";
		pri2.name = "PrivilegeWithQuestion";
		pri2.purchasingName = pri2.code+"-"+pri2.name;
		pri2.licenseYear = fiscalYear;
		pri2.qty = "1";
		pri2.document.docTepl = privilege.document.docTepl;// Don't change!
		question.questDisplayText = "Add New Question For Automation Test";
		question.questionType = "textfield";
		question.questAnswer = "aaa";
		pri2.privilegeQuestions = new QuestionInfo[]{question};

		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;// IMPORTANT: precondition, don't change!
		cust.fName = "QA-DuplicateF2";
		cust.lName = "Test-Duplicate2";
		cust.dateOfBirth = "19861006";
		cust.identifier.identifierType = "Social Security Number";
		cust.identifier.identifierNum = "963258741";// IMPORTANT: the same with support script.
		cust.residencyStatus = "Non Resident";
	}

	/**
	 * Verify product name and pricing of each privilege in duplicate list.
	 * @param orderNum
	 */
	private void verifyProductAndPricing(String orderNum){
		LicMgrReplacePrivSaleAddItemPage replacementPg = LicMgrReplacePrivSaleAddItemPage.getInstance();

		// privilege purchase name list, should contains PP1 and 998
		List<String> purchasingNameList = new ArrayList<String>();
		purchasingNameList.add(privilege.purchasingName);
		purchasingNameList.add(pri2.purchasingName);
		
		// privilege pricing list
		List<String> pricingList = new ArrayList<String>();
		pricingList.add(lm.getPriCustPrice(schema, privilege.code, REPLACEMENT_PURCHASE_TYPE_ID, OrmsConstants.STATUS_ACTIVE));
		pricingList.add(lm.getPriCustPrice(schema, pri2.code, REPLACEMENT_PURCHASE_TYPE_ID, OrmsConstants.STATUS_INACTIVE));
		
		// verify
		replacementPg.verifyDupPriPricingPerDoc(orderNum, privilege.document.docTepl, purchasingNameList, pricingList);

		// privilege purchase name list, should contains 999
		purchasingNameList = new ArrayList<String>();
		purchasingNameList.add(pri1.purchasingName);
		BigDecimal pricing = new BigDecimal(lm.getPriCustPrice(schema, pri1, REPLACEMENT_PURCHASE_TYPE_ID, OrmsConstants.STATUS_INACTIVE));

		// verify
		replacementPg.verifyDupPriPricingPerDoc(orderNum, pri1.document.docTepl, purchasingNameList, pricing.setScale(2).toString());
	}
}
