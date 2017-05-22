package com.activenetwork.qa.awo.pages.orms.common.giftcard;

import com.activenetwork.qa.testapi.page.HtmlPopupPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * Click the "Activate Card" button in gift card order detail page, the widget will pop up
 * @author Phoebe
 * @Date  July 31,2014
 */
public class OrmsActivateGiftCardWidget extends HtmlPopupPage {
	
	private static OrmsActivateGiftCardWidget _instance;
	
	protected OrmsActivateGiftCardWidget () {
		super("title","Activate Gift Card");
	}
	
	public static OrmsActivateGiftCardWidget getInstance() {
		if(null==_instance) {
			_instance=new OrmsActivateGiftCardWidget();
		}
		
		return _instance;
	}
	
	private Property[] giftCardNumTextField() {
		return Property.toPropertyArray(".id","GiftCardOrderView.giftCardNum");
	}
	
	private Property[] dialogInfoNameSpan() {
		return Property.toPropertyArray(".class","Html.SPAN", ".className", "size8fontbold");
	}
	
	private Property[] successMsgDiv() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "message msgsuccess");
	}
	
	private Property[] errorMsgDiv() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "message msgerror");
	}
	
	private Property[] okBtn() {
		return Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("Ok", false));
	}
	
	private Property[] cancelBtn() {
		return Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("Cancel", false));
	}
	
	public void clickOK(){
		popup.clickGuiObject(this.okBtn());
	}
	
	public void clickCancel(){
		popup.clickGuiObject(this.cancelBtn());
	}
	
	public void setGiftCardNum(String num){
		popup.setTextField(this.giftCardNumTextField(), num);
	}
	
	public String getGiftCardNum(){
		return popup.getTextFieldValue(this.giftCardNumTextField());
	}
	
	public String getDialogInfo(){
		return popup.getObjectText(this.dialogInfoNameSpan());
	}
	
	public String getSuccessMsg() {
		String msg = "";
		msg = popup.getObjectText(successMsgDiv());
		return msg;
	}
	
	public String getErrorMsg(){
		String msg = "";
		msg = popup.getObjectText(this.errorMsgDiv());
		return msg;
	}
}
