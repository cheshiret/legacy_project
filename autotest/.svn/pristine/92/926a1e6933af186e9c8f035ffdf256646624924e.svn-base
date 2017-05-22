/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrAddItemPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.NotSupportedException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author ssong
 * @Date  Oct 31, 2011
 */
public class LicMgrReplacePrivSaleAddItemPage extends LicMgrAddItemPage{

	private static LicMgrReplacePrivSaleAddItemPage _instance = null;
	
	private LicMgrReplacePrivSaleAddItemPage(){
	}
	
	public static LicMgrReplacePrivSaleAddItemPage getInstance(){
		if(_instance == null){
			_instance = new LicMgrReplacePrivSaleAddItemPage();
		}
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "privilege_duplcate");
	}
	
	/**
	 * This method used to get product name from privilege purchase list
	 * 
	 * @return
	 */
	public List<String> getProductInThePurchaseList() {
		List<String> prdName = new ArrayList<String>();
		IHtmlObject[] objs = browser.getTableTestObject(".id","privilege_duplcate");
		IHtmlTable table = (IHtmlTable) objs[0];

		int prdouctCol = table.findColumn(0, "Product");
		for (int i = 0; i < table.rowCount(); i++) {
			String product = table.getCellValue(i, prdouctCol);
			prdName.add(product);
		}
		Browser.unregister(objs);
		return prdName;
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
			throw new ItemNotFoundException("Could Not Found Given Product "
					+ prodName);
		}
		return num;
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
	
	/**
	 * Replace original privilege order in Add Item page - Replacement
	 * Privileges sub page
	 * 
	 * @param privilegeOrdNum
	 *            - the order number of privilege order needed to be replaced
	 * @return
	 */
	private boolean replacePrivilege(String privilegeOrdNum, String privilegeNum) {
		logger.info("Replace privilege order(#=" + privilegeOrdNum + ")");

		boolean canBeReplaced = false;

		IHtmlObject objs[] = browser.getTableTestObject(".id",
				"privilege_duplcate");
		IHtmlTable duplicateTable = null;
		if (objs!=null && objs.length > 0) {
			duplicateTable = (IHtmlTable) objs[0];
		} else {
			throw new ItemNotFoundException("Cannot find the Table object.");
		}
		int length =0;
		int counter = 0;
		
//		if(MiscFunctions.isQA1()) {					
			if(null != privilegeNum && privilegeNum.length()>0){
				for (int i =0; i< duplicateTable.rowCount(); i ++){
					if (!(duplicateTable.getCellValue(i, 2).equalsIgnoreCase( privilegeNum)) && 
							duplicateTable.getCellValue(i, 6).equalsIgnoreCase("Add to Cart")){
						counter ++;
					}else if (duplicateTable.getCellValue(i, 2).equalsIgnoreCase( privilegeNum) && 
							duplicateTable.getCellValue(i, 6).equalsIgnoreCase("Add to Cart")){
						break;
					}
					
					length++;
				}
			}
			
			if(null !=privilegeOrdNum && privilegeOrdNum.length()>0){
				
				for (int i =0; i< duplicateTable.rowCount(); i ++){
					if (!(duplicateTable.getCellValue(i, 0).equalsIgnoreCase("Order " + privilegeOrdNum)) &&
							duplicateTable.getCellValue(i, 6).equalsIgnoreCase("Add to Cart")){
						counter ++;
					
					}else if(duplicateTable.getCellValue(i, 0).equalsIgnoreCase("Order " + privilegeOrdNum) &&
							 duplicateTable.getCellValue(i, 6).equalsIgnoreCase("Add to Cart")){
						break;
					}
					
					length++;
				}
			}
			if (length == duplicateTable.rowCount()) {
				canBeReplaced = false;
			} else {
				this.clickAddToCart(counter);
				ajax.waitLoading();
				canBeReplaced = true;
			}
//		} 
		
//		else {
//			String contract=getContract();
//			String env=TestProperty.getProperty("target_env");
//			String[] priv_nums=DataBaseFunctions.getPrivilegeNumber(privilegeOrdNum, TestProperty.getProperty(env+".db.schema.prefix")+contract);
//			int[] index=new int[priv_nums.length];
//			canBeReplaced=true;
//			for(int j=0;j<priv_nums.length;j++) {
//				counter=-1;
//				index[j]=-1;
//				for (int i =0; i< duplicateTable.rowCount(); i ++){
//					if(duplicateTable.getCellValue(i, 6).equalsIgnoreCase("Add to Cart")) {
//						counter++;
//					}
//					
//					if ((duplicateTable.getCellValue(i, 2).equalsIgnoreCase( priv_nums[j]))){
//						index[j]=counter;
//						break;
//					}
//					
//				}
//				
//				canBeReplaced = canBeReplaced && index[j]>=0;
//				
//			}
//			clickAddToCart(index);			
//		}
		
		

		Browser.unregister(objs);
		return canBeReplaced;
	}
	
