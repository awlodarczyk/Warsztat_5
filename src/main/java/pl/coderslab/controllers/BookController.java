package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.Book;
import pl.coderslab.model.JsonResponse;
import pl.coderslab.service.BookService;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {


    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

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
        return bookService.loadById(id).orElseThrow(()->new RuntimeException("Non matching book"));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public JsonResponse create(@RequestBody Book book) {
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
        book.setId(id);
        bookService.update(book);
        return new JsonResponse("updated book",book);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public JsonResponse create(@PathVariable Long id) {

        if(bookService.remove(bookService.loadById(id).orElseThrow(()->new RuntimeException("Non matching book")))){
            return new JsonResponse("removed book id: "+id);
        }else{
            return new JsonResponse("cannot remove book id: "+id);
        }

    }
}
