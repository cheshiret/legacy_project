package com.activenetwork.qa.awo.util;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
import com.inflectra.spirateam.mylyn.core.internal.services.soap.IImportExport;
import com.inflectra.spirateam.mylyn.core.internal.services.soap.IImportExportConnectionAuthenticateServiceFaultMessageFaultFaultMessage;
import com.inflectra.spirateam.mylyn.core.internal.services.soap.IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage;
import com.inflectra.spirateam.mylyn.core.internal.services.soap.ImportExport;
import com.inflectra.spirateam.mylyn.core.internal.services.soap.RemoteProject;
import com.inflectra.spirateam.mylyn.core.internal.services.soap.RemoteRelease;
/**
 * This is a utility class to record test results to SpiraTeam server
 * @author jdu
 *
 */
public class SpiraTeam {
	protected static final String WEB_SERVICE_SUFFIX = "/Services/v4_0/ImportExport.svc"; //$NON-NLS-1$
	protected static final String WEB_SERVICE_NAMESPACE = "{http://www.inflectra.com/SpiraTest/Services/v4.0/}ImportExport"; //$NON-NLS-1$
	protected static final String WEB_SERVICE_NAMESPACE_DATA_OBJECTS = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v4_0.DataObjects"; //$NON-NLS-1$
	private IImportExport soap;
	private String userName,password;
	
	private static SpiraTeam _instance=null;
	
	protected SpiraTeam() {
		userName=TestProperty.getProperty("spira.userName");
		password=TestProperty.getProperty("spira.password");
		initSoap();
	}
	
	public static SpiraTeam getInstance() {
		if(_instance==null) {
			_instance=new SpiraTeam();
		}
		
		return _instance;
	}
	
	private void initSoap() {
		try {
			String baseUrl=TestProperty.getProperty("spira.url");
			URL serviceUrl = new URL(baseUrl + WEB_SERVICE_SUFFIX);
			ImportExport service = new ImportExport(serviceUrl, QName.valueOf(WEB_SERVICE_NAMESPACE));
			soap = service.getBasicHttpBindingIImportExport();
			Map<String, Object> requestContext = ((BindingProvider)soap).getRequestContext();
			requestContext.put(BindingProvider.SESSION_MAINTAIN_PROPERTY,true); 
		} catch(Exception e) {
			AutomationLogger.getInstance().warn("Failed to init Soap due to "+e);
			soap=null;
		}
	}
	
	public IImportExport getSoap() {
		return soap;
	}
	
	public boolean auth() {
		try {
			return soap.connectionAuthenticate(userName, password);
		} catch (IImportExportConnectionAuthenticateServiceFaultMessageFaultFaultMessage e) {
			AutomationLogger.getInstance().warn(e);
			return false;
		}	
	}
	
	public boolean connectToProject(int projectId) {
		try {
			return soap.connectionConnectToProject(projectId);
		} catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage e) {
			AutomationLogger.getInstance().warn(e);
			return false;
		}
	}
	
//	public static int recordTestResult(String build,int projectId,String testCaseId,int result,XMLGregorianCalendar startDate,XMLGregorianCalendar endDate,String runnerName, String testcaseName,String errorMsg, String errorStatckTrace) {
//		int testrunFormatId=1; //The format of the runnerStackTrace (1=Plain Text, 2=HTML)
//		int releaseId=getReleaseId(projectId, build);
//		try{
//			return getSoap().testRunRecordAutomated2(userName, password, projectId, null, testCaseId,  releaseId==-1?null:releaseId, null, null, null, startDate, endDate, result, runnerName, testcaseName, 1, errorMsg, errorStatckTrace, testrunFormatId);
//		} catch(Exception e) {
//			RALogger.getInstance().warn(e);
//			return -1;
//		}
//	}
	
	public static int[] recordTestResult(String build,String projectName,String tcIds,int result,XMLGregorianCalendar startDate,XMLGregorianCalendar endDate,String runnerName, String testcaseName, String msg, String errorStatckTrace) {
		int testrunFormatId=1; //The format of the runnerStackTrace (1=Plain Text, 2=HTML)
		int projectId=getProjectId(projectName);
		int releaseId=getReleaseId(projectId, build);
		String[] ids=tcIds.split(",");
		int[] trIds=new int[ids.length];
		SpiraTeam spira=SpiraTeam.getInstance();
		for(int i=0;i<ids.length;i++) {
			int testCaseId=Integer.parseInt(ids[i]);
			try {
				trIds[i]=spira.soap.testRunRecordAutomated2(spira.userName, spira.password, projectId, null, testCaseId, releaseId==-1?null:releaseId, null, null, null, startDate, endDate, result, runnerName, testcaseName, 1, msg, errorStatckTrace, testrunFormatId);
			} catch(Exception e) {
				AutomationLogger.getInstance().warn(e);
				trIds[i]=-1;
			}
		}
		
		AutomationLogger.getInstance().info("Recorded to SpiraTeam TRs: "+Arrays.toString(trIds));
		
		return trIds;
	}
	
	public static XMLGregorianCalendar getSpiraTeamServerTime() {
		try{
			return SpiraTeam.getInstance().soap.systemGetServerDateTime();
		} catch(Exception e) {
			try {
				return DateFunctions.getXMLGregorianCalendarNow();
			} catch (DatatypeConfigurationException e1) {
				AutomationLogger.getInstance().warn(e);
				return null;
			} 
		}
	}
	
	public static int getReleaseId(int projectId,String build) {
		int releaseId=-1;
		SpiraTeam spira=SpiraTeam.getInstance();
		try{
			spira.auth();
			spira.connectToProject(projectId);
			List<RemoteRelease> releases= spira.soap.releaseRetrieve(true).getRemoteRelease();
				
			String projectName=spira.soap.projectRetrieveById(projectId).getName().getValue().toUpperCase();
			build=projectName+" "+build;
				
			for(RemoteRelease release:releases) {
				if(build.startsWith(release.getVersionNumber().getValue())) {
					releaseId=release.getReleaseId().getValue();
					break;
				}
			}
		} catch(Exception e) {
			AutomationLogger.getInstance().warn(e);
		}
			
			
		return releaseId;
	}
	
	public static int getProjectId(String projectName) {
		int prodjectId=-1;
		SpiraTeam spira=SpiraTeam.getInstance();
		try{
			spira.auth();
			List<RemoteProject> projects= spira.soap.projectRetrieve().getRemoteProject();
							
			for(RemoteProject p:projects) {
				if(projectName.equalsIgnoreCase(p.getName().getValue())) {
					prodjectId=p.getProjectId().getValue();
					break;
				}
			}
		} catch(Exception e) {
			AutomationLogger.getInstance().warn(e);
		}
		return prodjectId;
	}
	
	public static void main(String[] args) {
		try {
			AwoUtil.initAwo();
			String build="3.04.02.32";
			String projectName="orms";
			String testCaseId="1282";
			int result=2;
			String runnerName="Selenium";
			String testcaseName="FM_LoginSanity";
			String errorStatckTrace="";
			String msg="";
			recordTestResult(build, projectName, testCaseId, result, getSpiraTeamServerTime(), getSpiraTeamServerTime(), runnerName, testcaseName, msg, errorStatckTrace);
//			System.out.println(getProjectId(""));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
