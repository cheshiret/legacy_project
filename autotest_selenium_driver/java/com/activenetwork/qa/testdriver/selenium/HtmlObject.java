package com.activenetwork.qa.testdriver.selenium;

import java.util.List;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
import com.activenetwork.qa.testapi.util.Timer;
/**
 * Wrap the WebElement object from Selenium framework
 * @author Jdu
 *
 */
class HtmlObject implements IHtmlObject {
	protected Element element;
	protected WebElement web_element;
	protected Properties style;
	protected SeleniumBrowser browser;
	protected String[] handlers;
	
	private boolean useXpath=TestProperty.getBooleanProperty("selenium.xpath", true);
	
	public HtmlObject(Element element, String... handlers) {
		this.element=element;
		this.handlers=handlers;
		this.style=new Properties();
		this.web_element=null;
		this.browser=(SeleniumBrowser)Browser.getInstance();
	}
	
	Element getElement() {
		return element;
	}
	
	String[] getHandler() {
		return handlers;
	}
	
	WebElement getWebElement() {
		if(web_element!=null) {
			try { //detect if the web_element is still valid
				web_element.isEnabled();
			} catch(Exception e) { 
				AutomationLogger.getInstance().warn("Current element is not valid any more. Need to retrieve the web element.");
				web_element=null;
			}
		}
				
		if(web_element==null) {			
			String[] selector=getSelector();
			String xpathSelector=selector[0];
			String cssSelector=selector[1]; //this is the CSS selector for JSoup
			String css_p=selector[2]; //this is the pure CSS selector
			String short_xpathSelector=selector[3];
			String short_cssSelector=selector[4];
			String short_css_p=selector[5];
			
			WebDriver driver=browser.getWebDriver();
			if(handlers[0].startsWith("frame:")) {
				browser.switchTo(browser.handler);
				browser.switchTo(handlers);
				
			} else {
				browser.switchTo(handlers);
			}
			
			try{
				boolean shorter=false;
				List<WebElement> es=driver.findElements(useXpath?By.xpath(xpathSelector):By.cssSelector(css_p));
				
				if(es.size()<1) {
					AutomationLogger.getInstance().warn("Found nothing using xpath="+xpathSelector+". Try using \""+short_xpathSelector+"\"");
					es=driver.findElements(useXpath?By.xpath(short_xpathSelector):By.cssSelector(short_css_p));//(By.xpath(short_xpathSelector));
					shorter=true;
					if(es.size()<1 && xpathSelector.contains("/IMG")) {
						//Vivian[20131211]: workaround for <image> tag, copy from functest3_selenium
						AutomationLogger.getInstance().warn("Still found nothing using short selector. Try using IMAGE tag");
						if(useXpath) {
							String xpath_image=xpathSelector.replaceAll("/IMG", "/IMAGE");
							es=driver.findElements(By.xpath(xpath_image));
						} else {
							String css_p_image=xpathSelector.replaceAll(" IMG ", " IMAGE ");
							es=driver.findElements(By.cssSelector(css_p_image));
						}
					}
				}
				
				int index=0;
				if(es.size()>1) {
					Elements jes=Jsoup.parse(driver.getPageSource()).select(shorter?short_cssSelector:cssSelector);
					if(jes.size()!=es.size()) {
						throw new RuntimeException("Jsoup size "+jes.size()+" is not same as WebDriver size "+es.size());
					} else {
						index=getIndex(jes);
					}
				} else if(es.size()<1) {
					throw new RuntimeException("Found nothing using xpath="+xpathSelector+" or "+short_xpathSelector);
				}
				web_element=new ConsistentWebElement( es.get(index) );
				scrollIntoView(web_element);	
			} catch(Exception e) {
				e.printStackTrace();
				throw new ActionFailedException(e);
			}
		}
		return web_element;
	}
	
	protected void scrollIntoView(WebElement e) {	
		String script=" try {window.focus(); var height=document.documentElement.clientHeight; var width=document.documentElement.clientWidth; var e=arguments[0]; var rects = e.getClientRects(); " +
				" var on_top = function(r) {var x = (r.left + r.right)/2, y = (r.top + r.bottom)/2; return document.elementFromPoint(x, y) === e;}; "+
		 		" var in_viewport = function() {for ( var i = 0, l = rects.length; i < l; i++) { var r = rects[i]; var in_view = r.top >0 && r.left >0 && r.bottom < height && r.right < width && on_top(r); if (!in_view) return false; } return true;}; " +
		 		" if( !in_viewport() ) { e.scrollIntoView(true); } return 'successful';} catch(err) {return err.message;}";
		
		Object result=browser.executeJavascript(script,e);
		if(!((String)result).equalsIgnoreCase("successful")) {
			AutomationLogger.getInstance().warn("Executing javascript error: "+result.toString()+" for '"+script+"'");
		}
		
	}
	
