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
package org.rembx.sample.jee.service;

import org.rembx.sample.jee.Resources;
import org.rembx.sample.jee.model.Book;
import org.rembx.sample.jee.model.Books;
import org.rembx.sample.jee.dao.LibraryDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Sample of stateless EJB WebService
 * @author remibantos
 */
@Stateless
@WebService

@JMSDestinationDefinitions({
        @JMSDestinationDefinition(
                name = Resources.TEST_QUEUE,
                resourceAdapter = "jmsra",
                interfaceName = "javax.jms.Queue",
                destinationName="testQueue2",
                description="Test Queue")})
public class LibraryWebService implements LibraryService {
    
    LibraryDAO libraryDAO;

    public LibraryWebService(){

    }
    
    @Inject
    public LibraryWebService (LibraryDAO libraryDAO){
        this.libraryDAO = libraryDAO;
    }

    @Override
    @WebMethod
    public void createOrUpdateBook(Book book) {
        libraryDAO.createOrUpdateBook(book);

    }

    @Override
    @WebMethod
    public Book findBook(Integer id) {
        return libraryDAO.getBook(id);
    }

    @Override
    @WebMethod
    public void deleteBook(Integer id) {

        libraryDAO.deleteBook(id);
    }

    @Override
    @WebMethod
    public Books getAllBooks() {
        return new Books(libraryDAO.getAllBooks());
    }
}
