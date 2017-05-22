package com.activenetwork.qa.awo.pages.orms;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.page.HtmlMainPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class OrmsPage extends HtmlMainPage implements OrmsConstants {
	protected static final int NOFRAME=-1;
	protected static final int ORDER_FRAME=0;
	protected static final int TRANSACTION_FRAME=1;
	protected static final int MAP_FRAME=2;
	private String buildpattern = "[2-9](\\.\\d+)+";
	private String sessionid_pattern="[0-9A-Z]{32}\\.[0-9a-z\\-]+(?=:)";
	
	private static OrmsPage _instance=null;
	
	protected OrmsPage() {}
	
	public static OrmsPage getInstance() {
		if(_instance==null) {
			_instance=new OrmsPage();
		}
		
		return _instance;
	}
	
	protected Property[] footerDiv() {
		return Property.concatPropertyArray(div(), ".className", "footer_branding");
	}
	
	protected Property[] activeworksDiv() {
		return Property.concatPropertyArray(div(),".id","activeworks");
	}
	
	protected Property[] productNameDiv() {
		return Property.concatPropertyArray(div(),".id","productName");
	}
	
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(activeworksDiv());
	}
	
	/**
	 * Get build num according to get humldocument's text property
	 * @return
	 */
	public String getBuildNum() {
		String txt = browser.getObjectText(footerDiv());
		String[] bn = RegularExpression.getMatches(txt,buildpattern );
		if(bn.length>0)
			return bn[0];
		else {
			logger.info("Failed to get build number with pattern "+buildpattern);
			return "";
		}
	}
	
	public String getManagerName() {
		IHtmlObject objs[] = browser.getHtmlObject(Property.atList(productNameDiv(),img()));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find product name img object.");
		}
		String name = objs[0].getProperty("alt").trim();
		
		Browser.unregister(objs);
		return name;
	}
	
	public String getContract() {
		String pattern ="(?<=\\s)[A-Z]{2,4} Contract";
		String txt = browser.getObjectText(".class", "Html.DIV", ".className", "footer_branding");
		String[] contract = RegularExpression.getMatches(txt, pattern);	
		
		if(contract!=null && contract.length>0) {
			return contract[0].replaceAll("Contract", "").trim();
		} else {
			return null;
		}
	}
	
	public int[] getFrameSize() {
		IHtmlObject[] objs=browser.getHtmlObject(frameset());
		
		if(objs!=null && objs.length>0) {
			int[] frameSizes=new int[3];
			String rows=objs[0].getAttributeValue("rows").replaceAll("px","");
			String[] tokens=rows.split(",");
			int size=Math.min(3, tokens.length);
			
			for(int i=0;i<size;i++) {
				if(tokens[i].equals("*")){
					frameSizes[i]=Integer.MAX_VALUE;
				}else {
					frameSizes[i]=Integer.parseInt(tokens[i]);
				}
			}
			
			Browser.unregister(objs);
			
			return frameSizes;
		} else {
			return null;
		}
	}
	
	public int getMainFrame() {
		IHtmlObject[] objs=browser.getHtmlObject(frameset());
		
		if(objs!=null && objs.length>0) {
			String rows=objs[0].getAttributeValue("rows").replaceAll("px","");
			Browser.unregister(objs);
			String[] tokens=rows.split(",");
			int size=Math.min(3, tokens.length);
			
			for(int i=0;i<size;i++) {
				if(tokens[i].equals("*")){
					return i;
				}
			}

		} 
		
		return NOFRAME;
	}
	
	protected boolean checkLastTrailValueIs(String trailValue) {
		Property[] p1=Property.toPropertyArray(".class","Html.TD",".className","label_activetransaction");
		Property[] p2=Property.toPropertyArray(".class","Html.A",".className","btnanchor");
		
		IHtmlObject[] objs=browser.getHtmlObject(Property.atList(p1,p2));
		boolean isSame=false;
		if(objs.length>0) {
			String text=objs[objs.length-1].text();
			isSame= text.equalsIgnoreCase(trailValue);
		}
		
		Browser.unregister(objs);
		return isSame;
	}
	
	public String getSessionID() {
		String txt = browser.getObjectText(footerDiv());
		String[] bn = RegularExpression.getMatches(txt,sessionid_pattern );
		if(bn.length>0) {
			logger.info("Get session id#: "+bn[0]);
			return bn[0];
		} else {
			logger.warn("Failed to get session id with pattern "+buildpattern);
			return "";
		}
	}
}
