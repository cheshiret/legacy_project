/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.xmloutput;

import java.util.List;
import java.util.Map;

import com.activenetwork.qa.testapi.page.HtmlMainPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.WebXMLFeedParser;

/**
 * @Description: It is for Web XML Output page.
 * The page can be accessed by the URL with the parameter xml=true, such as:
 * http://[host URL]/campgroundDetails.do?contractCode=NRSO&parkId=70984&xml=true
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Oct 17, 2012
 */
public abstract class WebXMLOutputPage extends HtmlMainPage {
	private static WebXMLFeedParser xmlFeed = null;
	protected static String loadedUrl = "";
	protected Property[] pageProperty;
	protected Property[] pageProperty2;
	
	protected WebXMLOutputPage(String url) {
		pageProperty=new Property[2];
		pageProperty[0]=new Property(".class","Html.DIV");
		pageProperty[1]=new Property(".text",new RegularExpression(
				"<\\?xml version=\"1.0\" encoding=\"UTF-8\"( standalone=\"no\"\\s+)?\\?>",false));
		if (!loadedUrl.equals(url)) {
			xmlFeed = new WebXMLFeedParser(url);
			loadedUrl = url;
		}
	}
	
	protected WebXMLOutputPage(String specificTag, String url) {
		pageProperty=new Property[2];
		pageProperty[0]=new Property(".class","Html.DIV");
		pageProperty[1]=new Property(".text",new RegularExpression(
				"<\\?xml version=\"1.0\" encoding=\"UTF-8\"( standalone=\"no\"\\s+)?\\?>",false));
		
		pageProperty2=new Property[2];
		pageProperty2[0]=new Property(".class","Html.SPAN");
//		pageProperty2[1]=new Property(".classname","t");
//		pageProperty2[2]=new Property(".text", specificTag);
		pageProperty2[1]=new Property(".text", specificTag); //Lesley[20140221] page mark changed
		if (!loadedUrl.equals(url)) {
			reloadXML(url);
		}
	}
	
	@Override
	public boolean exists() {
		if (pageProperty2 == null || pageProperty2.length < 1) {
			return browser.checkHtmlObjectExists(pageProperty);
		} else {
			return browser.checkHtmlObjectExists(pageProperty) && browser.checkHtmlObjectExists(pageProperty2);
		}
	}

	public static void reloadXML(String xmlUrl) {
		xmlFeed = new WebXMLFeedParser(xmlUrl);
		loadedUrl = xmlUrl;
	}
	/**
	 * Get the value of the given tag's parameter 
	 * @param tagName
	 * @param parameter
	 * @return
	 */
	protected String getElementParameterValue(String tagName, String parameter) {
		return xmlFeed.getXMLOutputParameterValue(tagName, parameter);
	}
	
	/**
	 * Get the value of  the given tag's parameter with the given index value
	 * @param tagName
	 * @param parameter
	 * @param index
	 * @return
	 */
	protected String getElementParameterValue(String tagName, String parameter, int index) {
		return xmlFeed.getXMLOutputParameterValues(tagName, parameter).get(index);
	}
	
	/**
	 * Get values of the all tags' parameters
	 * @param tagName
	 * @param parameter
	 * @return
	 */
	protected List<String> getElementsParameterValues(String tagName, String... parameters) {
		return xmlFeed.getXMLOutputParameterValues(tagName, parameters);
	}
	
	/**
	 * Get values of all tags' two parameters
	 * @param tagName
	 * @param para1
	 * @param para2
	 * @return
	 */
	protected Map<String, String> getElementsParametersValues(String tagName, String para1, String para2) {
		return xmlFeed.getXMLOutputParameterValues(tagName, para1, para2);
	}
	
	/**
	 * Check if the tag exist
	 * @param tag
	 * @return
	 */
	protected boolean isElementExist(String tag) {
		return xmlFeed.isXMLOutputParameterExist(tag);
	}
	
	/**
	 * Check if tag has parameters
	 * @param tag
	 * @return
	 */
	protected boolean isElementSubComponentsExist(String tag) {
		return xmlFeed.isXMLOutputParameterValueExist(tag);
	}
}
