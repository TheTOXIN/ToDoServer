package com.toxin.todo.service;

import com.toxin.todo.dto.ToDoDtoRequest;
import com.toxin.todo.dto.ToDoDtoResponse;
import com.toxin.todo.entity.Task;
import com.toxin.todo.entity.User;
import com.toxin.todo.repository.TaskRepository;
import com.toxin.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ToDoServiceImpl implements ToDoService {

    private final List<String> messages = Arrays.asList(
        "Hello %s",
        "Hi %s",
        "Welcome %s",
        "%s you so cute ",
        "Very nice %s",
        "Amazing day! %s",
        "Go ToDo %s",
        "You need more ToDo, %s",
        "All right %s",
        "%s! I'm glad to see you.",
        "How are you? %s",
        "Omg, %s it's you",
        "Yeeeah %s",
        "Please %s visit my GtiHub",
        "How many Todo %s ?",
        "JUST DO IT %s !!!"
    );

    private final Random rand = new Random();

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public ToDoServiceImpl(
        TaskRepository taskRepository,
        UserRepository userRepository
    ) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public ToDoDtoResponse todo(ToDoDtoRequest request) {
        String login = userRepository.getLoginById(request.getUserId());
        String message = messages.get(rand.nextInt(messages.size()));

        return new ToDoDtoResponse(taskRepository.findByUser_Id(request.getUserId())
            .stream()
            .sorted(Comparator.comparing(Task::getDate))
            .map(Task::getId)
            .collect(Collectors.toList()),
            String.format(message, login)
        );
    }

}
