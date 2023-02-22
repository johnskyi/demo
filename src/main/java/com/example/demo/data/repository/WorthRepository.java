package com.example.demo.data.repository;

import com.example.demo.data.entity.Worth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorthRepository extends JpaRepository<Worth, Long> {
}
