/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.qasetup.inventory;


import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityDetail.InvMgrFacilityDetailPage;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.inventory.AddTourParticipantDataFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:AUTO-967
 * 
 * @author asun
 * @Date  Jun 8, 2012
 */
public class AddTourParticipantData extends SetupCase {
	
	private LoginInfo login=new LoginInfo();
	private String facilityID;
	private String tourName;
	private boolean isCombTour;
	private String recordId;
	private String[] attrs;
	String[] groups;
	String[] labels;
	String[] methods;
	String[] sort_orders;
	String[] isIndvs;
	String[] isMandatorys;
	InvMgrFacilityDetailPage invFaciDetailPg = InvMgrFacilityDetailPage.getInstance();
	private AddTourParticipantDataFunction addTour = new AddTourParticipantDataFunction();
	
	@Override
	public void executeSetup() {
		Object[] args = new Object[12];
		args[0] = login;
		args[1] = facilityID;
		args[2] = recordId;
		args[3] = isCombTour;
		args[4] = tourName;
		args[5] = attrs;
		args[6] = groups;
		args[7] = labels;
		args[8] = methods;
		args[9] = sort_orders;
		args[10] = isIndvs;
		args[11] = isMandatorys;
		
		addTour.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("CONTRACT");
		login.location=datasFromDB.get("ROLE_LOCATION");
		recordId =datasFromDB.get("ID");
		facilityID=datasFromDB.get("PARKID");
		isCombTour=datasFromDB.get("ISCOMBTOUR").equals("1");
		tourName=datasFromDB.get("TOUR_NAME");
		attrs= datasFromDB.get("ATTR").split("\\|");
		groups=datasFromDB.get("GROUP_NAME").split("\\|");
		labels=datasFromDB.get("LABEL").split("\\|");
		methods=datasFromDB.get("COLLECTION_METHOD").split("\\|");
		sort_orders=datasFromDB.get("SORT_ORDER").split("\\|");
		isIndvs=datasFromDB.get("ISINDIVIDUAL").split("\\|");
		isMandatorys=datasFromDB.get("MANDATORY").split("\\|");
	}

	@Override
	public void wrapParameters(Object[] param) {
		queryDataSql = "";

		dataTableName = "d_inv_tpa";;
		
	    //Initialize login informaiton
	  	login.url = AwoUtil.getOrmsURL(env);
	    login.userName = TestProperty.getProperty("orms.adm.user");
	    login.password = TestProperty.getProperty("orms.adm.pw"); 
	}

}
