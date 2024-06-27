package com.baominh.bookservice.config;

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
                "com.baominh.bookservice.event.BookEventHandler",
                "com.baominh.commonservice.query.GetBookDetailQuery",
                "event.BookUpdatedStatusEvent"
        });
        return xStream;
    }
}
