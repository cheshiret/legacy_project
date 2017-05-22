package com.activenetwork.qa.awo.pages.orms.common.marina;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * @Description: This widget pop-up when click "Add Contact Log" button in list entry detail page
 * @author phoebe
 * @Date  Jan 9, 2014
 */
public class OrmsListEntryContactStatusWidget extends DialogWidget {
	private static OrmsListEntryContactStatusWidget _instance = null;
	
	private OrmsListEntryContactStatusWidget() {
		super("Contact Status");
	}
	
	public static OrmsListEntryContactStatusWidget getInstance() {
		if(_instance == null) {
			_instance = new OrmsListEntryContactStatusWidget();
		}
		
		return _instance;
	}
	private String prefix = "WaitingListContactHistoryView-\\d+\\.";
	
	private Property[] contactStatusDropdownList() {  
		return Property.toPropertyArray(".id", new RegularExpression(prefix+"waitingListContactStatusID", false));
	}
	
	private Property[] commentsTextArea(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix+"note", false));
	}
	
	private Property[] okButton(){
		return Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("Ok", false));
	}
	
	private Property[] cancelButton(){
		return Property.toPropertyArray(".class", "Html.A", ".text", "Cancel");
	}
	
	public void setContactStatus(String status){
		browser.selectDropdownList(this.contactStatusDropdownList(), status);
	}
	
	public void setComments(String comment){
		browser.setTextArea(this.commentsTextArea(), comment);
	}
	
	public void setContactInfo(String status, String comment){
		this.setContactStatus(status);
		this.setComments(comment);
	}
	
	public void clickOk(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^Ok", false), 1);
	}
	
	public void clickCancel(){
		browser.clickGuiObject(this.cancelButton());
	}
	
	public void setContactInfoAndClickOk(String status, String comment){
		this.setContactInfo(status, comment);
		this.clickOk();
	}
}
