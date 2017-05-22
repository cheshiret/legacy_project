package com.activenetwork.qa.awo.pages.orms.inventoryManager.list;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.ListInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.ListInfo.ListSubmissionRules;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class InvMgrListDetailPage extends InvMgrListDetailCommonPage{
	private static InvMgrListDetailPage _instance = null;
	protected InvMgrListDetailPage (){}
	
	public static InvMgrListDetailPage getInstance(){
		if(null == _instance){
			_instance = new InvMgrListDetailPage();
		}
		
		return _instance;
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(".class", "Html.TD",".text","List Information");
	}
	
	public void setListName(String listName){
		browser.setTextField(".id", 
				new RegularExpression("SlipWaitingListProductView-\\d+\\.name",false), listName);
	}
	
	public void selectNumOfChoice(String numOfChoice){
		browser.selectDropdownList(".id", 
				new RegularExpression("SlipWaitingListProductView-\\d+\\.numOfChoices",false), numOfChoice);
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickApply(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public void clickAddNew(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New");
	}
	
	public boolean checkErrorMessageIsExisting(){
		return browser.checkHtmlObjectExists(".id", "NOTSET",".className","message msgerror");
	}
	
	public String getErrorMessage(){
		return browser.getObjectText(".id", "NOTSET",".className","message msgerror");
	}
	
	public void setListInfo(ListInfo list){
		if(StringUtil.notEmpty(list.getListName())){
			this.setListName(list.getListName());
		}
		
		if(StringUtil.notEmpty(list.getNumOfChoice())){
			this.selectNumOfChoice(list.getNumOfChoice());
		}
		
		// add list submission rules
		if(null != list.getListSubrules()){
			this.setSubmissionRule(list.getListSubrules());
		}
		
		if(null != list.getHeaderMessage()){
			this.setHeaderMsg(list.getHeaderMessage());
		}
		
		if(null != list.getListTermCon()){
			this.setListTermCon(list.getListTermCon());
		}
	}
	
	/**
	 * Add list submission rules
	 */
	public void clickAdd(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add");
	}
	
	/**
	 * Remove list submission rules
	 */
	public void clickRemove(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove");
	}
	
	String prefix1 = "LotteryRuleView-\\d+\\.";
	public void selectRuleName(String name){
		if(StringUtil.EMPTY.endsWith(name)){
			browser.selectDropdownList(".id", new RegularExpression(prefix1+"lotteryRuleType", false), 0);
		} else {
			browser.selectDropdownList(".id", new RegularExpression(prefix1+"lotteryRuleType", false), name);
		}
	}
	
	public void setMaxNumber(String maxNum){
		browser.setTextField(".id", new RegularExpression(prefix1+"maxValue:ZERO_TO_NULL", false), maxNum);
	}
	
	private String prefix2 = "SlipWaitingListProductView-\\d+\\.";
	public void setHeaderMsg(String headerMsg){
		browser.setTextArea(".id", new RegularExpression(prefix2+"headerMessage", false), headerMsg);
	}
	
	public String getHeaderMsg(){
		return browser.getTextAreaValue(".id", new RegularExpression(prefix2+"headerMessage", false));
	}
	
	public void setListTermCon(String text){
		browser.setTextArea(".id", new RegularExpression(prefix2+"termConditions", false), text);
	}
	
	public String getListTermCon(){
		return browser.getTextAreaValue(".id", new RegularExpression(prefix2+"termConditions", false));
	}
	
	public void setSubmissionRule(List<ListSubmissionRules> rules){
		ListSubmissionRules rule = new ListSubmissionRules();
		for(int i=0; i< rules.size(); i++){
			this.clickAdd();
			ajax.waitLoading();
			this.waitLoading();
			
			rule = rules.get(i);
			this.selectRuleName(rule.getRule());
			ajax.waitLoading();
			this.waitLoading();
			this.setMaxNumber(rule.getMaxNum());
		}
	}
	
	public String getNumOfChoice(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefix2+"numOfChoices", false));
	}

	public List<String> getNumOfChoiceList(){
		return browser.getDropdownElements(".id", new RegularExpression(prefix2+"numOfChoices", false));
	}
	
	public boolean isListSubmissionRuleExit(){
		return browser.checkHtmlObjectExists(".id", new RegularExpression(prefix1+"lotteryRuleType", false));
	}
	
	public String getListSubRuleName(){
		if(this.isListSubmissionRuleExit()){
			return browser.getObjectText(".id", new RegularExpression(prefix1+"lotteryRuleType", false));
		} else {
			return null;
		}
	}
	
	public String getListSubRuleMaxNum(){
		if(this.isListSubmissionRuleExit()){
			return browser.getTextFieldValue(".id", new RegularExpression(prefix1+"maxValue:ZERO_TO_NULL", false));
		} else {
			return null;
		}
	}
	
	public List<ListSubmissionRules> getListSubRuleInfo(){
		List<ListSubmissionRules> rules = new ArrayList<ListSubmissionRules>();
		ListSubmissionRules ruleInfo = new ListSubmissionRules();
		ruleInfo.setRule(this.getListSubRuleName());
		ruleInfo.setMaxNum(this.getListSubRuleMaxNum());
		rules.add(ruleInfo);// only one rule exist now.
		return rules;
	}

	public String getListNameInListDetails(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix2+"name", false));
	}
	
	public ListInfo getListDetailInfo(){
		ListInfo list = new ListInfo();
		list.setListID(this.getListID());
		list.setListName(this.getListNameInListDetails());
		list.setNumOfChoice(this.getNumOfChoice());
		list.setListSubrules(this.getListSubRuleInfo());
		list.setHeaderMessage(this.getHeaderMsg());
		list.setListTermCon(this.getListTermCon());
		return list;
	}

	public void verifyListDetaliInfo(ListInfo expectList){
		boolean result = true;
		
		ListInfo actualList = this. getListDetailInfo();
		result &= MiscFunctions.compareResult("List ID", expectList.getListID(), actualList.getListID());
		result &= MiscFunctions.compareResult("List Name", expectList.getListName(), actualList.getListName());
		result &= MiscFunctions.compareResult("Header Message", expectList.getHeaderMessage(), actualList.getHeaderMessage());
		result &= MiscFunctions.compareResult("List Term and Conditions", expectList.getListTermCon(), actualList.getListTermCon());
		
		if(MiscFunctions.compareResult("Size of List Submission Rules", expectList.getListSubrules().size(), actualList.getListSubrules().size())){
			for(int i=0; i<actualList.getListSubrules().size(); i++){
				ListSubmissionRules expect = expectList.getListSubrules().get(i);
				ListSubmissionRules actual = actualList.getListSubrules().get(i);
				
				logger.info("No. "+(i+1)+" List Submission Rules.");
				result &= MiscFunctions.compareResult("Rule Name", expect.getRule(), actual.getRule());
				result &= MiscFunctions.compareResult("Max Number", expect.getMaxNum(), actual.getMaxNum());
			}
		} else {
			result &= false;
		}
		
		if(!result){
			throw new ErrorOnPageException("---Check logs above!");
		}
	}
	
	public boolean isListParticipationEnable(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "List Participation");
	}
	
	public boolean isOKEnable(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "OK");
	}

	public boolean isAddEnable(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Add");
	}

	public boolean isRemoveEnable(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Remove");
	}
	
	public List<String> getPreferenceAttr(String colName){
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("preference_attributes", false));
		if(null == objs || objs.length < 1){
			throw new ItemNotFoundException("Can't find Preference Attribute table.");
		}

		IHtmlTable table = (IHtmlTable) objs[0];
		int col = table.findColumn(0, colName);		
		if(col < 0){
			throw new ItemNotFoundException("Can't find column by given name "+colName);
		}
		List<String> colList = new ArrayList<String>();
		colList = table.getColumnValues(col);
		colList.remove(0);
		
		List<String> returnList = new ArrayList<String>();
		for(String colvalue: colList){
			if(!StringUtil.EMPTY.equals(colvalue.trim())){
				returnList.add(colvalue);
			}
		}
		return returnList;
	}
	
	public String getListNameTextField(){
		return browser.getTextFieldValue(".id", new RegularExpression("SlipWaitingListProductView-\\d+.name", false)).trim();
	}
}
