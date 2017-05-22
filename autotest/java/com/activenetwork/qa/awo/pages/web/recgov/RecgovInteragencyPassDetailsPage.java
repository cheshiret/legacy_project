package com.activenetwork.qa.awo.pages.web.recgov;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.web.pos.InteragencyPassAttr;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.page.IPopupPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * @Description:
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Apr 23, 2014
 */
//public class RecgovInteragencyPassDetailsPage extends UwpHeaderBar{
public class RecgovInteragencyPassDetailsPage extends UwpPage implements IPopupPage {
	private String attributeName;
	private Object value;
	
	static class SingletonHolder {
		protected static RecgovInteragencyPassDetailsPage _instance = new RecgovInteragencyPassDetailsPage();
	}

//	protected RecgovInteragencyPassDetailsPage() {
//	}

	protected RecgovInteragencyPassDetailsPage() {
		browser = null;
		attributeName = "url";
		value = new RegularExpression(".*/htm/InterAgencyPassSale.html",false);
	}
	
	public static RecgovInteragencyPassDetailsPage getInstance() {
		return SingletonHolder._instance;
	}

	private static String LABEL_PRICE = "Price: \\$";
	private static String LABEL_QUANTITY = "Quantity";
	private static String LABEL_DOMESTICSTANDARD = "Domestic Standard \\(average 1 - 2 weeks\\) \\$\\d+\\.\\d+ mailing fee";
	private static String LABEL_DOMESTICEXPEDITED = "Domestic Expedited \\(average 2 - 3 days\\) \\$\\d+\\.\\d+ mailing fee";

	protected Property[] interagencyPassQuantity(){
		return Property.concatPropertyArray(div(), ".id", "quantity");
	}

	protected Property[] labelProp(String labelReg){
		return Property.concatPropertyArray(label(), ".text", new RegularExpression(labelReg, false));
	}

	protected Property[] standardDeliveryMethod(){
		return Property.toPropertyArray(".id", "standardshipping");
	}

	protected Property[] standardDeliveryMethod(String id){
		return Property.toPropertyArray(".name", "passrb", ".id", id);
	}

	protected Property[] expeditedDeliveryMethod(){
		return Property.toPropertyArray(".id", "expeditedshipping");
	}

	protected Property[] expeditedDeliveryMethod(String id){
		return Property.toPropertyArray(".name", "passrb", ".id", id);
	}

	protected Property[] addToShoppingCartButton(){
		return Property.toPropertyArray(".id", "button", ".text", "Add to Shopping Cart");
	}

	protected Property[] formContent(){
		return Property.toPropertyArray(".className", "formContent");
	}

//	public boolean exists() {
//		return browser.checkHtmlObjectExists(standardDeliveryMethod());
//	}

	public boolean exists() {
		browser=Browser.getInstance().getBrowser(attributeName, value);
		return browser!=null;
	}
	
	private String getObjIDByLabel(String labelReg) {
		IHtmlObject[] objs = browser.getHtmlObject(labelProp(labelReg));
		if (objs.length < 1) {
			throw new ErrorOnPageException("Can't find the div for " + labelReg);
		}
		String forValue = objs[0].getAttributeValue("for");
		Browser.unregister(objs);
		return forValue;
	}

	public void selectInteragencyQuantity(String interagencyPassQuantity){
		browser.selectDropdownList(interagencyPassQuantity(), interagencyPassQuantity);
	}

	public String getInteragencyQuantity(){
		return browser.getDropdownListValue(interagencyPassQuantity(), 0);
	}

	public List<String> getInteragencyQuantities(){
		return browser.getDropdownElements(interagencyPassQuantity());
	}

	//	public void selectStandardDeliveryMethod(){
	//		browser.selectRadioButton(standardDeliveryMethod(), 0);
	//	}

	public void selectStandardDeliveryMethod(){
		browser.clickGuiObject(standardDeliveryMethod(getObjIDByLabel(LABEL_DOMESTICSTANDARD)));
	}

	//	public void selectExpeditedDeliveryMethod(){
	//		browser.selectRadioButton(expeditedDeliveryMethod(), 0);
	//	}

	public void selectExpeditedDeliveryMethod(){
		browser.clickGuiObject(expeditedDeliveryMethod(getObjIDByLabel(LABEL_DOMESTICEXPEDITED)));
	}

	public void clickAddToShoppingCartButton(){
		browser.clickGuiObject(addToShoppingCartButton());
	}

	public void setInteragencyPassInfo(Data<InteragencyPassAttr> interagencyPass) {
		selectInteragencyQuantity(interagencyPass.stringValue(InteragencyPassAttr.quantity));
		if(interagencyPass.booleanValue(InteragencyPassAttr.isExpeditedDeliveryMethod)){
			selectExpeditedDeliveryMethod();
		}else selectStandardDeliveryMethod();
	}

	public String getFormContent(){
		return browser.getObjectText(formContent());
	}

	public String getPrice(){
		return getFormContent().split(LABEL_QUANTITY)[0].split(LABEL_PRICE)[1].trim();
	}
}
