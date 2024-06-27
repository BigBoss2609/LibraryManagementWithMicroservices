package com.baominh.borrowingservice.command.saga;

import com.baominh.borrowingservice.command.command.DeleteBorrowBookCommand;
import com.baominh.borrowingservice.command.event.BookBorrowedEvent;
import com.baominh.commonservice.command.UpdateStatusBookCommand;
import com.baominh.commonservice.query.GetBookDetailQuery;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import com.baominh.commonservice.model.BookResponseCommonModel;


@Saga
public class BorrowingSaga {
    @Autowired
    private transient CommandGateway commandGateway;
    @Autowired
    private transient QueryGateway queryGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "borrowingId")
    private void handle(BookBorrowedEvent event) {
        System.out.println("BorrowCreatedEvent in Saga for BookId : "+ event.getBookId()+ " : EmployeeId :  "+ event.getEmployeeId());

        try {
            SagaLifecycle.associateWith("bookId", event.getBookId());

            GetBookDetailQuery getDetailsBookQuery = new GetBookDetailQuery(event.getBookId());

            BookResponseCommonModel bookResponseModel =
                    queryGateway.query(getDetailsBookQuery,
                                    ResponseTypes.instanceOf(BookResponseCommonModel.class))
                            .join();
            if(bookResponseModel.getIsReady()) {
                UpdateStatusBookCommand command = new UpdateStatusBookCommand(event.getBookId(), false,event.getEmployeeId(),event.getBorrowingId());
                commandGateway.sendAndWait(command);
            }
            else {

                throw new Exception("Sach Da co nguoi Muon");
            }


        } catch (Exception e) {
            rollBackBorrowRecord(event.getBorrowingId());

            System.out.println(e.getMessage());
        }
    }
    private void rollBackBorrowRecord(String borrowingId) {
        commandGateway.sendAndWait(new DeleteBorrowBookCommand(borrowingId));
    }
}
