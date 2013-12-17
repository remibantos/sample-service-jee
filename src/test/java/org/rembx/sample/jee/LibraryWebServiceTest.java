/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.rembx.sample.jee;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rembx.sample.jee.dao.LibraryDAO;
import org.rembx.sample.jee.model.Book;
import org.rembx.sample.jee.model.Books;
import org.rembx.sample.jee.service.LibraryWebService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * @author remibantos
 */
public class LibraryWebServiceTest {
    
    @Mock
    private LibraryDAO libraryDAO;
    
    private LibraryWebService toTest;
    
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        toTest = spy(new LibraryWebService(libraryDAO));
    }

    @Test
    public void createOrUpdateBook() {
        Book book = new Book(1,"Beginning Java EE 7", 19.99F,"Beginning Java EE 7","143024626X",577,true);
        
        toTest.createOrUpdateBook(book);
        verify(libraryDAO).createOrUpdateBook(book);
    }

    @Test
    public void findBook() {
        toTest.findBook(1234);
        verify(libraryDAO).getBook(1234);
    }

    @Test
    public void deleteBook() {
        toTest.deleteBook(1234);
        verify(libraryDAO).deleteBook(1234);
    }

    @Test
    public void getAllBooks() {
        Books mockResult = new Books();
        doReturn(mockResult)
                .when(libraryDAO)
                .getAllBooks();
        Books books = toTest.getAllBooks();
        verify(libraryDAO).getAllBooks();
        assertEquals(mockResult, books);
    }
    
}
