package org.rembx.sample.jee.model;

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
    @Column(length = 200)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (description != null ? !description.equals(book.description) : book.description != null) return false;
        if (id != null ? !id.equals(book.id) : book.id != null) return false;
        if (illustrations != null ? !illustrations.equals(book.illustrations) : book.illustrations != null)
            return false;
        if (isbn != null ? !isbn.equals(book.isbn) : book.isbn != null) return false;
        if (nbOfPage != null ? !nbOfPage.equals(book.nbOfPage) : book.nbOfPage != null) return false;
        if (price != null ? !price.equals(book.price) : book.price != null) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (nbOfPage != null ? nbOfPage.hashCode() : 0);
        result = 31 * result + (illustrations != null ? illustrations.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", isbn='" + isbn + '\'' +
                ", nbOfPage=" + nbOfPage +
                ", illustrations=" + illustrations +
                '}';
    }
}