package ui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

import dao.DBManager;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import core.Auditorium;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JSeparator;
import javax.swing.JTable;

import java.awt.Color;
import java.awt.SystemColor;

public class TicketPanel extends JPanel {
	private DBManager dbManager;

	/**
	 * Create the panel.
	 */

	public TicketPanel(DBManager db) {
		removeAll();


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

		// get movie titles
		try {

			List<String> movies;
			movies = db.showPlayingMovie();

			for (String title : movies) {
				titleDropDown.addItem(title);
			}

		} catch (Exception e1) {
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
					for (String date : dates) {
						dateDropDown.addItem(date);
					}
					timeDropDown.removeAllItems();
				} catch (Exception e2) {
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
					times = db.showAvailableTime((String) titleDropDown.getSelectedItem(),
							(String) dateDropDown.getSelectedItem());
					for (String time : times) {
						timeDropDown.addItem(time);
					}
				} catch (Exception e3) {
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

		searchButton.addActionListener(new ActionListener() {
			private String s1;
			private String s2;
			private String s3;

			@Override
			public void actionPerformed(ActionEvent e) {
				s1 = titleDropDown.getSelectedItem().toString();
				s2 = dateDropDown.getSelectedItem().toString();
				s3 = timeDropDown.getSelectedItem().toString();
				removeAll();
				invalidate();
				validate();
				repaint();

				JPanel audiPanel = new JPanel();
				audiPanel.setBounds(0, 78, 450, 222);
				add(audiPanel);
				validate();
				audiPanel.setLayout(new MigLayout("", "[145px,grow]", "[10px][25px,grow]"));	

				JPanel top = new JPanel();
				TitledBorder title;
				title = BorderFactory.createTitledBorder("Auditorium List");
				audiPanel.setBorder(title);

				audiPanel.add(top);
				validate();
				top.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

				JLabel s_1 = new JLabel(s1);
				top.add(s_1);
				JLabel s_2 = new JLabel(s2);
				top.add(s_2);
				JLabel s_3 = new JLabel(s3);
				top.add(s_3);

				JTable tableAudi = new JTable();
				JScrollPane scrollPane = new JScrollPane();
				audiPanel.add(scrollPane, "cell 0 1,grow");

				scrollPane.setViewportView(tableAudi);
				tableAudi.setRowSelectionAllowed(false);
				tableAudi.setCellSelectionEnabled(true);
				
				try {
					List<Auditorium> audis;
					audis = db.availableSeats(s1, s2, s3);
					AudiTableModel audiModel = new AudiTableModel(audis);
					tableAudi.setModel(audiModel);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


				JButton btnBuy = new JButton("Buy");
				btnBuy.setFont(new Font("Cordia New", Font.PLAIN, 18));
				btnBuy.setBounds(353, 189, 87, 23);
				audiPanel.add(btnBuy);
				
			
			}
		});

	}
}
