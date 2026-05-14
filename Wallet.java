package sadaya568050;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.awt.Color;

public class Wallet extends JFrame {
	
	static JTextField name, course, year, wallet, status;
	static JTable table;
	static DefaultTableModel model;
	
	Wallet() {
		
		JLabel nameTitle = new JLabel("Full Name");
		add(nameTitle).setBounds(20, 10, 100, 20);
		
		name = new JTextField();
		add(name).setBounds(20, 30, 200, 20);
		
		JLabel courseTitle = new JLabel("Course/Dept.");
		add(courseTitle).setBounds(20, 60, 100, 20);
		
		course = new JTextField();
		add(course).setBounds(20, 80, 200, 20);
		
		JLabel yearTitle = new JLabel("Year Level");
		add(yearTitle).setBounds(230, 10, 100, 20);
		
		year = new JTextField();
		add(year).setBounds(230, 30, 200, 20);
		
		JLabel walletTitle = new JLabel("Wallet Balance");
		add(walletTitle).setBounds(230, 60, 100, 20);
		
		wallet = new JTextField();
		add(wallet).setBounds(230, 80, 200, 20);
		
		JLabel statusTitle = new JLabel("Status (Active or Suspended)");
		add(statusTitle).setBounds(440, 10, 200, 20);
		
		status = new JTextField();
		add(status).setBounds(440, 30, 200, 20);
		
		JButton Add = new JButton("Add");
		add(Add).setBounds(440, 52, 95, 23);
		
		JButton Delete = new JButton("Delete");
		add(Delete).setBounds(440, 76, 95, 23);
		
		JButton Update = new JButton("Update");
		add(Update).setBounds(545, 52, 95, 23);
		
		JButton Clear = new JButton("Clear");
		add(Clear).setBounds(545, 76, 95, 23);
		
		String[] column = {"Full Name", "Course", "Year", "Wallet Balance", "Status"};
		model = new DefaultTableModel(column, 0);
		table = new JTable(model);
		table.getTableHeader().setForeground(Color.BLACK);
		table.getTableHeader().setBackground(Color.CYAN);
		table.setBorder(null);
		table.setDefaultEditor(Object.class, null);
		table.setBackground(getContentPane().getBackground());
		
		JScrollPane scroller = new JScrollPane(table);
		add(scroller).setBounds(20, 110, 620, 330);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					name.setText(model.getValueAt(selectedRow, 0).toString());
					course.setText(model.getValueAt(selectedRow, 1).toString());
					year.setText(model.getValueAt(selectedRow, 2).toString());
					wallet.setText(model.getValueAt(selectedRow, 3).toString());
					status.setText(model.getValueAt(selectedRow, 4).toString());
				}
			}
		});
		
		try {
			File file = new File("Wallet.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		
		read();
		
		Add.addActionListener(e -> {
			String names = name.getText().trim();
			String courses = course.getText().trim();
			String years = year.getText().trim();
			String wallets = wallet.getText().trim();
			String statuses = status.getText().trim();
			
			if (names.isEmpty() || courses.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Full Name & Courses cannot be empty!");
				return;
			}
			if (!years.matches("^\\d+")) {
				JOptionPane.showMessageDialog(null, "Year Level must be numerical only!");
				return;
			}
			try {
				Double.parseDouble(wallets);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Wallet Balance must be numerical or decimal");
				return;
			}
			if (!statuses.equalsIgnoreCase("Active") && !statuses.equalsIgnoreCase("Suspended")) {
				JOptionPane.showMessageDialog(null, "Status must only be Active or Suspended");
				return;
			}
			
			try {
				FileWriter writer = new FileWriter("Wallet.txt", true);
				writer.write(names + " - " + courses + " - " + years + " - " + wallets + " - " + statuses + "\n");
				writer.close();
				
				read();
				clearFields();
				JOptionPane.showMessageDialog(null, "Saved Successfully!");
			} catch (IOException e1) {
				System.err.println("System Error" + e1);
			}
		});
		
		Update.addActionListener(e3 -> {
			int row = table.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Select a record first.");
				return;
			}
			
			String names = name.getText().trim();
			String courses = course.getText().trim();
			String years = year.getText().trim();
			String wallets = wallet.getText().trim();
			String statuses = status.getText().trim();
			
			if (names.isEmpty() || courses.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Full Name & Courses cannot be empty!");
				return;
			}
			if (!years.matches("^\\d+")) {
				JOptionPane.showMessageDialog(null, "Year Level must be numerical only!");
				return;
			}
			try {
				Double.parseDouble(wallets);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Wallet Balance must be numerical or decimal");
				return;
			}
			if (!statuses.equalsIgnoreCase("Active") && !statuses.equalsIgnoreCase("Suspended")) {
				JOptionPane.showMessageDialog(null, "Status must only be Active or Suspended");
				return;
			}
			
			ArrayList<String> list = new ArrayList<>();
			try (BufferedReader br = new BufferedReader(new FileReader("Wallet.txt"))) {
				String line;
				int i = 0;
				while ((line = br.readLine()) != null) {
					if (i == row) {
						list.add(names + " - " + courses + " - " + years + " - " + wallets + " - " + statuses);
					} else {
						list.add(line);
					}
					i++;
				}
			} catch (IOException ex) {
				System.out.println(ex);
			}
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter("Wallet.txt"))) {
				for (String s : list) bw.write(s + "\n");
			} catch (IOException ex) {
				System.out.println(ex);
			}
			
			read();
			clearFields();
			JOptionPane.showMessageDialog(null, "Updated Successfully!");
		});
		
		Delete.addActionListener(e4 -> {
			int row = table.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Select a record first.");
				return;
			}
			
			ArrayList<String> list = new ArrayList<>();
			try (BufferedReader br = new BufferedReader(new FileReader("Wallet.txt"))) {
				String line;
				int i = 0;
				while ((line = br.readLine()) != null) {
					if (i != row) list.add(line);
					i++;
				}
			} catch (IOException ex) {
				System.out.println(ex);
			}
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter("Wallet.txt"))) {
				for (String s : list) bw.write(s + "\n");
			} catch (IOException ex) {
				System.out.println(ex);
			}
			
			read();
			clearFields();
		});
		
		Clear.addActionListener(e5 -> {
			clearFields();
		});
		
		setTitle("School Canteen Wallet");
		setLayout(null);
		setSize(670, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void read() {
		model.setRowCount(0);
		try (BufferedReader br = new BufferedReader(new FileReader("Wallet.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String row[] = line.split(" - ");
				if (row.length == 5)
					model.addRow(row);
			}
		} catch (IOException e2) {
			System.err.println("System Error: " + e2.getMessage());
		}
	}
	
	public static void clearFields() {
		name.setText("");
		course.setText("");
		year.setText("");
		wallet.setText("");
		status.setText("");
		table.clearSelection();
	}
	
	public static void main(String[] args) {
		new Wallet();
	}
}
