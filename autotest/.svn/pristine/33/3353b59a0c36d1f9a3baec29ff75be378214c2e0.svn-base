package com.activenetwork.qa.awo.pages.web.recgov;

import com.activenetwork.qa.awo.pages.web.uwp.UwpPermitingPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description: It is Permit Area Map page
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Jun 27, 2013
 */
public class RecgovPermitAreaMapPage extends UwpPermitingPage {
	private static RecgovPermitAreaMapPage _instance = null;

	public static RecgovPermitAreaMapPage getInstance() {
		if (null == _instance)
			_instance = new RecgovPermitAreaMapPage();

		return _instance;
	}

	protected RecgovPermitAreaMapPage() {}

	/** Elements Properties Define Begin */
	protected Property[] getViewMapDivProp() {
		return Property.toPropertyArray(".class", "Html.TABLE", ".id", "staticMap");
	}
	
	protected Property[] mapviewport(){
		return Property.concatPropertyArray(div(), ".id", "mapviewport");
	}
	
	protected Property[] getMapLinkProp() {
		return Property.toPropertyArray(".class", "Html.A", ".id", "mapLink");
	}
	
	protected Property[] getEntranceMapRadioBtnProp() {
		return Property.toPropertyArray(".class", "Html.INPUT.radio", ".id", "entranceTab");
	}
	
	protected Property[] getIssueStationMapRadioBtnProp() {
		return Property.toPropertyArray(".class", "Html.INPUT.radio", ".id", "stationTab");
	}
	
	protected Property[] mapEntranceList(){
		return Property.concatPropertyArray(div(), ".id", "mapentrancelist");
	}
	
	protected Property[] entranceLink(String contractCode, String parkId, String entranceId){
		return Property.concatPropertyArray(a(), ".href", new RegularExpression(".*contractCode="+contractCode+"&parkId="+parkId+"&entranceId="+entranceId, false));
	}
	/** Elements Properties Define End */
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(getViewMapDivProp()) || browser.checkHtmlObjectExists(mapviewport());
	}
	
	public boolean isMapImgExist(String facilityID) {
		return browser.checkHtmlObjectExists(".class", "Html.IMG", ".id", facilityID);
	}
	
	public boolean isMapLinkExist() {
		return browser.checkHtmlObjectExists(getMapLinkProp());
	}
	
	public String getMapLinkText() {
		return browser.getObjectText(getMapLinkProp());
	}
	
	public boolean isEntranceMapRadioBtnExist() {
		return browser.checkHtmlObjectExists(getEntranceMapRadioBtnProp());
	}
	
	public boolean isIssueStationMapRadioBtnExist() {
		return browser.checkHtmlObjectExists(getIssueStationMapRadioBtnProp());
	}
	
	public boolean isEntranceExisted(String contractCode, String parkId, String entranceId){
		return browser.checkHtmlObjectExists(Property.atList(mapEntranceList(), entranceLink(contractCode, parkId, entranceId)));
	}
	
	public void verifyEntranceExisted(String contractCode, String parkId, String entranceId, boolean existed){
		boolean existedFromUI = isEntranceExisted(contractCode, parkId, entranceId);
		if(existedFromUI!=existed){
			throw new ErrorOnPageException("Entrance:"+entranceId+" under contractCode:"+contractCode+", parkID:"+parkId+" should "+(existed?"":"not ")+"exist");
		}
	}
}
