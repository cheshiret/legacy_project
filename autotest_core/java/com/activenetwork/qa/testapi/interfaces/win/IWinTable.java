package com.activenetwork.qa.testapi.interfaces.win;

import com.activenetwork.qa.testapi.interfaces.ITable;

public interface IWinTable extends ITable {
	public String getSelectedRowValue(int identifyColumn);
	public int getSelectedRowNumber(int identifyColumn);
	public void select(String rowOption, int identifyColumn);
	public void select(int row, int identifyColumn);
	public void select(int row, String columnName);
	public void select(int row);
}
