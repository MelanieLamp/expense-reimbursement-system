package dev.lamp.service;

import dev.lamp.models.Employee;
import dev.lamp.models.Expense;
import java.util.List;

public interface EmployeeService {
    Employee login(String username, String password);
    Expense submitExpense(int id, Expense expense);
    List<Expense> getExpensesByEmployee(Employee employee);
    Employee getEmployeeById(int id);
}
