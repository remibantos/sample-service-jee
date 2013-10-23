package org.rembx.test.jee.model;


import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateful
public class LibraryDAO {

    @PersistenceContext(unitName = "library")
    private EntityManager em;

    public void createBook(Book book) {
        if (book==null)
            throw new IllegalArgumentException("book cannot be null");
        em.persist(book);
    }

    public void updateBook(Book book) {
        if (book==null)
            throw new IllegalArgumentException("book cannot be null");
        em.merge(book);
    }

    public Book getBook(String id) {
        if (id==null)
            throw new IllegalArgumentException("book id cannot be null");
        return em.find(Book.class,id);
    }

    public void deleteBook(String id) {
        if (id==null)
            throw new IllegalArgumentException("book id cannot be null");

        Book book = em.find(Book.class, id);
        if (book != null)
            em.remove(book);
    }

    public Books getAllBooks() {
        TypedQuery<Book> query = em.createNamedQuery(Book.FIND_ALL, Book.class);
        return new Books(query.getResultList());
    }

}
