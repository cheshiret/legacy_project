/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.web.recgov.RecgovCommonPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author asun
 * @Date  Oct 25, 2011
 */
public class UwpUnifiedSearchHelpInfoPage extends RecgovCommonPage {
   
	private static UwpUnifiedSearchHelpInfoPage instance=null;
	
	private UwpUnifiedSearchHelpInfoPage(){}
	
	public static UwpUnifiedSearchHelpInfoPage getInstance(){
		if(instance==null){
			instance=new UwpUnifiedSearchHelpInfoPage();
		}
		return instance;
	}
	
	public boolean exists(){
		Property[] properties=new Property[]{new Property(".class", "Html.DIV"),new Property(".className","inpagehelp"),new Property(".id", "dpdiv")};
		return browser.checkHtmlObjectExists(properties);
	}
	
	public String getErrorMes(){
		IHtmlObject[] objs = browser.getHtmlObject(".className", "msg error");
		String errorMes = objs[0].text().replaceAll("\n|\r", " ");
		
		Browser.unregister(objs);
		return errorMes;
	}
	
	public void verifyErrorMsgExist(boolean exist){
		boolean actualErrorMes = false;
		IHtmlObject[] objs = browser.getHtmlObject(".className", "msg error");
		if(!objs[0].style("display").equals("none")){
			actualErrorMes = true;
		}
		
		if(exist){
			if(!actualErrorMes){
				throw new ErrorOnDataException("Error message should exist!");
			}
		}else if(actualErrorMes){
			throw new ErrorOnDataException("Error message should not exist!");
		}
		
		Browser.unregister(objs);
	}
	
	public boolean checkInstructionMessageExisting(){
		Property[] p=new Property[]{new Property(".class","Html.DIV"),
				new Property(".id","dpdiv"),
				new Property(".className","inpagehelp"),
		        new Property(".text",new RegularExpression("^Find Parks, Forests, Campgrounds and more.*water sports and more\\.$",false))
		};
		return browser.checkHtmlObjectExists(p);
	}
	
	/**
	 * Get instruction message 
	 * @return
	 */
	public String getInstructionMessage(){
		String instructionMes = "";
		Property[] p=new Property[]{new Property(".class","Html.DIV"),
				new Property(".id","dpdiv"),
				new Property(".className","inpagehelp"),
				new Property(".text",new RegularExpression("^Find Parks, Forests, Campgrounds and more.*water sports and more\\.$",false))
		};
		IHtmlObject[] objs = browser.getHtmlObject(p);
		if(objs.length<1){
			throw new ObjectNotFoundException("Instruction message object can't be found.");
		}else{
			instructionMes = objs[0].text();
		}
		return instructionMes;
	}
	
	public void verifyErrorMes(String errorMes){
		String currentMsg = this.getErrorMes();
		if(!currentMsg.equals(errorMes)){
			throw new ErrorOnDataException("Error message is wrong!", errorMes, currentMsg);
		}else{
			logger.info("verify error message on unified help page successfully");
		}
	}
	
	/**
	 * Get search help content
	 * @return
	 */
	public String getSearchHelpContent(){
		String content = "";
		IHtmlObject[] contentObjs = browser.getHtmlObject(".id", "usearch_info");

		if(contentObjs.length<1){
			throw new ObjectNotFoundException("Content Object can't be found.");
		}else {
			content = contentObjs[0].getProperty(".text");
		}

		Browser.unregister(contentObjs);
		return content;
	}
	
	/**
	 * Verify instruction message
	 * @param expectedValue
	 */
	public void verifySearchHelpContent(String expectedValue){
		String actualValue = this.getSearchHelpContent();
		if(!actualValue.equals(expectedValue)){
			throw new ErrorOnDataException("Search Help Content is incorrect.", expectedValue, actualValue);
		}else{
			logger.info("Successfully verify search help content: "+expectedValue);
		}
	}
	
	/**
	 * Is result filters displaying
	 * @return
	 */
	public boolean isResultFiltersDisplaying(){
		return browser.checkHtmlObjectExists(".className", "resultsFilters", ".id", "resultsFilters");
	}
	
	/**
	 * Verify no result filter display in Suggestion page
	 */
	public void verifyNoResultFilter(){
		if(this.isResultFiltersDisplaying()){
			throw new ErrorOnPageException("Result filters should not display in Suggestion page.");
		}
		logger.info("Successfully verify Result filters doesn't display in Suggestion page.");
	}
}
