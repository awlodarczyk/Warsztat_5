package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemoryBookService implements BookService{
    private List<Book> list;

    public MemoryBookService() {
        list = new ArrayList<>();
        list.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel",
            "Helion", "programming"));
        list.add(new Book(2L, "9788324627738", "Rusz glowa, Java.",
            "Sierra Kathy, Bates Bert", "Helion", "programming"));
        list.add(new Book(3L, "9780130819338", "Java 2. Podstawy",
            "Cay Horstmann, Gary Cornell", "Helion", "programming"));
    }
    public List<Book> getList() {return list;}
    public void setList(List<Book> list) {this.list = list;}

    public Optional<Book> loadById(long id){
        return getList().stream().filter(it->it.getId() == id).findFirst();
//        for (Book b: getList()){
//            if(b.getId()==id){
//                return b;
//            }
//        }
//        return null;
    }

    public Book update(Book book){
       for(Book it: getList()) {
           if(it.getId().equals(book.getId())) {
               it.setAuthor(book.getAuthor());
               it.setIsbn(book.getIsbn());
               it.setPublisher(book.getPublisher());
               it.setTitle(book.getTitle());
               it.setType(book.getType());
               return it;
           }
       }
       return null;
    }
    public boolean remove(Book book){
        return list.remove(book);
    }
    public Book add(Book book){
        long lastId = 0;
        for (Book b : getList()) {
            if (lastId < b.getId()) {
                lastId = b.getId();
            }
        }
        book.setId(++lastId);
        list.add(book);
        return book;
    }


}