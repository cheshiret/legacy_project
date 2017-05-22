package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuantityControlInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrPrivilegeQuantityControlCommonWidget;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrPrivilegeEditQuantityControlWidget extends LicMgrPrivilegeQuantityControlCommonWidget {

	private static LicMgrPrivilegeEditQuantityControlWidget _instance = null;
	
	protected LicMgrPrivilegeEditQuantityControlWidget() {
		super("Edit Product Quantity Controls Popup");
	}
	
	public static LicMgrPrivilegeEditQuantityControlWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrPrivilegeEditQuantityControlWidget();
		}
		return _instance;
	}
	
	public boolean exists() {
		return super.exists()
		&& browser.checkHtmlObjectExists(".class", "Html.SPAN",
				".text", "Edit Product Quantity Controls Popup");
	}
	
	public void selectStatus(String status){
		browser.selectDropdownList(".id", 
				new RegularExpression("^ProductQuantityControlView-\\d+\\.active",false), status);
	}
	
	public void selectMultiSalesAllowance(String multiSalesAllowance){
		browser.selectDropdownList(".id", 
				new RegularExpression("^ProductQuantityControlView-\\d+\\.multiSalesAllowance",false), multiSalesAllowance);
		ajax.waitLoading();
	}
	
	public void setQuantityControlInfo(QuantityControlInfo quantityControl){
		this.selectStatus(quantityControl.status);		
		this.selectMultiSalesAllowance(quantityControl.multiSalesAllowance);
		if(this.checkMaxQuantityPerTranIsExists()){
			this.setMaxQuantityPerTransaction(quantityControl.maxQuantityPerTran);
		}
		if(this.checkMaxAllowedIsExists()){
			this.setMaxAllowed(quantityControl.maxAllowed);
		}
		if(this.checkMinQuantityPerTranIsExists()) {
			this.setMinQuantityPerTransaction(quantityControl.minQuantityPerTran);
		}
		this.setReplacementMaxAllowed(quantityControl.replacementMaxAllowed);
	}
	
	public List<String> getMutiSalesAllowedElements(){
		return browser.getDropdownElements(".id", 
				new RegularExpression("^ProductQuantityControlView-\\d+\\.multiSalesAllowance",false));
	}
	
	public String getStatus(){
		return browser.getDropdownListValue(".id", 
				new RegularExpression("^ProductQuantityControlView-\\d+\\.active",false), 0).trim();
	}
	
	public String getLocationClass(){
		return browser.getDropdownListValue(".id", 
				new RegularExpression("^ProductQuantityControlView-\\d+\\.locationClassID",false), 0).trim();
	}
	
	public String getMultiSalesAllowance(){
		return browser.getDropdownListValue(".id", 
				new RegularExpression("^ProductQuantityControlView-\\d+\\.multiSalesAllowance",false), 0).trim();
	}
	
	public String getMaxQuantityPerTran(){
		return browser.getTextFieldValue(".id", 
				new RegularExpression("^ProductQuantityControlView-\\d+\\.maxQtyPerTrans",false)).trim();
	}
	
	public String getMaxAllowed(){
		return browser.getTextFieldValue(".id", 
				new RegularExpression("^ProductQuantityControlView-\\d+\\.maxAllowed",false)).trim();
	}
	
	public String getReplacementMaxAllowed(){
		return browser.getTextFieldValue(".id", 
				new RegularExpression("^ProductQuantityControlView-\\d+\\.maxReplacementAllowed",false)).trim();
	}
	
	public boolean isReplacementMaxAllowedExist(){
		return browser.checkHtmlObjectExists(".id", 
				new RegularExpression("^ProductQuantityControlView-\\d+\\.maxReplacementAllowed",false));
	}
	
	public String getCreateUser(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", 
				new RegularExpression("^ProductQuantityControlView-\\d+\\.createUser",false));
		
		Property[] p = new Property[1];
		p[0] = new Property(".class", "Html.SPAN");
		IHtmlObject[] divObjs = browser.getHtmlObject(p, objs[0]);
		
		String value = divObjs[0].text();
		
		Browser.unregister(divObjs);
		Browser.unregister(objs);
		
		return value.replaceAll("Create User", StringUtil.EMPTY);
	}
	
	public String getCreateLocation(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", 
				new RegularExpression("^ProductQuantityControlView-\\d+\\.createLocation",false));
		
		Property[] p = new Property[1];
		p[0] = new Property(".class", "Html.SPAN");
		IHtmlObject[] divObjs = browser.getHtmlObject(p, objs[0]);
		
		String value = divObjs[0].text();
		
		Browser.unregister(divObjs);
		Browser.unregister(objs);
		
		return value.replaceAll("Create Location", StringUtil.EMPTY);
	}
	
	public String getCreateTime(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", 
				new RegularExpression("^ProductQuantityControlView-\\d+\\.createTime.*",false));
		
		Property[] p = new Property[1];
		p[0] = new Property(".class", "Html.SPAN");
		IHtmlObject[] divObjs = browser.getHtmlObject(p, objs[0]);
		
		String value = divObjs[0].text();
		
		Browser.unregister(divObjs);
		Browser.unregister(objs);
		
		return value;
	}
	
	public String getLastUpdateUser(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", 
				new RegularExpression("^ProductQuantityControlView-\\d+\\.lastUpdateUser",false));
		
		Property[] p = new Property[1];
		p[0] = new Property(".class", "Html.SPAN");
		IHtmlObject[] divObjs = browser.getHtmlObject(p, objs[0]);
		
		String value = divObjs[0].text();
		
		Browser.unregister(divObjs);
		Browser.unregister(objs);
		
		return value.replaceAll("Last Update User", StringUtil.EMPTY);
	}
	
	public String getLastUpdateLocation(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", 
				new RegularExpression("^ProductQuantityControlView-\\d+\\.lastUpdateLocation",false));
		
		Property[] p = new Property[1];
		p[0] = new Property(".class", "Html.SPAN");
		IHtmlObject[] divObjs = browser.getHtmlObject(p, objs[0]);
		
		String value = divObjs[0].text();
		
		Browser.unregister(divObjs);
		Browser.unregister(objs);
		
		return value.replaceAll("Last Update Location", StringUtil.EMPTY);
	}
	
	public String getLastUpdateTime(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", 
				new RegularExpression("^ProductQuantityControlView-\\d+\\.lastUpdateTime.*",false));
		
		Property[] p = new Property[1];
		p[0] = new Property(".class", "Html.SPAN");
		IHtmlObject[] divObjs = browser.getHtmlObject(p, objs[0]);
		
		String value = divObjs[0].text();
		
		Browser.unregister(divObjs);
		Browser.unregister(objs);
		
		return value;
	}
	
	public boolean checkLocationClassIsDisabled(){
		boolean isEditable;
		IHtmlObject[] objs = browser.getHtmlObject(".id", 
				new RegularExpression("^ProductQuantityControlView-\\d+\\.locationClassID",false));
		
		String property = objs[0].getProperty("isDisabled");
		if(property.equals("false")){
			isEditable = false;
		}else {
			isEditable = true;
		}
		
		Browser.unregister(objs);
		return isEditable;
	}
	
	public boolean checkQuantityControlIDIsDiabled(){
		boolean isEditable;
		IHtmlObject[] objs = browser.getHtmlObject(".id", 
				new RegularExpression("^ProductQuantityControlView-\\d+\\.id",false));
		
		String property = objs[0].getProperty("isDisabled");
		if(property.equals("false")){
			isEditable = false;
		}else {
			isEditable = true;
		}
		
		Browser.unregister(objs);
		return isEditable;
	}
	
	public boolean compareQuantityControlInfo(QuantityControlInfo quantityControl){
		boolean result = true;
		String temp = this.getStatus();
		if(!quantityControl.status.equals(temp)){
			result &= false;
			logger.error("Expect status should be " + quantityControl.status 
					+ ", but actually is " + this.getStatus());
		}
		temp = this.getLocationClass();
		if(!quantityControl.locationClass.equals(temp)){
			result &= false;
			logger.error("Expect location class should be " + quantityControl.locationClass 
					+ ", but actually is " + temp);
		}
		if(quantityControl.maxQuantityPerTran != null &&  quantityControl.maxQuantityPerTran.length()>0){
			if(!this.checkMaxQuantityPerTranIsExists()){
				result &= false;
				logger.error("Max Quantity Per Transaction should display in UI.");
			}else {
				temp = this.getMaxQuantityPerTran();
				if(!quantityControl.maxQuantityPerTran.equals(temp)){
					result &= false;
					logger.error("Expect max quantity per transaction should be " + quantityControl.maxQuantityPerTran 
							+ ", but actually is " + temp);
				}
			}
		}
		if(quantityControl.maxAllowed != null && quantityControl.maxAllowed.length()>0){
			if(!this.checkMaxAllowedIsExists()){
				result &= false;
				logger.error("Max Allowed should display in UI.");
			}else {
				temp = this.getMaxAllowed();
				if(!quantityControl.maxAllowed.equals(temp)){
					result &= false;
					logger.error("Expect max allowed should be " + quantityControl.maxAllowed 
							+ ", but acutally is " + temp);
				}
			}
		}
		temp = this.getReplacementMaxAllowed();
		if(!quantityControl.replacementMaxAllowed.equals(temp)){
			result &= false;
			logger.error("Expected replacement max allowed should be " + quantityControl.replacementMaxAllowed 
					+ ", but acutally is " + temp);
		}
		temp = this.getCreateUser();
		if(!quantityControl.createUser.replace(", ", ",").equals(temp.replace(", ", ","))){
			result &= false;
			logger.error("Expected create user should be " + quantityControl.createUser 
					+ ", but actually is " + temp);
		}
		temp = this.getCreateLocation();
		if(!quantityControl.createLocation.equals(temp)){
			result &= false;
			logger.error("Expect create location should be " + quantityControl.createLocation 
					+ ", but actullay is " + temp);
		}
		temp = this.getCreateTime();
		if(!temp.contains(DateFunctions.formatDate(quantityControl.createTime, "E MMM dd yyyy"))){
			result &= false;
			logger.error("Expect create time should be " + quantityControl.createTime 
					+ ", but acutally is " + temp);
		}
		if(quantityControl.status.equals(OrmsConstants.INACTIVE_STATUS)) {
			//if the quantity control record is Inactive, the last update information will be set value; if not, there is null.
			temp = this.getLastUpdateUser();
			if(!quantityControl.lastUpdateUser.replace(", ", ",").equals(temp.replace(", ", ","))){
				result &= false;
				logger.error("Expect last update user should be " + quantityControl.lastUpdateUser 
						+ ", but actually is " + temp);
			}
			temp = this.getLastUpdateLocation();
			if(!quantityControl.lastUpdateLocation.equals(temp)){
				result &= false;
				logger.error("Expect last update location should be " + quantityControl.lastUpdateLocation 
						+ ", but actullay is " + temp);
			}
			
			if(quantityControl.lastUpdateTime.length()>0){
				quantityControl.lastUpdateTime = DateFunctions.formatDate(quantityControl.lastUpdateTime, "MMM dd yyyy");
			}
			temp = this.getLastUpdateTime();
			if(!temp.contains(quantityControl.lastUpdateTime)){
				result &= false;
				logger.error("Expect last update time should be " + quantityControl.lastUpdateTime 
						+ ", but acutally is " + temp);
			}
		}
						
		return result;
	}
}
