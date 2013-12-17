package org.rembx.test.jee.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rembx.test.jee.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

/**
 * @author remibantos
 */
public class LibraryDAOTest {

    @Mock
    private EntityManager em;

    @Mock
    private TypedQuery<Book> typedQuery;

    private LibraryDAO toTest;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        toTest = spy(new LibraryDAO(em));
    }

    @Test
    public void createOrUpdateBook() throws Exception {
        Book book = new Book();

        toTest.createOrUpdateBook(book);
        verify(em).merge(book);

    }

    @Test
    public void createOrUpdateBook_withNullBook_shouldThowEx() throws Exception {
        try {
            toTest.createOrUpdateBook(null);
            fail();
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void getBook() throws Exception {
        toTest.getBook(1234);
        verify(em).find(Book.class, 1234);
    }

    @Test
    public void getBook_withNullId_shouldThowEx() throws Exception {
        try {
            toTest.getBook(null);
            fail();
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void deleteBook() throws Exception {
        Book mockResult = new Book();
        doReturn(mockResult)
                .when(em)
                .find(Book.class, 1234);

        toTest.deleteBook(1234);

        verify(em).remove(mockResult);
    }

    @Test
    public void deleteBook_withNullId_shouldThowEx() throws Exception {
        try {
            toTest.deleteBook(null);
            fail();
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void deleteBook_forUnknownBook_should() throws Exception {

        doReturn(null)
                .when(em)
                .find(Book.class, 1234);

        toTest.deleteBook(1234);

        verify(em, never()).remove(null);
    }

    @Test
    public void getAllBooks() throws Exception {
        List<Book> mockResult = new ArrayList<>();

        doReturn(typedQuery)
                .when(em)
                .createNamedQuery(Book.FIND_ALL, Book.class);

        doReturn(mockResult)
                .when(typedQuery)
                .getResultList();

        List<Book> actual = toTest.getAllBooks();

        assertEquals(mockResult, actual);

    }
}
