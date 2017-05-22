package com.activenetwork.qa.awo.pages.orms.common.reports;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.page.HtmlMainPage;
import com.activenetwork.qa.testapi.util.KeyInput;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.TestProperty;
import com.activenetwork.qa.testapi.util.Timer;


/**
 * DHTML report page and also a DHTML parser 
 **/
public class OrmsDHTMLReportPage extends HtmlMainPage {
	protected static final String DELIMIT="#;#;#;#";
	
	private String attributeName;
	private Object value;
	private static OrmsDHTMLReportPage instance = null;

	public static OrmsDHTMLReportPage getInstance() {
		if (instance == null) {
			instance = new OrmsDHTMLReportPage();
		}
		return instance;
	}

	protected OrmsDHTMLReportPage(){
		browser=null;
		attributeName="title";
//		value=new RegularExpression(".*/jreport/dhtmljsp/index\\.jsp\\?.*",false);
		value="JReport Web- Powered by JReport -Index";
		timeout=Integer.parseInt(TestProperty.getProperty("page.loading.onlinereport.wait"));
	}
	
	@Override
	public boolean exists() {
		browser=Browser.getInstance().getBrowser(attributeName, value);
		boolean exists= browser!=null;
		if(exists) {
			browser.maximize();
			pagingSynchronize(timeout);
		}
		return exists;
	}
	
	public void pagingSynchronize(int timeout) {
		Timer timer=new Timer();
		boolean done=false;
		while(!done && timer.diff()<timeout) {
			ConfirmDialogPage dialog = ConfirmDialogPage.getInstance();
			if(dialog.exists()){
				dialog.dismiss();
			}
			IHtmlObject[] objs=browser.getHtmlObject(".class","Html.DIV",".id","jro_waitingdiv");
		
			if(null == objs || objs.length<1) {
				done=true;
			} else {
				String style=objs[0].style("DISPLAY").trim();
				if(style.equals("none")) {
					done=true;
				}
				
//				if(style.length()<1 || style.equals("none")) {
//					done=true;
//				}
			}
			Browser.sleep(1);
			
			Browser.unregister(objs);
		}
		
		if(!done) {
			throw new PageNotFoundException("Page loading not done in "+timer.diff()+" seconds");
		}
		
	}
	
	/**
	 * goto page with given page number
	 * @param pageNumber
	 */
	public void gotoPage(int pageNumber,boolean isAdhocReport) {
		IHtmlObject[] objs=browser.getTextField(".id", "jro_gotopageinput");
		IText textField=(IText) objs[0];
		if(isAdhocReport){
			textField=(IText) objs[1];
		}
		int currentNumber=Integer.parseInt(textField.getText());
		if(currentNumber!=pageNumber) {
			textField.setText("");
			textField.setText(pageNumber);
//			browser.inputKey("{ENTER}");
			browser.inputKey(KeyInput.get(KeyInput.ENTER));
			pagingSynchronize(timeout);
		}
		Browser.unregister(objs);
		
	}
	
	/**
	 * goto last page
	 */
	public void gotoLastPage(boolean isAdhocReport) {
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.IMG",".alt","Last");
		if(isAdhocReport){
			objs[1].click();
		}else{
			objs[0].click();
		}
		Browser.unregister(objs);
		pagingSynchronize(timeout);
	}
	
	/**
	 * goto first page
	 */
	public void gotoFirstPage(boolean isAdhocReport) {
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.IMG",".alt","First");
		if(isAdhocReport){
			objs[1].click();
		}else{
			objs[0].click();
		}
		Browser.unregister(objs);
		pagingSynchronize(timeout);
	}
	
	/**
	 * goto previous page
	 */
	public void gotoPreviousPage() {
		browser.clickGuiObject(".class","Html.IMG",".alt","Previous");
		pagingSynchronize(timeout);
	}
	
	/**
	 * goto next page
	 */
	public void gotoNextPage() {
		browser.clickGuiObject(".class","Html.IMG",".alt","Next");
		pagingSynchronize(timeout);
	}
	
	public void zoom(String option,boolean isAdhocReport) {
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.IMG",".alt","Zoom");
		if(isAdhocReport){
			objs[1].click();
		}else{
			objs[0].click();
		}
		browser.clickGuiObject(".class","Html.LABEL",".text",option);
		Browser.unregister(objs);
		pagingSynchronize(timeout);
	}
	
	public IHtmlObject[] getReportBody() {
		return browser.getHtmlObject(".class","Html.DIV",".id",new RegularExpression("jro_ReportBody1?+",false));
	}
	
