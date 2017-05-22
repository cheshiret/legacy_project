package com.activenetwork.qa.awo.pages.orms.common.ticket;

import java.util.HashMap;
import java.util.Map;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * @Description: This is Charges label page under ticket order detail page:VM --->Ticket Order Search/List ---> Ticket Order Details
 * @author pchen
 * @Date  Septem 10, 2012
 */
public class OrmsTicketOrderDetailsChargesLabelPage extends OrmsTicketOrderDetailsPage{
    static private OrmsTicketOrderDetailsChargesLabelPage _instance = null;
	
    private OrmsTicketOrderDetailsChargesLabelPage(){}
	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsTicketOrderDetailsChargesLabelPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsTicketOrderDetailsChargesLabelPage();
		}

		return _instance;
	}
	
	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(
				".class", "Html.DIV",
				".id", "res-charges");
	}
	
	/**
	 * Get pos sale info in ticket order detail page under Charges label
	 * @param posSaleNum
	 * @return
	 */
	public Map<String, String> getPOSSaleDetail(String posSaleNum){
		Map<String, String> values = new HashMap<String, String>();
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		int rows;
		objs = browser.getTableTestObject(".id",new RegularExpression("grid_\\d+",false),".text",new RegularExpression("^POS Sale#.*",false) );
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find pos sale records table object.");
		}
		table = (IHtmlTable) objs[0];
		rows = table.rowCount();
		for(int i=1; i<rows; i++){
			if(posSaleNum.equals(table.getCellValue(i, 1))){
				logger.info("Find record on page OrmsTicketOrderDetailsChargesLabelPage in row:"+i);
				values.put("saleNum", table.getCellValue(i, 1));
				values.put("orderStatus", table.getCellValue(i, 2));
				values.put("saleDate", table.getCellValue(i, 3));
				values.put("saleLocation", table.getCellValue(i, 4));
				values.put("Price", table.getCellValue(i, 5));
				values.put("Paid", table.getCellValue(i, 6));
				values.put("Balance", table.getCellValue(i, 7));
				break;
			}
		}
		if(values.size() == 0){
			throw new ItemNotFoundException(
					"Can't find pos sale record in the pos sale table.");
		}
        Browser.unregister(objs);
        return values;
	}
	
	/**
	 * Click Pos sale number 
	 * @param posSaleNum
	 */
	public void clickPosSaleNum(String posSaleNum){
		browser.clickGuiObject(".class","Html.A",".text", posSaleNum);
	}
	
	/**
	 * Click Add to Cart
	 */
	public void clickAddToCart(){
		browser.clickGuiObject(".class","Html.A",".text", "Add To Cart");
	}
	
	/**
	 * Click Move POS Sale
	 */
	public void clickMovePOSSale(){
		browser.clickGuiObject(".class","Html.A",".text", "Move POS Sale");
	}
	
	/**
	 * Select the check box before the given pos sale number
	 * @param posSaleNum
	 */
	public void selectCheckBox(String posSaleNum){
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		int rows;
		objs = browser.getTableTestObject(".id",new RegularExpression("grid_\\d+",false),".text",new RegularExpression("^POS Sale#.*",false) );
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find pos sale records table object.");
		}
		table = (IHtmlTable) objs[0];
		rows = table.rowCount();
		int i;
		for(i = 1; i<rows; i++){
			if(posSaleNum.equals(table.getCellValue(i, 1))){
				logger.info("Find record on page OrmsTicketOrderDetailsChargesLabelPage in row:"+i);
				browser.selectCheckBox(".id", "POSOrderView.id", ".name", "POSOrderView.id", i-1);
				break;
			}
		}
		if(i == rows){
			throw new ItemNotFoundException(
					"Can't find pos sale record in the pos sale table.");
		}
		Browser.unregister(objs);
	}
}