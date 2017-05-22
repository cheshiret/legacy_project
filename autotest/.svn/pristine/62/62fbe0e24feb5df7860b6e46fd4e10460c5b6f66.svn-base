package com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.cust.AuditAttr;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jan 21, 2014
 */
public class LicMgrAuditDetailsWidget extends DialogWidget{
	static class SingletonHolder {
		protected static LicMgrAuditDetailsWidget _instance = new LicMgrAuditDetailsWidget();
	}

	protected LicMgrAuditDetailsWidget() {
	}

	public static LicMgrAuditDetailsWidget getInstance() {
		return SingletonHolder._instance;
	}

	private static String LABEL_PROPERTYID = "Property ID";
	private static String LABEL_AUDITID = "ID";
	private static String LABEL_CREATIONAPPLICATION = "Creation Application";
	private static String LABEL_CREATIONDATE = "Creation Date";
	private static String LABEL_CREATIONUSER = "Creation User";

	/** Page Object Property Definition Begin */
	protected Property[] auditPanelTable(){
		return Property.concatPropertyArray(table(), ".id", "EditLandPropertyAuditPanel");
	}

	protected Property[] auditID(){
		return Property.toPropertyArray(".id", new RegularExpression("LandPropertyAuditView-\\d+.id", false));
	}

	protected Property[] disabledAuditStatus(){
		return Property.toPropertyArray(".id", new RegularExpression("codebaseDropdown", false));
	}

	protected Property[] auditStatus(){
		return Property.toPropertyArray(".id", new RegularExpression("LandPropertyAuditView-\\d+.status", false)); 
	}

	protected Property[] customer(){
		return Property.toPropertyArray(".id", new RegularExpression("LandPropertyAuditView-\\d+.customer", false));
	}

	protected Property[] documentsType(){
		return Property.toPropertyArray(".id", new RegularExpression("LandPropertyAuditDetailView-\\d+.docType", false));
	}

	protected Property[] documentDate(){
		return Property.toPropertyArray(".id", new RegularExpression("LandPropertyAuditDetailView-\\d+.docDate_ForDisplay", false));
	}

	protected Property[] contactDate(){
		return Property.toPropertyArray(".id", new RegularExpression("LandPropertyAuditDetailView-\\d+.contactDate_ForDisplay", false));
	}

	protected Property[] auditYear(){
		return Property.toPropertyArray(".id", new RegularExpression("LandPropertyAuditView-\\d+.auditYear", false));
	}

	protected Property[] auditComment(){
		return Property.toPropertyArray(".id", new RegularExpression("LandPropertyAuditDetailView-\\d+.comments", false));
	}

	protected Property[] createApplication(){
		return Property.toPropertyArray(".id", new RegularExpression("LandPropertyAuditView-\\d+.creationInfo.applicationName", false));
	}

	protected Property[] creationDate(){
		return Property.toPropertyArray(".id", new RegularExpression("LandPropertyAuditView-\\d+.creationInfo.date:LOCAL_TIME", false));
	}

	protected Property[] creationUser(){
		return Property.toPropertyArray(".id", new RegularExpression("LandPropertyAuditView-\\d+.creationInfo.userName", false));
	}

	protected Property[] labelSpan(String label){
		return Property.concatPropertyArray(span(), ".text", new RegularExpression("^"+label+".*", false));
	}

	protected Property[] textField(){
		return Property.concatPropertyArray(input("text"));
	}

	protected Property[] spanField(){
		return Property.concatPropertyArray(span());
	}

	protected Property[] ok(){
		return Property.concatPropertyArray(a(), ".text", "OK");
	}

	protected Property[] cancel(){
		return Property.concatPropertyArray(a(), ".text", "Cancel");
	}
	/** Page Object Property Definition END */

	public boolean exists() {
		boolean flag1 = super.exists();
		boolean flag2 = browser.checkHtmlObjectExists(auditPanelTable());
		return flag1 && flag2;
	}

	public void selectAuditStatus(String auditStatus){
		browser.selectDropdownList(auditStatus(), auditStatus);
	}

	public String getAuditStatus(){
		return browser.getDropdownListValue(auditStatus(), 0);
	}

	public List<String> getAuditStatusOptions(){
		return browser.getDropdownElements(auditStatus());
	}

	public void selectCustomer(String customer){
		browser.selectDropdownList(customer(), customer);
	}

	public void selectCustomer(int index){
		browser.selectDropdownList(customer(), index);
	}

	public String getCustomer(){
		return browser.getDropdownListValue(customer(), 0);
	}

