package com.activenetwork.qa.awo.pages.orms.callManager;

import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class CallMgrOrigPrivSaleAddItemPage extends CallMgrAddItemPage {
	static private CallMgrOrigPrivSaleAddItemPage _instance = null;
	
	static public CallMgrOrigPrivSaleAddItemPage getInstance(){
		if (null == _instance) {
			_instance = new CallMgrOrigPrivSaleAddItemPage();
		}

		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "privilege_purchase");
	}
	
	private String prefix = "PrivilegeProductForSaleSearchCriteria-\\d+\\.";
	
	public void clickShowAll(){
		browser.selectCheckBox(".id", new RegularExpression(prefix+"showAll", false), 0);
	}
	
	public void clickBypassCache(){
		browser.selectCheckBox(".id", new RegularExpression(prefix+"bypassCache", false), true);
	}
	
	/**
	 * The method used to add specify product to cart
	 * 
	 * @param prodName
	 * @param licenseYear
	 * @param qty
	 */
	public void addProductToCart(String prodName, String licenseYear, String qty) {
		int index = this.getProductIndex(prodName, licenseYear)
				- this.getBlankRowsNum(prodName);
		this.selectProdQty(index, qty);
		this.clickAddToCart(index);
		ajax.waitLoading();
		this.waitLoading();
	}
	
	/**
	 * The method used to get the row number for given product
	 * 
	 * @param prodName
	 * @return
	 */
	private int getProductIndex(String prodName, String licenseYear) {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"privilege_purchase");
		IHtmlTable grid = (IHtmlTable) objs[0];
		boolean found = false;
		int num = -1;
		for (int i = 0; i < grid.rowCount(); i++) {
			if (grid.getCellValue(i, 1).equals(prodName)
					&& grid.getCellValue(i, 0).equals(licenseYear)) {
				found = true;
				num = i;
				break;
			}
		}
		Browser.unregister(objs);
		if (!found) {
			throw new ItemNotFoundException("Could Not Found Given Product '"
					+ prodName + "'.");
		}
		return num;
	}
	
	private int getBlankRowsNum(String prodName) {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"privilege_purchase");
		IHtmlTable grid = (IHtmlTable) objs[0];
		int count = 1;
		for (int i = 0; i < grid.rowCount(); i++) {
			String value = grid.getCellValue(i, 1);
			if (StringUtil.isEmpty(value)) {
				count++;
			}
			if (value.equals(prodName)) {
				break;
			}
		}
		Browser.unregister(objs);
		return count;
	}
	
	private void selectProdQty(int index, String qty) {
		browser.selectDropdownList(".id", new RegularExpression(
						"PrivilegeProductForTransaction-\\d+\\.qty", false),
						qty, index);
	}
	/**
	 * get privilege price.
	 * @param prodName
	 * @param licenseYear
	 * @return
	 */
	public String getPriPrice(String prodName,String licenseYear){
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"privilege_purchase");
		IHtmlTable grid = (IHtmlTable) objs[0];
		int index = this.getProductIndex(prodName, licenseYear);
		if(index<=-1){
			throw new ErrorOnPageException("No Specific product was found");
		}
		String price =  grid.getRowValues(index).get(2);
		Browser.unregister(objs);
		return  price;
	}
	
	
}
