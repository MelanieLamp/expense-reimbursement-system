package dev.lamp.dao;

import dev.lamp.models.Employee;
import dev.lamp.models.Manager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ManagerDAOHibernate implements ManagerDAO
{
    private Logger log = Logger.getLogger(ManagerDAOHibernate.class.getName());
    private SessionFactory sf;

    public ManagerDAOHibernate(SessionFactory sf) { this.sf = sf;}

    @Override
    public Manager login(String username, String password)
    {
        try(Session sess = sf.openSession())
        {
            CriteriaBuilder build = sf.getCriteriaBuilder();

            CriteriaQuery<Manager> cq = build.createQuery(Manager.class);
            Root<Manager> rootEntry = cq.from(Manager.class);

            cq.select(rootEntry)
                    .where(build.equal(rootEntry.get("managerUsername"), username),
                            (build.equal(rootEntry.get("managerPassword"), password)));

            Query q = sess.createQuery(cq);
            Manager man = (Manager) q.getSingleResult();

            man.setManagerId(man.getManagerId());
            return man;

        }catch (HibernateException he){
            he.printStackTrace();
            log.error("Unable to login as Manager");
            return null;
        }
    }

    @Override
    public Manager getManagerById(int id)
    {
        try(Session sess = sf.openSession()){
            CriteriaBuilder build = sess.getCriteriaBuilder();
            CriteriaQuery<Manager> cq = build.createQuery(Manager.class);

            Root<Manager> rootEntry = cq.from(Manager.class);
            cq.select(rootEntry).where(build.equal(rootEntry.get("managerId"), id));

            Query q = sess.createQuery(cq);
            Manager man = (Manager) q.getSingleResult();
            return man;
        }catch(HibernateException he){
            he.printStackTrace();
            log.error("Unable to get manager by id");
            return null;
        }
    }
}
