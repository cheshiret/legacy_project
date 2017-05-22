package com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.pospurchaseorder;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Jun 27, 2012
 */
public class InvMgrCancelPOSPurchaseOrderPage extends InvMgrCommonTopMenuPage {

    public static InvMgrCancelPOSPurchaseOrderPage instance = null;
    
    private InvMgrCancelPOSPurchaseOrderPage(){};
    
    public static InvMgrCancelPOSPurchaseOrderPage getInstance(){
    	if(null == instance){
    		instance = new InvMgrCancelPOSPurchaseOrderPage();
    	}
		return instance;
    }
    
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("POView-\\d+.internalNotes",false));
	}
	
	public void setInternalNotes(String internalNotes){
		browser.setTextArea(".id", new RegularExpression("POView-\\d+.internalNotes",false), internalNotes, true);
	}
	
	
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public void clickApply(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}
}
