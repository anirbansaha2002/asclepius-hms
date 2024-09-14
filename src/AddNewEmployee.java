
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class AddNewEmployee extends JFrame implements ActionListener{

    static Font pageFont = new Font("Tahoma", Font.PLAIN,16);
    static Color darkBlue = new Color(34,152,153);
    static Color white = new Color(245,245,245);
    static Color black = new Color(66,66,66);

    final String[] departments = {
        "IT",
        "Nursing",
        "Pharmacy",
        "Security",
        "Maintainance",
        "Hospitality",
    };
    JTextField nameText,salaryText;
    JRadioButton maleButton, femaleButton, othersButton;
    JComboBox<String> department;

    LocalDateTime dateObj = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String date = dateObj.format(formatter);

    JButton add,cancel;



    public AddNewEmployee() {

        //JPanel for the frame
        JPanel framePanel = new JPanel();
        framePanel.setBounds(5, 5, 590, 550);
        framePanel.setLayout(null);
        framePanel.setBackground(darkBlue);
        add(framePanel);

        //Heading
        JLabel frameLabel = new JLabel("ADD NEW EMPLOYEE");
        frameLabel.setFont(pageFont);
        frameLabel.setForeground(white);
        frameLabel.setBounds(225, 0, 180, 30);
        framePanel.add(frameLabel);

        //Name Input
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(pageFont);
        nameLabel.setForeground(white);
        nameLabel.setBounds(10,60,100,20);
        framePanel.add(nameLabel);
        nameText = new JTextField();
        nameText.setBounds(150, 60, 200, 20);
        nameText.setFont(pageFont);
        framePanel.add(nameText);

        //Gender input
        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setFont(pageFont);
        genderLabel.setForeground(white);
        genderLabel.setBounds(10,100,100,20);
        framePanel.add(genderLabel);
        maleButton = new JRadioButton("Male");
        maleButton.setBounds(150, 100, 80, 20);
        maleButton.setBackground(darkBlue);
        maleButton.setForeground(white);
        femaleButton = new JRadioButton("Female");
        femaleButton.setBounds(230, 100, 80, 20);
        femaleButton.setBackground(darkBlue);
        femaleButton.setForeground(white);
        othersButton = new JRadioButton("Others");
        othersButton.setBounds(310, 100, 80, 20);
        othersButton.setBackground(darkBlue);
        othersButton.setForeground(white);
        ButtonGroup genders  = new ButtonGroup();
        genders.add(maleButton);genders.add(femaleButton);genders.add(othersButton);
        framePanel.add(maleButton);
        framePanel.add(femaleButton);
        framePanel.add(othersButton);

        //Date of Joining
        JLabel dateLabel = new JLabel("Date of joining");
        dateLabel.setFont(pageFont);
        dateLabel.setForeground(white);
        dateLabel.setBounds(10,140,150,20);
        framePanel.add(dateLabel);
        JLabel dateString = new JLabel(date);
        dateString.setFont(new Font("Tahoma", Font.PLAIN,14));
        dateString.setForeground(white);
        dateString.setBounds(150,140,200,20);
        framePanel.add(dateString);

        //Department choose
        JLabel deptLabel = new JLabel("Department");
        deptLabel.setFont(pageFont);
        deptLabel.setForeground(white);
        deptLabel.setBounds(10,185,100,20);
        framePanel.add(deptLabel);
        department = new JComboBox<>(departments);
        department.setBounds(150, 185, 200, 24);
        framePanel.add(department);

        //Salary
        JLabel salaryLabel = new JLabel("Salary");
        salaryLabel.setFont(pageFont);
        salaryLabel.setForeground(white);
        salaryLabel.setBounds(10,230,100,20);
        framePanel.add(salaryLabel);
        salaryText = new JTextField();
        salaryText.setBounds(150, 230, 200, 20);
        salaryText.setFont(pageFont);
        framePanel.add(salaryText);

        //Buttons
        add = new JButton("ADD");
        add.setBounds(30, 340, 150, 30);
        add.setBackground(black);
        add.setForeground(white);
        add.addActionListener(this);
        framePanel.add(add);

        cancel = new JButton("CANCEL");
        cancel.setBounds(200, 340, 150, 30);
        cancel.setBackground(black);
        cancel.setForeground(white);
        cancel.addActionListener(this);
        framePanel.add(cancel);
        



        setBounds(450, 240, 600, 560);
        setLayout(null);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getSource() == add){
                String name,gender,dept,joinDate;
                int salary;
                name = "'"+nameText.getText()+"'";
                if(maleButton.isSelected()){
                    gender = "'Male'";
                }else if(femaleButton.isSelected()){
                    gender = "'Female'";
                }else if (othersButton.isSelected()) {
                    gender = "'Others'";
                }else{
                    gender = null;
                }
                dept = "'"+(String)department.getSelectedItem()+"'";
                salary = Integer.parseInt(salaryText.getText());
                joinDate = "'"+date+"'";
                Employee emp = new Employee(name, gender, salary, dept, joinDate);

                String q = "insert into employee_info(name, gender, department, dateOfJoining, salary) values ("+emp.name+", "+emp.gender+", "+emp.department+", "+emp.joinDate+", "+emp.salary+");";
                Conn c = new Conn();
                int result = c.statement.executeUpdate(q);
                if(result>0){
                    JOptionPane.showMessageDialog(null, "Successfully Added");
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Give all values");
                }
            }else{
                dispose();
            }
        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, "Give correct values");
        }
    }
}
