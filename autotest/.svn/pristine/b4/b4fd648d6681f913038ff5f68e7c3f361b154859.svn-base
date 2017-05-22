package com.activenetwork.qa.awo.pages.orms.callManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
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
 * @author jwang7
 * @Date  Feb 29, 2012
 */
public class CallMgrReplacePrivSaleAddItemPage extends CallMgrAddItemPage {
	static private CallMgrReplacePrivSaleAddItemPage _instance = null;
	
	static public CallMgrReplacePrivSaleAddItemPage getInstance(){
		if (null == _instance) {
			_instance = new CallMgrReplacePrivSaleAddItemPage();
		}

		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "privilege_duplcate");
	}
	
	/**
	 * Replace privilege with order number
	 * @param privilegeOrdNum
	 * @return
	 */
	public boolean replacePrivilegeWithOrdNum(String privilegeOrdNum){
		return this.replacePrivilege(privilegeOrdNum, null);
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

		int counter = 0;
		int length =0;
		
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

		Browser.unregister(objs);
		return canBeReplaced;
	}

	private List<String> getRowByPriNum(String privilegeNumber){

		IHtmlObject[] objs = browser.getTableTestObject(".id","privilege_duplcate");
		IHtmlTable table = (IHtmlTable) objs[0];

		int row = table.findRow(2, privilegeNumber);
		List<String> rowList = table.getRowValues(row);
		Browser.unregister(objs);
		if(null == rowList || rowList.size() < 1){
			throw new ErrorOnPageException("Can't find a record which privilege number is "+privilegeNumber);
		}
		return rowList;
	}

	public String getProductByPriNum(String privilegeNumber){
		String pricing = this.getRowByPriNum(privilegeNumber).get(3);
		return pricing.replaceAll("\\$", "");
	}

	public String getPricingByPriNum(String privilegeNumber){
		String pricing = this.getRowByPriNum(privilegeNumber).get(5);
		return pricing.replaceAll("\\$", "");
	}
	
	/**
	 * 1.Verify the list of replacement privilege should only contains that particular Privilege in replacement privilege page.
	 * 2.Verify whether pricing is correct or not in replacement privilege page.
	 * @param expectPurchasingName
	 * @param expectPricing
	 * @param privilegeNum
	 */
	public void verifyDupPriPricing(String expectProduct, String expectPricing, String privilegeNum){
		boolean result = true;
		
		// verify duplicate privilege purchasing name.
		String actualProduct = this.getProductByPriNum(privilegeNum);
		result &= MiscFunctions.compareResult("Pricing", expectProduct, actualProduct);

		// verify pricing.
		String actualPricing = this.getPricingByPriNum(privilegeNum);
		result &= MiscFunctions.compareResult("Pricing", expectPricing, actualPricing);
		
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
		String parentId = parent[0].getProperty(".id");
		IHtmlObject children[] = browser.getHtmlObject(".class", "Html.TR", ".className", new RegularExpression("(|oddrow  )child-of-" + parentId + " initialized", false));
		if(children.length < 1) {
			throw new ItemNotFoundException("Can't find any privilege instances under Order#=" + orderNum + ".");
		}
		
		// key is product name, value is pricing.
		Map<String, String> productMap = new HashMap<String, String>();
		for(int i = 0; i < children.length; i ++) {
			productMap.put(children[i].getChildren()[1].getProperty(".text"), children[i].getChildren()[5].getProperty(".text").replaceAll("\\$", ""));
		}
		
		Browser.unregister(parent);
		Browser.unregister(children);
		return productMap;
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
				result &= MiscFunctions.compareResult("Pricing", Double.valueOf(expectPriceList.get(i)),  Double.valueOf(productMap.get(purchasingNameList.get(i))));
			}
		}

		if(!result){
			throw new ErrorOnPageException("Not all the check points are passed. Check logs abve.");
		} else {
			logger.info("Replacement list and pricing is correct.");
		}
	}
	
	private String prefix = "PrivilegeProductForSaleSearchCriteria-\\d+\\.";
	public void selectShowAll(){
		browser.selectCheckBox(".id", new RegularExpression(prefix+"showAll", false), 0);
	}
	
	public void selectBypassCache(){
		browser.selectCheckBox(".id", new RegularExpression(prefix+"bypassCache", false), true);
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
	 * 
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
		result &= MiscFunctions.compareResult("Total Price",  Double.valueOf(expectTotalPrice),  Double.valueOf(actualPrice));
	
		if(!result){
			throw new ErrorOnPageException("Not all the check points are passed. Check logs abve.");
		} else {
			logger.info("Replacement list and pricing is correct.");
		}
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
		result &= MiscFunctions.compareResult("Total Price", expectTotalPrice, actualPrice);
		
		if(!result){
			throw new ErrorOnPageException("Not all the check points are passed. Check logs abve.");
		} else {
			logger.info("Replacement list and pricing is correct.");
		}
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
}
