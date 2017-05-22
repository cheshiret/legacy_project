package com.activenetwork.qa.testapi.interfaces;

import java.util.List;

public interface ITable {
	public int rowCount();

    public int columnCount();
       
    public String getCellValue(int row,int col);
    
    public List<String> getCellValue(String pattern);
    
    public int findColumn(int row,Object value);
    
    public int findRow(int col, Object value);
    
    public int findRow(int startRow, int col, Object value);
    
    public int findColumn(int startCol, int row, Object value);
    
    public int[] findColumns(int startCol, int row, Object value);
    
    public List<String> getColumnValues(int col);
    
    public List<String> getRowValues(int row);
    
    public String[][] getTableValues();

	public int[] findRows(int col, Object value);

}
