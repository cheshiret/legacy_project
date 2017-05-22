package com.activenetwork.qa.awo.pages;

import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.page.Ajax;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class OrmsPage extends AwoPage {
	protected static final int NOFRAME=-1;
	protected static final int ORDER_FRAME=0;
	protected static final int TRANSACTION_FRAME=1;
	protected static final int MAP_FRAME=2;
	private String buildpattern = "[2-9](\\.\\d+)+";
	private String sessionid_pattern="[0-9A-Z]{32}\\.[0-9a-z\\-]+(?=:)";
	protected Ajax ajax=AwoAjax.getInstance();
	
	private static OrmsPage _instance=null;
	
	protected OrmsPage() {}
	
	public static OrmsPage getInstance() {
		if(_instance==null) {
			_instance=new OrmsPage();
		}
		
		return _instance;
	}
	
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.DIV", ".className", "footer_branding",".text",buildpattern));
	}
	
	/**
	 * Get build num according to get humldocument's text property
	 * @return
	 */
	public String getBuildNum() {
		String txt = browser.getObjectText(".class", "Html.DIV", ".className", "footer_branding");
		String[] bn = RegularExpression.getMatches(txt,buildpattern );
		if(bn.length>0)
			return bn[0];
		else {
			logger.info("Failed to get build number with pattern "+buildpattern);
			return "";
		}
	}
	
	public String getManagerName() {
		Property[] p1=Property.toPropertyArray(".class", "Html.DIV", ".id", "productName");
		Property[] p2=Property.toPropertyArray(".class", "Html.IMG");
		IHtmlObject objs[] = browser.getHtmlObject(Property.atList(p1,p2));
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
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.FRAMESET");
		
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
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.FRAMESET");
		
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
		Property[] p2=Property.toPropertyArray(".class","Html.A",".className",new RegularExpression("^btnanchor( traillink)?$",false));
		
		IHtmlObject[] objs=browser.getHtmlObject(Property.atList(p1,p2));
		boolean isSame=false;
		if(objs.length>0) {
			String text=objs[objs.length-1].text();
			isSame= StringUtil.notEmpty(text) && text.matches(trailValue);
		}
		
		Browser.unregister(objs);
		return isSame;
	}
	
	public String getSessionID() {
		String txt = browser.getObjectText(".class", "Html.DIV", ".className", "footer_branding");
		String[] bn = RegularExpression.getMatches(txt,sessionid_pattern );
		if(bn.length>0) {
			logger.info("Get session id#: "+bn[0]);
			return bn[0];
		} else {
			logger.warn("Failed to get session id with pattern "+buildpattern);
			return "";
		}
	}
	
	public PagingComponent getPagingComponent() {
		return new PagingComponent();
	}
	
	public IHtmlObject[] getMapFrame() {	
		return browser.getFrame("map");
	}

	public IHtmlObject[] getOrderFrame() {
		return browser.getFrame("order");
	}

	public IHtmlObject[] getTransactionFrame() {
		return browser.getFrame("transaction");
	}
}
