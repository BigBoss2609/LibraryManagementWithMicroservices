package com.baominh.bookservice.query.projection;

import com.baominh.bookservice.command.data.Book;
import com.baominh.bookservice.command.data.BookRepository;
import com.baominh.bookservice.query.model.GetBookResponseModel;
import com.baominh.bookservice.query.query.GetAllBooksQuery;
import com.baominh.bookservice.query.query.GetBookQuery;
import com.baominh.commonservice.model.BookResponseCommonModel;
import com.baominh.commonservice.query.GetBookDetailQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BookProjection {
    @Autowired
    private BookRepository bookRepository;

    @QueryHandler
    public BookResponseCommonModel handle(GetBookDetailQuery getDetailsBookQuery) {
        BookResponseCommonModel model = new BookResponseCommonModel();
        Optional<Book> book = bookRepository.findById(getDetailsBookQuery.getBookId());
        if(book.isEmpty()) {
            throw new IllegalArgumentException("Book not found with id: " + getDetailsBookQuery.getBookId());
        }
        BeanUtils.copyProperties(book.get(), model);

        return model;
    }
    @QueryHandler
    public GetBookResponseModel handle(GetBookQuery query) {
       Optional<Book> book = bookRepository.findById(query.getBookId());
       if(book.isPresent()) {
              Book bookDetail = book.get();
              GetBookResponseModel response = new GetBookResponseModel();
              BeanUtils.copyProperties(bookDetail, response);
              return response;
         }
         else {
              throw new IllegalArgumentException("Book not found with id: " + query.getBookId());
       }
    }

    @QueryHandler
    public List<GetBookResponseModel> handle(GetAllBooksQuery query) {
        List<Book> books = bookRepository.findAll();
        List<GetBookResponseModel> response = new ArrayList<>();
        for(Book book : books) {
            GetBookResponseModel model = new GetBookResponseModel();
            BeanUtils.copyProperties(book, model);
            response.add(model);
        }
        return response;
    }
}
