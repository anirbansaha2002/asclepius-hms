
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Appointments implements ActionListener {

    static Font pageFont = new Font("Tahoma", Font.PLAIN,16);
    static Color darkBlue = new Color(34,152,153);
    static Color white = new Color(245,245,245);
    static Color black = new Color(66,66,66);
    static JFrame inputFrame;
    static JPanel framePanel;
    
    int appointmentID;
    int patientID;
    JComboBox<String> doctor;
    JTextField appointmentDateText, amountText;
    JButton confirm,cancel;

    LocalDateTime dateObj = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("Hms");
    String today = dateObj.format(formatter);

    Appointments(){}

    public boolean getPatientID(){
        patientID =  Integer.parseInt(JOptionPane.showInputDialog("Enter the Patient UID"));
        try {
            Conn c = new Conn();
            ResultSet ids = c.statement.executeQuery("select uid from patient_info");
            int flag=0;
            while (ids.next()) {
                int id = ids.getInt("uid");
                if(id==patientID){
                    flag++;
                    break;
                }
            }
            return flag==1;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter correct id");
            e.printStackTrace();
        }
        return false;
        
    }

    private static void createInputFrame(){
        inputFrame = new JFrame();
        inputFrame.setBounds(450, 240, 600, 560);
        inputFrame.setLayout(null);
        inputFrame.setUndecorated(true);
        inputFrame.setVisible(true);
        //JPanel for the frame
        framePanel = new JPanel();
        framePanel.setBounds(5, 5, 590, 550);
        framePanel.setLayout(null);
        framePanel.setBackground(darkBlue);
        inputFrame.add(framePanel);
        //Heading
        JLabel frameLabel = new JLabel("MAKE APPOINTMENT");
        frameLabel.setFont(pageFont);
        frameLabel.setForeground(white);
        frameLabel.setBounds(225, 0, 200, 30);
        framePanel.add(frameLabel);

        //Appointment ID
        JLabel appointmentIdLabel = new JLabel("Appointment ID");
        appointmentIdLabel.setFont(pageFont);
        appointmentIdLabel.setForeground(white);
        appointmentIdLabel.setBounds(10,60,150,20);
        framePanel.add(appointmentIdLabel);


        //Patient ID
        JLabel patientIdLabel = new JLabel("Patient ID");
        patientIdLabel.setFont(pageFont);
        patientIdLabel.setForeground(white);
        patientIdLabel.setBounds(10,100,150,20);
        framePanel.add(patientIdLabel);


        //Doctor
        JLabel doctorLabel = new JLabel("Doctor");
        doctorLabel.setFont(pageFont);
        doctorLabel.setForeground(white);
        doctorLabel.setBounds(10,140,150,20);
        framePanel.add(doctorLabel);


        //Appointment Date
        JLabel dateLabel = new JLabel("Appointment Date");
        dateLabel.setFont(pageFont);
        dateLabel.setForeground(white);
        dateLabel.setBounds(10,180,150,20);
        framePanel.add(dateLabel);

        //Amount
        JLabel amountLabel = new JLabel("Deposit Amount");
        amountLabel.setFont(pageFont);
        amountLabel.setForeground(white);
        amountLabel.setBounds(10,220,150,20);
        framePanel.add(amountLabel);

    }

    public void addNewAppointment(){
        createInputFrame();

        //Appointment ID
        appointmentID = Integer.parseInt(today+patientID);
        JLabel appointmentIdLabel = new JLabel(Integer.toString(appointmentID));
        appointmentIdLabel.setFont(pageFont);
        appointmentIdLabel.setForeground(white);
        appointmentIdLabel.setBounds(170,60,200,20);
        framePanel.add(appointmentIdLabel);

        //PatientID
        JLabel patientLabel = new JLabel(Integer.toString(patientID));
        patientLabel.setFont(pageFont);
        patientLabel.setForeground(white);
        patientLabel.setBounds(170,100,200,20);
        framePanel.add(patientLabel);

        //Doctor
        String[] doctorsList={};
        try {
            Conn c = new Conn();
            ResultSet result = c.statement.executeQuery("select name from doctor_info order by name");
            ArrayList<String> list = new ArrayList<>();
            while(result.next()){
                list.add(result.getNString("name"));
            }
            doctorsList = list.toArray(String[]::new);
        } catch (Exception e) {
            e.printStackTrace();
        }
        doctor = new JComboBox<>(doctorsList);
        doctor.setBounds(170,140,200,24);
        framePanel.add(doctor);
        

        //Appointment Date
        appointmentDateText = new JTextField();
        appointmentDateText.setBounds(170, 180, 200,20);
        appointmentDateText.setFont(pageFont);
        framePanel.add(appointmentDateText);

        //Appointment Date
        amountText = new JTextField();
        amountText.setBounds(170, 220, 200,20);
        amountText.setFont(pageFont);
        framePanel.add(amountText);

        //Confirm and Cancel Button
        //Buttons
        confirm = new JButton("ADD");
        confirm.setBounds(30, 340, 150, 30);
        confirm.setBackground(black);
        confirm.setForeground(white);
        confirm.addActionListener(this);
        framePanel.add(confirm);

        cancel = new JButton("CANCEL");
        cancel.setBounds(200, 340, 150, 30);
        cancel.setBackground(black);
        cancel.setForeground(white);
        cancel.addActionListener(this);
        framePanel.add(cancel);

        framePanel.revalidate();
        framePanel.repaint();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getSource() == confirm){
                String doctorName ="'"+(String)doctor.getSelectedItem()+"'";
                String appointmentdate = "'"+appointmentDateText.getText()+"'";
                int amount = Integer.parseInt(amountText.getText());

                String q = "insert into appointments values("+appointmentID+", "+patientID+", "+doctorName+", "+appointmentdate+", "+amount+");";
                try {
                    Conn c = new Conn();
                    int result = c.statement.executeUpdate(q);
                    if(result>0){
                        JOptionPane.showMessageDialog(null, "Successfully Added");
                        inputFrame.dispose();
                    }else{
                        JOptionPane.showMessageDialog(null, "Give all values");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Give all values");
                }
            }else{
                inputFrame.dispose();
            }
            
        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, "Give correct values");
        }
    }
}
