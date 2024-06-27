package com.baominh.bookservice.command.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateBookRequestModel {
    private String name;
    private String author;
    private Boolean isReady;

    public UpdateBookRequestModel(String name, String author, Boolean isReady) {
        this.name = name;
        this.author = author;
        this.isReady = isReady;
    }
}
