/**
 * 
 */
package com.activenetwork.qa.awo.keywords.web;

/**
 * @Description: These methods are for only for Photo Tool Application 
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Dec 12, 2012
 */
public class PhotoToolApplication extends MaintenanceApplication {
	private static PhotoToolApplication _instance = null;

	public static PhotoToolApplication getInstance() {
		if (null == _instance)
			_instance = new PhotoToolApplication();

		return _instance;
	}
	
	protected PhotoToolApplication() {
	}
}
