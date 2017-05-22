package com.activenetwork.qa.awo.pages.orms.licenseManager.privallocations;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.OutfitterAllocation;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Add Outfitter Allocations Widget. Admin -> Privilege Allocations -> Outfitter Allocations -> Add Outfitter Allocations
 * 
 * @author Lesley Wang
 * @Date  Sep 26, 2013
 */
public class LicMgrAddOutfitterAllocationsWidget extends DialogWidget {
	private static LicMgrAddOutfitterAllocationsWidget _instance = null;
	
	protected LicMgrAddOutfitterAllocationsWidget(){	
		super("Add Outfitter Allocations");
	}
	
	public static LicMgrAddOutfitterAllocationsWidget getInstance(){
		if(null ==_instance ){
			_instance = new LicMgrAddOutfitterAllocationsWidget();
		}
		return _instance;
	}
	
	/** Page Object Property Definition Begin */
	protected Property[] widgetDIV() {
		return Property.concatPropertyArray(div(), ".id", "Dialog001");
	}
	
	protected Property[] allocationTypeList() {
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("HFAllocationSearchCriteria-\\d+\\.allocationType", false));
	}
	
	protected Property[] licenseYearList() {
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("HFAllocationSearchCriteria-\\d+\\.licenseYear", false));
	}
	
	protected Property[] outfitterList() {
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("HFOutfitterAllocationSumamry-\\d+\\.outfitterId", false));
	}
	
	protected Property[] quantityTextField() {
		return Property.concatPropertyArray(input("text"), ".id", new RegularExpression("HFOutfitterAllocationSumamry-\\d+\\.total", false));
	}
	
	protected Property[] huntLocationList() {
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("HFAllocationSearchCriteria-\\d+\\.huntLocationId", false));
	}
	/** Page Object Property Definition END */
	private IHtmlObject[] getWidgetDIVs() {
		IHtmlObject[] objs = browser.getHtmlObject(widgetDIV());
		if (objs.length < 1) {
			throw new ObjectNotFoundException("Can't find widget div objects.");
		}
		return objs;
	}
	
	public void selectAllocationType(String type) {
		IHtmlObject[] objs = this.getWidgetDIVs();
		browser.selectDropdownList(allocationTypeList(), type, true, objs[0]);
		Browser.unregister(objs);
	}
	
	public void selectLicenseYear(String year) {
		IHtmlObject[] objs = this.getWidgetDIVs();
		browser.selectDropdownList(licenseYearList(), year, true, objs[0]);
		Browser.unregister(objs);
	}
	
	public void selectOutfitter(String outfitter, int index) {
		IHtmlObject[] objs = this.getWidgetDIVs();
		browser.selectDropdownList(outfitterList(), outfitter, true, index, objs[0]);
		Browser.unregister(objs);
	}
	
	public void setQuantity(String qty, int index) {
		IHtmlObject[] objs = this.getWidgetDIVs();
		browser.setTextField(this.quantityTextField(), qty, true, index, objs[0]);
		Browser.unregister(objs);
	}
	
	public boolean isHuntLocationListExist() {
		return browser.checkHtmlObjectDisplayed(huntLocationList());
	}
	
	public void selectHuntLocation(String location) {
		//Jane[20140514]:There is another location dropdown list on main page for search
		IHtmlObject[] objs = browser.getHtmlObject(huntLocationList());
		ISelect dd = (ISelect)objs[objs.length-1];
		dd.select(location);
		Browser.unregister(objs);
	}
	
	public void setOutfitterAllocations(String[] outfitters, String[] qtys) {
		if (outfitters.length != qtys.length) {
			throw new ErrorOnDataException("the number of outfitters and quantitys should be equal! outfitters=" + outfitters.length + "; quantitys=" + qtys.length);
		}
		for (int i = 0; i < outfitters.length; i++) {
			this.selectOutfitter(outfitters[i], i);
			this.setQuantity(qtys[i], i);
		}
	}
	
	public void setOutfitterAllocations(Data<OutfitterAllocation> alloc) {
		//Jane[2014-5-14]:updated for AB contract
		String code = OutfitterAllocation.AllocationCode.getStrValue(alloc);
		String type = OutfitterAllocation.AllocationType.getStrValue(alloc);
		if(StringUtil.notEmpty(code))
			this.selectAllocationType(type+" ("+code+")");
		else
			this.selectAllocationType(type);
		ajax.waitLoading();
		this.selectLicenseYear(OutfitterAllocation.LicenseYear.getStrValue(alloc));
		ajax.waitLoading();
		this.waitLoading();
		if(isHuntLocationListExist()) {
			selectHuntLocation(OutfitterAllocation.Location.getStrValue(alloc));
			ajax.waitLoading();
		}
		this.setOutfitterAllocations((String[])alloc.get(OutfitterAllocation.Outfitters), 
				(String[])alloc.get(OutfitterAllocation.Quantities));
	}
}
