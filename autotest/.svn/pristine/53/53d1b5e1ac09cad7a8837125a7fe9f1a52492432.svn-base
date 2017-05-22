/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Certification;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @ScriptName LicMgrEditCertificationWidget.java
 * @Date:Feb 14, 2011
 * @Description:
 * @author asun
 */
public class LicMgrEditCertificationWidget extends DialogWidget {
	 private static LicMgrEditCertificationWidget instance=null;
	   
	   private LicMgrEditCertificationWidget(){}
	   
	   public static LicMgrEditCertificationWidget getInstance(){
		   if(instance==null){
			   instance=new LicMgrEditCertificationWidget();
		   }
		   return instance;
	   }
	   
	   public boolean exists(){
		   boolean flag1=super.exists();
		   boolean flag2=browser.checkHtmlObjectExists(".class", "Html.DIV",".text","Edit Certificationclose");
	       return flag1 && flag2;
	   }
	   
	   public void setCertificationNum(String num){
		   RegularExpression regx=new RegularExpression("CustomerCertificationView-\\d+\\.certificationNumber",false);
	       browser.setTextField(".id", regx, num);
	   }
	   
	   public void setEffectiveFrom(String effectivefrom){
		   RegularExpression regx=new RegularExpression("CustomerCertificationView-\\d+\\.from_ForDisplay",false);
	       browser.setTextField(".id",regx,effectivefrom, 0, IText.Event.LOSEFOCUS);
	   }
	   
	   public void setEffectiveTo(String effectiveTo){
		   RegularExpression regx=new RegularExpression("CustomerCertificationView-\\d+\\.to_ForDisplay",false);
	       browser.setTextField(".id", regx, effectiveTo, 0, IText.Event.LOSEFOCUS);
	   }
	   
	   public void setCertification(Certification c){
		   this.setCertificationNum(c.num);
		   this.setEffectiveFrom(c.effectiveFrom);
		   this.setEffectiveTo(c.effectiveTo);
	   }
	   
	   /**
	    * Get specific attribute value identified by its corresponding name
	    * @param attributeName
	    * @return
	    */
	   private String getAttributeValueByName(String attributeName) {
		   Property property[] = new Property[3];
		   property[0] = new Property(".class", "Html.SPAN");
		   property[1] = new Property(".className", "inputwithrubylabel");
		   property[2] = new Property(".text", new RegularExpression("^" + attributeName, false));
		   IHtmlObject objs[] = browser.getHtmlObject(property);
		   if(objs.length < 1) {
			   throw new ObjectNotFoundException("Can't find any attribute named - " + attributeName);
		   }
		   int index = (attributeName.equalsIgnoreCase("Status") || attributeName.equalsIgnoreCase("Creation User"))? 1:0;
		   String value = objs[index].getProperty(".text").split(attributeName)[1].trim();
		   
		   Browser.unregister(objs);
		   return value;
	   }
	   
	   public String getCertificationID() {
		   return this.getAttributeValueByName("ID");
	   }
	   
	   public String getCertificationStatus() {
		   return this.getAttributeValueByName("Status");
	   }
	   
	   public String getCreationLocation(){
		   return this.getAttributeValueByName("Creation Location");
	   }
	   
	   public String getCreationDateTime() {
		   return this.getAttributeValueByName("Creation Date/Time");
	   }
	   
	   public String getCreationUser() {
		   return this.getAttributeValueByName("Creation User");
	   }
	   
	   public String getCertificationType() {
		   return browser.getDropdownListValue(".id", new RegularExpression("CustomerCertificationView-\\d+\\.certType", false), 0);
	   }
	   
	   public String getCertificationNum() {
		   return browser.getTextFieldValue(".id", new RegularExpression("CustomerCertificationView-\\d+\\.certificationNumber", false));
	   }
	   
	   public String getEffectiveFromDate() {
		   return browser.getTextFieldValue(".id", new RegularExpression("CustomerCertificationView-\\d+\\.from_ForDisplay", false));
	   }
	   
	   public String getEffectiveToDate() {
		   return browser.getTextFieldValue(".id", new RegularExpression("CustomerCertificationView-\\d+\\.to_ForDisplay", false));
	   }
	   
	   public String getErrorMessage() {
			return browser.getObjectText(".id", "NOTSET");
		}
	   
		public boolean checkCertificationIDIsEditable(){
			return this.checkSpanObjectIsEditable(new RegularExpression("^ID.*",false));
		}
		
		public boolean checkCertificationStatusIsEditable(){
			return this.checkSpanObjectIsEditable(new RegularExpression("^Status.*",false));
		}
		
		public boolean checkCreateLocationIsEditable(){
			return this.checkSpanObjectIsEditable(new RegularExpression("^Creation Location.*",false));
		}
		
		public boolean checkCreateTimeIsEditable(){
			return this.checkSpanObjectIsEditable(new RegularExpression("^Creation Date/Time*",false));
		}
		
		public boolean checkCreateUserIsEditable(){
			return this.checkSpanObjectIsEditable(new RegularExpression("^Creation User*",false));
		}
		
