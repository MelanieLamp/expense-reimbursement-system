package dev.lamp.service;

import dev.lamp.dao.ExpenseDAO;
import dev.lamp.models.Employee;
import dev.lamp.models.Expense;
import java.util.List;

public class ExpenseServiceImpl implements ExpenseService {
    private ExpenseDAO expdao;
    public ExpenseServiceImpl(ExpenseDAO expenseDAO)
    {
        this.expdao = expenseDAO;
    }

    @Override
    public Expense getExpenseById(int emp, int id) {
        Expense exp = this.expdao.getExpenseById(emp, id);
        return exp;
    }
    @Override
    public List<Expense> getAllExpenses(Employee employee) {
        List<Expense> allExp = this.expdao.getAllExpenses();
        return allExp;
    }
}
