package ui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

import dao.DBManager;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.SystemColor;

public class TicketPanel extends JPanel {
	private DBManager dbManager;

	/**
	 * Create the panel.
	 */
	
	public TicketPanel(DBManager db) {
		
		dbManager = db;
		
		// create dbManager
		try {
			dbManager = new DBManager();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
		}
		setLayout(null);
		
		JPanel labelPanel = new JPanel();
		labelPanel.setBounds(0, 0, 450, 79);
		add(labelPanel);
		labelPanel.setLayout(null);
		
		JLabel lblPurchasingTicket = new JLabel("Purchasing Ticket: ");
		lblPurchasingTicket.setBounds(10, 12, 342, 57);
		lblPurchasingTicket.setFont(new Font("Cordia New", Font.BOLD, 30));
		labelPanel.add(lblPurchasingTicket);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 67, 430, 1);
		labelPanel.add(separator);
		
		JPanel selectionPanel = new JPanel();
		selectionPanel.setBounds(0, 78, 450, 222);
		add(selectionPanel);
		selectionPanel.setLayout(null);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(40, 36, 356, 32);
		selectionPanel.add(titlePanel);
		titlePanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblSelectTitle = new JLabel("Select movie:");
		lblSelectTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblSelectTitle.setFont(new Font("Cordia New", Font.BOLD, 25));
		titlePanel.add(lblSelectTitle);
		
		JComboBox<String> titleDropDown = new JComboBox<String>();
		JComboBox<String> dateDropDown = new JComboBox<String>();
		JComboBox<String> timeDropDown = new JComboBox<String>();
		
		titleDropDown.removeAllItems();
		dateDropDown.removeAllItems();
		timeDropDown.removeAllItems();
		
		
		//get movie titles
		try {
			
			List<String> movies;
			movies = db.showPlayingMovie();
			
			for(String title:movies){
				titleDropDown.addItem(title);
			}
			
		}catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		titleDropDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get dates given selected movie 
				dateDropDown.removeAllItems();
				timeDropDown.removeAllItems();			

				try {
					List<String> dates;
					dates = db.showAvailableDate((String) titleDropDown.getSelectedItem());
					for(String date:dates){
						dateDropDown.addItem(date);
					}
					timeDropDown.removeAllItems();
				}catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
			}
		});

		dateDropDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timeDropDown.removeAllItems();
				// get times given selected movie and date
				try {
					List<String> times;
					times = db.showAvailableTime((String) titleDropDown.getSelectedItem(),(String) dateDropDown.getSelectedItem());
					for(String time:times){
						timeDropDown.addItem(time);
					}
				}catch (Exception e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			}
		});
					
		titlePanel.add(titleDropDown);
		
		JPanel datePanel = new JPanel();
		datePanel.setBounds(40, 78, 356, 32);
		selectionPanel.add(datePanel);
		datePanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblSelectDate = new JLabel("Select date:");
		lblSelectDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblSelectDate.setFont(new Font("Cordia New", Font.BOLD, 25));
		datePanel.add(lblSelectDate);
		
		
		datePanel.add(dateDropDown);
		
		JPanel timePanel = new JPanel();
		timePanel.setBounds(40, 120, 356, 32);
		selectionPanel.add(timePanel);
		timePanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblSelectTime = new JLabel("Select time:");
		lblSelectTime.setHorizontalAlignment(SwingConstants.LEFT);
		lblSelectTime.setFont(new Font("Cordia New", Font.BOLD, 25));
		timePanel.add(lblSelectTime);
		
		
		timePanel.add(timeDropDown);
				
		JButton searchButton = new JButton("Search");
		searchButton.setFont(new Font("Cordia New", Font.PLAIN, 18));
		searchButton.setBounds(353, 189, 87, 23);
		selectionPanel.add(searchButton);

	}
}
