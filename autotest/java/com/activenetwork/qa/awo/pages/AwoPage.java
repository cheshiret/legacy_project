package com.activenetwork.qa.awo.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.page.HtmlMainPage;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.StringUtil;

public abstract class AwoPage extends HtmlMainPage {
	/**
	 * verify date component automatically validate and correct those invalid dates input
	 * @param dateFieldObject
	 * @param invalidDates
	 * @return
	 */
	public boolean verifyAutomaticDateCorrection(IText dateFieldObject, String invalidDates[]) {
		String correctedDateValue = "";
		for(int i = 0; i < invalidDates.length; i ++) {
			setDateAndGetMessage(dateFieldObject, invalidDates[i]);
			correctedDateValue = dateFieldObject.getText();
			if(!StringUtil.isEmpty(correctedDateValue) && !DateFunctions.isValidDate(correctedDateValue)) {
				logger.error("Correct invalid date: " + invalidDates[i] + " failed.");
				return false;
			}
		}
		
		return true;
	}
	
	public boolean verifyAutomaticDateCorrection(Property[] properties, String invalidDates[]) {
		String correctedDateValue = "";
		IText dateFieldObject;
		for(int i = 0; i < invalidDates.length; i ++) {
			dateFieldObject = (IText)browser.getTextField(properties)[0];
			setDateAndGetMessage(dateFieldObject, invalidDates[i]);
			correctedDateValue = dateFieldObject.getText();
			if(!StringUtil.isEmpty(correctedDateValue) && !DateFunctions.isValidDate(correctedDateValue)) {
				logger.error("Correct invalid date: " + invalidDates[i] + " failed.");
				
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Check given property drop down options sorted
	 * @param pros
	 * @return
	 */
	protected boolean checkDropDownOptionsSorting(Property[] pros){
		List<String> options = browser.getDropdownElements(pros);
		List<String> tmp = new ArrayList<>();
		tmp.addAll(options);
		
		Collections.sort(tmp);
		
		return tmp.equals(options);
	}
}
