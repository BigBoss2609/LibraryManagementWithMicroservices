package com.baominh.bookservice.command.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@NoArgsConstructor
public class AddBookCommand {
    @TargetAggregateIdentifier
    private String bookId;
    private String name;
    private String author;
    private Boolean isReady;


    public AddBookCommand(String bookId, String name, String author, Boolean isReady) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.isReady = isReady;
    }
}
