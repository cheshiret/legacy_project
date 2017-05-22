package com.activenetwork.qa.awo.pages.web.ra;

import com.activenetwork.qa.awo.pages.web.uwp.UwpProductDetailsCommonPage;

public class RASlipDetailsPage extends UwpProductDetailsCommonPage{
	static class SingletonHolder {
		protected static RASlipDetailsPage _instance = new RASlipDetailsPage();
	}

	protected RASlipDetailsPage() {
	}

	public static RASlipDetailsPage getInstance() {
		return SingletonHolder._instance;
	}
}
