/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.voidorder;

import java.util.Arrays;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderHistory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:Proccess Undo Void Privilege
 * @Task#: AUTO-933
 * 
 * @author asun
 * @Date  2012-3-28
 */
public class UndoVoidPrivilegeHistory extends LicenseManagerTestCase {
	private String voidReason="14 - Other";
	private String undoVoidReason="17 - Other";
	private String note="Auto test";
	protected String orderNum;
	private String[] privilegeNums;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//make a privilege order;
		lm.makePrivilegeToCartFromCustomerQuickSearch(cust, privilege);
		orderNum=lm.processOrderCart(pay,true).split(" ")[0];
		//Void privilege order;
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.rePrintPrivilegeInPrivilegeOrderDetailsPg();
		lm.voidPrivilegeOrderToCart(voidReason,note);
		lm.processOrderCart(pay);
		
		//Undo Void
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.undoVoidPrivilegeOrdToCart(undoVoidReason, note);
		lm.processOrderCart(pay);

		//verivy history for undo void 
		privilegeNums = lm.getPrivilegeNumsByOrdNum(schema, orderNum);
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.gotoPrivilegeOrderHistoryPage();
		this.verifyUndoVoidTransactionRecord();
		
		lm.logOutLicenseManager();
	}

	

	/**
	 * Verify order history for undo void 
	 * 1.there should be only one;
	 * 2.the 'Info at time of transaction' corresponding to only one 'UndoVoid' transaction records should contains the privilege nums in the order
	 */
	private void verifyUndoVoidTransactionRecord() {
		LicMgrPrivilegeOrderHistoryPage privOrderHistoryPg = LicMgrPrivilegeOrderHistoryPage.getInstance();
		
		logger.info("Verify order history for 'Undo Void' privilege order");
		
		List<OrderHistory>  list=privOrderHistoryPg.getHistoryInfoByTransaction("Undo Void");
		if(list==null ||list.size()!=1){
			throw new ErrorOnPageException("there should be only one records in current page..");
		}
		
		OrderHistory history=list.get(0);
//		String[] priNumsOnPage=history.infoTransaction .replaceAll(" ", "").split(":")[1].split(",");
		String[] priNumsOnPage = RegularExpression.getMatches(history.infoTransaction, "\\d+");
		if(priNumsOnPage.length!=privilegeNums.length){
			throw new ErrorOnPageException("there should be "+privilegeNums.length+" privilege nums.");
		}
		Arrays.sort(priNumsOnPage);
		Arrays.sort(privilegeNums);
		for(int i=0;i<privilegeNums.length;i++){
			if(!privilegeNums[i].equals(priNumsOnPage[i])){
				throw new ErrorOnPageException("Can't find privilage num:"+privilegeNums[i]+" for Undo Reverse");
			}
		}
	}



	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = DataBaseFunctions.getSchemaName("MS",env);
		
		privilege.purchasingName = "COP-CalculateOrderPrice";
		privilege.licenseYear = lm.getFiscalYear(schema);		
		privilege.qty = "2";
		privilege.operateReason = "01 - Operator Error";
		privilege.operateNote = "Automation-test";
		
		cust.identifier.identifierType = "VISA";
		cust.identifier.identifierNum = "AUTO12344";
		cust.identifier.country="Canada";
		cust.residencyStatus="Non Resident";
	}

}
