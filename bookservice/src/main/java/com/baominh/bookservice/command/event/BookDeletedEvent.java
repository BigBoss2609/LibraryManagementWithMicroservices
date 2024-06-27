package com.baominh.bookservice.command.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookDeletedEvent {
    private String bookId;
}
