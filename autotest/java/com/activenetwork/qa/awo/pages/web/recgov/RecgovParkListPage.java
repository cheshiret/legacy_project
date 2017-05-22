package com.activenetwork.qa.awo.pages.web.recgov;

import com.activenetwork.qa.awo.pages.web.uwp.UwpParkListCommonPage;

public class RecgovParkListPage extends UwpParkListCommonPage{
	private static RecgovParkListPage _instance = null;

	public static RecgovParkListPage getInstance() {
		if (null == _instance)
			_instance = new RecgovParkListPage();

		return _instance;
	}
}
