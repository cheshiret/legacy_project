package com.activenetwork.qa.awo.pages.web.uwp;



import com.activenetwork.qa.awo.pages.web.recgov.RecgovCommonPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.Timer;

/**
 * @Description:
 * This page is the Interstitial page after people click search and before the search result page.
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author bzhang
 * @Date  Oct 24, 2011
 */
public class UwpUnifiedSearchInterstitialPage extends RecgovCommonPage{
	private static UwpUnifiedSearchInterstitialPage _instance = null;

	protected UwpUnifiedSearchInterstitialPage() {
	}

	public static UwpUnifiedSearchInterstitialPage getInstance() {
		if (null == _instance)
			_instance = new UwpUnifiedSearchInterstitialPage();

		return _instance;
	}

	public boolean exists() {
		boolean flag = browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "contentProgressBar");
		return flag;
	}
	
	/**
	 * get the message like "Searching", "Retrieving" "Updating"
	 * @return
	 */
	public String getInterstitialMessage(){
		String msg = "";
		Property[] p = new Property[]{new Property(".class","Html.DIV"),new Property(".id","contentProgressBar")};
        IHtmlObject[] objs = browser.getHtmlObject(p);
      
        if(objs != null && objs.length>0) {
        	msg = objs[0].text();
        } 
        Browser.unregister(objs);
		return msg;
	}
	
	/**
	 * get the message like "Searching", "Retrieving" "Updating" in the given timeout period.
	 * @param timeout
	 * @return
	 */
	public String getInterstitialMessage(int timeout) {
		logger.info("loop and wating for Interstitial page display. please wait.. ");
		String msg = "";
		Timer timer=new Timer();
		while(!(msg.length()>0) && timer.diffLong()<timeout*1000) {
			msg=getInterstitialMessage();
		}
		return msg;
	}
	
	/**
	 * check whether the green progress bar exist on the page or not.
	 * @return
	 */
	public boolean checkProgressBarExist(){
		return browser.checkHtmlObjectExists(".class", "Html.DIV",".id", "progressBar");
	}
	
	/**
	 * verify the Interstitial text(Searching...  Retrieving... Updating...) displayed on the page
	 * @param expectMsg
	 */
	public void verifyInterstitialMessage(String expectMsg){
		String currentMsg = this.getInterstitialMessage(10).trim();
		if(currentMsg.length()>0 && currentMsg.equals(expectMsg.trim())){
			logger.info("the progress page verify successful! the interstitial message is:" + currentMsg);
		}else{
			logger.error("The Expect  interstitial message is:" + expectMsg);
			logger.error("The current interstitial message is:" + currentMsg);
			throw new ErrorOnPageException("the progress message didn't display/match with the expect value on the page");
		}
	}
}
