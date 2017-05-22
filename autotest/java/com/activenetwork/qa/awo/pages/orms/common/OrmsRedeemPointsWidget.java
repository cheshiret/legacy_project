/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * 
 * @author szhou
 * @Date  Mar 25, 2014
 *
 */
public class OrmsRedeemPointsWidget extends DialogWidget{
	private static OrmsRedeemPointsWidget instance = null;

	private OrmsRedeemPointsWidget() {
		super("Redeem Points");// title is Redeem Points.
	}

	public static OrmsRedeemPointsWidget getInstance() {
		if (instance == null) {
			instance = new OrmsRedeemPointsWidget();
		}
		return instance;
	}
	
	public void selectFirstRedeemRecord(){
		browser.selectCheckBox(".id", new RegularExpression("LoyaltyFeatureRedeemUnitData-\\d+\\.selected",false), 0);
	}
	
	public void clickRedeem(){
		browser.clickGuiObject(".id", "RedeemPointButtonAnchor");
	}
}
