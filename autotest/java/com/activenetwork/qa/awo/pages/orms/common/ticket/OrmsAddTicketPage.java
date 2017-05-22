/*
 * $Id: VnuMgrAddTicketsPage.java 7065 2009-01-30 15:08:12Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.common.ticket;

import java.util.HashMap;
import java.util.Map;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * TODO: enter class description
 * 
 * @author CGuo
 */
public class OrmsAddTicketPage extends OrmsPage {

	/**
	 * Script Name : VnuMgrAddTicketsPage Generated : Feb 19, 2007 2:37:14 PM
	 * Original Host : WinNT Version 5.1 Build 2600 (S)
	 * 
	 * @since 2007/02/19
	 */
	private static OrmsAddTicketPage _instance = null;

	private OrmsAddTicketPage() {
	}

	public static OrmsAddTicketPage getInstance() {
		if (null == _instance)
			_instance = new OrmsAddTicketPage();

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Add Tickets");
		p[2] = new Property(".href", new RegularExpression("\"addTickets\"",
				false));
		return browser.checkHtmlObjectExists(p);
	}

	/**
	 * Input ticket number
	 * 
	 * @param ticketType
	 * @param num
	 */
	public void setTicketNumber(String ticketType, String num) {
		String textFieldId = "";
		if (ticketType.equalsIgnoreCase("General Admission"))
			textFieldId = "quantity_17_0_0";
		else if (ticketType.equalsIgnoreCase("Adult")) {
			textFieldId = "quantity_1_0_0";
		} else if (ticketType.equalsIgnoreCase("Child")) {
			textFieldId = "quantity_21_0_0|quantity_19_0_0";//19
		} else if (ticketType.equalsIgnoreCase("Youth")) {
			textFieldId = "quantity_2_0_0";
		} else if(ticketType.equalsIgnoreCase("Youth 6 Through 12")){
			textFieldId = "quantity_3_0_0";
		} else if(ticketType.equalsIgnoreCase("Child (3 - 16)")) {
			textFieldId = "quantity_36_0_0";
		} else if(ticketType.equalsIgnoreCase("2 and under")) {
			textFieldId = "quantity_5_0_0";
		} else 
			throw new ItemNotFoundException("Unknown ticket type: "
					+ ticketType);
		
		browser.setTextField(".id", new RegularExpression(textFieldId, false), num);
	}
	
	
	/**judge if Id pass existing*/
	public boolean isIDPassRequired(){
		return browser.checkHtmlObjectExists(".class", "Html.A",".text","Add");
	}

	/**
	 * 
	 * TODO add ID/Pass for one ticket type
	 * 
	 * @param ticketType
	 * @param pass
	 * @Return void
	 * @Throws
	 */
	public void setTicketPass(String ticketType, String[] pass) {
		String passFeildId = "";
		if (ticketType.equalsIgnoreCase("Adult")) {
			passFeildId = "TicketPass_1_0_0";
		} else if (ticketType.equalsIgnoreCase("Child")) {
			passFeildId = "TicketPass_19_0_0";
		} else {
			throw new ItemNotFoundException("Unknown ticket type: "
					+ ticketType);
		}

		IHtmlObject[] objs = browser
				.getTableTestObject(".class", "Html.TABLE", ".text",
						new RegularExpression("^Ticket Type.*", false));

		if(objs.length<1){
			Browser.unregister(objs);
			throw new ItemNotFoundException("The Specify Quantity Table is not found.");
		}
		
IHtmlTable table=(IHtmlTable)objs[1];
		
		int typeRow=table.findRow(0, ticketType);
		
		for (int i = 0; i < pass.length; i++) {
			browser.clickGuiObject(".class", "Html.A",".text",new RegularExpression("^Add$", false),typeRow-1);
			browser.setTextField(".id", passFeildId, pass[i], i+1);
		}
		
		Browser.unregister(objs);
	}

	/**
	 * 
	 * TODO add ID/Pass for more Type
	 * 
	 * @param passes
	 * @Return void
	 * @Throws
	 */
	public void addMorePass(HashMap<String, String[]> passes) {
		for (Map.Entry<String, String[]> pass : passes.entrySet()) {
			this.setTicketPass(pass.getKey(), pass.getValue());
		}
	}

	/**
	 * Add more tickets
	 * 
	 * @param types
	 * @param typeNums
	 */
	public void addMoreTickets(String[] types, String[] typeNums) {
		if (types.length != typeNums.length)
			throw new ItemNotFoundException("The size of ticket types array "
					+ types.length
					+ " is not same as the size of numbers array "
					+ typeNums.length);

		for (int i = 0; i < types.length; i++) {
			this.setTicketNumber(types[i], typeNums[i]);
		}
	}

	/** Click Add Tickets */
	public void clickAddTickets() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Add Tickets");
		p[2] = new Property(".href", new RegularExpression("\"(addTickets|TourOrderAddTickets\\.do)\"",
				false));
		browser.clickGuiObject(p);
	}

	/** Click Don't Add Button */
	public void clickDontAdd() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Don't Add");
	}

	public String getErrorMessages() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("((^v-\\d+)|(^error.*))|(NOTSET)|statusMessages", false));
		StringBuffer sb = new StringBuffer();
		for (IHtmlObject o : objs) {
			sb.append(o.getProperty(".text").trim()).append(" ");
		}
		Browser.unregister(objs);
		return sb.toString();
	}

	/**
	 * 
	 * TODO get ticket inventory by index
	 * 
	 * @param index
	 *            start from 0;
	 * @return
	 * @Return String
	 * @Throws
	 */
	public String getTicketInventory(int index) {
		Property[] p = new Property[] {
				new Property(".class", "Html.A"),
				new Property(".className", "btn"),
				new Property(".text", new RegularExpression(
						"(^[0-9]{1,2}:[0-9]{2}.*)|([a-zA-Z]{3} \\d{1,2}-[a-zA-Z]{3} \\d{1,2})", false)) };
		IHtmlObject[] objs = browser.getHtmlObject(p);
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"The inventory button you expect is not found..");
		}

		String buttontext = objs[index].getProperty(".text");
		Browser.unregister(objs);
		return buttontext.split(" ")[1];
	}
	
	public String getMutiDayTicketInventory(int index) {
		Property[] p = new Property[] {
				new Property(".class", "Html.A"),
				new Property(".className", "btn_w"),
				new Property(".text", new RegularExpression(
						"^[a-zA-Z]{3} \\d{1,2} - [a-zA-Z]{3} \\d{1,2}.*", false)) };
		IHtmlObject[] objs = browser.getHtmlObject(p);
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"The inventory button you expect is not found..");
		}

		String buttontext = objs[index].getProperty(".text");
		Browser.unregister(objs);
		return buttontext.split("\\+")[0].trim();

	}
	
	public String getTourDateInvInfo(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",".text", new RegularExpression("^Tour Date.*", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("The span about tour inventory information for tour date is not correct!");
		}
		String content = objs[0].text().replace("Tour Date", "");
		return content;
	}

	public static void main(String[] args) {
		OrmsAddTicketPage page = OrmsAddTicketPage.getInstance();
		page.setTicketPass("Adult", new String[] { "WWW" });
//		page.browser.setTextField(".id", "TicketPass_1_0_0", "ww",1);
//	    System.out.print(page.browser.getHtmlObject(".id", "TicketPass_1_0_0").length);
	}
}
