/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;


/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * @Defects:
 * 
 * @author asun
 * @Date  Oct 26, 2011
 */
public class UwpCalendarPage extends UwpPage {
	
	private static UwpCalendarPage instance=null;
	 IHtmlObject frame=null;
	
	private UwpCalendarPage(){
	}
	
	
	public static UwpCalendarPage getInstance(){
		if(instance==null){
			instance=new UwpCalendarPage();
		}
		return instance;
	}

	@Override
	public boolean exists() {
		return checkArrivalDateWidgetExist();
	}
	
	private IHtmlObject[] getCalendarFrame(){
		IHtmlObject[] objs = browser.getFrame("popupCalendar");
		return objs;
	}
	
	
	/**
	 * Check Day Use Date widget object exist
	 * @return
	 */
	public boolean checkArrivalDateWidgetExist(){
		boolean returnValue = false;
		IHtmlObject[] objs = getCalendarFrame();
		if(objs.length<=0){
			returnValue=false;
		}else{
			if(objs[0].style("display").trim().equals("block")){
				returnValue = true;
			}else returnValue = false;
		}
		Browser.unregister(objs);
		return returnValue;
	}
	
	/** Click Close in Day Use Date widget */
	public void clickClose(){
		IHtmlObject[] objs = browser.getFrame("popupCalendar");
		browser.clickGuiObject(".id", "close", false, 0, objs[0]);
		Browser.unregister(objs);
	}
	
	public void selectMonthYearForDateCalendar(String date){
		IHtmlObject[] objs = browser.getFrame("popupCalendar");
		if(objs.length<=0){
			throw new ErrorOnDataException("Calendar table can't be found.");
		}
		String[] dateFormat=DateFunctions.formatDate(date, "E MMM d yyyy").split(" ");
		String monthYear=dateFormat[1]+" "+dateFormat[3];
		browser.selectDropdownList(new Property[]{new Property(".id", "month")}, monthYear,true,objs[0]);
		Browser.unregister(objs);
	}
	
	public void clickDateFromDayUseDateWidget(String date){
		IHtmlObject[] objs = browser.getFrame("popupCalendar");
		if(objs.length<=0){
			throw new ErrorOnDataException("Calendar table can't be found.");
		}
		String today=DateFunctions.getToday();
		String monthForToday=today.split("/")[0];
		date=DateFunctions.formatDate(date, "MM/d/yyyy");
		String day = date.split("/")[1];
		String montnForDate=date.split("/")[0];
		Property[] p=null;;
		if(Integer.parseInt(montnForDate)==Integer.parseInt(monthForToday)){
			if(DateFunctions.compareDates(date, today)>=0)
			 p=new Property[]{new Property(".class", "Html.A"),new Property(".text", day),new Property(".href","javascript:void(0)")};
			else
			 p=new Property[]{new Property(".class", "Html.A"),new Property(".text", day)};
				
		}else if(Integer.parseInt(montnForDate)<Integer.parseInt(monthForToday)){
			p=new Property[]{new Property(".class", "Html.A"),new Property(".text", day)};
		}
		browser.clickGuiObject(p,  false, 0, objs[0]);
		Browser.unregister(objs);
	}
	
	
	
	public void verifyDayUseDateDisabled(String date, boolean flag){
		if(flag){
			if(!this.checkDayUseDateDisabled(date)){
				throw new ObjectNotFoundException("Day Use Date should be disabled.");
			}
		}else if(this.checkDayUseDateDisabled(date)){
			throw new ObjectNotFoundException("Day Use Date should be not disabled.");
		}
	}
	
	/**
	 * Check the date before current date and after the maximum searching window date should be grey out
	 * @param date
	 * @return
	 */
	public boolean checkDayUseDateDisabled(String date){
		boolean flag = false;
		IHtmlObject[] objs_1 = browser.getFrame("popupCalendar");
		if(objs_1.length<=0){
			throw new ErrorOnDataException("Calendar table can't be found.");
		}
		String day = DateFunctions.formatDate(date, "E MMM d yyyy").split(" ")[2];
		IHtmlObject[] objs_2 = browser.getHtmlObject(".class", "Html.A", ".text", day, objs_1[0]);
		if(objs_2.length<=0){
			throw new ErrorOnDataException("Specific date can't be found.");
		}
		if(objs_2[0].getProperty(".className").equals("grid window")){
			flag = true;
		}
		Browser.unregister(objs_1, objs_2);
		return flag;
	}

}
