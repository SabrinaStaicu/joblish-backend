package com.codecool.travelish.repository;

import com.codecool.travelish.model.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findAllByAppUserId(Long id);
}
