package com.baominh.borrowingservice.command.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "borrowings")
public class BorrowingData {
    @Id
    private String borrowingId;
    private String bookId;
    private String employeeId;
    private Date borrowDate;
    private Date returnDate;
}
