package com.codecool.travelish.repository;

import com.codecool.travelish.model.application.Application;
import com.codecool.travelish.model.application.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findAllByAppUserId(Long id);
    List<Application> findAllByJobId(Long id);

    @Modifying
    @Transactional
    @Query("update application ap set ap.status = ?1  where ap.id = ?2")
    void updateStatus(ApplicationStatus status, Long id);
}
