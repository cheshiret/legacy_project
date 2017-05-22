/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrAddItemPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.NotSupportedException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author ssong
 * @Date  Oct 31, 2011
 */
public class LicMgrOrigPrivSaleAddItemPage extends LicMgrAddItemPage{

	private static LicMgrOrigPrivSaleAddItemPage _instance = null;
	
	private LicMgrOrigPrivSaleAddItemPage(){
	}
	
	public static LicMgrOrigPrivSaleAddItemPage getInstance(){
		if(_instance == null){
			_instance = new LicMgrOrigPrivSaleAddItemPage();
		}
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id",
		"privilege_purchase") & !DialogWidget.getInstance().exists();
	}
	
	private IHtmlObject[] getItemRow(int index){
		return browser.getHtmlObject(".class","Html.TR",".id","privilege_purchase_row_"+index);
	}
	private void selectProdQty(String qty,IHtmlObject top) {
		browser
				.selectDropdownList(".id", new RegularExpression(
						"HFProductForTransaction-\\d+\\.qty", false),
						qty,false, top);
	}

	/**
	 * The method used to add specify product to cart
	 * 
	 * @param prodName
	 * @param licenseYear
	 * @param qty
	 */
	public void addProductToCart(String prodName, String licenseYear, String qty) {
//		int index = this.getProductIndex(prodName, licenseYear)
//				- this.getBlankRowsNum(prodName);
		//Shane:update exist use index to select qty, as for hunt, no qty drop down, so it will affect the index value
		//updated logic as use index to found specific row and use that row as top object to operate qty drop down and click add to cart
		int index = this.getProductIndex(prodName, licenseYear);
		IHtmlObject[] rows = this.getItemRow(index-1);
		if(rows==null||rows.length<1){
			throw new ItemNotFoundException("Failed to found Row with ("+licenseYear+")"+prodName);
		}
		if(!StringUtil.isEmpty(qty)) {
			this.selectProdQty(qty,rows[0]);
		}
		this.clickAddToCart(rows[0]);
		Browser.unregister(rows);
		ajax.waitLoading();
	}

	/**
	 * The method used to get the row number for given product
	 * 
	 * @param prodName, prodName string or regular expression pattern
	 * @return
	 */
	public int getProductIndex(String prodName, String licenseYear) {
		//change the prodName to support a prodName pattern search, if the prodName is a pattern, it should start with <regex> keyword
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"privilege_purchase");
		IHtmlTable grid = (IHtmlTable) objs[0];
		boolean found = false;
		boolean isPattern=prodName.startsWith("<regex>");
		if(isPattern) {
			prodName=prodName.replaceFirst("<regex>", "");
		}
		int num = -1;
		for (int i = 0; i < grid.rowCount(); i++) {
			String nameValue=grid.getCellValue(i, 1);
			
			boolean nameFound=(isPattern?nameValue.matches(prodName):prodName.equals(nameValue));
		
			if (nameFound && grid.getCellValue(i, 0).equals(licenseYear)) {
				found = true;
				num = i;
				break;
			}
		}
		Browser.unregister(objs);
		if (!found) {
			throw new ItemNotFoundException("Could Not Found Given Product: '(" + licenseYear + ") " + prodName + "'.");
		}
		return num;
	}	

	/**
	 * The method used to get column through column name in privilege purchase table
	 * @param columnName
	 * @return
	 */
	private int getPrivPurchTableColumn(String columnName){
		IHtmlObject[] objs = browser.getTableTestObject(".id","privilege_purchase");
		IHtmlTable grid = (IHtmlTable)objs[0];
		boolean found = false;
		int col = -1;
		
		for(int i =0; i<grid.columnCount(); i++){
			if(grid.getCellValue(0, i).equalsIgnoreCase(columnName)){
				found = true;
				col = i;
				break;
			}
		}
		Browser.unregister(objs);
		if(!found){
			throw new ItemNotFoundException("Could Not Found Given Column Name "+columnName);
		}
		return col;		
	}

