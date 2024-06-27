package com.baominh.commonservice.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@NoArgsConstructor
public class UpdateStatusBookCommand {
    @TargetAggregateIdentifier
    private String bookId;
    private Boolean isReady;
    private String employeeId;
    private String borrowId;

    public UpdateStatusBookCommand(String bookId, Boolean isReady, String employeeId, String borrowId) {
        this.bookId = bookId;
        this.isReady = isReady;
        this.employeeId = employeeId;
        this.borrowId = borrowId;
    }
}
