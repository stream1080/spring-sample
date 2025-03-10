package com.example.demo.task;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

/**
 * @author stream1080
 * @date 2023-07-29 22:01:39
 */
@Slf4j
@Component
@CanalTable(value = "user")
@RequiredArgsConstructor
public class UserHandler implements EntryHandler<User> {

    private final UserMapper demoUserMapper;

    public void insert(User user) {
        log.info("insert message  {}", user);
        demoUserMapper.insert(user);
    }

    public void update(User before, User after) {
        log.info("update before {} ", before);
        log.info("update after {}", after);
    }

    public void delete(User user) {
        log.info("delete  {}", user);
    }
}

