package com.activenetwork.qa.awo.keywords.orms.search;

import com.activenetwork.qa.awo.datacollection.legacy.orms.TicketInfo;
import com.activenetwork.qa.awo.pages.orms.common.ticket.OrmsTicketAvailabilityPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.browser.IBrowser;
import com.activenetwork.qa.testapi.util.AutomationLogger;

/**
 * @ScriptName TicketSearch.java
 * @Date:Oct 9, 2010
 * @author asun
 */
public class TicketSearch {

	private static TicketSearch instance=null;
	protected static AutomationLogger logger = AutomationLogger.getInstance();
	protected IBrowser browser=Browser.getInstance();
	
	private TicketSearch(){
		
	}
	
	public static TicketSearch getInstance(){
		if(instance==null){
			instance=new TicketSearch();
		}
		return instance;
	}
	
	/**
	 * 
	 * TODO   search ticket in ticket availability page
	 * @param ticket
	 * @Return void
	 * @Throws
	 */
	public void searchTicket(TicketInfo ticket){
		OrmsTicketAvailabilityPage page=OrmsTicketAvailabilityPage.getInstance();
		logger.info("Search available ticket.....");
		page.searchAvailibility(ticket);
		browser.waitExists(page);
	}
}
