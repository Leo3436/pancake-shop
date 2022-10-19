package com.example.pancakeshop.pancake;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PancakeRepository extends JpaRepository<Pancake, Long> {
}
