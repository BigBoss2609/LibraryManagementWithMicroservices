package com.baominh.borrowingservice.command.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookBorrowingDeletedEvent {
    private String borrowingId;
}
