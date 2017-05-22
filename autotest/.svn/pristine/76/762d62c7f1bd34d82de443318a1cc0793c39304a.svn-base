package com.activenetwork.qa.awo.pages.orms.operationManager.admin;

import com.activenetwork.qa.awo.pages.orms.operationManager.OperationsManagerPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;

/**
 * 
 * @author Ssong
 *
 */
public class OpMgrRunLotteryNotificationPage extends OperationsManagerPage{

	private OpMgrRunLotteryNotificationPage(){
	}
	
	private static OpMgrRunLotteryNotificationPage _instance = null;
	
	public static OpMgrRunLotteryNotificationPage getInstance(){
		if(null==_instance){
			_instance = new OpMgrRunLotteryNotificationPage();
		}
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.A",".text","Run Process");
	}
	
	/**
	 * Select Lottery Name from drop down list
	 * @param lotteryName
	 */
	public void selectLotteryName(String lotteryName){
		browser.selectDropdownList(".id", "lottery", lotteryName);
	}
	
	/**
	 * Select the first lottery name from drop down list 
	 */
	public void selectFirstLotteryName(){
		browser.selectDropdownList(".id", "lottery", 1);
	}
	
	/**
	 * Select given status you want to send notification to
	 * @param status
	 */
	public void selectSendNotificationToStatus(String[] status){
		for(int i=0;i<status.length;i++){
			if(status[i]!=null&&status[i].length()>0){
				if(status[i].equalsIgnoreCase("Reserved")){
					browser.selectCheckBox(".id","reserved");
				}else if(status[i].equalsIgnoreCase("Awarded")){
					browser.selectCheckBox(".id","awarded");
				}else if(status[i].equalsIgnoreCase("Unsuccessful")){
					browser.selectCheckBox(".id","denied");
				}else{
					throw new ItemNotFoundException("Unknown Status "+status[i]);
				}
			}
		}
	}
	
	/**
	 * This method used to deselect all status check box
	 */
	public void unselectAllSendNotificationToStatus(){
		browser.unSelectCheckBox(".id","reserved");
		browser.unSelectCheckBox(".id","awarded");
		browser.unSelectCheckBox(".id","denied");
	}
	
	/**
	 * Click Run process button
	 */
	public void clickRunProcess(){
		browser.clickGuiObject(".class","Html.A",".text","Run Process");
	}	
	
	public void clickCancel(){
		browser.clickGuiObject(".class","Html.A",".text","Cancel");
	}
	
	/**
	 * 
	 * @return the first lottery name retrieved from drop down list
	 */
	public String getLotteryNameFromDropdownList(){
		return browser.getDropdownElements(".id", "lottery").get(1);
	}
}
