package com.activenetwork.qa.awo.supportscripts.support.inventorymgr;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Address;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PocInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.WarehouseInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.warehouse.InvMgrWarehouseDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.warehouse.InvMgrWarehouseSearchPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.warehouse.InvMgrWarehouseSelectLocationPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;


/**
 * 
 * @Description: add a new ware house.
 * @Preconditions:The add ware house rule feature has been assigned.
 * @SPEC:
 * @Task#:Auto-972
 * 
 * @author jwang8
 * @Date  Mar 27, 2012
 */
public class AddWarehouse extends SupportCase{

	private LoginInfo login=new LoginInfo();
	boolean loggedin=false;
	private String contract = "";
	private InventoryManager invMgr=InventoryManager.getInstance();
	private WarehouseInfo whouseInfo = new WarehouseInfo();
	private InvMgrWarehouseDetailsPage whouseDetailPg = InvMgrWarehouseDetailsPage.getInstance();
	private InvMgrWarehouseSelectLocationPage whouseSelectPg = InvMgrWarehouseSelectLocationPage.getInstance();
	
	public void execute() {
		if (!contract.equalsIgnoreCase(login.contract) && loggedin) {
			invMgr.logoutInvManager();
			loggedin = false;
		}
		if((!loggedin )|| (loggedin && (!whouseDetailPg.exists() || !whouseSelectPg.exists()))){
			invMgr.loginInventoryManager(login);
			loggedin = true;
			invMgr.gotoWarehousesSearchPg();
		}
		
		invMgr.addWareHouse(whouseInfo);
		this.verifyResult();
		
		 contract = login.contract;
	}

	public void getNextData() {
		
		login.contract=dpIter.dpString("contract");
		login.location=dpIter.dpString("location");
		
		whouseInfo.setAgency(dpIter.dpString("agency"));
		whouseInfo.setRegion(dpIter.dpString("region"));
		whouseInfo.setDistrict(dpIter.dpString("district"));
		whouseInfo.setName(dpIter.dpString("warehouseName"));
		whouseInfo.setDesctiption(dpIter.dpString("description"));
		Address address = new Address();
		address.address = dpIter.dpString("mailingAddress");
		address.city =  dpIter.dpString("mailingCity");
	    address.zip =dpIter.dpString("mailingZip");
	    address.state = dpIter.dpString("mailingState");
	    address.country = dpIter.dpString("mailingCountry");
	    
	    PocInfo poc = new PocInfo();
	    poc.setLastName(dpIter.dpString("primLastName"));
	    poc.setFirstName(dpIter.dpString("primFirstName"));
	    poc.setPhone( dpIter.dpString("primPhone"));
		poc.setFax(dpIter.dpString("primFax"));
		poc.setEmail(dpIter.dpString("primEmail"));
		address.address = dpIter.dpString("primAddress");
		address.city =  dpIter.dpString("primCity");
	    address.zip = dpIter.dpString("primZip");
	    address.state = dpIter.dpString("primState");
	    address.country = dpIter.dpString("primCountry");
	    
	    whouseInfo.setPrimaryPoc(poc);
	    
	    poc.setLastName(dpIter.dpString("secPosLastName"));
	    poc.setFirstName(dpIter.dpString("secPosFirstName"));
	    poc.setPhone( dpIter.dpString("secPosPhone"));
		poc.setFax(dpIter.dpString("secPosFax"));
		poc.setEmail(dpIter.dpString("secPosEmail"));
		address.address = dpIter.dpString("secPosAddress");
		address.city = dpIter.dpString("secPosCity");
	    address.zip = dpIter.dpString("secPosZip");
	    address.state = dpIter.dpString("secPosState");
	    address.country = dpIter.dpString("secPosCountry");
	    
	    whouseInfo.setSecPoc(poc);
		
		
		logMsg[0] = whouseInfo.getName();
		logMsg[1] = whouseInfo.getAgency();
	}

	public void wrapParameters(Object[] param) {
		cursor = 0;
		startpoint = 0; // the start point in the data pool
		endpoint = 999; // the end point in the data pool
		
		dataSource = casePath + "/" + caseName;
		
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.adm.user");
		login.password = TestProperty.getProperty("orms.adm.pw");
		logMsg=new String[3];
		logMsg[0] = "warehouseName";
		logMsg[1] = "warehouseDescription";
		logMsg[2] = "result";
	}
	
	public void verifyResult(){
		InvMgrWarehouseSearchPage whouseSearchPg = InvMgrWarehouseSearchPage.getInstance();
		if(!whouseSearchPg.exists()){
			logger.error("add warehouse failed:warehouse name ="+whouseInfo.getName()+",warehouse description="+whouseInfo.getDesctiption()+whouseDetailPg.getErrorMsg());
			logMsg[2] = "Failed";
		}else{
			logMsg[2] = "Pass";
		}
	}

}
