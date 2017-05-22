package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeLotteryScheduleInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
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
 * @Date  Sep 4, 2013
 */
public class LicMgrProcessingDetailsPage extends LicMgrLotteriesCommonPage {
	private static LicMgrProcessingDetailsPage _instance = null;
	
	protected LicMgrProcessingDetailsPage() {}
	
	public static LicMgrProcessingDetailsPage getInstance() {
		if(_instance == null) _instance = new LicMgrProcessingDetailsPage();
		
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(status());
	}
	
	private static final String SPECIFIC_DATE = "Specific Date";
	private static final String SUBMISSION_DATE = "Submission Date";
	
	private Property[] changeHistory() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Change History");
	}
	
	private Property[] executionHistory() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Execution History");
	}
	
	private Property[] runNotification() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Run Notification");
	}
	
	private Property[] id() {                                         
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryScheduleView-\\d+\\.id:ZERO_TO_NEW", false));
	}
	
	private Property[] licenseYear() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryScheduleView-\\d+\\.licenseYear", false));
	}
	
	private Property[] status() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryScheduleView-\\d+\\.status", false));
	}
	
	private Property[] description() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryScheduleView-\\d+\\.description", false));
	}
	
	private Property[] lottery() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryScheduleView-\\d+\\.lottery", false));
	}
	
	private Property[] lotteryExecutionConf(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryScheduleView-\\d+\\.privilegeLotteryExecConfig.description", false));
	}
	
	private Property[] processingStatus(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryScheduleView-\\d+\\.executionStatus:CB_TO_NAME", false));
	}
	
	private Property[] submissionDateRadioButton() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryScheduleView-\\d+\\.calculateAgeAsOfSubmission", false), ".value", "true");
	}
		
	private Property[] seedNumber(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryScheduleView-\\d+\\.seedNumber", false));
	}
	
	private Property[] initialNumber(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryScheduleView-\\d+\\.initNumber", false));
	}
	
	private Property[] specificDateRadioButton() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryScheduleView-\\d+\\.calculateAgeAsOfSubmission", false), ".value", "false");
	}
	
	private Property[] specificDateTextField() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryScheduleView-\\d+\\.calculateAgeAsOf_ForDisplay", false));
	}
	
	private Property[] freezePeriodEndDate() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryScheduleView-\\d+\\.freezePeriodEnd.day_ForDisplay", false));
	}
	
	private Property[] freezePeriodEndTime() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryScheduleView-\\d+\\.freezePeriodEnd.timeStr", false));
	}
	
	private Property[] randomRangeTo() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryExecConfigView-\\d+.randomRangeTo", false));
	}
	
	private Property[] freezePeriodEndAMPM() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryScheduleView-\\d+\\.freezePeriodEnd.ampmStr", false));
	}
	
	private Property[] awardAcceptanceBy() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryScheduleView-\\d+\\.custAwardAcceptDeadline.day_ForDisplay", false));
	}
	
	private Property[] awardAcceptanceTime() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryScheduleView-\\d+\\.custAwardAcceptDeadline.timeStr", false));
	}
	
	private Property[] awardAcceptanceAMPM() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryScheduleView-\\d+\\.custAwardAcceptDeadline.ampmStr", false));
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
	
	private Property[] configurationTab() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Configuration");
	}
	
	private Property[] exceptionsTab() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Exceptions");
	}
	
	private Property[] resultsTab() {
		return Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("Results(\\(\\d+\\))?", false)); //"Results");
	}
	
	public void clickConfigurationTab() {
		browser.clickGuiObject(configurationTab());
	}
	
	public void clickExceptionsTab() {
		browser.clickGuiObject(exceptionsTab());
	}
	
	public void clickResultsTab() {
		browser.clickGuiObject(resultsTab());
	}
	
	public void clickChangeHistory() {
		browser.clickGuiObject(changeHistory());
	}
	
	public void clickExecutionHistory() {
		browser.clickGuiObject(executionHistory());
	}
	
	public void clickRunNotification() {
		browser.clickGuiObject(runNotification());
	}
	
	public String getID() {
//		return browser.getObjectText(id());
		return browser.getTextFieldValue(id());
	}
	
	public void selectLicenseYear(String year) {
		browser.selectDropdownList(licenseYear(), year);
	}
	
	public void selectStatus(String status) {
		browser.selectDropdownList(status(), status);
	}
	
	public void setDescription(String desc) {
		browser.setTextField(description(), desc);
	}
	
	public void selectLottery(String lottery) {
		browser.selectDropdownList(lottery(), lottery);
	}
	
	public void selectSubmissionDate() {
		browser.selectRadioButton(this.submissionDateRadioButton(), 0);
	}
	
	public void selectSpecificDate() {
		browser.selectRadioButton(this.specificDateRadioButton(), 0);
	}
	
	public void selectCalculateAgeAsOf(String type) {
		if(type.equalsIgnoreCase(SUBMISSION_DATE)) {
			selectSubmissionDate();
		} else if(type.equalsIgnoreCase(SPECIFIC_DATE)) {
			selectSpecificDate();
		} else throw new ItemNotFoundException("Unknown Calculate Age as of type: " + type);
		ajax.waitLoading();
	}
	
	public void setSpecificDate(String date) {
		browser.setCalendarField(specificDateTextField(), date);
	}
	
	public boolean isSpecificDateEnabled(){
		return browser.checkHtmlObjectEnabled(this.specificDateTextField());
	}
	
	public void setFreezePeriodEndDate(String date) {
		browser.setCalendarField(freezePeriodEndDate(), date);
	}
	
	public void setFreezePeriodEndTime(String time) {
		browser.setTextField(freezePeriodEndTime(), time);
	}
	
	public void selectFreezePeriodAMPM(String ampm) {
		browser.selectDropdownList(freezePeriodEndAMPM(), ampm);
	}
	
	public void setAwardAcceptanceBy(String date) {
		browser.setCalendarField(awardAcceptanceBy(), date);
	}
	
	public void setAwardAcceptanceTime(String time) {
		browser.setTextField(awardAcceptanceTime(), time);
	}
	
	public void selectAwardAcceptanceAMPM(String ampm) {
		browser.selectDropdownList(awardAcceptanceAMPM(), ampm);
	}
	
	public void setSeedNumber(String number){
		browser.setTextField(this.seedNumber(), number);
	}
	
	public void setInitialNumber(String number){
		browser.setTextField(this.initialNumber(), number);
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
	
	public void setupLotterySchedule(PrivilegeLotteryScheduleInfo schedule) {
		if(this.isStatusEditable() && StringUtil.notEmpty(schedule.getStatus())) { this.selectStatus(schedule.getStatus()); }
		
		if(this.isLicenseYearEditable() && StringUtil.notEmpty(schedule.getLicenseYear())) this.selectLicenseYear(schedule.getLicenseYear());
		if(schedule.getDescription()!=null) this.setDescription(schedule.getDescription());
		if(this.isLotteryEditable()&&StringUtil.notEmpty(schedule.getLottery())) this.selectLottery(schedule.getLottery());
		if(StringUtil.notEmpty(schedule.getCalculateAgeMethod())) {
			this.selectCalculateAgeAsOf(schedule.getCalculateAgeMethod());
			ajax.waitLoading();
			if(schedule.getCalculateAgeMethod().equalsIgnoreCase(SPECIFIC_DATE)) {
				if(schedule.getSpecificDate()!=null) this.setSpecificDate(schedule.getSpecificDate());
			}
		}
		
		if(this.isSeedNumberExist()&&schedule.getSeedNumber()!=null) { this.setSeedNumber(schedule.getSeedNumber()); }
		if(this.isInitalNumberExist()&&schedule.getInitialNumber()!=null) { this.setInitialNumber(schedule.getInitialNumber()); }
		if(this.isFreezePeriodEndDateExist()&&schedule.getFreezePeriodEndDate()!=null) this.setFreezePeriodEndDate(schedule.getFreezePeriodEndDate());
		if(StringUtil.notEmpty(schedule.getFreezePeriodEndTime())) this.setFreezePeriodEndTime(schedule.getFreezePeriodEndTime());
		if(StringUtil.notEmpty(schedule.getFreezePeriodEndAmPm())) this.selectFreezePeriodAMPM(schedule.getFreezePeriodEndAmPm());
		if(StringUtil.notEmpty(schedule.getAwardAcceptanceByDate())) this.setAwardAcceptanceBy(schedule.getAwardAcceptanceByDate());
		if(StringUtil.notEmpty(schedule.getAwardAcceptanceByTime())) this.setAwardAcceptanceTime(schedule.getAwardAcceptanceByTime());
		if(StringUtil.notEmpty(schedule.getAwardAcceptanceByAmPm())) this.selectAwardAcceptanceAMPM(schedule.getAwardAcceptanceByAmPm());
	}
	
	public String getLisenseYear(){
		return browser.getTextFieldValue(this.licenseYear());
	}
	
	public String getStatus(){
		return browser.getDropdownListValue(this.status(), 0);
	}
	
	public String getStatusWhenAddNew(){
		return browser.getTextFieldValue(this.status());
	}
	
	public String getDescription(){
		return browser.getTextFieldValue(this.description());
	}
	
	public String getLottery(){
		return browser.getTextFieldValue(this.lottery());
	}
	
	public String getExeConfiguration(){
		return browser.getTextFieldValue(this.lotteryExecutionConf());
	}
	
	public String getProcessingStatus(){
		return browser.getTextFieldValue(this.processingStatus());
	}
	
	public String getLotteryProcessingStatus(){
		return browser.getTextFieldValue(this.processingStatus());
	}
	
	public String getCalculateAgeAsOf(){
		String dateType = "";
		if(browser.isRadioButtonSelected(this.submissionDateRadioButton())){
			dateType = SUBMISSION_DATE;
		}
		if(browser.isRadioButtonSelected(this.specificDateRadioButton())){
			dateType = SPECIFIC_DATE;
		}
		return dateType;
	}
	
	public String getSpecificDate(){
		return browser.getTextFieldValue(this.specificDateTextField());
	}
	
	public String getSeedNum(){
		return browser.getTextFieldValue(this.seedNumber());
	}
	
	public String getInitialNum(){
		return browser.getTextFieldValue(this.initialNumber());
	}
	
	public String getFreezePeriodEndDate(){
		return browser.getTextFieldValue(this.freezePeriodEndDate());
	}
	
	public String getFreezePeriodEndTime(){
		return browser.getTextFieldValue(this.freezePeriodEndTime());
	}
	
	public String getFreezePeriodEndAPM(){
		return browser.getDropdownListValue(this.freezePeriodEndAMPM(), 0);
	}
	
	public String getAwardAcceptanceByDate(){
		return browser.getTextFieldValue(this.awardAcceptanceBy());
	}
	
	public String getAwardAcceptanceByTime(){
		return browser.getTextFieldValue(this.awardAcceptanceTime());
	}
	
	public String getAwardAcceptanceByAPM(){
		return browser.getDropdownListValue(this.awardAcceptanceAMPM(), 0);
	}
	
	public boolean isStatusEditable(){
		return browser.checkHtmlObjectEnabled(this.status());
	}
	
	public boolean isLicenseYearEditable(){
		return browser.checkHtmlObjectEnabled(this.licenseYear());
	}
	
	public boolean isDescriptionEditable(){
		return browser.checkHtmlObjectEnabled(this.description());
	}
	
	public boolean isLotteryEditable(){
		return browser.checkHtmlObjectEnabled(this.lottery());
	}
	
	public boolean isLotteryExecutionConfigEditable(){
		return browser.checkHtmlObjectEnabled(this.lotteryExecutionConf());
	}
	
	public boolean isProcessingStatusEditable(){
		return browser.checkHtmlObjectEnabled(this.processingStatus());
	}
	
	//This is show when Indicator Use System Seed is â€œNoâ€� of execution configuration
	public boolean isSeedNumberEditable(){
		return browser.checkHtmlObjectEnabled(this.seedNumber());
	}
	public boolean isSeedNumberExist(){
		return browser.checkHtmlObjectExists(this.seedNumber());
	}
	//This is show when Indicator Initial Number is â€œYesâ€� of execution configuration
	public boolean isInitalNumberEditable(){
		return browser.checkHtmlObjectEnabled(this.initialNumber());
	}
	public boolean isInitalNumberExist(){
		return browser.checkHtmlObjectExists(this.initialNumber());
	}
	
	public boolean isFreezePeriodEndDateEditable(){
		return browser.checkHtmlObjectEnabled(this.freezePeriodEndDate());
	}
	
	public boolean isFreezePeriodEndDateExist(){
		return browser.checkHtmlObjectExists(this.freezePeriodEndDate());
	}
	
	public boolean isFreezePeriodEndTimeEditable(){
		return browser.checkHtmlObjectEnabled(this.freezePeriodEndTime());
	}
	
	public boolean isFreezePeriodEndTimeExist(){
		return browser.checkHtmlObjectExists(this.freezePeriodEndTime());
	}

	public boolean isAwardAcceptanceByDateEditable(){
		return browser.checkHtmlObjectEnabled(this.awardAcceptanceBy());
	}
	
	public boolean isAwardAcceptanceByDateExist(){
		return browser.checkHtmlObjectExists(this.awardAcceptanceBy());
	}
	
	public boolean isAwardAcceptanceByTimeEditable(){
		return browser.checkHtmlObjectEnabled(this.awardAcceptanceTime());
	}
	
	public boolean isAwardAcceptanceTimeExist(){
		return browser.checkHtmlObjectExists(this.awardAcceptanceTime());
	}
	
	public String getErrorMess(){
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.DIV",".id", "NOTSET");
		if(objs.length<1){
			throw new ItemNotFoundException("Cann't find error message.");
		}
		String message=objs[0].getProperty(".text");
		Browser.unregister(objs);
		return message;
	}
	
	public PrivilegeLotteryScheduleInfo getScheduleInfo(){
		PrivilegeLotteryScheduleInfo schedule = new PrivilegeLotteryScheduleInfo();
		schedule.setId(this.getID());
		schedule.setLicenseYear(this.getLisenseYear());
		schedule.setStatus(this.getStatus());
		schedule.setDescription(this.getDescription());
		schedule.setLottery(this.getLottery());
		schedule.setExecutionConfig(this.getExeConfiguration());
		schedule.setProcessingStatus(this.getProcessingStatus());
		if(this.isSeedNumberExist()){ schedule.setSeedNumber(this.getSeedNum());}
		if(this.isInitalNumberExist()){ schedule.setInitialNumber(this.getInitialNum());}
		schedule.setCalculateAgeMethod(this.getCalculateAgeAsOf());
		if(schedule.getCalculateAgeMethod().equalsIgnoreCase(SPECIFIC_DATE)){
			schedule.setSpecificDate(this.getSpecificDate());
		}
		if(this.isFreezePeriodEndDateExist()){
			schedule.setFreezePeriodEndDate(this.getFreezePeriodEndDate());
			schedule.setFreezePeriodEndTime(this.getFreezePeriodEndTime());
			schedule.setFreezePeriodEndAmPm(this.getFreezePeriodEndAPM());
		}
		if(this.isAwardAcceptanceByDateExist()){
			schedule.setAwardAcceptanceByDate(this.getAwardAcceptanceByDate());
			schedule.setAwardAcceptanceByTime(this.getAwardAcceptanceByTime());
			schedule.setAwardAcceptanceByAmPm(this.getAwardAcceptanceByAPM());
		}
		return schedule;
	}
	
	public boolean compareScheduleInfo(PrivilegeLotteryScheduleInfo expShecule){
		boolean result = true;
		PrivilegeLotteryScheduleInfo actSchedule = this.getScheduleInfo();
		result &= MiscFunctions.compareResult("ID", expShecule.getId(), actSchedule.getId());
		result &= MiscFunctions.compareResult("License year", expShecule.getLicenseYear(), actSchedule.getLicenseYear());
		result &= MiscFunctions.compareResult("Status", expShecule.getStatus(), actSchedule.getStatus());
		result &= MiscFunctions.compareResult("Description", expShecule.getDescription(), actSchedule.getDescription());
		result &= MiscFunctions.compareResult("Lottery", expShecule.getLottery(), actSchedule.getLottery());
		result &= MiscFunctions.compareResult("Lottery execution configuration", expShecule.getExecutionConfig(), actSchedule.getExecutionConfig());
		result &= MiscFunctions.compareResult("Processing status", expShecule.getProcessingStatus(), actSchedule.getProcessingStatus());
		if(StringUtil.notEmpty(expShecule.getSeedNumber())){
			result &= MiscFunctions.compareResult("Seed number", expShecule.getSeedNumber(), actSchedule.getSeedNumber());
		}
		if(StringUtil.notEmpty(expShecule.getInitialNumber())){
			result &= MiscFunctions.compareResult("Initial number", expShecule.getInitialNumber(), actSchedule.getInitialNumber());
		}
		result &= MiscFunctions.compareResult("Calculate Age as method", expShecule.getCalculateAgeMethod(), actSchedule.getCalculateAgeMethod());
		if(expShecule.getCalculateAgeMethod().equalsIgnoreCase(SPECIFIC_DATE)){
			result &= MiscFunctions.compareResult("Specific Date", expShecule.getSpecificDate(), actSchedule.getSpecificDate());
		}
		if(this.isFreezePeriodEndDateExist()){
			result &= MiscFunctions.compareResult("Freeze period end date", expShecule.getFreezePeriodEndDate(), actSchedule.getFreezePeriodEndDate());
			result &= MiscFunctions.compareResult("Freeze period end time", expShecule.getFreezePeriodEndTime(), actSchedule.getFreezePeriodEndTime());
			result &= MiscFunctions.compareResult("Freeze period end AM/PM", expShecule.getFreezePeriodEndAmPm(), actSchedule.getFreezePeriodEndAmPm());
		}
		if(this.isAwardAcceptanceByDateExist()){
			result &= MiscFunctions.compareResult("Award acceptance by date", expShecule.getAwardAcceptanceByDate(), actSchedule.getAwardAcceptanceByDate());
			result &= MiscFunctions.compareResult("Award acceptance by time", expShecule.getAwardAcceptanceByTime(), actSchedule.getAwardAcceptanceByTime());
			result &= MiscFunctions.compareResult("Award acceptance by AM/PM", expShecule.getAwardAcceptanceByAmPm(), actSchedule.getAwardAcceptanceByAmPm());
		}
		return result;
	}
	
	public String getRandomRangeTo(){
		IHtmlObject[] objs = browser.getHtmlObject(randomRangeTo());
		String randomRangeTo = StringUtil.EMPTY;
		if(objs.length>0){
			randomRangeTo = objs[0].getProperty(".value").trim();
		}else throw new ObjectNotFoundException("Can't find randon range to text field.");
		return randomRangeTo;
	}
}
