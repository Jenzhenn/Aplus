package ui;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import core.Auditorium;
import core.Employee;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dao.DBManager;
import net.miginfocom.swing.MigLayout;
import ui.MovieInfoPanel.MovieListCellRenderer;
import ui.MovieInfoPanel.ScrollableListContainer;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private JButton btnMemPoint_1;

	/**
	 * Create the frame.
	 */
	public CustomerFrame(DBManager db) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 794, 567);
		setTitle("Customer View");
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
		contentPane.add(mainPanel,"cell 0 0 12 9,grow");
		mainPanel.setLayout(new CardLayout(0, 0));
		
		JPanel panelCustomer = new JPanel();
		mainPanel.add(panelCustomer, "Customer View");
		panelCustomer.setLayout(new MigLayout("", "[50px,grow][][][][][][][]", "[41px,grow][][][][][][]"));
		
		JSplitPane splitPane = new JSplitPane();
		panelCustomer.add(splitPane, "cell 0 0 8 7,grow");
		
		panelLeft = new JPanel();
		splitPane.setLeftComponent(panelLeft);
		panelLeft.setLayout(new MigLayout("wrap 1", "[grow,fill]", "[][][][][]"));
		
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
		btnMemPoint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// add a dialog frame
				JFrame pointFrame = new PointFrame(db);
				pointFrame.setVisible(true);
	            pointFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
			}
		});
		panelLeft.add(btnMemPoint, "cell 0 3");
		
		
		panelRight = new JPanel();
		splitPane.setRightComponent(panelRight);
		panelRight.setLayout(new CardLayout(0, 0));

		btnMemPoint_1 = new JButton("See Ticket");
		btnMemPoint_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnMemPoint_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame phoneFrame = new PhoneFrame(db);
				phoneFrame.setVisible(true);
	            phoneFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});

		
		panelLeft.add(btnMemPoint_1, "cell 0 2");
		
		JPanel panelMovieInfo = new MovieInfoPanel(db);
		panelRight.add(panelMovieInfo,"Movie Info");
		
		JPanel panelSellTicket = new TicketPanel(db);
		panelRight.add(panelSellTicket, "Sell Tickets");
		
		
		
		pack();
		
			
	}

	public class PhoneFrame extends JFrame {

		private JPanel contentPane;
		private JTextField textField;
		private DBManager db;

		/**
		 * Create the frame.
		 */
		public PhoneFrame(DBManager db) {
			this.db=db;
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 273, 149);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblEnterPhone = new JLabel("Enter phone:");
			lblEnterPhone.setBounds(27, 35, 80, 20);
			contentPane.add(lblEnterPhone);
			lblEnterPhone.setFont(new Font("Cordia New", Font.PLAIN, 20));
			
			textField = new JTextField();
			textField.setFont(new Font("Cordia New", Font.PLAIN, 15));
			textField.setBounds(115, 35, 96, 21);
			contentPane.add(textField);
			textField.setColumns(10);
			
			JButton btnOk = new JButton("OK");
			btnOk.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String cphone = textField.getText();
					JPanel panelReservedTicket = new ReservedTicketPanel(db,cphone);
					panelRight.add(panelReservedTicket, "Reserved Ticket");
					CardLayout cardLayout = (CardLayout) panelRight.getLayout();
					cardLayout.show(panelRight, "Reserved Ticket");
					dispose();
				}
			});
			btnOk.setBounds(191, 82, 56, 19);
			contentPane.add(btnOk);
		}
	}
	
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
			titlePanel.setBounds(40, 36, 302, 32);
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
						e3.printStackTrace();
					}
				}
			});
						
			titlePanel.add(titleDropDown);
			
			JPanel datePanel = new JPanel();
			datePanel.setBounds(40, 78, 302, 32);
			selectionPanel.add(datePanel);
			datePanel.setLayout(new GridLayout(0, 2, 0, 0));
			
			JLabel lblSelectDate = new JLabel("Select date:");
			lblSelectDate.setHorizontalAlignment(SwingConstants.LEFT);
			lblSelectDate.setFont(new Font("Cordia New", Font.BOLD, 25));
			datePanel.add(lblSelectDate);
			
			
			datePanel.add(dateDropDown);
			
			JPanel timePanel = new JPanel();
			timePanel.setBounds(40, 120, 302, 32);
			selectionPanel.add(timePanel);
			timePanel.setLayout(new GridLayout(0, 2, 0, 0));
			
			JLabel lblSelectTime = new JLabel("Select time:");
			lblSelectTime.setHorizontalAlignment(SwingConstants.LEFT);
			lblSelectTime.setFont(new Font("Cordia New", Font.BOLD, 25));
			timePanel.add(lblSelectTime);
			
			
			timePanel.add(timeDropDown);
					
			JButton searchButton = new JButton("Search");
			searchButton.addMouseListener(new MouseAdapter() {
				private String s1;
				private String s2;
				private String s3;
				
				@Override
				public void mouseClicked(MouseEvent e) {
					s1 = titleDropDown.getSelectedItem().toString();
					s2 = dateDropDown.getSelectedItem().toString();
					s3 = timeDropDown.getSelectedItem().toString();
					JPanel ticketPurchasingPanel = null;
					try {
						ticketPurchasingPanel = new TicketPurchasingPanel(db, s1,  s2,  s3);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ticketPurchasingPanel.setVisible(true);
					
//					ticketPurchasingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
					panelRight.add(ticketPurchasingPanel, "Buy Ticket");
					CardLayout cardLayout = (CardLayout) panelRight.getLayout();
					cardLayout.show(panelRight, "Buy Ticket");
//					dispose();
				}
			});
			searchButton.setFont(new Font("Cordia New", Font.PLAIN, 18));
			searchButton.setBounds(353, 189, 87, 23);
			selectionPanel.add(searchButton);

		}
		
		public class TicketPurchasingPanel extends JPanel {
			private JTable tableAudi;
			private DBManager db;
			private String s1;
			private String s2;
			private String s3;
			
			public TicketPurchasingPanel(DBManager db, String s1, String s2, String s3) throws Exception {
				this.db = db;
				this.s1 = s1;
				this.s2 = s2;
				this.s3 = s3;
				
				setLayout(new MigLayout("", "[50px,grow]", "[10px][50px,grow]"));
				JPanel top = new JPanel();
				TitledBorder title;
				this.db = db;
				title = BorderFactory.createTitledBorder("Auditorium List");
				this.setBorder(title);
				
				this.add(top,"flowx,cell 0 0");
				top.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
								
				JLabel s_1 = new JLabel(s1);
				top.add(s_1);
				JLabel s_2 = new JLabel(s2);
				top.add(s_2);
				JLabel s_3 = new JLabel(s3);
				top.add(s_3);
						
				
				JScrollPane scrollPane = new JScrollPane();
				add(scrollPane, "cell 0 1,grow");
		
			
				tableAudi = new JTable();
				scrollPane.setViewportView(tableAudi);
				tableAudi.setRowSelectionAllowed(false);
//				tableAudi.setCellSelectionEnabled(true);
				
				
				try {
					List<Auditorium> audis = db.availableSeats(s1, s2, s3);
					AudiTableModel audiModel = new AudiTableModel(audis);
					tableAudi.setModel(audiModel);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				JPanel btm = new JPanel();			
				this.add(btm,"flowx,cell 1 2");
				btm.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
	
				
				JButton btnBuy = new JButton("Buy");
				btnBuy.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
//						try {
////							db.deleteEmployee(deleteTextField.getText());
//				            JOptionPane.showMessageDialog(top, "Successful Transaction");
//						} catch (SQLException e) {
//							// TODO Auto-generated catch block
//							JOptionPane.showMessageDialog(top, "Transaction Failure");
//						}
//						
//						List<Auditorium> auditoriums;
//						try {
//							auditoriums = db.availableSeats(s1, s2, s3);
//							AudiTableModel audiModel = new AudiTableModel(auditoriums);
//							tableAudi.setModel(audiModel);
//						} catch (SQLException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
					}
				});
				btm.add(btnBuy);
				
			}
		
		}
	
	}
	

}