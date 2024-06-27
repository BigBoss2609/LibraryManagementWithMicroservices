package com.baominh.bookservice.command.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddBookRequestModel {
    private String name;
    private String author;

    public AddBookRequestModel(String name, String author) {
        this.name = name;
        this.author = author;
    }
}