	public void selectDocumentType(String documentType){
		browser.selectDropdownList(documentsType(), documentType);
	}

	public void selectDocumentType(int index){
		browser.selectDropdownList(documentsType(), index);
	}

	public String getDocumentType(){
		return browser.getDropdownListValue(documentsType(), 0);
	}

	public void setDocumentDate(String documentDate){
		browser.setTextField(documentDate(), documentDate, true,0, IText.Event.LOSEFOCUS);
	}

	public String getDocumentDate(){
		return browser.getTextFieldValue(documentDate());
	}

	public void setContactDate(String contactDate){
		browser.setTextField(contactDate(), contactDate, true,0, IText.Event.LOSEFOCUS);
	}

	public String getContactDate(){
		return browser.getTextFieldValue(contactDate());
	}

	public void selectAuditYear(String auditYear){
		browser.selectDropdownList(auditYear(), auditYear);
	}

	public String getAuditYear(){
		return browser.getDropdownListValue(auditYear(), 0);
	}

	public void setAuditComment(String auditComment){
		browser.setTextArea(auditComment(), auditComment);
	}

	public String getAuditComment(){
		return browser.getTextAreaValue(auditComment());
	}

	public void clickButton(Property[] p){
		IHtmlObject[] objs = browser.getHtmlObject(p);
		if(objs.length<1){
			throw new ObjectNotFoundException("Failed to find button.");
		}
		objs[objs.length-1].click();
		Browser.unregister(objs);
	}

	public void clickOK(){
		clickButton(ok());
	}

	public void clickCancel(){
		clickButton(cancel());
	}

