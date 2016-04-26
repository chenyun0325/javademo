package jvm;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private static List<Address> addresses = new ArrayList<Address>();

    private List<Book> books = new ArrayList<Book>();

    private static Address address = new Address();

    private Book book = new Book();

    public static Address getAddress() {
        return address;
    }

    public static void setAddress(Address address) {
        Student.address = address;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public static List<Address> getAddresses() {
        return addresses;
    }

    public static void setAddresses(List<Address> addresses) {
        Student.addresses = addresses;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
