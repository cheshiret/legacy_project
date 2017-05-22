package com.activenetwork.qa.awo.datacollection.datadefinition.web.config;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.util.StringProcessor;
import com.activenetwork.qa.testapi.util.Property;

/**
 * Define the attributes of ui-options.xml file
 * @author jdu
 *
 */
public enum UIOptions implements XmlAttribute {
	UnifiedSearch(new String[]{"unified-search-form"},"visible", StringProcessor.booleanor(true,new String[]{"","true"})),
	ExcludedActivity(new String[]{"excluded-activities"},"value",StringProcessor.replacor(new String[]{"/",",",",$",""})),
	Threshold(new String[]{"threshold"},"value"),
	AllowDateChange(new String[] {"allow-existing-camping-reservation-date-change"}, "contracts"),
	AvailabilityNotification(new String[]{"availabilityNotification"},"contracts"),
	TypeFilters(new String[]{"interest-filters"},"visible", StringProcessor.booleanor(true,new String[]{"","true"})),
	AgencyFilters(new String[]{"agency-filters"},"visible", StringProcessor.booleanor(true,new String[]{"","true"})),
	LetterFilters(new String[]{"letter-filters"},"visible", StringProcessor.booleanor(true,new String[]{"","true"})),
	LetterFilterInteractiveMode(new String[]{"letter-filters"},"value", StringProcessor.booleanor(true,new String[]{"interactive"})),
	ShowCustomerPasses(new String[]{"show-customer-passes"},"visible", StringProcessor.booleanor(true,new String[]{"","true"})),
	
	//PLW specific
	HFWebAuthByIdentifier(new String[]{"use-hf-authenticate-by-identifier"},"visible", StringProcessor.booleanor(true,new String[]{"","true"})),
	PLFacilityMapShownAsLink(new String[]{"display-facility-map-as-link"},"visible", StringProcessor.booleanor(true,new String[]{"","true"})),
	ContractsWithHFWebHideValidDates(new String[] {"hide-validitydates", "order-history"}, "contracts"),
	HFWebDisplayValidDates(new String[]{"display-validitydates"},"visible", StringProcessor.booleanor(true,new String[]{"","true"})),
	LotteryHuntGroupMinVisiable(new String[]{"lottery", "hunts", "groupMin"},"visible", StringProcessor.booleanor(true,new String[]{"","true"})),
	LotteryHuntGroupMaxVisiable(new String[]{"lottery", "hunts", "groupMax"},"visible", StringProcessor.booleanor(true,new String[]{"","true"})),
	LotteryHuntSpeciesVisiable(new String[]{"lottery", "hunts", "species"},"visible", StringProcessor.booleanor(true,new String[]{"","true"})),
	
	LotteryHuntWeaponVisiable(new String[]{"lottery", "hunts", "weapon"},"visible", StringProcessor.booleanor(true,new String[]{"","true"})),
	LotteryHuntDatePeriodVisiable(new String[]{"lottery", "hunts", "datePeriod"},"visible", StringProcessor.booleanor(true,new String[]{"","true"})),
	LotteryHuntLocationVisiable(new String[]{"lottery", "hunts", "location"},"visible", StringProcessor.booleanor(true,new String[]{"","true"})),
	LotteryHuntSubLocationsVisiable(new String[]{"lottery", "hunts", "sublocations"},"visible", StringProcessor.booleanor(true,new String[]{"","true"})),
	
	LotteryHuntsSearchResultPageSize(new String[]{"lottery", "search-results", "page-size", "hunts"},"value"),
	EnteredLotteryHuntCodeExist(new String[]{"lottery", "huntInformation", "member-lottery-applications", "entered", "code"},"auto_check_exists"),
	EnteredLotteryHuntSpeciesExist(new String[]{"member-lottery-applications", "entered", "species"},"auto_check_exists"),
	EnteredLotteryHuntWeaponExist(new String[]{"member-lottery-applications", "entered", "weapon"},"auto_check_exists"),
	
	EnteredLotteryHuntDatePeriedExist(new String[]{"member-lottery-applications", "entered", "datePeried"},"auto_check_exists"),
	EnteredLotteryHuntLocationExist(new String[]{"member-lottery-applications", "entered", "location"},"auto_check_exists"),
	AwardedLotteryHuntCodeExist(new String[]{"member-lottery-applications", "awarded", "code"},"auto_check_exists"),
	AwardedLotteryHuntSpeciesExist(new String[]{"member-lottery-applications", "awarded", "species"},"auto_check_exists"),
	AwardedLotteryHuntWeaponExist(new String[]{"member-lottery-applications", "awarded", "weapon"},"auto_check_exists"),
	AwardedLotteryHuntDatePeriedExist(new String[]{"member-lottery-applications", "awarded", "datePeried"},"auto_check_exists"),
	AwardedLotteryHuntLocationExist(new String[]{"member-lottery-applications", "entered", "location"},"auto_check_exists"),
	HFWebLotteryTopLinkShown(new String[]{"BigGameDraw"},"caption",StringProcessor.booleanor(false,new String[]{"",null})),
	HFABWebLotteryTopLinkShown(new String[]{"Lottery"},"caption",StringProcessor.booleanor(false,new String[]{"",null})),
	
	HFSKPointAccountsAttributes(new String[]{"point-accounts-attributes", "SK"},"value"),
	HFSKPointDetailsAttributes(new String[]{"point-details-attributes", "SK"},"value"),
	;

	private List<Property[]> prop;
	private String attr;
	private StringProcessor[] processors;
	
	private UIOptions(String[] names, String attr, StringProcessor... processors) {
		this.prop=new ArrayList<Property[]>();
		for(String name:names) {
			prop.add(Property.toPropertyArray("tag", "option","name", name));
		}
		
		this.processors=processors;
		this.attr=attr;
	}
	
	private UIOptions(String[] names, String attr) {
		this.prop=new ArrayList<Property[]>();
		for(String name:names) {
			prop.add(Property.toPropertyArray("tag", "option","name", name));
		}
		
		this.processors=null;
		this.attr=attr;
	}
	@Override
	public Class<?> getType() {
		return String.class;
	}
	@Override
	public List<Property[]> getProperty() {
		return prop;
	}
	@Override
	public String getAttributeName() {
		return attr;
	}
	@Override
	public StringProcessor[] getProcessors() {
		return processors;
	}

}
