package com.activenetwork.qa.awo.pages.orms.inventoryManager.list;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class InvMgrCloseListWidget extends DialogWidget {
	   private static InvMgrCloseListWidget instance=null;
	   
	   private InvMgrCloseListWidget(){
		   super("Close List");
	   }
	   
	   public static InvMgrCloseListWidget getInstance(){
		   if(instance==null){
			   instance=new InvMgrCloseListWidget();
		   }
		   return instance;
	   }
	   
	   public boolean exists(){
			return browser.checkHtmlObjectExists(".id", new RegularExpression("ListCloseBean-\\d+\\.closeReason", false));
	   }
	   
	   private String prefix = "ListCloseBean-\\d+\\.";
	   public void selectCloseReason(String reason){
		   if(StringUtil.isEmpty(reason)){
			   browser.selectDropdownList(".id", new RegularExpression(prefix+"closeReason", false), 0);
		   } else {
			   browser.selectDropdownList(".id", new RegularExpression(prefix+"closeReason", false), reason);
		   }
	   }
	   
	   public void setCloseNote(String note){
		   browser.setTextArea(".id", new RegularExpression(prefix+"closeNotes", false), note);
	   }
}
