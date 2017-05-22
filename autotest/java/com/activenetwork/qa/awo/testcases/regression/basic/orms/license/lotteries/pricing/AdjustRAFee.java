package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.pricing;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HFLotteryProduct;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderFeesDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrApplicationOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * @Description:This case is used to verify adjust in fee detail page when purchase a lottery privilege
 * @LinkSetUp:
 *			  d_hf_add_privilege_prd:id=2640 --PFL,Product For Lottery
 * 			  d_hf_add_pricing:id=3892 --Priv, lottery ,3782
 * 			  d_hf_assi_pri_to_store:id=1870
 * 			  d_hf_add_prvi_license_year:id=2770
 * 			  d_hf_add_qty_control:id=1850
 * 			 
 * 			  d_hf_add_hunt_quota:id=280
 * 			  d_hf_add_hunt_location:id=10
 * 			  d_hf_add_weapon:id=10
 *			  d_hf_add_hunt:id=850 --HFO1  
 * 			  d_hf_add_hunt_license_year:id=730
 * 			  d_hf_assign_priv_to_hunt:id=550
 * 
 * 			  d_hf_add_lottery_prd:id=260(CODE='LPP') 
 * 			  d_hf_assi_hunts_to_lottery:id=230 
 * 			  d_hf_add_lottery_license_year:id=170
 * 			  d_hf_add_lottery_quantity_control:id=170
 * 
 *            d_hf_add_lottery_execution_config:id=140(DESCRIPTION='DrawLotteryForOrder')
 *            d_hf_add_lottery_schedule:id=80 
 * @SPEC:[TC:056676] Adjust Contractor(RA) Fees – Privilege Lottery Application Order - processing 
 * @Task#: Auto-2064
 * @Note:Need to add a ra fee for the product under contract level
 * @author Phoebe Chen
 * @Date  March 06, 2014
 */
public class AdjustRAFee extends LicenseManagerTestCase{
	private String adjustNote,	olderValue, adjustValue, orderNum;
	private HFLotteryProduct lottery = new HFLotteryProduct();
	private LicMgrOrderFeesDetailsPage feepg = LicMgrOrderFeesDetailsPage.getInstance();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.makePrivilegeLotteryToCartFromCustomerQuickSearch(cust, lottery);
		orderNum = lm.processOrderCart(pay);
		lm.gotoApplicationOrderDetailsPageFromTopMenu(orderNum);
		this.gotoFeeDetailPage();
		
		// adjust fee(only RA fee can be edited)
		this.verifyFeesEditable();
		
		this.adjustRAFee(TRANNAME_SUBMIT_LOTTERY_ENTRY, adjustNote);
		lm.processOrderCart(pay);
		
		// go to application order detail
		lm.gotoApplicationOrderDetailsPageFromTopMenu(orderNum);
		// go to fee adjustment page
		this.gotoFeeDetailPage();
		// verify RA fee adjustment,Fee was Previously Adjusted set to YES 
		this.verifyAjustment(TRANNAME_SUBMIT_LOTTERY_ENTRY, adjustValue,olderValue);
		this.verifyRAFeeReversalRecordInDB(0, "1","6");
		this.verifyRAFeeReversalRecordInDB(2, "1","2");
		this.backToHomePg();
		
		//clean up
		lm.gotoApplicationOrderDetailsPageFromTopMenu(orderNum);
		lm.reverseApplicationOrderToCart("14 - Operation Error", "Automation-test");
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}
	
	private void verifyRAFeeReversalRecordInDB(int num, String expType, String expStatus){
		boolean passed = true;
		logger.info("Trying to check Ra fee info for order -->"+orderNum);
		List<HashMap<String,String>> raFeeRecords= lm.getRAFeeInfoFromDB(orderNum,schema);
		//1	DEBIT	 2	CREDIT, RA Fee Type, which shall be set to "Credit" 
		passed &= MiscFunctions.compareResult("RA Fee type", expType, raFeeRecords.get(num).get("RAFEE_TYPE"));
		//1Pending	 2Priced  3Void  4Invoiced  5Distributed  6Reversed  7Canceled, RA Fee Status, which shall be set to "Priced" 
		passed &= MiscFunctions.compareResult("RA Fee status", expStatus, raFeeRecords.get(num).get("RAFEE_STATUS"));
		if(!passed)
		{
			throw new ErrorOnDataException("Checking RA fee info not correct, see the log above");
		}
		logger.info("Checking RA fee info passed!!!");
	}
	
	private void verifyAjustment(String transaction, String value,String older) {
		String actual = feepg.getContractFeesPrice().get(transaction).split("\\$")[1];
		if (new BigDecimal(older).compareTo(new BigDecimal(value)) == 0) {
			throw new ErrorOnPageException(
					"Adjust RA fee unsuccessful...", value, older);
		}
		if (new BigDecimal(actual).compareTo(new BigDecimal(value)) != 0) {
			throw new ErrorOnPageException(
					"RA fee adjustment amount is not correct...", value, actual);
		}
	}

	private void adjustRAFee(String transaction, String note) {
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
		olderValue = feepg.getContractFeesPrice().get(transaction).split("\\$")[1];
		adjustValue= new BigDecimal(olderValue).add(new BigDecimal("1.8")).toString();
		feepg.inputAdjustmentValue(transaction, adjustValue);
		feepg.inputAdjustmentNotes(note);
		feepg.clickOK();
		ajax.waitLoading();
		ordCartPg.waitLoading();
	}
	
	private void gotoFeeDetailPage() {
		LicMgrApplicationOrderDetailsPage detailsPage = LicMgrApplicationOrderDetailsPage.getInstance();
		detailsPage.clickFees();
		ajax.waitLoading();
		feepg.waitLoading();
	}
	
	private void verifyFeesEditable() {
		if (!feepg.isCustormerFeesUneditable()) {
			throw new ErrorOnPageException("Customer fees could not edit...");
		}
	}
	
	private void backToHomePg() {
		LicenseMgrHomePage licMrgHomePg = LicenseMgrHomePage.getInstance();
		feepg.clickHome();
		licMrgHomePg.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = DataBaseFunctions.getSchemaName("MS",env);

		cust.lName = "Test-ViewPriDetail";
		cust.fName ="QA-ViewPriDetail";
		cust.identifier.identifierType = "MDWFP #";//"Green Card";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		cust.identifier.country = "Canada";
		cust.customerClass = "Individual";
		cust.residencyStatus="Non Resident";
		cust.phoneContact = "4088144589";
		cust.email = "jas@sina.com";
		
		hunt.setHuntCode("HFO1");
		
		//lottery info
		lottery.setCode("LPO"); 
		lottery.setDescription("Lottery Product For Order");
		lottery.setPurchasingName(lottery.getCode() + "-" + lottery.getDescription());
		lottery.setLicenseYear(lm.getFiscalYear(schema));//String.valueOf(DateFunctions.getCurrentYear())); //lm.getFiscalYear(schema));
		lottery.setHuntCodes(Arrays.asList(new String[]{hunt.getHuntCode()}));
		lottery.setApplicantType("Individual");
		
		adjustNote = "qa auto test";
	}

}
