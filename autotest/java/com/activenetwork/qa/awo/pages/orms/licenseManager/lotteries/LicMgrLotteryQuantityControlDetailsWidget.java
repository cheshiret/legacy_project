package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.awo.datacollection.legacy.orms.QuantityControlInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrPrivilegeQuantityControlCommonWidget;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Oct 11, 2013
 */
public class LicMgrLotteryQuantityControlDetailsWidget extends LicMgrPrivilegeQuantityControlCommonWidget {
	private static LicMgrLotteryQuantityControlDetailsWidget _instance = null;
	
	private LicMgrLotteryQuantityControlDetailsWidget() {
		super("Add Product Quantity Controls Popup");
	}
	
	public static LicMgrLotteryQuantityControlDetailsWidget getInstance() {
		if(_instance == null) _instance = new LicMgrLotteryQuantityControlDetailsWidget();
		return _instance;
	}
	
	public void setQuantityControlInfo(QuantityControlInfo quantityControl){
		this.selectLocationClass(quantityControl.locationClass);
		this.selectMultiSalesAllowance(quantityControl.multiSalesAllowance);
		if(this.checkMaxQuantityPerTranIsExists()) {
			this.setMaxQuantityPerTransaction(quantityControl.maxQuantityPerTran);
		}
		if(this.checkMaxAllowedIsExists()) {
			this.setMaxAllowed(quantityControl.maxAllowed);
		}
	}
}