	protected boolean elementIsInView(WebElement e) {
		String script="var height=document.documentElement.clientHeight; var width=document.documentElement.clientWidth; var e=arguments[0]; var rects = e.getClientRects(); " +
		" var on_top = function(r) {var x = (r.left + r.right)/2, y = (r.top + r.bottom)/2; return document.elementFromPoint(x, y) === e;}; "+
 		" var in_viewport = function() {for ( var i = 0, l = rects.length; i < l; i++) { var r = rects[i]; var in_view = r.top >=0 && r.left >=0 && r.bottom <= height && r.right <= width && on_top(r); if (!in_view) return false; } return true;}; " +
 		" return in_viewport();";
		
		Object in_viewport=browser.executeJavascript(script,e);
		return ((Boolean) in_viewport).booleanValue();
	}
	
	private int getIndex(Elements es) {
		int index=0;
		int count =0;
		boolean[] isSame=new boolean[es.size()];
		for(int i=0;i<es.size();i++){
			isSame[i]=true;
		}
		Element pointer=element;
		while(es.size()-count!=1) {
			
			if(pointer.tagName().equalsIgnoreCase("body")) {
				break;
			}
			for(int i=0;i<es.size();i++) {
				if(isSame[i]) {
					Element aElement=es.get(i);
					es.set(i, aElement.parent());
					if(pointer.siblingIndex()!=aElement.siblingIndex()) {
						isSame[i]=false;
						count++;
					}
				}
			}
			pointer=pointer.parent();
		}
		
		if(es.size()-count==1) {
			for(int i=0;i<es.size();i++){
				if(isSame[i]==true) {
					index=i;
					break;
				}
			}
		} else {
			throw new RuntimeException("Ambiguious objs found.");
		}
		
		return index;
	}
	
	private String[] getSelector() {
		Element pointer=element;
		String tag=element.tagName().toUpperCase();
		String[] attr=getAttrComponent(element);
		String long_xpath="/"+tag+attr[0];
		String long_css= tag+attr[1];
		String short_xpath="//"+tag+attr[0];
		String short_css=tag+attr[1];
		
		String long_css_p=tag.toLowerCase()+attr[2];
		String short_css_p=tag.toLowerCase()+attr[2];
		while((pointer=pointer.parent())!=null ) {
			tag=pointer.tagName().toUpperCase();
//			System.out.println(tag+"- isblock="+pointer.tag().isBlock()+" isData="+pointer.tag().isData()+" isInline"+pointer.tag().isInline()+" isKnown="+pointer.tag().isKnownTag());
			if(tag.equalsIgnoreCase("#root")) {
				break;
			} else {
				long_xpath="/"+tag+long_xpath;
				
				if(tag.contains(":")) {
					tag=tag.replaceAll(":", "|");
				}
				long_css=tag+" > "+long_css;
				long_css_p=tag.toLowerCase()+" > "+long_css_p;
			}
		}
		
//		RALogger.getInstance().debug("XPath="+xpath);
	
		return new String[]{long_xpath,long_css,long_css_p,short_xpath,short_css,short_css_p};
		
	}
	
	private String[] getAttrComponent(Element e) {
		String xpath="";
		String css="";
		String css_p="";
		Attributes attr=element.attributes();
		String value="";
		if(attr.hasKey("name") && !StringUtil.isEmpty(value=attr.get("name"))){
			xpath="[string(@name)="+processTextValue(value)+"]";
			css="[name="+value+"]";
			css_p="[name='"+value+"']";
		} else if(attr.hasKey("id") && !StringUtil.isEmpty(value=attr.get("id"))) {
//			xpath="[@id="+processTextValue(value)+"]";
			xpath="[string(@id)="+processTextValue(value)+"]";
			css="[id="+value+"]";
			css_p="[id='"+value+"']";
		} else if(attr.hasKey("href") && !StringUtil.isEmpty(value=attr.get("href")) && attr.get("href").length()>1 ) {
			xpath="[@href="+processTextValue(value)+"]";
			css="[href="+value+"]";
			css_p="[href='"+value.replaceAll("'", "\\\\'")+"']";
		} else if(attr.hasKey("src") && !StringUtil.isEmpty(value=attr.get("src"))) {
			xpath="[@src="+processTextValue(value)+"]";
			css="[src="+value+"]";
			css_p="[src='"+value.replaceAll("'", "\\\\'")+"']";
		} 
//		else { //Comment out text attribute due to it is not stable and not available for CSS selector
//			//using text attribute is not always stable
//			
//			String innerHtml=element.html();
//			String css_text=element.text();
//			
//			String xpath_text=css_text;
//			
//			if(innerHtml.matches(".*<br\\s?/?>.*")) {
//				//<br/> will be treated as a space in jsoup, but in IE9, nothing
//				xpath_text=innerHtml.replaceAll("<[^<>]+>", "").replaceAll("&nbsp;", " ");
//			}
//			
//			if(!StringUtil.isEmpty(css_text) && css_text.length()<50) {
//				xpath_text=StringUtil.normalize_space(xpath_text);
//				css_text=StringUtil.normalize_space(css_text);
//				if(css_text.length()>0) {
//					xpath="[normalize-space()="+processTextValue(xpath_text)+"]";
//					css=":matches("+StringUtil.convertToRegex(css_text)+")";
//				}
//			} 
			
//		}
		
		return new String[]{xpath,css,css_p};
	}
	
