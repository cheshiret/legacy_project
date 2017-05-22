package com.activenetwork.qa.awo.pages.orms.licenseManager.privallocations;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.AllocationType;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Add Allocation Type widget: Admin -> Privilege Allocations -> Allocation Type -> Add Allocation Types
 * 
 * @author Lesley Wang
 * @Date  Sep 26, 2013
 */
public class LicMgrAddAllocationTypeWidget extends DialogWidget {
	private static LicMgrAddAllocationTypeWidget _instance = null;
	
	protected LicMgrAddAllocationTypeWidget(){	
		super("Add Allocation Type");
	}
	
	public static LicMgrAddAllocationTypeWidget getInstance(){
		if(null ==_instance ){
			_instance = new LicMgrAddAllocationTypeWidget();
		}
		return _instance;
	}
	
	protected Property[] allocationTypeNameFiled() {
		return Property.concatPropertyArray(input("text"), ".id", new RegularExpression("HFAllocationTypeView-\\d+\\.code:ZERO_TO_NEW", false));
	}
	
	protected Property[] allocationTypeCodeFiled() {
		return Property.concatPropertyArray(input("text"), ".id", new RegularExpression("HFAllocationTypeView-\\d+\\.allocationCode:ZERO_TO_NEW", false));
	}
	
	protected Property[] allocationTypeSpeciesList() {
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("HFAllocationTypeView-\\d+\\.speciesView", false));
	}
	
	public void setAllocationTypeName(String text) {
		browser.setTextField(this.allocationTypeNameFiled(), text);
	}
	
	public void setAllocationTypeCode(String code) {
		browser.setTextField(this.allocationTypeCodeFiled(), code);
	}
	
	public void selectAllocationTypeSpecies(int index) {
		browser.selectDropdownList(this.allocationTypeSpeciesList(), index);
	}
	
	public boolean isAllocationTypeSpeciesExist() {
		return browser.checkHtmlObjectExists(this.allocationTypeSpeciesList());
	}
	
	public void selectAllocationTypeSpecies(String species) {
		browser.selectDropdownList(this.allocationTypeSpeciesList(), species);
	}
	
	public void setAllocationTypeInfo(Data<AllocationType> type) {
		logger.info("Set allocation type info...");
		this.setAllocationTypeName(AllocationType.AllocationType.getStrValue(type));
		this.setAllocationTypeCode(AllocationType.Code.getStrValue(type));
		String species = AllocationType.Species.getStrValue(type);
		if(isAllocationTypeSpeciesExist()){
			if (StringUtil.isEmpty(species)) {
				this.selectAllocationTypeSpecies(0);
			} else {
				this.selectAllocationTypeSpecies(species);
			}
		}else logger.info("No Allocation Type species drop down list.");
		ajax.waitLoading();
	}
}
