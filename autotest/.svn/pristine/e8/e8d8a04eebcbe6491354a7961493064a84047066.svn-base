package com.activenetwork.qa.awo.pages;

import java.util.Arrays;
import java.util.List;

import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.TimedOutException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.Timer;

public abstract class UwpPage extends AwoPage {
	
	protected Property[] pageTitleDiv() {
		return Property.concatPropertyArray(this.div(), ".id", "pagetitle");
	}
	
	public String getPageTitle() {
		return browser.getObjectText(this.pageTitleDiv());
	}
	
	public String getBrowserTitle() {
		return browser.title();
	}
	
	public void dismissCalendar() {
		IHtmlObject[] frames=browser.getFrame("popupCalendar");
		
		if(frames.length>0) {
			String display=frames[0].style("DISPLAY");
			if(display !=null && display.length()>0 && !display.equalsIgnoreCase("none")) {
				browser.clickGuiObject(".id","close",true,0,frames[0]);
			}
		}
		Browser.unregister(frames);
	}
	
	/**
	 * check if the UWP Progress bar displayed or not
	 * @return
	 */
	public boolean progressBarDisplayed()  {
        IHtmlObject[] objs=browser.getHtmlObject(".class","Html.DIV",".id", new RegularExpression("(contentProgressBar|matrixprogresspopup_\\d+)", false));
        
        if(objs==null || objs.length>0) { 
        	if(objs==null) {
        		//we will get null if browser is not ready (in loading progress), return true in this case
        		return true;
        	} 
        	
        	String display=objs[0].style("DISPLAY");
        	Browser.unregister(objs);
            if(display.trim().toLowerCase().matches("block|none")) {
            	return false;	  
            } else {
            	return true;
            }
        } else {
        	return false;
        }
        
        
	}
	
	/**
	 * check if the UWP progress bar displayed within timeout period
	 * @param timeout
	 * @return
	 */
	public boolean progressBarDisplayed(int timeout) {
		boolean found=false;
		Timer timer=new Timer();
		while(!found && timer.diffLong()<timeout*1000) {
			found=progressBarDisplayed();
		}
		
		return found;
	}
	
	public void waitForProgressBarDisapear() {
		waitForProgressBarDisapear(timeout);
	}
	
	public void waitForProgressBarDisapear(int timeout) {
		logger.info("Waiting for progress bar dispear...");
		boolean exists=true;
		Timer timer=new Timer();
		while(exists && timer.diffLong()<timeout*2000) {
			exists=progressBarDisplayed();
			Timer.sleep(2000);
		}
		
		if(exists) {
			throw new TimedOutException("Progression bar doesn't disappear within "+timeout+" seconds.");
		}
	}
	
	/**
	 * Verify gave data sorting by alphabetically 
	 * @param data
	 * @param isCaseSensitive  --true: is Case Sensitive
	 *                           --false: is not case Sensitive
	 */
	public void verifySortByAlphabetical(String[] gaveData, boolean isCaseSensitive){

		String[] beforeSorting_1 = gaveData;
		String[] beforeSorting_2 = new String[beforeSorting_1.length];
		for(int i=0; i<beforeSorting_1.length; i++){
			if(isCaseSensitive){
				beforeSorting_2[i] = beforeSorting_1[i];
			}else{
				beforeSorting_2[i] = beforeSorting_1[i].toLowerCase();
			}
		}
		Arrays.sort(gaveData);
		for(int j=0; j<gaveData.length; j++){
			if(!gaveData[j].equalsIgnoreCase(beforeSorting_2[j])){
				throw new ErrorOnDataException(j+" - The sorting order is incorrect.", beforeSorting_2[j], gaveData[j]);
			}
		}
	}

	/**
	 * Verify gave data sorting by alphabetically, has case insensitive
	 * @param gaveData
	 */
	public void verifySortByAlphabetical(String[] gaveData){
		this.verifySortByAlphabetical(gaveData, true);
	}

	/**	Verify gave data sorting by alphabetically 	 
	 *  @param data	 
	 * @param isCaseSensitive    --true: is Case Sensitive
	 *                           --false: is not case Sensitive
	 */
	public void verifySortByAlphabetical(List<String> gaveData, boolean isCaseSensitive){		
		List<String> beforeSorting = gaveData;		
		String[] afterSorting = new String[beforeSorting.size()];	

		for(int i=0; i<beforeSorting.size(); i++){	
			if(isCaseSensitive){
				afterSorting[i] = beforeSorting.get(i);	
			}else{
				afterSorting[i] = beforeSorting.get(i).toLowerCase();
			}
		}				
		Arrays.sort(afterSorting);		
		for(int i=0; i<beforeSorting.size(); i++){			
			if(!beforeSorting.get(i).equalsIgnoreCase(afterSorting[i])){				
				throw new ErrorOnDataException("The sorting order is incorrect at index:"+i, afterSorting[i], beforeSorting.get(i));			
			}		
		}	
		logger.info("Successfully verify test data "+gaveData.toString()+" sort alphabetically.");
	}

	/**
	 * Verify gave data sorting by alphabetically, has case insensitive
	 * @param gaveData
	 */
	public void verifySortByAlphabetical(List<String> gaveData){
		this.verifySortByAlphabetical(gaveData, true);
	}
}