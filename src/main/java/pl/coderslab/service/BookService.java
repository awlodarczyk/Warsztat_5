package pl.coderslab.service;

import pl.coderslab.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getList();
    Optional<Book> loadById(long id);
    boolean remove(Book book);
    Book update(Book book);
    Book add(Book book);
}
