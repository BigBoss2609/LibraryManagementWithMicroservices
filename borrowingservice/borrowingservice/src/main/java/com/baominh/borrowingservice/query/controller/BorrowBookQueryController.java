package com.baominh.borrowingservice.query.controller;

import com.baominh.borrowingservice.query.model.BorrowingResponseModel;
import com.baominh.borrowingservice.query.query.GetBorrowingBookQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.axonframework.queryhandling.QueryGateway;

@RestController
@RequestMapping("/api/v1/borrowings")
public class BorrowBookQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/{borrowingId}")
    public BorrowingResponseModel GetBorrowingDetail(@PathVariable String borrowingId) {
        GetBorrowingBookQuery query = new GetBorrowingBookQuery(borrowingId);
        return queryGateway.query(query, BorrowingResponseModel.class).join();
    }
}
