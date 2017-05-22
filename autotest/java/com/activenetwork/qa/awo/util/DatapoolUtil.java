package com.activenetwork.qa.awo.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.hyades.execution.runtime.datapool.IDatapool;
import org.eclipse.hyades.execution.runtime.datapool.IDatapoolFactory;
import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;
import org.eclipse.hyades.models.common.datapool.impl.Common_DatapoolFactoryImpl;

import com.activenetwork.qa.awo.supportscripts.SetupCase.RAMap;
import com.activenetwork.qa.testapi.util.AutomationLogger;
import com.activenetwork.qa.testapi.util.StringUtil;

public class DatapoolUtil {

	private static AutomationLogger logger = AutomationLogger.getInstance();
	protected static IDatapoolIterator dpIter; // datapool iterator
	protected static IDatapool dp;
	private static List<String> datapoolPath = new ArrayList<String>();
	private static AwoDatabase dbCon = AwoDatabase.getInstance();
	private static final String SUPPT_SCRIPT_PATH = AwoUtil.PROJECT_PATH+"/supportscripts/qasetup/license/";
	private static final String PREFIX_ = "D_hf_";
	private static int START_POINT = 0;
	private static int END_POINT = 999;
	
	private static void retriveAllDatapoolFilePath(String path){
		File file = new File(path);
		iterateFile(file);
	}
	
	private static void iterateFile(File file){
		File[] files = file.listFiles();
		for(File f:files){
			if(f.isDirectory()){
				iterateFile(f);
			}else {
				String path = f.getAbsolutePath();
				if(path.toLowerCase().endsWith(".datapool")){
					datapoolPath.add(path);
				}
			}
		}
	}
	
	private static void initializeDataPool(String dpFileStr) {
		if (StringUtil.isEmpty(dpFileStr))
			return;

		File dpFile = getDatapoolFile(dpFileStr);

		logger.info("Init datapool " + dpFile);

		IDatapoolFactory dpFactory = new Common_DatapoolFactoryImpl();

		dp = dpFactory.load(dpFile, true);

		dpIter = dpFactory
				.open(dp,
						"org.eclipse.hyades.datapool.iterator.DatapoolIteratorSequentialPrivate");
		dpIter.dpInitialize(dp);
	}
	
	private static String getHeaderInfo(){
		StringBuilder sb = new StringBuilder();
		int size = dp.getVariableCount();
		for(int i=0;i<size;i++){
			sb.append(dp.getVariable(i).getName());
			if(i<size-1){
				sb.append(",");
			}
		}
		
		return sb.toString();
	}

	protected static File getDatapoolFile(String dpFileStr) {
		if (!dpFileStr.toLowerCase().endsWith(".datapool"))
			dpFileStr += ".datapool";

		File dpFile = new File(dpFileStr);

		return dpFile;
	}

	public static void main(String[] args){
		boolean tableExist = true; // this means data table already there, no need to create table, just migrate data
		if(tableExist){
			//migrate specific data pool data from row start to end into table
			DatapoolUtil.migrateDataIntoTable(AwoUtil.PROJECT_PATH+"/supportscripts/adminmgr/ruledatapool/Access Type.datapool", START_POINT, END_POINT);
		}else{
			DatapoolUtil.retriveAllDatapoolFilePath(SUPPT_SCRIPT_PATH);
			for(String path:datapoolPath){
				DatapoolUtil.initializeDataPool(path);
				DatapoolUtil.batchMigrateDatapoolIntoTable(dp.getName(), DatapoolUtil.getHeaderInfo().toString(),START_POINT,END_POINT);
			}
		}
	}
	
	private static void migrateDataIntoTable(String fileName,int startPoint,int endPoint){
		initializeDataPool(fileName);
		String tableName = convertDataPoolNameIntoTableName(dp.getName());
		insertDataIntoDB(tableName,  DatapoolUtil.getHeaderInfo().toString().split(","), startPoint, endPoint);
	}
	
	private static String convertDataPoolNameIntoTableName(String datapoolName){
		return PREFIX_+dp.getName().replaceAll(" ", "_").toLowerCase();
	}
	
	private static void insertDataIntoDB(String tableName,String[] colNames,int start,int end){
		int counter = 0;
		String query = "select Max(id) as id from "+tableName;
		String id = dbCon.executeQuery(query, "id", 0);
		int idSeq = Integer.parseInt((id == null ? "0" : id));

		while(!dpIter.dpDone()){
			if(counter < start) {
			  	counter++;
			  	dpIter.dpNext();
		  	  	continue;
		  	}
			if(counter>end){
				break;
			}
			StringBuilder sql = new StringBuilder();

			idSeq += 10;
			sql.append("insert into "+tableName).append(" values(");
			sql.append("'").append(idSeq).append("'");
			for(String colName:colNames){
				sql.append(",");
				sql.append("'");
				sql.append(dpIter.dpString(colName).replaceAll("'", "''"));
				sql.append("'");
			}
			sql.append(")");
			logger.info(sql);
			dbCon.executeUpdate(sql.toString());
			counter++;
			dpIter.dpNext();
		}
	}
	
