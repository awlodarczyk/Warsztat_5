package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.Book;
import pl.coderslab.model.JsonResponse;
import pl.coderslab.service.MemoryBookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    MemoryBookService bookService;

    @RequestMapping("/hello")
    public String hello() {
        return "{hello: World}";
    }

    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel", "Helion", "programming");
    }

    @RequestMapping("")
    public List<Book> books() {
        return bookService.getList();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Book bookById(@PathVariable Long id) {
        return bookService.loadById(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public JsonResponse create(@RequestBody Book book) {

        long lastId = 0;
        for (Book b : bookService.getList()) {
            if (lastId < b.getId()) {
                lastId = b.getId();
            }
        }
        book.setId(++lastId);
        bookService.add(book);
        return new JsonResponse("created new book", book);
    }
//    @RequestMapping(value = "", method = RequestMethod.POST)
//    public JsonResponse create(@RequestParam String isbn,
//                               @RequestParam String name,
//                               @RequestParam String author,
//                               @RequestParam String publisher,
//                               @RequestParam String type) {
//
//        long lastId = 0;
//        for (Book b : bookService.getList()) {
//            if (lastId < b.getId()) {
//                lastId = b.getId();
//            }
//        }
//        Book book = new Book(++lastId, isbn, name, author, publisher, type);
//        bookService.add(book);
//        return new JsonResponse("created new book", book);
//    }


//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public JsonResponse update(@PathVariable Long id,
//                               @RequestParam String isbn,
//                               @RequestParam String name,
//                               @RequestParam String author,
//                               @RequestParam String publisher,
//                               @RequestParam String type) {
//        Book book = null;
//        for (Book b : bookService.getList()){
//            if (b.getId().equals(id)) {
//                b.setIsbn(isbn);
//                b.setAuthor(author);
//                b.setPublisher(publisher);
//                b.setTitle(name);
//                b.setType(type);
//                book = b;
//            }
//        }
//        return new JsonResponse("updated book",book);
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public JsonResponse update(@PathVariable Long id,
                               @RequestBody Book book) {

        for (Book b : bookService.getList()){
            if (b.getId().equals(id)) {
                b.setIsbn(book.getIsbn());
                b.setAuthor(book.getAuthor());
                b.setPublisher(book.getPublisher());
                b.setTitle(book.getTitle());
                b.setType(book.getType());
                book = b;
            }
        }
        return new JsonResponse("updated book",book);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public JsonResponse create(@PathVariable Long id) {

        bookService.remove(bookService.loadById(id));
        return new JsonResponse("removed book id: "+id);
    }
}
