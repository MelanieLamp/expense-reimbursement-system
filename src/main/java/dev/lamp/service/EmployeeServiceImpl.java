package dev.lamp.service;

import dev.lamp.dao.EmployeeDAO;
import dev.lamp.dao.ExpenseDAO;
import dev.lamp.models.Employee;
import dev.lamp.models.Expense;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDAO empdao;
    private ExpenseDAO expdao;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO, ExpenseDAO expenseDAO) {
        this.empdao = employeeDAO;
        this.expdao = expenseDAO;
    }
    @Override
    public Employee login(String username, String password) {
        Employee emp = empdao.login(username, password);
        return emp;
    }
    @Override
    public Expense submitExpense(int id, Expense expense)
    {
        if(id == expense.getEmployeeId()){
            this.expdao.submitExpense(id, expense);
            expense.setDateSubmitted(new Date(System.currentTimeMillis()));
            return expense;
        }
        return null;
    }
    @Override
    public List<Expense> getExpensesByEmployee(Employee employee) {
        List<Expense> expList = this.expdao.getAllExpenses();
        List<Expense> selectedExp = new ArrayList<>();
        for(Expense ex : expList){
            if(ex.getExpenseId() == employee.getEmployeeId()){ expList.add(ex);}}
        return selectedExp;
    }

    @Override
    public Employee getEmployeeById(int id) {
        return this.getEmployeeById(id);
    }
}
