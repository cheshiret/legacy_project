package com.activenetwork.qa.awo.pages.orms.licenseManager.vendor;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Agents;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 *
 * @author QA
 * @Date  Dec 6, 2011
 */
public class LicMgrVendorAgentSubPage extends LicMgrVendorDetailsPage {
	private static LicMgrVendorAgentSubPage instance=null;

	private LicMgrVendorAgentSubPage() {};

	public static LicMgrVendorAgentSubPage getInstance() {
		if(null == instance) {
			instance = new LicMgrVendorAgentSubPage();
		}
		return instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.A",".text",new RegularExpression("Add Agent.*", false));
	}

	public List<Agents> getAgentInfo(){
		List<Agents> list = new ArrayList<Agents>();
		IHtmlObject objs[] = browser.getTableTestObject(".id", "vendorStoreSearch");
		if(objs.length < 1) {
			throw new ObjectNotFoundException("There is not a table which id = vendorStoreSearch");
		}
		IHtmlTable table = (IHtmlTable)objs[0];

		String id, status, name, address, suppAdd, city, country, state, zip, verifoneDial, verifoneIP, webPOS, touchPOS;
		for(int i = 1; i < table.rowCount(); i ++) {
			id = table.getCellValue(i, 0);
			status = table.getCellValue(i, 1);
			name = table.getCellValue(i, 2);
			address =  table.getCellValue(i, 3);
			suppAdd =  table.getCellValue(i, 4);
			city =  table.getCellValue(i, 5);
			country =  table.getCellValue(i, 6);
			state =  table.getCellValue(i, 7);
			zip =  table.getCellValue(i, 8);
			verifoneDial = table.getCellValue(i, 9);
			verifoneIP = table.getCellValue(i,10);
			webPOS = table.getCellValue(i, 11);
			touchPOS = table.getCellValue(i, 12);

			Agents agent = new Agents();
			agent.ID=id;
			agent.status=status;
			agent.name=name;
			agent.address=address;
			agent.suppAdd=suppAdd;
			agent.city=city;
			agent.country=country;
			agent.state=state;
			agent.zip=zip;
			agent.verifoneDial=verifoneDial;
			agent.verifoneIP=verifoneIP;
			agent.webPOS=webPOS;
			agent.touchPOS=touchPOS;

			list.add(agent);
		}

		return list;
	}

	public List<String> getRowInfo(String id){
		IHtmlObject objs[] = browser.getTableTestObject(".id", "vendorStoreSearch");
		if(objs.length < 1) {
			throw new ObjectNotFoundException("There is not a table which id = vendorStoreSearch");
		}
		IHtmlTable table = (IHtmlTable)objs[0];

		int row = table.findRow(0, id);
		if(row<0){
			throw new ItemNotFoundException("Can't find store info by ID "+id);
		}
		List<String> list = table.getRowValues(row);
		return list;
	}

	public void clickStoreId(String id){
		browser.clickGuiObject(".class", "Html.A", ".text", id);

	}

	public boolean compareStoreListInfo(StoreInfo expectedStore){
		return this.compareStoreListInfoStatusNotSure(expectedStore, true);
	}

	public boolean compareStoreListInfoStatusNotSure(StoreInfo expectedStore, boolean checkStatus){
		boolean pass = true;
		String temp = "";
		List<String> rowInfo = this.getRowInfo(expectedStore.storeID);
		if(null == rowInfo ||rowInfo.size()<=0){
			pass &= false;
			logger.error("There is no Expected vender store was added");
		}
		temp = rowInfo.get(0);
		if(!temp.equals(expectedStore.storeID)){
			pass &= false;
			logger.error("Vendor store id "+temp+" error since expected store id is:" + expectedStore.storeID);
		}
		if(checkStatus){
			temp = rowInfo.get(1);
			if(!temp.equals(expectedStore.status)){
				pass &= false;
				logger.error("Vendor store status "+temp+" error since expected store status is:" + expectedStore.status);
			}
		}
		temp = rowInfo.get(2);
		if(!temp.equals(expectedStore.storeName)){
			pass &= false;
			logger.error("Vendor store name "+temp+" error since expected store name is: " + expectedStore.storeName);
		}
		temp = rowInfo.get(3);
		if(!temp.equals(expectedStore.physicalAddress.address)){
			pass &= false;
			logger.error("Vendor store address <"+temp+"> error since expected physical address is <" + expectedStore.physicalAddress.address + ">");
		}
		temp = rowInfo.get(4);
		if(!temp.equals(expectedStore.physicalAddress.supplementalAddr)){
			pass &= false;
			logger.error("Vendor store supplement address "+temp+" error since expected supplement address is " + expectedStore.physicalAddress.supplementalAddr);
		}
		temp = rowInfo.get(5);
		if(!temp.equals(expectedStore.physicalAddress.city)){
			pass &= false;
			logger.error("Vendor store city "+temp+" error since expected store city is " + expectedStore.physicalAddress.city);
		}
		temp = rowInfo.get(6);
		if(!temp.equals(expectedStore.physicalAddress.county)){
			pass &= false;
			logger.error("Vendor store county "+temp+" error since expected store county is " + expectedStore.physicalAddress.county);
		}
		temp = rowInfo.get(7);
		if(!temp.equals(expectedStore.physicalAddress.state)){
			pass &= false;
			logger.error("Vendor store state "+temp+" error since expected store state is " + expectedStore.physicalAddress.state);
		}
		temp = rowInfo.get(8);
		if(!temp.equals(expectedStore.physicalAddress.zip)){
			pass &= false;
			logger.error("Vendor store zip "+temp+" error since expected store address zip code is " + expectedStore.physicalAddress.zip);
		}
	   return pass;
	}
	
	
	public void clickAddAgents(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Add Agent.*",false));
	}
}
