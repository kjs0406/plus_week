package com.example.demo.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class TestPasswordEncoder {

    @Test
    void matches() {
        String testPassword = "testPassword";
        String encodedPassword = PasswordEncoder.encode(testPassword);

        assertTrue(PasswordEncoder.matches(testPassword, encodedPassword));
    }
}
