package com.activenetwork.qa.awo.testcases.regression.basic.web.pl;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.UserStoppedScriptException;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:AUTO-623(DEFECT-29209)
 * 
 * @author bzhang
 * @Date  Jun 16, 2011
 */
public class HeaderAndBackgroundImageVerificationPLWs extends WebTestCase {
	String msg = "";

	private String contractCodes[];

	public void execute() {
		int counter = 0;
		int failed = 0;
		for(int i = 0; i < contractCodes.length; i ++) {
			getPLWUrl(i); // get data from data pool

			try {
				logger.info("Verify Header and BackGround display on " + bd.contractCode + " private lable");
				if(!url.startsWith("els")){//Sara[6/18/2014] Per Aida, we don't have ELS anymore		
					web.invokeURL(url);
					this.verifyHeaderImageDisplay(bd.contractCode);
				}
			} catch (UserStoppedScriptException e) {
				throw e;
			} catch (Exception e) {
				failed++;
				logger.error("#"+counter+"failed: "+e.getMessage()+".\n");
				msg +="#"+counter + e.getMessage()+".\n";
				e.printStackTrace();
			} finally {
				counter++;
				//				dpIter.dpNext();
			}
		}
		if(failed>0) {
			logger.info("The following PLW(s) failed on header and background verification:\n" + msg);
			throw new TestCaseFailedException("Failed: total "+failed+" of "+(counter+1)+" failed on Header and BackGround display verification.");
		}
	}

	public void wrapParameters(Object[] param) {
		//		dpFileName = casePath + "/" + caseName ;	
		contractCodes = new String[]{"ky", "ne" , "ny", "ri", "id", "nh", "de", "ut", "md", "nm", "els2", "larc"};
	}

	private void getPLWUrl(int index) {
		bd.contractCode = contractCodes[index];
		url = TestProperty.getProperty(env + ".web." + bd.contractCode  + ".url");	
	}

	/**
	 * Verify the PL header image's URL matching with the corresponding PL info
	 * @param conCode
	 */
	public void verifyHeaderImageDisplay(String conCode){
		if (conCode.equalsIgnoreCase("orng")){
			conCode = "oc";
		}
		if (conCode.equalsIgnoreCase("larc")){
			conCode = "lc";
		}
		String pattern = ".*/brands/" + conCode + "/(marketing/)?images/.*";
		RegularExpression reg = new RegularExpression(pattern, false);

		logger.info("Verify the PLW header image display");

		if (!browser.checkHtmlObjectExists(".class","Html.IMG",".src",reg)){
			throw new ErrorOnPageException("The PLW header imange for " + conCode + " is not correct!");
		}		
	}
}
