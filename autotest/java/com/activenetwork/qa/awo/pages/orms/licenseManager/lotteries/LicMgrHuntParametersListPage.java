package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntParameterInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Hunt Parameters list page in LM, Admin(drop down list)-->Lotteries --- > Hunts --- > Hunt Details --- > Parameters tab
 * @author Lesley Wang
 * @Date  Aug 7, 2013
 */
public class LicMgrHuntParametersListPage extends LicMgrHuntDetailPage {
	private static LicMgrHuntParametersListPage _instance = null;
	
	protected LicMgrHuntParametersListPage (){}
	
	public static LicMgrHuntParametersListPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrHuntParametersListPage();
		}
		
		return _instance;
	}
	
	/** Page Object Property Begin */
	protected Property[] huntParasTable() {
		return Property.toPropertyArray(".class", "Html.TABLE", ".id", new RegularExpression("grid_\\d+", false), 
				".text", new RegularExpression("^ID\\s+Status\\s+Description\\s+Value\\s+Print Parameter", false));
	}
	
	protected Property[] addParameterBtn() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Add Parameter");
	}
	
	protected Property[] statusList() {
		return Property.toPropertyArray(".class", "Html.Select", ".id", new RegularExpression("DisplayParameterListSearchCriteria-\\d+\\.status", false));
	}
	
	protected Property[] goBtn() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Go");
	}
	
	protected Property[] idLink(String id) {
		return Property.toPropertyArray(".class", "Html.A", ".text", id);
	}
	
	/** Page Object Property END */
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(this.huntParasTable());
	}
	
	public void clickAddParameter() {
		browser.clickGuiObject(this.addParameterBtn());
	}
	
	public void selectStatus(String status) {
		browser.selectDropdownList(this.statusList(), status, true);
	}
	
	public void selectStatus(int index) {
		browser.selectDropdownList(this.statusList(), index, true);
	}
	
	public void clickGo() {
		browser.clickGuiObject(this.goBtn());
	}
	
	public void searchHuntParameters(String status) {
		if (StringUtil.notEmpty(status)) {
			this.selectStatus(status);
		} else {
			this.selectStatus(0);
		}
		clickGo();
		ajax.waitLoading();
		waitLoading();
	}
	
	private IHtmlObject[] getHuntParameterTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(this.huntParasTable());
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Date Period License Year list table object.");
		}
		
		return objs;
	}

	public String getHuntParamID(String paramDes) {
		IHtmlObject objs[] = getHuntParameterTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int rowIndex = table.findRow(2, paramDes);
		String id = null;
		if(rowIndex != -1) {
			id = table.getCellValue(rowIndex, 0);
		}
		Browser.unregister(objs);
		return id;
	}
	
	public void clickID(String id) {
		browser.clickGuiObject(this.idLink(id));
	}
	
	/**
	 * Verfiy the parameter inforamtion
	 * Note:It is only suitable for that all the active parameter are from the one you added
	 * @param parameters
	 */
	public void verifyParameterInfo(HuntParameterInfo parameters){
		IHtmlObject objs[] = getHuntParameterTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		boolean passed = true;
		for(int i=0; i<parameters.getDescriptAndValue().size(); i++){
		    List<String> parameterInfo = table.getRowValues(i+1);
			logger.info("Verify the "+(i+1)+"th record");
		    passed &= MiscFunctions.compareResult("Parameter status:", OrmsConstants.ACTIVE_STATUS,	parameterInfo.get(1));
			passed &= MiscFunctions.compareResult("Parameter description:", parameters.getDescriptAndValue().get(i).getDescription(),	parameterInfo.get(2));
			passed &= MiscFunctions.compareResult("Parameter value:", parameters.getDescriptAndValue().get(i).getValue(),	parameterInfo.get(3));
			passed &= MiscFunctions.compareResult("Parameter print parameter:", parameters.getDescriptAndValue().get(i).getIsPrint()?"Yes":"No",	parameterInfo.get(4));
		}
		if(!passed){
			throw new ErrorOnPageException("Hunt parameter information may not correct, please check the log above!");
		}
		Browser.unregister(objs);
		logger.info("The information for hunt parameter is correct!");
	}
	
	public List<String> getAllParameterIds(){
		IHtmlObject objs[] = getHuntParameterTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> ids = table.getColumnValues(0);
		ids.remove(0); // Remove the title column
		return ids;
	}
	
	public boolean isAddParameterButtonExist(){
		return browser.checkHtmlObjectExists(this.addParameterBtn());
	}
	
	public boolean isHuntParameterIdLinkExist(String id){
		return browser.checkHtmlObjectDisplayed(".class", "Html.A", ".text", id);
	}
}
