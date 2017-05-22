/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.pricing.add;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrAddProductPricingWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegePricingPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrAddProductPricingTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:Add Product Pricing.doc
 * @Task#:AUTO-666
 * @Defects:
 * 
 * @author asun
 * @Date  Aug 1, 2011
 */
public class VerifyUIRequiremeant extends LicMgrAddProductPricingTestCase {
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		LicMgrProductPage prodPage = lm.gotoProductSearchListPageFromTopMenu(pricing.prodType);
		
		if (!prodPage.checkProductRecordExist(pricing.prodCode)) {
			newPrivilegeProduct();
			lm.addPrivilegeProduct(privilege);
		}
		
		pricingPage=lm.gotoProductPricingPageFromListPage(pricing.prodType,pricing.prodCode);
		this.VerifyCommonUIRequirement();
		this.verifyUIRequirementForPrivilege();
		lm.logOutLicenseManager();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		pricing.prodCode="A7";
		pricing.prodType="privilege";
	}
	
	private void newPrivilegeProduct() {
		privilege.code = pricing.prodCode;
		privilege.name = "Privilege " + privilege.code;
		privilege.customerClasses = new String[]{"Individual"};
	}
	
	public void verifyUIRequirementForPrivilege(){
		this.verifyInventoryType(true);
		this.verifyInventoryType(false);
	}
	
	public void verifyInventoryType(  boolean isNone){
		LicMgrPrivilegePricingPage pricingPage=LicMgrPrivilegePricingPage.getInstance();
		LicMgrAddProductPricingWidget addWidget=LicMgrAddProductPricingWidget.getInstance();
		
		logger.info("Verify UI when inventory type is 'None'");
		if(isNone){
			pricingPage.selectInventoryType("None");
		}else{
			pricingPage.selectInventoryType(1);	
		}
		pricingPage.clickApply();
		ajax.waitLoading();
		pricingPage.clickAddProductPricing();
		ajax.waitLoading();
		addWidget.waitLoading();
		
		String[] purchaseTyp=new String[]{"Duplicate","Original","Transfer"};
		List<String> list=addWidget.getPurchaseTypes();
		
		if(!isNone){
			if(!list.contains(purchaseTyp[0])|| !list.contains(purchaseTyp[1])||list.contains(purchaseTyp[2])){
				throw new ErrorOnPageException("The valid option should include :'Original','Duplicate'and no 'Transfer'");
			}
		}else{
			for(String type:purchaseTyp){
				if(!list.contains(type)){
					throw new ErrorOnPageException("Purcahse type '"+type+"' is not found.");
				}
			}
		}
		addWidget.clickCancel();
	}
	
	

}
