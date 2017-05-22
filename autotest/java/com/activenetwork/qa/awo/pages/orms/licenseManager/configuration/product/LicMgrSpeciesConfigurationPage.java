package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Species;
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
public class LicMgrSpeciesConfigurationPage extends LicMgrProductConfigurationPage {
	
	private static LicMgrSpeciesConfigurationPage _instance = null;
	
	protected LicMgrSpeciesConfigurationPage() {}
	
	public static LicMgrSpeciesConfigurationPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrSpeciesConfigurationPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "species");
	}
	
	/**
	 * Get the species info
	 * 
	 * @param harvestDesignation
	 *            - harvest designation
	 * @param colName
	 *            - column name
	 * @return
	 */
	public String getSpeciesInfo(String harvestDesignation, String colName) {
		return this.getCellValue("species", harvestDesignation, null,colName);
	}
	
	public boolean isSpeciesInfoExist(String harvestDesignation, String colName) {
		return isCellExist("species", harvestDesignation, colName);
	}
	
	/**
	 * Compare the species info.
	 * 
	 * @param species
	 *            - the species info
	 * @return boolean - the boolean value of comparing.
	 */
	public boolean compareSpeciesInfo(Species species){
		boolean pass = true;
		IHtmlObject[] obj = browser.getHtmlObject(".class", "Html.TABLE", ".id", "species");
		IHtmlTable table = (IHtmlTable) obj[0];
		int row = table.findRow(2, species.description);
		List<String> list = table.getRowValues(row);
		Browser.unregister(obj);

		if(list.size() < 1) {
			pass &= false;
			logger.error("The species info is not exist");
		}
		if(!list.get(0).equals(species.speciesId)){
			pass &= false;
			logger.error("The species id "+species.speciesId+" is not exist");
		}
		if(!list.get(2).equals(species.description)){
			pass &= false;
			logger.error("The species despription "+species.description+" is not exist");
		}
		if(!list.get(4).equals(species.creationUser)){
			pass &= false;
			logger.error("The species "+species.speciesId+" is not exist");
		}
		if(!list.get(5).equals(species.creationLocation)){
			pass &= false;
			logger.error("The species "+species.speciesId+" is not exist");
		}
		return pass;
	}
}
