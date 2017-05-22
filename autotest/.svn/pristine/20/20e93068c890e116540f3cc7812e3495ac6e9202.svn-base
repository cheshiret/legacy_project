package com.activenetwork.qa.awo.pages.orms.common.ticket;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class OrmsTicketTransferSelectionPage extends OrmsPage {
	private static OrmsTicketTransferSelectionPage _instance = null;

	private OrmsTicketTransferSelectionPage() {
	}

	public static OrmsTicketTransferSelectionPage getInstance() {
		if (null == _instance) {
			_instance = new OrmsTicketTransferSelectionPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
//		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
//				new RegularExpression("^(To )?Tour Inventory.*Ticket Selection$", false));
//		Property[] p1=Property.toPropertyArray(".class","Html.TD",".className","label_activetransaction");
//		Property[] p2=Property.toPropertyArray(".class","Html.A",".className","btnanchor");
//		
//		HtmlObject[] objs=browser.getHtmlObject(Property.atList(p1,p2));
//		
//		if(objs.length<1) {
//			return false;
//		} else {
//			String text=objs[objs.length-1].text();
//			return text.equalsIgnoreCase("Transfer Tickets - Ticket Selection");
//		}
		return checkLastTrailValueIs("Transfer Tickets - Ticket Selection");
		
	}
	
	public void doTicketSelection(String number) {
		doTicketSelection(null,null,number);
	}
	
	public void doTicketSelection(String ticketTypeTo, String number) {
		doTicketSelection(null,ticketTypeTo,number);
	}
	
	public void doTicketSelection(String ticketTypeFrom, String ticketTypeTo, String number) {
		String[] froms=StringUtil.isEmpty(ticketTypeFrom)?null:new String[] {ticketTypeFrom};
		String[] tos=StringUtil.isEmpty(ticketTypeTo)?null:new String[] {ticketTypeTo};
		String[] nums=new String[] {number};
		doTicketSelection(froms,tos,nums);
	}
	
	/**
	 * This method was used to get Ticket Selection row count for current page cause there are some hidden rows in ticket selection table 
	 * @return
	 */
	private int getTicketSelectionRowCount(){
		IHtmlObject[] objs=getTicketSelectionTable();
		IHtmlTable table=(IHtmlTable) objs[0];
		int rowCount=0;
		IHtmlObject[] rowNums=browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^Quantity.*", false), table);
		for(int i=0;i<rowNums.length;i++)
			if(rowNums[i].exists())
				rowCount++;
		Browser.unregister(rowNums);
		Browser.unregister(objs);
		return rowCount;
	}
	
	public void doTicketSelection(String[] ticketTypeFroms, String[] ticketTypeTos, String[] numbers) {	
		int rowCount=getTicketSelectionRowCount();
		int diff=numbers.length-rowCount;
		for(int i=0;i<diff;i++) {
			clickAddTicketSelection();
		}
		
		if(ticketTypeFroms!=null) {
			IHtmlObject[] froms=browser.getHtmlObject(".class","Html.SELECT",".id", new RegularExpression("^fromTicketType.*", false));//"fromTicketType_0_3");
			
			for(int i=0;i<ticketTypeFroms.length;i++) {
				((ISelect)froms[i+1]).select(ticketTypeFroms[i]);
			}
			Browser.unregister(froms);
		}
		
		if(ticketTypeTos!=null) {
			IHtmlObject[] tos=browser.getHtmlObject(".class","Html.SELECT",".id",new RegularExpression("^toTicketType.*", false));//"toTicketType_0_3");
			
			
			for(int i=0;i<ticketTypeTos.length;i++) {
				((ISelect)tos[i+1]).select(ticketTypeTos[i]);
			}
			Browser.unregister(tos);
		}
		
		IHtmlObject[] nums=browser.getHtmlObject(".class","Html.INPUT.text",".id",new RegularExpression("^quantity.*", false));//"quantity_0_3");
		for(int i=0;i<numbers.length;i++) {
			((IText)nums[i+1]).setText(numbers[i]);
			
		}
		Browser.unregister(nums);
	}
	
	public void clickAddTicketSelection() {
		browser.clickGuiObject(".class","Html.A",".text","Add Ticket Selection");
	}
	
	protected IHtmlObject[] getTicketSelectionTable() {
		return browser.getTableTestObject(".id",new RegularExpression("^ticketSelectionTable.*", false));
	//	return browser.getTableTestObject(".id","ticketSelectionTable_0_3");
	}
	
	public void clickOK() {
		browser.clickGuiObject(".class","Html.A",".text","OK");
	}
	
	/** Select Transfer Reason */
	public void selectTransferReason(String reason) {
		if (reason == null || reason.length() < 1)
			browser.selectDropdownList(".id", "reason", 0);
		else
			browser.selectDropdownList(".id", "reason", reason);
	}
	
	public String getTourDateInvInfo(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",".text", new RegularExpression("^Tour Date.*", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("The span about tour inventory information for tour date is not correct!");
		}
		String content = objs[0].text().replace("Tour Date", "");
		return content;
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class","Html.A",".text","Cancel");
	}
	
}
