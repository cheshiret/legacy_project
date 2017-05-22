package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.product.pricing;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo.ChoiceFee;
import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrViewProductPricingListTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: View Privilege lottery product pricing list
 * @Preconditions:
 * Privilege lottery product exist "PL1"
 * @SPEC: 
 * View Product Pricing List - use case [TC:045992]
 * View Product Pricing List - general UI requirment [TC:045991]
 * @LinkSetUp:
 * d_hf_add_lottery_prd:id=30
 * @Task#: Auto-2060
 * 
 * @author Lesley Wang
 * @Date  Jan 7, 2014
 */
public class ViewPricingList extends LicMgrViewProductPricingListTestCase {
	private List<PricingInfo> pricingInfos = new ArrayList<PricingInfo>();
	private PricingInfo pricing2, pricing3, pricing4, pricing5, pricing6;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		pricingPage = lm.gotoProductPricingPageFromListPage(pricing.prodType,pricing.prodCode);
		lm.deactivateAllProductPricings(pricingPage); //Blocked by DEFECT-60283
		for(int i=pricingInfos.size()-1; i>=0; i--){
			logger.info("Starting with a new pricingInfo (" + i + ")");
			pricingInfos.get(i).id = lm.addPricingForProduct(pricingInfos.get(i), pricingPage, true);
		} 

		//verify pricing list info
		resetPriceInfoOrder();
		pricingPage.searchPricingRecords(pricing.status);
		boolean pass = this.verifyPricingListInfo(pricingInfos);
		//verify UI requirements
		pass &= this.verifyUIRequirements();
		
		//clear up environment
		lm.deactivateAllProductPricings(pricingPage);
		
		if(!pass){
			throw new ErrorOnPageException("Some check points is not correct, please check.");
		}
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		// Pricing info
		pricing.status = "Active";
		pricing.prodType = "Lottery";
		pricing.prodCode = "PL1";
		pricing.locationClass = "04 - Commercial Agent";
		pricing.licYearFrom = String.valueOf(DateFunctions.getCurrentYear() + 4);
		pricing.licYearTo = pricing.licYearFrom;
		pricing.effectFrom = DateFunctions.getToday();
		pricing.effectTo = DateFunctions.getDateAfterToday(30);
		pricing.rateType = CHOICE_RANGE_TYPE;
		ChoiceFee range1 = pricing.new ChoiceFee();
		range1.range = "1";
		range1.vendorFee = "24.15";
		range1.stateFee = "12.31";
		range1.transFee = "14.34";
		pricing.choiceFees.add(range1);
		pricing.stateVendorFee = "9.52";
		pricing.stateTransFee = "15.97";
		pricing.holdingFee = "1.58";
		pricing.isAppliesToAllState = true;
		
		pricing2 = new PricingInfo();
		pricing2.status = pricing.status;
		pricing2.prodType = pricing.prodType;
		pricing2.prodCode = pricing.prodCode;
		pricing2.locationClass = "06 - State Parks Agent";
		pricing2.licYearFrom = "All";
		pricing2.effectFrom =pricing.effectFrom;
		pricing2.rateType = PER_CHOICE_TYPE;
		ChoiceFee range2 = pricing.new ChoiceFee();
		range2.range = "1";
		range2.vendorFee = "20.05";
		range2.stateFee = "2.32";
		range2.transFee = "4.37";
		ChoiceFee range2_2 = pricing.new ChoiceFee();
		range2_2.range = "";
		range2_2.vendorFee = "22.15";
		range2_2.stateFee = "13.11";
		range2_2.transFee = "5.01";
		pricing2.choiceFees.add(range2);
		pricing2.choiceFees.add(range2_2);
		pricing2.stateTransFee = "11.99";
		pricing2.stateVendorFee = "0.00";
		pricing2.holdingFee = "2.01";
		pricing2.isAppliesToAllState = false;
		pricing2.addState = "SC";
		
		pricing3 = new PricingInfo();
		pricing3.status = pricing.status;
		pricing3.prodType = pricing.prodType;
		pricing3.prodCode = pricing.prodCode;
		pricing3.locationClass = "All";
		pricing3.licYearFrom = "All";
		pricing3.effectFrom = pricing.effectFrom;
		pricing3.rateType = PER_APPLICATION_TYPE;
		pricing3.vendorFee = "19.11";
		pricing3.stateTransFee = "12.57";
		pricing3.stateFee = "11.94";
		pricing3.transFee = "10.05";
		pricing3.stateVendorFee = "4.59";
		pricing3.holdingFee = "0.00";
		pricing3.isAppliesToAllState = false;
		pricing3.addState = "CA,MS";
		
