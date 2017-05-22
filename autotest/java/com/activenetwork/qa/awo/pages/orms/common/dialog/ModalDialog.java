/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.dialog;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * 
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author szhou
 * @Date Jun 16, 2014
 */
public class ModalDialog extends OrmsPage{
    private static ModalDialog _instance=null;
	
	public static ModalDialog getInstance() {
		if(null==_instance) {
			_instance=new ModalDialog();
		}
		
		return _instance;
	}
	
   protected Property[] dialogProperty;
	
	protected ModalDialog() {
		dialogProperty=new Property[2];
		dialogProperty[0]=new Property(".class","Html.DIV");
		RegularExpression pattern=new RegularExpression("modal-dialog",false);
		dialogProperty[1]=new Property(".className",pattern);
	}
	
	protected ModalDialog(String headerName) {
		dialogProperty=new Property[3];
		dialogProperty[0]=new Property(".class","Html.DIV");
		RegularExpression pattern=new RegularExpression("modal-dialog",false);
		dialogProperty[1]=new Property(".className",pattern);
		dialogProperty[2]=new Property(".text", new RegularExpression("^"+headerName+".*",false));
	}
	
	protected ModalDialog(RegularExpression content) {
		dialogProperty=new Property[3];
		dialogProperty[0]=new Property(".class","Html.DIV");
		RegularExpression pattern=new RegularExpression("modal-dialog",false);
		dialogProperty[1]=new Property(".className",pattern);
		dialogProperty[2]=new Property(".text", content);
	}
	
	protected ModalDialog(Property[] dialogProperty) {
		this.dialogProperty=dialogProperty;
	}
	
	private Property[] continueButton() {
		return Property.toPropertyArray(".class", "Html.button",".text","Continue");
	}
	
	private Property[] cancelButton() {
		return Property.toPropertyArray(".class", "Html.button",".text","Cancel");
	}
	
	private Property[] doneButton() {
		return Property.toPropertyArray(".class", "Html.button",".text","Done");
	}
	
	private Property[] okButton() {
		return Property.toPropertyArray(".class", "Html.button",".text","OK");
	}
	
	/**if true, this dialog is displayed,or it is hidden*/
	public boolean isDisplay(){
		IHtmlObject[] objs=browser.getHtmlObject(dialogProperty);
		if(objs==null || objs.length<1){
			return false;
		}
		String display=objs[objs.length-1].getProperty(".isDisable");
		if(Boolean.valueOf(display)==false){
			return true;
		}
		Browser.unregister(objs);
		return false;
	}
	
	@Override
	public boolean exists() {
		return isDisplay();
	}
	
	public void clickContinue() {
		browser.clickGuiObject(continueButton());
	}
	
	public void clickCancel() {
		browser.clickGuiObject(cancelButton());
	}
	
	public void clickDone() {
		browser.clickGuiObject(doneButton());
	}
	
	public void clickOK() {
		browser.clickGuiObject(okButton());
	}
}
