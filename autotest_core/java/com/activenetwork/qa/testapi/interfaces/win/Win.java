package com.activenetwork.qa.testapi.interfaces.win;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.NotInitializedException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.TestApiConstants;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.page.Page;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.Timer;

public abstract class Win implements IWin {
	protected static AutomationLogger logger = AutomationLogger.getInstance();
	protected static Win _instance=null;
	protected String processName,path;
	
	protected Win() {

	}
	
	public static Win getInstance() {
		if(_instance==null) {
			throw new NotInitializedException("Win control is not initialized.");
		}
		
		return _instance;
	}
	
	public static void unregister(IWinObject... objs) {
		if(objs!=null) {
			for(int i=0;i<objs.length;i++) {
				objs[i].unregister();
			}
		}
	}
	
	public static void sleep(int seconds) {
		try {
			Thread.sleep(1000*seconds);
		} catch(Exception e) {
			logger.warn(e.getMessage());
		}
	}

	@Override
	public void waitExists(){
		waitExists(TestApiConstants.SLEEP);
	}
	
	@Override
	public void waitExists(int timeout){
		boolean exists=false;
		exists=exists();
		int count=timeout;
		while(!exists && count>0) {
			count--;
			Browser.sleep(1);
			exists=exists();
		}
		
		if(!exists) {
			throw new ItemNotFoundException("Win application is not found in "+timeout+" seconds.");
		}
	}
	
	@Override
	public Object waitExists(int timeout, Page... pages) {
		String objName = "";
		for (int i = 0; i < pages.length; i++) {
			if(objName.length()>0) {
				objName +=" or ";
			}
			
			objName +=pages[i].getName();
		}
				
		logger.debug("Checking page(s) exist: "+objName);
		Timer timer=new Timer();
		Page foundPage=null;
		while(foundPage==null && timer.diff()<timeout) {
			for(Page p:pages) {
				if(p.exists()) {
					foundPage=p;
					break;
				}
			}
		}
		
		if(foundPage==null) {
			throw new PageNotFoundException("Page(s) "+objName+" not found" );
		}
		
		return foundPage;
	}

	@Override
	public boolean checkWinObjectExists(Property[] properties) {
		IWinObject[] objs=getWinObject(properties);
		boolean exists=objs!=null && objs.length>0;
		unregister(objs);
		return exists;
	}

	@Override
	public boolean checkWinObjectDisplayed(Property[] properties) {
		IWinObject[] objs=getWinObject(properties);
		boolean displayed=objs!=null && objs.length>0 && objs[0].isVisible();
		unregister(objs);
		return displayed;
	}

	@Override
	public IWinObject[] getTextBox(Property[] properties) {
		return getWinObject(Property.addToPropertyArray(properties, ".class", "System.Windows.Forms.TextBox"));
	}

	@Override
	public IWinObject[] getDateTimePicker(Property[] properties) {
		return getWinObject(Property.addToPropertyArray(properties, ".class", "System.Windows.Forms.DateTimePicker"));
	}

	@Override
	public IWinObject[] getDataGrid(Property[] properties) {
		return getWinObject(Property.addToPropertyArray(properties, ".class", "System.Windows.Forms.DataGridView"));
	}
	
	@Override
	public IWinObject[] getComboBox(Property[] properties) {
		return getWinObject(Property.addToPropertyArray(properties, ".class", "System.Windows.Forms.ComboBox"));
	}

	@Override
	public void setTextBox(Property[] properties, String text) {
		setTextBox(properties,0,false,text);
		
	}

	@Override
	public void setTextBox(Property[] properties, int index, String text) {
		setTextBox(properties,index,false,text);
		
	}

	@Override
	public void setTextBox(Property[] properties, int index, boolean forced, String text) {
		IWinObject[] objs=getTextBox(properties);
		
		if(objs.length>index) {
			((IWinTextBox) objs[0]).setText(text);
			unregister(objs);
		} else if(forced) {
			unregister(objs);
			throw new ItemNotFoundException("Failed to find Text box with property: "+Property.propertyArrayToString(properties));
		} 
		
	}

