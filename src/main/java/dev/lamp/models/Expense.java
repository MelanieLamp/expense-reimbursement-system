package dev.lamp.models;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "expense")
public class Expense
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="expense_id")
    private int expenseId;

    @Column(name ="manager_id")
    private int managerId;

    @Column(name ="employee_id")
    private int employeeId;

    @Column(name ="amount")
    private double amount;

    @Column(name ="date_submitted")
    private Date dateSubmitted;

    @Column(name ="status")
    private String status;

    @Column(name ="reason")
    private String reason;

    @Column(name = "date_determined")
    private Date dateDetermined;

    public Expense() {
    }

    public Expense(int expenseId, int managerId, int employeeId,
                   double amount, Date dateSubmitted, String status,
                   String reason, Date dateDetermined) {
        this.expenseId = expenseId;
        this.managerId = managerId;
        this.employeeId = employeeId;
        this.amount = amount;
        this.dateSubmitted = dateSubmitted;
        this.status = status;
        this.reason = reason;
        this.dateDetermined = dateDetermined;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getDateDetermined() {
        return dateDetermined;
    }

    public void setDateDetermined(Date dateDetermined) {
        this.dateDetermined = dateDetermined;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseId=" + expenseId +
                ", managerId=" + managerId +
                ", employeeId=" + employeeId +
                ", amount=" + amount +
                ", dateSubmitted=" + dateSubmitted +
                ", status='" + status + '\'' +
                ", reason='" + reason + '\'' +
                ", dateDetermined=" + dateDetermined +
                '}';
    }
}
