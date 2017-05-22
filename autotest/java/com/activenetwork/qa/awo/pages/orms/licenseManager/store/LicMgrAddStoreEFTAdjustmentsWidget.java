package com.activenetwork.qa.awo.pages.orms.licenseManager.store;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.EFTAdjustmentInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrAddStoreEFTAdjustmentsWidget  extends DialogWidget{

	private static LicMgrAddStoreEFTAdjustmentsWidget instance = null;

	private LicMgrAddStoreEFTAdjustmentsWidget(){};

	public static LicMgrAddStoreEFTAdjustmentsWidget getInstance(){
		if(null == instance){
			instance = new LicMgrAddStoreEFTAdjustmentsWidget();
		}
		return instance;
	}
	
	public void selectAdjustmentType(String type){
		browser.selectDropdownList(".id", new RegularExpression("StoreEFTAdjustmentView-\\d+\\.type",false), type);
	}
	
	public void selectAdjustmentReason(String reason){
		browser.selectDropdownList(".id", new RegularExpression("StoreEFTAdjustmentView-\\d+.reason",false), reason);
	}
	
	public void setNote(String note){
		browser.setTextArea(".id", new RegularExpression("StoreEFTAdjustmentView-\\d+\\.note",false), note);
	}
	
	public void setTotalAdjustmentAmount(String amount){
		browser.setTextField(".id", new RegularExpression("StoreEFTAdjustmentView-\\d+\\.totalAmount",false), amount);
	}
	
	public void selectSplitRevenueAccount(String split){
		browser.selectDropdownList(".id", new RegularExpression("StoreEFTAdjustmentView-\\d+\\.splitRevAccounts",false), split);
	}
	
	public void selectAccount(String account){
		browser.selectDropdownList(".id", new RegularExpression("StoreEFTAdjAccAmtView-\\d+\\.accID",false), account);
	}
	
	public void setAccountAmount(String amount){
		browser.setTextField(".id", new RegularExpression("StoreEFTAdjAccAmtView-\\d+\\.amount",false), amount);
	}
	
	public void clickEFTAdjustmentDetailSpace(){
		browser.clickGuiObject(Property.toPropertyArray(".class", "Html.TD",".className","label_row",".text",  new RegularExpression("EFT Adjustment(\\s)?Details", false)));
	}
	
	public void setEFTAdjustmentInfo(EFTAdjustmentInfo eftAdjustment){
		this.selectAdjustmentType(eftAdjustment.getAdjustmentType());
		this.selectAdjustmentReason(eftAdjustment.getReason());
		
		this.setTotalAdjustmentAmount(eftAdjustment.getTotalAdjustmentAmount());
		this.clickEFTAdjustmentDetailSpace();
		ajax.waitLoading();
		this.selectAccount(eftAdjustment.getAccount());
		
		this.setNote(eftAdjustment.getNote());
		
		//TODO split and account selected need to update		
	}
}
