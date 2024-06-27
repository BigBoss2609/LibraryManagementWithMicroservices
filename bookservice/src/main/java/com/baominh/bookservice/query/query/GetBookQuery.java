package com.baominh.bookservice.query.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetBookQuery {
    private String bookId;

    public GetBookQuery(String bookId) {
        this.bookId = bookId;
    }
}
