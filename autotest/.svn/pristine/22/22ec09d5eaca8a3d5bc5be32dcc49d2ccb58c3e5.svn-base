package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * When add privilege to cart, this widget will pop up.
 * @author nding
 * @Date  Oct 17, 2013
 *
 */
public class LicMgrPriLandownerDeclarationWidget extends DialogWidget {
	private static LicMgrPriLandownerDeclarationWidget _instance = null;
	
	protected LicMgrPriLandownerDeclarationWidget() {
	}


	public boolean exists() {// this widget doesn't have title.
//		return browser.checkHtmlObjectExists(landownerDeclarationTable());
		return browser.checkHtmlObjectExists(".class", "Html.Table", ".text", new RegularExpression("Property in County",false));// TOOD
	}
	
	private String prefix = "PrivilegeInstanceCountyPropertyAcreageView-\\d+.";

	protected Property[] landownerDeclarationTable(){
		return Property.toPropertyArray(".class", "Html.TABLE", ".text", new RegularExpression("Landowner Declaration", false));
	}
	
	public static LicMgrPriLandownerDeclarationWidget getInstance () {
		if (null == _instance) {
			_instance = new LicMgrPriLandownerDeclarationWidget();
		}
		return _instance;
	}
	
	protected Property[] county(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix+"county", false));
	}
	
	public void setPropertyInCounty(String county, int index){
		browser.selectDropdownList(county(), county, index);
	}

	protected Property[] acres(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix+"strAcresOwned:ZERO_TO_NULL", false));
	}
	
	public void setAcres(String acres, int index){
		browser.setTextField(acres(), acres, index);
	}
	
	/**
	 * Nicole
	 * Using hash map to set county and acres.
	 * @param landownerDeclar
	 */
	public void setLandownerDelaration(HashMap<String, String> landownerDeclar){
		Iterator iterator = landownerDeclar.entrySet().iterator();
		for (int i=0; i<landownerDeclar.size(); i++) {
			Map.Entry entry = (Map.Entry) iterator.next(); 
	        String county = entry.getKey().toString(); 
	        String arecs = entry.getValue().toString();

			this.setPropertyInCounty(county, i);
			this.setAcres(arecs, i);
		}
	}
}