package HotelReservation;

import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class Transylvania extends JFrame{
	
	static JTextField guestName, roomType, inDate, outDate;
	static JTable table;
	static DefaultTableModel model;
	
	Transylvania() {
		
		String[] column = {" ", " ", " ", " "};
		model = new DefaultTableModel(column, 0);
		table = new JTable(model);
		table.setTableHeader(null);
		table.setBorder(null);
		table.setDefaultEditor(Object.class, null);
		table.setBackground(getContentPane().getBackground());

		JScrollPane scroller = new JScrollPane(table);
		add(scroller).setBounds(10,10,765,500);
		
		JLabel guestTitle = new JLabel("Guest Name");
		add(guestTitle).setBounds(10,520,100,20);
		
		guestName = new JTextField();
		add(guestName).setBounds(10,540,150,25);
		
		JLabel roomTitle = new JLabel("Room Type");
		add(roomTitle).setBounds(170,520,150,20);
		
		roomType = new JTextField();
		add(roomType).setBounds(170,540,150,25);
		
		JLabel inTitle = new JLabel("Check-In Date");
		add(inTitle).setBounds(330,520,100,20);
		
		inDate = new JTextField();
		add(inDate).setBounds(330,540,150,25);
		
		JLabel outTitle = new JLabel("Check-Out Date");
		add(outTitle).setBounds(490,520,100,20);
		
		outDate = new JTextField();
		add(outDate).setBounds(490,540,150,25);
		
		JButton Add = new JButton("Add");
		add(Add).setBounds(660,540,100,25);
		
		JButton Update = new JButton("Update");
		add(Update).setBounds(660,580,100,25);
		
		JButton Delete = new JButton("Delete");
		add(Delete).setBounds(660,620,100,25);
		
		JButton Exit = new JButton("Exit");
		add(Exit).setBounds(660,660,100,25);
		
		table.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e) {
	                int selectedRow = table.getSelectedRow();
	                if (selectedRow >= 0) {
	                    guestName.setText(model.getValueAt(selectedRow, 0).toString());
	                    roomType.setText(model.getValueAt(selectedRow, 1).toString());
	                    inDate.setText(model.getValueAt(selectedRow, 2).toString());
	                    outDate.setText(model.getValueAt(selectedRow, 3).toString());
	                }
	            }
	        });
		
		read();
		Add.addActionListener(e -> {
			try {
				FileWriter writer = new FileWriter("guestinfo.txt",true);
				String guest = guestName.getText();
				String room = roomType.getText();
				String in = inDate.getText();
				String out = outDate.getText();
				
				writer.write(guest + " - " + room + " - " + in + " - " + out + "\n");
				writer.close();
				
				read();
				
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

            ArrayList<String> list = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader("guestinfo.txt"))) {
                String line;
                int i = 0;

                while ((line = br.readLine()) != null) {
                    if (i == row) {
                        list.add(guestName.getText() + " - " + roomType.getText() + " - " + inDate.getText() + " - " + outDate.getText());
                    } else {
                        list.add(line);
                    }
                    i++;
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("guestinfo.txt"))) {
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

	            try (BufferedReader br = new BufferedReader(new FileReader("guestinfo.txt"))) {
	                String line;
	                int i = 0;

	                while ((line = br.readLine()) != null) {
	                    if (i != row) list.add(line);
	                    i++;
	                }
	            } catch (IOException ex) {
	                System.out.println(ex);
	            }

	            try (BufferedWriter bw = new BufferedWriter(new FileWriter("guestinfo.txt"))) {
	                for (String s : list) bw.write(s + "\n");
	            } catch (IOException ex) {
	                System.out.println(ex);
	            }

	            read();
	            clearFields();
	            JOptionPane.showMessageDialog(null, "Deleted Successfully!");
		});
		
		Exit.addActionListener(e5 -> {
			System.exit(0);
		});
		
		
		setTitle("Hotel Reservation System");
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800,800);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void read() {
		model.setRowCount(0);
		
		try (BufferedReader br = new BufferedReader(new FileReader("guestinfo.txt"))) {
			String line;
			while((line = br.readLine()) != null) {
				String row[] = line.split(" - ");
				if (row.length == 4)
				model.addRow(row);
			}
		} catch (IOException e2) {
			System.err.println("System Error: " + e2.getMessage());
		}
			
	}
	
	public static void clearFields() {
       guestName.setText("");
       roomType.setText("");
       inDate.setText("");
       outDate.setText("");
    }
	
	public static void main(String[] args) {
		new Transylvania();
	}

}