	public IHtmlObject[] spanFieldObjs(String label){
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(auditPanelTable(), labelSpan(label)));
		if(objs.length<1){
			throw new ObjectNotFoundException("Failed to find span with label:"+label);
		}
		return objs;
	}

	public String getDisabledTextFieldValue(String label){
		IHtmlObject[] objs = browser.getHtmlObject(textField(), spanFieldObjs(label)[0]);
		if(objs.length<1){
			throw new ObjectNotFoundException("Failed to find Span objects which label is "+label);
		}
		if(objs[objs.length-1].getProperty(".isDisabled").equalsIgnoreCase("false")){
			throw new ErrorOnPageException("Text field should be disabled with label:"+label);
		}
		String value = objs[objs.length-1].getProperty(".value");
		logger.info(label+":"+value);
		Browser.unregister(objs);
		return value;
	}

	public String getAuditID(){
		return getDisabledTextFieldValue(LABEL_AUDITID);
	}

	public String getPropertyID(){
		return getDisabledTextFieldValue(LABEL_PROPERTYID);
	}

	public String getDisabledSpanFieldValue(String label){
		IHtmlObject[] objs = browser.getHtmlObject(span(), spanFieldObjs(label)[0]);
		if(objs.length<1){
			throw new ObjectNotFoundException("Failed to find Span objects which label is "+label);
		}
		String value = objs[0].text().split(label)[1];
		logger.info(label+":"+value);
		Browser.unregister(objs);
		return value;
	}

	public String getCreationApplication(){
		return getDisabledSpanFieldValue(LABEL_CREATIONAPPLICATION);
	}

	public String getCreationDate(){
		return getDisabledSpanFieldValue(LABEL_CREATIONDATE);
	}

	public String getCreationUser(){
		return getDisabledSpanFieldValue(LABEL_CREATIONUSER);
	}

	public boolean auditStatusDisabled(){
		return browser.checkHtmlObjectExists(disabledAuditStatus());
	}
	
	public String getDisabledAuditStatus(){
		if(!auditStatusDisabled()){
			throw new ErrorOnPageException("Audit staus DDL should be disabled");
		}
		String value = browser.getDropdownListValue(disabledAuditStatus(), 0);
		logger.info("Audit status:"+value);
		return value;
	}

	public String getDisabledCustomer(){
		IHtmlObject[] objs = browser.getDropdownList(customer());
		if(objs.length<1){
			throw new ObjectNotFoundException("Failed to find customer objects");
		}
		if(objs[0].getProperty(".isDisabled").equalsIgnoreCase("false")){
			throw new ErrorOnPageException("Customer DDL should be disabled");
		}
		String value = browser.getDropdownListValue(customer(), 0);
		logger.info("Customer:"+value);
		Browser.unregister(objs);
		return value;
	}

	public void setAuditData(Data<AuditAttr> audit){
		selectCustomer(audit.stringValue(AuditAttr.cust));
        if(StringUtil.notEmpty(audit.stringValue(AuditAttr.auditStatus)) && !auditStatusDisabled())
        	selectAuditStatus(audit.stringValue(AuditAttr.auditStatus));
		selectDocumentType(audit.stringValue(AuditAttr.documentType));
		if(StringUtil.notEmpty(audit.stringValue(AuditAttr.documentDate)))
			setDocumentDate(audit.stringValue(AuditAttr.documentDate));
		if(StringUtil.notEmpty(audit.stringValue(AuditAttr.contactDate)))
			setContactDate(audit.stringValue(AuditAttr.contactDate));
		if(StringUtil.notEmpty(audit.stringValue(AuditAttr.auditYear)))
			selectAuditYear(audit.stringValue(AuditAttr.auditYear));
		if(StringUtil.notEmpty(audit.stringValue(AuditAttr.auditComment)))
			setAuditComment(audit.stringValue(AuditAttr.auditComment));
	}

	public Data<AuditAttr> getAuditData(boolean duringEdit) {
		Data<AuditAttr> audit = new Data<AuditAttr>();
		audit.put(AuditAttr.auditID, getAuditID());
		if(duringEdit){
			audit.put(AuditAttr.auditStatus, getAuditStatus());
			audit.put(AuditAttr.cust, getDisabledCustomer());
			audit.put(AuditAttr.creationApplication, getCreationApplication());
			audit.put(AuditAttr.creationDate, getCreationDate());
			audit.put(AuditAttr.creationUser, getCreationUser());
		}else {
			audit.put(AuditAttr.auditStatus, getDisabledAuditStatus());
			audit.put(AuditAttr.cust, getCustomer());
		}
		audit.put(AuditAttr.propertyID, getPropertyID());
		audit.put(AuditAttr.documentType, getDocumentType());
		audit.put(AuditAttr.documentDate, getDocumentDate());
		audit.put(AuditAttr.auditYear, getAuditYear());
		audit.put(AuditAttr.auditComment, getAuditComment());
		return audit;
	}

	public void verifyAuditInfo(Data<AuditAttr> audit, boolean duringEdit){
		Data<AuditAttr> auditFromUI= getAuditData(duringEdit);

		boolean result = MiscFunctions.compareResult("Audit ID", audit.stringValue(AuditAttr.auditID), auditFromUI.stringValue(AuditAttr.auditID));
		result &= MiscFunctions.compareResult("Audit Status", audit.stringValue(AuditAttr.auditStatus), auditFromUI.stringValue(AuditAttr.auditStatus));
		result &= MiscFunctions.compareResult("Property ID", audit.stringValue(AuditAttr.propertyID), auditFromUI.stringValue(AuditAttr.propertyID));
		result &= MiscFunctions.compareResult("Customer", audit.stringValue(AuditAttr.cust), auditFromUI.stringValue(AuditAttr.cust));
		result &= MiscFunctions.compareResult("Document Type", audit.stringValue(AuditAttr.documentType), auditFromUI.stringValue(AuditAttr.documentType));
		result &= MiscFunctions.compareResult("Document Date", 0, DateFunctions.compareDates(audit.stringValue(AuditAttr.documentDate),auditFromUI.stringValue(AuditAttr.documentDate)));
		result &= MiscFunctions.compareResult("Audit Year", audit.stringValue(AuditAttr.auditYear), auditFromUI.stringValue(AuditAttr.auditYear));
		result &= MiscFunctions.compareResult("Audit Comment", audit.stringValue(AuditAttr.auditComment), auditFromUI.stringValue(AuditAttr.auditComment));

		if(duringEdit){
			result &= MiscFunctions.compareResult("Creation Application", audit.stringValue(AuditAttr.creationApplication), auditFromUI.stringValue(AuditAttr.creationApplication));
			result &= MiscFunctions.compareResult("Creation Date", 0, DateFunctions.compareDates(audit.stringValue(AuditAttr.creationDate),auditFromUI.stringValue(AuditAttr.creationDate)));
			result &= MiscFunctions.compareResult("Creation User", audit.stringValue(AuditAttr.creationUser), auditFromUI.stringValue(AuditAttr.creationUser));
		}

		if(!result){
			throw new ErrorOnPageException("Not all check points are passed in Audit details page duting "+(duringEdit?"Edit ":"Add ")+"Audit.");
		}
		logger.info("Successfully verify all check points in Audit details page duting "+(duringEdit?"Edit ":"Add ")+"Audit.");
	}

	public void verifyAuditInfo(Data<AuditAttr> audit){
		verifyAuditInfo(audit, true);
	}
}
