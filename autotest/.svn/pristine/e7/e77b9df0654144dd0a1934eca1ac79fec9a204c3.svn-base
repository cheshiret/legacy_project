package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrCustomerMergeOptionWidget extends DialogWidget{
	private static LicMgrCustomerMergeOptionWidget instance=null;
	   
	   private LicMgrCustomerMergeOptionWidget(){}
	   
	   public static LicMgrCustomerMergeOptionWidget getInstance(){
		   if(instance==null){
			   instance=new LicMgrCustomerMergeOptionWidget();
		   }
		   return instance;
	   }
	   
	   public boolean exists(){
		   RegularExpression pattern=new RegularExpression("MergeSelection-\\d+\\.mergeSelection",false);
	       return super.exists() && browser.checkHtmlObjectExists(".class", "Html.INPUT.radio",".name",pattern);
	   }
	   
	   public void selectViewMergeCandidates() {
		   RegularExpression pattern=new RegularExpression("MergeSelection-\\d+\\.mergeSelection",false);
		   browser.selectRadioButton(Property.toPropertyArray(".class", "Html.INPUT.radio",".value","1",".name",pattern),0);
	   }
	   
	   public void selectMergeWithMDWFP() {
		   RegularExpression pattern=new RegularExpression("MergeSelection-\\d+\\.mergeSelection",false);
		   browser.selectRadioButton(Property.toPropertyArray(".class", "Html.INPUT.radio",".value","2",".name",pattern),0);
	   }
	   
	   public void setMDWFP(String mdwfp) {
		   RegularExpression pattern = new RegularExpression("MergeSelection-\\d+\\.customerNumber",false);
		   browser.setTextField(".name",pattern, mdwfp);
	   }

}
