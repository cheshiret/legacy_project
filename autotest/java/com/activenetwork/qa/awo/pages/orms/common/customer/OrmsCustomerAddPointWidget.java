/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.customer;

import com.activenetwork.qa.awo.datacollection.legacy.Customer.PointInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author szhou
 * @Date  Mar 18 2014
 */
public class OrmsCustomerAddPointWidget extends DialogWidget{
    private static OrmsCustomerAddPointWidget _instance = null;
	
	private OrmsCustomerAddPointWidget() {
		super("Add/Remove Points");
	}
	
	public static OrmsCustomerAddPointWidget getInstance() {
		if(_instance == null) {
			_instance = new OrmsCustomerAddPointWidget();
		}
		
		return _instance;
	}
	
	private Property[] action() {
		return Property.toPropertyArray(".id", "PointAllocationView.transactionType");
	}
	
	private Property[] points() {
		return Property.toPropertyArray(".id", "PointAllocationView.points");
	}
	
	private Property[] reason() {
		return Property.toPropertyArray(".id", new RegularExpression("PointAllocationView-\\d+\\.reason",false));
	}
	
	private Property[] comment() {
		return Property.toPropertyArray(".id", "PointAllocationView.comments");
	}
	
	private Property[] okButton() {
		return Property.toPropertyArray(".class", "Html.A",".text","OK",".id","Anchor");
	}
	
	public void selectAction(String action){
		browser.selectDropdownList(action(), action);
	}
	
	public void setPoint(String point){
		browser.setTextField(points(), point);
	}
	
	public void selectReason(String reason){
		browser.selectDropdownList(reason(), reason);
	}
	
	public void clickOK(){
		IHtmlObject[] objs=browser.getHtmlObject(okButton());
		if(objs.length<2){
			throw new ErrorOnPageException("Couldn't find OK button...");
		}
		objs[1].click();
		
		Browser.unregister(objs);
		
	}
	
	public void setComment(String comment){
		browser.setTextArea( comment(),comment);
	}
	
	public void setPointInfo(PointInfo point){
		this.selectAction(point.getAction());
		this.setPoint(point.getAddingpoints());
		this.selectReason(point.getReason());
	    this.setComment(point.getAddingComment());
	}
	
}
