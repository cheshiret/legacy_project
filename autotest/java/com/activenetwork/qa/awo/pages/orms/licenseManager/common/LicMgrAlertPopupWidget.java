package com.activenetwork.qa.awo.pages.orms.licenseManager.common;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;

/**
 * @Description: Alert pop up widget.
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Oct 16, 2012
 */
public class LicMgrAlertPopupWidget extends DialogWidget {
	   private static LicMgrAlertPopupWidget instance=null;
	   
	   private LicMgrAlertPopupWidget(){}
	   
	   public static LicMgrAlertPopupWidget getInstance(){
		   if(instance==null){
			   instance=new LicMgrAlertPopupWidget();
		   }
		   return instance;
	   }
	   
	   public boolean exists(){
		   return browser.checkHtmlObjectExists(".id", "note_alert_form");
	   }
	   
	   public String getAlertInfo(){
		   IHtmlObject[] objs = browser.getTableTestObject(".id", "note_alert_form");
		   if(objs.length < 1){
			   throw new ItemNotFoundException("Can't find Alert table.");
		   }
		   IHtmlTable table = (IHtmlTable)objs[0];
		   String alertInfo = table.getCellValue(0, 1);
		   Browser.unregister(objs);
		   return alertInfo;
	   }
}
