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
public class SupportCenterNewTicketPage extends SupportCenterPage{
	private static SupportCenterNewTicketPage _instance = null;

	public static SupportCenterNewTicketPage getInstance() {
		if (_instance == null) {
			_instance = new SupportCenterNewTicketPage();
		}
		return _instance;
	}

	protected Property[] newTicket_RequestTypeComboBox() {
		return Property.toPropertyArray(".class","FlexComboBox","id","cbSelectForm");
	}
	
	@Override
	public boolean exists() {
		return flex.checkFlexObjectExists(newTicket_RequestTypeComboBox());
	}
	
	public void selectRequestType(String option){
		flex.selectComboBox(newTicket_RequestTypeComboBox(), option);
	}
}
