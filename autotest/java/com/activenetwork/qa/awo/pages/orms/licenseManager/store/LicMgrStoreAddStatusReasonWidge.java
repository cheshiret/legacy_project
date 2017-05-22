package com.activenetwork.qa.awo.pages.orms.licenseManager.store;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:This page is the sub page of system configuration for add the store status reason.
 * @Preconditions:
 * @SPEC:Add store status reason
 * @Task#:Auto-753
 * @Defects:
 * 
 * @author jwang8  
 * @Date  Jan 10, 2012  
 */
public class LicMgrStoreAddStatusReasonWidge extends DialogWidget{
	
   private static LicMgrStoreAddStatusReasonWidge instance = null;
   
   private LicMgrStoreAddStatusReasonWidge(){};
   
   public static LicMgrStoreAddStatusReasonWidge getInstance(){
	   if(null == instance){
		   instance = new LicMgrStoreAddStatusReasonWidge();
	   }
	   return instance;
   }
   /**
	 * Select the store status.
	 * @param status - the value of store status.
	 */
   public void selectAgentsStatus(String status){
	   browser.selectDropdownList(".id", new RegularExpression("StoreStatusReasonView-\\d+\\.statusID",false), status);
   }
   
   /**
	 * Select the store reason.
	 * @param reason - the value of store reason.
	 */
   public void setReason(String reason){
	   browser.setTextField(".id", new RegularExpression("StoreStatusReasonView-\\d+\\.reason",false) , reason);
   }
   /**
	 * Select the store reason.
	 * @param status - the value of store status.
	 * @param reason - the value of store reason.
	 */
   public void addStatusReason(String status, String reason){
	   this.selectAgentsStatus(status);
	   this.setReason(reason);
   }
}
