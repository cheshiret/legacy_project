package com.activenetwork.qa.awo.supportscripts;

import java.io.File;

import org.eclipse.hyades.execution.runtime.datapool.IDatapool;
import org.eclipse.hyades.execution.runtime.datapool.IDatapoolFactory;
import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;
import org.eclipse.hyades.execution.runtime.datapool.IDatapoolRecord;
import org.eclipse.hyades.models.common.datapool.impl.Common_DatapoolFactoryImpl;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.keywords.orms.ResourceManager;
import com.activenetwork.qa.awo.keywords.orms.SupportCenter;
import com.activenetwork.qa.awo.keywords.orms.VenueManager;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * Abstract class with default LoginInfo and datapool file(same directory and
 * name with class file)<br><br>
 * 
 * For loginInfo, the contract and location column name in datapool should be
 * "contract" and "location"<br><br>
 * 
 * 
 * @author Reed Wang
 * 
 */
// TODO: Split this into two abstract classes: one for single datapool file
// support, another for multiple files
// TODO: encapsulate log in an object, code is too ugly when using array
public abstract class AbstractSingleDatapoolSupportCase extends SupportCase {

	private String result = null;
	private LoginInfo login = new LoginInfo();

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		
		/*
		 * TODO: Start finding from case folder, if could find
		 * ${casename}.datapool, use it, if not, find all datapool file under
		 * ${casename} folder and use them
		 */
		dataSource = casePath + "/" + caseName;

		initLogMsgWithDatapoolheader();
		
		initRange();

	}

	// TODO: Maybe add an API to make user could define which column in datapool they want? 
	private void initLogMsgWithDatapoolheader() {
		IDatapool dp = initDatapool();
		
		// Only get the first 5 columns
		// Index + datapool column(5) + result = 7
		logMsg = new String[7];
		logMsg[0] = "Index";
		logMsg[logMsg.length - 1] = "Result";
		
		// filter index and result column out
		for (int i = 0; i < logMsg.length - 2; i++) { 
			logMsg[i + 1] = dp.getVariable(i).getName();
		}
	}

	// TODO: Could we reuse it with SupportCase.initializeDatapool?
	private IDatapool initDatapool() {
		File dpFile = getDatapoolFile(dataSource);
		IDatapoolFactory dpFactory = new Common_DatapoolFactoryImpl();
		return dpFactory.load(dpFile, true);
	}

	@Override
	public void getNextData() {
		login.contract = dpIter.dpString("contract");
		login.location = dpIter.dpString("location");
		readDataPool(dpIter);
	}

	@Override
	public void execute() {
		try {
			Orms orms = new Orms(login);
			execute(orms);
		} catch (Exception e) {
			throw new RuntimeException(e.getCause());
		} finally {
			logMsg[0] = String.valueOf(cursor);
			
			// filter index and result column out
			for (int i = 0; i < logMsg.length - 2; i++) {
				IDatapoolRecord dpRecord = dpIter.dpCurrent();
				String value = String.valueOf(dpRecord.getCell(i).getCellValue());
				if (!StringUtil.notEmpty(value)) {
					value = "NULL"; // Placeholder for empty string to cheat with superclass writelog()
				}
				logMsg[i + 1] = value;
			}
			
			if (result != null) {
				logMsg[logMsg.length - 1] = result;
			} else {
				throw new RuntimeException("Please set support case result in execute(loginInfo)");
			}
		}
	}
	
	protected void setResult(String result) {
		this.result = result;
	}

	/**
	 * If you want to implement this method, no need to get loginInfo from
	 * datapool
	 * 
	 * @param dpIter
	 */
	protected abstract void readDataPool(IDatapoolIterator dpIter);

	/**
	 * Set value of startpoint and endpoint, please DO NOT add other logic here
	 */
	protected abstract void initRange();

	protected abstract void execute(Orms orms);
	
	protected class Orms {
		
		public LoginInfo loginInfo;
		
		public Orms(LoginInfo loginInfo) {
			this.loginInfo = loginInfo;
		}
		
		
		public LicenseManager loginLicenseManager() {
			login.userName = TestProperty.getProperty("orms.fm.user");
			login.password = TestProperty.getProperty("orms.fm.pw");
			
			LicenseManager lm = LicenseManager.getInstance();
			lm.loginLicenseManager(login);
			return lm;
		}
		
		public InventoryManager loginInventoryManager() {
			login.userName = TestProperty.getProperty("orms.im.user");
			login.password = TestProperty.getProperty("orms.im.pw");
			
			InventoryManager im = InventoryManager.getInstance();
			im.loginInventoryManager(login);
			return im;
		}
		
//		public PermitManager loginPermitManager() {
//			login.userName = TestProperty.getProperty("orms.pm.user");
//			login.password = TestProperty.getProperty("orms.pm.pw");
//			
//			PermitManager pm = PermitManager.getInstance();
//			pm.loginPermitManager(login);
//			return pm;
//		}
		
		public VenueManager loginVenueManager() {
			login.userName = TestProperty.getProperty("orms.vm.user");
			login.password = TestProperty.getProperty("orms.vm.pw");
			
			VenueManager vm = VenueManager.getInstance();
			vm.loginVenueManager(login);
			return vm;
		}
		
//		public CallManager loginCallManager() {
//			login.userName = TestProperty.getProperty("orms.cm.user");
//			login.password = TestProperty.getProperty("orms.cm.pw");
//			
//			CallManager cm = CallManager.getInstance();
//			cm.loginCallMgr(login);
//			return cm;
//		}
		
//		public OperationManager loginOperationManager() {
//			login.userName = TestProperty.getProperty("orms.om.user");
//			login.password = TestProperty.getProperty("orms.om.pw");
//			
//			OperationManager om = OperationManager.getInstance();
//			om.loginOperationMgr(login);
//			return om;
//		}
		
		public AdminManager loginAmdingManager() {
			login.userName = TestProperty.getProperty("orms.adm.user");
			login.password = TestProperty.getProperty("orms.adm.pw");
			
			AdminManager adm = AdminManager.getInstance();
			adm.loginAdminMgr(login);
			return adm;
		}
		
		public FinanceManager loginFinanceManager() {
			login.userName = TestProperty.getProperty("orms.fnm.user");
			login.password = TestProperty.getProperty("orms.fnm.pw");
			
			FinanceManager fm = FinanceManager.getInstance();
			fm.loginFinanceManager(login);
			return fm;
		}
		
		public ResourceManager loginRersourceManager() {
			login.userName = TestProperty.getProperty("orms.rm.user");
			login.password = TestProperty.getProperty("orms.rm.pw");
			
			ResourceManager rm = ResourceManager.getInstance();
			rm.loginResourceManager(login);
			return rm;
		}
		
		public SupportCenter loginSupportCenter() {
			login.userName = TestProperty.getProperty("orms.sc.user");
			login.password = TestProperty.getProperty("orms.sc.pw");
			
			SupportCenter sc = SupportCenter.getInstance();
			sc.loginSupportCenter(login);
			return sc;
		}
	}
}
