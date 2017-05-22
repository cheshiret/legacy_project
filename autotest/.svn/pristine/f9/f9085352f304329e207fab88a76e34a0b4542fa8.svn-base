package com.activenetwork.qa.awo.pages.orms.common.financial;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author ding1
 * @Date  Jul 18, 2012
 */
public class OrmsDepositAdjustmentPage extends OrmsPage {
	
	protected OrmsDepositAdjustmentPage() {}

	private static OrmsDepositAdjustmentPage _instance = null;
	public static OrmsDepositAdjustmentPage getInstance() {
		if (null == _instance)
			_instance = new OrmsDepositAdjustmentPage();
		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.Table", ".text", new RegularExpression("Adjustment ID.*", false));
	}
	
	public List<String> getAdjustmentID(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.Table", ".text", new RegularExpression("Adjustment ID.*", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find deposit adjustment table.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> adjustmentIDList = new ArrayList<String>();
		
		for(int i=1; i<table.rowCount(); i++){
			adjustmentIDList.add(table.getCellValue(i, 0));
		}
		Browser.unregister(objs);
		return adjustmentIDList;
	}
}
