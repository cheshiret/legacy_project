/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.newsale;

import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:check point: purchase privilege work flow
 * @Preconditions:need a privilege(TDP-TesPrivilege)
 * @SPEC:Purchase Privilege
 * @Task#:AUTO-880
 * 
 * @author szhou
 * @Date  Mar 16, 2012
 */
public class PurchasePrivilege extends LicenseManagerTestCase {

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//purchase a privilege
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		// verify transaction type and order type in order cart page
		this.verifyTransTypeAndOrderType();
		
		String orderNum = lm.processOrderCart(pay);
		if(orderNum.contains(" ")){
        	orderNum = orderNum.split(" ")[0];
        }
		// verify order status
		lm.verifyResStatusInDB(orderNum, PROC_STATUS_PREARRIVAL, ORD_STATUS_ACTIVE, -1, schema);
		
		//cancel duplicated order
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}
	
	private void verifyTransTypeAndOrderType(){
		OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();
		String transaction=ormsOrderCartPg.getFirstTransactionName();
		String ordertype=ormsOrderCartPg.getFirstOrderType().split("\\(")[0];
		if(!TRANNAME_PURCHASE_PRIVILEGE.equals(transaction)||!ORDERTYPE_PRIVILEGE_SALE.equals(ordertype)){
			throw new ErrorOnDataException("transaction name or order type is not correct.",TRANNAME_PURCHASE_PRIVILEGE+"+"+ORDERTYPE_PRIVILEGE_SALE,transaction+"+"+ordertype);
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		privilege.name = "TetsPrivilege";
		privilege.purchasingName = "TDP-TestPrivilege";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		
		cust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "19880311";
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "111191";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
	}

}
