package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntPermitInfo;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrHuntPermitsListPage extends LicMgrHuntDetailPage{
	private static LicMgrHuntPermitsListPage _instance = null;
	
	protected LicMgrHuntPermitsListPage (){}
	
	public static LicMgrHuntPermitsListPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrHuntPermitsListPage();
		}
		
		return _instance;
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "huntPermitListGrid");
	}
	
	public void clickAddHuntPermitButton(){
		RegularExpression pattern=new RegularExpression("Add (Hunt (Permit|Licence))|((Permit|Licence) to Hunt)",false);
		browser.clickGuiObject(".class", "Html.A", ".text", pattern);
	}
	
	public void clickGoButton(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
	
	public void selectFiltersStatus(String status){
		browser.selectDropdownList(".id", new RegularExpression("HuntPermitSearchCriteria-\\d+\\.entityStatus",false), status);
	}
	
	public void selectFiltersStatus(){
		browser.selectDropdownList(".id", new RegularExpression("HuntPermitSearchCriteria-\\d+\\.entityStatus",false), 0);
	}
	
	public void clickHuntPermitID(String huntPermitID){
		browser.clickGuiObject(".class", "Html.A", ".text", huntPermitID);
	}
	
	public IHtmlObject[] getHuntPermitListTableObjects(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "huntPermitListGrid");
		return objs;
	}
	
	public List<HuntPermitInfo> getHuntPermitIDByHuntPermitInfo(List<HuntPermitInfo> huntPermitInfos){
		List<HuntPermitInfo> huntPermitInfoLists = new ArrayList<HuntPermitInfo>();
		
		for(HuntPermitInfo huntPermitInfo : huntPermitInfos){
			HuntPermitInfo huntPermit = new HuntPermitInfo();
			huntPermit = this.getHuntPermitIDByHuntPermitInfo(huntPermitInfo);
			huntPermitInfoLists.add(huntPermit);
		}
		return huntPermitInfoLists;
	}
	
	public HuntPermitInfo getHuntPermitIDByHuntPermitInfo(HuntPermitInfo huntPermitInfo){
		HuntPermitInfo huntPermit = new HuntPermitInfo();
		IHtmlObject[] objs = this.getHuntPermitListTableObjects();

		if(objs.length<1){
			throw new ObjectNotFoundException("Did not get hunt permit list table object.");
		}

		IHtmlTable table = (IHtmlTable)objs[0];
		String expectAwardCriteria = "Min " + 
				(StringUtil.notEmpty(huntPermitInfo.getMinAge())?huntPermitInfo.getMinAge() + " " : "") + 
				"Max " + huntPermitInfo.getMaxAge();
		if(StringUtil.notEmpty(huntPermitInfo.getResidencyStatus())){
			expectAwardCriteria = expectAwardCriteria + "; " + huntPermitInfo.getResidencyStatus();
		}
		for(int i=1; i<table.rowCount(); i++){
			String status = table.getCellValue(i, 1);
			String permit = table.getCellValue(i, 2);
			String appType = table.getCellValue(i, 3);
			String awardCriteria = table.getCellValue(i, 4);

			
			if(huntPermitInfo.getHuntPermitStatus().equals(status) &&
					huntPermitInfo.getApplicantType().equals(appType) &&
					expectAwardCriteria.trim().equals(awardCriteria) &&
					huntPermitInfo.getPermit().equals(permit)){
				String huntPermitID = table.getCellValue(i, 0);
				huntPermitInfo.setHuntPermitID(huntPermitID);
				break;
			}
		}
		
		huntPermit = huntPermitInfo;
		Browser.unregister(objs);
		return huntPermit;
	}
	
	public List<String> getHuntPermitCloumnListInfo(String cloumnName){
		IHtmlObject[] objs = this.getHuntPermitListTableObjects();

		if(objs.length<1){
			throw new ObjectNotFoundException("Did not get hunt permit list table object.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int column = table.findColumn(0, cloumnName);
		List<String> huntPermitIDs = table.getColumnValues(column);
		huntPermitIDs.remove(0);//remove column name ID
		Browser.unregister(objs);
		
		return huntPermitIDs;
	}
	
	public List<String> getHuntPermitIDListInfo(){
		List<String> huntPermitIDs = this.getHuntPermitCloumnListInfo("ID");
		return huntPermitIDs;
	}
	
	public boolean checkHuntPermitIDExisting(String huntPermitID){
		List<String> huntPermitIDs = this.getHuntPermitIDListInfo();
		if(huntPermitIDs.contains(huntPermitID)){
			return true;
		}else{
			return false;
		}
	}
	
	public List<String> getHuntPermitStatusListInfo(){
		List<String> huntPermitStatusList = this.getHuntPermitCloumnListInfo("Status");
		return huntPermitStatusList;
	}
	
	public void filterHuntPermitStatus(String status){
		if(StringUtil.isEmpty(status)){
			this.selectFiltersStatus();
		}else{
			this.selectFiltersStatus(status);
		}
		this.clickGoButton();
	}
	
	
}
