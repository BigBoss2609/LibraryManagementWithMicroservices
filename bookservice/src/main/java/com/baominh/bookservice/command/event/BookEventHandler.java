package com.baominh.bookservice.command.event;

import com.baominh.bookservice.command.data.Book;
import com.baominh.bookservice.command.data.BookRepository;
import event.BookUpdatedStatusEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookEventHandler {
    @Autowired
    private BookRepository bookRepository;

    @EventHandler
    public void onBookCreatedEvent(BookCreatedEvent event) {
        Book book = new Book();
        BeanUtils.copyProperties(event, book);
        bookRepository.save(book);
    }

    @EventHandler
    public void onBookUpdatedEvent(BookUpdatedEvent event) {
        Optional<Book> book = bookRepository.findById(event.getBookId());
        if(book.isPresent()) {
            Book updatedBook = book.get();
            updatedBook.setName(event.getName());
            updatedBook.setAuthor(event.getAuthor());
            updatedBook.setIsReady(event.getIsReady());
            bookRepository.save(updatedBook);
        }
        else {
            throw new IllegalArgumentException("Book not found with id: " + event.getBookId());
        }
    }

    @EventHandler
    public void onBookDeletedEvent(BookDeletedEvent event) {
        Optional<Book> book = bookRepository.findById(event.getBookId());
        if(book.isPresent()) {
            bookRepository.deleteById(event.getBookId());
        }
        else {
            throw new IllegalArgumentException("Book not found with id: " + event.getBookId());
        }
    }

    @EventHandler
    public void onBookUpdatedStatusEvent(BookUpdatedStatusEvent event) {
        Optional<Book> book = bookRepository.findById(event.getBookId());
        if(book.isPresent()) {
            Book updatedBook = book.get();
            updatedBook.setIsReady(event.getIsReady());
            bookRepository.save(updatedBook);
        }
        else {
            throw new IllegalArgumentException("Book not found with id: " + event.getBookId());
        }
    }
}
