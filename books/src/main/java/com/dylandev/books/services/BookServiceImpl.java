package com.dylandev.books.services;

import com.dylandev.books.entities.Book;
import com.dylandev.books.entities.Image;
import com.dylandev.books.repositories.BookRepository;
import com.dylandev.books.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ImageService imageService;

    public BookServiceImpl(BookRepository bookRepository, ImageService imageService) {
        this.bookRepository = bookRepository;
        this.imageService = imageService;
    }

    @Override
    public Book saveBook(Book book, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            Image image = imageService.uploadImage(file);
            book.setImage(image);
        }
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void deleteBook(Book book) throws IOException{
        if (book.getImage() != null) {
            imageService.deleteImage(book.getImage());
        }
        bookRepository.deleteById(book.getId());
    }

    @Override
    public Book updateBookImage(MultipartFile file, Book book) throws IOException {
        if (book.getImage() != null) {
            imageService.deleteImage(book.getImage());
        }
        Image newImage = imageService.uploadImage(file);
        book.setImage(newImage);
        return bookRepository.save(book);
    }
}
