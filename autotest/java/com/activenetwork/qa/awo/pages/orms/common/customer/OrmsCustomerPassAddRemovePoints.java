package com.activenetwork.qa.awo.pages.orms.common.customer;


import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Apr 30, 2014
 */
public class OrmsCustomerPassAddRemovePoints extends DialogWidget {
	
	private static OrmsCustomerPassAddRemovePoints _instance = null;
	
	private OrmsCustomerPassAddRemovePoints() {
		super("Add/Remove Points");
	}
	
	public static OrmsCustomerPassAddRemovePoints getInstance() {
		if(_instance == null) _instance = new OrmsCustomerPassAddRemovePoints();
		return _instance;
	}
	
	private Property[] action() {
		return Property.toPropertyArray(".id", "PointAllocationView.transactionType");
	}
	
	private Property[] numOfPoints() {
		return Property.toPropertyArray(".id", "PointAllocationView.points");
	}
	
	private Property[] currentEarnedPoints() {
		return Property.toPropertyArray(".id", "PointAllocationView.currentPoints");
	}
	
	private Property[] reason() {
		return Property.toPropertyArray(".id", new RegularExpression("PointAllocationView-\\d+\\.reason", false));
	}
	
	private Property[] comments() {
		return Property.toPropertyArray(".id", "PointAllocationView.comments");
	}
	
	public void selectAction(String action) {
		browser.selectDropdownList(action(), action);
	}
	
	public void setNumOfPoints(String points) {
		browser.setTextField(numOfPoints(), points);
	}
	
	public String getCurrentEarnedPoints() {
		return browser.getTextFieldValue(currentEarnedPoints());
	}
	
	public void selectReason(String reason) {
		browser.selectDropdownList(reason(), reason);
	}
	
	public void selectReason(int index) {
		browser.selectDropdownList(reason(), index);
	}
	
	public void setComments(String comments) {
		browser.setTextArea(comments(), comments);
	}
	
	public void setupPointsInfo(String action, String numOfPoints, String reason, String comments) {
		if(!StringUtil.isEmpty(action)) {
			selectAction(action);
			ajax.waitLoading();
		}
		setNumOfPoints(numOfPoints);
		if(!StringUtil.isEmpty(reason)) {
			selectReason(reason);
		} else {
			selectReason(1);
		}
		setComments(comments);
	}
}
