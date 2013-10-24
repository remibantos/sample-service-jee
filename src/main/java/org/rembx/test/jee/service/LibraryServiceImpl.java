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
package org.rembx.test.jee.service;

import org.rembx.test.jee.model.Book;
import org.rembx.test.jee.model.Books;
import org.rembx.test.jee.model.LibraryDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;


@Stateless
@WebService
public class LibraryServiceImpl implements LibraryService {

    @Inject
    LibraryDAO libraryDAO;

    @Override
    public void createOrUpdateBook(Book book) {
        if (book.getId() == null) {
            libraryDAO.createBook(book);
        } else {
            libraryDAO.updateBook(book);
        }

    }


    @Override
    public Book findBook(String id) {
        return libraryDAO.getBook(id);
    }

    @Override
    public void deleteBook(String id) {

        libraryDAO.deleteBook(id);
    }

    @Override
    public Books getAllBooks() {
        return new Books(libraryDAO.getAllBooks());
    }
}
