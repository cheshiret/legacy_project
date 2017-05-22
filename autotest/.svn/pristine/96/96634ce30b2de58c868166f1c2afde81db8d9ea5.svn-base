package com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.util.KeyInput;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.TestProperty;


public class LotterySearchPage extends InvMgrCommonTopMenuPage {
	private static LotterySearchPage _instance = null;


	public static LotterySearchPage getInstance() {
		if (null == _instance) {
			_instance = new LotterySearchPage();
		}

		return _instance;
	}

	protected LotterySearchPage() {
	}

	public boolean exists() {
	  return browser.checkHtmlObjectExists(".id","LotterySearchCriteria.lotteryId");
	}
	
	/** Click on Facilities link. */
	public void clickFacilities() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Facilities");
	}
	
	public void setLotteryID(String lotteryID){
		browser.setTextField(".id", "LotterySearchCriteria.lotteryId", lotteryID);
	}
	
	public String setInvalidLotteryID(String lotteryId){
		browser.setTextField(".id", "LotterySearchCriteria.lotteryId", lotteryId);
		new Thread(){//to handle alert dialog popped up for check invalid report date
			ConfirmDialogPage alertPg = ConfirmDialogPage.getInstance();
			
			public void run(){
				int i=0;
				alertPg.setDismissible(false);
				while(i<2){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						logger.warn(e.getMessage());
					}
					if(alertPg.exists()){
						TestProperty.putProperty("msg", alertPg.text());
						alertPg.dismiss();
						break;
					}
					i++;
				}
			};
		}.start();
		browser.inputKey(KeyInput.get(KeyInput.TAB));
		String alertMsg = TestProperty.getProperty("msg",null);
		TestProperty.putProperty("msg","");//reset msg to null
		return alertMsg;
	}
	
	public void setLotteryName(String lotteryName){
		browser.setTextField(".id", "LotterySearchCriteria.lotteryName", lotteryName);
	}
	
	public void setCoverage(String coverage){
		browser.setTextField(".id","LotterySearchCriteria.coverage",coverage);
	}
	
	public void setRevenLocation(String revenue){
		browser.setTextField(".id", "LotterySearchCriteria.revenueLocation", revenue);
	}
	
	public void selectStatus(String status){
		browser.selectDropdownList(".id","LotterySearchCriteria.active",status);
	}
	
	public void selectBlankStatus(){
		browser.selectDropdownList(".id","LotterySearchCriteria.active",0);
	}
	
	public void selectProductCategory(String category){
		browser.selectDropdownList(".id", "LotterySearchCriteria.productCategory", category);
	}
	
	public void selectBlankProductCategory(){
		browser.selectDropdownList(".id", "LotterySearchCriteria.productCategory", 0);
	}
	
	public void selectProductGroup(String group){
		browser.selectDropdownList(".id", "LotterySearchCriteria.productGroup", group);
	}
	
	public void selectBlankProductGroup(){
		browser.selectDropdownList(".id", "LotterySearchCriteria.productGroup", 0);
	}
	
	public void selectPermitCategory(String category){
		browser.selectDropdownList(".id", "LotterySearchCriteria.permitCategory", category);
	}
	
	public void selectPermitType(String type){
		browser.selectDropdownList(".id", "LotterySearchCriteria.permitType", type);
	}
	
	/**
	 * Select page from drop down list
	 * @param item
	 */
	public void selectPageFromDropDownList(String item){
		browser.selectDropdownList(".id", "page_name", item);
	}
	
	public boolean isLotterySetup() {
		ISelect dropdown = (ISelect) browser.getHtmlObject(".class", "Html.SELECT", ".id", "page_name")[0];
		return "Lottery Setup".equalsIgnoreCase(dropdown.getSelectedText());
	}
	
	public void clickGo(){
		browser.clickGuiObject(".class","Html.A",".text", new RegularExpression("Go|Search", false));
	}
	
	public void clickAddNew(){
		browser.clickGuiObject(".class","Html.A",".text","Add New");
	}
	
	public void clickLotteryID(){
		browser.clickGuiObject(".class", "Html.A", ".id", "LotteryLightView.id", true, 0);
	}
	
	public void clickLotteryID(String id) {
		browser.clickGuiObject(".class", "Html.A", ".text", id, true);
	}
	
	public void selectLottery(){
		browser.selectCheckBox(".class","Html.INPUT.checkbox",".id", "LotteryLightView.ID", 0);
	}
	
	public void clickActive(){
		browser.clickGuiObject(".class","Html.A",".text","Activate");
	}
	
	public void clickDeactive(){
		browser.clickGuiObject(".class","Html.A",".text","Deactivate");
	}
	
	public String getMessage(){
		String message="";
		IHtmlObject[] objs=browser.getHtmlObject(".id",new RegularExpression("NOTSET|statusMessages",false));
		message=objs[0].text();
		Browser.unregister(objs);
		
		return message;
	}
	
	public List<String> getTableInfo(String coloumName){
		List<String> L=new ArrayList<String>();
		int colCount=0;
		IHtmlObject[] objs=browser.getTableTestObject(".id","lotteryList_LIST");
		if(objs.length < 0) {
			throw new ObjectNotFoundException("Can't find Lottery List table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		colCount = table.findColumn(0, new RegularExpression(coloumName,false));
		for(int i = 1; i < table.rowCount(); i++) {
			L.add(table.getCellValue(i, colCount));
		}
		Browser.unregister(objs);
		return L;
	}
	
	public List<String> getLotteryCoverage(){
		return getTableInfo("Lottery Coverage");
	}
	
	public String getErrorMessage(){
		String error="";
		IHtmlObject[] objs=browser.getHtmlObject(".id","error.lottery.search");
		error=objs[0].text();
		
		Browser.unregister(objs);
		return error;
	}
	
	public void clickNext(){
		browser.clickGuiObject(".class","Html.A",".text","Next");
	}
	
	public boolean nextButtonIsExists(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Next");
	}

	/**
	 * Check lottery record exists or not identified by its id or name
	 * @param idOrName
	 * @return
	 */
	public boolean checkLotteryExists(String idOrName) {
		boolean exists = false;
		if(idOrName.matches("[0-9]*")) {
			exists = browser.checkHtmlObjectExists(".class", "Html.A", ".text", idOrName);
		} else {
			List<String> namesList = getTableInfo("Lottery Name");
			for(int i = 0; i < namesList.size(); i ++) {
				if(namesList.get(i).equals(idOrName)) {
					exists = true;
					break;
				}
			}
		}
		
		return exists;
	}
	
	/**
	 * Search lottery by lottery name
	 * @param lotteryName
	 */
	public void searchLotterByName(String lotteryName){
		this.setLotteryName(lotteryName);
		this.clickGo();
		this.waitLoading();
	}

	public void waitSearchResult(String id) {
		browser.searchObjectWaitExists(".class", "Html.A", ".text",  id);
	}
	
	private IHtmlObject[] getLotteryTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "lotteryList_LIST");
		if(objs.length<1){
			throw new ErrorOnPageException("No table element exist");
		}
		return objs;
	}
	/**
	 * select lottery check box.
	 * @param index
	 */
	public void selectLotteryCheckBox(int index){
		browser.selectCheckBox(".id", "LotteryLightView.ID", index);
	}
	
	public void selectLotteryCheckBox(String lotteryId){
		IHtmlTable table = (IHtmlTable)this.getLotteryTable()[0];
		for(int i=0;i<table.rowCount();i++){
			if(table.getRowValues(i).get(1).equals(lotteryId)){
				this.selectLotteryCheckBox(i - 1);
			}
		}
	}
	
	/**
	 * activate lottery.
	 * @param lotteryId
	 */
	public void activateLottery(String lotteryId){
		this.selectLotteryCheckBox(lotteryId);
		this.clickActive();
		ajax.waitLoading();
		this.waitLoading();
	}
	/**
	 * deactivate lottery
	 * @param lotteryId
	 */
	public void deactiveLottery(String lotteryId){
		this.selectLotteryCheckBox(lotteryId);
		this.clickDeactive();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void clickColumnHeading(String columnName){
		browser.clickGuiObject(".class", "Html.A", ".text", columnName);
	}
	
	public List<String> getLotteryColumnValues(String columnName){
		IHtmlObject[] objs = this.getLotteryTable();
		IHtmlTable table =(IHtmlTable)objs[0];
		
		int column = table.findColumn(0, columnName);
		List<String> columnValues = table.getColumnValues(column);
		columnValues.remove(0);
		
		Browser.unregister(objs);
		return columnValues;
	}
	
	public List<String> getLotteryIDColumnValues(){
		return this.getLotteryColumnValues("Lottery ID");
	}
	/**
	 * get lottery id by name.
	 * @param lotteryName
	 * @return
	 */
	public String getLotteryIdByName(String lotteryName){
		IHtmlTable table =(IHtmlTable)this.getLotteryTable()[0];
		String id ="";
		for(int i=1;i<table.rowCount();i++){
			if(table.getCellValue(i, 2).equals(lotteryName)){
				id = table.getCellValue(i, 1);
			}
		
		}
		return id;
	}
}
