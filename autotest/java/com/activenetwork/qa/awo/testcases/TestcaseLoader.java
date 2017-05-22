package com.activenetwork.qa.awo.testcases;

import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.TestDriverUtil;
import com.activenetwork.qa.awo.util.CSVLoader;
import com.activenetwork.qa.awo.util.CSVUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class TestcaseLoader {	
//private static String defaultScript= com.activenetwork.qa.awo.
//testcases.sanity.orms.LM_MIG_BasicSearchSanity
//testcases.regression.basic.web.hf.customer.ForgetPassword

//supportscripts.support.licensemgr.MergeCustomers
//testcases.regression.advanced.web.search.unifiedsearch.mapviewsearchresult.BubblePhoto
//supportscripts.qasetup.db.ResetPWForVeriFone
//testcases.regression.advanced.orms.financial.finsession.site.EditOpeningFloat
//testcases.sanity.orms.FM_NSSMidstayTransfer
//testcases.regression.basic.orms.license.feeschedule.AddEditActivityFeeScheduleForActivityPrd
//testcases.regression.basic.web.ra.MakeReservationWithoutUseFee
//testcases.regression.basic.web.rec.MakeReservation_MapSearch_1
//supportscripts.qasetup.db.
//			.class.getName();
	public static void main(String[] args) {
//	       System.out.println("Working Directory = " +
//	               System.getProperty("user.dir") + "\\resources\\csv\\csvfolders\\test.csv");
		TestProperty.load(AwoUtil.TEST_PROPERTY);
		CSVLoader cl = new CSVLoader();
		cl.SetFile("test.csv");
		System.out.println(CSVLoader.Table.Data().get(0));
//		System.out.println(CSVLoader.Table.getColNum());
//		System.out.println(CSVLoader.Table.getRowNum());
		System.out.println(CSVLoader.Table.getFieldName());
	
//		System.out.println(TestProperty.getProperty("csv.field.type"));
//		cl.finalize();
//		TestDriverUtil.load(args, defaultScript);	
//		String javaHome = System.getProperty("java.home");
//		System.out.println(javaHome);
	}
}

