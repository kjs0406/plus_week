package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    default User findByIdOrElseThrow(Long userId) {
        return findById(userId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 유저 id 입니다.")
        );
    }

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.status = 'BLOCKED' WHERE u.id IN :userIds")
    int blockedStatusWithUserIds(@Param("userIds") List<Long> userIds);
}
