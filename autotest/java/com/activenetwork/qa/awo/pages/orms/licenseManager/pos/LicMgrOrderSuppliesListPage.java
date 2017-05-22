/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.pos;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: The page can be accessed by: select 'Order Supplies' from Admin dropdown list on the top menu 
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Sep 4, 2012
 */
public class LicMgrOrderSuppliesListPage extends LicMgrCommonTopMenuPage {

	private static LicMgrOrderSuppliesListPage instance = null;
	
	private LicMgrOrderSuppliesListPage() {
	}
	
	public static LicMgrOrderSuppliesListPage getInstance() {
		if(instance ==null) {
			instance = new LicMgrOrderSuppliesListPage();
		}
		return instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "suppliesProductList");
	}

	public void clickAddToCart(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add to Cart", index);
	}

	public void clickGoToCart() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go to Cart");
	}
	
	private IHtmlObject[] getSuppliesProductListTables() {
		IHtmlObject[] objs = browser.getTableTestObject(".id", "suppliesProductList");
		if (objs == null || objs.length < 1) {
			throw new ItemNotFoundException("Can't find supplies product list tables");
		}
		return objs;
	}
	
	public int getSuppliesProductRowIndex(String code, String name) {
		IHtmlObject[] objs = this.getSuppliesProductListTables();
		int row = ((IHtmlTable)objs[0]).findRow(0, code+"-"+name);
		Browser.unregister(objs);
		return row;
	}
	
	public void setProdQty(int index, String qty) {
		browser.setTextField(".id", new RegularExpression(
				"SupplyForPurchase-\\d+.qty", false), qty, index);
	}
	
	public void addProductToCart(String code, String name, String qty) {
		int rowCount=this.getSuppliesProductRowIndex(code, name);
		if(rowCount<1){
			throw new ItemNotFoundException("Not Found Product "+ code+"-"+name);
		}
		int index = rowCount-1;
		if (!StringUtil.isEmpty(qty)) {
			this.setProdQty(index, qty);
		}
		this.clickAddToCart(index);
		ajax.waitLoading();
	}
}
