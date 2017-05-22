package com.activenetwork.qa.awo.sql;

import java.util.Hashtable;
public class SiteDataForDB {
	public String filterLoc = "0";
	public String invInd = "Null";
	public String prd_id;
	public String prd_cd;
	public String prd_dscr;
	public String prd_name;
	public String active_ind;
	public String deleted_ind;
	public int prd_grp_id;//select * from P_PRD_GRP where prd_grp_name = 'Amphitheater';
	public int loc_id;
	public int park_id;
	public int prd_rel_type; //1- site specific, 2 - Non site specific parent, 3 - Non site specific child.
	public int parent_id;
	public int unit_Of_Stay_Type_Id; //1-overnight,2-Day, 3-Hour, 4-None
	public int prdRateTypeId; //Rate type of the product, possible values are: 1(Family), 2(Group), 3(both)
	public String importReservable;
	public String importWebReservable;

	/**
	 * those are attribute for Site
	 *
	 */

	Hashtable<String,Object> attrs;

	/*	
	 public String attr_id;
	 public String attr_value;
	 
	 public String BBQ; 					// ATTR_ID = 3
	 public String wood_heating;			// ATTR_ID = 4
	 public String site_length;			// ATTR_ID =5
	 public String site_width;			// ATTR_ID = 6
	 public String site_height_overhead_clearance; 		//7
	 public String max_num_vehicles;		// ATTR_ID = 9
	 public String shade; 				// ATTR_ID = 10
	 public String driveway_length;		// ATTR_ID = 11		
	 public String max_num_peoples;		// ATTR_ID = 12
	 public String driveway_entry;		// ATTR_ID = 13
	 public String platform;				// ATTR_ID = 14
	 public String checkin_time;			// ATTR_ID = 15
	 public String checkout_time;		// ATTR_ID = 16
	 public String driveway_grade;		// ATTR_ID = 26
	 public String fax_hookup;			// ATTR_ID = 35
	 public String cable_TV_access;		// ATTR_ID = 37
	 public String fire_pit;				// ATTR_ID = 39
	 public String clothes_line;			// ATTR_ID = 41
	 public String food_locker;			// ATTR_ID = 42
	 public String map_x_coordinate;		// ATTR_ID = 100
	 public String map_y_coordinate;		// ATTR_ID = 101
	 public String type_of_use;			// ATTR_ID = 102
	 public String ADA_accessible;		// ATTR_ID = 104
	 public String site_rating;			// ATTR_ID = 105
	 public String driveway_surface;		// ATTR_ID = 108
	 public String max_vehicle_length;	// ATTR_ID = 109
	 public String min_num_people;		// ATTR_ID = 111
	 public String placed_on_map;		// ATTR_ID = 114
	 public String double_driveway;		// ATTR_ID = 202
	 public String condition_rating;		// ATTR_ID = 204
	 public String site_access;			// ATTR_ID = 207	
	 public String min_num_vehicles;		// ATTR_ID = 209
	 public String base_num_vehicles;	// ATTR_ID = 210
	 public String capacity_size_rating;	// ATTR_ID = 211
	 public String base_num_people;		// ATTR_ID = 212
	 public String site_reserve_type; 	// ATTR_ID = 216
	 public String electricity_hookup;	// ATTR_ID = 218
	 public String pets_allowed;			// ATTR_ID = 220
	 public String campfire_allowed;		// ATTR_ID = 222
	 public String group_allowed;		// ARRR_ID = 224
	 public String full_hookup;			// ATTR_ID = 239
	 */

	public SiteDataForDB() {
		prd_cd = "";
		prd_dscr = "";
		prd_name = "";
		active_ind = "1";
		deleted_ind = "0";

		prd_grp_id = 0;
		loc_id = 0;
		park_id = 0;
		prd_rel_type = 1;
		parent_id = 0;
		unit_Of_Stay_Type_Id = 1;
		prdRateTypeId = 1;
		importReservable = "Y";
		importWebReservable = "Y";
		
		attrs = new Hashtable<String,Object>();

	}

}
