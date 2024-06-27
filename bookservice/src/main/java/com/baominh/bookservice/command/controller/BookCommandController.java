package com.baominh.bookservice.command.controller;

import com.baominh.bookservice.command.command.AddBookCommand;
import com.baominh.bookservice.command.command.DeleteBookCommand;
import com.baominh.bookservice.command.command.UpdateBookCommand;
import com.baominh.bookservice.command.model.AddBookRequestModel;
import com.baominh.bookservice.command.model.UpdateBookRequestModel;
import  org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
public class BookCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String addBook(@RequestBody AddBookRequestModel model) {
        String bookId = UUID.randomUUID().toString();
        AddBookCommand command = new AddBookCommand(bookId, model.getName(), model.getAuthor(), true);
        commandGateway.sendAndWait(command);
        return bookId;
    }

    @PutMapping("/{bookId}")
    public String updateBook(@PathVariable String bookId, @RequestBody UpdateBookRequestModel model) {
        if (bookId == null || bookId.isEmpty()) {
            throw new IllegalArgumentException("Book id must not be null or empty");
        }
        UpdateBookCommand command = new UpdateBookCommand(bookId, model.getName(), model.getAuthor(), model.getIsReady());
        commandGateway.sendAndWait(command);
        return "Updated successfully!";
    }

    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable String bookId) {
        if (bookId == null || bookId.isEmpty()) {
            throw new IllegalArgumentException("Book id must not be null or empty");
        }
        DeleteBookCommand command = new DeleteBookCommand(bookId);
        commandGateway.sendAndWait(command);
        return "Deleted successfully!";
    }
}
