package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * This is the hunts list page in license manager, Admin(drop down list)-->Lotteries --- > Hunts
 * @author pchen
 * @Date Sep 20, 2012
 */
public class LicMgrHuntsListPage extends LicMgrLotteriesCommonPage{
	private static LicMgrHuntsListPage _instance = null;
	private static final int SPECIES_SUB_TYPE_COL=4;
	private static final int WEAPON_COL = 5;
	private static final int HUNT_LOCATION_COL = 6;
	private static final int DATE_PERIOD_COL = 7;
	/*private static final int SPECIES_SUB_TYPE_COL=5;
	private static final int WEAPON_COL = 6;
	private static final int HUNT_LOCATION_COL = 7;
	private static final int DATE_PERIOD_COL = 4;*/
	protected LicMgrHuntsListPage (){}
	
	public static LicMgrHuntsListPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrHuntsListPage();
		}
		
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE",".id","FilteredHuntsGroupByGrid");
	}
	
	public void clickAddHunt(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Hunt",true);
	}
	
	/**
	 * Select status from drop down list
	 * @param status
	 */
	public void selectStatus(String status){
		browser.selectDropdownList(".id", new RegularExpression("^HuntFilterCriteria-\\d+\\.status", false), status);
	}
	
	/**
	 * Select specie from drop down list
	 * @param specie
	 */
	public void selectSpecie(String specie){
		browser.selectDropdownList(".id", new RegularExpression("^HuntFilterCriteria-\\d+\\.species", false), specie);
	}
	
	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Go", true);
	}
	
	public String getHuntId(String huntCode){
		String huntId = "";
		IHtmlObject objs[] = browser.getTableTestObject(".id","FilteredHuntsGroupByGrid");
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find hunt list table object.");
		}
		IHtmlTable table = (IHtmlTable) objs[0];
		int rows = table.rowCount();
		String code = "";
		for(int i = 1; i < rows; i++ ){
			code = table.getCellValue(i, 2);
			if(StringUtil.notEmpty(code)){
				code = code.split(" - ")[0].trim();
				if(huntCode.equals(code)){
					huntId = table.getCellValue(i, 0);
					break;
				}
			}
		}
		Browser.unregister(objs);
		return huntId;
	}
	/**
	 * Click the code of hunt
	 * @param huntCode
	 */
	public void clickHuntIdAccoringToHuntCode(String huntCode){
		String huntId = this.getHuntId(huntCode);
		if(StringUtil.isEmpty(huntId)){
			throw new ItemNotFoundException(
					"Can't find the record of hunt whose code is:" + huntCode);
		}
	    browser.clickGuiObject(".class", "Html.A", ".text", huntId, true);
	}
	
	/**
	 * Click the id of hunt
	 * @param huntId
	 */
	public void clickHuntId(String huntId){
		browser.clickGuiObject(".class", "Html.A", ".text", huntId, true);
	}
	
	public boolean huntExist(String huntCode){
		boolean exist = false;
		String huntId = this.getHuntId(huntCode);
		if(StringUtil.notEmpty(huntId)){
			exist = true;
		}
		return exist;
	}
    /**
     * 	private static final int SPECIES_SUB_TYPE_COL=4;
	private static final int WEAPON_COL = 5;
	private static final int HUNT_LOCATION_COL = 6;
	private static final int DATE_PERIOD_COL = 7;
     * @param huntCode
     * @return
     */
	public HuntInfo gethuntInfoInList(String huntCode) {
		HuntInfo hunt = new HuntInfo();
		IHtmlObject objs[] = browser.getTableTestObject(".id","FilteredHuntsGroupByGrid");
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find hunt list table object.");
		}
		IHtmlTable table = (IHtmlTable) objs[0];
		int rows = table.rowCount();
		for(int i = 1; i < rows; i++ ){
			if(StringUtil.notEmpty(table.getCellValue(i, 2))){
				String code = table.getCellValue(i, 2).split("-")[0].trim();
				if(huntCode.equals(code)){
					hunt.setHuntStatus(table.getCellValue(i, 1));
					hunt.setHuntCode(table.getCellValue(i, 2).split("-")[0].trim());
					hunt.setDescription(table.getCellValue(i, 2).split("-")[1].trim());
					hunt.setSpecieSubType(table.getCellValue(i, SPECIES_SUB_TYPE_COL));
					hunt.setWeapon(table.getCellValue(i, WEAPON_COL));
					hunt.setHuntLocationInfo(table.getCellValue(i, HUNT_LOCATION_COL));
					hunt.setHuntDatePeriodInfo(table.getCellValue(i, DATE_PERIOD_COL));
					break;
				}
			}
		}
		Browser.unregister(objs);
		return hunt;
	}
	
	/**
	 * Verify the new added hunt information in hunt list 
	 */
	public boolean checkHuntInfoInList(HuntInfo hunt ) {
		boolean passed = true;
		HuntInfo huntInList = this.gethuntInfoInList(hunt.getHuntCode());
		passed &= MiscFunctions.compareResult("hunt Status:", hunt.getHuntStatus(), huntInList.getHuntStatus());
		passed &= MiscFunctions.compareResult("hunt Code:", hunt.getHuntCode(), huntInList.getHuntCode());
		passed &= MiscFunctions.compareResult("hunt Description:", hunt.getDescription(), huntInList.getDescription());
		passed &= MiscFunctions.compareResult("hunt SpecieSubType:", hunt.getSpecieSubType(), huntInList.getSpecieSubType());
		passed &= MiscFunctions.compareResult("hunt Weapon:", hunt.getWeapon(), huntInList.getWeapon());
		passed &= MiscFunctions.compareResult("hunt Location Info:", hunt.getHuntLocationInfo(), huntInList.getHuntLocationInfo());
		passed &= MiscFunctions.compareResult("hunt Date period:", hunt.getHuntDatePeriodInfo(), huntInList.getHuntDatePeriodInfo());
		return passed;
	}
	
	/**
	 * Get all the species on page
	 * @return
	 */
	public List<String> getAllSpeciesOnPage(){
		List<String> speciesList = new ArrayList<String>();
		IHtmlObject objs[] = browser.getTableTestObject(".id","FilteredHuntsGroupByGrid");
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find hunt list table object.");
		}
		IHtmlTable table = (IHtmlTable) objs[0];
		IHtmlObject[] trs=browser.getHtmlObject(".class", "Html.TR", ".className","label_title labelgroup   initialized parent", table);
		for(IHtmlObject tr:trs){
			speciesList.add(tr.text());
		}
		Browser.unregister(trs);
		Browser.unregister(objs);
		return speciesList;
	}
	
	/**
	 * Get hunts under the given species
	 * @param species
	 * @return
	 */
	public List<String> getAllHuntOfSpecies(String species){
		List<String> huntList = new ArrayList<String>();
		IHtmlObject objs[] = browser.getTableTestObject(".id","FilteredHuntsGroupByGrid");
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find hunt list table object.");
		}
		IHtmlTable table = (IHtmlTable) objs[0];
		IHtmlObject[] trs=browser.getHtmlObject(".class", "Html.TR", table);
		int specieRowNum = table.findRow(0, species);
		int childIndex = specieRowNum - 1;
		String className = "child-of-FilteredHuntsGroupByGrid_row_" + childIndex + " initialized";
		for(int i = 0; i< table.rowCount(); i++){
			if(trs[i].getProperty(".className").replace("oddrow", "").trim().equals(className)){
				huntList.add(table.getCellValue(i, 2));
			}
		}
		Browser.unregister(trs);
		Browser.unregister(objs);
		return huntList;
	}
	/**
	 * Get all the number of rows for the hunt list table
	 * @return
	 */
	public int getRowNumOfHuntListTable(){
		IHtmlObject objs[] = browser.getTableTestObject(".id","FilteredHuntsGroupByGrid");
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find hunt list table object.");
		}
		IHtmlTable table = (IHtmlTable) objs[0];
		int rowNum = table.rowCount();
		return rowNum;
	}
	
	/**
	 * Set up filter info
	 * @param status
	 * @param species
	 */
	public void setFilter(String status, String species){
		if(StringUtil.notEmpty(status)){
			this.selectStatus(status);
		}else{
			this.selectStatus();
		}
		if(StringUtil.notEmpty(species)){
			this.selectSpecie(species);
		}else{
			this.selectSpecie();
		}
	}
	
	public void selectStatus(){
		browser.selectDropdownList(".id", new RegularExpression("^HuntFilterCriteria-\\d+\\.status",false), 0);	
	}
	
	public void selectSpecie(){
		browser.selectDropdownList(".id", new RegularExpression("^HuntFilterCriteria-\\d+\\.species",false), 0);
	}
		
	/**
	 * get all the values in one column on the page.
	 * @param
	 * @return List of records.
	 */
	public List<String> getRecordsSpecialColumnValueOnPage(int col) {
		IHtmlObject objs[] = browser.getTableTestObject(".id","FilteredHuntsGroupByGrid");
		List<String> values = new ArrayList<String>();
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find hunt list table object.");
		}
		IHtmlTable table = (IHtmlTable) objs[0];
		int rowNum = table.rowCount();
		for(int i=0; i<rowNum; i++){
			if(StringUtil.notEmpty(table.getCellValue(i, 2))){
				values.add(table.getCellValue(i, col));
			}
		}
        Browser.unregister(objs);
		return values;
	}
	
	public List<String> getHuntColumn(){
		return this.getRecordsSpecialColumnValueOnPage(2);
	}
	
	public List<String> getStatusColumn(){
		return this.getRecordsSpecialColumnValueOnPage(1);
	}
}
