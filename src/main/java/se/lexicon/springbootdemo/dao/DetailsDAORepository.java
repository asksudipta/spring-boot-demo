package se.lexicon.springbootdemo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.springbootdemo.entity.Details;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Collections;

@Repository
public class DetailsDAORepository implements DetailsDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Details findById(int id) {
        return entityManager.find(Details.class,id);
    }

    @Override
    public Collection<Details> findAll() {
        return Collections.unmodifiableCollection(entityManager.createQuery
                ("select obj from Details obj").getResultList());
    }

    @Override
    @Transactional
    public Details create(Details details) {
        entityManager.persist(details);
        return details;
    }

    @Override
    @Transactional
    public Details update(Details details) {
        return entityManager.merge(details);
    }

    @Override
    @Transactional
    public void delete(Details details) {
       findById(details.getDetailsId());
       entityManager.remove(details);
    }
}
