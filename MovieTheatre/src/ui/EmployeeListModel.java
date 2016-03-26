package ui;

import javax.swing.table.AbstractTableModel;
import core.Employee;
import java.util.List;

public class EmployeeListModel extends AbstractTableModel {
	
	private static final int EID_COL = 0;
	private static final int NAME_COL = 1;
	private static final int PHONE_COL = 2;
	private static final int SALARY_COL = 3;

	private String[] columnNames = {"EID", "Name", "Phone", "Salary"};
	private List<Employee> employees;
	
	public EmployeeListModel(List<Employee> theEmployees) {
		employees = theEmployees;
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return employees.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Employee tempEmployee = employees.get(rowIndex);
		
		switch(columnIndex){
		case EID_COL:
			return tempEmployee.getEID();
		case NAME_COL:
			return tempEmployee.getName();
		case PHONE_COL:
			return tempEmployee.getPhone();
		case SALARY_COL:
			return tempEmployee.getSalary();
		default:
			return tempEmployee.getEID();
		}
	}
	
	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}
