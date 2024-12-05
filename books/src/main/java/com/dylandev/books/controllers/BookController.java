package com.dylandev.books.controllers;

import com.dylandev.books.entities.Book;
import com.dylandev.books.services.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
@CrossOrigin("http://localhost:4200/")
public class BookController {

    @Autowired
    private BookServiceImpl bookServiceImpl;

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestPart("book") Book book, @RequestPart("file") MultipartFile file) {
        try{
            Book savedBook = bookServiceImpl.saveBook(book, file);
            return ResponseEntity.ok(savedBook);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/image")
    public ResponseEntity<Book> updateBookImage(@PathVariable Long id, @RequestPart("file") MultipartFile file) throws IOException {
        Optional<Book> book = bookServiceImpl.getBookById(id);
        if(book.isPresent()){
            Book updatedBook = bookServiceImpl.updateBookImage(file, book.get());
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
        try{
            Book savedBook = bookServiceImpl.updateBook(book);
            return ResponseEntity.ok(savedBook);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<>(bookServiceImpl.getBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Optional<Book> book = bookServiceImpl.getBookById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) throws IOException{
        Optional<Book> book = bookServiceImpl.getBookById(id);
        if(book.isPresent()){
            bookServiceImpl.deleteBook(book.get());
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
