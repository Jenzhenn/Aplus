package ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import core.Movie;
import core.Ticket;

public class ReservedTicketModel extends AbstractTableModel{
    private static final int CODE_COL = 0;
    private static final int TITLE_COL = 1;
    private static final int DATE_COL = 2;
    private static final int TIME_COL = 3;
    private static final int AUDI_COL = 4;
    private static final int SEAT_COL = 5;
    
    private String[] columnNames = {"Confirmation Code", "Title", "Show Date", "Show time", "Auditorium", "Seat"};
    private List<Ticket> tickets;
    
    public ReservedTicketModel(List<Ticket> theTickets) {
        tickets = theTickets;
    }
    @Override
    public int getRowCount() {
        return tickets.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Ticket tempTicket = tickets.get(rowIndex);
        
        switch(columnIndex){
        case CODE_COL:
            return tempTicket.getConfCode();
        case TITLE_COL:
            return tempTicket.getTitle();
        case DATE_COL:
            return tempTicket.getShowDate();
        case TIME_COL:
        	return tempTicket.getShowTime();
        case AUDI_COL:
        	return String.valueOf(tempTicket.getAudiNum());
        case SEAT_COL:
        	return tempTicket.getSeatNum();
        default:
            return tempTicket.getConfCode();
        }
        
    }


    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
