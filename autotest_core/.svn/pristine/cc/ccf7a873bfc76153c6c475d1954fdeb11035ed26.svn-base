package com.activenetwork.qa.testapi.interfaces.html;


/**
 * Exposes methods that are explicitly associated with a text type control.
 * @author jdu
 *
 */
public interface IText {
	public static final int ONCHANGE=1;
	public static final int ENTERKEY=2;
	public static final int ONKEYDOWN=3;
	public static final int LOSEFOCUS=4;
	public static final int FOCUS=5;
	
	public static enum Event{
		ONCHANGE,ENTERKEY,ONKEYDOWN,LOSEFOCUS,FOCUS
	}
	
	public String getText();
	
	public void setText(String text);
	
	public void setText(int text);
	
	public void setText(double text);
	
	public void clear();
	
	public boolean readOnly();
	
	public void setText(String text, Event... eventCodes);

	String getType();
}
