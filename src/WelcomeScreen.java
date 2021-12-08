import dao.StudentDao;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen implements ActionListener {
    JFrame frame;
    JPanel panel;
    JLabel userNameLabel,passwordLabel;
    JTextField userNameField;
    JPasswordField passwordField;
    JButton signup,login;

    public WelcomeScreen(){
        frame= new JFrame("Student Management System");
        panel=new JPanel();
        panel.setLayout(null);
        panel.setBounds(60,60,380,500);
        panel.setBorder(BorderFactory.createTitledBorder("Login / Register"));

        //Username Label
        userNameLabel=new JLabel("User Name");
        userNameLabel.setBounds(80,80,120,30);
        panel.add(userNameLabel);

        //Username Field
        userNameField=new JTextField();
        userNameField.setBounds(180,80,120,30);
        panel.add(userNameField);

        //Password Label
        passwordLabel=new JLabel("Password");
        passwordLabel.setBounds(80,120,120,30);
        panel.add(passwordLabel);

        //PassWordField
        passwordField=new JPasswordField();
        passwordField.setBounds(180,120,120,30);
        panel.add(passwordField);

        //Buttons
        signup= new JButton("Signup");
        signup.setBounds(180,160,80,30);
        panel.add(signup);
        signup.addActionListener(this);
        signup.setActionCommand("signup");

        login=new JButton("Login");
        login.setBounds(280,160,80,30);
        panel.add(login);
        login.addActionListener(this);
        login.setActionCommand("login");

        //Frame Size
        frame.add(panel);
        frame.setSize(600,600);//sets size of the frame
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);//makes form visible
    }

    public static void main(String[] args) {
        new WelcomeScreen();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //REGISTER NEW USER
        if(e.getActionCommand().equals("signup")){
            this.frame.dispose();
            new RegisterUser();
        }
        //LOGIN BY VALIDATING USER
        else if(e.getActionCommand().equals("login")){
            String username=userNameField.getText().trim();
            String password=passwordField.getText().trim();
            if(username.isEmpty()||password.isEmpty()){
                JOptionPane.showMessageDialog(this.frame,"Fill the field","Empty Fields",JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                try{
                    User user=new User(username,password);
                    if(new StudentDao().checkValidUser(user)){
                        this.frame.dispose();
                        new HomePage();
                    }
                    else{
                        JOptionPane.showMessageDialog(this.frame,"Error","Invalid Credentials",JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
