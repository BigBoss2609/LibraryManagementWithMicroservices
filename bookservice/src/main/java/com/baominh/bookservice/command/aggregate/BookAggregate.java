package com.baominh.bookservice.command.aggregate;


import com.baominh.bookservice.command.command.AddBookCommand;
import com.baominh.bookservice.command.command.DeleteBookCommand;
import com.baominh.bookservice.command.command.UpdateBookCommand;
import com.baominh.bookservice.command.event.BookCreatedEvent;
import com.baominh.bookservice.command.event.BookDeletedEvent;
import com.baominh.bookservice.command.event.BookUpdatedEvent;
import com.baominh.commonservice.command.UpdateStatusBookCommand;
import event.BookUpdatedStatusEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor
@AllArgsConstructor
@Aggregate
@Getter
@Setter
public class BookAggregate {
    @AggregateIdentifier
    public String bookId;
    public String name;
    public String author;
    public Boolean isReady;

    @CommandHandler
    public BookAggregate(AddBookCommand command) {
        BookCreatedEvent event = new BookCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdateBookCommand command) {
        BookUpdatedEvent event = new BookUpdatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(DeleteBookCommand command) {
        BookDeletedEvent event = new BookDeletedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }
    @CommandHandler
    public void handle(UpdateStatusBookCommand command) {
        BookUpdatedStatusEvent event = new BookUpdatedStatusEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }
    @EventSourcingHandler
    public void on(BookCreatedEvent event) {
        this.bookId = event.getBookId();
        this.name = event.getName();
        this.author = event.getAuthor();
        this.isReady = event.getIsReady();
    }

    @EventSourcingHandler
    public void on(BookUpdatedEvent event) {
        this.bookId = event.getBookId();
        this.name = event.getName();
        this.author = event.getAuthor();
        this.isReady = event.getIsReady();
    }

    @EventSourcingHandler
    public void on(BookDeletedEvent event) {
        this.bookId = event.getBookId();
    }
    @EventSourcingHandler
    public void on(BookUpdatedStatusEvent event) {
        this.bookId = event.getBookId();
        this.isReady = event.getIsReady();
    }
}
