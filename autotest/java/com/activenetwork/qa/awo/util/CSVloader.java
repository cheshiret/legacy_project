package com.activenetwork.qa.awo.util;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.sql.ResultSet ;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.util.CSVUtil;
import com.activenetwork.qa.awo.util.AwoUtil;


/**
 * 
 * @Description: Load csv with specific filename
 * @Preconditions: With test property settings
  
 * @author tchen
 * @Date  Sep 29, 2014
 */


public class CSVLoader {
	
	static CSVLoader _instance = null;
	public static CSVLoader getInstance() {
		if (null == _instance)
			_instance = new CSVLoader();
		return _instance;
	}
	
	private static Logger logger=Logger.getLogger(CSVLoader.class);
	private static String file_path = AwoUtil.CSV_DATA;
	public static ArrayList<List<String>> t = new ArrayList<List<String>>();	
	public static int colnum = 0;
	public static int rownum = 0;
	
	public void SetFile(String file_name) {
		try {
//			ArrayList<List<String>> t = new ArrayList<List<String>>();			
			List<String> list = new ArrayList<String>();
			CSVReader reader = new CSVReader(new FileReader(file_path + File.separator+ file_name));			
			String [] nextLine = null;		
			
			int i = 0;
			int k = 0;
			while ((nextLine = reader.readNext()) != null) {
				String[] stringArray = list.toArray(new String[nextLine.length]);
				for (int j = 0; j < nextLine.length; j++) {
					stringArray[j] = nextLine[j];
	                k = j + 1;
	                
				}
				List<String> t2 = Arrays.asList(stringArray);
				t.add(t2);	
				i ++ ;						
		}
			reader.close();
//			System.out.println("Row # is: " + i);
//			System.out.println("Column # is: " + k);
			rownum=i;
			colnum=k;
			logger.info("Data of CSV file has been saved into the array:");
		} catch (IOException e) {
			logger.warn("Error" + e);
		}
	}
//	public String GetFile() {
//	       return t;
//	}

	public static class Table {
		public static ArrayList<List<String>> Data() {
			return t;
//		try {
//			ArrayList<List<String>> t = new ArrayList<List<String>>();			
//			List<String> list = new ArrayList<String>();
//			CSVReader reader = new CSVReader(new FileReader(file_path + File.separator+ file_name));			
//			String [] nextLine = null;		
//			
//			int i = 0;
//			int k = 0;
//			while ((nextLine = reader.readNext()) != null) {
//				String[] stringArray = list.toArray(new String[nextLine.length]);
//				for (int j = 0; j < nextLine.length; j++) {
//					stringArray[j] = nextLine[j];
//	                k = j + 1;
//	                
//				}
//				List<String> t2 = Arrays.asList(stringArray);
//				t.add(t2);	
//				i ++ ;						
//		}
//			reader.close();
////			System.out.println("Row # is: " + i);
////			System.out.println("Column # is: " + k);
//			col=i;
//			row=k;
//			logger.info("Data of CSV file has been saved into the array:");
//			return t;
//		} catch (IOException e) {
//			logger.warn("Error" + e);
//			return null;			
//		}
		}
		
		public static int getRowNum() {
			
			//CSVLoader.Data;
			return rownum;
//			tableArray();
//			return tableArray.length;
		}
		
		public static int getColNum() {
			return colnum;
//			tableArray();
//			return tableArray.length;
		}
		public static List<String> getFieldName() {
			boolean haveField = CSVUtil.containsField();
			String getfieldType = CSVUtil.fieldType(); 
			if (haveField) {
				if (getfieldType.contains("col")) {
					return t.get(0);
				}
				else {
					List<String> fN = new ArrayList<String>();
					for (int i=0;i<colnum; i++){
						fN.add(t.get(i).get(0));					
					}
						
					return fN;
					}
				}					
			else{			
				logger.warn("There is no field name saved in the CSV.");
				return null;
			}
		}
	}

	public void finalize(){
	}
		
}


