package dao;

import hibernate.util.HibernateUtil;

import javax.persistence.EntityManager;

public abstract class AbstractDao {

    protected final HibernateUtil hibernateUtil = HibernateUtil.getInstance();
    protected final EntityManager entityManager = hibernateUtil.getEntityManager();

}
