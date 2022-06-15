package se.lexicon.springbootdemo.dao;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.springbootdemo.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

@Repository
public class BookDAORepository implements BookDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Book findById(int id) {
        return entityManager.find(Book.class,id);
    }

    @Override
    @Transactional
    public Collection<Book> findAll() {
        Query selectQuery=(Query) entityManager.createQuery("select bk from Book bk");
        return selectQuery.getResultList();
    }

    @Override
    @Transactional
    public Book create(Book book) {
        if(book==null) throw new IllegalIdentifierException("Book value is null");
        entityManager.persist(book);
        return book;
    }

    @Override
    @Transactional
    public Book update(Book book) {
        return entityManager.merge(book);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Book bookForDelete=findById(id);
        if(bookForDelete==null)throw new IllegalIdentifierException("Book is null");
        entityManager.remove(bookForDelete);

    }
}
