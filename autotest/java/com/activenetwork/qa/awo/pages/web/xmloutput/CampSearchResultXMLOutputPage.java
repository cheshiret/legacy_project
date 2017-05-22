/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.xmloutput;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.SiteInfoData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @Description: It is for the campground and campsite search result page in XML format
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Nov 12, 2012
 */
public class CampSearchResultXMLOutputPage extends WebXMLOutputPage {
//	private static String currentUrl = null;
	private static CampSearchResultXMLOutputPage _instance = null;
   
	// search criterial id
	public static final String SITE_TYPE_ID_RV_SITES = "2001";
	public static final String AMENITY_ID_BIKING = "4001";
	public static final String ELECTIRC_HOOPUP_ID_15AMP = "3002";
	public static final String WATER_HOOPUP_ID = "3006";
	public static final String SEWER_HOOPUP_ID = "3007";
	public static final String DRIVEWAY_ID = "3008";
	public static final String ACCESSIBLE_ID = "3009";
	public static final String PETS_ALLOWED_ID = "3010";
	public static final String WATERFRONT_SITE_ID = "3011";
    public static final String DATES_RANGE_SEARCH_ID = "2";
    
	public static CampSearchResultXMLOutputPage getInstance(String url) {
		if(null==_instance) {
			_instance=new CampSearchResultXMLOutputPage(url);
//			currentUrl = url;
		}
		if (!loadedUrl.equals(url)) {
			reloadXML(url);
		}
		
		return _instance;
	}
	
	private CampSearchResultXMLOutputPage(String url) {
		super("resultset", url);
	}
	
	/** Below methods are used to get Campground/Campsite Search Parameters' values in XML */
	private String getCampSearchParameterValue(String parameter) {
		return this.getElementParameterValue("resultset", parameter);
	}

	public String getCampSearchResultCount() {
		return this.getCampSearchParameterValue("count");
	}

	public String getCampSearchResultType() {
		return this.getCampSearchParameterValue("resultType");
	}
	
	public String getCampSearchDateRange() {
		return this.getCampSearchParameterValue("range");
	}
	
	public String getCampSearchEndDate() {
		return this.getCampSearchParameterValue("enddate");
	}
	
	public String getCampSearchSiteType() {
		return this.getCampSearchParameterValue("siteType");
	}
	
	public String getCampSearchCriteriaLengthOfStay() {
		return this.getCampSearchParameterValue("lengthOfStay");
	}
	
	/** Below methods are only for campground search result */
	public String getCampgroundSearchCriteriaAmenity() {
		return this.getCampSearchParameterValue("amenity");
	}
	
	public String getCampgroundSearchCriteriaEqpLen() {
		return this.getCampSearchParameterValue("eqplen");
	}
	
	public String getCampgroundSearchCriteriaHookup() {
		return this.getCampSearchParameterValue("hookup");
	}

	public String getCampgroundSearchCriteriaMaxPeople() {
		return this.getCampSearchParameterValue("maxpeople");
	}
	
	public String getCampgroundSearchParkName() {
		return this.getCampSearchParameterValue("pname");
	}
	
	public String getCampgroundSearchState() {
		return this.getCampSearchParameterValue("pstate");
	}
	
	public String getCampgroundSearchWaterFront() {
		return this.getCampSearchParameterValue("waterfront");
	}
	
	public String getCampgroundSearchCriteriaAda() {
		return this.getCampSearchParameterValue("ada");
	}
	
	/**
	 * Get Campground Search Criteria Info in XML
	 * @return
	 */
	public BookingData getSearchCriteriaInfo() {
		BookingData bd = new BookingData();
		bd.stateCode = this.getCampgroundSearchState();
		bd.park = this.getCampgroundSearchParkName();
		bd.siteType = this.getCampSearchSiteType();
		bd.isRange = this.getCampSearchDateRange().equals(DATES_RANGE_SEARCH_ID);
		bd.endDate = this.getCampSearchEndDate();
		bd.lengthOfStay = this.getCampSearchCriteriaLengthOfStay();
		bd.parkWithAmenity = this.getCampgroundSearchCriteriaAmenity();
		bd.parkWith = StringUtil.notEmpty(bd.parkWithAmenity);
		bd.equipLength = this.getCampgroundSearchCriteriaEqpLen();
		bd.occupants = this.getCampgroundSearchCriteriaMaxPeople();
		bd.electricHookupValue = this.getCampgroundSearchCriteriaHookup();
		
		String value = this.getCampgroundSearchCriteriaAda();
		bd.spotWithAccessible = value != null ? value.equals(ACCESSIBLE_ID) : false;
		
		value = this.getCampgroundSearchWaterFront();
		bd.spotWithWaterFront = value != null ? value.equals(WATERFRONT_SITE_ID) : false;
		
		bd.spotWith = StringUtil.notEmpty(bd.electricHookupValue) || StringUtil.notEmpty(bd.equipLength) ||
				StringUtil.notEmpty(bd.occupants) || bd.spotWithAccessible || bd.spotWithWaterFront;
		
		return bd;
	}
	
