///**
// * 
// */
//package com.activenetwork.qa.awo.pages.orms.licenseManager.receipt;
//
//import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
//import com.activenetwork.qa.awo.util.MiscFunctions;
//import com.activenetwork.qa.testapi.ErrorOnPageException;
//import com.activenetwork.qa.testapi.interfaces.browser.Browser;
//import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
//import com.activenetwork.qa.testapi.util.Property;
//import com.activenetwork.qa.testapi.util.RegularExpression;
//
///**
// * @Description:
// * @Preconditions:
// * @SPEC:
// * @Task#:
// * 
// * @author szhou
// * @Date Jun 1, 2012
// */
//public class LicMgrReceiptDetailPage extends LicMgrCommonTopMenuPage {
//	private static LicMgrReceiptDetailPage _instance = null;
//
//	protected LicMgrReceiptDetailPage() {
//
//	}
//
//	public static LicMgrReceiptDetailPage getInstance() {
//		if (_instance == null) {
//			_instance = new LicMgrReceiptDetailPage();
//		}
//		return _instance;
//	}
//	
//	private Property[] addToCart() {
//		return Property.toPropertyArray(".class", "Html.A", ".text", "Add to Cart");
//	}
//	
//	private Property[] reprintReceipt() {
//		return Property.toPropertyArray(".class", "Html.A", ".text", "Reprint Receipt");
//	}
//	
//	@Override
//	public boolean exists() {
//		return (browser.checkHtmlObjectExists(".class", "Html.DIV", ".text",
//				new RegularExpression("Receipt Actions.*",false)));
//	}
//	
//	//Request H&F Conf Letter
//	public void clickRequestConfLetter()
//	{
//		browser.clickGuiObject(".class", "Html.A", ".text", "Request H&F Conf Letter", true);
//	}
//	/**
//	 * check confirm letter button enable.
//	 * @return
//	 */
//	public boolean checkConfLetterButtonEnable(){
//		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", "Request H&F Conf Letter");
//		if(objs.length<1){
//			throw new ErrorOnPageException("No specific element exist");
//		}
//		String value = objs[0].getAttributeValue("isDisabled");
//		if(value.equals("false")){
//			return true;
//		}else{
//			return false;
//		}
//	}
//	public String getAlertMsg(){
//		String msg = "";
//		IHtmlObject[] notSet = browser.getHtmlObject(".class", "Html.DIV", ".id", "NOTSET");
//		if(notSet.length>0)
//		{
//		 msg = notSet[0].text();
//		}
//		
//		Browser.unregister(notSet);
//		return msg; 
//	}
//}
