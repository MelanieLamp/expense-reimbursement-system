package dev.lamp.daotests;

import dev.lamp.dao.*;
import dev.lamp.models.Employee;
import dev.lamp.models.Expense;
import dev.lamp.models.Manager;

import dev.lamp.utilities.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.util.Date;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseTests
{
    private static ExpenseDAO expdao;
    private static SessionFactory sf;
    private static Expense testExpense = null;

    @BeforeAll
    static void setup(){
        sf = HibernateUtil.getSessionFactory();
        expdao = new ExpenseDAOHibernate();

        Employee employee = new Employee(0,
                "Emily", "Turner", "user", "pass");
        Expense exp1 = new Expense(0, 2, 1,
                250.00, new Date(System.currentTimeMillis()), "Pending", "", null);
        Expense exp2 = new Expense(0, 2, 1,
                450.00, new Date(System.currentTimeMillis()), "Pending", "", null);
    }

    @AfterAll
    static void teardown()
    {
        sf = null;
    }

    @Test
    @Order(1)
    void submit_expense(){
        Expense expense = new Expense(0, 2, 1,
                250.00, new Date(System.currentTimeMillis()), "Pending", "", null);
        Employee employee = new Employee(0,
                "Emily", "Turner", "user", "pass");
        expense = expdao.submitExpense(employee.getEmployeeId(), expense);
        Assertions.assertNotNull(expense);
        testExpense = expense;
    }

    @Test
    @Order(2)
    void get_expense(){
        int id = 2;
        Employee employee = new Employee(0,
                "Emily", "Jessica", "user", "pass");
        Expense expense = expdao.getExpenseById(employee.getEmployeeId(), id);
        Assertions.assertNotNull(expense);
    }

    @Test
    @Order(3)
    void get_all_expenses_by_employee(){
        Employee employee = new Employee(1,
                "Emily", "Abbey", "user", "pass");
        Expense exp1 = new Expense(0, 2, 2,
                250.00, new Date(System.currentTimeMillis()), "Pending", "", null);
        Expense exp2 = new Expense(0, 2, 3,
                450.00, new Date(System.currentTimeMillis()), "Pending", "", null);

        expdao.submitExpense(employee.getEmployeeId(), exp1);
        expdao.submitExpense(employee.getEmployeeId(), exp2);
        List<Expense> expList = expdao.getAllExpenses();
        Assertions.assertTrue(expList.size() > 1);
    }

    @Test
    @Order(4)
    void update_expense(){
        Employee employee = new Employee(0, "Mel", "Lamp", "user",
                "pass");
        Manager manager = new Manager(0, "taylor", "swift", "user",
                "pass");
        employee.setEmployeeId(2);
        Expense exp = expdao.getExpenseById(employee.getEmployeeId(), testExpense.getExpenseId());
        exp.setAmount(333.0);
        Expense updatedExpense = expdao.updateExpense(exp);
        Assertions.assertEquals(333.0 , updatedExpense.getAmount());
    }
}
