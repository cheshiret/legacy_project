package com.activenetwork.qa.awo.supportscripts.function.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.WarehouseInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.warehouse.InvMgrWarehouseSearchPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;

public class AddWarehouseFunction extends FunctionCase{
	
	private LoginInfo login=new LoginInfo();
	private InventoryManager invMgr=InventoryManager.getInstance();
	private WarehouseInfo whouseInfo = new WarehouseInfo();
	private boolean loggedin=false;
	private String contract = "";
	private InvMgrHomePage invHmPg=InvMgrHomePage.getInstance();

	@Override
	public void execute() {
		if (!login.contract.equals(contract) && loggedin && isBrowserOpened) {
			invMgr.switchToContract(login.contract, login.location);
		} 
		if (!loggedin || !isBrowserOpened) { // Logged in
			invMgr.loginInventoryManager(login);
			loggedin = true;
		}
		if(!invHmPg.exists()){
			invMgr.gotoInventoryHomePg();
		}
		contract = login.contract;
		invMgr.gotoWarehousesSearchPg();
		String result = invMgr.addWareHouse(whouseInfo);
		if(result.matches("\\d+"))
		{
			newAddValue = result;
			this.verifyResult(result);
		}else{
			throw new ErrorOnPageException("add warehouse failed:warehouse name ="+whouseInfo.getName()+",warehouse description="+whouseInfo.getDesctiption()+result);
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		whouseInfo = (WarehouseInfo)param[1];
	}
	
	public void verifyResult(String id){
		InvMgrWarehouseSearchPage whouseSearchPg = InvMgrWarehouseSearchPage.getInstance();
		whouseSearchPg.searchWarehouseById(id);
		WarehouseInfo rs = whouseSearchPg.getAllRecordsOnPage().get(0);
		
		boolean result = true;
		
			result &= (StringUtil.isEmpty(whouseInfo.getName())?true:(rs.getName().equalsIgnoreCase(whouseInfo.getName())));
			result &= (StringUtil.isEmpty(whouseInfo.getAgency())?true:(rs.getAgency().equalsIgnoreCase(whouseInfo.getAgency())));
			result &= (StringUtil.isEmpty(whouseInfo.getRegion())?true:(rs.getRegion().equalsIgnoreCase(whouseInfo.getRegion())));
			result &= (StringUtil.isEmpty(whouseInfo.getDistrict())?true:(rs.getDistrict().equalsIgnoreCase(whouseInfo.getDistrict())));
			result &= (StringUtil.isEmpty(whouseInfo.getDesctiption())?true:(rs.getDesctiption().equalsIgnoreCase(whouseInfo.getDesctiption())));
			
		if(!result){
			throw new ErrorOnPageException("add and check warehouse failed:warehouse name ="+whouseInfo.getName()+",warehouse description="+whouseInfo.getDesctiption());
		}else{
			logger.info("Warehose info added success, warehose name = " + whouseInfo.getName() + ", warehose id = " + id);
		}
	}

}
