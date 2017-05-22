/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;

/**
 * @ScriptName LicMgrCreateNewVehiclePage.java
 * @Date:Mar 21, 2011
 * @Description:
 * @author asun
 */
public class LicMgrCreateNewVehiclePage extends LicMgrVehicleProductCommonPage {

	private static LicMgrCreateNewVehiclePage instance=null;
	
	private LicMgrCreateNewVehiclePage(){}
	
	public static LicMgrCreateNewVehiclePage getInstance(){
		if(instance==null){
			instance=new LicMgrCreateNewVehiclePage();
		}
		return instance;
	}
	
	@Override
	public boolean exists() {
		return (browser.checkHtmlObjectExists(".class","Html.TABLE",".id", "productDetails")
				&&(!browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Add Product Pricing")));
	}
	
	public void setVehicleInfo(VehicleRTI vehicle){
		this.setVehicleCode(vehicle.getPrdCode());
		this.selectVehicleGroup(vehicle.getPrdGroup());
		ajax.waitLoading();
		if(vehicle.getVehicleType().trim().length()<1){
			this.selectVehicleType(0);
		}else{
			this.selectVehicleType(vehicle.getVehicleType());
		}
		if(vehicle.getCustClass() != null){
			this.setCustClass(vehicle.getCustClass());
		}
		ajax.waitLoading();
		super.setVehicleInfo(vehicle);
	}
	
	/**
	 * Get the error message displayed at the page top
	 * @return
	 */
	public String getErrorMsg() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".id", "NOTSET");
		String errorMsg = "";
		if(objs.length > 0) {
			errorMsg = objs[0].getProperty(".text").trim();
		}
		
		return errorMsg;
	}
	
}
