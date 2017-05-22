package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.phototool.productphotostab;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.PhotoToolSelectProductPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.PhotoToolTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: P
 * @Preconditions:
 * @SPEC:
 * Loop" drop down list [TC:020513] 
 * Show" drop down list [TC:020514] 
 * Result Summary Bar and Paging Control [TC:020515] 
 * Product Result Table [TC:020521] 
 * @Task#:AUTO-1540
 * 
 * @author SWang
 * @Date  Mar 14, 2013
 */
public class MoreThanOnePgResEntrances extends PhotoToolTestCase {
	private PhotoToolSelectProductPage selectProductPg = PhotoToolSelectProductPage.getInstance();
	private String colName_Entrance, colName_NumPhoto, regxString;
	private List<String> allEntrancesInParkLevel, showDDLOptions;

	public void execute() {
		//Login in to product photos page
		pt.invokeURL(url);
		pt.signIn(login);
		pt.gotoFacilityListPage(facility.contract);
		pt.gotoProductPhotosPage(StringUtil.EMPTY, StringUtil.EMPTY, letterFilter, facility.facilityID);

		//Verify Show drop down list default value
		verifyMoreThanOnePgReservableEntrances();
		selectProductPg.verifyShowDDLOption(showDDLOptions.get(0));

		//Verify Entrance and #photo col values in first page
		selectProductPg.verifyColValuesInFirstPg(colName_Entrance, allEntrancesInParkLevel);
		selectProductPg.verifyColRegxValuesInFirstPg(colName_NumPhoto, regxString);

		//Verify page results is changed with next and previous link
		selectProductPg.verifyPageResults(selectProductPg.generatePageResultsInFirstPage(selectProductPg.getTotalResultNum()));
		selectProductPg.navigatePage(true);
		selectProductPg.verifyPageResults(selectProductPg.generatePageResults(selectProductPg.getTotalResultNum(), 2));
		selectProductPg.navigatePage(false);
		selectProductPg.verifyPageResults(selectProductPg.generatePageResultsInFirstPage(selectProductPg.getTotalResultNum()));

		//Verify #Photo, is bigger than zero when with photos, equal zero when without photos
		selectProductPg.filterProduct(StringUtil.EMPTY, showDDLOptions.get(1));
		selectProductPg.verifyProductsPhotoNumInFirstPg(false);
		selectProductPg.filterProduct(StringUtil.EMPTY, showDDLOptions.get(2));
		selectProductPg.verifyProductsPhotoNumInFirstPg(true);

		//Verify Show will not be changed after clicking next link
		selectProductPg.navigatePage(true);
		selectProductPg.verifyShowDDLOption(showDDLOptions.get(2));

		pt.logOut();
	}

	public void wrapParameters(Object[] param) {
		login.userName = TestProperty.getProperty("orms.pt.mul.user");
		login.password = TestProperty.getProperty("orms.pt.mul.pw");
		login.role = "PhotoTool User";
		login.location = "RA Contract";

		facility.contract = "NRSO";
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		facility.facilityID = "72202"; //Desolation Wilderness Permit
		letterFilter = "D";

		showDDLOptions = new ArrayList<String>();
		showDDLOptions.add("All");
		showDDLOptions.add("With Photos");
		showDDLOptions.add("Without Photos");

		colName_Entrance = "Entrance";
		colName_NumPhoto = "# Photo";
		regxString = "\\d+";
	}

	/**
	 * Get all entrances in park level other than those Trailheads because trailheads will not be displayed in Entrance list and no Entrance Details page for it
	 * @param schema
	 * @param parkID
	 * @return
	 */
	public List<String> queryAllEntranceNamesOtherThanTrailHead(String schema, String parkID){
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		logger.info("Query all entrance names in park (id:"+parkID+") level other than trail heads.");

		String query = "select prd_name from p_prd where park_id = '"+ parkID+ "' and prd_grp_id<>212 and product_cat_id = "+OrmsConstants.PRDCAT_PERMIT+" and active_ind=1 and deleted_ind=0 order by prd_name asc";
		List<String> values = db.executeQuery(query, "PRD_NAME");

		db.disconnect();
		return values;
	}

	private void verifyMoreThanOnePgReservableEntrances(){
		allEntrancesInParkLevel = queryAllEntranceNamesOtherThanTrailHead(schema, facility.facilityID);
		if(allEntrancesInParkLevel.size()<=25){
			throw new ErrorOnDataException("Failed to verify more than 25 reservable tours.");
		}
		logger.info("Successfully verify more than 25 reservable tours.");
	}
}
