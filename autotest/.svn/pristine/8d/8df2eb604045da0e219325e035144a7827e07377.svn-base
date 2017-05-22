package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.datacollection.datadefinition.web.pos.SerializePassAttr;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * @Description: This page will display after click annual day use pass or annual camping pass image in home page
 * URL:showPage.do?name=common&commonPath=/htm/NM_AnnualPasses.html
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Apr 25, 2014
 */
public class UwpSerializePassDetailsPage extends UwpHeaderBar{
	static class SingletonHolder {
		protected static UwpSerializePassDetailsPage _instance = new UwpSerializePassDetailsPage();
	}

	protected UwpSerializePassDetailsPage() {
	}

	public static UwpSerializePassDetailsPage getInstance() {
		return SingletonHolder._instance;
	}

	private static String LABEL_ANNUALDAYUSEPASS = "Annual Day Use Pass - \\$\\d+";
	private static String LABEL_ANNUALCAMPINGPASS = "Annual Camping Pass";

	/** Page Object Property Definition Begin */
	protected Property[] labelProp(String labelReg){
		return Property.concatPropertyArray(label(), ".text", new RegularExpression(labelReg, false));
	}

	protected Property[] annualDayUsePass(){
		return Property.toPropertyArray(".id", "pass1", ".value", "AnnualDayUsePass");
	}

	protected Property[] annualCampingPass(){
		return Property.toPropertyArray(".id", "pass2");
	}

	protected Property[] annualCampingPass(String id){
		return Property.toPropertyArray(".id", id);
	}

	protected Property[] annualCampingPassInState(){
		return Property.toPropertyArray(".name", "pass2rb", ".value", "AnnualCampingPassInState");
	}

	protected Property[] annualCampingPassOutState(){
		return Property.toPropertyArray(".name", "pass2rb", ".value", "AnnualCampingPassOutState");
	}

	protected Property[] addToShoppingCart(){
		return Property.toPropertyArray(".id", "continueshop", ".text", "Add to Shopping Cart");
	}

	protected Property[] backToPreviousPage(){
		return Property.concatPropertyArray(a(), ".className", "CampName");
	}
	/** Page Object Property Definition End */

	public boolean exists() {
		return browser.checkHtmlObjectExists(annualDayUsePass());
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

	public void selectAnnualDayUsePass(){
		browser.selectCheckBox(annualDayUsePass());
	}

	public boolean isAnnualDayUsePassExist(){
		return browser.checkHtmlObjectExists(annualDayUsePass());
	}

	public void selectAnnualCampingPass(){
		browser.selectCheckBox(annualCampingPass());
	}

	public void selectAnnualCampingPassInState(){
		browser.selectRadioButton(annualCampingPassInState(), 0);
	}

	public void selectAnnualCampingPassOutOfState(){
		browser.selectRadioButton(annualCampingPassOutState(), 0);
	}

	public void clickAddToShoppingCart(){
		browser.clickGuiObject(addToShoppingCart());
	}

	public void clickBackToPreviousPage(){
		browser.clickGuiObject(backToPreviousPage());
	}

	public void setupPassInfo(Data<SerializePassAttr> pass){
		if(pass.booleanValue(SerializePassAttr.isAnnualDayUsePass))
			selectAnnualDayUsePass();
		if(pass.booleanValue(SerializePassAttr.isAnnualCampingPass)){
			selectAnnualCampingPass();
			if(pass.booleanValue(SerializePassAttr.isInState)){
				selectAnnualCampingPassInState();
			}else selectAnnualCampingPassOutOfState();
		}
	}
}
