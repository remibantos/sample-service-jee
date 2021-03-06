package org.rembx.sample.jee.dao;


import org.rembx.sample.jee.model.Book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author remibantos
 */
@Stateless
public class LibraryDAO {

    @PersistenceContext(unitName = "library")
    private EntityManager em;

    public LibraryDAO(){
    }
    
    public LibraryDAO(EntityManager em){
        this.em = em;
    }

    public void createOrUpdateBook(Book book) {
        if (book==null)
            throw new IllegalArgumentException("book cannot be null");
            em.merge(book);
    }

    public Book getBook(Integer id) {
        if (id==null)
            throw new IllegalArgumentException("book id cannot be null");
        return em.find(Book.class,id);
    }

    public void deleteBook(Integer id) {
        if (id==null)
            throw new IllegalArgumentException("book id cannot be null");

        Book book = em.find(Book.class, id);
        if (book != null)
            em.remove(book);
    }

    public List<Book> getAllBooks() {
        TypedQuery<Book> query = em.createNamedQuery(Book.FIND_ALL, Book.class);
        return query.getResultList();
    }

}
