package com.tw.springbootLibrarygradle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.springbootLibrarygradle.entity.Book;
import com.tw.springbootLibrarygradle.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void shouldRetrieveAllTheBooksFromTheRepo() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String bookJSON = objectMapper.writeValueAsString(Arrays.asList(new Book("To Kill a Mockingbird ","Harper Lee",250),new Book("Lord of the Rings","J.R.R.Tolkien",1000)));
        System.out.println(bookJSON);

        when(bookService.getAllBook()).thenReturn(Arrays.asList(new Book("To Kill a Mockingbird ","Harper Lee",250),new Book("Lord of the Rings","J.R.R.Tolkien",1000)));
        RequestBuilder request= MockMvcRequestBuilders.get("/books").accept(MediaType.APPLICATION_JSON);


        MvcResult result=mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(bookJSON))
                .andReturn();
    }

    @Test
    void shouldRetrieveZeroBooksWhenThereNoBooksInTheRepo() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String bookJSON = objectMapper.writeValueAsString(Arrays.asList());


        when(bookService.getAllBook()).thenReturn(Arrays.asList());
        RequestBuilder request= MockMvcRequestBuilders.get("/books").accept(MediaType.APPLICATION_JSON);


        MvcResult result=mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(bookJSON))
                .andReturn();
    }

    @Test
    void shouldAddBooksInTheRepo() throws Exception {
        Book book =new Book("To Kill a Mockingbird","Harper Lee",250);
        ObjectMapper objectMapper = new ObjectMapper();
        String bookJSON = objectMapper.writeValueAsString(book);


        when(bookService.saveOrUpdate(ArgumentMatchers.any(Book.class))).thenReturn(book);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJSON)
        );


        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("To Kill a Mockingbird"))
                .andExpect(jsonPath("$.author").value("Harper Lee"))
                .andExpect(jsonPath("$.price").value(250))
                .andReturn();


    }

    @Test
    void shouldUpdateBooksInTheRepoById() throws Exception {
        Book book =new Book("To Kill a Mockingbird","Harper Lee",250);
        ObjectMapper objectMapper = new ObjectMapper();
        String bookJSON = objectMapper.writeValueAsString(book);


        when(bookService.update(ArgumentMatchers.any(Book.class),anyInt())).thenReturn(book);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJSON)
        );


        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("To Kill a Mockingbird"))
                .andExpect(jsonPath("$.author").value("Harper Lee"))
                .andExpect(jsonPath("$.price").value(250))
                .andReturn();


    }

    @Test
    void shouldDeleteBooksInTheRepoById() throws Exception {
        Book book =new Book("To Kill a Mockingbird","Harper Lee",250);

        bookService.saveOrUpdate(book);


        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/book/1")
                .contentType(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk());
    }
}