		public boolean checkSpanObjectIsEditable(Object object){
			boolean isEditable = true;
			Property[] p = new Property[3];
			p[0] = new Property(".class", "Html.SPAN");
			p[1] = new Property(".text",object);
			p[2] = new Property(".className", "inputwithrubylabel");
			IHtmlObject[] objs = browser.getHtmlObject(p);
			if(objs.length<1){
				throw new ObjectNotFoundException("SPAN object not found.");
			}
			
			Property[] p1 = new Property[1];
			p1[0] = new Property(".class","Html.SPAN");
			IHtmlObject[] spanObjs = browser.getHtmlObject(p1, objs[objs.length-1]);
			
			if(spanObjs.length<1){
				throw new ObjectNotFoundException("SPAN object not found.");
			}
			
			String value = spanObjs[spanObjs.length-1].getProperty("isContentEditable");
			if(value.equalsIgnoreCase("true")){
				isEditable = true;
			}else {
				isEditable = false;
			}
			
			Browser.unregister(spanObjs);
			Browser.unregister(objs);
			return isEditable;
		}
		
		public boolean checkCertificationTypeIsEditable(){
			boolean isEditable = true;
			IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("^CustomerCertificationView-\\d+\\.certType",false));
			if(objs.length<1){
				throw new ObjectNotFoundException("Certification type object not found.");
			}
			String value = objs[0].getProperty("isDisabled");
			if(value.equalsIgnoreCase("true")){
				isEditable = false;
			}else {
				isEditable = true;
			}
			Browser.unregister(objs);
			return isEditable;
		}
		
		public boolean verifyCertificationDetailsInfo(Certification expectedCertification, Certification actualCertification) {
			boolean result = true;
			

			logger.info("Verify whether the actaul certification details are correct with expected.");
			if(!actualCertification.status.equalsIgnoreCase(expectedCertification.status)) {
				logger.error("Expect certification satus should be " + expectedCertification.status 
						+ ", but acutally is " + actualCertification.status);
				result &= false;
			} else {
				logger.info("Certification Status is correct.");
			}

			if(!actualCertification.type.equalsIgnoreCase(expectedCertification.type)) {
				logger.error("Expect certification type should be " + expectedCertification.type 
						+ ", but acutally is " + actualCertification.type);
				result &= false;
			} else {
				logger.info("Certification Type is correct.");
			}

			if(!actualCertification.num.equalsIgnoreCase(expectedCertification.num)) {
				logger.error("Expect certification number should be " + expectedCertification.num 
						+ ", but acutally is " +actualCertification.num);
				result &= false;
			} else {
				logger.info("Certification Number is correct.");
			}

			if(DateFunctions.compareDates(actualCertification.effectiveFrom, expectedCertification.effectiveFrom) != 0) {
				logger.error("Expect effective from date should be " + expectedCertification.effectiveFrom 
						+ ", but acutally is " + actualCertification.effectiveFrom);
				result &= false;
			} else {
				logger.info("Certification Effective From Date is correct.");
			}

			if(DateFunctions.compareDates(actualCertification.effectiveTo, expectedCertification.effectiveTo) != 0) {
				logger.error("Expect effective to date should be " + expectedCertification.effectiveTo 
						+ ", but acutally is " + actualCertification.effectiveTo);
				result &= false;
			} else {
				logger.info("Certification Effective To Date is correct.");
			}

			if(!actualCertification.creationLocation.equalsIgnoreCase(expectedCertification.creationLocation)) {
				logger.error("Expect certification creation Location should be " + expectedCertification.creationLocation 
						+ ", but acutally is " +actualCertification.creationLocation);
				result &= false;
			} else {
				logger.info("Certification Creation Location is correct.");
			}
			
			if(DateFunctions.compareDates(actualCertification.creationDateTime, expectedCertification.creationDateTime) != 0) {
				logger.error("Expect creation Date/Time should be " + expectedCertification.creationDateTime 
						+ ", but acutally is " + actualCertification.creationDateTime);
				result &= false;
			} else {
				logger.info("Certification Creation Date/Time is correct.");
			}

			expectedCertification.creationUser = expectedCertification.creationUser.replace(" ", "");
			if(!actualCertification.creationUser.equalsIgnoreCase(expectedCertification.creationUser)) {
				logger.error("Expect certification creation User should be " + expectedCertification.creationUser 
						+ ", but acutally is " +actualCertification.creationUser);
				result &= false;
			} else {
				logger.info("Certification Creation User is correct.");
			}
			
			return result;
		}
	   
	   /**
	    * Get certification info
	    * @return
	    */
	   public Certification getCertificationInfo() {
		   Certification certification = new Certification();
		   certification.id = this.getCertificationID();
		   certification.status = this.getCertificationStatus();
		   certification.type = this.getCertificationType();
		   certification.num = this.getCertificationNum();
		   certification.effectiveFrom = this.getEffectiveFromDate();
		   certification.effectiveTo = this.getEffectiveToDate();
		   certification.creationLocation = this.getCreationLocation();
		   certification.creationDateTime = this.getCreationDateTime();
		   certification.creationUser = this.getCreationUser();
		   
		   return certification;
	   }
}
