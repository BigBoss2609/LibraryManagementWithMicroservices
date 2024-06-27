package com.baominh.bookservice.command.command;

import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
public class DeleteBookCommand {
    @TargetAggregateIdentifier
    private String bookId;

    public DeleteBookCommand(String bookId) {
        this.bookId = bookId;
    }
}
