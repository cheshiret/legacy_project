package com.activenetwork.qa.awo.pages.orms.inventoryManager.loyaltyprogram;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PointType;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Mar 17, 2014
 */
public class InvMgrPointTypeDetailsWidget extends DialogWidget {
	
	private static InvMgrPointTypeDetailsWidget _instance = null;
	
	private InvMgrPointTypeDetailsWidget() {
		super("Point Type Details");
	}
	
	public static InvMgrPointTypeDetailsWidget getInstance() {
		if(_instance == null) _instance = new InvMgrPointTypeDetailsWidget();
		return _instance;
	}
	
	private Property[] id() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyPointTypeView-\\d+\\.id\\:ZERO_TO_NEW", false));
	}
	
	private Property[] name() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyPointTypeView-\\d+\\.name", false));
	}
	
	private Property[] redeemableType() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyPointTypeView-\\d+\\.redeemableType", false));
	}
	
	public void setName(String name) {
		browser.setTextField(name(), name);
	}
	
	public void selectRedeemableType(String type) {
		browser.selectDropdownList(redeemableType(), type);
	}
	
	public void setupPointType(PointType point) {
		this.setName(point.getName());
		this.selectRedeemableType(point.getRedeemableType());
	}
	
	public String getID() {
		return browser.getObjectText(id());
	}
}
