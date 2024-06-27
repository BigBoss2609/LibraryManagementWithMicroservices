package com.baominh.borrowingservice.command.event;

import com.baominh.borrowingservice.command.data.BorrowingData;
import com.baominh.borrowingservice.command.data.BorrowingRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BorrowingEventHandler {
    @Autowired
    private BorrowingRepository borrowingRepository;

    @EventHandler
    public void handle(BookBorrowedEvent event) {
        BorrowingData data = new BorrowingData();
        BeanUtils.copyProperties(event, data);
        borrowingRepository.save(data);
    }

    @EventHandler
    public void handle(BookBorrowingDeletedEvent event) {
        Optional<BorrowingData> data = borrowingRepository.findById(event.getBorrowingId());
        if(data.isPresent()) {
            borrowingRepository.deleteById(event.getBorrowingId());
        }
        else {
            throw new IllegalArgumentException("Borrowing not found with id: " + event.getBorrowingId());
        }
    }
}
