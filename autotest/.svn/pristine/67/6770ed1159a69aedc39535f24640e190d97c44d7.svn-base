/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafeethreshold;


import java.util.HashMap;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeThresholdsDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;




/**
 * @Description:1. Goto adding new RA Fee threshold schedule
				2. Go to fee detail page. check relationship of Dock and Product.
 * 
 * @Preconditions: feature 'CreateModifyRAFees' has been assigned to role 'Administrator-Auto'
 * @SPEC:
 * @Task#:AUTO-1334
 * TC042197: Assignment Selection - Dock/Area/Loop&Product 
 * @author pzhu
 * @Date  Nov 26, 2012
 */

public class VerifyDockAndProductSelection_Slip extends FinanceManagerTestCase {
	private ScheduleData schedule = new ScheduleData();
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	private FinMgrRaFeeThresholdsDetailPage detailPg = FinMgrRaFeeThresholdsDetailPage
	.getInstance();
	
	private String[][] features ={
			{"CreateModifyRAFees",	"Create/Modify RA Fees"},
			};

	private List<String> allDockElements;
	private List<String> allProductElements;
	private String locationID;
	private static final String TYPE_PARK = "7";

	@Override
	public void execute() {
		
		fnm.checkRolesFeatures("Administrator", this.features, FINANCE_MANAGER, schema);
		// login finance manager
		fnm.loginFinanceManager(login);

		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeThresholdPage();
		
		fnm.gotoAddRaFeeThresholdPg(schedule);
		//check point 1:
		this.verifyDockAndProductSelection();
		
		//check point 2:
		this.verifyProductAndDockFilter();

		fnm.logoutFinanceManager();

	}

	/**
	 * 
	 */
	private void verifyProductAndDockFilter() {
		String prd = this.allProductElements.get(this.allProductElements.size()-1);
		detailPg.selectProductAndWait(prd);
		
		String dockFromPg = detailPg.getDock();
		
		String dockFromDB = this.getDockOfProduct(prd.split("-")[0], this.locationID);
		
		if(!dockFromPg.equalsIgnoreCase(dockFromDB))
		{
			throw new ErrorOnPageException("Display of Dock selection error!!!", dockFromDB, dockFromPg);
		}else{
			logger.info("Check display of Dock selection Passed!!!");
		}
		
		
	}

	/**
	 * @param prd
	 */
	private String getDockOfProduct(String prd, String locID) {
		db.resetSchema(schema);
		
		String query = "SELECT D_LOC.NAME, D_LOC.TYPE_ID FROM D_LOC " +
				"INNER JOIN P_PRD ON P_PRD.LOC_ID=D_LOC.ID AND P_PRD.PRD_CD='" +
				prd +
				"' AND P_PRD.ACTIVE_IND=1 AND P_PRD.DELETED_IND=0 " +
				"WHERE D_LOC.CD LIKE (SELECT CD FROM D_LOC WHERE ID=" +
				locID +
				")||'%' ";
		logger.info("SQL: "+query);
		
		List<HashMap<String, String>> rs= db.executeQuery(query);
		
		if(rs.size()<1)
		{
			throw new ErrorOnDataException("Cannot find location where the product is assigned...");
		}else{
		
			if(rs.get(0).get("TYPE_ID").equalsIgnoreCase(TYPE_PARK))//equals 7, means product assigned to a Park.
			{
				return "All";
			}else{													//not equals 7, means assigned to Dock...
				return rs.get(0).get("NAME");
			}
		}
	}

	/**
	 * 
	 */
	private void verifyDockAndProductSelection() {
		
		this.allDockElements = detailPg.getDockElements();
		this.allProductElements = detailPg.getProductList();
			
		detailPg.selectDockAreaVenue(this.allDockElements.get(this.allDockElements.size()-1));
		List<String> filteredProductElements = detailPg.getProductList();
		
		detailPg.selectDockAreaVenue("All");
		List<String> unfilteredProductElements = detailPg.getProductList();
	

		//check 'unfilteredProductElements' should be equal with 'allProductElements'
		if(!allProductElements.toString().equalsIgnoreCase(unfilteredProductElements.toString()))
		{
			throw new ErrorOnPageException("unfiltered product list is not consist with default list....", allProductElements.toString(), unfilteredProductElements.toString());
		}else{
			logger.info("Check unfiltered product list Passed!!!");
		}
		
		//check 'filteredProductElements' is part of 'allProductElements'		
		boolean found =false;
		for(String s: filteredProductElements)
		{	
			found = false;
			for(String t: allProductElements)
			{
				if(t.equalsIgnoreCase(s))
				{
					found = true;
					break;
				}
			}
			
			if(!found)
			{
				throw new ErrorOnPageException("Cannot found product ["+s+"] in allProductElements!!!!");
			}
		}
		
	}


	
	
	@Override
	public void wrapParameters(Object[] param) {
		// initialize login finance manager loginInfo
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "NC";
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NC Contract";
		login.location = "Administrator/"+fnm.getFacilityName("1", schema);//North Carolina State Parks
		

		// initialize schedule data
		this.locationID = "552818";
		schedule.location = fnm.getFacilityName(this.locationID, schema);//Mayo River State Park
		schedule.locationCategory = "Park";
		schedule.productCategory = "Slip";

	}

}