	private String processTextValue(String text) {
		String newText;
		if(text.contains("'"))
			newText=generateConcatForXPath(text);
		else 
			newText="'"+text+"'";
		
		return newText;
	}
	
	private String generateConcatForXPath(String text) {
		char[] chars=text.toCharArray();
		
		StringBuffer b=new StringBuffer();
		
		b.append("concat(");
		char previous;
		for(int i=0;i<chars.length;i++) {
			if(i==0) {
				previous='(';
			} else {
				previous=chars[i-1];
			}
						
			if(chars[i]=='\'') {
				if(i==0) {
					b.append("\"");
					
				} else if(previous!='\'') {
					b.append("',\"");
				}
				b.append("'");
			}else {
				if(i==0){
					b.append("'");
				} else if(previous=='\''){
					b.append("\",'");
				}
				b.append(chars[i]);
			}
			
			if(i>=chars.length-1) {
				if(chars[i]=='\'') {
					b.append("\"");
				} else {
					b.append("'");
				}
			}
			
			
		}
		b.append(")");
		return b.toString();
	}
	
	@Override
	public String id() {
		return element.id();
	}
	
	@Override
	public String name() {
		return element.attr("name");
	}
	
	@Override
	public String title() {
		return element.attr("title");
	}
	
	@Override
	public String className() {
		return element.attr("class");
	}
	
	@Override
	public String type() {
		return element.attr("type");
	}
	

	@Override
	public String getAttributeValue(String name) {
		String attrName = trimDot(name);
		if (attrName.equalsIgnoreCase("disabled")) {
			attrName = "isDisabled";
		}
		if (element.attributes().hasKey(attrName)) {
			return element.attr(attrName);
		} else {
			return getWebElement().getAttribute(attrName);
		}
	}
	
	private String trimDot(String name) {
		if(name.matches("\\..+")) {
			return name.substring(1);
		} else {
			return name;
		}
	}
	
	@Override
	public void click() {
		WebElement e=getWebElement();
		browser.timer.reset();
		while(!e.isDisplayed()) {
			//in certain conditions, the object is showing however Selenium detected the element is not displayed
			AutomationLogger.getInstance().debug("Somehow Selenium detected element is not displayed. Do javascript click");
			browser.executeJavascriptInThread("arguments[0].click();", e);
			return;
		}
		
		try {
			
			if(element.tagName().equalsIgnoreCase("A") ) {
				int h=Integer.parseInt(e.getAttribute("offsetHeight"));
				int w=Integer.parseInt(e.getAttribute("offsetWidth"));
				
				if( mayNeedModifiedClick(e) && h>15) {
					h=10;
					String align=e.getCssValue("text-align");
					
					if(align.equalsIgnoreCase("left")) {
						w=w>10?5:w/2;
					} else if(align.equalsIgnoreCase("center")) {
						w=w/2;
					} else {
						w=w>10?w-5:w/2;
					}
					
					AutomationLogger.getInstance().debug("Do modified click at ("+w+","+h+") for "+getWebElement().getAttribute("outerHTML"));
					Action click=new Actions(browser.getWebDriver()).moveToElement(e,w,h).click().build();
					click.perform();
				} else {
					e.click();
				}
			}else if(element.tagName().equalsIgnoreCase("input") && element.attr("type").equalsIgnoreCase("file")) {
				AutomationLogger.getInstance().debug("Do javascript click");
				browser.executeJavascriptInThread("arguments[0].click();", e);
			}else{
				e.click();
			}

		} catch(org.openqa.selenium.ElementNotVisibleException ex) {
			AutomationLogger.getInstance().warn("Click failed due to ElementNotVisibleException. Try javascript click.");
			browser.executeJavascript("arguments[0].click();", e);
		}
	}
	
