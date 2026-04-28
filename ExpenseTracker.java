package sadaya_568050;

import javax.swing.*;
import javax.swing.table.DefaultTableModel; // Added for Table model
import java.io.*;
import java.awt.Color;

public class ExpenseTracker extends JFrame{
	
	static JTextField receiptNumber, nameStore, costTotal, Taxes, amountFinal;
	JTable table;
	DefaultTableModel model;
	
	ExpenseTracker() {
		
		JLabel Title = new JLabel("EXPENSE TRACKER");
		add(Title).setBounds(175,30,150,10);
		
		JLabel receiptNo = new JLabel("Receipt Number:");
		add(receiptNo).setBounds(30,70,175,25);
		
		receiptNumber = new JTextField();
		add(receiptNumber).setBounds(150,70,200,20);
		
		JLabel storeName = new JLabel("Store Name:");
		add(storeName).setBounds(30,100,175,25);
		
		nameStore = new JTextField();
		add(nameStore).setBounds(150,100,200,20);
		
		JLabel TotalCost = new JLabel("Total Cost:");
		add(TotalCost).setBounds(30,130,175,25);
		
		costTotal = new JTextField();
		add(costTotal).setBounds(150,130,200,20);
		
		JLabel Tax = new JLabel("Tax(12%):");
		add(Tax).setBounds(30,175,175,25);
		
		Taxes = new JTextField();
		add(Taxes).setBounds(150,175,200,20);
		Taxes.setEditable(false);
		
		JLabel finalAmount = new JLabel("Total Cost:");
		add(finalAmount).setBounds(30,200,175,25);
		
		amountFinal = new JTextField();
		add(amountFinal).setBounds(150,200,200,20);
		amountFinal.setEditable(false);
		
		String[] columns = {"Receipt #", "Store", "Cost", "Tax", "Final"};
		model = new DefaultTableModel(columns, 0);
		table = new JTable(model);
		table.getTableHeader().setForeground(Color.BLACK);
		table.getTableHeader().setBackground(Color.CYAN);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane).setBounds(380, 70, 380, 200);

		JButton Record = new JButton("Record");
		add(Record).setBounds(70,240,105,30);
		
		Record.addActionListener(e -> {
			try {
				FileWriter writer = new FileWriter("data.txt", true);
				int number = Integer.parseInt(receiptNumber.getText());
				String name = nameStore.getText();
				int Total = Integer.parseInt(costTotal.getText());
		
				
				double Taxes1 = Total*0.12;
				double Final = Total + Taxes1;
				
				Taxes.setText(String.valueOf(Taxes1));
				amountFinal.setText(String.valueOf(Final));
				
				writer.write(number + ", " + name + ", " + Total + ", " + Taxes1 + ", " + Final + "\n");
				writer.close();
				
				model.addRow(new Object[]{number, name, Total, Taxes1, Final});
				
				JOptionPane.showMessageDialog(null, "Saved Successfully!");
				
			} catch (IOException e1) {
				System.err.println("System Error" + e1);
			}
			
			
		});
		
		JButton Clear = new JButton("Clear");
		add(Clear).setBounds(220,240,105,30);
		
		Clear.addActionListener(e2 -> {
			receiptNumber.setText("");
			nameStore.setText("");
			costTotal.setText("");
			Taxes.setText("");
			amountFinal.setText("");
		});
		
		
		
		setTitle("Expense Tracker");
		setLayout(null);
		setSize(800,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ExpenseTracker();

	}

}