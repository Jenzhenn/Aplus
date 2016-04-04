package ui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import core.Auditorium;
import dao.DBManager;
import net.miginfocom.swing.MigLayout;

public class SetMoviePanel extends JPanel {
	
	private DBManager dbManager;
	private String managerID;
	

	public SetMoviePanel(DBManager db, String managerid) {
		removeAll();


		dbManager = db;
		managerID = managerid;
		
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

		JLabel lblSetMovie = new JLabel("Set Movie");
		lblSetMovie.setBounds(10, 12, 342, 57);
		lblSetMovie.setFont(new Font("Cordia New", Font.BOLD, 20));
		labelPanel.add(lblSetMovie);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 67, 430, 1);
		labelPanel.add(separator);

		JPanel selectionPanel = new JPanel();
		selectionPanel.setBounds(0, 78, 450, 300);
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
		JComboBox<String> auditDropDown = new JComboBox<String>();
		
		JComboBox<String> timeDropDown = new JComboBox<String>();

		titleDropDown.removeAllItems();
		auditDropDown.removeAllItems();
		
		timeDropDown.removeAllItems();

		// get movie titles
		try {

			List<String> movies;
			movies = db.showAllMovie();

			for (String title : movies) {
				titleDropDown.addItem(title);
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		titleDropDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get available auditoriums
				auditDropDown.removeAllItems();
			
				//timeDropDown.removeAllItems();

				try {
					List<Integer> auditorium;
					auditorium = db.showAvailableAud();
					for (Integer aud : auditorium) {
						System.out.println(aud);
						auditDropDown.addItem(Integer.toString(aud));
						
					}
					//timeDropDown.removeAllItems();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

			}
		});
		

		titlePanel.add(titleDropDown);
		//auditorium
		JPanel audPanel = new JPanel();
		audPanel.setBounds(40, 78, 356, 32);
		selectionPanel.add(audPanel);
		audPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblSelectAud = new JLabel("Select auditorium:");
		lblSelectAud.setHorizontalAlignment(SwingConstants.LEFT);
		lblSelectAud.setFont(new Font("Cordia New", Font.BOLD, 25));
		audPanel.add(lblSelectAud);

		audPanel.add(auditDropDown);
		
		//date
		JTextField dateField = new JTextField();
		selectionPanel.add(dateField);
		dateField.setColumns(10);
		
		JPanel datePanel = new JPanel();
		datePanel.setBounds(40, 120, 356, 32);
		selectionPanel.add(datePanel);
		datePanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblEnterDate = new JLabel("Enter Date");
		lblEnterDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnterDate.setFont(new Font("Cordia New", Font.BOLD, 25));
		datePanel.add(lblEnterDate);
		
		datePanel.add(dateField);
		
		
		//time
		JTextField timeField = new JTextField();
		selectionPanel.add(timeField);
		dateField.setColumns(10);
		
		JPanel timePanel = new JPanel();
		timePanel.setBounds(40, 160, 356, 32);
		selectionPanel.add(timePanel);
		timePanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblEnterTime = new JLabel("Enter time:");
		lblEnterTime.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnterTime.setFont(new Font("Cordia New", Font.BOLD, 25));
		timePanel.add(lblEnterTime);

		timePanel.add(timeField);
		
		
		

		JButton setButton = new JButton("Set");
		setButton.setFont(new Font("Cordia New", Font.PLAIN, 18));
		setButton.setBounds(353, 210, 87, 23);
		selectionPanel.add(setButton);

		setButton.addActionListener(new ActionListener() {
			private String showtime;
			private String showdate;
			private String format;
			private int audinum;
			private String mid;
			private String eid;

			@Override
			public void actionPerformed(ActionEvent e) {
				String movie = titleDropDown.getSelectedItem().toString(); //get a prepared statement
				try {
					mid = db.getMid(movie);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String aud = auditDropDown.getSelectedItem().toString();
				audinum = Integer.parseInt(aud);
				try {
					format = dbManager.getAudType(audinum);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				showtime = timeField.getText();
				showdate = dateField.getText();
				eid = managerid;
				
				try {
					db.setMovie(showtime, showdate, format, audinum, mid, eid);
					JOptionPane.showMessageDialog(labelPanel, "Successful Set");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				db.quit();
				db.dbConnector();
				
				//removeAll();
				invalidate();
				validate();
				//repaint();

			}
		});

	}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}


