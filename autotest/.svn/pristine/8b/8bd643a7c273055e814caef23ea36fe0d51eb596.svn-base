package com.activenetwork.qa.awo.pages.component;

import com.activenetwork.qa.awo.pages.AwoAjax;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.browser.IBrowser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.page.Ajax;
import com.activenetwork.qa.testapi.page.Page;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Jun 28, 2012
 */
public class PagingComponent{
	protected Ajax ajax=AwoAjax.getInstance();
	IBrowser browser = Browser.getInstance();
	private Page page;
	
	public PagingComponent(){
		
	}
	
	public PagingComponent(Page pg){
		this.page=pg;
	}

	private boolean has(String buttonName){
		return has(buttonName, false);
	}
	
	private IHtmlObject[] getPagingBarTopObject() {
		IHtmlObject objs[] = browser.getTableTestObject(".className", "pagingbar");
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Paging Bar top object.");
		
		return objs;
	}
	
	private boolean has(String buttonName, boolean isTop) {
		IHtmlObject top[] = null;
		if(isTop) {
			top = getPagingBarTopObject();
		}
		boolean exists =true;
		if(top != null && top.length>0){
			exists = browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression("(\\s+)?" +buttonName, false), (isTop ? top[0] : null));
		}else{
			exists = browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression("(\\s+)?" + buttonName, false));
		}
		
		if(isTop) {
			Browser.unregister(top);
		}
		
		return exists;
	}
	
	private void clickPagingButton(String buttonName){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("(\\s+)?" + buttonName, false), true, 0, getPagingBarTopObject()[0]);//Quentin[20131209]
	}
	
	public boolean nextExists() {
		return this.nextExists(true);
	}
	
	public boolean nextExists(boolean isTop) {
		return this.has("Next", isTop);
	}
	
	
	public boolean clickNext(){
		boolean result = nextExists(true);
		if(result) {
			this.clickPagingButton("Next");
			ajax.waitLoading();
			if(page!=null){
				page.waitLoading();
			}
		}
		return result;
	}
	
	public boolean firstExists(){
		return this.has("First");
	}
	
	public boolean clickFirst(){
		boolean result = firstExists();
		if(result){
			this.clickPagingButton("First");
			ajax.waitLoading();
			if(page!=null){
				page.waitLoading();
			}
		}
		return result;
	}
	
	public boolean previousExists(){
		return this.has("Previous");
	}
	
	public void clickPrevious(){
		this.clickPagingButton("Previous");
	}

	public boolean lastExists(){
		return this.has("Last");
	}
	
	public boolean lastExists(boolean isTop) {
		return this.has("Last", isTop);
	}
	
	public void clickLast(){
		this.clickPagingButton("Last");
	}
}
