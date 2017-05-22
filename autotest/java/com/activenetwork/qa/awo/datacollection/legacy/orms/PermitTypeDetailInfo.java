package com.activenetwork.qa.awo.datacollection.legacy.orms;


import java.util.ArrayList;

/**
 *Add Commercial Use Type in DB by below SQL.
		DECLARE 
		   useTypeID NUMBER(38);
		   locID NUMBER(38);
		Begin
		  locID := 72202;  --DESOLATION WILDERNESS
		  insert into d_loc_comm_use_type (id, name, loc_id, deleted_ind) values (get_sequence('d_loc_comm_use_type'), 'Outfitter and Guide', locID, '0');
		  insert into d_loc_comm_use_type (id, name, loc_id, deleted_ind) values (get_sequence('d_loc_comm_use_type'), 'Pack Outfitter', locID, '0');
		  insert into d_loc_comm_use_type (id, name, loc_id, deleted_ind) values (get_sequence('d_loc_comm_use_type'), 'Organized Group', locID, '0');
		  insert into d_loc_comm_use_type (id, name, loc_id, deleted_ind) values (get_sequence('d_loc_comm_use_type'), 'Educational', locID, '0');
		
		  select id into useTypeID from d_loc_comm_use_type where loc_id = locID and name = 'Outfitter and Guide';
		  insert into d_comm_use_serv_type (id, name, comm_use_type_id, deleted_ind) values (get_sequence('d_comm_use_serv_type'), 'Winter Mountaineering', useTypeID,'0');
		  insert into d_comm_use_serv_type (id, name, comm_use_type_id, deleted_ind) values (get_sequence('d_comm_use_serv_type'), 'Summer Mountaineering', useTypeID,'0');
		  insert into d_comm_use_serv_type (id, name, comm_use_type_id, deleted_ind) values (get_sequence('d_comm_use_serv_type'), 'Backpack', useTypeID,'0');
		  insert into d_comm_use_serv_type (id, name, comm_use_type_id, deleted_ind) values (get_sequence('d_comm_use_serv_type'), 'Ski/Snowshoe Tour', useTypeID,'0');
		
		  select id into useTypeID from d_loc_comm_use_type where loc_id = locID and name = 'Pack Outfitter';
		  insert into d_comm_use_serv_type (id, name, comm_use_type_id, deleted_ind) values (get_sequence('d_comm_use_serv_type'), 'Full Service', useTypeID,'0');
		  insert into d_comm_use_serv_type (id, name, comm_use_type_id, deleted_ind) values (get_sequence('d_comm_use_serv_type'), 'Spot-one way', useTypeID,'0');
		  insert into d_comm_use_serv_type (id, name, comm_use_type_id, deleted_ind) values (get_sequence('d_comm_use_serv_type'), 'Spot-two way', useTypeID,'0');
		  insert into d_comm_use_serv_type (id, name, comm_use_type_id, deleted_ind) values (get_sequence('d_comm_use_serv_type'), 'Dunnage-one way', useTypeID,'0');
		  insert into d_comm_use_serv_type (id, name, comm_use_type_id, deleted_ind) values (get_sequence('d_comm_use_serv_type'), 'Dunnage-two way', useTypeID,'0');
		  insert into d_comm_use_serv_type (id, name, comm_use_type_id, deleted_ind) values (get_sequence('d_comm_use_serv_type'), 'Crew', useTypeID,'0');
		
		END;
 * @author pzhu
 *
 */
public class PermitTypeDetailInfo {

	//Please do not modify the following string value!!!!!!!!!!!!!!!!!!
	public String facilityName="facility Name";
	public String permitTypeName ="Permit Type Name";
	public String permitTypeCode ="Permit Type Code";
	public String description ="Description";
	public String salesChannel ="Sales Channel";
	public String permitCategory ="Permit Category";
	public String type ="Type";
	public String overlapsAllowedProductGroups = "Overlaps Allowed Product Groups";
	public String entrance ="Entrance";
	public String entryDate ="Entry Date";
	public String groupSize ="Group Size";
	public String alternateLeaders="Alternate Leaders";


	public String capturePersonTypes ="Capture Person Types";
	public String commerciallyGuidedTrip ="Commercially Guided Trip";
	public String exitDate ="Exit Date";
	public String exitPoint ="Exit Point";
	public String lengthOfStay ="Length Of Stay";
	public String permitIssueStation="Permit Issue Station";

