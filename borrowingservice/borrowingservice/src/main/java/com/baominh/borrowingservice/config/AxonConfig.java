package com.baominh.borrowingservice.config;

import com.thoughtworks.xstream.XStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {
    @Bean
    public XStream xStream() {
        XStream xStream = new XStream();
        xStream.allowTypesByWildcard(new String[] {
                "com.baominh.**",
                "com.baominh.borrowingservice.command.command.BorrowBookCommand",
                "com.baominh.borrowingservice.command.event.BookBorrowedEvent",
                "com.baominh.commonservice.model.BookResponseCommonModel",
                "com.baominh.borrowingservice.command.command.DeleteBorrowBookCommand",
                "event.BookUpdatedStatusEvent",
                "com.baominh.commonservice.query.GetBookDetailQuery"
        });
        return xStream;
    }
}
