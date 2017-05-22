package com.activenetwork.qa.awo.pages.orms.inventoryManager.pos;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class InvMgrPosPrintBarcodePage extends InvMgrCommonTopMenuPage{
	private static InvMgrPosPrintBarcodePage _instance = null;
	
	protected InvMgrPosPrintBarcodePage(){
		
	}
	
	public static InvMgrPosPrintBarcodePage getInstance(){
		if(null ==_instance){
			_instance = new InvMgrPosPrintBarcodePage();
		}
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "posinventoryforprinting",".class","Html.DIV");
	}
	
	public static final String PRODUCT_ID_COL = "Product ID";
	public static final String PRODUCT_NAME_COL = "Product Name";
	public static final String PRODUCT_DESCRIPTION_COL = "Product Description";
	public static final String PRODUCT_GROUP_COL = "Product Group";
	public static final String PRODUCT_CLASS_COL = "Product Class";
	public static final String PRODUCT_SUB_CLASS_COL = "Product Sub-Class";
	public static final String INVENTORY_TYPE_COL = "Inventory Type";
	public static final String BARCODE_COL = "Barcode";
	public static final String QTY_ON_HAND_COL = "Qty On Hand";
	public static final String NUM_OF_BARCODES_COL = "NO # Of Barcodes";
	
	public void setProductName(String posName){
		browser.setTextField(".id", new RegularExpression("FacilityWarehousePOSSearchCriteria-\\d+\\.prdName",false), posName, true);
	}                                                      
	
	public void setProductGroup(String posGroup){
		if(!StringUtil.isEmpty(posGroup)){
			browser.selectDropdownList(".id", new RegularExpression("FacilityWarehousePOSSearchCriteria-\\d+\\.productGroupID",false), posGroup, true);
		}
	}
	
	public void setQtyOnHand(String qtyOnHand){
		browser.setTextField(".id", new RegularExpression("FacilityWarehousePOSSearchCriteria-\\d+\\.qtyOnHand",false), qtyOnHand, true);
	}
	
	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A",".text","Search");
	}
	
	public void clickPrintBarcodes(){
		browser.clickGuiObject(".class", "Html.A",".text","Print Barcodes");
	}
	
	public void setSearchCriteria(String posName, String posGroup, String qtyOnHand){
		if(null == posName){
			posName = "";
		}
		this.setProductName(posName);
		if(null == posGroup){
			posGroup = "";
		}
		this.setProductGroup(posGroup);
		if(null == qtyOnHand){
			qtyOnHand = "";
		}
		this.setQtyOnHand(qtyOnHand);
	}
	
	public IHtmlObject[] getPOSProductListTableObject(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "posinventoryforprinting_LIST");
		if(objs.length<1){
			throw new ObjectNotFoundException("Did not found pos inventory fro printing list table object.");
		}
		
		return objs;
	}
	
	public int getPOSProductRowCount(String posName){
		IHtmlObject[] objs = this.getPOSProductListTableObject();
		
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(2, posName);
		
		Browser.unregister(objs);
		return row;
	}
	
	public String getPOSIDByName(String name) {
		IHtmlObject objs[] = this.getPOSProductListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int nameColIndex = table.findColumn(0, PRODUCT_NAME_COL);
		int idColIndex = table.findColumn(0, PRODUCT_ID_COL);
		int rowIndex = table.findRow(nameColIndex, name);
		String id = table.getCellValue(rowIndex, idColIndex);
		
		Browser.unregister(objs);
		return id;
	}
	
	public void selectCheckBox(String posName){
		int index = this.getPOSProductRowCount(posName);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".name", "e_Glow");
//		browser.selectCheckBox(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems",false), 0, objs[index-1]);
		browser.selectCheckBox(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems",false), 0, objs[index]);
		Browser.unregister(objs);
	}
	
	public boolean checkBoxIsSelected(String posName){
//		int index = this.getPOSProductRowCount(posName);
//		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".name", "e_Glow");
		
//		Property[] properties = new Property[1];
//		properties[0] = new Property(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems",false));
		
		//Quentin[20131212] sometimes some records don't have checkbox
		String posID = this.getPOSIDByName(posName);
		IHtmlObject[] boxes = browser.getCheckBox(Property.toPropertyArray(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems",false), ".value", posID));
		ICheckBox checkBoxObj = (ICheckBox)boxes[0];
		boolean isSelected = checkBoxObj.isSelected();
		
		Browser.unregister(boxes);
		
		return isSelected;
	}
	
	public void setNumberOfPrint(String posName, String number){
		int index = this.getPOSProductRowCount(posName);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".name", "e_Glow");
//		browser.setTextField(".id", new RegularExpression("FacilityWarehousePOSInvForPrintingSearchView-\\d+\\.numOfPrint",false), number, objs[index-1]);
		browser.setTextField(".id", new RegularExpression("FacilityWarehousePOSInvForPrintingSearchView-\\d+\\.numOfPrint",false), number, objs[index]);
		Browser.unregister(objs);
	}
	
	public String getNumberOfPrint(String posName){
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.TR", ".name", "e_Glow", ".text", new RegularExpression(posName, false)));
		Property[] properties = new Property[1];
		properties[0] = new Property(".id", new RegularExpression("FacilityWarehousePOSInvForPrintingSearchView-\\d+\\.numOfPrint",false));
		String text = browser.getTextFieldValue(properties, objs[0]);
		Browser.unregister(objs);
		return text;
	}
	
	public void clickColumnHeading(String columnName){
		browser.clickGuiObject(".class", "Html.A", ".text", columnName);
	}
	
	public List<String> getColumnValue(String columnName){
		IHtmlObject[] objs = this.getPOSProductListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int col = table.findColumn(0, columnName);
		List<String> colValues = table.getColumnValues(col);
		
		Browser.unregister(objs);
		return colValues;
	}
	
	public String getMessage(){
		String message = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if(objs.length<1){
			throw new ObjectNotFoundException("Did not found message object.");
		}
		message = objs[0].text();
		Browser.unregister(objs);
		return message;
	}

}
