package model;

public class Student {
    private int studentid;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
    private String email;
    private String courses;
    private String phoneNo;
    private String remarks;

    public int getStudentid() {
        return studentid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getCourses() {
        return courses;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getRemarks() {
        return remarks;
    }

    //For Update Student model
    public Student(String firstName, String lastName, String address, String gender, String email, String courses, String phoneNo, String remarks,int student_id){
        this.firstName=firstName;
        this.lastName=lastName;
        this.address=address;
        this.gender=gender;
        this.email=email;
        this.courses=courses;
        this.phoneNo=phoneNo;
        this.remarks=remarks;
        this.studentid =student_id;
    }
    //For Insert Student Model
    public Student(String firstName, String lastName, String address, String gender, String email, String courses, String phoneNo, String remarks ){
        this.firstName=firstName;
        this.lastName=lastName;
        this.address=address;
        this.gender=gender;
        this.email=email;
        this.courses=courses;
        this.phoneNo=phoneNo;
        this.remarks=remarks;
    }
}
