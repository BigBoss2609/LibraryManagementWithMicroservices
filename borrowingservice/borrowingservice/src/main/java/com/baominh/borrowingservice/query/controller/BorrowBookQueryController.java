package com.baominh.borrowingservice.query.controller;

import com.baominh.borrowingservice.query.model.BorrowingResponseModel;
import com.baominh.borrowingservice.query.query.GetAllBorrowingBooksQuery;
import com.baominh.borrowingservice.query.query.GetBorrowingBookQuery;
import com.baominh.borrowingservice.query.query.GetBorrowingBooksByEmployeeQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.axonframework.queryhandling.QueryGateway;

import java.util.List;

@RestController
@RequestMapping("/api/v1/borrowing")
public class BorrowBookQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/{borrowingId}")
    public BorrowingResponseModel getBorrowingDetail(@PathVariable String borrowingId) {
        GetBorrowingBookQuery query = new GetBorrowingBookQuery(borrowingId);
        return queryGateway.query(query, BorrowingResponseModel.class).join();
    }

    @GetMapping("/{employeeId}")
    public List<BorrowingResponseModel> getBorrowingByEmployee(@PathVariable String employeeId) {
        GetBorrowingBooksByEmployeeQuery query = new GetBorrowingBooksByEmployeeQuery(employeeId);
        return queryGateway.query(query, ResponseTypes.multipleInstancesOf(BorrowingResponseModel.class)).join();
    }

    @GetMapping
    public List<BorrowingResponseModel> getAllBorrowing() {
        GetAllBorrowingBooksQuery query = new GetAllBorrowingBooksQuery();
        return queryGateway.query(query, ResponseTypes.multipleInstancesOf(BorrowingResponseModel.class)).join();
    }
}
