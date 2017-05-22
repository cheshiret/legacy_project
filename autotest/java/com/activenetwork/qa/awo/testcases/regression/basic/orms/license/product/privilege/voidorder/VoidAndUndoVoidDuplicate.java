/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.voidorder;

import com.activenetwork.qa.awo.testcases.abstractcases.VoidPrivilegeTransTestCase;


/**
 * @Description:
 * 1. make a privilege order;
 * 2. Duplicate privilege
 * 3. abandon void do verify order status;
 * 4. void order and do verify order status;
 * 5. Undo void and do verify order status;
 * @Preconditions:
 * 1.the privilege mast have an available print document;
 * 2.Print privilege feature should be assigin to the user
 * @SPEC:Void Privilege Transaction 
 * @Task#:AUTO-933
 * 
 * @author asun
 * @Date  2012-3-15
 */
public class VoidAndUndoVoidDuplicate extends VoidPrivilegeTransTestCase {
    
	//Duplicate privilege
	@Override
	public void voidTransExecute(){	
		lm.replacePrivilegeToCartFromCustQuickSearch(cust, orderNum, privilege);
		orderNum = lm.processOrderCart(pay,isPrinted).split(" ")[0];
	}

	@Override
	public void wrapLocalParameter(Object[] param) {
		this.isPrinted = true;
		orderType = "Duplicate";
	}
}
