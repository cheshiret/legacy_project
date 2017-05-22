package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.campdetaillayout;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovParkDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
/** @Description(P):
 * 1: verify the total photo number is equal or less than 6.
 * 2: verify the photo's title should be: Photo:[Park Name]
 * 
 * @Preconditions:
 * @SPEC: Story Q 
 * @Task#: AUTO - 849
 * 
 * @author bzhang
 * @Date  Jan 5, 2012
 */
public class CampgroundPhoto  extends RecgovTestCase{
	int maxPhotoNum;

	public void execute(){
		web.invokeURL(url);
		web.gotoCampgroundDetailsPg(bd);
		this.verifyCampPhotoInfo();
	}
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.whereTextValue = "AMITY";
		bd.park = bd.whereTextValue;
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bd.contractCode ="NRSO";
		bd.parkId = "71005";
		
		maxPhotoNum = 6;
	}
	
	/**
	 * verify all photo title info in the given format, verify the total photo number less than the maximum number configured.
	 */
	public void verifyCampPhotoInfo(){
		RecgovParkDetailsPage detailPg = RecgovParkDetailsPage.getInstance();
		
		//1: get all photo title info.
		List<String> photes = detailPg.getAllParkImageTitle();
		//2: verify the size is less or equal than 6.
		
		if(photes.size() > maxPhotoNum){
			throw new ErrorOnDataException("the photo displayed on the page should be less or equal than " + maxPhotoNum);
		}
		//3: verify the title is "Photo: [Park Name]"
		detailPg.verifyAllParkImageTitle(bd.whereTextValue);
	}

}
