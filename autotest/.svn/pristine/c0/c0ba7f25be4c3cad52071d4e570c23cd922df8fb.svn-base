/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product;

import com.activenetwork.qa.awo.datacollection.legacy.orms.DocumentTemplateInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrProductConfigurationPage;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**  
 * @Description:  Document template detail page.
 * @Preconditions:  
 * @SPEC:  Add Document Template
 * @Task#: Auto-868
 * @author jwang8  
 * @Date  Feb 13, 2012    
 */
public class LicMgrDocumentTemplatesdetailPage extends LicMgrProductConfigurationPage{
	public static LicMgrDocumentTemplatesdetailPage instance = null;
	
	private LicMgrDocumentTemplatesdetailPage(){};
	
	public static LicMgrDocumentTemplatesdetailPage getInstance(){
		if(instance == null){
			instance = new LicMgrDocumentTemplatesdetailPage();
		}
		return instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "DocumentTemplatePanel");
	}
	
	/**
	 * Get document template name
	 * @return String- the name of document template.
	 */
	public String getDocuTemplateName(){
		return browser.getTextFieldValue(".id", new RegularExpression("DocumentTemplateView-\\d+\\.name", false));
	}
	
	/**
	 * Get document template type
	 * @return String- the name of document type.
	 */
	public String getTemplateType(){
		return browser.getDropdownListValue(".id", new RegularExpression("DocumentTemplateView-\\d+\\.templateFile",false));
	}
	
	/**
	 * Get document template Max reprint count
	 * @return String- the data of max reprint count.
	 */
	public String getMaxReprintCount(){
		return browser.getTextFieldValue(".id", new RegularExpression("DocumentTemplateView-\\d+\\.maxReprintCount", false));
	}
	
	/**
	 * Get document template harvest document.
	 * @return String- the value of harvest document template.
	 */
	public String getHarvestDocument(){
		return browser.getDropdownListValue(".id", new RegularExpression("DocumentTemplateView-\\d+\\.harvestDocument", false));
	}
	
	/**
	 * Get combination indicator value.
	 * @return String- the value of combination indicator.
	 */
	public String getCombinationIndicator(){
		return browser.getDropdownListValue(".id", new RegularExpression("DocumentTemplateView-\\d+\\.indicator", false));
	}
	
	/**
	 *Click the back button.
	 */
	public void clickBack(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Back");
	}
	
	/**
	 * Get the attribute name.
	 * @param String - the value of attribute name.
	 * @return String- the value of attribute name.
	 */
//	private String getAttributeValueByName(String attributeName){
//		IHtmlObject objs[] = null;
//		Property property[] = new Property[3];
//		property[0] = new Property(".class", "Html.DIV");
//		property[1] = new Property(".className", "inputwithrubylabel");
//		property[2] = new Property(".text", new RegularExpression("^" + attributeName, false));
//		
//		if(null != attributeName && attributeName.length() > 0){
//			objs = browser.getHtmlObject(property);
//		} else {
//			throw new ErrorOnDataException("Unknown attribute name.");
//		}
//		
//		String attributeValue = "";
//		System.out.println(objs.length);
//		if(objs.length ==1){
//			attributeValue = objs[0].getProperty(".text").split(attributeName)[1].trim();
//		} else {
//			throw new ActionFailedException("Find 0 or multiple DIV object named - " + attributeName + ".");
//		}
//		Browser.unregister(objs);
//		return attributeValue;
//	}
	
	public String getAddUser(){
		return browser.getObjectText(".id", new RegularExpression("DocumentTemplateView-\\d+.createUserName", false)).replaceAll("Added User", StringUtil.EMPTY);
	}
	
	public String getAddLocation(){
		return browser.getObjectText(".id", new RegularExpression("DocumentTemplateView-\\d+.createLocationName", false)).replaceAll("Added Location", StringUtil.EMPTY);
	}
	
	public boolean compareDocuTemplateDetailInfo(DocumentTemplateInfo exceptDocuTemplateInfo){
		boolean pass = true;
		if(!this.getDocuTemplateName().equals(exceptDocuTemplateInfo.docTemplateName)){
			pass &= false;
			logger.error("The document template name error");
		}
		if(!this.getTemplateType().equals(exceptDocuTemplateInfo.templateType)){
			pass &= false;
			logger.error("The document template type error");
		}
		if(!this.getMaxReprintCount().equals(exceptDocuTemplateInfo.maxReprintCount)){
			pass &= false;
			logger.error("The document template max reprint count error");
		}
		if(!this.getHarvestDocument().equals(exceptDocuTemplateInfo.harvestDocument)){
			pass &= false;
			logger.error("The document template harvest documet error");
		}
		if(!this.getCombinationIndicator().equalsIgnoreCase(exceptDocuTemplateInfo.combIndicator)){
			pass &=false;
			logger.error("The document template combination indicator error");
		}
		if(!this.getAddUser().replaceAll(" ", StringUtil.EMPTY).contains(exceptDocuTemplateInfo.addUser.replaceAll(" ", StringUtil.EMPTY))){
			pass &= false;
			logger.error("The document template add user error");
		}
		if(!this.getAddLocation().contains(exceptDocuTemplateInfo.addLocation)){
			pass &= false;
			logger.error("The document template add location error");
		}
		return pass;
	}
}