package com.baominh.borrowingservice.command.controller;

import com.baominh.borrowingservice.command.command.BorrowBookCommand;
import com.baominh.borrowingservice.command.command.DeleteBorrowBookCommand;
import com.baominh.borrowingservice.command.model.BorrowRequestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/borrowing")
public class BorrowBookCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String borrowBook(@RequestBody BorrowRequestModel model) {
        String borrowingId = UUID.randomUUID().toString();
        BorrowBookCommand command = new BorrowBookCommand(borrowingId, model.getBookId(), model.getEmployeeId(), new Date());
        commandGateway.sendAndWait(command);
        return "Book borrowed successfully!";
    }

    @DeleteMapping("/{borrowingId}")
    public String returnBook(@PathVariable String borrowingId) {
        if (borrowingId == null || borrowingId.isEmpty()) {
            throw new IllegalArgumentException("Borrowing id must not be null or empty");
        }
        DeleteBorrowBookCommand command = new DeleteBorrowBookCommand(borrowingId);
        commandGateway.sendAndWait(command);
        return "Book returned successfully!";
    }
}
