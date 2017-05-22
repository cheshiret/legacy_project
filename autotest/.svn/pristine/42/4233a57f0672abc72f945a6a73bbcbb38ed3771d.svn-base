package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddPricingFunction;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author jwang8
 * @Date  Mar 19, 2012
 */
public class AddPricing extends SetupCase {
	private PricingInfo pricing=new PricingInfo();
	private String schema = "";
	private Object[] args = new Object[3];
	private AddPricingFunction addPricingFunction = new AddPricingFunction();
	
	public void executeSetup() {
		addPricingFunction.execute(args);
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_pricing";
		ids = "3292";
	}
	
	@Override
	public void readDataFromDatabase() {
		args[0] = datasFromDB.get("contract");
		args[1] = datasFromDB.get("location");
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + args[0].toString().split("Contract")[0].trim();
		
		pricing.prodType = datasFromDB.get("productType");
		pricing.prodCode = datasFromDB.get("productCode");
		pricing.status = datasFromDB.get("status");
		pricing.locationClass = datasFromDB.get("locationClass");
		pricing.licYearFrom = datasFromDB.get("licenseYearFrom");
		pricing.licYearTo = datasFromDB.get("licenseYearTo");
		pricing.purchaseType = datasFromDB.get("pruchaseType");
		pricing.effectFrom = datasFromDB.get("effectiveFromDate");
		if(pricing.effectFrom.length() < 1) {
			pricing.effectFrom = DateFunctions.getToday(DataBaseFunctions
					.getContractTimeZone(schema));
		}
		pricing.effectTo = datasFromDB.get("effectiveToDate");
		pricing.vendorFee =datasFromDB.get("vendorFee");
		pricing.stateTransFee = datasFromDB.get("stateTransFee");
		pricing.stateFee = datasFromDB.get("stateFee");
		pricing.stateFee_SplitBy = datasFromDB.get("stateFeeSplitBy");
		pricing.stateFee_SplitInto = datasFromDB.get("stateFeeSplitInto");
		pricing.stateFee_accounts = this.getTheAccountInfo(datasFromDB.get("stateFeeAccount"), datasFromDB.get("stateFeeValue"));
		
		//pricing.stateFee_accounts = 
		pricing.transFee = datasFromDB.get("TransFee");
		pricing.transFee_SplitBy = datasFromDB.get("transFeeSplitBy");
		pricing.transFee_SplitInto = datasFromDB.get("transFeeSplitInto");
		pricing.transFee_accounts = this.getTheAccountInfo(datasFromDB.get("transFeeAccount"), datasFromDB.get("transFeeVaule"));	
		
		pricing.isAppliesToAllState = Boolean.valueOf(StringUtil.isEmpty(datasFromDB.get("ISAPPLIEDTOALL"))?"true":datasFromDB.get("ISAPPLIEDTOALL"));
		pricing.addState = datasFromDB.get("STATE");
		
		//for Lottery product
		pricing.rateType = datasFromDB.get("RateType");
		pricing.stateVendorFee = datasFromDB.get("StateVendorFee");
		pricing.keepPreviousPriceDuringChangeIfHigher = datasFromDB.get("KeepPreviousPrice").equalsIgnoreCase(OrmsConstants.YES_STATUS) ? true : false;
		pricing.calculateHoldingFeeBasedOnSelectedChoices = datasFromDB.get("CalculateHoldingFee").equalsIgnoreCase(OrmsConstants.YES_STATUS) ? true : false;
		pricing.holdingFee = datasFromDB.get("HoldingFee");
		pricing.holdingFeeAccount = datasFromDB.get("HolfingFeeAccount");
		
		args[2] = pricing;
	}

	/**
	 * split the text by comma
	 * @param text - text need to comma
	 * @return String[] - the text list split by comma
	 */
	private String[] splitTextByComma(String text){
		String[] list = new String[]{};
		if(text.contains(",")){
			list =  text.split(",", 0);
		}else if(!text.equals("")){
			list = new String[]{text};
		}
		return list;
	}
	
	/**
	 * Get the account list info
	 * @param account - the test of account in the drop down list.
	 * @param value - the value of percent or amount
	 * @return - the account list info.
	 */
	private List<String[]> getTheAccountInfo(String account,String value){
		List<String[]> arrayList  = new ArrayList<String[]>();
		String [] accountArray = this.splitTextByComma(account);
		String [] valueArray = this.splitTextByComma(value);
		if(accountArray.length != valueArray.length){
			throw new ErrorOnDataException("The account and value should be preparation");
		}
		for(int i = 0;i<accountArray.length;i++){
			arrayList.add(new String[]{accountArray[i],valueArray[i]});
		}
	
		return arrayList;
	}
}
