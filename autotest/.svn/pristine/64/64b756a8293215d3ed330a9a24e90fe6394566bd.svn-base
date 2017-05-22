/*
 * Created on Mar 12, 2010
 */
package com.activenetwork.qa.awo.pages.orms.inventoryManager;

import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author QA
 */
public class InvMgrAddFacilitySelectLocationPage  extends InvMgrCommonTopMenuPage{  	
  	private static InvMgrAddFacilitySelectLocationPage _instance = null;

	public static InvMgrAddFacilitySelectLocationPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrAddFacilitySelectLocationPage();
		}

		return _instance;
	}

	protected InvMgrAddFacilitySelectLocationPage() {
	}

	public boolean exists() {
	  	Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "OK");
		p[2] = new Property(".href", new RegularExpression("(\"addFacility\")|(#link)", false));
	  	return browser.checkHtmlObjectExists(p);
	}
	
	/**
	 * Select facility agency
	 * @param agency
	 */
	public void selectAgency(String agency){
	  	browser.selectDropdownList(".id","level_20",agency);
	}
	
	/**
	 * Select facility region
	 * @param region
	 */
	public void selectRegion(String region){
	  	browser.selectDropdownList(".id","level_30",region);
	}
	
	/**
	 * Select facility district
	 * @param district
	 */
	public void selectDistrict(String district){
	  	browser.selectDropdownList(".id","level_32",district);
	}
	
	/**
	 * Select faility project
	 * @param project
	 */
	public void selectProject(String project){
	  	browser.selectDropdownList(".id","level_35",project);
	}

	/** Click on OK button*/
	public void clickOK(){
	  	browser.clickGuiObject(".class","Html.A",".text","OK");
	}

	/** Click on Cancel button*/
	public void clickCancel(){
	  	browser.clickGuiObject(".class","Html.A",".text","Cancel");
	}
	
	/**
	 * Fill in all facility location info
	 * @param fd - facility data
	 */
	public void setupLocationData(FacilityData fd){
	  	this.selectAgency(fd.agency);
	  	this.waitLoading();
	  	if(null != fd.region && fd.region.length()>0){
	  	  	this.selectRegion(fd.region);
	  	  	this.waitLoading();
	  	}
	  	if(null != fd.district && fd.district.length()>0){
	  	  	this.selectDistrict(fd.district);
	  	  	this.waitLoading();
	  	}
	  	if(null != fd.project && fd.project.length()>0){
	  	  	this.selectProject(fd.project);
	  	  	this.waitLoading();
	  	}
	}
}
