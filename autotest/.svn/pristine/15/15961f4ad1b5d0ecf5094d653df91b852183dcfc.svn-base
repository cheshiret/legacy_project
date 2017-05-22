package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.lottery.PointTypesAttr;
import com.activenetwork.qa.testapi.datacollection.Data;
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
 * @Date  Feb 13, 2014
 */
public class LicMgrPointTypeDetailsPage extends LicMgrLotteriesCommonPage {
	private static LicMgrPointTypeDetailsPage _instance = null;
	
	protected LicMgrPointTypeDetailsPage (){}
	
	public static LicMgrPointTypeDetailsPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrPointTypeDetailsPage();
		}
		
		return _instance;
	}
	
	protected Property[] pointTypeID(){
		return Property.toPropertyArray(".id", new RegularExpression("LotteryPointTypeView-\\d+\\.id:ZERO_TO_NEW", false));
	}
	
	protected Property[] code() {
		return Property.toPropertyArray(".id", new RegularExpression("LotteryPointTypeView-\\d+\\.code", false));
	}
	
	protected Property[] displayCode() {
		return Property.toPropertyArray(".id", new RegularExpression("LotteryPointTypeView-\\d+\\.displayCode", false));
	}
	
	protected Property[] description() {
		return Property.toPropertyArray(".id", new RegularExpression("LotteryPointTypeView-\\d+\\.description", false));
	}
	
	protected Property[] maxAllowed() {
		return Property.toPropertyArray(".id", new RegularExpression("LotteryPointTypeView-\\d+\\.maxAllowedPoints", false));
	}
	
	protected Property[] lyLimit() {
		return Property.toPropertyArray(".id", new RegularExpression("LotteryPointTypeView-\\d+\\.pointAwardLimit", false));
	}
	
	protected Property[] transitionSetting() {
		return Property.toPropertyArray(".id", new RegularExpression("LotteryPointTypeView-\\d+\\.transitionSetting", false));
	}
	
	protected Property[] ok() {
		return Property.concatPropertyArray(a(), ".id", "Anchor", ".text", "OK");
	}
	
	protected Property[] cancel() {
		return Property.concatPropertyArray(a(), ".id", "Anchor", ".text", "Cancel");
	}
	
	protected Property[] apply() {
		return Property.concatPropertyArray(a(), ".id", "Anchor", ".text", "Apply");
	}
	
	protected Property[] errorMsg(){
		return Property.concatPropertyArray(div(), ".className", "message msgerror");
	}
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(code());
	}
	
	public String getPointTypeID(){
		return browser.getTextFieldValue(pointTypeID());
	}
	
	public void setCode(String code){
		browser.setTextField(code(), code);
	}
	
	public void setDisplayCode(String displayCode){
		browser.setTextField(displayCode(), displayCode);
	}
	
	public void setDescription(String description){
		browser.setTextField(description(), description);
	}
	
	public void setMaxAllowed(String maxAllowed){
		browser.setTextField(maxAllowed(), maxAllowed);
	}
	
	public void selectLYLimit(String lyLimit){
		browser.selectDropdownList(lyLimit(), lyLimit);
	}
	
	public void selectTransitionSettings(String transitionSettings){
		browser.selectDropdownList(transitionSetting(), transitionSettings);
	}
	
	public void clickOK(){
		browser.clickGuiObject(ok());
	}
	
	public void clickCancel(){
		browser.clickGuiObject(cancel());
	}
	
	public void clickApply(){
		browser.clickGuiObject(apply());
	}
	
	public String getErrorMsg(){
		return browser.getObjectText(errorMsg());
	}
	
	public void setupPointType(Data<PointTypesAttr> pointType){
		setCode(pointType.stringValue(PointTypesAttr.code));
		if(StringUtil.notEmpty(pointType.stringValue(PointTypesAttr.displayCode)))
			setDisplayCode(pointType.stringValue(PointTypesAttr.displayCode));
		setDescription(pointType.stringValue(PointTypesAttr.description));
		if(StringUtil.notEmpty(pointType.stringValue(PointTypesAttr.maxAllowed)))
			setMaxAllowed(pointType.stringValue(PointTypesAttr.maxAllowed));
		selectLYLimit(pointType.stringValue(PointTypesAttr.lyLimit));
		if(StringUtil.notEmpty(pointType.stringValue(PointTypesAttr.transitionSettings)))
			selectTransitionSettings(pointType.stringValue(PointTypesAttr.transitionSettings));
	}
	
}