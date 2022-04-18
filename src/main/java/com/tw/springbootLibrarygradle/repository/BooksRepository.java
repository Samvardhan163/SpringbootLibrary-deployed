package com.tw.springbootLibrarygradle.repository;

import com.tw.springbootLibrarygradle.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BooksRepository extends CrudRepository<Book, Integer>
{
}