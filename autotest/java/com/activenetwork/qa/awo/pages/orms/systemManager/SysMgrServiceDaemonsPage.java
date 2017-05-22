/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.systemManager;

import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * @Defects:
 * 
 * @author asun
 * @Date  Aug 31, 2011
 */
public class SysMgrServiceDaemonsPage extends SysMgrEnvsPage {
	
	private static SysMgrServiceDaemonsPage instance=null;
	
	private SysMgrServiceDaemonsPage(){}
	
	public static SysMgrServiceDaemonsPage getInstance(){
		if(instance==null){
			instance=new SysMgrServiceDaemonsPage();
		}
		return instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "ServiceDaemonList");
	}
	
	public String getDaemonRunAt(String name){
		String runAt="";
		IHtmlObject[] objs=browser.getTableTestObject(".id", "ServiceDaemonList");
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find the table by id = 'ServiceDaemonList'.");
		}
		IHtmlTable table=(IHtmlTable)objs[0];
		int row = table.findRow(1, name);
		runAt = table.getCellValue(row, 3);
		Browser.unregister(objs);
		return runAt;
	}

	/**
	 * Get the daemon running status by daemon name
	 * @param name
	 * @return daemon running status
	 *  - RUNNING
	 *  - NOT_RUNNING
	 */
	public String getDaemonStatus(String name) {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "ServiceDaemonList");
		if(objs.length < 1) {
			throw new ObjectNotFoundException("Can't find the table by id = 'ServiceDaemonList'.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(1, name);
		String status = table.getCellValue(row, 2);
		
		Browser.unregister(objs);
		return status;
	}
}
