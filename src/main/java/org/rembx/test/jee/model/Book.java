package org.rembx.test.jee.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@XmlRootElement
@NamedQuery(name = Book.FIND_ALL, query = "SELECT b FROM Book b")
public class Book {

    public static final String FIND_ALL = "Book.findAll";

    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private String title;
    private Float price;
    @Column(length = 2000)
    private String description;
    private String isbn;
    private Integer nbOfPage;
    private Boolean illustrations;


    public Book() {
    }

    public Book(Integer id, String title, Float price, String description, String isbn, Integer nbOfPage, Boolean illustrations) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.isbn = isbn;
        this.nbOfPage = nbOfPage;
        this.illustrations = illustrations;
    }

    public Book(String title, Float price, String description, String isbn, Integer nbOfPage, Boolean illustrations) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.isbn = isbn;
        this.nbOfPage = nbOfPage;
        this.illustrations = illustrations;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getNbOfPage() {
        return nbOfPage;
    }

    public void setNbOfPage(Integer nbOfPage) {
        this.nbOfPage = nbOfPage;
    }

    public Boolean getIllustrations() {
        return illustrations;
    }

    public void setIllustrations(Boolean illustrations) {
        this.illustrations = illustrations;
    }


}