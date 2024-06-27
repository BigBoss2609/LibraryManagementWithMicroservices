package com.baominh.bookservice.command.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookCreatedEvent {
    public String bookId;
    public String name;
    public String author;
    public Boolean isReady;
}
