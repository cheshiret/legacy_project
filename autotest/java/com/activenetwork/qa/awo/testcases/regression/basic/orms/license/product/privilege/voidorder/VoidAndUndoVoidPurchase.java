/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.voidorder;

import com.activenetwork.qa.awo.testcases.abstractcases.VoidPrivilegeTransTestCase;


/**
 * @Description:
 * 1. make a privilege order;
 * 2. abandon void do verify order status;
 * 3. void order and do verify order status;
 * 4. Undo void and do verify order status;
 * @Preconditions:
 * 1.the privilege mast have an available print document;
 * 2.Print privilege feature should be assigin to the user
 * @SPEC: Void Privilege Transaction 
 * @Task#: AUTO-933
 * 
 * @author asun
 * @Date  2012-3-15
 */
public class VoidAndUndoVoidPurchase extends VoidPrivilegeTransTestCase {


	@Override
	public void wrapLocalParameter(Object[] param) {
		this.isPrinted=true;
		orderType = "Purchase";
	}

}
