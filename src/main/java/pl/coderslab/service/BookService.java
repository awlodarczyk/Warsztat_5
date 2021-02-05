package pl.coderslab.service;

import pl.coderslab.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getList();
    Book loadById(long id);
    void setList(List<Book> list);
    void remove(Book book);
    void add(Book book);
}
