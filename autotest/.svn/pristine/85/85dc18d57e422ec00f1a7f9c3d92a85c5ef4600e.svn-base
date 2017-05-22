package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Lottery Parameters list page in LM, Admin(drop down list)-->Lotteries  --- > Lottery Details --- > Parameters tab
 * @author Lesley Wang
 * @Date  Mar 25, 2014
 */
public class LicMgrLotteryParametersListPage extends
		LicMgrLotteryProductDetailsPage {
	private static LicMgrLotteryParametersListPage _instance = null;
	
	protected LicMgrLotteryParametersListPage (){}
	
	public static LicMgrLotteryParametersListPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrLotteryParametersListPage();
		}
		
		return _instance;
	}
	
	/** Page Object Property Begin */
	protected Property[] lotteryParasTable() {
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
		return browser.checkHtmlObjectExists(this.lotteryParasTable());
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
	
	public void searchLotteryParameters(String status) {
		if (StringUtil.notEmpty(status)) {
			this.selectStatus(status);
		} else {
			this.selectStatus(0);
		}
		clickGo();
		ajax.waitLoading();
		waitLoading();
	}
	
	private IHtmlObject[] getLotteryParameterTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(this.lotteryParasTable());
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Lottery parameter list table object.");
		}
		
		return objs;
	}

	public String getLotteryParamID(String paramDes) {
		IHtmlObject objs[] = getLotteryParameterTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int rowIndex = table.findRow(2, paramDes);
		String id = null;
		if(rowIndex != -1) {
			id = table.getCellValue(rowIndex, 0);
		}
		Browser.unregister(objs);
		return id;
	}
}
