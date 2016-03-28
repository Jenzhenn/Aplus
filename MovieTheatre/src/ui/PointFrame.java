package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DBManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class PointFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private DBManager db;

	/**
	 * Create the frame.
	 * 
	 */
	public PointFrame(DBManager db) {
		this.db = db;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 292, 147);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel labelPanel = new JPanel();
		labelPanel.setBounds(0, 0, 272, 37);
		contentPane.add(labelPanel);
		labelPanel.setLayout(null);
		
		JLabel lblMemberVerification = new JLabel("Member Verification:");
		lblMemberVerification.setVerticalAlignment(SwingConstants.BOTTOM);
		lblMemberVerification.setHorizontalAlignment(SwingConstants.CENTER);
		lblMemberVerification.setBounds(-18, 0, 172, 32);
		labelPanel.add(lblMemberVerification);
		lblMemberVerification.setFont(new Font("Cordia New", Font.PLAIN, 20));
		
		JPanel phonePanel = new JPanel();
		phonePanel.setBounds(0, 37, 272, 72);
		contentPane.add(phonePanel);
		phonePanel.setLayout(null);
		
		JLabel lblEnterPhone = new JLabel("Enter phone:");
		lblEnterPhone.setBounds(10, 14, 73, 15);
		phonePanel.add(lblEnterPhone);
		lblEnterPhone.setFont(new Font("Cordia New", Font.PLAIN, 15));
		
		textField = new JTextField();
		textField.setBounds(81, 12, 96, 21);
		phonePanel.add(textField);
		textField.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Integer point = db.getPoint(textField.getText());
		            JOptionPane.showMessageDialog(contentPane, "Your total reward points: "+ point.toString());
		            dispose();
				}catch (SQLException error) {
					JOptionPane.showMessageDialog(contentPane, "You are not a member.");
				}	
			}
		});
		btnOk.setBounds(187, 11, 63, 22);
		phonePanel.add(btnOk);
	}

}
