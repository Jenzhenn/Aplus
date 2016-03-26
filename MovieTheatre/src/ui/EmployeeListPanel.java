package ui;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import core.Employee;
import dao.DBManager;

import javax.swing.BorderFactory;
import javax.swing.DropMode;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmployeeListPanel extends JPanel {
	private JTextField deleteTextField;
	private JTable tableEmployee;
	private DBManager db;

	/**
	 * Create the panel.
	 */
	public EmployeeListPanel(DBManager db) {
		setLayout(new MigLayout("", "[50px,grow]", "[10px][50px,grow]"));
		JPanel top = new JPanel();
		TitledBorder title;
		this.db = db;
		title = BorderFactory.createTitledBorder("Employee List");
		this.setBorder(title);
		
		this.add(top,"flowx,cell 0 0");
		top.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblEID = new JLabel("EID: ");
		top.add(lblEID);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1,grow");
		
		tableEmployee = new JTable();
		scrollPane.setViewportView(tableEmployee);
		tableEmployee.setRowSelectionAllowed(false);
		tableEmployee.setCellSelectionEnabled(true);
		
		
		try {
			List<Employee> employees = db.getAllEmployee();
			EmployeeListModel eModel = new EmployeeListModel(employees);
			tableEmployee.setModel(eModel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		deleteTextField = new JTextField();
		top.add(deleteTextField);
		deleteTextField.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					db.deleteEmployee(deleteTextField.getText());
		            JOptionPane.showMessageDialog(top, "Successful Delete");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(top, "Invalid EID");
				}
				
				List<Employee> employees;
				try {
				    employees = db.getAllEmployee();
					EmployeeListModel eModel = new EmployeeListModel(employees);
					tableEmployee.setModel(eModel);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		top.add(btnDelete);
		
		
	}

}
