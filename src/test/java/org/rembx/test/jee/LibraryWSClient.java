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
package org.rembx.test.jee;

import org.rembx.test.jee.model.Book;
import org.rembx.test.jee.model.Books;
import org.rembx.test.jee.service.LibraryService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

/**
 * @author remibantos
 */
public class LibraryWSClient implements LibraryService {
    private LibraryService libraryService;

    /**
     * Default constructor
     * 
     * @param wsdlUrl The URL to the LibraryService WSDL endpoint.
     */
    public LibraryWSClient(final URL wsdlUrl) {
        QName serviceName = new QName("http://service.jee.test.rembx.org/", "LibraryWebServiceService");

        Service service = Service.create(wsdlUrl, serviceName);
        libraryService = service.getPort(LibraryService.class);
        assert (libraryService != null);
    }

    @Override
    public void createOrUpdateBook(Book book) {
        libraryService.createOrUpdateBook(book);
    }

    @Override
    public Book findBook(Integer id) {
        return libraryService.findBook(id);
    }

    @Override
    public void deleteBook(Integer id) {
        libraryService.deleteBook(id);
    }

    @Override
    public Books getAllBooks() {
        return libraryService.getAllBooks();
    }
}
