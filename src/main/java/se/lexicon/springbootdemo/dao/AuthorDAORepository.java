package se.lexicon.springbootdemo.dao;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.springbootdemo.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class AuthorDAORepository implements AuthorDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Author findById(int id) {
        return entityManager.find(Author.class,id);
    }

    @Override
    @Transactional
    public Collection<Author> findAll() {
        Query selectQuery= (Query) entityManager.createQuery("select  obj from Author obj");
        return selectQuery.getResultList();

    }

    @Override
    @Transactional
    public Author create(Author author) {
        if(author==null)throw new IllegalArgumentException("Author field in null");
        entityManager.persist(author);
        return author;
    }

    @Override
    @Transactional
    public Author update(Author author) {
        if(author==null)throw new IllegalArgumentException("Author has null value");
       return entityManager.merge(author);

    }

    @Override
    @Transactional
    public void delete(int id) {
        Author deleteById=findById(id);
        if(deleteById==null)throw new IllegalArgumentException("Id has null value");
        entityManager.remove(deleteById);

    }
}
