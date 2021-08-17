package dev.lamp.utiltests;

import dev.lamp.utilities.HibernateUtil;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

public class HibernateUtilTest
{
    @Test
    public void creates_sessionFactory(){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        System.out.println(sf);
    }
}
