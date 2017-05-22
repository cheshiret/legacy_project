/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.popup;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.interfaces.ITable;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author ssong
 * @Date  Jan 25, 2014
 */
public class OrmsPOSFinSessionTotalVarianceWidget extends DialogWidget {
	
	protected OrmsPOSFinSessionTotalVarianceWidget(){
		super("Total Variance");
	}
	
	private static OrmsPOSFinSessionTotalVarianceWidget _instance = null;
	
	public static OrmsPOSFinSessionTotalVarianceWidget getInstance(){
		if(_instance == null){
			_instance = new OrmsPOSFinSessionTotalVarianceWidget();
		}
		return _instance;
	}
	
	protected Property[] passInfoTable(){
		return Property.concatPropertyArray(table(), ".text",new RegularExpression("^Pass Number.*",false));
	}
	
	public String getPassNumVariance(String passNum){
		IHtmlObject[] objs = browser.getTableTestObject(passInfoTable());
		
		ITable grid = (ITable)objs[objs.length-1];
		
		int row = grid.findRow(0, passNum);
		String tmp = grid.getCellValue(row, 2);
		Browser.unregister(objs);
		return tmp;
	}
}
