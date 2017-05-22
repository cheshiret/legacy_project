package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.page.HtmlPopupPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * @Description:
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jul 23, 2014
 */
public class LicMgrAddHuntLocationImagePopupPage extends HtmlPopupPage {
	private static LicMgrAddHuntLocationImagePopupPage _instance;
	
	protected LicMgrAddHuntLocationImagePopupPage () {
		super("title",new RegularExpression("activeworks \\| outdoors License Manager.*",false));
	}
	
	public static LicMgrAddHuntLocationImagePopupPage getInstance() {
		if(null==_instance) {
			_instance=new LicMgrAddHuntLocationImagePopupPage();
		}
		
		return _instance;
	}
	
	protected Property[] browseBtn(){
		return Property.toPropertyArray(".id", "huntLocationImageLocalLocation");
	}
	
	public void clickBrowseBtn(){
		popup.clickGuiObject(browseBtn());
	}
	
	public void setLocationOfGiftCardFile(String locationOfFile){
		popup.setTextField(".id", "filepath", locationOfFile);
	}
	
	public void clickStartImport(){
		popup.clickGuiObject(".class", "Html.A", ".text", "Start Import");
	}
	
	public void clickBrowse() {
		IHtmlObject objs[] = popup.getHtmlObject(".id", "filepath");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find File Upload component.");
		}
		objs[0].click();
		
		Browser.unregister(objs);
	}

	public void clickCancel() {
		popup.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	public void clickOK() {
		popup.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

}

