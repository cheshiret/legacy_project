/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:Transfer motors widget
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Jwang8
 * @Date  Jun 20, 2012
 */
public class LicMgrTransferMotorWidget extends DialogWidget{
	private static LicMgrTransferMotorWidget _instance = null;

	protected LicMgrTransferMotorWidget() {
	}

	public static LicMgrTransferMotorWidget getInstance() {
		if (_instance == null) {
			_instance = new LicMgrTransferMotorWidget();
		}
		return _instance;
	}
	
	public boolean exists() {
		boolean flag1= super.exists();
		boolean flag2 = browser.checkHtmlObjectExists(".id", new RegularExpression("rb_\\d+",false));
		return  flag1&flag2;
	}
	/**
	 * select motor transfer box.
	 * @param index
	 */
	public void selectMotorTransferBox(int index){
		browser.selectRadioButton(".id", new RegularExpression("rb_\\d+",false),index);
	}
	/**
	 * get transfer motor table.
	 * @return
	 */
	public IHtmlObject[] getTranferMotroTable(){
		Property[] property = Property.toPropertyArray(".id", "transfer_associated_vehicle");
		
		IHtmlObject[] objs = browser.getTableTestObject(property, this.getWidget()[0]);
		if(objs.length<1){
			throw new ErrorOnPageException("No transfer motor table exist");
		}
		return objs;
	}
	/**
	 * select motor transfer yes radio box.
	 * @param id
	 */
	public void selectMotorTransferYesRadioBox(String id){
		IHtmlObject[] objs = this.getTranferMotroTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		for(int i =1;i<table.rowCount();i++){
			if(table.getCellValue(i, 1).equals(id)){
				this.selectMotorTransferBox((i-1)*2);
			}else{
				this.selectMotorTransferBox((i-1)*2+1);
			}
		}
		Browser.unregister(objs);
	}
}
