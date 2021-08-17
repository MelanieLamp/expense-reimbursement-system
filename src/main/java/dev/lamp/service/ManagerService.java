package dev.lamp.service;

import dev.lamp.models.Expense;
import dev.lamp.models.Manager;
import java.util.List;

public interface ManagerService {
    Manager login(String username, String password);
    List<Expense> getAllExpenses();
    Expense getExpenseById(int emp, int id);
    List<Expense> getExpenseByStatus(int employee, String status);
    Expense updateExpense(int manager, int expense, String status, String reason);
}