	private static void batchMigrateDatapoolIntoTable(String dpName,String columnInfos,int start,int end){
			String tableName = convertDataPoolNameIntoTableName(dpName);
			String[] colNames = columnInfos.split(",");
			createTable(tableName, colNames);
			batchInsertData(tableName, colNames,start,end);
	}
	
	private static void createTable(String tableName,String[] colNames){
		logger.info("Create Table: "+tableName);
		
		StringBuilder sql = new StringBuilder();
		sql.append("Create table "+tableName).append(" ( ");
		sql.append("ID NUMBER NOT NULL ");
		
		for(String colName:colNames){
			sql.append(",").append(colName.replaceAll(" ", "_")).append(" VARCHAR2(256) ");
		}
		sql.append(",").append("CONSTRAINT "+tableName+"_PK PRIMARY KEY (ID) ENABLE)");
		logger.debug(sql);
		dbCon.executeUpdate(sql.toString());
	}
	
	private static void batchInsertData(String tableName,String[] colNames,int start,int end){
		logger.info("Insert data into Table "+tableName);
		List<String> insertSqls = new ArrayList<String>();
		String query = "select Max(id) as id from "+tableName;
		String id = dbCon.executeQuery(query, "id", 0);
		int idSeq = Integer.parseInt((id == null ? "0" : id));
		
		int counter = 0;
		while(!dpIter.dpDone()){
			if(counter < start) {
			  	counter++;
			  	dpIter.dpNext();
		  	  	continue;
		  	}
			if(counter>end){
				break;
			}
			StringBuffer sql = new StringBuffer();
			
			idSeq += 10;
			sql.append("insert into "+tableName).append(" values(");
			sql.append("'").append(idSeq).append("'");
			for(String colName:colNames){
				sql.append(",");
				sql.append("'");
				sql.append(dpIter.dpString(colName).replaceAll("'", "''"));
				sql.append("'");
			}
			sql.append(")");
			logger.debug(sql);
			insertSqls.add(sql.toString());
			counter++;
			dpIter.dpNext();
		}
		dbCon.executeBatch(insertSqls);
	}
	
	@SuppressWarnings({"unchecked" })
	public static <T> T fill(Class<T> dataCollection, Object dpIter) {
		Object instance = null;
		try {
			instance = dataCollection.newInstance();
			Class clz = dataCollection;
			while (!clz.equals(Object.class)) {
				Field[] fs = clz.getDeclaredFields();
				for (Field f : fs) {
					int mod = f.getModifiers();
					// Non static or final field
					if (!Modifier.isStatic(mod) && !Modifier.isFinal(mod)) {
						Class fieldType = f.getType();

						// Not Array, Collection or Map
						// TODO: find a way to support them
						if (!fieldType.isArray()
							&& !Collection.class.isAssignableFrom(fieldType)
							&& !Map.class.isAssignableFrom(fieldType)) {
							Object value = getValue(dpIter, fieldType, f.getName());
							
							if (value != null) {
								f.set(instance, value);
							}
						}
					}
				}
				clz = clz.getSuperclass();
			}
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}

		return (T) instance;
	}

	@SuppressWarnings({ "unchecked" })
	private static Object getValue(Object dpIter, Class fieldType,
			String name) {
		Object returnVal = null;
		String typeName = null;

		if (fieldType.equals(Boolean.TYPE) || fieldType.equals(Boolean.class)) {
			typeName = "Boolean";
		} else if (fieldType.equals(Integer.TYPE)
				|| fieldType.equals(Integer.class)) {
			typeName = "Int";
		} else if (fieldType.equals(Double.TYPE)
				|| fieldType.equals(Double.class)) {
			typeName = "Double";
		} else if (fieldType.equals(String.class)) {
			typeName = "String";
		}

		if (typeName == null) {
			logger.warn("Currently we only support "
					+ "Boolean/boolean, "
					+ "Integer/int, "
					+ "Double/double & String, "
					+ "if you want to use " + fieldType.getName() + ", please feel free to hacking :)");
		} else {

			try {
				if(dpIter instanceof IDatapoolIterator){
					Method dpMethod = ((IDatapoolIterator)dpIter).getClass().getMethod("dp" + typeName,
							String.class);
					returnVal = dpMethod.invoke(dpIter, name);
				}else if(dpIter instanceof RAMap||dpIter instanceof com.activenetwork.qa.awo.supportscripts.SetupCase.RAMap){
					if(((RAMap)dpIter).get(name)!=null){
						if(typeName.equals("Boolean")){
							returnVal = Boolean.parseBoolean(((RAMap)dpIter).get(name));
						}else if(typeName.equals("Int")){
							returnVal = Integer.parseInt(((RAMap)dpIter).get(name));
						}else if(typeName.equals("Double")){
							returnVal = Double.parseDouble(((RAMap)dpIter).get(name));
						}else{
							returnVal = ((RAMap)dpIter).get(name);
						}
					}
				}
			} catch (SecurityException e) {
				throw new RuntimeException(e);
			} catch (NoSuchMethodException e) {
				throw new RuntimeException(e);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (InvocationTargetException ite) {
				Throwable e = ite.getCause();
				if (e.getMessage().contains("Nonexistent variable name")) {
					logger.warn("Couldn't found column <" + name + "> in datapool, ignore it.");
				} else {
					throw new RuntimeException(e);
				}
			}
		}
		return returnVal;
	}
}
