package com.activenetwork.qa.awo.pages.orms.inventoryManager.warehouse;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.WarehouseInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**  
 * @Description:  ware house select location page
 * @Preconditions:  
 * @SPEC:  
 * @Task#: Auto-972
 * @author jwang8  
 * @Date  Mar 26, 2012    
 */
public class InvMgrWarehouseSelectLocationPage extends InventoryManagerPage{

	public static InvMgrWarehouseSelectLocationPage instance = null;
	
	private InvMgrWarehouseSelectLocationPage(){};
	
	public static InvMgrWarehouseSelectLocationPage getInstance(){
		if(null == instance){
			instance = new InvMgrWarehouseSelectLocationPage();
		}
		return instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("WarehouseAddSelectParentUI-\\d+\\.agency",false));
	}

	/**
	 * select the agency
	 * @param agency- the agency name.
	 */
	public void selectAgency(String agency){
		browser.selectDropdownList(".id", new RegularExpression("WarehouseAddSelectParentUI-\\d+\\.agency",false), agency);
	}
	
	/**
	 * select the region.
	 * @param region- the region name.
	 */
	public void selectRegion(String region){
		browser.selectDropdownList(".id", new RegularExpression("WarehouseAddSelectParentUI-\\d+\\.region",false), region);
	}
	
	/**
	 * select the district.
	 * @param region- the region name.
	 */
	public void selectDistrict(String district){
		browser.selectDropdownList(".id",  new RegularExpression("WarehouseAddSelectParentUI-\\d+\\.district",false), district);
	}
	
	/**
	 * Check the region whether select or not.
	 * @return boolean- whether select or not.
	 */
	public boolean checkRegionSelection(){
		boolean isSelect = true;
		List<String> list = browser.getDropdownElements(".id", new RegularExpression("WarehouseAddSelectParentUI-\\d+\\.region",false));
		if(list.size()==0){
			isSelect = false;
		}
		return isSelect;
	}
	
	/**
	 * Check the district whether select or not.
	 * @return boolean- whether select or not.
	 */
	public boolean checkDistrctSelection(){
		boolean isSelect = true;
		List<String> list = browser.getDropdownElements(".id", new RegularExpression("WarehouseAddSelectParentUI-\\d+\\.district",false));
		if(list.size()==0){
			isSelect = false;
		}
		return isSelect;
	}
	
	/**
	 * Check OK button.
	 */
	public void clickOkButton(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	/**
	 * Select the ware house location info.
	 * @param WarehouseInfo- the ware house info.
	 */
	public void selectLocationInfo(WarehouseInfo whouseInfo){
		this.selectAgency(whouseInfo.getAgency());
		ajax.waitLoading();
		//if(this.checkRegionSelection()){
			this.selectRegion(whouseInfo.getRegion());
			ajax.waitLoading();
		//}
		//if(this.checkDistrctSelection()){
			this.selectDistrict(whouseInfo.getDistrict());
		//}
	}
}
