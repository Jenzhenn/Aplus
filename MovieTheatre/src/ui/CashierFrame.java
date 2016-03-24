package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;

public class CashierFrame extends JFrame {

	/**
	 * Create the frame.
	 */
	public CashierFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Cashier View");
		getContentPane().setLayout(new MigLayout("", "[][][][][][][][][][][][grow]", "[][][][][][][][][grow]"));
		
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, "cell 0 0 12 9,grow");
		mainPanel.setLayout(new CardLayout(0, 0));
		
		JPanel panelLogin = new JPanel();
		mainPanel.add(panelLogin, "name_171831861873876");
		
		JPanel panelCashier = new JPanel();
		mainPanel.add(panelCashier, "name_171853031906081");
	}

}
