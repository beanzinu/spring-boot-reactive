package com.springbootreactive.springbootreactive.repository;

import com.springbootreactive.springbootreactive.domain.Item;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

@Component
public class TemplateDatabaseLoader {

    @Bean
    CommandLineRunner initialize(MongoOperations mongo){
        return args -> {
            mongo.save(new Item("item1","Alf alarm clock","alarm clock",19.99));
            mongo.save(new Item("item2","Smurf TV tray","TV tray",24.99));
        };
    }
}
