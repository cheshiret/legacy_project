package com.activenetwork.qa.awo.pages.web.ra;

import com.activenetwork.qa.awo.pages.web.uwp.UwpProductListCommonPage;


public class RASiteListPage extends UwpProductListCommonPage {
	static class SingletonHolder {
		protected static RASiteListPage _instance = new RASiteListPage();
	}

	protected RASiteListPage() {
	}

	public static RASiteListPage getInstance() {
		return SingletonHolder._instance;
	}
}
