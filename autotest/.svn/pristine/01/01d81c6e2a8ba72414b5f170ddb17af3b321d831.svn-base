package com.activenetwork.qa.awo.sql;

import java.util.Enumeration;

import com.activenetwork.qa.awo.util.AwoDatabase;


public class AddSiteSql {

	public SiteDataForDB sd;

	public AwoDatabase omConn;

	public AddSiteSql() {
		sd = new SiteDataForDB();
		omConn = AwoDatabase.getInstance();
	}

	/**
	 * Check if specific site has be added
	 * @param prdCd: product code
	 * @return  --false: the site has be added
	 *          --true: the site need add
	 */
	public boolean checkSiteExist(String prdCd){
		boolean needAddSite = false;
		String query = "select count(*) as num from P_PRD where PRD_CD = '"+ prdCd +"'";
		String num = omConn.executeQuery(query, "num", 0);
		if(Integer.parseInt(num)<=0)
		{
			needAddSite = true;
			System.out.println("The site which site code = "+prdCd+" should be inserted into DB!");
		}else {
			System.out.println("The site which site code = "+prdCd+" has been added into DB!");
		}
		return needAddSite;
	}

	/**
	 * Add site 
	 * @param sd: SiteDataForDB
	 * @return
	 */
	public void addSite(SiteDataForDB sd){
		//Find the max product id 
		String query = "select get_sequence('P_PRD') as ID from dual";
		String id = omConn.executeQuery(query, "ID", 0);
		//Add site
		query = "insert into P_PRD "
			+ "(PRD_ID, PRD_CD, PRD_DSCR, PRD_GRP_ID, PRD_NAME, ACTIVE_IND, DELETED_IND, LOC_ID, PARK_ID,"
			+ "PRD_REL_TYPE, PARENT_ID, UNIT_OF_STAY_TYPE_ID, PRD_RATE_TYPE_ID, " 
			+ "IMPORT_RESERVABLE,IMPORT_WEB_RESERVABLE,PRODUCT_CAT_ID) " 
			+ "values ('" 
			+ id + "','" + sd.prd_cd + "','"+ sd.prd_dscr +"',"+ sd.prd_grp_id +",'"+ sd.prd_name +"'," 
			+ "1, 0,"+ sd.loc_id +","+ sd.park_id +","+ sd.prd_rel_type +","
			+ + sd.parent_id +","+ sd.unit_Of_Stay_Type_Id +","+ sd.prdRateTypeId +","
			+ "'Y','Y',1)";
		omConn.executeUpdate(query);
		System.out.println("The site which site name = "+sd.prd_name+" inserts into DB successfully!");
	}

	/** Add a site. */
	public void run() {

		String query = "select max(PRD_ID) as prd_id from P_PRD";
		int prd_id = Integer.parseInt(((String) omConn.executeQuery(query,
		"prd_id").get(0)).trim());
		System.out.println("PRD_ID: " + prd_id++);

		query = "insert into P_PRD "
			+ "(PRD_ID, FILTER_LOC, INV_IND, PRD_CD, PRD_DSCR, PRD_GRP_ID, "
			+ "PRD_NAME, STATUS_ID, ACTIVE_IND, DELETED_IND, LOC_ID, PARK_ID,"
			+ "IMPORT_ID, EQPSET_ID, PRD_REL_TYPE, PARENT_ID, IMPORT_TYPE, IMPORT_RESERVABLE,"
			+ "IMPORT_WEB_RESERVABLE ) " +//PRD_REL_TYPE: 1- site specific, 2 - Non site specific parent, 3 - Non site specific child.

			"values  ( " + prd_id + ", '0', NULL," + sd.prd_cd + ","
			+ sd.prd_dscr + "," + sd.prd_grp_id + "," + sd.prd_name
			+ ", NULL," + sd.active_ind + "," + sd.deleted_ind + ","
			+ sd.loc_id + "," + sd.park_id + ","
			+ " null, null, 1, 0, 'TEST', 'Y', " + " 'Y' )";

		omConn.executeUpdate(query);

		Enumeration<?> enumerator = sd.attrs.keys();

		while (enumerator.hasMoreElements()) {

			query = "select max (PRD_ATTR_ID) as prd_attr_id from P_PRD_ATTR";
			int prd_attr_id = Integer.parseInt(((String) omConn.executeQuery(
					query, "prd_attr_id").get(0)).trim());
			System.out.println("PRD_ID: " + prd_attr_id++);

			String attr_name = (String) enumerator.nextElement();

			query = "select ATTR_ID from D_ATTR where ATTR_NAME = '"
				+ attr_name + "'";
			int attr_id = Integer.parseInt((String) omConn.executeQuery(query,
			"ATTR_ID").get(0));

			String attr_value = (String) sd.attrs.get(attr_name);

			query = "insert into P_PRD_ATTR (    PRD_ATTR_ID, ATTR_ID, PRD_ID, STATUS_ID, ACTIVE_IND, DELETED_IND, ATTR_VALUE) "
				+ "  VALUES ( "
				+ prd_attr_id
				+ ","
				+ attr_id
				+ ","
				+ prd_id + ", null, '1', '0',+ " + attr_value + ")";

			omConn.executeUpdate(query);
		}

	}

	public static void main(String[] args) {

		AddSiteSql sl = new AddSiteSql();

		String attr_name = "Field Referral";
		String query = "select ATTR_ID from D_ATTR where ATTR_NAME = '"
			+ attr_name + "'";
		int attr_id = Integer.parseInt((String) sl.omConn.executeQuery(query,
		"ATTR_ID").get(0));

		System.out.print(attr_id);
	}

}
