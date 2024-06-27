package com.baominh.commonservice.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetBookDetailQuery {
    private String bookId;

    public GetBookDetailQuery(String bookId) {
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }
}
