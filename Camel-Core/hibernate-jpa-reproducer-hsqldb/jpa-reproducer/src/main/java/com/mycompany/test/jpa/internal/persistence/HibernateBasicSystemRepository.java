package com.mycompany.test.jpa.internal.persistence;

import java.net.URI;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import com.mycompany.test.interfaces.HibenrateBasicSystemInterface;
import com.mycompany.test.jpa.model.BasicSystem;

/**
 * Hibernate implementation of the {@link BasicSystemRepository}.
 *
 * @author arne
 */
public final class HibernateBasicSystemRepository implements HibenrateBasicSystemInterface{
    private EntityManager entityManager;

    public boolean add(BasicSystem basicSystem) {
        try {
            entityManager.persist(basicSystem);  
            return true;
        } catch(PersistenceException e) {
            return false;
        }
    }

    public BasicSystem get(String code) {
        try {
            return entityManager.createQuery("SELECT b FROM BasicSystem b WHERE b.code = :code", BasicSystem.class)
                    .setParameter("code", code).getSingleResult();
        } catch (NoResultException e) {
            //Let's keep the behaviour consistent with implementation we migrated from Hibernate Query.uniqueResult();
            return null;
        }
    }

    public BasicSystem get(long id) {
        return entityManager.find(BasicSystem.class, id);
    }

    public boolean update(BasicSystem basicSystem) {
        BasicSystem currentBasicSystem = get(basicSystem.getId());

        if (currentBasicSystem != null) {
            currentBasicSystem.setCode(basicSystem.getCode());
            currentBasicSystem.setName(basicSystem.getName());
            currentBasicSystem.setUri(basicSystem.getUri());

            entityManager.merge(currentBasicSystem);
            return true;
        }
        return false;
    }

    public boolean delete(String code) {
        BasicSystem basicSystem = get(code);

        if (basicSystem != null) {
            entityManager.remove(basicSystem);
            return true;
        }
        return false;
    }

    public List<BasicSystem> getAll() {
        return entityManager.createQuery("SELECT b FROM BasicSystem b ORDER BY b.name ASC", BasicSystem.class)
                .getResultList();
    }

    public List<BasicSystem> getAllByUri(URI uri) {
        return entityManager.createQuery("SELECT b FROM BasicSystem b where b.uri = :uri", BasicSystem.class)
                .setParameter("uri", uri.toString()).getResultList();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}