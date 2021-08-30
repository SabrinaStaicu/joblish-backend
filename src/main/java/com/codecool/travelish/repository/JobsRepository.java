package com.codecool.travelish.repository;

import com.codecool.travelish.model.job.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobsRepository extends JpaRepository<JobOffer, Long> {
}