	@Override
	public String getTextBoxValue(Property[] properties) {
		return getTextBoxValue(properties,0,false);
	}

	@Override
	public String getTextBoxValue(Property[] properties, int index) {
		return getTextBoxValue(properties,index,false);
	}

	@Override
	public String getTextBoxValue(Property[] properties, int index,	boolean forced) {
		String text=null;
		IWinObject[] objs=getTextBox(properties);
		
		if(objs.length>index) {
			text=((IWinTextBox) objs[0]).getText();
			unregister(objs);
		} else if(forced) {
			unregister(objs);
			throw new ItemNotFoundException("Failed to find Text box with property: "+Property.propertyArrayToString(properties));
		} 
		
		return text;
	}

	@Override
	public void clickWinObject(Property[] properties) {
		clickWinObject(properties,0,false);		
	}

	@Override
	public void clickWinObject(Property[] properties, int index) {
		clickWinObject(properties,index,false);		
	}

	@Override
	public void clickWinObject(Property[] properties, int index, boolean forced) {
		IWinObject[] objs=getTextBox(properties);
		
		if(objs.length>index) {
			objs[0].click();
			unregister(objs);
		} else if(forced) {
			unregister(objs);
			throw new ItemNotFoundException("Failed to find Text box with property: "+Property.propertyArrayToString(properties));
		} 
	}

	@Override
	public void doubleclickWinObject(Property[] properties) {
		doubleclickWinObject(properties,0,false);		
	}

	@Override
	public void doubleclickWinObject(Property[] properties, int index) {
		doubleclickWinObject(properties,index,false);		
	}

	@Override
	public void doubleclickWinObject(Property[] properties, int index,boolean forced) {
		IWinObject[] objs=getTextBox(properties);
		
		if(objs.length>index) {
			objs[0].doubleClick();
			unregister(objs);
		} else if(forced) {
			unregister(objs);
			throw new ItemNotFoundException("Failed to find Text box with property: "+Property.propertyArrayToString(properties));
		} 
		
	}

	@Override
	public void setDateTimePicker(Property[] properties, String date) {
		setDateTimePicker(properties,0,false,date);
		
	}

	@Override
	public void setDateTimePicker(Property[] properties, int index, String date) {
		setDateTimePicker(properties,index,false,date);		
	}

	@Override
	public void setDateTimePicker(Property[] properties, int index,boolean forced, String date) {
		IWinObject[] objs=getTextBox(properties);
		
		if(objs.length>index) {
			((IWinDateTimePicker) objs[0]).setDateTime(date);
			unregister(objs);
		} else if(forced) {
			unregister(objs);
			throw new ItemNotFoundException("Failed to find Text box with property: "+Property.propertyArrayToString(properties));
		}
		
	}

	@Override
	public String getDateTimePickerValue(Property[] properties) {
		return getDateTimePickerValue(properties,0,false);
	}

	@Override
	public String getDateTimePickerValue(Property[] properties, int index) {
		return getDateTimePickerValue(properties,index,false);
	}

	@Override
	public String getDateTimePickerValue(Property[] properties, int index,boolean forced) {
		String text=null;
		IWinObject[] objs=getTextBox(properties);
		
		if(objs.length>index) {
			text=((IWinDateTimePicker) objs[0]).getDateTime();
			unregister(objs);
		} else if(forced) {
			unregister(objs);
			throw new ItemNotFoundException("Failed to find Text box with property: "+Property.propertyArrayToString(properties));
		} 
		
		return text;
	}
	
	

	@Override
	public void selectDropDownlist(Property[] properties, String item) {
		IWinObject[] objs=getComboBox(properties);
		
		if(objs.length>1) {
		   ((IWinComboBox) objs[0]).select(item);
			unregister(objs);
		} else {
			unregister(objs);
			throw new ItemNotFoundException("Failed to find Combo box with property: "+Property.propertyArrayToString(properties));
		} 
	}
	
	
}
