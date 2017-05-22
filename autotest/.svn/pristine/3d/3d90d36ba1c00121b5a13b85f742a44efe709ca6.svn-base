package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * @author SWang
 *
 */
public class UwpPrintTicketsAndPermitsPage extends UwpPage {

	private static UwpPrintTicketsAndPermitsPage instance=null;

	public static UwpPrintTicketsAndPermitsPage getInstance(){
		if(instance==null){
			instance=new UwpPrintTicketsAndPermitsPage();
		}
		return instance;
	}
	
	private UwpPrintTicketsAndPermitsPage() {
	}

	protected Property[] ordNumLink(String ordNum) {
		return Property.concatPropertyArray(a(), ".text", ordNum);
	}
	
	protected Property[] printTicketsBtn(String ordNum) {
		return Property.toPropertyArray(".class", "Html.Button", ".text", "Print Tickets", 
				".onclick", new RegularExpression(".*reservationNumber="+ordNum, false));
	}
	
	protected Property[] reprintTicketsBtn(String ordNum) {
		return Property.toPropertyArray(".class", "Html.Button", ".text", "Reprint Tickets", 
				".onclick", new RegularExpression(".*reservationNumber="+ordNum, false));
	}
	
	protected Property[] itemTable() {
		return Property.concatPropertyArray(table(), ".className", "items");
	}
	
	public boolean exists() {
		Property[] p=Property.toPropertyArray(".class", "Html.DIV", 
				".id", "contentArea",
				".text", new RegularExpression("^Print Tickets \\& Permits.*",false));
		return browser.checkHtmlObjectExists(p);
	}
	
	/**
	 * Click "Print Permit" button
	 * @param resNum
	 */
	public void clickPrintPermitButton(String resNum) {
		Property[] p1 = Property.toPropertyArray(".class", "Html.TD", ".text", 
				new RegularExpression("^#"+resNum+".*", false));
		Property[] p2 = Property.toPropertyArray(".className", "all search", ".id" ,"prnt");
		
		browser.clickGuiObject(Property.atList(p1, p2), true, 0);
	}

	
	public boolean checkOrderExist(String resNum) {
		Property[] p = Property.toPropertyArray(".class", "Html.TD", ".text", 
				new RegularExpression("^#"+resNum+".*", false));
		return browser.checkHtmlObjectExists(p);
	}


	public boolean checkPrintPermitButtonExist(String resNum) {
		Property[] p1 = Property.toPropertyArray(".class", "Html.TD", ".text", 
				new RegularExpression("^#"+resNum+".*", false));
		
		Property[] p2 = Property.toPropertyArray(".className", "all search", ".id" ,"prnt",".text",new RegularExpression(" ?Print Permit",false));
		
		IHtmlObject[] objs=browser.getHtmlObject(p1);
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Can't found TD for order:\""+resNum+"\"");
		}
		return browser.checkHtmlObjectExists(p2, objs[0]);
	}

	public void waitForOrderNum(String ordNum){
		browser.searchObjectWaitExists(Property.atList(itemTable(), ordNumLink(ordNum)));
	}
	
	/**
	 * @param orderNum
	 */
	public void clickOrderNum(String orderNum) {
		browser.clickGuiObject(ordNumLink(orderNum));
	}
	
	public boolean isOrdNumLinkExist(String ordNum) {
		return browser.checkHtmlObjectExists(ordNumLink(ordNum));
	}
	
	private IHtmlObject[] getPrintListTable(){
		IHtmlObject[] objs=browser.getTableTestObject(".className", "items",".text",new RegularExpression("Reservations.*Print Details.*",false));
	    if(objs==null ||objs.length<1){
	    	throw new ObjectNotFoundException("Can't find order items table.");
	    }
	    
//	    Browser.unregister(objs);
	    return objs;
	}
	
	/**
	 * EntryDate, GroupSize, GroupLeader
	 * @param orderNum
	 * @return
	 */
	public String getPrintDetails(String orderNum){
		String details;
	    IHtmlObject[] objs=getPrintListTable();
	    IHtmlTable table=(IHtmlTable)objs[0];
	    int row=table.findRow(0, new RegularExpression("#"+orderNum+".*",false));
	    details=table.getCellValue(row, 1);
	    Browser.unregister(objs);
	    return details;
	}
	/**
	 * ReservationDetails:order#, Permit type, Entrance code/name,Facility,state.
	 * @param orderNum
	 * @return
	 */
	public String getReservationDetails(String orderNum){
		IHtmlObject[] objs=getPrintListTable();
		IHtmlTable table=(IHtmlTable)objs[0];
	    int row=table.findRow(0, new RegularExpression("#"+orderNum+".*",false));
	    String details=table.getCellValue(row, 0);
	    Browser.unregister(objs);
	    return details;
	}
	
	public boolean isPrintTicketBtnExist(String ordNum) {
		return browser.checkHtmlObjectExists(this.printTicketsBtn(ordNum));
	}
	
	public boolean isReprintTicketBtnExist(String ordNum) {
		return browser.checkHtmlObjectExists(this.reprintTicketsBtn(ordNum));
	}
}
