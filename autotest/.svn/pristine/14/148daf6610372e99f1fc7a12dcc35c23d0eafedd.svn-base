package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.campdetaillayout;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovParkDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/** @Description(P):
		1: verify Facility Details page is the default sub page.
		2: verify on Facility Details page, the "Overview" is the default sub page.
		3: verify page have Overview info, Activities and Amenities info, Know Before You Go info. Getting There info , and Phone Park info.
		4: verify the park phone info in the given format.
 * 
 * @Preconditions:
 * @SPEC: Story Q 
 * @Task#: AUTO - 849
 * 
 * @author bzhang
 * @Date  Jan 4, 2012
 */
public class PageContentVerify  extends RecgovTestCase{

	public void execute(){
		web.invokeURL(url);
		web.gotoCampgroundDetailsPg(bd);
		this.verifyDefaultSubPageOfCampgroundDetails();
	}
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.whereTextValue = "AMITY";
		bd.park = bd.whereTextValue;
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bd.contractCode ="NRSO";
		bd.parkId = "71005";
	}
	
	/**
	 * verify Facility Details page is the default sub master page for campground details. on the other hand verify the 
	 * "Overview" is the default sub page. and other page contenet info.
	 */
	public void verifyDefaultSubPageOfCampgroundDetails(){
		RecgovParkDetailsPage detailPg = RecgovParkDetailsPage.getInstance();
		
		detailPg.verifyDefaultSubPage();
		detailPg.verifyOverviewDescriptionInfoExist();
		detailPg.verifyAmenitiesAndActivitiesInfoExist();
		detailPg.verifyNoteAndAlertInfoExist();
		detailPg.verifyGettingThereInfoExist();
		detailPg.verifyPhoneParkForMoreInfoExist();//DEFECT-34469, DEFECT-34377
		detailPg.verifyPhoneNumberFormat();
	}

}
