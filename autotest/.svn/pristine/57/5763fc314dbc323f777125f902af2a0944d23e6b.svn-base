package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @Task#: AUTO-681
 * 
 * @author SWang
 * @Date  Aug 15, 2011
 */
public class UwpPreRegistrationListPage  extends UwpPage {
	private static UwpPreRegistrationListPage _instance = null;

	public static UwpPreRegistrationListPage getInstance() {
		if (null == _instance)
			_instance = new UwpPreRegistrationListPage();

		return _instance;
	}

	public UwpPreRegistrationListPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id",  new RegularExpression("[p|P]re-Registration",false)); //,".className","accountside in");
	}
	
	public IHtmlObject[] getPreRegistrationList(){
		return browser.getTableTestObject(".id", "Pre-Registration");
	}

	/**
	 * Verify whether or not this is Pre-Registration page.
	 * @return true - found page, false - not found
	 */
	public boolean isPreRegistrationPg() {
		IHtmlObject[] objs = browser.getHtmlObject(".className", "component",".text", new RegularExpression("^Pre-Registration.*",false));
		Browser.unregister(objs);
		if (objs.length == 1)
			return true;
		else
			return false;
	}
	
	/**
	 * Get reservation numbers which meet Pre-registrayion. If there is no reservation meeted, it will
	 * message: There are no reservations eligible for Pre-registration at this time. 
	 * @return
	 */
	public String[] getResNums(){
		IHtmlObject[] objs = this.getPreRegistrationList();

		if(objs.length<1){
			throw new ObjectNotFoundException("Pre-Registration list object can't be found.");
		}

		IHtmlTable preRegistrationListTable = (IHtmlTable)objs[0];
		String[] resNums = new String[preRegistrationListTable.rowCount()-3];
		for(int i=3; i<preRegistrationListTable.rowCount();i++){
			resNums[i-3] = preRegistrationListTable.getCellValue(i, 0);
		}
		return resNums;
	}
	
	/**
	 * Check reservation number whether exist or not
	 * @param resNum
	 * @param expectExisted
	 */
	public void checkResNumsExist(String resNum, boolean expectExisted){
		boolean existed = browser.checkHtmlObjectExists(".class", "Html.TD", ".text", new RegularExpression("^"+resNum+".*", false));

		if(expectExisted && !existed){
			throw new ErrorOnDataException("The reservation with number = "+resNum+" should exist.");
		}else{
			if(!expectExisted && existed){
				throw new ErrorOnDataException("The reservation with number = "+resNum+" should don't exist.");
			}
		}
	}
}
