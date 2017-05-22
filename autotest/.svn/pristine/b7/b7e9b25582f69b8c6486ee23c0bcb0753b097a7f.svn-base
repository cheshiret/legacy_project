/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.testapi.interfaces.page.Loadable;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * @Defects:
 * 
 * @author asun
 * @Date  Jul 21, 2011
 */
public interface ILicMgrProductPricingPage extends Loadable {
	
	public static RegularExpression showCurrentOnlyRegex = new RegularExpression("HFProductPricingSearchCriteria-\\d+\\.showCurrentRecordsOnly",false);

	public static RegularExpression statusRegex = new RegularExpression("HFProductPricingSearchCriteria-\\d+\\.statusID",false);
	
	public static RegularExpression typeRegex = new RegularExpression("HFProductPricingSearchCriteria-\\d+\\.purchaseTypeID",false);
	
	public static RegularExpression locClassRegex = new RegularExpression("HFProductPricingSearchCriteria-\\d+\\.locationClassID",false);
	
	public abstract void clickAddProductPricing();

	public abstract boolean isShowCurrentRecordsOnlyChecked();
	
	public abstract boolean isShowCurrentRecordsExist();
	
	public abstract void checkShowCurrentRecordsOnly();

	public abstract void uncheckShowCurrentRecordsOnly();

	public abstract void selectStatus(String status);
	
	public abstract void selectLocationClass(String locationClass);
	
	public abstract void clickGo();
	
	public abstract int getRowCount();

	public abstract void clickPricingID(String id);
	
	public abstract boolean checkPricingRecordExists(String id);
	
	public boolean checkPricingRecordExists(PricingInfo pricing);
	
	public abstract String getPricingID(PricingInfo pricing);
	
	public abstract String getPricingID(String status,String locClass,String licYearFrom,String purchaseType);
	
	public abstract void searchPricingRecords();
	
	public abstract void searchPricingRecords(String status);
	
	public abstract void searchPricingRecords(String status, String locationClass);
	
	public abstract List<String> getPricingIDs();
	
	public abstract void setPricingSearchInfo(PricingInfo pricing);
	
	public abstract boolean verifySearchResult(PricingInfo pricing);
	
	public abstract List<String> getStatusElements();
	
	public abstract List<String> getLocationClassElements();
	
	public abstract boolean comparePricingRecordInfo(PricingInfo pricing);
	
	public abstract void clickPricingTab();
}
