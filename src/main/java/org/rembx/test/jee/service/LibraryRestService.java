package org.rembx.test.jee.service;

import org.rembx.test.jee.model.Book;
import org.rembx.test.jee.model.Books;
import org.rembx.test.jee.model.LibraryDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;


@Path("/book")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Stateless
public class LibraryRestService implements LibraryService {


    @Inject
    private LibraryDAO libraryDAO;

    @Context
    private UriInfo uriInfo;


    /**
     * curl -X POST --data-binary "<book><description>Books story</description><illustrations>true</illustrations><isbn>1234-12</isbn><nbOfPage>577</nbOfPage><price>12.5</price><title>Books story</title></book>" -H "Content-Type: application/xml" http://localhost:8080/sample-service-jee/rs/book -v
     * curl -X POST --data-binary "{\"description\":\"Books story\",\"illustrations\":true,\"isbn\":\"1234-12\",\"nbOfPage\":577,\"price\":12.5,\"title\":\"Books story\"}" -H "Content-Type: application/json" http://localhost:8080/sample-service-jee/rs/book -v
     */
    @POST
    @Override
    public void createOrUpdateBook(Book book) {
        if (book == null)
            throw new BadRequestException();
        libraryDAO.createOrUpdateBook(book);
    }

    /**
     * JSON : curl -X GET -H "Accept: application/json" http://localhost:8080/sample-service-jee/rs/book/1 -v
     * XML : curl -X GET -H "Accept: application/xml" http://localhost:8080/sample-service-jee/rs/book/1 -v
     */
    @GET
    @Path("{id}")
    @Override
    public Book findBook(@PathParam("id") Integer id) {

        return libraryDAO.getBook(id);
    }

    /**
     * curl -X DELETE http://localhost:8080/sample-service-jee/rs/book/1 -v
     */
    @DELETE
    @Path("{id}")
    @Override
    public void deleteBook(@PathParam("id") Integer id) {
        libraryDAO.deleteBook(id);
    }

    /**
     * JSON : curl -X GET -H "Accept: application/json" http://localhost:8080/sample-service-jee/rs/book -v
     * XML : curl -X GET -H "Accept: application/xml" http://localhost:8080/sample-service-jee/rs/book -v
     */
    @GET
    @Override
    public Books getAllBooks() {

        return new Books(libraryDAO.getAllBooks());
    }

    /**
     * Same as createOrUpdateBook
     * Use a ResponseBuilder
     *
     * @param book a book to create or update
     * @return a response
     */
    @POST
    public Response createOrUpdateBookWithResponse(Book book) {
        if (book == null)
            throw new BadRequestException();
        libraryDAO.createOrUpdateBook(book);
        URI bookUri = uriInfo.getAbsolutePathBuilder().path(book.getId().toString()).build();
        return Response.created(bookUri).build();
    }
}