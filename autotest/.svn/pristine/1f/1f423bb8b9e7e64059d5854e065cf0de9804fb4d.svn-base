/**
 * 
 */
package com.activenetwork.qa.awo.testcases.abstractcases.web;

import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.keywords.web.PhotoToolApplication;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Dec 11, 2012
 */
public abstract class PhotoToolTestCase extends WebTestCase {
	protected PhotoToolApplication pt;
	protected FacilityData facility;
	protected String prdID;
	protected String prdName;
	protected String prdCode;
	protected String stateFilter;
	protected String letterFilter;
	protected String loopFilter;
	protected String showFilter;
	protected String photoDescription;
	protected String photoDisplayOrder;
	protected String fileLocation;
	
	protected PhotoToolTestCase() {
		url =  TestProperty.getProperty(env + ".phototool.url");
		pt = PhotoToolApplication.getInstance();
		facility = new FacilityData();
		fileLocation = (AwoUtil.PNG_ROOT + "/testcases/regression/advanced/web/maintenanceapps/phototool/facilityphotostab/").replace("/", "\\");
	}
}
