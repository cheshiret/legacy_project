/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.callManager;


import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class CallMgrConsumableSaleAddItemPage extends CallMgrAddItemPage {
	static private CallMgrConsumableSaleAddItemPage _instance = null;
	
	static public CallMgrConsumableSaleAddItemPage getInstance(){
		if (null == _instance) {
			_instance = new CallMgrConsumableSaleAddItemPage();
		}

		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "content_ConsumableSalesTabs");
	}
	

	public void setProdQty(int index, String qty) {
		browser.setTextField(".id", new RegularExpression(
				"ConsumableSaleProductSelection-\\d+\\.quantity", false), qty, index);
	}
	
	public void clickAddToCart(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add to Cart",
				index);
	}
	
	private int getBlankRowsNum(int rowCount) {
		IHtmlObject[] objs = this.getConsumbleProducts();
		IHtmlTable grid = (IHtmlTable) objs[0];
		int count = 0;
		for (int i = 0; i < rowCount; i++) {
			String value = grid.getCellValue(i, 4);
			if (StringUtil.isEmpty(value)) {
				count++;
			}
		}
		Browser.unregister(objs);
		return count;
	}
	
	private int getProductIndex(String prodName) {
		IHtmlObject[] objs = this.getConsumbleProducts();
		IHtmlTable grid = (IHtmlTable) objs[0];
		
		int num = -1;
		for (int i = 0; i < grid.rowCount(); i++) {
			if (grid.getCellValue(i, 0).equals(prodName)) {
				num = i;
				break;
			}
		}
		Browser.unregister(objs);
		return num;
	}
	
	private IHtmlObject[] getConsumbleProducts(){
		IHtmlObject[] objs = browser.getTableTestObject(".id",
		"consumable_purchase");
		return objs;
	}
	
	public void addProductToCart(String prodName, String qty) {
		int rowCount=this.getProductIndex(prodName);
		if(rowCount<0){
			throw new ItemNotFoundException("Not Found Product "+prodName);
		}
		int index = rowCount-this.getBlankRowsNum(rowCount);
		
//		int count=this.getProductIndex("POS Sale");
//		if(count!=-1){//used to check whether 'POS Sale' exist from UI
//			if(rowCount>count){
//				this.setProdQty(rowCount-count-1, qty);
//			}
//		}
		if (!StringUtil.isEmpty(qty)) {
			this.setProdQty(prodName, qty);
		}
		this.clickAddToCart(index);
		ajax.waitLoading();
	}
	
	/**
	 * Get the table row in which the product is shown
	 * @param prodName
	 * @return
	 * @author Lesley Wang
	 * @date Jun 7, 2012
	 */
	public IHtmlObject[] getProdTableRows(String prodName) {
		RegularExpression rex = new RegularExpression("^"+prodName+".*", false);
		return browser.getHtmlObject(".class", "Html.TR", ".text", rex);
	}
	
	/**
	 * Set the product quantity. 
	 * Find the product table row and set the value into the quantity textfield in the row. 
	 * @param prodName
	 * @param qty
	 * @author Lesley Wang
	 * @date Jun 7, 2012
	 */
	private void setProdQty(String prodName, String qty) {
		IHtmlObject[] prodTRs = this.getProdTableRows(prodName);
		IHtmlObject prodTR = prodTRs[0];
		browser.setTextField(".id", new RegularExpression(
				"ConsumableSaleProductSelection-\\d+\\.quantity", false), qty, prodTR);
		Browser.unregister(prodTRs);
	}
	
	/**
	 * Set the product price
	 * @param prodName
	 * @param price
	 * @author Lesley Wang
	 * @date Jun 7, 2012
	 */
	private void setProdPrice(String prodName, String price) {
		IHtmlObject[] prodTRs = this.getProdTableRows(prodName);
		IHtmlObject prodTR = prodTRs[0];
		browser.setTextField(".id", new RegularExpression(
				"ConsumableSaleProductSelection-\\d+\\.price", false), price, prodTR);
		Browser.unregister(prodTRs);
	}
	
	/**
	 * Set the product's price and add it to cart.
	 * Make sure the consumable name, and order type are not empty.
	 * @param prodName
	 * @param price
	 * @author Lesley Wang
	 * @date May 15, 2012
	 */
	public void setProductPriceAndAddToCart(String prodName, String price) {
		int rowCount=this.getProductIndex(prodName);
		if(rowCount<0){
			throw new ItemNotFoundException("Not Found Product "+prodName);
		}
		int index = rowCount-this.getBlankRowsNum(rowCount);
	
		if (!StringUtil.isEmpty(price)) {
			this.setProdPrice(prodName, price);
		}
		this.clickAddToCart(index);
		ajax.waitLoading();
	}
	
	
}
