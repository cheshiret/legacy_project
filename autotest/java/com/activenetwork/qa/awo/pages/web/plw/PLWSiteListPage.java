package com.activenetwork.qa.awo.pages.web.plw;

import com.activenetwork.qa.awo.pages.web.uwp.UwpProductListCommonPage;


public class PLWSiteListPage extends UwpProductListCommonPage {
	static class SingletonHolder {
		protected static PLWSiteListPage _instance = new PLWSiteListPage();
	}

	protected PLWSiteListPage() {
	}

	public static PLWSiteListPage getInstance() {
		return SingletonHolder._instance;
	}
}
