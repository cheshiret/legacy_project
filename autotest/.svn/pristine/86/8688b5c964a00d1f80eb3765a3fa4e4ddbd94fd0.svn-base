/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo.QuotaTransfer;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author ssong
 * @Date  Aug 5, 2014
 */
public class LicMgrQuotaTransferWidget extends DialogWidget {
	private static LicMgrQuotaTransferWidget _instance = null;
	
	private LicMgrQuotaTransferWidget() {}
	
	public static LicMgrQuotaTransferWidget getInstance() {
		if(_instance == null) {
			_instance = new LicMgrQuotaTransferWidget();
		}
		
		return _instance;
	}
	
	
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(description());
	}
	
	protected Property[] id(){
		return Property.addToPropertyArray(input("text"), ".id", new RegularExpression("HuntQuotaTransferView-\\d+\\.id:ZERO_TO_NEW", false));
	}
	
	protected Property[] description(){
		return Property.addToPropertyArray(input("text"), ".id", new RegularExpression("HuntQuotaTransferView-\\d+\\.description",false));
	}
	
	protected Property[] statusDropdown(){
		return Property.addToPropertyArray(select(), ".id", new RegularExpression("HuntQuotaTransferView-\\d+\\.status",false));
	}
	
	protected Property[] quotaFromDropdown(){
		return Property.addToPropertyArray(select(), ".id", new RegularExpression("HuntQuotaTransferView-\\d+\\.quotaFrom",false));
	}
	
	protected Property[] quotaTypeFromDropdown(){
		return Property.addToPropertyArray(select(), ".id", new RegularExpression("HuntQuotaTransferView-\\d+\\.quotaTypeFrom",false));
	}
	
	protected Property[] quotaToDropdown(){
		return Property.addToPropertyArray(select(), ".id", new RegularExpression("HuntQuotaTransferView-\\d+\\.quotaTo",false));
	}
	
	protected Property[] quotaTypeToDropdown(){
		return Property.addToPropertyArray(select(), ".id", new RegularExpression("HuntQuotaTransferView-\\d+\\.quotaTypeTo",false));
	}
	
	protected Property[] quotaTransferDetailTable(){
		return Property.addToPropertyArray(table(), ".id", "HuntQuotaTransferDetailUI");
	}
	
	public IHtmlTable getQuotaTransferDetailTable(){
		IHtmlObject[] objs = browser.getHtmlObject(quotaTransferDetailTable());
		IHtmlTable table = (IHtmlTable)objs[0];
		return table;
	}
	
	public String getId(){
		return browser.getTextFieldValue(id());
	}
	
	public void setDescription(String desc){
		browser.setTextField(description(), desc);
	}
	
	public String getDescription(){
		return browser.getTextFieldValue(description());
	}
	
	public void selectStatus(String status){
		browser.selectDropdownList(statusDropdown(), status);
	}
	
	public String getStatus(){
		return browser.getDropdownListValue(statusDropdown(),0);
	}
	
	public void selectQuotaFrom(String quotaFrom){
		browser.selectDropdownList(quotaFromDropdown(), quotaFrom);
	}
	
	public String getQuotaFrom(){
		return browser.getDropdownListValue(quotaFromDropdown(), 0);
	}
	
	public void selectQuotaTypeFrom(String quotaTypeFrom){
		browser.selectDropdownList(quotaTypeFromDropdown(), quotaTypeFrom);
	}

	public String getQuotaTypeFrom(){
		return browser.getDropdownListValue(quotaTypeFromDropdown(), 0);
	}
	
	public void selectQuotaTo(String quotaTo){
		browser.selectDropdownList(quotaToDropdown(), quotaTo);
	}

	public String getQuotaTo(){
		return browser.getDropdownListValue(quotaToDropdown(), 0);
	}
	
	public void selectQuotaTypeTo(String quotaTypeTo){
		browser.selectDropdownList(quotaTypeToDropdown(), quotaTypeTo);
	}

	public String getQuotaTypeTo(){
		return browser.getDropdownListValue(quotaTypeToDropdown(), 0);
	}
	
	public String getCreationUser(){
		return this.getAttributeValue("Creation User");
	}
	
	public String getCreationLocation(){
		return this.getAttributeValue("Creation Location");
	}
	
	public String getCreateDateTime(){
		return this.getAttributeValue("Creation Date/Time");
	}
	
	public String getLastUpdatedUser(){
		return this.getAttributeValue("Last Updated User");
	}
	
	public String getLastUpatedLocation(){
		return this.getAttributeValue("Last Updated Location");
	}
	
	public String getLastUpdatedDateTime(){
		return this.getAttributeValue("Last Updated Date/Time");
	}
	
	protected String getAttributeValue(String attrName){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^"+attrName + ".*", false), getQuotaTransferDetailTable());
		if(objs.length < 1){
			throw new ErrorOnPageException("Can not find span object for attribute:" + attrName);
		}
		String content = objs[0].text().replace(attrName, "");
		Browser.unregister(objs);
		return content;
	}
	
	public void setupQuotaTransfer(QuotaTransfer qt){
		setDescription(qt.getDescription());
		selectQuotaFrom(qt.getQuotaFrom());
		ajax.waitLoading();
		selectQuotaTypeFrom(qt.getQuotaTypeFrom());
		selectQuotaTo(qt.getQuotaTo());
		ajax.waitLoading();
		selectQuotaTypeTo(qt.getQuotaTypeTo());
		clickOKAndWait();
	}
	
	public QuotaTransfer getQuotaTransfer(){
		QuotaTransfer qt = new QuotaTransfer();
		
		qt.setId(getId());
		qt.setStatus(getStatus());
		qt.setDescription(getDescription());
		qt.setQuotaFrom(getDescription());
		qt.setQuotaTypeFrom(getQuotaTypeFrom());
		qt.setQuotaTo(getQuotaTo());
		qt.setQuotaTypeTo(getQuotaTypeTo());
		qt.setCreateUser(getCreationUser());
		qt.setCreateLocation(getCreationLocation());
		qt.setCreateDate(getCreateDateTime());
		qt.setLastUpdateUser(getLastUpdatedUser());
		qt.setLastUpdateLocation(getLastUpatedLocation());
		qt.setLastUpdateDate(getLastUpdatedDateTime());
		
		return qt;
	}
	
	public boolean compareQuotaTransferDetail(QuotaTransfer expQt){
		QuotaTransfer actQt = getQuotaTransfer();
		
		boolean result = true;
		result &= MiscFunctions.compareResult("Quota Transfer ID", expQt.getId(), actQt.getId());
		result &= MiscFunctions.compareResult("Quota Transfer Status", expQt.getStatus(), actQt.getStatus());
		result &= MiscFunctions.compareResult("Quota Transfer Description", expQt.getDescription(), actQt.getDescription());
		result &= MiscFunctions.compareResult("Quota Transfer From", expQt.getQuotaFrom(), actQt.getQuotaFrom());
		result &= MiscFunctions.compareResult("Quota Transfer Type From", expQt.getQuotaTypeFrom(), actQt.getQuotaTypeFrom());
		result &= MiscFunctions.compareResult("Quota Transfer To", expQt.getQuotaTo(), actQt.getQuotaTo());
		result &= MiscFunctions.compareResult("Quota Transfer Type To", expQt.getQuotaTypeTo(), actQt.getQuotaTypeTo());
		if(StringUtil.notEmpty(expQt.getCreateUser())){
			result &= MiscFunctions.compareResult("Quota Transfer Creation User", expQt.getCreateUser(), actQt.getCreateUser());
		}
		if(StringUtil.notEmpty(expQt.getCreateLocation())){
			result &= MiscFunctions.compareResult("Quota Transfer Creation Location", expQt.getCreateLocation(), actQt.getCreateLocation());
		}
		if(StringUtil.notEmpty(expQt.getCreateDate())){
			String actulDate = actQt.getCreateDate().split(String.valueOf(DateFunctions.getCurrentYear()))[0] + String.valueOf(DateFunctions.getCurrentYear());
			result &= DateFunctions.compareDates(actulDate, expQt.getCreateDate())==0;
		}
		if(StringUtil.notEmpty(expQt.getLastUpdateUser())){
			result &= MiscFunctions.compareResult("Quota Transfer Last Update User", expQt.getLastUpdateUser(), actQt.getLastUpdateUser());
		}
		if(StringUtil.notEmpty(expQt.getLastUpdateLocation())){
			result &= MiscFunctions.compareResult("Quota Transfer Last Update Location", expQt.getLastUpdateLocation(), actQt.getLastUpdateLocation());
		}
		if(StringUtil.notEmpty(expQt.getLastUpdateDate())){
			String actulDate = actQt.getLastUpdateDate().split(String.valueOf(DateFunctions.getCurrentYear()))[0] + String.valueOf(DateFunctions.getCurrentYear());
			result &= DateFunctions.compareDates(actulDate, expQt.getLastUpdateDate())==0;
		}
		
		return result;
	}
}
