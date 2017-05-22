package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo.QuotaType;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * @Description: Quota Details page in License Manager: Lotteries -> Quota -> Click quota id
 * 
 * @author Lesley Wang
 * @Date  Jan 21, 2014
 */
public class LicMgrQuotaDetailsPage extends LicMgrQuotaCommonPage {
	private static LicMgrQuotaDetailsPage _instance = null;
	
	protected LicMgrQuotaDetailsPage (){}
	
	public static LicMgrQuotaDetailsPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrQuotaDetailsPage();
		}
		return _instance;
	}
	
	protected Property[] huntQuotaDiv() {
		return Property.concatPropertyArray(this.div(), ".id", "EditHuntQuota");
	}
	
	protected Property[] changeHistoryLink() {
		return Property.concatPropertyArray(this.a(), ".text", "Change History");
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(huntQuotaDiv());
	}
	
	public boolean checkChangeHistoryLinkExist() {
		return browser.checkHtmlObjectExists(changeHistoryLink());
	}
	
	public void clickChangeHistory() {
		browser.clickGuiObject(changeHistoryLink());
	}
	
	public boolean checkQuotaLicenseYearsLabelExist(){
		return browser.checkHtmlObjectExists(quotaLicenseYearsLabel());
	}
	
	public int getNumOfQuotaTypes() {
		IHtmlObject[] objs = browser.getHtmlObject(this.quotaTypeDescriptionField());
		int num = objs.length;
		Browser.unregister(objs);
		return num;
	}
	
	public List<QuotaType> getQuotaTypes() {
		List<QuotaType> types = new ArrayList<QuotaType> ();
		int num = this.getNumOfQuotaTypes();
		for (int i = 0; i < num; i++) {
			QuotaType type = new QuotaType();
			type.setQuotaType(this.getQuotaType(i));
			type.setAgeMin(this.getQuotaTypeAgeMin(i));
			type.setAgeMax(this.getQuotaTypeAgeMax(i));
			type.setResidencyStatus(this.getQuotaTypeResidencyStatus(i));
			type.setDrawOrder(this.getQuotaTypeDrawOrder(i));
			types.add(type);
		}
		return types;
	}
	
	public QuotaInfo getQuotaInfo() {
		QuotaInfo quota = new QuotaInfo();
		quota.setQuotaId(this.getQuotaID());
		quota.setQuotaStatus(this.getQuotaStatus());
		quota.setDescription(this.getQuotaDescription());
		quota.setSpecie(this.getQuotaSpecies());
		quota.setQuotaTypes(this.getQuotaTypes());
		return quota;
	}
	
	public void verifyQuotaInfo(QuotaInfo quota) {
		QuotaInfo actQuota = this.getQuotaInfo();
		boolean result = true;
		result &= MiscFunctions.compareString("Quota ID", quota.getQuotaId(), actQuota.getQuotaId());
		result &= MiscFunctions.compareString("Quota Status", quota.getQuotaStatus(), actQuota.getQuotaStatus());
		result &= MiscFunctions.compareString("Quota Description", quota.getDescription(), actQuota.getDescription());
		result &= MiscFunctions.compareString("Quota Species", quota.getSpecie(), actQuota.getSpecie());
		result &= MiscFunctions.compareResult("Quota Types number", quota.getQuotaTypes().size(), actQuota.getQuotaTypes().size());
		for (int i = 0; i < quota.getQuotaTypes().size(); i++) {
			QuotaType type = quota.getQuotaTypes().get(i);
			QuotaType actType = actQuota.getQuotaTypes().get(i);
			result &= MiscFunctions.compareString("Quota Type", type.getQuotaType(), actType.getQuotaType());
			result &= MiscFunctions.compareString("Quota Type Age Min", type.getAgeMin(), actType.getAgeMin());
			result &= MiscFunctions.compareString("Quota Type Age Max", type.getAgeMax(), actType.getAgeMax());
			result &= MiscFunctions.compareString("Quota Type Residency Status", type.getResidencyStatus(), actType.getResidencyStatus());
			result &= MiscFunctions.compareString("Quota Type Draw Order", type.getDrawOrder(), actType.getDrawOrder());
		}
		if (!result) {
			throw new ErrorOnPageException("Quota info is wrong in quota details page!");
		}
		logger.info("Verify quota info correctly in quota details page!");
	}
	
	public void setQuotaInfo(QuotaInfo quota) {
		logger.info("Set Quota Info...");
		this.selectQuotaStatus(quota.getQuotaStatus());
		this.setQuotaDescription(quota.getDescription());	
		
		List<QuotaType> quotaList = quota.getQuotaTypes();
		for(int i=0; i<quotaList.size(); i++){
			if(i != 0&&getNumOfQuotaTypes()==1){
				clickAddQuotaType();
				ajax.waitLoading();
			}
			QuotaType type = quotaList.get(i);
			this.setQuotaType(type.getQuotaType(), i);
			this.setQuotaUse(type.getQuotaUse(), i);
			ajax.waitLoading();
			this.setQuotaTypeDrawOrder(type.getDrawOrder(), i);
			this.setAgeMin(quotaList.get(i).getAgeMin(), i);
			this.setAgeMax(quotaList.get(i).getAgeMax(), i);
			if(StringUtil.notEmpty(quotaList.get(i).getResidencyStatus())){
				this.selectResidencyStatus(quotaList.get(i).getResidencyStatus(), i);
			} else {
				this.selectResidencyStatus(0, i);
			}
		}
	}
	
	public void setQuotaUse(String quotaUse, int index){
		browser.selectDropdownList(this.quotaUseList(), quotaUse, index);
	}
}
