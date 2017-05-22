package com.activenetwork.qa.awo.testcases.abstractcases;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.web.AttractionInfo;
import com.activenetwork.qa.awo.datacollection.legacy.web.LoginInfo;
import com.activenetwork.qa.awo.keywords.web.Lam;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author bzhang
 * @Date  Jun 16, 2011
 */
public abstract class LAMTestCase extends AWOTestCase{

	protected Lam lam;	
	protected LoginInfo login;
	protected AttractionInfo info;
	protected String url;
	protected Payment pay;

	public LAMTestCase() {		
		lam = Lam.getInstance();
		login = new LoginInfo();
		info = new AttractionInfo();
		pay = new Payment("visa");
		
		url = TestProperty.getProperty(env + ".web.lam.url");
		login.userName = TestProperty.getProperty("web.lam.user");
		login.password = TestProperty.getProperty("web.lam.pw");
	}
	
}