	private boolean mayNeedModifiedClick(WebElement e) {
		String img=e.getCssValue("background-image");
		if(img.contains("btn_booknow.gif")) {
			return false;
		} 
			
		return true;
		
	}
	
	@Override
	public boolean isEnabled() {
		try {
			return isVisible() && getWebElement().isEnabled();
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean isVisible() {
		int h=Integer.parseInt(getWebElement().getAttribute("offsetHeight"));
		int w=Integer.parseInt(getWebElement().getAttribute("offsetWidth"));
		
		if( h!=0 && w!=0) {//loose the criteria to work around the elementIsInView issue
			return true; //elementIsInView(getWebElement());
		} else {
			return false;
		}
	}
	
	@Override
	public boolean exists() {
		try {
			return getWebElement() !=null && isVisible();
		} catch (Exception e) {
			return false;
		}
	}

	String html() {
		try {
			return element.outerHtml();
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public void doubleClick() {
		browser.timer.reset();
		new FireEvent(getWebElement(),"ondblclick").start();
		javascriptSync();
		
		
		
	}
		
	@Override
	public String text() {
		return StringUtil.convertSpaceUnicode2ASCII(element.text()).trim();
	}
	
	@Override
	public void unregister() {
		element=null;
		web_element=null;
		browser=null;
		handlers=null;
	}

	@Override
	public String getProperty(String name) {
		String trimedName=trimDot(name);
		if(trimedName.equalsIgnoreCase("text")){
			return text();
		}else if(trimedName.equalsIgnoreCase("id")) {
			return id();
		}else if(trimedName.equalsIgnoreCase("name")) {
			return name();
		}else if(trimedName.equalsIgnoreCase("type")) {
			return type();
		} else if(trimedName.equalsIgnoreCase("title")) {
			return title();
		} else if(trimedName.equalsIgnoreCase("className")) {
			return className();
		} else if(trimedName.equalsIgnoreCase("classIndex")) {
			throw new ItemNotFoundException("Property \"classIndex\" is not supported");
		} else {
			return getAttributeValue(name);
		}
	}
	
	@Override
	public IHtmlObject[] getChildren() {
		try {
			Elements es=element.children();
			int size=es.size();
			IHtmlObject[] children=new IHtmlObject[size];
			
			for(int i=0;i<size;i++) {
				children[i]=new HtmlObject(es.get(i),handlers);
			}
			
			return children;
			
		} catch (Exception e) {}
		return null;
	}

	public String style(String name) {
		if(style.size()<1) {
			String styleStr=(String) element.attr("style");
			if(styleStr!=null && styleStr.length()>0) {
				String[] tokens=styleStr.split(";");
				for(String token: tokens) {
					int index=token.indexOf(":");
					String styleName=token.substring(0,index).trim().toUpperCase();
					String styleValue=token.substring(index+1).trim();
					style.put(styleName, styleValue);
				}
			}
		
		} 
		return style.getProperty(name.toUpperCase(), "").trim();
	}

	protected void focus() {
		browser.executeJavascript("try {window.focus();} catch(err) {return err.message;}", getWebElement());
	}
	
	protected void javascriptSync() {
		Timer timer=new Timer();
		int timeout=300;
		Timer.sleep(100);
		while(timer.diffLong()<timeout && browser.isLocked()) {
			Timer.sleep(10);
		}
	}
	
	class FireEvent extends Thread {
		WebElement element;
		String event;
	
		public FireEvent(WebElement element,String event) {
			this.element=element;
			this.event=event;
		}
		
		public void run() {
			try {
				WebDriver driver=browser.getWebDriver();
				browser.lockDriver();
				((JavascriptExecutor)driver).executeScript("arguments[0].fireEvent('"+event+"');", element);
				
			}catch(Exception e) {
				throw new ActionFailedException("FireEvent '"+event+"' failed: "+e.getMessage());
			} finally {
				((SimpleBrowser)Browser.getInstance()).unlockDriver();
			}
		}
	}

	@Override
	public IHtmlObject getParent() {
		Element parent=element.parent();
		return new HtmlObject(parent,handlers);
	}

	@Override
	public String tag() {
		return element.tagName();
	}

	@Override
	public void hover() {
		WebElement we=this.getWebElement();
		int h=Integer.parseInt(we.getAttribute("offsetHeight"));
		int w=Integer.parseInt(we.getAttribute("offsetWidth"));

		Action hover=new Actions(browser.getWebDriver()).moveToElement(we,w/2,h/2).build();
		hover.perform();
		
	}
	
	
}