		pricing4 = new PricingInfo();
		pricing4.status = pricing.status;
		pricing4.prodType = pricing.prodType;
		pricing4.prodCode = pricing.prodCode;
		pricing4.locationClass = "All";
		pricing4.licYearFrom = "All";
		pricing4.effectFrom = pricing.effectFrom;
		pricing4.rateType = CHOICE_RANGE_TYPE;
		ChoiceFee range4 = pricing.new ChoiceFee();
		range4.range = "3";
		range4.vendorFee = "21.11";
		range4.stateFee = "11.34";
		range4.transFee = "10.04";
		pricing4.choiceFees.add(range1);
		pricing4.choiceFees.add(range4);
		pricing4.stateTransFee = "12.27";
		pricing4.stateVendorFee = "4.51";
		pricing4.calculateHoldingFeeBasedOnSelectedChoices = true;
		pricing4.holdingFee = "0.00";
		pricing4.isAppliesToAllState = false;
		pricing4.addState = "LA,MA";
		
		pricing5 = new PricingInfo();
		pricing5.status = pricing.status;
		pricing5.prodType = pricing.prodType;
		pricing5.prodCode = pricing.prodCode;
		pricing5.locationClass = "All";
		pricing5.licYearFrom = "All";
		pricing5.effectFrom = pricing.effectFrom;
		pricing5.rateType = PER_CHOICE_TYPE;
		ChoiceFee range5 = pricing.new ChoiceFee();
		range5.range = "2";
		range5.vendorFee = "20.05";
		range5.stateFee = "2.32";
		range5.transFee = "4.37";
		ChoiceFee range5_2 = pricing.new ChoiceFee();
		range5_2.range = "";
		range5_2.vendorFee = "22.12";
		range5_2.stateFee = "0.00";
		range5_2.transFee = "5.04";
		pricing5.choiceFees.add(range5);
		pricing5.choiceFees.add(range5_2);
		pricing5.stateTransFee = "11.99";
		pricing5.stateVendorFee = "1.00";
		pricing5.calculateHoldingFeeBasedOnSelectedChoices = true;
		pricing5.holdingFee = "0.00";
		pricing5.isAppliesToAllState = true;
		
		pricing6 = new PricingInfo();
		pricing6.status = pricing.status;
		pricing6.prodType = pricing.prodType;
		pricing6.prodCode = pricing.prodCode;
		pricing6.locationClass = pricing.locationClass;
		pricing6.licYearFrom = String.valueOf(DateFunctions.getCurrentYear() + 5);
		pricing6.licYearTo = pricing6.licYearFrom;
		pricing6.effectFrom = pricing.effectFrom;
		pricing6.rateType = PER_APPLICATION_TYPE;
		pricing6.vendorFee = "9.11";
		pricing6.stateTransFee = "2.57";
		pricing6.stateFee = "1.94";
		pricing6.transFee = "0.05";
		pricing6.stateVendorFee = "4.09";
		pricing6.holdingFee = "1.09";
		pricing6.isAppliesToAllState = true;
		
		pricingInfos.add(pricing);
		pricingInfos.add(pricing2);
		pricingInfos.add(pricing3);
		pricingInfos.add(pricing4);
		pricingInfos.add(pricing5);
		pricingInfos.add(pricing6);
	}
	
	/** Reset pricing info sort order by
	 * First, by the Location Class with "All" first followed by the Code of the Location Class in ascending order; and
	 * Second, by the License Year with "All" first followed by the License Year in ascending order; and
	 * Third, by the Applicable State/Province with "All", followed by the State/Province in ascending order
	 * Fourth, by the Rate Type in ascending order
	 */
	private void resetPriceInfoOrder() {
		pricingInfos.clear();
		pricingInfos.add(pricing5);
		pricingInfos.add(pricing4);
		pricingInfos.add(pricing3);
		pricingInfos.add(pricing);
		pricingInfos.add(pricing6);
		pricingInfos.add(pricing2);
	}
}
