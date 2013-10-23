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

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rembx.test.jee.model.Book;
import org.rembx.test.jee.model.LibraryDAO;
import org.rembx.test.jee.service.LibraryService;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;


@RunWith(Arquillian.class)
public class LibraryServiceIT {

    /**
     * The name of the WAR Archive that will be used by Arquillian to deploy the application.
     */
    private static final String APP_NAME = "sample-service-jee";
    /**
     * The path of the WSDL endpoint in relation to the deployed web application.
     */
    private static final String WSDL_PATH = "sample-service-jee/LibraryServiceImpl?wsdl";
    public static final String RESOURCES_META_INF = "src/main/resources/META-INF/";

    @ArquillianResource
    private URL deploymentUrl;

    private LibraryService client;

    @Deployment(testable = false)
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, APP_NAME + ".jar")
                .addPackage(LibraryService.class.getPackage())
                .addPackage(LibraryDAO.class.getPackage())
                .addAsManifestResource(new File(RESOURCES_META_INF + "persistence.xml"))
                .addAsManifestResource(new File (RESOURCES_META_INF + "beans.xml"));
    }

    @Before
    public void setup() {
        try {
            client = new LibraryClient(new URL(deploymentUrl, WSDL_PATH));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createShouldAddABookWhenNoIdProvided() {

        Book book = new Book("Beginning Java EE 7", 19.99F,"Beginning Java EE 7","143024626X",577,true);

        // Get a response from the WebService
        client.createOrUpdateBook(book);

        assertEquals(client.getAllBooks().size(), 1);

    }

}
