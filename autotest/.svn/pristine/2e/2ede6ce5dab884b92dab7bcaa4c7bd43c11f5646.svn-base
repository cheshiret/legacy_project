package com.activenetwork.qa.awo.testcases.abstractcases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public abstract class LicMgrViewProductPricingListTestCase extends LicenseManagerTestCase{
	
	protected ILicMgrProductPricingPage pricingPage = null;
	protected boolean pass = true;
	protected List<PricingInfo> pricingInfos = new ArrayList<PricingInfo>();
	private List<String> pricingIDListFromUIBySorted = new ArrayList<String>();
	
	public void execute(){
		lm.loginLicenseManager(login);
		lm.gotoProductSearchListPageFromTopMenu(pricing.prodType);
		
		pricingPage = lm.gotoProductPricingPageFromListPage(pricing.prodType,pricing.prodCode);
		
		//clean up environment
		lm.deactivateAllProductPricings(pricingPage);
		
		//prepare pricing
		for(int i=pricingInfos.size()-1; i>=0; i--){
			logger.info("\n>>>> Starting with a new pricingInfo (" + i + ")...\n");
			
			pricingInfos.get(i).id = lm.addPricingForProduct(pricingInfos.get(i), pricingPage, true);
		} 
		
		//verify pricing list info
		//1. verify pricing record is exists and compare record info
		//2. verify sorted
		//3. verify search result
		pass &= this.verifyPricingListInfo(pricingInfos);
		//verify UI requirements
		pass &= this.verifyUIRequirements();
		
		//clear up environment
		lm.deactivateAllProductPricings(pricingPage);
		
		if(!pass){
			throw new ErrorOnPageException("Some check points is not correct, please check.");
		}
		lm.logOutLicenseManager();				
	}
	
	protected boolean verifyPricingListInfo(List<PricingInfo> pricingInfo){
		boolean result = true;
		pricingIDListFromUIBySorted = pricingPage.getPricingIDs();
		
		//verify pricing records is exists
		if(pricingInfo.size() != pricingIDListFromUIBySorted.size()){
			throw new ErrorOnPageException("Prepare Data is Failed. Please check Data. Expected size: " + pricingInfo.size() + ", but actual is: " + pricingIDListFromUIBySorted.size());
		}else{
			for(int i=0; i<pricingInfo.size(); i++){			
				if(!pricingPage.checkPricingRecordExists(pricingInfo.get(i))){
					result &= false;
					logger.error("Pricing info should existes in pricing list page.");
				}
				
				//compare pricing record info in list
				pricingPage.comparePricingRecordInfo(pricingInfo.get(i));
			}
		}
		
		//verify sorted
		for(int i=0; i<pricingInfo.size(); i++){
			if(!pricingInfo.get(i).id.equals(pricingIDListFromUIBySorted.get(i))){
				result &= false;
				logger.error("Displaied pricing ID should be " + pricingInfo.get(i).id 
						+ ", but actually is " + pricingIDListFromUIBySorted.get(i));
			}
		}
		
		//verify search result
		pricingPage.setPricingSearchInfo(pricing);
		pricingPage.clickGo();
		ajax.waitLoading();
		pricingPage.waitLoading();
		result &= pricingPage.verifySearchResult(pricing);
		
		pricingPage.checkShowCurrentRecordsOnly();
		pricingPage.waitLoading();
		
		return result;
	}
	
	protected boolean verifyUIRequirements(){
		boolean result = true;
		//verify show current record is selected by default
		if(pricingPage.isShowCurrentRecordsExist() && !pricingPage.isShowCurrentRecordsOnlyChecked()){
			result &= false;
			logger.error("Show current records should be selected by default.");
		}
		
		pricingPage.uncheckShowCurrentRecordsOnly();
		ajax.waitLoading();
		//verify status drop down list
		List<String> statusElements = pricingPage.getStatusElements();
		if(statusElements.size() != 3){
			result &= false;
			logger.error("Status drop down list should just have three options.");
		}else{
			if(statusElements.get(0).trim().length()>0){
				result &= false;
				logger.error("Status first option should be blank.");
			}
			if(!statusElements.contains("Active")){
				result &= false;
				logger.error("Status should have Active option.");
			}
			if(!statusElements.contains("Inactive")){
				result &= false;
				logger.error("Status should have Inactive option.");
			}
		}
		
		//verify location class drop down list
		List<String> needSortedLocClassElements = pricingPage.getLocationClassElements();
		if(needSortedLocClassElements.get(0).trim().length()>0){
			result &= false;
			logger.error("Location class first option should be blank, but acutally is " + needSortedLocClassElements.get(0));
		}
		
		if(!needSortedLocClassElements.get(1).equals("All")){
			result &= false;
			logger.error("Location class first non-null option should be All, but acutally is " + needSortedLocClassElements.get(1));
		}
		List<String> subSortedLocClassElements = needSortedLocClassElements.subList(2, needSortedLocClassElements.size());
		Collections.sort(subSortedLocClassElements);
		List<String> locClassElements = pricingPage.getLocationClassElements();
		if(!locClassElements.subList(2, locClassElements.size()).equals(subSortedLocClassElements)){
			result &= false;
			logger.error("Location class sorted is not correct.");
		}
				
		return result;
	}
}
