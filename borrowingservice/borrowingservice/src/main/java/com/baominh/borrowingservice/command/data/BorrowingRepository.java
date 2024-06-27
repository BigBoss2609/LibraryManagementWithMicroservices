package com.baominh.borrowingservice.command.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowingRepository extends JpaRepository<BorrowingData, String> {
    List<BorrowingData> findByEmployeeId(String employeeId);
    BorrowingData findByEmployeeIdAndBookId(String employeeId, String bookId);

}
