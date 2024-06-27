package com.baominh.borrowingservice.query.projection;

import com.baominh.borrowingservice.command.data.BorrowingData;
import com.baominh.borrowingservice.command.data.BorrowingRepository;
import com.baominh.borrowingservice.query.model.BorrowingResponseModel;
import com.baominh.borrowingservice.query.query.GetAllBorrowingBooksQuery;
import com.baominh.borrowingservice.query.query.GetBorrowingBooksByEmployeeQuery;
import com.baominh.commonservice.model.BookResponseCommonModel;
import com.baominh.commonservice.model.EmployeeResponseCommonModel;
import com.baominh.commonservice.query.GetBookDetailQuery;
import com.baominh.commonservice.query.GetEmployeeDetailQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BorrowingProjection {
    @Autowired
    private BorrowingRepository borrowingRepository;
    @Autowired
    private QueryGateway queryGateway;

    @QueryHandler
    public List<BorrowingResponseModel> handle(GetBorrowingBooksByEmployeeQuery query)
    {
        List<BorrowingData> borrowings = borrowingRepository.findByEmployeeId(query.getEmployeeId());
        List<BorrowingResponseModel> response = new ArrayList<>();
        for(BorrowingData borrowing : borrowings) {
            BorrowingResponseModel model = new BorrowingResponseModel();
            BeanUtils.copyProperties(borrowing, model);
            response.add(model);
        }
        return response;
    }

    @QueryHandler
    public List<BorrowingResponseModel> handle(GetAllBorrowingBooksQuery query)
    {
        List<BorrowingResponseModel> list  = new ArrayList<>();
        List<BorrowingData> listEntity = borrowingRepository.findAll();
        listEntity.forEach(s ->{
            BorrowingResponseModel model = new BorrowingResponseModel();
            BeanUtils.copyProperties(s, model);
            model.setBookName(queryGateway.query(new GetBookDetailQuery(model.getBookId()), ResponseTypes.instanceOf(BookResponseCommonModel.class)).join().getName());
            EmployeeResponseCommonModel employee = queryGateway.query(new GetEmployeeDetailQuery(model.getEmployeeId()), ResponseTypes.instanceOf(EmployeeResponseCommonModel.class)).join();
            model.setEmployeeName(employee.getFirstName()+" "+employee.getLastName());
            list.add(model);
        });
        return list;
    }
}
