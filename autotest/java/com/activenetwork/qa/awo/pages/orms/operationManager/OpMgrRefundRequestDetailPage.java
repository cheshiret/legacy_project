/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.operationManager;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Oct 31, 2012
 */
public class OpMgrRefundRequestDetailPage extends OperationsManagerPage{


	private static OpMgrRefundRequestDetailPage _instance = null;

	protected OpMgrRefundRequestDetailPage() {

	}

	public static OpMgrRefundRequestDetailPage getInstance() {
		if (null == _instance) {
			_instance = new OpMgrRefundRequestDetailPage();
		}

		return _instance;
	}

	public boolean exists() {
		// using Refund Request part as page mark
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("Refund Request", false));
	}
	
	public String getState(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^State", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find state!");
		}
		
		return objs[0].getProperty(".text").replaceAll("State", StringUtil.EMPTY);
	}
	
	
	
}
