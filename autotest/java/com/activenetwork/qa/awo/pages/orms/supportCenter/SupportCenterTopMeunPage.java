/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.supportCenter;

import com.activenetwork.qa.testapi.util.Property;


/**
 * 
 * @author szhou
 * @Date  Jul 26, 2011
 */
public class SupportCenterTopMeunPage extends SupportCenterPage{
    private static SupportCenterTopMeunPage _instance = null;
	
    protected SupportCenterTopMeunPage() {}
    
	public static SupportCenterTopMeunPage getInstance() {
		if (_instance == null) {
			_instance = new SupportCenterTopMeunPage();
		}
		return _instance;
	}
	
	protected Property[] mainMenu_NewTicketButton() {
		return Property.toPropertyArray(".class","FlexButton","automationName","New Ticket");
	}
	
	protected Property[] mainMenu_SearchTicket() {
		return Property.toPropertyArray(".class","FlexButton","automationName","Search%20Tickets");
	}
	
	public void clickNewTicket() {
		flex.clickFlexObject(mainMenu_NewTicketButton());
	}
	
	public void clickSearchTickets() {
		flex.clickFlexObject(mainMenu_SearchTicket());
	}

	@Override
	public boolean exists() {
		return flex.checkFlexObjectExists(mainMenu_SearchTicket());
	}
}
