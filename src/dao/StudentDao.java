package dao;

import database.DbConnection;
import model.Student;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDao {
    Connection connection= null;
    PreparedStatement pstmt;
    //Register User
    public boolean signupUser(User user) throws  Exception{
        boolean isRegistered = false;
        connection=new DbConnection().getDbConnection();
        pstmt =connection.prepareStatement("INSERT INTO USERS(FULLNAME,EMAIL,USERNAME,PASSWORD) VALUES (?,?,?,?)");
        pstmt.setString(1,user.getFullName());
        pstmt.setString(2,user.getEmail());
        pstmt.setString(3,user.getUserName());
        pstmt.setString(4,user.getPassword());
        int rows= pstmt.executeUpdate();
        if(rows>0){
            isRegistered=true;
        }
        pstmt.close();
        connection.close();
        return isRegistered;
    }
    //Login CheckUser Validity
    public boolean checkValidUser(User user) throws Exception {
        boolean isValidUser=false;
        connection=new DbConnection().getDbConnection();
        pstmt =connection.prepareStatement("SELECT COUNT(1) FROM USERS WHERE USERNAME=? AND PASSWORD=?");
        pstmt.setString(1,user.getUserName());
        pstmt.setString(2, user.getPassword());
        ResultSet resultSet= pstmt.executeQuery();
        if(resultSet.next()){
            isValidUser=resultSet.getInt(1)>0?true:false;
        }
        pstmt.close();
        connection.close();
        return isValidUser;
    }
    //Add or Insert Student
    public boolean addStudent(Student std) throws Exception{
        boolean isAdded=false;
        connection=new DbConnection().getDbConnection();
        pstmt= connection.prepareStatement("INSERT INTO STUDENTS(FIRSTNAME,LASTNAME,ADDRESS,GENDER,EMAIL,COURSES,PHONE,REMARKS) VALUES(?,?,?,?,?,?,?,?)");
        pstmt.setString(1,std.getFirstName());
        pstmt.setString(2,std.getLastName());
        pstmt.setString(3,std.getAddress());
        pstmt.setString(4,std.getGender());
        pstmt.setString(5,std.getEmail());
        pstmt.setString(6,std.getCourses());
        pstmt.setString(7,std.getPhoneNo());
        pstmt.setString(8,std.getRemarks());
        int rows=pstmt.executeUpdate();
        if(rows>0){
            isAdded=true;
        }
        pstmt.close();
        connection.close();
        return isAdded;
    }
    //Update Student
    public boolean updateStudent(Student std)throws Exception{
        boolean isUpdated = false;
        connection=new DbConnection().getDbConnection();
        pstmt=connection.prepareStatement("UPDATE STUDENTS SET FIRSTNAME=?,LASTNAME=?,ADDRESS=?,GENDER=?,EMAIL=?,COURSES=?,PHONE=?,REMARKS=? WHERE STUDENTID=?");
        pstmt.setString(1,std.getFirstName());
        pstmt.setString(2,std.getLastName());
        pstmt.setString(3,std.getAddress());
        pstmt.setString(4,std.getGender());
        pstmt.setString(5,std.getEmail());
        pstmt.setString(6,std.getCourses());
        pstmt.setString(7,std.getPhoneNo());
        pstmt.setString(8,std.getRemarks());
        pstmt.setInt(9,std.getStudentid());
        int rows=pstmt.executeUpdate();
        if(rows>0){
            isUpdated=true;
        }
        pstmt.close();
        connection.close();
        return isUpdated;
    }
    //Delete Student
    public boolean deleteStudent(int studentid) throws Exception{
        boolean isDeleted=false;
        connection= new DbConnection().getDbConnection();
        pstmt =connection.prepareStatement("DELETE FROM STUDENTS WHERE STUDENTID=?");
        pstmt.setInt(1,studentid);
        int rows=pstmt.executeUpdate();
        if(rows>0){
            isDeleted=true;
        }
        pstmt.close();
        connection.close();
        return isDeleted;
    }
}
