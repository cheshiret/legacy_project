package com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery;



import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Lottery;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LotteryAssignNewParticipationPage extends InvMgrCommonTopMenuPage{
	
	private static LotteryAssignNewParticipationPage _instance = null;


	public static LotteryAssignNewParticipationPage getInstance() {
		if (null == _instance) {
			_instance = new LotteryAssignNewParticipationPage();
		}

		return _instance;
	}

	protected LotteryAssignNewParticipationPage() {
	}

	public boolean exists() {
	    return browser.checkHtmlObjectExists(".id","FacilityID");
	}
	
	public void selectFacility(String facility){
		browser.selectDropdownList(".id", "FacilityID", facility);
	}
	
	public boolean isFacilityDropdownListExists(){
		return browser.checkHtmlObjectExists(".id", "FacilityID");
	}
	
	public void selectAgency(String agency){
		browser.selectDropdownList(".id", "AgencyID", agency);
	}
	
	public boolean isAgencyDropdownListExists(){
		return browser.checkHtmlObjectExists(".id", "AgencyID");
	}
	
	public void selectRegion(String region){
		browser.selectDropdownList(".id", "RegionID", region);
	}
	
	public boolean isRegionDropdownListExists(){
		return browser.checkHtmlObjectExists(".id", "RegionID");
	}
		
	public boolean isAreaDropdownListExists() {
		return browser.checkHtmlObjectExists(".id", "AreaID");
	}
	
	public void selectArea(String area){  //It is the same drop down list for dock of participation to marina lottery
 		browser.selectDropdownList(".id", "AreaID", area);
 		ajax.waitLoading();
		waitLoading();
	}
	
	public void selectProductGroup(String group){
		browser.selectDropdownList(".id", "product_group", group,1);
		ajax.waitLoading();
		waitLoading();
	}
	
	public void selectParticiptingProduct(String product){
		browser.selectDropdownList(".id", "product", product, 1);//1 hide same object exists
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class","Html.A",".text","OK");
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class","Html.A",".text","Cancel");
	}
	
	public void setUpParticipationInfo(Lottery lottery){
		if (!StringUtil.isEmpty(lottery.agency)) {
			this.selectAgency(lottery.agency);
		}
		
		if (!StringUtil.isEmpty(lottery.Region)) {
			this.selectRegion(lottery.Region);
			ajax.waitLoading();
			this.waitLoading();
		}
		
		if (!StringUtil.isEmpty(lottery.facility)) {
			this.selectFacility(lottery.facility);
			ajax.waitLoading();
			this.waitLoading();
		}

		if (!StringUtil.isEmpty(lottery.area) && this.isAreaDropdownListExists()) {
			this.selectArea(lottery.area);
		}
		
		if (!StringUtil.isEmpty(lottery.productGroup)) {
			this.selectProductGroup(lottery.productGroup);
		}

		if (!StringUtil.isEmpty(lottery.products)) {
			this.selectParticiptingProduct(lottery.products);
		}
	}

	public List<String> getProductFromDropDownlist() {
		IHtmlObject[] objs = browser.getTableTestObject(".id", "products");
		IHtmlObject[] tbody = browser.getHtmlObject(".class", "Html.TBODY", objs[0]);
		Property[] properties = new Property[1];
		properties[0] = new Property(".id", "product");
		Browser.unregister(objs);
		Browser.unregister(tbody);
		return browser.getDropdownElements(properties, tbody[1]);
	}
	
	public String getProductDropdownListSelectedValue() {
		return browser.getDropdownListValue(".id", "product", 1);
	}
	
	public List<String> getProductGroups(){
		IHtmlObject[] objs = browser.getDropdownList(".id", "product_group");
		if(objs.length<2){
			throw new ItemNotFoundException("Did not found product group object.");
		}
		List<String> productGroupsList = new ArrayList<String>();
		for(int i= 1; i<objs.length; i++){
			String productGroup=((ISelect)objs[i]).getSelectedText();
			productGroupsList.add(productGroup);
		}
		
		Browser.unregister(objs);
		return productGroupsList;
	}
	
	public String getParticipantProduct(){
		IHtmlObject[] objs = browser.getDropdownList(".id", "product");

		if(objs.length<2){
			throw new ItemNotFoundException("Did not found participant product object.");
		}
		List<String> participantPrdList = ((ISelect)objs[1]).getAllOptions();
		
		Browser.unregister(objs);
		return participantPrdList.get(0);
	}
	
	public void selectProductGroup(String group, int index){
		browser.selectDropdownList(".id", "product_group", group,index+1);
	}
	
	public void selectParticiptingProduct(String product, int index){
		browser.selectDropdownList(".id", "product", product, index+1);//1 hide same object exists
	}
	
	public String getDock(){
		return browser.getDropdownListValue(".id", "AreaID");
	}
	
	public List<String> getDockElements(){
		return browser.getDropdownElements(".id", "AreaID");
	}
	
	private IHtmlObject[] getPrdGroupTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "product_groups");
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found the product group table object.");
		}
		return objs;
	}
	
	public boolean checkAddPrdGroupIsDisabled(){
		IHtmlObject[] objs = this.getPrdGroupTable();
		
		IHtmlObject[] addObjs = browser.getHtmlObject(".class", "Html.A", ".text", "Add", objs[0]);
		boolean isDisabled;
		if(addObjs.length<1){
			isDisabled = true;
		}else{
			isDisabled = false;
		}
		
		Browser.unregister(addObjs);
		Browser.unregister(objs);
		return isDisabled;
	}
	
	private IHtmlObject[] getParticipantTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "products");
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found the Participant Product table object.");
		}
		return objs;
	}
	
	public boolean checkAddParticipantPrdIsDisabled(){
		IHtmlObject[] objs = this.getParticipantTable();
		
		IHtmlObject[] addObjs = browser.getHtmlObject(".class", "Html.A", ".text", "Add", objs[0]);
		boolean isDisabled;
		if(addObjs.length<1){
			isDisabled = true;
		}else{
			isDisabled = false;
		}
		
		Browser.unregister(addObjs);
		Browser.unregister(objs);
		return isDisabled;
	}
	
	public void clickAddParticipantPrd(){
		IHtmlObject[] objs = this.getParticipantTable();
		browser.clickGuiObject(".class", "Html.A", ".text", "Add", true, 0, objs[0]);
		Browser.unregister(objs);
	}
	
	public void clickRemoveParticipantPrd(int index){
		IHtmlObject[] objs = this.getParticipantTable();
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove", true, index+1, objs[0]);
		Browser.unregister(objs);
	}
	
	public int getParticipantPrdDropdownListObjectLength(){
		IHtmlObject[] objs = this.getParticipantTable();
		IHtmlObject[] partcipantPrdObjs = browser.getDropdownList(Property.toPropertyArray(".id", "product"), objs[0]);
		int length = partcipantPrdObjs.length;
		
		Browser.unregister(objs);
		Browser.unregister(partcipantPrdObjs);
		return length;
	}
	
	public int getParticipantPrdRemoveObjectLength(){
		IHtmlObject[] objs = this.getParticipantTable();
		IHtmlObject[] removeObjs = browser.getHtmlObject(".class", "Html.A", ".text", "Remove", objs[0]);
		int length = removeObjs.length;
		
		Browser.unregister(objs);
		Browser.unregister(removeObjs);
		return length;
	}
	
	public String getErrorMessage(){
		String error = "";
		IHtmlObject[] objs = browser.getHtmlObject(".classname", "message msgerror");
		error = objs[0].text();
		Browser.unregister(objs);
		return error;
	}
}
