/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.xmloutput;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.SiteInfoData;

/**
 * @Description: It is for the camp site detail page in XML format
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Nov 1, 2012
 */
public class CampsiteDetailXMLOutputPage extends WebXMLOutputPage {
	private static CampsiteDetailXMLOutputPage _instance = null;
//	private static String currentUrl = null;
	
	public static CampsiteDetailXMLOutputPage getInstance(String url) {
//		if(null==_instance || currentUrl != url) {
		if(null==_instance) {
			_instance=new CampsiteDetailXMLOutputPage(url);
//			currentUrl = url;
		}
		
		if (!loadedUrl.equals(url)) {
			reloadXML(url);
		}
		return _instance;
	}
	
	private CampsiteDetailXMLOutputPage(String url) {
		super("campsite", url);
	}

	public SiteInfoData getSiteDetailsInfo() {
		SiteInfoData site = new SiteInfoData();
		site.loopName = this.getCampsiteLoopName();
		site.parkID = this.getCampsiteParkID();
		site.parkName = this.getCampsiteParkName();
		site.state = this.getCampsiteParkState();
		site.siteType = this.getCampsiteProductGroupName();
		site.siteCode = this.getCampsiteSiteCode();
		site.siteId = this.getCampsiteSiteID();
		return site;
	}
	
	/**The below methods are used to get the campsite basic info */
	private String getCampsiteInfo(String parameter) {
		return this.getElementParameterValue("campsite", parameter);
	}
	
	public String getCampsiteLoopName() {
		return this.getCampsiteInfo("loopName");
	}
	
	public String getCampsiteParkID() {
		return this.getCampsiteInfo("parkId");
	}
	
	private String[] getCampsiteParkNameAndState() {
		return this.getCampsiteInfo("parkNameState").split(",");
	}
	
	public String getCampsiteParkName() {
		return this.getCampsiteParkNameAndState()[0];
	}
	
	public String getCampsiteParkState() {
		return this.getCampsiteParkNameAndState()[1].trim();
	}
	
	public String getCampsiteProductGroupName() {
		return this.getCampsiteInfo("productGroupName");
	}
	
	public String getCampsiteSiteCode() {
		return this.getCampsiteInfo("siteCode");
	}
	
	public String getCampsiteSiteID() {
		return this.getCampsiteInfo("siteId");
	}
	
	public String getCampsiteGPS() {
		return this.getCampsiteInfo("gps");
	}
	
	public String getCampsiteAccessibleMeg() {
		String meg = this.getCampsiteInfo("accessibleMessage");
		meg = meg.replaceAll("&lt;.*?&gt;", "").trim();
		return meg;
	}
	
	public String getCampsiteNssCount() {
		return this.getCampsiteInfo("nssCount");
	}
	
	public String getCampsiteNonSpecific() {
		return this.getCampsiteInfo("nonSpecific");
	}
	
	public List<String> getCampsiteAttributes() {
		return this.getElementsParameterValues("attribute", "attributeName", "attributeValue", "displaySequenceNumber");
	}
	
	public List<String> getCampsiteSiteAlerts() {
		return this.getElementsParameterValues("siteAlert", "value");
	}
	
	public List<String> getCampsiteLoopAlerts() {
		return this.getElementsParameterValues("loopAlert", "value");
	}
	
	public List<String> getCampsiteSiteNotes() {
		return this.getElementsParameterValues("siteNote", "value");
	}
	
	public List<String> getCampsiteLoopNotes() {
		return this.getElementsParameterValues("loopNote", "value");
	}
	
	public List<String> getCampsitePhotos() {
		return this.getElementsParameterValues("photo", "value");
	}
	
	public boolean isCampsitePhotosElementExist() {
		return this.isElementExist("photos");
	}
	
	public boolean isCampsitePhotoElementExist() {
		return this.isElementExist("photo");
	}
	
	public boolean isCampsiteSiteAlertsElementExist() {
		return this.isElementExist("siteAlerts");
	}
	
	public boolean isCampsiteSiteAlertElementExist() {
		return this.isElementExist("siteAlert");
	}
	
	public boolean isCampsiteSiteNotesElementExist() {
		return this.isElementExist("siteNotes");
	}
	
	public boolean isCampsiteSiteNoteElementExist() {
		return this.isElementExist("siteNote");
	}
	
	public boolean isCampsiteLoopAlertsElementExist() {
		return this.isElementExist("loopAlerts");
	}
	
	public boolean isCampsiteLoopAlertElementExist() {
		return this.isElementExist("loopAlert");
	}
	
	public boolean isCampsiteLoopNotesElementExist() {
		return this.isElementExist("loopNotes");
	}

	public boolean isCampsiteLoopNoteElementExist() {
		return this.isElementExist("loopNote");
	}
	
	public boolean isCampsiteNonSpecificParameterExist() {
		return this.getCampsiteNonSpecific() != null; 
	}
	
	public boolean isCampsiteNssCountParameterExist() {
		return this.getCampsiteNssCount() != null; 
	}
}
