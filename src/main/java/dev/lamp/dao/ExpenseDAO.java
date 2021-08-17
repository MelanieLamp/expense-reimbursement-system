package dev.lamp.dao;

import dev.lamp.models.Employee;
import dev.lamp.models.Expense;

import java.util.List;

public interface ExpenseDAO {
    Expense submitExpense(int id, Expense expense);
    Expense getExpenseById(int emp, int id);
    List <Expense> getExpenseByStatus(Employee employee, String status);
    List<Expense> getAllExpenses();
    Expense updateExpense(Expense expense);
}
