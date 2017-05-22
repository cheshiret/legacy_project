/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnDataException;
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
 * @author win7_vm
 * @Date  Jun 20, 2012
 */
public class LicMgrViewPreviousMotorsWidget  extends DialogWidget{
	private static LicMgrViewPreviousMotorsWidget _instance = null;

	protected LicMgrViewPreviousMotorsWidget() {
          super("View Previous Associated Vehicles");
	}

	public static LicMgrViewPreviousMotorsWidget getInstance() {
		if (_instance == null) {
			_instance = new LicMgrViewPreviousMotorsWidget();
		}
		return _instance;
	}
	/**
	 * get previous motor by id.
	 * @param id
	 */
	public boolean checkMotorExist(String id){
		IHtmlObject[] objs = browser.getTableTestObject(".class", "Html.TABLE", ".id", new RegularExpression("grid_\\d+",false));
		if(objs.length<1){
			throw new ErrorOnDataException("No previous associated vehicles table exist");
		}
		IHtmlTable table =(IHtmlTable)objs[1];
		boolean isExist = table.getColumnValues(0).contains(id);
		Browser.unregister(objs);
		return isExist;
	}
}
