package com.baominh.borrowingservice.command.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeleteBorrowBookCommand {
    private String borrowingId;

    public DeleteBorrowBookCommand(String borrowingId) {
        this.borrowingId = borrowingId;
    }
}
