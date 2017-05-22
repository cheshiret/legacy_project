package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
/**
 * 
 * @Description:
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Feb 13, 2014
 */
public class LicMgrPointTypesListPage extends LicMgrLotteriesCommonPage {
	private static LicMgrPointTypesListPage _instance = null;
	
	protected LicMgrPointTypesListPage (){}
	
	public static LicMgrPointTypesListPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrPointTypesListPage();
		}
		
		return _instance;
	}
	private static final String DESCRIPTION_COL = "Description";
	private static final String CODE_COL = "Code";
	
	protected Property[] huntPointTypeListTable() {
		return Property.concatPropertyArray(table(), ".id", "HuntPointTypeListPanel");
	}
	
	protected Property[] huntPointTypeListGrid() {
		return Property.concatPropertyArray(table(), ".id", "huntPointTypeListGrid");
	}
	
	protected Property[] addPointType() {
		return Property.concatPropertyArray(a(), ".id", "Anchor", ".text", "Add Point Type");
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(huntPointTypeListTable());
	}
	
	public void clickAddPointType(){
		browser.clickGuiObject(addPointType());
	}
	
	public String getPointTypeID(String pointTypeCode) {
		IHtmlObject[] objs = browser.getHtmlObject(huntPointTypeListGrid());
		IHtmlTable table = (IHtmlTable)objs[0];
		System.out.println(table.getRowValues(0).toString());
		System.out.println(12);
		int colIndex = table.findColumn(0, CODE_COL);
		int row = table.findRow(colIndex, pointTypeCode);
		String id = table.getCellValue(row, 0);
		Browser.unregister(objs);
		return id;
	}
}