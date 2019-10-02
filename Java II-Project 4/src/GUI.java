/*
 * File:    Student.java
 * Author:  Anthony Smith
 * Date:    15 December 2018
 * Purpose: creates GUI and database to store student information.
 * users can enter and edit information for a student. They can also look up students
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

//create JFrame class
public class GUI extends JFrame {
    private JLabel id;
    private JLabel nameLabel;
    private JLabel major;
    private JLabel chooseSelectionLabel;
    private JTextField idField;
    private JTextField nameField;
    private JTextField majorField;
    private String[] boxOptions = {"Insert", "Delete", "Find", "Update"};
    private JButton processRequest;
    //combobox to select action
    private JComboBox<String> chooseSection;
    //hashmap to store data
    private HashMap<String, Student> storeStudents;
    private JOptionPane window;

    //constructs the GUI and uses the listener
    public GUI() {
        //set layout and add all buttons and functionality
        super("Project 4");
        setLayout(new GridLayout(5, 2));
        storeStudents = new HashMap<>();
        window = new JOptionPane();
        id = new JLabel("Id: ");
        add(id);
        idField = new JTextField();
        add(idField);
        nameLabel = new JLabel("Name: ");
        add(nameLabel);
        nameField = new JTextField();
        add(nameField);
        major = new JLabel("Major: ");
        add(major);
        majorField = new JTextField();
        add(majorField);
        chooseSelectionLabel = new JLabel("Choose Selection");
        add(chooseSelectionLabel);
        chooseSection = new JComboBox<>(boxOptions);
        add(chooseSection);
        processRequest = new JButton("Process Request");
        add(processRequest);
        Handler handler = new Handler();
        processRequest.addActionListener(handler);
    }
    //main method to run the program
    public static void main(String args[]) {
        GUI atm = new GUI();
        atm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        atm.setSize(350, 250);
        atm.setVisible(true);
    }

    private class Handler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selection = chooseSection.getSelectedIndex();

            if (e.getSource() == processRequest) {
                String id = idField.getText();
                //tells the user to enter an id number if none is entered
                if (id.length() == 0) {
                    JOptionPane.showMessageDialog(window, "Enter Id number");
                    return;
                }
                //tells the user the student is already in the database and doesnt need to be added
                if (selection == 0) {
                    if (storeStudents.containsKey(id)) {
                        JOptionPane.showMessageDialog(window, "Student is already in the database");
                        return;
                    }
                    String name = nameField.getText();
                    String major = majorField.getText();
                    //if user doesnt enter the name or major user will be prompted to do so
                    if (name.length() == 0) {
                        JOptionPane.showMessageDialog(window, "Enter a name");
                        return;
                    }
                    if (major.length() == 0) {
                        JOptionPane.showMessageDialog(window, "Enter a major.");
                        return;
                    }
                    storeStudents.put(id, new Student(name, major));
                    JOptionPane.showMessageDialog(window, "Student information has been saved.");
                } else {
                    //notifies user if student is not found in database
                    if (!storeStudents.containsKey(id)) {
                        JOptionPane.showMessageDialog(window, "student ID not found");
                        return;
                    }
                    if (selection == 1) {
                        Student s = storeStudents.get(id);
                        storeStudents.remove(id);
                        JOptionPane.showMessageDialog(window, "student deleted");
                    //tells user the student is in the database and shows information
                    } else if (selection == 2) {
                        Student s = storeStudents.get(id);
                        JOptionPane.showMessageDialog(window, "student Found" + s.toString());
                    //shows dialog box and asks user to enter the grade and the credits
                    } else if (selection == 3) {
                        String[] choices = {"A", "B", "C", "D", "F"};
                        String grade = (String) JOptionPane.showInputDialog(null,
                                "Choose grade: ", "Choose grade",
                                JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
                        choices = new String[]{"3", "6"};
                        int credits = Integer.parseInt((String)
                                JOptionPane.showInputDialog(null, "Choose credits: ",
                                        "Choose credits", JOptionPane.QUESTION_MESSAGE, null,
                                        choices, choices[0]));
                        Student s = storeStudents.get(id);
                        s.courseCompleted(grade, credits);
                        JOptionPane.showMessageDialog(window, "Student information updated" + s.toString());
                    }
                }
            }
        }
    }
}
