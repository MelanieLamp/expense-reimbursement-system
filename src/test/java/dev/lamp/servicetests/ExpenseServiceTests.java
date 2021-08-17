package dev.lamp.servicetests;

import dev.lamp.dao.*;
import dev.lamp.models.Employee;
import dev.lamp.models.Expense;
import dev.lamp.models.Manager;
import dev.lamp.service.*;
import dev.lamp.utilities.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseServiceTests {
    @Mock
    private static EmployeeService employeeService = Mockito.mock(EmployeeService.class);
    private static EmployeeDAO employeeDAO = Mockito.mock(EmployeeDAOHibernate.class);
    @Mock
    private static ExpenseService expenseService = Mockito.mock(ExpenseService.class);
    private static ExpenseDAO expenseDAO = Mockito.mock(ExpenseDAOHibernate.class);
    @Mock
    private static ManagerService managerService = Mockito.mock(ManagerService.class);
    private static ManagerDAO managerDAO = Mockito.mock(ManagerDAOHibernate.class);

    private static Employee testEmployee;
    private static Expense testExpense;
    private static Manager testManager;

    private static List<Expense> expenseList;
    private static List<Employee> employeeList;
    private static List<Manager> managerList;

    @BeforeAll
    static void setup(){
        EmployeeService employeeService = new EmployeeServiceImpl(employeeDAO, expenseDAO);
        ExpenseService expenseService = new ExpenseServiceImpl(expenseDAO);
        ManagerService managerService = new ManagerServiceImpl(managerDAO, expenseDAO);

        Employee testEmployee1 = new Employee(0, "Mel",
                "Lamp", "user", "pass");
        Manager testManager1 = new Manager(0, "Melanie",
                "Lamp", "user", "pass");
        Expense testExpense1 = new Expense(0, 0, 0,
                250.00, new Date(System.currentTimeMillis()), "pending", "dummy", null);

        testEmployee1.setEmployeeId(1);
        testExpense1.setExpenseId(1);
        testManager1.setManagerId(1);

        Employee testEmployee2 = new Employee(0, "Emma",
                "Lamp", "user", "pass");
        Manager testManager2 = new Manager(0, "Lily",
                "Lamp", "user", "pass");
        Expense testExpense2 = new Expense(0, 0, 0,
                335.00, new Date(System.currentTimeMillis()), "approved", "", null);

        testEmployee2.setEmployeeId(2);
        testExpense2.setExpenseId(2);
        testManager2.setManagerId(2);

        List<Employee> empList = new ArrayList<>();
        empList.add(testEmployee1);
        empList.add(testEmployee2);

        List<Expense> expList = new ArrayList<>();
        expList.add(testExpense1);
        expList.add(testExpense2);

        List<Manager> manList = new ArrayList<>();
        manList.add(testManager1);
        manList.add(testManager2);
    }

    @AfterAll
    static void teardown()
    {
        managerDAO = null;
        expenseDAO = null;
        employeeDAO = null;
    }
    @Test
    @Order(1)
    void get_expense(){
        Expense testExpense2 = new Expense(0, 0, 0,
                335.00, new Date(System.currentTimeMillis()), "approved", "", null);
        Assertions.assertNotNull(Mockito.when(expenseService.getExpenseById(Mockito.anyInt(),
                Mockito.anyInt())).thenReturn(testExpense2));
    }
    @Test
    @Order(2)
    void get_all_expenses()
    {
        List<Expense> expList = new ArrayList<>();
        Assertions.assertNotNull(Mockito.when(expenseService.getAllExpenses(Mockito.anyObject())).thenReturn(expList));
    }
}
