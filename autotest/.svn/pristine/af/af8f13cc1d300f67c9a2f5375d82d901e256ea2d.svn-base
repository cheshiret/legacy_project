/*
 * $Id: VnuMgrManageTicketsPage.java 6747 2008-11-26 18:09:36Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.venueManager;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author CGuo,Jdu
 */
public class VnuMgrManageTicketsPage extends VenueManagerPage {

	/**
	 * Script Name   : VnuMgrManageTicketsPage
	 * Generated     : Feb 19, 2007 2:52:10 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2007/02/19
	 */

	private static VnuMgrManageTicketsPage _instance = null;

	private VnuMgrManageTicketsPage() {
	}

	public static VnuMgrManageTicketsPage getInstance() {
		if (null == _instance)
			_instance = new VnuMgrManageTicketsPage();

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Invalidate Tickets");
	}

	/** Click on link to return to order details page.*/
	public void clickReturnToOrderDetail() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Return to Order Detail");
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class","Html.A",".text","Cancel");
	}

	/** Click on Searching link. */
	public void clickSearch() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".href", new RegularExpression(
				"\"ManagePrintTickets\\.do\",.+\"managePrintTickets\"", false));
		p[2] = new Property(".text", "Search");
		browser.clickGuiObject(p);
	}

	/** Select all check box fields.	 */
	public void selectAllCheckBox() {
		browser.selectCheckBox(".name", "all_slct");
	}

	/**
	 * Select the price status as given.
	 * @param status
	 */
	public void selectPrintStatus(String status) {
		browser.selectDropdownList(".id", "printStatus", status);
	}

	/**  Click on Invalidate Tickets link. */
	public void clickInvalidTickets() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Invalidate Tickets");
	}

	/**
	 * Fill in ticket number.
	 * @param num - number of tickets
	 */
	public void setTicketNumber(String num) {
		browser.setTextField(".id", "ticketNo", num);
	}
	
	public int getInvalidateTicketNum(){
		IHtmlObject[] objs = browser.getHtmlObject(".id","ticketCheckNo",".disabled","true");
		return objs.length;
	}

	/**
	 * Invalidated all tickets.
	 * @throws PageNotFoundException
	 */
	public void invalidateAllTickets() throws PageNotFoundException {
		boolean invalidAll = false;
		while (!invalidAll) {
			searchTicketWithStatus("Printed");
			this.waitLoading();
			if (!isInvalidated()) {
				this.selectAllCheckBox();
				this.clickInvalidTickets();
				waitLoading();
			}else{
				invalidAll = true;
			}
			searchTicketWithStatus("Reprinted");
			if (!isInvalidated()) {
				this.selectAllCheckBox();
				this.clickInvalidTickets();
				waitLoading();
			}else {
				invalidAll = true;
			}
		}
	}

	/**
	 * Search tickets by given price status.
	 * @param printStatus - price status
	 * @throws PageNotFoundException
	 */
	public void searchTicketWithStatus(String printStatus)
			throws PageNotFoundException {
		this.selectPrintStatus(printStatus);
		this.clickSearch();
	}

	/**
	 * Verify whether or not the ticket has been invalidated.
	 * @return
	 */
	public boolean isInvalidated() {
		boolean invalided = true;
		IHtmlObject[] objs = browser.getHtmlObject(".id", "ticketCheckNo");
		for (int i = 0; i < objs.length; i++) {
			boolean enabled=objs[i].isEnabled();
			if (enabled) {
				invalided = false;
				break;
			}
		}
		Browser.unregister(objs);
		return invalided;
	}
	
	/**
	 * verify the information in manage tickets page
	 * @param beforeAction
	 * @param afterAction
	 */
	public void verifyManageTickets(boolean beforeAction, boolean afterAction, String action){
		RegularExpression reg = new RegularExpression("^Ticket # Print Status Tour Tour Date.*",false);
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.TABLE",".text",reg);
		IHtmlTable tableGrid = (IHtmlTable)objs[0];
		List<String> ticketNumsList = new ArrayList<String>();
		
		int rowNum = tableGrid.rowCount();
		
		if(rowNum>0){
			if(beforeAction){
				//verify invalidates tickets by selecting tickets in descending order of print/update/time
				ticketNumsList = tableGrid.getColumnValues(1);
				for(int m=1; m<ticketNumsList.size()-1; m++){
					for(int n=m+1; n<ticketNumsList.size(); n++){
						if(ticketNumsList.get(m).compareTo(ticketNumsList.get(n))>0){
							throw new ErrorOnDataException("Sate/Time should order in descending before action.");
						}
					}
				}
			}
			if(afterAction){
				//verify invalidates tickets by selecting tickets in descending order of print/update/time
				int invalidateTicketNum = this.getInvalidateTicketNum();
				//For the ticket invalidated
				if(!action.equalsIgnoreCase("Cancel")){
					for(int m=rowNum-2; m>rowNum-1-invalidateTicketNum*2; m--){ 
						if(!(tableGrid.getCellValue(rowNum-1,7).equals(tableGrid.getCellValue(m,7)))){
							throw new ErrorOnDataException("Invalidates tickets by selecting tickets should be in descending order of print/update/time");
						}
					}
					//For the tickets don't invalidate
					if(ticketNumsList.size()-1-invalidateTicketNum*2>=2){
						for(int m=1; m<ticketNumsList.size()-1-invalidateTicketNum*2; m++){
							for(int n=m+1; n<ticketNumsList.size()-invalidateTicketNum*2; n++){
								if(ticketNumsList.get(m).compareTo(ticketNumsList.get(n))>0){
									throw new ErrorOnDataException("Sate/Time should order in descending after action.");
								}
							}
						}
					}
				}else{
					//For the ticket invalidated 
					for(int m=ticketNumsList.size()-1; m<ticketNumsList.size()-2-invalidateTicketNum; m--){
						for(int n=m-1; n<ticketNumsList.size()-1-invalidateTicketNum; n--){
							if(!(ticketNumsList.get(m).equals(ticketNumsList.get(n)))){
								throw new ErrorOnDataException("Sate/Time should order in descending after action(Special).");
							}
						}
					}
					//For the tickets don't invalidate
					if(ticketNumsList.size()-1-invalidateTicketNum>=2){
						for(int m=1; m<ticketNumsList.size()-1-invalidateTicketNum; m++){
							for(int n=m+1; n<ticketNumsList.size()-invalidateTicketNum; n++){
								if(ticketNumsList.get(m).compareTo(ticketNumsList.get(n))>0){
									throw new ErrorOnDataException("Sate/Time should order in descending after action(Special).");
								}
							}
						}
					}
				}
			}
			//verify ticket# orders in descending
			ticketNumsList = tableGrid.getColumnValues(1);
			for(int m=1; m<ticketNumsList.size()-1; m++){
				for(int n=m+1; n<ticketNumsList.size(); n++){
					if(ticketNumsList.get(m).compareTo(ticketNumsList.get(n))>0){
						throw new ErrorOnDataException("Ticket# should order in descending");
					}
				}
			}
		}
		Browser.unregister(objs);
	}
}