	/** Below methods are used to get Campground/Campsite Search Result Parameters' values in XML */
	private String getCampSearchResultParameterValue(String parameter) {
		return this.getElementParameterValue("result", parameter);
	}

	public String getCampSearchResultAvaStatus() {
		return this.getCampSearchResultParameterValue("availabilityStatus");
	}

	public String getCampSearchResultReservationChannel() {
		return this.getCampSearchResultParameterValue("reservationChannel");
	}

	public String getCampgroundSearchResultSitesWithAmps() {
		return this.getCampSearchResultParameterValue("sitesWithAmps");
	}
	
	public String getCampgroundSearchResultSitesWithPetsAllowed() {
		return this.getCampSearchResultParameterValue("sitesWithPetsAllowed");
	}
	
	public String getCampgroundSearchResultSitesWithSewerHookup() {
		return this.getCampSearchResultParameterValue("sitesWithSewerHookup");
	}
	
	public String getCampgroundSearchResultSitesWithWaterHookup() {
		return this.getCampSearchResultParameterValue("sitesWithWaterHookup");
	}
	
	public String getCampgroundSearchResultSitesWithWaterfront() {
		return this.getCampSearchResultParameterValue("sitesWithWaterfront");
	}
	
	/** Below methods are used to get campground search result Parameters' values in XML */
	public String getCampgroundSearchResultAgencyIcon() {
		return this.getCampSearchResultParameterValue("agencyIcon");
	}
	
	public String getCampgroundSearchResultAgencyName() {
		return this.getCampSearchResultParameterValue("agencyName");
	}
	
	public String getCampgroundSearchResultContractID() {
		return this.getCampSearchResultParameterValue("contractID");
	}
	
	public String getCampgroundSearchResultContractType() {
		return this.getCampSearchResultParameterValue("contractType");
	}
	
	public String getCampgroundSearchResultState() {
		return this.getCampSearchResultParameterValue("state");
	}
	
	public String getCampgroundSearchResultFacilityID() {
		return this.getCampSearchResultParameterValue("facilityID");
	}
	
	public String getCampgroundSearchResultFacilityName() {
		return this.getCampSearchResultParameterValue("facilityName");
	}
	
	public String getCampgroundSearchResultFacilityPhoto() {
		return this.getCampSearchResultParameterValue("faciltyPhoto");
	}
	
	public String getCampgroundSearchResultLatitude() {
		return this.getCampSearchResultParameterValue("latitude");
	}
	
	public String getCampgroundSearchResultLongitude() {
		return this.getCampSearchResultParameterValue("longitude");
	}
	
	public String getCampgroundSearchResultRegionName() {
		return this.getCampSearchResultParameterValue("regionName");
	}
	
	public String getCampgroundSearchResultShortName() {
		return this.getCampSearchResultParameterValue("shortName");
	}
	
	/**
	 * Get First Search Result Facility Info
	 * @return
	 */
	public FacilityData getFirstSearchResultFacilityInfo() {
		FacilityData facility = new FacilityData();
		facility.region = this.getCampgroundSearchResultRegionName();
		facility.latitudeDegrees = this.getCampgroundSearchResultLatitude();
		facility.longitudeDegrees = this.getCampgroundSearchResultLongitude();
		facility.shortName = this.getCampgroundSearchResultShortName();
		facility.agency = this.getCampgroundSearchResultAgencyName();
		facility.facilityID = this.getCampgroundSearchResultFacilityID();
		facility.facilityName = this.getCampgroundSearchResultFacilityName();
		facility.contract = this.getCampgroundSearchResultContractID();
		facility.stateCode = this.getCampgroundSearchResultState();	
		facility.status = this.getCampSearchResultReservationChannel();
		facility.isAvailable = this.getCampSearchResultAvaStatus().equals("Y");
		facility.facilityPhotoSrc = this.getCampgroundSearchResultFacilityPhoto();
		facility.agency = this.getCampgroundSearchResultAgencyName();
		facility.facilityType = this.getCampgroundSearchResultContractType();
		facility.hasSitesWithElectircHookup = this.getCampgroundSearchResultSitesWithAmps().equals("Y");
		facility.hasSitesWithFullHookup = this.getCampgroundSearchResultSitesWithSewerHookup().equals("Y") ||
			this.getCampgroundSearchResultSitesWithWaterHookup().equals("Y");
		facility.hasSitesWithPetsAllowed = this.getCampgroundSearchResultSitesWithPetsAllowed().equals("Y");
		facility.hasSitesWithWaterFront = !StringUtil.isEmpty(this.getCampgroundSearchResultSitesWithWaterfront());
		return facility;
	}
	
	
	/** Below methods are about getting campsite search criteria info */
	public String getCampsiteSearchContractCode() {
		return this.getCampSearchParameterValue("contractCode");
	}
	
