package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Season;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrProductConfigurationPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Dec 20, 2011
 */
public class LicMgrSeasonsConfigurationPage extends LicMgrProductConfigurationPage {
	
	private static LicMgrSeasonsConfigurationPage _instance = null;
	
	protected LicMgrSeasonsConfigurationPage() {}
	
	public static LicMgrSeasonsConfigurationPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrSeasonsConfigurationPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "huntingSeason");
	}
	
	/**
	 * Get the season info
	 * 
	 * @param harvestDesignationValue
	 * @param colName
	 * @return
	 */
	public String getSeasonInfo(String harvestDesignationValue, String expectColName) {
		return this.getCellValue("huntingSeason", harvestDesignationValue, null, expectColName);
	}
	
	/**
	 * Get the season row info
	 * 
	 * @param despription - the value of description.
	 * @param printOrder - the value of print order.
	 * @return List<String>-  the row info.
	 */
	public List<String> getSeaonRoeInfo(String despription,String printOrder){
		List<String> list  = new ArrayList<String>();
		IHtmlObject[] objs= browser.getHtmlObject(".class", "Html.TABLE", ".id", "huntingSeason");
		IHtmlTable table = (IHtmlTable)objs[0];
		for(int i= 0;i<table.rowCount();i++){
			if(table.getCellValue(i, 1).equals(despription) && table.getCellValue(i, 2).equals(printOrder)){
				list = table.getRowValues(i);
			}
				
		}
		Browser.unregister(objs);
		return list;
	}
	
	/**
	 * Compare the list item value
	 * 
	 * @param season - the season value
	 * @return boolean-  the boolean value of comparing.
	 */
	public boolean compareSeasonInfo(Season season){
		boolean pass = true;
		List<String> array = this.getSeaonRoeInfo(season.despription,season.printOrder);
		if(array.size()<=0){
			pass &= false;
			logger.error("The specific season info is not exist");
		}
		if(!array.get(0).equals(season.harvestDesignation)){
			pass &= false;
			logger.error("The season harvest designation "+season.harvestDesignation+" is not exist");
		}
		if(!array.get(1).equals(season.despription)){
			pass &= false;
			logger.error("The season description "+season.despription+" is not exist");
		}
		if(!array.get(2).equals(season.printOrder)){
			pass &= false;
			logger.error("The season print order "+season.printOrder+" is not exist");
		}
		if(!array.get(3).equals(season.creationUser)){
			pass &= false;
			logger.error("The season creat user "+season.creationUser+" is not exist");
		}
		if(!array.get(4).equals(season.creationLocation)){
			pass &= false;
			logger.error("The season creat location "+season.creationLocation+" is not exist");
		}
		return pass;
	}
}
