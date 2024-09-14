
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class AddNewPatient extends JFrame implements ActionListener{
    static Font pageFont = new Font("Tahoma", Font.PLAIN,16);
    static Color darkBlue = new Color(34,152,153);
    static Color white = new Color(245,245,245);
    static Color black = new Color(66,66,66);

    JTextField uidText, nameText, addressText, phnoText;
    JRadioButton maleButton, femaleButton, othersButton;
    JComboBox<String> bloodGroup;
    JButton add,cancel;



    public AddNewPatient() {


        JPanel framePanel = new JPanel();
        framePanel.setBounds(5, 5, 590, 550);
        framePanel.setLayout(null);
        framePanel.setBackground(darkBlue);
        add(framePanel);

        JLabel frameLabel = new JLabel("Add New Patient Details");
        frameLabel.setFont(pageFont);
        frameLabel.setForeground(white);
        frameLabel.setBounds(210,10,180,20);
        framePanel.add(frameLabel);

        //UID input
        JLabel uidLabel = new JLabel("UID");
        uidLabel.setFont(pageFont);
        uidLabel.setForeground(white);
        uidLabel.setBounds(10,60,100,20);
        framePanel.add(uidLabel);
        uidText = new JTextField();
        uidText.setBounds(150, 60, 200, 20);
        uidText.setFont(pageFont);
        framePanel.add(uidText);

        //Name input
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(pageFont);
        nameLabel.setForeground(white);
        nameLabel.setBounds(10,100,100,20);
        framePanel.add(nameLabel);
        nameText = new JTextField();
        nameText.setBounds(150, 100, 200, 20);
        nameText.setFont(pageFont);
        framePanel.add(nameText);

        //Address input
        JLabel addressLabel = new JLabel("Address");
        addressLabel.setFont(pageFont);
        addressLabel.setForeground(white);
        addressLabel.setBounds(10,140,100,20);
        framePanel.add(addressLabel);
        addressText = new JTextField();
        addressText.setBounds(150, 140, 200, 20);
        addressText.setFont(pageFont);
        framePanel.add(addressText);

        //Phone number input
        JLabel phnoLabel = new JLabel("Ph. No.");
        phnoLabel.setFont(pageFont);
        phnoLabel.setForeground(white);
        phnoLabel.setBounds(10,180,100,20);
        framePanel.add(phnoLabel);
        phnoText = new JTextField();
        phnoText.setBounds(150, 180, 200, 20);
        phnoText.setFont(pageFont);
        framePanel.add(phnoText);

        //Gender input
        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setFont(pageFont);
        genderLabel.setForeground(white);
        genderLabel.setBounds(10,220,100,20);
        framePanel.add(genderLabel);
        maleButton = new JRadioButton("Male");
        maleButton.setBounds(150, 220, 80, 20);
        maleButton.setBackground(darkBlue);
        maleButton.setForeground(white);
        femaleButton = new JRadioButton("Female");
        femaleButton.setBounds(230, 220, 80, 20);
        femaleButton.setBackground(darkBlue);
        femaleButton.setForeground(white);
        othersButton = new JRadioButton("Others");
        othersButton.setBounds(310, 220, 80, 20);
        othersButton.setBackground(darkBlue);
        othersButton.setForeground(white);
        ButtonGroup genders  = new ButtonGroup();
        genders.add(maleButton);genders.add(femaleButton);genders.add(othersButton);
        framePanel.add(maleButton);
        framePanel.add(femaleButton);
        framePanel.add(othersButton);

        //Blood group input
        JLabel bloodLabel = new JLabel("Blood Group");
        bloodLabel.setFont(pageFont);
        bloodLabel.setForeground(white);
        bloodLabel.setBounds(10,260,100,20);
        framePanel.add(bloodLabel);
        String[] bloodGroups = {"A+", "A-","B+", "B-", "AB+", "AB-", "O+", "O-"};
        bloodGroup = new JComboBox<>(bloodGroups);
        bloodGroup.setBounds(150, 260, 200, 20);
        bloodGroup.setFont(pageFont);
        framePanel.add(bloodGroup);

        //Add and Cancel Button
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
        try{
            if(e.getSource() == add){
                Patient p = new Patient();
                p.uid = Integer.parseInt(uidText.getText());
                p.name = "'"+nameText.getText()+"'";
                p.address = "'"+addressText.getText()+"'";
                p.phno = "'"+phnoText.getText()+"'";
                if(maleButton.isSelected()){
                    p.gender = "'Male'";
                }else if(femaleButton.isSelected()){
                    p.gender = "'Female'";
                }else if(othersButton.isSelected()){
                    p.gender = "'Others'";
                }else{
                    p.gender = null;
                }

                p.bloodgroup = "'"+(String)bloodGroup.getSelectedItem()+"'";
                try {
                    Conn c = new Conn();

                    String q = "insert into patient_info values("+p.uid+", "+p.name+", "+p.address+", "+p.phno+", "+p.gender+", "+p.bloodgroup+");";
                    int result = c.statement.executeUpdate(q);
                    if(result>0){
                        JOptionPane.showMessageDialog(null, "Successfully Added");
                        dispose();
                    }else{
                        JOptionPane.showMessageDialog(null, "Give all values");
                    }

                }catch (Exception E) {
                    JOptionPane.showMessageDialog(null, "Give all inputs");
                }
            }else{
                dispose();
            }
        }catch(Exception EXC){
            JOptionPane.showMessageDialog(null, "Give correct values");
        }
    }

}
