package dev.lamp.models;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="employee_id")
    private int employeeId;

    @Column(name ="fname")
    @NotNull
    private String fname;

    @Column(name ="lname")
    private String lname;

    @Column(name ="employee_username")
    @NotNull
    private String employeeUsername;

    @Column(name ="employee_password")
    private String employeePassword;

    @OneToMany(mappedBy = "employeeId", fetch = FetchType.LAZY)
    Set<Expense> expenseList = new HashSet<>();

    public Employee() {
    }

    public Employee(int employeeId, String fname,
                    String lname, String employeeUsername, String employeePassword) {
        this.employeeId = employeeId;
        this.fname = fname;
        this.lname = lname;
        this.employeeUsername = employeeUsername;
        this.employeePassword = employeePassword;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmployeeUsername() {
        return employeeUsername;
    }

    public void setEmployeeUsername(String employeeUsername) {
        this.employeeUsername = employeeUsername;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public Set<Expense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(Set<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", employeeUsername='" + employeeUsername + '\'' +
                ", employeePassword='" + employeePassword + '\'' +
                ", expenseList=" + expenseList +
                '}';
    }
}
