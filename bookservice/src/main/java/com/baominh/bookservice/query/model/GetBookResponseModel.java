package com.baominh.bookservice.query.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetBookResponseModel {
    private String bookId;
    private String name;
    private String author;
    private Boolean isReady;

}
