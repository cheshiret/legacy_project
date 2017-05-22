/**
 *
 */
package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;

/**
 * @Description:  Add the claim free gift page.
 * @Preconditions:
 * @SPEC:
 * @Task#: The claim free gift page was turn on.
 * @author jwang8
 * @Date  Jan 19, 2012
 */
public class UwpClaimFreeGiftPage extends UwpPage{

   public static UwpClaimFreeGiftPage instance = null;

   private UwpClaimFreeGiftPage(){};
	/* (non-Javadoc)
	 * @see testAPI.interfaces.IPage#exists()
	 */

   public static UwpClaimFreeGiftPage getInstance(){
	   if(null == instance){
		   instance = new UwpClaimFreeGiftPage();
	   }
	   return instance;
   }
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "nobtn");
	}

	/**
	 * Set the email page.
	 * @param email -  the value of email
	 */
	public void setVerifyEmail(String email){
		browser.setTextField(".id", "EMAIL_INPUT_VERIFY", email);
	}

	/**
	 * Click the yes button.
	 */
	public void clickYes(){
		browser.clickGuiObject(".id", "yesbtn");
	}
	/**
	 * Click the No Thanks button.
	 */
	public void clickNoThanks(){
		browser.clickGuiObject(".id", "nobtn");
	}

}
