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
 * @Date  Jun 25, 2012
 */
public class InvMgrClosePOSPurchaseOrderPage extends InvMgrCommonTopMenuPage {

    public static InvMgrClosePOSPurchaseOrderPage instance = null;
    
    private InvMgrClosePOSPurchaseOrderPage(){};
    
    public static InvMgrClosePOSPurchaseOrderPage getInstance(){
    	if(null == instance){
    		instance = new InvMgrClosePOSPurchaseOrderPage();
    	}
		return instance;
    }
    
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("POView-\\d+.internalNotes",false));
	}
	
	public void setInternalNotes(String internalNotes){
//		HtmlObject[] top = browser.getHtmlObject(".class", "Html.Table", ".text", new RegularExpression("^Internal Notes.*", false));
//		if(top.length < 1){
//			throw new ItemNotFoundException("Can't find iternal notes.");
//		}
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
