package com.tw.springbootLibrarygradle.controller;

import com.tw.springbootLibrarygradle.entity.Book;
import com.tw.springbootLibrarygradle.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<Book> getAllBook()
    {
        return bookService.getAllBook();
    }
    @PostMapping("/books")
    private Book saveBook(@RequestBody Book books)
    {
        return   bookService.saveOrUpdate(books);
    }
    @DeleteMapping("/book/{id}")
    private void  deleteBook(@PathVariable("id") int bookId)
    {
      bookService.delete(bookId);
    }
    @PutMapping("/books/{id}")
    private Book update(@PathVariable int id , @RequestBody Book books)
    {
      return   bookService.update(books,id);

    }
}
