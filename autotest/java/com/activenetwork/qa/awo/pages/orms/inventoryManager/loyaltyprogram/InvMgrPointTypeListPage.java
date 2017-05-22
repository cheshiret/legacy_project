package com.activenetwork.qa.awo.pages.orms.inventoryManager.loyaltyprogram;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PointType;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Mar 17, 2014
 */
public class InvMgrPointTypeListPage extends InvMgrLoyaltyProgramCommonPage {

	private static InvMgrPointTypeListPage _instance = null;
	
	private InvMgrPointTypeListPage() {}
	
	public static InvMgrPointTypeListPage getInstance() {
		if(_instance == null) _instance = new InvMgrPointTypeListPage();
		return _instance;
	}
	
	private static String ID_COL = "ID";
	private static String POINT_TYPE_NAME_COL = "Point Type Name";
	private static String REDEEMABLE_COL = "Redeemable";
	private static String ACTIVE_COL = "Active";
	
	private Property[] addNew() {
		return Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("Add New", false));
	}
	
	private Property[] pointTypesListTable() {
		return Property.toPropertyArray(".class", "Html.TABLE", ".text", new RegularExpression("^ID(\\s)?Point Type Name", false));
	}
	
	public void clickAddNew() {
		browser.clickGuiObject(addNew(), true);
	}
	
	private IHtmlObject[] getPointTypesListTable() {
		IHtmlObject objs[] = browser.getTableTestObject(pointTypesListTable());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Point Types list table object.");
		
		return objs;
	}
	
	public String getPointTypeIdByType(String type){
		IHtmlObject objs[] = this.getPointTypesListTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int colIndex=table.findColumn(0, POINT_TYPE_NAME_COL);
		int rowIndex = table.findRow(colIndex, type);
		String id=table.getCellValue(rowIndex, colIndex);
		
		Browser.unregister(objs);
		return id;
	}
	
	public PointType getPointType(String id) {
		IHtmlObject objs[] = this.getPointTypesListTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int rowIndex = table.findRow(table.findColumn(0, ID_COL), id);
		List<String> values = table.getRowValues(rowIndex);
		
		PointType pt = new PointType();
		pt.setId(id);
		pt.setName(values.get(table.findColumn(0, POINT_TYPE_NAME_COL)));
		pt.setRedeemableType(values.get(table.findColumn(0, REDEEMABLE_COL)));
		pt.setStatus(values.get(table.findColumn(0, ACTIVE_COL)));
		
		Browser.unregister(objs);
		return pt;
	}
}
