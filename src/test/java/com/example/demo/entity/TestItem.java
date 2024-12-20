package com.example.demo.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class TestItem {

    @Test
    @DisplayName("nullable = false test")
    void StatusTest() {
        User user = new User();
        User owner = new User();
        Item item = new Item("testName", "testDescription", user, owner);

        assertNotNull(item.getStatus());
    }
}
