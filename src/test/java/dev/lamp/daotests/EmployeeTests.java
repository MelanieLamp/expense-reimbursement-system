package dev.lamp.daotests;

import dev.lamp.dao.EmployeeDAO;
import dev.lamp.dao.EmployeeDAOHibernate;
import dev.lamp.dao.ManagerDAO;
import dev.lamp.dao.ManagerDAOHibernate;
import dev.lamp.models.Employee;
import dev.lamp.models.Manager;
import dev.lamp.utilities.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeTests
{
    private static EmployeeDAO empdao;
    private static SessionFactory sf;
    private static SessionFactory sf2;

    @BeforeAll
    static void setup(){
        sf = HibernateUtil.getSessionFactory();
        empdao = new EmployeeDAOHibernate(sf);

        Employee e1 = new Employee();
        Manager m1 = new Manager();

        e1.setEmployeeUsername("east324");
        e1.setEmployeePassword("333");
    }

    @AfterAll
    static void teardown(){
        empdao = null;
        sf = null;
    }

    @Test
    @Order(1)
    void employee_login(){
        Employee employee = new Employee();
        employee.setEmployeeUsername("Wast324");
        employee.setEmployeePassword("333");
        employee.setEmployeeId(0);
        empdao.login(employee.getEmployeeUsername(), employee.getEmployeePassword());
        Assertions.assertNotEquals(0, employee.getEmployeeId());
    }

    @Test
    @Order(2)
    void get_employee() {
        int id = 1;
        Employee employee = empdao.getEmployeeById(id);
        Assertions.assertEquals(1, employee.getEmployeeId());
    }

    @Test
    @Order(3)
    void get_all_employees() {
        List<Employee> empList = empdao.getAllEmployees();
        Assertions.assertNotNull(empList);
    }
}
