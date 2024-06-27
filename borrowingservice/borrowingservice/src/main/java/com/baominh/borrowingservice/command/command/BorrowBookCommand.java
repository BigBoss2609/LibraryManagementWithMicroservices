package com.baominh.borrowingservice.command.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class BorrowBookCommand {
    private String borrowingId;
    private String bookId;
    private String employeeId;
    private Date borrowDate;

    public BorrowBookCommand(String borrowingId, String bookId, String employeeId, Date borrowDate) {
        this.borrowingId = borrowingId;
        this.bookId = bookId;
        this.employeeId = employeeId;
        this.borrowDate = borrowDate;
    }
}
