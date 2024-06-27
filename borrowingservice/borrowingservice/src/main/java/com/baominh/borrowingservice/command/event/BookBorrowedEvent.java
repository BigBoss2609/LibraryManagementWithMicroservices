package com.baominh.borrowingservice.command.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class BookBorrowedEvent {
    private String borrowingId;
    private String bookId;
    private String employeeId;
    private Date borrowDate;
    private Date returnDate;

}
