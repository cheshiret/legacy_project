/*
 * Created on Dec 28, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.supportCenter;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.TestApiConstants;
import com.activenetwork.qa.testapi.interfaces.ITable;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.flex.Flex;
import com.activenetwork.qa.testapi.interfaces.flex.IFlexObject;
import com.activenetwork.qa.testapi.interfaces.flex.IFlexTable;
import com.activenetwork.qa.testapi.util.Property;


/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SupportCenterSearchPage extends SupportCenterPage{
  /**
	 * A handle to the unique Singleton instance.
	 */
	static private SupportCenterSearchPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected SupportCenterSearchPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	public static SupportCenterSearchPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new SupportCenterSearchPage();
		}
		return _instance;
	}
	
	protected Property[] searchTickets_SearchButton() {
		return Property.toPropertyArray(".class","FlexButton","automationName","Search");
	}
	
	protected Property[] searchTickets_SearchResultsGrid() {
		return Property.toPropertyArray(".class","FlexDataGrid","id","adgSearchResults");
	}
	
	protected Property[] searchTickets_RequestNumberText() {
		return Property.toPropertyArray(".class","FlexTextArea","id","tiId");
	}
	
	public void setRequestNumber(String num) {
		flex.setTextArea(searchTickets_RequestNumberText(), num);
	}
	
	public void clickSearch(){
		int count = 0;
		do{
			Browser.sleep(3);
			count++;
			if(count>TestApiConstants.SLEEP){
				throw new ItemNotFoundException("Can not found....");
			}
		}while (!flex.checkFlexObjectExists(searchTickets_SearchButton()));
		flex.clickFlexObject(searchTickets_SearchButton());
	}
	
	public String getRequestStatus(int row){
		IFlexObject[] table=flex.getFlexTable(searchTickets_SearchResultsGrid());
		String status= ((IFlexTable)table[0]).getCellValue(row, 1);
		Flex.unregister(table);
		
		return status;
	}
	
	@Override
	public boolean exists() {
		return flex.checkFlexObjectExists(searchTickets_RequestNumberText());
	}

	/* (non-Javadoc)
	 * @see testAPI.pages.orms.supportCenter.SupportCenterSearchPage#supportCenterSearchPageWaitExist(int)
	 */
	public void supportCenterSearchPageWaitExist() {
		SupportCenterSearchPage searchPg = SupportCenterSearchPage.getInstance();
		int count = 0;
		do{
			Browser.sleep(3);
			count++;
			if(count>TestApiConstants.SLEEP){
				throw new ItemNotFoundException("Can not found....");
			}
		}while (searchPg.getRequestStatus(0)==null||searchPg.getRequestStatus(0).equalsIgnoreCase(""));
	}
	
}
