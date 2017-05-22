package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.consumables.order;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ConsumableInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableProductQuestionsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableSaleAddItemPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuestionWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: verify purchase work flow when POS has question
 * @Preconditions: need POS product could purchase
 *                 need active customer 
 * @LinkSetUp:d_hf_add_cust_profile 770 <TEST-Advanced1,QA-Advanced1>
 *            d_hf_addconsu_prd 80 <POSForSale>
 *            d_hf_assi_consu_to_store 60 <PFS>
 *            d_hf_add_pricing 2482 <PFS>
 * @SPEC: 	Ask Questions for Purchase Subscriptions [TC:024156] 
 * @Task#: AUTO-1456
 * @author szhou
 * @Date  Mar 27, 2013
 */
public class AskQuestion extends LicenseManagerTestCase {

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//Applicable Product Questions for New Sale: create a new product question assignment record
		lm.gotoConsumableQuestionPgFromTopMenu(consumable.id);
		lm.safeAddQuestionsForProduct(question, new LicenseManager.QuestionCompare() {
			@Override
			public String exist(QuestionInfo question) {
				LicMgrConsumableProductQuestionsPage consumableQestionPg = LicMgrConsumableProductQuestionsPage
				.getInstance();
				consumableQestionPg.setSearchCriteria(ACTIVE_STATUS, question.questDisplayText, question.locationClass);
				consumableQestionPg.clickGo();
				ajax.waitLoading();
				consumableQestionPg.waitLoading();
				return consumableQestionPg.getProductQuestionId(3, question.locationClass);
			}
		});
		
		lm.logOutLicenseManager();

		// make a original consumable order
		login.location = "HF HQ Role/WAL-MART";
		lm.loginLicenseManager(login);
		
		// purchase a consumable
		this.gotoConsumableAddItemPage(cust);
		this.addConsumableItem(consumable);
		//verify question
		this.verifyQuestionContent(question.questDisplayText);
		
		lm.logOutLicenseManager();
		
	}
	
	private void addConsumableItem(ConsumableInfo consumable){
		LicMgrConsumableSaleAddItemPage consumAddItemPg = LicMgrConsumableSaleAddItemPage
		.getInstance();
		LicMgrPrivilegeQuestionWidget privQuestWidget = LicMgrPrivilegeQuestionWidget
		.getInstance();
		
		logger.info("Add consumable item - " + consumable.name+ ".");

		consumAddItemPg.addProductToCart(consumable.name, consumable.quantity);
		privQuestWidget.waitLoading();
		
	}
	
	private void verifyQuestionContent(String text){
		LicMgrPrivilegeQuestionWidget privQuestWidget = LicMgrPrivilegeQuestionWidget
		.getInstance();
		LicMgrConsumableSaleAddItemPage consumAddItemPg = LicMgrConsumableSaleAddItemPage
				.getInstance();
		
		if(!privQuestWidget.isQuestionExists(text)){
			throw new ErrorOnPageException("There is no question what we want..."+text);
		}
		
		privQuestWidget.clickCancel();
		ajax.waitLoading();
		consumAddItemPg.waitLoading();
//		consumAddItemPg.clickHome();
	}
	
	private void gotoConsumableAddItemPage(Customer cust) {
		LicMgrOrigPrivSaleAddItemPage privAddItemPg = LicMgrOrigPrivSaleAddItemPage
				.getInstance();
		LicMgrConsumableSaleAddItemPage consumAddItemPg = LicMgrConsumableSaleAddItemPage
				.getInstance();
		
		lm.gotoAddItemPgFromCustomerTopMenu(cust);
		privAddItemPg.clickConsumables();
		ajax.waitLoading();
		consumAddItemPg.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		cust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "19850224";
		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "111111";
		cust.residencyStatus = "Non Resident";
		cust.lName = "TEST-Advanced1";
		cust.fName = "QA-Advanced1";
		
		consumable.name = "PFS - POSForSale";
		consumable.code = "PFS";
		consumable.licenseYear = lm.getFiscalYear(schema);
		consumable.id = lm.getProductID("Product Code", consumable.code,
				null, schema);
		
		question.status = OrmsConstants.ACTIVE_STATUS;
		question.displayOrder = "1";
		question.questDisplayText = "Please provide your phone number.";
		question.originalOption = "Required";
		question.licenseYearFrom = String.valueOf(DateFunctions.getCurrentYear());
		question.licenseYearTo = String.valueOf(DateFunctions.getCurrentYear() + 1);//question.licenseYearFrom;
		question.collectionMethod = "Once per Transaction";
		question.effectiveFromDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		question.effectiveToDate = DateFunctions.getDateAfterGivenDay(question.effectiveFromDate, 3);
		question.locationClass = "All";
		question.questionType = "textfield";
		question.questAnswer = "15929776636";
		
	}

}
