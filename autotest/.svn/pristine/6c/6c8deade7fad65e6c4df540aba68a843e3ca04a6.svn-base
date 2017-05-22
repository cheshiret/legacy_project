package com.activenetwork.qa.awo.pages.orms.callManager;

import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Swang5
 * @Date  Oct 30, 2012
 */
public class CallMgrRequestConfLetterPage extends CallManagerPage {
	  
	  static private CallMgrRequestConfLetterPage _instance = null;

		protected CallMgrRequestConfLetterPage() {
		}

		static public CallMgrRequestConfLetterPage getInstance()
				throws PageNotFoundException {
			if (null == _instance) {
				_instance = new CallMgrRequestConfLetterPage();
			}

			return _instance;
		}

		public boolean exists() {
			return browser.checkHtmlObjectExists(".class", "Html.TR", ".text", new RegularExpression("Confirmation Letter Distribution method.*", false));
		}
		
		/**
		 * Click distribution method
		 * @param methodName: Email, File/Print, Add to Batch
		 */
		public void clickDistributionMethod(String methodName){
			browser.selectRadioButton(".id", "method", ".value", new RegularExpression(methodName, false));
		}
		
		public void clickOK(){
			browser.clickGuiObject(".class","Html.A",".text","Ok");
		}
		
		public void clickCancel(){
			browser.clickGuiObject(".class","Html.A",".text","Cancel");
		}
	}
