package dev.lamp.models;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "manager")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="manager_id")
    private int managerId;

    @Column(name ="fname")
    private String fname;

    @Column(name ="lname")
    private String lname;

    @Column(name ="manager_username")
    private String managerUsername;

    @Column(name ="manager_password")
    private String managerPassword;

    @OneToMany(mappedBy = "managerId", fetch = FetchType.LAZY)
    Set<Expense> expenseList = new HashSet<>();

    public Manager() {
    }

    public Manager(int managerId, String fname, String lname,
                   String managerUsername, String managerPassword) {
        this.managerId = managerId;
        this.fname = fname;
        this.lname = lname;
        this.managerUsername = managerUsername;
        this.managerPassword = managerPassword;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
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

    public String getManagerUsername() {
        return managerUsername;
    }

    public void setManagerUsername(String managerUsername) {
        this.managerUsername = managerUsername;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }

    public Set<Expense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(Set<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerId=" + managerId +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", managerUsername='" + managerUsername + '\'' +
                ", managerPassword='" + managerPassword + '\'' +
                ", expenseList=" + expenseList +
                '}';
    }
}
