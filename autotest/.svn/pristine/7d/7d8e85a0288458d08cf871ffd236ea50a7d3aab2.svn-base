package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.FacilityPrdAttr;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jan 7, 2014
 */
public class LicMgrFacilityProductPage extends LicMgrFacilityDetailsPage{
	static class SingletonHolder {
		protected static LicMgrFacilityProductPage _instance = new LicMgrFacilityProductPage();
	}

	protected LicMgrFacilityProductPage() {
	}

	public static LicMgrFacilityProductPage getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] facilityProductPageMark(){
		return Property.concatPropertyArray(table(), ".id", "facilityProductResultGrid_LIST");
	}

	protected Property[] status(){
		return Property.toPropertyArray(".id", new RegularExpression("FacilityProductSearchCriteria-\\d+.productStatus", false));
	}

	protected Property[] facilityPrdName(){
		return Property.toPropertyArray(".id", new RegularExpression("FacilityProductSearchCriteria-\\d+.facilityProductName", false));
	}

	protected Property[] facilityPrdType(){
		return Property.toPropertyArray(".id", new RegularExpression("FacilityProductSearchCriteria-\\d+.productGroupLightView", false));
	}

	protected Property[] addFacilityPrd(){
		return Property.concatPropertyArray(a(), ".text", "Add Facility Product");
	}

	protected Property[] go(){
		return Property.concatPropertyArray(a(), ".text", "Go");
	}

	protected Property[] prdResultGridListRowTR(String prdCode, String prdName){
		return Property.concatPropertyArray(tr(), ".className", "oddRow", ".text", new RegularExpression("^"+prdCode+"\\s+"+prdName+".*", false));
	}

	protected Property[] prdChangeHistory(){
		return Property.concatPropertyArray(a(), ".text", "Change History");
	}

	protected Property[] linkInPrdListPg(String text){
		return Property.concatPropertyArray(a(), ".text", text);
	}
	/** Page Object Property Definition END */

	public boolean exists() {
		return browser.checkHtmlObjectExists(facilityProductPageMark());
	}

	public void clickAddFacilityProductButton(){
		browser.clickGuiObject(addFacilityPrd());
	}

	public void selectStatus(String status){
		browser.selectDropdownList(status(), status);
	}

	public List<String> getPrdStatus(){
		return browser.getDropdownElements(status());
	}
	
	public void setFacilityPrdName(String prdName){
		browser.setTextField(facilityPrdName(), prdName);
	}

	public String getFacilityPrdName(){
		return browser.getTextFieldValue(facilityPrdName());
	}
	
	public void selectFacilityPrdType(String prdType){
		browser.selectDropdownList(facilityPrdType(), prdType);
	}

	public void selectFacilityPrdType(int index){
		browser.selectDropdownList(facilityPrdType(), index);
	}
	
	public List<String> getFacilityPrdTypes(){
		return browser.getDropdownElements(facilityPrdType());
	}
	
	public void clickGo(){
		browser.clickGuiObject(go());
	}

	public void clickPrdChangeHistoryButton(String prdCode, String prdName){
		browser.clickGuiObject(Property.atList(prdResultGridListRowTR(prdCode, prdName), prdChangeHistory()));
	}

	public boolean isPrdChangeHistoryButtonExisted(String prdCode, String prdName){
		return browser.checkHtmlObjectExists(Property.atList(prdResultGridListRowTR(prdCode, prdName), prdChangeHistory()));
	}

	public void verifyAddFacilityProductButtonExisted(boolean existed, String prdCode, String prdName){
		boolean fromUI = isPrdChangeHistoryButtonExisted(prdCode, prdName);
		if(existed!=fromUI){
			throw new ErrorOnPageException("Change History button should "+(existed?"":"not ")+"exist.");
		}
		logger.info("Successfully verify Change History button "+(existed?"":"displays ")+"doesn't exist.");
	}
	
	public void clickPrdCode(String prdCode, String prdName){
		browser.clickGuiObject(Property.atList(prdResultGridListRowTR(prdCode, prdName), linkInPrdListPg(prdCode)));
	}

	public boolean isAddFacilityButtonExisted(){
		return browser.checkHtmlObjectExists(addFacilityPrd());
	}

	public void verifyAddFacilityProductButtonExisted(boolean existed){
		boolean fromUI = isAddFacilityButtonExisted();
		if(existed!=fromUI){
			throw new ErrorOnPageException("Add Facility product button should "+(existed?"":"not ")+"exist.");
		}
		logger.info("Successfully verify Add Facility product button "+(existed?"":"displays ")+"doesn't exist.");
	}

	public void searchFacilityPrd(Data<FacilityPrdAttr> fpd){
		selectStatus(fpd.stringValue(FacilityPrdAttr.prdStatus));
		setFacilityPrdName(fpd.stringValue(FacilityPrdAttr.prdName));
		if(StringUtil.isEmpty(fpd.stringValue(FacilityPrdAttr.prdType))){
			selectFacilityPrdType(0);
		}else selectFacilityPrdType(fpd.stringValue(FacilityPrdAttr.prdType));
		
		clickGo();
		ajax.waitLoading();
		waitLoading();
	}

	public void searchFacilityPrdWithPrdName(String prdName){
		setFacilityPrdName(prdName);
		clickGo();
		ajax.waitLoading();
		waitLoading();
	}
	
	public IHtmlTable getFacilityPrdListTable(){
		IHtmlObject[] objs = browser.getTableTestObject(facilityProductPageMark());
		IHtmlTable table = null;
		if(objs.length>0){
			table = (IHtmlTable) objs[0];	
		}else throw new ObjectNotFoundException("Can't find activity facility product result grid list objects.");

		return table;
	}

	public int getNumOfFacilityPrd(){
		return getFacilityPrdListTable().rowCount()-1;
	}
	
	public void verifyNoFacilityPrdData(){
		if(getNumOfFacilityPrd()!=0){
			throw new ErrorOnPageException("Has faclity prd data in list page, should not have.");
		}
		logger.info("Successfully verify no facility prd data in list page.");
	}

	public List<Data<FacilityPrdAttr>> getFacilityPrdData() {
		List<Data<FacilityPrdAttr>> list = new ArrayList<Data<FacilityPrdAttr>>();
		IHtmlTable table = getFacilityPrdListTable();
		for (int i = 1; i < table.rowCount(); i++) {
			Data<FacilityPrdAttr> fpd = new Data<FacilityPrdAttr>();
			fpd.put(FacilityPrdAttr.prdCode, table.getCellValue(i, 0).trim());
			fpd.put(FacilityPrdAttr.prdName, table.getCellValue(i, 1).trim());
			fpd.put(FacilityPrdAttr.prdType, table.getCellValue(i, 2).trim());
			fpd.put(FacilityPrdAttr.capacity, table.getCellValue(i, 3).trim());
			fpd.put(FacilityPrdAttr.prdStatus, table.getCellValue(i, 4).trim());
			list.add(fpd);
		}
		Browser.unregister(table);
		return list;
	}

	public void verifyFacilityPrdInListPg(List<Data<FacilityPrdAttr>> facilityPrds) {
		List<Data<FacilityPrdAttr>> fpds = this.getFacilityPrdData();
		boolean result = MiscFunctions.compareResult("Size of list", facilityPrds.size(), fpds.size());
		if(!result){
			throw new ErrorOnPageException("The size of list is different.");
		}else{
			for(int i=0; i<facilityPrds.size(); i++){
				result &= MiscFunctions.compareResult(i+"-Facility Prd Code", facilityPrds.get(i).get(FacilityPrdAttr.prdCode), fpds.get(i).get(FacilityPrdAttr.prdCode));
				result &= MiscFunctions.compareResult(i+"-Facility Prd Name", facilityPrds.get(i).get(FacilityPrdAttr.prdName), fpds.get(i).get(FacilityPrdAttr.prdName));
				result &= MiscFunctions.compareResult(i+"-Facility Prd Type", facilityPrds.get(i).get(FacilityPrdAttr.prdType), fpds.get(i).get(FacilityPrdAttr.prdType));
				result &= MiscFunctions.compareResult(i+"-Capacity", facilityPrds.get(i).get(FacilityPrdAttr.capacity), fpds.get(i).get(FacilityPrdAttr.capacity));
				result &= MiscFunctions.compareResult(i+"-Status", facilityPrds.get(i).get(FacilityPrdAttr.prdStatus), fpds.get(i).get(FacilityPrdAttr.prdStatus));
			}
			if(!result){
				throw new ErrorOnPageException("Not all check points are passed in facility prd list page. Please check the details from previous logs.");
			}
			logger.info("All check points are passed in faclity list page.");
		}
	}
	
	public void verifyInitialPrdSearchPanel(List<String> expectedPrdTypes){
		boolean result = MiscFunctions.compareResult("Status DDL", Arrays.asList(new String[]{"Active", "Inactive"}).toString(), getPrdStatus().toString());
		result &= MiscFunctions.compareResult("Prd Name", StringUtil.EMPTY, getFacilityPrdName());
		result &= MiscFunctions.compareResult("Res Types", expectedPrdTypes.toString(), getFacilityPrdTypes().toString());
		if(!result){
			throw new ErrorOnPageException("Not all check points are passed in facility prd search panel. Please check the details from previous logs.");
		}
		logger.info("All check points are passed in facility prd search panel.");
	}
}
