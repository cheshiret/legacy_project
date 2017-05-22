/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.marina;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author ssong
 * @Date  Jul 10, 2013
 */
public class OrmsVoidListEntryPage extends OrmsPage{
	
	private static OrmsVoidListEntryPage _instance = null;
	
	private OrmsVoidListEntryPage() {}
	
	public static OrmsVoidListEntryPage getInstance() {
		if(_instance == null) {
			_instance = new OrmsVoidListEntryPage();
		}
		
		return _instance;
	}
	private String prefix_Item = "SlipWaitingListItemView-\\d+\\.";
	
	private String prefix_product = "SlipWaitingListProductView-\\d+\\.";
	
	private String prefix_customer = "BillingCustomerInfo-\\d+\\.";
	
	private String prefix_order = "MarinaOrderView-\\d+\\.";
	
	protected Property[] listIdSpan() {  
		return Property.toPropertyArray(".id", new RegularExpression(prefix_Item + "productId",false));
	}
	protected Property[] listNameSpan() {  
		return Property.toPropertyArray(".id", new RegularExpression(prefix_Item + "productName",false));
	}
	protected Property[] listStatusSpan() {  
		return Property.toPropertyArray(".id", new RegularExpression(prefix_product + "status:CB_TO_NAME",false));
	}
	protected Property[] listParticipationSpan() {  
		return Property.toPropertyArray(".id", new RegularExpression(prefix_product + "participationDisplayString",false));
	}
	
	protected Property[] customerNameSpan(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_customer + "name",false));
	}
	
	protected Property[] customerPhoneSpan(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_customer + "phone",false));
	}
	
	protected Property[] customerEmailSpan(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_customer + "email",false));
	}
	
	protected Property[] customerOrderNumSpan(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_order + "orderName",false));
	}
	
	protected Property[] customerEntryStatusSpan(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_Item + "waitingStatus:CB_TO_NAME",false));
	}
	
	protected Property[] customerEntryTypeSpan(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_Item + "waitingType:CB_TO_NAME",false));
	}
	
	protected Property[] customerPreDockSpan(){
		return Property.toPropertyArray(".id", new RegularExpression("LocationView-\\d+\\.name",false));
	}
	
	protected Property[] customerPreSlipSpan(){
		return Property.toPropertyArray(".id", new RegularExpression("ProductLightView-\\d+\\.productName",false));
	}
	
	protected Property[] voidReasonTextArea(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix_Item + "actionReasonDesc",false));
	}
	
	protected Property[] completeVoidButton(){
		return Property.toPropertyArray(".class","Html.A",".text","Complete Void");
	}
	
	protected Property[] donotVoidButton(){
		return Property.toPropertyArray(".class", "Html.A", ".text", "Don't Void");
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TEXTAREA", ".id", new RegularExpression("SlipWaitingListItemView-\\d+\\.actionReasonDesc",false));
	}

	public void setVoidReason(String reason){
		browser.setTextArea(this.voidReasonTextArea(), reason);
	}
	
	public void clickCompleteVoid(){
		browser.clickGuiObject(this.completeVoidButton());
	}
	
	public void clickDontVoid() {
		browser.clickGuiObject(this.donotVoidButton());
	}
	
	public String getErrorMsg() {
		return browser.getObjectText(".class", "Html.DIV", ".className", "message msgerror");
	}
	
	public String getAttrValue(Property[] properties){
		IHtmlObject[] spans = browser.getHtmlObject(properties);
		if(spans.length < 1){
			throw new ErrorOnPageException("No span found for properties:" + properties.toString());
		}
		IHtmlObject[] subSpans = browser.getHtmlObject(".class", "Html.SPAN", spans[0]);
		String content = subSpans[0].text().trim();
		Browser.unregister(spans);
		Browser.unregister(subSpans);
		return content;
	}
	
	public String getListId(){
		return this.getAttrValue(this.listIdSpan()).replace("List ID", "").trim();
	}
	
	public String getListName(){
		return this.getAttrValue(this.listNameSpan()).replace("Name","").trim();
	}
	
	public String getListStatus(){
		return this.getAttrValue(this.listStatusSpan()).replace("Status","").trim();
	}
	
	public String getListParticipation(){
		return this.getAttrValue(this.listParticipationSpan()).replace("Participation","").trim();
	}
	
	public String getCustomerName(){
		return this.getAttrValue(this.customerNameSpan()).replace("Name", "").trim();
	}
	
	public String getCustomerPhone(){
		return this.getAttrValue(this.customerPhoneSpan()).replace("Phone", "").trim();
	}
	
	public String getCustomerEmail(){
		return this.getAttrValue(this.customerEmailSpan()).replace("Email", "").trim();
	}
	
	public String getCustomerOrderNum(){
		return this.getAttrValue(this.customerOrderNumSpan()).replace("Order#", "").trim();
	}
	
	public String getCustomerEntryStatus(){
		return this.getAttrValue(this.customerEntryStatusSpan()).replace("Entry Status", "").trim();
	}
	
	public String getCustomerEntryType(){
		return this.getAttrValue(this.customerEntryTypeSpan()).replace("Entry Type", "").trim();
	}
	
	public String getCustomerPreDock(){
		return this.getAttrValue(this.customerPreDockSpan()).replace("Preferred Dock", "").trim();
	}
	
	public String getCustomerPreSlip(){
		return this.getAttrValue(this.customerPreSlipSpan()).replace("Preferred Slip", "").trim();
	}
	
	public String getVoidReason(){
		return browser.getTextAreaValue(this.voidReasonTextArea());
	}
	
	public boolean isCompleteVoidButtonEnabled(){
		return browser.checkHtmlObjectEnabled(completeVoidButton());
	}
	
	public boolean isDoNotVoidButtonEnabled(){
		return browser.checkHtmlObjectEnabled(this.donotVoidButton());
	}
	
	public boolean isVoidReasonTextAreaEnabled(){
		return browser.checkHtmlObjectDisplayed(voidReasonTextArea());
	}
}
