package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import dao.DBManager;
import net.miginfocom.swing.MigLayout;

import java.awt.CardLayout;

import javax.swing.JSplitPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerFrame extends JFrame {

	private JPanel contentPane;
	private String eid;
	private String name;
	private DBManager db;
	
	private JPanel panelLeft;
	private JPanel panelRight;
	private JButton btnMovieInfo;
	private JButton btnBuyTicket;
	private JButton btnMemPoint;

	/**
	 * Create the frame.
	 */
	public CustomerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 794, 567);
		setTitle("Customer View");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JPanel mainPanel = new JPanel();
		contentPane.add(mainPanel, "cell 0 0,grow");
		mainPanel.setLayout(new CardLayout(0, 0));
		
		JPanel panelCustomer = new JPanel();
		mainPanel.add(panelCustomer, "Customer View");
		panelCustomer.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JSplitPane splitPane = new JSplitPane();
		panelCustomer.add(splitPane, "cell 0 0,grow");
		
		panelLeft = new JPanel();
		splitPane.setLeftComponent(panelLeft);
		panelLeft.setLayout(new MigLayout("wrap 1", "[grow,fill]", "[][][]"));
		
		btnMovieInfo = new JButton("Movie Info");
		btnMovieInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) panelRight.getLayout();
				cardLayout.show(panelRight, "Movie Info");
			}
		});
		panelLeft.add(btnMovieInfo, "cell 0 0,alignx left,aligny top");
		
		btnBuyTicket = new JButton("Buy Ticket");
		btnBuyTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) panelRight.getLayout();
				cardLayout.show(panelRight, "Sell Tickets");
			}
		});
		panelLeft.add(btnBuyTicket, "cell 0 1,alignx left,aligny top");
		
		btnMemPoint = new JButton("Membership Point");
		panelLeft.add(btnMemPoint, "cell 0 2");
		
		panelRight = new JPanel();
		splitPane.setRightComponent(panelRight);
		panelRight.setLayout(new CardLayout(0, 0));
		
		JPanel panelMovieInfo = new MovieInfoPanel();
		panelRight.add(panelMovieInfo,"Movie Info");
		
		JPanel panelSellTicket = new TicketPanel();
		panelRight.add(panelSellTicket, "Sell Tickets");
	}

}
