package com.activenetwork.qa.awo.util;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;




public class GenerateTestCombinations  {
	private static final String ADDRESS_FILE="resources//csv//csvfolders//addresses.csv";

	    
		public static void main(String[] args) throws IOException {
			
			ArrayList<List<String>> t = new ArrayList<List<String>>();			
			List<String> list = new ArrayList<String>();		
			CSVReader reader = new CSVReader(new FileReader(ADDRESS_FILE));			
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
			
//			System.out.println("\n\nGenerated CSV File:\n\n");
//			System.out.println(t);
			System.out.println("Row # is: " + i);
			System.out.println("Column # is: " + k);
			reader.close();
			
			
		}

}

