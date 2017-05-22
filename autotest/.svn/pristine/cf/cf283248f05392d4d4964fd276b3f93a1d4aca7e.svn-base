/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.common;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description: It is a common page for UWP shopping cart page
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Apr 12, 2013
 */
public abstract class UwpShoppingCartCommonPage extends UwpPage {

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id","shoppingCartForm");
	}

	private Property[] continueShoppingProp(){
		return Property.toPropertyArray(".id", "reservemore", ".text", new RegularExpression("Continue shopping", false));
	}
	
	/**
	 * Click on Abandon cart link.
	 */
	public void abandonThisCart() {
		browser.clickGuiObject(".class","Html.A",".text", "Abandon This Cart",true);
	}
	
	/**
	 * Click on link to remove select order items.
	 */
	public void clickRemoveItem() {
		browser.clickGuiObject(".id", "removeitem");
	}
	
	/**
	 * Remove the order items in cart by given index list.
	 * For example, 14 stands for remove the 1st and 4th order items.
	 * @param itemsList - item index list
	 */
	public void removeItems(String itemsList) {
		String[] listStr = itemsList.split(":");
		IHtmlObject[] objs = browser.getHtmlObject(".id","ckitems");
		for (int i = 0; i < listStr.length; i++) {
			int itemNo = Integer.parseInt(listStr[i]);
			objs[itemNo - 1].click();
			Browser.sleep(1);
		}

		Browser.unregister(objs);
		this.clickRemoveItem();
	}
	
	/**
	 * Remove first order item in cart.
	 */
	public void removeFirstItem() {
		removeItems("1");
	}
	
	/**
	 * Remove the last order item.
	 */
	public void removeLastItem() {
		removeItems(numberOfItems()+"");
	}
	
	/**
	 * Retrieve the number of order items.
	 * @return - number of order items
	 */
	public int numberOfItems() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "ckitems");
		int num = objs.length;
		Browser.unregister(objs);
		return num;
	}
	
	/**
	* Retrieve the number of contracts which the order items belong to.
	 * @return - number of contracts
	 */
	public int numberOfContracts() {
		IHtmlObject[] objs = browser.getHtmlObject(".className","partition");
		int num = objs.length;
		Browser.unregister(objs);
		return num;
	}
	
	/**
	 * Click on Check Out Shopping Cart button.
	 */
	public void clickCheckOutShoppingCart() {
		browser.clickGuiObject(".id", "chkout",true);
	}
	
	/**
	 * Verify whether the given string match text in table cell.
	 * @param verifyStr - given verify string
	 * @return 0 - not found; not 0 - found
	 */
	public int tableData(String verifyStr) {
		IHtmlObject[] foundTOs = browser.getHtmlObject(".class","Html.TABLE",".id", "table1");

		IHtmlTable table = (IHtmlTable)foundTOs[0];
		int count = 0;
		for (int i = 0; i < table.rowCount(); i++) {
			for (int j = 0; j < table.columnCount(); j++) {
				if (table.getCellValue(i, j) != null) {
					String str = (String) table.getCellValue(i, j).toString();
					if (str.matches(verifyStr)) {
						count++;
					}
				}
			}
		}

		Browser.unregister(foundTOs);
		return count;
	}
	
	/**
	 * Retrieve the fee by given name.
	 * @param feeName - special fee name
	 * @return - fee
	 */
	public String getFeeByName(String feeName) {
	  	IHtmlObject[] foundTOs = browser.getTableTestObject(".id", "table1");
		String text = foundTOs[0].getProperty(".text");
		
		String[] temp = null;

		if(!text.contains(feeName))
			return null;
		
		temp = text.split(feeName + ": ?");
	
		String fee="";
		if(feeName.equalsIgnoreCase("Total")) {// get the total fee
	  	  	int lastDollarIndex=text.lastIndexOf("$");
	  	  	fee = text.substring(lastDollarIndex + 1);
		}else{
		  	fee = temp[1].split(" ")[0].replaceAll("\\$","");
		}
		Browser.unregister(foundTOs);
		return fee;
	}
	
	public String getTotalAmount(){
		return this.getFeeByName("Total");
	}
	
	/**
	 * Verify specific fee type exist in shopping card page
	 * @param feeType
	 * @param exist true: feeType should exist in shopping cart page
	 *              false: feeType shouldn't exist in shopping cart pag
	 */
	public void checkFeeTypeExist(String feeType, boolean exist){
	  	IHtmlObject[] foundTOs = browser.getTableTestObject(".id", "table1");
		String text = foundTOs[0].getProperty(".text");
		
		if(exist){
			if(!text.contains(feeType)){
				throw new ErrorOnDataException("Fee type = "+feeType+ " should be existed");
			}
		}else{
			if(text.contains(feeType)){
				throw new ErrorOnDataException("Fee type = "+feeType+ " should not be existed");
			}
		}
		Browser.unregister(foundTOs);
	}
	
	/**
	 * Click on Book More Reservation link.
	 */
	public void gotoBookMoreReservation() {
		browser.clickGuiObject(".id", "reservemore");
	}
	
	/**
	 * Retrieve the text in shopping cart page.
	 * @return - text in shopping cart
	 */
	public String getTextInShoppingCart() {
		IHtmlObject[] obj = browser.getTableTestObject(".id", "table1");
		String content = obj[0].getProperty(".text");
		Browser.unregister(obj);

		return content;
	}
	
	/**
	 * Get number of order items
	 * @return
	 */
	public int getNumOfOrderItems(){
		Property[] p = Property.toPropertyArray(".id", "ckitems");
		IHtmlObject[] objs = browser.getCheckBox(p);
		if(null==objs || objs.length<1){
			throw new ObjectNotFoundException("Checked Shopping Cart Items can't be found.");
		}
		
		int numOfOrderItems = objs.length;
		
		Browser.unregister(objs);
		return numOfOrderItems;
	}
	
	public IHtmlObject[] getShoppingCartTable(){
		IHtmlObject[] foundTOs = browser.getTableTestObject(".id", "table1");
		if(foundTOs==null || foundTOs.length<1){
			throw new ObjectNotFoundException("Shopping cat table can't be found.");
		}
		
		return foundTOs;
	}
	
	/**
	 * Get shopping cat table content
	 * @return
	 */
	public String getShoppingCartTableContent(){
		IHtmlObject[] foundTOs = this.getShoppingCartTable();
		String text = foundTOs[0].getProperty(".text");
		
		Browser.unregister(foundTOs);
		return text;
	}
	
    public boolean checkContinueShoppingExist(){
    	return browser.checkHtmlObjectExists(".id", "reservemore", ".text", new RegularExpression("Continue shopping", false));
    }
    
	/**
	 * Click "Continue shopping" link
	 */
    public void clickContinueShoppinLink(){
    	browser.searchObjectWaitExists(continueShoppingProp(), LONG_SLEEP);
    	browser.clickGuiObject(continueShoppingProp());
    }
    
    /**
     * Get cart link, such as "Items: 2"
     * @return
     */
    public String getCartLink(){
    	return browser.getObjectText(".id", "cartLink").replaceAll("\\s+", "");
    }
    
    public void verifyCartLinkValue(String theExpected){
    	String theActual = getCartLink();
    	if(!MiscFunctions.compareResult("Cart link value in shopping cart page", theExpected, theActual)){
    		throw new ErrorOnPageException("Cart link value is wrong in shopping cart page. Please find the detaisl from previous logs.");
    	}
    }
}
