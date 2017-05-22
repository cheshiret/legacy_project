package com.activenetwork.qa.awo.pages.orms.resourceManager;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.ReportData;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.page.HtmlPopupPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class ResMgrJreportPopupPage  extends HtmlPopupPage {

	protected ResMgrJreportPopupPage() {
		super("title", new RegularExpression("JReport Web- Powered by JReport.*",false));
	}
	
	private static ResMgrJreportPopupPage instance = null;
	
	public static ResMgrJreportPopupPage getInstance(){
		if(null==instance){
			instance = new ResMgrJreportPopupPage();
		}
		return instance;
	}
	
	public void setReportTitle(String reportTitle){
		popup.setTextField(".id", "rptTitle", reportTitle);
	}

	public void selectReportLayout(String altMsg){
		popup.clickGuiObject(".class","Html.IMG",".alt",altMsg);
	}
	
	public void selectDataResource(String resourceName){
		waitDataSourceLoading();
		popup.clickGuiObject(".class","Html.LABEL",".text",resourceName);
	}
	
	public void waitDataSourceLoading(){
		boolean exists = false;
		int i = 0;
		while(!exists){
			Browser.sleep(1);
			exists = popup.checkHtmlObjectExists(".class","Html.LABEL",".text","Data Source 1");
			i++;
			if(i>60){
				throw new ItemNotFoundException("Failed to Load Resource in "+i+" seconds.");
			}
		}
	}
	public void chooseReportColumn(Hashtable<String, List<String>> table){
		Enumeration<String> enu = table.keys();
		while(enu.hasMoreElements()){
			String key = enu.nextElement();
			IHtmlObject[] objs = popup.getHtmlObject(".class","Html.LABEL",".text",key);
			objs[0].doubleClick();
			List<String> columns = table.get(key);
			for(String column : columns){
				popup.clickGuiObject(".class","Html.LABEL",".text",column);
				clickAddArrow();
			}
			objs[0].doubleClick();
			Browser.unregister(objs);
		}		
	}
	
	public void chooseReportGroupBy(Hashtable<String, List<String>> table){
		Enumeration<String> enu = table.keys();
		while(enu.hasMoreElements()){
			String key = enu.nextElement();
			IHtmlObject[] objs = null;
			int i = 0;
			while(objs==null||objs.length<2){
				Browser.sleep(1);
				objs = popup.getHtmlObject(".class","Html.LABEL",".text",key);
				i++;
				if(i>60){
					throw new ItemNotFoundException("Failed to Load Group By in "+i+" seconds.");
				}
			}
			objs[1].doubleClick();
			List<String> columns = table.get(key);
			for(String column : columns){
				IHtmlObject[] obj = popup.getHtmlObject(".class","Html.LABEL",".text",column);
				obj[obj.length-1].click();
				Browser.unregister(obj);
				clickAddGroup();
			}
			Browser.unregister(objs);
		}
	}

	public void waitResourceLoading(){
		boolean exists = false;
		int i = 0;
		while(!exists){
			Browser.sleep(1);
			exists = popup.checkHtmlObjectExists(".class","Html.LABEL",".text","Formulas");
			i++;
			if(i>60){
				throw new ItemNotFoundException("Failed to Load Resources in "+i+" seconds.");
			}
		}
	}

	public void clickAddArrow(){
		popup.clickGuiObject(".class","Html.IMG",".id","addF");
	}
	
	public void clickAddGroup(){
		popup.clickGuiObject(".class","Html.IMG",".id","addG_group");
	}
	public void clickOK(){
		popup.clickGuiObject(".class","Html.INPUT.button",".value","OK");
	}
	
	public void clickYes(){
		popup.clickGuiObject(".class","Html.INPUT.button",".value","Yes");
	}
	
	public void clickNext(){
		popup.clickGuiObject(".class","Html.INPUT.button",".value","Next");
	}
	
	public void clickCancel(){
		popup.clickGuiObject(".class","Html.INPUT.button",".value","Cancel");
	}
	
	public void clickFinish(){
		popup.clickGuiObject(".class","Html.INPUT.button",".value","Finish");
	}
	
	/**
	 * The method used to generate a customized report
	 * @param rd report data
	 * @param table  the column you want to included into the report
	 * @param groupByTable determine the report should be group and order by
	 */
	public void generateCustomizeReport(ReportData rd,Hashtable<String, List<String>> table,Hashtable<String, List<String>> groupByTable){
		logger.info("Generate a Customized Report.");
		
		setReportTitle(rd.reportTitle);
		selectReportLayout(rd.reportLayout);
		clickOK();
		waitLoading();
		selectDataResource(rd.dataResource);
		clickNext();
		waitResourceLoading();
		chooseReportColumn(table);
		clickNext();
		waitResourceLoading();
		chooseReportGroupBy(groupByTable);
		clickFinish();
	}
}
