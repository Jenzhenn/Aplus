package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DBManager;
import net.miginfocom.swing.MigLayout;

import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import javax.swing.JSplitPane;




import java.awt.FlowLayout;

public class ManagerFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textEIDField;
	private String eid;
	private String name;
	private DBManager db;
	
	private JPanel panelLeft;
	private JPanel panelRight;
	
	/**

	/**
	 * Create the frame.
	 */
	public ManagerFrame(DBManager db) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 526, 417);
		setTitle("Manager View");
		this.db = db;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][][][][][][][grow]", "[][][][][][][][][grow]"));

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
               db.quit();
               System.out.println("db quit");
               System.exit(0);
            }
          });		
		
		JPanel mainPanel = new JPanel();
		contentPane.add(mainPanel, "cell 0 0 12 9,grow");
		mainPanel.setLayout(new CardLayout(0, 0));
		
		JPanel panelLogin = new JPanel();
		mainPanel.add(panelLogin, "Login");
		
		JLabel label = new JLabel("Enter EID:");
		panelLogin.add(label);
		
		textEIDField = new JTextField();
		textEIDField.setColumns(10);
		panelLogin.add(textEIDField);
		
		JButton button = new JButton("Login");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String[] eidEnamePair =db.managerLogin(textEIDField.getText());
					eid = eidEnamePair[0];
					name = eidEnamePair[1];
					JLabel lblEid = new JLabel("EID: "+eid);
					panelLeft.add(lblEid, "cell 0 9,aligny bottom");
					JLabel lblName = new JLabel("Name:"+name);
					panelLeft.add(lblName, "cell 0 10,aligny bottom");
					CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
					cardLayout.show(mainPanel, "Manager view");
				} catch (SQLException error) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(mainPanel, "Login Fail");
				}				
			}
		});
		panelLogin.add(button);
		
		JPanel panelManagerView = new JPanel();
		mainPanel.add(panelManagerView, "Manager view");
		panelManagerView.setLayout(new MigLayout("", "[50px,grow][][][][][][][]", "[41px,grow][][][][][][]"));
		
		JSplitPane splitPane = new JSplitPane();
		panelManagerView.add(splitPane, "cell 0 0 8 7,grow");
		
		panelLeft = new JPanel();
		splitPane.setLeftComponent(panelLeft);
		
		JButton btnMovieInfo = new JButton("Movie Info");

		btnMovieInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cardLayout = (CardLayout) panelRight.getLayout();
				cardLayout.show(panelRight, "Movie Info");
			}
		});
		panelLeft.setLayout(new MigLayout("wrap 1", "[grow,fill]", "[][][][][]"));
		panelLeft.add(btnMovieInfo, "cell 0 0,alignx left,aligny top");
		
		JButton btnBuyTickets = new JButton("Sell Ticket");
		btnBuyTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cardLayout = (CardLayout) panelRight.getLayout();
				cardLayout.show(panelRight, "Sell Tickets");
			}
		});
		panelLeft.add(btnBuyTickets, "cell 0 1");
		
		JButton btnSetMovie = new JButton("Set Movie");
		panelLeft.add(btnSetMovie, "cell 0 2");
		
		JButton btnEmployeeList = new JButton("Employee List");
		btnEmployeeList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cardLayout = (CardLayout) panelRight.getLayout();
				cardLayout.show(panelRight, "Employee List");
			}
		});
		panelLeft.add(btnEmployeeList, "cell 0 3");
		
		JButton btnTicketsSold = new JButton("Tickets Sold");
		btnTicketsSold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) panelRight.getLayout();
				cardLayout.show(panelRight, "Tickets Sold");
			}
		});
		panelLeft.add(btnTicketsSold, "cell 0 4");
		
		panelRight = new JPanel();
		splitPane.setRightComponent(panelRight);
		panelRight.setLayout(new CardLayout(0, 0));
		
		JPanel panelMovieInfo = new MovieInfoPanel(db);
		panelRight.add(panelMovieInfo,"Movie Info");
		
		JPanel panelEmployeeList = new EmployeeListPanel(db);
		panelRight.add(panelEmployeeList, "Employee List");
		
		JPanel panelSellTicket = new TicketPanel();
		panelRight.add(panelSellTicket, "Sell Tickets");
		
		JPanel panelTicketSold = new MostLeastSoldPanel();
		panelRight.add(panelTicketSold, "Tickets Sold");
		pack();
	}


}
