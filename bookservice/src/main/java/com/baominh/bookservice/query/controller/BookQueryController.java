package com.baominh.bookservice.query.controller;

import com.baominh.bookservice.query.model.GetBookResponseModel;
import com.baominh.bookservice.query.query.GetAllBooksQuery;
import com.baominh.bookservice.query.query.GetBookQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/{bookId}")
    public GetBookResponseModel GetBookDetail(@PathVariable String bookId) {
        GetBookQuery query = new GetBookQuery(bookId);
        return queryGateway.query(query, GetBookResponseModel.class).join();
    }

    @GetMapping
    public List<GetBookResponseModel> GetAllBooks(){
        GetAllBooksQuery query = new GetAllBooksQuery();
        return queryGateway.query(query, ResponseTypes.multipleInstancesOf(GetBookResponseModel.class)).join();
    }
}
