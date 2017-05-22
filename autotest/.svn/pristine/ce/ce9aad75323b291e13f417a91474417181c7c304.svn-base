package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.TitleInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienCompanyDetailsInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrVehicleTitleListPage extends LicMgrVehicleDetailPage{
	
	private static LicMgrVehicleTitleListPage _instance = null;
	
	protected LicMgrVehicleTitleListPage(){
		
	}
	
	public static LicMgrVehicleTitleListPage getInstance(){
		if(_instance == null){
			_instance = new LicMgrVehicleTitleListPage();
		}
		return _instance;
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(".class","Html.TABLE",".id",new RegularExpression("grid_\\d+",false));
	}
	
	public List<TitleInfo> getVehicleTitles(){
		IHtmlObject[] objs = browser.getTableTestObject(".text",new RegularExpression("^Title # Title ID Status.*",false));
		IHtmlTable grid = (IHtmlTable)objs[objs.length-1];

		TitleInfo title;
		List<TitleInfo> titles = new ArrayList<TitleInfo>();
		
		for(int i=1;i<grid.rowCount();i++){
			title = new TitleInfo();
			title.titleNum = grid.getCellValue(i, 0);
			title.titleId = grid.getCellValue(i, 1);
			title.status = grid.getCellValue(i, 2);
			title.customer = grid.getCellValue(i, 3);
			title.product = grid.getCellValue(i, 4);
			title.activeLiens = grid.getCellValue(i, 5);
			title.numOfDuplicates = grid.getCellValue(i, 6);
			title.numOfCorrections = grid.getCellValue(i, 7);
			titles.add(title);
		}
		Browser.unregister(objs);
		return titles;
	}
	
	public IHtmlObject[] getVehicleTitleListTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".id",new RegularExpression("grid_\\d+",false));
		if(objs.length<1){
			throw new ErrorOnDataException("No tilte info exist");
		}
		return objs;
	}

	/**
	 * get active title Item Id.
	 * @return
	 */
	public String getActiveTitleItemId(){
		String titleID = "";
		IHtmlObject[] objs = getVehicleTitleListTable();
		IHtmlTable grid = (IHtmlTable)objs[objs.length-1];
		for(int i=0;i<grid.rowCount();i++){
			if(grid.getRowValues(i).get(3).equals("Active")){
				titleID = grid.getRowValues(i).get(2);
				break;
			}
		}
		Browser.unregister(objs);
		return titleID;
	}
	/**
	 * click TitleID
	 * @param id
	 */
	public void clickTitleId(String id){
		browser.clickGuiObject(".class", "Html.A", ".text", id);
	}
	/**
	 * Select Title Item check box.
	 * @param index
	 */
	public void selectTitleItemRadioBox(int index){
		browser.selectRadioButton(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems", false), index);
	}
	
	public void selectTitleItemRadioBox(String titleId){
		browser.selectRadioButton(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems", false),".value",titleId, 0);
	}
	
	/**
	 * click transferable title.
	 */
	public void clickSetToTransferableTitle(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Set to Transferrable");
	}
	/**
	 * click Surrender title
	 */
	public void clickSurrenderTitle(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Surrender Title");
	}
	/**
	 * click reactive title.
	 */
	public void clickReactiveTitle(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Reactivate Title");
	}
	/**
	 * set title to transferable title.
	 */
	public void transferableTitle(String titleId){
		this.selectTitleItemRadioBox(titleId);
		this.clickSetToTransferableTitle();
		ajax.waitLoading();
		this.waitLoading();
	}
	/**
	 * select tilte
	 * @param titleId
	 */
	
//	public void selectTitleItmeRadioById(String titleId){
//		HtmlObject[] objs = getVehicleTitleListTable();
//		ITable table = (ITable)objs[0];
//		int row=table.findRow(2, titleId);
//		this.selectTitleItemRadioBox(row-1);
//		
//		Browser.unregister(objs);
//	}
	
	/**
	 * select title item using product name
	 * @param name
	 */
	public void selectTitleItmeRadioByPrdName(String name){
		IHtmlObject[] objs = getVehicleTitleListTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		int row=table.findRow(5, name);
		this.selectTitleItemRadioBox(row-1);
		
		Browser.unregister(objs);
	}
	
	/**
	 * Compare title list info
	 * @param expected
	 * @return
	 */
	public boolean compareTitleListInfo(TitleInfo expected) {
		TitleInfo actual = getTitleInfoByTitleNum(expected.titleNum);
		
		boolean result = true;
		result &= MiscFunctions.compareResult("Title Num", expected.titleNum, actual.titleNum);
		result &= MiscFunctions.compareResult("Title ID", expected.titleId, actual.titleId);
		result &= MiscFunctions.compareResult("Title Status", expected.status, actual.status);
		result &= MiscFunctions.compareResult("Customer Name", expected.customer, actual.customer);
		result &= MiscFunctions.compareResult("Product", expected.product, actual.product);
		//result &= MiscFunctions.compareResult("Active Lien - Lien ID", expected.lienInfo.getLienId(), actual.lienInfo.getLienId());
		result &= MiscFunctions.compareResult("Active Lien - Lien Company Name", expected.lienInfo.getLienCompanyDetailsInfo().lienCompanyName, actual.lienInfo.getLienCompanyDetailsInfo().lienCompanyName);
		result &= MiscFunctions.compareResult("Num of Duplicates", expected.numOfDuplicates, actual.numOfDuplicates);
		result &= MiscFunctions.compareResult("Num of Corrections", expected.numOfCorrections, actual.numOfCorrections);
		
		return result;
	}
	
	/**
	 * get title list info in vehicle-title sub page, identified by product(code - name)
	 * @param prdName
	 * @return
	 */
	public TitleInfo getTitleInfoByProductName(String prdName) {
		TitleInfo title = new TitleInfo();
		title.titleNum = getTitleItemNumByPrdName(prdName);
		title.titleId = getTitleItemIdByPrdName(prdName);
		title.status = getTitleItemStatusByPrdName(prdName);
		title.customer = getTitleItemCustNameByPrdName(prdName);
		title.product = prdName;
		String lien = getTitleItemActiveLiensByPrdName(prdName);
		if(lien.length() > 0) {
			String lienInfo[] = getTitleItemActiveLiensByPrdName(prdName).split(",");
			LienCompanyDetailsInfo comInfo = new LienCompanyDetailsInfo();
			title.lienInfo.setLienId(lienInfo[0].trim());
			comInfo.lienCompanyName = lienInfo[1].trim();
			title.lienInfo.setLienCompanyDetailsInfo(comInfo);
		}
		title.numOfDuplicates = getTitleItemDuplicateCountsByPrdName(prdName);
		title.numOfCorrections = getTitleItemCorrectCountsByPrdName(prdName);
		
		return title;
	}
	
	/**
	 * get title info identified by title number
	 * @param titleNum
	 * @return
	 */
	public TitleInfo getTitleInfoByTitleNum(String titleNum) {
		TitleInfo title = new TitleInfo();
		title.titleNum = titleNum;
		title.titleId = getTitleItemIdByTitleNum(titleNum);
		title.status = getTitleItemStatusByTitleNum(titleNum);
		title.customer = getTitleItemCustNameByTitleNum(titleNum);
		title.product = getTitleItemProductNameByTitleNum(titleNum);
		String lien = getTitleItemActiveLiensByTitleNum(titleNum);
		if(lien.length() > 0) {
			String lienInfo[] = lien.split(",");
			LienCompanyDetailsInfo comInfo = new LienCompanyDetailsInfo();
			title.lienInfo.setLienId(lienInfo[0].trim());
			comInfo.lienCompanyName = lienInfo[1].trim();
			title.lienInfo.setLienCompanyDetailsInfo(comInfo);
		}
		title.numOfDuplicates = getTitleItemDuplicateCountsByTitleNum(titleNum);
		title.numOfCorrections = getTitleItemCorrectCountsByTitleNum(titleNum);
		
		return title;
	}
	
	public String getTitleItemNumByPrdName(String name){
		return getCellValueByPrdName(name, 1);
	}
	
	public String getTitleItemIdByPrdName(String name){
		return getCellValueByPrdName(name, 2);
	}
	
	public String getTitleItemStatusByPrdName(String name){
		return getCellValueByPrdName(name, 3);
	}
	
	public String getTitleItemCustNameByPrdName(String name) {
		return getCellValueByPrdName(name, 4);
	}
	
	public String getTitleItemActiveLiensByPrdName(String name) {
		return getCellValueByPrdName(name, 6);
	}
	
	public String getTitleItemDuplicateCountsByPrdName(String name){
		return getCellValueByPrdName(name, 7);
	}
	
	public String getTitleItemCorrectCountsByPrdName(String name){
		return getCellValueByPrdName(name, 8);
	}
	
	
	public String getTitleItemIdByTitleNum(String titleNum){
		return getCellValueByTitleNum(titleNum, 2);
	}
	
	public String getTitleItemStatusByTitleNum(String titleNum){
		return getCellValueByTitleNum(titleNum, 3);
	}
	
	public String getTitleItemCustNameByTitleNum(String titleNum) {
		return getCellValueByTitleNum(titleNum, 4);
	}
	
	public String getTitleItemProductNameByTitleNum(String titleNum) {
		return getCellValueByTitleNum(titleNum, 5);
	}
	
	public String getTitleItemActiveLiensByTitleNum(String titleNum) {
		return getCellValueByTitleNum(titleNum, 6);
	}
	
	public String getTitleItemDuplicateCountsByTitleNum(String titleNum) {
		return getCellValueByTitleNum(titleNum, 7);
	}
	
	public String getTitleItemCorrectCountsByTitleNum(String titleNum) {
		return getCellValueByTitleNum(titleNum, 8);
	}
	
	protected String getCellValueByPrdName(String prd_name,int column){
		return getCellValueByGivenRowValue(5, prd_name, column);
	}
	
	protected String getCellValueByTitleNum(String titleNum,int column){
		return getCellValueByGivenRowValue(1, titleNum, column);
	}
	
	/**
	 * 
	 * @param col_num-the column number for the value you provide
	 * @param value
	 * @param column-the column number for the cell you want 
	 * @return
	 */
	protected String getCellValueByGivenRowValue(int col_num,String value,int column){
		IHtmlObject[] objs = getVehicleTitleListTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		int row=table.findRow(col_num, value);
		String cellValue=table.getCellValue(row, column);
		
		Browser.unregister(objs);
		
		return cellValue;
	}
	
	/**
	 * reactivate title.
	 */
	public void reactivateTitle(String titleId){
		this.selectTitleItemRadioBox(titleId);
		this.clickReactiveTitle();
		ajax.waitLoading();
		this.waitLoading();
	}
	/**
	 * surrender title.
	 */
	public void surrenderTitle(String titleId){
		this.selectTitleItemRadioBox(titleId);
		this.clickSurrenderTitle();
		ajax.waitLoading();
		this.waitLoading();
	}
	/**
	 * select search status check box.
	 */
	public void selectSearchStatusCheckbox(int index){
		browser.selectCheckBox(".id", new RegularExpression("VehicleTitlesSearchCriteria-\\d+\\.vehicleRTIStatuseIds_\\d+",false),index);
	}
	/**
	 * select active status.
	 */
	public void selectActiveStautsCheckBox(){
		this.selectSearchStatusCheckbox(0);
	}
	/**
	 * Select transferable status.
	 */
	public void selectTransferableStatusCheckBox(){
	    this.selectSearchStatusCheckbox(1);
	}
	/**
	 * select surrendered.
	 */
	public void selectSurrenderedStatusCheckBox(){
		 this.selectSearchStatusCheckbox(2);
	}
	/**
	 * unSelect active.
	 */
	public void unselectActiveStautsCheckBox(){
		this.unSelectSearchStatusCheckbox(0);
	}
	/**
	 * unSelect transferable status check box.
	 */
	public void unselectTransferableStatusCheckBox(){
	    this.unSelectSearchStatusCheckbox(1);
	}
	/**
	 * unselect surrendered.
	 */
	public void unselectSurrenderedStatusCheckBox(){
		 this.unSelectSearchStatusCheckbox(2);
	}
	/**
	 * unselect transferred status check box.
	 */
	public void unselectTransferredStautsCheckBox(){
		this.unSelectSearchStatusCheckbox(3);
	}
	/**
	 * unselect reverse status check box.
	 */
	public void unselectReverseStatusCheckBox(){
		this.unSelectSearchStatusCheckbox(4);
	}
	/**
	 * unselect search status check box.
	 */
	public void unSelectSearchStatusCheckbox(int index){
		browser.unSelectCheckBox(".id", new RegularExpression("VehicleTitlesSearchCriteria-\\d+\\.vehicleRTIStatuseIds_\\d+",false),index);
	}
	
	/**
	 * click go button.
	 */
	public void clickGobutton(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
	/**
	 * show all the list item
	 */
	public void showAll(){
		this.unselectActiveStautsCheckBox();
		this.unselectTransferableStatusCheckBox();
		this.unselectSurrenderedStatusCheckBox();
		this.unselectTransferredStautsCheckBox();
		this.unselectReverseStatusCheckBox();
		this.clickGobutton();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void showActiveTitles(){
		unselectTransferableStatusCheckBox();
		selectActiveStautsCheckBox();
		clickGobutton();
		ajax.waitLoading();
	}
	
}
