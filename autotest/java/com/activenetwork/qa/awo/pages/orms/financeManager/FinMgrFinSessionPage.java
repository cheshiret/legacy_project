/*
 * $Id: FinMgrFinSessionPage.java 1207 2005-09-29 14:07:26Z i2k-net\cguo $ 
 */

package com.activenetwork.qa.awo.pages.orms.financeManager;

/**
 * @author CGuo
 */
public class FinMgrFinSessionPage extends FinanceManagerPage {

	/**
	 * Script Name   : FinMgrFinSessionPage
	 * Generated     : Nov 22, 2004 4:18:09 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 2)
	 *
	 * @since  2004/11/22
	 */
	private static FinMgrFinSessionPage _instance = null;

	private FinMgrFinSessionPage() {

	}

	public static FinMgrFinSessionPage getInstance() {
		if (null == _instance)
			_instance = new FinMgrFinSessionPage();

		return _instance;
	}

	public boolean exists() {
		return false;
	}

	public void closeFinSession(String finID, String pinNum) {
		//		MiscFunctions.selectItem (List_finsesssearchtype(),"Fin Session ID");
		//		MiscFunctions.setText(Text_finsesssearchtypevalue(),finID);
		//		Link_Go().click();
		//		
		//		sleep(2);
		//		
		//		Link_OpenFinSessions().click();
		//		
		//		CheckBox_all_slctall().click();
		//		Link_CloseWithoutAdjustment().click();
		//		
		//		MiscFunctions.setText(Html_pinnumber(), pinNum);
		//		Link_OK().click();

	}

}
