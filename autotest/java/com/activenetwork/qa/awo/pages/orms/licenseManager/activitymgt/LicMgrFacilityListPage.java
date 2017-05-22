package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.SearchFacilityAttr;
import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrActivityMGTCommonPage;
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
public class LicMgrFacilityListPage extends LicMgrActivityMGTCommonPage {
	static class SingletonHolder {
		protected static LicMgrFacilityListPage _instance = new LicMgrFacilityListPage();
	}

	protected LicMgrFacilityListPage() {
	}

	public static LicMgrFacilityListPage getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] activityFacilityResultGrid(){
		return Property.concatPropertyArray(div(), ".id", "activityFacilityResultGrid");
	}

	protected Property[] activityFacilityResultGridList(){
		return Property.concatPropertyArray(table(), ".id", "activityFacilityResultGrid_LIST");
	}

	protected Property[] linkCommonParentDIV(){
		return Property.concatPropertyArray(div(), ".className", "link link blockinline rubyspacing");
	}

	protected Property[] addFacilityButton(){
		return Property.concatPropertyArray(a(), ".text", "Add Facility");
	}

	protected Property[] addFacilitySpan(){
		return Property.concatPropertyArray(span(), ".text", "Add Facility");
	}

	protected Property[] facilityIDText(){
		return Property.concatPropertyArray(input("text"), ".id", new RegularExpression("ActivityFacilitySearchCriteria-\\d+.id", false));
	}
	
	protected Property[] facilityIDLink(String facilityID){
		return Property.concatPropertyArray(a(), ".text", facilityID);
	}
	
	protected Property[] facilityIDLink(){
		return Property.concatPropertyArray(a(), ".text", new RegularExpression("\\d+", false));
	}
	
	protected Property[] facilityName(){
		return Property.concatPropertyArray(input("text"), ".id", new RegularExpression("ActivityFacilitySearchCriteria-\\d+.facilityName", false));
	}

	protected Property[] city(){
		return Property.concatPropertyArray(input(".text"), ".id", new RegularExpression("ActivityFacilitySearchCriteria-\\d+.city", false));
	}

	protected Property[] county(){
		return Property.toPropertyArray(".id", new RegularExpression("ActivityFacilitySearchCriteria-\\d+.county", false));
	}

	protected Property[] status(){
		return Property.concatPropertyArray(input(".text"), ".id", new RegularExpression("ActivityFacilitySearchCriteria-\\d+.status", false));
	}

	protected Property[] go(){
		return Property.concatPropertyArray(a(), ".text", "Go");
	}

	protected Property[] oddRowTR(){
		return Property.concatPropertyArray(tr(), ".className", "oddRow");
	}

	protected Property[] message(String msg){
		return Property.concatPropertyArray(div(), ".id", "NOTSET", ".className", "message msgsuccess", ".text", new RegularExpression(msg, false));
	}
	/** Page Object Property Definition END */

	public boolean exists() {
		return browser.checkHtmlObjectExists(activityFacilityResultGrid());
	}

	public boolean isAddFacilityButtonDisplayed(){
		return browser.checkHtmlObjectDisplayed(Property.atList(linkCommonParentDIV(), addFacilityButton()));
	}

	public boolean isAddFacilitySpanDisplayed(){
		return browser.checkHtmlObjectDisplayed(Property.atList(linkCommonParentDIV(), addFacilitySpan()));
	}

	public void clickAddFacilityButton(){
		browser.clickGuiObject(addFacilityButton());
	}

	public void clickGo(){
		browser.clickGuiObject(go());
	}

	public void setFacilityID(String facilityID){
		browser.setTextField(facilityIDText(), facilityID);
	}

	public void setFacilityName(String facilityName){
		browser.setTextField(facilityName(), facilityName);
	}

	public void setCity(String city){
		browser.setTextField(city(), city);
	}

	public void selectCounty(String county){
		browser.selectDropdownList(county(), 0);
	}

	public void selectStatus(String status){
		browser.selectDropdownList(status(), 0);
	}

	public void clickFacilityID(String facilityID){
		browser.clickGuiObject(facilityIDLink(facilityID));
	}
	
	public void clickFirstFacilityID(){
		browser.clickGuiObject(facilityIDLink());
	}
	
	public void searchFacility(Data<SearchFacilityAttr> src){
		if(StringUtil.notEmpty(SearchFacilityAttr.facilityId.getStrValue(src)))
			setFacilityID(SearchFacilityAttr.facilityId.getStrValue(src));
		if(StringUtil.notEmpty(SearchFacilityAttr.facilityName.getStrValue(src)))
			setFacilityName(SearchFacilityAttr.facilityName.getStrValue(src));
		if(StringUtil.notEmpty(SearchFacilityAttr.city.getStrValue(src)))
			setCity(SearchFacilityAttr.city.getStrValue(src));
		if(StringUtil.notEmpty(SearchFacilityAttr.county.getStrValue(src)))
			selectCounty(SearchFacilityAttr.county.getStrValue(src));
		if(StringUtil.notEmpty(SearchFacilityAttr.status.getStrValue(src)))
			selectStatus(SearchFacilityAttr.status.getStrValue(src));

		clickGo();
		ajax.waitLoading();
		waitLoading();
	}

	public IHtmlObject[] getFacilityResultObjs(){
		return browser.getHtmlObject(Property.atList(activityFacilityResultGrid(), oddRowTR()));
	}

	public boolean isMsgExisted(String msg) {
		return browser.checkHtmlObjectExists(message(msg));
	}

	public void verifyMsgExisted(String msg, boolean isExist) {
		if (this.isMsgExisted(msg) != isExist) {
			throw new ErrorOnPageException("The message: " + msg + " should " + (isExist ? " " : " not ") + "exist!");
		}
		logger.info("The message: " + msg + " does " + (isExist ? " " : " not ") + "exist!");
	}

	public int getNumOfResult(){
		IHtmlObject[] objs = getFacilityResultObjs();
		int numOfResult = objs.length;
		Browser.unregister(objs);
		return numOfResult;
	}

	public List<FacilityData> getFacilityData() {
		List<FacilityData> list = new ArrayList<FacilityData>();
		IHtmlObject[] objs = browser.getHtmlObject(activityFacilityResultGridList());
		if(objs.length>0){
			IHtmlTable table = (IHtmlTable) objs[0];
			for (int i = 1; i < table.rowCount(); i++) {
				FacilityData fd = new FacilityData();
				fd.facilityID = table.getCellValue(i, 0).trim();
				fd.facilityName = table.getCellValue(i, 1).split("\\(")[0].trim();
				fd.shortName = table.getCellValue(i, 1).split("\\(")[1].split("\\)")[0].trim();
				fd.mailingCityTown = table.getCellValue(i, 2).trim();
				fd.publicLine = table.getCellValue(i, 3).trim();
				fd.mailingCounty = table.getCellValue(i, 4).trim();
				fd.status = table.getCellValue(i, 5).trim();
				list.add(fd);
			}
			Browser.unregister(table);
			Browser.unregister(objs);
			return list;
		}else throw new ObjectNotFoundException("Can't find activity facility result grid list objects.");
	}

	public void verifyFacilityInListPg(List<FacilityData> FacilityDatas) {
		List<FacilityData> fds = this.getFacilityData();
		boolean result = MiscFunctions.compareResult("Size of list", FacilityDatas.size(), fds.size());
		if(!result){
			throw new ErrorOnPageException("The size of list is different.");
		}else{
			for(int i=0; i<FacilityDatas.size(); i++){
				result &= MiscFunctions.compareResult(i+"-Facility ID", FacilityDatas.get(i).facilityID, fds.get(i).facilityID);
				result &= MiscFunctions.compareResult(i+"-Facility Name", FacilityDatas.get(i).facilityName, fds.get(i).facilityName);
				result &= MiscFunctions.compareResult(i+"-Facility Short Name", FacilityDatas.get(i).shortName, fds.get(i).shortName);
				result &= MiscFunctions.compareResult(i+"-City", FacilityDatas.get(i).mailingCityTown, fds.get(i).mailingCityTown);
				result &= MiscFunctions.compareResult(i+"-Public Line", FacilityDatas.get(i).publicLine, fds.get(i).publicLine);
				result &= MiscFunctions.compareResult(i+"-County", FacilityDatas.get(i).mailingCounty, fds.get(i).mailingCounty);
				result &= MiscFunctions.compareResult(i+"-Status", FacilityDatas.get(i).status, fds.get(i).status);
			}
			if(!result){
				throw new ErrorOnPageException("Not all check points are passed in facility list page. Please check the details from previous logs.");
			}
			logger.info("All check points are passed in faclity list page.");
		}
	}
	
	public boolean hasFacility(Data<SearchFacilityAttr> src){
		searchFacility(src);
		if(getNumOfResult()>0){
			return true;
		}else return false;
	}
}
