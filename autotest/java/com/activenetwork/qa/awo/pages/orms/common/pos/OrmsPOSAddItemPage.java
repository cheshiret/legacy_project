/*
 * $Id: FldMgrPosItemsPage.java 7058 2009-01-28 18:59:02Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.common.pos;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * TODO: enter class description
 * 
 * @author CGuo
 */
public class OrmsPOSAddItemPage extends OrmsPage {

	/**
	 * Script Name   : FldMgrPosItemsPage
	 * Generated     : Jun 30, 2005 10:55:39 AM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2005/06/30
	 */

	/** Column Names of POS List*/
	private final String PROD_NAME_COL = "Product Name";
	private final String PROD_GROUP_COL = "Product Group";
	private final String PROD_UNIT_PRICE_COL = "Unit Price";
	
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsPOSAddItemPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsPOSAddItemPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsPOSAddItemPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsPOSAddItemPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id","posListView");
	}

	/**
	 * Input Item number
	 * @param num
	 */
	public void setItemNumber(String num) {
		browser.setTextField(".id", "itemNumber", num);
	}

	/**Click Go button*/
	public void clickGO() {
//		if (browser.checkHtmlObjectExists(".class", "Html.TR", ".text", 
//				new RegularExpression("^Search", false))) {
//			browser.clickGuiObject(".class", "Html.A", ".text", "Search", 1);
//		} else {
//			browser.clickGuiObject(".class", "Html.A", ".text", "Search");
//		}
		Property[] properties = new Property[3];
		properties[0] = new Property(".class", "Html.A");
		properties[1] = new Property(".text", "Search");
		properties[2] = new Property(".href", new RegularExpression(".*SearchAvailablePOS.*",false));
		browser.clickGuiObject(properties);
		System.out.println("hello");
	}

	/**
	 * Input produce name
	 * @param product
	 */
	public void setProductName(String product) {
		if(!StringUtil.isEmpty(product))
			browser.setTextField(".id", "prodName", product);
	}

	/**check variable price checkbox*/
	public void selectVariablePriceCheckBox() {
		browser.selectCheckBox(".id", "variablePrice");
	}

	/**Unselect Variabl Price Checkbox*/
	public void deSelectVariablePriceCheckBox() {
		browser.unSelectCheckBox(".id", "variablePrice");
	}

	/**
	 * Select search item
	 * @param product
	 * @param priced
	 */
	public void searchItem(String product, boolean priced) {
		this.setProductName(product);
		if (priced) {
			selectVariablePriceCheckBox();
		}
		this.clickGO();
	}

	/**
	 * Select product item
	 * @param product
	 */
	public void searchItem(String product) {
		searchItem(product, false);
	}

	/**
	 * Select the POS Item's checkbox by index
	 * @param index - 0 based
	 */
	public void selectPOSItemCheckbox(int index) {
		browser.selectCheckBox(".id", "pos_row_" + index);
	}

	/**
	 * Check POS Item checkbox exist
	 * @param index
	 * @return
	 * @author Lesley Wang
	 * @date Jul 31, 2012
	 */
	public boolean checkPOSItemCheckboxExist(int index) {
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.checkbox", 
				".id", "pos_row_" + index);
	}
	
	/**
	 * Click POS Item link to select it. New in 3.03
	 * @param index
	 * @author Lesley Wang
	 * @date Jul 31, 2012
	 */
	public void clickPOSItemLink(int index) {
		IHtmlObject[] frames = browser.getFrame("transaction");
		IHtmlObject[] list = null;
		if(index==0) {
			logger.warn("POS item index should not be less than 1. reset to 1.");
			index=1;
		}
		if(frames.length == 0){
			browser.clickGuiObject(".class", "Html.A", ".text", "A", index-1);
		}else{
			list = browser.getHtmlObject(".class", "Html.DIV", ".id", "posListView", frames[0]);
			Property[] p = new Property[2];
			p[0] = new Property(".class", "Html.A");
			p[1] = new Property(".text", new RegularExpression("^A\\d*", false));
			browser.clickGuiObject(p,  true, index-1, list[0]);
		}
		Browser.unregister(list);
		Browser.unregister(frames);	
	}
	
	/**
	 * Select POS item Quantity
	 * @param index
	 * @param value
	 */
	public String setPOSItemQuantity(int index, String value) {
		String msg = "";
		IHtmlObject[] frames = browser.getFrame("transaction");
		if(frames.length == 0){
			msg = this.setDateAndGetMessage((IText)browser.getTextField(".id", "quantity_row_" + (index-1))[0], value);
		}else{
			msg = this.setDateAndGetMessage((IText)browser.getTextField(Property.toPropertyArray(".id", "quantity_row_" + (index-1)),frames[0])[0], value);
		}
		Browser.unregister(frames);	
		return msg;
	}

	/**
	 * Input POS item Price
	 * @param index
	 * @param value
	 */
	public void setPOSItemPrice(int index, String value) {
		browser.setTextField(".id", "variable_price_row_" + index, value);
	}

	/**
	 * Select POS. 
	 * @param pos
	 */
	public String selectItem(POSInfo pos) {
		String msg = "";
		int index;
		if (pos.index.equalsIgnoreCase(""))
			if(StringUtil.isEmpty(pos.product)) {
				index=1;
			} else {
				index = getPOSRowIndex(pos.product);
			}
		else
			index = Integer.parseInt(pos.index);

		
		if (isCheckBox()) { //3.03 pos return page has check box
			this.selectPOSItemCheckbox(index); 
		} else {
			this.clickPOSItemLink(index); // for 3.03
		}
			
		if (!StringUtil.isEmpty(pos.quantity)) {
			msg = this.setPOSItemQuantity(index, pos.quantity);
		}
		return msg;
	}
	
	public String selectItem(POSInfo... pos) {
		String msg = StringUtil.EMPTY;
		int index = -1;

		for(int i=0; i<pos.length; i++){
			//Get POS product index
			if(pos[i].index.equals(StringUtil.EMPTY)){
				if(StringUtil.isEmpty(pos[i].product)) {
					index=1;
				} else {
					index = getPOSRowIndex(pos[i].product);
				}
			}

			//Select POS 
			if (isCheckBox()) {
				this.selectPOSItemCheckbox(index); 
			} else {
				this.clickPOSItemLink(index);
			}

			//Set POS quantity
			if (StringUtil.notEmpty(pos[i].quantity)) {
				msg = this.setPOSItemQuantity(index, pos[i].quantity);
			}
		}
		return msg;
	}
	
	public boolean isCheckBox() {
		Property[] p1=Property.toPropertyArray(".class","Html.DIV",".name","posListView");
		Property[] p2=Property.toPropertyArray(".class","Html.INPUT.checkbox");
		IHtmlObject[] objs=browser.getHtmlObject(Property.atList(p1,p2));
		boolean exists=false;
		if(objs.length>0) {
			exists=objs[0].isEnabled();
		}
		
		Browser.unregister(objs);
		return exists;
	}
	
	public void setPOSVariablePrice(String posName, String price) {
		if (!StringUtil.isEmpty(price)) {
			this.setPOSItemPrice(getPOSRowIndexInBottomTable(posName) - 1, price);//this index is in bottom table
		}
	}
	
	public void setPOSVariablePrice(POSInfo... pos) {
		for(int i=0; i<pos.length; i++){
			if(StringUtil.notEmpty(pos[i].price))
				this.setPOSItemPrice(getPOSRowIndexInBottomTable(pos[i].product) - 1, pos[i].price);
		}
	}
	
	private int getPOSRowIndex(String posName) {
		IHtmlObject objs[] = browser.getTableTestObject(".text", new RegularExpression("^(((BARCODE)|(Product ID))(\\s)?)?Product Name(\\s)?Product Group(\\s)?(Quantity|QTY TO RETURN)(\\s)?(Unit Price)?", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Top table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[1];
		int colIndex=table.findColumn(0, "PRODUCT NAME");
		
		int rowIndex = table.findRow(colIndex, posName);
		
		if(rowIndex == -1) {
			throw new ItemNotFoundException("Can't find POS - " + posName + " in the top table.");
		}
		
		Browser.unregister(objs);
		return rowIndex;
//		String id=getPOSProductID(posName);
//		HtmlObject objs[] = browser.getCheckBox(".value",id);
//		String attr=objs[0].getProperty(".name");
//		String[] tokens=RegularExpression.getMatches(attr, "\\d+");
//		return Integer.parseInt(tokens[0]);
	}
	
	public String getPOSProductID(String posName) {
		String query="select prd_id from p_prd where trim(prd_name)='"+posName.trim()+"'";
		String contract=this.getContract();
		String schema=TestProperty.getProperty(TestProperty.getProperty("target_env")+".db.schema.prefix")+contract;
		AwoDatabase db = (AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		String result=db.executeQuery(query, "prd_id", 0);
				
		return result;
	}
	
	private int getPOSRowIndexInBottomTable(String posName) {
		IHtmlObject objs[] = browser.getTableTestObject(".className", "listview_midalign");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find bottom table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowIndex = table.findRow(2, posName);
		
		if(rowIndex == -1) {
			throw new ItemNotFoundException("Can't find POS - " + posName + " in the bottom table.");
		}
		
		Browser.unregister(objs);
		return rowIndex;
	}

	/**click Add to cart button*/
	public void clickAddToCart() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add to Cart");
	}

	public void clickAddToReturnList()
	{
		browser.clickGuiObject(".class", "Html.A", ".text", "Add to Return List");
	}
	
	public void clickContinue()
	{
		browser.clickGuiObject(".class", "Html.A", ".text", "Continue");
	}
	
	/**click Go to cart */
	public void clickGoToCart() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go to Cart");
	}
	
	/**
	 * Search pos and add pos to cart
	 * This workflow was on OrmsPOSAddItemPage
	 * @param pos
	 */
	public void addPosToCart(POSInfo pos){
		logger.info("Search Pos product and add to cart.");
		searchItem(pos.product);
		this.waitLoading();
		selectItem(pos);
		clickAddToCart();
		ajax.waitLoading();
		this.waitLoading();
		clickGoToCart();
	}
	
	public IHtmlObject[] getPOSListDivs() {
		return browser.getHtmlObject(".class", "Html.DIV", ".id", "posListView");
	}
	
	public IHtmlObject[] getPOSProdListTables() {
		IHtmlObject[] posListDivs = this.getPOSListDivs();
		if (posListDivs.length < 1) {
			throw new ItemNotFoundException("The POS List Div is not found!");
		}	
		IHtmlObject[] posListTables = browser.getTableTestObject(Property.toPropertyArray(".className","listView"), posListDivs[0]);
		Browser.unregister(posListDivs);
		return posListTables;
	}
	
	/**
	 * Get POS Product Info in the list by product name
	 * @param name
	 * @return
	 * @author Lesley Wang
	 * @date Jul 23, 2012
	 */
	public POSInfo getPOSProdInfoInList(String name) {
		IHtmlObject[] posListTables = this.getPOSProdListTables();
		if (posListTables.length < 1) {
			throw new ItemNotFoundException("The POS List Table is not found!");
		}
		IHtmlTable table = (IHtmlTable)posListTables[0];	
		POSInfo pos = new POSInfo();
	
		List<String> firstRowValue = table.getRowValues(0);
		int col = firstRowValue.indexOf(PROD_NAME_COL); 
		int row = table.findRow(col, name);
		if (row > 0) {
			List<String> values = table.getRowValues(row);
			pos.product = name;
			pos.productGroup = values.get(firstRowValue.indexOf(PROD_GROUP_COL));
			pos.unitPrice = values.get(firstRowValue.indexOf(PROD_UNIT_PRICE_COL)).replace("$", "").trim();
		}
		
		Browser.unregister(posListTables);
		return pos;
	}
	
	public boolean comparePOSProdInfo(POSInfo pos) {
		POSInfo actPOS = this.getPOSProdInfoInList(pos.product);
		boolean result = true;
		result &= MiscFunctions.compareResult("POS product group", pos.productGroup, actPOS.productGroup);
		result &= MiscFunctions.compareResult("POS unit price", pos.unitPrice, actPOS.unitPrice);
		return result;
	}
	
	public void verifyPOSProdInfo(POSInfo pos) {
		boolean act = this.comparePOSProdInfo(pos);
		if (!act) {
			throw new ErrorOnPageException("The POS Info is wrong! please check logger error!");
		}
		logger.info("The POS Info is displayed correctly in the list!");
	}

	public boolean checkPOSProdExistInList(String name) {
		POSInfo actPOS = this.getPOSProdInfoInList(name);
		return !StringUtil.isEmpty(actPOS.product);
	}
	
	public void verifyPOSProdExistInList(String name, boolean expRes) {
		boolean actRes = this.checkPOSProdExistInList(name);
		String meg1 = "The POS with the name=" + name; 
		String meg2 = (expRes ? "" : "NOT") + " exist in the list";
		if (actRes != expRes) {
			throw new ErrorOnPageException(meg1 + " should " + meg2);
		} else {
			logger.info(meg1 + " does " + meg2);
		}
	}
	
	public void verifyPOSProdIsAvaviable(String name, boolean isAvaiable){
//		IHtmlObject[] posListTables = this.getPOSProdListTables();
//		if (posListTables.length < 1) {
//			throw new ItemNotFoundException("The POS List Table is not found!");
//		}
//		IHtmlTable table = (IHtmlTable)posListTables[0];	
		IHtmlTable table = (IHtmlTable)browser.getTableTestObject(".text", new RegularExpression("Product ID.*Product Name.*Quantity.*Unit Price", false))[1];
		int row = table.findRow(2, name);// find pos name from 'Product Name' column
		if(row<0){
			throw new ItemNotFoundException("Can't find POS "+name);
		}
		String text = table.getCellValue(row, 0);
		if(!isAvaiable){
			if(!text.equalsIgnoreCase("X")){
				throw new ErrorOnPageException("The POS Product should not avaiable.");
			}
		}else{
			if(!text.equalsIgnoreCase("A")){
				throw new ErrorOnPageException("The POS Product should avaiable.");
			}
		}
	}
	
	public List<String> getPOSProductNameColumnListValues(){
		IHtmlObject[] objs = this.getPOSProdListTables();
		
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		int col = table.findColumn(0, "Product Name");
		List<String> values = table.getColumnValues(col);
		values.remove(0);
		Browser.unregister(objs);
		return values;
	}
	
	public void selectIncurredBySiteOrSlipName(String name){
		browser.selectDropdownList(".id", "selectedIncurredBySite", name);
	}
	
	public void selectIncurredByResNum(String num){
		browser.selectDropdownList(".id", "selectedIncurredByReservation", num);
	}
	
	public void selectIncurredByPrimaryOcc(String primaryOcc){
		browser.selectDropdownList(".id", "selectedIncurredByPrimaryOcc", primaryOcc);
	}
}
