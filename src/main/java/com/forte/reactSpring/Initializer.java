package com.forte.reactSpring;



import com.forte.reactSpring.model.Event;
import com.forte.reactSpring.model.User;
import com.forte.reactSpring.model.Group;
import com.forte.reactSpring.model.GroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Component
class Initializer implements CommandLineRunner {

    private final GroupRepository repository;

    public Initializer(GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("Philadelphia", "NYC", "Seattle",
                "Richmond").forEach(name ->
                repository.save(new Group(name))
        );

        Group djug = repository.findByName("Philadelphia");
        Group nycGroup = repository.findByName("NYC");
        Event e = Event.builder().title("Full Stack Reactive")
                .description("Reactive with Spring Boot + React")
                .date(Instant.parse("2018-12-12T18:00:00.000Z"))
                .build();

        Event e2 = Event.builder().title("Example Art Event")
                    .description("Big time event baby!")
                    .date(Instant.now())
                    .build();



        djug.setEvents(Collections.singleton(e));
        nycGroup.setEvents(Collections.singleton(e2));
        repository.save(djug);
        repository.save(nycGroup);

        repository.findAll().forEach(System.out::println);
    }
}