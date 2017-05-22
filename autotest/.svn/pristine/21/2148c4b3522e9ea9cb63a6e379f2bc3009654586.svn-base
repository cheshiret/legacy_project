package com.activenetwork.qa.awo.pages.orms.common.dialog;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class DialogWidget extends OrmsPage {
	private static DialogWidget _instance=null;
	
	public static DialogWidget getInstance() {
		if(null==_instance) {
			_instance=new DialogWidget();
		}
		
		return _instance;
	}
	
	protected Property[] widgetProperty;
	
	protected DialogWidget() {
		widgetProperty=new Property[2];
		widgetProperty[0]=new Property(".class","Html.DIV");
		RegularExpression pattern=new RegularExpression("ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable",false);
		widgetProperty[1]=new Property(".className",pattern);
	}
	
	protected DialogWidget(String titleName) {
		widgetProperty=new Property[3];
		widgetProperty[0]=new Property(".class","Html.DIV");
		RegularExpression pattern=new RegularExpression("ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable",false);
		widgetProperty[1]=new Property(".className",pattern);
		widgetProperty[2]=new Property(".text", new RegularExpression("^"+titleName+"close.*",false));
	}
	
	protected DialogWidget(RegularExpression content) {
		widgetProperty=new Property[3];
		widgetProperty[0]=new Property(".class","Html.DIV");
		RegularExpression pattern=new RegularExpression("ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable",false);
		widgetProperty[1]=new Property(".className",pattern);
		widgetProperty[2]=new Property(".text", content);
	}
	
	protected DialogWidget(Property[] widgetProperty) {
		this.widgetProperty=widgetProperty;
	}
	
	/**if true, this widget is displayed,or it is hidden*/
	public boolean isDisplay(){
		IHtmlObject[] objs=browser.getHtmlObject(widgetProperty);
		if(objs==null || objs.length<1){
			return false;
		}
		String display=objs[objs.length-1].style("display");//Shane[20140304]for multiple objects found,use the last one
		if(display.trim().equalsIgnoreCase("block")){
			return true;
		}
		Browser.unregister(objs);
		return false;
	}
	
	@Override
	public boolean exists() {
		return isDisplay();
	}
	
	public String getTitle() {
		return browser.getObjectText(".class","Html.SPAN",".id","ui-dialog-title-_message_dialog");
	}
	
	public void close() {
		browser.clickGuiObject(".class","Html.A",".className","ui-dialog-titlebar-close ui-corner-all");
	}
	
	public void clickOK() {
		clickButtonByText("OK");
	}
	
	public void clickCancel() {
		clickButtonByText("Cancel");
	}
	
	public void clickYes() {
		clickButtonByText("Yes");
	}
	
	public void clickNo() {
		clickButtonByText("No");
	}
	
	public void clickContinue() {
		clickButtonByText("Continue");
	}
	
	public void clickOKAndWait() {
		this.clickOK();
		ajax.waitLoading();
	}
	
	protected IHtmlObject[] getWidget() {
		return browser.getHtmlObject(widgetProperty);
	}
	
	protected void clickButtonByText(String text) {
		Property[] p=new Property[2];
		p[0]=new Property(".class","Html.A");
		p[1]=new Property(".text",new RegularExpression(text, false));
		
		IHtmlObject[] buttons=browser.getHtmlObject(Property.atList(widgetProperty,p));
		
		int size=buttons.length;
		buttons[size-1].click();
		Browser.unregister(buttons);
//		browser.clickGuiObject(Property.atList(widgetProperty,p),true,0);
	}
	
	public String getErrorMsg() {
		Property[] p=Property.toPropertyArray(".class","Html.DIV",".name","NOTSET");
		String msg = browser.getObjectText(Property.atList(widgetProperty,p));
		
		if(StringUtil.isEmpty(msg)) {
			Property[] p1 = Property.toPropertyArray(".class", "Html.SPAN", ".className", new RegularExpression("(messagebox_text)|(message msgerror)",false));
			msg = browser.getObjectText(Property.atList(widgetProperty, p1));
			if(StringUtil.isEmpty(msg)){
				p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", new RegularExpression("(messagebox_text)|(message msgerror)",false));
				msg = browser.getObjectText(Property.atList(widgetProperty, p1));
			}
		}
		
		return msg;
	}
	
	/**
	 * expect message should be a regular expression
	 * @param expectMsg
	 */
	public void verifyMessage(String expectMsg){
		String actualMsg = this.getErrorMsg();
		
		if(!actualMsg.matches(expectMsg)){
			throw new ErrorOnPageException("Confirm message is not correct. Expect one like:"+expectMsg+", but actual one is:"+actualMsg);
		} else {
			logger.info("Confrim message is correct as:"+actualMsg);
		}
	}
	
	public boolean hasRadioOptions() {
		return browser.checkHtmlObjectDisplayed(Property.atList(this.widgetProperty,Property.toPropertyArray(".class","Html.INPUT.radio")));
	}
	
	public void selectYesRadioOption() {
		browser.selectRadioButton(".class","Html.INPUT.radio",".id",new RegularExpression("RadioButtonGroup-\\d+\\.selectedValue",false), 0);
	}
	
	public void selectNoRadioOption() {
		browser.selectRadioButton(".class","Html.INPUT.radio",".id",new RegularExpression("RadioButtonGroup-\\d+\\.selectedValue",false), 2);
	}
}
