/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.pos;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo.RevenueLocation;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Jane Wang
 * @Date  Jun 19, 2012
 */
public class OrmsPosSelectRevenueLocationWidget extends DialogWidget {
	private static OrmsPosSelectRevenueLocationWidget _instance = null;
	
	private OrmsPosSelectRevenueLocationWidget() {
		
	}
	
	public static OrmsPosSelectRevenueLocationWidget getInstance(){
		if(_instance == null){
			_instance = new OrmsPosSelectRevenueLocationWidget();
		}
		
		return _instance;
	}
	
	public boolean exists(){
		return super.exists() && (browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", new RegularExpression("Select( Revenue)? Location",false))||browser.checkHtmlObjectExists(".class","Html.TD",".text","Select Station/User"));
	}
	
	public IHtmlObject getSpanObjects(String text){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^"+text+".*", false), getWidget()[0]);
		if(objs.length<1){
			throw new ErrorOnPageException("Could not get any Span by text "+text);
		}
		
		return objs[0];
	}
	
	public boolean isFacilityExisted(){
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", new RegularExpression("^Facility.*", false));
	}
	
	public void selectValue(String location, String value){
		IHtmlObject obj = getSpanObjects(location);
		browser.selectDropdownList(".id", new RegularExpression("DropdownExt-\\d+\\.selectedValue", false), value,false, obj);
		Browser.unregister(obj);
	}
	
	public void selectLocation(RevenueLocation revLocation){
		selectValue("Agency", revLocation.agency);
		ajax.waitLoading();
		if (!revLocation.region.isEmpty()) {
			selectValue("Region", revLocation.region);
			ajax.waitLoading();
		}
		if (!revLocation.district.isEmpty()) {
			selectValue("District", revLocation.district);
			ajax.waitLoading();
		}
		if (!revLocation.project.isEmpty()) {
			selectValue("Project", revLocation.project);
			ajax.waitLoading();
		}
		if(isFacilityExisted() && !revLocation.facility.isEmpty()){
			selectValue("Facility", revLocation.facility);
			ajax.waitLoading();
		}
	}
}
