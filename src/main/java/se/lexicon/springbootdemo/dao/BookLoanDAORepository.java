package se.lexicon.springbootdemo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.springbootdemo.entity.BookLoan;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

@Repository
public class BookLoanDAORepository implements BookLoanDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public BookLoan findById(int id) {
        return entityManager.find(BookLoan.class,id);
    }

    @Override
    @Transactional
    public Collection<BookLoan> findAll() {
        Query selectquery=(Query) entityManager.createQuery("select bookl from BookLoan bookl").getResultList();
        return selectquery.getResultList();

    }

    @Override
    @Transactional
    public BookLoan create(BookLoan bookLoan) {
        if(bookLoan==null)throw new IllegalArgumentException("Book Loan is null");
        entityManager.persist(bookLoan);
        return null;
    }

    @Override
    @Transactional
    public BookLoan update(BookLoan bookLoan) {
        return entityManager.merge(bookLoan);
    }

    @Override
    @Transactional
    public void delete(int id) {
        BookLoan bookLoanForRemove=findById(id);
        if (bookLoanForRemove==null)throw new IllegalArgumentException("Bookloan value is null");
        entityManager.remove(bookLoanForRemove);

    }
}
