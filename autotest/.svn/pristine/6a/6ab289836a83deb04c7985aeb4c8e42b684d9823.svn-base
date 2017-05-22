package com.activenetwork.qa.awo.pages.orms.inventoryManager.equipment;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.equipment.EquipmentAttr;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * This page is the equipment detail page, to go to this page, click "Add New" button in equipment list page
 * @author Phoebe
 */
public class InvMgrEquipmentDetailPage extends InvMgrCommonTopMenuPage {
	static private InvMgrEquipmentDetailPage _instance = null;

	protected InvMgrEquipmentDetailPage() {
	}

	static public InvMgrEquipmentDetailPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new InvMgrEquipmentDetailPage();
		}

		return _instance;
	}

	/**
	 * Check the object exists
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.SPAN",".text","Equipment Details");
	}
	
	protected Property[] idSpan(){
		return Property.toPropertyArray(".id", new RegularExpression("EquipmentProductDetailView-\\d+\\.id:ZERO_TO_NEW", false));
	}
	
	protected Property[] equipmentCodeTextField(){
		return Property.toPropertyArray(".id", new RegularExpression("EquipmentProductDetailView-\\d+\\.equipmentCode", false));
	}
	
	protected Property[] equipmentNameTextField(){
		return Property.toPropertyArray(".id", new RegularExpression("EquipmentProductDetailView-\\d+\\.equipmentName", false));
	}
	
	protected Property[] equipmentDesTextArea(){
		return Property.toPropertyArray(".id", new RegularExpression("EquipmentProductDetailView-\\d+\\.equipmentDesc", false));
	}
	
	protected Property[] equipmentTypeDropdownList(){
		return Property.toPropertyArray(".id", new RegularExpression("EquipmentProductDetailView-\\d+\\.productGrpView", false));
	}
	
	protected Property[] usageDropdownList(){
		return Property.toPropertyArray(".id", new RegularExpression("EquipmentProductDetailView-\\d+\\.resourceType", false));
	}

	protected Property[] statusDropdownList(){
		return Property.toPropertyArray(".id", new RegularExpression("EquipmentProductDetailView-\\d+\\.equipmentStatus", false));
	}
	
	protected Property[] webVisibleDropdownList(){
		return Property.toPropertyArray(".id", new RegularExpression("EquipmentProductDetailView-\\d+\\.webVisible", false));
	}
	
	protected Property[] allowGeneralPublicSalesDropdownList(){
		return Property.toPropertyArray(".id", new RegularExpression("EquipmentProductDetailView-\\d+\\.allowGP", false));
	}
	
	protected Property[] checkOutLagTimeTextField(){
		return Property.toPropertyArray(".id", new RegularExpression("EquipmentProductDetailView-\\d+\\.checkoutLagTime", false));
	}
	
	protected Property[] checkInLagTimeTextField(){
		return Property.toPropertyArray(".id", new RegularExpression("EquipmentProductDetailView-\\d+\\.checkinLagTime", false));
	}
	
	protected Property[] okBtn(){
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("Ok", false));
	}
	
	protected Property[] cancelBtn(){
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("Cancel", false));
	}
	
	protected Property[] applyBtn(){
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("Apply", false));
	}
	
	protected Property[] equipmentHoursTab(){
		return Property.concatPropertyArray(this.span(), ".text", "Equipment Hours");
	}
	
	public void setEquipmentCode(String code){
		browser.setTextField(this.equipmentCodeTextField(), code);
	}
	
	public void setEquipmentName(String name){
		browser.setTextField(this.equipmentNameTextField(), name);
	}
	
	public void setEquipmentDescription(String description){
		browser.setTextArea(this.equipmentDesTextArea(), description);
	}
	
	public void selectEquipmentType(String type){
		browser.selectDropdownList(this.equipmentTypeDropdownList(), type);
	}
	
	public void selectUsage(String usage){
		browser.selectDropdownList(this.usageDropdownList(), usage);
	}
	
	public void selectStatus(String status){
		browser.selectDropdownList(this.statusDropdownList(), status);
	}
	
	public void selectWebVisible(String visible){
		browser.selectDropdownList(this.webVisibleDropdownList(), visible);
	}
	
	public void selectAllowGeneralPublicSales(String allowSale){
		browser.selectDropdownList(this.allowGeneralPublicSalesDropdownList(), allowSale);
	}
	
	public void setCheckOutLagTime(String checkOutTime){
		browser.setTextField(this.checkOutLagTimeTextField(), checkOutTime);
	}
	
	public void setCheckInLagTime(String checkInTime){
		browser.setTextField(this.checkInLagTimeTextField(), checkInTime);
	}
	
	public void clickOk(){
		browser.clickGuiObject(this.okBtn());
	}
	
	public void clickCancel(){
		browser.clickGuiObject(this.cancelBtn());
	}
	
	public void clickApply(){
		browser.clickGuiObject(this.applyBtn());
	}
	
	public void setEquipmentInfo(Data<EquipmentAttr> eq){
		this.setEquipmentCode(eq.stringValue(EquipmentAttr.code));
		if(StringUtil.notEmpty(eq.stringValue(EquipmentAttr.name))){
			this.setEquipmentName(eq.stringValue(EquipmentAttr.name));
		}
		if(StringUtil.notEmpty(eq.stringValue(EquipmentAttr.descrition))){
			this.setEquipmentDescription(eq.stringValue(EquipmentAttr.descrition));
		}
		if(StringUtil.notEmpty(eq.stringValue(EquipmentAttr.equipmentType))){
			this.selectEquipmentType(eq.stringValue(EquipmentAttr.equipmentType));
		}
		if(StringUtil.notEmpty(eq.stringValue(EquipmentAttr.usage))){
			this.selectUsage(eq.stringValue(EquipmentAttr.usage));
		}
		if(StringUtil.notEmpty(eq.stringValue(EquipmentAttr.status))){
			this.selectStatus(eq.stringValue(EquipmentAttr.status));
		}
		if(StringUtil.notEmpty(eq.stringValue(EquipmentAttr.webVisible))){
			this.selectWebVisible(eq.stringValue(EquipmentAttr.webVisible));
		}
		if(StringUtil.notEmpty(eq.stringValue(EquipmentAttr.allGeneralPublicSales))){
			this.selectAllowGeneralPublicSales(eq.stringValue(EquipmentAttr.allGeneralPublicSales));
		}
		if(StringUtil.notEmpty(eq.stringValue(EquipmentAttr.checkOutLagTime))){
			this.setCheckOutLagTime(eq.stringValue(EquipmentAttr.checkOutLagTime));
		}
		if(StringUtil.notEmpty(eq.stringValue(EquipmentAttr.checkInLagTime))){
			this.setCheckInLagTime(eq.stringValue(EquipmentAttr.checkInLagTime));
		}
	}
	
	public void setUpEquipmentAndClickApply(Data<EquipmentAttr> eq){
		this.setEquipmentInfo(eq);
		this.clickApply();
		ajax.waitLoading();
	}
	
	public void setUpEquipmentAndClickOk(Data<EquipmentAttr> eq){
		this.setUpEquipmentAndClickApply(eq);
		this.clickOk();
	}
	
	public String getErrorMsg(){
		String errMsg = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".className", "message msgerror");
		if(objs.length > 0){
			errMsg = objs[0].text();
		}
		return errMsg;
	}
	
	public String getEquipmentId(){
		IHtmlObject[] objs = browser.getHtmlObject(this.idSpan());
		String id = objs[0].text().replace("ID", "");
		Browser.unregister(objs);
		return id;
	}
	
	public void clickEquipmentHoursTab() {
		browser.clickGuiObject(equipmentHoursTab());
	}
}
