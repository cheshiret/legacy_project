package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntParameterInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * @Description: Edit Hunt Parameter
 * @author Lesley Wang
 * @Date  Aug 7, 2013
 */
public class LicMgrEditHuntParameterWidget extends LicMgrHuntParameterCommonWidget {
	private static LicMgrEditHuntParameterWidget _instance = null;
	
	protected LicMgrEditHuntParameterWidget() {
		super("Edit Hunt Parameter");
	}
	
	public static LicMgrEditHuntParameterWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrEditHuntParameterWidget();
		}
		
		return _instance;
	}
	
	protected Property[] newIdTextField() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", new RegularExpression("DisplayParameterView-\\d+\\.id:ZERO_TO_NEW", false));
	}
	
	protected Property[] paramStatusList() {
		return Property.toPropertyArray(".class", "Html.Select", ".id", new RegularExpression("DisplayParameterView-\\d+\\.status", false));
	}
	
	protected Property[] creationUserSpanProp() {
		return Property.toPropertyArray(".id", new RegularExpression("DisplayParameterView-\\d+\\.userCreator.fullName", false));
	}
	
	protected Property[] creationLicationSpanProp() {
		return Property.toPropertyArray(".id", new RegularExpression("DisplayParameterView-\\d+\\.locationCreated.name", false));
	}
	
	protected Property[] creationDateTimeSpanProp() {
		return Property.toPropertyArray(".id", new RegularExpression("DisplayParameterView-\\d+\\.createDate:LOCAL_TIME", false));
	}
	
	protected Property[] lastUpdatedUserSpanProp(){
		return Property.toPropertyArray(".id", new RegularExpression("DisplayParameterView-\\d+\\.userLastUpdated.fullName", false));
	}
	
	protected Property[] lastUpdatedLocationSpanProp(){
		return Property.toPropertyArray(".id", new RegularExpression("DisplayParameterView-\\d+\\.locationLastUpdated.name", false));
	}
	
	protected Property[] lastUpdatedDateTimeSpanProp(){
		return Property.toPropertyArray(".id", new RegularExpression("DisplayParameterView-\\d+\\.lastUpdated:LOCAL_TIME", false));
	}
	
	public void selectParamStatus(String status) {
		browser.selectDropdownList(this.paramStatusList(), status, true);
	}
	
	public String getId(){
		return browser.getTextFieldValue(this.newIdTextField());
	}
	
	public String getStatus(){
		return browser.getDropdownListValue(this.paramStatusList(), 0);
	}
	
	
	public String getSpanContent(Property[] properties){
		IHtmlObject[] objs = browser.getHtmlObject(properties);
		if(objs.length < 1){
			throw new ItemNotFoundException("Did not get the span object with properties: " +  properties.toString() );
		}
		String text = objs[0].text();
		Browser.unregister(objs);
		return text;
	}
	
	public String getParameterDes(){
		return browser.getTextFieldValue(this.paramDesTextField());
	}
	
	public String getParameterValue(){
		return browser.getTextAreaValue(this.paramValueTextArea());
	}
	
	public boolean isPrintParameter(){
		boolean isPrint = false;
		if(browser.isRadioButtonSelected(Property.addToPropertyArray(this.printParamRadio(), ".value", "true"))){
			isPrint = true;
		}else{
			isPrint = false;
		}
		return isPrint;
	}
	
	public String getCreateUser(){
		String text = this.getSpanContent(this.creationUserSpanProp());
		String value = text.replace("Creation User", "");
		return value;
	}
	
	public String getCreateLocation(){
		String text = this.getSpanContent(this.creationLicationSpanProp());
		String value = text.replace("Creation Location", "");
		return value;
	}
	
	public String getCreateDateTime(){
		String text = this.getSpanContent(this.creationDateTimeSpanProp());
		String value = text.replace("Creation Date/Time", "");
		return value;
	}
	
	public String getLastUpdatedUser(){
		String text = this.getSpanContent(this.lastUpdatedUserSpanProp());
		String value = text.replace("Last Updated User", "");
		return value;
	}
	
	public String getLastUpdatedLocation(){
		String text = this.getSpanContent(this.lastUpdatedLocationSpanProp());
		String value = text.replace("Last Updated Location", "");
		return value;
	}
	
	public String getLastUpdatedDateTime(){
		String text = this.getSpanContent(this.lastUpdatedDateTimeSpanProp());
		String value = text.replace("Last Updated Date/Time", "");
		return value;
	}
	
	public HuntParameterInfo getHuntParameterInfo(){
		HuntParameterInfo parameters = new HuntParameterInfo();
		parameters.setHuntParamStatus(this.getStatus());
		parameters.setHuntParamID(this.getId());
		parameters.setHuntParamDes(this.getParameterDes());
		parameters.setHuntParamValue(this.getParameterValue());
		parameters.setPrintHuntParam(this.isPrintParameter());
		parameters.createUser = this.getCreateUser();
		parameters.createLocation = this.getCreateLocation();
		parameters.createTime = this.getCreateDateTime();
		parameters.lastUpdatedUser = this.getLastUpdatedUser();
		parameters.lastUpdatedLocation = this.getLastUpdatedLocation();
		parameters.lastUpdatedTime = this.getLastUpdatedDateTime();
		return parameters;
	}
	
	public void verifyHuntParameterDetailInfo(HuntParameterInfo expParameters, int desAndValueIndex){
		HuntParameterInfo actParameterInfo = this.getHuntParameterInfo();
		boolean passed = true;
		passed &= MiscFunctions.compareResult("hunt parameter status:", expParameters.getHuntParamStatus(),	actParameterInfo.getHuntParamStatus());
		if(expParameters.getDescriptAndValue().size() < 1){
			passed &= MiscFunctions.compareResult("hunt parameter description:", expParameters.getHuntParamDes(),	actParameterInfo.getHuntParamDes());
			passed &= MiscFunctions.compareResult("hunt parameter value:", expParameters.getHuntParamValue(),	actParameterInfo.getHuntParamValue());
			passed &= MiscFunctions.compareResult("hunt parameter is print:", expParameters.isPrintHuntParam(),	actParameterInfo.isPrintHuntParam());
		}else{
			passed &= MiscFunctions.compareResult("hunt parameter description:", expParameters.getDescriptAndValue().get(desAndValueIndex).getDescription(),	actParameterInfo.getHuntParamDes());
			passed &= MiscFunctions.compareResult("hunt parameter value:", expParameters.getDescriptAndValue().get(desAndValueIndex).getValue(), actParameterInfo.getHuntParamValue());
			passed &= MiscFunctions.compareResult("hunt parameter value:", expParameters.getDescriptAndValue().get(desAndValueIndex).getIsPrint(), actParameterInfo.isPrintHuntParam());
		}
		passed &= MiscFunctions.compareResult("hunt parameter creation user:", expParameters.createUser,	actParameterInfo.createUser.replaceAll(" ", ""));
		passed &= MiscFunctions.compareResult("hunt parameter creation location:", expParameters.createLocation,	actParameterInfo.createLocation);
		passed &= MiscFunctions.compareResult("hunt parameter creation date/time:", expParameters.createTime, 
				actParameterInfo.createTime.split(String.valueOf(DateFunctions.getCurrentYear()))[0] + String.valueOf(DateFunctions.getCurrentYear()));
		
		passed &= MiscFunctions.compareResult("hunt parameter last updated user:", expParameters.lastUpdatedUser,	actParameterInfo.lastUpdatedUser);
		passed &= MiscFunctions.compareResult("hunt parameter last updated  location:", expParameters.lastUpdatedLocation,	actParameterInfo.lastUpdatedLocation);
		passed &= MiscFunctions.compareResult("hunt parameter last updated  date/time:", expParameters.lastUpdatedTime,	(StringUtil.notEmpty(actParameterInfo.lastUpdatedTime)?
				actParameterInfo.lastUpdatedTime.split(String.valueOf(DateFunctions.getCurrentYear()))[0] + String.valueOf(DateFunctions.getCurrentYear()):StringUtil.EMPTY));
		
		if(!passed){
			throw new ErrorOnPageException("Hunt parameter information may not correct, please check the log above!");
		}
		logger.info("The information for hunt parameter is correct!");
	}
	
	public void editParameterInfo(HuntParameterInfo parameter){
		this.selectParamStatus(parameter.getHuntParamStatus());
		this.setParameterInfo(parameter.getHuntParamDes(),  parameter.getHuntParamValue(), parameter.isPrintHuntParam(), 0);
	}

	public void editParameterInfoAndClickOk(HuntParameterInfo parameter){
		this.editParameterInfo(parameter);
		this.clickOK();
	}
	
	public boolean isIdTextFieldEditable(){
		return browser.checkHtmlObjectEnabled(this.newIdTextField());
	}
	
	public boolean isParameterDesTextFieldEditable(){
		return browser.checkHtmlObjectEnabled(this.paramDesTextField());
	}
	
	public boolean isParameterValueTextFieldEditable(){
		return browser.checkHtmlObjectEnabled(this.paramValueTextArea());
	}
	
	public boolean isStatusEditable(){
		return browser.checkHtmlObjectEnabled(this.paramStatusList());
	}
	
	public boolean isPrintIndicatorEditable(){
		return browser.checkHtmlObjectEnabled(this.printParamRadio());
	}
}
