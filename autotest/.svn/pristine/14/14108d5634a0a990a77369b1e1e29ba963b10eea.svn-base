package com.activenetwork.qa.awo.datacollection.legacy.web;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Aug 4, 2011
 */
public class UwpUnifiedSearch {
	
	//Where 
	public final static String DEFAULT_WHEREVALUE = "Enter a location";
	
	//Heading 
	public final static String HEADING_ADDRESSES = "Locations"; //"Destinations"; //"Addresses";
	public final static String HEADING_FACILITIES = "Destinations"; //"Experience"; //Experience"; //"Facilities";
	public final static String HEADING_WITHIN_STATE = "Within State";
	public final static String HEADING_DISCOVER = "Discover";
	
	//Arrival Date
	public final static String ARRIVALDATE_MMDDYY = "MM/DD/YY";
	
	//Interest in
	public final static String DEFAULT_INSTERETED_IN="-- Please Select --";
	public final static String EVERYTHING_INSTERETED_IN="All"; //"Everything";
	public final static String CAMPINGANDLODGING_INSTERETED_IN="Camping & Lodging";
	public final static String DAYUSEPICNICAREAS_INSTERETED_IN="Day use & Picnic areas";
	public final static String PERMITSANDWILDERNESS_INSTERETED_IN="Permits & Wilderness";
	public final static String TOURSTICKETS_INSTERETED_IN="Tours & Tickets";
	public final static String OTHERACTIVITIES_INSTERETED_IN="Things to Do"; //"More"; //"Other Activities";
	
	//Type filter 
	public final static String FILTER_CAMPINGANDDAYUSE_LINKTITLE = "Camping & Lodging/Day use & Picnic areas"; // link title
	public final static String FILTER_CAMPINGANDDAYUSE_NAME = "Camping & Day Use";
	public final static String FILTER_PERMITANDWILTERNESS = "Permits & Wilderness";
	public final static String FILTER_TOURSANDTICKETS = "Tours & Tickets";
	public final static String FILTER_OTHERACTIVITIES_LINKTITLE = "Other Recreation Facilities";
	public final static String FILTER_OTHERACTIVITIES_NAME = "Other Activities";
	
	//Explore recreation activities
	public final static String EXPLORE_BIKING = "Biking";
	public final static String EXPLORE_CAMPING= "Camping";
	public final static String EXPLORE_VISITORCENTERS= "Visitor Centers";
	public final static String EXPLORE_HIKING= "Hiking";
	public final static String EXPLORE_WINTERSPORTS= "Winter Sports";
	public final static String EXPLORE_CLIMBING= "Climbing";
	public final static String EXPLORE_HUNTING= "Hunting";
	
	//Filter Category
	public final static String FILTERCATEGORY_FILTERYOURRESULTS= "Filter Your Results";
	public final static String FILTERCATEGORY_TYPE= "Type";
	public final static String FILTERCATEGORY_AGENCY= "Agency";
	public final static String FILTERCATEGORY_FIRSTLETTEROFNAME= "First Letter of Name";
	
	//Flexible
	public final static String FLEXIBLE_DEFAULT = "Not Flexible";
	public final static String FLEXIBLE_NEXT2WEEK= "Flexible for next 2 weeks";
	public final static String FLEXIBLE_NEXT4WEEK= "Flexible for next 4 weeks";
	
	//Park type in view as list page
	public final static String PARKTYPE_CAMPINGANDLODGING= "Camping and Lodging";
	public final static String PARKTYPE_DAYUSEANDPICNICAREAS= "Day use and Picnic areas";
	
	//Invalid values to verify error message in unified search and 'Find sites' panel for 'length of stay', 'length' and 'occupant'
//	public final static String[] INVALID_VALUES = new String[]{"0.5", "-0.5", "-150", "abc", "ABC", "#10", "1 0", " 0 0 ", "1,5", "20.0", "20@", "&2;*1", "+12", "=5"};
	public final static String[] INVALID_VALUES = new String[]{"0.5", "-0.5", "-150", "abc", "ABC", "#10", "1 0", " 0 0 ", "1,5", "20.0", "20@", "&2;*1", "=5"}; //Lesley[20140318]remove +12 as it is valid value according to the email confirmed by Sara
	
	//Where text field
	public String whereTextValue = "";
	public boolean inputWhereByChar=false;
	public String[] whereTextValues = null;
	public String[] autocompleteOptions = null;
	public String matchType = "";//Facilities, Addresses
	public String selectedAutoCompletedOption = "";
	public String hintMes = "";
	public String parkId="";
	public String contractCode="";//Contract code, the short name for each contract, contract code should be all capital
	
	
	//Interest In drop down list
	public String interestInValue = "";
	public String[] interestInValues = null;
	
	//Day use & Picnic areas
	public String arrivalDate = ""; //Arrival date
	public String depatureDate = StringUtil.EMPTY;
	public String flexibleValue = "";
	public String[] flexibleValues = null;
	public String occupants = "";
	public boolean moreOptions = false;//Use for selecting More Options check box
	public String electricHookupValue = "";
	public String[] electricHookupValues = null;
	public boolean waterFront = false;//Use for selecting Water front check box
	public String lengthOfStay = ""; //Used for "Length of Stay" and "Duration"
	public boolean accessibilityNeeds = false;//Use for selecting Accessibility Needs check box
	public boolean petsAllowed = false;//Use for selecting Pets Allowed check box 

	public boolean clickClearSearch = false;//Use for clicking Clear Search link
	public boolean clickSearch = true;//Use for clicking Search button
    public List<String> facilityList = new ArrayList<String>();
    
    //Camping&Lodging
    //more options
    public String lookFor="";
    public boolean waterHookup=false;
    public boolean sewerHookup=false;
    public boolean pullthroughDriveWay=false;
    public String length="";  //length (ft)
    
    //Other activities
    public String otherActivityName = "";
    public String[] otherActivitiesName = new String[]{};
    public int otherActivityId = 0;
    public String excludedActivities = "32,30,27,11,44,10,39,38,20,23,43,34,25,28";
	public String date;

	public boolean selectAutoCompleteOption;

	public int selectedAutoCompletedOptionIndex;
	
	public boolean selectExactOption = false;//Using select option from auto completed box or suggestion in 'SUggestion' page
}
