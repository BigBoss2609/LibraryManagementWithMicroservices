package com.baominh.borrowingservice.command.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BorrowRequestModel {
    private String bookId;
    private String employeeId;
}
