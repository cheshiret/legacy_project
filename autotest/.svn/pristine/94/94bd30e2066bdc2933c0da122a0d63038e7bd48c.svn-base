/*
 * Created on Feb 3, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.resourceManager;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;



/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ResMgrSchedulerDetailStepThreePage extends ResourceManagerPage {

	static private ResMgrSchedulerDetailStepThreePage _instance = null;
	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */

	static public ResMgrSchedulerDetailStepThreePage getInstance()throws PageNotFoundException {
		if (null == _instance) {
			_instance = new ResMgrSchedulerDetailStepThreePage();
		}

		return _instance;
	}

	private ResMgrSchedulerDetailStepThreePage(){}
	
	/** Determine if the Resource Manager Scheduler Detail Page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("Step 3: Set Schedule Date Time & Notifications",false));
	}

	/**
	 * Select Hour from dropdown list.
	 * @param hour
	 */
	public void selectHour(String hour) {
		browser.selectDropdownList(".id", "_SchedulerHour", hour);
	}
	
	/**
	 * This method used to get default Hour Value
	 * @return-hour value
	 */
	public String getDefaultHour()
	{
	  	return browser.getDropdownListValue(".id", "_SchedulerHour",0);
	}
	
	/**
	 * Select Minute from dropdown list.
	 * @param minute
	 */
	public void selectMinute(String minute) {
		browser.selectDropdownList(".id", "_SchedulerMinute", minute);
	}
	
	public String getDefaultMinute()
	{
	  	return browser.getDropdownListValue(".id", "_SchedulerMinute",0);
	}
	/**
	 * Select Ampm from dropdown list.
	 * @param ampm
	 */
	public void selectAmpm(String ampm) {
		browser.selectDropdownList(".id", "_SchedulerAmpm", ampm);
	}
	
	public String getAmPm()
	{
	  	return browser.getDropdownListValue(".id", "_SchedulerAmpm",0);
	}
	
	/**
	 * select one of the radio of Once Daily Weekly Monthly
	 * @param Frequency2
	 */
	public void selectOnceDailyWeeklyMonthlyRadio(String Frequency2) {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.INPUT.radio");
		p[1] = new Property(".id", "_SchedulerScheduleFrequency");
		p[2] = new Property(".value", Frequency2);
		browser.selectRadioButton(p,0);
		waitLoading();
	}
	
	/**select Every radio on daily */
	public void selectDailyEveryRadio() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.INPUT.radio");
		p[1] = new Property(".id", "_SchedulerDailyFreq");
		p[2] = new Property(".value", "every_n");
		browser.selectRadioButton(p,0);
	}
	
	/**select weekdays radio on daily */
	public void selectDailyWeekdaysRadio() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.INPUT.radio");
		p[1] = new Property(".id", "_SchedulerDailyFreq");
		p[2] = new Property(".value", "weekdays");
		browser.selectRadioButton(p,0);
	}
	
	/**
	 * set days value on daily
	 * @param days
	 */
	public void selectDailyDays(String days) {
		browser.selectDropdownList(".id", "_SchedulerDailyFreqCount", days, true);
		
	}
	
	/**select Every radio on Weekly */
	public void selectWeeklyEveryRadio() {
		browser.selectRadioButton(".class", "Html.INPUT.radio",".id", "_SchedulerWeeklyFreq");
	}

	/**
	 * set times per week value on Weekly
	 * @param timesperweek
	 */
	public void selectWeeklyTimesPerweek(String timesperweek) {
		browser.selectDropdownList(".class","Html.SELECT",".id", "_SchedulerWeeklyFreqCount", timesperweek, true);
		
	}
	/**select DayOfWeek CheckBox Weekly 
	 * @param dayOfWeek --Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday
	 * */
	public void selectWeeklyDayOfWeekCheckBox(String dayOfWeek) {
		
		String getdayOfWeek[] = dayOfWeek.split(",| ");
		for(int i=0;i<getdayOfWeek.length;i++){
			Property[] p = new Property[3];
			p[0] = new Property(".class", "Html.INPUT.checkbox");
			p[1] = new Property(".id", "_SchedulerWeekDay");
			p[2] = new Property(".value", getdayOfWeek[i]);
			browser.selectCheckBox(p);
		}
	}
	
	/**select Day radio on Monthly */
	public void selectMonthlyDayRadio() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.INPUT.radio");
		p[1] = new Property(".id", "_SchedulerMonthlyFreq");
		p[2] = new Property(".value", "Day");
		browser.selectRadioButton(p,0);
	}
	
	/**
	 * set day count about day on Monthly
	 * @param DayCount
	 */
	public void selectMonthlyDayDayCount(String DayDayCount) {
		browser.selectDropdownList(".id", "_SchedulerMonthDayCount", DayDayCount, true);
		
	}
	
	/**
	 * set Month count about Day radio on Monthly
	 * @param DayMonthCount
	 */
	public void selectMonthlyDayMonthCount(String DayMonthCount) {
		browser.selectDropdownList(".id", "_SchedulerMonthDayCount", DayMonthCount, true);
		
	}
	
	/**select The radio on Monthly */
	public void selectMonthlyTheRadio() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.INPUT.radio");
		p[1] = new Property(".id", "_SchedulerMonthlyFreq");
		p[2] = new Property(".value", "The");
		browser.selectRadioButton(p,0);
	}
	
	/**
	 * set Weekday Count about The radio on Monthly
	 * @param TheWeekdayCount
	 */
	public void selectMonthlyTheWeekdayCount(String TheWeekdayCount) {
		browser.selectDropdownList(".id", "_SchedulerMonthWeekdayCount", TheWeekdayCount, true);
		
	}
	
	/**
	 * set Weekday about The radio on Monthly
	 * @param TheWeekday
	 */
	public void selectMonthlyTheWeekday(String TheWeekday) {
		browser.selectDropdownList(".id", "_SchedulerMonthWeekday", TheWeekday, true);
		
	}
	
	/**
	 * set Month Count about The radio on Monthly
	 * @param TheMonthCount
	 */
	public void selectMonthlyTheMonthCount(String TheMonthCount) {
		browser.selectDropdownList(".id", "_SchedulerMonthCount2", TheMonthCount, true);
		
	}
	
	/**
	 * Input Start date on Once
	 * @param onceStartDate
	 */
	public void setOnceStartDate(String onceStartDate) {
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.INPUT.text", ".id", "_SchedulerScheduleStartDate_ForDisplay");
		((IText)objs[0]).setText(onceStartDate);
		Browser.unregister(objs);
	}
	
	/**
	 * Input Start date 
	 * @param StartDate
	 */
	public void setStartDate(String StartDate) {
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.INPUT.text", ".id", "_SchedulerSchRangeStartDate_ForDisplay");
		((IText)objs[0]).setText(StartDate);
		Browser.unregister(objs);
	}

	/**
	 * set End date 
	 * @param EndDate
	 */
	public void setEndDate(String EndDate) {
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.INPUT.text", ".id", "_SchedulerSchRangeEndDate_ForDisplay");
		((IText)objs[0]).setText(EndDate);
		Browser.unregister(objs);
	}
	
	/**select No End Date radio */
	public void selectNoEndDateRadio() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.INPUT.radio");
		p[1] = new Property(".id", "_SchedulerSchRangeEndDateOption");
		p[2] = new Property(".value", "no_end");
		browser.selectRadioButton(p,0);
	}
	
	/**select End Date radio */
	public void selectEndDateRadio() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.INPUT.radio");
		p[1] = new Property(".id", "_SchedulerSchRangeEndDateOption");
		p[2] = new Property(".value", "yes");
		browser.selectRadioButton(p,0);
	}
	
	/** Click on Add for new */
	public void clickAddForNew() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Add>>");
		p[2] = new Property(".href", new RegularExpression(".*AddManualFailureRecipient.*",true));
		browser.clickGuiObject(p);
	}
	
	/** Click on Add for selecting from email list */
	public void clickAddForSelectingList() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Add>>");
		p[2] = new Property(".href", new RegularExpression(".*AddAutoFailureRecipient.*",true));
		browser.clickGuiObject(p);
	}
	
	
	/** Click on Remove after adding for new */
	public void clickRemoveAfterAddForNew() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "<<Remove");
		p[2] = new Property(".href", new RegularExpression("^javascript.*RemoveManualFailureRecipient.*",true));
		browser.clickGuiObject(p);
	}
	
	/** Click on Remove after selecting from email list */
	public void clickRemoveAfterSelectList() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "<<Remove");
		p[2] = new Property(".href", new RegularExpression("^javascript.*RemoveAutoFailureRecipient.*",true));
		browser.clickGuiObject(p);
	}
	/**
	 * add new email address
	 * @param EmailAddr
	 */
	public void addNewEmailAddr(String EmailAddr) {
		browser.setTextField(".id", "__SchedulerFailureManuBaseRecipients", EmailAddr, true);
		this.clickAddForNew();
		this.waitLoading();
		
	}
	
	/**
	 * Select one email address from email address list.
	 * @param EmailAddress 
	 */
	public void selectEmailAddress(String EmailAddress) {
		browser.selectDropdownList(".id", "__SchedulerFailureAutoBaseRecipients", EmailAddress);
		this.clickAddForSelectingList();
		this.waitLoading();
	}
	
	/**
	 * get email address from email address after adding list.
	 * @param EmailAddress 
	 */
	public String getEmailAddressAfterAdd() {
		return browser.getObjectText(".class","Html.SELECT",".id", "__SchedulerFailureManuChosenRecipients");
	}
	
	/**
	 * get email address from email address after selecting list.
	 * @param EmailAddress 
	 */
	public String getEmailAddressAfterSelectLisr() {
		return browser.getObjectText(".class","Html.SELECT",".id", "__SchedulerFailureAutoChosenRecipients");
	}

	/** Click on Previous */
	public void clickPrevious() {
		browser.clickGuiObject(".class", "Html.A", ".text", "<<Previous", true);
	}
	
	/** Click on Save Schedule */
	public void clickSaveSchedule() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Save", true);
		
	}
	
	/** Click on Cancel */
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel", true);
	}
	
	/**
	 * This method used to enter schedule date time and notification info
	 * @param schedule-ScheduleData
	 */
	public void enterInfoForStepThree(ScheduleData schedule)
	{
	  	logger.info("Step 3: Set Schedule Date Time & Notifications Info.");
	  	
	  	if(schedule.timePeriod!=null&&!schedule.timePeriod.equals("")){
	  	  calculateScheduleTimeForAutomation(schedule);//set a dynamic schedule time for automation to verify scheduler running
	  	  selectHour(schedule.startHour);
	  	  selectMinute(schedule.startMinutes);
	  	  selectAmpm(schedule.startAmPm);
	  	}
	  	if(schedule.frequency!=null&&!schedule.frequency.equals("")){
	  	  	selectOnceDailyWeeklyMonthlyRadio(schedule.frequency);
			if(schedule.frequency.equalsIgnoreCase("Once")){
			  if(schedule.startDate!=null&&!schedule.startDate.equals("")){
			    setOnceStartDate(schedule.startDate);
			  }
			}else{
			  //TO DO~~
			}
	  	}
	  	
	  	if(schedule.notifyRecipient!=null&&!schedule.notifyRecipient.equals("")){
	  	  addNewEmailAddr(schedule.notifyRecipient);
	  	}
	  	if(schedule.recipientFromList!=null&&!schedule.recipientFromList.equals("")){
	  	  selectEmailAddress(schedule.recipientFromList);
	  	}
	}	
	
	/**
	 * This method is used to set a dynamic scheduler running time for automation
	 * Current time add given time period is the final running time
	 * @param schedule
	 */
	public void calculateScheduleTimeForAutomation(ScheduleData schedule)
	{
	  	  int min = Integer.parseInt(this.getDefaultMinute())+Integer.parseInt(schedule.timePeriod);
	  	  int hour = Integer.parseInt(this.getDefaultHour());
	  	  String startAmPm = this.getAmPm();
	  	  if(min>59){
	  	    min = min-60;
	  	    hour = hour+1;
	  	    if(startAmPm.equalsIgnoreCase("AM")){
		  	    if(hour>12){
		  	      hour = hour -12;
		  	      schedule.startAmPm = "PM";
		  	    }
	  	    }else if(startAmPm.equalsIgnoreCase("PM")){
		  	    if(hour>11){
		  	      hour = hour -12;
		  	      schedule.startAmPm = "AM";
		  	    }
	  	    }
	  	  }
	  	  schedule.startHour = Integer.valueOf(hour).toString();
	  	  schedule.startMinutes = Integer.valueOf(min).toString();
	  	  schedule.startAmPm = startAmPm;
	}
}
