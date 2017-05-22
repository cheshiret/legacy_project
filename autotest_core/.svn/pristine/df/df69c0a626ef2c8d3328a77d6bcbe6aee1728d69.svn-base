package com.activenetwork.qa.testapi.interfaces.win;

import com.activenetwork.qa.testapi.page.Page;
import com.activenetwork.qa.testapi.util.Property;

public interface IWin {
	
	boolean exists();
	
	void waitExists();
	
	void waitExists(int timeout);
	
	Object waitExists(int timeout, Page... page);
	
	boolean checkWinObjectExists(Property[] properties);
	
	boolean checkWinObjectDisplayed(Property[] properties);
	
	IWinObject[] getWinObject(Property[] properties);
		
	IWinObject[] getTextBox(Property[] properties);
	
	IWinObject[] getDateTimePicker(Property[] properties);
	
	IWinObject[] getDataGrid(Property[] properties);
	
	IWinObject[] getComboBox(Property[] properties);
	
	void setTextBox(Property[] properties,String text);
	void setTextBox(Property[] properties, int index, String text);
	void setTextBox(Property[] properties, int index, boolean forced, String text);
	
	String getTextBoxValue(Property[]properties);
	String getTextBoxValue(Property[] properties, int index);
	String getTextBoxValue(Property[] properties, int index, boolean forced);
	
	void clickWinObject(Property[] properties);
	void clickWinObject(Property[] properties, int index);
	void clickWinObject(Property[] properties, int index, boolean forced);
	
	void doubleclickWinObject(Property[] properties);
	void doubleclickWinObject(Property[] properties, int index);
	void doubleclickWinObject(Property[] properties, int index, boolean forced);
	
	void setDateTimePicker(Property[] properties, String date);
	void setDateTimePicker(Property[] properties, int index,String date);
	void setDateTimePicker(Property[] properties, int index, boolean forced,String date);
	
	String getDateTimePickerValue(Property[]properties);
	String getDateTimePickerValue(Property[] properties, int index);
	String getDateTimePickerValue(Property[] properties, int index, boolean forced);
	
	void selectDropDownlist(Property[] properties,String item);
	
	void start(String path, String processName);
	
	void close();
}
