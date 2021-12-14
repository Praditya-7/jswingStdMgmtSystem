import dao.StudentDao;
import database.DbConnection;
import model.Student;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeMap;

public class HomePage implements ActionListener {
JFrame frame;
JLabel title,title2,firstNameLabel,lastNameLabel,addressLabel,genderLabel,emailLabel,courseLabel,phoneLabel,remarksLabel,searchLabel;
JTextField firstNameField, lastNameField,addressField,emailField,phoneField,studentIDField,searchField;
JTextArea remarksField;
JButton saveButton,editButton,deleteButton,logoutButton,changePasswordButton ,searchButton,exportButton;
JRadioButton maleGenderButton, femaleGenderButton;
ButtonGroup genderButton;
JComboBox<String> courseBox;
String[] courseList ={"BBM","BIM","BCA","BBS","BSc.CSIT"};
JTable studentTable,studentTable2;
DefaultTableModel tmodel,searchmodel;
String[] columns={"STUDENTID","FIRSTNAME","LASTNAME","ADDRESS","GENDER","EMAIL","COURSES","PHONE","REMARKS"};
JScrollPane scrollPane,scrollPane2;
JFileChooser fileChooser;
String path;
public HomePage(){
    frame=new JFrame("Home Page");

    title =new JLabel("Student Detail Form");
    title.setFont(new Font("Comfortaa", Font.BOLD, 18));
    title.setBounds(80,20,200,30);
    frame.add(title);

    exportButton=new JButton("Export to excel");
    exportButton.setBounds(1150,40,150,30);
    frame.add(exportButton);
    exportButton.addActionListener(this);
    exportButton.setActionCommand("export");

    title2=new JLabel("Student Details");
    title2.setFont(new Font("Comfortaa", Font.BOLD, 18));
    title2.setBounds(500,110,150,30);
    frame.add(title2);

    //Table Definition
    tmodel = new DefaultTableModel();
    tmodel.setColumnIdentifiers(columns);
    studentTable=new JTable();
    studentTable.setAutoCreateRowSorter(true);//for Sorting the table
    studentTable.setModel(tmodel);
    scrollPane=new JScrollPane(studentTable);
    scrollPane.setBounds(500,140,800,150);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    frame.add(scrollPane);

    //Form
    studentIDField=new JTextField();

    firstNameLabel=new JLabel("First Name");
    firstNameLabel.setBounds(80,60,120,30);
    frame.add(firstNameLabel);

    firstNameField = new JTextField();
    firstNameField.setBounds(180,60,150,30);
    frame.add(firstNameField);

    lastNameLabel=new JLabel("Last name");
    lastNameLabel.setBounds(80,100,120,30);
    frame.add(lastNameLabel);

    lastNameField=new JTextField();
    lastNameField.setBounds(180,100,150,30);
    frame.add(lastNameField);

    addressLabel=new JLabel("Address");
    addressLabel.setBounds(80,140,120,30);
    frame.add(addressLabel);

    addressField= new JTextField();
    addressField.setBounds(180,140,150,30);
    frame.add(addressField);

    genderLabel=new JLabel("Gender");
    genderLabel.setBounds(80,180,120,30);
    frame.add(genderLabel);

    genderButton= new ButtonGroup();

    maleGenderButton=new JRadioButton("Male");
    maleGenderButton.setBounds(180,180,100,30);
    genderButton.add(maleGenderButton);
    frame.add(maleGenderButton);

    femaleGenderButton=new JRadioButton("Female");
    femaleGenderButton.setBounds(280,180,100,30);
    genderButton.add(femaleGenderButton);
    frame.add(femaleGenderButton);

    emailLabel=new JLabel("Email");
    emailLabel.setBounds(80,220,120,30);
    frame.add(emailLabel);

    emailField =new JTextField();
    emailField.setBounds(180,220,150,30);
    frame.add(emailField);

    courseLabel=new JLabel("Courses");
    courseLabel.setBounds(80,260,120,30);
    frame.add(courseLabel);

    courseBox =new JComboBox<>(courseList);
    courseBox.setSelectedItem("select the Course");
    courseBox.setBounds(180,260,150,30);
    frame.add(courseBox);

    phoneLabel=new JLabel("Phone No");
    phoneLabel.setBounds(80,300,120,30);
    frame.add(phoneLabel);

    phoneField=new JTextField();
    phoneField.setBounds(180,300,150,30);
    frame.add(phoneField);

    remarksLabel=new JLabel("Remarks");
    remarksLabel.setBounds(80,340,120,30);
    frame.add(remarksLabel);

    remarksField=new JTextArea();
    remarksField.setBounds(180,340,150,30);
    frame.add(remarksField);

    saveButton=new JButton("Save");
    saveButton.setBounds(150,380,120,30);
    frame.add(saveButton);
    saveButton.addActionListener(this);
    saveButton.setActionCommand("save");

    editButton= new JButton("Edit");
    editButton.setBounds(500,300,120,30);
    frame.add(editButton);
    editButton.addActionListener(this);
    editButton.setActionCommand("update");

    deleteButton= new JButton("Delete");
    deleteButton.setBounds(700,300,120,30);
    frame.add(deleteButton);
    deleteButton.addActionListener(this);
    deleteButton.setActionCommand("delete");

    logoutButton=new JButton("Logout");
    logoutButton.setBounds(900,300,120,30);
    frame.add(logoutButton);
    logoutButton.addActionListener(this);
    logoutButton.setActionCommand("logout");

    changePasswordButton=new JButton("Change Password");
    changePasswordButton.setBounds(1100,300,150,30);
    frame.add(changePasswordButton);
    changePasswordButton.addActionListener(this);
    changePasswordButton.setActionCommand("change");

    searchLabel=new JLabel("Search by Email or First Name :");
    searchLabel.setBounds(500,40,180,30);
    frame.add(searchLabel);

    searchField=new JTextField();
    searchField.setBounds(700,40,150,30);
    frame.add(searchField);

    searchButton=new JButton("Search");
    searchButton.setBounds(900,40,100,30);
    frame.add(searchButton);
    searchButton.addActionListener(this);
    searchButton.setActionCommand("search");


    frame.setSize(1400,900);
    frame.setLayout(null);
    frame.setVisible(true);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    getStudentList();
}
    @Override
    public void actionPerformed(ActionEvent e) {
    //SAVE OR INSERT
    if(e.getActionCommand().equals("save")){
        String firstName=firstNameField.getText();
        String lastName=lastNameField.getText();
        String address=addressField.getText();
        String gender=maleGenderButton.isSelected()?"Male":"Female";
        String email =emailField.getText();
        String course=(String) courseBox.getSelectedItem();
        String phone=phoneField.getText();
        String remarks=remarksField.getText();
        try{
            if(studentIDField.getText().isEmpty()){
                Student std=new Student(firstName,lastName,address,gender,email,course,phone,remarks);
                //AddStudentDao Called
                if(new StudentDao().addStudent(std)){
                    JOptionPane.showMessageDialog(this.frame,"Student Details Added Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
                    refreshStudentList();
                }
                else{
                    JOptionPane.showMessageDialog(this.frame,"Registration Failed","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                Student std=new Student(firstName,lastName,address,gender,email,course,phone,remarks,Integer.parseInt(studentIDField.getText()));
                //Update Studentdao called
                if(new StudentDao().updateStudent(std)){
                    JOptionPane.showMessageDialog(this.frame,"Updated Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
                    refreshStudentList();
                }
                else{
                    JOptionPane.showMessageDialog(this.frame,"Updated Failed","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    //UPDATE STUDENT
    else if(e.getActionCommand().equals("update")){
        if(studentTable.getSelectedRowCount()<1){
            JOptionPane.showMessageDialog(this.frame,"Please Select a row to edit","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(studentTable.getSelectedRowCount()>1){
            JOptionPane.showMessageDialog(this.frame,"Please Select only 1 row at a time","Error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            studentIDField.setText(studentTable.getValueAt(studentTable.getSelectedRow(),0).toString());
            firstNameField.setText((String) studentTable.getValueAt(studentTable.getSelectedRow(),1));
            lastNameField.setText((String) studentTable.getValueAt(studentTable.getSelectedRow(),2));
            addressField.setText((String) studentTable.getValueAt(studentTable.getSelectedRow(),3));
            String gender=(String) studentTable.getValueAt(studentTable.getSelectedRow(),4);
            if(gender.equals("Male")){
                maleGenderButton.setSelected(true);
            }
            else {
                femaleGenderButton.setSelected(true);
            }
            emailField.setText((String) studentTable.getValueAt(studentTable.getSelectedRow(),5));
            courseBox.setSelectedItem((String) studentTable.getValueAt(studentTable.getSelectedRow(),6));
            phoneField.setText((String) studentTable.getValueAt(studentTable.getSelectedRow(),7));
            remarksField.setText((String) studentTable.getValueAt(studentTable.getSelectedRow(),8));
            saveButton.setText("update");
        }
    }
    //DELETE STUDENT
    else if(e.getActionCommand().equals("delete")){
        if(studentTable.getSelectedRowCount()<1){
            JOptionPane.showMessageDialog(this.frame,"Please Select a row to edit","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(studentTable.getSelectedRowCount()>1){
            JOptionPane.showMessageDialog(this.frame,"Please Select only 1 row at a time","Error",JOptionPane.ERROR_MESSAGE);
        }
        else {
            try {
                if(new StudentDao().deleteStudent(Integer.parseInt(studentTable.getValueAt(studentTable.getSelectedRow(),0).toString()))){
                    JOptionPane.showMessageDialog(this.frame,"Deleted Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
                    refreshStudentList();
                }
                else {
                    JOptionPane.showMessageDialog(this.frame,"Cannot Delete","Error",JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    //Logout
    else if(e.getActionCommand().equals("logout")){
        int logout_choice=JOptionPane.showConfirmDialog(this.frame,"Do you Want to logout");
        if(logout_choice==JOptionPane.YES_OPTION){
            this.frame.dispose();
            new WelcomeScreen();
        }
    }
    //changePassword
    else  if(e.getActionCommand().equals("change")){
            this.frame.dispose();
            new ChangePassword();
        }
    //Search
    else if(e.getActionCommand().equals("search")){
            getSearchList();
            searchField.setText("");
    }
    //export to excel
    else if(e.getActionCommand().equals("export")){
        fileChooser=new JFileChooser();
        fileChooser.showSaveDialog(this.frame);
        try{
            File f= fileChooser.getSelectedFile();
            path =f.getAbsolutePath();
            path= path+".xlsx";
            exportToExcel();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    }
    //Get Student List VIEW
    public DefaultTableModel getStudentList(){
    if(tmodel.getRowCount()>0){
        tmodel.setRowCount(0);
    }
    try{
        Connection connection=new DbConnection().getDbConnection();
        Statement stmt =connection.createStatement();
        ResultSet rs =stmt.executeQuery("SELECT STUDENTID,FIRSTNAME,LASTNAME,ADDRESS,GENDER,EMAIL,COURSES,PHONE,REMARKS FROM STUDENTS");
        while ((rs.next())){
            tmodel.addRow(new Object[]{
                rs.getString("STUDENTID"),
                rs.getString("FIRSTNAME"),
                    rs.getString("LASTNAME"),
                    rs.getString("ADDRESS"),
                    rs.getString("GENDER"),
                    rs.getString("EMAIL"),
                    rs.getString("COURSES"),
                    rs.getString("PHONE"),
                    rs.getString("REMARKS"),
            });
        }
        stmt.close();
        connection.close();
    }
    catch (Exception e) {
        e.printStackTrace();
    }
        return tmodel;
    }
    //REFRESH STUDENT LIST TABLE
    public  void refreshStudentList(){
        getStudentList();
        firstNameField.setText("");
        lastNameField.setText("");
        addressField.setText("");
        phoneField.setText("");
        remarksField.setText("");
        genderButton.clearSelection();
        maleGenderButton.setSelected(false);
        femaleGenderButton.setSelected(false);
        emailField.setText("");
        courseBox.setSelectedItem("Select Course");
        saveButton.setText("Save");
    }
    //Search Student List
    public void getSearchList(){
        studentTable.clearSelection();
        try{
            Connection connection=new DbConnection().getDbConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT STUDENTID,FIRSTNAME,LASTNAME,ADDRESS,GENDER,EMAIL,COURSES,PHONE,REMARKS FROM STUDENTS WHERE FIRSTNAME=? OR EMAIL=?");
            stmt.setString(1,searchField.getText());
            stmt.setString(2,searchField.getText());
            ResultSet rs =stmt.executeQuery();
            if ((rs.next())){
                for(int i=0;i<studentTable.getRowCount();i++){
                    if(rs.getString("EMAIL").equals(studentTable.getValueAt(i,5).toString())){
                        studentTable.addRowSelectionInterval(i,i);
                        JOptionPane.showMessageDialog(this.frame,"Data Available","Found",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(this.frame,"No Data Available","Not Found",JOptionPane.ERROR_MESSAGE);
            }
            stmt.close();
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Export Function
    public void exportToExcel() throws IOException {
    XSSFWorkbook wb= new XSSFWorkbook();
        XSSFSheet ws =wb.createSheet();
        DefaultTableModel model=getStudentList();
        System.out.println(model);
        TreeMap<String,Object[]> map =new TreeMap<>();
        TreeMap<String,String[]> map2 =new TreeMap<>();
        map2.put("0",new String[]{
                columns[0],
                columns[1],
                columns[2],
                columns[3],
                columns[4],
                columns[5],
                columns[6],
                columns[7],
                columns[8],
        });
        for(int i=0;i<model.getRowCount();i++){
            map.put(Integer.toString(i),new Object[]{
                    model.getValueAt(i,0),
                    model.getValueAt(i,1),
                    model.getValueAt(i,2),
                    model.getValueAt(i,3),
                    model.getValueAt(i,4),
                    model.getValueAt(i,5),
                    model.getValueAt(i,6),
                    model.getValueAt(i,7),
                    model.getValueAt(i,8),
            });
        }
        Set<String> id2=map2.keySet();
        Set<String> id=map.keySet();
        XSSFRow xssfRow;
        int rowId = 0;

        for(String key : id2){
            xssfRow =ws.createRow(rowId++);
            String[] value = map2.get(key);
            int cellId=0;
            for (String string : value){
                XSSFCell cell = xssfRow.createCell(cellId++);
                cell.setCellValue(string);
            }
        }

        for(String key : id){
            xssfRow =ws.createRow(rowId++);
            Object[] value = map.get(key);
            int cellId=0;
            for (Object object : value){
                XSSFCell cell = xssfRow.createCell(cellId++);
                cell.setCellValue(object.toString());
            }
        }
        try{
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        wb.write(fileOutputStream);
        fileOutputStream.close();
        JOptionPane.showMessageDialog(this.frame,"File exported Successfully at "+path,"success",JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
