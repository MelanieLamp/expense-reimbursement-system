package dev.lamp.daotests;

import dev.lamp.dao.*;
import dev.lamp.models.Employee;
import dev.lamp.models.Manager;
import dev.lamp.utilities.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ManagerTests
{
    private static ManagerDAO mdao;
    private static SessionFactory sf;


    @BeforeAll
    static void setup(){
        sf = HibernateUtil.getSessionFactory();
        mdao = new ManagerDAOHibernate(sf);

        Manager m1 = new Manager();
        m1.setManagerUsername("day3");
        m1.setManagerPassword("10");
    }

    @AfterAll
    static void teardown(){
        mdao = null;
        sf = null;
    }

    @Test
    @Order(1)
    void manager_login(){
        Manager manager = new Manager();
        manager.setManagerUsername("day3");
        manager.setManagerPassword("10");
        manager.setManagerId(0);
        mdao.login(manager.getManagerUsername(), manager.getManagerPassword());
        Assertions.assertNotEquals(0, manager.getManagerId());
    }

    @Test
    @Order(2)
    void get_manager() {
        int id = 1;
        Manager manager = mdao.getManagerById(1);
        Assertions.assertNotNull(manager);
    }
}
