/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.CustomerPoint;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @ScriptName LicMgrConfirmDialogWidget.java
 * @Date:Feb 14, 2011
 * @Description:
 * @author asun
 */
public class LicMgrCustomerAddPointsWidget extends DialogWidget {
	
	private static LicMgrCustomerAddPointsWidget instance = null;

	private LicMgrCustomerAddPointsWidget() {
		super("(Add|Deduct) Point(s)?|(Pool Status)");
	}
	
	public static LicMgrCustomerAddPointsWidget getInstance() {
		if (instance == null) {
			instance = new LicMgrCustomerAddPointsWidget();
		}
		return instance;
	}
	
	public void selectPointType(String type)
	{
		browser.selectDropdownList(".id", new RegularExpression("PointAllocationView-\\d+\\.pointType",false),type);
	}
	public void setToAdd(String amount)
	{
		browser.setTextField(".id", new RegularExpression("PointAllocationView-\\d+\\.points",false),amount);
	}
	
	public void selectReason(String txt)
	{
		browser.selectDropdownList(".id", new RegularExpression("PointAllocationView-\\d+\\.reason",false),txt);
	}
	
	public void selectReason(int idx)
	{
		browser.selectDropdownList(".id", new RegularExpression("PointAllocationView-\\d+\\.reason",false),idx);
	}
	
	public void setComments(String text)
	{
		browser.setTextArea(".id", new RegularExpression("PointAllocationView-\\d+\\.comments",false),text);
	}
	
	public void setChangePointsInfo(CustomerPoint point, boolean isAdd)
	{
		if (StringUtil.notEmpty(point.pointTypeCode) && StringUtil.notEmpty(point.pointTypeDescr)) {
			this.selectPointType(point.pointTypeCode + "-" + point.pointTypeDescr);
			ajax.waitLoading();
		} else if(StringUtil.notEmpty(point.pointType))
		{
			this.selectPointType(point.pointType);
			ajax.waitLoading();
		}
		
		if(isAdd && StringUtil.notEmpty(point.pointToAdd))
		{
			this.setToAdd(point.pointToAdd);
		} else if (!isAdd && StringUtil.notEmpty(point.pointToDeduct)) {
			this.setToAdd(point.pointToDeduct);
		}
		
		if(StringUtil.notEmpty(point.addReason))
		{
			this.selectReason(point.addReason);
		}else{
			this.selectReason(0);
		}
		if(StringUtil.notEmpty(point.comments))
		{
			this.setComments(point.comments);
		}
	}
	
}
