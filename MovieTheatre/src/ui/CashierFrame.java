package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DBManager;

import java.awt.CardLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.GridBagLayout;

public class CashierFrame extends JFrame {
	private JTextField textEIDField;
	private String eid;
	private String name;
	private DBManager db;
	
	private JPanel panelLeft;
	private JPanel panelRight;
	/**
	 * Create the frame.
	 */
	public CashierFrame(DBManager db) {
		this.db = db;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Cashier View");
		getContentPane().setLayout(new MigLayout("", "[][][][][][][][][][][][grow]", "[][][][][][][][][grow]"));
		
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, "cell 0 0 12 9,grow");
		mainPanel.setLayout(new CardLayout(0, 0));
		
		JPanel panelLogin = new JPanel();
		mainPanel.add(panelLogin, "name_171831861873876");
		
		JLabel lblEnterEid = new JLabel("Enter EID:");
		panelLogin.add(lblEnterEid);
		
		textEIDField = new JTextField();
		panelLogin.add(textEIDField);
		textEIDField.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
				cardLayout.show(mainPanel, "Cashier view");
				try {
					String[] eidEnamePair =db.getEIDEname(textEIDField.getText());
					eid = eidEnamePair[0];
					name = eidEnamePair[1];
					JLabel lblEid = new JLabel("EID: "+eid);
					panelLeft.add(lblEid, "cell 0 6");
					JLabel lblName = new JLabel("Name:"+name);
					panelLeft.add(lblName, "cell 0 7");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(mainPanel, "Invalid SQL Operation");
				}
			}
		});
		panelLogin.add(btnNewButton);
		
		JPanel panelCashier = new JPanel();
		mainPanel.add(panelCashier, "name_171853031906081");
		
		mainPanel.add(panelLogin, "Login");
		mainPanel.add(panelCashier, "Cashier view");
		panelCashier.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JSplitPane splitPane = new JSplitPane();
		panelCashier.add(splitPane, "cell 0 0,grow");
		
		//Left Panel Contain Button and eid,ename (created in Login ActionListener)
		panelLeft = new JPanel();
		splitPane.setLeftComponent(panelLeft);
		panelLeft.setLayout(new MigLayout("", "[83px]", "[23px][][][][][][][]"));
		
		JButton btnMovieInfo = new JButton("Movie Info");
		panelLeft.add(btnMovieInfo, "cell 0 0,alignx left,aligny top");
		
		JButton btnSellTicket = new JButton("Sell Ticket");
		panelLeft.add(btnSellTicket, "cell 0 1");
		

		//Right Panel should show correct information correspond to the pushed button
		panelRight = new JPanel();
		splitPane.setRightComponent(panelRight);
		panelRight.setLayout(new CardLayout(0, 0));
	}
}