//	private int getBlankRowsNum(String prodName) {
//		IHtmlObject[] objs = browser.getTableTestObject(".id",
//				"privilege_purchase");
//		IHtmlTable grid = (IHtmlTable) objs[0];
//		int count = 1;
//		for (int i = 0; i < grid.rowCount(); i++) {
//			String value = grid.getCellValue(i, 1);
//			if (StringUtil.isEmpty(value)) {
//				count++;
//			}
//			if (value.equals(prodName)) {
//				break;
//			}
//		}
//		Browser.unregister(objs);
//		return count;
//	}

	/**
	 * This method used to get product name from privilege purchase list
	 * 
	 * @return
	 */
	public List<String> getProductInThePurchaseList() {
		List<String> prdName = new ArrayList<String>();
		IHtmlObject[] objs = browser.getTableTestObject(".id","privilege_purchase");
		IHtmlTable table = (IHtmlTable) objs[0];

		// there are two check boxes added below Product
		// Show All and Bypass Cache
		int prdouctCol = table.findColumn(0, "Product Bypass Cache Show All");
		for (int i = 0; i < table.rowCount(); i++) {
			String product = table.getCellValue(i, prdouctCol);
			prdName.add(product);
		}
		Browser.unregister(objs);
		return prdName;
	}

	public boolean isThisPrivilegeExist(String prodName) {
		boolean flag = false;
		IHtmlObject[] objs = browser.getTableTestObject(".text", prodName);

		if (objs.length > 0) {
			flag = true;
		}
		Browser.unregister(objs);
		return flag;
	}
	
	/**
	 * Get qty of item in cart for privilege from add item page
	 * @param privilegeName
	 * @param licneseYear
	 * @return
	 */
	public int getQtyOfPriviInCartFromAddItemPg(String privilegeName, String licneseYear){
		logger.info("Get Qty Of Item In Cart For Privilege From Add Item Page");
		
		int row = this.getProductIndex(privilegeName, licneseYear);
		int col = this.getPrivPurchTableColumn("# of Items in Cart");
		
		IHtmlObject[] objs = browser.getTableTestObject(".id","privilege_purchase");
		IHtmlTable grid = (IHtmlTable)objs[0];
		
		int qty = Integer.parseInt(grid.getCellValue(row, col));
		
		return qty;
	}
	
	public boolean checkConsumablesTabExisted(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Consumables");
	}
	/**
	 * get privilege price.
	 * @param prodName
	 * @param licenseYear
	 * @return
	 */
	public String getPriPriceByPrdNameAndLicenseYear(String prodName,String licenseYear){
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"privilege_purchase");
		IHtmlTable grid = (IHtmlTable) objs[0];
		int index  = this.getProductIndex(prodName, licenseYear);
		if(index<=-1){
			throw new ErrorOnPageException("No Specific product was found");
		}
		String price =  grid.getRowValues(index).get(2);
		Browser.unregister(objs);
		return  price;
	}
	
	public void clickBypassCache(){
		throw new NotSupportedException("It is not allowed to use BypassCache in Automated testing.");
//		browser.selectCheckBox(".id", new RegularExpression("PrivilegeProductForSaleSearchCriteria-\\d+\\.bypassCache", false), true);
	}
	
	public List<String> getQtyListForPrivilege(String privilegeName, String licneseYear){
		logger.info("Get Qty list for privilege:"+privilegeName+" on page.");
		
		int index = this.getProductIndex(privilegeName, licneseYear);
		
		IHtmlObject[] rows = browser.getHtmlObject(".class", "Html.TR", ".id", "privilege_purchase_row_"+(index-1));
		if(rows.length<1)
			throw new ItemNotFoundException("Could not find table row for "+index);
		
		List<String> qtyList = browser.getDropdownElements(Property.toPropertyArray(".id", new RegularExpression("HFProductForTransaction-\\d+\\.qty", false)), rows[0]);
		Browser.unregister(rows);
		return qtyList;
	}
	
	public boolean checkPrivilegeExitence(String privCode, String privName, String licYear) {
		IHtmlObject[] objs = browser.getTableTestObject(".id", "privilege_purchase");
		IHtmlTable grid = (IHtmlTable) objs[0];
		boolean found = false;
		String privPurchaseName = privCode + "-" + privName;
		boolean isPattern=privPurchaseName.startsWith("<regex>");
		if(isPattern) {
			privPurchaseName=privPurchaseName.replaceFirst("<regex>", "");
		}
		for (int i = 0; i < grid.rowCount(); i++) {
			String nameValue=grid.getCellValue(i, 1);
			
			boolean nameFound=(isPattern?nameValue.matches(privPurchaseName):privPurchaseName.equals(nameValue));
		
			if (nameFound && grid.getCellValue(i, 0).equals(licYear)) {
				found = true;
				break;
			}
		}
		Browser.unregister(objs);
		if (!found) 
			return false;
		return true;
	}
	
	public int getPrivilegeExitenceNums(String privCode, String privName, String licYear) {
		IHtmlObject[] objs = browser.getTableTestObject(".id", "privilege_purchase");
		IHtmlTable grid = (IHtmlTable) objs[0];
		boolean found = false;
		int nums = 0;
		String privPurchaseName = privCode + "-" + privName;
		boolean isPattern=privPurchaseName.startsWith("<regex>");
		if(isPattern) {
			privPurchaseName=privPurchaseName.replaceFirst("<regex>", "");
		}
		for (int i = 0; i < grid.rowCount(); i++) {
			String nameValue=grid.getCellValue(i, 1);
			
			boolean nameFound=(isPattern?nameValue.matches(privPurchaseName):privPurchaseName.equals(nameValue));
		
			if (nameFound && grid.getCellValue(i, 0).equals(licYear)) {
				found = true;
				nums++;
			}
		}
		Browser.unregister(objs);
		if (!found) 
			throw new ItemNotFoundException("Privilege "+privName+" not found on page.");
		logger.info("Found "+nums+" privilege "+privName);
		return nums;
	}
	
	public void verifyPrivExistence(String privCode, String privName, String licYear, boolean existed) {
		boolean existedUI = checkPrivilegeExitence(privCode, privName, licYear);
		if(existed && !existedUI)
			throw new ErrorOnPageException("Privilege "+privCode+"-"+privName+" should exist on page.");
		else if(!existed && existedUI) 
			throw new ErrorOnPageException("Privilege "+privCode+"-"+privName+" should Not exist on page.");
		
		logger.info("---Verify privilege "+privCode+"-"+privName+" existence on page successfully.");
	}
	
	public void verifyPrivExistence(String privCode, String privName, String licYear, boolean existed, int expectedNums) {
		boolean existedUI = checkPrivilegeExitence(privCode, privName, licYear);
		if(existed && !existedUI)
			throw new ErrorOnPageException("Privilege "+privCode+"-"+privName+" should exist on page.");
		else if(!existed && existedUI) 
			throw new ErrorOnPageException("Privilege "+privCode+"-"+privName+" should Not exist on page.");
		
		if(existed) {
			int numsUI = getPrivilegeExitenceNums(privCode, privName, licYear);
			if(numsUI!=expectedNums)
				throw new ErrorOnPageException("Privilege "+privCode+"-"+privName+" should display "+expectedNums+" times on page.", expectedNums, numsUI);
		}
		
		logger.info("---Verify privilege "+privCode+"-"+privName+" existence on page successfully.");
	}
}
