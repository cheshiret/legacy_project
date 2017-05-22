package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrAddProductPricingWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddPricingFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private PricingInfo pricing=new PricingInfo();
	private ILicMgrProductPricingPage pricingPage = null;
	private LicenseManager lm = LicenseManager.getInstance();
	private boolean loggedin=false;
	private String contract = "";
	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private LicMgrAddProductPricingWidget addWidget = LicMgrAddProductPricingWidget.getInstance();
	private String location;
	
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		login.contract = (String)param[0];
		login.location = (String)param[1];
		pricing = (PricingInfo)param[2];
	}
	@Override
	public void execute() {
		if (!login.contract.equals(contract) && loggedin && isBrowserOpened) {
			lm.logOutLicenseManager();
			loggedin= false;
		}
		if(login.contract.equals(contract) && loggedin && isBrowserOpened){
			if(!login.location.equals(location)){
				lm.switchLocationInHomePage(login.location.replace("/", "-"));
			}
		}
		if (!loggedin || !isBrowserOpened) { 
			lm.loginLicenseManager(login);
			loggedin= true;
		}
		if (!homePg.exists()) {
			if(addWidget.exists()) {
				lm.cancelFromProductPricingWidget(pricingPage);
			}
			lm.gotoHomePage();
		}
		contract = login.contract;
		location = login.location;
		
		if(!pricing.prodType.equalsIgnoreCase("Lottery")) {//for common products: Privilege, Consumable, Supply,.etc
			lm.gotoProductSearchListPageFromTopMenu(pricing.prodType);
		} else {//for Lottery product
			lm.gotoLotteriesProductListPgFromTopMenu();
		}
		
		pricingPage = lm.gotoProductPricingPageFromListPage(pricing.prodType, pricing.prodCode);
		
		String id = lm.addPricingForProduct(pricing, pricingPage, true);
		this.verifyResult(id);
	}
	
	private void verifyResult(String idOrErrorMsg) {
		LicMgrAddProductPricingWidget addProdPringWidget = LicMgrAddProductPricingWidget
		.getInstance();
		if(addProdPringWidget.exists()) {
			addProdPringWidget.clickCancel();
			ajax.waitLoading();
			pricingPage.waitLoading();
			if(idOrErrorMsg.matches("^Another active Product Pricing record \\d+ already exists.*")) {
				//get the existing pricing id
				String existingIds[] = RegularExpression.getMatches(idOrErrorMsg, "\\d+");
				//deactivate the existing pricing
				lm.deactivateProductPricing(existingIds[0], pricingPage);
				//add again
				idOrErrorMsg = lm.addPricingForProduct(pricing, pricingPage, true);
				this.verifyResult(idOrErrorMsg);
			} else {
				throw new ErrorOnPageException("[FAILED]Add product pricing failed:Price product type="+pricing.prodType+", Product Code="+pricing.prodCode+", due to:" + idOrErrorMsg);
			}
		} else {
			boolean passed = pricingPage.checkPricingRecordExists(pricing);
			if (!passed) {
				throw new ErrorOnPageException("[FAILED]Add product pricing failed: Price product type="+pricing.prodType+", Product Code="+pricing.prodCode);
			} else {
				logger.info("[PASSED]Add product pricing successfully: Price product type="+pricing.prodType+", Product Code="+pricing.prodCode);
				newAddValue = idOrErrorMsg;
			}
		}
	}
}
