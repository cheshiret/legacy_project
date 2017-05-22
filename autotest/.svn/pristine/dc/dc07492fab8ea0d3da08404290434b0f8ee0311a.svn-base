package com.activenetwork.qa.awo.supportscripts.support.lottery;

import java.io.IOException;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.keywords.web.BWCooperator;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.TestDriverUtil;
import com.activenetwork.qa.testapi.util.FileUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class MakeLotteries extends WebTestCase {
	/**
	 * @since  2010/12/21
	 * @author VZHAO
	 */
	private PermitInfo bd;
	private BWCooperator bw = BWCooperator.getInstance();
	private String contract = "NRRS";//for creating schema name
	private boolean isRecgov = true;
	private String logfile="J:\\lottery_bw.log";
	private int size=0;
	private boolean multi=false;

	public void execute() {
		web.invokeURL(url);

		web.signIn(email, pw);
		for(int count=0;count<size;count++) {
			if(size>50 && count % 30==0 && count>0 ) {
				browser.closeAllBrowsers();
				web.invokeURL(url); 	
			}
			if(multi) {
				web.signOut();
				email=web.getNextEmail();
				web.signIn(email, pw);
			}

			bw.submitLotteryToCart(bd, pay, isRecgov);
			String resId = bw.checkOutCart(pay);
			try {
				FileUtil.writeLog(logfile, count+" - "+resId);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}


		}

		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		env=TestDriverUtil.getParameter(param,"env", env);
		size=Integer.parseInt(TestDriverUtil.getParameter(param, "size"));
		email=TestDriverUtil.getParameter(param,"email", web.getNextEmail());
		multi=Boolean.valueOf(TestDriverUtil.getParameter(param, "multi","false"));
		url = TestProperty.getProperty(env + ".web.recgov.url");
		pw = TestProperty.getProperty("web.login.pw");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + contract;

		bd = new PermitInfo();
		//		bd.groupSize = "2";
		//		bd.faclilty = "Snake River";
		//		bd.permitType = "River Launch Permit";
		//		bd.entrance = "01 Snake River";
		//		bd.isRange = false;
		//		bd.entryDate = "02-14-2011";//Lottery inventory start date, need to be updated after the lottery schedule changes
		bd.groupSize = "2";
		bd.facility = "Boundary Waters Canoe Area Wilderness (Reservations)";
		bd.permitType = "Overnight Paddle";
		bd.entrance = "01 Trout Lake (op,om)";
		bd.isRange = false;
		bd.entryDate = TestDriverUtil.getParameter(param,"date",web.getLotteryAvailability(schema, 72600,"web"));
	}
}
