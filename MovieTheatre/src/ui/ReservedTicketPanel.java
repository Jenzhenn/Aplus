package ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import core.Movie;
import core.Ticket;
import dao.DBManager;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

public class ReservedTicketPanel extends JPanel {
	private JTable ticketTable;
	private DBManager dbManager;
	private String cphone;


	/**
	 * Create the panel.
	 */
	public ReservedTicketPanel(DBManager db, String cphone) {
		this.dbManager = db;
		this.cphone = cphone;
		
		setLayout(new MigLayout("", "[145px,grow]", "[10px][25px,grow]"));		
		JLabel lblYourReservedTickets = new JLabel("Your reserved ticket(s):");
		lblYourReservedTickets.setFont(new Font("Cordia New", Font.BOLD, 18));
		add(lblYourReservedTickets, "cell 0 0");

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1,grow");
		
		ticketTable = new JTable();
		scrollPane.setViewportView(ticketTable);
		
		
        try {
            List<Ticket> tickets = null;
            tickets = dbManager.seeReservedTicket(this.cphone);
            ReservedTicketModel ticketModel = new ReservedTicketModel(tickets);
            ticketTable.setModel(ticketModel);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

	}

}
