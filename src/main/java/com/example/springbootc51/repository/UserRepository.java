package com.example.springbootc51.repository;

import com.example.springbootc51.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByUsername(String userName);

}
