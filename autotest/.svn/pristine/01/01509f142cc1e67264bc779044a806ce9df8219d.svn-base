package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityQuotaType;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuotaTypeInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 *
 * @author fliu
 * @Date  March 12, 2012
 */
public class InvMgrAddNewQuotaTypePage extends InventoryManagerPage {
	private static InvMgrAddNewQuotaTypePage _instance = null;

	public static InvMgrAddNewQuotaTypePage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrAddNewQuotaTypePage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Quota Type Details");
	}

	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	public void clickApply(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}

	// Quota Type Information -- Start
	public void setQuotaTypeCode(String quotaTypeCode){
		browser.setTextField(".id", "QuotaTypeView.code", quotaTypeCode, true);
	}

	public void setQuotaTypeName(String quotaTypeName){
		browser.setTextField(".id", "QuotaTypeView.name", quotaTypeName, true);
	}

	public void setQuotaInterval(String quotaInterval){
		browser.selectDropdownList(".id", "QuotaTypeView.quotaInterval", quotaInterval);
	}

	public boolean checkQuotaReleaseRulesExists(){
		return browser.checkHtmlObjectExists(".text", "Quota Release Rules");
	}

	public boolean checkCommercialAllocationDropDownExists(){
		return browser.checkHtmlObjectExists(".id", "QuotaTypeView.commercialAllocRelease");
	}

	public boolean checkNonCommercialAllocationDropDownExists(){
		return browser.checkHtmlObjectExists(".id", "QuotaTypeView.nonCommercialAllocRelease");
	}

	public void setCommercialAllocation(String commercialAllocation){
		browser.selectDropdownList(".id", "QuotaTypeView.commercialAllocRelease", commercialAllocation);
	}

	public void setNonCommercialAllocation(String nonCommercialAllocation){
		browser.selectDropdownList(".id", "QuotaTypeView.nonCommercialAllocRelease", nonCommercialAllocation);
	}

	public void setQuotaTypeInfo(QuotaTypeInfo quotaTypeInfo){
		this.setQuotaTypeCode(quotaTypeInfo.quotaTypeCode);
		this.setQuotaTypeName(quotaTypeInfo.quotaTypeName);
		this.setQuotaInterval(quotaTypeInfo.quotaInterval);

		if(checkCommercialAllocationDropDownExists()){
			this.setCommercialAllocation(quotaTypeInfo.commercialAllocation);
		}

		if(checkNonCommercialAllocationDropDownExists()){
			this.setNonCommercialAllocation(quotaTypeInfo.nonCommercialAllocation);
		}
	}

	public void setInfoForNewQuptaType(QuotaTypeInfo quotaTypeInfo){
		logger.info("Set Up Information for adding new Quota Type.");

		// set up quota type information.
		if(null != quotaTypeInfo){
			this.setQuotaTypeInfo(quotaTypeInfo);
		}

		this.clickOK();
		ajax.waitLoading();
	}

}
