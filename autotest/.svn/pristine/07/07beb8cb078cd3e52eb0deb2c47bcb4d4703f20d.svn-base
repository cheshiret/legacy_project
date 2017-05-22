package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.mapviewsearchresult;

import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
	Verify  the Location indicator will be put onto the map to indicate the target location (where location). 
	Verify the location indicator should appear as a pin or mark that will be displayed on the map
	Verify the location indicator will always be on the map when the user is paging through the result; 
 * @Preconditions:

 * @SPEC: Story J 
 * @Task#: AUTO - 839
 * 
 * @author bzhang
 * @Date  Dec 22, 2011
 */
public class LocationIndicator extends RecgovTestCase {
	UwpUnifiedSearchViewAsMapPage mapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
	
	public void execute(){
		web.invokeURL(url);
		web.gotoViewAsMapPage(bd);
		mapPg.verifyLocationIndicatorExistCrossPages();
	}
	
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "NRRS";
		url = TestProperty.getProperty(env + ".web.recgov.url");
		
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.whereTextValue = "BOYKIN SPRINGS RECREATION AREA";
		bd.park = bd.whereTextValue;
		bd.contractCode ="NRSO";
		bd.parkId = "72156";
		
		bd.interestInValue = "Day use & Picnic areas";
		bd.arrivalDate = DateFunctions.getToday(); 
		bd.flexibleValue = "Not Flexible";
		bd.lengthOfStay = "1";
	}
}
