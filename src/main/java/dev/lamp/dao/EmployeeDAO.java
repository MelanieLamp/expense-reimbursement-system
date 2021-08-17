package dev.lamp.dao;

import dev.lamp.models.Employee;
import java.util.List;

public interface EmployeeDAO {
    Employee login(String username, String password);
    Employee getEmployeeById(int id);
    List<Employee> getAllEmployees();
}
