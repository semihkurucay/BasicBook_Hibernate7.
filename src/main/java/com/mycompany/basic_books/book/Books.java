/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.basic_books.book;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author semih
 */
@Entity
@Table(name = "tbl_Books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "write")
    private String write;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "page")
    private int page;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getWrite() {
        return write;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getPage() {
        return page;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWrite(String write) {
        this.write = write;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPage(int page) {
        this.page = page;
    }

}
