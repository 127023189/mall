package org.yixi.mallcore.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class JacksonConfig {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public Jackson2ObjectMapperBuilderCustomizer customizer(){
            return new Jackson2ObjectMapperBuilderCustomizer() {
                @Override
                public void customize(Jackson2ObjectMapperBuilder builder) {
                     //序列化
                    builder.serializerByType(LocalDateTime.class,
                            new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    builder.serializerByType(LocalDate.class,
                            new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    builder.serializerByType(LocalTime.class,
                            new LocalDateSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
                    //反序列化
                    builder.deserializerByType(LocalDateTime.class,
                            new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    builder.deserializerByType(LocalDate.class,
                            new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    builder.deserializerByType(LocalTime.class,
                            new LocalDateDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
                }
            };
    }
}
