package com.baominh.borrowingservice.command.aggregate;

import com.baominh.borrowingservice.command.command.BorrowBookCommand;
import com.baominh.borrowingservice.command.command.DeleteBorrowBookCommand;
import com.baominh.borrowingservice.command.event.BookBorrowedEvent;
import com.baominh.borrowingservice.command.event.BookBorrowingDeletedEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Aggregate
public class BorrowingAggregate {
    @AggregateIdentifier
    private String borrowingId;

    private String bookId;
    private String employeeId;
    private Date borrowDate;
    private Date returnDate;

   @CommandHandler
    public BorrowingAggregate(BorrowBookCommand command) {
       BookBorrowedEvent event = new BookBorrowedEvent();
       BeanUtils.copyProperties(command, event);
       AggregateLifecycle.apply(event);
    }
    @CommandHandler
    public void handle(DeleteBorrowBookCommand command) {
        BookBorrowingDeletedEvent event = new BookBorrowingDeletedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(BookBorrowedEvent event) {
        this.borrowingId = event.getBorrowingId();
        this.bookId = event.getBookId();
        this.employeeId = event.getEmployeeId();
        this.borrowDate = event.getBorrowDate();
        this.returnDate = event.getReturnDate();
    }
    @EventSourcingHandler
    public void on(BookBorrowingDeletedEvent event) {
       this.borrowingId = event.getBorrowingId();
    }
}
