package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LotteryExecutionConfigInfo;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
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
 * @author qchen
 * @Date  Oct 11, 2013
 */
public class LicMgrLotteryExecutionConfigDetailsPage extends LicMgrLotteriesCommonPage {
	
	private static LicMgrLotteryExecutionConfigDetailsPage _instance = null;
	
	private LicMgrLotteryExecutionConfigDetailsPage() {}
	
	public static LicMgrLotteryExecutionConfigDetailsPage getInstance() {
		if(_instance == null) _instance = new LicMgrLotteryExecutionConfigDetailsPage();
		return _instance;
	}
	
	private Property[] id() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryExecConfigView-\\d+\\.id:ZERO_TO_NEW", false));
	}
	
	private Property[] status() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryExecConfigView-\\d+\\.status", false));
	}
	
	private Property[] description() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryExecConfigView-\\d+\\.description", false));
	}
	
	private Property[] algorithm() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryExecConfigView-\\d+\\.algorithmCfg.algorithm:CB_TO_NAME", false));
	}
	
	private Property[] lotteryType() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLottery.*ExecConfigView-\\d+\\.lotteryType", false));
	}
	
	private Property[] drawType() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLottery.*ExecConfigView-\\d+\\.lotteryDrawType", false));
	}
	
	private Property[] randomNumberRangeFrom() {//
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLottery.*ExecConfigView-\\d+\\.randomRangeFrom", false));
	}
	
	private Property[] randomNumberRangeTo() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLottery.*ExecConfigView-\\d+\\.randomRangeTo", false));
	}
	
	private Property[] useSystemSeed() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLottery.*ExecConfigView-\\d+\\.seedNumberInd", false));
	}
	
	private Property[] initialNumber() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLottery.*ExecConfigView-\\d+\\.initialNumberInd", false));
	}
	
	private Property[] awardMethod() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLottery.*ExecConfigView-\\d+\\.lotteryAwardMethod", false));
	}
	
	private Property[] emailNotification() {
		return Property.toPropertyArray(".class", "Html.INPUT.checkbox", ".id", new RegularExpression("PrivilegeLottery.*ExecConfigView-\\d+\\.awardNotifications_\\d+", false));
	}
	
	private Property[] emailNotification(String id) {
		return Property.toPropertyArray(".id", id);
	}
	
	private Property[] emailNotificationLabelByFor(String forValue) {
		return Property.toPropertyArray(".class", "Html.LABEL", ".for", new RegularExpression("PrivilegeLottery.*ExecConfigView-\\d+\\.awardNotifications_\\d+", false), ".className", "trailing");
	}
	
	private Property[] emailNotificationLabel(String label) {
		return Property.toPropertyArray(".class", "Html.LABEL", ".text", label, ".className", "trailing");
	}
	
	private Property[] supportGroup() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLottery.*ExecConfigView-\\d+\\.groupSupported", false));
	}
	
	private Property[] groupPointsUsage() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLottery.*ExecConfigView-\\d+\\.groupPointsUsage", false));
	}
	
	private Property[] groupQuotaUsage() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLottery.*ExecConfigView-\\d+\\.groupQuotaUsage", false));
	}
	
	private Property[] groupQuotaIntegrity() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLottery.*ExecConfigView-\\d+\\.groupQuotaIntegrity", false));
	}
	
	private Property[] maximumExcessPercentage() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLottery.*ExecConfigView-\\d+\\.maxExcessPercent", false));
	}
	
	private Property[] successfulRangeFrom() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryInstantExecConfigView-\\d+\\.successRangeFrom", false));
	}
	
	private Property[] successfulRangeTo() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryInstantExecConfigView-\\d+\\.successRangeTo", false));
	}
	
	private Property[] winingPercentage() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryInstantExecConfigView-\\d+\\.winningPercent", false));
	}
	
	private Property[] ok() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "OK");
	}
	
	private Property[] cancel() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Cancel");
	}
	
	private Property[] apply() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Apply");
	}
	
	private Property[] errorMsg() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "message msgerror");
	}
	
	public String getID() {
		return browser.getTextFieldValue(id());
	}
	
	public void selectStatus(String status) {
		browser.selectDropdownList(status(), status);
	}
	
	public String getStatus() {
		return browser.getDropdownListValue(status(), 0);
	}
	
	public void setDescription(String desc) {
		browser.setTextField(description(), desc);
	}
	
	public String getDescription() {
		return browser.getTextFieldValue(description());
	}
	
	public String getAlgorithm() {
		return browser.getDropdownListValue(algorithm(), 0);
	}
	
	public void selectLotteryType(String type) {
		browser.selectDropdownList(lotteryType(), type);
	}
	
	public String getLotteryType() {
		return browser.getDropdownListValue(lotteryType(), 0);
	}
	
	public void selectDrawType(String type) {
		browser.selectDropdownList(drawType(), type);
	}
	
	public String getDrawType() {
		return browser.getDropdownListValue(drawType(), 0);
	}
	
	public void setRandomNumberRangeFrom(String numFrom) {
		browser.setTextField(randomNumberRangeFrom(), numFrom);
	}
	
	public String getRandomNumberRangeFrom() {
		return browser.getTextFieldValue(randomNumberRangeFrom());
	}
	
	public void setRandomNumberRangeTo(String numTo) {
		browser.setTextField(randomNumberRangeTo(), numTo);
	}
	
	public String getRandomNumberRangeTo() {
		return browser.getTextFieldValue(randomNumberRangeTo());
	}
	
	public boolean isUseSystemSeedExists() {
		return browser.checkHtmlObjectExists(useSystemSeed());
	}
	
	public void selectUseSystemSeed(boolean yes) {
		browser.selectDropdownList(useSystemSeed(), yes ? OrmsConstants.YES_STATUS : OrmsConstants.NO_STATUS);
	}
	
	public boolean getUseSystemSeed() {
		return browser.getDropdownListValue(useSystemSeed(), 0).equalsIgnoreCase(OrmsConstants.YES_STATUS) ? true : false;
	}
	
	public boolean isInitialNumberExists() {
		return browser.checkHtmlObjectExists(initialNumber());
	}
	
	public void selectInitialNumber(boolean yes) {
		browser.selectDropdownList(initialNumber(), yes ? OrmsConstants.YES_STATUS : OrmsConstants.NO_STATUS);
	}
	
	public boolean getInitialNumber() {
		return browser.getDropdownListValue(initialNumber(), 0).equalsIgnoreCase(OrmsConstants.YES_STATUS) ? true : false;
	}
	
	public void selectAwardMethod(String method) {
		browser.selectDropdownList(awardMethod(), method);
	}
	
	public String getAwardMethod() {
		return browser.getDropdownListValue(awardMethod(), 0);
	}
	
	public void selectEmailNotifications(String notifications[]) {
		IHtmlObject labelObjs[] = null;
		for(String notification : notifications) {
			labelObjs = browser.getHtmlObject(emailNotificationLabel(notification));
			if(labelObjs.length < 1) throw new ItemNotFoundException("Cannot find Email Notification label - " + notification);
			String checkboxId = labelObjs[0].getProperty(".for");
			browser.selectCheckBox(emailNotification(checkboxId));
		}
		
		Browser.unregister(labelObjs);
	}
	
	public String[] getEmailNotifications() {
		IHtmlObject checkboxObjs[] = browser.getCheckBox(emailNotification());
		if(checkboxObjs.length < 1) throw new ItemNotFoundException("Cannot find any Email Notification checkbox object.");
		
		String id, label;
		List<String> labels = new ArrayList<String>();
		for(int i = 0; i < checkboxObjs.length; i ++) {
			if(((ICheckBox)checkboxObjs[i]).isSelected()) {
				id = checkboxObjs[i].getProperty(".id");
				label = browser.getObjectText(emailNotificationLabelByFor(id));
				labels.add(label);
			}
		}
		
		return labels.toArray(new String[0]);
	}
	
	public void selectSupportGroup() {
		browser.selectCheckBox(supportGroup());
	}
	
	public void unselectSupportGroup() {
		browser.unSelectCheckBox(supportGroup(), 0);
	}
	
	public boolean getSupportGroup() {
		return browser.isCheckBoxSelected(supportGroup());
	}
	
	public void selectGroupPointsUsage(String usage) {
		browser.selectDropdownList(groupPointsUsage(), usage);
	}
	
	public String getGroupPointsUsage() {
		return browser.getDropdownListValue(groupPointsUsage(), 0);
	}
	
	public void selectGroupQuotaUsage(String usage) {
		browser.selectDropdownList(groupQuotaUsage(), usage);
	}
	
	public String getGroupQuotaUsage() {
		return browser.getDropdownListValue(groupQuotaUsage(), 0);
	}
	
	public void selectGroupQuotaIntegrity(String integrity) {
		browser.selectDropdownList(groupQuotaIntegrity(), integrity);
	}
	
	public String getGroupQuotaIntegrity() {
		return browser.getDropdownListValue(groupQuotaIntegrity(), 0);
	}
	
	public String getMaximumExcessPercentage() {
		return browser.getTextFieldValue(maximumExcessPercentage());
	}
	
	public void setSuccessfulRangeFrom(String rangeFrom) {
		browser.setTextField(successfulRangeFrom(), rangeFrom);
	}
	
	public String getSuccessfulRangeFrom() {
		return browser.getTextFieldValue(successfulRangeFrom());
	}
	
	public void setSuccessfulRangeTo(String rangeTo) {
		browser.setTextField(successfulRangeTo(), rangeTo);
	}
	
	public String getSuccessfulRangeTo() {
		return browser.getTextFieldValue(successfulRangeTo());
	}
	
	public String getWiningPercentage() {
		return browser.getTextFieldValue(winingPercentage());
	}
	
	public void clickOK() {
		browser.clickGuiObject(ok());
	}
	
	public void clickCancel() {
		browser.clickGuiObject(cancel());
	}
	
	public void clickApply() {
		browser.clickGuiObject(apply());
	}
	
	public void setupExecutionConfig(LotteryExecutionConfigInfo config) {
		setDescription(config.getDescription());
		if(!StringUtil.isEmpty(config.getLotteryType())) {
			selectLotteryType(config.getLotteryType());
			ajax.waitLoading();
		}
		if(!StringUtil.isEmpty(config.getDrawType())) {
			selectDrawType(config.getDrawType());
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(config.getRandomNumberRangeFrom())){
			setRandomNumberRangeFrom(config.getRandomNumberRangeFrom());
		}
		if(StringUtil.notEmpty(config.getRandomNumberRangeTo())){
			setRandomNumberRangeTo(config.getRandomNumberRangeTo());
		}
		if(isUseSystemSeedExists()) {
			selectUseSystemSeed(config.isUseSystemSeed());
		}
		if(isInitialNumberExists()) {
			selectInitialNumber(config.isInitialNumber());
		}
		
		if(!StringUtil.isEmpty(config.getAwardMethod())) {
			selectAwardMethod(config.getAwardMethod());
		}
		if(config.getEmailNotifications() != null && config.getEmailNotifications().length > 0) {
			selectEmailNotifications(config.getEmailNotifications());
		}
		
		if(!StringUtil.isEmpty(config.getSuccessfulRangeFrom())) {
			setSuccessfulRangeFrom(config.getSuccessfulRangeFrom());
		}
		if(!StringUtil.isEmpty(config.getSuccessfulRangeTo())) {
			setSuccessfulRangeTo(config.getSuccessfulRangeTo());
		}
		
		if(config.isSupportGroup()) {
			selectSupportGroup();
			ajax.waitLoading();
			selectGroupPointsUsage(config.getGroupConfiguration().getGroupPointsUsage());
			selectGroupQuotaUsage(config.getGroupConfiguration().getGroupQuotaUsage());
			selectGroupQuotaIntegrity(config.getGroupConfiguration().getGroupQuotaIntegrity());
			ajax.waitLoading();
		}
	}
	
	public boolean isErrorMessageExists() {
		return browser.checkHtmlObjectExists(errorMsg());
	}
	
	public String getErrorMessage() {
		return browser.getObjectText(errorMsg());
	}
}