	public String specialAttrOfGuides ="# Of Guides";
	public String specialAttrWatercraft ="# Watercraft";
	public String specialAttrOfPets ="# of Pets";
	public String specialAttrOfStock ="# of Stock";
	public String specialAttrComments ="Comments";
	public String specialAttrCommercialUseType="Commercial Use Type";
	public String specialAttrDirectionOfTravel="Direction of Travel";
	public String specialAttrEmergencyContact ="Emergency Contact";
	public String specialAttrGroupMemberInfo ="Group Member Info";
	public String specialAttrLaunchPoint ="Launch Point";
	public String specialAttrMethodOfTravel ="Method Of Travel";
	public String specialAttrNProfitOrg="Non Profit Organization";
	public String specialAttrPmitDelMetd ="Permit Delivery Method";
	public String specialAttrReentryDate="Reentry Date";
	public String specialAttrTakeOutPoint ="Take Out Point";
	public String specialAttrTrailhead ="Trailhead";
	public String specialAttrTripItinerary ="Trip Itinerary";

	public String issueAttrActualEntryDate="Actual Entry Date";
	public String issueAttrBoatLeader ="Boat Leader";
	public String issueAttrTravelPlan ="Travel Plan";
	public String issueAttrVehiclePlate ="Vehicle Plate #";

	public String issueTimeMandatoryAttributes="Issue Time Mandatory Attributes";


	public String primaryQuotaType ="Primary Quota Type";
	public String AdditionalQuotaTypes="Additional Quota Types";
	public String personTypes ="Person Types";
	public String personTypeQuotaDepletions ="Person Type Quota Depletion";

	public static class AttributeInfo
	{
		public String attrname;
		public String[] attr;
	}
	public ArrayList<AttributeInfo> attrArray = new ArrayList<AttributeInfo>();

	public String getAttributeInfo(String attributeName)
	{
		for(AttributeInfo at: attrArray)
		{
			if(at.attrname.equalsIgnoreCase(attributeName))
			{
				return arrayToString(at.attr,"|");
			}
		}
		return "";
	}
	public static String arrayToString(String[] a, String separator) {
		if (a == null) {
			return "";
		}
		String result = "";
		if (a.length > 0) {
			result = a[0]; // start with the first element
			for (int i = 1; i < a.length; i++) {
				result = result + separator + a[i];
			}
		}
		return result;
	}

}

/**@ This is example of the data which should be filled into datapool:
 * single value in single field : [***]
 * multiple value in multiple fields: [***][***][***], each [***] represent one value of one field
 * multiple value of multiple lines: {[Overnight Quota][1][Per Permit][On Entry]},{[Overnight Quota][1][Per Permit][On Entry]} 
 * 
 * 
facility Name: [Boundary Waters Canoe Area Wilderness (Reservations)]
Permit Type Name: [1 Child, 1 Adult]
Permit Type Code: [12E]
Description: [qa test]
Sales Channel: [Field,Call Center,Web]
Permit Category: [All]
Entrance: [Mandatory]
Entry Date: [Mandatory]
Group Size: [Applicable][Applicable]
Alternate Leaders: [Not Applicable][3]
Capture Person Types: [Capture Person Types]
Commercially Guided Trip: [Mandatory]
Exit Date: [Optional][true][Applicable][true]
Exit Point: [Not Applicable]
Length Of Stay: [Not Applicable][Applicable][Daily]
Permit Issue Station: [Not Applicable]
# Of Guides: [Mandatory]
# Watercraft: [Mandatory][Applicable][Raft,Canoe,Other]
# of Pets: [Optional][Applicable][Dog,Horse,Dog or other pets]
# of Stock: [Optional][Applicable][Applicable][Burro,Horse - Riding]
Commercial Use Type: [Mandatory]
Emergency Contact: [Mandatory][2][QA test]
Group Member Info: [Mandatory][Applicable][1]
Launch Point: [Optional]
Method Of Travel: [Mandatory][true]
Non Profit Organization: [Optional]
Permit Delivery Method: [Applicable]
Reentry Date: [Optional]
Take Out Point: [Optional]
Trailhead: [Optional]
Trip Itinerary: [Optional][true]
Actual Entry Date: [Optional]
Boat Leader: [Optional]
Travel Plan: [Optional]
Vehicle Plate #: [Optional]
Issue Time Mandatory Attributes: [# Of Guides,Exit Date,# of Stock]
Primary Quota Type: [Overnight Quota][1][Per Permit][On Entry]
Additional Quota Types: [Overnight Quota][1][Per Permit][On Entry]
Additional Quota Types: [Overnight Quota][1][Per Permit][On Entry]
Person Types: [A Guide][All][All]
Person Types: [A Guide][All][All]
 * */
