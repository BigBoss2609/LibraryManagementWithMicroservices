package com.baominh.borrowingservice.query.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class BorrowingResponseModel {
    private String borrowingId;
    private String bookId;
    private String employeeId;
    private Date borrowDate;
    private Date returnDate;
    private String bookName;
    private String employeeName;
}
