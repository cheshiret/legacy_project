/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:https://orms-torqa3.dev.activenetwork.com/HFCustomerMgrPage.page?_PageParam.privInstID=\\d+ --->License History or Privilege History  
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author sborjigin
 * @Date  Jul 24, 2014
 */
public class LicMgrPrivilegeHistoryPage extends LicMgrPrivilegeItemDetailPage{
	private static LicMgrPrivilegeHistoryPage _instance = null;
	private final String transactionColName = "Transaction";
	private final String infoAtTimeColName = "Information at time of transaction";

	protected LicMgrPrivilegeHistoryPage() {
		
	}
	
	public static LicMgrPrivilegeHistoryPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrPrivilegeHistoryPage();
		}
		
		return _instance;
	}
	
	protected Property[] privilegeHistory(){
		return Property.concatPropertyArray(a(), ".text", new RegularExpression("(Licence|Privilege) History",false));
	}
	protected Property[] privHistoryTable(){
		return Property.concatPropertyArray(table(), ".text", new RegularExpression("Information at time of transaction",false));
	}
	
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(privilegeHistory());
	}
	public String getTransInfoAtTimeByTransacation(String transaction){
			logger.info("Get actual privilege history transaction information");
			IHtmlObject []objs = browser.getHtmlObject(privHistoryTable());
			if(objs.length<1){
				throw new ErrorOnPageException("Privilege History table is not found.");
			}
			IHtmlTable table = (IHtmlTable)objs[objs.length-1];
			//find Column num for 'Transaction' in the table header
			int transactionCol = table.findColumn(0, transactionColName);
			//find Column num for 'Information at time of transaction' in the table header
			int inforCol = table.findColumn(0, infoAtTimeColName);
			int row = table.findRow(transactionCol, transaction);
			if(transactionCol<0||inforCol<0||row<0){
				throw new ErrorOnPageException("Cannot find corresponding column and row");
			}
			return table.getCellValue(row, inforCol);
	}
	public void verifyPrivHistoryInfo(String expectedInfo,String trans){
		logger.info("Verify privilege history transaction information");
		String actualHisInfo = 	getTransInfoAtTimeByTransacation(trans);
		boolean result = true;
		result &= MiscFunctions.compareResult("Privilege History info",true,actualHisInfo.matches(expectedInfo));
		if(!result){
			throw new ErrorOnDataException("Privilege History info is not correct");
		} else {
			logger.info("Privilege History info is not correct");
		}
	}

}
