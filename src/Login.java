import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;

public class Login extends JFrame implements ActionListener{

    static Font pageFont = new Font("Tahoma", Font.PLAIN,16);
    static Color darkBlue = new Color(34,152,153);
    static Color white = new Color(245,245,245);
    static Color black = new Color(66,66,66);

    JTextField nameField;
    JPasswordField passwordField;
    JButton loginButton, cancelButton;

    Login(){

        JLabel nameLabel = new JLabel("Username");
        nameLabel.setBounds(50, 80, 100, 30);
        nameLabel.setFont(pageFont);
        nameLabel.setForeground(white);
        add(nameLabel);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50, 130, 100, 30);
        passwordLabel.setFont(pageFont);
        passwordLabel.setForeground(white);
        add(passwordLabel);

        nameField = new JTextField();
        nameField.setBounds(180, 80, 170, 30);
        nameField.setFont(pageFont);
        add(nameField);

        passwordField = new JPasswordField();
        passwordField.setBounds(180, 130, 170, 30);
        passwordField.setFont(pageFont);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(60, 200, 100, 30);
        loginButton.setFont(pageFont);
        loginButton.setBackground(darkBlue);
        loginButton.setForeground(white);   
        loginButton.addActionListener(this);
        add(loginButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(180, 200, 100, 30);
        cancelButton.setFont(pageFont);
        cancelButton.setBackground(darkBlue);
        cancelButton.setForeground(white);
        cancelButton.addActionListener(this);
        add(cancelButton);

        ImageIcon hospitalIcon = new ImageIcon("./icons/login-logo.png");
        JLabel iconLabel = new JLabel(hospitalIcon);
        iconLabel.setBounds(500, 50, 200, 200);
        add(iconLabel);

        getContentPane().setBackground(black);;
        setTitle("Asclepius Hospitals");
        setSize(800, 350);
        setLayout(null);
        setLocation(400, 250);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){
            try {
                Conn c = new Conn();

                String username = nameField.getText();
                String pass = new String(passwordField.getPassword());

                String q = "select * from user_credentials where username = '"+username+"' AND pass = '"+pass+"'";
                ResultSet result = c.statement.executeQuery(q);

                if(result.next()){
                    new LandingPage();
                    setVisible(false);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Invalid Credentials");
                    nameField.setText("");
                    passwordField.setText("");
                }
                
            } catch (Exception E) {
                E.printStackTrace();
            }
        }
        else{
            System.exit(10);
        }
    }

    public static void main(String[] args){
        new Login();
    }


}