package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dao.DBManager;
import net.miginfocom.swing.MigLayout;

import java.awt.CardLayout;

import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
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

}