//	public boolean replacePrivilegeWithPrivNum(String... privilegeNum) {
//		HtmlObject objs[] = browser.getTableTestObject(".id",
//				"privilege_duplcate");
//		ITable duplicateTable = null;
//		if (objs!=null && objs.length > 0) {
//			duplicateTable = (ITable) objs[0];
//		} else {
//			throw new ItemNotFoundException("Cannot find the Table object.");
//		}
//		
//		
//	}
	
	/**
	 * Replace privilege with order number
	 * @param privilegeOrdNum
	 * @return
	 */
	public boolean replacePrivilegeWithOrdNum(String privilegeOrdNum){
		return this.replacePrivilege(privilegeOrdNum, null);
	}
	
	public void verifyPrivilegeOrderCanBeReplacedOrNot(String privilegeOrdNum, boolean expReplace) {
		logger.info("Verify whether the privilege order(#=" + privilegeOrdNum
				+ ") can be replaced or not.");

		boolean canBeReplaced = true;
		// try to replace privilege order in Replacement Privileges sub page of
		// Add Item page
		this.clickReplacePrivilege();
		ajax.waitLoading();
		canBeReplaced = this.replacePrivilegeWithOrdNum(privilegeOrdNum);
		boolean result = MiscFunctions.compareResult("Can be replacement", expReplace, canBeReplaced);
		if (!result) {
			throw new ActionFailedException("The privilege order(#="
					+ privilegeOrdNum + ") can be replacement info is not correct, please check log.");
		} else {
			logger.info("----The privilege order(#=" + privilegeOrdNum
					+ ") can be replacement info is correct.");
		}
	}
	
	private String prefix = "PrivilegeProductForSaleSearchCriteria-\\d+\\.";
	public void clickShowAll(){
		browser.selectCheckBox(".id", new RegularExpression(prefix+"showAll", false), 0);
	}
	
	public void clickBypassCache(){
		//James: click ByPassCache is not allowed for automation test. 
		throw new NotSupportedException("Click ByPassCache is not allowed for automation test. ");
//		browser.selectCheckBox(".id", new RegularExpression(prefix+"bypassCache", false), true);
	}
	
	/**
	 * Replace privilege with privilege number
	 * @param privilegeNum
	 * @return
	 */
	public boolean replacePrivilegeWithPrivNum(String privilegeNum){
		return this.replacePrivilege(null, privilegeNum);
	}
	
	/**
	 * Get the all privilege instances purchasing names under a specific order and document.
	 * @param orderNum
	 * @param document
	 * @return
	 */
	public List<String> getPrivilegeNamesByDocument(String orderNum, String document) {
		IHtmlObject parent[] = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Order " + orderNum + ".*" + document, false));
		if(parent.length < 1) {
			throw new ItemNotFoundException("Can't find '" + orderNum + "' and '"+document+"' TR object.");
		}
		String parentId = parent[0].getProperty(".id");
		IHtmlObject children[] = browser.getHtmlObject(".class", "Html.TR", ".className", new RegularExpression("(|oddrow  )child-of-" + parentId + " initialized", false));
		if(children.length < 1) {
			throw new ItemNotFoundException("Can't find any privilege instances under Order#=" + orderNum + ".");
		}
		
		List<String> privilegePurchasingNames = new ArrayList<String>();
		for(int i = 0; i < children.length; i ++) {
			privilegePurchasingNames.add(children[i].getChildren()[1].getProperty(".text"));
		}
		
		Browser.unregister(parent);
		Browser.unregister(children);
		return privilegePurchasingNames;
	}
	
	/**
	 * Get the all privilege instances purchasing names under a specific order
	 * @param orderNum
	 * @return
	 */
	public List<String> getPrivilegeNames(String orderNum) {
		IHtmlObject parent[] = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Order " + orderNum, false));
		if(parent.length < 1) {
			throw new ItemNotFoundException("Can't find '" + orderNum + "' TR object.");
		}
		String parentId = parent[0].getProperty(".id");
		IHtmlObject children[] = browser.getHtmlObject(".class", "Html.TR", ".className", new RegularExpression("(|oddrow  )child-of-" + parentId + " initialized", false));
		if(children.length < 1) {
			throw new ItemNotFoundException("Can't find any privilege instances under Order#=" + orderNum + ".");
		}
		
		List<String> privilegePurchasingNames = new ArrayList<String>();
		for(int i = 0; i < children.length; i ++) {
			privilegePurchasingNames.add(children[i].getChildren()[1].getProperty(".text"));
		}
		
		Browser.unregister(parent);
		Browser.unregister(children);
		return privilegePurchasingNames;
	}
	
	public String getPricingByPurchaseName(String prdPurchaseName){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression(prdPurchaseName, false));
		if(objs.length < 0){
			throw new ErrorOnPageException("Can't find record which contais given purchase name "+prdPurchaseName);
		}
		
		IHtmlObject[] td = browser.getHtmlObject(".class", "Html.TD", objs[0]);
		if(td.length < 0){
			throw new ErrorOnPageException("Can't find records!");
		}
		return td[5].getProperty(".text").replaceAll("\\$", "");
	}
	
	private List<String> getRowByPriNum(String privilegeNumber){

		IHtmlObject[] objs = browser.getTableTestObject(".id","privilege_duplcate");
		IHtmlTable table = (IHtmlTable) objs[0];

		int row = table.findRow(2, privilegeNumber);
		List<String> rowList = table.getRowValues(row);
		Browser.unregister(objs);
		if(null == rowList || rowList.size() < 1){
			throw new ItemNotFoundException("Can't find a record which privilege number is "+privilegeNumber);
		}
		return rowList;
	}

	public String getProductByPriNum(String privilegeNumber){
		String pricing = this.getRowByPriNum(privilegeNumber).get(1);
		return pricing.replaceAll("\\$", "");
	}

	public String getPricingByPriNum(String privilegeNumber){
		String pricing = this.getRowByPriNum(privilegeNumber).get(5);
		return pricing.replaceAll("\\$", "");
	}

	/**
	 * Verify product and pricing when PrivilegeReplacementTransactionCoverage is set to 1.
	 * @param privilegeNumList
	 * @param purchasingNameList
	 * @param expectPriceList
	 */
	public void verifyDupPriPricing(String[] privilegeNumList, List<String> purchasingNameList,  List<String> expectPriceList){
		boolean result = true;
		for(int i=0; i<privilegeNumList.length; i++){
			String privilegeNum = privilegeNumList[i];
			
			// verify duplicate privilege purchasing name.
			String actualProduct = this.getProductByPriNum(privilegeNum);
			result &= MiscFunctions.compareResult("No."+(i+1)+" Purchasing Name", purchasingNameList.get(i), actualProduct);

			// verify pricing.
			String actualPricing = this.getPricingByPriNum(privilegeNum);
			result &= MiscFunctions.compareResult("No."+(i+1)+" Pricing", Double.valueOf(expectPriceList.get(i)), Double.valueOf(actualPricing));
		}
		
		if(!result){
			throw new ErrorOnPageException("Not all the check points are passed. Check logs abve.");
		} else {
			logger.info("Replacement list and pricing is correct.");
		}
	}

	/**
	 * Get the all privilege instances purchasing names and pricing under a specific order
	 * @param orderNum
	 * @return
	 */
	public Map<String, String> getProductNameAndPricingByOrderNum(String orderNum) {
		IHtmlObject parent[] = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Order " + orderNum, false));
		if(parent.length < 1) {
			throw new ItemNotFoundException("Can't find '" + orderNum + "' TR object.");
		}

		Map<String, String> productMap = new HashMap<String, String>();
		String parentId = parent[0].getProperty(".id");
		IHtmlObject children[] = browser.getHtmlObject(".class", "Html.TR", ".className", new RegularExpression("(|oddrow  )child-of-" + parentId + " initialized", false));
		if(children.length < 1) {
			throw new ItemNotFoundException("Can't find any privilege instances under Order#=" + orderNum + ".");
		}
		
		// key is product name, value is pricing.
		for(int j = 0; j < children.length; j ++) {
			productMap.put(children[j].getChildren()[1].getProperty(".text"), children[j].getChildren()[5].getProperty(".text").replaceAll("\\$", ""));
		}
		
		Browser.unregister(parent);
		Browser.unregister(children);
		return productMap;
	}

	/**
	 * Get the all privilege instances purchasing names and pricing under a specific order and document
	 * @param orderNum
	 * @param document
	 * @return
	 */
	public Map<String, String> getProductNameAndPricingByDocument(String orderNum, String document) {
		IHtmlObject parent[] = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Order " + orderNum +".*" + document, false));
		if(parent.length < 1) {
			throw new ItemNotFoundException("Can't find '" + orderNum + "' TR object.");
		}

		Map<String, String> productMap = new HashMap<String, String>();
		String parentId = parent[0].getProperty(".id");
		IHtmlObject children[] = browser.getHtmlObject(".class", "Html.TR", ".className", new RegularExpression("(|oddrow  )child-of-" + parentId + " initialized", false));
		if(children.length < 1) {
			throw new ItemNotFoundException("Can't find any privilege instances under Order#=" + orderNum + ".");
		}
		
		// key is product name, value is pricing.
		for(int j = 0; j < children.length; j ++) {
			productMap.put(children[j].getChildren()[1].getProperty(".text"), children[j].getChildren()[5].getProperty(".text").replaceAll("\\$", ""));
		}
		
		Browser.unregister(parent);
		Browser.unregister(children);
		return productMap;
	}
	
	public String getTotalPriceByDocument(String orderNum, String document){
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Order " + orderNum +".*" + document, false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find '" + orderNum + "' and '"+document+"' TR object.");
		}
		
		String text = (objs[0].getProperty(".text").split("\\$")[1]).split(" ")[0];
		Browser.unregister(objs);
		return text;
	}
	
	/**
	 * Verify product and pricing when PrivilegeReplacementTransactionCoverage is set to 3.
	 * Privilege should be grouped by order number and document.
	 * @param orderNum
	 * @param purchasingNameList
	 * @param expectTotalPrice
	 */
	public void verifyDupPriPricingPerDoc(String orderNum, String document, List<String> purchasingNameList,  String expectTotalPrice){
		boolean result = true;
		
		logger.info("Verify product and pricing when PrivilegeReplacementTransactionCoverage is set to 3.");
		List<String> actualPurchasingNameList = this.getPrivilegeNamesByDocument(orderNum, document);
		
		// verify product list
		if(!MiscFunctions.compareResult("Size of Product List", purchasingNameList.size(), actualPurchasingNameList.size())){
			throw new ErrorOnPageException("There should be "+purchasingNameList.size()+" products under order "+ orderNum + " and document " + document);
		} else {
			for(int i=0; i<purchasingNameList.size(); i++){
				result &= MiscFunctions.compareResult("No."+(i+1)+" Product", purchasingNameList.get(i), actualPurchasingNameList.get(i));
			}
		}
		
		// verify pricing
		String actualPrice = this.getTotalPriceByDocument(orderNum, document);
		result &= MiscFunctions.compareResult("Total Price", Double.valueOf(expectTotalPrice), Double.valueOf(actualPrice));
		
		if(!result){
			throw new ErrorOnPageException("Not all the check points are passed. Check logs abve.");
		} else {
			logger.info("Replacement list and pricing is correct.");
		}
	}
	
	/**
	 * Verify product and pricing when PrivilegeReplacementTransactionCoverage is set to 2.
	 * Privilege should be grouped by order number and document.
	 * @param orderNum
	 * @param purchasingNameList
	 * @param expectTotalPrice
	 */
	public void verifyDupPriPricingPerDoc(String orderNum, String document, List<String> purchasingNameList, List<String> expectPriceList){
		boolean result = true;
		
		logger.info("Verify product and pricing when PrivilegeReplacementTransactionCoverage is set to 2.");

		Map<String, String> productMap = getProductNameAndPricingByDocument(orderNum, document);
		if(!MiscFunctions.compareResult("Size of Product List", purchasingNameList.size(), productMap.size())){
			throw new ErrorOnPageException("There should be "+purchasingNameList.size()+" products under order "+ orderNum);
		} else {
			for(int i=0; i < purchasingNameList.size(); i++){
				result &= MiscFunctions.compareResult("Pricing", Double.valueOf(expectPriceList.get(i)), Double.valueOf(productMap.get(purchasingNameList.get(i))));
			}
		}

		if(!result){
			throw new ErrorOnPageException("Not all the check points are passed. Check logs abve.");
		} else {
			logger.info("Replacement list and pricing is correct.");
		}
	}

	/**
	 * Verify product and pricing when PrivilegeReplacementTransactionCoverage is set to 4.
	 * Privilege should be grouped by order number.
	 * @param orderNum
	 * @param purchasingNameList
	 * @param expectPriceList
	 */
	public void verifyDupPriPricingPerOrder(String orderNum, List<String> purchasingNameList,  List<String> expectPriceList) {
		boolean result = true;
		Map<String, String> productMap = getProductNameAndPricingByOrderNum(orderNum);
		if(!MiscFunctions.compareResult("Size of Product List", purchasingNameList.size(), productMap.size())){
			throw new ErrorOnPageException("There should be "+purchasingNameList.size()+" products under order "+ orderNum);
		} else {
			for(int i=0; i < purchasingNameList.size(); i++){
				result &= MiscFunctions.compareResult("Pricing", Double.valueOf(expectPriceList.get(i)), Double.valueOf(productMap.get(purchasingNameList.get(i))));
			}
		}

		if(!result){
			throw new ErrorOnPageException("Not all the check points are passed. Check logs abve.");
		} else {
			logger.info("Replacement list and pricing is correct.");
		}
	}
	
	/**
	 * Get total price when PrivilegeReplacementTransactionCoverage is set to 5.
	 * @param orderNum
	 * @return
	 */
	public String getTotalPriceByOrderNum(String orderNum){
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Order " + orderNum, false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find '" + orderNum + "' TR object.");
		}
		
		String text = (objs[0].getProperty(".text").split("\\$")[1]).split(" ")[0];
		Browser.unregister(objs);
		return text;
	}
	
	/**
	 * Verify product and pricing when PrivilegeReplacementTransactionCoverage is set to 5.
	 * Privilege should be grouped by order number.
	 * @param orderNum
	 * @param purchasingNameList
	 * @param expectTotalPrice
	 */
	public void verifyDupPriPricingPerOrder(String orderNum, List<String> purchasingNameList, String expectTotalPrice){
		boolean result = true;
		List<String> actualPurchasingNameList = this.getPrivilegeNames(orderNum);
		
		// verify product list
		if(!MiscFunctions.compareResult("Size of Product List", purchasingNameList.size(), actualPurchasingNameList.size())){
			throw new ErrorOnPageException("There should be "+purchasingNameList.size()+" products under order "+ orderNum);
		} else {
			for(int i=0; i<purchasingNameList.size(); i++){
				result &= MiscFunctions.compareResult("No."+(i+1)+" Product", purchasingNameList.get(i), actualPurchasingNameList.get(i));
			}
		}
		
		// verify pricing
		String actualPrice = this.getTotalPriceByOrderNum(orderNum);
		result &= MiscFunctions.compareResult("Total Price", Double.valueOf(expectTotalPrice), Double.valueOf(actualPrice));
	
		if(!result){
			throw new ErrorOnPageException("Not all the check points are passed. Check logs abve.");
		} else {
			logger.info("Replacement list and pricing is correct.");
		}
	}
}
