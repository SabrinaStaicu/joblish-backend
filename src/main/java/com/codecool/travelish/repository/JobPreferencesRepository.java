package com.codecool.travelish.repository;

import com.codecool.travelish.model.user.JobPreferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface JobPreferencesRepository extends JpaRepository<JobPreferences, Long> {
    @Modifying
    @Transactional
    @Query("update JobPreferences u set u.openToWork = ?1 where u.id = ?2")
    void updateJobsPreferences(boolean lookingForJob, Long id);

}
