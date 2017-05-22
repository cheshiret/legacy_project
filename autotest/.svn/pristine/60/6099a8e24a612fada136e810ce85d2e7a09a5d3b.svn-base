package com.activenetwork.qa.awo.pages.orms.licenseManager.privallocations;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.AllocationTypeLicenseYear;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: 
 * @Preconditions:
 * @SPEC: 
 * @LinkSetUp:
 * @Task#: 
 * 
 * @author Lesley Wang
 * @Date  Jan 29, 2014
 */
public class LicMgrAddAllocationTypeLicYearWidget extends DialogWidget {
	private static LicMgrAddAllocationTypeLicYearWidget _instance = null;
	
	protected LicMgrAddAllocationTypeLicYearWidget(){	
		super("Add Allocation Type Licen(s|c)e Year");
	}
	
	public static LicMgrAddAllocationTypeLicYearWidget getInstance(){
		if(null ==_instance ){
			_instance = new LicMgrAddAllocationTypeLicYearWidget();
		}
		return _instance;
	}
	
	protected Property[] allocTypeList() {
		return Property.concatPropertyArray(this.select(), ".id", new RegularExpression("HFAllocationView-\\d+\\.allocationType", false));
	}
	
	protected Property[] allocTypeLicYearList() {
		return Property.concatPropertyArray(this.select(), ".id", new RegularExpression("HFAllocationView-\\d+\\.licenseYear", false));
	}
	
	protected Property[] totalQuotaField() {
		return Property.concatPropertyArray(this.input("text"), ".id", new RegularExpression("HFAllocationView-\\d+\\.totalQuota", false));
	}
	
	protected Property[] tagQtyField() {
		return Property.concatPropertyArray(this.input("text"), ".id", new RegularExpression("HFAllocationView-\\d+\\.tagQty", false));
	}
	
	protected Property[] huntLocationList() {
		return Property.concatPropertyArray(this.select(), ".id", new RegularExpression("HFAllocationView-\\d+\\.huntLocation", false));
	}
	
	public void selectAllocationType(String type) {
		browser.selectDropdownList(this.allocTypeList(), type);
	}
	
	public void selectAllocationTypeLicYear(String year) {
		browser.selectDropdownList(this.allocTypeLicYearList(), year);
	}
	
	public void setTotalQuota(String quota) {
		browser.setTextField(this.totalQuotaField(), quota);
	}
	
	public void setTagQty(String qty) {
		browser.setTextField(this.tagQtyField(), qty);
	}
	
	public boolean isHuntLocationListExist() {
		return browser.checkHtmlObjectDisplayed(huntLocationList());
	}
	
	public void selectHuntLocation(String location) {
		browser.selectDropdownList(this.huntLocationList(), location);
	}
	
	public void setAllocationTypeLicYearInfo(Data<AllocationTypeLicenseYear> licYear) {
		String type = AllocationTypeLicenseYear.AllocationType.getStrValue(licYear);
		String code = AllocationTypeLicenseYear.AllocationCode.getStrValue(licYear);
		//Jane[2014-5-14]:updated for AB contract
		if(StringUtil.notEmpty(code))
			this.selectAllocationType(type+"("+code+")");
		else
			this.selectAllocationType(type);
		ajax.waitLoading();
		this.selectAllocationTypeLicYear(AllocationTypeLicenseYear.LicenseYear.getStrValue(licYear));
		this.setTotalQuota(AllocationTypeLicenseYear.TotalQuota.getStrValue(licYear));
		this.setTagQty(AllocationTypeLicenseYear.TagQty.getStrValue(licYear));
		if(isHuntLocationListExist())
			selectHuntLocation(AllocationTypeLicenseYear.location.getStrValue(licYear));
	}
}
