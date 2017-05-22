package com.activenetwork.qa.awo.pages.orms.common.giftcard;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class OrmsGiftCardDetailsPage extends OrmsPage {

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsGiftCardDetailsPage _instance = null;
	
	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsGiftCardDetailsPage() {
		browser = Browser.getInstance();
	}
	
	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsGiftCardDetailsPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsGiftCardDetailsPage();
		}

		return _instance;
	}
	
	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression(" Gift Card Details$", false));
	}
	
	/**Click OK button*/
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK", true);
	}
	
	/** Click Fees */
	public void clickFees() {
		IHtmlObject[] objs = getTransactionFrame();
		if(objs.length>0){
			browser.clickGuiObject(".class", "Html.A", ".text", "Fees",false,0,objs[0]);
			Browser.unregister(objs);
		}else{
			browser.clickGuiObject(".class", "Html.A", ".text", "Fees");
		}
	}
	
	private Property[] assignCardEnableBtn() {
		return Property.toPropertyArray(".class","Html.A",".text","Assign Card");
	}
	
	private Property[] assignCardDisableBtn() {
		return Property.toPropertyArray(".class","Html.SPAN",".text","Assign Card");
	}
	
	public boolean isAssignCardBtnEnabled(){
		return browser.checkHtmlObjectDisplayed(assignCardEnableBtn());
	}
	
	public boolean isAssignCardBtnExist(){
		return browser.checkHtmlObjectDisplayed(assignCardEnableBtn())||browser.checkHtmlObjectDisplayed(assignCardDisableBtn());
	}
	
	public void clickAssignCard(){
		browser.clickGuiObject(this.assignCardEnableBtn());
	}
	
	/**
	 * Click void sale button
	 */
	public void clickVoidSale(){
		browser.clickGuiObject(".class","Html.A",".text","Void Sale");
	}
	
	public void clickReloadGiftCard(){
		browser.clickGuiObject(".class","Html.A",".text","Reload Gift Card");
	}
	
	private Property[] activateCardEnableBtn() {
		return Property.toPropertyArray(".class","Html.A",".text","Activate Card");
	}
	
	private Property[] activeCardDisableBtn() {
		return Property.toPropertyArray(".class","Html.SPAN",".text","Activate Card");
	}
	
	public boolean isActiveCardBtnExist(){
		return browser.checkHtmlObjectDisplayed(activateCardEnableBtn())||browser.checkHtmlObjectDisplayed(activeCardDisableBtn());
	}
	
	/**Click Activate Card*/
	public void clickActivateCard(){
		browser.clickGuiObject(activateCardEnableBtn(), true);
	}
	
	/**Click History */
	public void clickHistory(){
		browser.clickGuiObject(".class", "Html.A", ".text", "History", true);
	}
	
	/**Click Add Note and Alerts button*/
	public void clickAddNoteAlerts() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Note/Alert");
	}
	
	public void clickInvoiceNum(String invoiceNum){
		browser.clickGuiObject(".class", "Html.A", ".text", invoiceNum);
	}

	/**Click Note and Alerts button*/
	public void clickNotesAndAlerts() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Notes & Alerts");
	}
	/**Get the notice message shown on the page*/
	public String getNoteMessage() {
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV", ".id", "NOTSET"));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find message object.");
		}
		String text = objs[0].text();
		Browser.unregister(objs);		
		return text;		
	}
	
	private Property[] spanObject(String label) {
		return Property.toPropertyArray(".class","Html.SPAN",".text", new RegularExpression(label + ".*",false));
	}
	
	private String getLabelValue(String label){
		IHtmlObject[] objs = browser.getHtmlObject(this.spanObject(label));
		String content = objs[0].text().replace(label, "").trim();
		Browser.unregister(objs);	
		return content;
	}
	
	public String getGiftCardNum(){
		return getLabelValue("Gift Card #");
	}
	
	public String getStatus(){
		return getLabelValue("Status");
	}
	
	public String getAmount(){
		return getLabelValue("Available Balance").replace("$", "");
	}
}
