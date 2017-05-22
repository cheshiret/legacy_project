/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.popup;

import com.activenetwork.qa.testapi.page.HtmlPopupPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author QA
 * @Date  Mar 4, 2014
 */
public class OrmsRepositoryFileViewerPopupPage extends HtmlPopupPage{
	private static OrmsRepositoryFileViewerPopupPage _instance = null;

	protected OrmsRepositoryFileViewerPopupPage() {
		super("url",new RegularExpression("https?://.+/RepositoryFileViewer\\?id=\\d+.*",false));
	}
	
	public static OrmsRepositoryFileViewerPopupPage getInstance(){
		if(null==_instance){
			_instance = new OrmsRepositoryFileViewerPopupPage();
		}
		return _instance;
	}	
	


}
