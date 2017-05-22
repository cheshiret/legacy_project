package com.activenetwork.qa.awo.supportscripts.qasetup.unused;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class EnvironmentCleanup extends SupportCase {
	/**
	 * @since  2011/01/26
	 * @author QA
	 */
	AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	String env = "";
	String schema_pref = "";
	String parkName = "";
	String contract = "";
	String season = "";
	String schema = "";
	List<String[]> results = null;
	String queryIDs = "";
	String query = "";
	String[] colNames = {"ID", "BOOKING_ID"};
	String ordIDs = "";
	String bookingIDs = "";
	//handle 'maximum number of expressions in a list is 1000' when using in() method in a query
	List<String> ordIDSet= new ArrayList<String>();
	List<String> bookingIDSet= new ArrayList<String>();

	public void wrapParameters(Object[] param) {
		
		startpoint=114;
		endpoint=114;
	  	
	  	dataSource = casePath + "/FacilityList";
	  	
	  	//log information
	  	logMsg = new String[4];
		logMsg[0] = "parkName";
		logMsg[1] = "contract";
		logMsg[2] = "season";
		logMsg[3] = "result";
		
		env = TestProperty.getProperty("target_env");
		schema_pref = TestProperty.getProperty(env+".db.schema.prefix");
	}
	
	public void execute() {
		String parkID=this.getParkID(parkName,schema);
		System.out.println("Cleaning up \""+parkName+"\" with id="+parkID+" in "+contract+" in schema "+schema);
		
		this.voidReservations(parkID, schema);
		this.noshowReservations(parkID, schema);
		this.cancelReservations(parkID, schema);
		
		System.out.println("Done.");
		logMsg[3] = "successful";
	}

	public void getNextData() {
		parkName = dpIter.dpString("park_name");
		contract=dpIter.dpString("contract_abbr");
		season = dpIter.dpString("season");
		
		schema = schema_pref+contract;
		
		//log information
		logMsg[0] = parkName;
		logMsg[1] = contract;
		logMsg[2] = season;
		logMsg[3] = "Fail";
	}

	/**
	 * Void any reservations that are active/checkedIn and also Clear out i_inv_used records
	 * @param parkIDs
	 * @param schema
	 */
	private void voidReservations(String parkIDs, String schema) {
		db.resetSchema(schema);
		db.connect();

		queryIDs = "select o.id, oi.booking_id from o_order o, o_ord_item oi where "
			+ "o.id = oi.ord_id and o.proc_status_id = 2 and o.ord_status_id = 1 "
			+ "and o.loc_id in (" + parkIDs + ")";

		results = db.executeQuery(queryIDs, colNames);
		if(results.size()>0) {
			System.out.println("Void any reservations that are active/checkedIn and also Clear out i_inv_used records");
			this.parseQueryResultToString(results);
			
			if(ordIDSet.size()>0) {
				for(int i=0; i<ordIDSet.size(); i++) {
					query = "delete from i_inv_used where booking_id in (" +bookingIDSet.get(i)+")";
					db.executeUpdate(query);
					
					query = "update o_ord_item set booking_id = 0 where ord_id in (" + ordIDSet.get(i) + ")";
					db.executeUpdate(query);
					
					//update order status to No show
					query = "update o_order set ord_status_id = 3, concurrency_version_num = concurrency_version_num + 1 where "
						+ "id in (" + ordIDSet.get(i) + ")";
					db.executeUpdate(query);
				}
			} else {
				query = "delete from i_inv_used where booking_id in (" +bookingIDs+")";
				db.executeUpdate(query);
				
				query = "update o_ord_item set booking_id = 0 where ord_id in (" + ordIDs + ")";
				db.executeUpdate(query);
				
				//update order status to No show
				query = "update o_order set ord_status_id = 3, concurrency_version_num = concurrency_version_num + 1 where "
					+ "id in (" + ordIDs + ")";
				db.executeUpdate(query);
			}
			
			this.resetQueryResultSet();
		} else {
			System.out.println("No reservations need to void.");
		}
		db.disconnect();
	}

	/**
	 * NoShow any reservations that are active/preArrival with arrivalDate before today
	 * @param parkIDs
	 * @param schema
	 */
	private void noshowReservations(String parkIDs, String schema) {
		db.resetSchema(schema);
		db.connect();
		queryIDs = "select o.id, oi.booking_id from o_order o, o_ord_item oi where "
			+ "o.id = oi.ord_id and o.proc_status_id = 1 and o.ord_status_id = 1 "
			+ "and oi.start_date <= trunc(sysdate)  and o.loc_id in (" + parkIDs + ")";

		results = db.executeQuery(queryIDs, colNames);
		if(results.size()>0) {
			System.out.println("NoShow any reservations that are active/preArrival with arrivalDate before today.");
			this.parseQueryResultToString(results);
			
			if(ordIDSet.size()>0) {
				for(int i=0; i<ordIDSet.size(); i++) {
					query = "delete from i_inv_used where booking_id in (" +bookingIDSet.get(i)+")";
					db.executeUpdate(query);
					
					query = "update o_ord_item set booking_id = 0 where ord_id in (" + ordIDSet.get(i) + ")";
					db.executeUpdate(query);
					
					//update order status to No show
					query = "update o_order set ord_status_id = 4, concurrency_version_num = concurrency_version_num + 1 where "
						+ "id in (" + ordIDSet.get(i) + ")";
					db.executeUpdate(query);
				}
			} else {
				query = "delete from i_inv_used where booking_id in (" +bookingIDs+")";
				db.executeUpdate(query);
				
				query = "update o_ord_item set booking_id = 0 where ord_id in (" + ordIDs + ")";
				db.executeUpdate(query);
				
				//update order status to No show
				query = "update o_order set ord_status_id = 4, concurrency_version_num = concurrency_version_num + 1 where "
					+ "id in (" + ordIDs + ")";
				db.executeUpdate(query);
			}
			
			this.resetQueryResultSet();
		} else {
			System.out.println("No reservations need to no show.");
		}
		db.disconnect();
	}

	/**
	 * Cancel any reservations that are active/preArrival with arrivalDate after today
	 * @param parkIDs
	 * @param schema
	 */
	private void cancelReservations(String parkIDs, String schema) {
		db.resetSchema(schema);
		db.connect();

		queryIDs = "select o.id, oi.booking_id from o_order o, o_ord_item oi where "
		+ "o.id = oi.ord_id and o.proc_status_id = 1 and o.ord_status_id = 1 "
		+ "and oi.start_date > trunc(sysdate) and o.loc_id in (" + parkIDs + ")";
		
		results = db.executeQuery(queryIDs, colNames);
		if(results.size()>0) {
			System.out.println("Cancel any reservations that are active/preArrival with arrivalDate after today.");
			this.parseQueryResultToString(results);
			
			if(ordIDSet.size()>0) {
				for(int i=0; i<ordIDSet.size(); i++) {
					query = "delete from i_inv_used where booking_id in (" +bookingIDSet.get(i)+")";
					db.executeUpdate(query);
					
					query = "update o_ord_item set booking_id = 0 where ord_id in (" + ordIDSet.get(i) + ")";
					db.executeUpdate(query);
					
					//update order status to No show
					query = "update o_order set ord_status_id = 2, concurrency_version_num = concurrency_version_num + 1 where "
						+ "id in (" + ordIDSet.get(i) + ")";
					db.executeUpdate(query);
				}
			} else {
				query = "delete from i_inv_used where booking_id in (" +bookingIDs+")";
				db.executeUpdate(query);
				
				query = "update o_ord_item set booking_id = 0 where ord_id in (" + ordIDs + ")";
				db.executeUpdate(query);
				
				//update order status to No show
				query = "update o_order set ord_status_id = 2, concurrency_version_num = concurrency_version_num + 1 where "
					+ "id in (" + ordIDs + ")";
				db.executeUpdate(query);
			}
						
			this.resetQueryResultSet();
		} else {
			System.out.println("No reservations need to cancel.");
		}
		db.disconnect();
	}

	private void parseQueryResultToString(List<String[]> result) {
		if(result.size()>1000) {
			for(int i=0; i<result.size(); i++) {
				ordIDs = ordIDs +result.get(i)[0]+",";
				bookingIDs = bookingIDs +result.get(i)[1]+",";
				if((i+1)%1000 == 0) {// 0%1000 will be 0 too
					ordIDs = ordIDs.substring(0, ordIDs.length()-1);
					ordIDSet.add(ordIDs);//add order id to a list for every 1000 rows
					ordIDs = "";//reset order id string
					
					bookingIDs = bookingIDs.substring(0, bookingIDs.length()-1);
					bookingIDSet.add(bookingIDs);//add booking id to a list for every 1000 rows
					bookingIDs = "";// reset booking id string
				}
			}
		} else {
			for(int i=0; i<result.size(); i++) {
				ordIDs = ordIDs +result.get(i)[0]+",";
				bookingIDs = bookingIDs +result.get(i)[1]+",";
			}
		}
		
		//handle the data after the last time of %
		if(result.size()>1000 && ordIDs.length()>0) {
			ordIDs = ordIDs.substring(0, ordIDs.length()-1);
			ordIDSet.add(ordIDs);//add order id to a list for every 1000 rows
			
			bookingIDs = bookingIDs.substring(0, bookingIDs.length()-1);
			bookingIDSet.add(bookingIDs);//add booking id to a list for every 1000 rows
		} else {
			ordIDs = ordIDs.substring(0, ordIDs.length()-1);
			bookingIDs = bookingIDs.substring(0, bookingIDs.length()-1);
		}
	}
	
	private void resetQueryResultSet() {
		ordIDs = "";
		bookingIDs = "";
		ordIDSet.clear();
		bookingIDSet.clear();
	}
	
	private String getParkID(String parkName, String schema) {
		db.resetSchema(schema);
		db.connect();
		
		String query="select id from d_loc where UPPER(name) = UPPER('"+parkName+"')";
		String id=db.executeQuery(query,"id",0);
		db.disconnect();
		
		return id;		
	}
}
