package com.activenetwork.qa.awo.supportscripts.support.licensemgr;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrAddProductPricingWidget;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
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
public class AddPricing extends SupportCase {
	private boolean loggedIn = false;// Don't login.
	private LoginInfo login = new LoginInfo();
	private String contract = "";
	private PricingInfo pricing=new PricingInfo();
//	private LicMgrPrivilegePricingPage pricingPage = LicMgrPrivilegePricingPage.getInstance();
	private ILicMgrProductPricingPage pricingPage = null;
	private LicenseManager lm = LicenseManager.getInstance();
	private String schema = "";
	private String previousProdType = "";
	private String previousCode = "";
	
	public void execute() {
		//log into license Manger
		if (!contract.equalsIgnoreCase(login.contract) && loggedIn) {
			lm.logOutLicenseManager();
			loggedIn = false;
		}
		if((!loggedIn ) || (loggedIn && !pricingPage.exists())){
			lm.loginLicenseManager(login);
			loggedIn = true;
		}
		if(!previousProdType.equalsIgnoreCase(pricing.prodType)) {
			lm.gotoProductSearchListPageFromTopMenu(pricing.prodType);
		}
		
		if(!previousCode.equalsIgnoreCase(pricing.prodCode)) {
			lm.gotoProductListFromProductDetailPage();
			pricingPage = lm.gotoProductPricingPageFromListPage(pricing.prodType,
					pricing.prodCode);
		}
		lm.addPricingForProduct(pricing, pricingPage, true);
		this.verifyResult();
		
		contract = login.contract;
		previousProdType = pricing.prodType;
		previousCode = pricing.prodCode;
	}

	@Override
	public void wrapParameters(Object[] param) {
		cursor = 0;
		startpoint = 145; // the start point in the data pool
		endpoint = 145; // the end point in the data pool
		
		dataSource = casePath + "/" + caseName;
		
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		logMsg=new String[4];
		logMsg[0] = "Index";
		logMsg[1] = "ProductType";
		logMsg[2] = "PrivilegeCode";
		logMsg[3] = "Result";
	}
	
	@Override
	public void getNextData() {
		login.contract=dpIter.dpString("contract");
		login.location=dpIter.dpString("location");
		
		pricing.prodType = dpIter.dpString("productType");
		pricing.prodCode = dpIter.dpString("productCode");
		pricing.status = dpIter.dpString("status");
		pricing.locationClass = dpIter.dpString("locationClass");
		pricing.licYearFrom = dpIter.dpString("licenseYearFrom");
		pricing.licYearTo = dpIter.dpString("licenseYearTo");
		pricing.purchaseType = dpIter.dpString("pruchaseType");
		pricing.effectFrom = dpIter.dpString("effectiveFromDate");
		if(pricing.effectFrom.length() < 1) {
			pricing.effectFrom = DateFunctions.getToday(DataBaseFunctions
					.getContractTimeZone(schema));
		}
		pricing.effectTo = dpIter.dpString("effectiveToDate");
		pricing.vendorFee =dpIter.dpString("vendorFee");
		pricing.stateTransFee = dpIter.dpString("stateTransFee");
		pricing.stateFee = dpIter.dpString("stateFee");
		pricing.stateFee_SplitBy = dpIter.dpString("stateFeeSplitBy");
		pricing.stateFee_SplitInto = dpIter.dpString("stateFeeSplitInto");
		pricing.stateFee_accounts = this.getTheAccountInfo(dpIter.dpString("stateFeeAccount"), dpIter.dpString("stateFeeValue"));
		
		//pricing.stateFee_accounts = 
		pricing.transFee = dpIter.dpString("TransFee");
		pricing.transFee_SplitBy = dpIter.dpString("transFeeSplitBy");
		pricing.transFee_SplitInto = dpIter.dpString("transFeeSplitInto");
		pricing.transFee_accounts = this.getTheAccountInfo(dpIter.dpString("transFeeAccount"), dpIter.dpString("transFeeVaule"));
		
		logMsg[0] = String.valueOf(cursor);
		logMsg[1] = pricing.prodType;
		logMsg[2] = pricing.prodCode;
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
	
	private void verifyResult() {
		LicMgrAddProductPricingWidget addProdPringWidget = LicMgrAddProductPricingWidget
		.getInstance();
		if(!pricingPage.exists()) {
			logger.error("Add privilege pricing failed:Price product type="+pricing.prodType+",Privileg Code="+previousCode+addProdPringWidget.getErrorMsg());
			logMsg[3] = "Failed";
		} else {
			logMsg[3] = "Passed";
		}
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
