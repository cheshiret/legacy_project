package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityInventory;

import com.activenetwork.qa.awo.datacollection.legacy.SiteInfoData;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author bzhang
 * @Date  Jul 26, 2011
 */
public class InvMgrAvailabilityPage extends InventoryManagerPage {

	private static InvMgrAvailabilityPage _instance = null;
	

	public static InvMgrAvailabilityPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrAvailabilityPage();
		}

		return _instance;
	}

	protected InvMgrAvailabilityPage() {
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "productID");
	}
	
	public void selectProducts(String product){
		browser.selectDropdownList(".id", "productID", product);
	}
	
	public void selectProductsFirstItem(){
		browser.selectDropdownList(".id", "productID", 1);
	}
	
	public void setStartDate(String startDate){
		browser.setTextField(".id", "startdate_ForDisplay", startDate);
	}
	
	public void setEndDate(String endDate){
		browser.setTextField(".id", "enddate_ForDisplay", endDate);
	}
	
	public void clickAvailabilityBtn(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Availability Grid");
	}
	
	public void setSearchCriteria(SiteInfoData searchData){
		if(null != searchData.prdName && searchData.prdName.length() >0){
			this.selectProducts( searchData.prdName);
		}else{
			this.selectProductsFirstItem();
		}
		
		if(null != searchData.arrivalDate && searchData.arrivalDate.length() >0){
			this.setStartDate( searchData.arrivalDate);
		}else{
			throw new ErrorOnDataException("please specify a start date for available search");
		}
		
		if(null != searchData.departDate && searchData.departDate.length() >0){
			this.setEndDate(searchData.departDate);
		}else{
			throw new ErrorOnDataException("please specify a end date for available search");
		}		
	}
	
	public void searchAvailability(SiteInfoData searchData){
		this.setSearchCriteria(searchData);
		this.clickAvailabilityBtn();
		this.waitLoading();
	}
	
	public IHtmlTable getAvailabilityTable(){
		IHtmlObject objs[] = browser.getTableTestObject(".text", new RegularExpression(".*\\d{2}.*\\d{2}.*",false));
		
		IHtmlTable table = null;
		if (null != objs && objs.length >0){
			table = (IHtmlTable)objs[0];
		}
		return table;
	}
	
	/**
	 * retrive available site number based on the Products, StartDate, EndDate passed.
	 * 
	 * @param searchData
	 * @return available total site number
	 */
	public int getAvailabilitySiteNum(SiteInfoData searchData){
		int count = 0;
		boolean flag = true;
		this.searchAvailability(searchData);
		IHtmlTable table = getAvailabilityTable();
		
		for (int i = 1; i < table.rowCount() ; i ++	){
			flag = true;
			for(int j = 1; j < table.columnCount(); j ++){
				if(table.getCellValue(i, j).length()>0){
					flag = false;
					break;
				}
			}
			if (flag == true){
				count = count + 1;
			}
		}		
		return count;
	}
	

}
