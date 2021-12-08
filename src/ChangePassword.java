import database.DbConnection;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChangePassword implements ActionListener {
    Frame frame;
    JLabel userNameLabel,passwordLabel;
    JTextField userNameField,passwordField;
    JButton confrimButton, cancelButton;
    Connection connection;
    PreparedStatement pstmt;

    public ChangePassword(){

        frame=new JFrame("Change Password");

        userNameLabel=new JLabel("User Name");
        userNameLabel.setBounds(80,80,120,30);
        frame.add(userNameLabel);

        userNameField=new JTextField();
        userNameField.setBounds(220,80,120,30);
        frame.add(userNameField);

        passwordLabel=new JLabel("Type New Password");
        passwordLabel.setBounds(80,120,120,30);
        frame.add(passwordLabel);

        passwordField=new JPasswordField();
        passwordField.setBounds(220,120,120,30);
        frame.add(passwordField);

        confrimButton=new JButton("Confirm");
        confrimButton.setBounds(100,160,120,30);
        frame.add(confrimButton);
        confrimButton.addActionListener(this);
        confrimButton.setActionCommand("confirm");

        cancelButton=new JButton("Cancel");
        cancelButton.setBounds(240,160,120,30);
        frame.add(cancelButton);
        cancelButton.addActionListener(this);
        cancelButton.setActionCommand("cancel");

        frame.setSize(400,300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("confirm")){
            //change Password
            try {
                connection =new DbConnection().getDbConnection();
                pstmt = connection.prepareStatement("UPDATE USERS SET PASSWORD=? WHERE USERNAME=? ");
                pstmt.setString(1, passwordField.getText());
                pstmt.setString(2, userNameField.getText());
                int x=pstmt.executeUpdate();
                if(x==1) {
                    JOptionPane.showMessageDialog(this.frame,"Password Changed Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
                    this.frame.dispose();
                    new WelcomeScreen();

                }
                else {
                    JOptionPane.showMessageDialog(this.frame,"Password Canged Failed","Failed",JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        else  if(e.getActionCommand().equals("cancel")){
            int a=JOptionPane.showConfirmDialog(this.frame,"Do you want to cancel?");
            if(a==JOptionPane.YES_OPTION){
                this.frame.dispose();
                new HomePage();
            }
        }
    }
}
