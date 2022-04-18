package com.tw.springbootLibrarygradle.service;

import com.tw.springbootLibrarygradle.entity.Book;
import com.tw.springbootLibrarygradle.repository.BooksRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
   private BooksRepository repository;

    @InjectMocks
  private BookService bookService;


    @Test
    void retrieveAllTheBooks()
    {
        Book books= new Book("To Kill a Mockingbird ","Harper Lee",250);
        List<Book> BookList = new ArrayList<>();
        BookList.add(books);

        when(repository.findAll()).thenReturn(BookList);

        List<Book> fetchedBooks = bookService.getAllBook();

        assertThat(fetchedBooks.size()).isGreaterThan(0);
    }

    @Test
    public void savedBooksSuccess() {
        Book books= new Book("To Kill a Mockingbird ","Harper Lee",250);
        List<Book> BookList = new ArrayList<>();
        BookList.add(books);

        when(repository.save(any(Book.class))).thenReturn(books);

        Book savedBook = repository.save(books);
        assertThat(savedBook.getAuthor()).isNotNull();
    }

    @Test
    public void verifyingWhetherDeleteIdExist()
    {
        Book books= new Book("To Kill a Mockingbird ","Harper Lee",250);

       bookService.delete(books.getId());

        verify(repository).deleteById(books.getId());
    }

    @Test
    void verifyingWhetherBookToAddedToTheRepo() {
        Book books= new Book("To Kill a Mockingbird ","Harper Lee",250);

        bookService.saveOrUpdate(books);

        verify(repository,times(1)).save(books);
    }

    @Test
    void verifyingWhetherBookToUpdatedInTheRepo() {
        Book books= new Book("To Kill a Mockingbird ","Harper Lee",250);

        bookService.update(books,books.getId());

        verify(repository,times(1)).save(books);
    }
}
