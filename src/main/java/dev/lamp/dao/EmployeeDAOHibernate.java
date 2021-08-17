package dev.lamp.dao;

import dev.lamp.models.Employee;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class EmployeeDAOHibernate implements EmployeeDAO
{
    private Logger log = Logger.getLogger(EmployeeDAOHibernate.class.getName());
    private SessionFactory sf;

    public EmployeeDAOHibernate(SessionFactory sf) { this.sf = sf;}

    @Override
    public Employee login(String username, String password)
    {
        try(Session sess = sf.openSession())
        {
            CriteriaBuilder build = sf.getCriteriaBuilder();

            CriteriaQuery<Employee> cq = build.createQuery(Employee.class);
            Root<Employee> rootEntry = cq.from(Employee.class);

            cq.select(rootEntry)
                    .where(build.equal(rootEntry.get("employeeUsername"), username),
                    (build.equal(rootEntry.get("employeePassword"), password)));

            Query q = sess.createQuery(cq);
            Employee emp = (Employee) q.getSingleResult();

            emp.setEmployeeId(emp.getEmployeeId());
            return emp;

        }catch (HibernateException he)
        {
            he.printStackTrace();
            log.error("Unable to login as Employee");
            return null;
        }
    }

    @Override
    public Employee getEmployeeById(int id)
    {
        try(Session sess = sf.openSession()){
            CriteriaBuilder build = sess.getCriteriaBuilder();
            CriteriaQuery<Employee> cq = build.createQuery(Employee.class);

            Root<Employee> rootEntry = cq.from(Employee.class);
            cq.select(rootEntry).where(build.equal(rootEntry.get("employeeId"), id));

            Query q = sess.createQuery(cq);
            Employee emp = (Employee) q.getSingleResult();
            return emp;
        }catch(HibernateException he){
            he.printStackTrace();
            log.error("Unable to get employee by id");
            return null;
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        try(Session sess = sf.openSession()){
            CriteriaBuilder cb = sess.getCriteriaBuilder();
            CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

            Root<Employee> rootEntry = cq.from(Employee.class);
            CriteriaQuery <Employee> all = cq.select(rootEntry);
            TypedQuery<Employee> allQuery = sess.createQuery(all);
            return allQuery.getResultList();

        } catch(HibernateException he){
            he.printStackTrace();
            log.error("Unable to get all employees");
            return null;
        }
    }
}
