/*
 * PBA 2 - Employee Management System
 *
 * Name: SHAFIQ ARIF BIN SHAMSUL ARIF
 * Registration Number: 01DIT24F1203
 *
 * Name: MUHAMMAD AMSYAR ADAM BIN SHARUDIN
 * Registration Number: 01DIT24F1224
 *
 * Name: MUHAMMAD ADAM DANIAL BIN MOHAMMAD RIDZUAN
 * Registration Number: 01DIT24F11995
 *
 * Class: DITP5B
 */
package model;

public class Student {

    private int id;
    private String name;
    private String email;
    private String course;
    private String phone;

    public Student() {
    }

    public Student(String name, String email, String course, String phone) {
        this.name = name;
        this.email = email;
        this.course = course;
        this.phone = phone;
    }

    public Student(int id, String name, String email, String course, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.course = course;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}