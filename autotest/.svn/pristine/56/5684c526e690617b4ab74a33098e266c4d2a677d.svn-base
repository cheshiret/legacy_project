package com.activenetwork.qa.awo.pages.orms.licenseManager.pos;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * Access this page by: Select 'Products' from top menu, and then click 'Supplies' link.
 * @author vzhao
 *
 */
public class LicMgrSuppliesListPage extends LicMgrProductPage {
	private static LicMgrSuppliesListPage instance = null;
	
	private LicMgrSuppliesListPage() {
	}
	
	public static LicMgrSuppliesListPage getInstance() {
		if(instance ==null) {
			instance = new LicMgrSuppliesListPage();
		}
		return instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Add Supply Product");
	}
	
	/**Click Add Supply Product link*/
	public void clickAddSupplyProduct() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Supply Product");
	}
	
	public void clickSupplyCode(String code){
		browser.clickGuiObject(".class", "Html.A", ".text", code);
	}
	
	@Override
	public boolean checkProductRecordExist(String prodCode) {
		return isThisSupplyExist(prodCode);
	}
	
	public boolean isThisSupplyExist(String code){
		return browser.checkHtmlObjectExists(".class", "Html.A",".text",code);		
	}
	
	private IHtmlObject[] getSupplyProductTables() {
		IHtmlObject[] objs = browser.getTableTestObject(".id", "suppliesProductGrid");
		if (objs == null || objs.length < 1) {
			throw new ItemNotFoundException("Can't find supply product table!");
		}
		return objs;
	}
	
	private int getIndexBySupplyCode(String code) {
		IHtmlObject[] objs = this.getSupplyProductTables();
		List<String> codes = (List<String>)((IHtmlTable)objs[0]).getColumnValues(1);
		while(codes.contains("")) {
			codes.remove(""); // remove the supply product group rows
		}
		int index = codes.indexOf(code);
//		int row = ((ITable)objs[0]).findRow(1, code);
		Browser.unregister(objs);
		return index;
	}
	
	public void clickAdjust(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text", "Adjust", index);
	}
	
	public void clickAdjust(String code) {
		int index = this.getIndexBySupplyCode(code);
		if (index < 1) {
			throw new ItemNotFoundException("Can't find the Supply with code=" + code);
		}
		this.clickAdjust(index - 1); // the first row is the column names
	}
	
	public void selectActiveCheckBox(){
		browser.selectCheckBox(".id", new RegularExpression("CheckboxExt-\\d+\\.checked",false), 0);
	}
	
	public void selectInactiveCheckBox(){
		browser.selectCheckBox(".id", new RegularExpression("CheckboxExt-\\d+\\.checked",false), 1);
	}
	
	public void clickGoButton(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
	/**
	 * get supplier status.
	 * @param code
	 * @return
	 */
	public String getSupplierStatusByCode(String code){
		IHtmlObject[] objs = this.getSupplyProductTables();
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> codes = (List<String>)(table).getColumnValues(1);
		/*while(codes.contains("")) {
			codes.remove(""); // remove the supply product group rows
		}*/
		int index = codes.indexOf(code);
		String status = table.getCellValue(index, 0);
		
        
		Browser.unregister(objs);
		return status;
	}
	/*
	 * check supplier active status.
	 */
	public boolean checkSupplierActive(String code){
		String status = this.getSupplierStatusByCode(code);
		if(status == "Active"){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * search all the supplier product.
	 */
	public void searchAllSupplierPrd(){
		this.selectActiveCheckBox();
		this.selectInactiveCheckBox();
		this.clickGoButton();
		ajax.waitLoading();
		this.waitLoading();
	}
	
}
