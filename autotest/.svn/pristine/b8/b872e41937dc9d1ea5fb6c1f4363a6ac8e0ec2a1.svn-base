package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.testapi.page.HtmlPopupPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description: Hunt Location Add Image Popup Page in License Manager
 * @author Lesley Wang
 * @Date  Aug 8, 2013
 */
public class LicMgrHuntLocationImagePopupPage extends HtmlPopupPage {
	private static LicMgrHuntLocationImagePopupPage _instance=null;
	private static RegularExpression urlPattern=new RegularExpression(".*HuntLocationImagePage.*entityoperation=EDIT",false);
	
	protected LicMgrHuntLocationImagePopupPage() {
		super("url", urlPattern);
	}
	
	public static LicMgrHuntLocationImagePopupPage getInstance() {
		if(null==_instance) {
			_instance=new LicMgrHuntLocationImagePopupPage();
		}
		
		return _instance;
	}
	
	public void setImgFilePath(String filePath) {
		popup.setTextField(".id", "huntLocationImageLocalLocation", filePath);
	}
	
	public void clickOK() {
		popup.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickCancel() {
		popup.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
}
