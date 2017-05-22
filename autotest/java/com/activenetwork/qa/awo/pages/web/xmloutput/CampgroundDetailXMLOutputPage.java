/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.xmloutput;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;

/**
 * @Description: It is for the campground detail page in XML format
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Oct 16, 2012
 */
public class CampgroundDetailXMLOutputPage extends WebXMLOutputPage {
	private final String DetailDescriptionTag = "detailDescription";
//	private static String currentUrl = null;
	private static CampgroundDetailXMLOutputPage _instance = null;
	
	public static CampgroundDetailXMLOutputPage getInstance(String url) {
//		if(null==_instance || currentUrl != url) {
		if(null==_instance) {
			_instance=new CampgroundDetailXMLOutputPage(url);
//			currentUrl = url;
		}
		
		if (!loadedUrl.equals(url)) {
			reloadXML(url);
		}
		return _instance;
	}
	
	private CampgroundDetailXMLOutputPage(String url) {
		super("detailDescription", url);
	}
	
	/** Below methods are used to get Campground Details Parameters' values in XML */
	private String getCampgroundDetailDescriptionInfo(String parameter) {
		return this.getElementParameterValue(DetailDescriptionTag, parameter);
	}
	
	private String getCampgroundAddressInfo(String parameter) {
		return this.getElementParameterValue("address", parameter);
	}
	
	private String getCampgroundContactNumber(String nameValue) {
		List<String> contactNames = this.getElementsParameterValues("contact", "name");
		return this.getElementParameterValue("contact", "number", contactNames.indexOf(nameValue));
	}
	
	public String getCampgroundAmenitiesWithinFacility() {
		String amenities = "";
		List<String> distances = this.getCampgroundAmenitiesDistances();
		for (int i = 0; i < distances.size(); i++){
			String distance = distances.get(i);
			if (distance.equals("Within Facility")) {
				amenities = amenities + " " + this.getElementParameterValue("amenity", "name", i);
			}
		}
		return amenities.trim();
	}
	
	public List<String> getCampgroundAmenitiesDistances() {
		return this.getElementsParameterValues("amenity", "distance");
	}
	
	public List<String> getCampgroundAmenitiesNames() {
		return this.getElementsParameterValues("amenity", "name");
	}
	
	public List<String> getCampgoundPhotoRealUrls() {
		return this.getElementsParameterValues("photo", "realUrl");
	}
	
	public List<String> getCampgoundPhotoUrls() {
		return this.getElementsParameterValues("photo", "url");
	}
	
	public String getContractID() {
		return this.getCampgroundDetailDescriptionInfo("contractID");
	}
	
	public String getDescription() {
		return this.getCampgroundDetailDescriptionInfo("description").replaceAll("\\s+", " ").replaceAll("&amp;#39;", "'");
	}
	
	public String getDrivingDirection() {
		return this.getCampgroundDetailDescriptionInfo("drivingDirection");
	}
	
	public String getfacilitiesDescription() {
		return this.getCampgroundDetailDescriptionInfo("facilitiesDescription").replaceAll("\\s+", " ").replaceAll("&amp;#39;", "'");
	}
	
	public String getFacilityName() {
		return this.getCampgroundDetailDescriptionInfo("facility");
	}
	
	public String getFacilityID() {
		return this.getCampgroundDetailDescriptionInfo("facilityID");
	}
	
	public String getFullReservationUrl() {
		return this.getCampgroundDetailDescriptionInfo("fullReservationUrl");
	}
	
	public String getImportantInformation() {
		return this.getCampgroundDetailDescriptionInfo("importantInformation").replaceAll("\\s+", " ").replaceAll("&amp;#39;", "'").trim();
	}
	
	public String getLatitude() {
		return this.getCampgroundDetailDescriptionInfo("latitude");
	}
	
	public String getLongitude() {
		return this.getCampgroundDetailDescriptionInfo("longitude");
	}
	
	public String getNearByAttrDescription() {
		return this.getCampgroundDetailDescriptionInfo("nearbyAttrctionDescription").replaceAll("\\s+", " ").replaceAll("&amp;#39;", "'");
	}
	
	public String getOrientationDescription() {
		return this.getCampgroundDetailDescriptionInfo("orientationDescription").replaceAll("\\s+", " ").replaceAll("&amp;#39;", "'");
	}
	
	public String getRecreationDescription() {
		return this.getCampgroundDetailDescriptionInfo("recreationDescription").replaceAll("\\s+", " ").replaceAll("&amp;#39;", "'");
	}
	
	public String getRegionName() {
		return this.getCampgroundDetailDescriptionInfo("regionName");
	}
	
	public String getReservationUrl() {
		return this.getCampgroundDetailDescriptionInfo("reservationUrl");
	}
	
	public String getAddressState() {
		return this.getCampgroundAddressInfo("state");
	}
	
	public String getAddressStreet() {
		return this.getCampgroundAddressInfo("streetAddress");
	}
	
	public String getAddressCity() {
		return this.getCampgroundAddressInfo("city");
	}
	
	public String getAddressZip() {
		return this.getCampgroundAddressInfo("zip");
	}
	
	public String getAddressCountry() {
		return this.getCampgroundAddressInfo("country");
	}
	
	public String getDirectLinePhoneNumber() {
		return this.getCampgroundContactNumber("Direct Line");
	}
	
	public String getPublicLinePhoneNumber() {
		return this.getCampgroundContactNumber("Ranger Station");
	}
	
	public List<String> getCampgroundInformationLinks() {
		return this.getElementsParameterValues("informationLink", "link");
	}
	
	public List<String> getCampgroundInformationLinkTitles() {
		return this.getElementsParameterValues("informationLink", "title");
	}
	
	public String getCampgroundAlert() {
		return this.getCampgroundDetailDescriptionInfo("alert");
	}
	
	/** Check if Note element exist in XML*/
	public boolean isCampgroundNoteParameterExist() {
		return this.isElementExist("note");
	}
	
	/** Check if Bulletin element exist in XML*/
	public boolean isCampgroundBulletinParameterExist() {
		return this.isElementExist("bulletin");
	}
	
	/** Check if Bulletin element's subcomponents exist in XML*/
	public boolean isCampgroundBulletinSubComponentsExist() {
		return this.isElementSubComponentsExist("bulletin");
	}
	
	/** Get facility info from XML */
	public FacilityData getFacilityDetailsInfo() {
		FacilityData facility = new FacilityData();
		facility.facilityName = this.getFacilityName();
		facility.facilityID = this.getFacilityID();
		
		facility.brochureDescription = this.getDescription();
		facility.brochureGeography = this.getOrientationDescription();
		facility.brochureRecreation = this.getRecreationDescription();
		facility.brochureFacilities = this.getfacilitiesDescription();
		facility.brochureNearbyAttractions = this.getNearByAttrDescription();
		
		facility.importantInfo = this.getImportantInformation();
		facility.latitudeDegrees = this.getLatitude();
		facility.longitudeDegrees = this.getLongitude();
		facility.drivingDirection = this.getDrivingDirection();
		
		facility.mailingAddress = this.getAddressStreet();
		facility.mailingCityTown = this.getAddressCity();
		facility.mailingCountry = this.getAddressCountry();
		facility.mailingState = this.getAddressState();
		facility.mailingZipCode = this.getAddressZip();
		facility.directLine = this.getDirectLinePhoneNumber();
		facility.publicLine = this.getPublicLinePhoneNumber();
		return facility;
	}
}
