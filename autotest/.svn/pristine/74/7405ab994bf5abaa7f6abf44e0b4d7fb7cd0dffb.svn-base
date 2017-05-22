/**
 * 
 */
package com.activenetwork.qa.awo.testcases.production.web;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.keywords.web.BWCooperator;
import com.activenetwork.qa.awo.testcases.abstractcases.BWTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:BW cooperator process order 
 * @Preconditions:
 * @SPEC:
 * @Task#:	AUTO-882
 * 
 * @author asun
 * @Date  2012-2-27
 */
public class BW_CooperatorProcessOrder extends BWTestCase {
	
	private BWCooperator bw = BWCooperator.getInstance();

	private PermitInfo bd;

	public void execute() {
		bw.invokeURL(url, false);
		bw.signInBW(email, pw);
		bw.bookPermitByFindGroupLeader(bd, false);//isRec=false
		String resNum=bw.checkOutCart(pay);
		bw.gotoPermitDetailsFromHome(resNum);
		bw.cancelPermitOrderFromDetails();
		bw.signOutBW();
	}
	

	public void wrapParameters(Object[] param) {
		
		if(env.equalsIgnoreCase("live")) {
			AwoUtil.loadLiveInformation();
	  	  	email = TestProperty.getProperty(env+".training.bw.coop.email");
	  	  	pw = TestProperty.getProperty(env+".training.bw.coop.pw");
	  	} else {
	  		schema=DataBaseFunctions.getSchemaName("NRRS", env);
	  		email = DataBaseFunctions.getCooperatorLogin(schema, TestProperty.getProperty("bw.permit.coop.id"));
			pw = TestProperty.getProperty("bw.coop.pwd");
	  	}
		
		url = TestProperty.getProperty(env + ".training.web.bw.url");
		bd = new PermitInfo();
		bd.permitType="Overnight Hiking";
		bd.groupSize = "2";
		bd.issue = false;
		bd.isRange = true;
		bd.isDistrict = false;
		
		bd.groupLeader.setAsDefaultGroupLeader();
	}
}