	protected String getStringOfTotalNumberOfPages() {
		String text=browser.getObjectText(".class","Html.DIV",".id","jro_goto");
		String[] tokens=text.split(" ");
		return tokens[tokens.length-1].trim();
	}
	
	/**
	 * retrieve the total number of page for the report
	 * @return total page number
	 */
	public int getTotalNumberOfPages(boolean isAdhocReport) {
		String num=getStringOfTotalNumberOfPages();
		if(num.equals("+")) {
			gotoLastPage(isAdhocReport);
			num=getStringOfTotalNumberOfPages();
		}
	
		return Integer.parseInt(num);
	}
	
	protected IHtmlObject[] getDivNodes() {
		IHtmlObject[] objs=getReportBody();
//		RegularExpression reg=new RegularExpression(".+",false);
//		Property[] prop=new Property[2];
//		prop[0]=new Property(".class","Html.SPAN");
//		prop[1]=new Property(".id",reg);
//		prop[2]=new Property("child","SPAN");
//		prop[2]=new Property("innerHtml",new RegularExpression("^<SPAN.*",false));
		IHtmlObject[] spans=browser.getHtmlObject(".class","Html.SPAN");
		List<IHtmlObject> divs=new ArrayList<IHtmlObject>();
		
		for(IHtmlObject span:spans) {
			IHtmlObject parent=(IHtmlObject) span.getParent();
			if(parent.tag().equalsIgnoreCase("DIV")) {
				divs.add(parent);
			}
		}
		Browser.unregister(objs,spans);
		return divs.toArray(new IHtmlObject[0]);
	}
	
	protected String getPropertyString(IHtmlObject obj) {
		StringBuffer text=new StringBuffer();
		
		text.append(obj.getAttributeValue(".id")+DELIMIT);
		text.append(obj.text()+DELIMIT);
		text.append(obj.getProperty("style"));
		
		return text.toString();
	}
	
	protected Properties parseCells(IHtmlObject[] divs, String keyPrefix) {
		Properties prop=new Properties();
		
		prop.put(keyPrefix, divs.length+"");
		for(int i=0;i<divs.length;i++) {
			prop.put(keyPrefix+"_cell_"+i, getPropertyString(divs[i]));
		}
		
		return prop;
	}

	/**
	 * parse the detailed information about the current report and save the information to the given report data file
	 * @param dataFileName - the absolute path of the data file
	 * @throws IOException
	 */
	public void parseInto(String dataFileName,boolean isAdhocReport) throws IOException {
		Properties prop=parse(isAdhocReport);
		PrintStream out=new PrintStream(dataFileName);
		
		prop.store(out, dataFileName);
		out.close();
	}
	
	/**
	 * Parse the detailed information of the current report
	 * @return information properties
	 */
	public Properties parse(boolean isAdhocReport) {
		Properties prop=new Properties();
		int pageNum=getTotalNumberOfPages(isAdhocReport);
		
		prop.put("page", Integer.toString(pageNum));
		for(int i=1;i<=pageNum;i++) {

			gotoPage(i,isAdhocReport);
			prop.putAll(scanPage("page_"+i));
		}
		
		return prop;
	}
	
	public void getSnapshotForEachPage(String fileName,boolean isAdhocReport){
		int pageNum=getTotalNumberOfPages(isAdhocReport);
		zoom("75%",isAdhocReport);
		gotoFirstPage(isAdhocReport);
		for(int i=1;i<=pageNum;i++) {
			gotoPage(i,isAdhocReport);
			browser.catchScreenShot(fileName+i);
		}
	}
	
	protected Properties scanPage(String keyPrefix) {
		Properties prop=new Properties();
		IHtmlObject[] divs=getDivNodes();
		prop.putAll(parseCells(divs,keyPrefix));
		Browser.unregister(divs);
		
		return prop;	
	}
	
	/**
	 * Compare the current report with the information in the given data file
	 * @param dataFile - the absolute page of data file
	 * @return - a list of error messages
	 * @throws IOException
	 */
	public List<String> matchFrom(String dataFile,boolean isAdhocReport) throws IOException {
		InputStream in=new FileInputStream(dataFile);
		Properties propFrom=new Properties();
		propFrom.load(in);
		in.close();
		Properties propTo=parse(isAdhocReport);
		
		return match(propFrom,propTo);
	}
	
