package com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Jul 26, 2012
 */
public abstract class InvMgrPOSSupplierCommonPage extends InvMgrCommonTopMenuPage{

	/**
     * get supplier id.
     * @return String the supplier id.
     */
    public String getSupplierId() {
    	return browser.getTextFieldValue(".id", new RegularExpression("Supplier(Light)?View-\\d+\\.id(\\:ZERO_TO_NEW)?",false));
    }
    
    /**
     * get supplier name.
     * @return the supplier name.
     */
    public String getSupplierName(){
    	return browser.getTextFieldValue(".id", new RegularExpression("Supplier(Light)?View-\\d+\\.name",false));
    }
}
