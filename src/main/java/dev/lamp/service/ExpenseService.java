package dev.lamp.service;

import dev.lamp.models.Employee;
import dev.lamp.models.Expense;

import java.util.List;

public interface ExpenseService {
    Expense getExpenseById(int emp, int id);
    List<Expense> getAllExpenses(Employee employee);
}
