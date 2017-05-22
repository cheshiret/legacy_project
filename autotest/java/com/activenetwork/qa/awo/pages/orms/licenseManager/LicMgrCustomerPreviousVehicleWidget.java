package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;

/**
 * 
 * @author ssong
 * @date Dec 5, 2011
 */
public class LicMgrCustomerPreviousVehicleWidget extends DialogWidget {
	
	private static LicMgrCustomerPreviousVehicleWidget instance=null;
	
	private LicMgrCustomerPreviousVehicleWidget(){}
	
	public static LicMgrCustomerPreviousVehicleWidget getInstance(){
		if(instance==null){
			instance=new LicMgrCustomerPreviousVehicleWidget();
		}
		return instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id","CustomerPreviousVehicleList");
	}
	
	public void clickOk(){
		browser.clickGuiObject(".class","Html.A",".text","OK");
	}
	
	public List<BoatInfo> getPrivVehicles(){
		IHtmlObject[] objs = browser.getTableTestObject(".id","CustomerPreviousVehicleList");
		IHtmlTable grid = (IHtmlTable)objs[0];
		
		BoatInfo privVehicle;
		List<BoatInfo> vehicles = new ArrayList<BoatInfo>();
		
		if(grid.rowCount()>1){
			for(int i=1;i<grid.rowCount();i++){
				privVehicle = new BoatInfo();
				privVehicle.id = grid.getCellValue(i, 0);
				privVehicle.status = grid.getCellValue(i, 1);
				privVehicle.type = grid.getCellValue(i, 2);
				privVehicle.hullIdSerialNum = grid.getCellValue(i, 3);
				privVehicle.manufacturerName = grid.getCellValue(i, 4);
				privVehicle.modelYear = grid.getCellValue(i, 5);
				privVehicle.ownerFromDate = grid.getCellValue(i, 6);
				privVehicle.ownerToDate = grid.getCellValue(i, 7);
				
				vehicles.add(privVehicle);
			}
		}
		Browser.unregister(objs);
		return vehicles;
	}
}
