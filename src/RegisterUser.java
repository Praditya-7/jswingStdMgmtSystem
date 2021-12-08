import dao.StudentDao;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterUser implements ActionListener {
    JFrame frame;
    JLabel fullnamelabel,emailLabel,usernameLabel,passwordLabel;
    JTextField fullNameField,emailField,usernameField;
    JPasswordField passwordField;
    JButton register;

    public RegisterUser(){
        frame =new JFrame("Register Here!!");

        fullnamelabel =new JLabel("Full Name");
        fullnamelabel.setBounds(80,80,120,30);
        frame.add(fullnamelabel);

        fullNameField=new JTextField();
        fullNameField.setBounds(180,80,120,30);
        frame.add(fullNameField);

        usernameLabel=new JLabel("User Name");
        usernameLabel.setBounds(80,120,120,30);
        frame.add(usernameLabel);

        usernameField=new JTextField();
        usernameField.setBounds(180,120,120,30);
        frame.add(usernameField);

        emailLabel=new JLabel("Email");
        emailLabel.setBounds(80,160,120,30);
        frame.add(emailLabel);

        emailField=new JTextField();
        emailField.setBounds(180,160,120,30);
        frame.add(emailField);

        passwordLabel=new JLabel("Password");
        passwordLabel.setBounds(80,200,120,30);
        frame.add(passwordLabel);

        passwordField=new JPasswordField();
        passwordField.setBounds(180,200,120,30);
        frame.add(passwordField);

        register =new JButton("Register");
        register.setBounds(80,240,120,30);
        frame.add(register);
        register.addActionListener(this);

        frame.setSize(400,600);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String fullname=fullNameField.getText().trim();
        String email=emailField.getText().trim();
        String username=usernameField.getText().trim();
        String password=passwordField.getText().trim();
        if(username.isEmpty()||email.isEmpty()||username.isEmpty()||password.isEmpty()){
            JOptionPane.showMessageDialog(this.frame,"Field Empty","Incomplete Field",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            try{
                User user =new User(fullname,email,username,password);
                if(new StudentDao().signupUser(user)){
                    JOptionPane.showMessageDialog(this.frame, "User Registered Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
                    //to empty field after registrarion
                    fullNameField.setText("");
                    emailField.setText("");
                    usernameField.setText("");
                    passwordField.setText("");
                    this.frame.dispose();
                    new WelcomeScreen();
                }
                else{
                    JOptionPane.showMessageDialog(this.frame,"Error","Failed",JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
