package com.tw.springbootLibrarygradle.entity;


import javax.persistence.*;


@Entity
@Table
public class Book {
    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "book_sequence",
            strategy = GenerationType.SEQUENCE)
        private int id;
        @Column
        private String name;
        @Column
        private String author;
        @Column
        private int price;

        public Book(int id, String name, String author, int price) {
            this.id = id;
            this.name = name;
            this.author = author;
            this.price = price;
        }
    public Book( String name, String author, int price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }
        protected Book()
        {

        }


    public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAuthor() {
            return author;
        }

        public int getPrice() {
            return price;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setPrice(int price) {
            this.price = price;
        }


}
