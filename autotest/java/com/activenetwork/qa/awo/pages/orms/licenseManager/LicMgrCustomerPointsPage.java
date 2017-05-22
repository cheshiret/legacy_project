
package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.CustomerPoint;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @author ssong
 * @date Dec 5, 2011
 */
public class LicMgrCustomerPointsPage extends LicMgrCustomerDetailsPage{
	
	private static final String POINT_TYPE = "Point Type";
	private static final String POINT_BALANCE = "Balance";
	private static final String POINT_MAX_ALLOW = "Max Points Allowed";
	private static LicMgrCustomerPointsPage _instance = null;
	
	protected LicMgrCustomerPointsPage(){
		
	}
	
	protected Property[] deductPointsLink() {
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("Deduct (Point(s)?|(Pool Status))", false));
	}
	
	public static LicMgrCustomerPointsPage getInstance(){
		if(_instance == null){
			_instance = new LicMgrCustomerPointsPage();
		}
		return _instance;
	}
	
	public boolean exists(){//check customer order table exists
		return browser.checkHtmlObjectExists(".id","CustomerPointListPanel");
	}
	 
	
	public void clickAddPoints()
	{
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Add (Point(s)?|(Pool Status))", false));
	}
	
	public void clickDeductPoints() {
		browser.clickGuiObject(this.deductPointsLink());
	}
	
	public List<CustomerPoint> getAllPointTypeRecord()
	{
		int rows;
		int columns;
		List<CustomerPoint> ts =new ArrayList<CustomerPoint>();
		
		IHtmlObject[] objs =  browser.getHtmlObject(".class", "Html.TABLE", ".id", "PointTypeListGrid");
		if(objs.length<1)
		{
			throw new ErrorOnPageException("Cannot find point type list record");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		
		rows = table.rowCount();
		columns = table.columnCount();
		logger.info("Find record on page, "+rows+" rows, "+columns+" columns.");
		CustomerPoint cp;
		for(int i = 1; i < rows; i ++) {
			cp = new CustomerPoint();
			cp.pointType = table.getCellValue(i, table.findColumn(0, POINT_TYPE));
			cp.pointMax = table.getCellValue(i, table.findColumn(0, POINT_MAX_ALLOW));
			cp.pointBalance = table.getCellValue(i, table.findColumn(0, POINT_BALANCE));
			ts.add(cp);			
		}
		
		Browser.unregister(objs);
		return ts;
	}
	
	 
	}
