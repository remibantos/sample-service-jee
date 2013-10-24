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
public class LibraryRestService {


    @Inject
    private LibraryDAO libraryDAO;

    @Context
    private UriInfo uriInfo;


    /**
     * curl -X POST --data-binary "<book><description>Books story</description><illustrations>true</illustrations><isbn>1234-12</isbn><nbOfPage>577</nbOfPage><price>12.5</price><title>Books story</title></book>" -H "Content-Type: application/xml" http://localhost:8080/sample-service-jee/rs/book -v
     * curl -X POST --data-binary "{\"description\":\"Books story\",\"illustrations\":true,\"isbn\":\"1234-12\",\"nbOfPage\":577,\"price\":12.5,\"title\":\"Books story\"}" -H "Content-Type: application/json" http://localhost:8080/sample-service-jee/rs/book -v
     */
    @POST
    public Response createOrUpdateBook(Book book) {
        if (book == null)
            throw new BadRequestException();
        if (book.getId() == null) {
            libraryDAO.createBook(book);
        } else {
            libraryDAO.updateBook(book);
        }
        URI bookUri = uriInfo.getAbsolutePathBuilder().path(book.getId().toString()).build();
        return Response.created(bookUri).build();
    }

    @PUT
    public Response updateBook(Book book) {
        if (book == null)
            throw new BadRequestException();

        libraryDAO.updateBook(book);
        return Response.ok().build();
    }

    /**
     * JSON : curl -X GET -H "Accept: application/json" http://localhost:8080/sample-service-jee/rs/book/1 -v
     * XML : curl -X GET -H "Accept: application/xml" http://localhost:8080/sample-service-jee/rs/book/1 -v
     */
    @GET
    @Path("{id}")
    public Response findBook(@PathParam("id") String id) {
        Book book = libraryDAO.getBook(id);

        if (book == null)
            throw new NotFoundException();

        return Response.ok(book).build();
    }

    /**
     * curl -X DELETE http://localhost:8080/sample-service-jee/rs/book/1 -v
     */
    @DELETE
    @Path("{id}")
    public Response deleteBook(@PathParam("id") String id) {
        libraryDAO.deleteBook(id);

        return Response.noContent().build();
    }

    /**
     * JSON : curl -X GET -H "Accept: application/json" http://localhost:8080/sample-service-jee/rs/book -v
     * XML : curl -X GET -H "Accept: application/xml" http://localhost:8080/sample-service-jee/rs/book -v
     */
    @GET
    public Response getAllBooks() {

        Books books = new Books(libraryDAO.getAllBooks());
        return Response.ok(books).build();
    }
}