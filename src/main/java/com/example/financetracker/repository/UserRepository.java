package com.example.financetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.financetracker.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
