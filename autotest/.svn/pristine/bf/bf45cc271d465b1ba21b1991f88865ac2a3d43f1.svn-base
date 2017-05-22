package com.activenetwork.qa.awo.pages.orms.licenseManager.store;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrStoreDaliySalesActivityCommonPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author jwang
 * @Date  Feb 22, 2012
 */
public class LicMgrStoreActivityTransactionSubPage extends
		LicMgrStoreDaliySalesActivityCommonPage {
	
	private final String[] colName = new String[]{"Allocation ID",
			"Order Number", 
			"Payment/RefundID/Voucher ID", 
			"Product/Account", 
			"Fee Transaction Type/ Allocation Transaction Type",
//			"Fee Transaction Type/Allocation Transaction Type", 
			"Fee Type", 
			"Amount"};
	
	private String prefix = "LocationDailySalesBDByTransSearchCriteria-";
	
	private static LicMgrStoreActivityTransactionSubPage _instance = null;
	
	protected LicMgrStoreActivityTransactionSubPage() {}
	
	public static LicMgrStoreActivityTransactionSubPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrStoreActivityTransactionSubPage();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "LocationDailyDSalesList_LIST");		
	}
	
	public void clickGoForFilter(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Go", 1);
	}
	
	public void selectRowPerPage(String num){
		browser.selectDropdownList(".id", new RegularExpression("GridPagingBar-\\d+\\.rowsPerPage",false), num, true);
	}
	
	/**
	 * Verify whether result is more than one page.
	 * @return true - more than one; false - only one page
	 */
	public boolean isFindNext(){
	  	boolean toReturn = false;
	  	
	  	IHtmlObject[] objs=browser.getHtmlObject(".class","Html.A",".text",new RegularExpression("Next",false));
	  	if(objs.length>0){
	  	  	toReturn = true;
	  	}
	  	Browser.unregister(objs);
	  	
	  	return toReturn;
	}
	

	/** If Next button is enable, Click NextButton. */
	public void gotoNext() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",
				".text", "Next");
		if (objs.length > 0) {
			((IHtmlObject)objs[0]).click();
		}
		Browser.unregister(objs);
	}
	
	/**
	 * Verify current page is first page
	 * @return true - current page is not first page; false - current page is first page
	 */
	public boolean isFindFirst(){
	  	boolean toReturn = false;
	  	
	  	IHtmlObject[] objs=browser.getHtmlObject(".class","Html.A",".text",new RegularExpression("First",false));
	  	if(objs.length>0){
	  	  	toReturn = true;
	  	}
	  	Browser.unregister(objs);
	  	
	  	return toReturn;
	}
	
	/** If Next button is enable, Click NextButton. */
	public void gotoFirst() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",
				".text", "First");
		if (objs.length > 0) {
			((IHtmlObject)objs[0]).click();
		}
		Browser.unregister(objs);
	}
	
	/**
	 * Get column value by order number
	 * @param columnName
	 * @param ordNum
	 * @return
	 */
	public List<String> getColValueByOrdNum(int index, String ordNum){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "LocationDailyDSalesList_LIST");
		List<String> value = new ArrayList<String>();
		
		if(objs.length < 1){
			throw new ErrorOnPageException("Could not get table by id LocationDailyDSalesList_LIST.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowCount = table.rowCount();
		if(rowCount <= 1){
			throw new ErrorOnPageException("There is no tranaction records on this page");
		}
		
		// get col num by column name
		int col = table.findColumn(0, colName[index]);
		if(col < 0){
			throw new ErrorOnPageException("Could not get column num by column name "+colName[index]+".");
		}
				
		// get row num by ordNum for more than one page
		boolean controller = true;
		
		while (controller){
			
			IHtmlObject[] objs1 = browser.getTableTestObject(".id", "LocationDailyDSalesList_LIST");
			if(objs1.length < 1){
				throw new ErrorOnPageException("Could not get table by id LocationDailyDSalesList_LIST.");
			}
			
			IHtmlTable table1 = (IHtmlTable)objs1[0];
			int rowCount1 = table1.rowCount();
			
			if(rowCount1 <= 1 && !controller){
				break;
			}
			
			for(int i=0;i<rowCount1;i++){
				if(table1.getCellValue(i, 1).equalsIgnoreCase(ordNum)){
					value.add(table1.getCellValue(i, col));
//					System.out.println(i+":"+table1.getCellValue(i, col));
				}
			}
			
			controller = isFindNext();
			if(controller){
				gotoNext();
				ajax.waitLoading();
				this.waitLoading();
			}
			
			Browser.unregister(objs1);
		};
		
		//return first page
		if(isFindFirst()){
			gotoFirst();
			ajax.waitLoading();
			this.waitLoading();
		}
		
		Browser.unregister(objs);
		
		return value;
	}	
	
	public void setOrdNum(String num){
		browser.setTextField(".id", new RegularExpression(prefix+"\\d+\\.ordNum",false), num);
	}
	
	public void searchRecordsByOrdNum(String num){
		setOrdNum(num);
		clickGoForFilter();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	/**
	 * Get all records by column index
	 * @param index
	 * @return
	 */
	public List<String> getAllRecordsByColumn(int index){
		List<String> records = new ArrayList<String>();
		
		IHtmlObject[] objs = browser.getTableTestObject(".id", "LocationDailyDSalesList_LIST");
		if(objs.length < 1){
			throw new ErrorOnPageException("Could not get table by id LocationDailyDSalesList_LIST.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int rowCount = table.rowCount();
		
		for(int i=1;i<rowCount;i++){
			records.add(table.getCellValue(i, index));
		}
		
		Browser.unregister(objs);
		
		return records;
	}
	
	/**
	 * Check transaction records sorting by allocation id
	 */
	public void verifyTransactionRecordsSorting(){
		List<String> allocationList = getAllRecordsByColumn(0);
		
		for(int i=0; i<allocationList.size()-1; i++){
			if(new BigInteger(allocationList.get(i)).compareTo(new BigInteger(allocationList.get(i+1))) >0){
				throw new ErrorOnDataException("Transaction records sorting by the Payment Allocation ID displayed un-correctly.");
			}					
		}
		
		logger.info("Transaction records were displayed sorted by the Payment Allocation ID in ascending order");
		
	}
}
