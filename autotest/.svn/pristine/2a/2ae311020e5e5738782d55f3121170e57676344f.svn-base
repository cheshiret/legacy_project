package com.activenetwork.qa.awo.pages.orms.common.popup;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class OrmsProcessOrderCartPopupPage extends OrmsPinPopupPage {

	private static OrmsProcessOrderCartPopupPage _instance;

	protected OrmsProcessOrderCartPopupPage () {
		super("Enter Pin #|");//Shane[20131016] in 3.05.00.141 build, pin pop up has no title
	}

	public static OrmsProcessOrderCartPopupPage getInstance() {
		if(null==_instance) {
			_instance=new OrmsProcessOrderCartPopupPage();
		}

		return _instance;
	}

	private void selectRefund(String yesOrno, int index) {
		String value=yesOrno+"_refund";
		IHtmlObject[] objs=browser.getRadioButton(".id", value);
		if(objs.length<1) {
			Browser.unregister(objs);
			objs=browser.getRadioButton(".id", "issue_refund_" + index, ".value", value);
		}

		if(objs.length>0) {
			((IRadioButton)objs[0]).select();
		}
	}

	public void selectRefundYes(int index) {
		selectRefund("yes", index);
	}

	public void selectRefundNo(int index) {
		selectRefund("no", index);
	}

	public void enterNote(String note) {
		browser.setTextArea("id","note", note);
	}

	public void enterOverrideReason(String reason) {
		browser.setTextArea(".id","reason", reason);
	}

	public void enterGiftCardNumber(String number, int index) {
		browser.setTextField(".id", new RegularExpression("giftcardnumber_\\d+", false), number, index);
	}

	private void selectIssueRefundToGiftCard(String yesOrno, int index) {
		browser.selectRadioButton(".value", yesOrno, ".id", new RegularExpression("to_giftcard_\\d+", false), index);
	}

	public void selectIssueToGiftCardYes(int index) {
		selectIssueRefundToGiftCard("yes", index);
	}

	public void selectIssueToGiftCardNo(int index) {
		selectIssueRefundToGiftCard("no", index);
	}

	public void setupInformation(String pin, boolean issueCash,	boolean issueGiftCard, String giftCardNumber) {
		this.setupInformation(pin, issueCash, issueGiftCard, giftCardNumber, 0);
	}

	public void setupInformation(String pin, boolean issueCash,	boolean issueGiftCard, String giftCardNumber, int index) {
		this.enterPIN(pin);
		if(issueCashDisplayed()) {
			if(issueCash) {
				this.selectRefundYes(index);
			} else {
				this.selectRefundNo(index);
			}
		}

		if(issueGiftcardDisplayed()){
			if(issueGiftCard) {
				this.enterGiftCardNumber(giftCardNumber, index);
				this.selectIssueToGiftCardYes(index);
			} else {
				this.selectIssueToGiftCardNo(index);
			}
		}
	}

	public boolean issueCashDisplayed() {
		IHtmlObject[] o=browser.getHtmlObject(".class","Html.DIV",".id","issueCashRefund_0");
		boolean displayed=false;
		if(o.length>0) {
			String style=o[0].style("DISPLAY").trim();
			if(style!=null && style.length()>0) {
				displayed=!style.equalsIgnoreCase("none");
			} else {
				displayed=true;
			}
		}
		Browser.unregister(o);
		return displayed;
	}

	public boolean issueGiftcardDisplayed() {
		IHtmlObject[] o=browser.getHtmlObject(".class","Html.DIV",".id", new RegularExpression("toGiftCard_\\d+", false));
		boolean displayed=false;
		if(o.length>0) {
			String style=o[0].style("DISPLAY");

			if(style!=null && style.length()>0) {
				displayed=!style.trim().equalsIgnoreCase("none");
			} else {
				displayed=true;
			}
		}
		Browser.unregister(o);
		return displayed;
	}

	public int getIssueToObjectCount() {
		IHtmlObject[] cashObjs = browser.getHtmlObject(".class", "Html.DIV", ".id", new RegularExpression("issueCashRefund_\\d+", false));
		IHtmlObject[] giftCardObjs = browser.getHtmlObject(".class", "Html.DIV", ".id", new RegularExpression("toGiftCard_\\d+", false));
		int count = 0;
		if(cashObjs.length > 0) {
			count += cashObjs.length;
		}
		if(giftCardObjs.length > 0) {
			count += giftCardObjs.length;
		}

		Browser.unregister(cashObjs);
		Browser.unregister(giftCardObjs);

		return count;
	}

	public void closePopupReasonBox(String reason) {
		RegularExpression idPattern=new RegularExpression("(reason)",false);
		browser.setTextArea(".id", idPattern, reason);
		clickOK();
	}

	/**
	 * Set the adjust fee reason
	 * @param reason
	 */
	public void setAdjustReason(String reason){
	  //browser.setTextArea(".id","fee_adjustment_notes",reason);
//	  TestObject[] objs=browser.find(Browser.atDescendant(".id","fee_adjustment_notes"));
//	   MiscFunctions.setText(objs[0],reason);
//
//	   Browser.unregister(objs);
		browser.setTextArea(".id","fee_adjustment_notes",reason);
	}

//	/**
//	 * Get pop up message
//	 * @return pop up message
//	 */
//	public String getDiaMessage(){
//	  return browser.getDialogMessage();
//
//	}

	/**
	 * Get popup message
	 * @return popup message
	 */
	public String getPopUpMessage(){
	  IHtmlObject[] obj=browser.getAllTestObjects();
	  String popupMessage=obj[0].getProperty(".text").toString();
	  Browser.unregister(obj);
	  return popupMessage;
	}

	@Override
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	@Override
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

//	public void test() {
//		HtmlObject[] o=browser.getHtmlObject(".class","Html.DIV",".id","toGiftCard_0");
//	}

//
//	/**
//	 * Click OK button
//	 * @return---If pop up dialouge and OK button exist, return true
//	 */
//	public boolean clickOK() {
//		//click OK
//		//RegularExpression reg=new RegularExpression(".*desk/order/html/pin.html#a",false);
//		boolean executed = false;
//		TestObject[] objs = null;
//		if (browser.exists()) {//if the popup is print ticket window, it may disappear automatically
//			try {
//				RegularExpression reg = new RegularExpression("Html\\.(A|BUTTON)", false);
////				objs = browser.find(Browser.atDescendant(".class", reg, ".text","OK"));
//				Property[] property=new Property[2];
//				property[0]=new Property(".class", reg);
//				property[1]=new Property(".text","OK");
//				objs = browser.find(Browser.atList(property));
//			} catch (ObjectNotFoundException e) {
//			}
//		}
//		if (objs != null && objs.length > 0 && browser.exists()) {
//			MiscFunctions.clickObject(objs[0]);
//			executed = true;
//		}
//		Browser.unregister(objs);
//		return executed;
//	}

}
