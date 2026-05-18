package sadaya_568050;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;
import java.awt.*;

public class Employee  extends JFrame{
	
	static JTextField employeeID, fullName, birthDate, Age, Nationality, contactNumber, Email, Department, Job;
	static JComboBox<String> civilStatus;
	static ButtonGroup genderGroup;
	static String selectedGender;
	static JRadioButton Male,Female;
	static JTable table;
	static DefaultTableModel model;
	
	Employee() {
		
		JLabel Title = new JLabel("EMS Inc.");
		add(Title).setBounds(30,20,100,20);
		
		JLabel idTitle = new JLabel("Employee ID");
		add(idTitle).setBounds(30,50,100,20);
		
		employeeID = new JTextField();
		add(employeeID).setBounds(30,70,160,20);
		
		JLabel nameTitle = new JLabel("Full Name");
		
		add(nameTitle).setBounds(30,95,100,20);
	
		fullName = new JTextField();
		add(fullName).setBounds(30,115,160,20);
		
		JLabel dateTitle = new JLabel("Date of Birth");
		add(dateTitle).setBounds(30,140,100,20);
		
		birthDate = new JTextField();
		add(birthDate).setBounds(30,160,160,20);
		
		JLabel ageTitle = new JLabel("Age");
		add(ageTitle).setBounds(220,50,100,20);
		
		Age = new JTextField();
		add(Age).setBounds(220,70,160,20);
		
		JLabel civilTitle = new JLabel("Civil Status");
		add(civilTitle).setBounds(220,95,100,20);
		
		civilStatus = new JComboBox<>();
		add(civilStatus).setBounds(220,115,160,20);
		civilStatus.addItem("Single");
		civilStatus.addItem("Married");
		civilStatus.addItem("Separated");
		civilStatus.addItem("Divorced");
		civilStatus.addItem("Widowed");
		
		JLabel nationalityTitle = new JLabel("Nationality");
		add(nationalityTitle).setBounds(220,140,100,20);
		
		Nationality = new JTextField();
		add(Nationality).setBounds(220,160,160,20);
		
		JLabel genderTitle = new JLabel("Gender");
		add(genderTitle).setBounds(410,50,100,20);
		
		Male = new JRadioButton("Male");
		Male.setActionCommand("Male");
		add(Male).setBounds(410, 70, 55, 20);
		
		Female = new JRadioButton("Female");
		Female.setActionCommand("Female");
		add(Female).setBounds(465, 70, 80, 20);
		
		genderGroup = new ButtonGroup();
		genderGroup.add(Male);
		genderGroup.add(Female);
		
		JLabel numberTitle = new JLabel("Contact Number");
		add(numberTitle).setBounds(410,95,100,20);
		
		contactNumber = new JTextField();
		add(contactNumber).setBounds(410,115,160,20);
		
		JLabel emailTitle = new JLabel("Email");
		add(emailTitle).setBounds(410,140,100,20);
		
		Email = new JTextField();
		add(Email).setBounds(410,160,160,20);
		
		JLabel departmentTitle = new JLabel("Department");
		add(departmentTitle).setBounds(600,95,100,20);
		
		Department = new JTextField();
		add(Department).setBounds(600,115,160,20);
		
		JLabel jobTitle = new JLabel("Job Title / Position");
		add(jobTitle).setBounds(600,140,150,20);
		
		Job = new JTextField();
		add(Job).setBounds(600,160,160,20);
		
		String[] Columns = {"Employee ID", "Fullname", "Birth", "Age", "Civil Status", "Nationality", "Gender", "Contact", "Email", "Department", "Job Title"};
		model = new DefaultTableModel(Columns, 0);
		table = new JTable(model);
		table.setDefaultEditor(Object.class, null);
		table.getTableHeader().setForeground(Color.BLACK);
		table.getTableHeader().setBackground(Color.CYAN);
		JScrollPane scroller = new JScrollPane(table);
		add(scroller).setBounds(30,250,730,230);	
		
		JButton Add = new JButton("Add Employee");
		add(Add).setBounds(615,195,130,20);
		
		JButton Update = new JButton("Update");
		add(Update).setBounds(470,195,130,20);
		
		JButton Delete = new JButton("Delete");
		add(Delete).setBounds(325,195,130,20);
		
		
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = table.getSelectedRow();
		        employeeID.setText(model.getValueAt(row, 0).toString());
		        fullName.setText(model.getValueAt(row, 1).toString());
		        birthDate.setText(model.getValueAt(row, 2).toString());
		        Age.setText(model.getValueAt(row, 3).toString());
		        civilStatus.setSelectedItem(model.getValueAt(row, 4).toString());
		        Nationality.setText(model.getValueAt(row, 5).toString());
		        
		        String gender = model.getValueAt(row, 6).toString();
		        if (gender.equals("Male")) Male.setSelected(true);
		        else if (gender.equals("Female")) Female.setSelected(true);
		        
		        contactNumber.setText(model.getValueAt(row, 7).toString());
		        Email.setText(model.getValueAt(row, 8).toString());
		        Department.setText(model.getValueAt(row, 9).toString());
		        Job.setText(model.getValueAt(row, 10).toString());
		    }
		});
		
		read();
		Add.addActionListener(e -> {
			try {
				FileWriter writer = new FileWriter("employees.txt", true);
				String ID = employeeID.getText();
				String name = fullName.getText();
				String birth = birthDate.getText();
				String age = Age.getText();
				String civil = civilStatus.getSelectedItem().toString();
				String national = Nationality.getText();
				
				if (genderGroup.getSelection() != null) {
						selectedGender = genderGroup.getSelection().getActionCommand();
				}
				
				String number = contactNumber.getText();
				String email = Email.getText();
				String department = Department.getText();
				String JobTitle = Job.getText();
				
				writer.write(ID + " - " + name + " - " + birth + " - " + age + " - " + civil + " - " + national + " - " + selectedGender + " - "
						+ number + " - " + email + " - " + department + " - " + JobTitle + "\n");
				writer.close();
				
				read();
			
				JOptionPane.showMessageDialog(null, "Saved Sucessfully!");
				
				
			} catch (IOException e1) {
				System.err.println("System Error" + e1);
			}
			
			
		});
		
		Delete.addActionListener(e1 -> {
	            int row = table.getSelectedRow();
	            if (row == -1) {
	                JOptionPane.showMessageDialog(null, "Select a record first.");
	                return;
	            }

	            ArrayList<String> list = new ArrayList<>();

	            try (BufferedReader br = new BufferedReader(new FileReader("employees.txt"))) {
	                String line;
	                int i = 0;

	                while ((line = br.readLine()) != null) {
	                    if (i != row) list.add(line);
	                    i++;
	                }
	            } catch (IOException ex) {
	                System.out.println(ex);
	            }

	            try (BufferedWriter bw = new BufferedWriter(new FileWriter("employees.txt"))) {
	                for (String s : list) bw.write(s + "\n");
	            } catch (IOException ex) {
	                System.out.println(ex);
	            }

	            read();
	            clearFields();
	            JOptionPane.showMessageDialog(null, "Deleted Successfully!");
		});
		
		Update.addActionListener(e2 -> {
			int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Select a record first.");
                return;
            }

            ArrayList<String> list = new ArrayList<>();
            
            String gender = "";
            if (genderGroup.getSelection() != null) {
                gender = genderGroup.getSelection().getActionCommand();
            }
            
            try (BufferedReader br = new BufferedReader(new FileReader("employees.txt"))) {
                String line;
                int i = 0;

                while ((line = br.readLine()) != null) {
                    if (i == row) {
                        list.add(employeeID.getText() + " - " + 
                                fullName.getText() + " - " + 
                                birthDate.getText() + " - " + 
                                Age.getText() + " - " + 
                                civilStatus.getSelectedItem().toString() + " - " + 
                                Nationality.getText() + " - " + 
                                gender + " - " + 
                                contactNumber.getText() + " - " + 
                                Email.getText() + " - " + 
                                Department.getText() + " - " + 
                                Job.getText());
                    } else {
                        list.add(line);
                    }
                    i++;
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("employees.txt"))) {
                for (String s : list) bw.write(s + "\n");
            } catch (IOException ex) {
                System.out.println(ex);
            }

            read();
            clearFields();
            JOptionPane.showMessageDialog(null, "Updated Successfully!");
		});
		
		setTitle("Employee Management System");
		setLayout(null);
		setSize(800,550);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void read() {
		model.setRowCount(0);
		File file = new File("employees.txt");
		
		if (!file.exists()) {
	        return; 
	    }
		
		try (BufferedReader br = new BufferedReader(new FileReader("employees.txt"))) {
			String line;
			while((line = br.readLine()) != null) {
				String row[] = line.split(" - ");
				model.addRow(row);
			}
		} catch (IOException e2) {
			System.err.println("System Error: " + e2.getMessage());
		}
			
	}
	
	public static void clearFields() {
	       employeeID.setText("");
	       fullName.setText("");
	       birthDate.setText("");
	       Age.setText("");
	       Nationality.setText("");
	       contactNumber.setText("");
	       Email.setText("");
	       Department.setText("");
	       Job.setText("");
	       
	       if (civilStatus != null) {
	           civilStatus.setSelectedIndex(0);
	       }
	       
	       if (genderGroup != null) {
	           genderGroup.clearSelection();
	       }
	       
	    }
	
	public static void main(String[] args) {
		new Employee();
		
	}

}
