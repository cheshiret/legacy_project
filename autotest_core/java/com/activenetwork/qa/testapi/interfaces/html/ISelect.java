package com.activenetwork.qa.testapi.interfaces.html;

import java.util.List;

import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * Support Html objects with multiple options, such as dropdown list
 * @author jdu
 *
 */
public interface ISelect extends IHtmlObject {
	public String getSelectedText();
	
	public List<String> getSelectedTexts();
	
	public void select(int index);
	
	public void select(String item);
	
	public void select(RegularExpression itemPattern);
	
	public List<String> getAllOptions();
	
	public boolean isMultiple();
	
	public void select(String[] items);

//	List<String> getAjaxSelectedTexts();
//
//	String getAjaxSelectedText();
	
//	public boolean isSelected(String item);
//	
//	public boolean isSelected(int index);
//	
//	public boolean isSelected(RegularExpression itemPattern);
}
