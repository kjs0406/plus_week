package com.example.demo.service;

import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {
    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // TODO: 4. find or save 예제 개선
    @Transactional
    public void reportUsers(List<Long> userIds) {

        int updateRow = userRepository.blockedStatusWithUserIds(userIds);

        if (updateRow != userIds.size()) {
            throw new IllegalArgumentException("해당 ID 중에 존재하지 않는 값이 있습니다.");
        }
    }
}
