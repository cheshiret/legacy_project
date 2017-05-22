package com.activenetwork.qa.testapi.interfaces.html;

public interface IScrollable {
	public void hScrollTo(int position);
	
	public void vScrollTo(int position);
	
	public void scrollLineDown();
	
	public void scrollLineLeft();
	
	public void scrollLineRight();
	
	public void scrollLineUp();
	
	public void scrollPageLeft();
	
	public void scrollPageRight();
	
	public void scrollPageDown();
	
	public void scrollPageUp();
}