	public String getCampsiteSearchParkID() {
		return this.getCampSearchParameterValue("parkId");
	}
	
	public String getCampsiteSearchArrivalDate() {
		return this.getCampSearchParameterValue("arvdate");
	}
	
	public BookingData getCampsiteSearchCriteriaInfo() {
		BookingData bd = new BookingData();
		bd.contractCode = getCampsiteSearchContractCode();
		bd.parkId = this.getCampsiteSearchParkID();
		bd.siteType = this.getCampSearchSiteType();
		bd.arrivalDate = this.getCampsiteSearchArrivalDate();
		bd.lengthOfStay = this.getCampSearchCriteriaLengthOfStay();
		bd.endDate = this.getCampSearchEndDate();
		if (this.getCampSearchDateRange() != null) {
			bd.isRange = this.getCampSearchDateRange().equals(DATES_RANGE_SEARCH_ID);
		}
		return bd;
	}
	
	/** Below methods are about getting campsite search result info*/
	public String getCampsiteSearchResultDrivewayentry() {
		return this.getCampSearchResultParameterValue("Drivewayentry");
	}
	
	public String getCampsiteSearchResultLoop() {
		return this.getCampSearchResultParameterValue("Loop");
	}
	
	public String getCampsiteSearchResultMaxEqpLen() {
		return this.getCampSearchResultParameterValue("Maxeqplen");
	}
	
	public String getCampsiteSearchResultMaxPeople() {
		return this.getCampSearchResultParameterValue("Maxpeople");
	}
	
	public String getCampsiteSearchResultSiteName() {
		return this.getCampSearchResultParameterValue("Site");
	}
	
	public String getCampsiteSearchResultSiteId() {
		return this.getCampSearchResultParameterValue("SiteId");
	}
	
	public String getCampsiteSearchResultSiteType() {
		return this.getCampSearchResultParameterValue("SiteType");
	}
	
	public String getCampsiteSearchResultMapX() {
		return this.getCampSearchResultParameterValue("mapx");
	}
	
	public String getCampsiteSearchResultMapY() {
		return this.getCampSearchResultParameterValue("mapy");
	}
	
	public String getCampsiteSearchResultSitePhoto() {
		return this.getCampSearchResultParameterValue("sitePhoto");
	}
	
	public String getCampsiteSearchResultNssCount() {
		return this.getCampSearchResultParameterValue("nssCount");
	}
	
	public String getCampsiteSearchResultNssAvailSitesCount() {
		return this.getCampSearchResultParameterValue("nssAvailSitesCount");
	}
	
	public String getCampsiteSearchResultSiteResType() {
		return this.getCampSearchResultParameterValue("SiteReserveType");
	}
	
	public SiteInfoData getFirstCampsiteSearchResultInfo() {
		SiteInfoData site = new SiteInfoData();
		site.siteName = this.getCampsiteSearchResultSiteName();
		site.siteId = this.getCampsiteSearchResultSiteId();
		site.siteType = this.getCampsiteSearchResultSiteType();
		site.loopName = this.getCampsiteSearchResultLoop();
		site.maxVehicleLength = this.getCampsiteSearchResultMaxEqpLen();
		site.maxNumPeople = this.getCampsiteSearchResultMaxPeople();
		site.drivewayEntry = this.getCampsiteSearchResultDrivewayentry();
		site.electricityHookup = this.getCampgroundSearchResultSitesWithAmps();
		site.petAllowed = this.getCampgroundSearchResultSitesWithPetsAllowed().equals("Y");
		site.withSewerHookup = this.getCampgroundSearchResultSitesWithSewerHookup().equals("Y");
		site.waterHookup = this.getCampgroundSearchResultSitesWithWaterHookup().equals("Y");
		site.waterfront = this.getCampgroundSearchResultSitesWithWaterfront();
		site.onlineAvailability = this.getCampSearchResultAvaStatus();
		site.resChannel = this.getCampSearchResultReservationChannel();
		site.sitePhoto = this.getCampsiteSearchResultSitePhoto().replaceAll("//", "/");
		site.mapXCoordinate = this.getCampsiteSearchResultMapX();
		site.mapYCoordinate = this.getCampsiteSearchResultMapY();
		site.nssCount = this.getCampsiteSearchResultNssCount();
		site.nssAvailSitesCount = this.getCampsiteSearchResultNssAvailSitesCount();
		site.siteReserveType = this.getCampsiteSearchResultSiteResType();
		return site;
	}
	
	/** Get campsite next available date value in XML */
	public String getCampsiteNextAvailableDate() {
		return this.getCampSearchParameterValue("date");
	}
}
