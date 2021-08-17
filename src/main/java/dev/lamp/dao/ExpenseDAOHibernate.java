package dev.lamp.dao;

import dev.lamp.models.Employee;
import dev.lamp.models.Expense;
import dev.lamp.utilities.HibernateUtil;
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

public class ExpenseDAOHibernate implements ExpenseDAO
{
    private SessionFactory sf = HibernateUtil.getSessionFactory();
    private Logger log = Logger.getLogger(ExpenseDAOHibernate.class.getName());

    @Override
    public Expense submitExpense(int id, Expense expense)
    {
        try{
            Session sess = sf.openSession();
            sess.getTransaction().begin();
            sess.save(expense);
            sess.getTransaction().commit();
            sess.close();
            return expense;
        }catch(HibernateException he){
            he.printStackTrace();
            log.error("Unable to submit expense");
        }
        return null;
    }

    @Override
    public Expense getExpenseById(int employee, int id)
    {
        try(Session sess = sf.openSession()){
            CriteriaBuilder build = sess.getCriteriaBuilder();
            CriteriaQuery<Expense> cq = build.createQuery(Expense.class);

            Root<Expense> rootEntry = cq.from(Expense.class);
            cq.select(rootEntry).where(build.equal(rootEntry.get("expenseId"), id));

            Query q = sess.createQuery(cq);
            Expense exp = (Expense) q.getSingleResult();
            return exp;
        }catch(HibernateException he){
            he.printStackTrace();
            log.error("Unable to get expense by id");
            return null;
        }
    }

    @Override
    public List<Expense> getExpenseByStatus(Employee employee, String status)
    {
        try(Session sess = sf.openSession()){
            CriteriaBuilder build = sess.getCriteriaBuilder();
            CriteriaQuery<Expense> cq = build.createQuery(Expense.class);

            Root<Expense> rootEntry = cq.from(Expense.class);
            CriteriaQuery <Expense> all = cq.select(rootEntry)
                    .where(build.equal(rootEntry.get("status"), status));

            TypedQuery<Expense> allQuery = sess.createQuery(all);
            sess.close();

            return allQuery.getResultList();
        }catch(HibernateException he){
            he.printStackTrace();
            log.error("Unable to get expenses by status");
            return null;
        }
    }

    @Override
    public List<Expense> getAllExpenses()
    {
        try(Session sess = sf.openSession()){
            CriteriaBuilder cb = sess.getCriteriaBuilder();
            CriteriaQuery<Expense> cq = cb.createQuery(Expense.class);

            Root<Expense> rootEntry = cq.from(Expense.class);
            CriteriaQuery <Expense> all = cq.select(rootEntry);
            TypedQuery<Expense> allQuery = sess.createQuery(all);

            return allQuery.getResultList();
        } catch(HibernateException he){
            he.printStackTrace();
            log.error("Unable to get all expenses");
        }
        return null;
    }

    @Override
    public Expense updateExpense(Expense expense)
    {
        try(Session sess = sf.openSession()){
            sess.getTransaction().begin();
            sess.update(expense);
            sess.getTransaction().commit();
            sess.close();
            return expense;
        }catch(HibernateException he){
            he.printStackTrace();
            log.error("Unable to update expense by status");
        }
        return null;
    }
}
