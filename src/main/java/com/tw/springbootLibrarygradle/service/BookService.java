package com.tw.springbootLibrarygradle.service;

import com.tw.springbootLibrarygradle.entity.Book;
import com.tw.springbootLibrarygradle.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {

    @Autowired
    BooksRepository booksRepository;



    public List<Book> getAllBook() {
        List<Book> books = new ArrayList<>();
        booksRepository.findAll().forEach(book -> books.add(book));
        return books;
    }

    public Book saveOrUpdate(Book books) {

        return booksRepository.save(books);

    }

    public void delete(int bookId) {
       booksRepository.deleteById(bookId);

    }

    public Book findById(int bookId) throws NoSuchElementException
    {
        return  booksRepository.findById(bookId).get();
    }

    public Book update(Book books, int id) {

       return booksRepository.save(books);
    }

}
