package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.lottery.marinalottery;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.LotteryPreferenceAttribute;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryAssignNewParticipationPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryParticipationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: It is to verify the UI of assign a participation location to marina lottery(slip lottery product), the check point including:
 *               1.Participation location:
 *               	 	a.The list shall include such Locations and their child Locations down to the Dock level.
 *               2.Select the location higher than Facility Level and check the Products:
 *               		a.The one and only valid value for this shall be "All".
 *               3.Select the facility as location and check the products:
 *                 		a.All is available and is the default value;
 *                      b.All slips assigned to the docks of the facility are listed;
 *                      c.The slips included in the Child Dock are listed (slip under child docks will be listed in order to consistent with site behaviour);
 *                      d.The Child Slip of NSS Slip are not listed.
 * @Preconditions:
 * 1. Make sure the lottery has been setup(AssignParticipationLocationForSlipLottery)
 * @LinkSetUp:[linked--<changed>-->set up by case self]d_inv_new_lottery_program:id=340(LOTTERYNAME='marina_lottery_assign_participation_ui_check')
 * @SPEC: TC:042119
 * @Task#: Auto-1342
 * @author Phoebe Chen
 * @Date  Dec 24, 2012
 */
public class AssignParticipationLocationToMarinaUICheck extends InventoryManagerTestCase{
	LotteryAssignNewParticipationPage assiPartPg = LotteryAssignNewParticipationPage
			.getInstance();
	List<String> expectProducts = new ArrayList<String>();
	String locationId = "";
	String regionId = "";
	String facilityId = "";
	@Override
	public void execute() {
		//login into the inventory manager
		im.loginInventoryManager(login);
		im.gotoLotterySearchPage();
		
		if (StringUtil.isEmpty(im.getLotteryId(schema,lottery.name))) {
			lottery.id = im.addNewLottery(lottery);
			im.changeLotteryStatus(false, lottery);
		}
		
		//Go to the lottery details page
		im.gotoLotteryDetailPageFromSearchPage(lottery);
				
		this.gotoLotteryParticipationPageFromDetailPage();
		
		
		//Verify the UI
		this.verifyUI();
		
		im.logoutInvManager();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		
		locationId = "550001";
		lottery.name = "marina_lottery_assign_participation_ui_check";
		lottery.locationCategory = "Agency";
		lottery.description = "QA AUTO TEST";		
		lottery.location = im.getFacilityName(locationId, schema);;//"NC Division of Parks and Recreation";
		lottery.productCategory = "Slip";
		this.setPreferenceAttributes();
		
		regionId = "550014";
		lottery.agency = lottery.location;
		lottery.Region = im.getFacilityName(regionId, schema);//"South District";
		
		facilityId = "552806";
	}
	
	private void setPreferenceAttributes() {
		lottery.attributes.clear();
		String[] attributes = {"Facility","Min Slip Depth","Min Slip Length","Min Slip Width","Boat Category","Dock"};
		for (int i = 0; i < attributes.length; i++) {
			LotteryPreferenceAttribute attr = new LotteryPreferenceAttribute();
			attr.label = attributes[i];
			attr.displayOrder = String.valueOf(i + 1);
			lottery.attributes.add(attr);
		}
	}

	private void gotoLotteryParticipationPageFromDetailPage(){
		LotteryDetailsPage lotteryDetailsPg = LotteryDetailsPage.getInstance();
		LotteryParticipationPage lotteryPaticipationPg = LotteryParticipationPage
				.getInstance();
	
		logger.info("Go to lottery assign new participation page.");
		lotteryDetailsPg.clickLotteryParticiption();
		Object page = browser.waitExists(lotteryPaticipationPg,
				assiPartPg);
		if (page == lotteryPaticipationPg) {
			lotteryPaticipationPg.clickAssignNew();
			assiPartPg.waitLoading();
		}
	}
	
	private void verifyUI() {
		boolean passed = true;
		//The list shall include such Locations and their child Locations down to the Dock level. 
		passed = assiPartPg.isAgencyDropdownListExists() && assiPartPg.isRegionDropdownListExists() && assiPartPg.isFacilityDropdownListExists() &&
				assiPartPg.isAreaDropdownListExists();
		if(!passed){
			logger.info("The list did not include as 'Agency'--'Region'--'Facility'--'Dock'");
		}
		
		//Select the location higher than Facility Level and check the Products:The one and only valid value for this shall be "All".
		assiPartPg.setUpParticipationInfo(lottery);
		expectProducts.add("All");
		passed &= this.checkProduct();
		
		//Select the facility as location and check the products
		lottery.facility = im.getFacilityName(facilityId, schema);//"Carolina Beach State Park";
		assiPartPg.setUpParticipationInfo(lottery);
		expectProducts.addAll(this.getSlipsUnderTheFacility());
		passed &= this.checkProduct();
		
		if(!passed){
			throw new ErrorOnPageException("The data information on the page is not correct, please check the log above!");
		}
	}
	
	private List<String> getSlipsUnderTheFacility() {
		//PRD_REL_TYPE:Product relation type: 1- site specific, 2 - Non site specific parent, 3 - Non site specific child. 
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		String sql = "select trim(prd_name)  as prd_name from p_prd  where  active_ind=1 and deleted_ind=0 and prd_rel_type in ('1','2')  and loc_id in " +
              "(select id from d_loc where level_num=50 and status_id=1 and type_id=32 and delete_ind=0 and active_ind=1 and cd like '|1|" + locationId + "|" + 
              regionId + "|" + facilityId + "%' or id=" + facilityId + ")  order by prd_name  asc";
		logger.info("Excute sql:" + sql);
		List<String> idList = db.executeQuery(sql, "PRD_NAME");
		return idList;
	}

	private boolean checkProduct(){
		boolean result =true;
		//Check the default selected value is "All"
		String defaultSeleted = assiPartPg.getProductDropdownListSelectedValue();
		if(!defaultSeleted.equalsIgnoreCase("All")){
			result = false;
			logger.info("'All' is not the default selected value for product!");
		}
		//Check the content of the product drop list
		List<String> products = assiPartPg.getProductFromDropDownlist();
		if((!products.containsAll(expectProducts))||(!expectProducts.containsAll(products))){
			result = false;
			logger.info("Expect products:" + this.printPrd(expectProducts) + " But actually products in list are:" + this.printPrd(products));
		}
		return result;
	}
	
	private String printPrd(List<String> prdList){
		String prds = "[" ;
		for(String p:prdList){
			prds += p + ",";
		}
		prds += "]";
		return prds;
	}
}
