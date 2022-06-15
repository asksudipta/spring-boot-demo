package se.lexicon.springbootdemo.dao;


import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.springbootdemo.entity.AppUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;


@Repository
public class AppUserDaoRepository implements AppUserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public AppUser findById(int id) {
        return entityManager.find(AppUser.class,id);
    }

    @Override
    @Transactional
    public Collection<AppUser> findAll() {
        Query selectQuery= (Query) entityManager.createQuery("select  obj from Details obj");
        return selectQuery.getResultList();

        //return Collections.unmodifiableCollection(entityManager.createQuery("select obj from Details obj").getResultList());
    }

    @Override
    @Transactional
    public AppUser create(AppUser appUser) {
        if(appUser==null) throw new IllegalArgumentException("AppUser has null value");
        entityManager.persist(appUser);
        return appUser;
    }

    @Override
    @Transactional
    public void delete(int id) {
        AppUser result = findById(id);
        if(result==null)throw new IllegalArgumentException("Id has null value");
        entityManager.remove(result);

    }
}