	/**
	 * Compare the current report with the information in data properties
	 * @param from - the properties retrieved from file
	 * @param to - the properties retrieved from current report
	 * @return - a list of error messages
	 */
	public List<String> match(Properties from,Properties to) {
		List<String> msgs=new ArrayList<String>();
		int actualPageNumber=Integer.parseInt(to.getProperty("page"));
		int expectedNumber=Integer.parseInt(from.getProperty("page"));

		if(actualPageNumber!=expectedNumber) {
			String msg="Total number of pages "+actualPageNumber+" is not expected "+expectedNumber;
			logger.fatal(msg);
			msgs.add(msg);
		} else {
			for(int i=1;i<=actualPageNumber;i++) {
				int actualCells=Integer.parseInt(from.getProperty("page_"+i));
				int expectedCells=Integer.parseInt(to.getProperty("page_"+i));
			
				if(actualCells!=expectedCells) {
					String msg="page_"+i+" - Total number of cells "+actualCells+" is not expected "+expectedCells;
					logger.error(msg);
					msgs.add(msg);
				} else {
					for(int j=0;j<actualCells;j++) {
						String key="page_"+i+"_cell_"+j;
						String valueFrom=from.getProperty(key);
						String valueTo=to.getProperty(key);
					
						msgs.addAll(match(valueFrom, valueTo,key));
					}
				}
			}
		}

		return msgs;
	}
	
	protected List<String> match(String from, String to, String msgPrefix) {
		List<String> msgs=new ArrayList<String>();
		String[] tokensF=from.split(DELIMIT);
		String idf=tokensF[0];
		String textf=tokensF[1];
		String stylef=tokensF[2];
		
		String[] tokensT=to.split(DELIMIT);
		String idt=tokensT[0];
		String textt=tokensT[1];
		String stylet=tokensT[2];

		if(!idt.equals(idf)) {
			logger.warn(msgPrefix+" - cell id#"+idt+" is not expected "+idf);
		}
		
		if(!textf.equals(textt)) {
			if(!textf.startsWith("Run Date and Time") && !textt.startsWith("Run Date and Time")) {
				String msg=msgPrefix+" - text \""+textt+"\" should be \""+textf+"\"";
				logger.error(msg);
				msgs.add(msg);
			}
		}

		msgs.addAll(matchStyle(stylef,stylet,msgPrefix));
		return msgs;
		
	}
	
	protected List<String> matchStyle(String from, String to, String msgPrefix) {
		Properties styleF=parseStyle(from);
		Properties styleT=parseStyle(to);
		List<String> msgs=new ArrayList<String>();
		
		Enumeration<Object> keyF= styleF.keys();
		while(keyF.hasMoreElements()){
			String key=(String)keyF.nextElement();
			String valueF=styleF.getProperty(key);
			String valueT=styleT.getProperty(key);
			boolean equal=false;
			if(valueT.matches("\\d+px") && valueF.matches("\\d+px")) {
				equal=pixelSizeCompare(valueF,valueT);
			} else {
				equal=valueF.equals(valueT);
			}
			if(!equal) {
				String msg=msgPrefix+" - "+key+"'s value \""+valueT+"\" should be \""+valueF+"\"";
				logger.error(msg);
				msgs.add(msg);
			}
		}
		return msgs;
	}
	
	protected boolean pixelSizeCompare(String from, String to) {
		int fromPixel=Integer.parseInt(from.substring(0,from.length()-2));
		int toPixel=Integer.parseInt(to.substring(0,to.length()-2));
		int diff=Math.abs(fromPixel-toPixel);
		
		return diff<3;
	}
	
	protected Properties parseStyle(String styleStr) {
		Properties style=new Properties();
		
		String[] tokens=styleStr.split(";");
		for(int i=0;i<tokens.length;i++) {
			int index=tokens[i].indexOf(":");
			if(index<0) {
				System.out.println(styleStr);
			} else {
				String styleName=tokens[i].substring(0,index).trim();
				String value=tokens[i].substring(index+1).trim();
				style.put(styleName, value);
			}
		}
		
		return style;
	}	
	
	protected void dismissDialog(){
		ConfirmDialogPage dialog = ConfirmDialogPage.getInstance();
		boolean done = false;
		int count=0;
		while(!done) {
			Timer.sleep(1000);
			if(dialog.exists()){
				dialog.dismiss();
				done=true;
			} else {
				count++;
				done=count>5;
			}
		}
	}
	public void clickInteractiveView(){
		pagingSynchronize(timeout);
		browser.clickGuiObject(".class","Html.A",".text","Interactive View");
		dismissDialog();
	}
	
	public void newReport(){
		browser.clickGuiObject(".class","Html.LABEL",".text","Menu");
		browser.clickGuiObject(".class","Html.LABEL",".text","File");
		browser.clickGuiObject(".class","Html.LABEL",".text","File");
		browser.clickGuiObject(".class","Html.LABEL",".text","New Report...");
	}

	public void close() {
		browser.close();
		
	}
	

}
