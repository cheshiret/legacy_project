/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Certification;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @ScriptName LicMgrAddCertificationWidget.java
 * @Date:Feb 14, 2011
 * @Description:
 * @author asun
 */
public class LicMgrAddCertificationWidget extends DialogWidget {
   private static LicMgrAddCertificationWidget instance=null;
   
   private LicMgrAddCertificationWidget(){}
   
   public static LicMgrAddCertificationWidget getInstance(){
	   if(instance==null){
		   instance=new LicMgrAddCertificationWidget();
	   }
	   return instance;
   }
   
   public boolean exists(){
	   boolean flag1=super.exists();
	   boolean flag2=browser.checkHtmlObjectExists(".class", "Html.DIV",".text","Add Certificationclose");
       return flag1 && flag2;
   }
   
   public List<String> getCertificationTypeElements() {
	   return browser.getDropdownElements(".id", new RegularExpression("CustomerCertificationView-\\d+\\.certType",false));
   }
   
   public void selectCertificationType(String type){
	   RegularExpression regx=new RegularExpression("CustomerCertificationView-\\d+\\.certType",false);
	   browser.selectDropdownList(".id", regx, type);
   }
   
   public void setCertificationNum(String num){
	   RegularExpression regx=new RegularExpression("CustomerCertificationView-\\d+\\.certificationNumber",false);
       browser.setTextField(".id", regx, num);
   }
   
   public void setEffectiveFrom(String effectivefrom){
	   RegularExpression regx=new RegularExpression("CustomerCertificationView-\\d+\\.from_ForDisplay",false);
       browser.setTextField(".id",regx,effectivefrom);
   }
   
   public void setEffectiveTo(String effectiveTo){
	   RegularExpression regx=new RegularExpression("CustomerCertificationView-\\d+\\.to_ForDisplay",false);
       browser.setTextField(".id", regx, effectiveTo);
   }
   
   /**
    * Get error message when the entries violating requirement
    * @return
    */
   public String getErrorMessage() {
	   IHtmlObject objs[] = browser.getHtmlObject(".className", "message msgerror", ".id", "NOTSET");
	   if(objs.length < 1) {
		   throw new ObjectNotFoundException("Can't find error message object.");
	   }
	   String msg = objs[0].getProperty(".text").trim();
	   
	   Browser.unregister(objs);
	   return msg;
   }
   
   public void setCertification(Certification certification){
	   this.selectCertificationType(certification.type);
	   this.setCertificationNum(certification.num);
	   this.setEffectiveFrom(certification.effectiveFrom);
	   if(null != certification.effectiveTo && certification.effectiveTo.trim().length()>0){
	       this.setEffectiveTo(certification.effectiveTo);
	   }
   }
   
   /**
    * Verify effective from date component works correctly
    * @param invalidDates
    * @return
    */
   public boolean verifyEffectiveFromDateFieldValid(String invalidDates[]) {
	   return verifyAutomaticDateCorrection((IText)browser.getTextField(".id", new RegularExpression("CustomerCertificationView-\\d+\\.from_ForDisplay", false))[0], invalidDates);
   }
   
   /**
    * Verify effective to date component works correctly
    * @param invalidDates
    * @return
    */
   public boolean verifyEffectiveToDateFieldValid(String invalidDates[]) {
	   return verifyAutomaticDateCorrection((IText)browser.getTextField(".id", new RegularExpression("CustomerCertificationView-\\d+\\.to_ForDisplay", false))[0], invalidDates);
   }
}
