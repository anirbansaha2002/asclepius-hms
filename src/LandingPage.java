
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LandingPage extends JFrame{
    //Constants
    static Font pageFont = new Font("Tahoma", Font.PLAIN,16);
    static Color darkBlue = new Color(34,152,153);
    static Color white = new Color(245,245,245);
    static Color black = new Color(66,66,66);

    public LandingPage() {
        
        //Upper panel
        JPanel topPanel = new JPanel();
        topPanel.setBackground(black);
        topPanel.setVisible(true);
        topPanel.setBounds(5, 5,1528, 180);
        topPanel.setLayout(null);
        add(topPanel);

        //Bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(black);
        bottomPanel.setVisible(true);
        bottomPanel.setBounds(5, 190,1528, 600);
        bottomPanel.setLayout(null);
        add(bottomPanel);

        
        /*Top row buttons*/
        //Add new patient button
        JButton addPatientButton = new JButton("Add New Patient");
        addPatientButton.setBounds(30, 40, 200, 30);
        addPatientButton.setBackground(darkBlue);
        addPatientButton.setForeground(white);
        addPatientButton.setFont(pageFont);
        addPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new AddNewPatient();
            } 
        });
        topPanel.add(addPatientButton);

        //Make appointment button
        JButton makeappointmentButton = new JButton("Make Appointment");
        makeappointmentButton.setBounds(260, 40, 200, 30);
        makeappointmentButton.setBackground(darkBlue);
        makeappointmentButton.setForeground(white);
        makeappointmentButton.setFont(pageFont);
        makeappointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Appointments app = new Appointments();
                if(app.getPatientID()){
                    app.addNewAppointment();
                }else{
                    JOptionPane.showMessageDialog(null, "No patient found");
                }
            }
        });
        topPanel.add(makeappointmentButton);

        //Add New Employee Button
        JButton addEmployeeButton = new JButton("Add New Employee");
        addEmployeeButton.setBounds(490, 40, 200, 30);
        addEmployeeButton.setBackground(darkBlue);
        addEmployeeButton.setForeground(white);
        addEmployeeButton.setFont(pageFont);
        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new AddNewEmployee();
            }
        });
        topPanel.add(addEmployeeButton);
        

        //Add in-patient Button
        JButton viewDoctorsButton = new JButton("View Doctors");
        viewDoctorsButton.setBounds(720, 40, 200, 30);
        viewDoctorsButton.setBackground(darkBlue);
        viewDoctorsButton.setForeground(white);
        viewDoctorsButton.setFont(pageFont);
        viewDoctorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new ViewDoctors();
            }
        });
        topPanel.add(viewDoctorsButton);

        /*Bottom row buttons*/
        //View patients button
        JButton viewPatientsButton = new JButton("View Patients");
        viewPatientsButton.setBounds(30, 110, 200, 30);
        viewPatientsButton.setBackground(darkBlue);
        viewPatientsButton.setForeground(white);
        viewPatientsButton.setFont(pageFont);
        viewPatientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new ViewPatients();
            }
        });
        topPanel.add(viewPatientsButton);

        //View Appointments button
        JButton viewAppointmentsButton = new JButton("View Appointments");
        viewAppointmentsButton.setBounds(260, 110, 200, 30);
        viewAppointmentsButton.setBackground(darkBlue);
        viewAppointmentsButton.setForeground(white);
        viewAppointmentsButton.setFont(pageFont);
        viewAppointmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new ViewAppointments();
            }
        });
        topPanel.add(viewAppointmentsButton);

        //View Employees Button
        JButton viewEmployeesButton = new JButton("View Employees");
        viewEmployeesButton.setBounds(490, 110, 200, 30);
        viewEmployeesButton.setBackground(darkBlue);
        viewEmployeesButton.setForeground(white);
        viewEmployeesButton.setFont(pageFont);
        viewEmployeesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new ViewEmployees();
            } 
        });
        topPanel.add(viewEmployeesButton);

        //Logout Button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(720, 110, 200, 30);
        logoutButton.setBackground(darkBlue);
        logoutButton.setForeground(white);
        logoutButton.setFont(pageFont);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                new Login();
            } 
        });
        topPanel.add(logoutButton);

        //Image of top panel
        ImageIcon hospitalIcon = new ImageIcon("./icons/landing-logo.png");
        JLabel iconLabel = new JLabel(hospitalIcon);
        iconLabel.setBounds(1120, 20, 300, 140);
        topPanel.add(iconLabel);


        
        setTitle("Asclepius Hospitals - Management System");
        setLayout(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
}
