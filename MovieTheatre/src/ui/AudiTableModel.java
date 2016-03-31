package ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import core.Auditorium;
import core.Employee;
import core.Ticket;


public class AudiTableModel extends AbstractTableModel{
	
	private static final int AUDITORIUM_COL = 0;
	private static final int SEAT_COL = 1;

	private String[] columnNames = { "Auditorium","Available Seats"};
	private List<Auditorium> auditoriums;

	public AudiTableModel(List<Auditorium> audis) {
		auditoriums = audis;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return auditoriums.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Auditorium tempAudi = auditoriums.get(rowIndex);
		
		switch(columnIndex){
		case AUDITORIUM_COL:
			return tempAudi.getAudi();
		case SEAT_COL:
			return tempAudi.getAvailSeat();
		default:
			return tempAudi.getAudi();
		}
	}
	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}
