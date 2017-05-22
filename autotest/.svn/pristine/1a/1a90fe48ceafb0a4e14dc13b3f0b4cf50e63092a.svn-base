package com.activenetwork.qa.awo.pages.orms.common.pos;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description:
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  May 4, 2014
 */
public class OrmsPOSAdditionalInfomationPage  extends OrmsPage {
	static class SingletonHolder {
		protected static OrmsPOSAdditionalInfomationPage _instance = new OrmsPOSAdditionalInfomationPage();}

	protected OrmsPOSAdditionalInfomationPage() {}

	public static OrmsPOSAdditionalInfomationPage getInstance() {
		return SingletonHolder._instance;
	}
	
	/** Page Object Property Definition Begin */
	protected Property[] additionalInfomationLink(){
		return Property.concatPropertyArray(a(), ".className", "btnanchor traillink", ".text", "Additional Information");
	}
	
	protected Property[] add(){
		return Property.concatPropertyArray(a(), ".id", "Anchor", ".text", "Add");
	}
	
	protected Property[] remove(){
		return Property.concatPropertyArray(a(), ".id", "Anchor", ".text", "Remove");
	}
	
	protected Property[] addPassHolder(){
		return Property.concatPropertyArray(a(), ".id", "Anchor", ".text", "Add Pass Holder");
	}
	
	protected Property[] variableUnitPrice(){
		return Property.toPropertyArray(".id", new RegularExpression("InlineChildPassInfo-\\d+.variablePrice", false));
	}
	
	protected Property[] color(){
		return Property.toPropertyArray(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8257\\]", false));
	}
	
	protected Property[] vehiclePlate(){
		return Property.toPropertyArray(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[100889\\]", false));
	}
	
	protected Property[] posDIV(String posName){
		return Property.concatPropertyArray(div(), ".className", "div_info", ".text", new RegularExpression("^"+posName+".*", false));
	}
	
	protected Property[] secondaryPrdName(){
		return Property.toPropertyArray(".id", new RegularExpression("InlineChildPassInfo-\\d+\\.product", false));
	}
	
	protected Property[] ok(){
		return Property.concatPropertyArray(a(), ".id", "Anchor", ".text", "OK");
	}
	
	/** Page Object Property Definition End */

	public boolean exists() { 
		return browser.checkHtmlObjectExists(additionalInfomationLink());
	}
	
	public void clickAdditionalInfomationLink(){
		browser.clickGuiObject(additionalInfomationLink());
	}
	
	public void clickAddButton(){
		browser.clickGuiObject(add());
	}
	
	public void clickAddButton(String posName){
		browser.clickGuiObject(Property.atList(posDIV(posName), add()));
	}
	
	public void clickAddButton(int index){
		browser.clickGuiObject(add(), index);
	}
	public void clickRemoveButton(){
		browser.clickGuiObject(remove());
	}
	
	public void clickAddPassHolderButton(){
		browser.clickGuiObject(addPassHolder());
	}
	
	public void setVariableUnitPrice(String parentPosName, String variableUnitPrice){
		browser.setTextField(Property.atList(posDIV(parentPosName), variableUnitPrice()), variableUnitPrice);
	}
	
	public void setColor(String parentPosName, String color){
		browser.setTextField(Property.atList(posDIV(parentPosName), color()), color);
	}
	
	public void setColor(String color, int index){
		browser.setTextField(color(), color, index);
	}
	
	public void setVehiclePlate(String parentPosName, String vehiclePlate){
		browser.setTextField(Property.atList(posDIV(parentPosName), vehiclePlate()), vehiclePlate);
	}
	
	public void setVehiclePlate(String vehiclePlate, int index){
		browser.setTextField(vehiclePlate(), vehiclePlate, index);
	}
	
	public void clickOK(){
		browser.clickGuiObject(ok());
	}
	
	public IHtmlObject[] getPosDIVObjs(String posName){
		IHtmlObject[] objs = browser.getHtmlObject(posDIV(posName));
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find pos:"+posName+" DIV.");
		}
		return objs;
	}
	
	public void selectSecondaryPrdName(String parentPosName, String childPosName){
		IHtmlObject[] objs = getPosDIVObjs(parentPosName);
		browser.selectDropdownList(secondaryPrdName(), childPosName, true, objs[0]);
		Browser.unregister(objs);
	}
	
	public void selectSecondaryPrdName(String childPosName, int index){
		browser.selectDropdownList(secondaryPrdName(), childPosName, index);
	}
	
	public void selectSecondaryPrdName(String childPosName){
		browser.selectDropdownList(secondaryPrdName(), childPosName, 0);
	}
	
	public void setupAdditionalInfomation(POSInfo... pos){
		int addIndex = 0;
		for(int i=0; i<pos.length; i++){
			if(StringUtil.notEmpty(pos[i].childPOSName)){
				clickAddButton(addIndex);
				addIndex++;
				ajax.waitLoading();
				
				selectSecondaryPrdName(pos[i].childPOSName);
				ajax.waitLoading();
				
				if(StringUtil.notEmpty(pos[i].childPOSVariableUnitPrice));{
					setVariableUnitPrice(pos[i].product, pos[i].childPOSVariableUnitPrice);
					ajax.waitLoading();
				}
				
				if(StringUtil.notEmpty(pos[i].childPOSAttr_Color))
					setColor(pos[i].childPOSAttr_Color, i);
				
				if(StringUtil.notEmpty(pos[i].childPOSAttr_VehiclePlate))
					setVehiclePlate(pos[i].childPOSAttr_VehiclePlate, i);
			}
		}
	}
}
