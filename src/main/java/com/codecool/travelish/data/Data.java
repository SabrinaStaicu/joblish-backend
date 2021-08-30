package com.codecool.travelish.data;

import com.codecool.travelish.model.job.ExperienceType;
import com.codecool.travelish.model.job.JobOffer;
import com.codecool.travelish.repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Data implements CommandLineRunner {
    private JobsRepository jobsRepository;

    @Autowired
    public Data(JobsRepository jobsRepository){
        this.jobsRepository = jobsRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        JobOffer jobOffer = new JobOffer("JOB1", LocalDate.now(),"Romania","Remote", ExperienceType.JUNIOR,"Microsoft","no-image","It","asdasdasd",99,"Bucharest");
        jobsRepository.save(jobOffer);
    }
}
