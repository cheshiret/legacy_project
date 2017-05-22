package com.activenetwork.qa.awo.pages.orms.inventoryManager.dockslip;

import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Sep 10, 2012
 */
public class InvMgrSlipNSSGroupListPage extends InvMgrSlipListCommonPage {
	private static InvMgrSlipNSSGroupListPage _instance = null;
	
	private InvMgrSlipNSSGroupListPage() {}
	
	public static InvMgrSlipNSSGroupListPage getInstance() {
		if(_instance == null) {
			_instance = new InvMgrSlipNSSGroupListPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", "Add New Group");
	}
	
	public void clickAddNewGroup() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New Group", true);
	}
	
	public boolean compareNSSGroupListInfo(SlipInfo expected) {
		searchSlip(SLIP_CODE_COL, expected.getCode());
		SlipInfo actual = getSlipInfoByCode(expected.getCode());
		
		boolean result = true;
		
		
		result &= commonCompareListInfo(expected, actual);
		result &= MiscFunctions.compareResult(NUM_OF_SLIPS_COL, expected.getNumOfSlips(), actual.getNumOfSlips());
		
		return result;
	}
	
	public boolean isSlipNSSGroupActive(String id) {
		return super.isSlipActive(id);
	}
	
	public void verifySlipNssGroupActive(String id){
		if(!this.isSlipNSSGroupActive(id)){
			throw new ErrorOnPageException("The slip should be active");
		}else{
			logger.info("Active status was correct");
		}
	}
}
