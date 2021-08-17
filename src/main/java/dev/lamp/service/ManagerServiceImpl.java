package dev.lamp.service;

import dev.lamp.dao.ExpenseDAO;
import dev.lamp.dao.ManagerDAO;
import dev.lamp.models.Expense;
import dev.lamp.models.Manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManagerServiceImpl implements ManagerService {
    private ExpenseDAO expdao;
    private ManagerDAO mdao;

    public ManagerServiceImpl(ManagerDAO managerDAO, ExpenseDAO expenseDAO) {
        this.mdao = managerDAO;
        this.expdao = expenseDAO;
    }
    @Override
    public Manager login(String username, String password) {
        Manager man = this.mdao.login(username, password);
        return man;
    }
    @Override
    public List<Expense> getAllExpenses() {
        List<Expense> eList = this.expdao.getAllExpenses();
        return eList;
    }
    @Override
    public Expense getExpenseById(int emp, int id)
    {
        return this.expdao.getExpenseById(emp, id);
    }
    @Override
    public Expense updateExpense(int manager, int expense, String status, String reason) {
        Expense expense1 = new Expense();
        expense1.setManagerId(manager);
        expense1.setExpenseId(expense);
        expense1.setDateDetermined(new Date(System.currentTimeMillis()));
        expense1.setStatus(status);
        expense1.setReason(reason);
        Expense exp = this.expdao.updateExpense(expense1);
        return exp;
    }
    @Override
    public List<Expense> getExpenseByStatus(int employee, String status)
    {
        List<Expense> allExp = this.expdao.getAllExpenses();
        List<Expense> selectedExp = new ArrayList<>();
        for(Expense e : allExp){
            if(status.toLowerCase().
                    contains("pending") && e.getStatus()=="pending"){
                selectedExp.add(e);
            }
            if(status.toLowerCase().
                    contains("approved") && e.getStatus()=="approved"){
                selectedExp.add(e);
            }
            if(status.toLowerCase().
                    contains("denied") && e.getStatus()=="denied"){
                selectedExp.add(e);
            }
        }
        return selectedExp;
    }
}
